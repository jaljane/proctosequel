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
public class TableJoinExpr {
    
    private String table;
    private List<JoinExp> joinExps = new ArrayList<>();

    public TableJoinExpr() {
    }

    /**
     * @return the table
     */
    public String getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(String table) {
        this.table = table;
    }

    /**
     * @return the joinExps
     */
    public List<JoinExp> getJoinExps() {
        return joinExps;
    }

    

}
