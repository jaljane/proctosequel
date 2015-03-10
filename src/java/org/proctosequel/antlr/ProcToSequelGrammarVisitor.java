// Generated from src/conf/ProcToSequelGrammar.g4 by ANTLR 4.1
package org.proctosequel.antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ProcToSequelGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ProcToSequelGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ProcToSequelGrammarParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(@NotNull ProcToSequelGrammarParser.ProgContext ctx);

	/**
	 * Visit a parse tree produced by {@link ProcToSequelGrammarParser#setvar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetvar(@NotNull ProcToSequelGrammarParser.SetvarContext ctx);

	/**
	 * Visit a parse tree produced by {@link ProcToSequelGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(@NotNull ProcToSequelGrammarParser.ExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link ProcToSequelGrammarParser#selectStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectStmt(@NotNull ProcToSequelGrammarParser.SelectStmtContext ctx);

	/**
	 * Visit a parse tree produced by {@link ProcToSequelGrammarParser#exportToFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExportToFunction(@NotNull ProcToSequelGrammarParser.ExportToFunctionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ProcToSequelGrammarParser#exportToTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExportToTable(@NotNull ProcToSequelGrammarParser.ExportToTableContext ctx);

	/**
	 * Visit a parse tree produced by {@link ProcToSequelGrammarParser#inst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInst(@NotNull ProcToSequelGrammarParser.InstContext ctx);

	/**
	 * Visit a parse tree produced by {@link ProcToSequelGrammarParser#sqlPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlPart(@NotNull ProcToSequelGrammarParser.SqlPartContext ctx);
}