package org.proctosequel.main.commands.nested;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.proctosequel.Command;
import org.proctosequel.antlr.ProcToSequelGrammarParser;
import org.proctosequel.main.commands.EvaluateQueryCommand;
import org.proctosequel.parsing.om.Query;
import org.proctosequel.parsing.om.composite.JoinExp;
import org.proctosequel.parsing.om.composite.Table;
import org.proctosequel.parsing.om.composite.TableJoinExpr;
import org.proctosequel.parsing.utils.ProctosequelHelper;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class JoinQueryToJoinExprCommand implements Command{

    private TableJoinExpr tableJoinExpr;
    private EvaluateQueryCommand.QueryEvalContext queryEvalContext;

    public JoinQueryToJoinExprCommand(EvaluateQueryCommand.QueryEvalContext queryEvalContext, TableJoinExpr tableJoinExpr) {
        this.tableJoinExpr = tableJoinExpr;
        this.queryEvalContext = queryEvalContext;
    }
    
    @Override
    public void execute() {
       List<JoinExp> joinExprs = tableJoinExpr.getJoinExps();
       Map<JoinExp, List<TableJoinExpr>> possibleJoinExps = new HashMap<>();
       for(JoinExp joinExp : tableJoinExpr.getJoinExps()){
           if(joinExp.getLeftTables().size()==1){
               Table leftTable = joinExp.getLeftTables().get(0);
               EvaluateQueryCommand.QueryEvalContext queryEvalContext1 = new EvaluateQueryCommand.QueryEvalContext(queryEvalContext.getQuery(), new Query());
               ProcToSequelGrammarParser.SqlPartContext sqlPartContext = ProctosequelHelper.parseSqlPart(leftTable.getExpr());
               JoinSqlPartToQueryCommand joinSqlPartToQueryCommand = new JoinSqlPartToQueryCommand(queryEvalContext1, sqlPartContext, leftTable.getAlias());
               joinSqlPartToQueryCommand.execute();
               possibleJoinExps.put(joinExp, new ArrayList<TableJoinExpr>());               
               for(TableJoinExpr nestedTableJoinExpr : queryEvalContext1.getResult().getTableJoinExprs()){
                   possibleJoinExps.get(joinExp).add(nestedTableJoinExpr);
               }
               
               
           }
       }
    }
    
    /**
     * @return the queryEvalContext
     */
    public EvaluateQueryCommand.QueryEvalContext getQueryEvalContext() {
        return queryEvalContext;
    }
    
    public static class JoinQueryToJoinExprCommandContext {
        
    }
            



}
