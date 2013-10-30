package org.proctosequel.parsing;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.lang3.StringUtils;
import org.proctosequel.antlr.ProcToSequelGrammarLexer;
import org.proctosequel.antlr.ProcToSequelGrammarParser;
import org.proctosequel.parsing.commands.ReadExportQueryCommand;
import org.proctosequel.parsing.commands.ReadExportResultCommand;
import org.proctosequel.parsing.commands.ReadQueriesCommand;
import org.proctosequel.parsing.om.ExportQuery;
import org.proctosequel.parsing.om.ExportResult;
import org.proctosequel.parsing.om.Query;
import org.proctosequel.parsing.utils.Constants;
import org.proctosequel.parsing.utils.ProctosequelHelper;
import org.proctosequel.utils.FileHelper;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class QueryDefCache {

    private Map<String, Query> queries = new LinkedHashMap<>();
    private List<ExportQuery> exportQueries = new ArrayList<>();
    private List<ExportResult> exportResults = new ArrayList<>();
    
    private static QueryDefCache queryDefCache;
    
    private QueryDefCache() {
        
    }
    
    public static QueryDefCache getInstance() throws IOException{
        synchronized(QueryDefCache.class){
            if(queryDefCache==null){
                queryDefCache = new QueryDefCache();
                queryDefCache.init();                
            }
        }
        return queryDefCache;
        
    }
    
    private void init() throws IOException{
        String[] paths = StringUtils.split(System.getProperty("proctosequel.queryDefs"));
        for(String path : paths){
            String content = FileHelper.getContent(getClass().getResource(path));
            content = ProctosequelHelper.progToLowerCase(content, Constants.replaceTokenMaps);
            ANTLRInputStream input = new ANTLRInputStream(new StringReader(content));
            ProcToSequelGrammarLexer lexer = new ProcToSequelGrammarLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ProcToSequelGrammarParser parser = new ProcToSequelGrammarParser(tokens);
            parser.setBuildParseTree(true);
            ProcToSequelGrammarParser.ProgContext tree = parser.prog();
            ReadQueriesCommand readQueriesCommand = new ReadQueriesCommand(tree);
            readQueriesCommand.execute();
            this.getQueries().putAll(readQueriesCommand.getQueries());
            ReadExportQueryCommand readExportQueryCommand = new ReadExportQueryCommand(tree);
            readExportQueryCommand.execute();
            this.getExportQueries().addAll(readExportQueryCommand.getExportQueries());
            ReadExportResultCommand readExportResultCommand = new ReadExportResultCommand(tree);
            readExportResultCommand.execute();                
            this.getExportResults().addAll(readExportResultCommand.getExportResults());
        }
    }
    
    public static void reload() throws IOException{
        synchronized(QueryDefCache.class){
            queryDefCache.clear();
            queryDefCache.init();
        }
    }
    
    private void clear(){
        queries.clear();
        exportQueries.clear();
        exportResults.clear();
    }

    /**
     * @return the queries
     */
    public Map<String, Query> getQueries() {
        return queries;
    }

    /**
     * @return the exportQueries
     */
    public List<ExportQuery> getExportQueries() {
        return exportQueries;
    }

    /**
     * @return the exportResults
     */
    public List<ExportResult> getExportResults() {
        return exportResults;
    }
    
    
}
