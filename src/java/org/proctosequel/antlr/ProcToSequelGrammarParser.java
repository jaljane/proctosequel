// Generated from src/conf/ProcToSequelGrammar.g4 by ANTLR 4.1
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
public class ProcToSequelGrammarParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__10=1, T__9=2, T__8=3, T__7=4, T__6=5, T__5=6, T__4=7, T__3=8, T__2=9, 
		T__1=10, T__0=11, VarName=12, ParamName=13, Word=14, PUNCT=15, OP_ASSIGN=16, 
		OP_EQ=17, COMMA=18, GREATER=19, LOWER=20, PLUS_OP=21, MINUS_OP=22, MULTIPLY_OP=23, 
		DIVIDE_OP=24, SEMICOLON=25, STRING=26, MULTIPLE_LINE_COMMENT_BLOCK=27, 
		ONE_LINE_COMMENT_BLOCK=28, NEW_LINE=29, WS=30;
	public static final String[] tokenNames = {
		"<INVALID>", "'from'", "'to'", "')'", "'select'", "'exportResult'", "'by'", 
		"'primaryKey'", "'exportQuery'", "'('", "'where'", "'group'", "VarName", 
		"ParamName", "Word", "PUNCT", "':='", "'='", "','", "'>'", "'<'", "'+'", 
		"'-'", "'*'", "'/'", "';'", "STRING", "MULTIPLE_LINE_COMMENT_BLOCK", "ONE_LINE_COMMENT_BLOCK", 
		"NEW_LINE", "WS"
	};
	public static final int
		RULE_prog = 0, RULE_inst = 1, RULE_setvar = 2, RULE_exportToFunction = 3, 
		RULE_exportToTable = 4, RULE_selectStmt = 5, RULE_sqlPart = 6, RULE_expr = 7;
	public static final String[] ruleNames = {
		"prog", "inst", "setvar", "exportToFunction", "exportToTable", "selectStmt", 
		"sqlPart", "expr"
	};

	@Override
	public String getGrammarFileName() { return "ProcToSequelGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public ProcToSequelGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public InstContext inst(int i) {
			return getRuleContext(InstContext.class,i);
		}
		public TerminalNode EOF() { return getToken(ProcToSequelGrammarParser.EOF, 0); }
		public List<InstContext> inst() {
			return getRuleContexts(InstContext.class);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcToSequelGrammarListener ) ((ProcToSequelGrammarListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcToSequelGrammarListener ) ((ProcToSequelGrammarListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProcToSequelGrammarVisitor ) return ((ProcToSequelGrammarVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16); inst();
			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 5) | (1L << 8) | (1L << VarName))) != 0)) {
				{
				{
				setState(17); inst();
				}
				}
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(23); match(EOF);
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

	public static class InstContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(ProcToSequelGrammarParser.SEMICOLON, 0); }
		public ExportToTableContext exportToTable() {
			return getRuleContext(ExportToTableContext.class,0);
		}
		public SetvarContext setvar() {
			return getRuleContext(SetvarContext.class,0);
		}
		public ExportToFunctionContext exportToFunction() {
			return getRuleContext(ExportToFunctionContext.class,0);
		}
		public InstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcToSequelGrammarListener ) ((ProcToSequelGrammarListener)listener).enterInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcToSequelGrammarListener ) ((ProcToSequelGrammarListener)listener).exitInst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProcToSequelGrammarVisitor ) return ((ProcToSequelGrammarVisitor<? extends T>)visitor).visitInst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstContext inst() throws RecognitionException {
		InstContext _localctx = new InstContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_inst);
		try {
			setState(34);
			switch (_input.LA(1)) {
			case VarName:
				enterOuterAlt(_localctx, 1);
				{
				setState(25); setvar();
				setState(26); match(SEMICOLON);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 2);
				{
				setState(28); exportToFunction();
				setState(29); match(SEMICOLON);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 3);
				{
				setState(31); exportToTable();
				setState(32); match(SEMICOLON);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class SetvarContext extends ParserRuleContext {
		public SelectStmtContext selectStmt() {
			return getRuleContext(SelectStmtContext.class,0);
		}
		public TerminalNode VarName() { return getToken(ProcToSequelGrammarParser.VarName, 0); }
		public TerminalNode OP_ASSIGN() { return getToken(ProcToSequelGrammarParser.OP_ASSIGN, 0); }
		public SetvarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setvar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcToSequelGrammarListener ) ((ProcToSequelGrammarListener)listener).enterSetvar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcToSequelGrammarListener ) ((ProcToSequelGrammarListener)listener).exitSetvar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProcToSequelGrammarVisitor ) return ((ProcToSequelGrammarVisitor<? extends T>)visitor).visitSetvar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetvarContext setvar() throws RecognitionException {
		SetvarContext _localctx = new SetvarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_setvar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36); match(VarName);
			setState(37); match(OP_ASSIGN);
			setState(38); selectStmt();
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

	public static class ExportToFunctionContext extends ParserRuleContext {
		public TerminalNode VarName() { return getToken(ProcToSequelGrammarParser.VarName, 0); }
		public ExportToFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exportToFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcToSequelGrammarListener ) ((ProcToSequelGrammarListener)listener).enterExportToFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcToSequelGrammarListener ) ((ProcToSequelGrammarListener)listener).exitExportToFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProcToSequelGrammarVisitor ) return ((ProcToSequelGrammarVisitor<? extends T>)visitor).visitExportToFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExportToFunctionContext exportToFunction() throws RecognitionException {
		ExportToFunctionContext _localctx = new ExportToFunctionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_exportToFunction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40); match(8);
			setState(41); match(9);
			setState(42); match(VarName);
			setState(43); match(3);
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

	public static class ExportToTableContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode VarName() { return getToken(ProcToSequelGrammarParser.VarName, 0); }
		public TerminalNode Word() { return getToken(ProcToSequelGrammarParser.Word, 0); }
		public ExportToTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exportToTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcToSequelGrammarListener ) ((ProcToSequelGrammarListener)listener).enterExportToTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcToSequelGrammarListener ) ((ProcToSequelGrammarListener)listener).exitExportToTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProcToSequelGrammarVisitor ) return ((ProcToSequelGrammarVisitor<? extends T>)visitor).visitExportToTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExportToTableContext exportToTable() throws RecognitionException {
		ExportToTableContext _localctx = new ExportToTableContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_exportToTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45); match(5);
			setState(46); match(9);
			setState(47); match(VarName);
			setState(48); match(3);
			setState(49); match(2);
			setState(50); match(Word);
			setState(53);
			_la = _input.LA(1);
			if (_la==7) {
				{
				setState(51); match(7);
				setState(52); expr();
				}
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

	public static class SelectStmtContext extends ParserRuleContext {
		public SelectStmtContext selectStmt() {
			return getRuleContext(SelectStmtContext.class,0);
		}
		public List<SqlPartContext> sqlPart() {
			return getRuleContexts(SqlPartContext.class);
		}
		public SqlPartContext sqlPart(int i) {
			return getRuleContext(SqlPartContext.class,i);
		}
		public SelectStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcToSequelGrammarListener ) ((ProcToSequelGrammarListener)listener).enterSelectStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcToSequelGrammarListener ) ((ProcToSequelGrammarListener)listener).exitSelectStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProcToSequelGrammarVisitor ) return ((ProcToSequelGrammarVisitor<? extends T>)visitor).visitSelectStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectStmtContext selectStmt() throws RecognitionException {
		SelectStmtContext _localctx = new SelectStmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_selectStmt);
		try {
			setState(72);
			switch (_input.LA(1)) {
			case 4:
				enterOuterAlt(_localctx, 1);
				{
				setState(55); match(4);
				setState(56); sqlPart();
				setState(57); match(1);
				setState(58); sqlPart();
				setState(61);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(59); match(10);
					setState(60); sqlPart();
					}
					break;
				}
				setState(66);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(63); match(11);
					setState(64); match(6);
					setState(65); sqlPart();
					}
					break;
				}
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 2);
				{
				setState(68); match(9);
				setState(69); selectStmt();
				setState(70); match(3);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class SqlPartContext extends ParserRuleContext {
		public TerminalNode PUNCT(int i) {
			return getToken(ProcToSequelGrammarParser.PUNCT, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public List<SelectStmtContext> selectStmt() {
			return getRuleContexts(SelectStmtContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> PUNCT() { return getTokens(ProcToSequelGrammarParser.PUNCT); }
		public SelectStmtContext selectStmt(int i) {
			return getRuleContext(SelectStmtContext.class,i);
		}
		public SqlPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlPart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcToSequelGrammarListener ) ((ProcToSequelGrammarListener)listener).enterSqlPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcToSequelGrammarListener ) ((ProcToSequelGrammarListener)listener).exitSqlPart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProcToSequelGrammarVisitor ) return ((ProcToSequelGrammarVisitor<? extends T>)visitor).visitSqlPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SqlPartContext sqlPart() throws RecognitionException {
		SqlPartContext _localctx = new SqlPartContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_sqlPart);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(74); expr();
				}
				break;

			case 2:
				{
				setState(75); selectStmt();
				}
				break;
			}
			setState(87);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(79);
					_la = _input.LA(1);
					if (_la==PUNCT) {
						{
						setState(78); match(PUNCT);
						}
					}

					setState(83);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						setState(81); expr();
						}
						break;

					case 2:
						{
						setState(82); selectStmt();
						}
						break;
					}
					}
					} 
				}
				setState(89);
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

	public static class ExprContext extends ParserRuleContext {
		public List<TerminalNode> ParamName() { return getTokens(ProcToSequelGrammarParser.ParamName); }
		public TerminalNode STRING(int i) {
			return getToken(ProcToSequelGrammarParser.STRING, i);
		}
		public TerminalNode ParamName(int i) {
			return getToken(ProcToSequelGrammarParser.ParamName, i);
		}
		public TerminalNode PUNCT(int i) {
			return getToken(ProcToSequelGrammarParser.PUNCT, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> PUNCT() { return getTokens(ProcToSequelGrammarParser.PUNCT); }
		public TerminalNode Word(int i) {
			return getToken(ProcToSequelGrammarParser.Word, i);
		}
		public List<TerminalNode> VarName() { return getTokens(ProcToSequelGrammarParser.VarName); }
		public List<TerminalNode> Word() { return getTokens(ProcToSequelGrammarParser.Word); }
		public List<TerminalNode> STRING() { return getTokens(ProcToSequelGrammarParser.STRING); }
		public TerminalNode VarName(int i) {
			return getToken(ProcToSequelGrammarParser.VarName, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcToSequelGrammarListener ) ((ProcToSequelGrammarListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcToSequelGrammarListener ) ((ProcToSequelGrammarListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProcToSequelGrammarVisitor ) return ((ProcToSequelGrammarVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_expr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			switch (_input.LA(1)) {
			case Word:
				{
				setState(90); match(Word);
				}
				break;
			case VarName:
				{
				setState(91); match(VarName);
				}
				break;
			case ParamName:
				{
				setState(92); match(ParamName);
				}
				break;
			case STRING:
				{
				setState(93); match(STRING);
				}
				break;
			case 9:
				{
				setState(94); match(9);
				setState(96); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(95); expr();
					}
					}
					setState(98); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 9) | (1L << VarName) | (1L << ParamName) | (1L << Word) | (1L << STRING))) != 0) );
				setState(100); match(3);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(123);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(105);
					_la = _input.LA(1);
					if (_la==PUNCT) {
						{
						setState(104); match(PUNCT);
						}
					}

					setState(119);
					switch (_input.LA(1)) {
					case Word:
						{
						setState(107); match(Word);
						}
						break;
					case VarName:
						{
						setState(108); match(VarName);
						}
						break;
					case ParamName:
						{
						setState(109); match(ParamName);
						}
						break;
					case STRING:
						{
						setState(110); match(STRING);
						}
						break;
					case 9:
						{
						setState(111); match(9);
						setState(113); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(112); expr();
							}
							}
							setState(115); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 9) | (1L << VarName) | (1L << ParamName) | (1L << Word) | (1L << STRING))) != 0) );
						setState(117); match(3);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					} 
				}
				setState(125);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3 \u0081\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\7\2\25"+
		"\n\2\f\2\16\2\30\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3"+
		"%\n\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\5\68\n\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7@\n\7\3\7\3\7\3\7\5\7E\n\7\3"+
		"\7\3\7\3\7\3\7\5\7K\n\7\3\b\3\b\5\bO\n\b\3\b\5\bR\n\b\3\b\3\b\5\bV\n\b"+
		"\7\bX\n\b\f\b\16\b[\13\b\3\t\3\t\3\t\3\t\3\t\3\t\6\tc\n\t\r\t\16\td\3"+
		"\t\3\t\5\ti\n\t\3\t\5\tl\n\t\3\t\3\t\3\t\3\t\3\t\3\t\6\tt\n\t\r\t\16\t"+
		"u\3\t\3\t\5\tz\n\t\7\t|\n\t\f\t\16\t\177\13\t\3\t\2\n\2\4\6\b\n\f\16\20"+
		"\2\2\u008f\2\22\3\2\2\2\4$\3\2\2\2\6&\3\2\2\2\b*\3\2\2\2\n/\3\2\2\2\f"+
		"J\3\2\2\2\16N\3\2\2\2\20h\3\2\2\2\22\26\5\4\3\2\23\25\5\4\3\2\24\23\3"+
		"\2\2\2\25\30\3\2\2\2\26\24\3\2\2\2\26\27\3\2\2\2\27\31\3\2\2\2\30\26\3"+
		"\2\2\2\31\32\7\2\2\3\32\3\3\2\2\2\33\34\5\6\4\2\34\35\7\33\2\2\35%\3\2"+
		"\2\2\36\37\5\b\5\2\37 \7\33\2\2 %\3\2\2\2!\"\5\n\6\2\"#\7\33\2\2#%\3\2"+
		"\2\2$\33\3\2\2\2$\36\3\2\2\2$!\3\2\2\2%\5\3\2\2\2&\'\7\16\2\2\'(\7\22"+
		"\2\2()\5\f\7\2)\7\3\2\2\2*+\7\n\2\2+,\7\13\2\2,-\7\16\2\2-.\7\5\2\2.\t"+
		"\3\2\2\2/\60\7\7\2\2\60\61\7\13\2\2\61\62\7\16\2\2\62\63\7\5\2\2\63\64"+
		"\7\4\2\2\64\67\7\20\2\2\65\66\7\t\2\2\668\5\20\t\2\67\65\3\2\2\2\678\3"+
		"\2\2\28\13\3\2\2\29:\7\6\2\2:;\5\16\b\2;<\7\3\2\2<?\5\16\b\2=>\7\f\2\2"+
		">@\5\16\b\2?=\3\2\2\2?@\3\2\2\2@D\3\2\2\2AB\7\r\2\2BC\7\b\2\2CE\5\16\b"+
		"\2DA\3\2\2\2DE\3\2\2\2EK\3\2\2\2FG\7\13\2\2GH\5\f\7\2HI\7\5\2\2IK\3\2"+
		"\2\2J9\3\2\2\2JF\3\2\2\2K\r\3\2\2\2LO\5\20\t\2MO\5\f\7\2NL\3\2\2\2NM\3"+
		"\2\2\2OY\3\2\2\2PR\7\21\2\2QP\3\2\2\2QR\3\2\2\2RU\3\2\2\2SV\5\20\t\2T"+
		"V\5\f\7\2US\3\2\2\2UT\3\2\2\2VX\3\2\2\2WQ\3\2\2\2X[\3\2\2\2YW\3\2\2\2"+
		"YZ\3\2\2\2Z\17\3\2\2\2[Y\3\2\2\2\\i\7\20\2\2]i\7\16\2\2^i\7\17\2\2_i\7"+
		"\34\2\2`b\7\13\2\2ac\5\20\t\2ba\3\2\2\2cd\3\2\2\2db\3\2\2\2de\3\2\2\2"+
		"ef\3\2\2\2fg\7\5\2\2gi\3\2\2\2h\\\3\2\2\2h]\3\2\2\2h^\3\2\2\2h_\3\2\2"+
		"\2h`\3\2\2\2i}\3\2\2\2jl\7\21\2\2kj\3\2\2\2kl\3\2\2\2ly\3\2\2\2mz\7\20"+
		"\2\2nz\7\16\2\2oz\7\17\2\2pz\7\34\2\2qs\7\13\2\2rt\5\20\t\2sr\3\2\2\2"+
		"tu\3\2\2\2us\3\2\2\2uv\3\2\2\2vw\3\2\2\2wx\7\5\2\2xz\3\2\2\2ym\3\2\2\2"+
		"yn\3\2\2\2yo\3\2\2\2yp\3\2\2\2yq\3\2\2\2z|\3\2\2\2{k\3\2\2\2|\177\3\2"+
		"\2\2}{\3\2\2\2}~\3\2\2\2~\21\3\2\2\2\177}\3\2\2\2\22\26$\67?DJNQUYdhk"+
		"uy}";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}