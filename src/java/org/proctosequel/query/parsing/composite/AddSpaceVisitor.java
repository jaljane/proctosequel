
package org.proctosequel.query.parsing.composite;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.proctosequel.antlr.ProcToSequelGrammarBaseVisitor;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class AddSpaceVisitor extends  ProcToSequelGrammarBaseVisitor{

    
    private String expr = "";
    
    @Override
    public Object visitTerminal(TerminalNode tn) {
        expr += " " + tn.getText();
         return null;
    }

    /**
     * @return the query
     */
    public String getExpr() {
        return expr;
    }
    public void reset(){
        expr = "";
    }
}
