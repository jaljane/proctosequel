/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.query.om.composite;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class JoinExp {
    
    private String leftTable;
    private String rightTable;
    private String join;
    private String condition;

    public JoinExp() {
    }

    public JoinExp(String leftTable, String rightTable, String join, String condition) {
        this.leftTable = leftTable;
        this.rightTable = rightTable;
        this.join = join;
        this.condition = condition;
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
    public String getCondition() {
        return condition;
    }

    /**
     * @param condition the condition to set
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }
    
    
    

}
