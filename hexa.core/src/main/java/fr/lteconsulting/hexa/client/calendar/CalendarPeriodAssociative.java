package fr.lteconsulting.hexa.client.calendar;

import java.util.ArrayList;
import java.util.List;

import fr.lteconsulting.hexa.client.calendar.CalendarPeriod.Period;

public class CalendarPeriodAssociative<T>
{
	// Attributes
	// list of untouching periods in chronological order
	private List<PeriodAssociative<T>> periods = new ArrayList<PeriodAssociative<T>>(); // 0:from,
																						// 1:to,
																						// 2:value

	// Constructors
	public CalendarPeriodAssociative()
	{

	}

	// Laurent : Constructors added for commodity
	public CalendarPeriodAssociative( CalendarPeriod period, T value )
	{
		Init( period, value );
	}

	// Getters
	public List<PeriodAssociative<T>> getPeriods()
	{
		return periods;
	}

	// Setters
	public void setPeriods( List<PeriodAssociative<T>> p )
	{
		periods = p;
	}

	// init from a CalendarPeriod with a value
	public void Init( CalendarPeriod period, T value )
	{
		for( int i = 0; i < period.getPeriods().size(); i++ )
		{
			Period p = period.getPeriods().get( i );
			periods.add( new PeriodAssociative<T>( p.getFrom(), p.getTo(), value ) );
		}
	}

	// print string
	public String Out()
	{
		String out = "";
		for( int i = 0; i < periods.size(); i++ )
		{
			PeriodAssociative<T> period = periods.get( i );
			out += "[" + period.getFrom() + ";" + period.getTo() + "] => " + period.getValue() + "<br>";
		}
		return out;
	}

	// print string
	public String OutEx( Printer<T> printer )
	{
		String out = "";
		for( int i = 0; i < periods.size(); i++ )
		{
			PeriodAssociative<T> period = periods.get( i );
			out += "[" + period.getFrom() + "->" + period.getTo() + "] => " + printer.print( period.getValue() ) + "<br>";
		}
		return out;
	}

	// adding two associative periods
	public Boolean Add( CalendarPeriodAssociative<T> period, AddFunction<T> addFunction )
	{
		// combine les deux tableaux dans ordre croissant
		List<PeriodAssociative<T>> combined = _Combine( periods, period.getPeriods() );

		// merge overlapping periods
		List<PeriodAssociative<T>> result = _Merge( combined, addFunction );
		if( result == null )
			return null; // we should stop the MakeUpCalendarAssociative process

		periods = result;

		// to say we can continue...
		return true;
	}

	// returns the same but with no values, and with the periods merged
	public CalendarPeriod GetCalendarPeriod()
	{
		CalendarPeriod res = new CalendarPeriod();
		List<Period> periods = new ArrayList<Period>();
		for( int i = 0; i < this.periods.size(); i++ )
		{
			PeriodAssociative<T> p = this.periods.get( i );
			periods.add( new Period( p.getFrom(), p.getTo() ) );
		}
		// use merge to merge jointed periods...
		res.setPeriods( res._Merge( periods ) );

		return res;
	}

	// returns a CalendarPeriodAssociative objet corresponding to the periods
	// where $testFunction returned true
	public CalendarPeriodAssociative<T> Extract( TestFunction<T> testFunction )
	{
		List<PeriodAssociative<T>> _periods = new ArrayList<PeriodAssociative<T>>();
		for( int i = 0; i < periods.size(); i++ )
		{
			PeriodAssociative<T> test = periods.get( i );

			if( testFunction.function( test.getValue() ) )
				_periods.add( test );
		}

		CalendarPeriodAssociative<T> res = new CalendarPeriodAssociative<T>();
		res.setPeriods( _periods );

		return res;
	}

	// returns a new object that is the current object trimmed by the given
	// $calendarPeriod
	public CalendarPeriodAssociative<T> Trim( CalendarPeriodAssociative<T> calendarPeriod )
	{
		List<PeriodAssociative<T>> result = new ArrayList<PeriodAssociative<T>>();

		int count1 = periods.size();
		int count2 = calendarPeriod.getPeriods().size();

		int i = 0;
		int j = 0;

		while( i < count1 && j < count2 )
		{
			// one of the periods begins after the end of the other
			if( periods.get( i ).getFrom().compareTo( calendarPeriod.getPeriods().get( j ).getTo() ) > 0 )
			{ // period 1 begins after period 2 finishes => period2 is
				// eliminated !
				j++;
			}
			else if( calendarPeriod.getPeriods().get( j ).getFrom().compareTo( periods.get( i ).getTo() ) > 0 )
			{ // period 2 begins after end of period 1 => period 1 is eliminated
				// !
				i++;
			}

			// after that test, we can assume there is a non-void intersection
			else
			{
				result.add( new PeriodAssociative<T>( CalendarFunctions.max_date( periods.get( i ).getFrom(), calendarPeriod.getPeriods().get( j ).getFrom() ), CalendarFunctions.min_date( periods.get( i ).getTo(), calendarPeriod.getPeriods().get( j ).getTo() ), periods.get( i )
						.getValue() ) );

				if( periods.get( i ).getTo().compareTo( calendarPeriod.getPeriods().get( j ).getTo() ) > 0 )
					j++;
				else
					i++;
			}
		}

		CalendarPeriodAssociative<T> res = new CalendarPeriodAssociative<T>();
		res.setPeriods( result );

		return res;
	}

