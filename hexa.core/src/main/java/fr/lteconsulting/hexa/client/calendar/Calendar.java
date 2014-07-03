package fr.lteconsulting.hexa.client.calendar;

import java.util.List;
import java.util.Stack;

/**
 * Top Level Class parsing complex period expression and building CalendarPeriod
 * objects<br>
 * <b>Usage :</b><br>
 * Expression operators: '|' => union, '&' => intersection, '~' => not<br>
 * Expression operands : '[2013-06-12;2013-06-21]' period, 'd0' => day : 0 =
 * Sunday, 6 = Saturday<br>
 * Expression is built in Reverse Polish Notation format. <br>
 * <b>Example:</b> Period from the 12 june 2013 to the 21 june 2013 except
 * Sunday<br>
 * String expression = "[2013-06-12;2013-06-21] d0 ~ &";<br>
 * Tree tree = Calendar.get().Parse(expression);<br>
 * <br>
 * <b>Special:</b> MakupCalendarPeriodAssociative() see method description<br>
 * 
 * @author Arnaud, traduction Java Laurent
 * 
 */
public class Calendar
{
	private static Calendar _instance = null;

	public static Calendar get()
	{
		if( _instance == null )
			_instance = new Calendar();

		return _instance;
	}

	public Tree Parse( String pExpression )
	{
		return _Parse( pExpression );
	}

	// takes as a parameter an array of period expressions and their initial
	// values
	// returns an associative calendar that is the merge of all these
	// $addFunction is a callback that can combine two period values
	/**
	 * Create CalendarPeriodAssociative using an adding function.<br>
	 * PeriodAssociative are periods associated with an integer, result of the
	 * add function.
	 * 
	 * @param periodsAndValues
	 * @param addFunction
	 * @return CalendarPeriodAssociative
	 */
	public <T> CalendarPeriodAssociative<T> MakeUpCalendarPeriodAssociative( List<Tree> trees, List<T> values, CalendarPeriodAssociative.AddFunction<T> addFunction )
	{
		if( trees.size() != values.size() )
		{
			assert false : "Calendar.MakeUpCalendarPeriodAssociative : trees and values should have the same size !";
			return null;
		}

		int nbPeriod = trees.size();
		if( nbPeriod == 0 )
			return new CalendarPeriodAssociative<T>();

		// get the first non empty period
		CalendarPeriodAssociative<T> res = null;
		int first = 0;
		do
		{
			Tree tree = trees.get( first );
			T value = values.get( first );

			// PeriodAssociative firstPeriodAndValue = periodsAndValues.get(
			// first );
			// Tree tree = Parse( firstPeriodAndValue.getFrom() );
			if( tree.getNbDays() == 0 )
			{
				first++;
				if( first >= nbPeriod )
					return new CalendarPeriodAssociative<T>();
				continue;
			}

			CalendarPeriod flat = tree.getFlat();
			res = new CalendarPeriodAssociative<T>();
			res.Init( flat, value );

			break;
		}
		while( true );

		if( res == null )
			return null; // no non-empty period

		for( int i = first + 1; i < nbPeriod; i++ )
		{
			Tree tree = trees.get( i );
			T value = values.get( i );

			if( tree.getNbDays() == 0 )
			{
				// echo "IGNORING N PERIOD $i (" . $periodsAndValues[$i][0] .
				// ") BECAUSE EMPTY<br>";
				continue;
			}

			CalendarPeriod flat = tree.getFlat();
			CalendarPeriodAssociative<T> assoc = new CalendarPeriodAssociative<T>();
			assoc.Init( flat, value );

			if( res.Add( assoc, addFunction ) == null )
				return null;
		}

		return res;
	}

	// public CalendarPeriodAssociative MakeUpCalendarPeriodAssociative(
	// PeriodsAssociative periodsAndValues,
	// CalendarPeriodAssociative.AddFunction addFunction )
	// {
	// int nbPeriod = periodsAndValues.size();
	// if( nbPeriod == 0 )
	// return new CalendarPeriodAssociative();
	//
	// // get the first non empty period
	// CalendarPeriodAssociative res = null;
	// int first = 0;
	// do
	// {
	// PeriodAssociative firstPeriodAndValue = periodsAndValues.get( first );
	// Tree tree = Parse( firstPeriodAndValue.getFrom() );
	// if( tree.getNbDays() == 0 )
	// {
	// // echo "IGNORING F PERIOD $first (" . $firstPeriodAndValue[0] .
	// ") BECAUSE EMPTY<br>";
	// first++;
	// if( first >= nbPeriod )
	// return new CalendarPeriodAssociative();
	// continue;
	// }
	//
	// CalendarPeriod flat = tree.getFlat();
	// res = new CalendarPeriodAssociative();
	// res.Init( flat, firstPeriodAndValue.getValue() ); // TODO :
	// firstPeriodAndValue might be wrong type
	//
	// break;
	// }
	// while( true );
	//
	// if( res == null )
	// return null; // no non-empty period
	//
	// for( int i = first + 1; i < nbPeriod; i++ )
	// {
	// Tree tree = Parse( periodsAndValues.get( i ).getFrom() );
	// if( tree.getNbDays() == 0 )
	// {
	// // echo "IGNORING N PERIOD $i (" . $periodsAndValues[$i][0] .
	// ") BECAUSE EMPTY<br>";
	// continue;
	// }
	//
	// CalendarPeriod flat = tree.getFlat();
	// CalendarPeriodAssociative assoc = new CalendarPeriodAssociative();
	// assoc.Init( flat, periodsAndValues.get( i ).getValue() ); // TODO :
	// firstPeriodAndValue might be wrong type
	//
	// if( res.Add( assoc, addFunction ) == null )
	// return null;
	// }
	//
	// return res;
	// }

