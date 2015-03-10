// Generated from src/conf/LogicExprGrammar.g4 by ANTLR 4.1
package org.proctosequel.antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LogicExprGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LogicExprGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LogicExprGrammarParser#subCond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubCond(@NotNull LogicExprGrammarParser.SubCondContext ctx);

	/**
	 * Visit a parse tree produced by {@link LogicExprGrammarParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(@NotNull LogicExprGrammarParser.ConditionContext ctx);

	/**
	 * Visit a parse tree produced by {@link LogicExprGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(@NotNull LogicExprGrammarParser.ExprContext ctx);
}