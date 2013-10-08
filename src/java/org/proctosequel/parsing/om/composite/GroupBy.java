/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.parsing.om.composite;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class GroupBy {
    
    private List<String> exprs = new ArrayList<>();
    private String havingexpr;

    public GroupBy() {
    }

    /**
     * @return the exprs
     */
    public List<String> getExprs() {
        return exprs;
    }

    /**
     * @return the havingexpr
     */
    public String getHavingexpr() {
        return havingexpr;
    }

    /**
     * @param havingexpr the havingexpr to set
     */
    public void setHavingexpr(String havingexpr) {
        this.havingexpr = havingexpr;
    }
   
    
    

}
