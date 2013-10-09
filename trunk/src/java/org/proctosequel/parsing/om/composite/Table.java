/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.parsing.om.composite;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class Table extends AliasedData{

    public Table(String alias, String expr) {
        super(alias, expr);
    }
    
    public Table(AliasedData aliasedData) {
        super(aliasedData.getAlias(), aliasedData.getExpr());
    }    
}
