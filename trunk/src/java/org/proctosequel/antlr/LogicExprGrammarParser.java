// Generated from src/conf/LogicExprGrammar.g4 by ANTLR 4.1
package org.proctosequel.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LogicExprGrammarParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__3=1, T__2=2, T__1=3, T__0=4, VarName=5, ParamName=6, Word=7, PUNCT=8, 
		OP_ASSIGN=9, OP_EQ=10, COMMA=11, GREATER=12, LOWER=13, PLUS_OP=14, MINUS_OP=15, 
		MULTIPLY_OP=16, DIVIDE_OP=17, SEMICOLON=18, STRING=19, MULTIPLE_LINE_COMMENT_BLOCK=20, 
		ONE_LINE_COMMENT_BLOCK=21, NEW_LINE=22, WS=23;
	public static final String[] tokenNames = {
		"<INVALID>", "')'", "'and'", "'or'", "'('", "VarName", "ParamName", "Word", 
		"PUNCT", "':='", "'='", "','", "'>'", "'<'", "'+'", "'-'", "'*'", "'/'", 
		"';'", "STRING", "MULTIPLE_LINE_COMMENT_BLOCK", "ONE_LINE_COMMENT_BLOCK", 
		"NEW_LINE", "WS"
	};
	public static final int
		RULE_condition = 0, RULE_subCond = 1, RULE_expr = 2;
	public static final String[] ruleNames = {
		"condition", "subCond", "expr"
	};

	@Override
	public String getGrammarFileName() { return "LogicExprGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public LogicExprGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ConditionContext extends ParserRuleContext {
		public List<SubCondContext> subCond() {
			return getRuleContexts(SubCondContext.class);
		}
		public TerminalNode EOF() { return getToken(LogicExprGrammarParser.EOF, 0); }
		public SubCondContext subCond(int i) {
			return getRuleContext(SubCondContext.class,i);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprGrammarListener ) ((LogicExprGrammarListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprGrammarListener ) ((LogicExprGrammarListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprGrammarVisitor ) return ((LogicExprGrammarVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(7); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(6); subCond(0);
				}
				}
				setState(9); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 4) | (1L << VarName) | (1L << ParamName) | (1L << Word) | (1L << STRING))) != 0) );
			setState(11); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubCondContext extends ParserRuleContext {
		public int _p;
		public List<SubCondContext> subCond() {
			return getRuleContexts(SubCondContext.class);
		}
		public SubCondContext subCond(int i) {
			return getRuleContext(SubCondContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public SubCondContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public SubCondContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_subCond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprGrammarListener ) ((LogicExprGrammarListener)listener).enterSubCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprGrammarListener ) ((LogicExprGrammarListener)listener).exitSubCond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprGrammarVisitor ) return ((LogicExprGrammarVisitor<? extends T>)visitor).visitSubCond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubCondContext subCond(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SubCondContext _localctx = new SubCondContext(_ctx, _parentState, _p);
		SubCondContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, RULE_subCond);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(14); expr();
				}
				break;

			case 2:
				{
				setState(15); expr();
				setState(16);
				_la = _input.LA(1);
				if ( !(_la==2 || _la==3) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(17); expr();
				}
				break;

			case 3:
				{
				setState(19); match(4);
				setState(21); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(20); subCond(0);
					}
					}
					setState(23); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 4) | (1L << VarName) | (1L << ParamName) | (1L << Word) | (1L << STRING))) != 0) );
				setState(25); match(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(34);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new SubCondContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_subCond);
					setState(29);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(30);
					_la = _input.LA(1);
					if ( !(_la==2 || _la==3) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					setState(31); subCond(3);
					}
					} 
				}
				setState(36);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<TerminalNode> ParamName() { return getTokens(LogicExprGrammarParser.ParamName); }
		public TerminalNode STRING(int i) {
			return getToken(LogicExprGrammarParser.STRING, i);
		}
		public TerminalNode ParamName(int i) {
			return getToken(LogicExprGrammarParser.ParamName, i);
		}
		public TerminalNode PUNCT(int i) {
			return getToken(LogicExprGrammarParser.PUNCT, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> PUNCT() { return getTokens(LogicExprGrammarParser.PUNCT); }
		public TerminalNode Word(int i) {
			return getToken(LogicExprGrammarParser.Word, i);
		}
		public List<TerminalNode> VarName() { return getTokens(LogicExprGrammarParser.VarName); }
		public List<TerminalNode> Word() { return getTokens(LogicExprGrammarParser.Word); }
		public List<TerminalNode> STRING() { return getTokens(LogicExprGrammarParser.STRING); }
		public TerminalNode VarName(int i) {
			return getToken(LogicExprGrammarParser.VarName, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprGrammarListener ) ((LogicExprGrammarListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprGrammarListener ) ((LogicExprGrammarListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprGrammarVisitor ) return ((LogicExprGrammarVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			switch (_input.LA(1)) {
			case Word:
				{
				setState(37); match(Word);
				}
				break;
			case VarName:
				{
				setState(38); match(VarName);
				}
				break;
			case ParamName:
				{
				setState(39); match(ParamName);
				}
				break;
			case STRING:
				{
				setState(40); match(STRING);
				}
				break;
			case 4:
				{
				setState(41); match(4);
				setState(43); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(42); expr();
					}
					}
					setState(45); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 4) | (1L << VarName) | (1L << ParamName) | (1L << Word) | (1L << STRING))) != 0) );
				setState(47); match(1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(70);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(52);
					_la = _input.LA(1);
					if (_la==PUNCT) {
						{
						setState(51); match(PUNCT);
						}
					}

					setState(66);
					switch (_input.LA(1)) {
					case Word:
						{
						setState(54); match(Word);
						}
						break;
					case VarName:
						{
						setState(55); match(VarName);
						}
						break;
					case ParamName:
						{
						setState(56); match(ParamName);
						}
						break;
					case STRING:
						{
						setState(57); match(STRING);
						}
						break;
					case 4:
						{
						setState(58); match(4);
						setState(60); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(59); expr();
							}
							}
							setState(62); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 4) | (1L << VarName) | (1L << ParamName) | (1L << Word) | (1L << STRING))) != 0) );
						setState(64); match(1);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					} 
				}
				setState(72);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1: return subCond_sempred((SubCondContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean subCond_sempred(SubCondContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return 2 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\31L\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\3\2\6\2\n\n\2\r\2\16\2\13\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\6\3\30\n\3\r\3\16\3\31\3\3\3\3\5\3\36\n\3\3\3\3\3\3\3\7\3#\n\3"+
		"\f\3\16\3&\13\3\3\4\3\4\3\4\3\4\3\4\3\4\6\4.\n\4\r\4\16\4/\3\4\3\4\5\4"+
		"\64\n\4\3\4\5\4\67\n\4\3\4\3\4\3\4\3\4\3\4\3\4\6\4?\n\4\r\4\16\4@\3\4"+
		"\3\4\5\4E\n\4\7\4G\n\4\f\4\16\4J\13\4\3\4\2\5\2\4\6\2\3\3\2\4\5Y\2\t\3"+
		"\2\2\2\4\35\3\2\2\2\6\63\3\2\2\2\b\n\5\4\3\2\t\b\3\2\2\2\n\13\3\2\2\2"+
		"\13\t\3\2\2\2\13\f\3\2\2\2\f\r\3\2\2\2\r\16\7\2\2\3\16\3\3\2\2\2\17\20"+
		"\b\3\1\2\20\36\5\6\4\2\21\22\5\6\4\2\22\23\t\2\2\2\23\24\5\6\4\2\24\36"+
		"\3\2\2\2\25\27\7\6\2\2\26\30\5\4\3\2\27\26\3\2\2\2\30\31\3\2\2\2\31\27"+
		"\3\2\2\2\31\32\3\2\2\2\32\33\3\2\2\2\33\34\7\3\2\2\34\36\3\2\2\2\35\17"+
		"\3\2\2\2\35\21\3\2\2\2\35\25\3\2\2\2\36$\3\2\2\2\37 \6\3\2\3 !\t\2\2\2"+
		"!#\5\4\3\2\"\37\3\2\2\2#&\3\2\2\2$\"\3\2\2\2$%\3\2\2\2%\5\3\2\2\2&$\3"+
		"\2\2\2\'\64\7\t\2\2(\64\7\7\2\2)\64\7\b\2\2*\64\7\25\2\2+-\7\6\2\2,.\5"+
		"\6\4\2-,\3\2\2\2./\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\61\3\2\2\2\61\62\7"+
		"\3\2\2\62\64\3\2\2\2\63\'\3\2\2\2\63(\3\2\2\2\63)\3\2\2\2\63*\3\2\2\2"+
		"\63+\3\2\2\2\64H\3\2\2\2\65\67\7\n\2\2\66\65\3\2\2\2\66\67\3\2\2\2\67"+
		"D\3\2\2\28E\7\t\2\29E\7\7\2\2:E\7\b\2\2;E\7\25\2\2<>\7\6\2\2=?\5\6\4\2"+
		">=\3\2\2\2?@\3\2\2\2@>\3\2\2\2@A\3\2\2\2AB\3\2\2\2BC\7\3\2\2CE\3\2\2\2"+
		"D8\3\2\2\2D9\3\2\2\2D:\3\2\2\2D;\3\2\2\2D<\3\2\2\2EG\3\2\2\2F\66\3\2\2"+
		"\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2I\7\3\2\2\2JH\3\2\2\2\f\13\31\35$/\63"+
		"\66@DH";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}