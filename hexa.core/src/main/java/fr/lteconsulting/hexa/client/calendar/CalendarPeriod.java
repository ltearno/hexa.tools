package fr.lteconsulting.hexa.client.calendar;

import java.util.ArrayList;
import java.util.List;

public class CalendarPeriod
{
	// List of untouching periods in chronological order
	private List<Period> periods = null;

	// list of days this period is available
	// if in this mode, it is implied that its boundaries are
	// Functions.TIME_BEGIN to Functions.TIME_END
	private Days days = null;

	// Constructors
	public CalendarPeriod()
	{
	}

	public CalendarPeriod( CalendarPeriod model )
	{
		if( model.periods != null )
		{
			periods = new ArrayList<Period>();
			periods.addAll( model.periods );
		}

		if( model.days != null )
			days = new Days( model.days );
	}

	// Laurent : Constructors added for commodity
	public CalendarPeriod( String pFrom, String pTo )
	{
		Init( pFrom, pTo );
	}

	public CalendarPeriod( Period p )
	{
		Init( p.getFrom(), p.getTo() );
	}

	public CalendarPeriod( int pDay )
	{
		InitWeekDays( pDay );
	}

	// Getters
	public Days getDays()
	{
		return days;
	}

	public List<Period> getPeriods()
	{
		return periods;
	}

	// Setters
	public boolean setPeriods( List<Period> p )
	{
		periods = p;
		return true;
	}

	// Public methods

	// init
	public void Init()
	{
		periods = new ArrayList<Period>();
	}

	public void Init( String pFrom, String pTo )
	{
		Init();

		if( (pFrom != null) && (pTo != null) )
			periods.add( new Period( pFrom, pTo ) );
	}

	// init as a week days unresolved period
	public void InitWeekDays( int pDay )
	{
		days = new Days();
		days.setDay( pDay );
	}

	// print string
	public String Out()
	{
		if( periods == null )
		{
			// means we are in unresolved week days mode
			return "Unresolved days : " + days.implode( "," );
		}
		String out = "";
		for( int i = 0; i < periods.size(); i++ )
		{
			out += "[" + periods.get( i ).getFrom() + "->" + periods.get( i ).getTo() + "]<br>";
		}
		return out;
	}

	public String GetBeautiful()
	{
		if( periods == null )
		{
			if( days == null ) // security added to avoid null exception
				return "";
			return "Unresolved days : " + days.implode( "," );
		}

		if( periods.size() == 0 )
			return CalendarMessages.INSTANCE.never();

		if( (periods.size() == 1) && (periods.get( 0 ).getFrom() == CalendarFunctions.TIME_BEGIN) && (periods.get( 0 ).getTo() == CalendarFunctions.TIME_END) )
			return CalendarMessages.INSTANCE.always();

		String expr = "";

		for( int i = 0; i < periods.size(); i++ )
		{
			if( periods.get( i ).getFrom().compareTo( periods.get( i ).getTo() ) == 0 )
				expr += CalendarFunctions.date2Display( periods.get( i ).getFrom() );
			else
			{
				if( periods.get( i ).getFrom().compareTo( CalendarFunctions.TIME_BEGIN ) == 0 )
					expr += CalendarMessages.INSTANCE.until( CalendarFunctions.date2Display( periods.get( i ).getTo() ) );
				else if( periods.get( i ).getTo().compareTo( CalendarFunctions.TIME_END ) == 0 )
					expr += CalendarMessages.INSTANCE.from( CalendarFunctions.date2Display( periods.get( i ).getFrom() ) );
				else
					expr += CalendarMessages.INSTANCE.dateRange( CalendarFunctions.date2Display( periods.get( i ).getFrom() ), CalendarFunctions.date2Display( periods.get( i ).getTo() ) );
			}

			if( i < (periods.size() - 1) )
				expr = expr + ", ";
		}

		return expr;
	}

