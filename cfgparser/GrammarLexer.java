// Generated from Grammar.g4 by ANTLR 4.9.1
package cfgparser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, GRAMMAR=4, IDENT=5, WS=6, COMMENT=7;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "GRAMMAR", "IDENT", "WS", "COMMENT", "LETTER", 
			"DIGIT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "':'", "'|'", "'grammar'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "GRAMMAR", "IDENT", "WS", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public GrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\tR\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2"+
		"\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\7\6\'\n\6"+
		"\f\6\16\6*\13\6\3\7\6\7-\n\7\r\7\16\7.\3\7\3\7\3\b\3\b\3\b\3\b\7\b\67"+
		"\n\b\f\b\16\b:\13\b\3\b\5\b=\n\b\3\b\3\b\3\b\3\b\3\b\7\bD\n\b\f\b\16\b"+
		"G\13\b\3\b\3\b\5\bK\n\b\3\b\3\b\3\t\3\t\3\n\3\n\3E\2\13\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\2\23\2\3\2\5\5\2\13\f\16\17\"\"\4\2\f\f\17\17\4\2"+
		"C\\c|\2V\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\3\25\3\2\2\2\5\27\3\2\2\2\7\31\3\2\2\2\t\33"+
		"\3\2\2\2\13#\3\2\2\2\r,\3\2\2\2\17J\3\2\2\2\21N\3\2\2\2\23P\3\2\2\2\25"+
		"\26\7=\2\2\26\4\3\2\2\2\27\30\7<\2\2\30\6\3\2\2\2\31\32\7~\2\2\32\b\3"+
		"\2\2\2\33\34\7i\2\2\34\35\7t\2\2\35\36\7c\2\2\36\37\7o\2\2\37 \7o\2\2"+
		" !\7c\2\2!\"\7t\2\2\"\n\3\2\2\2#(\5\21\t\2$\'\5\21\t\2%\'\5\23\n\2&$\3"+
		"\2\2\2&%\3\2\2\2\'*\3\2\2\2(&\3\2\2\2()\3\2\2\2)\f\3\2\2\2*(\3\2\2\2+"+
		"-\t\2\2\2,+\3\2\2\2-.\3\2\2\2.,\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\61\b"+
		"\7\2\2\61\16\3\2\2\2\62\63\7\61\2\2\63\64\7\61\2\2\648\3\2\2\2\65\67\n"+
		"\3\2\2\66\65\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29<\3\2\2\2:8\3\2"+
		"\2\2;=\7\17\2\2<;\3\2\2\2<=\3\2\2\2=>\3\2\2\2>K\7\f\2\2?@\7\61\2\2@A\7"+
		",\2\2AE\3\2\2\2BD\13\2\2\2CB\3\2\2\2DG\3\2\2\2EF\3\2\2\2EC\3\2\2\2FH\3"+
		"\2\2\2GE\3\2\2\2HI\7,\2\2IK\7\61\2\2J\62\3\2\2\2J?\3\2\2\2KL\3\2\2\2L"+
		"M\b\b\2\2M\20\3\2\2\2NO\t\4\2\2O\22\3\2\2\2PQ\4\62;\2Q\24\3\2\2\2\n\2"+
		"&(.8<EJ\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}