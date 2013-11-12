package org.proctosequel.main.commands.nested;

import java.util.ArrayList;
import java.util.List;
import org.proctosequel.Command;
import org.proctosequel.antlr.ProcToSequelGrammarParser;
import org.proctosequel.main.commands.EvaluateQueryCommand;
import org.proctosequel.parsing.exception.SemanticsError;
import org.proctosequel.parsing.om.Query;
import org.proctosequel.parsing.om.composite.JoinExp;
import org.proctosequel.parsing.om.composite.Table;
import org.proctosequel.parsing.om.composite.TableJoinExpr;
import org.proctosequel.parsing.utils.ProctosequelHelper;
import org.proctosequel.utils.CollectionHelper;
import org.proctosequel.utils.Errors;

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
       List<List<TableJoinExpr>> joinExpFragments = new ArrayList<>();
       joinExpFragments.add(getTableJoinExprs(joinExprs.get(0).getLeftTables().get(0)));       
       for(int i=0;i< tableJoinExpr.getJoinExps().size();i++){
           joinExpFragments.add(getTableJoinExprs(joinExprs.get(0).getRightTable()));
       }
       List[] possibleJoins = CollectionHelper.cartesianProduct(joinExpFragments.toArray(new List[0]));
       List joinableJoins = new ArrayList();
       for(List exprs : possibleJoins){
           if(joinableTableJoinExprs(exprs, tableJoinExpr.getJoinExps())){
               joinableJoins.add(exprs);
           }
       }
       if(joinableJoins.size()!=1){
           throw new SemanticsError(String.format(Errors.bad_or_unsupported_join_fragment, queryEvalContext.getQuery().getIdentifier()));
       }
       List<TableJoinExpr> joins = (List<TableJoinExpr>)joinableJoins.get(0);
       List<JoinExp> newJoinExps = new ArrayList<>();
       TableJoinExpr join = joins.get(0);
       JoinExp newJoinExp = new JoinExp();
       JoinExp previousJoinExp = null;
       if(join.getTable()!=null){
           newJoinExp.getLeftTables().add(join.getTable());
           newJoinExps.add(newJoinExp);
       }else {                  
           newJoinExps.addAll(join.getJoinExps().subList(0, join.getJoinExps().size()-1));
           newJoinExp = join.getJoinExps().get(join.getJoinExps().size()-1);
       }
       for(int i=1;i<joins.size();i++){
            join = joins.get(i);
            JoinExp joinExp = tableJoinExpr.getJoinExps().get(i-1);
            if(join.getTable()!=null){
                newJoinExp.setRightTable(join.getTable());
                newJoinExp.setJoin(joinExp.getJoin());
                newJoinExp.getConditions().addAll(joinExp.getConditions());
                newJoinExps.add(newJoinExp);
                newJoinExp = new JoinExp();
                previousJoinExp = newJoinExp;
                newJoinExp.getLeftTables().addAll(previousJoinExp.getLeftTables());    
                newJoinExp.getLeftTables().add(previousJoinExp.getRightTable());
            }else {
                newJoinExp.setRightTable(null);
                newJoinExps.addAll(join.getJoinExps());
                
            }         
       }
       
    }
    
    /**
     * @return the queryEvalContext
     */
    public EvaluateQueryCommand.QueryEvalContext getQueryEvalContext() {
        return queryEvalContext;
    }
    
    private List<TableJoinExpr> getTableJoinExprs (Table table){
        EvaluateQueryCommand.QueryEvalContext queryEvalContext1 = new EvaluateQueryCommand.QueryEvalContext(queryEvalContext.getQuery(), new Query());
        ProcToSequelGrammarParser.SqlPartContext sqlPartContext = ProctosequelHelper.parseSqlPart(table.getExpr());
        JoinSqlPartToQueryCommand joinSqlPartToQueryCommand = new JoinSqlPartToQueryCommand(queryEvalContext1, sqlPartContext, table.getAlias());
        joinSqlPartToQueryCommand.execute();
        return joinSqlPartToQueryCommand.getQueryEvalContext().getResult().getTableJoinExprs();        
    }
    
    private boolean joinableTableJoinExprs(List<TableJoinExpr> tableJoinExprs, List<JoinExp> joinExprs){
        return true;
    } 
    
}
