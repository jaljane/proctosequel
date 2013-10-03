/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.query.parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.proctosequel.antlr.ProcToSequalGrammarBaseVisitor;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class DependencyVisitor extends ProcToSequalGrammarBaseVisitor{

    private List<String> dependencies = new ArrayList<>();
    
    @Override
    public Object visitTerminal(TerminalNode tn) {
        if(Pattern.matches("\\$[a-zA-z0-9_\\.]+", tn.getText())){
            if(!getDependencies().contains(tn.getText())){                
                getDependencies().add(tn.getText());
            }
        }
        return super.visitTerminal(tn);
    }

    /**
     * @return the dependencies
     */
    public List<String> getDependencies() {
        return dependencies;
    }
    

    
    
}
