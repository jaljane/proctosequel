/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proctosequel.main.commands.nested;

import org.proctosequel.Command;
import org.proctosequel.antlr.ProcToSequelGrammarParser;
import org.proctosequel.main.commands.EvaluateQueryCommand;
import org.proctosequel.main.exception.QueryEvalException;
import org.proctosequel.main.utils.QueryEvalHelper;
import org.proctosequel.parsing.QueryDefCache;
import org.proctosequel.parsing.om.Query;
import org.proctosequel.parsing.om.composite.Table;
import org.proctosequel.parsing.om.composite.TableJoinExpr;
import org.proctosequel.parsing.utils.ProctosequelHelper;
import org.proctosequel.parsing.utils.QueryParseHelper;
import org.proctosequel.parsing.visitors.AddSpaceVisitor;

/**
 *
 * @author Jamel
 */
public class JoinSqlPartToQueryCommand  implements Command {

    private ProcToSequelGrammarParser.SqlPartContext sqlPartContext;
    private String sqlPartAlias;
    private EvaluateQueryCommand.QueryEvalContext queryEvalContext;
    
    public JoinSqlPartToQueryCommand(EvaluateQueryCommand.QueryEvalContext queryEvalContext,ProcToSequelGrammarParser.SqlPartContext sqlPartContext, String sqlPartAlias) {
        this.sqlPartContext = sqlPartContext;
        this.queryEvalContext = queryEvalContext; 
        this.sqlPartAlias = sqlPartAlias;
    }

    @Override
    public void execute() {        
        try{
            Query result = queryEvalContext.getResult();        
            if(QueryParseHelper.hasSelectStmt(sqlPartContext)){
                String expr = QueryEvalHelper.evaluateNestedQueries(sqlPartContext);
                TableJoinExpr tableJoinExpr1 = new TableJoinExpr();
                tableJoinExpr1.setTable(new Table(sqlPartAlias, expr));
                result.getTableJoinExprs().add(tableJoinExpr1);
            }else if(QueryParseHelper.isQueryIdentifier(sqlPartContext)){
                Query nested = QueryDefCache.getInstance().getQueries().get(sqlPartContext.getText());
                nested = QueryEvalHelper.evaluateQuery(nested);
                queryEvalContext.getEvaluatedQueries().put(sqlPartContext.getText(), nested);
                JoinQueryToQueryCommand joinQueryToQueryCommand = new JoinQueryToQueryCommand(queryEvalContext, nested);
                joinQueryToQueryCommand.execute();
            }else {
                TableJoinExpr tableJoinExpr = new TableJoinExpr();
                AddSpaceVisitor addSpaceVisitor = new AddSpaceVisitor();
                addSpaceVisitor.visit(sqlPartContext);
                tableJoinExpr.setTable(new Table(sqlPartAlias, addSpaceVisitor.getExpr()));
                result.getTableJoinExprs().add(tableJoinExpr);
            }                 
        }catch (Exception ex){
            throw new QueryEvalException("see below...", ex);
        }
        
   
    }

    /**
     * @return the queryEvalContext
     */
    public EvaluateQueryCommand.QueryEvalContext getQueryEvalContext() {
        return queryEvalContext;
    }
    
}
