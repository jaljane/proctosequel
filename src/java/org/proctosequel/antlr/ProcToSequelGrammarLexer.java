// Generated from src/conf/ProcToSequelGrammar.g4 by ANTLR 4.1
package org.proctosequel.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ProcToSequelGrammarLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__10=1, T__9=2, T__8=3, T__7=4, T__6=5, T__5=6, T__4=7, T__3=8, T__2=9, 
		T__1=10, T__0=11, VarName=12, ParamName=13, Word=14, PUNCT=15, OP_ASSIGN=16, 
		OP_EQ=17, COMMA=18, GREATER=19, LOWER=20, PLUS_OP=21, MINUS_OP=22, MULTIPLY_OP=23, 
		DIVIDE_OP=24, SEMICOLON=25, STRING=26, MULTIPLE_LINE_COMMENT_BLOCK=27, 
		ONE_LINE_COMMENT_BLOCK=28, NEW_LINE=29, WS=30;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'from'", "'to'", "')'", "'select'", "'exportResult'", "'by'", "'primaryKey'", 
		"'exportQuery'", "'('", "'where'", "'group'", "VarName", "ParamName", 
		"Word", "PUNCT", "':='", "'='", "','", "'>'", "'<'", "'+'", "'-'", "'*'", 
		"'/'", "';'", "STRING", "MULTIPLE_LINE_COMMENT_BLOCK", "ONE_LINE_COMMENT_BLOCK", 
		"NEW_LINE", "WS"
	};
	public static final String[] ruleNames = {
		"T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", 
		"T__1", "T__0", "VarName", "ParamName", "Word", "PUNCT", "OP_ASSIGN", 
		"OP_EQ", "COMMA", "GREATER", "LOWER", "PLUS_OP", "MINUS_OP", "MULTIPLY_OP", 
		"DIVIDE_OP", "GREATER_EQUAL", "LOWER_EQUAL", "DIFFERENT", "SEMICOLON", 
		"STRING", "MULTIPLE_LINE_COMMENT_BLOCK", "ONE_LINE_COMMENT_BLOCK", "NEW_LINE", 
		"WS"
	};


	public ProcToSequelGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ProcToSequelGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 29: MULTIPLE_LINE_COMMENT_BLOCK_action((RuleContext)_localctx, actionIndex); break;

		case 30: ONE_LINE_COMMENT_BLOCK_action((RuleContext)_localctx, actionIndex); break;

		case 32: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void MULTIPLE_LINE_COMMENT_BLOCK_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: skip();  break;
		}
	}
	private void ONE_LINE_COMMENT_BLOCK_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2 \u00ff\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\6\17\u0093\n\17\r\17"+
		"\16\17\u0094\3\17\5\17\u0098\n\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\5\20\u00a5\n\20\3\21\3\21\3\21\3\22\3\22\3\23\3\23"+
		"\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\36\7\36"+
		"\u00c9\n\36\f\36\16\36\u00cc\13\36\3\36\3\36\3\36\3\36\3\36\7\36\u00d3"+
		"\n\36\f\36\16\36\u00d6\13\36\3\36\5\36\u00d9\n\36\3\37\3\37\3\37\7\37"+
		"\u00de\n\37\f\37\16\37\u00e1\13\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \7"+
		" \u00eb\n \f \16 \u00ee\13 \3 \3 \3 \3 \3!\6!\u00f5\n!\r!\16!\u00f6\3"+
		"\"\6\"\u00fa\n\"\r\"\16\"\u00fb\3\"\3\"\6\u00ca\u00d4\u00df\u00ec#\3\3"+
		"\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1"+
		"\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1"+
		"-\30\1/\31\1\61\32\1\63\2\1\65\2\1\67\2\19\33\1;\34\1=\35\2?\36\3A\37"+
		"\1C \4\3\2\5\b\2\60\60\62;C\\^^aac|\3\2\f\f\5\2\13\f\17\17\"\"\u0110\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2"+
		"\2\61\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2"+
		"C\3\2\2\2\3E\3\2\2\2\5J\3\2\2\2\7M\3\2\2\2\tO\3\2\2\2\13V\3\2\2\2\rc\3"+
		"\2\2\2\17f\3\2\2\2\21q\3\2\2\2\23}\3\2\2\2\25\177\3\2\2\2\27\u0085\3\2"+
		"\2\2\31\u008b\3\2\2\2\33\u008e\3\2\2\2\35\u0097\3\2\2\2\37\u00a4\3\2\2"+
		"\2!\u00a6\3\2\2\2#\u00a9\3\2\2\2%\u00ab\3\2\2\2\'\u00ad\3\2\2\2)\u00af"+
		"\3\2\2\2+\u00b1\3\2\2\2-\u00b3\3\2\2\2/\u00b5\3\2\2\2\61\u00b7\3\2\2\2"+
		"\63\u00b9\3\2\2\2\65\u00bc\3\2\2\2\67\u00bf\3\2\2\29\u00c2\3\2\2\2;\u00d8"+
		"\3\2\2\2=\u00da\3\2\2\2?\u00e7\3\2\2\2A\u00f4\3\2\2\2C\u00f9\3\2\2\2E"+
		"F\7h\2\2FG\7t\2\2GH\7q\2\2HI\7o\2\2I\4\3\2\2\2JK\7v\2\2KL\7q\2\2L\6\3"+
		"\2\2\2MN\7+\2\2N\b\3\2\2\2OP\7u\2\2PQ\7g\2\2QR\7n\2\2RS\7g\2\2ST\7e\2"+
		"\2TU\7v\2\2U\n\3\2\2\2VW\7g\2\2WX\7z\2\2XY\7r\2\2YZ\7q\2\2Z[\7t\2\2[\\"+
		"\7v\2\2\\]\7T\2\2]^\7g\2\2^_\7u\2\2_`\7w\2\2`a\7n\2\2ab\7v\2\2b\f\3\2"+
		"\2\2cd\7d\2\2de\7{\2\2e\16\3\2\2\2fg\7r\2\2gh\7t\2\2hi\7k\2\2ij\7o\2\2"+
		"jk\7c\2\2kl\7t\2\2lm\7{\2\2mn\7M\2\2no\7g\2\2op\7{\2\2p\20\3\2\2\2qr\7"+
		"g\2\2rs\7z\2\2st\7r\2\2tu\7q\2\2uv\7t\2\2vw\7v\2\2wx\7S\2\2xy\7w\2\2y"+
		"z\7g\2\2z{\7t\2\2{|\7{\2\2|\22\3\2\2\2}~\7*\2\2~\24\3\2\2\2\177\u0080"+
		"\7y\2\2\u0080\u0081\7j\2\2\u0081\u0082\7g\2\2\u0082\u0083\7t\2\2\u0083"+
		"\u0084\7g\2\2\u0084\26\3\2\2\2\u0085\u0086\7i\2\2\u0086\u0087\7t\2\2\u0087"+
		"\u0088\7q\2\2\u0088\u0089\7w\2\2\u0089\u008a\7r\2\2\u008a\30\3\2\2\2\u008b"+
		"\u008c\7&\2\2\u008c\u008d\5\35\17\2\u008d\32\3\2\2\2\u008e\u008f\7<\2"+
		"\2\u008f\u0090\5\35\17\2\u0090\34\3\2\2\2\u0091\u0093\t\2\2\2\u0092\u0091"+
		"\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095"+
		"\u0098\3\2\2\2\u0096\u0098\5/\30\2\u0097\u0092\3\2\2\2\u0097\u0096\3\2"+
		"\2\2\u0098\36\3\2\2\2\u0099\u00a5\5#\22\2\u009a\u00a5\5%\23\2\u009b\u00a5"+
		"\5\'\24\2\u009c\u00a5\5)\25\2\u009d\u00a5\5\63\32\2\u009e\u00a5\5\65\33"+
		"\2\u009f\u00a5\5\67\34\2\u00a0\u00a5\5+\26\2\u00a1\u00a5\5-\27\2\u00a2"+
		"\u00a5\5/\30\2\u00a3\u00a5\5\61\31\2\u00a4\u0099\3\2\2\2\u00a4\u009a\3"+
		"\2\2\2\u00a4\u009b\3\2\2\2\u00a4\u009c\3\2\2\2\u00a4\u009d\3\2\2\2\u00a4"+
		"\u009e\3\2\2\2\u00a4\u009f\3\2\2\2\u00a4\u00a0\3\2\2\2\u00a4\u00a1\3\2"+
		"\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a3\3\2\2\2\u00a5 \3\2\2\2\u00a6\u00a7"+
		"\7<\2\2\u00a7\u00a8\7?\2\2\u00a8\"\3\2\2\2\u00a9\u00aa\7?\2\2\u00aa$\3"+
		"\2\2\2\u00ab\u00ac\7.\2\2\u00ac&\3\2\2\2\u00ad\u00ae\7@\2\2\u00ae(\3\2"+
		"\2\2\u00af\u00b0\7>\2\2\u00b0*\3\2\2\2\u00b1\u00b2\7-\2\2\u00b2,\3\2\2"+
		"\2\u00b3\u00b4\7/\2\2\u00b4.\3\2\2\2\u00b5\u00b6\7,\2\2\u00b6\60\3\2\2"+
		"\2\u00b7\u00b8\7\61\2\2\u00b8\62\3\2\2\2\u00b9\u00ba\5\'\24\2\u00ba\u00bb"+
		"\5#\22\2\u00bb\64\3\2\2\2\u00bc\u00bd\5)\25\2\u00bd\u00be\5#\22\2\u00be"+
		"\66\3\2\2\2\u00bf\u00c0\5)\25\2\u00c0\u00c1\5\'\24\2\u00c18\3\2\2\2\u00c2"+
		"\u00c3\7=\2\2\u00c3:\3\2\2\2\u00c4\u00ca\7$\2\2\u00c5\u00c6\7^\2\2\u00c6"+
		"\u00c9\7$\2\2\u00c7\u00c9\13\2\2\2\u00c8\u00c5\3\2\2\2\u00c8\u00c7\3\2"+
		"\2\2\u00c9\u00cc\3\2\2\2\u00ca\u00cb\3\2\2\2\u00ca\u00c8\3\2\2\2\u00cb"+
		"\u00cd\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cd\u00d9\7$\2\2\u00ce\u00d4\7)\2"+
		"\2\u00cf\u00d0\7^\2\2\u00d0\u00d3\7)\2\2\u00d1\u00d3\13\2\2\2\u00d2\u00cf"+
		"\3\2\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d4"+
		"\u00d2\3\2\2\2\u00d5\u00d7\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7\u00d9\7)"+
		"\2\2\u00d8\u00c4\3\2\2\2\u00d8\u00ce\3\2\2\2\u00d9<\3\2\2\2\u00da\u00db"+
		"\5\61\31\2\u00db\u00df\5/\30\2\u00dc\u00de\13\2\2\2\u00dd\u00dc\3\2\2"+
		"\2\u00de\u00e1\3\2\2\2\u00df\u00e0\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0\u00e2"+
		"\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2\u00e3\5/\30\2\u00e3\u00e4\5\61\31\2"+
		"\u00e4\u00e5\3\2\2\2\u00e5\u00e6\b\37\2\2\u00e6>\3\2\2\2\u00e7\u00e8\5"+
		"-\27\2\u00e8\u00ec\5-\27\2\u00e9\u00eb\13\2\2\2\u00ea\u00e9\3\2\2\2\u00eb"+
		"\u00ee\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00ef\3\2"+
		"\2\2\u00ee\u00ec\3\2\2\2\u00ef\u00f0\5A!\2\u00f0\u00f1\3\2\2\2\u00f1\u00f2"+
		"\b \3\2\u00f2@\3\2\2\2\u00f3\u00f5\t\3\2\2\u00f4\u00f3\3\2\2\2\u00f5\u00f6"+
		"\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7B\3\2\2\2\u00f8"+
		"\u00fa\t\4\2\2\u00f9\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00f9\3\2"+
		"\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00fe\b\"\4\2\u00fe"+
		"D\3\2\2\2\17\2\u0094\u0097\u00a4\u00c8\u00ca\u00d2\u00d4\u00d8\u00df\u00ec"+
		"\u00f6\u00fb";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}