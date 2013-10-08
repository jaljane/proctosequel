/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.query.parsing.composite;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.proctosequel.antlr.ProcToSequelGrammarBaseVisitor;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class AllTokensVisitor extends ProcToSequelGrammarBaseVisitor{

    private List<String> tokens = new ArrayList<>();
    
    @Override
    public Object visitTerminal(TerminalNode tn) {
        getTokens().add(tn.getText());
        return null;
    }

    /**
     * @return the tokens
     */
    public List<String> getTokens() {
        return tokens;
    }

    
}
