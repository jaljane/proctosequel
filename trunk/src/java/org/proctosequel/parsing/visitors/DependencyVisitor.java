
package org.proctosequel.parsing.visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.proctosequel.antlr.ProcToSequelGrammarBaseVisitor;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class DependencyVisitor extends ProcToSequelGrammarBaseVisitor{

    private List<String> dependencies = new ArrayList<>();
    
    @Override
    public Object visitTerminal(TerminalNode tn) {
        if(Pattern.matches("\\$[a-zA-z0-9_\\.]+", tn.getText())){
            if(!getDependencies().contains(tn.getText())){                
                getDependencies().add(tn.getText());
            }
        }
        return null;
    }

    /**
     * @return the dependencies
     */
    public List<String> getDependencies() {
        return dependencies;
    }
    

    
    
}
