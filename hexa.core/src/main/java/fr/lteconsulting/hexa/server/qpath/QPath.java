package fr.lteconsulting.hexa.server.qpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;

import fr.lteconsulting.hexa.server.qpath.QPathResult.QPathResultRow;
import fr.lteconsulting.hexa.server.tools.LoggerFactory;

public class QPath
{
	private Database db = null;
	private DatabaseHelper dbh = null;

	private final static Logger logger = LoggerFactory.getLogger();

	private final HashMap<String, String> singularizations = new HashMap<String, String>();
	private final HashMap<String, String> pluralizations = new HashMap<String, String>();

	private final AutoDTOs autoDtos = new AutoDTOs( this );

	public void init( Database database )
	{
		init( database, null );
	}

	public void init( Database database, DatabaseHelper databaseHelper )
	{
		this.db = database;
		this.dbh = databaseHelper;

		logger.info( "QPath initialized with database " + database.getCurrentDatabase() );
	}

	public void term()
	{
		db = null;
		dbh = null;

		logger.info( "QPath terminated" );
	}

	// function NewParsedQuery( $template )
	// function NewQWalk( $table, $id, $idField = 'id', $rawRecord = null )
	// function QPex()

	public AutoDTOs getAutoDTOs()
	{
		return autoDtos;
	}

	/*
	 * Converts a QPath expression to the corresponding in SQL language
	 */
	public String toSql( String expression )
	{
		return parseEx( expression, null, null, null );
	}

	/*
	 * Executes a QPath expression and return a corresponding QPathResult
	 */
	public QPathResult queryEx( String expression )
	{
		return queryEx( expression, null, null );
	}

	public QPathResult queryEx( String expression, Integer limitStart, Integer limitSize )
	{
		String sql = parseEx( expression, null, limitStart, limitSize );
		if( sql == null )
			return null;

		DBResults dbRes = db.sql( sql );
		if( dbRes == null )
			return null;

		QPathResult res = new QPathResult( dbRes );

		dbRes.close();

		return res;
	}

	public <T> Iterable<T> queryExDTO( final Class<T> clazz, String expression )
	{
		return queryExDTO( clazz, expression, null, null );
	}

	public <T> Iterable<T> queryExDTO( final Class<T> clazz, String expression, Integer limitStart, Integer limitSize )
	{
		final QPathResult res = queryEx( expression, limitStart, limitSize );
		return new Iterable<T>()
		{
			final Iterator<QPathResultRow> iterator = res.iterator();

			@Override
			public Iterator<T> iterator()
			{
				return new Iterator<T>()
				{
					@Override
					public boolean hasNext()
					{
						return iterator.hasNext();
					}

					@Override
					public T next()
					{
						return autoDtos.get( clazz ).convert( iterator.next() );
					}

					@Override
					public void remove()
					{
						assert false;
					}
				};
			}
		};
	}

	/*
	 * Executes a QPath expression and return only one row, or null if there is
	 * none in the result
	 */
	public QPathResultRow queryOne( String expression )
	{
		final QPathResult res = queryEx( expression );
		if( res == null )
			return null;

		if( res.getNbRows() < 1 )
			return null; // easy way for the caller to know that no result were
							// found

		return new QPathResultRow()
		{
			@Override
			public <T> T get( String field )
			{
				return res.getValue( 0, field );
			}
		};
	}

	public <T> T queryOneDTO( Class<T> clazz, String expression )
	{
		return autoDtos.get( clazz ).convert( queryOne( expression ) );
	}

	private String parseEx( String expression, String whereStatement, Integer limitStart, Integer limitSize )
	{
		// allows to use variable arguments
		// $args = func_get_args();
		// $expression = call_user_func_array( 'sprintf', $args );

		Token tree = _Parse( expression );

		// Dump( $tree );

		TravInfo travInfo = _Traverse( tree );

		String sql = "SELECT " + travInfo.sql_fields + " FROM " + travInfo.sql_from;

		if( whereStatement == null )
			whereStatement = travInfo.sql_where;
		if( !whereStatement.isEmpty() )
			sql += " WHERE " + whereStatement;
		if( travInfo.sql_group_by != null )
			sql += " GROUP BY " + travInfo.sql_group_by;

		if( travInfo.sql_order_by != null )
			sql += " ORDER BY " + travInfo.sql_order_by + " ASC";

		if( limitStart != null && limitSize != null )
			sql += " LIMIT " + limitStart + ", " + limitSize;

		return sql;
	}

