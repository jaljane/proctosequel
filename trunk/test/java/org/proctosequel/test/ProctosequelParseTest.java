package org.proctosequel.test;

import java.io.File;
import java.io.StringReader;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.proctosequel.antlr.ProcToSequelGrammarLexer;
import org.proctosequel.antlr.ProcToSequelGrammarParser;
import org.proctosequel.parsing.commands.ReadExportQueryCommand;
import org.proctosequel.parsing.commands.ReadExportResultCommand;
import org.proctosequel.parsing.commands.ReadQueriesCommand;
import org.proctosequel.parsing.utils.ProctosequelHelper;
import org.proctosequel.utils.FileHelper;

/**
 *
 * @author Jamel
 */
public class ProctosequelParseTest extends BaseTest {
    
    public static Logger log = Logger.getLogger(ProctosequelParseTest.class);
    
    public ProctosequelParseTest() {
        
    }
    
    
    
    @Test
    public void testProcToSequelParse() throws Exception{        
            String content = FileHelper.getContent(getClass().getResource("/org/proctosequel/datasets/dummy.txt"));
            content = ProctosequelHelper.progToLowerCase(content);
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
            tree.inspect(parser);
    }
 
    
    
}