/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proctosequel;

import java.io.File;
import java.io.StringReader;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.proctosequel.antlr.ProcToSequelGrammarLexer;
import org.proctosequel.antlr.ProcToSequelGrammarParser;
import org.proctosequel.query.commands.ReadExportQueryCommand;
import org.proctosequel.query.commands.ReadExportResultCommand;
import org.proctosequel.query.commands.ReadQueriesCommand;
import org.proctosequel.query.utils.ProgTreeHelper;
import org.proctosequel.utils.FileHelper;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class ProceduraltoSequal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            String content = FileHelper.getContent(new File("C:\\Users\\Jamel\\Documents\\NetBeansProjects\\proctosequel\\src\\java\\org\\proctosequel\\dummy_1.txt"));
            content = ProgTreeHelper.progToLowerCase(content);
            ANTLRInputStream input = new ANTLRInputStream(new StringReader(content));
            ProcToSequelGrammarLexer lexer = new ProcToSequelGrammarLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ProcToSequelGrammarParser parser = new ProcToSequelGrammarParser(tokens);
            parser.setBuildParseTree(true);
            ProcToSequelGrammarParser.ProgContext tree = parser.prog();
            System.out.println("-------------------------------ReadQueriesCommand---------------------------------------");
            ReadQueriesCommand readQueriesCommand = new ReadQueriesCommand(tree);
            readQueriesCommand.execute();
            System.out.println("-------------------------------ReadExportQueryCommand---------------------------------------");
            ReadExportQueryCommand readExportQueryCommand = new ReadExportQueryCommand(tree);
            readExportQueryCommand.execute();
            System.out.println("------------------------------ReadExportResultCommand----------------------------------------");
            ReadExportResultCommand readExportResultCommand = new ReadExportResultCommand(tree);
            readExportResultCommand.execute();
//            tree.inspect(parser);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}
