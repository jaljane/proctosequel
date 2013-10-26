
package org.proctosequel.parsing.commands;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.proctosequel.antlr.ProcToSequelGrammarParser;
import org.proctosequel.antlr.ProcToSequelGrammarParser.InstContext;
import org.proctosequel.parsing.exception.SemanticsError;
import org.proctosequel.parsing.exception.SyntaxError;
import org.proctosequel.parsing.om.Query;
import org.proctosequel.parsing.om.composite.AliasedData;
import org.proctosequel.parsing.om.composite.Column;
import org.proctosequel.parsing.om.composite.TableJoinExpr;
import org.proctosequel.parsing.visitors.VarNamesVisitor;
import org.proctosequel.parsing.utils.Errors;
import org.proctosequel.parsing.utils.ProctosequelHelper;
import org.proctosequel.parsing.utils.QueryPaseHelper;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class ReadQueriesCommand  implements Command {
    public static Logger log = Logger.getLogger(ReadQueriesCommand.class);
    private Map<String, Query> queries = new LinkedHashMap<>();
    private ProcToSequelGrammarParser.ProgContext progTree;

    public ReadQueriesCommand(ProcToSequelGrammarParser.ProgContext progTree) {
        this.progTree = progTree;
    }
    
    public void execute(){
        for(InstContext instContext : progTree.inst()){
            // get sql part trees.
            if (instContext.setvar()!= null && !instContext.setvar().isEmpty()){
                ProcToSequelGrammarParser.SelectStmtContext selectStmtContext = instContext.setvar().selectStmt();
                if(selectStmtContext==null || selectStmtContext.isEmpty()){
                    throw new SyntaxError(instContext.setvar().VarName().getText(), Errors.set_var_error_msg);
                }
                if(  "(".equals(selectStmtContext.getChild(0).getText())){
                    throw new SyntaxError(instContext.setvar().VarName().getText(), Errors.set_var_unsupported_parenthesis_error_msg);
                }
                boolean selectpart = false;
                boolean frompart = false;
                boolean wherepart = false;
                boolean groupbypart = false;
                Query query = new Query();
                query.setIdentifier(instContext.setvar().VarName().getText());
                for(int i = 0;i<selectStmtContext.getChildCount();i++){
                    String token = selectStmtContext.getChild(i).getText();                    
                    if("select".equals(token)){
                        selectpart = true;
                        frompart = false;
                        wherepart = false;
                        groupbypart = false;                        
                        continue;
                    }
                    if("from".equals(token)){
                        selectpart = false;
                        frompart = true;
                        wherepart = false;
                        groupbypart = false;                        
                        continue;
                    }      
                    if("where".equals(token)){
                        selectpart = false;
                        frompart = false;
                        wherepart = true;
                        groupbypart = false;                        
                        continue;
                    }     
                    if("group".equals(token)){
                        selectpart = false;
                        frompart = false;
                        wherepart = false;
                        groupbypart = selectStmtContext.getChild(i+1)!=null && "by".equals(selectStmtContext.getChild(i+1).getText() ) ;                        
                        continue;
                    }                      
                    if("by".equals(token) && groupbypart){
                         continue;
                    }
                    if(selectpart){
                        query.setSelectPart(selectStmtContext.getChild(i));
                    }
                    if(frompart){
                        query.setFromPart(selectStmtContext.getChild(i));
                    }        
                    if(wherepart){
                        query.setWherePart(selectStmtContext.getChild(i));
                    }        
                    if(groupbypart){
                        query.setGroupPart(selectStmtContext.getChild(i));
                    }  
                    
                }
                System.out.println(query); 
                queries.put(instContext.setvar().VarName().getText(), query);
            }
        }
        
        // get Dependencies
        for(Query query : queries.values()){
            VarNamesVisitor dependencyVisitor = new VarNamesVisitor();
            if(query.getSelectPart()!=null){
                dependencyVisitor.visit(query.getSelectPart());
            }
            if(query.getFromPart()!=null){
                dependencyVisitor.visit(query.getFromPart());
            }
            if(query.getWherePart()!=null){
                dependencyVisitor.visit(query.getWherePart());
            }
            if(query.getGroupPart()!=null){
                dependencyVisitor.visit(query.getGroupPart());
            }
            
            for(String varname : dependencyVisitor.getVarNames()){
                if(queries.get(varname)!=null){                    
                    query.getDependsOn().add(queries.get(varname));
                }else {
                    throw new SemanticsError(String.format(Errors.unkown_varname, varname));
                }
            }
        }
        
        // get columns
        for(Query query : queries.values()){
            List<String> selectParts = QueryPaseHelper.getSepCommaTokens(query.getIdentifier(), (ProcToSequelGrammarParser.SqlPartContext) query.getSelectPart());
            for(String selectPart : selectParts){
                AliasedData aliasedData= QueryPaseHelper.getAliasedData(query.getIdentifier(), ProctosequelHelper.parseSqlPart(selectPart));
                query.getColumns().add(new Column(aliasedData));
                log.debug(new Column(aliasedData) + " added");
            }
        } 
        
        // get tables and joins
        for(Query query : queries.values()){
               List<TableJoinExpr> tableJoinExprs = QueryPaseHelper.getTableJoinExpr(query.getIdentifier(), (ProcToSequelGrammarParser.SqlPartContext) query.getFromPart());
               query.getTableJoinExprs().addAll(tableJoinExprs);
        }
        
        for(Query query : queries.values()){
            query.getConditions().addAll(QueryPaseHelper.getQueryConditions(query.getIdentifier(), (ProcToSequelGrammarParser.SqlPartContext) query.getWherePart()));
        }
        for(Query query : queries.values()){
            query.setGroupBy(QueryPaseHelper.getQueryGroupBy(query.getIdentifier(),  (ProcToSequelGrammarParser.SqlPartContext) query.getGroupPart()));
        }
        log.debug(queries);
    }

    /**
     * @return the queries
     */
    public Map<String, Query> getQueries() {
        return queries;
    }

    /**
     * @return the progTree
     */
    public ProcToSequelGrammarParser.ProgContext getProgTree() {
        return progTree;
    }
    

   
    
}