	public void addPluralForm( String singular, String plural )
	{
		singularizations.put( plural, singular );
		pluralizations.put( singular, plural );
	}

	public String pluralize( String str )
	{
		String pluralized = pluralizations.get( str );

		if( pluralized == null )
			return str + "s";

		return pluralized;
	}

	public String singularize( String str )
	{
		String singularized = singularizations.get( str );

		if( singularized == null )
			return str.substring( 0, str.length() - 1 );// substr($str, 0, -1);

		return singularized;
	}

	private Token _Parse( String toEval )
	{
		ArrayList<Token> tokens = new ArrayList<Token>();
		int toEvalLen = toEval.length();

		// while( $token = $this->_NextToken( $toeval, $toEvalLen, $pos ) )
		// {
		// $tokens = array_merge( $tokens, $token );
		// }
		NavigableString navToEval = new NavigableString( toEval );
		while( true )
		{
			List<Token> nextTokens = _NextToken( navToEval, toEvalLen );
			if( nextTokens == null )
				break;
			tokens.addAll( nextTokens );
		}

		// $stack = array();
		ArrayList<Token> stack = new ArrayList<QPath.Token>();
		while( true )
		{
			// etait déjà commenté en PHP
			// echo "parser $pos stack-layout:'";
			// for($i=0;$i<count($stack);$i++)
			// echo $stack[$i]['t_type'];
			// echo "'<br>";

			// $token = array_shift( $tokens );
			if( tokens.isEmpty() )
				break;

			Token token = tokens.remove( 0 );

			// $nextToken = null;
			// if( count($tokens) > 0 )
			// $nextToken = &$tokens[0];
			Token nextToken = null;
			if( !tokens.isEmpty() )
				nextToken = tokens.get( 0 );

			// array_unshift( $stack, $token );
			stack.add( 0, token );

			while( _TryReduce( stack, nextToken ) > 0 )
				;

			// $this->Dump( $token );
			// echo "<br>";
		}

		// if( count($stack)==1 && $stack[0]['t_type']=='e' )
		// {
		// echo "parse successful!!!<br>";
		// return $stack[0];
		// }
		if( stack.size() == 1 && stack.get( 0 ).t_type == 'e' )
		{
			return stack.get( 0 );
		}

		// echo
		// "QPath parse error with expression : <b>$toeval</b><br>Stack content is :";
		// Dump( $stack );
		logger.error( "QPath parse error with expression : <b>" + toEval + "</b><br>Stack content is :" + stack );

		return null;
	}

	char tokens[] = new char[] { '(', ')', '[', ']', '{', '}', '?', 'G', 'F', 'O' };

	private boolean _IsToken( char c )
	{
		switch( c )
		{
			case '(':
			case ')':
			case '[':
			case ']':
			case '{':
			case '}':
			case '?':
			case 'G':
			case 'A':
			case 'F':
			case 'O':
				return true;
		}
		return false;
	}

	private boolean _IsReducable( ArrayList<Token> stack, String test, int testLen )
	{
		// $testLen = strlen($test);

		if( stack.size() < testLen )
			return false;

		for( int i = 0; i < testLen; i++ )
		{
			if( test.charAt( i ) != stack.get( testLen - i - 1 ).t_type )
				return false;
		}

		// echo "$test is reducable<br>";

		return true;
	}

