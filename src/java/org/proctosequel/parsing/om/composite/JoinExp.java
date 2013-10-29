
package org.proctosequel.parsing.om.composite;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 * all left tables will be added in the leftTables struct. further parse will identify which one is targeted in the join.
 */
public class JoinExp {

    private List<Table> leftTables = new ArrayList<>();
    private Table rightTable;
    private String join;
    private List<Condition> conditions = new ArrayList<>();

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
    
    public String getSQL(){
        String sql = "";
        if(leftTables.size() == 1){
            sql = leftTables.get(0).getSQL();    
        }        
        sql+=" " + join + " " + rightTable.getSQL();
        sql+=" on " + conditions.get(0).getSQL();
        for(int i=1;i<conditions.size();i++){
            sql+=" and " + conditions.get(i).getSQL();
        }
        return sql;
    }
    
}
