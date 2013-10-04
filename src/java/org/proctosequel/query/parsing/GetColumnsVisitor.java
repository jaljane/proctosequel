/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.query.parsing;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.tree.ParseTree;
import org.proctosequel.antlr.ProcToSequelGrammarBaseVisitor;
import org.proctosequel.antlr.ProcToSequelGrammarParser;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class GetColumnsVisitor extends ProcToSequelGrammarBaseVisitor{
    
    private ProcToSequelGrammarParser.SqlPartContext sqlPartContext;
    private List<String> columns = new ArrayList<>();
    private List<ParseTree> parseTreeBuffer = new ArrayList<>();

    @Override
    public Object visitSqlPart(ProcToSequelGrammarParser.SqlPartContext ctx) {
        if(this.sqlPartContext == null){
            this.sqlPartContext = ctx;
        }
        
        return super.visitSqlPart(ctx); 
    }
    
    
    
    @Override
    public Object visitExpr(ProcToSequelGrammarParser.ExprContext ctx) {
        if(ctx.getParent() == this.sqlPartContext ){
            for(int i=0;i<ctx.getChildCount();i++){
                if(!",".equals(ctx.getChild(i).getText()) ){
                    parseTreeBuffer.add(ctx.getChild(i));
                    
                }else {
                    String buffer = "";
                    for(ParseTree parseTree : parseTreeBuffer){
                        AddSpaceVisitor visitor = new AddSpaceVisitor();
                        visitor.visit(parseTree);                        
                        buffer+=visitor.getQuery();
                    }
                    parseTreeBuffer.clear();
                    columns.add(buffer);                    
                }
                
            }
            
            String buffer = "";
            
            for(ParseTree parseTree : parseTreeBuffer){
                AddSpaceVisitor visitor = new AddSpaceVisitor();
                visitor.visit(parseTree);
                buffer+=visitor.getQuery();
            }
            parseTreeBuffer.clear();
            columns.add(buffer);                    
                            
            
        }
        
        return super.visitExpr(ctx);
    }

    @Override
    public Object visitSelectStmt(ProcToSequelGrammarParser.SelectStmtContext ctx) {
        
        if(ctx.getParent() == this.sqlPartContext){
            
            String buffer = "";
            AddSpaceVisitor visitor = new AddSpaceVisitor();
            visitor.visit(ctx);
            buffer+=visitor.getQuery();
            columns.add(buffer);    
        }
        return super.visitSelectStmt(ctx);
    }


    /**
     * @return the columns
     */
    public List<String> getColumns() {
            return columns;
    }

    
    
}