	/**
	 * 
	 * @author Arnaud, Laurent
	 */
	private Tree _Parse( String pExpression )
	{
		ParsingExpression exp = new ParsingExpression( pExpression );

		Stack<Tree> stack = new Stack<Tree>();

		Tree token;
		while( (token = _NextToken( exp )) != null )
		{
			switch( token.getType() )
			{
				case ALWAYS:
				case NEVER:
				case DAY:
				case PERIOD:
					stack.push( token );
					break;

				case NOT:
				{
					Tree operand = stack.pop();
					stack.push( Tree.CreateNot( operand ) );
					break;
				}

				case AND:
				{
					Tree operandR = stack.pop();
					Tree operandL = stack.pop();
					stack.push( Tree.CreateAnd( operandL, operandR ) );
					break;
				}

				case OR:
				{
					Tree operandR = stack.pop();
					Tree operandL = stack.pop();
					stack.push( Tree.CreateOr( operandL, operandR ) );
					break;
				}
			}
		}

		if( stack.size() != 1 )
			return null;

		return stack.pop();

	}

	// public Boolean _ParseParam( String expression, String dateParam, int
	// dayParam )
	// {
	// exp = new Expression( expression );
	//
	// stack = new Stack();
	//
	// Tree token = null;
	// while( ( token = _NextToken() ) != null )
	// {
	// // echo "token : " . $token['t_type'] . "<br>";
	// if( token.getType().equals( "a" ) )
	// {
	// stack.push( new Tree( true ) );
	// }
	// else if( token.getType().equals( "n" ) )
	// {
	// stack.push( new Tree( false ) );
	// }
	// else if( token.getType().equals( "d" ) )
	// {
	// if( token.getVal() == dayParam )
	// stack.push( new Tree( true ) );
	// else
	// stack.push( new Tree( false ) );
	// }
	// else if( token.getType().equals( "p" ) )
	// {
	// if( (token.getFrom().compareTo( dateParam ) <= 0) &&
	// (token.getTo().compareTo( dateParam ) >= 0) )
	// stack.push( new Tree( true ) );
	// else
	// stack.push( new Tree( false ) );
	// }
	// else if( token.getType().equals( "~" ) )
	// {
	// stack.push( new Tree( !stack.pop().getBool() ) );
	// }
	// else if( token.getType().equals( "&" ) )
	// {
	// boolean operandR = stack.pop().getBool();
	// boolean operandL = stack.pop().getBool();
	// stack.push( new Tree( operandL & operandR ) );
	// }
	// else if( token.getType().equals( "|" ) )
	// {
	// boolean operandR = stack.pop().getBool();
	// boolean operandL = stack.pop().getBool();
	// stack.push( new Tree( operandL | operandR ) );
	// }
	// }
	//
	// if( stack.count() != 1 )
	// return null;
	//
	// return stack.get0().getBool();
	// }

	/**
	 * 
	 * 
	 * @return boolean
	 * 
	 * @author Arnaud, Laurent
	 */
	private Tree _NextToken( ParsingExpression exp )
	{
		exp.skipWhiteSpaces();

		// end of text...
		if( exp.isEndOfText() )
			return null;

		// test for period token
		if( exp.testChar( '[' ) )
		{
			exp.incPos(); // pass the '['

			// get date FROM
			if( !exp.readFrom() )
			{
				// Date not found : finished ...
				return null;
			}

			exp.skipWhiteSpaces();

			// check for ;
			if( !exp.testChar( ';' ) )
			{
				// error
				return null;
			}

			exp.incPos();

			exp.skipWhiteSpaces();

			// get date TO
			if( !exp.readTo() )
			{
				// Date not found : finished ...
				return null;
			}

			exp.skipWhiteSpaces();

			// check for ']'
			if( !exp.testChar( ']' ) )
			{
				// error
				return null;
			}

			exp.incPos();

			// return the period token
			Tree token = Tree.CreatePeriod( exp.getFrom(), exp.getTo() );
			return token;
		}

		// test for day token
		if( exp.testChar( 'd' ) )
		{
			exp.incPos(); // pass the 'd'

			// get number
			if( !exp.readDay() )
			{
				// day not found : finished ...
				return null;
			}

			// return day token
			Tree token = Tree.CreateDay( Integer.parseInt( exp.getDay() ) );
			return token;
		}

		// test for operator token
		if( exp.testChar( '~' ) || exp.testChar( '&' ) || exp.testChar( '|' ) )
		{
			exp.readOp();

			String op = exp.getOp();

			if( op.equals( "~" ) )
				return Tree.CreateNot( null );
			else if( op.equals( "&" ) )
				return Tree.CreateAnd( null, null );
			else if( op.equals( "|" ) )
				return Tree.CreateOr( null, null );
			else
				throw new IllegalArgumentException( "Unrecognized type from token : " + op );
		}

		// test for the 'always' token
		if( exp.testChar( 'a' ) )
		{
			exp.incPos();

			Tree token = Tree.CreateAlways();
			return token;
		}

		// test for the 'never' token
		if( exp.testChar( 'n' ) )
		{
			exp.incPos();

			Tree token = Tree.CreateNever();
			return token;
		}

		// last try a date only, which is shortcut for [from;to] with from and
		// to equal...
		if( exp.readDate() )
		{
			Tree token = Tree.CreatePeriod( exp.getDate() );
			return token;
		}

		return null;
	}
}
