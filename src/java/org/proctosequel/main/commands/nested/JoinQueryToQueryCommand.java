package org.proctosequel.main.commands.nested;

import org.proctosequel.Command;
import org.proctosequel.parsing.om.Query;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class JoinQueryToQueryCommand implements Command{

    private Query query;
    private Query nested;
    private Query result;

    public JoinQueryToQueryCommand(Query query, Query nested, Query result) {
        this.query = query;
        this.nested = nested;
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