	// combine two period arrays ordered by from date
	private List<PeriodAssociative<T>> _Combine( List<PeriodAssociative<T>> periods1, List<PeriodAssociative<T>> periods2 )
	{
		List<PeriodAssociative<T>> result = new ArrayList<PeriodAssociative<T>>();

		int count1 = periods1.size();
		int count2 = periods2.size();

		int i = 0;
		int j = 0;
		while( i < count1 && j < count2 )
		{
			if( periods1.get( i ).getFrom().compareTo( periods2.get( j ).getFrom() ) <= 0 )
			{
				result.add( periods1.get( i ) );
				i++;
			}
			else
			{
				result.add( periods2.get( j ) );
				j++;
			}
		}
		while( i < count1 )
		{
			result.add( periods1.get( i ) );
			i++;
		}
		while( j < count2 )
		{
			result.add( periods2.get( j ) );
			j++;
		}

		return result;
	}

	// merge, that is combine overlapping periods
	private List<PeriodAssociative<T>> _Merge( List<PeriodAssociative<T>> periods, AddFunction<T> addFunction )
	{
		int count = periods.size();
		if( count == 0 )
			return new ArrayList<PeriodAssociative<T>>();
		if( count == 1 )
			return periods;

		List<PeriodAssociative<T>> result = new ArrayList<PeriodAssociative<T>>();

		while( periods.size() > 1 )
		{
			if( periods.get( 1 ).getFrom().compareTo( periods.get( 0 ).getTo() ) > 0 )
			{
				// period is disjointed, so forget the first period, add it
				// directly into the results
				result.add( periods.remove( 0 ) );
			}
			else
			{
				List<PeriodAssociative<T>> toAdd = new ArrayList<PeriodAssociative<T>>();

				if( periods.get( 0 ).getFrom().compareTo( periods.get( 1 ).getFrom() ) < 0 )
				{
					PeriodAssociative<T> created = new PeriodAssociative<T>( periods.get( 0 ).getFrom(), CalendarFunctions.date_add_day( periods.get( 1 ).getFrom(), -1 ), periods.get( 0 ).getValue() );
					toAdd.add( created );
				}

				if( periods.get( 1 ).getTo().compareTo( periods.get( 0 ).getTo() ) < 0 )
				{
					T r = addFunction.function( periods.get( 0 ).getValue(), periods.get( 1 ).getValue() );
					if( r == null )
						return null;

					toAdd.add( new PeriodAssociative<T>( periods.get( 1 ).getFrom(), periods.get( 1 ).getTo(), r ) );
					toAdd.add( new PeriodAssociative<T>( CalendarFunctions.date_add_day( periods.get( 1 ).getTo(), 1 ), periods.get( 0 ).getTo(), periods.get( 0 ).getValue() ) );
				}
				else if( periods.get( 1 ).getTo().equals( periods.get( 0 ).getTo() ) )
				{
					T r = addFunction.function( periods.get( 0 ).getValue(), periods.get( 1 ).getValue() );
					if( r == null )
						return null;

					toAdd.add( new PeriodAssociative<T>( periods.get( 1 ).getFrom(), periods.get( 1 ).getTo(), r ) );
				}
				else
				// $periods[1][1] > $periods[0][1]
				{
					T r = addFunction.function( periods.get( 0 ).getValue(), periods.get( 1 ).getValue() );
					if( r == null )
						return null;

					toAdd.add( new PeriodAssociative<T>( periods.get( 1 ).getFrom(), periods.get( 0 ).getTo(), r ) );
					toAdd.add( new PeriodAssociative<T>( CalendarFunctions.date_add_day( periods.get( 0 ).getTo(), 1 ), periods.get( 1 ).getTo(), periods.get( 1 ).getValue() ) );
				}

				// periods 0 and 1 should be replaced by the newly calculated
				// $toAdd periods

				// remove periods 0 and 1
				periods.remove( 0 );
				periods.remove( 0 );

				periods = _Combine( periods, toAdd );
			}
		}

		if( periods.size() > 0 )
			result.add( periods.remove( 0 ) );

		return result;
	}

	// Additional Classes for Java (not in PHP source)
	@Override
	public String toString()
	{
		return periods.toString();
	}

	// Interfaces
	public interface Printer<T>
	{
		public String print( T value );
	}

	public interface AddFunction<T>
	{
		public T function( T a, T b );
	}

	public interface TestFunction<T>
	{
		public Boolean function( T a );
	}

	public static class PeriodAssociative<T>
	{
		// Attributes
		private String from = null;
		private String to = null;
		private T value = null;

		// Constructors
		PeriodAssociative()
		{
		}

		PeriodAssociative( String pFrom, String pTo )
		{
			from = pFrom;
			to = pTo;
		}

		PeriodAssociative( String pFrom, String pTo, T pV )
		{
			from = pFrom;
			to = pTo;
			value = pV;
		}

		// Getters
		public String getFrom()
		{
			return from;
		}

		public String getTo()
		{
			return to;
		}

		public T getValue()
		{
			return value;
		}

		// Setters
		public void setValue( T v )
		{
			value = v;
		}

		// Public methods
		@Override
		public String toString()
		{
			String res = "[" + from + ":" + to + "] = " + value;
			return res;
		}
	}
}
