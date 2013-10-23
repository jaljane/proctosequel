/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proctosequel.parsing.visitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.lang3.StringUtils;
import org.proctosequel.antlr.LogicExprGrammarBaseVisitor;
import org.proctosequel.antlr.LogicExprGrammarParser;
import org.proctosequel.parsing.utils.Constants;
import org.proctosequel.parsing.utils.ProctosequelHelper;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class StoreIsolatedOrExprVisitor  extends LogicExprGrammarBaseVisitor{


    private List<ParseTree> orExprs = new ArrayList<>();
    private Map<String, ParseTree> orExprByToken = new HashMap<>();
    private Map<ParseTree, String> tokenByOrExpr = new HashMap<>();
    
    private int counter =1; 
    private String expr = "";

    public StoreIsolatedOrExprVisitor(List<ParseTree> orExprs ) {
        this.orExprs = orExprs;
    }

    @Override
    public Object visitTerminal(TerminalNode tn) {
        ParseTree parseTree = getIsolatedParent(tn);
        if(parseTree==null){

            if(StringUtils.startsWithAny(tn.getText(), Constants.QUALIFIER_SEP_CHARS)){
                expr += tn.getText();
            }else {
                expr += " " + tn.getText();
            }                       
        }            
        return null;  
    }

    @Override
    public Object visitSubCond(LogicExprGrammarParser.SubCondContext ctx) {
        if(orExprs.contains(ctx)){            
            orExprByToken.put("LogicExprGrammarParser.SubCondContext" + counter, ctx);
            tokenByOrExpr.put(ctx, "LogicExprGrammarParser.SubCondContext" + counter);
            expr+=" " + "LogicExprGrammarParser.SubCondContext" + counter;
            counter++;            
        }
        return super.visitSubCond(ctx);
    }

    @Override
    public Object visitCondition(LogicExprGrammarParser.ConditionContext ctx) {
        if(orExprs.contains(ctx)){            
            orExprByToken.put("LogicExprGrammarParser.SubCondContext" + counter, ctx);
            tokenByOrExpr.put(ctx, "LogicExprGrammarParser.SubCondContext" + counter);
            expr+=" " + "LogicExprGrammarParser.SubCondContext" + counter;
            counter++;            
        }
        return super.visitCondition(ctx);
    }
    
    
    
    
    
    private ParseTree getIsolatedParent(TerminalNode tn){
        for(ParseTree condContext : orExprs){
            if(ProctosequelHelper.childOf(tn, condContext)){
                return condContext;
            }
        }
        return null;
    }
    
    public String getExpr(){
        return expr;
    }
    
    public String getRestoredExpr(){
        String buffer = expr;
        for(Map.Entry<String, ParseTree > entry : orExprByToken.entrySet()){
            AddSpaceVisitor addSpaceVisitor = new AddSpaceVisitor();
            addSpaceVisitor.visit(entry.getValue());
            buffer = StringUtils.replace(buffer, entry.getKey(), addSpaceVisitor.getExpr());
        }
        return  buffer;
            
    }
    
    public Map<String, String> getTokenContent(){
        Map<String, String> result = new HashMap<>();
        for(Map.Entry<String, ParseTree> entry :  orExprByToken.entrySet()){
            AddSpaceVisitor addSpaceVisitor = new AddSpaceVisitor();
            addSpaceVisitor.visit(entry.getValue());
            result.put(entry.getKey(), addSpaceVisitor.getExpr());
                  
        }
        return result;
    }
    
    
    public static void main(String[] args){
        String logicExpr = "hhh=yy and (hh=ii and (iid = kkk and ll=uu or ll=uu) and (gg=uu or kk=uu))";
        ParseTree subCondContext = ProctosequelHelper.parseCondition(logicExpr);
        IsolateOrExprVisitor isolateOrExprVisitor = new IsolateOrExprVisitor();
        isolateOrExprVisitor.visit(subCondContext);
        StoreIsolatedOrExprVisitor storeIsolatedOrExprVisitor = new StoreIsolatedOrExprVisitor(isolateOrExprVisitor.getOrExprs());
        storeIsolatedOrExprVisitor.visit(subCondContext);
        System.out.println(storeIsolatedOrExprVisitor.getExpr());
        System.out.println(storeIsolatedOrExprVisitor.getRestoredExpr());
    }
    
}
