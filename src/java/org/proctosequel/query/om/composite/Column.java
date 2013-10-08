/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.query.om.composite;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class Column {
    
    private String alias;
    private String expr;

    public Column(String alias, String expr) {
        this.alias = alias;
        this.expr = expr;
    }

    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @return the expr
     */
    public String getExpr() {
        return expr;
    }
    
}
