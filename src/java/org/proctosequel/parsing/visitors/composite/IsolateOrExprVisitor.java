/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proctosequel.parsing.visitors.composite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private List<LogicExprGrammarParser.SubCondContext> orExprs = new ArrayList<>();

    @Override
    public Object visitTerminal(TerminalNode tn) {
        if("or".equals(tn.getText())){
            LogicExprGrammarParser.SubCondContext subCondContext = getParentToIsolate(tn);
            if(!orExprs.contains(subCondContext) ){
                getOrExprs().add(subCondContext);
            }
            
        }
        return null;  
    }


    /**
     * @return the orExprs
     */
    public List<LogicExprGrammarParser.SubCondContext> getOrExprs() {
        List<LogicExprGrammarParser.SubCondContext> filtred = new ArrayList<>();
        for(LogicExprGrammarParser.SubCondContext subCondContext1 : orExprs){
            boolean accept = true;
            for(LogicExprGrammarParser.SubCondContext subCondContext2 : orExprs){
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

    private LogicExprGrammarParser.SubCondContext getParentToIsolate(ParseTree parseTree){
        if(parseTree.getParent()==null){
            return (LogicExprGrammarParser.SubCondContext) parseTree;
        }
        if(parseTree.getParent().getText().startsWith("(") 
                && parseTree.getParent() instanceof LogicExprGrammarParser.SubCondContext){
            return (LogicExprGrammarParser.SubCondContext)parseTree.getParent();
        }
        if(parseTree.getParent() == null){
            return (LogicExprGrammarParser.SubCondContext) parseTree;
        }
        return getParentToIsolate(parseTree.getParent());
        
    }
    
}
