package org.proctosequel.main.commands.nested;

import org.proctosequel.Command;
import org.proctosequel.main.commands.EvaluateQueryCommand;
import org.proctosequel.parsing.om.Query;
import org.proctosequel.parsing.om.composite.Table;
import org.proctosequel.parsing.om.composite.TableJoinExpr;
import org.proctosequel.utils.Constants;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class JoinQueryToQueryCommand implements Command{

    
    private EvaluateQueryCommand.QueryEvalContext queryEvalContext;
    
    private Query nested;


    public JoinQueryToQueryCommand(EvaluateQueryCommand.QueryEvalContext queryEvalContext, Query nested) {
        this.queryEvalContext = queryEvalContext;
        this.nested = nested;
    }
    
    @Override
    public void execute() {
        Query query = getQueryEvalContext().getQuery();
        Query result = getQueryEvalContext().getResult();
        String aliasBuffer = null;
        if(query.getGroupBy() == null && nested.getGroupBy() ==null ){
            for(TableJoinExpr tableJoinExpr : nested.getTableJoinExprs()){
                if(tableJoinExpr.getTable()!=null){
                    TableJoinExpr tableJoinExpr1 = new TableJoinExpr();
                    getQueryEvalContext().setAliasCounter(getQueryEvalContext().getAliasCounter()+1);
                    aliasBuffer = Constants.ALIAS_PERFIX + getQueryEvalContext().getAliasCounter();
                    tableJoinExpr1.setTable(new Table(aliasBuffer , tableJoinExpr.getTable().getExpr()));
                    getQueryEvalContext().getTableAliases().put(aliasBuffer, tableJoinExpr.getTable());      
                    result.getTableJoinExprs().add(tableJoinExpr1);
                }else {
                    TableJoinExpr tableJoinExpr1 = new TableJoinExpr();
                    
                    result.getTableJoinExprs().add(tableJoinExpr1);
                }
                
            }
            
        }else {
            TableJoinExpr tableJoinExpr = new TableJoinExpr();
            EvaluateQueryCommand evaluateQueryCommand = new EvaluateQueryCommand(nested);
            evaluateQueryCommand.execute();
            Query nested = evaluateQueryCommand.getResult();
            getQueryEvalContext().setAliasCounter(getQueryEvalContext().getAliasCounter()+1);
            aliasBuffer = Constants.ALIAS_PERFIX + getQueryEvalContext().getAliasCounter();                                        
            tableJoinExpr.setTable(new Table(aliasBuffer, nested.getSQL()));
            getQueryEvalContext().getTableAliases().put(aliasBuffer, nested);   
        }
    }

    /**
     * @return the queryEvalContext
     */
    public EvaluateQueryCommand.QueryEvalContext getQueryEvalContext() {
        return queryEvalContext;
    }


}