	public String GetExpression()
	{
		String expr;
		if( periods == null )
		{
			expr = "";
			int nb = 0;

			for( int i = 0; i < days.size(); i++ )
			{
				Day value = days.getDay( i );
				if( value.get() == 0 )
					continue;
				expr += " d" + value.toString();
				nb++;
			}
			for( int i = 0; i < nb - 1; i++ )
				expr += " |";
			return expr;
		}

		if( periods.size() == 0 )
			return "n";

		if( (periods.size() == 1) && (periods.get( 0 ).getFrom() == CalendarFunctions.TIME_BEGIN) && (periods.get( 0 ).getTo() == CalendarFunctions.TIME_END) )
			return "a";

		expr = "";

		for( int i = 0; i < periods.size(); i++ )
		{
			expr += "[" + periods.get( i ).getFrom() + ";" + periods.get( i ).getTo() + "] ";
			if( i > 0 )
				expr = expr + " |";
		}

		return expr;
	}

	// returns true if the submitted date is included in the list of periods,
	// false if not
	public boolean IsInside( String pDate )
	{
		if( periods == null )
		{
			if( days == null ) // security added to avoid null exception
				return false;

			if( days.getDay( CalendarFunctions.date_get_day( pDate ) ).get() > 0 )
				return true;

			return false;
		}

		for( int i = 0; i < periods.size(); i++ )
		{
			Period period = periods.get( i );
			if( period.getFrom().compareTo( pDate ) > 0 )
				return false;
			if( (period.getFrom().compareTo( pDate ) <= 0) && (period.getTo().compareTo( pDate ) >= 0) )
				return true;
		}

		return false;
	}

	// returns true if the submitted period is completely covered within the
	// list of periods, false if not
	public boolean IsContained( String pFrom, String pTo )
	{
		if( periods == null )
		{
			// echo "IsContained() TO BE IMPLEMENTED FLLKJ :: {{ } ''<br/>";
			throw new RuntimeException( "Error Periods is Null" );
		}

		for( int i = 0; i < periods.size(); i++ )
		{
			Period period = periods.get( i );
			if( period.getFrom().compareTo( pFrom ) > 0 )
				return false;

			if( (pFrom.compareTo( period.getFrom() ) >= 0) && (pTo.compareTo( period.getTo() ) <= 0) )
				return true;
		}

		return false;
	}

	public int GetNbDays()
	{
		if( periods == null )
		{
			// echo "IsContained() TO BE IMPLEMENTED FLLKJ :: {{ } ''<br/>";
			throw new RuntimeException( "Error Periods is Null" );
		}

		int nbDays = 0;
		for( int i = 0; i < periods.size(); i++ )
		{
			Period p = periods.get( i );
			// add one because the period description is inclusive
			nbDays += 1 + CalendarFunctions.date_interval_days( p.getFrom(), p.getTo() );
		}

		return nbDays;
	}

	public int GetNbNights()
	{
		if( periods == null )
		{
			// echo "IsContained() TO BE IMPLEMENTED FLLKJ :: {{ } ''<br/>";
			throw new RuntimeException( "Error Periods is Null" );
		}

		int nbNights = 0;
		for( int i = 0; i < periods.size(); i++ )
		{
			Period p = periods.get( i );
			nbNights += CalendarFunctions.date_interval_days( p.getFrom(), p.getTo() );
		}

		return nbNights;
	}

	public int GetBoundaries( PeriodBoundaries boundaries ) // String from and
															// to as attributes
															// in class
	{
		if( periods == null )
		{
			boundaries.from = CalendarFunctions.TIME_BEGIN;
			boundaries.to = CalendarFunctions.TIME_END;
			return 0;
		}

		if( periods.size() == 0 )
		{
			boundaries.from = CalendarFunctions.TIME_BEGIN;
			boundaries.to = CalendarFunctions.TIME_BEGIN; // change made on the
															// 2011-02-10, hope
															// it doesn't break
															// anyting...
			// $to = CalendarFunctions.TIME_END;
			return 0;
		}

		boundaries.from = periods.get( 0 ).getFrom();
		boundaries.to = periods.get( periods.size() - 1 ).getTo();

		return 1;
	}

