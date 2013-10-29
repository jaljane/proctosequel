/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.main;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.proctosequel.Command;
import org.proctosequel.antlr.ProcToSequelGrammarParser;
import org.proctosequel.parsing.commands.nested.ParseQueryCommand;
import org.proctosequel.parsing.om.Query;
import org.proctosequel.parsing.om.composite.Column;
import org.proctosequel.parsing.om.composite.Condition;
import org.proctosequel.parsing.om.composite.TableJoinExpr;
import org.proctosequel.parsing.utils.ProctosequelHelper;
import org.proctosequel.parsing.utils.QueryPaseHelper;
import org.proctosequel.parsing.visitors.StoreNestedQueriesVisitor;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class EvaluateQueryCommand implements Command{

    private Query query;
    private Query result;

    public EvaluateQueryCommand(Query query) {
        this.query = query;
    }
    
    @Override
    public void execute() {
        
        for(TableJoinExpr tableJoinExpr : query.getTableJoinExprs()){
            if(tableJoinExpr.getTable()!=null){
                ProcToSequelGrammarParser.SqlPartContext sqlPartContext = ProctosequelHelper.parseSqlPart(tableJoinExpr.getTable().getExpr());
                if(QueryPaseHelper.hasSelectStmt(sqlPartContext)){
                    String expr = evaluateNestedQueries(sqlPartContext);
                }
            }
        }
        
        for(Column column : query.getColumns() ){
            ProcToSequelGrammarParser.SqlPartContext sqlPart = ProctosequelHelper.parseSqlPart(column.getExpr());
            if(QueryPaseHelper.hasSelectStmt(sqlPart)){
                String expr = evaluateNestedQueries(sqlPart);
                result.getColumns().add(new Column(column.getAlias(), expr));                
            }else{
                throw new UnsupportedOperationException("do it!");
//                result.getColumns().add(column);
            }
        }

        for(Condition condition : query.getConditions()){
            throw new UnsupportedOperationException("do it!");
        }
        throw new UnsupportedOperationException("do it!");
        
    }

    
    private String evaluateNestedQueries(ProcToSequelGrammarParser.SqlPartContext sqlPart){
        Map<String, String> nestedQueryResult = new HashMap<>();
        StoreNestedQueriesVisitor storeNestedQueriesVisitor = new StoreNestedQueriesVisitor();
        storeNestedQueriesVisitor.visit(sqlPart);
        if(!storeNestedQueriesVisitor.getTokenContent().isEmpty()){
            for(Map.Entry<String, ProcToSequelGrammarParser.SelectStmtContext> nested : storeNestedQueriesVisitor.getNestedQueryByToken().entrySet()){
                ParseQueryCommand parseQueryCommand = new ParseQueryCommand("$$", nested.getValue());
                parseQueryCommand.execute();                    
                EvaluateQueryCommand evaluateQueryCommand = new EvaluateQueryCommand(parseQueryCommand.getQuery());
                evaluateQueryCommand.execute();
                nestedQueryResult.put(nested.getKey(), evaluateQueryCommand.getResult().getSQL());                        
            }
        }
        String expr = storeNestedQueriesVisitor.getExpr();
        for(Map.Entry<String, String> entry : nestedQueryResult.entrySet()){
            expr = StringUtils.replace(expr, entry.getKey(), entry.getValue());
        }
        return expr;
    } 
    
    /**
     * @return the result
     */
    public Query getResult() {
        return result;
    }

}
