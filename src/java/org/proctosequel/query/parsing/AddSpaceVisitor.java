
package org.proctosequel.query.parsing;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.proctosequel.antlr.ProcToSequelGrammarBaseVisitor;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class AddSpaceVisitor extends  ProcToSequelGrammarBaseVisitor{

    
    private String query = "";
    
    @Override
    public Object visitTerminal(TerminalNode tn) {
        query += " " + tn.getText();
         return null;
    }

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }
    public void reset(){
        query = "";
    }
}
