/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.main.commands.nested;

import java.util.List;
import org.proctosequel.Command;
import org.proctosequel.parsing.om.Query;
import org.proctosequel.parsing.om.composite.TableJoinExpr;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class JoinQueryToJoinExprCommand implements Command{

    private TableJoinExpr tableJoinExpr;
    private List<Query> nested;
    private Query result;

    public JoinQueryToJoinExprCommand(TableJoinExpr tableJoinExpr, List<Query> nested, Query result) {
        this.tableJoinExpr = tableJoinExpr;
        this.nested = nested;
        this.result = result;
    }
    
    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }



}
