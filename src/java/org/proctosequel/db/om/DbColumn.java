/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.db.om;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class DbColumn {
    
    private String name;
    private int type;

    public DbColumn(String name, int type) {
        this.name = name;
        this.type = type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }
    
    

}
