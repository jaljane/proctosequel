package org.proctosequel.main.commands.nested;

import org.proctosequel.Command;
import org.proctosequel.parsing.om.Query;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class SimplifyQueryCommand implements Command{

    private Query query;
    private Query result;

    public SimplifyQueryCommand(Query query, Query result) {
        this.query = query;        
        this.result = result;
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
     * @return the result
     */
    public Query getResult() {
        return result;
    }

}
