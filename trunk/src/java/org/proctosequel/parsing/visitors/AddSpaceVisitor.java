
package org.proctosequel.parsing.visitors;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.lang3.StringUtils;
import org.proctosequel.antlr.ProcToSequelGrammarBaseVisitor;
import org.proctosequel.parsing.utils.Constants;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class AddSpaceVisitor extends  ProcToSequelGrammarBaseVisitor{

    
    private String expr = "";
    
    @Override
    public Object visitTerminal(TerminalNode tn) {
        if(StringUtils.startsWithAny(tn.getText(), Constants.QUALIFIER_SEP_CHARS)){
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