	public String GetLeftBoundary()
	{
		if( periods == null || periods.isEmpty() )
			return CalendarFunctions.TIME_BEGIN;

		return periods.get( 0 ).getFrom();
	}

	public String GetRightBoundary()
	{
		if( periods == null || periods.isEmpty() )
			return CalendarFunctions.TIME_END;

		return periods.get( periods.size() - 1 ).getTo();
	}

	public int GetLeastBoundaries( PeriodBoundaries boundaries ) // String from
																	// and to as
																	// attributes
																	// in class
	{
		if( periods == null )
		{
			boundaries.from = CalendarFunctions.TIME_BEGIN;
			boundaries.to = CalendarFunctions.TIME_END;
			return 0;
		}

		if( periods.size() == 0 )
		{
			boundaries.from = CalendarFunctions.TIME_BEGIN;
			boundaries.to = CalendarFunctions.TIME_END;
			return 0;
		}

		boundaries.from = periods.get( 0 ).getFrom();
		boundaries.to = periods.get( 0 ).getTo();

		return 1;
	}

	// OR combination
	public void Add( CalendarPeriod pPeriod )
	{
		if( (days != null) && (pPeriod.getDays() != null) )
		{
			for( int i = 0; i < 7; i++ )
			{
				if( days.getDay( i ).get() + pPeriod.getDays().getDay( i ).get() >= 1 )
				{
					days.setDay( i );
				}
			}
			return;
		}

		// if one of the two operands is unresolved, it's a good time to resolve
		// it now
		List<Period> _periods = null;
		CalendarPeriod _toResolve = null;
		if( (days != null) || (pPeriod.getDays() != null) )
		{
			if( days == null )
			{
				_periods = this.getPeriods();
				_toResolve = pPeriod;
			}
			else
			{
				_periods = pPeriod.getPeriods();
				_toResolve = this;
			}

			String _from = _periods.get( 0 ).getFrom();
			String _to = _periods.get( _periods.size() - 1 ).getTo();

			_toResolve.Resolve( _from, _to );

		}

		// Laurent: added to prevent uninitialized periods
		if( periods == null )
			Init();

		// combine les deux tableaux dans ordre croissant
		List<Period> combined = _Combine( periods, pPeriod.getPeriods() );

		// merge overlapping periods
		List<Period> result = _Merge( combined );

		periods = result;
	}

	// AND operator
	public void Intersect( CalendarPeriod pPeriod )
	{
		if( (days != null) && (pPeriod.getDays() != null) )
		{
			for( int i = 0; i < 7; i++ )
				if( days.getDay( i ).get() + pPeriod.getDays().getDay( i ).get() >= 2 )
					days.getDay( i ).set();
				else
					days.getDay( i ).reset();
			return;
		}

		// if one of the two operands is unresolved, it's a good time to resolve
		// it now
		List<Period> _periods;
		CalendarPeriod _toResolve;
		if( (days != null) || (pPeriod.getDays() != null) )
		{
			if( days == null )
			{
				_periods = periods;
				_toResolve = pPeriod;
			}
			else
			{
				_periods = pPeriod.getPeriods();
				_toResolve = this;
			}

			String from = _periods.get( 0 ).getFrom();
			String to = _periods.get( _periods.size() - 1 ).getTo();

			_toResolve.Resolve( from, to );
		}

		// echo "Intersect " . $this->Out() . " with " . $period->Out() .
		// "<br>";

		// intersect the two period list
		List<Period> result = _Intersect( periods, pPeriod.getPeriods() );

		periods = result;
	}

