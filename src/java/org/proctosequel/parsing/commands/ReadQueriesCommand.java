
package org.proctosequel.parsing.commands;

import org.proctosequel.Command;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.proctosequel.antlr.ProcToSequelGrammarParser;
import org.proctosequel.antlr.ProcToSequelGrammarParser.InstContext;
import org.proctosequel.parsing.commands.nested.ParseQueryCommand;
import org.proctosequel.parsing.exception.SyntaxError;
import org.proctosequel.parsing.om.Query;
import org.proctosequel.parsing.utils.Errors;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class ReadQueriesCommand  implements Command {
    public static Logger log = Logger.getLogger(ReadQueriesCommand.class);
    private Map<String, Query> queries = new LinkedHashMap<>();
    private ProcToSequelGrammarParser.ProgContext progTree;

    public ReadQueriesCommand(ProcToSequelGrammarParser.ProgContext progTree) {
        this.progTree = progTree;
    }
    
    public void execute(){
        for(InstContext instContext : progTree.inst()){
            if (instContext.setvar()!= null && !instContext.setvar().isEmpty()){
                ProcToSequelGrammarParser.SelectStmtContext selectStmtContext = instContext.setvar().selectStmt();
                if(selectStmtContext==null || selectStmtContext.isEmpty()){
                    throw new SyntaxError(instContext.setvar().VarName().getText(), Errors.set_var_error_msg);
                }
                if(  "(".equals(selectStmtContext.getChild(0).getText())){
                    throw new SyntaxError(instContext.setvar().VarName().getText(), Errors.set_var_unsupported_parenthesis_error_msg);
                }
                ParseQueryCommand parseQueryCommand = new ParseQueryCommand(instContext.setvar().VarName().getText(), selectStmtContext);
                parseQueryCommand.execute();
                System.out.println(parseQueryCommand.getQuery()); 
                queries.put(instContext.setvar().VarName().getText(), parseQueryCommand.getQuery());                
            }
        }        
        log.debug(queries);
    }

    /**
     * @return the queries
     */
    public Map<String, Query> getQueries() {
        return queries;
    }

    /**
     * @return the progTree
     */
    public ProcToSequelGrammarParser.ProgContext getProgTree() {
        return progTree;
    }
    

   
    
}
