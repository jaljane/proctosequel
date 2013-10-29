
package org.proctosequel.parsing.om.composite;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class Column extends AliasedData{
    
    private Table table;
    
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
    
    public String getSQL (){
        return getExpr() + " " + (getAlias()==null?"":getAlias());
    }
    
}