	// returns the next token and increments the position
	// returns null when no more token to come
	// function _NextToken( &$text, int toEvalLen, &$pos )
	private List<Token> _NextToken( NavigableString text, int toEvalLen )
	{
		int len = toEvalLen;

		// skip whitespaces
		while( (text.pos < len) && (text.cur() == ' ') )
			text.pos++;

		// end of text...
		if( text.pos >= len )
			return null;

		Token token = null;

		// test one char tokens
		int tokenSize = 1;
		if( _IsToken( text.cur() ) )
		{
			token = new Token( text.cur() );

			// if token is [ then produce the string going until next ]
			if( text.cur() == '[' )
			{
				text.pos++; // pass the [
				int i = 0;
				while( (text.pos + i) < len )
				{
					if( text.ahead( i ) == ']' )
						break;
					i++;
				}
				Token tokString = new Token( 's', text.extract( i ) ); // rtrim(substr($text,$pos,$i))
																		// );
				text.pos += i;
				Token tokenClose = new Token( ']' );
				text.pos++;

				return Arrays.asList( token, tokString, tokenClose );
			}
		}

		// test two chars tokens
		if( (token == null) && (text.pos + 1 < len) )
		{
			tokenSize = 2;
			if( text.cur() == '<' && text.ahead() == '-' )
				token = new Token( 'o', "<-" );
			else if( text.cur() == '-' && text.ahead() == '>' )
				token = new Token( 'o', "->" );
		}

		// test for a token string
		if( token == null )
		{
			int i = 0;
			while( (text.pos + i) < len )
			{
				char c = text.ahead( i );
				if( _IsToken( c ) )
					break;
				if( (c == '-') && (text.pos + i + 1 < len) && (text.ahead( i + 1 ) == '>') )
					break;
				if( (c == '<') && (text.pos + i + 1 < len) && (text.ahead( i + 1 ) == '-') )
					break;
				i++;
			}

			if( i > 0 )
			{
				tokenSize = i;
				token = new Token( 's', text.extract( i ) );
			}
		}

		if( token == null )
			return null;
		text.pos += tokenSize;
		return Arrays.asList( token );
	}

	private static class Token
	{
		char t_type;
		String t_val;

		// extended fields
		String type;
		String value;

		boolean muteFields;

		Token left;
		Token right;

		String leftField;
		String rightField;

		ArrayList<String> where = null;

		ArrayList<String> groupby = null;

		String tableAlias;

		Token val;

		ArrayList<Token> add_field = null;

		Token sort_field;

		ArrayList<Token> add_where = null;

		public Token( char type )
		{
			this( type, null );
		}

		public Token( char type, String val )
		{
			this.t_type = type;
			this.t_val = val;
		}

		public Token type( String type )
		{
			this.type = type;

			return this;
		}

		public Token value( String value )
		{
			this.value = value;

			return this;
		}

		public Token muteFields( boolean muteFields )
		{
			this.muteFields = muteFields;

			return this;
		}

		public Token left( Token left )
		{
			this.left = left;

			return this;
		}

		public Token right( Token right )
		{
			this.right = right;

			return this;
		}

		public Token leftField( String leftField )
		{
			this.leftField = leftField;

			return this;
		}

		public Token rightField( String rightField )
		{
			this.rightField = rightField;

			return this;
		}

		public Token where( String where )
		{
			if( this.where == null )
				this.where = new ArrayList<String>();

			this.where.add( where );

			return this;
		}

		public Token groupby( String groupby )
		{
			if( this.groupby == null )
				this.groupby = new ArrayList<String>();

			this.groupby.add( groupby );

			return this;
		}

		public Token tableAlias( String tableAlias )
		{
			this.tableAlias = tableAlias;

			return this;
		}

		public Token val( Token val )
		{
			this.val = val;

			return this;
		}

		public Token add_field( Token add_field )
		{
			if( this.add_field == null )
				this.add_field = new ArrayList<Token>();

			this.add_field.add( add_field );

			return this;
		}

		public Token sort_field( Token sort_field )
		{
			this.sort_field = sort_field;

			return this;
		}

		public Token add_where( Token add_where )
		{
			if( this.add_where == null )
				this.add_where = new ArrayList<Token>();

			this.add_where.add( add_where );

			return this;
		}
	}

