
package org.proctosequel.query.commands;

import java.util.ArrayList;
import java.util.List;
import org.proctosequel.antlr.ProcToSequelGrammarParser;
import org.proctosequel.antlr.ProcToSequelGrammarParser.InstContext;
import org.proctosequel.query.om.ExportResult;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class ReadExportResultCommand implements Command{
    
    private List<ExportResult> exportResults = new ArrayList<>();
    
    
    private ProcToSequelGrammarParser.ProgContext progTree;

    public ReadExportResultCommand(ProcToSequelGrammarParser.ProgContext progTree) {
        this.progTree = progTree;
    }
    
    public void execute(){
        for(InstContext instContext : getProgTree().inst()){
            if (instContext.exportToTable()!= null && !instContext.exportToTable().isEmpty()){
                ExportResult exportResult = new ExportResult();
                ProcToSequelGrammarParser.ExportToTableContext exportToTableContext = instContext.exportToTable();
                
                for(int i=0;i<exportToTableContext.getChildCount();i++){
                    if("(".equals(exportToTableContext.getChild(i).getText()) && exportResult.getQueryVarname() == null){
                        exportResult.setQueryVarname(exportToTableContext.getChild(i+1).getText());
                        i++;
                    }
                    if("to".equals(exportToTableContext.getChild(i).getText())){
                        exportResult.setTablename(exportToTableContext.getChild(i+1).getText());
                        i++;
                    }
                    if("primaryKey".equals(exportToTableContext.getChild(i).getText())){
                        ProcToSequelGrammarParser.ExprContext exprContext = (ProcToSequelGrammarParser.ExprContext) exportToTableContext.getChild(i+1);
                        exprContext = (ProcToSequelGrammarParser.ExprContext) exprContext.getChild(1);
                        for(int j=0;j<exprContext.getChildCount();j++){
                            if(!",".equals(exprContext.getChild(j).getText())){
                                exportResult.getPrimaryKeyColumns().add(exprContext.getChild(j).getText());
                            }
                            
                        }
                        
                    }
                    
                }
                exportResults.add(exportResult);
            }
            
        }
        System.out.println(exportResults);
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
    public List<ExportResult> getExportResults() {
        return exportResults;
    }
    
}
