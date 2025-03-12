// Generated from Grammar.g4 by ANTLR 4.9.1
package cfgparser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#goal}.
	 * @param ctx the parse tree
	 */
	void enterGoal(GrammarParser.GoalContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#goal}.
	 * @param ctx the parse tree
	 */
	void exitGoal(GrammarParser.GoalContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(GrammarParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(GrammarParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#rule_list}.
	 * @param ctx the parse tree
	 */
	void enterRule_list(GrammarParser.Rule_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#rule_list}.
	 * @param ctx the parse tree
	 */
	void exitRule_list(GrammarParser.Rule_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#lhs}.
	 * @param ctx the parse tree
	 */
	void enterLhs(GrammarParser.LhsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#lhs}.
	 * @param ctx the parse tree
	 */
	void exitLhs(GrammarParser.LhsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#rhs}.
	 * @param ctx the parse tree
	 */
	void enterRhs(GrammarParser.RhsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#rhs}.
	 * @param ctx the parse tree
	 */
	void exitRhs(GrammarParser.RhsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#or_list}.
	 * @param ctx the parse tree
	 */
	void enterOr_list(GrammarParser.Or_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#or_list}.
	 * @param ctx the parse tree
	 */
	void exitOr_list(GrammarParser.Or_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#symbol_list}.
	 * @param ctx the parse tree
	 */
	void enterSymbol_list(GrammarParser.Symbol_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#symbol_list}.
	 * @param ctx the parse tree
	 */
	void exitSymbol_list(GrammarParser.Symbol_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#symbol}.
	 * @param ctx the parse tree
	 */
	void enterSymbol(GrammarParser.SymbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#symbol}.
	 * @param ctx the parse tree
	 */
	void exitSymbol(GrammarParser.SymbolContext ctx);
}