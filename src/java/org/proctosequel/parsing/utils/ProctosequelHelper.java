package org.proctosequel.parsing.utils;

import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.proctosequel.antlr.LogicExprGrammarLexer;
import org.proctosequel.antlr.LogicExprGrammarParser;
import org.proctosequel.antlr.ProcToSequelGrammarLexer;
import org.proctosequel.antlr.ProcToSequelGrammarParser;
import org.proctosequel.parsing.exception.SyntaxError;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class ProctosequelHelper {
    
    public static Logger log = Logger.getLogger(ProctosequelHelper.class);
        
    public static String progToLowerCase(String prog,  Map<String, String> replaceTokenMaps ) {
        String result = prog;
        Map<String, String> litterals = new LinkedHashMap<>();

        result = replaceLitteral(result, litterals);
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(result, Constants.DELIMITER_CHARS, true);
        while (st.hasMoreTokens()) {
            String token = st.nextToken().toLowerCase();

            if (replaceTokenMaps.get(token) == null) {
                sb.append(token);
            } else {
                sb.append(replaceTokenMaps.get(token));
            }

        }
        return restoreLitteral(sb.toString(), litterals);
    }

    public static String storeLitteral(String prog, int counter, Map<String, String> map, char litteralCote) {
        int start = prog.indexOf(litteralCote);
        if (start == -1) {
            return prog;
        }
        int end = start;
        while (prog.indexOf(litteralCote, end + 1) != -1) {
            char c = prog.charAt(prog.indexOf(litteralCote, end + 1) - 1);
            if (c != litteralCote && c != '\\') {
                end = prog.indexOf(litteralCote, end + 1);
                break;
            } else {
                end = prog.indexOf(litteralCote, end + 1);

            }

        }
        if (prog.indexOf(litteralCote, end + 1) == -1 && start == end) {
            throw new SyntaxError(Errors.bad_string_litteral_error_msg);
        } else {
//            end = prog.indexOf("'", end+1);
            map.put("stored_litteral_" + counter, prog.substring(start, end + 1));
            return storeLitteral(StringUtils.replace(prog, prog.substring(start, end + 1), "stored_litteral_" + counter), counter + 1, map, litteralCote);
        }

    }

    public static String replaceLitteral(String prog, Map<String, String> map) {
        String result = storeLitteral(prog, 1, map, '"');
        return storeLitteral(result, map.size() + 1, map, '\'');

    }

    public static String restoreLitteral(String prog, Map<String, String> litterals) {
        String result = prog;
        for (Map.Entry<String, String> entry : litterals.entrySet()) {
            result = StringUtils.replace(result, entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static ProcToSequelGrammarParser.SqlPartContext parseSqlPart(String expr) {
        try{
            ANTLRInputStream input = new ANTLRInputStream(new StringReader(expr));
            ProcToSequelGrammarLexer lexer = new ProcToSequelGrammarLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ProcToSequelGrammarParser parser = new ProcToSequelGrammarParser(tokens);
            parser.setBuildParseTree(true);
            return parser.sqlPart();            
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static LogicExprGrammarParser.ConditionContext parseCondition(String expr) {
        try{
            ANTLRInputStream input = new ANTLRInputStream(new StringReader(expr));
            LogicExprGrammarLexer lexer = new LogicExprGrammarLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            LogicExprGrammarParser parser = new LogicExprGrammarParser(tokens);
            parser.setBuildParseTree(true);
            return parser.condition();            
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }    
    
    public static ProcToSequelGrammarParser getProcToSequelParser(String expr) {
        try{
            ANTLRInputStream input = new ANTLRInputStream(new StringReader(expr));
            ProcToSequelGrammarLexer lexer = new ProcToSequelGrammarLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ProcToSequelGrammarParser parser = new ProcToSequelGrammarParser(tokens);
            parser.setBuildParseTree(true);
            return parser;            
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }    
    public static LogicExprGrammarParser getLogicExprParser(String expr) {
        try{
            ANTLRInputStream input = new ANTLRInputStream(new StringReader(expr));
            LogicExprGrammarLexer lexer = new LogicExprGrammarLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            LogicExprGrammarParser parser = new LogicExprGrammarParser(tokens);
            parser.setBuildParseTree(true);
            return parser;            
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }      
    
    public static ProcToSequelGrammarParser.ExprContext parseExpr(String expr) {
        try{
            ANTLRInputStream input = new ANTLRInputStream(new StringReader(expr));
            ProcToSequelGrammarLexer lexer = new ProcToSequelGrammarLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ProcToSequelGrammarParser parser = new ProcToSequelGrammarParser(tokens);
            parser.setBuildParseTree(true);
            return parser.expr();            
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }  
    
    public static boolean childOf(ParseTree child, ParseTree parent){
        if(child==null){
            return false;
        }
        return  child.getParent() == parent || childOf(child.getParent(), parent);
    }
    
    public static void main (String[] args){
        LogicExprGrammarParser.ConditionContext conditionContext = parseCondition("(true and (gg=uu and hhy=u) and yyt(ll,ii(kk,yy)) and dd=hh and jjj=uu and ( ll=88 and (kk=uu or kk=77)))");
//        ProcToSequelGrammarParser.SqlPartContext sqlPartContext = parseSqlPart("select * from toto where titi <= kk");
//        getJoinExps("$jj", sqlPartContext);
        conditionContext.inspect(getLogicExprParser("(true and (gg=uu and hhy=u) and yyt(ll,ii(kk,yy)) and dd=hh and jjj=uu and ( ll=88 and (kk=uu or kk=77)))"));
    }

}
