package fr.lteconsulting.hexa.persistence.client;

// $ANTLR 3.5.1 hql.g 2013-11-29 11:20:53

import org.antlr.runtime.BitSet;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.RewriteEarlyExitException;
import org.antlr.runtime.tree.RewriteRuleSubtreeStream;
import org.antlr.runtime.tree.RewriteRuleTokenStream;
import org.antlr.runtime.tree.TreeAdaptor;


@SuppressWarnings("all")
public class hqlParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "AGGREGATE", "ALIAS", "ALL", "AND",
		"ANY", "AS", "ASCENDING", "AVG", "BAND", "BETWEEN", "BNOT", "BOR", "BOTH",
		"BXOR", "CASE", "CASE2", "CLASS", "CLOSE", "CLOSE_BRACKET", "COLON", "COMMA",
		"CONCAT", "CONSTANT", "CONSTRUCTOR", "COUNT", "DELETE", "DESCENDING",
		"DISTINCT", "DIV", "DOT", "ELEMENTS", "ELSE", "EMPTY", "END", "EQ", "ESCAPE",
		"ESCqs", "EXISTS", "EXPONENT", "EXPR_LIST", "FALSE", "FETCH", "FILTER_ENTITY",
		"FLOAT_SUFFIX", "FROM", "FULL", "GE", "GROUP", "GT", "HAVING", "HEX_DIGIT",
		"IDENT", "ID_LETTER", "ID_START_LETTER", "IN", "INDEX_OP", "INDICES",
		"INNER", "INSERT", "INTO", "IN_LIST", "IS", "IS_NOT_NULL", "IS_NULL",
		"JAVA_CONSTANT", "JOIN", "LE", "LEADING", "LEFT", "LIKE", "LITERAL_by",
		"LT", "MAX", "MEMBER", "METHOD_CALL", "MIN", "MINUS", "NE", "NEW", "NOT",
		"NOT_BETWEEN", "NOT_IN", "NOT_LIKE", "NULL", "NUM_DECIMAL", "NUM_DOUBLE",
		"NUM_FLOAT", "NUM_INT", "NUM_LONG", "OBJECT", "OF", "ON", "OPEN", "OPEN_BRACKET",
		"OR", "ORDER", "ORDER_ELEMENT", "OUTER", "PARAM", "PLUS", "PROPERTIES",
		"QUERY", "QUOTED_String", "RANGE", "RIGHT", "ROW_STAR", "SELECT", "SELECT_FROM",
		"SET", "SKIP", "SOME", "SQL_NE", "STAR", "SUM", "TAKE", "THEN", "TRAILING",
		"TRUE", "UNARY_MINUS", "UNARY_PLUS", "UNION", "UPDATE", "VECTOR_EXPR",
		"VERSIONED", "WEIRD_IDENT", "WHEN", "WHERE", "WITH", "WS", "'ascending'",
		"'descending'"
	};
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
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public hqlParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public hqlParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return hqlParser.tokenNames; }
	@Override public String getGrammarFileName() { return "hql.g"; }


	        boolean filter = false;

		void WeakKeywords()
		{
		}

		Token NegateNode( Object node )
		{
			return (Token) node;
		}

		void HandleDotIdent()
		{
		}

		CommonTree ProcessEqualityExpression( CommonTree tree )
		{
			return tree;
		}

		CommonTree HandleIdentifierError( Token token, RecognitionException ex )
		{
			assert false;
			return null;
		}

		private CommonTree ProcessMemberOf( Token n, CommonTree commonTree, CommonTree root_0 )
		{
			// TODO Auto-generated method stub
			return null;
		}


	public static class statement_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "statement"
	// hql.g:160:1: statement : ( updateStatement | deleteStatement | selectStatement | insertStatement ) EOF !;
	public final hqlParser.statement_return statement() throws RecognitionException {
		hqlParser.statement_return retval = new hqlParser.statement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token EOF5=null;
		ParserRuleReturnScope updateStatement1 =null;
		ParserRuleReturnScope deleteStatement2 =null;
		ParserRuleReturnScope selectStatement3 =null;
		ParserRuleReturnScope insertStatement4 =null;

		CommonTree EOF5_tree=null;

		try {
			// hql.g:161:2: ( ( updateStatement | deleteStatement | selectStatement | insertStatement ) EOF !)
			// hql.g:161:4: ( updateStatement | deleteStatement | selectStatement | insertStatement ) EOF !
			{
			root_0 = (CommonTree)adaptor.nil();


			// hql.g:161:4: ( updateStatement | deleteStatement | selectStatement | insertStatement )
			int alt1=4;
			switch ( input.LA(1) ) {
			case UPDATE:
				{
				alt1=1;
				}
				break;
			case DELETE:
				{
				alt1=2;
				}
				break;
			case EOF:
			case CLOSE:
			case FROM:
			case GROUP:
			case HAVING:
			case ORDER:
			case SELECT:
			case SKIP:
			case TAKE:
			case UNION:
			case WHERE:
				{
				alt1=3;
				}
				break;
			case INSERT:
				{
				alt1=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// hql.g:161:6: updateStatement
					{
					pushFollow(FOLLOW_updateStatement_in_statement599);
					updateStatement1=updateStatement();
					state._fsp--;

					adaptor.addChild(root_0, updateStatement1.getTree());

					}
					break;
				case 2 :
					// hql.g:161:24: deleteStatement
					{
					pushFollow(FOLLOW_deleteStatement_in_statement603);
					deleteStatement2=deleteStatement();
					state._fsp--;

					adaptor.addChild(root_0, deleteStatement2.getTree());

					}
					break;
				case 3 :
					// hql.g:161:42: selectStatement
					{
					pushFollow(FOLLOW_selectStatement_in_statement607);
					selectStatement3=selectStatement();
					state._fsp--;

					adaptor.addChild(root_0, selectStatement3.getTree());

					}
					break;
				case 4 :
					// hql.g:161:60: insertStatement
					{
					pushFollow(FOLLOW_insertStatement_in_statement611);
					insertStatement4=insertStatement();
					state._fsp--;

					adaptor.addChild(root_0, insertStatement4.getTree());

					}
					break;

			}

			EOF5=(Token)match(input,EOF,FOLLOW_EOF_in_statement615);
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "statement"


	public static class updateStatement_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "updateStatement"
	// hql.g:164:1: updateStatement : UPDATE ^ ( VERSIONED )? optionalFromTokenFromClause setClause ( whereClause )? ;
	public final hqlParser.updateStatement_return updateStatement() throws RecognitionException {
		hqlParser.updateStatement_return retval = new hqlParser.updateStatement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token UPDATE6=null;
		Token VERSIONED7=null;
		ParserRuleReturnScope optionalFromTokenFromClause8 =null;
		ParserRuleReturnScope setClause9 =null;
		ParserRuleReturnScope whereClause10 =null;

		CommonTree UPDATE6_tree=null;
		CommonTree VERSIONED7_tree=null;

		try {
			// hql.g:165:2: ( UPDATE ^ ( VERSIONED )? optionalFromTokenFromClause setClause ( whereClause )? )
			// hql.g:165:4: UPDATE ^ ( VERSIONED )? optionalFromTokenFromClause setClause ( whereClause )?
			{
			root_0 = (CommonTree)adaptor.nil();


			UPDATE6=(Token)match(input,UPDATE,FOLLOW_UPDATE_in_updateStatement627);
			UPDATE6_tree = (CommonTree)adaptor.create(UPDATE6);
			root_0 = (CommonTree)adaptor.becomeRoot(UPDATE6_tree, root_0);

			// hql.g:165:12: ( VERSIONED )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==VERSIONED) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// hql.g:165:13: VERSIONED
					{
					VERSIONED7=(Token)match(input,VERSIONED,FOLLOW_VERSIONED_in_updateStatement631);
					VERSIONED7_tree = (CommonTree)adaptor.create(VERSIONED7);
					adaptor.addChild(root_0, VERSIONED7_tree);

					}
					break;

			}

			pushFollow(FOLLOW_optionalFromTokenFromClause_in_updateStatement637);
			optionalFromTokenFromClause8=optionalFromTokenFromClause();
			state._fsp--;

			adaptor.addChild(root_0, optionalFromTokenFromClause8.getTree());

			pushFollow(FOLLOW_setClause_in_updateStatement641);
			setClause9=setClause();
			state._fsp--;

			adaptor.addChild(root_0, setClause9.getTree());

			// hql.g:168:3: ( whereClause )?
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==WHERE) ) {
				alt3=1;
			}
			switch (alt3) {
				case 1 :
					// hql.g:168:4: whereClause
					{
					pushFollow(FOLLOW_whereClause_in_updateStatement646);
					whereClause10=whereClause();
					state._fsp--;

					adaptor.addChild(root_0, whereClause10.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "updateStatement"


	public static class setClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "setClause"
	// hql.g:171:1: setClause : ( SET ^ assignment ( COMMA ! assignment )* ) ;
	public final hqlParser.setClause_return setClause() throws RecognitionException {
		hqlParser.setClause_return retval = new hqlParser.setClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token SET11=null;
		Token COMMA13=null;
		ParserRuleReturnScope assignment12 =null;
		ParserRuleReturnScope assignment14 =null;

		CommonTree SET11_tree=null;
		CommonTree COMMA13_tree=null;

		try {
			// hql.g:172:2: ( ( SET ^ assignment ( COMMA ! assignment )* ) )
			// hql.g:172:4: ( SET ^ assignment ( COMMA ! assignment )* )
			{
			root_0 = (CommonTree)adaptor.nil();


			// hql.g:172:4: ( SET ^ assignment ( COMMA ! assignment )* )
			// hql.g:172:5: SET ^ assignment ( COMMA ! assignment )*
			{
			SET11=(Token)match(input,SET,FOLLOW_SET_in_setClause660);
			SET11_tree = (CommonTree)adaptor.create(SET11);
			root_0 = (CommonTree)adaptor.becomeRoot(SET11_tree, root_0);

			pushFollow(FOLLOW_assignment_in_setClause663);
			assignment12=assignment();
			state._fsp--;

			adaptor.addChild(root_0, assignment12.getTree());

			// hql.g:172:21: ( COMMA ! assignment )*
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0==COMMA) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// hql.g:172:22: COMMA ! assignment
					{
					COMMA13=(Token)match(input,COMMA,FOLLOW_COMMA_in_setClause666);
					pushFollow(FOLLOW_assignment_in_setClause669);
					assignment14=assignment();
					state._fsp--;

					adaptor.addChild(root_0, assignment14.getTree());

					}
					break;

				default :
					break loop4;
				}
			}

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "setClause"


	public static class assignment_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "assignment"
	// hql.g:175:1: assignment : stateField EQ ^ newValue ;
	public final hqlParser.assignment_return assignment() throws RecognitionException {
		hqlParser.assignment_return retval = new hqlParser.assignment_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token EQ16=null;
		ParserRuleReturnScope stateField15 =null;
		ParserRuleReturnScope newValue17 =null;

		CommonTree EQ16_tree=null;

		try {
			// hql.g:176:2: ( stateField EQ ^ newValue )
			// hql.g:176:4: stateField EQ ^ newValue
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_stateField_in_assignment683);
			stateField15=stateField();
			state._fsp--;

			adaptor.addChild(root_0, stateField15.getTree());

			EQ16=(Token)match(input,EQ,FOLLOW_EQ_in_assignment685);
			EQ16_tree = (CommonTree)adaptor.create(EQ16);
			root_0 = (CommonTree)adaptor.becomeRoot(EQ16_tree, root_0);

			pushFollow(FOLLOW_newValue_in_assignment688);
			newValue17=newValue();
			state._fsp--;

			adaptor.addChild(root_0, newValue17.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "assignment"


	public static class stateField_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "stateField"
	// hql.g:181:1: stateField : path ;
	public final hqlParser.stateField_return stateField() throws RecognitionException {
		hqlParser.stateField_return retval = new hqlParser.stateField_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope path18 =null;


		try {
			// hql.g:182:2: ( path )
			// hql.g:182:4: path
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_path_in_stateField701);
			path18=path();
			state._fsp--;

			adaptor.addChild(root_0, path18.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "stateField"


	public static class newValue_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "newValue"
	// hql.g:187:1: newValue : concatenation ;
	public final hqlParser.newValue_return newValue() throws RecognitionException {
		hqlParser.newValue_return retval = new hqlParser.newValue_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope concatenation19 =null;


		try {
			// hql.g:188:2: ( concatenation )
			// hql.g:188:4: concatenation
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_concatenation_in_newValue714);
			concatenation19=concatenation();
			state._fsp--;

			adaptor.addChild(root_0, concatenation19.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "newValue"


	public static class deleteStatement_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "deleteStatement"
	// hql.g:191:1: deleteStatement : DELETE ^ ( optionalFromTokenFromClause ) ( whereClause )? ;
	public final hqlParser.deleteStatement_return deleteStatement() throws RecognitionException {
		hqlParser.deleteStatement_return retval = new hqlParser.deleteStatement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token DELETE20=null;
		ParserRuleReturnScope optionalFromTokenFromClause21 =null;
		ParserRuleReturnScope whereClause22 =null;

		CommonTree DELETE20_tree=null;

		try {
			// hql.g:192:2: ( DELETE ^ ( optionalFromTokenFromClause ) ( whereClause )? )
			// hql.g:192:4: DELETE ^ ( optionalFromTokenFromClause ) ( whereClause )?
			{
			root_0 = (CommonTree)adaptor.nil();


			DELETE20=(Token)match(input,DELETE,FOLLOW_DELETE_in_deleteStatement725);
			DELETE20_tree = (CommonTree)adaptor.create(DELETE20);
			root_0 = (CommonTree)adaptor.becomeRoot(DELETE20_tree, root_0);

			// hql.g:193:3: ( optionalFromTokenFromClause )
			// hql.g:193:4: optionalFromTokenFromClause
			{
			pushFollow(FOLLOW_optionalFromTokenFromClause_in_deleteStatement731);
			optionalFromTokenFromClause21=optionalFromTokenFromClause();
			state._fsp--;

			adaptor.addChild(root_0, optionalFromTokenFromClause21.getTree());

			}

			// hql.g:194:3: ( whereClause )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==WHERE) ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// hql.g:194:4: whereClause
					{
					pushFollow(FOLLOW_whereClause_in_deleteStatement737);
					whereClause22=whereClause();
					state._fsp--;

					adaptor.addChild(root_0, whereClause22.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "deleteStatement"


	public static class optionalFromTokenFromClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "optionalFromTokenFromClause"
	// hql.g:199:1: optionalFromTokenFromClause : optionalFromTokenFromClause2 path ( asAlias )? -> ^( FROM ^( RANGE path ( asAlias )? ) ) ;
	public final hqlParser.optionalFromTokenFromClause_return optionalFromTokenFromClause() throws RecognitionException {
		hqlParser.optionalFromTokenFromClause_return retval = new hqlParser.optionalFromTokenFromClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope optionalFromTokenFromClause223 =null;
		ParserRuleReturnScope path24 =null;
		ParserRuleReturnScope asAlias25 =null;

		RewriteRuleSubtreeStream stream_optionalFromTokenFromClause2=new RewriteRuleSubtreeStream(adaptor,"rule optionalFromTokenFromClause2");
		RewriteRuleSubtreeStream stream_path=new RewriteRuleSubtreeStream(adaptor,"rule path");
		RewriteRuleSubtreeStream stream_asAlias=new RewriteRuleSubtreeStream(adaptor,"rule asAlias");

		try {
			// hql.g:200:2: ( optionalFromTokenFromClause2 path ( asAlias )? -> ^( FROM ^( RANGE path ( asAlias )? ) ) )
			// hql.g:200:4: optionalFromTokenFromClause2 path ( asAlias )?
			{
			pushFollow(FOLLOW_optionalFromTokenFromClause2_in_optionalFromTokenFromClause752);
			optionalFromTokenFromClause223=optionalFromTokenFromClause2();
			state._fsp--;

			stream_optionalFromTokenFromClause2.add(optionalFromTokenFromClause223.getTree());
			pushFollow(FOLLOW_path_in_optionalFromTokenFromClause754);
			path24=path();
			state._fsp--;

			stream_path.add(path24.getTree());
			// hql.g:200:38: ( asAlias )?
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==AS||LA6_0==IDENT) ) {
				alt6=1;
			}
			switch (alt6) {
				case 1 :
					// hql.g:200:39: asAlias
					{
					pushFollow(FOLLOW_asAlias_in_optionalFromTokenFromClause757);
					asAlias25=asAlias();
					state._fsp--;

					stream_asAlias.add(asAlias25.getTree());
					}
					break;

			}

			// AST REWRITE
			// elements: asAlias, path
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 201:3: -> ^( FROM ^( RANGE path ( asAlias )? ) )
			{
				// hql.g:201:6: ^( FROM ^( RANGE path ( asAlias )? ) )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(FROM, "FROM"), root_1);
				// hql.g:201:13: ^( RANGE path ( asAlias )? )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(RANGE, "RANGE"), root_2);
				adaptor.addChild(root_2, stream_path.nextTree());
				// hql.g:201:26: ( asAlias )?
				if ( stream_asAlias.hasNext() ) {
					adaptor.addChild(root_2, stream_asAlias.nextTree());
				}
				stream_asAlias.reset();

				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "optionalFromTokenFromClause"


	public static class optionalFromTokenFromClause2_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "optionalFromTokenFromClause2"
	// hql.g:204:1: optionalFromTokenFromClause2 : ( FROM )? ;
	public final hqlParser.optionalFromTokenFromClause2_return optionalFromTokenFromClause2() throws RecognitionException {
		hqlParser.optionalFromTokenFromClause2_return retval = new hqlParser.optionalFromTokenFromClause2_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token FROM26=null;

		CommonTree FROM26_tree=null;

		try {
			// hql.g:205:2: ( ( FROM )? )
			// hql.g:205:4: ( FROM )?
			{
			root_0 = (CommonTree)adaptor.nil();


			// hql.g:205:4: ( FROM )?
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==FROM) ) {
				alt7=1;
			}
			switch (alt7) {
				case 1 :
					// hql.g:205:4: FROM
					{
					FROM26=(Token)match(input,FROM,FOLLOW_FROM_in_optionalFromTokenFromClause2788);
					FROM26_tree = (CommonTree)adaptor.create(FROM26);
					adaptor.addChild(root_0, FROM26_tree);

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "optionalFromTokenFromClause2"


	public static class selectStatement_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "selectStatement"
	// hql.g:208:1: selectStatement : q= queryRule -> ^( QUERY[\"query\"] $q) ;
	public final hqlParser.selectStatement_return selectStatement() throws RecognitionException {
		hqlParser.selectStatement_return retval = new hqlParser.selectStatement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope q =null;

		RewriteRuleSubtreeStream stream_queryRule=new RewriteRuleSubtreeStream(adaptor,"rule queryRule");

		try {
			// hql.g:209:2: (q= queryRule -> ^( QUERY[\"query\"] $q) )
			// hql.g:209:4: q= queryRule
			{
			pushFollow(FOLLOW_queryRule_in_selectStatement802);
			q=queryRule();
			state._fsp--;

			stream_queryRule.add(q.getTree());
			// AST REWRITE
			// elements: q
			// token labels:
			// rule labels: retval, q
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_q=new RewriteRuleSubtreeStream(adaptor,"rule q",q!=null?q.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 210:2: -> ^( QUERY[\"query\"] $q)
			{
				// hql.g:210:5: ^( QUERY[\"query\"] $q)
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(QUERY, "query"), root_1);
				adaptor.addChild(root_1, stream_q.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "selectStatement"


	public static class insertStatement_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insertStatement"
	// hql.g:213:1: insertStatement : INSERT ^ intoClause selectStatement ;
	public final hqlParser.insertStatement_return insertStatement() throws RecognitionException {
		hqlParser.insertStatement_return retval = new hqlParser.insertStatement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSERT27=null;
		ParserRuleReturnScope intoClause28 =null;
		ParserRuleReturnScope selectStatement29 =null;

		CommonTree INSERT27_tree=null;

		try {
			// hql.g:217:2: ( INSERT ^ intoClause selectStatement )
			// hql.g:217:4: INSERT ^ intoClause selectStatement
			{
			root_0 = (CommonTree)adaptor.nil();


			INSERT27=(Token)match(input,INSERT,FOLLOW_INSERT_in_insertStatement831);
			INSERT27_tree = (CommonTree)adaptor.create(INSERT27);
			root_0 = (CommonTree)adaptor.becomeRoot(INSERT27_tree, root_0);

			pushFollow(FOLLOW_intoClause_in_insertStatement834);
			intoClause28=intoClause();
			state._fsp--;

			adaptor.addChild(root_0, intoClause28.getTree());

			pushFollow(FOLLOW_selectStatement_in_insertStatement836);
			selectStatement29=selectStatement();
			state._fsp--;

			adaptor.addChild(root_0, selectStatement29.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insertStatement"


	public static class intoClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "intoClause"
	// hql.g:220:1: intoClause : INTO ^ path insertablePropertySpec ;
	public final hqlParser.intoClause_return intoClause() throws RecognitionException {
		hqlParser.intoClause_return retval = new hqlParser.intoClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INTO30=null;
		ParserRuleReturnScope path31 =null;
		ParserRuleReturnScope insertablePropertySpec32 =null;

		CommonTree INTO30_tree=null;

		try {
			// hql.g:221:2: ( INTO ^ path insertablePropertySpec )
			// hql.g:221:4: INTO ^ path insertablePropertySpec
			{
			root_0 = (CommonTree)adaptor.nil();


			INTO30=(Token)match(input,INTO,FOLLOW_INTO_in_intoClause847);
			INTO30_tree = (CommonTree)adaptor.create(INTO30);
			root_0 = (CommonTree)adaptor.becomeRoot(INTO30_tree, root_0);

			pushFollow(FOLLOW_path_in_intoClause850);
			path31=path();
			state._fsp--;

			adaptor.addChild(root_0, path31.getTree());

			 WeakKeywords();
			pushFollow(FOLLOW_insertablePropertySpec_in_intoClause854);
			insertablePropertySpec32=insertablePropertySpec();
			state._fsp--;

			adaptor.addChild(root_0, insertablePropertySpec32.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "intoClause"


	public static class insertablePropertySpec_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insertablePropertySpec"
	// hql.g:224:1: insertablePropertySpec : OPEN primaryExpression ( COMMA primaryExpression )* CLOSE -> ^( RANGE[\"column-spec\"] ( primaryExpression )* ) ;
	public final hqlParser.insertablePropertySpec_return insertablePropertySpec() throws RecognitionException {
		hqlParser.insertablePropertySpec_return retval = new hqlParser.insertablePropertySpec_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token OPEN33=null;
		Token COMMA35=null;
		Token CLOSE37=null;
		ParserRuleReturnScope primaryExpression34 =null;
		ParserRuleReturnScope primaryExpression36 =null;

		CommonTree OPEN33_tree=null;
		CommonTree COMMA35_tree=null;
		CommonTree CLOSE37_tree=null;
		RewriteRuleTokenStream stream_OPEN=new RewriteRuleTokenStream(adaptor,"token OPEN");
		RewriteRuleTokenStream stream_CLOSE=new RewriteRuleTokenStream(adaptor,"token CLOSE");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleSubtreeStream stream_primaryExpression=new RewriteRuleSubtreeStream(adaptor,"rule primaryExpression");

		try {
			// hql.g:225:2: ( OPEN primaryExpression ( COMMA primaryExpression )* CLOSE -> ^( RANGE[\"column-spec\"] ( primaryExpression )* ) )
			// hql.g:225:4: OPEN primaryExpression ( COMMA primaryExpression )* CLOSE
			{
			OPEN33=(Token)match(input,OPEN,FOLLOW_OPEN_in_insertablePropertySpec865);
			stream_OPEN.add(OPEN33);

			pushFollow(FOLLOW_primaryExpression_in_insertablePropertySpec867);
			primaryExpression34=primaryExpression();
			state._fsp--;

			stream_primaryExpression.add(primaryExpression34.getTree());
			// hql.g:225:27: ( COMMA primaryExpression )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0==COMMA) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// hql.g:225:29: COMMA primaryExpression
					{
					COMMA35=(Token)match(input,COMMA,FOLLOW_COMMA_in_insertablePropertySpec871);
					stream_COMMA.add(COMMA35);

					pushFollow(FOLLOW_primaryExpression_in_insertablePropertySpec873);
					primaryExpression36=primaryExpression();
					state._fsp--;

					stream_primaryExpression.add(primaryExpression36.getTree());
					}
					break;

				default :
					break loop8;
				}
			}

			CLOSE37=(Token)match(input,CLOSE,FOLLOW_CLOSE_in_insertablePropertySpec878);
			stream_CLOSE.add(CLOSE37);

			// AST REWRITE
			// elements: primaryExpression
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 226:3: -> ^( RANGE[\"column-spec\"] ( primaryExpression )* )
			{
				// hql.g:226:6: ^( RANGE[\"column-spec\"] ( primaryExpression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(RANGE, "column-spec"), root_1);
				// hql.g:226:29: ( primaryExpression )*
				while ( stream_primaryExpression.hasNext() ) {
					adaptor.addChild(root_1, stream_primaryExpression.nextTree());
				}
				stream_primaryExpression.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insertablePropertySpec"


	public static class queryRule_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "queryRule"
	// hql.g:232:1: queryRule : selectFrom ( whereClause )? ( groupByClause )? ( havingClause )? ( orderByClause )? ( skipClause )? ( takeClause )? ;
	public final hqlParser.queryRule_return queryRule() throws RecognitionException {
		hqlParser.queryRule_return retval = new hqlParser.queryRule_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope selectFrom38 =null;
		ParserRuleReturnScope whereClause39 =null;
		ParserRuleReturnScope groupByClause40 =null;
		ParserRuleReturnScope havingClause41 =null;
		ParserRuleReturnScope orderByClause42 =null;
		ParserRuleReturnScope skipClause43 =null;
		ParserRuleReturnScope takeClause44 =null;


		try {
			// hql.g:233:2: ( selectFrom ( whereClause )? ( groupByClause )? ( havingClause )? ( orderByClause )? ( skipClause )? ( takeClause )? )
			// hql.g:233:4: selectFrom ( whereClause )? ( groupByClause )? ( havingClause )? ( orderByClause )? ( skipClause )? ( takeClause )?
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_selectFrom_in_queryRule904);
			selectFrom38=selectFrom();
			state._fsp--;

			adaptor.addChild(root_0, selectFrom38.getTree());

			// hql.g:234:3: ( whereClause )?
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==WHERE) ) {
				alt9=1;
			}
			switch (alt9) {
				case 1 :
					// hql.g:234:4: whereClause
					{
					pushFollow(FOLLOW_whereClause_in_queryRule909);
					whereClause39=whereClause();
					state._fsp--;

					adaptor.addChild(root_0, whereClause39.getTree());

					}
					break;

			}

			// hql.g:235:3: ( groupByClause )?
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==GROUP) ) {
				alt10=1;
			}
			switch (alt10) {
				case 1 :
					// hql.g:235:4: groupByClause
					{
					pushFollow(FOLLOW_groupByClause_in_queryRule916);
					groupByClause40=groupByClause();
					state._fsp--;

					adaptor.addChild(root_0, groupByClause40.getTree());

					}
					break;

			}

			// hql.g:236:3: ( havingClause )?
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0==HAVING) ) {
				alt11=1;
			}
			switch (alt11) {
				case 1 :
					// hql.g:236:4: havingClause
					{
					pushFollow(FOLLOW_havingClause_in_queryRule923);
					havingClause41=havingClause();
					state._fsp--;

					adaptor.addChild(root_0, havingClause41.getTree());

					}
					break;

			}

			// hql.g:237:3: ( orderByClause )?
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0==ORDER) ) {
				alt12=1;
			}
			switch (alt12) {
				case 1 :
					// hql.g:237:4: orderByClause
					{
					pushFollow(FOLLOW_orderByClause_in_queryRule930);
					orderByClause42=orderByClause();
					state._fsp--;

					adaptor.addChild(root_0, orderByClause42.getTree());

					}
					break;

			}

			// hql.g:238:3: ( skipClause )?
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==SKIP) ) {
				alt13=1;
			}
			switch (alt13) {
				case 1 :
					// hql.g:238:4: skipClause
					{
					pushFollow(FOLLOW_skipClause_in_queryRule937);
					skipClause43=skipClause();
					state._fsp--;

					adaptor.addChild(root_0, skipClause43.getTree());

					}
					break;

			}

			// hql.g:239:3: ( takeClause )?
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==TAKE) ) {
				alt14=1;
			}
			switch (alt14) {
				case 1 :
					// hql.g:239:4: takeClause
					{
					pushFollow(FOLLOW_takeClause_in_queryRule944);
					takeClause44=takeClause();
					state._fsp--;

					adaptor.addChild(root_0, takeClause44.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "queryRule"


	public static class selectFrom_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "selectFrom"
	// hql.g:242:1: selectFrom : (s= selectClause )? (f= fromClause )? -> {$f.tree == null && filter}? ^( SELECT_FROM FROM[\"{filter-implied FROM}\"] ( selectClause )? ) -> ^( SELECT_FROM ( fromClause )? ( selectClause )? ) ;
	public final hqlParser.selectFrom_return selectFrom() throws RecognitionException {
		hqlParser.selectFrom_return retval = new hqlParser.selectFrom_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope s =null;
		ParserRuleReturnScope f =null;

		RewriteRuleSubtreeStream stream_selectClause=new RewriteRuleSubtreeStream(adaptor,"rule selectClause");
		RewriteRuleSubtreeStream stream_fromClause=new RewriteRuleSubtreeStream(adaptor,"rule fromClause");

		try {
			// hql.g:243:2: ( (s= selectClause )? (f= fromClause )? -> {$f.tree == null && filter}? ^( SELECT_FROM FROM[\"{filter-implied FROM}\"] ( selectClause )? ) -> ^( SELECT_FROM ( fromClause )? ( selectClause )? ) )
			// hql.g:243:5: (s= selectClause )? (f= fromClause )?
			{
			// hql.g:243:5: (s= selectClause )?
			int alt15=2;
			int LA15_0 = input.LA(1);
			if ( (LA15_0==SELECT) ) {
				alt15=1;
			}
			switch (alt15) {
				case 1 :
					// hql.g:243:6: s= selectClause
					{
					pushFollow(FOLLOW_selectClause_in_selectFrom962);
					s=selectClause();
					state._fsp--;

					stream_selectClause.add(s.getTree());
					}
					break;

			}

			// hql.g:243:23: (f= fromClause )?
			int alt16=2;
			int LA16_0 = input.LA(1);
			if ( (LA16_0==FROM) ) {
				alt16=1;
			}
			switch (alt16) {
				case 1 :
					// hql.g:243:24: f= fromClause
					{
					pushFollow(FOLLOW_fromClause_in_selectFrom969);
					f=fromClause();
					state._fsp--;

					stream_fromClause.add(f.getTree());
					}
					break;

			}


						if ((f!=null?((CommonTree)f.getTree()):null) == null && !filter)
			                        {
			                                assert false : "FROM expected (non-filter queries must contain a FROM clause)";
			                		throw new RecognitionException();//("FROM expected (non-filter queries must contain a FROM clause)");
			                        }

			// AST REWRITE
			// elements: selectClause, selectClause, fromClause
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 251:3: -> {$f.tree == null && filter}? ^( SELECT_FROM FROM[\"{filter-implied FROM}\"] ( selectClause )? )
			if ((f!=null?((CommonTree)f.getTree()):null) == null && filter) {
				// hql.g:251:35: ^( SELECT_FROM FROM[\"{filter-implied FROM}\"] ( selectClause )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(SELECT_FROM, "SELECT_FROM"), root_1);
				adaptor.addChild(root_1, adaptor.create(FROM, "{filter-implied FROM}"));
				// hql.g:251:79: ( selectClause )?
				if ( stream_selectClause.hasNext() ) {
					adaptor.addChild(root_1, stream_selectClause.nextTree());
				}
				stream_selectClause.reset();

				adaptor.addChild(root_0, root_1);
				}

			}

			else // 252:3: -> ^( SELECT_FROM ( fromClause )? ( selectClause )? )
			{
				// hql.g:252:6: ^( SELECT_FROM ( fromClause )? ( selectClause )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(SELECT_FROM, "SELECT_FROM"), root_1);
				// hql.g:252:20: ( fromClause )?
				if ( stream_fromClause.hasNext() ) {
					adaptor.addChild(root_1, stream_fromClause.nextTree());
				}
				stream_fromClause.reset();

				// hql.g:252:32: ( selectClause )?
				if ( stream_selectClause.hasNext() ) {
					adaptor.addChild(root_1, stream_selectClause.nextTree());
				}
				stream_selectClause.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "selectFrom"


	public static class selectClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "selectClause"
	// hql.g:256:1: selectClause : SELECT ^ ( DISTINCT )? ( selectedPropertiesList | newExpression | selectObject ) ;
	public final hqlParser.selectClause_return selectClause() throws RecognitionException {
		hqlParser.selectClause_return retval = new hqlParser.selectClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token SELECT45=null;
		Token DISTINCT46=null;
		ParserRuleReturnScope selectedPropertiesList47 =null;
		ParserRuleReturnScope newExpression48 =null;
		ParserRuleReturnScope selectObject49 =null;

		CommonTree SELECT45_tree=null;
		CommonTree DISTINCT46_tree=null;

		try {
			// hql.g:257:2: ( SELECT ^ ( DISTINCT )? ( selectedPropertiesList | newExpression | selectObject ) )
			// hql.g:257:4: SELECT ^ ( DISTINCT )? ( selectedPropertiesList | newExpression | selectObject )
			{
			root_0 = (CommonTree)adaptor.nil();


			SELECT45=(Token)match(input,SELECT,FOLLOW_SELECT_in_selectClause1018);
			SELECT45_tree = (CommonTree)adaptor.create(SELECT45);
			root_0 = (CommonTree)adaptor.becomeRoot(SELECT45_tree, root_0);

			 WeakKeywords();
			// hql.g:259:3: ( DISTINCT )?
			int alt17=2;
			int LA17_0 = input.LA(1);
			if ( (LA17_0==DISTINCT) ) {
				alt17=1;
			}
			switch (alt17) {
				case 1 :
					// hql.g:259:4: DISTINCT
					{
					DISTINCT46=(Token)match(input,DISTINCT,FOLLOW_DISTINCT_in_selectClause1030);
					DISTINCT46_tree = (CommonTree)adaptor.create(DISTINCT46);
					adaptor.addChild(root_0, DISTINCT46_tree);

					}
					break;

			}

			// hql.g:259:15: ( selectedPropertiesList | newExpression | selectObject )
			int alt18=3;
			switch ( input.LA(1) ) {
			case ALL:
			case ANY:
			case AVG:
			case BNOT:
			case CASE:
			case COLON:
			case COUNT:
			case ELEMENTS:
			case EMPTY:
			case EXISTS:
			case FALSE:
			case IDENT:
			case INDICES:
			case MAX:
			case MIN:
			case MINUS:
			case NOT:
			case NULL:
			case NUM_DECIMAL:
			case NUM_DOUBLE:
			case NUM_FLOAT:
			case NUM_INT:
			case NUM_LONG:
			case OPEN:
			case PARAM:
			case PLUS:
			case QUOTED_String:
			case SOME:
			case SUM:
			case TRUE:
				{
				alt18=1;
				}
				break;
			case NEW:
				{
				alt18=2;
				}
				break;
			case OBJECT:
				{
				alt18=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 18, 0, input);
				throw nvae;
			}
			switch (alt18) {
				case 1 :
					// hql.g:259:17: selectedPropertiesList
					{
					pushFollow(FOLLOW_selectedPropertiesList_in_selectClause1036);
					selectedPropertiesList47=selectedPropertiesList();
					state._fsp--;

					adaptor.addChild(root_0, selectedPropertiesList47.getTree());

					}
					break;
				case 2 :
					// hql.g:259:42: newExpression
					{
					pushFollow(FOLLOW_newExpression_in_selectClause1040);
					newExpression48=newExpression();
					state._fsp--;

					adaptor.addChild(root_0, newExpression48.getTree());

					}
					break;
				case 3 :
					// hql.g:259:58: selectObject
					{
					pushFollow(FOLLOW_selectObject_in_selectClause1044);
					selectObject49=selectObject();
					state._fsp--;

					adaptor.addChild(root_0, selectObject49.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "selectClause"


	public static class newExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "newExpression"
	// hql.g:262:1: newExpression : ( NEW path ) op= OPEN selectedPropertiesList CLOSE -> ^( CONSTRUCTOR[$op] path selectedPropertiesList ) ;
	public final hqlParser.newExpression_return newExpression() throws RecognitionException {
		hqlParser.newExpression_return retval = new hqlParser.newExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token op=null;
		Token NEW50=null;
		Token CLOSE53=null;
		ParserRuleReturnScope path51 =null;
		ParserRuleReturnScope selectedPropertiesList52 =null;

		CommonTree op_tree=null;
		CommonTree NEW50_tree=null;
		CommonTree CLOSE53_tree=null;
		RewriteRuleTokenStream stream_NEW=new RewriteRuleTokenStream(adaptor,"token NEW");
		RewriteRuleTokenStream stream_OPEN=new RewriteRuleTokenStream(adaptor,"token OPEN");
		RewriteRuleTokenStream stream_CLOSE=new RewriteRuleTokenStream(adaptor,"token CLOSE");
		RewriteRuleSubtreeStream stream_path=new RewriteRuleSubtreeStream(adaptor,"rule path");
		RewriteRuleSubtreeStream stream_selectedPropertiesList=new RewriteRuleSubtreeStream(adaptor,"rule selectedPropertiesList");

		try {
			// hql.g:263:2: ( ( NEW path ) op= OPEN selectedPropertiesList CLOSE -> ^( CONSTRUCTOR[$op] path selectedPropertiesList ) )
			// hql.g:263:4: ( NEW path ) op= OPEN selectedPropertiesList CLOSE
			{
			// hql.g:263:4: ( NEW path )
			// hql.g:263:5: NEW path
			{
			NEW50=(Token)match(input,NEW,FOLLOW_NEW_in_newExpression1058);
			stream_NEW.add(NEW50);

			pushFollow(FOLLOW_path_in_newExpression1060);
			path51=path();
			state._fsp--;

			stream_path.add(path51.getTree());
			}

			op=(Token)match(input,OPEN,FOLLOW_OPEN_in_newExpression1065);
			stream_OPEN.add(op);

			pushFollow(FOLLOW_selectedPropertiesList_in_newExpression1067);
			selectedPropertiesList52=selectedPropertiesList();
			state._fsp--;

			stream_selectedPropertiesList.add(selectedPropertiesList52.getTree());
			CLOSE53=(Token)match(input,CLOSE,FOLLOW_CLOSE_in_newExpression1069);
			stream_CLOSE.add(CLOSE53);

			// AST REWRITE
			// elements: selectedPropertiesList, path
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 264:3: -> ^( CONSTRUCTOR[$op] path selectedPropertiesList )
			{
				// hql.g:264:6: ^( CONSTRUCTOR[$op] path selectedPropertiesList )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(CONSTRUCTOR, op), root_1);
				adaptor.addChild(root_1, stream_path.nextTree());
				adaptor.addChild(root_1, stream_selectedPropertiesList.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "newExpression"


	public static class selectObject_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "selectObject"
	// hql.g:267:1: selectObject : OBJECT ^ OPEN ! identifier CLOSE !;
	public final hqlParser.selectObject_return selectObject() throws RecognitionException {
		hqlParser.selectObject_return retval = new hqlParser.selectObject_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token OBJECT54=null;
		Token OPEN55=null;
		Token CLOSE57=null;
		ParserRuleReturnScope identifier56 =null;

		CommonTree OBJECT54_tree=null;
		CommonTree OPEN55_tree=null;
		CommonTree CLOSE57_tree=null;

		try {
			// hql.g:268:4: ( OBJECT ^ OPEN ! identifier CLOSE !)
			// hql.g:268:6: OBJECT ^ OPEN ! identifier CLOSE !
			{
			root_0 = (CommonTree)adaptor.nil();


			OBJECT54=(Token)match(input,OBJECT,FOLLOW_OBJECT_in_selectObject1095);
			OBJECT54_tree = (CommonTree)adaptor.create(OBJECT54);
			root_0 = (CommonTree)adaptor.becomeRoot(OBJECT54_tree, root_0);

			OPEN55=(Token)match(input,OPEN,FOLLOW_OPEN_in_selectObject1098);
			pushFollow(FOLLOW_identifier_in_selectObject1101);
			identifier56=identifier();
			state._fsp--;

			adaptor.addChild(root_0, identifier56.getTree());

			CLOSE57=(Token)match(input,CLOSE,FOLLOW_CLOSE_in_selectObject1103);
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "selectObject"


	public static class fromClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "fromClause"
	// hql.g:275:1: fromClause : FROM ^ fromRange ( fromJoin | COMMA ! fromRange )* ;
	public final hqlParser.fromClause_return fromClause() throws RecognitionException {
		hqlParser.fromClause_return retval = new hqlParser.fromClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token FROM58=null;
		Token COMMA61=null;
		ParserRuleReturnScope fromRange59 =null;
		ParserRuleReturnScope fromJoin60 =null;
		ParserRuleReturnScope fromRange62 =null;

		CommonTree FROM58_tree=null;
		CommonTree COMMA61_tree=null;

		try {
			// hql.g:276:2: ( FROM ^ fromRange ( fromJoin | COMMA ! fromRange )* )
			// hql.g:276:4: FROM ^ fromRange ( fromJoin | COMMA ! fromRange )*
			{
			root_0 = (CommonTree)adaptor.nil();


			FROM58=(Token)match(input,FROM,FOLLOW_FROM_in_fromClause1121);
			FROM58_tree = (CommonTree)adaptor.create(FROM58);
			root_0 = (CommonTree)adaptor.becomeRoot(FROM58_tree, root_0);

			 WeakKeywords();
			pushFollow(FOLLOW_fromRange_in_fromClause1126);
			fromRange59=fromRange();
			state._fsp--;

			adaptor.addChild(root_0, fromRange59.getTree());

			// hql.g:276:40: ( fromJoin | COMMA ! fromRange )*
			loop19:
			while (true) {
				int alt19=3;
				int LA19_0 = input.LA(1);
				if ( (LA19_0==FULL||LA19_0==INNER||LA19_0==JOIN||LA19_0==LEFT||LA19_0==RIGHT) ) {
					alt19=1;
				}
				else if ( (LA19_0==COMMA) ) {
					alt19=2;
				}

				switch (alt19) {
				case 1 :
					// hql.g:276:42: fromJoin
					{
					pushFollow(FOLLOW_fromJoin_in_fromClause1130);
					fromJoin60=fromJoin();
					state._fsp--;

					adaptor.addChild(root_0, fromJoin60.getTree());

					}
					break;
				case 2 :
					// hql.g:276:53: COMMA ! fromRange
					{
					COMMA61=(Token)match(input,COMMA,FOLLOW_COMMA_in_fromClause1134);
					 WeakKeywords();
					pushFollow(FOLLOW_fromRange_in_fromClause1139);
					fromRange62=fromRange();
					state._fsp--;

					adaptor.addChild(root_0, fromRange62.getTree());

					}
					break;

				default :
					break loop19;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "fromClause"


	public static class fromJoin_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "fromJoin"
	// hql.g:279:1: fromJoin : ( ( ( ( LEFT | RIGHT ) ( OUTER )? ) | FULL | INNER )? JOIN ^ ( FETCH )? path ( asAlias )? ( propertyFetch )? ( withClause )? | ( ( ( LEFT | RIGHT ) ( OUTER )? ) | FULL | INNER )? JOIN ^ ( FETCH )? ELEMENTS ! OPEN ! path CLOSE ! ( asAlias )? ( propertyFetch )? ( withClause )? );
	public final hqlParser.fromJoin_return fromJoin() throws RecognitionException {
		hqlParser.fromJoin_return retval = new hqlParser.fromJoin_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token set63=null;
		Token OUTER64=null;
		Token FULL65=null;
		Token INNER66=null;
		Token JOIN67=null;
		Token FETCH68=null;
		Token set73=null;
		Token OUTER74=null;
		Token FULL75=null;
		Token INNER76=null;
		Token JOIN77=null;
		Token FETCH78=null;
		Token ELEMENTS79=null;
		Token OPEN80=null;
		Token CLOSE82=null;
		ParserRuleReturnScope path69 =null;
		ParserRuleReturnScope asAlias70 =null;
		ParserRuleReturnScope propertyFetch71 =null;
		ParserRuleReturnScope withClause72 =null;
		ParserRuleReturnScope path81 =null;
		ParserRuleReturnScope asAlias83 =null;
		ParserRuleReturnScope propertyFetch84 =null;
		ParserRuleReturnScope withClause85 =null;

		CommonTree set63_tree=null;
		CommonTree OUTER64_tree=null;
		CommonTree FULL65_tree=null;
		CommonTree INNER66_tree=null;
		CommonTree JOIN67_tree=null;
		CommonTree FETCH68_tree=null;
		CommonTree set73_tree=null;
		CommonTree OUTER74_tree=null;
		CommonTree FULL75_tree=null;
		CommonTree INNER76_tree=null;
		CommonTree JOIN77_tree=null;
		CommonTree FETCH78_tree=null;
		CommonTree ELEMENTS79_tree=null;
		CommonTree OPEN80_tree=null;
		CommonTree CLOSE82_tree=null;

		try {
			// hql.g:280:2: ( ( ( ( LEFT | RIGHT ) ( OUTER )? ) | FULL | INNER )? JOIN ^ ( FETCH )? path ( asAlias )? ( propertyFetch )? ( withClause )? | ( ( ( LEFT | RIGHT ) ( OUTER )? ) | FULL | INNER )? JOIN ^ ( FETCH )? ELEMENTS ! OPEN ! path CLOSE ! ( asAlias )? ( propertyFetch )? ( withClause )? )
			int alt32=2;
			switch ( input.LA(1) ) {
			case LEFT:
			case RIGHT:
				{
				int LA32_1 = input.LA(2);
				if ( (LA32_1==OUTER) ) {
					int LA32_5 = input.LA(3);
					if ( (LA32_5==JOIN) ) {
						switch ( input.LA(4) ) {
						case FETCH:
							{
							int LA32_6 = input.LA(5);
							if ( (LA32_6==IDENT) ) {
								alt32=1;
							}
							else if ( (LA32_6==ELEMENTS) ) {
								alt32=2;
							}

							else {
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 32, 6, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

							}
							break;
						case IDENT:
							{
							alt32=1;
							}
							break;
						case ELEMENTS:
							{
							alt32=2;
							}
							break;
						default:
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 32, 4, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 32, 5, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}
				else if ( (LA32_1==JOIN) ) {
					switch ( input.LA(3) ) {
					case FETCH:
						{
						int LA32_6 = input.LA(4);
						if ( (LA32_6==IDENT) ) {
							alt32=1;
						}
						else if ( (LA32_6==ELEMENTS) ) {
							alt32=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 32, 6, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case IDENT:
						{
						alt32=1;
						}
						break;
					case ELEMENTS:
						{
						alt32=2;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 32, 4, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 32, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case FULL:
				{
				int LA32_2 = input.LA(2);
				if ( (LA32_2==JOIN) ) {
					switch ( input.LA(3) ) {
					case FETCH:
						{
						int LA32_6 = input.LA(4);
						if ( (LA32_6==IDENT) ) {
							alt32=1;
						}
						else if ( (LA32_6==ELEMENTS) ) {
							alt32=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 32, 6, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case IDENT:
						{
						alt32=1;
						}
						break;
					case ELEMENTS:
						{
						alt32=2;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 32, 4, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 32, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case INNER:
				{
				int LA32_3 = input.LA(2);
				if ( (LA32_3==JOIN) ) {
					switch ( input.LA(3) ) {
					case FETCH:
						{
						int LA32_6 = input.LA(4);
						if ( (LA32_6==IDENT) ) {
							alt32=1;
						}
						else if ( (LA32_6==ELEMENTS) ) {
							alt32=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 32, 6, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case IDENT:
						{
						alt32=1;
						}
						break;
					case ELEMENTS:
						{
						alt32=2;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 32, 4, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 32, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case JOIN:
				{
				switch ( input.LA(2) ) {
				case FETCH:
					{
					int LA32_6 = input.LA(3);
					if ( (LA32_6==IDENT) ) {
						alt32=1;
					}
					else if ( (LA32_6==ELEMENTS) ) {
						alt32=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 32, 6, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

					}
					break;
				case IDENT:
					{
					alt32=1;
					}
					break;
				case ELEMENTS:
					{
					alt32=2;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 32, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 32, 0, input);
				throw nvae;
			}
			switch (alt32) {
				case 1 :
					// hql.g:280:4: ( ( ( LEFT | RIGHT ) ( OUTER )? ) | FULL | INNER )? JOIN ^ ( FETCH )? path ( asAlias )? ( propertyFetch )? ( withClause )?
					{
					root_0 = (CommonTree)adaptor.nil();


					// hql.g:280:4: ( ( ( LEFT | RIGHT ) ( OUTER )? ) | FULL | INNER )?
					int alt21=4;
					switch ( input.LA(1) ) {
						case LEFT:
						case RIGHT:
							{
							alt21=1;
							}
							break;
						case FULL:
							{
							alt21=2;
							}
							break;
						case INNER:
							{
							alt21=3;
							}
							break;
					}
					switch (alt21) {
						case 1 :
							// hql.g:280:6: ( ( LEFT | RIGHT ) ( OUTER )? )
							{
							// hql.g:280:6: ( ( LEFT | RIGHT ) ( OUTER )? )
							// hql.g:280:8: ( LEFT | RIGHT ) ( OUTER )?
							{
							set63=input.LT(1);
							if ( input.LA(1)==LEFT||input.LA(1)==RIGHT ) {
								input.consume();
								adaptor.addChild(root_0, adaptor.create(set63));
								state.errorRecovery=false;
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								throw mse;
							}
							// hql.g:280:25: ( OUTER )?
							int alt20=2;
							int LA20_0 = input.LA(1);
							if ( (LA20_0==OUTER) ) {
								alt20=1;
							}
							switch (alt20) {
								case 1 :
									// hql.g:280:26: OUTER
									{
									OUTER64=(Token)match(input,OUTER,FOLLOW_OUTER_in_fromJoin1168);
									OUTER64_tree = (CommonTree)adaptor.create(OUTER64);
									adaptor.addChild(root_0, OUTER64_tree);

									}
									break;

							}

							}

							}
							break;
						case 2 :
							// hql.g:280:38: FULL
							{
							FULL65=(Token)match(input,FULL,FOLLOW_FULL_in_fromJoin1176);
							FULL65_tree = (CommonTree)adaptor.create(FULL65);
							adaptor.addChild(root_0, FULL65_tree);

							}
							break;
						case 3 :
							// hql.g:280:45: INNER
							{
							INNER66=(Token)match(input,INNER,FOLLOW_INNER_in_fromJoin1180);
							INNER66_tree = (CommonTree)adaptor.create(INNER66);
							adaptor.addChild(root_0, INNER66_tree);

							}
							break;

					}

					JOIN67=(Token)match(input,JOIN,FOLLOW_JOIN_in_fromJoin1185);
					JOIN67_tree = (CommonTree)adaptor.create(JOIN67);
					root_0 = (CommonTree)adaptor.becomeRoot(JOIN67_tree, root_0);

					// hql.g:280:60: ( FETCH )?
					int alt22=2;
					int LA22_0 = input.LA(1);
					if ( (LA22_0==FETCH) ) {
						alt22=1;
					}
					switch (alt22) {
						case 1 :
							// hql.g:280:61: FETCH
							{
							FETCH68=(Token)match(input,FETCH,FOLLOW_FETCH_in_fromJoin1189);
							FETCH68_tree = (CommonTree)adaptor.create(FETCH68);
							adaptor.addChild(root_0, FETCH68_tree);

							}
							break;

					}

					pushFollow(FOLLOW_path_in_fromJoin1193);
					path69=path();
					state._fsp--;

					adaptor.addChild(root_0, path69.getTree());

					// hql.g:280:74: ( asAlias )?
					int alt23=2;
					int LA23_0 = input.LA(1);
					if ( (LA23_0==AS||LA23_0==IDENT) ) {
						alt23=1;
					}
					switch (alt23) {
						case 1 :
							// hql.g:280:75: asAlias
							{
							pushFollow(FOLLOW_asAlias_in_fromJoin1196);
							asAlias70=asAlias();
							state._fsp--;

							adaptor.addChild(root_0, asAlias70.getTree());

							}
							break;

					}

					// hql.g:280:85: ( propertyFetch )?
					int alt24=2;
					int LA24_0 = input.LA(1);
					if ( (LA24_0==FETCH) ) {
						alt24=1;
					}
					switch (alt24) {
						case 1 :
							// hql.g:280:86: propertyFetch
							{
							pushFollow(FOLLOW_propertyFetch_in_fromJoin1201);
							propertyFetch71=propertyFetch();
							state._fsp--;

							adaptor.addChild(root_0, propertyFetch71.getTree());

							}
							break;

					}

					// hql.g:280:102: ( withClause )?
					int alt25=2;
					int LA25_0 = input.LA(1);
					if ( (LA25_0==WITH) ) {
						alt25=1;
					}
					switch (alt25) {
						case 1 :
							// hql.g:280:103: withClause
							{
							pushFollow(FOLLOW_withClause_in_fromJoin1206);
							withClause72=withClause();
							state._fsp--;

							adaptor.addChild(root_0, withClause72.getTree());

							}
							break;

					}

					}
					break;
				case 2 :
					// hql.g:281:4: ( ( ( LEFT | RIGHT ) ( OUTER )? ) | FULL | INNER )? JOIN ^ ( FETCH )? ELEMENTS ! OPEN ! path CLOSE ! ( asAlias )? ( propertyFetch )? ( withClause )?
					{
					root_0 = (CommonTree)adaptor.nil();


					// hql.g:281:4: ( ( ( LEFT | RIGHT ) ( OUTER )? ) | FULL | INNER )?
					int alt27=4;
					switch ( input.LA(1) ) {
						case LEFT:
						case RIGHT:
							{
							alt27=1;
							}
							break;
						case FULL:
							{
							alt27=2;
							}
							break;
						case INNER:
							{
							alt27=3;
							}
							break;
					}
					switch (alt27) {
						case 1 :
							// hql.g:281:6: ( ( LEFT | RIGHT ) ( OUTER )? )
							{
							// hql.g:281:6: ( ( LEFT | RIGHT ) ( OUTER )? )
							// hql.g:281:8: ( LEFT | RIGHT ) ( OUTER )?
							{
							set73=input.LT(1);
							if ( input.LA(1)==LEFT||input.LA(1)==RIGHT ) {
								input.consume();
								adaptor.addChild(root_0, adaptor.create(set73));
								state.errorRecovery=false;
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								throw mse;
							}
							// hql.g:281:25: ( OUTER )?
							int alt26=2;
							int LA26_0 = input.LA(1);
							if ( (LA26_0==OUTER) ) {
								alt26=1;
							}
							switch (alt26) {
								case 1 :
									// hql.g:281:26: OUTER
									{
									OUTER74=(Token)match(input,OUTER,FOLLOW_OUTER_in_fromJoin1228);
									OUTER74_tree = (CommonTree)adaptor.create(OUTER74);
									adaptor.addChild(root_0, OUTER74_tree);

									}
									break;

							}

							}

							}
							break;
						case 2 :
							// hql.g:281:38: FULL
							{
							FULL75=(Token)match(input,FULL,FOLLOW_FULL_in_fromJoin1236);
							FULL75_tree = (CommonTree)adaptor.create(FULL75);
							adaptor.addChild(root_0, FULL75_tree);

							}
							break;
						case 3 :
							// hql.g:281:45: INNER
							{
							INNER76=(Token)match(input,INNER,FOLLOW_INNER_in_fromJoin1240);
							INNER76_tree = (CommonTree)adaptor.create(INNER76);
							adaptor.addChild(root_0, INNER76_tree);

							}
							break;

					}

					JOIN77=(Token)match(input,JOIN,FOLLOW_JOIN_in_fromJoin1245);
					JOIN77_tree = (CommonTree)adaptor.create(JOIN77);
					root_0 = (CommonTree)adaptor.becomeRoot(JOIN77_tree, root_0);

					// hql.g:281:60: ( FETCH )?
					int alt28=2;
					int LA28_0 = input.LA(1);
					if ( (LA28_0==FETCH) ) {
						alt28=1;
					}
					switch (alt28) {
						case 1 :
							// hql.g:281:61: FETCH
							{
							FETCH78=(Token)match(input,FETCH,FOLLOW_FETCH_in_fromJoin1249);
							FETCH78_tree = (CommonTree)adaptor.create(FETCH78);
							adaptor.addChild(root_0, FETCH78_tree);

							}
							break;

					}

					ELEMENTS79=(Token)match(input,ELEMENTS,FOLLOW_ELEMENTS_in_fromJoin1253);
					OPEN80=(Token)match(input,OPEN,FOLLOW_OPEN_in_fromJoin1256);
					pushFollow(FOLLOW_path_in_fromJoin1259);
					path81=path();
					state._fsp--;

					adaptor.addChild(root_0, path81.getTree());

					CLOSE82=(Token)match(input,CLOSE,FOLLOW_CLOSE_in_fromJoin1261);
					// hql.g:281:97: ( asAlias )?
					int alt29=2;
					int LA29_0 = input.LA(1);
					if ( (LA29_0==AS||LA29_0==IDENT) ) {
						alt29=1;
					}
					switch (alt29) {
						case 1 :
							// hql.g:281:98: asAlias
							{
							pushFollow(FOLLOW_asAlias_in_fromJoin1265);
							asAlias83=asAlias();
							state._fsp--;

							adaptor.addChild(root_0, asAlias83.getTree());

							}
							break;

					}

					// hql.g:281:108: ( propertyFetch )?
					int alt30=2;
					int LA30_0 = input.LA(1);
					if ( (LA30_0==FETCH) ) {
						alt30=1;
					}
					switch (alt30) {
						case 1 :
							// hql.g:281:109: propertyFetch
							{
							pushFollow(FOLLOW_propertyFetch_in_fromJoin1270);
							propertyFetch84=propertyFetch();
							state._fsp--;

							adaptor.addChild(root_0, propertyFetch84.getTree());

							}
							break;

					}

					// hql.g:281:125: ( withClause )?
					int alt31=2;
					int LA31_0 = input.LA(1);
					if ( (LA31_0==WITH) ) {
						alt31=1;
					}
					switch (alt31) {
						case 1 :
							// hql.g:281:126: withClause
							{
							pushFollow(FOLLOW_withClause_in_fromJoin1275);
							withClause85=withClause();
							state._fsp--;

							adaptor.addChild(root_0, withClause85.getTree());

							}
							break;

					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "fromJoin"


	public static class withClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "withClause"
	// hql.g:284:1: withClause : WITH ^ logicalExpression ;
	public final hqlParser.withClause_return withClause() throws RecognitionException {
		hqlParser.withClause_return retval = new hqlParser.withClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WITH86=null;
		ParserRuleReturnScope logicalExpression87 =null;

		CommonTree WITH86_tree=null;

		try {
			// hql.g:285:2: ( WITH ^ logicalExpression )
			// hql.g:285:4: WITH ^ logicalExpression
			{
			root_0 = (CommonTree)adaptor.nil();


			WITH86=(Token)match(input,WITH,FOLLOW_WITH_in_withClause1288);
			WITH86_tree = (CommonTree)adaptor.create(WITH86);
			root_0 = (CommonTree)adaptor.becomeRoot(WITH86_tree, root_0);

			pushFollow(FOLLOW_logicalExpression_in_withClause1291);
			logicalExpression87=logicalExpression();
			state._fsp--;

			adaptor.addChild(root_0, logicalExpression87.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "withClause"


	public static class fromRange_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "fromRange"
	// hql.g:288:1: fromRange : ( fromClassOrOuterQueryPath | inClassDeclaration | inCollectionDeclaration | inCollectionElementsDeclaration );
	public final hqlParser.fromRange_return fromRange() throws RecognitionException {
		hqlParser.fromRange_return retval = new hqlParser.fromRange_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope fromClassOrOuterQueryPath88 =null;
		ParserRuleReturnScope inClassDeclaration89 =null;
		ParserRuleReturnScope inCollectionDeclaration90 =null;
		ParserRuleReturnScope inCollectionElementsDeclaration91 =null;


		try {
			// hql.g:289:2: ( fromClassOrOuterQueryPath | inClassDeclaration | inCollectionDeclaration | inCollectionElementsDeclaration )
			int alt33=4;
			switch ( input.LA(1) ) {
			case IDENT:
				{
				int LA33_1 = input.LA(2);
				if ( (LA33_1==EOF||LA33_1==AS||LA33_1==CLOSE||LA33_1==COMMA||LA33_1==DOT||LA33_1==FETCH||LA33_1==FULL||LA33_1==GROUP||LA33_1==HAVING||LA33_1==IDENT||LA33_1==INNER||LA33_1==JOIN||LA33_1==LEFT||LA33_1==ORDER||LA33_1==RIGHT||LA33_1==SKIP||LA33_1==TAKE||LA33_1==UNION||LA33_1==WHERE) ) {
					alt33=1;
				}
				else if ( (LA33_1==IN) ) {
					int LA33_5 = input.LA(3);
					if ( (LA33_5==ELEMENTS) ) {
						alt33=4;
					}
					else if ( (LA33_5==CLASS||LA33_5==IDENT) ) {
						alt33=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 33, 5, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 33, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case IN:
				{
				alt33=3;
				}
				break;
			case ELEMENTS:
				{
				alt33=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 33, 0, input);
				throw nvae;
			}
			switch (alt33) {
				case 1 :
					// hql.g:289:4: fromClassOrOuterQueryPath
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_fromClassOrOuterQueryPath_in_fromRange1302);
					fromClassOrOuterQueryPath88=fromClassOrOuterQueryPath();
					state._fsp--;

					adaptor.addChild(root_0, fromClassOrOuterQueryPath88.getTree());

					}
					break;
				case 2 :
					// hql.g:290:4: inClassDeclaration
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_inClassDeclaration_in_fromRange1307);
					inClassDeclaration89=inClassDeclaration();
					state._fsp--;

					adaptor.addChild(root_0, inClassDeclaration89.getTree());

					}
					break;
				case 3 :
					// hql.g:291:4: inCollectionDeclaration
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_inCollectionDeclaration_in_fromRange1312);
					inCollectionDeclaration90=inCollectionDeclaration();
					state._fsp--;

					adaptor.addChild(root_0, inCollectionDeclaration90.getTree());

					}
					break;
				case 4 :
					// hql.g:292:4: inCollectionElementsDeclaration
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_inCollectionElementsDeclaration_in_fromRange1317);
					inCollectionElementsDeclaration91=inCollectionElementsDeclaration();
					state._fsp--;

					adaptor.addChild(root_0, inCollectionElementsDeclaration91.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "fromRange"


	public static class fromClassOrOuterQueryPath_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "fromClassOrOuterQueryPath"
	// hql.g:295:1: fromClassOrOuterQueryPath : path ( asAlias )? ( propertyFetch )? -> ^( RANGE path ( asAlias )? ( propertyFetch )? ) ;
	public final hqlParser.fromClassOrOuterQueryPath_return fromClassOrOuterQueryPath() throws RecognitionException {
		hqlParser.fromClassOrOuterQueryPath_return retval = new hqlParser.fromClassOrOuterQueryPath_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope path92 =null;
		ParserRuleReturnScope asAlias93 =null;
		ParserRuleReturnScope propertyFetch94 =null;

		RewriteRuleSubtreeStream stream_propertyFetch=new RewriteRuleSubtreeStream(adaptor,"rule propertyFetch");
		RewriteRuleSubtreeStream stream_path=new RewriteRuleSubtreeStream(adaptor,"rule path");
		RewriteRuleSubtreeStream stream_asAlias=new RewriteRuleSubtreeStream(adaptor,"rule asAlias");

		try {
			// hql.g:296:2: ( path ( asAlias )? ( propertyFetch )? -> ^( RANGE path ( asAlias )? ( propertyFetch )? ) )
			// hql.g:296:4: path ( asAlias )? ( propertyFetch )?
			{
			pushFollow(FOLLOW_path_in_fromClassOrOuterQueryPath1329);
			path92=path();
			state._fsp--;

			stream_path.add(path92.getTree());
			 WeakKeywords();
			// hql.g:296:29: ( asAlias )?
			int alt34=2;
			int LA34_0 = input.LA(1);
			if ( (LA34_0==AS||LA34_0==IDENT) ) {
				alt34=1;
			}
			switch (alt34) {
				case 1 :
					// hql.g:296:30: asAlias
					{
					pushFollow(FOLLOW_asAlias_in_fromClassOrOuterQueryPath1334);
					asAlias93=asAlias();
					state._fsp--;

					stream_asAlias.add(asAlias93.getTree());
					}
					break;

			}

			// hql.g:296:40: ( propertyFetch )?
			int alt35=2;
			int LA35_0 = input.LA(1);
			if ( (LA35_0==FETCH) ) {
				alt35=1;
			}
			switch (alt35) {
				case 1 :
					// hql.g:296:41: propertyFetch
					{
					pushFollow(FOLLOW_propertyFetch_in_fromClassOrOuterQueryPath1339);
					propertyFetch94=propertyFetch();
					state._fsp--;

					stream_propertyFetch.add(propertyFetch94.getTree());
					}
					break;

			}

			// AST REWRITE
			// elements: asAlias, propertyFetch, path
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 297:3: -> ^( RANGE path ( asAlias )? ( propertyFetch )? )
			{
				// hql.g:297:6: ^( RANGE path ( asAlias )? ( propertyFetch )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(RANGE, "RANGE"), root_1);
				adaptor.addChild(root_1, stream_path.nextTree());
				// hql.g:297:19: ( asAlias )?
				if ( stream_asAlias.hasNext() ) {
					adaptor.addChild(root_1, stream_asAlias.nextTree());
				}
				stream_asAlias.reset();

				// hql.g:297:28: ( propertyFetch )?
				if ( stream_propertyFetch.hasNext() ) {
					adaptor.addChild(root_1, stream_propertyFetch.nextTree());
				}
				stream_propertyFetch.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "fromClassOrOuterQueryPath"


	public static class inClassDeclaration_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "inClassDeclaration"
	// hql.g:300:1: inClassDeclaration : alias IN ( CLASS )? path -> ^( RANGE path alias ) ;
	public final hqlParser.inClassDeclaration_return inClassDeclaration() throws RecognitionException {
		hqlParser.inClassDeclaration_return retval = new hqlParser.inClassDeclaration_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token IN96=null;
		Token CLASS97=null;
		ParserRuleReturnScope alias95 =null;
		ParserRuleReturnScope path98 =null;

		CommonTree IN96_tree=null;
		CommonTree CLASS97_tree=null;
		RewriteRuleTokenStream stream_CLASS=new RewriteRuleTokenStream(adaptor,"token CLASS");
		RewriteRuleTokenStream stream_IN=new RewriteRuleTokenStream(adaptor,"token IN");
		RewriteRuleSubtreeStream stream_alias=new RewriteRuleSubtreeStream(adaptor,"rule alias");
		RewriteRuleSubtreeStream stream_path=new RewriteRuleSubtreeStream(adaptor,"rule path");

		try {
			// hql.g:301:2: ( alias IN ( CLASS )? path -> ^( RANGE path alias ) )
			// hql.g:301:4: alias IN ( CLASS )? path
			{
			pushFollow(FOLLOW_alias_in_inClassDeclaration1369);
			alias95=alias();
			state._fsp--;

			stream_alias.add(alias95.getTree());
			IN96=(Token)match(input,IN,FOLLOW_IN_in_inClassDeclaration1371);
			stream_IN.add(IN96);

			// hql.g:301:13: ( CLASS )?
			int alt36=2;
			int LA36_0 = input.LA(1);
			if ( (LA36_0==CLASS) ) {
				alt36=1;
			}
			switch (alt36) {
				case 1 :
					// hql.g:301:13: CLASS
					{
					CLASS97=(Token)match(input,CLASS,FOLLOW_CLASS_in_inClassDeclaration1373);
					stream_CLASS.add(CLASS97);

					}
					break;

			}

			pushFollow(FOLLOW_path_in_inClassDeclaration1376);
			path98=path();
			state._fsp--;

			stream_path.add(path98.getTree());
			// AST REWRITE
			// elements: alias, path
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 302:3: -> ^( RANGE path alias )
			{
				// hql.g:302:6: ^( RANGE path alias )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(RANGE, "RANGE"), root_1);
				adaptor.addChild(root_1, stream_path.nextTree());
				adaptor.addChild(root_1, stream_alias.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "inClassDeclaration"


	public static class inCollectionDeclaration_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "inCollectionDeclaration"
	// hql.g:305:1: inCollectionDeclaration : IN OPEN path CLOSE alias -> ^( JOIN[\"join\"] INNER[\"inner\"] path alias ) ;
	public final hqlParser.inCollectionDeclaration_return inCollectionDeclaration() throws RecognitionException {
		hqlParser.inCollectionDeclaration_return retval = new hqlParser.inCollectionDeclaration_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token IN99=null;
		Token OPEN100=null;
		Token CLOSE102=null;
		ParserRuleReturnScope path101 =null;
		ParserRuleReturnScope alias103 =null;

		CommonTree IN99_tree=null;
		CommonTree OPEN100_tree=null;
		CommonTree CLOSE102_tree=null;
		RewriteRuleTokenStream stream_OPEN=new RewriteRuleTokenStream(adaptor,"token OPEN");
		RewriteRuleTokenStream stream_IN=new RewriteRuleTokenStream(adaptor,"token IN");
		RewriteRuleTokenStream stream_CLOSE=new RewriteRuleTokenStream(adaptor,"token CLOSE");
		RewriteRuleSubtreeStream stream_alias=new RewriteRuleSubtreeStream(adaptor,"rule alias");
		RewriteRuleSubtreeStream stream_path=new RewriteRuleSubtreeStream(adaptor,"rule path");

		try {
			// hql.g:306:5: ( IN OPEN path CLOSE alias -> ^( JOIN[\"join\"] INNER[\"inner\"] path alias ) )
			// hql.g:306:7: IN OPEN path CLOSE alias
			{
			IN99=(Token)match(input,IN,FOLLOW_IN_in_inCollectionDeclaration1404);
			stream_IN.add(IN99);

			OPEN100=(Token)match(input,OPEN,FOLLOW_OPEN_in_inCollectionDeclaration1406);
			stream_OPEN.add(OPEN100);

			pushFollow(FOLLOW_path_in_inCollectionDeclaration1408);
			path101=path();
			state._fsp--;

			stream_path.add(path101.getTree());
			CLOSE102=(Token)match(input,CLOSE,FOLLOW_CLOSE_in_inCollectionDeclaration1410);
			stream_CLOSE.add(CLOSE102);

			pushFollow(FOLLOW_alias_in_inCollectionDeclaration1412);
			alias103=alias();
			state._fsp--;

			stream_alias.add(alias103.getTree());
			// AST REWRITE
			// elements: alias, path
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 307:6: -> ^( JOIN[\"join\"] INNER[\"inner\"] path alias )
			{
				// hql.g:307:9: ^( JOIN[\"join\"] INNER[\"inner\"] path alias )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(JOIN, "join"), root_1);
				adaptor.addChild(root_1, adaptor.create(INNER, "inner"));
				adaptor.addChild(root_1, stream_path.nextTree());
				adaptor.addChild(root_1, stream_alias.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "inCollectionDeclaration"


	public static class inCollectionElementsDeclaration_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "inCollectionElementsDeclaration"
	// hql.g:310:1: inCollectionElementsDeclaration : ( alias IN ELEMENTS OPEN path CLOSE -> ^( JOIN[\"join\"] INNER[\"inner\"] path alias ) | ELEMENTS OPEN path CLOSE AS alias -> ^( JOIN[\"join\"] INNER[\"inner\"] path alias ) );
	public final hqlParser.inCollectionElementsDeclaration_return inCollectionElementsDeclaration() throws RecognitionException {
		hqlParser.inCollectionElementsDeclaration_return retval = new hqlParser.inCollectionElementsDeclaration_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token IN105=null;
		Token ELEMENTS106=null;
		Token OPEN107=null;
		Token CLOSE109=null;
		Token ELEMENTS110=null;
		Token OPEN111=null;
		Token CLOSE113=null;
		Token AS114=null;
		ParserRuleReturnScope alias104 =null;
		ParserRuleReturnScope path108 =null;
		ParserRuleReturnScope path112 =null;
		ParserRuleReturnScope alias115 =null;

		CommonTree IN105_tree=null;
		CommonTree ELEMENTS106_tree=null;
		CommonTree OPEN107_tree=null;
		CommonTree CLOSE109_tree=null;
		CommonTree ELEMENTS110_tree=null;
		CommonTree OPEN111_tree=null;
		CommonTree CLOSE113_tree=null;
		CommonTree AS114_tree=null;
		RewriteRuleTokenStream stream_OPEN=new RewriteRuleTokenStream(adaptor,"token OPEN");
		RewriteRuleTokenStream stream_AS=new RewriteRuleTokenStream(adaptor,"token AS");
		RewriteRuleTokenStream stream_IN=new RewriteRuleTokenStream(adaptor,"token IN");
		RewriteRuleTokenStream stream_CLOSE=new RewriteRuleTokenStream(adaptor,"token CLOSE");
		RewriteRuleTokenStream stream_ELEMENTS=new RewriteRuleTokenStream(adaptor,"token ELEMENTS");
		RewriteRuleSubtreeStream stream_alias=new RewriteRuleSubtreeStream(adaptor,"rule alias");
		RewriteRuleSubtreeStream stream_path=new RewriteRuleSubtreeStream(adaptor,"rule path");

		try {
			// hql.g:311:2: ( alias IN ELEMENTS OPEN path CLOSE -> ^( JOIN[\"join\"] INNER[\"inner\"] path alias ) | ELEMENTS OPEN path CLOSE AS alias -> ^( JOIN[\"join\"] INNER[\"inner\"] path alias ) )
			int alt37=2;
			int LA37_0 = input.LA(1);
			if ( (LA37_0==IDENT) ) {
				alt37=1;
			}
			else if ( (LA37_0==ELEMENTS) ) {
				alt37=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 37, 0, input);
				throw nvae;
			}

			switch (alt37) {
				case 1 :
					// hql.g:311:4: alias IN ELEMENTS OPEN path CLOSE
					{
					pushFollow(FOLLOW_alias_in_inCollectionElementsDeclaration1446);
					alias104=alias();
					state._fsp--;

					stream_alias.add(alias104.getTree());
					IN105=(Token)match(input,IN,FOLLOW_IN_in_inCollectionElementsDeclaration1448);
					stream_IN.add(IN105);

					ELEMENTS106=(Token)match(input,ELEMENTS,FOLLOW_ELEMENTS_in_inCollectionElementsDeclaration1450);
					stream_ELEMENTS.add(ELEMENTS106);

					OPEN107=(Token)match(input,OPEN,FOLLOW_OPEN_in_inCollectionElementsDeclaration1452);
					stream_OPEN.add(OPEN107);

					pushFollow(FOLLOW_path_in_inCollectionElementsDeclaration1454);
					path108=path();
					state._fsp--;

					stream_path.add(path108.getTree());
					CLOSE109=(Token)match(input,CLOSE,FOLLOW_CLOSE_in_inCollectionElementsDeclaration1456);
					stream_CLOSE.add(CLOSE109);

					// AST REWRITE
					// elements: path, alias
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 312:3: -> ^( JOIN[\"join\"] INNER[\"inner\"] path alias )
					{
						// hql.g:312:6: ^( JOIN[\"join\"] INNER[\"inner\"] path alias )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(JOIN, "join"), root_1);
						adaptor.addChild(root_1, adaptor.create(INNER, "inner"));
						adaptor.addChild(root_1, stream_path.nextTree());
						adaptor.addChild(root_1, stream_alias.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// hql.g:313:4: ELEMENTS OPEN path CLOSE AS alias
					{
					ELEMENTS110=(Token)match(input,ELEMENTS,FOLLOW_ELEMENTS_in_inCollectionElementsDeclaration1478);
					stream_ELEMENTS.add(ELEMENTS110);

					OPEN111=(Token)match(input,OPEN,FOLLOW_OPEN_in_inCollectionElementsDeclaration1480);
					stream_OPEN.add(OPEN111);

					pushFollow(FOLLOW_path_in_inCollectionElementsDeclaration1482);
					path112=path();
					state._fsp--;

					stream_path.add(path112.getTree());
					CLOSE113=(Token)match(input,CLOSE,FOLLOW_CLOSE_in_inCollectionElementsDeclaration1484);
					stream_CLOSE.add(CLOSE113);

					AS114=(Token)match(input,AS,FOLLOW_AS_in_inCollectionElementsDeclaration1486);
					stream_AS.add(AS114);

					pushFollow(FOLLOW_alias_in_inCollectionElementsDeclaration1488);
					alias115=alias();
					state._fsp--;

					stream_alias.add(alias115.getTree());
					// AST REWRITE
					// elements: path, alias
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 314:3: -> ^( JOIN[\"join\"] INNER[\"inner\"] path alias )
					{
						// hql.g:314:6: ^( JOIN[\"join\"] INNER[\"inner\"] path alias )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(JOIN, "join"), root_1);
						adaptor.addChild(root_1, adaptor.create(INNER, "inner"));
						adaptor.addChild(root_1, stream_path.nextTree());
						adaptor.addChild(root_1, stream_alias.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "inCollectionElementsDeclaration"


	public static class asAlias_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "asAlias"
	// hql.g:318:1: asAlias : ( AS !)? alias ;
	public final hqlParser.asAlias_return asAlias() throws RecognitionException {
		hqlParser.asAlias_return retval = new hqlParser.asAlias_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token AS116=null;
		ParserRuleReturnScope alias117 =null;

		CommonTree AS116_tree=null;

		try {
			// hql.g:319:2: ( ( AS !)? alias )
			// hql.g:319:4: ( AS !)? alias
			{
			root_0 = (CommonTree)adaptor.nil();


			// hql.g:319:4: ( AS !)?
			int alt38=2;
			int LA38_0 = input.LA(1);
			if ( (LA38_0==AS) ) {
				alt38=1;
			}
			switch (alt38) {
				case 1 :
					// hql.g:319:5: AS !
					{
					AS116=(Token)match(input,AS,FOLLOW_AS_in_asAlias1520);
					}
					break;

			}

			pushFollow(FOLLOW_alias_in_asAlias1525);
			alias117=alias();
			state._fsp--;

			adaptor.addChild(root_0, alias117.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "asAlias"


	public static class alias_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "alias"
	// hql.g:321:1: alias : i= identifier -> ^( ALIAS[$i.start] ) ;
	public final hqlParser.alias_return alias() throws RecognitionException {
		hqlParser.alias_return retval = new hqlParser.alias_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope i =null;

		RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");

		try {
			// hql.g:322:2: (i= identifier -> ^( ALIAS[$i.start] ) )
			// hql.g:322:4: i= identifier
			{
			pushFollow(FOLLOW_identifier_in_alias1537);
			i=identifier();
			state._fsp--;

			stream_identifier.add(i.getTree());
			// AST REWRITE
			// elements:
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 323:2: -> ^( ALIAS[$i.start] )
			{
				// hql.g:323:5: ^( ALIAS[$i.start] )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(ALIAS, (i!=null?(i.start):null)), root_1);
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "alias"


	public static class propertyFetch_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "propertyFetch"
	// hql.g:326:1: propertyFetch : FETCH ALL ! PROPERTIES !;
	public final hqlParser.propertyFetch_return propertyFetch() throws RecognitionException {
		hqlParser.propertyFetch_return retval = new hqlParser.propertyFetch_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token FETCH118=null;
		Token ALL119=null;
		Token PROPERTIES120=null;

		CommonTree FETCH118_tree=null;
		CommonTree ALL119_tree=null;
		CommonTree PROPERTIES120_tree=null;

		try {
			// hql.g:327:2: ( FETCH ALL ! PROPERTIES !)
			// hql.g:327:4: FETCH ALL ! PROPERTIES !
			{
			root_0 = (CommonTree)adaptor.nil();


			FETCH118=(Token)match(input,FETCH,FOLLOW_FETCH_in_propertyFetch1556);
			FETCH118_tree = (CommonTree)adaptor.create(FETCH118);
			adaptor.addChild(root_0, FETCH118_tree);

			ALL119=(Token)match(input,ALL,FOLLOW_ALL_in_propertyFetch1558);
			PROPERTIES120=(Token)match(input,PROPERTIES,FOLLOW_PROPERTIES_in_propertyFetch1561);
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "propertyFetch"


	public static class groupByClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "groupByClause"
	// hql.g:330:1: groupByClause : GROUP ^ 'by' ! expression ( COMMA ! expression )* ;
	public final hqlParser.groupByClause_return groupByClause() throws RecognitionException {
		hqlParser.groupByClause_return retval = new hqlParser.groupByClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token GROUP121=null;
		Token string_literal122=null;
		Token COMMA124=null;
		ParserRuleReturnScope expression123 =null;
		ParserRuleReturnScope expression125 =null;

		CommonTree GROUP121_tree=null;
		CommonTree string_literal122_tree=null;
		CommonTree COMMA124_tree=null;

		try {
			// hql.g:331:2: ( GROUP ^ 'by' ! expression ( COMMA ! expression )* )
			// hql.g:331:4: GROUP ^ 'by' ! expression ( COMMA ! expression )*
			{
			root_0 = (CommonTree)adaptor.nil();


			GROUP121=(Token)match(input,GROUP,FOLLOW_GROUP_in_groupByClause1573);
			GROUP121_tree = (CommonTree)adaptor.create(GROUP121);
			root_0 = (CommonTree)adaptor.becomeRoot(GROUP121_tree, root_0);

			string_literal122=(Token)match(input,LITERAL_by,FOLLOW_LITERAL_by_in_groupByClause1579);
			pushFollow(FOLLOW_expression_in_groupByClause1582);
			expression123=expression();
			state._fsp--;

			adaptor.addChild(root_0, expression123.getTree());

			// hql.g:332:20: ( COMMA ! expression )*
			loop39:
			while (true) {
				int alt39=2;
				int LA39_0 = input.LA(1);
				if ( (LA39_0==COMMA) ) {
					alt39=1;
				}

				switch (alt39) {
				case 1 :
					// hql.g:332:22: COMMA ! expression
					{
					COMMA124=(Token)match(input,COMMA,FOLLOW_COMMA_in_groupByClause1586);
					pushFollow(FOLLOW_expression_in_groupByClause1589);
					expression125=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression125.getTree());

					}
					break;

				default :
					break loop39;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "groupByClause"


	public static class orderByClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "orderByClause"
	// hql.g:335:1: orderByClause : ORDER ^ 'by' ! orderElement ( COMMA ! orderElement )* ;
	public final hqlParser.orderByClause_return orderByClause() throws RecognitionException {
		hqlParser.orderByClause_return retval = new hqlParser.orderByClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ORDER126=null;
		Token string_literal127=null;
		Token COMMA129=null;
		ParserRuleReturnScope orderElement128 =null;
		ParserRuleReturnScope orderElement130 =null;

		CommonTree ORDER126_tree=null;
		CommonTree string_literal127_tree=null;
		CommonTree COMMA129_tree=null;

		try {
			// hql.g:336:2: ( ORDER ^ 'by' ! orderElement ( COMMA ! orderElement )* )
			// hql.g:336:4: ORDER ^ 'by' ! orderElement ( COMMA ! orderElement )*
			{
			root_0 = (CommonTree)adaptor.nil();


			ORDER126=(Token)match(input,ORDER,FOLLOW_ORDER_in_orderByClause1603);
			ORDER126_tree = (CommonTree)adaptor.create(ORDER126);
			root_0 = (CommonTree)adaptor.becomeRoot(ORDER126_tree, root_0);

			string_literal127=(Token)match(input,LITERAL_by,FOLLOW_LITERAL_by_in_orderByClause1606);
			pushFollow(FOLLOW_orderElement_in_orderByClause1609);
			orderElement128=orderElement();
			state._fsp--;

			adaptor.addChild(root_0, orderElement128.getTree());

			// hql.g:336:30: ( COMMA ! orderElement )*
			loop40:
			while (true) {
				int alt40=2;
				int LA40_0 = input.LA(1);
				if ( (LA40_0==COMMA) ) {
					alt40=1;
				}

				switch (alt40) {
				case 1 :
					// hql.g:336:32: COMMA ! orderElement
					{
					COMMA129=(Token)match(input,COMMA,FOLLOW_COMMA_in_orderByClause1613);
					pushFollow(FOLLOW_orderElement_in_orderByClause1616);
					orderElement130=orderElement();
					state._fsp--;

					adaptor.addChild(root_0, orderElement130.getTree());

					}
					break;

				default :
					break loop40;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "orderByClause"


	public static class skipClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "skipClause"
	// hql.g:339:1: skipClause : SKIP ^ ( NUM_INT | parameter ) ;
	public final hqlParser.skipClause_return skipClause() throws RecognitionException {
		hqlParser.skipClause_return retval = new hqlParser.skipClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token SKIP131=null;
		Token NUM_INT132=null;
		ParserRuleReturnScope parameter133 =null;

		CommonTree SKIP131_tree=null;
		CommonTree NUM_INT132_tree=null;

		try {
			// hql.g:340:2: ( SKIP ^ ( NUM_INT | parameter ) )
			// hql.g:340:4: SKIP ^ ( NUM_INT | parameter )
			{
			root_0 = (CommonTree)adaptor.nil();


			SKIP131=(Token)match(input,SKIP,FOLLOW_SKIP_in_skipClause1630);
			SKIP131_tree = (CommonTree)adaptor.create(SKIP131);
			root_0 = (CommonTree)adaptor.becomeRoot(SKIP131_tree, root_0);

			// hql.g:340:10: ( NUM_INT | parameter )
			int alt41=2;
			int LA41_0 = input.LA(1);
			if ( (LA41_0==NUM_INT) ) {
				alt41=1;
			}
			else if ( (LA41_0==COLON||LA41_0==PARAM) ) {
				alt41=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 41, 0, input);
				throw nvae;
			}

			switch (alt41) {
				case 1 :
					// hql.g:340:11: NUM_INT
					{
					NUM_INT132=(Token)match(input,NUM_INT,FOLLOW_NUM_INT_in_skipClause1634);
					NUM_INT132_tree = (CommonTree)adaptor.create(NUM_INT132);
					adaptor.addChild(root_0, NUM_INT132_tree);

					}
					break;
				case 2 :
					// hql.g:340:21: parameter
					{
					pushFollow(FOLLOW_parameter_in_skipClause1638);
					parameter133=parameter();
					state._fsp--;

					adaptor.addChild(root_0, parameter133.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "skipClause"


	public static class takeClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "takeClause"
	// hql.g:343:1: takeClause : TAKE ^ ( NUM_INT | parameter ) ;
	public final hqlParser.takeClause_return takeClause() throws RecognitionException {
		hqlParser.takeClause_return retval = new hqlParser.takeClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token TAKE134=null;
		Token NUM_INT135=null;
		ParserRuleReturnScope parameter136 =null;

		CommonTree TAKE134_tree=null;
		CommonTree NUM_INT135_tree=null;

		try {
			// hql.g:344:2: ( TAKE ^ ( NUM_INT | parameter ) )
			// hql.g:344:4: TAKE ^ ( NUM_INT | parameter )
			{
			root_0 = (CommonTree)adaptor.nil();


			TAKE134=(Token)match(input,TAKE,FOLLOW_TAKE_in_takeClause1650);
			TAKE134_tree = (CommonTree)adaptor.create(TAKE134);
			root_0 = (CommonTree)adaptor.becomeRoot(TAKE134_tree, root_0);

			// hql.g:344:10: ( NUM_INT | parameter )
			int alt42=2;
			int LA42_0 = input.LA(1);
			if ( (LA42_0==NUM_INT) ) {
				alt42=1;
			}
			else if ( (LA42_0==COLON||LA42_0==PARAM) ) {
				alt42=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 42, 0, input);
				throw nvae;
			}

			switch (alt42) {
				case 1 :
					// hql.g:344:11: NUM_INT
					{
					NUM_INT135=(Token)match(input,NUM_INT,FOLLOW_NUM_INT_in_takeClause1654);
					NUM_INT135_tree = (CommonTree)adaptor.create(NUM_INT135);
					adaptor.addChild(root_0, NUM_INT135_tree);

					}
					break;
				case 2 :
					// hql.g:344:21: parameter
					{
					pushFollow(FOLLOW_parameter_in_takeClause1658);
					parameter136=parameter();
					state._fsp--;

					adaptor.addChild(root_0, parameter136.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "takeClause"


	public static class parameter_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "parameter"
	// hql.g:347:1: parameter : ( COLON ^ identifier | PARAM ^ ( NUM_INT )? );
	public final hqlParser.parameter_return parameter() throws RecognitionException {
		hqlParser.parameter_return retval = new hqlParser.parameter_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COLON137=null;
		Token PARAM139=null;
		Token NUM_INT140=null;
		ParserRuleReturnScope identifier138 =null;

		CommonTree COLON137_tree=null;
		CommonTree PARAM139_tree=null;
		CommonTree NUM_INT140_tree=null;

		try {
			// hql.g:348:2: ( COLON ^ identifier | PARAM ^ ( NUM_INT )? )
			int alt44=2;
			int LA44_0 = input.LA(1);
			if ( (LA44_0==COLON) ) {
				alt44=1;
			}
			else if ( (LA44_0==PARAM) ) {
				alt44=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 44, 0, input);
				throw nvae;
			}

			switch (alt44) {
				case 1 :
					// hql.g:348:4: COLON ^ identifier
					{
					root_0 = (CommonTree)adaptor.nil();


					COLON137=(Token)match(input,COLON,FOLLOW_COLON_in_parameter1670);
					COLON137_tree = (CommonTree)adaptor.create(COLON137);
					root_0 = (CommonTree)adaptor.becomeRoot(COLON137_tree, root_0);

					pushFollow(FOLLOW_identifier_in_parameter1673);
					identifier138=identifier();
					state._fsp--;

					adaptor.addChild(root_0, identifier138.getTree());

					}
					break;
				case 2 :
					// hql.g:349:4: PARAM ^ ( NUM_INT )?
					{
					root_0 = (CommonTree)adaptor.nil();


					PARAM139=(Token)match(input,PARAM,FOLLOW_PARAM_in_parameter1678);
					PARAM139_tree = (CommonTree)adaptor.create(PARAM139);
					root_0 = (CommonTree)adaptor.becomeRoot(PARAM139_tree, root_0);

					// hql.g:349:11: ( NUM_INT )?
					int alt43=2;
					int LA43_0 = input.LA(1);
					if ( (LA43_0==NUM_INT) ) {
						alt43=1;
					}
					switch (alt43) {
						case 1 :
							// hql.g:349:12: NUM_INT
							{
							NUM_INT140=(Token)match(input,NUM_INT,FOLLOW_NUM_INT_in_parameter1682);
							NUM_INT140_tree = (CommonTree)adaptor.create(NUM_INT140);
							adaptor.addChild(root_0, NUM_INT140_tree);

							}
							break;

					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "parameter"


	public static class orderElement_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "orderElement"
	// hql.g:352:1: orderElement : expression ( ascendingOrDescending )? ;
	public final hqlParser.orderElement_return orderElement() throws RecognitionException {
		hqlParser.orderElement_return retval = new hqlParser.orderElement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope expression141 =null;
		ParserRuleReturnScope ascendingOrDescending142 =null;


		try {
			// hql.g:353:2: ( expression ( ascendingOrDescending )? )
			// hql.g:353:4: expression ( ascendingOrDescending )?
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_expression_in_orderElement1695);
			expression141=expression();
			state._fsp--;

			adaptor.addChild(root_0, expression141.getTree());

			// hql.g:353:15: ( ascendingOrDescending )?
			int alt45=2;
			int LA45_0 = input.LA(1);
			if ( (LA45_0==ASCENDING||LA45_0==DESCENDING||(LA45_0 >= 133 && LA45_0 <= 134)) ) {
				alt45=1;
			}
			switch (alt45) {
				case 1 :
					// hql.g:353:17: ascendingOrDescending
					{
					pushFollow(FOLLOW_ascendingOrDescending_in_orderElement1699);
					ascendingOrDescending142=ascendingOrDescending();
					state._fsp--;

					adaptor.addChild(root_0, ascendingOrDescending142.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "orderElement"


	public static class ascendingOrDescending_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "ascendingOrDescending"
	// hql.g:356:1: ascendingOrDescending : ( (a= 'asc' |a= 'ascending' ) -> ^( ASCENDING[$a.getText()] ) | (d= 'desc' |d= 'descending' ) -> ^( DESCENDING[$d.getText()] ) );
	public final hqlParser.ascendingOrDescending_return ascendingOrDescending() throws RecognitionException {
		hqlParser.ascendingOrDescending_return retval = new hqlParser.ascendingOrDescending_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token a=null;
		Token d=null;

		CommonTree a_tree=null;
		CommonTree d_tree=null;
		RewriteRuleTokenStream stream_134=new RewriteRuleTokenStream(adaptor,"token 134");
		RewriteRuleTokenStream stream_133=new RewriteRuleTokenStream(adaptor,"token 133");
		RewriteRuleTokenStream stream_DESCENDING=new RewriteRuleTokenStream(adaptor,"token DESCENDING");
		RewriteRuleTokenStream stream_ASCENDING=new RewriteRuleTokenStream(adaptor,"token ASCENDING");

		try {
			// hql.g:357:2: ( (a= 'asc' |a= 'ascending' ) -> ^( ASCENDING[$a.getText()] ) | (d= 'desc' |d= 'descending' ) -> ^( DESCENDING[$d.getText()] ) )
			int alt48=2;
			int LA48_0 = input.LA(1);
			if ( (LA48_0==ASCENDING||LA48_0==133) ) {
				alt48=1;
			}
			else if ( (LA48_0==DESCENDING||LA48_0==134) ) {
				alt48=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 48, 0, input);
				throw nvae;
			}

			switch (alt48) {
				case 1 :
					// hql.g:357:4: (a= 'asc' |a= 'ascending' )
					{
					// hql.g:357:4: (a= 'asc' |a= 'ascending' )
					int alt46=2;
					int LA46_0 = input.LA(1);
					if ( (LA46_0==ASCENDING) ) {
						alt46=1;
					}
					else if ( (LA46_0==133) ) {
						alt46=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 46, 0, input);
						throw nvae;
					}

					switch (alt46) {
						case 1 :
							// hql.g:357:6: a= 'asc'
							{
							a=(Token)match(input,ASCENDING,FOLLOW_ASCENDING_in_ascendingOrDescending1717);
							stream_ASCENDING.add(a);

							}
							break;
						case 2 :
							// hql.g:357:16: a= 'ascending'
							{
							a=(Token)match(input,133,FOLLOW_133_in_ascendingOrDescending1723);
							stream_133.add(a);

							}
							break;

					}

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 358:3: -> ^( ASCENDING[$a.getText()] )
					{
						// hql.g:358:6: ^( ASCENDING[$a.getText()] )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASCENDING, a.getText()), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// hql.g:359:4: (d= 'desc' |d= 'descending' )
					{
					// hql.g:359:4: (d= 'desc' |d= 'descending' )
					int alt47=2;
					int LA47_0 = input.LA(1);
					if ( (LA47_0==DESCENDING) ) {
						alt47=1;
					}
					else if ( (LA47_0==134) ) {
						alt47=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 47, 0, input);
						throw nvae;
					}

					switch (alt47) {
						case 1 :
							// hql.g:359:6: d= 'desc'
							{
							d=(Token)match(input,DESCENDING,FOLLOW_DESCENDING_in_ascendingOrDescending1743);
							stream_DESCENDING.add(d);

							}
							break;
						case 2 :
							// hql.g:359:17: d= 'descending'
							{
							d=(Token)match(input,134,FOLLOW_134_in_ascendingOrDescending1749);
							stream_134.add(d);

							}
							break;

					}

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 360:3: -> ^( DESCENDING[$d.getText()] )
					{
						// hql.g:360:6: ^( DESCENDING[$d.getText()] )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(DESCENDING, d.getText()), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "ascendingOrDescending"


	public static class havingClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "havingClause"
	// hql.g:363:1: havingClause : HAVING ^ logicalExpression ;
	public final hqlParser.havingClause_return havingClause() throws RecognitionException {
		hqlParser.havingClause_return retval = new hqlParser.havingClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token HAVING143=null;
		ParserRuleReturnScope logicalExpression144 =null;

		CommonTree HAVING143_tree=null;

		try {
			// hql.g:364:2: ( HAVING ^ logicalExpression )
			// hql.g:364:4: HAVING ^ logicalExpression
			{
			root_0 = (CommonTree)adaptor.nil();


			HAVING143=(Token)match(input,HAVING,FOLLOW_HAVING_in_havingClause1770);
			HAVING143_tree = (CommonTree)adaptor.create(HAVING143);
			root_0 = (CommonTree)adaptor.becomeRoot(HAVING143_tree, root_0);

			pushFollow(FOLLOW_logicalExpression_in_havingClause1773);
			logicalExpression144=logicalExpression();
			state._fsp--;

			adaptor.addChild(root_0, logicalExpression144.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "havingClause"


	public static class whereClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "whereClause"
	// hql.g:367:1: whereClause : WHERE ^ logicalExpression ;
	public final hqlParser.whereClause_return whereClause() throws RecognitionException {
		hqlParser.whereClause_return retval = new hqlParser.whereClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WHERE145=null;
		ParserRuleReturnScope logicalExpression146 =null;

		CommonTree WHERE145_tree=null;

		try {
			// hql.g:368:2: ( WHERE ^ logicalExpression )
			// hql.g:368:4: WHERE ^ logicalExpression
			{
			root_0 = (CommonTree)adaptor.nil();


			WHERE145=(Token)match(input,WHERE,FOLLOW_WHERE_in_whereClause1784);
			WHERE145_tree = (CommonTree)adaptor.create(WHERE145);
			root_0 = (CommonTree)adaptor.becomeRoot(WHERE145_tree, root_0);

			pushFollow(FOLLOW_logicalExpression_in_whereClause1787);
			logicalExpression146=logicalExpression();
			state._fsp--;

			adaptor.addChild(root_0, logicalExpression146.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "whereClause"


	public static class selectedPropertiesList_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "selectedPropertiesList"
	// hql.g:371:1: selectedPropertiesList : aliasedExpression ( COMMA ! aliasedExpression )* ;
	public final hqlParser.selectedPropertiesList_return selectedPropertiesList() throws RecognitionException {
		hqlParser.selectedPropertiesList_return retval = new hqlParser.selectedPropertiesList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COMMA148=null;
		ParserRuleReturnScope aliasedExpression147 =null;
		ParserRuleReturnScope aliasedExpression149 =null;

		CommonTree COMMA148_tree=null;

		try {
			// hql.g:372:2: ( aliasedExpression ( COMMA ! aliasedExpression )* )
			// hql.g:372:4: aliasedExpression ( COMMA ! aliasedExpression )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_aliasedExpression_in_selectedPropertiesList1798);
			aliasedExpression147=aliasedExpression();
			state._fsp--;

			adaptor.addChild(root_0, aliasedExpression147.getTree());

			// hql.g:372:22: ( COMMA ! aliasedExpression )*
			loop49:
			while (true) {
				int alt49=2;
				int LA49_0 = input.LA(1);
				if ( (LA49_0==COMMA) ) {
					alt49=1;
				}

				switch (alt49) {
				case 1 :
					// hql.g:372:24: COMMA ! aliasedExpression
					{
					COMMA148=(Token)match(input,COMMA,FOLLOW_COMMA_in_selectedPropertiesList1802);
					pushFollow(FOLLOW_aliasedExpression_in_selectedPropertiesList1805);
					aliasedExpression149=aliasedExpression();
					state._fsp--;

					adaptor.addChild(root_0, aliasedExpression149.getTree());

					}
					break;

				default :
					break loop49;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "selectedPropertiesList"


	public static class aliasedExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "aliasedExpression"
	// hql.g:375:1: aliasedExpression : expression ( AS ^ identifier )? ;
	public final hqlParser.aliasedExpression_return aliasedExpression() throws RecognitionException {
		hqlParser.aliasedExpression_return retval = new hqlParser.aliasedExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token AS151=null;
		ParserRuleReturnScope expression150 =null;
		ParserRuleReturnScope identifier152 =null;

		CommonTree AS151_tree=null;

		try {
			// hql.g:376:2: ( expression ( AS ^ identifier )? )
			// hql.g:376:4: expression ( AS ^ identifier )?
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_expression_in_aliasedExpression1820);
			expression150=expression();
			state._fsp--;

			adaptor.addChild(root_0, expression150.getTree());

			// hql.g:376:15: ( AS ^ identifier )?
			int alt50=2;
			int LA50_0 = input.LA(1);
			if ( (LA50_0==AS) ) {
				alt50=1;
			}
			switch (alt50) {
				case 1 :
					// hql.g:376:17: AS ^ identifier
					{
					AS151=(Token)match(input,AS,FOLLOW_AS_in_aliasedExpression1824);
					AS151_tree = (CommonTree)adaptor.create(AS151);
					root_0 = (CommonTree)adaptor.becomeRoot(AS151_tree, root_0);

					pushFollow(FOLLOW_identifier_in_aliasedExpression1827);
					identifier152=identifier();
					state._fsp--;

					adaptor.addChild(root_0, identifier152.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "aliasedExpression"


	public static class logicalExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "logicalExpression"
	// hql.g:404:1: logicalExpression : expression ;
	public final hqlParser.logicalExpression_return logicalExpression() throws RecognitionException {
		hqlParser.logicalExpression_return retval = new hqlParser.logicalExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope expression153 =null;


		try {
			// hql.g:405:2: ( expression )
			// hql.g:405:4: expression
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_expression_in_logicalExpression1866);
			expression153=expression();
			state._fsp--;

			adaptor.addChild(root_0, expression153.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "logicalExpression"


	public static class expression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "expression"
	// hql.g:409:1: expression : logicalOrExpression ;
	public final hqlParser.expression_return expression() throws RecognitionException {
		hqlParser.expression_return retval = new hqlParser.expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope logicalOrExpression154 =null;


		try {
			// hql.g:410:2: ( logicalOrExpression )
			// hql.g:410:4: logicalOrExpression
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_logicalOrExpression_in_expression1878);
			logicalOrExpression154=logicalOrExpression();
			state._fsp--;

			adaptor.addChild(root_0, logicalOrExpression154.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expression"


	public static class logicalOrExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "logicalOrExpression"
	// hql.g:414:1: logicalOrExpression : logicalAndExpression ( OR ^ logicalAndExpression )* ;
	public final hqlParser.logicalOrExpression_return logicalOrExpression() throws RecognitionException {
		hqlParser.logicalOrExpression_return retval = new hqlParser.logicalOrExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token OR156=null;
		ParserRuleReturnScope logicalAndExpression155 =null;
		ParserRuleReturnScope logicalAndExpression157 =null;

		CommonTree OR156_tree=null;

		try {
			// hql.g:415:2: ( logicalAndExpression ( OR ^ logicalAndExpression )* )
			// hql.g:415:4: logicalAndExpression ( OR ^ logicalAndExpression )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_logicalAndExpression_in_logicalOrExpression1890);
			logicalAndExpression155=logicalAndExpression();
			state._fsp--;

			adaptor.addChild(root_0, logicalAndExpression155.getTree());

			// hql.g:415:25: ( OR ^ logicalAndExpression )*
			loop51:
			while (true) {
				int alt51=2;
				int LA51_0 = input.LA(1);
				if ( (LA51_0==OR) ) {
					alt51=1;
				}

				switch (alt51) {
				case 1 :
					// hql.g:415:27: OR ^ logicalAndExpression
					{
					OR156=(Token)match(input,OR,FOLLOW_OR_in_logicalOrExpression1894);
					OR156_tree = (CommonTree)adaptor.create(OR156);
					root_0 = (CommonTree)adaptor.becomeRoot(OR156_tree, root_0);

					pushFollow(FOLLOW_logicalAndExpression_in_logicalOrExpression1897);
					logicalAndExpression157=logicalAndExpression();
					state._fsp--;

					adaptor.addChild(root_0, logicalAndExpression157.getTree());

					}
					break;

				default :
					break loop51;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "logicalOrExpression"


	public static class logicalAndExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "logicalAndExpression"
	// hql.g:419:1: logicalAndExpression : negatedExpression ( AND ^ negatedExpression )* ;
	public final hqlParser.logicalAndExpression_return logicalAndExpression() throws RecognitionException {
		hqlParser.logicalAndExpression_return retval = new hqlParser.logicalAndExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token AND159=null;
		ParserRuleReturnScope negatedExpression158 =null;
		ParserRuleReturnScope negatedExpression160 =null;

		CommonTree AND159_tree=null;

		try {
			// hql.g:420:2: ( negatedExpression ( AND ^ negatedExpression )* )
			// hql.g:420:4: negatedExpression ( AND ^ negatedExpression )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_negatedExpression_in_logicalAndExpression1912);
			negatedExpression158=negatedExpression();
			state._fsp--;

			adaptor.addChild(root_0, negatedExpression158.getTree());

			// hql.g:420:22: ( AND ^ negatedExpression )*
			loop52:
			while (true) {
				int alt52=2;
				int LA52_0 = input.LA(1);
				if ( (LA52_0==AND) ) {
					alt52=1;
				}

				switch (alt52) {
				case 1 :
					// hql.g:420:24: AND ^ negatedExpression
					{
					AND159=(Token)match(input,AND,FOLLOW_AND_in_logicalAndExpression1916);
					AND159_tree = (CommonTree)adaptor.create(AND159);
					root_0 = (CommonTree)adaptor.becomeRoot(AND159_tree, root_0);

					pushFollow(FOLLOW_negatedExpression_in_logicalAndExpression1919);
					negatedExpression160=negatedExpression();
					state._fsp--;

					adaptor.addChild(root_0, negatedExpression160.getTree());

					}
					break;

				default :
					break loop52;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "logicalAndExpression"


	public static class negatedExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "negatedExpression"
	// hql.g:425:1: negatedExpression : ( NOT x= negatedExpression -> ^() | equalityExpression -> ^( equalityExpression ) );
	public final hqlParser.negatedExpression_return negatedExpression() throws RecognitionException {
		hqlParser.negatedExpression_return retval = new hqlParser.negatedExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token NOT161=null;
		ParserRuleReturnScope x =null;
		ParserRuleReturnScope equalityExpression162 =null;

		CommonTree NOT161_tree=null;
		RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
		RewriteRuleSubtreeStream stream_equalityExpression=new RewriteRuleSubtreeStream(adaptor,"rule equalityExpression");
		RewriteRuleSubtreeStream stream_negatedExpression=new RewriteRuleSubtreeStream(adaptor,"rule negatedExpression");

		 WeakKeywords();
		try {
			// hql.g:427:2: ( NOT x= negatedExpression -> ^() | equalityExpression -> ^( equalityExpression ) )
			int alt53=2;
			int LA53_0 = input.LA(1);
			if ( (LA53_0==NOT) ) {
				alt53=1;
			}
			else if ( (LA53_0==ALL||LA53_0==ANY||LA53_0==AVG||LA53_0==BNOT||LA53_0==CASE||LA53_0==COLON||LA53_0==COUNT||LA53_0==ELEMENTS||LA53_0==EMPTY||LA53_0==EXISTS||LA53_0==FALSE||LA53_0==IDENT||LA53_0==INDICES||LA53_0==MAX||(LA53_0 >= MIN && LA53_0 <= MINUS)||(LA53_0 >= NULL && LA53_0 <= NUM_LONG)||LA53_0==OPEN||(LA53_0 >= PARAM && LA53_0 <= PLUS)||LA53_0==QUOTED_String||LA53_0==SOME||LA53_0==SUM||LA53_0==TRUE) ) {
				alt53=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 53, 0, input);
				throw nvae;
			}

			switch (alt53) {
				case 1 :
					// hql.g:427:4: NOT x= negatedExpression
					{
					NOT161=(Token)match(input,NOT,FOLLOW_NOT_in_negatedExpression1940);
					stream_NOT.add(NOT161);

					pushFollow(FOLLOW_negatedExpression_in_negatedExpression1944);
					x=negatedExpression();
					state._fsp--;

					stream_negatedExpression.add(x.getTree());
					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 428:3: -> ^()
					{
						// hql.g:428:6: ^()
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(NegateNode((x!=null?((CommonTree)x.getTree()):null)), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// hql.g:429:4: equalityExpression
					{
					pushFollow(FOLLOW_equalityExpression_in_negatedExpression1957);
					equalityExpression162=equalityExpression();
					state._fsp--;

					stream_equalityExpression.add(equalityExpression162.getTree());
					// AST REWRITE
					// elements: equalityExpression
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 430:3: -> ^( equalityExpression )
					{
						// hql.g:430:6: ^( equalityExpression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_equalityExpression.nextNode(), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "negatedExpression"


	public static class equalityExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "equalityExpression"
	// hql.g:436:1: equalityExpression : x= relationalExpression ( ( EQ ^|isx= IS ^ ( NOT !)? | NE ^|ne= SQL_NE ^) y= relationalExpression )* ;
	public final hqlParser.equalityExpression_return equalityExpression() throws RecognitionException {
		hqlParser.equalityExpression_return retval = new hqlParser.equalityExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token isx=null;
		Token ne=null;
		Token EQ163=null;
		Token NOT164=null;
		Token NE165=null;
		ParserRuleReturnScope x =null;
		ParserRuleReturnScope y =null;

		CommonTree isx_tree=null;
		CommonTree ne_tree=null;
		CommonTree EQ163_tree=null;
		CommonTree NOT164_tree=null;
		CommonTree NE165_tree=null;

		try {
			// hql.g:441:2: (x= relationalExpression ( ( EQ ^|isx= IS ^ ( NOT !)? | NE ^|ne= SQL_NE ^) y= relationalExpression )* )
			// hql.g:441:4: x= relationalExpression ( ( EQ ^|isx= IS ^ ( NOT !)? | NE ^|ne= SQL_NE ^) y= relationalExpression )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_relationalExpression_in_equalityExpression1987);
			x=relationalExpression();
			state._fsp--;

			adaptor.addChild(root_0, x.getTree());

			// hql.g:441:27: ( ( EQ ^|isx= IS ^ ( NOT !)? | NE ^|ne= SQL_NE ^) y= relationalExpression )*
			loop56:
			while (true) {
				int alt56=2;
				int LA56_0 = input.LA(1);
				if ( (LA56_0==EQ||LA56_0==IS||LA56_0==NE||LA56_0==SQL_NE) ) {
					alt56=1;
				}

				switch (alt56) {
				case 1 :
					// hql.g:442:3: ( EQ ^|isx= IS ^ ( NOT !)? | NE ^|ne= SQL_NE ^) y= relationalExpression
					{
					// hql.g:442:3: ( EQ ^|isx= IS ^ ( NOT !)? | NE ^|ne= SQL_NE ^)
					int alt55=4;
					switch ( input.LA(1) ) {
					case EQ:
						{
						alt55=1;
						}
						break;
					case IS:
						{
						alt55=2;
						}
						break;
					case NE:
						{
						alt55=3;
						}
						break;
					case SQL_NE:
						{
						alt55=4;
						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 55, 0, input);
						throw nvae;
					}
					switch (alt55) {
						case 1 :
							// hql.g:442:5: EQ ^
							{
							EQ163=(Token)match(input,EQ,FOLLOW_EQ_in_equalityExpression1995);
							EQ163_tree = (CommonTree)adaptor.create(EQ163);
							root_0 = (CommonTree)adaptor.becomeRoot(EQ163_tree, root_0);

							}
							break;
						case 2 :
							// hql.g:443:5: isx= IS ^ ( NOT !)?
							{
							isx=(Token)match(input,IS,FOLLOW_IS_in_equalityExpression2004);
							isx_tree = (CommonTree)adaptor.create(isx);
							root_0 = (CommonTree)adaptor.becomeRoot(isx_tree, root_0);

							 isx.setType(EQ);
							// hql.g:443:35: ( NOT !)?
							int alt54=2;
							int LA54_0 = input.LA(1);
							if ( (LA54_0==NOT) ) {
								alt54=1;
							}
							switch (alt54) {
								case 1 :
									// hql.g:443:36: NOT !
									{
									NOT164=(Token)match(input,NOT,FOLLOW_NOT_in_equalityExpression2010);
									 isx.setType(NE);
									}
									break;

							}

							}
							break;
						case 3 :
							// hql.g:444:5: NE ^
							{
							NE165=(Token)match(input,NE,FOLLOW_NE_in_equalityExpression2022);
							NE165_tree = (CommonTree)adaptor.create(NE165);
							root_0 = (CommonTree)adaptor.becomeRoot(NE165_tree, root_0);

							}
							break;
						case 4 :
							// hql.g:445:5: ne= SQL_NE ^
							{
							ne=(Token)match(input,SQL_NE,FOLLOW_SQL_NE_in_equalityExpression2031);
							ne_tree = (CommonTree)adaptor.create(ne);
							root_0 = (CommonTree)adaptor.becomeRoot(ne_tree, root_0);

							 ne.setType(NE);
							}
							break;

					}

					pushFollow(FOLLOW_relationalExpression_in_equalityExpression2042);
					y=relationalExpression();
					state._fsp--;

					adaptor.addChild(root_0, y.getTree());

					}
					break;

				default :
					break loop56;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


						// Post process the equality expression to clean up 'is null', etc.
						retval.tree = ProcessEqualityExpression(retval.tree);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "equalityExpression"


	public static class relationalExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "relationalExpression"
	// hql.g:453:1: relationalExpression : concatenation ( ( ( ( LT ^| GT ^| LE ^| GE ^) bitwiseNotExpression )* ) | (n= NOT !)? ( (i= IN ^ inList ) | (b= BETWEEN ^ betweenList ) | (l= LIKE ^ concatenation likeEscape ) | ( MEMBER ! ( OF !)? p= path !) ) ) ;
	public final hqlParser.relationalExpression_return relationalExpression() throws RecognitionException {
		hqlParser.relationalExpression_return retval = new hqlParser.relationalExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token n=null;
		Token i=null;
		Token b=null;
		Token l=null;
		Token LT167=null;
		Token GT168=null;
		Token LE169=null;
		Token GE170=null;
		Token MEMBER176=null;
		Token OF177=null;
		ParserRuleReturnScope p =null;
		ParserRuleReturnScope concatenation166 =null;
		ParserRuleReturnScope bitwiseNotExpression171 =null;
		ParserRuleReturnScope inList172 =null;
		ParserRuleReturnScope betweenList173 =null;
		ParserRuleReturnScope concatenation174 =null;
		ParserRuleReturnScope likeEscape175 =null;

		CommonTree n_tree=null;
		CommonTree i_tree=null;
		CommonTree b_tree=null;
		CommonTree l_tree=null;
		CommonTree LT167_tree=null;
		CommonTree GT168_tree=null;
		CommonTree LE169_tree=null;
		CommonTree GE170_tree=null;
		CommonTree MEMBER176_tree=null;
		CommonTree OF177_tree=null;

		try {
			// hql.g:454:2: ( concatenation ( ( ( ( LT ^| GT ^| LE ^| GE ^) bitwiseNotExpression )* ) | (n= NOT !)? ( (i= IN ^ inList ) | (b= BETWEEN ^ betweenList ) | (l= LIKE ^ concatenation likeEscape ) | ( MEMBER ! ( OF !)? p= path !) ) ) )
			// hql.g:454:4: concatenation ( ( ( ( LT ^| GT ^| LE ^| GE ^) bitwiseNotExpression )* ) | (n= NOT !)? ( (i= IN ^ inList ) | (b= BETWEEN ^ betweenList ) | (l= LIKE ^ concatenation likeEscape ) | ( MEMBER ! ( OF !)? p= path !) ) )
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_concatenation_in_relationalExpression2059);
			concatenation166=concatenation();
			state._fsp--;

			adaptor.addChild(root_0, concatenation166.getTree());

			// hql.g:454:18: ( ( ( ( LT ^| GT ^| LE ^| GE ^) bitwiseNotExpression )* ) | (n= NOT !)? ( (i= IN ^ inList ) | (b= BETWEEN ^ betweenList ) | (l= LIKE ^ concatenation likeEscape ) | ( MEMBER ! ( OF !)? p= path !) ) )
			int alt62=2;
			int LA62_0 = input.LA(1);
			if ( (LA62_0==EOF||LA62_0==AND||(LA62_0 >= AS && LA62_0 <= ASCENDING)||(LA62_0 >= CLOSE && LA62_0 <= CLOSE_BRACKET)||LA62_0==COMMA||LA62_0==DESCENDING||LA62_0==ELSE||(LA62_0 >= END && LA62_0 <= EQ)||(LA62_0 >= FROM && LA62_0 <= HAVING)||LA62_0==INNER||LA62_0==IS||(LA62_0 >= JOIN && LA62_0 <= LE)||LA62_0==LEFT||LA62_0==LT||LA62_0==NE||(LA62_0 >= OR && LA62_0 <= ORDER)||LA62_0==RIGHT||LA62_0==SKIP||LA62_0==SQL_NE||(LA62_0 >= TAKE && LA62_0 <= THEN)||LA62_0==UNION||(LA62_0 >= WHEN && LA62_0 <= WHERE)||(LA62_0 >= 133 && LA62_0 <= 134)) ) {
				alt62=1;
			}
			else if ( (LA62_0==BETWEEN||LA62_0==IN||LA62_0==LIKE||LA62_0==MEMBER||LA62_0==NOT) ) {
				alt62=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 62, 0, input);
				throw nvae;
			}

			switch (alt62) {
				case 1 :
					// hql.g:455:3: ( ( ( LT ^| GT ^| LE ^| GE ^) bitwiseNotExpression )* )
					{
					// hql.g:455:3: ( ( ( LT ^| GT ^| LE ^| GE ^) bitwiseNotExpression )* )
					// hql.g:455:5: ( ( LT ^| GT ^| LE ^| GE ^) bitwiseNotExpression )*
					{
					// hql.g:455:5: ( ( LT ^| GT ^| LE ^| GE ^) bitwiseNotExpression )*
					loop58:
					while (true) {
						int alt58=2;
						int LA58_0 = input.LA(1);
						if ( (LA58_0==GE||LA58_0==GT||LA58_0==LE||LA58_0==LT) ) {
							alt58=1;
						}

						switch (alt58) {
						case 1 :
							// hql.g:455:7: ( LT ^| GT ^| LE ^| GE ^) bitwiseNotExpression
							{
							// hql.g:455:7: ( LT ^| GT ^| LE ^| GE ^)
							int alt57=4;
							switch ( input.LA(1) ) {
							case LT:
								{
								alt57=1;
								}
								break;
							case GT:
								{
								alt57=2;
								}
								break;
							case LE:
								{
								alt57=3;
								}
								break;
							case GE:
								{
								alt57=4;
								}
								break;
							default:
								NoViableAltException nvae =
									new NoViableAltException("", 57, 0, input);
								throw nvae;
							}
							switch (alt57) {
								case 1 :
									// hql.g:455:9: LT ^
									{
									LT167=(Token)match(input,LT,FOLLOW_LT_in_relationalExpression2071);
									LT167_tree = (CommonTree)adaptor.create(LT167);
									root_0 = (CommonTree)adaptor.becomeRoot(LT167_tree, root_0);

									}
									break;
								case 2 :
									// hql.g:455:15: GT ^
									{
									GT168=(Token)match(input,GT,FOLLOW_GT_in_relationalExpression2076);
									GT168_tree = (CommonTree)adaptor.create(GT168);
									root_0 = (CommonTree)adaptor.becomeRoot(GT168_tree, root_0);

									}
									break;
								case 3 :
									// hql.g:455:21: LE ^
									{
									LE169=(Token)match(input,LE,FOLLOW_LE_in_relationalExpression2081);
									LE169_tree = (CommonTree)adaptor.create(LE169);
									root_0 = (CommonTree)adaptor.becomeRoot(LE169_tree, root_0);

									}
									break;
								case 4 :
									// hql.g:455:27: GE ^
									{
									GE170=(Token)match(input,GE,FOLLOW_GE_in_relationalExpression2086);
									GE170_tree = (CommonTree)adaptor.create(GE170);
									root_0 = (CommonTree)adaptor.becomeRoot(GE170_tree, root_0);

									}
									break;

							}

							pushFollow(FOLLOW_bitwiseNotExpression_in_relationalExpression2091);
							bitwiseNotExpression171=bitwiseNotExpression();
							state._fsp--;

							adaptor.addChild(root_0, bitwiseNotExpression171.getTree());

							}
							break;

						default :
							break loop58;
						}
					}

					}

					}
					break;
				case 2 :
					// hql.g:457:5: (n= NOT !)? ( (i= IN ^ inList ) | (b= BETWEEN ^ betweenList ) | (l= LIKE ^ concatenation likeEscape ) | ( MEMBER ! ( OF !)? p= path !) )
					{
					// hql.g:457:5: (n= NOT !)?
					int alt59=2;
					int LA59_0 = input.LA(1);
					if ( (LA59_0==NOT) ) {
						alt59=1;
					}
					switch (alt59) {
						case 1 :
							// hql.g:457:6: n= NOT !
							{
							n=(Token)match(input,NOT,FOLLOW_NOT_in_relationalExpression2108);
							}
							break;

					}

					// hql.g:457:15: ( (i= IN ^ inList ) | (b= BETWEEN ^ betweenList ) | (l= LIKE ^ concatenation likeEscape ) | ( MEMBER ! ( OF !)? p= path !) )
					int alt61=4;
					switch ( input.LA(1) ) {
					case IN:
						{
						alt61=1;
						}
						break;
					case BETWEEN:
						{
						alt61=2;
						}
						break;
					case LIKE:
						{
						alt61=3;
						}
						break;
					case MEMBER:
						{
						alt61=4;
						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 61, 0, input);
						throw nvae;
					}
					switch (alt61) {
						case 1 :
							// hql.g:460:4: (i= IN ^ inList )
							{
							// hql.g:460:4: (i= IN ^ inList )
							// hql.g:460:5: i= IN ^ inList
							{
							i=(Token)match(input,IN,FOLLOW_IN_in_relationalExpression2129);
							i_tree = (CommonTree)adaptor.create(i);
							root_0 = (CommonTree)adaptor.becomeRoot(i_tree, root_0);


												i.setType( (n == null) ? IN : NOT_IN );
												i.setText( (n == null) ? "in" : "not in" );

							pushFollow(FOLLOW_inList_in_relationalExpression2138);
							inList172=inList();
							state._fsp--;

							adaptor.addChild(root_0, inList172.getTree());

							}

							}
							break;
						case 2 :
							// hql.g:465:6: (b= BETWEEN ^ betweenList )
							{
							// hql.g:465:6: (b= BETWEEN ^ betweenList )
							// hql.g:465:7: b= BETWEEN ^ betweenList
							{
							b=(Token)match(input,BETWEEN,FOLLOW_BETWEEN_in_relationalExpression2149);
							b_tree = (CommonTree)adaptor.create(b);
							root_0 = (CommonTree)adaptor.becomeRoot(b_tree, root_0);


												b.setType( (n == null) ? BETWEEN : NOT_BETWEEN );
												b.setText( (n == null) ? "between" : "not between" );

							pushFollow(FOLLOW_betweenList_in_relationalExpression2158);
							betweenList173=betweenList();
							state._fsp--;

							adaptor.addChild(root_0, betweenList173.getTree());

							}

							}
							break;
						case 3 :
							// hql.g:470:6: (l= LIKE ^ concatenation likeEscape )
							{
							// hql.g:470:6: (l= LIKE ^ concatenation likeEscape )
							// hql.g:470:7: l= LIKE ^ concatenation likeEscape
							{
							l=(Token)match(input,LIKE,FOLLOW_LIKE_in_relationalExpression2170);
							l_tree = (CommonTree)adaptor.create(l);
							root_0 = (CommonTree)adaptor.becomeRoot(l_tree, root_0);


												l.setType( (n == null) ? LIKE : NOT_LIKE );
												l.setText( (n == null) ? "like" : "not like" );

							pushFollow(FOLLOW_concatenation_in_relationalExpression2179);
							concatenation174=concatenation();
							state._fsp--;

							adaptor.addChild(root_0, concatenation174.getTree());

							pushFollow(FOLLOW_likeEscape_in_relationalExpression2181);
							likeEscape175=likeEscape();
							state._fsp--;

							adaptor.addChild(root_0, likeEscape175.getTree());

							}

							}
							break;
						case 4 :
							// hql.g:475:6: ( MEMBER ! ( OF !)? p= path !)
							{
							// hql.g:475:6: ( MEMBER ! ( OF !)? p= path !)
							// hql.g:475:7: MEMBER ! ( OF !)? p= path !
							{
							MEMBER176=(Token)match(input,MEMBER,FOLLOW_MEMBER_in_relationalExpression2190);
							// hql.g:475:15: ( OF !)?
							int alt60=2;
							int LA60_0 = input.LA(1);
							if ( (LA60_0==OF) ) {
								alt60=1;
							}
							switch (alt60) {
								case 1 :
									// hql.g:475:16: OF !
									{
									OF177=(Token)match(input,OF,FOLLOW_OF_in_relationalExpression2194);
									}
									break;

							}

							pushFollow(FOLLOW_path_in_relationalExpression2201);
							p=path();
							state._fsp--;


											root_0 = ProcessMemberOf(n,(p!=null?((CommonTree)p.getTree()):null), root_0);

							}

							}
							break;

					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "relationalExpression"


	public static class likeEscape_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "likeEscape"
	// hql.g:482:1: likeEscape : ( ESCAPE ^ concatenation )? ;
	public final hqlParser.likeEscape_return likeEscape() throws RecognitionException {
		hqlParser.likeEscape_return retval = new hqlParser.likeEscape_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ESCAPE178=null;
		ParserRuleReturnScope concatenation179 =null;

		CommonTree ESCAPE178_tree=null;

		try {
			// hql.g:483:2: ( ( ESCAPE ^ concatenation )? )
			// hql.g:483:4: ( ESCAPE ^ concatenation )?
			{
			root_0 = (CommonTree)adaptor.nil();


			// hql.g:483:4: ( ESCAPE ^ concatenation )?
			int alt63=2;
			int LA63_0 = input.LA(1);
			if ( (LA63_0==ESCAPE) ) {
				alt63=1;
			}
			switch (alt63) {
				case 1 :
					// hql.g:483:5: ESCAPE ^ concatenation
					{
					ESCAPE178=(Token)match(input,ESCAPE,FOLLOW_ESCAPE_in_likeEscape2228);
					ESCAPE178_tree = (CommonTree)adaptor.create(ESCAPE178);
					root_0 = (CommonTree)adaptor.becomeRoot(ESCAPE178_tree, root_0);

					pushFollow(FOLLOW_concatenation_in_likeEscape2231);
					concatenation179=concatenation();
					state._fsp--;

					adaptor.addChild(root_0, concatenation179.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "likeEscape"


	public static class inList_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "inList"
	// hql.g:486:1: inList : compoundExpr -> ^( IN_LIST[\"inList\"] compoundExpr ) ;
	public final hqlParser.inList_return inList() throws RecognitionException {
		hqlParser.inList_return retval = new hqlParser.inList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope compoundExpr180 =null;

		RewriteRuleSubtreeStream stream_compoundExpr=new RewriteRuleSubtreeStream(adaptor,"rule compoundExpr");

		try {
			// hql.g:487:2: ( compoundExpr -> ^( IN_LIST[\"inList\"] compoundExpr ) )
			// hql.g:487:4: compoundExpr
			{
			pushFollow(FOLLOW_compoundExpr_in_inList2244);
			compoundExpr180=compoundExpr();
			state._fsp--;

			stream_compoundExpr.add(compoundExpr180.getTree());
			// AST REWRITE
			// elements: compoundExpr
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 488:2: -> ^( IN_LIST[\"inList\"] compoundExpr )
			{
				// hql.g:488:5: ^( IN_LIST[\"inList\"] compoundExpr )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(IN_LIST, "inList"), root_1);
				adaptor.addChild(root_1, stream_compoundExpr.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "inList"


	public static class betweenList_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "betweenList"
	// hql.g:491:1: betweenList : concatenation AND ! concatenation ;
	public final hqlParser.betweenList_return betweenList() throws RecognitionException {
		hqlParser.betweenList_return retval = new hqlParser.betweenList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token AND182=null;
		ParserRuleReturnScope concatenation181 =null;
		ParserRuleReturnScope concatenation183 =null;

		CommonTree AND182_tree=null;

		try {
			// hql.g:492:2: ( concatenation AND ! concatenation )
			// hql.g:492:4: concatenation AND ! concatenation
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_concatenation_in_betweenList2265);
			concatenation181=concatenation();
			state._fsp--;

			adaptor.addChild(root_0, concatenation181.getTree());

			AND182=(Token)match(input,AND,FOLLOW_AND_in_betweenList2267);
			pushFollow(FOLLOW_concatenation_in_betweenList2270);
			concatenation183=concatenation();
			state._fsp--;

			adaptor.addChild(root_0, concatenation183.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "betweenList"


	public static class concatenation_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "concatenation"
	// hql.g:496:1: concatenation : a= bitwiseNotExpression (c= CONCAT ^ bitwiseNotExpression ( CONCAT ! bitwiseNotExpression )* )? ;
	public final hqlParser.concatenation_return concatenation() throws RecognitionException {
		hqlParser.concatenation_return retval = new hqlParser.concatenation_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token c=null;
		Token CONCAT185=null;
		ParserRuleReturnScope a =null;
		ParserRuleReturnScope bitwiseNotExpression184 =null;
		ParserRuleReturnScope bitwiseNotExpression186 =null;

		CommonTree c_tree=null;
		CommonTree CONCAT185_tree=null;

		try {
			// hql.g:513:2: (a= bitwiseNotExpression (c= CONCAT ^ bitwiseNotExpression ( CONCAT ! bitwiseNotExpression )* )? )
			// hql.g:513:4: a= bitwiseNotExpression (c= CONCAT ^ bitwiseNotExpression ( CONCAT ! bitwiseNotExpression )* )?
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_bitwiseNotExpression_in_concatenation2289);
			a=bitwiseNotExpression();
			state._fsp--;

			adaptor.addChild(root_0, a.getTree());

			// hql.g:514:2: (c= CONCAT ^ bitwiseNotExpression ( CONCAT ! bitwiseNotExpression )* )?
			int alt65=2;
			int LA65_0 = input.LA(1);
			if ( (LA65_0==CONCAT) ) {
				alt65=1;
			}
			switch (alt65) {
				case 1 :
					// hql.g:514:4: c= CONCAT ^ bitwiseNotExpression ( CONCAT ! bitwiseNotExpression )*
					{
					c=(Token)match(input,CONCAT,FOLLOW_CONCAT_in_concatenation2297);
					c_tree = (CommonTree)adaptor.create(c);
					root_0 = (CommonTree)adaptor.becomeRoot(c_tree, root_0);

					 c.setType( EXPR_LIST ); c.setText( "concatList" );
					pushFollow(FOLLOW_bitwiseNotExpression_in_concatenation2306);
					bitwiseNotExpression184=bitwiseNotExpression();
					state._fsp--;

					adaptor.addChild(root_0, bitwiseNotExpression184.getTree());

					// hql.g:516:4: ( CONCAT ! bitwiseNotExpression )*
					loop64:
					while (true) {
						int alt64=2;
						int LA64_0 = input.LA(1);
						if ( (LA64_0==CONCAT) ) {
							alt64=1;
						}

						switch (alt64) {
						case 1 :
							// hql.g:516:6: CONCAT ! bitwiseNotExpression
							{
							CONCAT185=(Token)match(input,CONCAT,FOLLOW_CONCAT_in_concatenation2313);
							pushFollow(FOLLOW_bitwiseNotExpression_in_concatenation2316);
							bitwiseNotExpression186=bitwiseNotExpression();
							state._fsp--;

							adaptor.addChild(root_0, bitwiseNotExpression186.getTree());

							}
							break;

						default :
							break loop64;
						}
					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


			   if (c != null)
			   {
			      assert false : "PLEASE CHECK THAT IT WORKS !!!";
			      CommonTree mc = (CommonTree) adaptor.create( METHOD_CALL, "||" );
			      CommonTree concat = (CommonTree) adaptor.create(IDENT, "concat");
			      mc.addChild( concat );
			      mc.addChild( retval.getTree() );
			      retval.tree = mc ;
			      //ASTNode mc = (ASTNode) adaptor.create(METHOD_CALL, "||");
			      //ASTNode concat = (ASTNode) adaptor.create(IDENT, "concat");
			      //mc.AddChild(concat);
			      //mc.AddChild((IASTNode) retval.Tree);
			      //retval.Tree = mc;
			   }

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "concatenation"


	public static class bitwiseNotExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "bitwiseNotExpression"
	// hql.g:521:1: bitwiseNotExpression : ( ( BNOT ^ bitwiseOrExpression ) | bitwiseOrExpression );
	public final hqlParser.bitwiseNotExpression_return bitwiseNotExpression() throws RecognitionException {
		hqlParser.bitwiseNotExpression_return retval = new hqlParser.bitwiseNotExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token BNOT187=null;
		ParserRuleReturnScope bitwiseOrExpression188 =null;
		ParserRuleReturnScope bitwiseOrExpression189 =null;

		CommonTree BNOT187_tree=null;

		try {
			// hql.g:522:2: ( ( BNOT ^ bitwiseOrExpression ) | bitwiseOrExpression )
			int alt66=2;
			int LA66_0 = input.LA(1);
			if ( (LA66_0==BNOT) ) {
				alt66=1;
			}
			else if ( (LA66_0==ALL||LA66_0==ANY||LA66_0==AVG||LA66_0==CASE||LA66_0==COLON||LA66_0==COUNT||LA66_0==ELEMENTS||LA66_0==EMPTY||LA66_0==EXISTS||LA66_0==FALSE||LA66_0==IDENT||LA66_0==INDICES||LA66_0==MAX||(LA66_0 >= MIN && LA66_0 <= MINUS)||(LA66_0 >= NULL && LA66_0 <= NUM_LONG)||LA66_0==OPEN||(LA66_0 >= PARAM && LA66_0 <= PLUS)||LA66_0==QUOTED_String||LA66_0==SOME||LA66_0==SUM||LA66_0==TRUE) ) {
				alt66=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 66, 0, input);
				throw nvae;
			}

			switch (alt66) {
				case 1 :
					// hql.g:522:4: ( BNOT ^ bitwiseOrExpression )
					{
					root_0 = (CommonTree)adaptor.nil();


					// hql.g:522:4: ( BNOT ^ bitwiseOrExpression )
					// hql.g:522:5: BNOT ^ bitwiseOrExpression
					{
					BNOT187=(Token)match(input,BNOT,FOLLOW_BNOT_in_bitwiseNotExpression2340);
					BNOT187_tree = (CommonTree)adaptor.create(BNOT187);
					root_0 = (CommonTree)adaptor.becomeRoot(BNOT187_tree, root_0);

					pushFollow(FOLLOW_bitwiseOrExpression_in_bitwiseNotExpression2343);
					bitwiseOrExpression188=bitwiseOrExpression();
					state._fsp--;

					adaptor.addChild(root_0, bitwiseOrExpression188.getTree());

					}

					}
					break;
				case 2 :
					// hql.g:523:4: bitwiseOrExpression
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_bitwiseOrExpression_in_bitwiseNotExpression2349);
					bitwiseOrExpression189=bitwiseOrExpression();
					state._fsp--;

					adaptor.addChild(root_0, bitwiseOrExpression189.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "bitwiseNotExpression"


	public static class bitwiseOrExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "bitwiseOrExpression"
	// hql.g:526:1: bitwiseOrExpression : bitwiseXOrExpression ( BOR ^ bitwiseXOrExpression )* ;
	public final hqlParser.bitwiseOrExpression_return bitwiseOrExpression() throws RecognitionException {
		hqlParser.bitwiseOrExpression_return retval = new hqlParser.bitwiseOrExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token BOR191=null;
		ParserRuleReturnScope bitwiseXOrExpression190 =null;
		ParserRuleReturnScope bitwiseXOrExpression192 =null;

		CommonTree BOR191_tree=null;

		try {
			// hql.g:527:2: ( bitwiseXOrExpression ( BOR ^ bitwiseXOrExpression )* )
			// hql.g:527:4: bitwiseXOrExpression ( BOR ^ bitwiseXOrExpression )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_bitwiseXOrExpression_in_bitwiseOrExpression2361);
			bitwiseXOrExpression190=bitwiseXOrExpression();
			state._fsp--;

			adaptor.addChild(root_0, bitwiseXOrExpression190.getTree());

			// hql.g:527:25: ( BOR ^ bitwiseXOrExpression )*
			loop67:
			while (true) {
				int alt67=2;
				int LA67_0 = input.LA(1);
				if ( (LA67_0==BOR) ) {
					alt67=1;
				}

				switch (alt67) {
				case 1 :
					// hql.g:527:26: BOR ^ bitwiseXOrExpression
					{
					BOR191=(Token)match(input,BOR,FOLLOW_BOR_in_bitwiseOrExpression2364);
					BOR191_tree = (CommonTree)adaptor.create(BOR191);
					root_0 = (CommonTree)adaptor.becomeRoot(BOR191_tree, root_0);

					pushFollow(FOLLOW_bitwiseXOrExpression_in_bitwiseOrExpression2367);
					bitwiseXOrExpression192=bitwiseXOrExpression();
					state._fsp--;

					adaptor.addChild(root_0, bitwiseXOrExpression192.getTree());

					}
					break;

				default :
					break loop67;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "bitwiseOrExpression"


	public static class bitwiseXOrExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "bitwiseXOrExpression"
	// hql.g:530:1: bitwiseXOrExpression : bitwiseAndExpression ( BXOR ^ bitwiseAndExpression )* ;
	public final hqlParser.bitwiseXOrExpression_return bitwiseXOrExpression() throws RecognitionException {
		hqlParser.bitwiseXOrExpression_return retval = new hqlParser.bitwiseXOrExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token BXOR194=null;
		ParserRuleReturnScope bitwiseAndExpression193 =null;
		ParserRuleReturnScope bitwiseAndExpression195 =null;

		CommonTree BXOR194_tree=null;

		try {
			// hql.g:531:2: ( bitwiseAndExpression ( BXOR ^ bitwiseAndExpression )* )
			// hql.g:531:4: bitwiseAndExpression ( BXOR ^ bitwiseAndExpression )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_bitwiseAndExpression_in_bitwiseXOrExpression2381);
			bitwiseAndExpression193=bitwiseAndExpression();
			state._fsp--;

			adaptor.addChild(root_0, bitwiseAndExpression193.getTree());

			// hql.g:531:25: ( BXOR ^ bitwiseAndExpression )*
			loop68:
			while (true) {
				int alt68=2;
				int LA68_0 = input.LA(1);
				if ( (LA68_0==BXOR) ) {
					alt68=1;
				}

				switch (alt68) {
				case 1 :
					// hql.g:531:26: BXOR ^ bitwiseAndExpression
					{
					BXOR194=(Token)match(input,BXOR,FOLLOW_BXOR_in_bitwiseXOrExpression2384);
					BXOR194_tree = (CommonTree)adaptor.create(BXOR194);
					root_0 = (CommonTree)adaptor.becomeRoot(BXOR194_tree, root_0);

					pushFollow(FOLLOW_bitwiseAndExpression_in_bitwiseXOrExpression2387);
					bitwiseAndExpression195=bitwiseAndExpression();
					state._fsp--;

					adaptor.addChild(root_0, bitwiseAndExpression195.getTree());

					}
					break;

				default :
					break loop68;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "bitwiseXOrExpression"


	public static class bitwiseAndExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "bitwiseAndExpression"
	// hql.g:534:1: bitwiseAndExpression : additiveExpression ( BAND ^ additiveExpression )* ;
	public final hqlParser.bitwiseAndExpression_return bitwiseAndExpression() throws RecognitionException {
		hqlParser.bitwiseAndExpression_return retval = new hqlParser.bitwiseAndExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token BAND197=null;
		ParserRuleReturnScope additiveExpression196 =null;
		ParserRuleReturnScope additiveExpression198 =null;

		CommonTree BAND197_tree=null;

		try {
			// hql.g:535:2: ( additiveExpression ( BAND ^ additiveExpression )* )
			// hql.g:535:4: additiveExpression ( BAND ^ additiveExpression )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_additiveExpression_in_bitwiseAndExpression2401);
			additiveExpression196=additiveExpression();
			state._fsp--;

			adaptor.addChild(root_0, additiveExpression196.getTree());

			// hql.g:535:23: ( BAND ^ additiveExpression )*
			loop69:
			while (true) {
				int alt69=2;
				int LA69_0 = input.LA(1);
				if ( (LA69_0==BAND) ) {
					alt69=1;
				}

				switch (alt69) {
				case 1 :
					// hql.g:535:24: BAND ^ additiveExpression
					{
					BAND197=(Token)match(input,BAND,FOLLOW_BAND_in_bitwiseAndExpression2404);
					BAND197_tree = (CommonTree)adaptor.create(BAND197);
					root_0 = (CommonTree)adaptor.becomeRoot(BAND197_tree, root_0);

					pushFollow(FOLLOW_additiveExpression_in_bitwiseAndExpression2407);
					additiveExpression198=additiveExpression();
					state._fsp--;

					adaptor.addChild(root_0, additiveExpression198.getTree());

					}
					break;

				default :
					break loop69;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "bitwiseAndExpression"


	public static class additiveExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "additiveExpression"
	// hql.g:539:1: additiveExpression : multiplyExpression ( ( PLUS ^| MINUS ^) multiplyExpression )* ;
	public final hqlParser.additiveExpression_return additiveExpression() throws RecognitionException {
		hqlParser.additiveExpression_return retval = new hqlParser.additiveExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token PLUS200=null;
		Token MINUS201=null;
		ParserRuleReturnScope multiplyExpression199 =null;
		ParserRuleReturnScope multiplyExpression202 =null;

		CommonTree PLUS200_tree=null;
		CommonTree MINUS201_tree=null;

		try {
			// hql.g:540:2: ( multiplyExpression ( ( PLUS ^| MINUS ^) multiplyExpression )* )
			// hql.g:540:4: multiplyExpression ( ( PLUS ^| MINUS ^) multiplyExpression )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_multiplyExpression_in_additiveExpression2421);
			multiplyExpression199=multiplyExpression();
			state._fsp--;

			adaptor.addChild(root_0, multiplyExpression199.getTree());

			// hql.g:540:23: ( ( PLUS ^| MINUS ^) multiplyExpression )*
			loop71:
			while (true) {
				int alt71=2;
				int LA71_0 = input.LA(1);
				if ( (LA71_0==MINUS||LA71_0==PLUS) ) {
					alt71=1;
				}

				switch (alt71) {
				case 1 :
					// hql.g:540:25: ( PLUS ^| MINUS ^) multiplyExpression
					{
					// hql.g:540:25: ( PLUS ^| MINUS ^)
					int alt70=2;
					int LA70_0 = input.LA(1);
					if ( (LA70_0==PLUS) ) {
						alt70=1;
					}
					else if ( (LA70_0==MINUS) ) {
						alt70=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 70, 0, input);
						throw nvae;
					}

					switch (alt70) {
						case 1 :
							// hql.g:540:27: PLUS ^
							{
							PLUS200=(Token)match(input,PLUS,FOLLOW_PLUS_in_additiveExpression2427);
							PLUS200_tree = (CommonTree)adaptor.create(PLUS200);
							root_0 = (CommonTree)adaptor.becomeRoot(PLUS200_tree, root_0);

							}
							break;
						case 2 :
							// hql.g:540:35: MINUS ^
							{
							MINUS201=(Token)match(input,MINUS,FOLLOW_MINUS_in_additiveExpression2432);
							MINUS201_tree = (CommonTree)adaptor.create(MINUS201);
							root_0 = (CommonTree)adaptor.becomeRoot(MINUS201_tree, root_0);

							}
							break;

					}

					pushFollow(FOLLOW_multiplyExpression_in_additiveExpression2437);
					multiplyExpression202=multiplyExpression();
					state._fsp--;

					adaptor.addChild(root_0, multiplyExpression202.getTree());

					}
					break;

				default :
					break loop71;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "additiveExpression"


	public static class multiplyExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "multiplyExpression"
	// hql.g:544:1: multiplyExpression : unaryExpression ( ( STAR ^| DIV ^) unaryExpression )* ;
	public final hqlParser.multiplyExpression_return multiplyExpression() throws RecognitionException {
		hqlParser.multiplyExpression_return retval = new hqlParser.multiplyExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token STAR204=null;
		Token DIV205=null;
		ParserRuleReturnScope unaryExpression203 =null;
		ParserRuleReturnScope unaryExpression206 =null;

		CommonTree STAR204_tree=null;
		CommonTree DIV205_tree=null;

		try {
			// hql.g:545:2: ( unaryExpression ( ( STAR ^| DIV ^) unaryExpression )* )
			// hql.g:545:4: unaryExpression ( ( STAR ^| DIV ^) unaryExpression )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_unaryExpression_in_multiplyExpression2452);
			unaryExpression203=unaryExpression();
			state._fsp--;

			adaptor.addChild(root_0, unaryExpression203.getTree());

			// hql.g:545:20: ( ( STAR ^| DIV ^) unaryExpression )*
			loop73:
			while (true) {
				int alt73=2;
				int LA73_0 = input.LA(1);
				if ( (LA73_0==DIV||LA73_0==STAR) ) {
					alt73=1;
				}

				switch (alt73) {
				case 1 :
					// hql.g:545:22: ( STAR ^| DIV ^) unaryExpression
					{
					// hql.g:545:22: ( STAR ^| DIV ^)
					int alt72=2;
					int LA72_0 = input.LA(1);
					if ( (LA72_0==STAR) ) {
						alt72=1;
					}
					else if ( (LA72_0==DIV) ) {
						alt72=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 72, 0, input);
						throw nvae;
					}

					switch (alt72) {
						case 1 :
							// hql.g:545:24: STAR ^
							{
							STAR204=(Token)match(input,STAR,FOLLOW_STAR_in_multiplyExpression2458);
							STAR204_tree = (CommonTree)adaptor.create(STAR204);
							root_0 = (CommonTree)adaptor.becomeRoot(STAR204_tree, root_0);

							}
							break;
						case 2 :
							// hql.g:545:32: DIV ^
							{
							DIV205=(Token)match(input,DIV,FOLLOW_DIV_in_multiplyExpression2463);
							DIV205_tree = (CommonTree)adaptor.create(DIV205);
							root_0 = (CommonTree)adaptor.becomeRoot(DIV205_tree, root_0);

							}
							break;

					}

					pushFollow(FOLLOW_unaryExpression_in_multiplyExpression2468);
					unaryExpression206=unaryExpression();
					state._fsp--;

					adaptor.addChild(root_0, unaryExpression206.getTree());

					}
					break;

				default :
					break loop73;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "multiplyExpression"


	public static class unaryExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "unaryExpression"
	// hql.g:549:1: unaryExpression : (m= MINUS mu= unaryExpression -> ^( UNARY_MINUS[$m] $mu) |p= PLUS pu= unaryExpression -> ^( UNARY_PLUS[$p] $pu) |c= caseExpression -> ^( $c) |q= quantifiedExpression -> ^( $q) |a= atom -> ^( $a) );
	public final hqlParser.unaryExpression_return unaryExpression() throws RecognitionException {
		hqlParser.unaryExpression_return retval = new hqlParser.unaryExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token m=null;
		Token p=null;
		ParserRuleReturnScope mu =null;
		ParserRuleReturnScope pu =null;
		ParserRuleReturnScope c =null;
		ParserRuleReturnScope q =null;
		ParserRuleReturnScope a =null;

		CommonTree m_tree=null;
		CommonTree p_tree=null;
		RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
		RewriteRuleTokenStream stream_MINUS=new RewriteRuleTokenStream(adaptor,"token MINUS");
		RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");
		RewriteRuleSubtreeStream stream_caseExpression=new RewriteRuleSubtreeStream(adaptor,"rule caseExpression");
		RewriteRuleSubtreeStream stream_quantifiedExpression=new RewriteRuleSubtreeStream(adaptor,"rule quantifiedExpression");
		RewriteRuleSubtreeStream stream_unaryExpression=new RewriteRuleSubtreeStream(adaptor,"rule unaryExpression");

		try {
			// hql.g:550:2: (m= MINUS mu= unaryExpression -> ^( UNARY_MINUS[$m] $mu) |p= PLUS pu= unaryExpression -> ^( UNARY_PLUS[$p] $pu) |c= caseExpression -> ^( $c) |q= quantifiedExpression -> ^( $q) |a= atom -> ^( $a) )
			int alt74=5;
			switch ( input.LA(1) ) {
			case MINUS:
				{
				alt74=1;
				}
				break;
			case PLUS:
				{
				alt74=2;
				}
				break;
			case CASE:
				{
				alt74=3;
				}
				break;
			case ALL:
			case ANY:
			case EXISTS:
			case SOME:
				{
				alt74=4;
				}
				break;
			case AVG:
			case COLON:
			case COUNT:
			case ELEMENTS:
			case EMPTY:
			case FALSE:
			case IDENT:
			case INDICES:
			case MAX:
			case MIN:
			case NULL:
			case NUM_DECIMAL:
			case NUM_DOUBLE:
			case NUM_FLOAT:
			case NUM_INT:
			case NUM_LONG:
			case OPEN:
			case PARAM:
			case QUOTED_String:
			case SUM:
			case TRUE:
				{
				alt74=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 74, 0, input);
				throw nvae;
			}
			switch (alt74) {
				case 1 :
					// hql.g:550:4: m= MINUS mu= unaryExpression
					{
					m=(Token)match(input,MINUS,FOLLOW_MINUS_in_unaryExpression2486);
					stream_MINUS.add(m);

					pushFollow(FOLLOW_unaryExpression_in_unaryExpression2490);
					mu=unaryExpression();
					state._fsp--;

					stream_unaryExpression.add(mu.getTree());
					// AST REWRITE
					// elements: mu
					// token labels:
					// rule labels: retval, mu
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_mu=new RewriteRuleSubtreeStream(adaptor,"rule mu",mu!=null?mu.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 550:31: -> ^( UNARY_MINUS[$m] $mu)
					{
						// hql.g:550:34: ^( UNARY_MINUS[$m] $mu)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(UNARY_MINUS, m), root_1);
						adaptor.addChild(root_1, stream_mu.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// hql.g:551:4: p= PLUS pu= unaryExpression
					{
					p=(Token)match(input,PLUS,FOLLOW_PLUS_in_unaryExpression2507);
					stream_PLUS.add(p);

					pushFollow(FOLLOW_unaryExpression_in_unaryExpression2511);
					pu=unaryExpression();
					state._fsp--;

					stream_unaryExpression.add(pu.getTree());
					// AST REWRITE
					// elements: pu
					// token labels:
					// rule labels: retval, pu
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_pu=new RewriteRuleSubtreeStream(adaptor,"rule pu",pu!=null?pu.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 551:30: -> ^( UNARY_PLUS[$p] $pu)
					{
						// hql.g:551:33: ^( UNARY_PLUS[$p] $pu)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(UNARY_PLUS, p), root_1);
						adaptor.addChild(root_1, stream_pu.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// hql.g:552:4: c= caseExpression
					{
					pushFollow(FOLLOW_caseExpression_in_unaryExpression2528);
					c=caseExpression();
					state._fsp--;

					stream_caseExpression.add(c.getTree());
					// AST REWRITE
					// elements: c
					// token labels:
					// rule labels: retval, c
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_c=new RewriteRuleSubtreeStream(adaptor,"rule c",c!=null?c.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 552:21: -> ^( $c)
					{
						// hql.g:552:24: ^( $c)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_c.nextNode(), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 4 :
					// hql.g:553:4: q= quantifiedExpression
					{
					pushFollow(FOLLOW_quantifiedExpression_in_unaryExpression2542);
					q=quantifiedExpression();
					state._fsp--;

					stream_quantifiedExpression.add(q.getTree());
					// AST REWRITE
					// elements: q
					// token labels:
					// rule labels: retval, q
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_q=new RewriteRuleSubtreeStream(adaptor,"rule q",q!=null?q.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 553:27: -> ^( $q)
					{
						// hql.g:553:30: ^( $q)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_q.nextNode(), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 5 :
					// hql.g:554:4: a= atom
					{
					pushFollow(FOLLOW_atom_in_unaryExpression2557);
					a=atom();
					state._fsp--;

					stream_atom.add(a.getTree());
					// AST REWRITE
					// elements: a
					// token labels:
					// rule labels: retval, a
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_a=new RewriteRuleSubtreeStream(adaptor,"rule a",a!=null?a.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 554:11: -> ^( $a)
					{
						// hql.g:554:14: ^( $a)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_a.nextNode(), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "unaryExpression"


	public static class caseExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "caseExpression"
	// hql.g:557:1: caseExpression : ( CASE ( whenClause )+ ( elseClause )? END -> ^( CASE ( whenClause )+ ( elseClause )? ) | CASE unaryExpression ( altWhenClause )+ ( elseClause )? END -> ^( CASE2 unaryExpression ( altWhenClause )+ ( elseClause )? ) );
	public final hqlParser.caseExpression_return caseExpression() throws RecognitionException {
		hqlParser.caseExpression_return retval = new hqlParser.caseExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token CASE207=null;
		Token END210=null;
		Token CASE211=null;
		Token END215=null;
		ParserRuleReturnScope whenClause208 =null;
		ParserRuleReturnScope elseClause209 =null;
		ParserRuleReturnScope unaryExpression212 =null;
		ParserRuleReturnScope altWhenClause213 =null;
		ParserRuleReturnScope elseClause214 =null;

		CommonTree CASE207_tree=null;
		CommonTree END210_tree=null;
		CommonTree CASE211_tree=null;
		CommonTree END215_tree=null;
		RewriteRuleTokenStream stream_END=new RewriteRuleTokenStream(adaptor,"token END");
		RewriteRuleTokenStream stream_CASE=new RewriteRuleTokenStream(adaptor,"token CASE");
		RewriteRuleSubtreeStream stream_whenClause=new RewriteRuleSubtreeStream(adaptor,"rule whenClause");
		RewriteRuleSubtreeStream stream_unaryExpression=new RewriteRuleSubtreeStream(adaptor,"rule unaryExpression");
		RewriteRuleSubtreeStream stream_altWhenClause=new RewriteRuleSubtreeStream(adaptor,"rule altWhenClause");
		RewriteRuleSubtreeStream stream_elseClause=new RewriteRuleSubtreeStream(adaptor,"rule elseClause");

		try {
			// hql.g:558:2: ( CASE ( whenClause )+ ( elseClause )? END -> ^( CASE ( whenClause )+ ( elseClause )? ) | CASE unaryExpression ( altWhenClause )+ ( elseClause )? END -> ^( CASE2 unaryExpression ( altWhenClause )+ ( elseClause )? ) )
			int alt79=2;
			int LA79_0 = input.LA(1);
			if ( (LA79_0==CASE) ) {
				int LA79_1 = input.LA(2);
				if ( (LA79_1==WHEN) ) {
					alt79=1;
				}
				else if ( (LA79_1==ALL||LA79_1==ANY||LA79_1==AVG||LA79_1==CASE||LA79_1==COLON||LA79_1==COUNT||LA79_1==ELEMENTS||LA79_1==EMPTY||LA79_1==EXISTS||LA79_1==FALSE||LA79_1==IDENT||LA79_1==INDICES||LA79_1==MAX||(LA79_1 >= MIN && LA79_1 <= MINUS)||(LA79_1 >= NULL && LA79_1 <= NUM_LONG)||LA79_1==OPEN||(LA79_1 >= PARAM && LA79_1 <= PLUS)||LA79_1==QUOTED_String||LA79_1==SOME||LA79_1==SUM||LA79_1==TRUE) ) {
					alt79=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 79, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 79, 0, input);
				throw nvae;
			}

			switch (alt79) {
				case 1 :
					// hql.g:558:4: CASE ( whenClause )+ ( elseClause )? END
					{
					CASE207=(Token)match(input,CASE,FOLLOW_CASE_in_caseExpression2576);
					stream_CASE.add(CASE207);

					// hql.g:558:9: ( whenClause )+
					int cnt75=0;
					loop75:
					while (true) {
						int alt75=2;
						int LA75_0 = input.LA(1);
						if ( (LA75_0==WHEN) ) {
							alt75=1;
						}

						switch (alt75) {
						case 1 :
							// hql.g:558:10: whenClause
							{
							pushFollow(FOLLOW_whenClause_in_caseExpression2579);
							whenClause208=whenClause();
							state._fsp--;

							stream_whenClause.add(whenClause208.getTree());
							}
							break;

						default :
							if ( cnt75 >= 1 ) break loop75;
							EarlyExitException eee = new EarlyExitException(75, input);
							throw eee;
						}
						cnt75++;
					}

					// hql.g:558:23: ( elseClause )?
					int alt76=2;
					int LA76_0 = input.LA(1);
					if ( (LA76_0==ELSE) ) {
						alt76=1;
					}
					switch (alt76) {
						case 1 :
							// hql.g:558:24: elseClause
							{
							pushFollow(FOLLOW_elseClause_in_caseExpression2584);
							elseClause209=elseClause();
							state._fsp--;

							stream_elseClause.add(elseClause209.getTree());
							}
							break;

					}

					END210=(Token)match(input,END,FOLLOW_END_in_caseExpression2588);
					stream_END.add(END210);

					// AST REWRITE
					// elements: CASE, elseClause, whenClause
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 559:3: -> ^( CASE ( whenClause )+ ( elseClause )? )
					{
						// hql.g:559:6: ^( CASE ( whenClause )+ ( elseClause )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_CASE.nextNode(), root_1);
						if ( !(stream_whenClause.hasNext()) ) {
							throw new RewriteEarlyExitException();
						}
						while ( stream_whenClause.hasNext() ) {
							adaptor.addChild(root_1, stream_whenClause.nextTree());
						}
						stream_whenClause.reset();

						// hql.g:559:25: ( elseClause )?
						if ( stream_elseClause.hasNext() ) {
							adaptor.addChild(root_1, stream_elseClause.nextTree());
						}
						stream_elseClause.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// hql.g:560:4: CASE unaryExpression ( altWhenClause )+ ( elseClause )? END
					{
					CASE211=(Token)match(input,CASE,FOLLOW_CASE_in_caseExpression2608);
					stream_CASE.add(CASE211);

					pushFollow(FOLLOW_unaryExpression_in_caseExpression2610);
					unaryExpression212=unaryExpression();
					state._fsp--;

					stream_unaryExpression.add(unaryExpression212.getTree());
					// hql.g:560:25: ( altWhenClause )+
					int cnt77=0;
					loop77:
					while (true) {
						int alt77=2;
						int LA77_0 = input.LA(1);
						if ( (LA77_0==WHEN) ) {
							alt77=1;
						}

						switch (alt77) {
						case 1 :
							// hql.g:560:26: altWhenClause
							{
							pushFollow(FOLLOW_altWhenClause_in_caseExpression2613);
							altWhenClause213=altWhenClause();
							state._fsp--;

							stream_altWhenClause.add(altWhenClause213.getTree());
							}
							break;

						default :
							if ( cnt77 >= 1 ) break loop77;
							EarlyExitException eee = new EarlyExitException(77, input);
							throw eee;
						}
						cnt77++;
					}

					// hql.g:560:42: ( elseClause )?
					int alt78=2;
					int LA78_0 = input.LA(1);
					if ( (LA78_0==ELSE) ) {
						alt78=1;
					}
					switch (alt78) {
						case 1 :
							// hql.g:560:43: elseClause
							{
							pushFollow(FOLLOW_elseClause_in_caseExpression2618);
							elseClause214=elseClause();
							state._fsp--;

							stream_elseClause.add(elseClause214.getTree());
							}
							break;

					}

					END215=(Token)match(input,END,FOLLOW_END_in_caseExpression2622);
					stream_END.add(END215);

					// AST REWRITE
					// elements: altWhenClause, elseClause, unaryExpression
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 561:3: -> ^( CASE2 unaryExpression ( altWhenClause )+ ( elseClause )? )
					{
						// hql.g:561:6: ^( CASE2 unaryExpression ( altWhenClause )+ ( elseClause )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(CASE2, "CASE2"), root_1);
						adaptor.addChild(root_1, stream_unaryExpression.nextTree());
						if ( !(stream_altWhenClause.hasNext()) ) {
							throw new RewriteEarlyExitException();
						}
						while ( stream_altWhenClause.hasNext() ) {
							adaptor.addChild(root_1, stream_altWhenClause.nextTree());
						}
						stream_altWhenClause.reset();

						// hql.g:561:45: ( elseClause )?
						if ( stream_elseClause.hasNext() ) {
							adaptor.addChild(root_1, stream_elseClause.nextTree());
						}
						stream_elseClause.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "caseExpression"


	public static class whenClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "whenClause"
	// hql.g:564:1: whenClause : ( WHEN ^ logicalExpression THEN ! expression ) ;
	public final hqlParser.whenClause_return whenClause() throws RecognitionException {
		hqlParser.whenClause_return retval = new hqlParser.whenClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WHEN216=null;
		Token THEN218=null;
		ParserRuleReturnScope logicalExpression217 =null;
		ParserRuleReturnScope expression219 =null;

		CommonTree WHEN216_tree=null;
		CommonTree THEN218_tree=null;

		try {
			// hql.g:565:2: ( ( WHEN ^ logicalExpression THEN ! expression ) )
			// hql.g:565:4: ( WHEN ^ logicalExpression THEN ! expression )
			{
			root_0 = (CommonTree)adaptor.nil();


			// hql.g:565:4: ( WHEN ^ logicalExpression THEN ! expression )
			// hql.g:565:5: WHEN ^ logicalExpression THEN ! expression
			{
			WHEN216=(Token)match(input,WHEN,FOLLOW_WHEN_in_whenClause2651);
			WHEN216_tree = (CommonTree)adaptor.create(WHEN216);
			root_0 = (CommonTree)adaptor.becomeRoot(WHEN216_tree, root_0);

			pushFollow(FOLLOW_logicalExpression_in_whenClause2654);
			logicalExpression217=logicalExpression();
			state._fsp--;

			adaptor.addChild(root_0, logicalExpression217.getTree());

			THEN218=(Token)match(input,THEN,FOLLOW_THEN_in_whenClause2656);
			pushFollow(FOLLOW_expression_in_whenClause2659);
			expression219=expression();
			state._fsp--;

			adaptor.addChild(root_0, expression219.getTree());

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "whenClause"


	public static class altWhenClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "altWhenClause"
	// hql.g:568:1: altWhenClause : ( WHEN ^ unaryExpression THEN ! expression ) ;
	public final hqlParser.altWhenClause_return altWhenClause() throws RecognitionException {
		hqlParser.altWhenClause_return retval = new hqlParser.altWhenClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token WHEN220=null;
		Token THEN222=null;
		ParserRuleReturnScope unaryExpression221 =null;
		ParserRuleReturnScope expression223 =null;

		CommonTree WHEN220_tree=null;
		CommonTree THEN222_tree=null;

		try {
			// hql.g:569:2: ( ( WHEN ^ unaryExpression THEN ! expression ) )
			// hql.g:569:4: ( WHEN ^ unaryExpression THEN ! expression )
			{
			root_0 = (CommonTree)adaptor.nil();


			// hql.g:569:4: ( WHEN ^ unaryExpression THEN ! expression )
			// hql.g:569:5: WHEN ^ unaryExpression THEN ! expression
			{
			WHEN220=(Token)match(input,WHEN,FOLLOW_WHEN_in_altWhenClause2673);
			WHEN220_tree = (CommonTree)adaptor.create(WHEN220);
			root_0 = (CommonTree)adaptor.becomeRoot(WHEN220_tree, root_0);

			pushFollow(FOLLOW_unaryExpression_in_altWhenClause2676);
			unaryExpression221=unaryExpression();
			state._fsp--;

			adaptor.addChild(root_0, unaryExpression221.getTree());

			THEN222=(Token)match(input,THEN,FOLLOW_THEN_in_altWhenClause2678);
			pushFollow(FOLLOW_expression_in_altWhenClause2681);
			expression223=expression();
			state._fsp--;

			adaptor.addChild(root_0, expression223.getTree());

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "altWhenClause"


	public static class elseClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "elseClause"
	// hql.g:572:1: elseClause : ( ELSE ^ expression ) ;
	public final hqlParser.elseClause_return elseClause() throws RecognitionException {
		hqlParser.elseClause_return retval = new hqlParser.elseClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ELSE224=null;
		ParserRuleReturnScope expression225 =null;

		CommonTree ELSE224_tree=null;

		try {
			// hql.g:573:2: ( ( ELSE ^ expression ) )
			// hql.g:573:4: ( ELSE ^ expression )
			{
			root_0 = (CommonTree)adaptor.nil();


			// hql.g:573:4: ( ELSE ^ expression )
			// hql.g:573:5: ELSE ^ expression
			{
			ELSE224=(Token)match(input,ELSE,FOLLOW_ELSE_in_elseClause2695);
			ELSE224_tree = (CommonTree)adaptor.create(ELSE224);
			root_0 = (CommonTree)adaptor.becomeRoot(ELSE224_tree, root_0);

			pushFollow(FOLLOW_expression_in_elseClause2698);
			expression225=expression();
			state._fsp--;

			adaptor.addChild(root_0, expression225.getTree());

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "elseClause"


	public static class quantifiedExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "quantifiedExpression"
	// hql.g:576:1: quantifiedExpression : ( SOME ^| EXISTS ^| ALL ^| ANY ^) ( identifier | collectionExpr | ( OPEN ! ( subQuery ) CLOSE !) ) ;
	public final hqlParser.quantifiedExpression_return quantifiedExpression() throws RecognitionException {
		hqlParser.quantifiedExpression_return retval = new hqlParser.quantifiedExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token SOME226=null;
		Token EXISTS227=null;
		Token ALL228=null;
		Token ANY229=null;
		Token OPEN232=null;
		Token CLOSE234=null;
		ParserRuleReturnScope identifier230 =null;
		ParserRuleReturnScope collectionExpr231 =null;
		ParserRuleReturnScope subQuery233 =null;

		CommonTree SOME226_tree=null;
		CommonTree EXISTS227_tree=null;
		CommonTree ALL228_tree=null;
		CommonTree ANY229_tree=null;
		CommonTree OPEN232_tree=null;
		CommonTree CLOSE234_tree=null;

		try {
			// hql.g:577:2: ( ( SOME ^| EXISTS ^| ALL ^| ANY ^) ( identifier | collectionExpr | ( OPEN ! ( subQuery ) CLOSE !) ) )
			// hql.g:577:4: ( SOME ^| EXISTS ^| ALL ^| ANY ^) ( identifier | collectionExpr | ( OPEN ! ( subQuery ) CLOSE !) )
			{
			root_0 = (CommonTree)adaptor.nil();


			// hql.g:577:4: ( SOME ^| EXISTS ^| ALL ^| ANY ^)
			int alt80=4;
			switch ( input.LA(1) ) {
			case SOME:
				{
				alt80=1;
				}
				break;
			case EXISTS:
				{
				alt80=2;
				}
				break;
			case ALL:
				{
				alt80=3;
				}
				break;
			case ANY:
				{
				alt80=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 80, 0, input);
				throw nvae;
			}
			switch (alt80) {
				case 1 :
					// hql.g:577:6: SOME ^
					{
					SOME226=(Token)match(input,SOME,FOLLOW_SOME_in_quantifiedExpression2713);
					SOME226_tree = (CommonTree)adaptor.create(SOME226);
					root_0 = (CommonTree)adaptor.becomeRoot(SOME226_tree, root_0);

					}
					break;
				case 2 :
					// hql.g:577:14: EXISTS ^
					{
					EXISTS227=(Token)match(input,EXISTS,FOLLOW_EXISTS_in_quantifiedExpression2718);
					EXISTS227_tree = (CommonTree)adaptor.create(EXISTS227);
					root_0 = (CommonTree)adaptor.becomeRoot(EXISTS227_tree, root_0);

					}
					break;
				case 3 :
					// hql.g:577:24: ALL ^
					{
					ALL228=(Token)match(input,ALL,FOLLOW_ALL_in_quantifiedExpression2723);
					ALL228_tree = (CommonTree)adaptor.create(ALL228);
					root_0 = (CommonTree)adaptor.becomeRoot(ALL228_tree, root_0);

					}
					break;
				case 4 :
					// hql.g:577:31: ANY ^
					{
					ANY229=(Token)match(input,ANY,FOLLOW_ANY_in_quantifiedExpression2728);
					ANY229_tree = (CommonTree)adaptor.create(ANY229);
					root_0 = (CommonTree)adaptor.becomeRoot(ANY229_tree, root_0);

					}
					break;

			}

			// hql.g:578:2: ( identifier | collectionExpr | ( OPEN ! ( subQuery ) CLOSE !) )
			int alt81=3;
			switch ( input.LA(1) ) {
			case IDENT:
				{
				alt81=1;
				}
				break;
			case ELEMENTS:
			case INDICES:
				{
				alt81=2;
				}
				break;
			case OPEN:
				{
				alt81=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 81, 0, input);
				throw nvae;
			}
			switch (alt81) {
				case 1 :
					// hql.g:578:4: identifier
					{
					pushFollow(FOLLOW_identifier_in_quantifiedExpression2737);
					identifier230=identifier();
					state._fsp--;

					adaptor.addChild(root_0, identifier230.getTree());

					}
					break;
				case 2 :
					// hql.g:578:17: collectionExpr
					{
					pushFollow(FOLLOW_collectionExpr_in_quantifiedExpression2741);
					collectionExpr231=collectionExpr();
					state._fsp--;

					adaptor.addChild(root_0, collectionExpr231.getTree());

					}
					break;
				case 3 :
					// hql.g:578:34: ( OPEN ! ( subQuery ) CLOSE !)
					{
					// hql.g:578:34: ( OPEN ! ( subQuery ) CLOSE !)
					// hql.g:578:35: OPEN ! ( subQuery ) CLOSE !
					{
					OPEN232=(Token)match(input,OPEN,FOLLOW_OPEN_in_quantifiedExpression2746);
					// hql.g:578:41: ( subQuery )
					// hql.g:578:43: subQuery
					{
					pushFollow(FOLLOW_subQuery_in_quantifiedExpression2751);
					subQuery233=subQuery();
					state._fsp--;

					adaptor.addChild(root_0, subQuery233.getTree());

					}

					CLOSE234=(Token)match(input,CLOSE,FOLLOW_CLOSE_in_quantifiedExpression2755);
					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "quantifiedExpression"


	public static class atom_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "atom"
	// hql.g:584:1: atom : primaryExpression ( DOT ^ identifier ( options {greedy=true; } : (op= OPEN ^ exprList CLOSE !) )? |lb= OPEN_BRACKET ^ expression CLOSE_BRACKET !)* ;
	public final hqlParser.atom_return atom() throws RecognitionException {
		hqlParser.atom_return retval = new hqlParser.atom_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token op=null;
		Token lb=null;
		Token DOT236=null;
		Token CLOSE239=null;
		Token CLOSE_BRACKET241=null;
		ParserRuleReturnScope primaryExpression235 =null;
		ParserRuleReturnScope identifier237 =null;
		ParserRuleReturnScope exprList238 =null;
		ParserRuleReturnScope expression240 =null;

		CommonTree op_tree=null;
		CommonTree lb_tree=null;
		CommonTree DOT236_tree=null;
		CommonTree CLOSE239_tree=null;
		CommonTree CLOSE_BRACKET241_tree=null;

		try {
			// hql.g:585:3: ( primaryExpression ( DOT ^ identifier ( options {greedy=true; } : (op= OPEN ^ exprList CLOSE !) )? |lb= OPEN_BRACKET ^ expression CLOSE_BRACKET !)* )
			// hql.g:585:5: primaryExpression ( DOT ^ identifier ( options {greedy=true; } : (op= OPEN ^ exprList CLOSE !) )? |lb= OPEN_BRACKET ^ expression CLOSE_BRACKET !)*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_primaryExpression_in_atom2774);
			primaryExpression235=primaryExpression();
			state._fsp--;

			adaptor.addChild(root_0, primaryExpression235.getTree());

			// hql.g:586:3: ( DOT ^ identifier ( options {greedy=true; } : (op= OPEN ^ exprList CLOSE !) )? |lb= OPEN_BRACKET ^ expression CLOSE_BRACKET !)*
			loop83:
			while (true) {
				int alt83=3;
				int LA83_0 = input.LA(1);
				if ( (LA83_0==DOT) ) {
					alt83=1;
				}
				else if ( (LA83_0==OPEN_BRACKET) ) {
					alt83=2;
				}

				switch (alt83) {
				case 1 :
					// hql.g:587:4: DOT ^ identifier ( options {greedy=true; } : (op= OPEN ^ exprList CLOSE !) )?
					{
					DOT236=(Token)match(input,DOT,FOLLOW_DOT_in_atom2783);
					DOT236_tree = (CommonTree)adaptor.create(DOT236);
					root_0 = (CommonTree)adaptor.becomeRoot(DOT236_tree, root_0);

					pushFollow(FOLLOW_identifier_in_atom2786);
					identifier237=identifier();
					state._fsp--;

					adaptor.addChild(root_0, identifier237.getTree());

					// hql.g:588:5: ( options {greedy=true; } : (op= OPEN ^ exprList CLOSE !) )?
					int alt82=2;
					int LA82_0 = input.LA(1);
					if ( (LA82_0==OPEN) ) {
						alt82=1;
					}
					switch (alt82) {
						case 1 :
							// hql.g:589:6: (op= OPEN ^ exprList CLOSE !)
							{
							// hql.g:589:6: (op= OPEN ^ exprList CLOSE !)
							// hql.g:589:8: op= OPEN ^ exprList CLOSE !
							{
							op=(Token)match(input,OPEN,FOLLOW_OPEN_in_atom2814);
							op_tree = (CommonTree)adaptor.create(op);
							root_0 = (CommonTree)adaptor.becomeRoot(op_tree, root_0);

							op.setType( METHOD_CALL );
							pushFollow(FOLLOW_exprList_in_atom2819);
							exprList238=exprList();
							state._fsp--;

							adaptor.addChild(root_0, exprList238.getTree());

							CLOSE239=(Token)match(input,CLOSE,FOLLOW_CLOSE_in_atom2821);
							}

							}
							break;

					}

					}
					break;
				case 2 :
					// hql.g:590:5: lb= OPEN_BRACKET ^ expression CLOSE_BRACKET !
					{
					lb=(Token)match(input,OPEN_BRACKET,FOLLOW_OPEN_BRACKET_in_atom2835);
					lb_tree = (CommonTree)adaptor.create(lb);
					root_0 = (CommonTree)adaptor.becomeRoot(lb_tree, root_0);

					lb.setType( INDEX_OP );
					pushFollow(FOLLOW_expression_in_atom2840);
					expression240=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression240.getTree());

					CLOSE_BRACKET241=(Token)match(input,CLOSE_BRACKET,FOLLOW_CLOSE_BRACKET_in_atom2842);
					}
					break;

				default :
					break loop83;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "atom"


	public static class primaryExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "primaryExpression"
	// hql.g:595:1: primaryExpression : ( identPrimary ( options {greedy=true; } : DOT ^ 'class' )? | constant | COLON ^ identifier | OPEN ! ( expressionOrVector | subQuery ) CLOSE !| PARAM ^ ( NUM_INT )? );
	public final hqlParser.primaryExpression_return primaryExpression() throws RecognitionException {
		hqlParser.primaryExpression_return retval = new hqlParser.primaryExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token DOT243=null;
		Token string_literal244=null;
		Token COLON246=null;
		Token OPEN248=null;
		Token CLOSE251=null;
		Token PARAM252=null;
		Token NUM_INT253=null;
		ParserRuleReturnScope identPrimary242 =null;
		ParserRuleReturnScope constant245 =null;
		ParserRuleReturnScope identifier247 =null;
		ParserRuleReturnScope expressionOrVector249 =null;
		ParserRuleReturnScope subQuery250 =null;

		CommonTree DOT243_tree=null;
		CommonTree string_literal244_tree=null;
		CommonTree COLON246_tree=null;
		CommonTree OPEN248_tree=null;
		CommonTree CLOSE251_tree=null;
		CommonTree PARAM252_tree=null;
		CommonTree NUM_INT253_tree=null;

		try {
			// hql.g:596:2: ( identPrimary ( options {greedy=true; } : DOT ^ 'class' )? | constant | COLON ^ identifier | OPEN ! ( expressionOrVector | subQuery ) CLOSE !| PARAM ^ ( NUM_INT )? )
			int alt87=5;
			switch ( input.LA(1) ) {
			case AVG:
			case COUNT:
			case ELEMENTS:
			case IDENT:
			case INDICES:
			case MAX:
			case MIN:
			case SUM:
				{
				alt87=1;
				}
				break;
			case EMPTY:
			case FALSE:
			case NULL:
			case NUM_DECIMAL:
			case NUM_DOUBLE:
			case NUM_FLOAT:
			case NUM_INT:
			case NUM_LONG:
			case QUOTED_String:
			case TRUE:
				{
				alt87=2;
				}
				break;
			case COLON:
				{
				alt87=3;
				}
				break;
			case OPEN:
				{
				alt87=4;
				}
				break;
			case PARAM:
				{
				alt87=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 87, 0, input);
				throw nvae;
			}
			switch (alt87) {
				case 1 :
					// hql.g:596:6: identPrimary ( options {greedy=true; } : DOT ^ 'class' )?
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_identPrimary_in_primaryExpression2862);
					identPrimary242=identPrimary();
					state._fsp--;

					adaptor.addChild(root_0, identPrimary242.getTree());

					// hql.g:596:19: ( options {greedy=true; } : DOT ^ 'class' )?
					int alt84=2;
					int LA84_0 = input.LA(1);
					if ( (LA84_0==DOT) ) {
						int LA84_1 = input.LA(2);
						if ( (LA84_1==CLASS) ) {
							alt84=1;
						}
					}
					switch (alt84) {
						case 1 :
							// hql.g:596:46: DOT ^ 'class'
							{
							DOT243=(Token)match(input,DOT,FOLLOW_DOT_in_primaryExpression2875);
							DOT243_tree = (CommonTree)adaptor.create(DOT243);
							root_0 = (CommonTree)adaptor.becomeRoot(DOT243_tree, root_0);

							string_literal244=(Token)match(input,CLASS,FOLLOW_CLASS_in_primaryExpression2878);
							string_literal244_tree = (CommonTree)adaptor.create(string_literal244);
							adaptor.addChild(root_0, string_literal244_tree);

							}
							break;

					}

					}
					break;
				case 2 :
					// hql.g:597:6: constant
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_constant_in_primaryExpression2888);
					constant245=constant();
					state._fsp--;

					adaptor.addChild(root_0, constant245.getTree());

					}
					break;
				case 3 :
					// hql.g:598:6: COLON ^ identifier
					{
					root_0 = (CommonTree)adaptor.nil();


					COLON246=(Token)match(input,COLON,FOLLOW_COLON_in_primaryExpression2895);
					COLON246_tree = (CommonTree)adaptor.create(COLON246);
					root_0 = (CommonTree)adaptor.becomeRoot(COLON246_tree, root_0);

					pushFollow(FOLLOW_identifier_in_primaryExpression2898);
					identifier247=identifier();
					state._fsp--;

					adaptor.addChild(root_0, identifier247.getTree());

					}
					break;
				case 4 :
					// hql.g:600:6: OPEN ! ( expressionOrVector | subQuery ) CLOSE !
					{
					root_0 = (CommonTree)adaptor.nil();


					OPEN248=(Token)match(input,OPEN,FOLLOW_OPEN_in_primaryExpression2907);
					// hql.g:600:12: ( expressionOrVector | subQuery )
					int alt85=2;
					int LA85_0 = input.LA(1);
					if ( (LA85_0==ALL||LA85_0==ANY||LA85_0==AVG||LA85_0==BNOT||LA85_0==CASE||LA85_0==COLON||LA85_0==COUNT||LA85_0==ELEMENTS||LA85_0==EMPTY||LA85_0==EXISTS||LA85_0==FALSE||LA85_0==IDENT||LA85_0==INDICES||LA85_0==MAX||(LA85_0 >= MIN && LA85_0 <= MINUS)||LA85_0==NOT||(LA85_0 >= NULL && LA85_0 <= NUM_LONG)||LA85_0==OPEN||(LA85_0 >= PARAM && LA85_0 <= PLUS)||LA85_0==QUOTED_String||LA85_0==SOME||LA85_0==SUM||LA85_0==TRUE) ) {
						alt85=1;
					}
					else if ( (LA85_0==EOF||LA85_0==CLOSE||LA85_0==FROM||LA85_0==GROUP||LA85_0==HAVING||LA85_0==ORDER||LA85_0==SELECT||LA85_0==SKIP||LA85_0==TAKE||LA85_0==UNION||LA85_0==WHERE) ) {
						alt85=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 85, 0, input);
						throw nvae;
					}

					switch (alt85) {
						case 1 :
							// hql.g:600:13: expressionOrVector
							{
							pushFollow(FOLLOW_expressionOrVector_in_primaryExpression2911);
							expressionOrVector249=expressionOrVector();
							state._fsp--;

							adaptor.addChild(root_0, expressionOrVector249.getTree());

							}
							break;
						case 2 :
							// hql.g:600:34: subQuery
							{
							pushFollow(FOLLOW_subQuery_in_primaryExpression2915);
							subQuery250=subQuery();
							state._fsp--;

							adaptor.addChild(root_0, subQuery250.getTree());

							}
							break;

					}

					CLOSE251=(Token)match(input,CLOSE,FOLLOW_CLOSE_in_primaryExpression2918);
					}
					break;
				case 5 :
					// hql.g:601:6: PARAM ^ ( NUM_INT )?
					{
					root_0 = (CommonTree)adaptor.nil();


					PARAM252=(Token)match(input,PARAM,FOLLOW_PARAM_in_primaryExpression2926);
					PARAM252_tree = (CommonTree)adaptor.create(PARAM252);
					root_0 = (CommonTree)adaptor.becomeRoot(PARAM252_tree, root_0);

					// hql.g:601:13: ( NUM_INT )?
					int alt86=2;
					int LA86_0 = input.LA(1);
					if ( (LA86_0==NUM_INT) ) {
						alt86=1;
					}
					switch (alt86) {
						case 1 :
							// hql.g:601:14: NUM_INT
							{
							NUM_INT253=(Token)match(input,NUM_INT,FOLLOW_NUM_INT_in_primaryExpression2930);
							NUM_INT253_tree = (CommonTree)adaptor.create(NUM_INT253);
							adaptor.addChild(root_0, NUM_INT253_tree);

							}
							break;

					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "primaryExpression"


	public static class expressionOrVector_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "expressionOrVector"
	// hql.g:606:1: expressionOrVector : e= expression (v= vectorExpr )? -> {v != null}? ^( VECTOR_EXPR[\"{vector}\"] $e $v) -> ^( $e) ;
	public final hqlParser.expressionOrVector_return expressionOrVector() throws RecognitionException {
		hqlParser.expressionOrVector_return retval = new hqlParser.expressionOrVector_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope e =null;
		ParserRuleReturnScope v =null;

		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
		RewriteRuleSubtreeStream stream_vectorExpr=new RewriteRuleSubtreeStream(adaptor,"rule vectorExpr");

		try {
			// hql.g:607:2: (e= expression (v= vectorExpr )? -> {v != null}? ^( VECTOR_EXPR[\"{vector}\"] $e $v) -> ^( $e) )
			// hql.g:607:4: e= expression (v= vectorExpr )?
			{
			pushFollow(FOLLOW_expression_in_expressionOrVector2948);
			e=expression();
			state._fsp--;

			stream_expression.add(e.getTree());
			// hql.g:607:17: (v= vectorExpr )?
			int alt88=2;
			int LA88_0 = input.LA(1);
			if ( (LA88_0==COMMA) ) {
				alt88=1;
			}
			switch (alt88) {
				case 1 :
					// hql.g:607:19: v= vectorExpr
					{
					pushFollow(FOLLOW_vectorExpr_in_expressionOrVector2954);
					v=vectorExpr();
					state._fsp--;

					stream_vectorExpr.add(v.getTree());
					}
					break;

			}

			// AST REWRITE
			// elements: v, e, e
			// token labels:
			// rule labels: v, retval, e
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_v=new RewriteRuleSubtreeStream(adaptor,"rule v",v!=null?v.getTree():null);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_e=new RewriteRuleSubtreeStream(adaptor,"rule e",e!=null?e.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 608:2: -> {v != null}? ^( VECTOR_EXPR[\"{vector}\"] $e $v)
			if (v != null) {
				// hql.g:608:18: ^( VECTOR_EXPR[\"{vector}\"] $e $v)
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(VECTOR_EXPR, "{vector}"), root_1);
				adaptor.addChild(root_1, stream_e.nextTree());
				adaptor.addChild(root_1, stream_v.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}

			else // 609:2: -> ^( $e)
			{
				// hql.g:609:5: ^( $e)
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(stream_e.nextNode(), root_1);
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expressionOrVector"


	public static class vectorExpr_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "vectorExpr"
	// hql.g:612:1: vectorExpr : COMMA ! expression ( COMMA ! expression )* ;
	public final hqlParser.vectorExpr_return vectorExpr() throws RecognitionException {
		hqlParser.vectorExpr_return retval = new hqlParser.vectorExpr_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COMMA254=null;
		Token COMMA256=null;
		ParserRuleReturnScope expression255 =null;
		ParserRuleReturnScope expression257 =null;

		CommonTree COMMA254_tree=null;
		CommonTree COMMA256_tree=null;

		try {
			// hql.g:613:2: ( COMMA ! expression ( COMMA ! expression )* )
			// hql.g:613:4: COMMA ! expression ( COMMA ! expression )*
			{
			root_0 = (CommonTree)adaptor.nil();


			COMMA254=(Token)match(input,COMMA,FOLLOW_COMMA_in_vectorExpr2993);
			pushFollow(FOLLOW_expression_in_vectorExpr2996);
			expression255=expression();
			state._fsp--;

			adaptor.addChild(root_0, expression255.getTree());

			// hql.g:613:22: ( COMMA ! expression )*
			loop89:
			while (true) {
				int alt89=2;
				int LA89_0 = input.LA(1);
				if ( (LA89_0==COMMA) ) {
					alt89=1;
				}

				switch (alt89) {
				case 1 :
					// hql.g:613:23: COMMA ! expression
					{
					COMMA256=(Token)match(input,COMMA,FOLLOW_COMMA_in_vectorExpr2999);
					pushFollow(FOLLOW_expression_in_vectorExpr3002);
					expression257=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression257.getTree());

					}
					break;

				default :
					break loop89;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "vectorExpr"


	public static class identPrimary_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "identPrimary"
	// hql.g:619:1: identPrimary : ( identifier ( options {greedy=true; } : DOT ^ ( identifier |o= OBJECT ) )* ( (op= OPEN ^ exprList CLOSE !) )? | aggregate );
	public final hqlParser.identPrimary_return identPrimary() throws RecognitionException {
		hqlParser.identPrimary_return retval = new hqlParser.identPrimary_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token o=null;
		Token op=null;
		Token DOT259=null;
		Token CLOSE262=null;
		ParserRuleReturnScope identifier258 =null;
		ParserRuleReturnScope identifier260 =null;
		ParserRuleReturnScope exprList261 =null;
		ParserRuleReturnScope aggregate263 =null;

		CommonTree o_tree=null;
		CommonTree op_tree=null;
		CommonTree DOT259_tree=null;
		CommonTree CLOSE262_tree=null;

		try {
			// hql.g:620:2: ( identifier ( options {greedy=true; } : DOT ^ ( identifier |o= OBJECT ) )* ( (op= OPEN ^ exprList CLOSE !) )? | aggregate )
			int alt93=2;
			int LA93_0 = input.LA(1);
			if ( (LA93_0==IDENT) ) {
				alt93=1;
			}
			else if ( (LA93_0==AVG||LA93_0==COUNT||LA93_0==ELEMENTS||LA93_0==INDICES||LA93_0==MAX||LA93_0==MIN||LA93_0==SUM) ) {
				alt93=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 93, 0, input);
				throw nvae;
			}

			switch (alt93) {
				case 1 :
					// hql.g:620:4: identifier ( options {greedy=true; } : DOT ^ ( identifier |o= OBJECT ) )* ( (op= OPEN ^ exprList CLOSE !) )?
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_identifier_in_identPrimary3018);
					identifier258=identifier();
					state._fsp--;

					adaptor.addChild(root_0, identifier258.getTree());

					 HandleDotIdent();
					// hql.g:621:4: ( options {greedy=true; } : DOT ^ ( identifier |o= OBJECT ) )*
					loop91:
					while (true) {
						int alt91=2;
						int LA91_0 = input.LA(1);
						if ( (LA91_0==DOT) ) {
							int LA91_2 = input.LA(2);
							if ( (LA91_2==IDENT||LA91_2==OBJECT) ) {
								alt91=1;
							}

						}

						switch (alt91) {
						case 1 :
							// hql.g:621:31: DOT ^ ( identifier |o= OBJECT )
							{
							DOT259=(Token)match(input,DOT,FOLLOW_DOT_in_identPrimary3036);
							DOT259_tree = (CommonTree)adaptor.create(DOT259);
							root_0 = (CommonTree)adaptor.becomeRoot(DOT259_tree, root_0);

							// hql.g:621:36: ( identifier |o= OBJECT )
							int alt90=2;
							int LA90_0 = input.LA(1);
							if ( (LA90_0==IDENT) ) {
								alt90=1;
							}
							else if ( (LA90_0==OBJECT) ) {
								alt90=2;
							}

							else {
								NoViableAltException nvae =
									new NoViableAltException("", 90, 0, input);
								throw nvae;
							}

							switch (alt90) {
								case 1 :
									// hql.g:621:38: identifier
									{
									pushFollow(FOLLOW_identifier_in_identPrimary3041);
									identifier260=identifier();
									state._fsp--;

									adaptor.addChild(root_0, identifier260.getTree());

									}
									break;
								case 2 :
									// hql.g:621:51: o= OBJECT
									{
									o=(Token)match(input,OBJECT,FOLLOW_OBJECT_in_identPrimary3047);
									o_tree = (CommonTree)adaptor.create(o);
									adaptor.addChild(root_0, o_tree);

									 o.setType( IDENT );
									}
									break;

							}

							}
							break;

						default :
							break loop91;
						}
					}

					// hql.g:622:4: ( (op= OPEN ^ exprList CLOSE !) )?
					int alt92=2;
					int LA92_0 = input.LA(1);
					if ( (LA92_0==OPEN) ) {
						alt92=1;
					}
					switch (alt92) {
						case 1 :
							// hql.g:622:6: (op= OPEN ^ exprList CLOSE !)
							{
							// hql.g:622:6: (op= OPEN ^ exprList CLOSE !)
							// hql.g:622:8: op= OPEN ^ exprList CLOSE !
							{
							op=(Token)match(input,OPEN,FOLLOW_OPEN_in_identPrimary3065);
							op_tree = (CommonTree)adaptor.create(op);
							root_0 = (CommonTree)adaptor.becomeRoot(op_tree, root_0);

							 op.setType( METHOD_CALL );
							pushFollow(FOLLOW_exprList_in_identPrimary3070);
							exprList261=exprList();
							state._fsp--;

							adaptor.addChild(root_0, exprList261.getTree());

							CLOSE262=(Token)match(input,CLOSE,FOLLOW_CLOSE_in_identPrimary3072);
							}

							}
							break;

					}

					}
					break;
				case 2 :
					// hql.g:625:4: aggregate
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_aggregate_in_identPrimary3088);
					aggregate263=aggregate();
					state._fsp--;

					adaptor.addChild(root_0, aggregate263.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "identPrimary"


	public static class aggregate_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "aggregate"
	// hql.g:633:1: aggregate : ( (op= SUM |op= AVG |op= MAX |op= MIN ) OPEN additiveExpression CLOSE -> ^( AGGREGATE[$op] additiveExpression ) | COUNT OPEN (s= STAR |p= aggregateDistinctAll ) CLOSE -> {s == null}? ^( COUNT $p) -> ^( COUNT ^( ROW_STAR[\"*\"] ) ) | collectionExpr );
	public final hqlParser.aggregate_return aggregate() throws RecognitionException {
		hqlParser.aggregate_return retval = new hqlParser.aggregate_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token op=null;
		Token s=null;
		Token OPEN264=null;
		Token CLOSE266=null;
		Token COUNT267=null;
		Token OPEN268=null;
		Token CLOSE269=null;
		ParserRuleReturnScope p =null;
		ParserRuleReturnScope additiveExpression265 =null;
		ParserRuleReturnScope collectionExpr270 =null;

		CommonTree op_tree=null;
		CommonTree s_tree=null;
		CommonTree OPEN264_tree=null;
		CommonTree CLOSE266_tree=null;
		CommonTree COUNT267_tree=null;
		CommonTree OPEN268_tree=null;
		CommonTree CLOSE269_tree=null;
		RewriteRuleTokenStream stream_OPEN=new RewriteRuleTokenStream(adaptor,"token OPEN");
		RewriteRuleTokenStream stream_MAX=new RewriteRuleTokenStream(adaptor,"token MAX");
		RewriteRuleTokenStream stream_COUNT=new RewriteRuleTokenStream(adaptor,"token COUNT");
		RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
		RewriteRuleTokenStream stream_MIN=new RewriteRuleTokenStream(adaptor,"token MIN");
		RewriteRuleTokenStream stream_CLOSE=new RewriteRuleTokenStream(adaptor,"token CLOSE");
		RewriteRuleTokenStream stream_SUM=new RewriteRuleTokenStream(adaptor,"token SUM");
		RewriteRuleTokenStream stream_AVG=new RewriteRuleTokenStream(adaptor,"token AVG");
		RewriteRuleSubtreeStream stream_aggregateDistinctAll=new RewriteRuleSubtreeStream(adaptor,"rule aggregateDistinctAll");
		RewriteRuleSubtreeStream stream_additiveExpression=new RewriteRuleSubtreeStream(adaptor,"rule additiveExpression");

		try {
			// hql.g:634:2: ( (op= SUM |op= AVG |op= MAX |op= MIN ) OPEN additiveExpression CLOSE -> ^( AGGREGATE[$op] additiveExpression ) | COUNT OPEN (s= STAR |p= aggregateDistinctAll ) CLOSE -> {s == null}? ^( COUNT $p) -> ^( COUNT ^( ROW_STAR[\"*\"] ) ) | collectionExpr )
			int alt96=3;
			switch ( input.LA(1) ) {
			case AVG:
			case MAX:
			case MIN:
			case SUM:
				{
				alt96=1;
				}
				break;
			case COUNT:
				{
				alt96=2;
				}
				break;
			case ELEMENTS:
			case INDICES:
				{
				alt96=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 96, 0, input);
				throw nvae;
			}
			switch (alt96) {
				case 1 :
					// hql.g:634:4: (op= SUM |op= AVG |op= MAX |op= MIN ) OPEN additiveExpression CLOSE
					{
					// hql.g:634:4: (op= SUM |op= AVG |op= MAX |op= MIN )
					int alt94=4;
					switch ( input.LA(1) ) {
					case SUM:
						{
						alt94=1;
						}
						break;
					case AVG:
						{
						alt94=2;
						}
						break;
					case MAX:
						{
						alt94=3;
						}
						break;
					case MIN:
						{
						alt94=4;
						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 94, 0, input);
						throw nvae;
					}
					switch (alt94) {
						case 1 :
							// hql.g:634:6: op= SUM
							{
							op=(Token)match(input,SUM,FOLLOW_SUM_in_aggregate3109);
							stream_SUM.add(op);

							}
							break;
						case 2 :
							// hql.g:634:15: op= AVG
							{
							op=(Token)match(input,AVG,FOLLOW_AVG_in_aggregate3115);
							stream_AVG.add(op);

							}
							break;
						case 3 :
							// hql.g:634:24: op= MAX
							{
							op=(Token)match(input,MAX,FOLLOW_MAX_in_aggregate3121);
							stream_MAX.add(op);

							}
							break;
						case 4 :
							// hql.g:634:33: op= MIN
							{
							op=(Token)match(input,MIN,FOLLOW_MIN_in_aggregate3127);
							stream_MIN.add(op);

							}
							break;

					}

					OPEN264=(Token)match(input,OPEN,FOLLOW_OPEN_in_aggregate3131);
					stream_OPEN.add(OPEN264);

					pushFollow(FOLLOW_additiveExpression_in_aggregate3133);
					additiveExpression265=additiveExpression();
					state._fsp--;

					stream_additiveExpression.add(additiveExpression265.getTree());
					CLOSE266=(Token)match(input,CLOSE,FOLLOW_CLOSE_in_aggregate3135);
					stream_CLOSE.add(CLOSE266);

					// AST REWRITE
					// elements: additiveExpression
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 635:3: -> ^( AGGREGATE[$op] additiveExpression )
					{
						// hql.g:635:6: ^( AGGREGATE[$op] additiveExpression )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(AGGREGATE, op), root_1);
						adaptor.addChild(root_1, stream_additiveExpression.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// hql.g:637:5: COUNT OPEN (s= STAR |p= aggregateDistinctAll ) CLOSE
					{
					COUNT267=(Token)match(input,COUNT,FOLLOW_COUNT_in_aggregate3154);
					stream_COUNT.add(COUNT267);

					OPEN268=(Token)match(input,OPEN,FOLLOW_OPEN_in_aggregate3156);
					stream_OPEN.add(OPEN268);

					// hql.g:637:16: (s= STAR |p= aggregateDistinctAll )
					int alt95=2;
					int LA95_0 = input.LA(1);
					if ( (LA95_0==STAR) ) {
						alt95=1;
					}
					else if ( (LA95_0==ALL||LA95_0==DISTINCT||LA95_0==ELEMENTS||LA95_0==IDENT||LA95_0==INDICES) ) {
						alt95=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 95, 0, input);
						throw nvae;
					}

					switch (alt95) {
						case 1 :
							// hql.g:637:18: s= STAR
							{
							s=(Token)match(input,STAR,FOLLOW_STAR_in_aggregate3162);
							stream_STAR.add(s);

							}
							break;
						case 2 :
							// hql.g:637:27: p= aggregateDistinctAll
							{
							pushFollow(FOLLOW_aggregateDistinctAll_in_aggregate3168);
							p=aggregateDistinctAll();
							state._fsp--;

							stream_aggregateDistinctAll.add(p.getTree());
							}
							break;

					}

					CLOSE269=(Token)match(input,CLOSE,FOLLOW_CLOSE_in_aggregate3172);
					stream_CLOSE.add(CLOSE269);

					// AST REWRITE
					// elements: COUNT, COUNT, p
					// token labels:
					// rule labels: retval, p
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_p=new RewriteRuleSubtreeStream(adaptor,"rule p",p!=null?p.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 638:3: -> {s == null}? ^( COUNT $p)
					if (s == null) {
						// hql.g:638:19: ^( COUNT $p)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_COUNT.nextNode(), root_1);
						adaptor.addChild(root_1, stream_p.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}

					else // 639:3: -> ^( COUNT ^( ROW_STAR[\"*\"] ) )
					{
						// hql.g:639:6: ^( COUNT ^( ROW_STAR[\"*\"] ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_COUNT.nextNode(), root_1);
						// hql.g:639:14: ^( ROW_STAR[\"*\"] )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ROW_STAR, "*"), root_2);
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// hql.g:640:5: collectionExpr
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_collectionExpr_in_aggregate3204);
					collectionExpr270=collectionExpr();
					state._fsp--;

					adaptor.addChild(root_0, collectionExpr270.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "aggregate"


	public static class aggregateDistinctAll_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "aggregateDistinctAll"
	// hql.g:643:1: aggregateDistinctAll : ( ( DISTINCT | ALL )? ( path | collectionExpr ) ) ;
	public final hqlParser.aggregateDistinctAll_return aggregateDistinctAll() throws RecognitionException {
		hqlParser.aggregateDistinctAll_return retval = new hqlParser.aggregateDistinctAll_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token set271=null;
		ParserRuleReturnScope path272 =null;
		ParserRuleReturnScope collectionExpr273 =null;

		CommonTree set271_tree=null;

		try {
			// hql.g:644:2: ( ( ( DISTINCT | ALL )? ( path | collectionExpr ) ) )
			// hql.g:644:4: ( ( DISTINCT | ALL )? ( path | collectionExpr ) )
			{
			root_0 = (CommonTree)adaptor.nil();


			// hql.g:644:4: ( ( DISTINCT | ALL )? ( path | collectionExpr ) )
			// hql.g:644:6: ( DISTINCT | ALL )? ( path | collectionExpr )
			{
			// hql.g:644:6: ( DISTINCT | ALL )?
			int alt97=2;
			int LA97_0 = input.LA(1);
			if ( (LA97_0==ALL||LA97_0==DISTINCT) ) {
				alt97=1;
			}
			switch (alt97) {
				case 1 :
					// hql.g:
					{
					set271=input.LT(1);
					if ( input.LA(1)==ALL||input.LA(1)==DISTINCT ) {
						input.consume();
						adaptor.addChild(root_0, adaptor.create(set271));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;

			}

			// hql.g:644:26: ( path | collectionExpr )
			int alt98=2;
			int LA98_0 = input.LA(1);
			if ( (LA98_0==IDENT) ) {
				alt98=1;
			}
			else if ( (LA98_0==ELEMENTS||LA98_0==INDICES) ) {
				alt98=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 98, 0, input);
				throw nvae;
			}

			switch (alt98) {
				case 1 :
					// hql.g:644:28: path
					{
					pushFollow(FOLLOW_path_in_aggregateDistinctAll3230);
					path272=path();
					state._fsp--;

					adaptor.addChild(root_0, path272.getTree());

					}
					break;
				case 2 :
					// hql.g:644:35: collectionExpr
					{
					pushFollow(FOLLOW_collectionExpr_in_aggregateDistinctAll3234);
					collectionExpr273=collectionExpr();
					state._fsp--;

					adaptor.addChild(root_0, collectionExpr273.getTree());

					}
					break;

			}

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "aggregateDistinctAll"


	public static class collectionExpr_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "collectionExpr"
	// hql.g:649:1: collectionExpr : ( ELEMENTS ^| INDICES ^) OPEN ! path CLOSE !;
	public final hqlParser.collectionExpr_return collectionExpr() throws RecognitionException {
		hqlParser.collectionExpr_return retval = new hqlParser.collectionExpr_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ELEMENTS274=null;
		Token INDICES275=null;
		Token OPEN276=null;
		Token CLOSE278=null;
		ParserRuleReturnScope path277 =null;

		CommonTree ELEMENTS274_tree=null;
		CommonTree INDICES275_tree=null;
		CommonTree OPEN276_tree=null;
		CommonTree CLOSE278_tree=null;

		try {
			// hql.g:650:2: ( ( ELEMENTS ^| INDICES ^) OPEN ! path CLOSE !)
			// hql.g:650:4: ( ELEMENTS ^| INDICES ^) OPEN ! path CLOSE !
			{
			root_0 = (CommonTree)adaptor.nil();


			// hql.g:650:4: ( ELEMENTS ^| INDICES ^)
			int alt99=2;
			int LA99_0 = input.LA(1);
			if ( (LA99_0==ELEMENTS) ) {
				alt99=1;
			}
			else if ( (LA99_0==INDICES) ) {
				alt99=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 99, 0, input);
				throw nvae;
			}

			switch (alt99) {
				case 1 :
					// hql.g:650:5: ELEMENTS ^
					{
					ELEMENTS274=(Token)match(input,ELEMENTS,FOLLOW_ELEMENTS_in_collectionExpr3253);
					ELEMENTS274_tree = (CommonTree)adaptor.create(ELEMENTS274);
					root_0 = (CommonTree)adaptor.becomeRoot(ELEMENTS274_tree, root_0);

					}
					break;
				case 2 :
					// hql.g:650:17: INDICES ^
					{
					INDICES275=(Token)match(input,INDICES,FOLLOW_INDICES_in_collectionExpr3258);
					INDICES275_tree = (CommonTree)adaptor.create(INDICES275);
					root_0 = (CommonTree)adaptor.becomeRoot(INDICES275_tree, root_0);

					}
					break;

			}

			OPEN276=(Token)match(input,OPEN,FOLLOW_OPEN_in_collectionExpr3262);
			pushFollow(FOLLOW_path_in_collectionExpr3265);
			path277=path();
			state._fsp--;

			adaptor.addChild(root_0, path277.getTree());

			CLOSE278=(Token)match(input,CLOSE,FOLLOW_CLOSE_in_collectionExpr3267);
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "collectionExpr"


	public static class compoundExpr_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "compoundExpr"
	// hql.g:653:1: compoundExpr : ( collectionExpr | path | ( OPEN ! ( subQuery | ( expression ( COMMA ! expression )* ) ) CLOSE !) );
	public final hqlParser.compoundExpr_return compoundExpr() throws RecognitionException {
		hqlParser.compoundExpr_return retval = new hqlParser.compoundExpr_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token OPEN281=null;
		Token COMMA284=null;
		Token CLOSE286=null;
		ParserRuleReturnScope collectionExpr279 =null;
		ParserRuleReturnScope path280 =null;
		ParserRuleReturnScope subQuery282 =null;
		ParserRuleReturnScope expression283 =null;
		ParserRuleReturnScope expression285 =null;

		CommonTree OPEN281_tree=null;
		CommonTree COMMA284_tree=null;
		CommonTree CLOSE286_tree=null;

		try {
			// hql.g:654:2: ( collectionExpr | path | ( OPEN ! ( subQuery | ( expression ( COMMA ! expression )* ) ) CLOSE !) )
			int alt102=3;
			switch ( input.LA(1) ) {
			case ELEMENTS:
			case INDICES:
				{
				alt102=1;
				}
				break;
			case IDENT:
				{
				alt102=2;
				}
				break;
			case OPEN:
				{
				alt102=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 102, 0, input);
				throw nvae;
			}
			switch (alt102) {
				case 1 :
					// hql.g:654:4: collectionExpr
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_collectionExpr_in_compoundExpr3322);
					collectionExpr279=collectionExpr();
					state._fsp--;

					adaptor.addChild(root_0, collectionExpr279.getTree());

					}
					break;
				case 2 :
					// hql.g:655:4: path
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_path_in_compoundExpr3327);
					path280=path();
					state._fsp--;

					adaptor.addChild(root_0, path280.getTree());

					}
					break;
				case 3 :
					// hql.g:656:4: ( OPEN ! ( subQuery | ( expression ( COMMA ! expression )* ) ) CLOSE !)
					{
					root_0 = (CommonTree)adaptor.nil();


					// hql.g:656:4: ( OPEN ! ( subQuery | ( expression ( COMMA ! expression )* ) ) CLOSE !)
					// hql.g:656:5: OPEN ! ( subQuery | ( expression ( COMMA ! expression )* ) ) CLOSE !
					{
					OPEN281=(Token)match(input,OPEN,FOLLOW_OPEN_in_compoundExpr3333);
					// hql.g:656:11: ( subQuery | ( expression ( COMMA ! expression )* ) )
					int alt101=2;
					int LA101_0 = input.LA(1);
					if ( (LA101_0==EOF||LA101_0==CLOSE||LA101_0==FROM||LA101_0==GROUP||LA101_0==HAVING||LA101_0==ORDER||LA101_0==SELECT||LA101_0==SKIP||LA101_0==TAKE||LA101_0==UNION||LA101_0==WHERE) ) {
						alt101=1;
					}
					else if ( (LA101_0==ALL||LA101_0==ANY||LA101_0==AVG||LA101_0==BNOT||LA101_0==CASE||LA101_0==COLON||LA101_0==COUNT||LA101_0==ELEMENTS||LA101_0==EMPTY||LA101_0==EXISTS||LA101_0==FALSE||LA101_0==IDENT||LA101_0==INDICES||LA101_0==MAX||(LA101_0 >= MIN && LA101_0 <= MINUS)||LA101_0==NOT||(LA101_0 >= NULL && LA101_0 <= NUM_LONG)||LA101_0==OPEN||(LA101_0 >= PARAM && LA101_0 <= PLUS)||LA101_0==QUOTED_String||LA101_0==SOME||LA101_0==SUM||LA101_0==TRUE) ) {
						alt101=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 101, 0, input);
						throw nvae;
					}

					switch (alt101) {
						case 1 :
							// hql.g:656:13: subQuery
							{
							pushFollow(FOLLOW_subQuery_in_compoundExpr3338);
							subQuery282=subQuery();
							state._fsp--;

							adaptor.addChild(root_0, subQuery282.getTree());

							}
							break;
						case 2 :
							// hql.g:656:24: ( expression ( COMMA ! expression )* )
							{
							// hql.g:656:24: ( expression ( COMMA ! expression )* )
							// hql.g:656:25: expression ( COMMA ! expression )*
							{
							pushFollow(FOLLOW_expression_in_compoundExpr3343);
							expression283=expression();
							state._fsp--;

							adaptor.addChild(root_0, expression283.getTree());

							// hql.g:656:36: ( COMMA ! expression )*
							loop100:
							while (true) {
								int alt100=2;
								int LA100_0 = input.LA(1);
								if ( (LA100_0==COMMA) ) {
									alt100=1;
								}

								switch (alt100) {
								case 1 :
									// hql.g:656:37: COMMA ! expression
									{
									COMMA284=(Token)match(input,COMMA,FOLLOW_COMMA_in_compoundExpr3346);
									pushFollow(FOLLOW_expression_in_compoundExpr3349);
									expression285=expression();
									state._fsp--;

									adaptor.addChild(root_0, expression285.getTree());

									}
									break;

								default :
									break loop100;
								}
							}

							}

							}
							break;

					}

					CLOSE286=(Token)match(input,CLOSE,FOLLOW_CLOSE_in_compoundExpr3356);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "compoundExpr"


	public static class exprList_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "exprList"
	// hql.g:659:1: exprList : ( TRAILING | LEADING | BOTH )? ( expression ( ( COMMA ! expression )+ |f= FROM expression | AS ! identifier )? |f2= FROM expression )? ;
	public final hqlParser.exprList_return exprList() throws RecognitionException {
		hqlParser.exprList_return retval = new hqlParser.exprList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token f=null;
		Token f2=null;
		Token TRAILING287=null;
		Token LEADING288=null;
		Token BOTH289=null;
		Token COMMA291=null;
		Token AS294=null;
		ParserRuleReturnScope expression290 =null;
		ParserRuleReturnScope expression292 =null;
		ParserRuleReturnScope expression293 =null;
		ParserRuleReturnScope identifier295 =null;
		ParserRuleReturnScope expression296 =null;

		CommonTree f_tree=null;
		CommonTree f2_tree=null;
		CommonTree TRAILING287_tree=null;
		CommonTree LEADING288_tree=null;
		CommonTree BOTH289_tree=null;
		CommonTree COMMA291_tree=null;
		CommonTree AS294_tree=null;

		try {
			// hql.g:669:2: ( ( TRAILING | LEADING | BOTH )? ( expression ( ( COMMA ! expression )+ |f= FROM expression | AS ! identifier )? |f2= FROM expression )? )
			// hql.g:669:4: ( TRAILING | LEADING | BOTH )? ( expression ( ( COMMA ! expression )+ |f= FROM expression | AS ! identifier )? |f2= FROM expression )?
			{
			root_0 = (CommonTree)adaptor.nil();


			// hql.g:669:4: ( TRAILING | LEADING | BOTH )?
			int alt103=4;
			switch ( input.LA(1) ) {
				case TRAILING:
					{
					alt103=1;
					}
					break;
				case LEADING:
					{
					alt103=2;
					}
					break;
				case BOTH:
					{
					alt103=3;
					}
					break;
			}
			switch (alt103) {
				case 1 :
					// hql.g:669:5: TRAILING
					{
					TRAILING287=(Token)match(input,TRAILING,FOLLOW_TRAILING_in_exprList3375);
					TRAILING287_tree = (CommonTree)adaptor.create(TRAILING287);
					adaptor.addChild(root_0, TRAILING287_tree);

					TRAILING287.setType( IDENT );
					}
					break;
				case 2 :
					// hql.g:670:10: LEADING
					{
					LEADING288=(Token)match(input,LEADING,FOLLOW_LEADING_in_exprList3388);
					LEADING288_tree = (CommonTree)adaptor.create(LEADING288);
					adaptor.addChild(root_0, LEADING288_tree);

					LEADING288.setType( IDENT );
					}
					break;
				case 3 :
					// hql.g:671:10: BOTH
					{
					BOTH289=(Token)match(input,BOTH,FOLLOW_BOTH_in_exprList3401);
					BOTH289_tree = (CommonTree)adaptor.create(BOTH289);
					adaptor.addChild(root_0, BOTH289_tree);

					BOTH289.setType( IDENT );
					}
					break;

			}

			// hql.g:673:4: ( expression ( ( COMMA ! expression )+ |f= FROM expression | AS ! identifier )? |f2= FROM expression )?
			int alt106=3;
			int LA106_0 = input.LA(1);
			if ( (LA106_0==ALL||LA106_0==ANY||LA106_0==AVG||LA106_0==BNOT||LA106_0==CASE||LA106_0==COLON||LA106_0==COUNT||LA106_0==ELEMENTS||LA106_0==EMPTY||LA106_0==EXISTS||LA106_0==FALSE||LA106_0==IDENT||LA106_0==INDICES||LA106_0==MAX||(LA106_0 >= MIN && LA106_0 <= MINUS)||LA106_0==NOT||(LA106_0 >= NULL && LA106_0 <= NUM_LONG)||LA106_0==OPEN||(LA106_0 >= PARAM && LA106_0 <= PLUS)||LA106_0==QUOTED_String||LA106_0==SOME||LA106_0==SUM||LA106_0==TRUE) ) {
				alt106=1;
			}
			else if ( (LA106_0==FROM) ) {
				alt106=2;
			}
			switch (alt106) {
				case 1 :
					// hql.g:674:5: expression ( ( COMMA ! expression )+ |f= FROM expression | AS ! identifier )?
					{
					pushFollow(FOLLOW_expression_in_exprList3425);
					expression290=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression290.getTree());

					// hql.g:674:16: ( ( COMMA ! expression )+ |f= FROM expression | AS ! identifier )?
					int alt105=4;
					switch ( input.LA(1) ) {
						case COMMA:
							{
							alt105=1;
							}
							break;
						case FROM:
							{
							alt105=2;
							}
							break;
						case AS:
							{
							alt105=3;
							}
							break;
					}
					switch (alt105) {
						case 1 :
							// hql.g:674:18: ( COMMA ! expression )+
							{
							// hql.g:674:18: ( COMMA ! expression )+
							int cnt104=0;
							loop104:
							while (true) {
								int alt104=2;
								int LA104_0 = input.LA(1);
								if ( (LA104_0==COMMA) ) {
									alt104=1;
								}

								switch (alt104) {
								case 1 :
									// hql.g:674:19: COMMA ! expression
									{
									COMMA291=(Token)match(input,COMMA,FOLLOW_COMMA_in_exprList3430);
									pushFollow(FOLLOW_expression_in_exprList3433);
									expression292=expression();
									state._fsp--;

									adaptor.addChild(root_0, expression292.getTree());

									}
									break;

								default :
									if ( cnt104 >= 1 ) break loop104;
									EarlyExitException eee = new EarlyExitException(104, input);
									throw eee;
								}
								cnt104++;
							}

							}
							break;
						case 2 :
							// hql.g:675:9: f= FROM expression
							{
							f=(Token)match(input,FROM,FOLLOW_FROM_in_exprList3448);
							f_tree = (CommonTree)adaptor.create(f);
							adaptor.addChild(root_0, f_tree);

							pushFollow(FOLLOW_expression_in_exprList3450);
							expression293=expression();
							state._fsp--;

							adaptor.addChild(root_0, expression293.getTree());

							f.setType( IDENT );
							}
							break;
						case 3 :
							// hql.g:676:9: AS ! identifier
							{
							AS294=(Token)match(input,AS,FOLLOW_AS_in_exprList3462);
							pushFollow(FOLLOW_identifier_in_exprList3465);
							identifier295=identifier();
							state._fsp--;

							adaptor.addChild(root_0, identifier295.getTree());

							}
							break;

					}

					}
					break;
				case 2 :
					// hql.g:677:7: f2= FROM expression
					{
					f2=(Token)match(input,FROM,FOLLOW_FROM_in_exprList3479);
					f2_tree = (CommonTree)adaptor.create(f2);
					adaptor.addChild(root_0, f2_tree);

					pushFollow(FOLLOW_expression_in_exprList3481);
					expression296=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression296.getTree());

					f2.setType( IDENT );
					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


			    assert false : "PLEASE CHECK THAT IT WORKS !!!";
			    CommonTree root = (CommonTree) adaptor.create( EXPR_LIST, "exprList" );
			    root.addChild( retval.getTree() );
			    retval.tree = root ;
			   //IASTNode root = (IASTNode) adaptor.Create(EXPR_LIST, "exprList");
			   //root.AddChild((IASTNode)retval.Tree);
			   //retval.Tree = root;

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "exprList"


	public static class subQuery_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "subQuery"
	// hql.g:681:1: subQuery : innerSubQuery ( UNION ^ innerSubQuery )* ;
	public final hqlParser.subQuery_return subQuery() throws RecognitionException {
		hqlParser.subQuery_return retval = new hqlParser.subQuery_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token UNION298=null;
		ParserRuleReturnScope innerSubQuery297 =null;
		ParserRuleReturnScope innerSubQuery299 =null;

		CommonTree UNION298_tree=null;

		try {
			// hql.g:682:2: ( innerSubQuery ( UNION ^ innerSubQuery )* )
			// hql.g:682:4: innerSubQuery ( UNION ^ innerSubQuery )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_innerSubQuery_in_subQuery3501);
			innerSubQuery297=innerSubQuery();
			state._fsp--;

			adaptor.addChild(root_0, innerSubQuery297.getTree());

			// hql.g:682:18: ( UNION ^ innerSubQuery )*
			loop107:
			while (true) {
				int alt107=2;
				int LA107_0 = input.LA(1);
				if ( (LA107_0==UNION) ) {
					alt107=1;
				}

				switch (alt107) {
				case 1 :
					// hql.g:682:19: UNION ^ innerSubQuery
					{
					UNION298=(Token)match(input,UNION,FOLLOW_UNION_in_subQuery3504);
					UNION298_tree = (CommonTree)adaptor.create(UNION298);
					root_0 = (CommonTree)adaptor.becomeRoot(UNION298_tree, root_0);

					pushFollow(FOLLOW_innerSubQuery_in_subQuery3507);
					innerSubQuery299=innerSubQuery();
					state._fsp--;

					adaptor.addChild(root_0, innerSubQuery299.getTree());

					}
					break;

				default :
					break loop107;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "subQuery"


	public static class innerSubQuery_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "innerSubQuery"
	// hql.g:685:1: innerSubQuery : queryRule -> ^( QUERY[\"query\"] queryRule ) ;
	public final hqlParser.innerSubQuery_return innerSubQuery() throws RecognitionException {
		hqlParser.innerSubQuery_return retval = new hqlParser.innerSubQuery_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope queryRule300 =null;

		RewriteRuleSubtreeStream stream_queryRule=new RewriteRuleSubtreeStream(adaptor,"rule queryRule");

		try {
			// hql.g:686:2: ( queryRule -> ^( QUERY[\"query\"] queryRule ) )
			// hql.g:686:4: queryRule
			{
			pushFollow(FOLLOW_queryRule_in_innerSubQuery3521);
			queryRule300=queryRule();
			state._fsp--;

			stream_queryRule.add(queryRule300.getTree());
			// AST REWRITE
			// elements: queryRule
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 687:2: -> ^( QUERY[\"query\"] queryRule )
			{
				// hql.g:687:5: ^( QUERY[\"query\"] queryRule )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(QUERY, "query"), root_1);
				adaptor.addChild(root_1, stream_queryRule.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "innerSubQuery"


	public static class constant_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "constant"
	// hql.g:690:1: constant : ( NUM_INT | NUM_FLOAT | NUM_LONG | NUM_DOUBLE | NUM_DECIMAL | QUOTED_String | NULL | TRUE | FALSE | EMPTY );
	public final hqlParser.constant_return constant() throws RecognitionException {
		hqlParser.constant_return retval = new hqlParser.constant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token set301=null;

		CommonTree set301_tree=null;

		try {
			// hql.g:691:2: ( NUM_INT | NUM_FLOAT | NUM_LONG | NUM_DOUBLE | NUM_DECIMAL | QUOTED_String | NULL | TRUE | FALSE | EMPTY )
			// hql.g:
			{
			root_0 = (CommonTree)adaptor.nil();


			set301=input.LT(1);
			if ( input.LA(1)==EMPTY||input.LA(1)==FALSE||(input.LA(1) >= NULL && input.LA(1) <= NUM_LONG)||input.LA(1)==QUOTED_String||input.LA(1)==TRUE ) {
				input.consume();
				adaptor.addChild(root_0, adaptor.create(set301));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "constant"


	public static class path_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "path"
	// hql.g:709:1: path : identifier ( DOT ^ identifier )* ;
	public final hqlParser.path_return path() throws RecognitionException {
		hqlParser.path_return retval = new hqlParser.path_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token DOT303=null;
		ParserRuleReturnScope identifier302 =null;
		ParserRuleReturnScope identifier304 =null;

		CommonTree DOT303_tree=null;


		// TODO - need to clean up DotIdent - suspect that DotIdent2 supersedes the other one, but need to do the analysis
		//HandleDotIdent2();

		try {
			// hql.g:714:2: ( identifier ( DOT ^ identifier )* )
			// hql.g:714:4: identifier ( DOT ^ identifier )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_identifier_in_path3609);
			identifier302=identifier();
			state._fsp--;

			adaptor.addChild(root_0, identifier302.getTree());

			// hql.g:714:15: ( DOT ^ identifier )*
			loop108:
			while (true) {
				int alt108=2;
				int LA108_0 = input.LA(1);
				if ( (LA108_0==DOT) ) {
					alt108=1;
				}

				switch (alt108) {
				case 1 :
					// hql.g:714:17: DOT ^ identifier
					{
					DOT303=(Token)match(input,DOT,FOLLOW_DOT_in_path3613);
					DOT303_tree = (CommonTree)adaptor.create(DOT303);
					root_0 = (CommonTree)adaptor.becomeRoot(DOT303_tree, root_0);

					 WeakKeywords();
					pushFollow(FOLLOW_identifier_in_path3618);
					identifier304=identifier();
					state._fsp--;

					adaptor.addChild(root_0, identifier304.getTree());

					}
					break;

				default :
					break loop108;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "path"


	public static class identifier_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "identifier"
	// hql.g:719:1: identifier : IDENT ;
	public final hqlParser.identifier_return identifier() throws RecognitionException {
		hqlParser.identifier_return retval = new hqlParser.identifier_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token IDENT305=null;

		CommonTree IDENT305_tree=null;

		try {
			// hql.g:720:2: ( IDENT )
			// hql.g:720:4: IDENT
			{
			root_0 = (CommonTree)adaptor.nil();


			IDENT305=(Token)match(input,IDENT,FOLLOW_IDENT_in_identifier3634);
			IDENT305_tree = (CommonTree)adaptor.create(IDENT305);
			adaptor.addChild(root_0, IDENT305_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException ex) {

					//retval.Tree = HandleIdentifierError(input.LT(1),ex);
			                retval.tree = HandleIdentifierError(input.LT(1),ex);

		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "identifier"

	// Delegated rules



	public static final BitSet FOLLOW_updateStatement_in_statement599 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_deleteStatement_in_statement603 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_selectStatement_in_statement607 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_insertStatement_in_statement611 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_EOF_in_statement615 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_UPDATE_in_updateStatement627 = new BitSet(new long[]{0x0081000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_VERSIONED_in_updateStatement631 = new BitSet(new long[]{0x0081000000000000L});
	public static final BitSet FOLLOW_optionalFromTokenFromClause_in_updateStatement637 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
	public static final BitSet FOLLOW_setClause_in_updateStatement641 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_whereClause_in_updateStatement646 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SET_in_setClause660 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_assignment_in_setClause663 = new BitSet(new long[]{0x0000000001000002L});
	public static final BitSet FOLLOW_COMMA_in_setClause666 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_assignment_in_setClause669 = new BitSet(new long[]{0x0000000001000002L});
	public static final BitSet FOLLOW_stateField_in_assignment683 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_EQ_in_assignment685 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_newValue_in_assignment688 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_path_in_stateField701 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_concatenation_in_newValue714 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DELETE_in_deleteStatement725 = new BitSet(new long[]{0x0081000000000000L});
	public static final BitSet FOLLOW_optionalFromTokenFromClause_in_deleteStatement731 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_whereClause_in_deleteStatement737 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_optionalFromTokenFromClause2_in_optionalFromTokenFromClause752 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_path_in_optionalFromTokenFromClause754 = new BitSet(new long[]{0x0080000000000202L});
	public static final BitSet FOLLOW_asAlias_in_optionalFromTokenFromClause757 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FROM_in_optionalFromTokenFromClause2788 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_queryRule_in_selectStatement802 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSERT_in_insertStatement831 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_intoClause_in_insertStatement834 = new BitSet(new long[]{0x0029000000000000L,0x0042400800000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_selectStatement_in_insertStatement836 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTO_in_intoClause847 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_path_in_intoClause850 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_insertablePropertySpec_in_intoClause854 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPEN_in_insertablePropertySpec865 = new BitSet(new long[]{0x1080101410800800L,0x022004411F809000L});
	public static final BitSet FOLLOW_primaryExpression_in_insertablePropertySpec867 = new BitSet(new long[]{0x0000000001200000L});
	public static final BitSet FOLLOW_COMMA_in_insertablePropertySpec871 = new BitSet(new long[]{0x1080101410800800L,0x022004411F809000L});
	public static final BitSet FOLLOW_primaryExpression_in_insertablePropertySpec873 = new BitSet(new long[]{0x0000000001200000L});
	public static final BitSet FOLLOW_CLOSE_in_insertablePropertySpec878 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_selectFrom_in_queryRule904 = new BitSet(new long[]{0x0028000000000002L,0x0042000800000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_whereClause_in_queryRule909 = new BitSet(new long[]{0x0028000000000002L,0x0042000800000000L});
	public static final BitSet FOLLOW_groupByClause_in_queryRule916 = new BitSet(new long[]{0x0020000000000002L,0x0042000800000000L});
	public static final BitSet FOLLOW_havingClause_in_queryRule923 = new BitSet(new long[]{0x0000000000000002L,0x0042000800000000L});
	public static final BitSet FOLLOW_orderByClause_in_queryRule930 = new BitSet(new long[]{0x0000000000000002L,0x0042000000000000L});
	public static final BitSet FOLLOW_skipClause_in_queryRule937 = new BitSet(new long[]{0x0000000000000002L,0x0040000000000000L});
	public static final BitSet FOLLOW_takeClause_in_queryRule944 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_selectClause_in_selectFrom962 = new BitSet(new long[]{0x0001000000000002L});
	public static final BitSet FOLLOW_fromClause_in_selectFrom969 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SELECT_in_selectClause1018 = new BitSet(new long[]{0x1080121490844940L,0x022404C13F8D9000L});
	public static final BitSet FOLLOW_DISTINCT_in_selectClause1030 = new BitSet(new long[]{0x1080121410844940L,0x022404C13F8D9000L});
	public static final BitSet FOLLOW_selectedPropertiesList_in_selectClause1036 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_newExpression_in_selectClause1040 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_selectObject_in_selectClause1044 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEW_in_newExpression1058 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_path_in_newExpression1060 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_OPEN_in_newExpression1065 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_selectedPropertiesList_in_newExpression1067 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_CLOSE_in_newExpression1069 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OBJECT_in_selectObject1095 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_OPEN_in_selectObject1098 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_identifier_in_selectObject1101 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_CLOSE_in_selectObject1103 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FROM_in_fromClause1121 = new BitSet(new long[]{0x0480000400000000L});
	public static final BitSet FOLLOW_fromRange_in_fromClause1126 = new BitSet(new long[]{0x2002000001000002L,0x0000100000000120L});
	public static final BitSet FOLLOW_fromJoin_in_fromClause1130 = new BitSet(new long[]{0x2002000001000002L,0x0000100000000120L});
	public static final BitSet FOLLOW_COMMA_in_fromClause1134 = new BitSet(new long[]{0x0480000400000000L});
	public static final BitSet FOLLOW_fromRange_in_fromClause1139 = new BitSet(new long[]{0x2002000001000002L,0x0000100000000120L});
	public static final BitSet FOLLOW_set_in_fromJoin1157 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000020L});
	public static final BitSet FOLLOW_OUTER_in_fromJoin1168 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_FULL_in_fromJoin1176 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_INNER_in_fromJoin1180 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_JOIN_in_fromJoin1185 = new BitSet(new long[]{0x0080200000000000L});
	public static final BitSet FOLLOW_FETCH_in_fromJoin1189 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_path_in_fromJoin1193 = new BitSet(new long[]{0x0080200000000202L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_asAlias_in_fromJoin1196 = new BitSet(new long[]{0x0000200000000002L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_propertyFetch_in_fromJoin1201 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_withClause_in_fromJoin1206 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_fromJoin1217 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000020L});
	public static final BitSet FOLLOW_OUTER_in_fromJoin1228 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_FULL_in_fromJoin1236 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_INNER_in_fromJoin1240 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_JOIN_in_fromJoin1245 = new BitSet(new long[]{0x0000200400000000L});
	public static final BitSet FOLLOW_FETCH_in_fromJoin1249 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_ELEMENTS_in_fromJoin1253 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_OPEN_in_fromJoin1256 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_path_in_fromJoin1259 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_CLOSE_in_fromJoin1261 = new BitSet(new long[]{0x0080200000000202L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_asAlias_in_fromJoin1265 = new BitSet(new long[]{0x0000200000000002L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_propertyFetch_in_fromJoin1270 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_withClause_in_fromJoin1275 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WITH_in_withClause1288 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_logicalExpression_in_withClause1291 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_fromClassOrOuterQueryPath_in_fromRange1302 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_inClassDeclaration_in_fromRange1307 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_inCollectionDeclaration_in_fromRange1312 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_inCollectionElementsDeclaration_in_fromRange1317 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_path_in_fromClassOrOuterQueryPath1329 = new BitSet(new long[]{0x0080200000000202L});
	public static final BitSet FOLLOW_asAlias_in_fromClassOrOuterQueryPath1334 = new BitSet(new long[]{0x0000200000000002L});
	public static final BitSet FOLLOW_propertyFetch_in_fromClassOrOuterQueryPath1339 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_alias_in_inClassDeclaration1369 = new BitSet(new long[]{0x0400000000000000L});
	public static final BitSet FOLLOW_IN_in_inClassDeclaration1371 = new BitSet(new long[]{0x0080000000100000L});
	public static final BitSet FOLLOW_CLASS_in_inClassDeclaration1373 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_path_in_inClassDeclaration1376 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IN_in_inCollectionDeclaration1404 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_OPEN_in_inCollectionDeclaration1406 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_path_in_inCollectionDeclaration1408 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_CLOSE_in_inCollectionDeclaration1410 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_alias_in_inCollectionDeclaration1412 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_alias_in_inCollectionElementsDeclaration1446 = new BitSet(new long[]{0x0400000000000000L});
	public static final BitSet FOLLOW_IN_in_inCollectionElementsDeclaration1448 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_ELEMENTS_in_inCollectionElementsDeclaration1450 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_OPEN_in_inCollectionElementsDeclaration1452 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_path_in_inCollectionElementsDeclaration1454 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_CLOSE_in_inCollectionElementsDeclaration1456 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ELEMENTS_in_inCollectionElementsDeclaration1478 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_OPEN_in_inCollectionElementsDeclaration1480 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_path_in_inCollectionElementsDeclaration1482 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_CLOSE_in_inCollectionElementsDeclaration1484 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_AS_in_inCollectionElementsDeclaration1486 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_alias_in_inCollectionElementsDeclaration1488 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_AS_in_asAlias1520 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_alias_in_asAlias1525 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_alias1537 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FETCH_in_propertyFetch1556 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_ALL_in_propertyFetch1558 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_PROPERTIES_in_propertyFetch1561 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GROUP_in_groupByClause1573 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_LITERAL_by_in_groupByClause1579 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_expression_in_groupByClause1582 = new BitSet(new long[]{0x0000000001000002L});
	public static final BitSet FOLLOW_COMMA_in_groupByClause1586 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_expression_in_groupByClause1589 = new BitSet(new long[]{0x0000000001000002L});
	public static final BitSet FOLLOW_ORDER_in_orderByClause1603 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_LITERAL_by_in_orderByClause1606 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_orderElement_in_orderByClause1609 = new BitSet(new long[]{0x0000000001000002L});
	public static final BitSet FOLLOW_COMMA_in_orderByClause1613 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_orderElement_in_orderByClause1616 = new BitSet(new long[]{0x0000000001000002L});
	public static final BitSet FOLLOW_SKIP_in_skipClause1630 = new BitSet(new long[]{0x0000000000800000L,0x0000004008000000L});
	public static final BitSet FOLLOW_NUM_INT_in_skipClause1634 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_parameter_in_skipClause1638 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TAKE_in_takeClause1650 = new BitSet(new long[]{0x0000000000800000L,0x0000004008000000L});
	public static final BitSet FOLLOW_NUM_INT_in_takeClause1654 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_parameter_in_takeClause1658 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COLON_in_parameter1670 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_identifier_in_parameter1673 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PARAM_in_parameter1678 = new BitSet(new long[]{0x0000000000000002L,0x0000000008000000L});
	public static final BitSet FOLLOW_NUM_INT_in_parameter1682 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_orderElement1695 = new BitSet(new long[]{0x0000000040000402L,0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_ascendingOrDescending_in_orderElement1699 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ASCENDING_in_ascendingOrDescending1717 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_133_in_ascendingOrDescending1723 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DESCENDING_in_ascendingOrDescending1743 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_134_in_ascendingOrDescending1749 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HAVING_in_havingClause1770 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_logicalExpression_in_havingClause1773 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WHERE_in_whereClause1784 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_logicalExpression_in_whereClause1787 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_aliasedExpression_in_selectedPropertiesList1798 = new BitSet(new long[]{0x0000000001000002L});
	public static final BitSet FOLLOW_COMMA_in_selectedPropertiesList1802 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_aliasedExpression_in_selectedPropertiesList1805 = new BitSet(new long[]{0x0000000001000002L});
	public static final BitSet FOLLOW_expression_in_aliasedExpression1820 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_AS_in_aliasedExpression1824 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_identifier_in_aliasedExpression1827 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_logicalExpression1866 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_logicalOrExpression_in_expression1878 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_logicalAndExpression_in_logicalOrExpression1890 = new BitSet(new long[]{0x0000000000000002L,0x0000000400000000L});
	public static final BitSet FOLLOW_OR_in_logicalOrExpression1894 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_logicalAndExpression_in_logicalOrExpression1897 = new BitSet(new long[]{0x0000000000000002L,0x0000000400000000L});
	public static final BitSet FOLLOW_negatedExpression_in_logicalAndExpression1912 = new BitSet(new long[]{0x0000000000000082L});
	public static final BitSet FOLLOW_AND_in_logicalAndExpression1916 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_negatedExpression_in_logicalAndExpression1919 = new BitSet(new long[]{0x0000000000000082L});
	public static final BitSet FOLLOW_NOT_in_negatedExpression1940 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_negatedExpression_in_negatedExpression1944 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_equalityExpression_in_negatedExpression1957 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_relationalExpression_in_equalityExpression1987 = new BitSet(new long[]{0x0000004000000002L,0x0008000000020002L});
	public static final BitSet FOLLOW_EQ_in_equalityExpression1995 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_IS_in_equalityExpression2004 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_NOT_in_equalityExpression2010 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_NE_in_equalityExpression2022 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_SQL_NE_in_equalityExpression2031 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_relationalExpression_in_equalityExpression2042 = new BitSet(new long[]{0x0000004000000002L,0x0008000000020002L});
	public static final BitSet FOLLOW_concatenation_in_relationalExpression2059 = new BitSet(new long[]{0x0414000000002002L,0x0000000000082A40L});
	public static final BitSet FOLLOW_LT_in_relationalExpression2071 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_GT_in_relationalExpression2076 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_LE_in_relationalExpression2081 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_GE_in_relationalExpression2086 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_bitwiseNotExpression_in_relationalExpression2091 = new BitSet(new long[]{0x0014000000000002L,0x0000000000000840L});
	public static final BitSet FOLLOW_NOT_in_relationalExpression2108 = new BitSet(new long[]{0x0400000000002000L,0x0000000000002200L});
	public static final BitSet FOLLOW_IN_in_relationalExpression2129 = new BitSet(new long[]{0x1080000400000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_inList_in_relationalExpression2138 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BETWEEN_in_relationalExpression2149 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_betweenList_in_relationalExpression2158 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LIKE_in_relationalExpression2170 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_concatenation_in_relationalExpression2179 = new BitSet(new long[]{0x0000008000000000L});
	public static final BitSet FOLLOW_likeEscape_in_relationalExpression2181 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MEMBER_in_relationalExpression2190 = new BitSet(new long[]{0x0080000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_OF_in_relationalExpression2194 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_path_in_relationalExpression2201 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ESCAPE_in_likeEscape2228 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_concatenation_in_likeEscape2231 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_compoundExpr_in_inList2244 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_concatenation_in_betweenList2265 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_AND_in_betweenList2267 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_concatenation_in_betweenList2270 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_bitwiseNotExpression_in_concatenation2289 = new BitSet(new long[]{0x0000000002000002L});
	public static final BitSet FOLLOW_CONCAT_in_concatenation2297 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_bitwiseNotExpression_in_concatenation2306 = new BitSet(new long[]{0x0000000002000002L});
	public static final BitSet FOLLOW_CONCAT_in_concatenation2313 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_bitwiseNotExpression_in_concatenation2316 = new BitSet(new long[]{0x0000000002000002L});
	public static final BitSet FOLLOW_BNOT_in_bitwiseNotExpression2340 = new BitSet(new long[]{0x1080121410840940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_bitwiseOrExpression_in_bitwiseNotExpression2343 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_bitwiseOrExpression_in_bitwiseNotExpression2349 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_bitwiseXOrExpression_in_bitwiseOrExpression2361 = new BitSet(new long[]{0x0000000000008002L});
	public static final BitSet FOLLOW_BOR_in_bitwiseOrExpression2364 = new BitSet(new long[]{0x1080121410840940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_bitwiseXOrExpression_in_bitwiseOrExpression2367 = new BitSet(new long[]{0x0000000000008002L});
	public static final BitSet FOLLOW_bitwiseAndExpression_in_bitwiseXOrExpression2381 = new BitSet(new long[]{0x0000000000020002L});
	public static final BitSet FOLLOW_BXOR_in_bitwiseXOrExpression2384 = new BitSet(new long[]{0x1080121410840940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_bitwiseAndExpression_in_bitwiseXOrExpression2387 = new BitSet(new long[]{0x0000000000020002L});
	public static final BitSet FOLLOW_additiveExpression_in_bitwiseAndExpression2401 = new BitSet(new long[]{0x0000000000001002L});
	public static final BitSet FOLLOW_BAND_in_bitwiseAndExpression2404 = new BitSet(new long[]{0x1080121410840940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_additiveExpression_in_bitwiseAndExpression2407 = new BitSet(new long[]{0x0000000000001002L});
	public static final BitSet FOLLOW_multiplyExpression_in_additiveExpression2421 = new BitSet(new long[]{0x0000000000000002L,0x0000008000010000L});
	public static final BitSet FOLLOW_PLUS_in_additiveExpression2427 = new BitSet(new long[]{0x1080121410840940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_MINUS_in_additiveExpression2432 = new BitSet(new long[]{0x1080121410840940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_multiplyExpression_in_additiveExpression2437 = new BitSet(new long[]{0x0000000000000002L,0x0000008000010000L});
	public static final BitSet FOLLOW_unaryExpression_in_multiplyExpression2452 = new BitSet(new long[]{0x0000000100000002L,0x0010000000000000L});
	public static final BitSet FOLLOW_STAR_in_multiplyExpression2458 = new BitSet(new long[]{0x1080121410840940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_DIV_in_multiplyExpression2463 = new BitSet(new long[]{0x1080121410840940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_unaryExpression_in_multiplyExpression2468 = new BitSet(new long[]{0x0000000100000002L,0x0010000000000000L});
	public static final BitSet FOLLOW_MINUS_in_unaryExpression2486 = new BitSet(new long[]{0x1080121410840940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_unaryExpression_in_unaryExpression2490 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PLUS_in_unaryExpression2507 = new BitSet(new long[]{0x1080121410840940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_unaryExpression_in_unaryExpression2511 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_caseExpression_in_unaryExpression2528 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_quantifiedExpression_in_unaryExpression2542 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_atom_in_unaryExpression2557 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CASE_in_caseExpression2576 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_whenClause_in_caseExpression2579 = new BitSet(new long[]{0x0000002800000000L,0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_elseClause_in_caseExpression2584 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_END_in_caseExpression2588 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CASE_in_caseExpression2608 = new BitSet(new long[]{0x1080121410840940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_unaryExpression_in_caseExpression2610 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_altWhenClause_in_caseExpression2613 = new BitSet(new long[]{0x0000002800000000L,0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_elseClause_in_caseExpression2618 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_END_in_caseExpression2622 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WHEN_in_whenClause2651 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_logicalExpression_in_whenClause2654 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_THEN_in_whenClause2656 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_expression_in_whenClause2659 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WHEN_in_altWhenClause2673 = new BitSet(new long[]{0x1080121410840940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_unaryExpression_in_altWhenClause2676 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_THEN_in_altWhenClause2678 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_expression_in_altWhenClause2681 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ELSE_in_elseClause2695 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_expression_in_elseClause2698 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SOME_in_quantifiedExpression2713 = new BitSet(new long[]{0x1080000400000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_EXISTS_in_quantifiedExpression2718 = new BitSet(new long[]{0x1080000400000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_ALL_in_quantifiedExpression2723 = new BitSet(new long[]{0x1080000400000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_ANY_in_quantifiedExpression2728 = new BitSet(new long[]{0x1080000400000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_identifier_in_quantifiedExpression2737 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_collectionExpr_in_quantifiedExpression2741 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPEN_in_quantifiedExpression2746 = new BitSet(new long[]{0x0029000000000000L,0x0042400800000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_subQuery_in_quantifiedExpression2751 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_CLOSE_in_quantifiedExpression2755 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_primaryExpression_in_atom2774 = new BitSet(new long[]{0x0000000200000002L,0x0000000200000000L});
	public static final BitSet FOLLOW_DOT_in_atom2783 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_identifier_in_atom2786 = new BitSet(new long[]{0x0000000200000002L,0x0000000300000000L});
	public static final BitSet FOLLOW_OPEN_in_atom2814 = new BitSet(new long[]{0x1081121410A54940L,0x032404C11F899080L});
	public static final BitSet FOLLOW_exprList_in_atom2819 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_CLOSE_in_atom2821 = new BitSet(new long[]{0x0000000200000002L,0x0000000200000000L});
	public static final BitSet FOLLOW_OPEN_BRACKET_in_atom2835 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_expression_in_atom2840 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_CLOSE_BRACKET_in_atom2842 = new BitSet(new long[]{0x0000000200000002L,0x0000000200000000L});
	public static final BitSet FOLLOW_identPrimary_in_primaryExpression2862 = new BitSet(new long[]{0x0000000200000002L});
	public static final BitSet FOLLOW_DOT_in_primaryExpression2875 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_CLASS_in_primaryExpression2878 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constant_in_primaryExpression2888 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COLON_in_primaryExpression2895 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_identifier_in_primaryExpression2898 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPEN_in_primaryExpression2907 = new BitSet(new long[]{0x10A9121410844940L,0x026644C91F899000L,0x0000000000000004L});
	public static final BitSet FOLLOW_expressionOrVector_in_primaryExpression2911 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_subQuery_in_primaryExpression2915 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_CLOSE_in_primaryExpression2918 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PARAM_in_primaryExpression2926 = new BitSet(new long[]{0x0000000000000002L,0x0000000008000000L});
	public static final BitSet FOLLOW_NUM_INT_in_primaryExpression2930 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_expressionOrVector2948 = new BitSet(new long[]{0x0000000001000002L});
	public static final BitSet FOLLOW_vectorExpr_in_expressionOrVector2954 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COMMA_in_vectorExpr2993 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_expression_in_vectorExpr2996 = new BitSet(new long[]{0x0000000001000002L});
	public static final BitSet FOLLOW_COMMA_in_vectorExpr2999 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_expression_in_vectorExpr3002 = new BitSet(new long[]{0x0000000001000002L});
	public static final BitSet FOLLOW_identifier_in_identPrimary3018 = new BitSet(new long[]{0x0000000200000002L,0x0000000100000000L});
	public static final BitSet FOLLOW_DOT_in_identPrimary3036 = new BitSet(new long[]{0x0080000000000000L,0x0000000020000000L});
	public static final BitSet FOLLOW_identifier_in_identPrimary3041 = new BitSet(new long[]{0x0000000200000002L,0x0000000100000000L});
	public static final BitSet FOLLOW_OBJECT_in_identPrimary3047 = new BitSet(new long[]{0x0000000200000002L,0x0000000100000000L});
	public static final BitSet FOLLOW_OPEN_in_identPrimary3065 = new BitSet(new long[]{0x1081121410A54940L,0x032404C11F899080L});
	public static final BitSet FOLLOW_exprList_in_identPrimary3070 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_CLOSE_in_identPrimary3072 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_aggregate_in_identPrimary3088 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SUM_in_aggregate3109 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_AVG_in_aggregate3115 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_MAX_in_aggregate3121 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_MIN_in_aggregate3127 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_OPEN_in_aggregate3131 = new BitSet(new long[]{0x1080121410840940L,0x022404C11F819000L});
	public static final BitSet FOLLOW_additiveExpression_in_aggregate3133 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_CLOSE_in_aggregate3135 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COUNT_in_aggregate3154 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_OPEN_in_aggregate3156 = new BitSet(new long[]{0x1080000480000040L,0x0010000000000000L});
	public static final BitSet FOLLOW_STAR_in_aggregate3162 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_aggregateDistinctAll_in_aggregate3168 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_CLOSE_in_aggregate3172 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_collectionExpr_in_aggregate3204 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_path_in_aggregateDistinctAll3230 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_collectionExpr_in_aggregateDistinctAll3234 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ELEMENTS_in_collectionExpr3253 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_INDICES_in_collectionExpr3258 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_OPEN_in_collectionExpr3262 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_path_in_collectionExpr3265 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_CLOSE_in_collectionExpr3267 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_collectionExpr_in_compoundExpr3322 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_path_in_compoundExpr3327 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPEN_in_compoundExpr3333 = new BitSet(new long[]{0x10A9121410844940L,0x026644C91F899000L,0x0000000000000004L});
	public static final BitSet FOLLOW_subQuery_in_compoundExpr3338 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_expression_in_compoundExpr3343 = new BitSet(new long[]{0x0000000001200000L});
	public static final BitSet FOLLOW_COMMA_in_compoundExpr3346 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_expression_in_compoundExpr3349 = new BitSet(new long[]{0x0000000001200000L});
	public static final BitSet FOLLOW_CLOSE_in_compoundExpr3356 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRAILING_in_exprList3375 = new BitSet(new long[]{0x1081121410844942L,0x022404C11F899000L});
	public static final BitSet FOLLOW_LEADING_in_exprList3388 = new BitSet(new long[]{0x1081121410844942L,0x022404C11F899000L});
	public static final BitSet FOLLOW_BOTH_in_exprList3401 = new BitSet(new long[]{0x1081121410844942L,0x022404C11F899000L});
	public static final BitSet FOLLOW_expression_in_exprList3425 = new BitSet(new long[]{0x0001000001000202L});
	public static final BitSet FOLLOW_COMMA_in_exprList3430 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_expression_in_exprList3433 = new BitSet(new long[]{0x0000000001000002L});
	public static final BitSet FOLLOW_FROM_in_exprList3448 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_expression_in_exprList3450 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_AS_in_exprList3462 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_identifier_in_exprList3465 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FROM_in_exprList3479 = new BitSet(new long[]{0x1080121410844940L,0x022404C11F899000L});
	public static final BitSet FOLLOW_expression_in_exprList3481 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_innerSubQuery_in_subQuery3501 = new BitSet(new long[]{0x0000000000000002L,0x1000000000000000L});
	public static final BitSet FOLLOW_UNION_in_subQuery3504 = new BitSet(new long[]{0x0029000000000000L,0x0042400800000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_innerSubQuery_in_subQuery3507 = new BitSet(new long[]{0x0000000000000002L,0x1000000000000000L});
	public static final BitSet FOLLOW_queryRule_in_innerSubQuery3521 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_path3609 = new BitSet(new long[]{0x0000000200000002L});
	public static final BitSet FOLLOW_DOT_in_path3613 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_identifier_in_path3618 = new BitSet(new long[]{0x0000000200000002L});
	public static final BitSet FOLLOW_IDENT_in_identifier3634 = new BitSet(new long[]{0x0000000000000002L});
}
