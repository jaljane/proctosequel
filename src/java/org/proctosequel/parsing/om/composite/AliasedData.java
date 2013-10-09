/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.parsing.om.composite;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class AliasedData {
    
    private String alias;
    private String expr;

    public AliasedData(String alias, String expr) {
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
