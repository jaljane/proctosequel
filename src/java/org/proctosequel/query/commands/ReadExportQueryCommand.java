
package org.proctosequel.query.commands;

import java.util.ArrayList;
import java.util.List;
import org.proctosequel.antlr.ProcToSequelGrammarParser;
import org.proctosequel.antlr.ProcToSequelGrammarParser.InstContext;
import org.proctosequel.query.exception.SemanticsError;
import org.proctosequel.query.om.ExportQuery;
import org.proctosequel.query.parsing.VarNamesVisitor;
import org.proctosequel.query.utils.Errors;

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
            if (instContext.exportToFunction()!= null && !instContext.exportToFunction().isEmpty()){
                VarNamesVisitor varNameVisitor = new VarNamesVisitor();
                varNameVisitor.visit(instContext.exportToFunction());
                if(varNameVisitor.getVarNames().size() == 1){
                    exportQueries.add(new ExportQuery(varNameVisitor.getVarNames().get(0)));
                }else {
                    throw new SemanticsError(Errors.bad_export_to_function_expr);
                }
                
            }
        }
        System.out.println(exportQueries);
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