	private int _TryReduce( ArrayList<Token> stack, Token nextToken )
	{
		if( _IsReducable( stack, "s", 1 ) )
		{
			Token reduced = stack.remove( 0 );
			// array_unshift( $stack, array( 't_type'=>'e', 'type'=>'v',
			// 'value'=>$reduced['t_val'] ) );
			stack.add( 0, new Token( 'e' ).type( "v" ).value( reduced.t_val ) );
			return 1;
		}

		if( _IsReducable( stack, "?e", 2 ) )
		{
			Token reduced = stack.remove( 0 );
			stack.remove( 0 );
			reduced.muteFields( true ); // $reduced['muteFields'] = "true";
			stack.add( 0, reduced ); // array_unshift( $stack, $reduced );
			return 1;
		}

		// if next token will be '[' we should not reduce this one...
		if( _IsReducable( stack, "eoe", 3 ) && ((nextToken == null) || ((nextToken.t_type != '[') && (nextToken.t_type != 'G') && (nextToken.t_type != 'A'))) )
		{
			Token opRight = stack.remove( 0 );
			Token op = stack.remove( 0 );
			Token opLeft = stack.remove( 0 );

			// $reduced = array( 't_type'=>'e', 'type'=>$op['t_val'],
			// 'left'=>$opLeft, 'right'=>$opRight );
			Token reduced = new Token( 'e' ).type( op.t_val ).left( opLeft ).right( opRight );
			// if( isset( $op['leftField'] ) )
			// $reduced = array_merge( $reduced, array(
			// 'leftField'=>$op['leftField'] ) );
			if( op.leftField != null )
				reduced.leftField( op.leftField );
			// if( isset( $op['rightField'] ) )
			// $reduced = array_merge( $reduced, array(
			// 'rightField'=>$op['rightField'] ) );
			if( op.rightField != null )
				reduced.rightField( op.rightField );
			stack.add( 0, reduced );
			return 1;
		}

		if( _IsReducable( stack, "(e)", 3 ) )
		{
			stack.remove( 0 );
			Token reduced = stack.remove( 0 );
			stack.remove( 0 );
			stack.add( 0, reduced );
			return 1;
		}

		if( _IsReducable( stack, "e[e]", 4 ) )
		{
			stack.remove( 0 );
			Token where = stack.remove( 0 );
			stack.remove( 0 );
			Token reduced = stack.remove( 0 );
			// TODO : faire gaffe a celui la
			// if( ! isset( $reduced['where'] ) )
			// $reduced['where'] = array();
			// $reduced['where'][] = $where['value'];
			reduced.where( where.value );
			stack.add( 0, reduced );
			return 1;
		}

		if( _IsReducable( stack, "eG[e]", 5 ) )
		{
			stack.remove( 0 );
			Token field = stack.remove( 0 );
			stack.remove( 0 );
			stack.remove( 0 );
			Token reduced = stack.remove( 0 );

			// TODO : faire gaffe a celui la
			// if( ! isset( $reduced['groupby'] ) )
			// $reduced['groupby'] = array();
			// $reduced['groupby'][] = $field['value'];
			reduced.groupby( field.value );

			stack.add( 0, reduced );
			return 1;
		}

		if( _IsReducable( stack, "eA[e]", 5 ) )
		{
			stack.remove( 0 );
			Token realTableName = stack.remove( 0 );
			stack.remove( 0 );
			stack.remove( 0 );
			Token reduced = stack.remove( 0 );

			reduced.tableAlias( realTableName.value );

			stack.add( 0, reduced );
			return 1;
		}

		if( _IsReducable( stack, "{e}o", 4 ) )
		{
			Token op = stack.remove( 0 );
			stack.remove( 0 );
			Token leftField = stack.remove( 0 );
			stack.remove( 0 );

			op.leftField( leftField.value );
			stack.add( 0, op );
			return 1;
		}

		if( _IsReducable( stack, "o{e}", 4 ) )
		{
			stack.remove( 0 );
			Token rightField = stack.remove( 0 );
			stack.remove( 0 );
			Token op = stack.remove( 0 );

			op.rightField( rightField.value );
			stack.add( 0, op );
			return 1;
		}

		if( _IsReducable( stack, "F[e]", 4 ) )
		{
			stack.remove( 0 );
			Token field = stack.remove( 0 );
			stack.remove( 0 );
			stack.remove( 0 );
			// array_unshift( $stack, array( 't_type'=>'f', 'val'=>$field ) );
			stack.add( 0, new Token( 'f' ).val( field ) );
			return 1;
		}

		if( _IsReducable( stack, "fe", 2 ) )
		{
			Token expr = stack.remove( 0 );
			Token field = stack.remove( 0 );

			// TODO : faire gaffe a celui la
			// if( ! isset( $expr['add_field'] ) )
			// $expr['add_field'] = array();
			// $expr['add_field'][] = $field['val'];
			expr.add_field( field.val );

			stack.add( 0, expr );
			return 1;
		}

		if( _IsReducable( stack, "O[e]", 4 ) )
		{
			stack.remove( 0 );
			Token field = stack.remove( 0 );
			stack.remove( 0 );
			stack.remove( 0 );
			// array_unshift( $stack, array( 't_type'=>'t', 'val'=>$field ) );
			stack.add( 0, new Token( 't' ).val( field ) );
			return 1;
		}

		if( _IsReducable( stack, "te", 2 ) )
		{
			Token expr = stack.remove( 0 );
			Token field = stack.remove( 0 );
			expr.sort_field( field.val );
			stack.add( 0, expr );
			return 1;
		}

		if( _IsReducable( stack, "[e]", 3 ) )
		{
			stack.remove( 0 );
			Token add_where = stack.remove( 0 );
			stack.remove( 0 );

			// array_unshift( $stack, array( 't_type'=>'w', 'val'=>$add_where )
			// );
			stack.add( 0, new Token( 'w' ).val( add_where ) );
			return 1;
		}

		if( _IsReducable( stack, "we", 2 ) )
		{
			Token expr = stack.remove( 0 );
			Token add_where = stack.remove( 0 );

			// TODO : faire gaffe a celui la
			// if( ! isset( $expr['add_where'] ) )
			// $expr['add_where'] = array();
			// $expr['add_where'][] = $add_where['val'];
			expr.add_where( add_where.val );

			stack.add( 0, expr );
			return 1;
		}

		return 0;
	}

