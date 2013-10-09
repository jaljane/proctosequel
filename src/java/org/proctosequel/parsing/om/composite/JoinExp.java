/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.parsing.om.composite;

import java.util.List;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class JoinExp {
    
    private String leftTable;
    private String rightTable;
    private String join;
    private List<Condition> conditions;

    public JoinExp() {
    }

    public JoinExp(String leftTable, String rightTable, String join, List<Condition> conditions) {
        this.leftTable = leftTable;
        this.rightTable = rightTable;
        this.join = join;
        this.conditions = conditions;
    }

    /**
     * @return the leftTable
     */
    public String getLeftTable() {
        return leftTable;
    }

    /**
     * @param leftTable the leftTable to set
     */
    public void setLeftTable(String leftTable) {
        this.leftTable = leftTable;
    }

    /**
     * @return the rightTable
     */
    public String getRightTable() {
        return rightTable;
    }

    /**
     * @param rightTable the rightTable to set
     */
    public void setRightTable(String rightTable) {
        this.rightTable = rightTable;
    }

    /**
     * @return the join
     */
    public String getJoin() {
        return join;
    }

    /**
     * @param join the join to set
     */
    public void setJoin(String join) {
        this.join = join;
    }

    /**
     * @return the condition
     */
    public List<Condition> getConditions() {
        return conditions;
    }

    /**
     * @param condition the condition to set
     */
    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }
    
    
    

}
