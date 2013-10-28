/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proctosequel.parsing.commands.nested;

import java.util.List;
import org.proctosequel.Command;
import org.proctosequel.antlr.ProcToSequelGrammarParser;
import static org.proctosequel.parsing.commands.ReadQueriesCommand.log;
import org.proctosequel.parsing.om.Query;
import org.proctosequel.parsing.om.composite.AliasedData;
import org.proctosequel.parsing.om.composite.Column;
import org.proctosequel.parsing.om.composite.TableJoinExpr;
import org.proctosequel.parsing.utils.ProctosequelHelper;
import org.proctosequel.parsing.utils.QueryPaseHelper;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class ParseQueryCommand implements Command{
    
    private ProcToSequelGrammarParser.SelectStmtContext select;
    private String identifier;
    private Query query;

    public ParseQueryCommand(String identifier, ProcToSequelGrammarParser.SelectStmtContext select) {
        this.select = select;
        this.identifier = identifier;
    }
    
    @Override
    public void execute() {
        boolean selectpart = false;
        boolean frompart = false;
        boolean wherepart = false;
        boolean groupbypart = false;
        query = new Query();
        query.setIdentifier(identifier);
        for(int i = 0;i<select.getChildCount();i++){
            String token = select.getChild(i).getText();                    
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
                groupbypart = select.getChild(i+1)!=null && "by".equals(select.getChild(i+1).getText() ) ;                        
                continue;
            }                      
            if("by".equals(token) && groupbypart){
                 continue;
            }
            if(selectpart){
                query.setSelectPart(select.getChild(i));
            }
            if(frompart){
                query.setFromPart(select.getChild(i));
            }        
            if(wherepart){
                query.setWherePart(select.getChild(i));
            }        
            if(groupbypart){
                query.setGroupPart(select.getChild(i));
            }  

        }
        List<String> selectParts = QueryPaseHelper.getSepCommaTokens(query.getIdentifier(), (ProcToSequelGrammarParser.SqlPartContext) query.getSelectPart());
        for(String selectPart : selectParts){
            AliasedData aliasedData= QueryPaseHelper.getAliasedData(query.getIdentifier(), ProctosequelHelper.parseSqlPart(selectPart));
            query.getColumns().add(new Column(aliasedData));
            log.debug(new Column(aliasedData) + " added");
        }

        // get tables and joins
        List<TableJoinExpr> tableJoinExprs = QueryPaseHelper.getTableJoinExpr(query.getIdentifier(), (ProcToSequelGrammarParser.SqlPartContext) query.getFromPart());
        query.getTableJoinExprs().addAll(tableJoinExprs);
        query.getConditions().addAll(QueryPaseHelper.getQueryConditions(query.getIdentifier(), (ProcToSequelGrammarParser.SqlPartContext) query.getWherePart()));
        query.setGroupBy(QueryPaseHelper.getQueryGroupBy(query.getIdentifier(),  (ProcToSequelGrammarParser.SqlPartContext) query.getGroupPart()));
    }

    /**
     * @return the query
     */
    public Query getQuery() {
        return query;
    }
    
}
