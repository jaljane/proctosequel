/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proctosequel.query.commands;

import java.util.ArrayList;
import java.util.List;
import org.proctosequel.antlr.ProcToSequalGrammarParser;
import org.proctosequel.antlr.ProcToSequalGrammarParser.InstContext;
import org.proctosequel.query.om.ExportQuery;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class ReadExportQueryCommand implements Command{
    
    private List<ExportQuery> exportQueries = new ArrayList<>();
    private ProcToSequalGrammarParser.ProgContext progTree;

    public ReadExportQueryCommand(ProcToSequalGrammarParser.ProgContext progTree) {
        this.progTree = progTree;
    }
    
    public void execute(){
        for(InstContext instContext : getProgTree().inst()){
            if (instContext.setvar()!= null && !instContext.setvar().isEmpty()){

//                System.out.println(); 
//                exportQueries.add();
            }
        }
    }

    /**
     * @return the progTree
     */
    public ProcToSequalGrammarParser.ProgContext getProgTree() {
        return progTree;
    }

    /**
     * @return the exportQueries
     */
    public List<ExportQuery> getExportQueries() {
        return exportQueries;
    }
    
}
