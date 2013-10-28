/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proctosequel.parsing.visitors;

import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.lang3.StringUtils;
import org.proctosequel.antlr.ProcToSequelGrammarBaseVisitor;
import org.proctosequel.antlr.ProcToSequelGrammarParser;
import org.proctosequel.parsing.utils.Constants;
import org.proctosequel.parsing.utils.ProctosequelHelper;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class StoreNestedQueriesVisitor  extends ProcToSequelGrammarBaseVisitor {

    private Map<String, ProcToSequelGrammarParser.SelectStmtContext> nestedQueryByToken = new HashMap<>();
    private Map<ProcToSequelGrammarParser.SelectStmtContext, String> tokenByNestedQuery = new HashMap<>();
    private ProcToSequelGrammarParser.SelectStmtContext current;
    private String expr = "";
    private int counter = 1;

    @Override
    public Object visitTerminal(TerminalNode tn) {
        if("<EOF>".equals(tn.getText())){
            return null;
        }        
        if(current !=null && ProctosequelHelper.childOf(tn, current) ){
            return null;
        }else if("select".equals(tn.getText())){
            current=(ProcToSequelGrammarParser.SelectStmtContext) tn.getParent();
            getNestedQueryByToken().put("ProcToSequelGrammarParser.SelectStmtContext" + counter, current);
            getTokenByNestedQuery().put(current, "ProcToSequelGrammarParser.SelectStmtContext" + counter);
            expr+=" " + "ProcToSequelGrammarParser.SelectStmtContext" + counter;
            counter++;
        }else {
            if(StringUtils.startsWithAny(tn.getText(), Constants.QUALIFIER_SEP_CHARS)){
                expr += tn.getText();
            }else {
                expr += " " + tn.getText();
            }           
        }
        return null;  
    }
    
   
    
    public String getExpr(){
        return expr;
    }
    
    public String getRestoredExpr(){
        String buffer = expr;
        for(Map.Entry<String, ProcToSequelGrammarParser.SelectStmtContext > entry : getNestedQueryByToken().entrySet()){
            AddSpaceVisitor addSpaceVisitor = new AddSpaceVisitor();
            addSpaceVisitor.visit(entry.getValue());
            buffer = StringUtils.replace(buffer, entry.getKey(), addSpaceVisitor.getExpr());
        }
        return  buffer;
            
    }
    
    public Map<String, String> getTokenContent(){
        Map<String, String> result = new HashMap<>();
        for(Map.Entry<String, ProcToSequelGrammarParser.SelectStmtContext> entry :  getNestedQueryByToken().entrySet()){
            AddSpaceVisitor addSpaceVisitor = new AddSpaceVisitor();
            addSpaceVisitor.visit(entry.getValue());
            result.put(entry.getKey(), addSpaceVisitor.getExpr());
                  
        }
        return result;
    }    
    public static void main(String[] args){
        String sqlpart = "hhd, sdkkd, (select * from hh) gg, skkd, (select * from uut where exists (select t from hhf))";
        ProcToSequelGrammarParser.SqlPartContext sqlPartContext = ProctosequelHelper.parseSqlPart(sqlpart);
        StoreNestedQueriesVisitor storeNestedQueriesVisitor = new StoreNestedQueriesVisitor();
        storeNestedQueriesVisitor.visit(sqlPartContext);
        System.out.println(storeNestedQueriesVisitor.getExpr());
        System.out.println(storeNestedQueriesVisitor.getRestoredExpr());
//        ProctosequelHelper.getProcToSequelParser(sqlpart);
    }

    /**
     * @return the nestedQueryByToken
     */
    public Map<String, ProcToSequelGrammarParser.SelectStmtContext> getNestedQueryByToken() {
        return nestedQueryByToken;
    }

    /**
     * @return the tokenByNestedQuery
     */
    public Map<ProcToSequelGrammarParser.SelectStmtContext, String> getTokenByNestedQuery() {
        return tokenByNestedQuery;
    }
    
}
