/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proctosequel.parsing.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.lang3.StringUtils;
import org.proctosequel.antlr.LogicExprGrammarParser;
import org.proctosequel.antlr.ProcToSequelGrammarLexer;
import org.proctosequel.antlr.ProcToSequelGrammarParser;
import org.proctosequel.parsing.exception.SemanticsError;
import org.proctosequel.parsing.om.composite.AliasedData;
import org.proctosequel.parsing.om.composite.Column;
import org.proctosequel.parsing.om.composite.Condition;
import org.proctosequel.parsing.om.composite.EqualMatchCondition;
import org.proctosequel.parsing.om.composite.ExprCondition;
import org.proctosequel.parsing.om.composite.GroupBy;
import org.proctosequel.parsing.om.composite.JoinExp;
import org.proctosequel.parsing.om.composite.Table;
import org.proctosequel.parsing.om.composite.TableJoinExpr;
import static org.proctosequel.parsing.utils.ProctosequelHelper.log;
import static org.proctosequel.parsing.utils.ProctosequelHelper.parseExpr;
import static org.proctosequel.parsing.utils.ProctosequelHelper.parseSqlPart;
import org.proctosequel.parsing.visitors.AddSpaceVisitor;
import org.proctosequel.parsing.visitors.AllTokensVisitor;
import org.proctosequel.parsing.visitors.IsolateOrExprVisitor;
import org.proctosequel.parsing.visitors.RemoveTokensVisitor;
import org.proctosequel.parsing.visitors.SplitBySepVisitor;
import org.proctosequel.parsing.visitors.StoreIsolatedOrExprVisitor;
import org.proctosequel.parsing.visitors.StoreNestedQueriesVisitor;
import org.proctosequel.utils.StringHelper;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class QueryPaseHelper {
    
    private static List<String> joinKeywords = Arrays.asList("left", "right", "outer", "inner", "join", "on");
    private static List<String> conditionKeywords = Arrays.asList("and", "or");
    private static List<String> operatorKeywords = Arrays.asList("=", ">", "<", "<=", ">=", "<>");
//    private static List<String> parenthesisKeywords = Arrays.asList("(", ")");
    
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
    
    
    public static List<TableJoinExpr> getTableJoinExpr(String varname, ProcToSequelGrammarParser.SqlPartContext sqlPartContext){        
        List<TableJoinExpr> result = new ArrayList<>();
        List<String> parts = getSepCommaTokens(varname, sqlPartContext);
        for(String part : parts){
            ProcToSequelGrammarParser.SqlPartContext sqlpartfragment = parseSqlPart(part);
            if(!hasSelectStmt(sqlpartfragment) && hasJoinExpr(sqlpartfragment)){
                List<JoinExp> joinExps =  getJoinExps(varname, sqlpartfragment);
                TableJoinExpr tableJoinExpr = new TableJoinExpr();
                tableJoinExpr.getJoinExps().addAll(joinExps);
                result.add(tableJoinExpr);
            }else {
               TableJoinExpr tableJoinExpr = new TableJoinExpr();
               tableJoinExpr.setTable(new Table(getAliasedData(varname, sqlpartfragment)));
               result.add(tableJoinExpr);                      
            }
        }
        return result;
       
    }
    
    public static List<Condition> getQueryConditions(String varname, ProcToSequelGrammarParser.SqlPartContext sqlPartContext){
        List<Condition> result = new ArrayList<>();
        StoreNestedQueriesVisitor storeNestedQueriesVisitor = new StoreNestedQueriesVisitor();
        storeNestedQueriesVisitor.visit(sqlPartContext);
        String expr = storeNestedQueriesVisitor.getExpr();
        IsolateOrExprVisitor isolateOrExprVisitor = new IsolateOrExprVisitor();
        ParseTree exprTree = ProctosequelHelper.parseCondition(expr);
        isolateOrExprVisitor.visit(exprTree);
        StoreIsolatedOrExprVisitor storeIsolatedOrExprVisitor = new StoreIsolatedOrExprVisitor(isolateOrExprVisitor.getOrExprs());
        storeIsolatedOrExprVisitor.visit(exprTree);
        expr = storeIsolatedOrExprVisitor.getExpr();
        //"(", ")"
        RemoveTokensVisitor removeTokensVisitor = new RemoveTokensVisitor(new RemoveTokensVisitor.TokenFilter() {

            @Override
            public boolean accept(TerminalNode tn) {
                return tn.getParent() instanceof LogicExprGrammarParser.SubCondContext && ("(".equals(tn.getText()) || ")".equals(tn.getText()));
            }
        });
        removeTokensVisitor.visit(ProctosequelHelper.parseCondition(expr));
        expr=removeTokensVisitor.getExpr();
        SplitBySepVisitor splitBySepVisitor = new SplitBySepVisitor("and");
        splitBySepVisitor.visit(ProctosequelHelper.parseCondition(expr));
        List<String> conditions = splitBySepVisitor.getTokens();
        for(String cond : conditions){

            String condrestored = StringHelper.restoreAllTokens(cond, storeNestedQueriesVisitor.getTokenContent());
            condrestored = StringHelper.restoreAllTokens(condrestored, storeIsolatedOrExprVisitor.getTokenContent());
            EqualMatchCondition condition= getEqualMatchCondition(varname, ProctosequelHelper.parseSqlPart(condrestored) );
            if(condition !=null){
                result.add(condition);
            }else {
                result.add(new ExprCondition(condrestored));
            }
            
        }
        return result;
    }
    
    public static EqualMatchCondition getEqualMatchCondition(String varname, ProcToSequelGrammarParser.SqlPartContext sqlPartContext){
        String leftOperand = "";
        String rightOperand = "";
        ParseTree expr = sqlPartContext;
        if(sqlPartContext.getChildCount() == 1){            
            if(!(sqlPartContext.getChild(0) instanceof ProcToSequelGrammarParser.ExprContext)){
               throw new SemanticsError(String.format(Errors.bad_or_unsupported_logic_condition, varname));               
            }
            expr = (ProcToSequelGrammarParser.ExprContext) sqlPartContext.getChild(0);            
        }
        boolean shift = false;
        for(int i = 0; i< expr.getChildCount();i++){
            if("=".equals(expr.getChild(i).getText())){
                shift = true;
                continue;
            }
            if(!shift){
                AddSpaceVisitor addSpaceVisitor = new AddSpaceVisitor();
                addSpaceVisitor.visit(expr.getChild(i));
                if(StringUtils.startsWithAny(addSpaceVisitor.getExpr(), Constants.QUALIFIER_SEP_CHARS)){
                    leftOperand += addSpaceVisitor.getExpr();
                }else {
                    leftOperand += " " + addSpaceVisitor.getExpr();
                }                
            }else {
                AddSpaceVisitor addSpaceVisitor = new AddSpaceVisitor();
                addSpaceVisitor.visit(expr.getChild(i));
                if(StringUtils.startsWithAny(addSpaceVisitor.getExpr(), Constants.QUALIFIER_SEP_CHARS)){
                    rightOperand += addSpaceVisitor.getExpr();
                }else {
                    rightOperand += " " + addSpaceVisitor.getExpr();
                }                 
            }
        }
        if(StringUtils.isBlank(rightOperand)){
            return null;
        }
        return new EqualMatchCondition(new Column(null, leftOperand) , new Column(null, rightOperand) );
    }
    
    
    public static GroupBy getQueryGroupBy(String varname, ProcToSequelGrammarParser.SqlPartContext sqlPartContext){
        if(sqlPartContext!=null){
            SplitBySepVisitor splitBySepVisitor = new SplitBySepVisitor("having");
            splitBySepVisitor.visit(sqlPartContext);
            if(splitBySepVisitor.getTokens().size() < 1 || splitBySepVisitor.getTokens().size() > 2){
                throw new SemanticsError(String.format(Errors.bad_or_unsupported_groupby, varname) );            
            }else{
                 List<String> groupByExprs = getSepCommaTokens(varname, ProctosequelHelper.parseSqlPart(splitBySepVisitor.getTokens().get(0)));
                 GroupBy groupBy = new GroupBy();
                 groupBy.getExprs().addAll(groupByExprs);
                 if(splitBySepVisitor.getTokens().size() == 2){
                    groupBy.setHavingexpr(splitBySepVisitor.getTokens().get(1));    
                 }
                 return groupBy;
            }            
        }else {
            return null;
        }
        
    }
    
    private static List<JoinExp> getJoinExps(String varname, ProcToSequelGrammarParser.SqlPartContext sqlPartContext){
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
//                Condition condition = new ExprCondition(buffer2.get(i));
                List<EqualMatchCondition> conditions = getJoinEqualMatchConditions(varname, buffer2.get(i));
                joinExp.getConditions().addAll(conditions);
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
    
    private static List<EqualMatchCondition> getJoinEqualMatchConditions(String varname, String expr){
        List<EqualMatchCondition> result = new ArrayList<>();
        ProcToSequelGrammarParser.ExprContext exprContext = parseExpr(expr);
        AllTokensVisitor allTokensVisitor = new AllTokensVisitor();
        allTokensVisitor.visit(exprContext);
        List<String> bufferList = new ArrayList<>();
        String buffer = "";
        for(String token : allTokensVisitor.getTokens()){
            if(conditionKeywords.contains(token) && !"and".equals(token)){
                throw new SemanticsError(String.format(Errors.bad_or_unsupported_eq_match, varname));
            }
            if(operatorKeywords.contains(token) && !"=".equals(token)){
                throw new SemanticsError(String.format(Errors.bad_or_unsupported_eq_match, varname));
            }    
            if("and".equals(token)){
                bufferList.add(buffer);
                buffer="";
            }else {
                buffer+=token;
            }
        }
        if(!StringUtils.isEmpty(buffer) ){
            bufferList.add(buffer);
        }
        for(String item : bufferList){
            String[] operands = StringUtils.split(item, "=");
            if(operands.length!=2){
                throw new SemanticsError(String.format(Errors.bad_or_unsupported_eq_match, varname));
            }
            Column column1 = new Column(null, operands[0]);
            Column column2 = new Column(null, operands[1]);
            EqualMatchCondition equalMatchCondition = new EqualMatchCondition(column1, column2);
            result.add(equalMatchCondition);
        }
        return result;
    }
    
    
    public static boolean hasJoinExpr(ProcToSequelGrammarParser.SqlPartContext sqlPartContext){
        for(int i=0;i<sqlPartContext.getChildCount();i++){
            AddSpaceVisitor addSpaceVisitor = new AddSpaceVisitor();
            addSpaceVisitor.visit(sqlPartContext.getChild(i));
            if(addSpaceVisitor.getExpr().contains(" join ")){
                return true;
            }
        }
        return false;        
    }
    
    public static boolean hasSelectStmt(ProcToSequelGrammarParser.SqlPartContext sqlPartContext){
        for(int i=0;i<sqlPartContext.getChildCount();i++){
            if(sqlPartContext.getChild(i) instanceof ProcToSequelGrammarParser.SelectStmtContext){
                return true;
            }
        }
        return false;
    }    
         
    public static boolean isQueryIdentifier(ProcToSequelGrammarParser.SqlPartContext sqlPartContext){
        for(int i=0;i<sqlPartContext.getChildCount();i++){
            if(sqlPartContext.getChild(i) instanceof ProcToSequelGrammarParser.ExprContext 
                    && Pattern.matches(Constants.VAR_NAME_REGEX, sqlPartContext.getChild(i).getChild(0).getText()) ){
                return true;
            }
        }
        return false;
    }
    
    
    public static void main(String[] args){
        ProcToSequelGrammarParser.SqlPartContext sqlPartContext = ProctosequelHelper.parseSqlPart("(select yy.jj from fjjjf where kkk= ii)=llf(hh.kkfj, kkf.kkj)");
        ProcToSequelGrammarParser parser= ProctosequelHelper.getProcToSequelParser("(select yy.jj from fjjjf where kkk= ii)=llf(hh.kkfj, kkf.kkj)");
        sqlPartContext.inspect(parser);
        
    }
    
}
