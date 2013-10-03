/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proctosequel.query.commands;

import java.util.ArrayList;
import java.util.List;
import org.proctosequel.antlr.ProcToSequalGrammarParser;
import org.proctosequel.antlr.ProcToSequalGrammarParser.InstContext;
import org.proctosequel.query.om.ExportResult;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class ReadExportResultCommand  implements Command{
    
    private List<ExportResult> exportResult = new ArrayList<>();
    
    private ProcToSequalGrammarParser.ProgContext progTree;

    public ReadExportResultCommand(ProcToSequalGrammarParser.ProgContext progTree) {
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
    public List<ExportResult> getExportResult() {
        return exportResult;
    }
    
}