	private static class TravInfo
	{
		String table;
		String tableAlias;

		String sql_from;
		String sql_fields;
		String sql_where;
		// StringBuilder sql_where = new StringBuilder();
		String sql_group_by;
		String sql_order_by;
	}

	private TravInfo _Traverse( Token tree )
	{
		TravInfo travInfo = new TravInfo();

		if( tree.type.equals( "->" ) || tree.type.equals( "<-" ) )
		{
			TravInfo leftTravInfo = _Traverse( tree.left );

			TravInfo rightTravInfo = _Traverse( tree.right );

			String leftTableAlias = leftTravInfo.table;
			if( leftTravInfo.tableAlias != null )
				leftTableAlias = leftTravInfo.tableAlias;

			String rightTableAlias = rightTravInfo.table;
			if( rightTravInfo.tableAlias != null )
				rightTableAlias = rightTravInfo.tableAlias;

			String leftField, rightField;
			if( tree.type.equals( "->" ) )
			{
				leftField = leftTableAlias + "." + singularize( rightTravInfo.table ) + "_id";
				rightField = rightTableAlias + ".id";
			}
			else if( tree.type.equals( "<-" ) )
			{
				leftField = leftTableAlias + ".id";
				rightField = rightTableAlias + "." + singularize( leftTravInfo.table ) + "_id";
			}
			else
			{
				leftField = "";
				rightField = "";
			}

			// custom fields
			if( tree.leftField != null )
				leftField = leftTableAlias + "." + tree.leftField;
			if( tree.rightField != null )
				rightField = rightTableAlias + "." + tree.rightField;

			travInfo.table = leftTravInfo.table;
			if( leftTravInfo.tableAlias != null )
				travInfo.tableAlias = leftTravInfo.tableAlias;

			travInfo.sql_from = " ( " + leftTravInfo.sql_from + " LEFT JOIN " + rightTravInfo.sql_from + " ON " + leftField + "=" + rightField + " ) ";
			travInfo.sql_where = " (" + leftTravInfo.sql_where + ") AND (" + rightTravInfo.sql_where + ") ";
			if( tree.where != null )
			{
				for( String clause : tree.where )
					travInfo.sql_where += " AND (" + leftTableAlias + "." + clause + ")";
			}
			if( tree.add_where != null )
			{
				// foreach( $tree['add_where'] as $clause )
				// $travInfo["sql_where"] .= ' AND (' . $clause['value'] . ')';
				for( Token clause : tree.add_where )
					travInfo.sql_where += " AND (" + clause.value + ")";
			}

			// group by
			ArrayList<String> groupby = new ArrayList<String>();
			if( leftTravInfo.sql_group_by != null )
				groupby.add( leftTravInfo.sql_group_by );
			if( rightTravInfo.sql_group_by != null )
				groupby.add( rightTravInfo.sql_group_by );
			// $gb = implode( ',', $groupby );
			// if( strlen( $gb ) > 0 )
			// $travInfo["sql_group_by"] = $gb;
			if( !groupby.isEmpty() )
				travInfo.sql_group_by = Tools.implode( ",", groupby );

			// fields
			travInfo.sql_fields = "";
			if( leftTravInfo.sql_fields != null )
			{
				if( rightTravInfo.sql_fields != null )
					travInfo.sql_fields = leftTravInfo.sql_fields + ", " + rightTravInfo.sql_fields;
				else
					travInfo.sql_fields = leftTravInfo.sql_fields;
			}
			else
			{
				if( rightTravInfo.sql_fields != null )
					travInfo.sql_fields = rightTravInfo.sql_fields;
			}

			// added fields
			if( tree.add_field != null )
			{
				ArrayList<String> fields = new ArrayList<String>();
				for( Token f : tree.add_field )
					fields.add( f.value );

				if( travInfo.sql_fields != null && travInfo.sql_fields != "" )
					fields.add( travInfo.sql_fields );

				travInfo.sql_fields = Tools.implode( ",", fields );
			}

			// sort fields
			ArrayList<String> sortby = new ArrayList<String>();
			if( leftTravInfo.sql_order_by != null )
				sortby.add( leftTravInfo.sql_order_by );
			if( rightTravInfo.sql_order_by != null )
				sortby.add( rightTravInfo.sql_order_by );
			if( tree.sort_field != null )
				sortby.add( tree.sort_field.value );
			if( !sortby.isEmpty() )
				travInfo.sql_order_by = Tools.implode( ", ", sortby );
		}
		else if( tree.type.equals( "v" ) )
		{
			travInfo.table = tree.value;
			String realTable = tree.value;
			String aliasTable = tree.value;
			if( tree.tableAlias != null )
			{
				aliasTable = tree.tableAlias;
				travInfo.tableAlias = tree.tableAlias;
				travInfo.sql_from = realTable + " AS " + tree.tableAlias;
			}
			else
			{
				travInfo.sql_from = realTable;
			}

			if( tree.where != null )
			{
				ArrayList<String> clauses = new ArrayList<String>();
				for( String clause : tree.where )
					clauses.add( aliasTable + "." + clause );
				travInfo.sql_where = Tools.implode( " AND ", clauses );
			}
			else
			{
				travInfo.sql_where = "1=1";
			}
			if( tree.add_where != null )
			{
				for( Token clause : tree.add_where )
					travInfo.sql_where += " AND (" + clause.value + ")";
			}

			if( tree.groupby != null )
			{
				ArrayList<String> groupBy = new ArrayList<String>();
				for( String field : tree.groupby )
					groupBy.add( aliasTable + "." + field );
				travInfo.sql_group_by = Tools.implode( ", ", groupBy );
			}

			// maybe fields are to be muted...
			if( tree.muteFields == false )
				travInfo.sql_fields = _GetFields( realTable, aliasTable );
			else
				travInfo.sql_fields = null;

			if( tree.add_field != null )
			{
				ArrayList<String> fields = new ArrayList<String>();
				for( Token f : tree.add_field )
					fields.add( f.value );

				if( travInfo.sql_fields != null )
					fields.add( travInfo.sql_fields );

				travInfo.sql_fields = Tools.implode( ",", fields );
			}

			// order by
			if( tree.sort_field != null )
				travInfo.sql_order_by = tree.sort_field.value;
		}
		else
		{
			return null;
		}

		return travInfo;
	}

	private void ensureDatabaseHelper()
	{
		if( dbh != null )
			return;

		dbh = new DatabaseHelper( db );
	}

	private String _GetFields( String tableName, String aliasName )
	{
		ensureDatabaseHelper();

		ArrayList<String> tableFields = dbh.getTableFields( tableName );

		StringBuilder b = new StringBuilder();
		boolean fAddComa = false;

		for( String field : tableFields )
		{
			if( fAddComa )
				b.append( ", " );
			else
				fAddComa = true;

			b.append( aliasName );
			b.append( "." );
			b.append( field );
			b.append( " AS `" );
			b.append( aliasName );
			b.append( "." );
			b.append( field );
			b.append( "`" );
		}

		return b.toString();
	}
}