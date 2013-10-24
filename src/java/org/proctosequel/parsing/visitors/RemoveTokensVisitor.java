/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.parsing.visitors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.lang3.StringUtils;
import org.proctosequel.antlr.ProcToSequelGrammarBaseVisitor;
import org.proctosequel.parsing.utils.Constants;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class RemoveTokensVisitor extends ProcToSequelGrammarBaseVisitor{
    
    private String[] tokens;
    private String expr = "";
    
    public RemoveTokensVisitor(String... tokens) {
        this.tokens = tokens;
    }

    @Override
    public Object visitTerminal(TerminalNode tn) {
        if("<EOF>".equals(tn.getText())){
            return null;
        }        
        if(!Arrays.asList(tokens).contains(tn.getText())){
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

    
}