	// starting from an unresolved periods, we build a resolved one, based on
	// from and to parameters
	public void Resolve( String pFrom, String pTo )
	{
		if( periods != null )
		{
			// call on an already resolved CalendarPeriod
			// echo
			// "LJLJKZHL KJH ELF B.EB EKJGF EFJBH EKLFJHL JGH <{{ : ' } <br/>";
			throw new RuntimeException( "Error call on an already resolved CalendarPeriod" );
		}

		// echo "Resolving from $from to $to " . implode( ".", $this->days ) .
		// "<br/>";

		// if all days are selected, make a whole period
		// build the micro periods
		int nb = 0;
		for( int i = 0; i < 7; i++ )
			nb += days.getDay( i ).get();

		if( nb == 7 )
		{
			periods = new ArrayList<Period>();
			periods.add( new Period( pFrom, pTo ) );
			return;
		}
		else if( nb == 0 )
		{
			periods = new ArrayList<Period>();
			return;
		}

		// echo "Continuing<br/>";
		int fromDay = CalendarFunctions.date_get_day( pFrom );

		// we have at least one gap
		Groups groups = new Groups();
		Group curGroup = null;
		for( int i = fromDay; i < fromDay + 7; i++ )
		{
			if( days.getDay( i % 7 ).get() > 0 )
			{
				if( curGroup == null ) // no group created yet
					curGroup = new Group( i - fromDay, i - fromDay );
				else if( curGroup.getTo() == i - fromDay - 1 ) // day jointed to
																// current group
					curGroup.setTo( i - fromDay );
				else
				// day disjointed from current group
				{
					groups.add( curGroup );
					curGroup = new Group( i - fromDay, i - fromDay );
				}
			}
		}
		if( curGroup != null )
			groups.add( curGroup );

		// Dump( $groups );

		// now generate the periods
		// String msg = "Starts on " + pFrom + ", which day is a " + fromDay +
		// "<br/>";
		// for( int i = 0; i < groups.size(); i++ )
		// {
		// Group group = groups.get( i );
		// msg += "Group : " + group.implode( " to " ) + "<br/>";
		// }

		// echo "From day : $from : $fromDay <br/>";

		String firstOccurence = pFrom;

		// echo "First occurence : $firstOccurence<br/>";

		days = null;
		periods = new ArrayList<Period>();
		while( firstOccurence.compareTo( pTo ) <= 0 )
		{
			// msg += "Occurence " + firstOccurence + "<br/>";
			// day of $firstOccurence is always $fromDay
			// foreach( $groups as $group )
			for( int i = 0; i < groups.size(); i++ )
			{
				Group group = groups.get( i );
				String mpFrom = CalendarFunctions.date_add_day( firstOccurence, group.getFrom() );
				if( mpFrom.compareTo( pTo ) <= 0 )
				{
					String mpTo = CalendarFunctions.date_add_day( firstOccurence, group.getTo() );
					if( mpTo.compareTo( pTo ) > 0 )
						mpTo = pTo;

					// msg += "Adding " + mpFrom + ", " + mpTo + "<br/>";

					periods.add( new Period( mpFrom, mpTo ) );
				}
			}

			firstOccurence = CalendarFunctions.date_add_day( firstOccurence, 7 );
		}

		// ServerState::inst()->AddMessage( $msg );
	}

	// NOT operator
	public void Not()
	{
		if( periods == null )
		{
			if( days == null ) // // security added to avoid null exception
				return;
			for( int i = 0; i < 7; i++ )
				days.getDay( i ).not();
			return;
		}

		List<Period> result = new ArrayList<Period>();

		String curBegin = CalendarFunctions.TIME_BEGIN;
		for( int i = 0; i < periods.size(); i++ )
		{
			Period period = periods.get( i );

			if( (!period.getFrom().equals( CalendarFunctions.TIME_BEGIN )) && (CalendarFunctions.date_add_day( period.getFrom(), -1 ).compareTo( curBegin ) >= 0) )
				result.add( new Period( curBegin, CalendarFunctions.date_add_day( period.getFrom(), -1 ) ) );
			curBegin = CalendarFunctions.date_add_day( period.getTo(), 1 );
		}

		if( CalendarFunctions.TIME_END.compareTo( curBegin ) >= 0 )
			result.add( new Period( curBegin, CalendarFunctions.TIME_END ) );

		periods = result;
	}

