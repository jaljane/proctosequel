/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.parsing.om.composite;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class Column extends AliasedData{
    
    public Column(String alias, String expr) {
        super(alias, expr);
    }
    
    public Column(AliasedData aliasedData) {
        super(aliasedData.getAlias(), aliasedData.getExpr());
    }    

    @Override
    public String toString() {
        return "Column[alias=" + getAlias() + ", expr=" + getExpr() + "]";
               
    }
    
}
