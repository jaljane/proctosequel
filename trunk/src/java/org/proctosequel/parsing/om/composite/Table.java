
package org.proctosequel.parsing.om.composite;

import java.util.List;
import org.proctosequel.db.om.DbColumn;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class Table extends AliasedData{

    private List<DbColumn> metadata; 
    
    public Table(String alias, String expr) {
        super(alias, expr);
    }
    
    public Table(AliasedData aliasedData) {
        super(aliasedData.getAlias(), aliasedData.getExpr());
    }    

    /**
     * @return the metadata
     */
    public List<DbColumn> getMetadata() {
        return metadata;
    }

    /**
     * @param metadata the metadata to set
     */
    public void setMetadata(List<DbColumn> metadata) {
        this.metadata = metadata;
    }

    public String getSQL (){
        return getExpr() + " " + (getAlias()==null?"":getAlias());
    }    
}
