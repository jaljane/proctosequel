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
public class SplitBySepVisitor extends ProcToSequelGrammarBaseVisitor{

    private List<String> tokens = new ArrayList<>();
    private String[] separators ;
    private String buffer = "";

    public SplitBySepVisitor(String... separators) {
        this.separators = separators;
    }
    
    @Override
    public Object visitTerminal(TerminalNode tn) {
       if("<EOF>".equals(tn.getText())){
            return null;
        }        
        if(!Arrays.asList(separators).contains(tn.getText())){
            if(StringUtils.startsWithAny(tn.getText(), Constants.QUALIFIER_SEP_CHARS)){
                buffer += tn.getText();
            }else {
                buffer += " " + tn.getText();
            }            
        }else {
            tokens.add(buffer);
            buffer="";
        }
        return null;
    }

    /**
     * @return the tokens
     */
    public List<String> getTokens() {
        if(!StringUtils.isEmpty(buffer)){
            tokens.add(buffer);
            buffer="";
        }
        return tokens;
    }

    
}
