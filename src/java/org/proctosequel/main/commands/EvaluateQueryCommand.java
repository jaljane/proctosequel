/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.main.commands;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.proctosequel.Command;
import org.proctosequel.antlr.ProcToSequelGrammarParser;
import org.proctosequel.parsing.QueryDefCache;
import org.proctosequel.main.exception.QueryEvalException;
import org.proctosequel.main.utils.QueryEvalHelper;
import org.proctosequel.parsing.commands.nested.ParseQueryCommand;
import org.proctosequel.parsing.om.Query;
import org.proctosequel.parsing.om.composite.Column;
import org.proctosequel.parsing.om.composite.Condition;
import org.proctosequel.parsing.om.composite.Table;
import org.proctosequel.parsing.om.composite.TableJoinExpr;
import org.proctosequel.parsing.utils.ProctosequelHelper;
import org.proctosequel.parsing.utils.QueryParseHelper;
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
        try{
            for(TableJoinExpr tableJoinExpr : query.getTableJoinExprs()){
                if(tableJoinExpr.getTable()!=null){
                    ProcToSequelGrammarParser.SqlPartContext sqlPartContext = ProctosequelHelper.parseSqlPart(tableJoinExpr.getTable().getExpr());
                    if(QueryParseHelper.hasSelectStmt(sqlPartContext)){
                        String expr = QueryEvalHelper.evaluateNestedQueries(sqlPartContext);
                        TableJoinExpr tableJoinExpr1 = new TableJoinExpr();
                        tableJoinExpr1.setTable(new Table(tableJoinExpr.getTable().getAlias(), expr));
                        result.getTableJoinExprs().add(tableJoinExpr1);
                    }else if(QueryParseHelper.isQueryIdentifier(sqlPartContext)){
                        Query nested = QueryDefCache.getInstance().getQueries().get(sqlPartContext.getText());
                        TableJoinExpr tableJoinExpr1 = new TableJoinExpr();
//                        tableJoinExpr1.setTable(new Table(tableJoinExpr.getTable().getAlias(), expr));
                        result.getTableJoinExprs().add(tableJoinExpr1);                        
                    }else {
                        
                    }
                }else {
    //                tableJoinExpr.getJoinExps().get(0).getLeftTables().get(0).getExpr()
                }
            }

            for(Column column : query.getColumns() ){
                ProcToSequelGrammarParser.SqlPartContext sqlPart = ProctosequelHelper.parseSqlPart(column.getExpr());
                if(QueryParseHelper.hasSelectStmt(sqlPart)){
                    String expr = QueryEvalHelper.evaluateNestedQueries(sqlPart);
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
        
            
        }catch (Exception ex){
            throw new QueryEvalException("see below...", ex);
        }
    }
    
    /**
     * @return the result
     */
    public Query getResult() {
        return result;
    }

}
