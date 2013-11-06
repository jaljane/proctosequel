package org.proctosequel.main.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.proctosequel.Command;
import org.proctosequel.antlr.ProcToSequelGrammarParser;
import org.proctosequel.main.commands.nested.JoinQueryToJoinExprCommand;
import org.proctosequel.main.commands.nested.JoinQueryToQueryCommand;
import org.proctosequel.main.commands.nested.JoinSqlPartToQueryCommand;
import org.proctosequel.parsing.QueryDefCache;
import org.proctosequel.main.exception.QueryEvalException;
import org.proctosequel.main.utils.QueryEvalHelper;
import org.proctosequel.parsing.om.Query;
import org.proctosequel.parsing.om.composite.Column;
import org.proctosequel.parsing.om.composite.Condition;
import org.proctosequel.parsing.om.composite.JoinExp;
import org.proctosequel.parsing.om.composite.RowSet;
import org.proctosequel.parsing.om.composite.Table;
import org.proctosequel.parsing.om.composite.TableJoinExpr;
import org.proctosequel.parsing.utils.ProctosequelHelper;
import org.proctosequel.parsing.utils.QueryParseHelper;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class EvaluateQueryCommand implements Command{

    private QueryEvalContext queryEvalContext;

    public EvaluateQueryCommand(Query query) {
        this.queryEvalContext = new QueryEvalContext(query);
        this.queryEvalContext.setResult(new Query());
    }
    
    public EvaluateQueryCommand(QueryEvalContext queryEvalContext) {
        this.queryEvalContext = queryEvalContext;
    }    
    @Override
    public void execute() {
        try{
            Query query = queryEvalContext.query;
            Query result = queryEvalContext.result;
            for(TableJoinExpr tableJoinExpr : query.getTableJoinExprs()){
                if(tableJoinExpr.getTable()!=null){
                    ProcToSequelGrammarParser.SqlPartContext sqlPartContext = ProctosequelHelper.parseSqlPart(tableJoinExpr.getTable().getExpr());
                    JoinSqlPartToQueryCommand joinSqlPartToQueryCommand = new JoinSqlPartToQueryCommand(queryEvalContext, sqlPartContext, tableJoinExpr.getTable().getAlias());
                    joinSqlPartToQueryCommand.execute();
                }else {
//                    for(JoinExp joinExp : tableJoinExpr.getJoinExps()){
                        
//                        JoinQueryToJoinExprCommand joinQueryToJoinExprCommand = new JoinQueryToJoinExprCommand(joinExp, result);
//                        joinQueryToJoinExprCommand.execute();
                        
//                    }
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
        return queryEvalContext.getResult();
    }
    
    public static class QueryEvalContext {
        private Query query;
        private Query result;    
        private Map<String, RowSet> tableAliases = new HashMap<>();
        private Map<String, Query> evaluatedQueries = new HashMap<>();   
        private Map<Query, List<RowSet>> tableMapping = new HashMap<>();
        private int aliasCounter = 0;

        public QueryEvalContext(Query query, Query result) {
            this.query = query;
            this.result = result;
        }

        public QueryEvalContext(Query query) {
            this.query = query;            
        }
        
        
        /**
         * @return the query
         */
        public Query getQuery() {
            return query;
        }

        /**
         * @param query the query to set
         */
        public void setQuery(Query query) {
            this.query = query;
        }

        /**
         * @return the result
         */
        public Query getResult() {
            return result;
        }

        /**
         * @param result the result to set
         */
        public void setResult(Query result) {
            this.result = result;
        }

        /**
         * @return the tableAliases
         */
        public Map<String, RowSet> getTableAliases() {
            return tableAliases;
        }

        /**
         * @param tableAliases the tableAliases to set
         */
        public void setTableAliases(Map<String, RowSet> tableAliases) {
            this.tableAliases = tableAliases;
        }

        /**
         * @return the anonymousQueries
         */
        public Map<String, Query> getEvaluatedQueries() {
            return evaluatedQueries;
        }

        /**
         * @param anonymousQueries the anonymousQueries to set
         */
        public void setEvaluatedQueries(Map<String, Query> evaluatedQueries) {
            this.evaluatedQueries = evaluatedQueries;
        }

        /**
         * @return the tableMapping
         */
        public Map<Query, List<RowSet>> getTableMapping() {
            return tableMapping;
        }

        /**
         * @param tableMapping the tableMapping to set
         */
        public void setTableMapping(Map<Query, List<RowSet>> tableMapping) {
            this.tableMapping = tableMapping;
        }

        /**
         * @return the aliasCounter
         */
        public int getAliasCounter() {
            return aliasCounter;
        }

        /**
         * @param aliasCounter the aliasCounter to set
         */
        public void setAliasCounter(int aliasCounter) {
            this.aliasCounter = aliasCounter;
        }
        
    }

}