	// intersect two period arrays
	private List<Period> _Intersect( List<Period> periods1, List<Period> periods2 )
	{
		List<Period> result = new ArrayList<Period>();

		int count1 = periods1.size();
		int count2 = periods2.size();

		int i = 0;
		int j = 0;

		while( i < count1 && j < count2 )
		{
			// one of the periods begins after the end of the other
			if( periods1.get( i ).getFrom().compareTo( periods2.get( j ).getTo() ) > 0 )
			{ // period 1 begins after period 2 finishes => period2 is
				// eliminated !
				j++;
			}
			else if( periods2.get( j ).getFrom().compareTo( periods1.get( i ).getTo() ) > 0 )
			{ // period 2 begins after end of period 1 => period 1 is eliminated
				// !
				i++;
			}

			// after that test, we can assume there is a non-void intersection
			else
			{
				// result[] = array( max($periods1[$i][0],$periods2[$j][0]),
				// min($periods1[$i][1],$periods2[$j][1]) );
				result.add( new Period( CalendarFunctions.max_date( periods1.get( i ).getFrom(), periods2.get( j ).getFrom() ), CalendarFunctions.min_date( periods1.get( i ).getTo(), periods2.get( j ).getTo() ) ) );

				if( periods1.get( i ).getTo().compareTo( periods2.get( j ).getTo() ) > 0 )
					j++;
				else
					i++;
			}
		}

		return result;
	}

