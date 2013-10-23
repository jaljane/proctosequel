/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proctosequel.parsing.visitors;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.proctosequel.antlr.LogicExprGrammarParser;
import org.proctosequel.antlr.ProcToSequelGrammarBaseVisitor;
import org.proctosequel.parsing.utils.ProctosequelHelper;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class IsolateOrExprVisitor  extends ProcToSequelGrammarBaseVisitor {

    private List<ParseTree> orExprs = new ArrayList<>();

    @Override
    public Object visitTerminal(TerminalNode tn) {
        if("or".equals(tn.getText())){
            ParseTree subCondContext = getParentToIsolate(tn);
            if(!orExprs.contains(subCondContext) ){
                orExprs.add(subCondContext);
            }
            
        }
        return null;  
    }


    /**
     * @return the orExprs
     */
    public List<ParseTree> getOrExprs() {
        List<ParseTree> filtred = new ArrayList<>();
        for(ParseTree subCondContext1 : orExprs){
            boolean accept = true;
            for(ParseTree subCondContext2 : orExprs){
                if(ProctosequelHelper.childOf(subCondContext1, subCondContext2)){
                    accept = false;
                    break;
                }
            }
            if(accept){
                filtred.add(subCondContext1);
            }
        }
        return filtred;
    }

    private ParseTree getParentToIsolate(ParseTree parseTree){
        if(parseTree.getParent()==null){
            return parseTree;
        }
        if(parseTree.getParent().getText().startsWith("(") 
                && (parseTree.getParent() instanceof LogicExprGrammarParser.SubCondContext
                || parseTree.getParent() instanceof LogicExprGrammarParser.ConditionContext)){
            return parseTree.getParent();
        }
        if(parseTree.getParent() == null){
            return parseTree;
        }
        return getParentToIsolate(parseTree.getParent());
        
    }
    
}
