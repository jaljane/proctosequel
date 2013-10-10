package org.proctosequel.parsing.om.composite;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class EqualMatchCondition implements Condition{
    private Column column1;
    private Column column2;

    public EqualMatchCondition(Column column1, Column column2) {
        this.column1 = column1;
        this.column2 = column2;
    }

    /**
     * @return the column1
     */
    public Column getColumn1() {
        return column1;
    }

    /**
     * @return the column2
     */
    public Column getColumn2() {
        return column2;
    }
    
    
        
}
