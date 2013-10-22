/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proctosequel.parsing.visitors.composite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.lang3.StringUtils;
import org.proctosequel.antlr.LogicExprGrammarBaseVisitor;
import org.proctosequel.antlr.LogicExprGrammarParser;
import org.proctosequel.parsing.utils.ProctosequelHelper;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class StoreIsolatedOrExprVisitor  extends LogicExprGrammarBaseVisitor{


    private List<LogicExprGrammarParser.SubCondContext> orExprs = new ArrayList<>();
    private Map<String, LogicExprGrammarParser.SubCondContext> orExprByToken = new HashMap<>();
    private Map<LogicExprGrammarParser.SubCondContext, String> tokenByOrExpr = new HashMap<>();
    
    private int counter =1; 
    private String expr = "";

    public StoreIsolatedOrExprVisitor(List<LogicExprGrammarParser.SubCondContext> orExprs ) {
        this.orExprs = orExprs;
    }

    @Override
    public Object visitTerminal(TerminalNode tn) {
        if(!skipTerminal(tn)){
            if(tn.getText().startsWith(".") || tn.getText().startsWith(":")){
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
        return null;
    }
    
    
    
    private boolean skipTerminal(TerminalNode tn){
        for(LogicExprGrammarParser.SubCondContext subCondContext : orExprs){
            if(ProctosequelHelper.childOf(tn, subCondContext)){
                return true;
            }
        }
        return false;
    }
    
    public String getExpr(){
        return expr;
    }
    
    public String getRestoredExpr(){
        String buffer = expr;
        for(Map.Entry<String, LogicExprGrammarParser.SubCondContext > entry : orExprByToken.entrySet()){
            AddSpaceVisitor addSpaceVisitor = new AddSpaceVisitor();
            addSpaceVisitor.visit(entry.getValue());
            buffer = StringUtils.replace(buffer, entry.getKey(), addSpaceVisitor.getExpr());
        }
        return  buffer;
            
    }
    
    
    public static void main(String[] args){
        String logicExpr = "hh=ii or kkk";
//        LogicExprGrammarParser.SubCondContext subCondContext = ProctosequelHelper.parseCondition(logicExpr);
//        StoreNestedQueriesVisitor storeNestedQueriesVisitor = new StoreNestedQueriesVisitor();
//        storeNestedQueriesVisitor.visit(sqlPartContext);
//        System.out.println(storeNestedQueriesVisitor.getExpr());
//        System.out.println(storeNestedQueriesVisitor.getRestoredExpr());
//        ProctosequelHelper.getProcToSequelParser(sqlpart);
    }
    
}
