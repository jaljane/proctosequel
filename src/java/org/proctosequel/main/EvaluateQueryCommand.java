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
public class EvaluateQueryCommand implements Command{

    private Query query;

    public EvaluateQueryCommand(Query query) {
        this.query = query;
    }
    
    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
