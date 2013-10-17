package org.proctosequel.parsing.utils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.proctosequel.antlr.ProcToSequelGrammarLexer;
import org.proctosequel.antlr.ProcToSequelGrammarParser;
import org.proctosequel.parsing.exception.SemanticsError;
import org.proctosequel.parsing.exception.SyntaxError;
import org.proctosequel.parsing.om.composite.AliasedData;
import org.proctosequel.parsing.om.composite.Condition;
import org.proctosequel.parsing.om.composite.EqualMatchCondition;
import org.proctosequel.parsing.om.composite.ExprCondition;
import org.proctosequel.parsing.om.composite.JoinExp;
import org.proctosequel.parsing.om.composite.Table;
import org.proctosequel.parsing.om.composite.TableJoinExpr;
import org.proctosequel.parsing.visitors.composite.AddSpaceVisitor;
import org.proctosequel.parsing.visitors.composite.AllTokensVisitor;
import org.proctosequel.utils.StringHelper;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class ProctosequelHelper {
    
    public static Logger log = Logger.getLogger(ProctosequelHelper.class);
    
    private static final Map<String, String> replaceTokenMaps = new HashMap<String, String>() {
        {
            put("exportquery", "exportQuery");
            put("exportresult", "exportResult");
            put("primarykey", "primaryKey");
        }
    };
    private static List<String> joinKeywords = Arrays.asList("left", "right", "outer", "inner", "join", "on");
    private static List<String> conditionKeywords = Arrays.asList("and", "or");
    private static List<String> operatorKeywords = Arrays.asList("=", ">", "<", "<=", ">=", "<>");
    private static List<String> parenthesisKeywords = Arrays.asList("(", ")");
    
    public static String progToLowerCase(String prog) {
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
    
    public static ProcToSequelGrammarParser getParser(String expr) {
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
    
    public static List<String> getSepCommaTokens(String varname, ProcToSequelGrammarParser.SqlPartContext sqlPartContext){
        List<String> result = new ArrayList<>();
        List<ParseTree> parseTreeBuffer = new ArrayList<>();
        for(int i=0;i<sqlPartContext.getChildCount();i++){
            if(sqlPartContext.getChild(i) instanceof ProcToSequelGrammarParser.ExprContext){
                ProcToSequelGrammarParser.ExprContext ctx = (ProcToSequelGrammarParser.ExprContext) sqlPartContext.getChild(i);
                for(int j=0;j<ctx.getChildCount();j++){
                    if(!",".equals(ctx.getChild(j).getText()) ){
                        parseTreeBuffer.add(ctx.getChild(j));

                    }else {
                        String buffer = "";
                        for(ParseTree parseTree : parseTreeBuffer){
                            AddSpaceVisitor visitor = new AddSpaceVisitor();
                            visitor.visit(parseTree);                        
                            buffer+=visitor.getExpr();
                        }
                        parseTreeBuffer.clear();
                        result.add(buffer);                    
                    }

                }

                String buffer = "";

                for(ParseTree parseTree : parseTreeBuffer){
                    AddSpaceVisitor visitor = new AddSpaceVisitor();
                    visitor.visit(parseTree);
                    buffer+=visitor.getExpr();
                }
                parseTreeBuffer.clear();
                result.add(buffer);                    

            }else if(sqlPartContext.getChild(i) instanceof ProcToSequelGrammarParser.SelectStmtContext){
                ProcToSequelGrammarParser.SelectStmtContext ctx = (ProcToSequelGrammarParser.SelectStmtContext) sqlPartContext.getChild(i);
                parseTreeBuffer.add(ctx);                 
            }else {
                throw new SemanticsError(String.format(Errors.bad_sql_part_fragment, varname, sqlPartContext.getChild(i).getText()) );
            }
        }
        return result;
        
    }
    
    public static AliasedData getAliasedData(String varname, ProcToSequelGrammarParser.SqlPartContext sqlPartContext){
        if(sqlPartContext.getChildCount() == 1 && sqlPartContext.getChild(0) instanceof ProcToSequelGrammarParser.ExprContext
                || sqlPartContext.getChild(0) instanceof ProcToSequelGrammarParser.SelectStmtContext){
            ParseTree ctx =sqlPartContext.getChildCount() == 1? (ParseTree) sqlPartContext.getChild(0) : sqlPartContext;
            if(ctx.getChildCount() == 1){
                return new AliasedData(null, ctx.getChild(0).getText());                    
            }else {
                AllTokensVisitor asexpr = new AllTokensVisitor();
                asexpr.visit(ctx.getChild(ctx.getChildCount()-1));
                if(asexpr.getTokens().size() == 1
                        || (asexpr.getTokens().size() == 2 && "as".equals(asexpr.getTokens().get(0)))){
                    asexpr.getTokens().remove("as");
                }
                if(Pattern.matches(Constants.WORD_REGEX, asexpr.getTokens().get(0))) {
                    AddSpaceVisitor addSpaceVisitor = new AddSpaceVisitor();
                    for(int j=0;j<ctx.getChildCount()-1;j++){   
                        if(!"as".equals(ctx.getChild(j).getText())){
                            addSpaceVisitor.visit(ctx.getChild(j));
                        }                        
                    }
                    return new AliasedData(asexpr.getTokens().get(0), addSpaceVisitor.getExpr());                    
                }else {
                    AddSpaceVisitor addSpaceVisitor = new AddSpaceVisitor();
                    addSpaceVisitor.visit(ctx);
                    return new AliasedData(null, addSpaceVisitor.getExpr());
                }
            }
        }else {
            throw new SemanticsError(String.format(Errors.bad_sql_part_fragment, varname, sqlPartContext.getChild(0).getText()) );
        }

    }
    
    
    public List<TableJoinExpr> getTableJoinExpr(String varname, ProcToSequelGrammarParser.SqlPartContext sqlPartContext){        
        List<TableJoinExpr> result = new ArrayList<>();
        List<String> parts = getSepCommaTokens(varname, sqlPartContext);
        for(String part : parts){
            ProcToSequelGrammarParser.SqlPartContext sqlpartfragment = parseSqlPart(part);
            if(hasSelectStmt(sqlpartfragment)){ 
               TableJoinExpr tableJoinExpr = new TableJoinExpr();
               tableJoinExpr.setTable(new Table(getAliasedData(varname, sqlPartContext)));
               result.add(tableJoinExpr);
            }else if(hasJoinExpr(sqlPartContext)){
                List<JoinExp> joinExps =  getJoinExps(varname, sqlPartContext);
                TableJoinExpr tableJoinExpr = new TableJoinExpr();
                tableJoinExpr.getJoinExps().addAll(joinExps);
                result.add(tableJoinExpr);
            }
        }
        return result;
       
    }
    
    public static List<JoinExp> getJoinExps(String varname, ProcToSequelGrammarParser.SqlPartContext sqlPartContext){
        List<String> buffer1 = new ArrayList<>();
        List<String> buffer2 = new ArrayList<>();
        
        boolean startOfJoinKeyword = false;
        
        List<ParseTree> children = new ArrayList<>();
        for(int i=0;i<sqlPartContext.getChildCount();i++){
            if(sqlPartContext.getChild(i) instanceof ProcToSequelGrammarParser.ExprContext){
                ProcToSequelGrammarParser.ExprContext exprContext = (ProcToSequelGrammarParser.ExprContext) sqlPartContext.getChild(i);
               for(int j = 0;j<exprContext.getChildCount();j++){
                   children.add(exprContext.getChild(j));
               }                    
            }else {
                children.add(sqlPartContext.getChild(i));
            }
        }
        
        for(int i=0;i<children.size();i++){
            if(children.get(i) instanceof ProcToSequelGrammarParser.ExprContext ){
                if(children.get(i).getText().startsWith("(")){
                    throw new SemanticsError(String.format(Errors.bad_parenthesis_join_fragment, varname));
                }
            }
            if (!joinKeywords.contains(children.get(i).getText())){  
                if(startOfJoinKeyword){
                    buffer2.add(StringHelper.insertSpaces(buffer1));
                    buffer1.clear();                         
                }
                AddSpaceVisitor spaceVisitor = new AddSpaceVisitor();
                spaceVisitor.visit(children.get(i));
                buffer1.add(spaceVisitor.getExpr());
                startOfJoinKeyword = false;
            }else if(joinKeywords.contains(children.get(i).getText()) && !startOfJoinKeyword){
                buffer2.add(StringHelper.insertSpaces(buffer1));
                buffer1.clear();                
                AddSpaceVisitor spaceVisitor = new AddSpaceVisitor();
                spaceVisitor.visit(children.get(i));
                buffer1.add(spaceVisitor.getExpr());
                startOfJoinKeyword = true;
            }else {
                AddSpaceVisitor spaceVisitor = new AddSpaceVisitor();
                spaceVisitor.visit(children.get(i));
                buffer1.add(spaceVisitor.getExpr());
            }
        }
        if(!buffer1.isEmpty()){
            buffer2.add(StringHelper.insertSpaces(buffer1));
        }
        log.debug("buffer2.length : " + buffer2.size());
        log.debug("buffer2:" + buffer2);
        List<JoinExp> result = new ArrayList();
        JoinExp joinExp = new JoinExp();
        JoinExp previousJoinExp = null;
        joinExp.getLeftTables().add(new Table(getAliasedData(varname, parseSqlPart(buffer2.get(0)))));
        
        for(int i=1;i<buffer2.size();i++){
            if(buffer2.get(i).contains("join")){
                joinExp.setJoin(buffer2.get(i));
                i++;
                joinExp.setRightTable(new Table(getAliasedData(varname, parseSqlPart(buffer2.get(i)))));                
            }else if("on".equals(buffer2.get(i).trim()) ){
                i++;
                Condition condition = new ExprCondition(buffer2.get(i));
                joinExp.getConditions().add(condition);
                result.add(joinExp);
                previousJoinExp = joinExp;
                joinExp = new JoinExp();
                joinExp.getLeftTables().addAll(previousJoinExp.getLeftTables());    
                joinExp.getLeftTables().add(previousJoinExp.getRightTable());
            }else {
                throw new SemanticsError(String.format(Errors.bad_or_unsupported_join_fragment, varname ));
            }
        }
        return result;

    }
    
    public static List<EqualMatchCondition> getEqualMatchConditions(String expr){
        ProcToSequelGrammarParser.ExprContext exprContext = parseExpr(expr);
        throw new UnsupportedOperationException("not yet implemented");
    }
    
    private static boolean hasJoinExpr(ProcToSequelGrammarParser.SqlPartContext sqlPartContext){
        for(int i=0;i<sqlPartContext.getChildCount();i++){
            if("".equals(sqlPartContext.getChild(i).getText()) ){
                return true;
            }
        }
        return false;        
    }
    
    private static boolean hasSelectStmt(ProcToSequelGrammarParser.SqlPartContext sqlPartContext){
        for(int i=0;i<sqlPartContext.getChildCount();i++){
            if(sqlPartContext.getChild(i) instanceof ProcToSequelGrammarParser.SelectStmtContext){
                return true;
            }
        }
        return false;
    }    
    
    public static void main (String[] args){
        ProcToSequelGrammarParser.SqlPartContext sqlPartContext = parseSqlPart("table2 as yy inner join (select kklk from hh) h y on jj=ii left outer join table4 on kkd=iii");
//        ProcToSequelGrammarParser.SqlPartContext sqlPartContext = parseSqlPart("select * from toto where titi <= kk");
        getJoinExps("$jj", sqlPartContext);
//        sqlPartContext.inspect(getParser("select * from toto where titi < = kk"));
    }

}
