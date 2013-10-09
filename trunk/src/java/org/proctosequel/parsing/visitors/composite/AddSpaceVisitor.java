
package org.proctosequel.parsing.visitors.composite;

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
        if(tn.getText().startsWith(".") || tn.getText().startsWith(":")){
            expr += tn.getText();
        }else {
            expr += " " + tn.getText();
        }
        
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
