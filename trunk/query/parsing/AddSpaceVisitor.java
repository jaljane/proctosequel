/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proctosequel.query.parsing;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.proctosequel.antlr.ProcToSequelGrammarBaseVisitor;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class AddSpaceVisitor extends  ProcToSequelGrammarBaseVisitor{

    
    private String query = "";
    
    @Override
    public Object visitTerminal(TerminalNode tn) {
        query += " " + tn.getText();
        return super.visitTerminal(tn); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }
    public void reset(){
        query = "";
    }
}
