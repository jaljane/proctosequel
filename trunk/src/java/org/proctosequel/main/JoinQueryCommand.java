/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.main;

import org.proctosequel.Command;
import org.proctosequel.parsing.om.Query;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class JoinQueryCommand implements Command{

    private Query query;
    private Query nested;
    private Query result;

    public JoinQueryCommand(Query query, Query nested) {
        this.query = query;
        this.nested = nested;
    }
    
    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the query
     */
    public Query getQuery() {
        return query;
    }

    /**
     * @return the nested
     */
    public Query getNested() {
        return nested;
    }

    /**
     * @return the result
     */
    public Query getResult() {
        return result;
    }

}
