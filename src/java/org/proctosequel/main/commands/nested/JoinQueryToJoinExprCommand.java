package org.proctosequel.main.commands.nested;

import java.util.List;
import org.proctosequel.Command;
import org.proctosequel.main.commands.EvaluateQueryCommand;
import org.proctosequel.parsing.om.composite.JoinExp;
import org.proctosequel.parsing.om.composite.TableJoinExpr;

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
       for(JoinExp joinExp : tableJoinExpr.getJoinExps()){
           
       }
    }

    /**
     * @return the queryEvalContext
     */
    public EvaluateQueryCommand.QueryEvalContext getQueryEvalContext() {
        return queryEvalContext;
    }



}
