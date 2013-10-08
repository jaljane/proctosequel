
package org.proctosequel.parsing.om;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class ExportResult {
    
    private String queryVarname;
    private String tablename;
    private List<String> primaryKeyColumns = new ArrayList<>();

    public ExportResult() {
    }

    
    
    /**
     * @return the queryVarname
     */
    public String getQueryVarname() {
        return queryVarname;
    }

    /**
     * @param queryVarname the queryVarname to set
     */
    public void setQueryVarname(String queryVarname) {
        this.queryVarname = queryVarname;
    }

    /**
     * @return the tablename
     */
    public String getTablename() {
        return tablename;
    }

    /**
     * @param tablename the tablename to set
     */
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    /**
     * @return the primaryKeyColumns
     */
    public List<String> getPrimaryKeyColumns() {
        return primaryKeyColumns;
    }

    /**
     * @param primaryKeyColumns the primaryKeyColumns to set
     */
    public void setPrimaryKeyColumns(List<String> primaryKeyColumns) {
        this.primaryKeyColumns = primaryKeyColumns;
    }

    @Override
    public String toString() {
        
        return "{ queryVarname : " + queryVarname + "\n, tablename : " + tablename + "\n, primaryKeyColumns : " + Arrays.deepToString(primaryKeyColumns.toArray()) + "}"; 
    }
    
    
    
}
