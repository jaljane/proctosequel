package org.proctosequel.main.commands.nested;

import java.util.List;
import org.proctosequel.Command;
import org.proctosequel.main.commands.EvaluateQueryCommand;
import org.proctosequel.parsing.om.Query;
import org.proctosequel.parsing.om.composite.Condition;
import org.proctosequel.parsing.om.composite.JoinExp;
import org.proctosequel.parsing.om.composite.Table;
import org.proctosequel.parsing.om.composite.TableJoinExpr;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class JoinQueryToQueryCommand implements Command{

    private static String ALIAS_PERFIX = "pts_t";
    private EvaluateQueryCommand.QueryEvalContext queryEvalContext;
    private Query nested;


    public JoinQueryToQueryCommand(EvaluateQueryCommand.QueryEvalContext queryEvalContext, Query nested) {
        this.queryEvalContext = queryEvalContext;
        this.nested = nested;

    }
    
    @Override
    public void execute() {
        Query query = queryEvalContext.getQuery();
        Query result = queryEvalContext.getResult();
        String aliasBuffer = null;
        if(query.getGroupBy() == null && nested.getGroupBy() ==null ){
            for(TableJoinExpr tableJoinExpr : nested.getTableJoinExprs()){
                if(tableJoinExpr.getTable()!=null){
                    TableJoinExpr tableJoinExpr1 = new TableJoinExpr();
                    queryEvalContext.setAliasCounter(queryEvalContext.getAliasCounter()+1);
                    aliasBuffer = ALIAS_PERFIX + queryEvalContext.getAliasCounter();
                    tableJoinExpr1.setTable(new Table(aliasBuffer , tableJoinExpr.getTable().getExpr()));
                    queryEvalContext.getTableAliases().put(aliasBuffer, tableJoinExpr.getTable());      
                    result.getTableJoinExprs().add(tableJoinExpr);
                }else {
                    TableJoinExpr tableJoinExpr1 = new TableJoinExpr();
                    for(int j=0;j<tableJoinExpr.getJoinExps().size();j++){
                        JoinExp joinExp1 = new JoinExp();
                        List<Table> lefts = tableJoinExpr.getJoinExps().get(j).getLeftTables();
                        for(int k=0;k<lefts.size();k++){
                            queryEvalContext.setAliasCounter(queryEvalContext.getAliasCounter()+1);
                            aliasBuffer = ALIAS_PERFIX + queryEvalContext.getAliasCounter();                            
                            joinExp1.getLeftTables().add(new Table(aliasBuffer , lefts.get(k).getExpr())); 
                            queryEvalContext.getTableAliases().put(aliasBuffer, lefts.get(k));                            
                        }
                        
                        queryEvalContext.setAliasCounter(queryEvalContext.getAliasCounter()+1);
                        Table rightTable=tableJoinExpr.getJoinExps().get(j).getRightTable();
                        aliasBuffer = ALIAS_PERFIX + queryEvalContext.getAliasCounter();
                        joinExp1.setRightTable(new Table(aliasBuffer, rightTable.getExpr()));
                        queryEvalContext.getTableAliases().put(aliasBuffer, rightTable); 
                        
                        List<Condition> conditions = tableJoinExpr.getJoinExps().get(j).getConditions();
                        for(int k=0;k<conditions.size();k++){
                            joinExp1.getConditions().add(conditions.get(k));                           
                        }                        
                        
                        tableJoinExpr1.getJoinExps().add(joinExp1);
                    }
                    result.getTableJoinExprs().add(tableJoinExpr1);
                }
                
            }
            
        }
    }


}
