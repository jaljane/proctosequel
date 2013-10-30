package org.proctosequel.parsing.visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.proctosequel.antlr.ProcToSequelGrammarBaseVisitor;
import org.proctosequel.parsing.utils.Constants;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class VarNamesVisitor extends ProcToSequelGrammarBaseVisitor{

    private List<String> varnames = new ArrayList<>();
    
    @Override
    public Object visitTerminal(TerminalNode tn) {
        if("<EOF>".equals(tn.getText())){
            return null;
        }        
        if(Pattern.matches(Constants.VAR_NAME_REGEX, tn.getText())){
            if(!getVarNames().contains(tn.getText())){                
                getVarNames().add(tn.getText());
            }
        }
        return null;
    }

    /**
     * @return the varnames
     */
    public List<String> getVarNames() {
        return varnames;
    }
    

    
    
}
