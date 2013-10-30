package org.proctosequel.main.commands.nested;

import org.proctosequel.Command;
import org.proctosequel.parsing.om.Query;
import org.proctosequel.parsing.om.composite.JoinExp;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class JoinQueryToJoinExprCommand implements Command{

    private JoinExp joinExpr;
    private Query nested;
    private Query result;

    public JoinQueryToJoinExprCommand(JoinExp joinExpr, Query nested, Query result) {
        this.joinExpr = joinExpr;
        this.nested = nested;
        this.result = result;
    }
    
    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }



}
