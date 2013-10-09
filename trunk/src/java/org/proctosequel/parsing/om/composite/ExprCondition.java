/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.parsing.om.composite;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class ExprCondition implements Condition{

    private String expr;

    public ExprCondition(String expr) {
        this.expr = expr;
    }

    /**
     * @return the expr
     */
    public String getExpr() {
        return expr;
    }
    
    
    
}
