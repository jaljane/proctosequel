package org.proctosequel.main.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.proctosequel.antlr.ProcToSequelGrammarParser;
import org.proctosequel.main.commands.EvaluateQueryCommand;
import org.proctosequel.parsing.commands.nested.ParseQueryCommand;
import org.proctosequel.parsing.om.Query;
import org.proctosequel.parsing.om.composite.Condition;
import org.proctosequel.parsing.om.composite.JoinExp;
import org.proctosequel.parsing.om.composite.Table;
import org.proctosequel.parsing.om.composite.TableJoinExpr;
import org.proctosequel.parsing.visitors.StoreNestedQueriesVisitor;
import org.proctosequel.utils.Constants;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class QueryEvalHelper {

    
    public static Query evaluateQuery(Query query){                  
        EvaluateQueryCommand evaluateQueryCommand = new EvaluateQueryCommand(query);
        evaluateQueryCommand.execute();     
        return evaluateQueryCommand.getResult();
    }    
    
    public static Query evaluateNestedQuery(ProcToSequelGrammarParser.SelectStmtContext selectStmtContext){
        ParseQueryCommand parseQueryCommand = new ParseQueryCommand("$anonymous", selectStmtContext);
        parseQueryCommand.execute();                    
        EvaluateQueryCommand evaluateQueryCommand = new EvaluateQueryCommand(parseQueryCommand.getQuery());
        evaluateQueryCommand.execute();     
        return evaluateQueryCommand.getResult();
    }
    
    public static String evaluateNestedQueries(ProcToSequelGrammarParser.SqlPartContext sqlPart){
        Map<String, String> nestedQueryResult = new HashMap<>();
        StoreNestedQueriesVisitor storeNestedQueriesVisitor = new StoreNestedQueriesVisitor();
        storeNestedQueriesVisitor.visit(sqlPart);
        if(!storeNestedQueriesVisitor.getTokenContent().isEmpty()){
            for(Map.Entry<String, ProcToSequelGrammarParser.SelectStmtContext> nested : storeNestedQueriesVisitor.getNestedQueryByToken().entrySet()){
                ParseQueryCommand parseQueryCommand = new ParseQueryCommand("$anonymous", nested.getValue());
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
    
    
}
