package org.proctosequel.parsing.visitors;

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
        if("<EOF>".equals(tn.getText())){
            return null;
        }        
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
