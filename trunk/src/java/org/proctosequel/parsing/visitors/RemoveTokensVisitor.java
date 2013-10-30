package org.proctosequel.parsing.visitors;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.lang3.StringUtils;
import org.proctosequel.antlr.ProcToSequelGrammarBaseVisitor;
import org.proctosequel.parsing.utils.Constants;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class RemoveTokensVisitor extends ProcToSequelGrammarBaseVisitor{
    
    TokenFilter filter;
    private String expr = "";
    
    public RemoveTokensVisitor(TokenFilter filter) {
        this.filter = filter;
    }

    @Override
    public Object visitTerminal(TerminalNode tn) {
        if("<EOF>".equals(tn.getText())){
            return null;
        }        
        if(!filter.accept(tn)){
            if(StringUtils.startsWithAny(tn.getText(), Constants.QUALIFIER_SEP_CHARS)){
                expr += tn.getText();
            }else {
                expr += " " + tn.getText();
            }            
        }        
        return null;

    }

    /**
     * @return the expr
     */
    public String getExpr() {
        return expr;
    }
    
    public interface TokenFilter{
        boolean accept(TerminalNode tn);
    }

    
}
