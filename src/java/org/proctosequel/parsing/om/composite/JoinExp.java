/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.parsing.om.composite;

import java.util.List;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 * all left tables will be added in the leftTables struct. further parse will identify which one is targeted in the join.
 */
public class JoinExp {

    private List<Table> leftTables;
    private Table rightTable;
    private String join;
    private List<Condition> conditions;

    public JoinExp() {
    }

    public JoinExp(List<Table> leftTables, Table rightTable, String join, List<Condition> conditions) {
        this.leftTables = leftTables;
        this.rightTable = rightTable;
        this.join = join;
        this.conditions = conditions;
    }

    /**
     * @return the leftTable
     */
    public List<Table> getLeftTables() {
        return leftTables;
    }

    /**
     * @param leftTable the leftTable to set
     */
    public void setLeftTable(List<Table> leftTables) {
        this.leftTables = leftTables;
    }

    /**
     * @return the rightTable
     */
    public Table getRightTable() {
        return rightTable;
    }

    /**
     * @param rightTable the rightTable to set
     */
    public void setRightTable(Table rightTable) {
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
