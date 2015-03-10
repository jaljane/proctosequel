// Generated from src/conf/ProcToSequelGrammar.g4 by ANTLR 4.1
package org.proctosequel.antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ProcToSequelGrammarParser}.
 */
public interface ProcToSequelGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ProcToSequelGrammarParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(@NotNull ProcToSequelGrammarParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcToSequelGrammarParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(@NotNull ProcToSequelGrammarParser.ProgContext ctx);

	/**
	 * Enter a parse tree produced by {@link ProcToSequelGrammarParser#setvar}.
	 * @param ctx the parse tree
	 */
	void enterSetvar(@NotNull ProcToSequelGrammarParser.SetvarContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcToSequelGrammarParser#setvar}.
	 * @param ctx the parse tree
	 */
	void exitSetvar(@NotNull ProcToSequelGrammarParser.SetvarContext ctx);

	/**
	 * Enter a parse tree produced by {@link ProcToSequelGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull ProcToSequelGrammarParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcToSequelGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull ProcToSequelGrammarParser.ExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link ProcToSequelGrammarParser#selectStmt}.
	 * @param ctx the parse tree
	 */
	void enterSelectStmt(@NotNull ProcToSequelGrammarParser.SelectStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcToSequelGrammarParser#selectStmt}.
	 * @param ctx the parse tree
	 */
	void exitSelectStmt(@NotNull ProcToSequelGrammarParser.SelectStmtContext ctx);

	/**
	 * Enter a parse tree produced by {@link ProcToSequelGrammarParser#exportToFunction}.
	 * @param ctx the parse tree
	 */
	void enterExportToFunction(@NotNull ProcToSequelGrammarParser.ExportToFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcToSequelGrammarParser#exportToFunction}.
	 * @param ctx the parse tree
	 */
	void exitExportToFunction(@NotNull ProcToSequelGrammarParser.ExportToFunctionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ProcToSequelGrammarParser#exportToTable}.
	 * @param ctx the parse tree
	 */
	void enterExportToTable(@NotNull ProcToSequelGrammarParser.ExportToTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcToSequelGrammarParser#exportToTable}.
	 * @param ctx the parse tree
	 */
	void exitExportToTable(@NotNull ProcToSequelGrammarParser.ExportToTableContext ctx);

	/**
	 * Enter a parse tree produced by {@link ProcToSequelGrammarParser#inst}.
	 * @param ctx the parse tree
	 */
	void enterInst(@NotNull ProcToSequelGrammarParser.InstContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcToSequelGrammarParser#inst}.
	 * @param ctx the parse tree
	 */
	void exitInst(@NotNull ProcToSequelGrammarParser.InstContext ctx);

	/**
	 * Enter a parse tree produced by {@link ProcToSequelGrammarParser#sqlPart}.
	 * @param ctx the parse tree
	 */
	void enterSqlPart(@NotNull ProcToSequelGrammarParser.SqlPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcToSequelGrammarParser#sqlPart}.
	 * @param ctx the parse tree
	 */
	void exitSqlPart(@NotNull ProcToSequelGrammarParser.SqlPartContext ctx);
}