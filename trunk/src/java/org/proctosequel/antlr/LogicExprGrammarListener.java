// Generated from src/conf/LogicExprGrammar.g4 by ANTLR 4.1
package org.proctosequel.antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LogicExprGrammarParser}.
 */
public interface LogicExprGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LogicExprGrammarParser#subCond}.
	 * @param ctx the parse tree
	 */
	void enterSubCond(@NotNull LogicExprGrammarParser.SubCondContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicExprGrammarParser#subCond}.
	 * @param ctx the parse tree
	 */
	void exitSubCond(@NotNull LogicExprGrammarParser.SubCondContext ctx);

	/**
	 * Enter a parse tree produced by {@link LogicExprGrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(@NotNull LogicExprGrammarParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicExprGrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(@NotNull LogicExprGrammarParser.ConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link LogicExprGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull LogicExprGrammarParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicExprGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull LogicExprGrammarParser.ExprContext ctx);
}