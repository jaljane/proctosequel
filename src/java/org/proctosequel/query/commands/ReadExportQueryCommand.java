/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proctosequel.query.commands;

import java.util.ArrayList;
import java.util.List;
import org.proctosequel.antlr.ProcToSequelGrammarParser;
import org.proctosequel.antlr.ProcToSequelGrammarParser.InstContext;
import org.proctosequel.query.om.ExportQuery;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class ReadExportQueryCommand implements Command{
    
    private List<ExportQuery> exportQueries = new ArrayList<>();
    private ProcToSequelGrammarParser.ProgContext progTree;

    public ReadExportQueryCommand(ProcToSequelGrammarParser.ProgContext progTree) {
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
    public ProcToSequelGrammarParser.ProgContext getProgTree() {
        return progTree;
    }

    /**
     * @return the exportQueries
     */
    public List<ExportQuery> getExportQueries() {
        return exportQueries;
    }
    
}