	// combine two period arrays ordered by from date
	public List<Period> _Combine( List<Period> periods1, List<Period> periods2 )
	{
		List<Period> result = new ArrayList<Period>();

		int count1 = periods1.size();
		int count2 = periods2.size();

		int i = 0;
		int j = 0;
		while( i < count1 && j < count2 )
		{
			if( periods1.get( i ).getFrom().compareTo( periods2.get( j ).from ) <= 0 )
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
	public List<Period> _Merge( List<Period> pPeriods )
	{
		List<Period> result = new ArrayList<Period>();

		int count = pPeriods.size();
		if( count == 0 )
			return result;

		// copy the first period
		result.add( pPeriods.get( 0 ) );

		// init
		int resIdx = 0;
		int i = 1;

		while( i < count )
		{
			if( pPeriods.get( i ).getFrom().compareTo( CalendarFunctions.date_add_day( result.get( resIdx ).getTo(), 1 ) ) > 0 )
			{
				// period is disjointed, so add it
				result.add( pPeriods.get( i ) );
				resIdx++;
			}
			else
			{
				// period is jointed so merge
				result.get( resIdx ).setTo( CalendarFunctions.max_date( result.get( resIdx ).getTo(), pPeriods.get( i ).getTo() ) );
			}
			i++;
		}

		return result;
	}

	@Override
	public String toString()
	{
		return Out();
	}

	public class Days
	{
		private ArrayList<Day> days = null;

		// Constructor
		public Days()
		{
			days = new ArrayList<Day>();
			for( int i = 0; i < 7; i++ )
				days.add( new Day() );
		}

		public Days( Days model )
		{
			days = new ArrayList<Day>();
			for( int i = 0; i < 7; i++ )
			{
				Day day = new Day();
				day.day = model.days.get( i ).day;
				days.add( day );
			}
		}

		// Getters
		public Day getDay( int d )
		{
			if( (d >= 0) && (d <= 7) )
				return days.get( d );

			return null;
		}

		// Setters
		public boolean setDay( int d )
		{
			if( (d >= 0) && (d <= 7) )
			{
				days.get( d ).set();
				return true;
			}
			return false;
		}

		public boolean resetDay( int d )
		{
			if( (d >= 0) && (d <= 7) )
			{
				days.get( d ).reset();
				return true;
			}
			return false;
		}

		// Public methods
		public String implode( String sep )
		{
			String str = "";
			for( Day d : days )
			{
				str += d.toString() + sep;
			}
			if( str.length() > 1 )
				return str.substring( 0, str.length() - 1 ); // remove last
																// separator
			return str;
		}

		public int size()
		{
			return days.size();
		}

		@Override
		public String toString()
		{
			return days.toString();
		}

	}

	public class Day
	{
		// attributes
		private int day;

		// Constructor
		public Day()
		{
			day = 0;
		}

		// Getters
		public int get()
		{
			return day;
		}

		// Setters
		public boolean set()
		{
			day = 1;
			return true;
		}

		public boolean reset()
		{
			day = 0;
			return true;
		}

		// Public methods
		public void not()
		{
			if( day > 0 )
				day = 0;
			else
				day = 1;
		}

		@Override
		public String toString()
		{
			return Integer.toString( day );
		}
	}

	public static class Period
	{
		// Attributes
		private String from = null;
		private String to = null;

		// Constructors
		public Period()
		{
		}

		public Period( String pFrom, String pTo )
		{
			from = pFrom;
			to = pTo;
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

		// Setters
		public boolean setFrom( String pFrom )
		{
			from = pFrom;
			return true;
		}

		public boolean setTo( String pTo )
		{
			to = pTo;
			return true;
		}

		// Public methods
		@Override
		public String toString()
		{
			return "[" + from + ":" + to + "]";
		}

	}

	public class PeriodsByYear
	{
		// Attributes
		private ArrayList<PeriodByYear> periods;

		// Contructors
		PeriodsByYear()
		{
			periods = new ArrayList<PeriodByYear>();
		}

		PeriodsByYear( PeriodByYear p )
		{
			periods = new ArrayList<PeriodByYear>();
			add( p );
		}

		// Public methods
		public boolean add( PeriodByYear p )
		{
			return periods.add( p );
		}

		@Override
		public String toString()
		{
			String res = "[";
			for( int i = 0; i < periods.size(); i++ )
			{
				res += periods.get( i ).toString();
			}
			res += "]";
			return res;
		}
	}

	public class PeriodByYear
	{
		// Attributes
		private Integer year;
		private String expression;

		// Constructors
		PeriodByYear( Integer pYear, String pExpr )
		{
			year = pYear;
			expression = pExpr;
		}

		// Getters
		public Integer getYear()
		{
			return year;
		}

		public String getExpression()
		{
			return expression;
		}

		// Public methods
		@Override
		public String toString()
		{
			return "{" + year + " : " + expression + "}";
		}
	}

	private class Groups
	{
		// Attributes
		private ArrayList<Group> groups = null;

		// Constructors
		public Groups()
		{
			groups = new ArrayList<Group>();
			groups.trimToSize();
		}

		// Getters
		public Group get( int index )
		{
			return groups.get( index );
		}

		// Public methods
		public boolean add( Group g )
		{
			return groups.add( g );
		}

		public int size()
		{
			return groups.size();
		}

		@Override
		public String toString()
		{
			String str = "";
			for( Group g : groups )
			{
				str += g.toString();
			}
			return str;
		}
	}

	private class Group
	{
		// Attributes
		int from;
		int to;

		// Constructor
		public Group( int pFrom, int pTo )
		{
			from = pFrom;
			to = pTo;
		}

		// Getters
		public int getFrom()
		{
			return from;
		}

		public int getTo()
		{
			return to;
		}

		// Setters
		// public boolean setFrom( int pFrom )
		// {
		// from = pFrom;
		// return true;
		// }

		public boolean setTo( int pTo )
		{
			to = pTo;
			return true;
		}

		// Public methods
		// public String implode( String sep )
		// {
		// return from + sep + to;
		// }

		@Override
		public String toString()
		{
			return "[" + from + ":" + to + "]";
		}

	}

}
