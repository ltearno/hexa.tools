package fr.lteconsulting.hexa.persistence.client;

// $ANTLR 3.5.1 hql.g 2013-11-29 11:20:54

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.FailedPredicateException;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;

@SuppressWarnings("all")
public class hqlLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__133=133;
	public static final int T__134=134;
	public static final int AGGREGATE=4;
	public static final int ALIAS=5;
	public static final int ALL=6;
	public static final int AND=7;
	public static final int ANY=8;
	public static final int AS=9;
	public static final int ASCENDING=10;
	public static final int AVG=11;
	public static final int BAND=12;
	public static final int BETWEEN=13;
	public static final int BNOT=14;
	public static final int BOR=15;
	public static final int BOTH=16;
	public static final int BXOR=17;
	public static final int CASE=18;
	public static final int CASE2=19;
	public static final int CLASS=20;
	public static final int CLOSE=21;
	public static final int CLOSE_BRACKET=22;
	public static final int COLON=23;
	public static final int COMMA=24;
	public static final int CONCAT=25;
	public static final int CONSTANT=26;
	public static final int CONSTRUCTOR=27;
	public static final int COUNT=28;
	public static final int DELETE=29;
	public static final int DESCENDING=30;
	public static final int DISTINCT=31;
	public static final int DIV=32;
	public static final int DOT=33;
	public static final int ELEMENTS=34;
	public static final int ELSE=35;
	public static final int EMPTY=36;
	public static final int END=37;
	public static final int EQ=38;
	public static final int ESCAPE=39;
	public static final int ESCqs=40;
	public static final int EXISTS=41;
	public static final int EXPONENT=42;
	public static final int EXPR_LIST=43;
	public static final int FALSE=44;
	public static final int FETCH=45;
	public static final int FILTER_ENTITY=46;
	public static final int FLOAT_SUFFIX=47;
	public static final int FROM=48;
	public static final int FULL=49;
	public static final int GE=50;
	public static final int GROUP=51;
	public static final int GT=52;
	public static final int HAVING=53;
	public static final int HEX_DIGIT=54;
	public static final int IDENT=55;
	public static final int ID_LETTER=56;
	public static final int ID_START_LETTER=57;
	public static final int IN=58;
	public static final int INDEX_OP=59;
	public static final int INDICES=60;
	public static final int INNER=61;
	public static final int INSERT=62;
	public static final int INTO=63;
	public static final int IN_LIST=64;
	public static final int IS=65;
	public static final int IS_NOT_NULL=66;
	public static final int IS_NULL=67;
	public static final int JAVA_CONSTANT=68;
	public static final int JOIN=69;
	public static final int LE=70;
	public static final int LEADING=71;
	public static final int LEFT=72;
	public static final int LIKE=73;
	public static final int LITERAL_by=74;
	public static final int LT=75;
	public static final int MAX=76;
	public static final int MEMBER=77;
	public static final int METHOD_CALL=78;
	public static final int MIN=79;
	public static final int MINUS=80;
	public static final int NE=81;
	public static final int NEW=82;
	public static final int NOT=83;
	public static final int NOT_BETWEEN=84;
	public static final int NOT_IN=85;
	public static final int NOT_LIKE=86;
	public static final int NULL=87;
	public static final int NUM_DECIMAL=88;
	public static final int NUM_DOUBLE=89;
	public static final int NUM_FLOAT=90;
	public static final int NUM_INT=91;
	public static final int NUM_LONG=92;
	public static final int OBJECT=93;
	public static final int OF=94;
	public static final int ON=95;
	public static final int OPEN=96;
	public static final int OPEN_BRACKET=97;
	public static final int OR=98;
	public static final int ORDER=99;
	public static final int ORDER_ELEMENT=100;
	public static final int OUTER=101;
	public static final int PARAM=102;
	public static final int PLUS=103;
	public static final int PROPERTIES=104;
	public static final int QUERY=105;
	public static final int QUOTED_String=106;
	public static final int RANGE=107;
	public static final int RIGHT=108;
	public static final int ROW_STAR=109;
	public static final int SELECT=110;
	public static final int SELECT_FROM=111;
	public static final int SET=112;
	public static final int SKIP=113;
	public static final int SOME=114;
	public static final int SQL_NE=115;
	public static final int STAR=116;
	public static final int SUM=117;
	public static final int TAKE=118;
	public static final int THEN=119;
	public static final int TRAILING=120;
	public static final int TRUE=121;
	public static final int UNARY_MINUS=122;
	public static final int UNARY_PLUS=123;
	public static final int UNION=124;
	public static final int UPDATE=125;
	public static final int VECTOR_EXPR=126;
	public static final int VERSIONED=127;
	public static final int WEIRD_IDENT=128;
	public static final int WHEN=129;
	public static final int WHERE=130;
	public static final int WITH=131;
	public static final int WS=132;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public hqlLexer() {}
	public hqlLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public hqlLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "hql.g"; }

	// $ANTLR start "ALL"
	public final void mALL() throws RecognitionException {
		try {
			int _type = ALL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:7:5: ( 'all' )
			// hql.g:7:7: 'all'
			{
			match("all"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ALL"

	// $ANTLR start "AND"
	public final void mAND() throws RecognitionException {
		try {
			int _type = AND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:8:5: ( 'and' )
			// hql.g:8:7: 'and'
			{
			match("and"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AND"

	// $ANTLR start "ANY"
	public final void mANY() throws RecognitionException {
		try {
			int _type = ANY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:9:5: ( 'any' )
			// hql.g:9:7: 'any'
			{
			match("any"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ANY"

	// $ANTLR start "AS"
	public final void mAS() throws RecognitionException {
		try {
			int _type = AS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:10:4: ( 'as' )
			// hql.g:10:6: 'as'
			{
			match("as"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AS"

	// $ANTLR start "ASCENDING"
	public final void mASCENDING() throws RecognitionException {
		try {
			int _type = ASCENDING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:11:11: ( 'asc' )
			// hql.g:11:13: 'asc'
			{
			match("asc"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ASCENDING"

	// $ANTLR start "AVG"
	public final void mAVG() throws RecognitionException {
		try {
			int _type = AVG;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:12:5: ( 'avg' )
			// hql.g:12:7: 'avg'
			{
			match("avg"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AVG"

	// $ANTLR start "BETWEEN"
	public final void mBETWEEN() throws RecognitionException {
		try {
			int _type = BETWEEN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:13:9: ( 'between' )
			// hql.g:13:11: 'between'
			{
			match("between"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BETWEEN"

	// $ANTLR start "BOTH"
	public final void mBOTH() throws RecognitionException {
		try {
			int _type = BOTH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:14:6: ( 'both' )
			// hql.g:14:8: 'both'
			{
			match("both"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BOTH"

	// $ANTLR start "CASE"
	public final void mCASE() throws RecognitionException {
		try {
			int _type = CASE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:15:6: ( 'case' )
			// hql.g:15:8: 'case'
			{
			match("case"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CASE"

	// $ANTLR start "CLASS"
	public final void mCLASS() throws RecognitionException {
		try {
			int _type = CLASS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:16:7: ( 'class' )
			// hql.g:16:9: 'class'
			{
			match("class"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CLASS"

	// $ANTLR start "COUNT"
	public final void mCOUNT() throws RecognitionException {
		try {
			int _type = COUNT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:17:7: ( 'count' )
			// hql.g:17:9: 'count'
			{
			match("count"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COUNT"

	// $ANTLR start "DELETE"
	public final void mDELETE() throws RecognitionException {
		try {
			int _type = DELETE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:18:8: ( 'delete' )
			// hql.g:18:10: 'delete'
			{
			match("delete"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DELETE"

	// $ANTLR start "DESCENDING"
	public final void mDESCENDING() throws RecognitionException {
		try {
			int _type = DESCENDING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:19:12: ( 'desc' )
			// hql.g:19:14: 'desc'
			{
			match("desc"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DESCENDING"

	// $ANTLR start "DISTINCT"
	public final void mDISTINCT() throws RecognitionException {
		try {
			int _type = DISTINCT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:20:10: ( 'distinct' )
			// hql.g:20:12: 'distinct'
			{
			match("distinct"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DISTINCT"

	// $ANTLR start "ELEMENTS"
	public final void mELEMENTS() throws RecognitionException {
		try {
			int _type = ELEMENTS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:21:10: ( 'elements' )
			// hql.g:21:12: 'elements'
			{
			match("elements"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ELEMENTS"

	// $ANTLR start "ELSE"
	public final void mELSE() throws RecognitionException {
		try {
			int _type = ELSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:22:6: ( 'else' )
			// hql.g:22:8: 'else'
			{
			match("else"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ELSE"

	// $ANTLR start "EMPTY"
	public final void mEMPTY() throws RecognitionException {
		try {
			int _type = EMPTY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:23:7: ( 'empty' )
			// hql.g:23:9: 'empty'
			{
			match("empty"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EMPTY"

	// $ANTLR start "END"
	public final void mEND() throws RecognitionException {
		try {
			int _type = END;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:24:5: ( 'end' )
			// hql.g:24:7: 'end'
			{
			match("end"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "END"

	// $ANTLR start "ESCAPE"
	public final void mESCAPE() throws RecognitionException {
		try {
			int _type = ESCAPE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:25:8: ( 'escape' )
			// hql.g:25:10: 'escape'
			{
			match("escape"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ESCAPE"

	// $ANTLR start "EXISTS"
	public final void mEXISTS() throws RecognitionException {
		try {
			int _type = EXISTS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:26:8: ( 'exists' )
			// hql.g:26:10: 'exists'
			{
			match("exists"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXISTS"

	// $ANTLR start "FALSE"
	public final void mFALSE() throws RecognitionException {
		try {
			int _type = FALSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:27:7: ( 'false' )
			// hql.g:27:9: 'false'
			{
			match("false"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FALSE"

	// $ANTLR start "FETCH"
	public final void mFETCH() throws RecognitionException {
		try {
			int _type = FETCH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:28:7: ( 'fetch' )
			// hql.g:28:9: 'fetch'
			{
			match("fetch"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FETCH"

	// $ANTLR start "FROM"
	public final void mFROM() throws RecognitionException {
		try {
			int _type = FROM;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:29:6: ( 'from' )
			// hql.g:29:8: 'from'
			{
			match("from"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FROM"

	// $ANTLR start "FULL"
	public final void mFULL() throws RecognitionException {
		try {
			int _type = FULL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:30:6: ( 'full' )
			// hql.g:30:8: 'full'
			{
			match("full"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FULL"

	// $ANTLR start "GROUP"
	public final void mGROUP() throws RecognitionException {
		try {
			int _type = GROUP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:31:7: ( 'group' )
			// hql.g:31:9: 'group'
			{
			match("group"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GROUP"

	// $ANTLR start "HAVING"
	public final void mHAVING() throws RecognitionException {
		try {
			int _type = HAVING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:32:8: ( 'having' )
			// hql.g:32:10: 'having'
			{
			match("having"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HAVING"

	// $ANTLR start "IN"
	public final void mIN() throws RecognitionException {
		try {
			int _type = IN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:33:4: ( 'in' )
			// hql.g:33:6: 'in'
			{
			match("in"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IN"

	// $ANTLR start "INDICES"
	public final void mINDICES() throws RecognitionException {
		try {
			int _type = INDICES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:34:9: ( 'indices' )
			// hql.g:34:11: 'indices'
			{
			match("indices"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INDICES"

	// $ANTLR start "INNER"
	public final void mINNER() throws RecognitionException {
		try {
			int _type = INNER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:35:7: ( 'inner' )
			// hql.g:35:9: 'inner'
			{
			match("inner"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INNER"

	// $ANTLR start "INSERT"
	public final void mINSERT() throws RecognitionException {
		try {
			int _type = INSERT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:36:8: ( 'insert' )
			// hql.g:36:10: 'insert'
			{
			match("insert"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INSERT"

	// $ANTLR start "INTO"
	public final void mINTO() throws RecognitionException {
		try {
			int _type = INTO;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:37:6: ( 'into' )
			// hql.g:37:8: 'into'
			{
			match("into"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTO"

	// $ANTLR start "IS"
	public final void mIS() throws RecognitionException {
		try {
			int _type = IS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:38:4: ( 'is' )
			// hql.g:38:6: 'is'
			{
			match("is"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IS"

	// $ANTLR start "JOIN"
	public final void mJOIN() throws RecognitionException {
		try {
			int _type = JOIN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:39:6: ( 'join' )
			// hql.g:39:8: 'join'
			{
			match("join"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "JOIN"

	// $ANTLR start "LEADING"
	public final void mLEADING() throws RecognitionException {
		try {
			int _type = LEADING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:40:9: ( 'leading' )
			// hql.g:40:11: 'leading'
			{
			match("leading"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LEADING"

	// $ANTLR start "LEFT"
	public final void mLEFT() throws RecognitionException {
		try {
			int _type = LEFT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:41:6: ( 'left' )
			// hql.g:41:8: 'left'
			{
			match("left"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LEFT"

	// $ANTLR start "LIKE"
	public final void mLIKE() throws RecognitionException {
		try {
			int _type = LIKE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:42:6: ( 'like' )
			// hql.g:42:8: 'like'
			{
			match("like"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LIKE"

	// $ANTLR start "LITERAL_by"
	public final void mLITERAL_by() throws RecognitionException {
		try {
			int _type = LITERAL_by;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:43:12: ( 'by' )
			// hql.g:43:14: 'by'
			{
			match("by"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LITERAL_by"

	// $ANTLR start "MAX"
	public final void mMAX() throws RecognitionException {
		try {
			int _type = MAX;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:44:5: ( 'max' )
			// hql.g:44:7: 'max'
			{
			match("max"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MAX"

	// $ANTLR start "MEMBER"
	public final void mMEMBER() throws RecognitionException {
		try {
			int _type = MEMBER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:45:8: ( 'member' )
			// hql.g:45:10: 'member'
			{
			match("member"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MEMBER"

	// $ANTLR start "MIN"
	public final void mMIN() throws RecognitionException {
		try {
			int _type = MIN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:46:5: ( 'min' )
			// hql.g:46:7: 'min'
			{
			match("min"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MIN"

	// $ANTLR start "NEW"
	public final void mNEW() throws RecognitionException {
		try {
			int _type = NEW;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:47:5: ( 'new' )
			// hql.g:47:7: 'new'
			{
			match("new"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NEW"

	// $ANTLR start "NOT"
	public final void mNOT() throws RecognitionException {
		try {
			int _type = NOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:48:5: ( 'not' )
			// hql.g:48:7: 'not'
			{
			match("not"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NOT"

	// $ANTLR start "NULL"
	public final void mNULL() throws RecognitionException {
		try {
			int _type = NULL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:49:6: ( 'null' )
			// hql.g:49:8: 'null'
			{
			match("null"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NULL"

	// $ANTLR start "OBJECT"
	public final void mOBJECT() throws RecognitionException {
		try {
			int _type = OBJECT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:50:8: ( 'object' )
			// hql.g:50:10: 'object'
			{
			match("object"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OBJECT"

	// $ANTLR start "OF"
	public final void mOF() throws RecognitionException {
		try {
			int _type = OF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:51:4: ( 'of' )
			// hql.g:51:6: 'of'
			{
			match("of"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OF"

	// $ANTLR start "ON"
	public final void mON() throws RecognitionException {
		try {
			int _type = ON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:52:4: ( 'on' )
			// hql.g:52:6: 'on'
			{
			match("on"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ON"

	// $ANTLR start "OR"
	public final void mOR() throws RecognitionException {
		try {
			int _type = OR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:53:4: ( 'or' )
			// hql.g:53:6: 'or'
			{
			match("or"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OR"

	// $ANTLR start "ORDER"
	public final void mORDER() throws RecognitionException {
		try {
			int _type = ORDER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:54:7: ( 'order' )
			// hql.g:54:9: 'order'
			{
			match("order"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ORDER"

	// $ANTLR start "OUTER"
	public final void mOUTER() throws RecognitionException {
		try {
			int _type = OUTER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:55:7: ( 'outer' )
			// hql.g:55:9: 'outer'
			{
			match("outer"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OUTER"

	// $ANTLR start "PROPERTIES"
	public final void mPROPERTIES() throws RecognitionException {
		try {
			int _type = PROPERTIES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:56:12: ( 'properties' )
			// hql.g:56:14: 'properties'
			{
			match("properties"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PROPERTIES"

	// $ANTLR start "RIGHT"
	public final void mRIGHT() throws RecognitionException {
		try {
			int _type = RIGHT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:57:7: ( 'right' )
			// hql.g:57:9: 'right'
			{
			match("right"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RIGHT"

	// $ANTLR start "SELECT"
	public final void mSELECT() throws RecognitionException {
		try {
			int _type = SELECT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:58:8: ( 'select' )
			// hql.g:58:10: 'select'
			{
			match("select"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SELECT"

	// $ANTLR start "SET"
	public final void mSET() throws RecognitionException {
		try {
			int _type = SET;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:59:5: ( 'set' )
			// hql.g:59:7: 'set'
			{
			match("set"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SET"

	// $ANTLR start "SKIP"
	public final void mSKIP() throws RecognitionException {
		try {
			int _type = SKIP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:60:6: ( 'skip' )
			// hql.g:60:8: 'skip'
			{
			match("skip"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SKIP"

	// $ANTLR start "SOME"
	public final void mSOME() throws RecognitionException {
		try {
			int _type = SOME;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:61:6: ( 'some' )
			// hql.g:61:8: 'some'
			{
			match("some"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SOME"

	// $ANTLR start "SUM"
	public final void mSUM() throws RecognitionException {
		try {
			int _type = SUM;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:62:5: ( 'sum' )
			// hql.g:62:7: 'sum'
			{
			match("sum"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SUM"

	// $ANTLR start "TAKE"
	public final void mTAKE() throws RecognitionException {
		try {
			int _type = TAKE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:63:6: ( 'take' )
			// hql.g:63:8: 'take'
			{
			match("take"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TAKE"

	// $ANTLR start "THEN"
	public final void mTHEN() throws RecognitionException {
		try {
			int _type = THEN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:64:6: ( 'then' )
			// hql.g:64:8: 'then'
			{
			match("then"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "THEN"

	// $ANTLR start "TRAILING"
	public final void mTRAILING() throws RecognitionException {
		try {
			int _type = TRAILING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:65:10: ( 'trailing' )
			// hql.g:65:12: 'trailing'
			{
			match("trailing"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TRAILING"

	// $ANTLR start "TRUE"
	public final void mTRUE() throws RecognitionException {
		try {
			int _type = TRUE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:66:6: ( 'true' )
			// hql.g:66:8: 'true'
			{
			match("true"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TRUE"

	// $ANTLR start "UNION"
	public final void mUNION() throws RecognitionException {
		try {
			int _type = UNION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:67:7: ( 'union' )
			// hql.g:67:9: 'union'
			{
			match("union"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UNION"

	// $ANTLR start "UPDATE"
	public final void mUPDATE() throws RecognitionException {
		try {
			int _type = UPDATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:68:8: ( 'update' )
			// hql.g:68:10: 'update'
			{
			match("update"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UPDATE"

	// $ANTLR start "VERSIONED"
	public final void mVERSIONED() throws RecognitionException {
		try {
			int _type = VERSIONED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:69:11: ( 'versioned' )
			// hql.g:69:13: 'versioned'
			{
			match("versioned"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VERSIONED"

	// $ANTLR start "WHEN"
	public final void mWHEN() throws RecognitionException {
		try {
			int _type = WHEN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:70:6: ( 'when' )
			// hql.g:70:8: 'when'
			{
			match("when"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WHEN"

	// $ANTLR start "WHERE"
	public final void mWHERE() throws RecognitionException {
		try {
			int _type = WHERE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:71:7: ( 'where' )
			// hql.g:71:9: 'where'
			{
			match("where"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WHERE"

	// $ANTLR start "WITH"
	public final void mWITH() throws RecognitionException {
		try {
			int _type = WITH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:72:6: ( 'with' )
			// hql.g:72:8: 'with'
			{
			match("with"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WITH"

	// $ANTLR start "T__133"
	public final void mT__133() throws RecognitionException {
		try {
			int _type = T__133;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:73:8: ( 'ascending' )
			// hql.g:73:10: 'ascending'
			{
			match("ascending"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__133"

	// $ANTLR start "T__134"
	public final void mT__134() throws RecognitionException {
		try {
			int _type = T__134;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:74:8: ( 'descending' )
			// hql.g:74:10: 'descending'
			{
			match("descending"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__134"

	// $ANTLR start "EQ"
	public final void mEQ() throws RecognitionException {
		try {
			int _type = EQ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:734:3: ( '=' )
			// hql.g:734:5: '='
			{
			match('='); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQ"

	// $ANTLR start "LT"
	public final void mLT() throws RecognitionException {
		try {
			int _type = LT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:735:3: ( '<' )
			// hql.g:735:5: '<'
			{
			match('<'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LT"

	// $ANTLR start "GT"
	public final void mGT() throws RecognitionException {
		try {
			int _type = GT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:736:3: ( '>' )
			// hql.g:736:5: '>'
			{
			match('>'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GT"

	// $ANTLR start "SQL_NE"
	public final void mSQL_NE() throws RecognitionException {
		try {
			int _type = SQL_NE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:737:7: ( '<>' )
			// hql.g:737:9: '<>'
			{
			match("<>"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SQL_NE"

	// $ANTLR start "NE"
	public final void mNE() throws RecognitionException {
		try {
			int _type = NE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:738:3: ( '!=' | '^=' )
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( (LA1_0=='!') ) {
				alt1=1;
			}
			else if ( (LA1_0=='^') ) {
				alt1=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return;}
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}

			switch (alt1) {
				case 1 :
					// hql.g:738:5: '!='
					{
					match("!="); if (state.failed) return;

					}
					break;
				case 2 :
					// hql.g:738:12: '^='
					{
					match("^="); if (state.failed) return;

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NE"

	// $ANTLR start "LE"
	public final void mLE() throws RecognitionException {
		try {
			int _type = LE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:739:3: ( '<=' )
			// hql.g:739:5: '<='
			{
			match("<="); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LE"

	// $ANTLR start "GE"
	public final void mGE() throws RecognitionException {
		try {
			int _type = GE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:740:3: ( '>=' )
			// hql.g:740:5: '>='
			{
			match(">="); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GE"

	// $ANTLR start "BOR"
	public final void mBOR() throws RecognitionException {
		try {
			int _type = BOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:742:5: ( '|' )
			// hql.g:742:8: '|'
			{
			match('|'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BOR"

	// $ANTLR start "BXOR"
	public final void mBXOR() throws RecognitionException {
		try {
			int _type = BXOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:743:6: ( '^' )
			// hql.g:743:8: '^'
			{
			match('^'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BXOR"

	// $ANTLR start "BAND"
	public final void mBAND() throws RecognitionException {
		try {
			int _type = BAND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:744:6: ( '&' )
			// hql.g:744:8: '&'
			{
			match('&'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BAND"

	// $ANTLR start "BNOT"
	public final void mBNOT() throws RecognitionException {
		try {
			int _type = BNOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:745:6: ( '!' )
			// hql.g:745:8: '!'
			{
			match('!'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BNOT"

	// $ANTLR start "COMMA"
	public final void mCOMMA() throws RecognitionException {
		try {
			int _type = COMMA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:747:6: ( ',' )
			// hql.g:747:8: ','
			{
			match(','); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMA"

	// $ANTLR start "OPEN"
	public final void mOPEN() throws RecognitionException {
		try {
			int _type = OPEN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:749:5: ( '(' )
			// hql.g:749:7: '('
			{
			match('('); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OPEN"

	// $ANTLR start "CLOSE"
	public final void mCLOSE() throws RecognitionException {
		try {
			int _type = CLOSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:750:6: ( ')' )
			// hql.g:750:8: ')'
			{
			match(')'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CLOSE"

	// $ANTLR start "OPEN_BRACKET"
	public final void mOPEN_BRACKET() throws RecognitionException {
		try {
			int _type = OPEN_BRACKET;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:751:13: ( '[' )
			// hql.g:751:15: '['
			{
			match('['); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OPEN_BRACKET"

	// $ANTLR start "CLOSE_BRACKET"
	public final void mCLOSE_BRACKET() throws RecognitionException {
		try {
			int _type = CLOSE_BRACKET;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:752:14: ( ']' )
			// hql.g:752:16: ']'
			{
			match(']'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CLOSE_BRACKET"

	// $ANTLR start "CONCAT"
	public final void mCONCAT() throws RecognitionException {
		try {
			int _type = CONCAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:754:7: ( '||' )
			// hql.g:754:9: '||'
			{
			match("||"); if (state.failed) return;

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CONCAT"

	// $ANTLR start "PLUS"
	public final void mPLUS() throws RecognitionException {
		try {
			int _type = PLUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:755:5: ( '+' )
			// hql.g:755:7: '+'
			{
			match('+'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PLUS"

	// $ANTLR start "MINUS"
	public final void mMINUS() throws RecognitionException {
		try {
			int _type = MINUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:756:6: ( '-' )
			// hql.g:756:8: '-'
			{
			match('-'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MINUS"

	// $ANTLR start "STAR"
	public final void mSTAR() throws RecognitionException {
		try {
			int _type = STAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:757:5: ( '*' )
			// hql.g:757:7: '*'
			{
			match('*'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STAR"

	// $ANTLR start "DIV"
	public final void mDIV() throws RecognitionException {
		try {
			int _type = DIV;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:758:4: ( '/' )
			// hql.g:758:6: '/'
			{
			match('/'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIV"

	// $ANTLR start "COLON"
	public final void mCOLON() throws RecognitionException {
		try {
			int _type = COLON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:759:6: ( ':' )
			// hql.g:759:8: ':'
			{
			match(':'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COLON"

	// $ANTLR start "PARAM"
	public final void mPARAM() throws RecognitionException {
		try {
			int _type = PARAM;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:760:6: ( '?' )
			// hql.g:760:8: '?'
			{
			match('?'); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PARAM"

	// $ANTLR start "IDENT"
	public final void mIDENT() throws RecognitionException {
		try {
			int _type = IDENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:763:2: ( ID_START_LETTER ( ID_LETTER )* )
			// hql.g:763:4: ID_START_LETTER ( ID_LETTER )*
			{
			mID_START_LETTER(); if (state.failed) return;

			// hql.g:763:20: ( ID_LETTER )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0=='$'||(LA2_0 >= '0' && LA2_0 <= '9')||(LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='_'||(LA2_0 >= 'a' && LA2_0 <= 'z')||(LA2_0 >= '\u0080' && LA2_0 <= '\uFFFE')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// hql.g:
					{
					if ( input.LA(1)=='$'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||(input.LA(1) >= '\u0080' && input.LA(1) <= '\uFFFE') ) {
						input.consume();
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop2;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IDENT"

	// $ANTLR start "ID_START_LETTER"
	public final void mID_START_LETTER() throws RecognitionException {
		try {
			// hql.g:769:5: ( '_' | '$' | 'a' .. 'z' | 'A' .. 'Z' | '\\u0080' .. '\\ufffe' )
			// hql.g:
			{
			if ( input.LA(1)=='$'||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||(input.LA(1) >= '\u0080' && input.LA(1) <= '\uFFFE') ) {
				input.consume();
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ID_START_LETTER"

	// $ANTLR start "ID_LETTER"
	public final void mID_LETTER() throws RecognitionException {
		try {
			// hql.g:778:5: ( ID_START_LETTER | '0' .. '9' )
			// hql.g:
			{
			if ( input.LA(1)=='$'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||(input.LA(1) >= '\u0080' && input.LA(1) <= '\uFFFE') ) {
				input.consume();
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ID_LETTER"

	// $ANTLR start "QUOTED_String"
	public final void mQUOTED_String() throws RecognitionException {
		try {
			int _type = QUOTED_String;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:782:4: ( '\\'' ( ( ESCqs )=> ESCqs |~ '\\'' )* '\\'' )
			// hql.g:782:6: '\\'' ( ( ESCqs )=> ESCqs |~ '\\'' )* '\\''
			{
			match('\''); if (state.failed) return;
			// hql.g:782:11: ( ( ESCqs )=> ESCqs |~ '\\'' )*
			loop3:
			while (true) {
				int alt3=3;
				int LA3_0 = input.LA(1);
				if ( (LA3_0=='\'') ) {
					int LA3_1 = input.LA(2);
					if ( (LA3_1=='\'') && (synpred1_hql())) {
						alt3=1;
					}

				}
				else if ( ((LA3_0 >= '\u0000' && LA3_0 <= '&')||(LA3_0 >= '(' && LA3_0 <= '\uFFFF')) ) {
					alt3=2;
				}

				switch (alt3) {
				case 1 :
					// hql.g:782:13: ( ESCqs )=> ESCqs
					{
					mESCqs(); if (state.failed) return;

					}
					break;
				case 2 :
					// hql.g:782:31: ~ '\\''
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop3;
				}
			}

			match('\''); if (state.failed) return;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "QUOTED_String"

	// $ANTLR start "ESCqs"
	public final void mESCqs() throws RecognitionException {
		try {
			// hql.g:788:2: ( '\\'' '\\'' )
			// hql.g:789:3: '\\'' '\\''
			{
			match('\''); if (state.failed) return;
			match('\''); if (state.failed) return;
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ESCqs"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// hql.g:791:5: ( ( ' ' | '\\t' | '\\r' '\\n' | '\\n' | '\\r' ) )
			// hql.g:791:9: ( ' ' | '\\t' | '\\r' '\\n' | '\\n' | '\\r' )
			{
			// hql.g:791:9: ( ' ' | '\\t' | '\\r' '\\n' | '\\n' | '\\r' )
			int alt4=5;
			switch ( input.LA(1) ) {
			case ' ':
				{
				alt4=1;
				}
				break;
			case '\t':
				{
				alt4=2;
				}
				break;
			case '\r':
				{
				int LA4_3 = input.LA(2);
				if ( (LA4_3=='\n') ) {
					alt4=3;
				}

				else {
					alt4=5;
				}

				}
				break;
			case '\n':
				{
				alt4=4;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return;}
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}
			switch (alt4) {
				case 1 :
					// hql.g:791:13: ' '
					{
					match(' '); if (state.failed) return;
					}
					break;
				case 2 :
					// hql.g:792:7: '\\t'
					{
					match('\t'); if (state.failed) return;
					}
					break;
				case 3 :
					// hql.g:793:7: '\\r' '\\n'
					{
					match('\r'); if (state.failed) return;
					match('\n'); if (state.failed) return;
					}
					break;
				case 4 :
					// hql.g:794:7: '\\n'
					{
					match('\n'); if (state.failed) return;
					}
					break;
				case 5 :
					// hql.g:795:7: '\\r'
					{
					match('\r'); if (state.failed) return;
					}
					break;

			}

			if ( state.backtracking==0 ) {skip();}
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	// $ANTLR start "NUM_INT"
	public final void mNUM_INT() throws RecognitionException {
		try {
			int _type = NUM_INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			CommonToken f1=null;
			CommonToken f2=null;
			CommonToken f3=null;
			CommonToken f4=null;

			boolean isDecimal=false; Token t=null;
			// hql.g:804:2: ( '.' ( ( '0' .. '9' )+ ( EXPONENT )? (f1= FLOAT_SUFFIX )? )? | ( '0' ( ( 'x' ) ( HEX_DIGIT )+ | ( '0' .. '7' )+ )? | ( '1' .. '9' ) ( '0' .. '9' )* ) ( ( 'l' ) |{...}? ( '.' ( '0' .. '9' )* ( EXPONENT )? (f2= FLOAT_SUFFIX )? | EXPONENT (f3= FLOAT_SUFFIX )? |f4= FLOAT_SUFFIX ) )? )
			int alt20=2;
			int LA20_0 = input.LA(1);
			if ( (LA20_0=='.') ) {
				alt20=1;
			}
			else if ( ((LA20_0 >= '0' && LA20_0 <= '9')) ) {
				alt20=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return;}
				NoViableAltException nvae =
					new NoViableAltException("", 20, 0, input);
				throw nvae;
			}

			switch (alt20) {
				case 1 :
					// hql.g:804:6: '.' ( ( '0' .. '9' )+ ( EXPONENT )? (f1= FLOAT_SUFFIX )? )?
					{
					match('.'); if (state.failed) return;
					if ( state.backtracking==0 ) {_type = DOT;}
					// hql.g:805:4: ( ( '0' .. '9' )+ ( EXPONENT )? (f1= FLOAT_SUFFIX )? )?
					int alt8=2;
					int LA8_0 = input.LA(1);
					if ( ((LA8_0 >= '0' && LA8_0 <= '9')) ) {
						alt8=1;
					}
					switch (alt8) {
						case 1 :
							// hql.g:805:6: ( '0' .. '9' )+ ( EXPONENT )? (f1= FLOAT_SUFFIX )?
							{
							// hql.g:805:6: ( '0' .. '9' )+
							int cnt5=0;
							loop5:
							while (true) {
								int alt5=2;
								int LA5_0 = input.LA(1);
								if ( ((LA5_0 >= '0' && LA5_0 <= '9')) ) {
									alt5=1;
								}

								switch (alt5) {
								case 1 :
									// hql.g:
									{
									if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
										input.consume();
										state.failed=false;
									}
									else {
										if (state.backtracking>0) {state.failed=true; return;}
										MismatchedSetException mse = new MismatchedSetException(null,input);
										recover(mse);
										throw mse;
									}
									}
									break;

								default :
									if ( cnt5 >= 1 ) break loop5;
									if (state.backtracking>0) {state.failed=true; return;}
									EarlyExitException eee = new EarlyExitException(5, input);
									throw eee;
								}
								cnt5++;
							}

							// hql.g:805:18: ( EXPONENT )?
							int alt6=2;
							int LA6_0 = input.LA(1);
							if ( (LA6_0=='e') ) {
								alt6=1;
							}
							switch (alt6) {
								case 1 :
									// hql.g:805:19: EXPONENT
									{
									mEXPONENT(); if (state.failed) return;

									}
									break;

							}

							// hql.g:805:30: (f1= FLOAT_SUFFIX )?
							int alt7=2;
							int LA7_0 = input.LA(1);
							if ( (LA7_0=='d'||LA7_0=='f'||LA7_0=='m') ) {
								alt7=1;
							}
							switch (alt7) {
								case 1 :
									// hql.g:805:31: f1= FLOAT_SUFFIX
									{
									int f1Start986 = getCharIndex();
									int f1StartLine986 = getLine();
									int f1StartCharPos986 = getCharPositionInLine();
									mFLOAT_SUFFIX(); if (state.failed) return;
									f1 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, f1Start986, getCharIndex()-1);
									f1.setLine(f1StartLine986);
									f1.setCharPositionInLine(f1StartCharPos986);

									if ( state.backtracking==0 ) {t=f1;}
									}
									break;

							}

							if ( state.backtracking==0 ) {
												if (t != null && t.getText().toUpperCase().indexOf('F')>=0)
												{
													_type = NUM_FLOAT;
												}
												else if (t != null && t.getText().toUpperCase().indexOf('M')>=0)
												{
													_type = NUM_DECIMAL;
												}
												else
												{
													_type = NUM_DOUBLE; // assume double
												}
											}
							}
							break;

					}

					}
					break;
				case 2 :
					// hql.g:821:4: ( '0' ( ( 'x' ) ( HEX_DIGIT )+ | ( '0' .. '7' )+ )? | ( '1' .. '9' ) ( '0' .. '9' )* ) ( ( 'l' ) |{...}? ( '.' ( '0' .. '9' )* ( EXPONENT )? (f2= FLOAT_SUFFIX )? | EXPONENT (f3= FLOAT_SUFFIX )? |f4= FLOAT_SUFFIX ) )?
					{
					// hql.g:821:4: ( '0' ( ( 'x' ) ( HEX_DIGIT )+ | ( '0' .. '7' )+ )? | ( '1' .. '9' ) ( '0' .. '9' )* )
					int alt13=2;
					int LA13_0 = input.LA(1);
					if ( (LA13_0=='0') ) {
						alt13=1;
					}
					else if ( ((LA13_0 >= '1' && LA13_0 <= '9')) ) {
						alt13=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return;}
						NoViableAltException nvae =
							new NoViableAltException("", 13, 0, input);
						throw nvae;
					}

					switch (alt13) {
						case 1 :
							// hql.g:821:6: '0' ( ( 'x' ) ( HEX_DIGIT )+ | ( '0' .. '7' )+ )?
							{
							match('0'); if (state.failed) return;
							if ( state.backtracking==0 ) {isDecimal = true;}
							// hql.g:822:4: ( ( 'x' ) ( HEX_DIGIT )+ | ( '0' .. '7' )+ )?
							int alt11=3;
							int LA11_0 = input.LA(1);
							if ( (LA11_0=='x') ) {
								alt11=1;
							}
							else if ( ((LA11_0 >= '0' && LA11_0 <= '7')) ) {
								alt11=2;
							}
							switch (alt11) {
								case 1 :
									// hql.g:822:6: ( 'x' ) ( HEX_DIGIT )+
									{
									// hql.g:822:6: ( 'x' )
									// hql.g:822:7: 'x'
									{
									match('x'); if (state.failed) return;
									}

									// hql.g:823:5: ( HEX_DIGIT )+
									int cnt9=0;
									loop9:
									while (true) {
										int alt9=2;
										switch ( input.LA(1) ) {
										case 'e':
											{
											int LA9_2 = input.LA(2);
											if ( ((LA9_2 >= '0' && LA9_2 <= '9')) ) {
												int LA9_5 = input.LA(3);
												if ( (!(((isDecimal)))) ) {
													alt9=1;
												}

											}
											else {
												alt9=1;
											}

											}
											break;
										case 'd':
										case 'f':
											{
											int LA9_3 = input.LA(2);
											if ( (!(((isDecimal)))) ) {
												alt9=1;
											}

											}
											break;
										case '0':
										case '1':
										case '2':
										case '3':
										case '4':
										case '5':
										case '6':
										case '7':
										case '8':
										case '9':
										case 'a':
										case 'b':
										case 'c':
											{
											alt9=1;
											}
											break;
										}
										switch (alt9) {
										case 1 :
											// hql.g:
											{
											if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
												input.consume();
												state.failed=false;
											}
											else {
												if (state.backtracking>0) {state.failed=true; return;}
												MismatchedSetException mse = new MismatchedSetException(null,input);
												recover(mse);
												throw mse;
											}
											}
											break;

										default :
											if ( cnt9 >= 1 ) break loop9;
											if (state.backtracking>0) {state.failed=true; return;}
											EarlyExitException eee = new EarlyExitException(9, input);
											throw eee;
										}
										cnt9++;
									}

									}
									break;
								case 2 :
									// hql.g:832:6: ( '0' .. '7' )+
									{
									// hql.g:832:6: ( '0' .. '7' )+
									int cnt10=0;
									loop10:
									while (true) {
										int alt10=2;
										int LA10_0 = input.LA(1);
										if ( ((LA10_0 >= '0' && LA10_0 <= '7')) ) {
											alt10=1;
										}

										switch (alt10) {
										case 1 :
											// hql.g:
											{
											if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
												input.consume();
												state.failed=false;
											}
											else {
												if (state.backtracking>0) {state.failed=true; return;}
												MismatchedSetException mse = new MismatchedSetException(null,input);
												recover(mse);
												throw mse;
											}
											}
											break;

										default :
											if ( cnt10 >= 1 ) break loop10;
											if (state.backtracking>0) {state.failed=true; return;}
											EarlyExitException eee = new EarlyExitException(10, input);
											throw eee;
										}
										cnt10++;
									}

									}
									break;

							}

							}
							break;
						case 2 :
							// hql.g:834:5: ( '1' .. '9' ) ( '0' .. '9' )*
							{
							if ( (input.LA(1) >= '1' && input.LA(1) <= '9') ) {
								input.consume();
								state.failed=false;
							}
							else {
								if (state.backtracking>0) {state.failed=true; return;}
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							// hql.g:834:16: ( '0' .. '9' )*
							loop12:
							while (true) {
								int alt12=2;
								int LA12_0 = input.LA(1);
								if ( ((LA12_0 >= '0' && LA12_0 <= '9')) ) {
									alt12=1;
								}

								switch (alt12) {
								case 1 :
									// hql.g:
									{
									if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
										input.consume();
										state.failed=false;
									}
									else {
										if (state.backtracking>0) {state.failed=true; return;}
										MismatchedSetException mse = new MismatchedSetException(null,input);
										recover(mse);
										throw mse;
									}
									}
									break;

								default :
									break loop12;
								}
							}

							if ( state.backtracking==0 ) {isDecimal=true;}
							}
							break;

					}

					// hql.g:836:3: ( ( 'l' ) |{...}? ( '.' ( '0' .. '9' )* ( EXPONENT )? (f2= FLOAT_SUFFIX )? | EXPONENT (f3= FLOAT_SUFFIX )? |f4= FLOAT_SUFFIX ) )?
					int alt19=3;
					int LA19_0 = input.LA(1);
					if ( (LA19_0=='l') ) {
						alt19=1;
					}
					else if ( (LA19_0=='.'||(LA19_0 >= 'd' && LA19_0 <= 'f')||LA19_0=='m') ) {
						alt19=2;
					}
					switch (alt19) {
						case 1 :
							// hql.g:836:5: ( 'l' )
							{
							// hql.g:836:5: ( 'l' )
							// hql.g:836:6: 'l'
							{
							match('l'); if (state.failed) return;
							}

							if ( state.backtracking==0 ) { _type = NUM_LONG; }
							}
							break;
						case 2 :
							// hql.g:839:5: {...}? ( '.' ( '0' .. '9' )* ( EXPONENT )? (f2= FLOAT_SUFFIX )? | EXPONENT (f3= FLOAT_SUFFIX )? |f4= FLOAT_SUFFIX )
							{
							if ( !((isDecimal)) ) {
								if (state.backtracking>0) {state.failed=true; return;}
								throw new FailedPredicateException(input, "NUM_INT", "isDecimal");
							}
							// hql.g:840:4: ( '.' ( '0' .. '9' )* ( EXPONENT )? (f2= FLOAT_SUFFIX )? | EXPONENT (f3= FLOAT_SUFFIX )? |f4= FLOAT_SUFFIX )
							int alt18=3;
							switch ( input.LA(1) ) {
							case '.':
								{
								alt18=1;
								}
								break;
							case 'e':
								{
								alt18=2;
								}
								break;
							case 'd':
							case 'f':
							case 'm':
								{
								alt18=3;
								}
								break;
							default:
								if (state.backtracking>0) {state.failed=true; return;}
								NoViableAltException nvae =
									new NoViableAltException("", 18, 0, input);
								throw nvae;
							}
							switch (alt18) {
								case 1 :
									// hql.g:840:8: '.' ( '0' .. '9' )* ( EXPONENT )? (f2= FLOAT_SUFFIX )?
									{
									match('.'); if (state.failed) return;
									// hql.g:840:12: ( '0' .. '9' )*
									loop14:
									while (true) {
										int alt14=2;
										int LA14_0 = input.LA(1);
										if ( ((LA14_0 >= '0' && LA14_0 <= '9')) ) {
											alt14=1;
										}

										switch (alt14) {
										case 1 :
											// hql.g:
											{
											if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
												input.consume();
												state.failed=false;
											}
											else {
												if (state.backtracking>0) {state.failed=true; return;}
												MismatchedSetException mse = new MismatchedSetException(null,input);
												recover(mse);
												throw mse;
											}
											}
											break;

										default :
											break loop14;
										}
									}

									// hql.g:840:24: ( EXPONENT )?
									int alt15=2;
									int LA15_0 = input.LA(1);
									if ( (LA15_0=='e') ) {
										alt15=1;
									}
									switch (alt15) {
										case 1 :
											// hql.g:840:25: EXPONENT
											{
											mEXPONENT(); if (state.failed) return;

											}
											break;

									}

									// hql.g:840:36: (f2= FLOAT_SUFFIX )?
									int alt16=2;
									int LA16_0 = input.LA(1);
									if ( (LA16_0=='d'||LA16_0=='f'||LA16_0=='m') ) {
										alt16=1;
									}
									switch (alt16) {
										case 1 :
											// hql.g:840:37: f2= FLOAT_SUFFIX
											{
											int f2Start1188 = getCharIndex();
											int f2StartLine1188 = getLine();
											int f2StartCharPos1188 = getCharPositionInLine();
											mFLOAT_SUFFIX(); if (state.failed) return;
											f2 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, f2Start1188, getCharIndex()-1);
											f2.setLine(f2StartLine1188);
											f2.setCharPositionInLine(f2StartCharPos1188);

											if ( state.backtracking==0 ) {t=f2;}
											}
											break;

									}

									}
									break;
								case 2 :
									// hql.g:841:8: EXPONENT (f3= FLOAT_SUFFIX )?
									{
									mEXPONENT(); if (state.failed) return;

									// hql.g:841:17: (f3= FLOAT_SUFFIX )?
									int alt17=2;
									int LA17_0 = input.LA(1);
									if ( (LA17_0=='d'||LA17_0=='f'||LA17_0=='m') ) {
										alt17=1;
									}
									switch (alt17) {
										case 1 :
											// hql.g:841:18: f3= FLOAT_SUFFIX
											{
											int f3Start1206 = getCharIndex();
											int f3StartLine1206 = getLine();
											int f3StartCharPos1206 = getCharPositionInLine();
											mFLOAT_SUFFIX(); if (state.failed) return;
											f3 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, f3Start1206, getCharIndex()-1);
											f3.setLine(f3StartLine1206);
											f3.setCharPositionInLine(f3StartCharPos1206);

											if ( state.backtracking==0 ) {t=f3;}
											}
											break;

									}

									}
									break;
								case 3 :
									// hql.g:842:8: f4= FLOAT_SUFFIX
									{
									int f4Start1221 = getCharIndex();
									int f4StartLine1221 = getLine();
									int f4StartCharPos1221 = getCharPositionInLine();
									mFLOAT_SUFFIX(); if (state.failed) return;
									f4 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, f4Start1221, getCharIndex()-1);
									f4.setLine(f4StartLine1221);
									f4.setCharPositionInLine(f4StartCharPos1221);

									if ( state.backtracking==0 ) {t=f4;}
									}
									break;

							}

							if ( state.backtracking==0 ) {
											if (t != null && t.getText().toUpperCase().indexOf('F') >= 0)
											{
												_type = NUM_FLOAT;
											}
											else if (t != null && t.getText().toUpperCase().indexOf('M')>=0)
											{
												_type = NUM_DECIMAL;
											}
											else
											{
												_type = NUM_DOUBLE; // assume double
											}
										}
							}
							break;

					}

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NUM_INT"

	// $ANTLR start "HEX_DIGIT"
	public final void mHEX_DIGIT() throws RecognitionException {
		try {
			// hql.g:865:2: ( ( '0' .. '9' | 'a' .. 'f' ) )
			// hql.g:
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
				input.consume();
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HEX_DIGIT"

	// $ANTLR start "EXPONENT"
	public final void mEXPONENT() throws RecognitionException {
		try {
			// hql.g:871:2: ( ( 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ )
			// hql.g:871:4: ( 'e' ) ( '+' | '-' )? ( '0' .. '9' )+
			{
			// hql.g:871:4: ( 'e' )
			// hql.g:871:5: 'e'
			{
			match('e'); if (state.failed) return;
			}

			// hql.g:871:10: ( '+' | '-' )?
			int alt21=2;
			int LA21_0 = input.LA(1);
			if ( (LA21_0=='+'||LA21_0=='-') ) {
				alt21=1;
			}
			switch (alt21) {
				case 1 :
					// hql.g:
					{
					if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
						input.consume();
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}

			// hql.g:871:21: ( '0' .. '9' )+
			int cnt22=0;
			loop22:
			while (true) {
				int alt22=2;
				int LA22_0 = input.LA(1);
				if ( ((LA22_0 >= '0' && LA22_0 <= '9')) ) {
					alt22=1;
				}

				switch (alt22) {
				case 1 :
					// hql.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt22 >= 1 ) break loop22;
					if (state.backtracking>0) {state.failed=true; return;}
					EarlyExitException eee = new EarlyExitException(22, input);
					throw eee;
				}
				cnt22++;
			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXPONENT"

	// $ANTLR start "FLOAT_SUFFIX"
	public final void mFLOAT_SUFFIX() throws RecognitionException {
		try {
			// hql.g:876:2: ( 'f' | 'd' | 'm' )
			// hql.g:
			{
			if ( input.LA(1)=='d'||input.LA(1)=='f'||input.LA(1)=='m' ) {
				input.consume();
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FLOAT_SUFFIX"

	@Override
	public void mTokens() throws RecognitionException {
		// hql.g:1:8: ( ALL | AND | ANY | AS | ASCENDING | AVG | BETWEEN | BOTH | CASE | CLASS | COUNT | DELETE | DESCENDING | DISTINCT | ELEMENTS | ELSE | EMPTY | END | ESCAPE | EXISTS | FALSE | FETCH | FROM | FULL | GROUP | HAVING | IN | INDICES | INNER | INSERT | INTO | IS | JOIN | LEADING | LEFT | LIKE | LITERAL_by | MAX | MEMBER | MIN | NEW | NOT | NULL | OBJECT | OF | ON | OR | ORDER | OUTER | PROPERTIES | RIGHT | SELECT | SET | SKIP | SOME | SUM | TAKE | THEN | TRAILING | TRUE | UNION | UPDATE | VERSIONED | WHEN | WHERE | WITH | T__133 | T__134 | EQ | LT | GT | SQL_NE | NE | LE | GE | BOR | BXOR | BAND | BNOT | COMMA | OPEN | CLOSE | OPEN_BRACKET | CLOSE_BRACKET | CONCAT | PLUS | MINUS | STAR | DIV | COLON | PARAM | IDENT | QUOTED_String | WS | NUM_INT )
		int alt23=95;
		alt23 = dfa23.predict(input);
		switch (alt23) {
			case 1 :
				// hql.g:1:10: ALL
				{
				mALL(); if (state.failed) return;

				}
				break;
			case 2 :
				// hql.g:1:14: AND
				{
				mAND(); if (state.failed) return;

				}
				break;
			case 3 :
				// hql.g:1:18: ANY
				{
				mANY(); if (state.failed) return;

				}
				break;
			case 4 :
				// hql.g:1:22: AS
				{
				mAS(); if (state.failed) return;

				}
				break;
			case 5 :
				// hql.g:1:25: ASCENDING
				{
				mASCENDING(); if (state.failed) return;

				}
				break;
			case 6 :
				// hql.g:1:35: AVG
				{
				mAVG(); if (state.failed) return;

				}
				break;
			case 7 :
				// hql.g:1:39: BETWEEN
				{
				mBETWEEN(); if (state.failed) return;

				}
				break;
			case 8 :
				// hql.g:1:47: BOTH
				{
				mBOTH(); if (state.failed) return;

				}
				break;
			case 9 :
				// hql.g:1:52: CASE
				{
				mCASE(); if (state.failed) return;

				}
				break;
			case 10 :
				// hql.g:1:57: CLASS
				{
				mCLASS(); if (state.failed) return;

				}
				break;
			case 11 :
				// hql.g:1:63: COUNT
				{
				mCOUNT(); if (state.failed) return;

				}
				break;
			case 12 :
				// hql.g:1:69: DELETE
				{
				mDELETE(); if (state.failed) return;

				}
				break;
			case 13 :
				// hql.g:1:76: DESCENDING
				{
				mDESCENDING(); if (state.failed) return;

				}
				break;
			case 14 :
				// hql.g:1:87: DISTINCT
				{
				mDISTINCT(); if (state.failed) return;

				}
				break;
			case 15 :
				// hql.g:1:96: ELEMENTS
				{
				mELEMENTS(); if (state.failed) return;

				}
				break;
			case 16 :
				// hql.g:1:105: ELSE
				{
				mELSE(); if (state.failed) return;

				}
				break;
			case 17 :
				// hql.g:1:110: EMPTY
				{
				mEMPTY(); if (state.failed) return;

				}
				break;
			case 18 :
				// hql.g:1:116: END
				{
				mEND(); if (state.failed) return;

				}
				break;
			case 19 :
				// hql.g:1:120: ESCAPE
				{
				mESCAPE(); if (state.failed) return;

				}
				break;
			case 20 :
				// hql.g:1:127: EXISTS
				{
				mEXISTS(); if (state.failed) return;

				}
				break;
			case 21 :
				// hql.g:1:134: FALSE
				{
				mFALSE(); if (state.failed) return;

				}
				break;
			case 22 :
				// hql.g:1:140: FETCH
				{
				mFETCH(); if (state.failed) return;

				}
				break;
			case 23 :
				// hql.g:1:146: FROM
				{
				mFROM(); if (state.failed) return;

				}
				break;
			case 24 :
				// hql.g:1:151: FULL
				{
				mFULL(); if (state.failed) return;

				}
				break;
			case 25 :
				// hql.g:1:156: GROUP
				{
				mGROUP(); if (state.failed) return;

				}
				break;
			case 26 :
				// hql.g:1:162: HAVING
				{
				mHAVING(); if (state.failed) return;

				}
				break;
			case 27 :
				// hql.g:1:169: IN
				{
				mIN(); if (state.failed) return;

				}
				break;
			case 28 :
				// hql.g:1:172: INDICES
				{
				mINDICES(); if (state.failed) return;

				}
				break;
			case 29 :
				// hql.g:1:180: INNER
				{
				mINNER(); if (state.failed) return;

				}
				break;
			case 30 :
				// hql.g:1:186: INSERT
				{
				mINSERT(); if (state.failed) return;

				}
				break;
			case 31 :
				// hql.g:1:193: INTO
				{
				mINTO(); if (state.failed) return;

				}
				break;
			case 32 :
				// hql.g:1:198: IS
				{
				mIS(); if (state.failed) return;

				}
				break;
			case 33 :
				// hql.g:1:201: JOIN
				{
				mJOIN(); if (state.failed) return;

				}
				break;
			case 34 :
				// hql.g:1:206: LEADING
				{
				mLEADING(); if (state.failed) return;

				}
				break;
			case 35 :
				// hql.g:1:214: LEFT
				{
				mLEFT(); if (state.failed) return;

				}
				break;
			case 36 :
				// hql.g:1:219: LIKE
				{
				mLIKE(); if (state.failed) return;

				}
				break;
			case 37 :
				// hql.g:1:224: LITERAL_by
				{
				mLITERAL_by(); if (state.failed) return;

				}
				break;
			case 38 :
				// hql.g:1:235: MAX
				{
				mMAX(); if (state.failed) return;

				}
				break;
			case 39 :
				// hql.g:1:239: MEMBER
				{
				mMEMBER(); if (state.failed) return;

				}
				break;
			case 40 :
				// hql.g:1:246: MIN
				{
				mMIN(); if (state.failed) return;

				}
				break;
			case 41 :
				// hql.g:1:250: NEW
				{
				mNEW(); if (state.failed) return;

				}
				break;
			case 42 :
				// hql.g:1:254: NOT
				{
				mNOT(); if (state.failed) return;

				}
				break;
			case 43 :
				// hql.g:1:258: NULL
				{
				mNULL(); if (state.failed) return;

				}
				break;
			case 44 :
				// hql.g:1:263: OBJECT
				{
				mOBJECT(); if (state.failed) return;

				}
				break;
			case 45 :
				// hql.g:1:270: OF
				{
				mOF(); if (state.failed) return;

				}
				break;
			case 46 :
				// hql.g:1:273: ON
				{
				mON(); if (state.failed) return;

				}
				break;
			case 47 :
				// hql.g:1:276: OR
				{
				mOR(); if (state.failed) return;

				}
				break;
			case 48 :
				// hql.g:1:279: ORDER
				{
				mORDER(); if (state.failed) return;

				}
				break;
			case 49 :
				// hql.g:1:285: OUTER
				{
				mOUTER(); if (state.failed) return;

				}
				break;
			case 50 :
				// hql.g:1:291: PROPERTIES
				{
				mPROPERTIES(); if (state.failed) return;

				}
				break;
			case 51 :
				// hql.g:1:302: RIGHT
				{
				mRIGHT(); if (state.failed) return;

				}
				break;
			case 52 :
				// hql.g:1:308: SELECT
				{
				mSELECT(); if (state.failed) return;

				}
				break;
			case 53 :
				// hql.g:1:315: SET
				{
				mSET(); if (state.failed) return;

				}
				break;
			case 54 :
				// hql.g:1:319: SKIP
				{
				mSKIP(); if (state.failed) return;

				}
				break;
			case 55 :
				// hql.g:1:324: SOME
				{
				mSOME(); if (state.failed) return;

				}
				break;
			case 56 :
				// hql.g:1:329: SUM
				{
				mSUM(); if (state.failed) return;

				}
				break;
			case 57 :
				// hql.g:1:333: TAKE
				{
				mTAKE(); if (state.failed) return;

				}
				break;
			case 58 :
				// hql.g:1:338: THEN
				{
				mTHEN(); if (state.failed) return;

				}
				break;
			case 59 :
				// hql.g:1:343: TRAILING
				{
				mTRAILING(); if (state.failed) return;

				}
				break;
			case 60 :
				// hql.g:1:352: TRUE
				{
				mTRUE(); if (state.failed) return;

				}
				break;
			case 61 :
				// hql.g:1:357: UNION
				{
				mUNION(); if (state.failed) return;

				}
				break;
			case 62 :
				// hql.g:1:363: UPDATE
				{
				mUPDATE(); if (state.failed) return;

				}
				break;
			case 63 :
				// hql.g:1:370: VERSIONED
				{
				mVERSIONED(); if (state.failed) return;

				}
				break;
			case 64 :
				// hql.g:1:380: WHEN
				{
				mWHEN(); if (state.failed) return;

				}
				break;
			case 65 :
				// hql.g:1:385: WHERE
				{
				mWHERE(); if (state.failed) return;

				}
				break;
			case 66 :
				// hql.g:1:391: WITH
				{
				mWITH(); if (state.failed) return;

				}
				break;
			case 67 :
				// hql.g:1:396: T__133
				{
				mT__133(); if (state.failed) return;

				}
				break;
			case 68 :
				// hql.g:1:403: T__134
				{
				mT__134(); if (state.failed) return;

				}
				break;
			case 69 :
				// hql.g:1:410: EQ
				{
				mEQ(); if (state.failed) return;

				}
				break;
			case 70 :
				// hql.g:1:413: LT
				{
				mLT(); if (state.failed) return;

				}
				break;
			case 71 :
				// hql.g:1:416: GT
				{
				mGT(); if (state.failed) return;

				}
				break;
			case 72 :
				// hql.g:1:419: SQL_NE
				{
				mSQL_NE(); if (state.failed) return;

				}
				break;
			case 73 :
				// hql.g:1:426: NE
				{
				mNE(); if (state.failed) return;

				}
				break;
			case 74 :
				// hql.g:1:429: LE
				{
				mLE(); if (state.failed) return;

				}
				break;
			case 75 :
				// hql.g:1:432: GE
				{
				mGE(); if (state.failed) return;

				}
				break;
			case 76 :
				// hql.g:1:435: BOR
				{
				mBOR(); if (state.failed) return;

				}
				break;
			case 77 :
				// hql.g:1:439: BXOR
				{
				mBXOR(); if (state.failed) return;

				}
				break;
			case 78 :
				// hql.g:1:444: BAND
				{
				mBAND(); if (state.failed) return;

				}
				break;
			case 79 :
				// hql.g:1:449: BNOT
				{
				mBNOT(); if (state.failed) return;

				}
				break;
			case 80 :
				// hql.g:1:454: COMMA
				{
				mCOMMA(); if (state.failed) return;

				}
				break;
			case 81 :
				// hql.g:1:460: OPEN
				{
				mOPEN(); if (state.failed) return;

				}
				break;
			case 82 :
				// hql.g:1:465: CLOSE
				{
				mCLOSE(); if (state.failed) return;

				}
				break;
			case 83 :
				// hql.g:1:471: OPEN_BRACKET
				{
				mOPEN_BRACKET(); if (state.failed) return;

				}
				break;
			case 84 :
				// hql.g:1:484: CLOSE_BRACKET
				{
				mCLOSE_BRACKET(); if (state.failed) return;

				}
				break;
			case 85 :
				// hql.g:1:498: CONCAT
				{
				mCONCAT(); if (state.failed) return;

				}
				break;
			case 86 :
				// hql.g:1:505: PLUS
				{
				mPLUS(); if (state.failed) return;

				}
				break;
			case 87 :
				// hql.g:1:510: MINUS
				{
				mMINUS(); if (state.failed) return;

				}
				break;
			case 88 :
				// hql.g:1:516: STAR
				{
				mSTAR(); if (state.failed) return;

				}
				break;
			case 89 :
				// hql.g:1:521: DIV
				{
				mDIV(); if (state.failed) return;

				}
				break;
			case 90 :
				// hql.g:1:525: COLON
				{
				mCOLON(); if (state.failed) return;

				}
				break;
			case 91 :
				// hql.g:1:531: PARAM
				{
				mPARAM(); if (state.failed) return;

				}
				break;
			case 92 :
				// hql.g:1:537: IDENT
				{
				mIDENT(); if (state.failed) return;

				}
				break;
			case 93 :
				// hql.g:1:543: QUOTED_String
				{
				mQUOTED_String(); if (state.failed) return;

				}
				break;
			case 94 :
				// hql.g:1:557: WS
				{
				mWS(); if (state.failed) return;

				}
				break;
			case 95 :
				// hql.g:1:560: NUM_INT
				{
				mNUM_INT(); if (state.failed) return;

				}
				break;

		}
	}

	// $ANTLR start synpred1_hql
	public final void synpred1_hql_fragment() throws RecognitionException {
		// hql.g:782:13: ( ESCqs )
		// hql.g:782:14: ESCqs
		{
		mESCqs(); if (state.failed) return;

		}

	}
	// $ANTLR end synpred1_hql

	public final boolean synpred1_hql() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred1_hql_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}


	protected DFA23 dfa23 = new DFA23(this);
	static final String DFA23_eotS =
		"\1\uffff\25\50\1\uffff\1\143\1\145\1\147\1\150\1\152\20\uffff\2\50\1\157"+
		"\3\50\1\163\20\50\1\u008a\1\u008b\12\50\1\u0097\1\u0098\1\u009a\17\50"+
		"\12\uffff\1\u00ac\1\u00ad\1\u00ae\1\u00b0\1\uffff\1\u00b1\2\50\1\uffff"+
		"\11\50\1\u00bd\14\50\2\uffff\4\50\1\u00ce\1\50\1\u00d0\1\u00d1\1\u00d2"+
		"\2\50\2\uffff\1\50\1\uffff\4\50\1\u00da\2\50\1\u00dd\11\50\3\uffff\1\50"+
		"\2\uffff\1\50\1\u00ea\1\u00eb\3\50\1\u00f0\2\50\1\u00f3\1\50\1\uffff\4"+
		"\50\1\u00f9\1\u00fa\5\50\1\u0100\1\u0101\1\50\1\u0103\1\u0104\1\uffff"+
		"\1\50\3\uffff\1\u0106\6\50\1\uffff\1\u010d\1\u010e\1\uffff\1\u010f\1\u0110"+
		"\1\50\1\u0112\3\50\1\u0116\1\50\1\u0118\2\50\2\uffff\1\u011b\1\u011c\2"+
		"\50\1\uffff\2\50\1\uffff\1\u0121\2\50\1\u0124\1\u0125\2\uffff\1\u0126"+
		"\2\50\1\u0129\1\50\2\uffff\1\50\2\uffff\1\50\1\uffff\1\50\1\u012e\1\u012f"+
		"\1\50\1\u0131\1\50\4\uffff\1\50\1\uffff\1\u0134\2\50\1\uffff\1\u0137\1"+
		"\uffff\2\50\2\uffff\1\u013a\3\50\1\uffff\1\u013e\1\u013f\3\uffff\1\u0140"+
		"\1\50\1\uffff\1\u0142\1\50\1\u0144\1\u0145\2\uffff\1\50\1\uffff\1\u0147"+
		"\1\50\1\uffff\1\u0149\1\50\1\uffff\1\50\1\u014c\1\uffff\3\50\3\uffff\1"+
		"\u0150\1\uffff\1\u0151\2\uffff\1\50\1\uffff\1\50\1\uffff\2\50\1\uffff"+
		"\1\50\1\u0157\1\u0158\2\uffff\1\50\1\u015a\1\50\1\u015c\1\50\2\uffff\1"+
		"\50\1\uffff\1\u015f\1\uffff\1\u0160\1\u0161\3\uffff";
	static final String DFA23_eofS =
		"\u0162\uffff";
	static final String DFA23_minS =
		"\1\11\1\154\1\145\1\141\1\145\1\154\1\141\1\162\1\141\1\156\1\157\1\145"+
		"\1\141\1\145\1\142\1\162\1\151\1\145\1\141\1\156\1\145\1\150\1\uffff\4"+
		"\75\1\174\20\uffff\1\154\1\144\1\44\1\147\2\164\1\44\1\163\1\141\1\165"+
		"\1\154\1\163\1\145\1\160\1\144\1\143\1\151\1\154\1\164\1\157\1\154\1\157"+
		"\1\166\2\44\1\151\1\141\1\153\1\170\1\155\1\156\1\167\1\164\1\154\1\152"+
		"\3\44\1\164\1\157\1\147\1\154\1\151\2\155\1\153\1\145\1\141\1\151\1\144"+
		"\1\162\1\145\1\164\12\uffff\4\44\1\uffff\1\44\1\167\1\150\1\uffff\1\145"+
		"\1\163\1\156\1\145\1\143\1\164\1\155\1\145\1\164\1\44\1\141\2\163\1\143"+
		"\1\155\1\154\1\165\2\151\2\145\1\157\2\uffff\1\156\1\144\1\164\1\145\1"+
		"\44\1\142\3\44\1\154\1\145\2\uffff\1\145\1\uffff\1\145\1\160\1\150\1\145"+
		"\1\44\1\160\1\145\1\44\1\145\1\156\1\151\1\145\1\157\1\141\1\163\1\156"+
		"\1\150\3\uffff\1\156\2\uffff\1\145\2\44\1\163\2\164\1\44\1\151\1\145\1"+
		"\44\1\171\1\uffff\1\160\1\164\1\145\1\150\2\44\1\160\1\156\1\143\2\162"+
		"\2\44\1\151\2\44\1\uffff\1\145\3\uffff\1\44\1\143\2\162\1\145\1\164\1"+
		"\143\1\uffff\2\44\1\uffff\2\44\1\154\1\44\1\156\1\164\1\151\1\44\1\145"+
		"\1\44\1\144\1\145\2\uffff\2\44\1\145\1\156\1\uffff\2\156\1\uffff\1\44"+
		"\1\145\1\163\2\44\2\uffff\1\44\1\147\1\145\1\44\1\164\2\uffff\1\156\2"+
		"\uffff\1\162\1\uffff\1\164\2\44\1\162\1\44\1\164\4\uffff\1\151\1\uffff"+
		"\1\44\1\145\1\157\1\uffff\1\44\1\uffff\1\151\1\156\2\uffff\1\44\1\144"+
		"\1\143\1\164\1\uffff\2\44\3\uffff\1\44\1\163\1\uffff\1\44\1\147\2\44\2"+
		"\uffff\1\164\1\uffff\1\44\1\156\1\uffff\1\44\1\156\1\uffff\1\156\1\44"+
		"\1\uffff\1\151\1\164\1\163\3\uffff\1\44\1\uffff\1\44\2\uffff\1\151\1\uffff"+
		"\1\147\1\uffff\1\145\1\147\1\uffff\1\156\2\44\2\uffff\1\145\1\44\1\144"+
		"\1\44\1\147\2\uffff\1\163\1\uffff\1\44\1\uffff\2\44\3\uffff";
	static final String DFA23_maxS =
		"\1\ufffe\1\166\1\171\1\157\1\151\1\170\1\165\1\162\1\141\1\163\1\157\2"+
		"\151\2\165\1\162\1\151\1\165\1\162\1\160\1\145\1\151\1\uffff\1\76\3\75"+
		"\1\174\20\uffff\1\154\1\171\1\ufffe\1\147\2\164\1\ufffe\1\163\1\141\1"+
		"\165\3\163\1\160\1\144\1\143\1\151\1\154\1\164\1\157\1\154\1\157\1\166"+
		"\2\ufffe\1\151\1\146\1\153\1\170\1\155\1\156\1\167\1\164\1\154\1\152\3"+
		"\ufffe\1\164\1\157\1\147\1\164\1\151\2\155\1\153\1\145\1\165\1\151\1\144"+
		"\1\162\1\145\1\164\12\uffff\4\ufffe\1\uffff\1\ufffe\1\167\1\150\1\uffff"+
		"\1\145\1\163\1\156\1\145\1\143\1\164\1\155\1\145\1\164\1\ufffe\1\141\2"+
		"\163\1\143\1\155\1\154\1\165\2\151\2\145\1\157\2\uffff\1\156\1\144\1\164"+
		"\1\145\1\ufffe\1\142\3\ufffe\1\154\1\145\2\uffff\1\145\1\uffff\1\145\1"+
		"\160\1\150\1\145\1\ufffe\1\160\1\145\1\ufffe\1\145\1\156\1\151\1\145\1"+
		"\157\1\141\1\163\1\162\1\150\3\uffff\1\156\2\uffff\1\145\2\ufffe\1\163"+
		"\2\164\1\ufffe\1\151\1\145\1\ufffe\1\171\1\uffff\1\160\1\164\1\145\1\150"+
		"\2\ufffe\1\160\1\156\1\143\2\162\2\ufffe\1\151\2\ufffe\1\uffff\1\145\3"+
		"\uffff\1\ufffe\1\143\2\162\1\145\1\164\1\143\1\uffff\2\ufffe\1\uffff\2"+
		"\ufffe\1\154\1\ufffe\1\156\1\164\1\151\1\ufffe\1\145\1\ufffe\1\144\1\145"+
		"\2\uffff\2\ufffe\1\145\1\156\1\uffff\2\156\1\uffff\1\ufffe\1\145\1\163"+
		"\2\ufffe\2\uffff\1\ufffe\1\147\1\145\1\ufffe\1\164\2\uffff\1\156\2\uffff"+
		"\1\162\1\uffff\1\164\2\ufffe\1\162\1\ufffe\1\164\4\uffff\1\151\1\uffff"+
		"\1\ufffe\1\145\1\157\1\uffff\1\ufffe\1\uffff\1\151\1\156\2\uffff\1\ufffe"+
		"\1\144\1\143\1\164\1\uffff\2\ufffe\3\uffff\1\ufffe\1\163\1\uffff\1\ufffe"+
		"\1\147\2\ufffe\2\uffff\1\164\1\uffff\1\ufffe\1\156\1\uffff\1\ufffe\1\156"+
		"\1\uffff\1\156\1\ufffe\1\uffff\1\151\1\164\1\163\3\uffff\1\ufffe\1\uffff"+
		"\1\ufffe\2\uffff\1\151\1\uffff\1\147\1\uffff\1\145\1\147\1\uffff\1\156"+
		"\2\ufffe\2\uffff\1\145\1\ufffe\1\144\1\ufffe\1\147\2\uffff\1\163\1\uffff"+
		"\1\ufffe\1\uffff\2\ufffe\3\uffff";
	static final String DFA23_acceptS =
		"\26\uffff\1\105\5\uffff\1\116\1\120\1\121\1\122\1\123\1\124\1\126\1\127"+
		"\1\130\1\131\1\132\1\133\1\134\1\135\1\136\1\137\65\uffff\1\110\1\112"+
		"\1\106\1\113\1\107\1\111\1\117\1\115\1\125\1\114\4\uffff\1\4\3\uffff\1"+
		"\45\26\uffff\1\33\1\40\13\uffff\1\55\1\56\1\uffff\1\57\21\uffff\1\1\1"+
		"\2\1\3\1\uffff\1\5\1\6\13\uffff\1\22\20\uffff\1\46\1\uffff\1\50\1\51\1"+
		"\52\7\uffff\1\65\2\uffff\1\70\14\uffff\1\10\1\11\4\uffff\1\15\2\uffff"+
		"\1\20\5\uffff\1\27\1\30\5\uffff\1\37\1\41\1\uffff\1\43\1\44\1\uffff\1"+
		"\53\6\uffff\1\66\1\67\1\71\1\72\1\uffff\1\74\3\uffff\1\100\1\uffff\1\102"+
		"\2\uffff\1\12\1\13\4\uffff\1\21\2\uffff\1\25\1\26\1\31\2\uffff\1\35\4"+
		"\uffff\1\60\1\61\1\uffff\1\63\2\uffff\1\75\2\uffff\1\101\2\uffff\1\14"+
		"\3\uffff\1\23\1\24\1\32\1\uffff\1\36\1\uffff\1\47\1\54\1\uffff\1\64\1"+
		"\uffff\1\76\2\uffff\1\7\3\uffff\1\34\1\42\5\uffff\1\16\1\17\1\uffff\1"+
		"\73\1\uffff\1\103\2\uffff\1\77\1\104\1\62";
	static final String DFA23_specialS =
		"\u0162\uffff}>";
	static final String[] DFA23_transitionS = {
			"\2\52\2\uffff\1\52\22\uffff\1\52\1\31\2\uffff\1\50\1\uffff\1\34\1\51"+
			"\1\36\1\37\1\44\1\42\1\35\1\43\1\53\1\45\12\53\1\46\1\uffff\1\27\1\26"+
			"\1\30\1\47\1\uffff\32\50\1\40\1\uffff\1\41\1\32\1\50\1\uffff\1\1\1\2"+
			"\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\50\1\13\1\14\1\15\1\16\1\17\1\50"+
			"\1\20\1\21\1\22\1\23\1\24\1\25\3\50\1\uffff\1\33\3\uffff\uff7f\50",
			"\1\54\1\uffff\1\55\4\uffff\1\56\2\uffff\1\57",
			"\1\60\11\uffff\1\61\11\uffff\1\62",
			"\1\63\12\uffff\1\64\2\uffff\1\65",
			"\1\66\3\uffff\1\67",
			"\1\70\1\71\1\72\4\uffff\1\73\4\uffff\1\74",
			"\1\75\3\uffff\1\76\14\uffff\1\77\2\uffff\1\100",
			"\1\101",
			"\1\102",
			"\1\103\4\uffff\1\104",
			"\1\105",
			"\1\106\3\uffff\1\107",
			"\1\110\3\uffff\1\111\3\uffff\1\112",
			"\1\113\11\uffff\1\114\5\uffff\1\115",
			"\1\116\3\uffff\1\117\7\uffff\1\120\3\uffff\1\121\2\uffff\1\122",
			"\1\123",
			"\1\124",
			"\1\125\5\uffff\1\126\3\uffff\1\127\5\uffff\1\130",
			"\1\131\6\uffff\1\132\11\uffff\1\133",
			"\1\134\1\uffff\1\135",
			"\1\136",
			"\1\137\1\140",
			"",
			"\1\142\1\141",
			"\1\144",
			"\1\146",
			"\1\146",
			"\1\151",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\153",
			"\1\154\24\uffff\1\155",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\2\50\1\156\27"+
			"\50\5\uffff\uff7f\50",
			"\1\160",
			"\1\161",
			"\1\162",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\164",
			"\1\165",
			"\1\166",
			"\1\167\6\uffff\1\170",
			"\1\171",
			"\1\172\15\uffff\1\173",
			"\1\174",
			"\1\175",
			"\1\176",
			"\1\177",
			"\1\u0080",
			"\1\u0081",
			"\1\u0082",
			"\1\u0083",
			"\1\u0084",
			"\1\u0085",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\3\50\1\u0086"+
			"\11\50\1\u0087\4\50\1\u0088\1\u0089\6\50\5\uffff\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u008c",
			"\1\u008d\4\uffff\1\u008e",
			"\1\u008f",
			"\1\u0090",
			"\1\u0091",
			"\1\u0092",
			"\1\u0093",
			"\1\u0094",
			"\1\u0095",
			"\1\u0096",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\3\50\1\u0099"+
			"\26\50\5\uffff\uff7f\50",
			"\1\u009b",
			"\1\u009c",
			"\1\u009d",
			"\1\u009e\7\uffff\1\u009f",
			"\1\u00a0",
			"\1\u00a1",
			"\1\u00a2",
			"\1\u00a3",
			"\1\u00a4",
			"\1\u00a5\23\uffff\1\u00a6",
			"\1\u00a7",
			"\1\u00a8",
			"\1\u00a9",
			"\1\u00aa",
			"\1\u00ab",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u00af"+
			"\25\50\5\uffff\uff7f\50",
			"",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u00b2",
			"\1\u00b3",
			"",
			"\1\u00b4",
			"\1\u00b5",
			"\1\u00b6",
			"\1\u00b7",
			"\1\u00b8",
			"\1\u00b9",
			"\1\u00ba",
			"\1\u00bb",
			"\1\u00bc",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u00be",
			"\1\u00bf",
			"\1\u00c0",
			"\1\u00c1",
			"\1\u00c2",
			"\1\u00c3",
			"\1\u00c4",
			"\1\u00c5",
			"\1\u00c6",
			"\1\u00c7",
			"\1\u00c8",
			"\1\u00c9",
			"",
			"",
			"\1\u00ca",
			"\1\u00cb",
			"\1\u00cc",
			"\1\u00cd",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u00cf",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u00d3",
			"\1\u00d4",
			"",
			"",
			"\1\u00d5",
			"",
			"\1\u00d6",
			"\1\u00d7",
			"\1\u00d8",
			"\1\u00d9",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u00db",
			"\1\u00dc",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u00de",
			"\1\u00df",
			"\1\u00e0",
			"\1\u00e1",
			"\1\u00e2",
			"\1\u00e3",
			"\1\u00e4",
			"\1\u00e5\3\uffff\1\u00e6",
			"\1\u00e7",
			"",
			"",
			"",
			"\1\u00e8",
			"",
			"",
			"\1\u00e9",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u00ec",
			"\1\u00ed",
			"\1\u00ee",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u00ef"+
			"\25\50\5\uffff\uff7f\50",
			"\1\u00f1",
			"\1\u00f2",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u00f4",
			"",
			"\1\u00f5",
			"\1\u00f6",
			"\1\u00f7",
			"\1\u00f8",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u00fb",
			"\1\u00fc",
			"\1\u00fd",
			"\1\u00fe",
			"\1\u00ff",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u0102",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"",
			"\1\u0105",
			"",
			"",
			"",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u0107",
			"\1\u0108",
			"\1\u0109",
			"\1\u010a",
			"\1\u010b",
			"\1\u010c",
			"",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u0111",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u0113",
			"\1\u0114",
			"\1\u0115",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u0117",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u0119",
			"\1\u011a",
			"",
			"",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u011d",
			"\1\u011e",
			"",
			"\1\u011f",
			"\1\u0120",
			"",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u0122",
			"\1\u0123",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"",
			"",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u0127",
			"\1\u0128",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u012a",
			"",
			"",
			"\1\u012b",
			"",
			"",
			"\1\u012c",
			"",
			"\1\u012d",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u0130",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u0132",
			"",
			"",
			"",
			"",
			"\1\u0133",
			"",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u0135",
			"\1\u0136",
			"",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"",
			"\1\u0138",
			"\1\u0139",
			"",
			"",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u013b",
			"\1\u013c",
			"\1\u013d",
			"",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"",
			"",
			"",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u0141",
			"",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u0143",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"",
			"",
			"\1\u0146",
			"",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u0148",
			"",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u014a",
			"",
			"\1\u014b",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"",
			"\1\u014d",
			"\1\u014e",
			"\1\u014f",
			"",
			"",
			"",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"",
			"",
			"\1\u0152",
			"",
			"\1\u0153",
			"",
			"\1\u0154",
			"\1\u0155",
			"",
			"\1\u0156",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"",
			"",
			"\1\u0159",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u015b",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\u015d",
			"",
			"",
			"\1\u015e",
			"",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"\1\50\13\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50\5\uffff"+
			"\uff7f\50",
			"",
			"",
			""
	};

	static final short[] DFA23_eot = DFA.unpackEncodedString(DFA23_eotS);
	static final short[] DFA23_eof = DFA.unpackEncodedString(DFA23_eofS);
	static final char[] DFA23_min = DFA.unpackEncodedStringToUnsignedChars(DFA23_minS);
	static final char[] DFA23_max = DFA.unpackEncodedStringToUnsignedChars(DFA23_maxS);
	static final short[] DFA23_accept = DFA.unpackEncodedString(DFA23_acceptS);
	static final short[] DFA23_special = DFA.unpackEncodedString(DFA23_specialS);
	static final short[][] DFA23_transition;

	static {
		int numStates = DFA23_transitionS.length;
		DFA23_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA23_transition[i] = DFA.unpackEncodedString(DFA23_transitionS[i]);
		}
	}

	protected class DFA23 extends DFA {

		public DFA23(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 23;
			this.eot = DFA23_eot;
			this.eof = DFA23_eof;
			this.min = DFA23_min;
			this.max = DFA23_max;
			this.accept = DFA23_accept;
			this.special = DFA23_special;
			this.transition = DFA23_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( ALL | AND | ANY | AS | ASCENDING | AVG | BETWEEN | BOTH | CASE | CLASS | COUNT | DELETE | DESCENDING | DISTINCT | ELEMENTS | ELSE | EMPTY | END | ESCAPE | EXISTS | FALSE | FETCH | FROM | FULL | GROUP | HAVING | IN | INDICES | INNER | INSERT | INTO | IS | JOIN | LEADING | LEFT | LIKE | LITERAL_by | MAX | MEMBER | MIN | NEW | NOT | NULL | OBJECT | OF | ON | OR | ORDER | OUTER | PROPERTIES | RIGHT | SELECT | SET | SKIP | SOME | SUM | TAKE | THEN | TRAILING | TRUE | UNION | UPDATE | VERSIONED | WHEN | WHERE | WITH | T__133 | T__134 | EQ | LT | GT | SQL_NE | NE | LE | GE | BOR | BXOR | BAND | BNOT | COMMA | OPEN | CLOSE | OPEN_BRACKET | CLOSE_BRACKET | CONCAT | PLUS | MINUS | STAR | DIV | COLON | PARAM | IDENT | QUOTED_String | WS | NUM_INT );";
		}
	}

}
