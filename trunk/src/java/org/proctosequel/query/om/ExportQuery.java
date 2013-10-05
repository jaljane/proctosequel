
package org.proctosequel.query.om;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class ExportQuery {
    
    private String varname;

    public ExportQuery(String varname) {
        this.varname = varname;
    }

    /**
     * @return the varname
     */
    public String getVarname() {
        return varname;
    }

    @Override
    public String toString() {
        return varname;
    }
    
    
    
}
