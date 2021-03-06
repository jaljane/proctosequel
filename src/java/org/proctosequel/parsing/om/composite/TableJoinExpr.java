
package org.proctosequel.parsing.om.composite;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 * table or joinExps. if one is null or empty, the other is not null and not empty, and vise versa.
 */
public class TableJoinExpr {

    private Table table;
    private List<JoinExp> joinExps = new ArrayList<>();

    public TableJoinExpr() {
    }

    /**
     * @return the table
     */
    public Table getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(Table table) {
        this.table = table;
    }

    /**
     * @return the joinExps
     */
    public List<JoinExp> getJoinExps() {
        return joinExps;
    }

    public String getSQL(){
        if(table!=null){
            return table.getSQL();
        }else {
            String sql ="";
            for(int i=0;i<joinExps.size();i++){
                sql+=" "+ joinExps.get(i).getSQL();
            }
            return sql;
        }
        
    }

}
