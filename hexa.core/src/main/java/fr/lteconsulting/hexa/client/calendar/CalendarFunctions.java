package fr.lteconsulting.hexa.client.calendar;

import fr.lteconsulting.hexa.client.calendar.CalendarPeriod.PeriodsByYear;
import fr.lteconsulting.hexa.client.common.HexaDate;
import fr.lteconsulting.hexa.client.common.HexaTime;

public class CalendarFunctions
{
	// Constants
	public static final String TIME_BEGIN = "1970-01-01";
	public static final String TIME_END = "2038-01-10"; // the 19th jan 2038 is
														// the last day that
														// strtotime can
														// handle...

	public static String date2Display( String pDate )
	{
		// Note re-written
		HexaDate hDate = new HexaDate( pDate );
		String sDate = hDate.getDisplayString();
		return sDate;
	}

	public static String date_add_day( String dateStr, int nbDays )
	{
		HexaDate hDate = new HexaDate( dateStr );
		hDate = hDate.addDays( nbDays );
		String d = hDate.getString();
		return d;

	}

	public static int date_interval_days( String pFrom, String pTo )
	{
		HexaDate d1 = new HexaDate( pFrom );
		HexaDate d2 = new HexaDate( pTo );
		return d1.getIntervalDays( d2 );
	}

	public static int intdiv( int q, int d )
	{
		int reste = q % d;
		int res = (q - reste) / d;
		return res;
	}

	public static int date_get_day( String date )
	{
		HexaDate hDate = new HexaDate( date );
		return hDate.getDay();
	}

	public static String now()
	{
		// return date( 'Y-m-d', strtotime( 'now' ) );
		return new HexaDate().getString();
	}

	public static String nowtime()
	{
		// return date( "Y-m-d H:i:s", strtotime( 'now' ) );
		return new HexaTime().getString();
	}

	public static PeriodsByYear SliceByYear( String periodExpr )
	{
		Tree tree = Calendar.get().Parse( periodExpr );
		PeriodBoundaries boundaries = tree.getBoundaries();

		int startYear = new HexaDate( boundaries.from ).getHumanYear();

		int endYear = new HexaDate( boundaries.to ).getHumanYear();

		CalendarPeriod cp = new CalendarPeriod();
		PeriodsByYear ps = cp.new PeriodsByYear();

		for( int year = startYear; year <= endYear; year++ )
			ps.add( cp.new PeriodByYear( year, "[" + year + "-01-01;" + year + "-12-31] " + periodExpr + " &" ) );

		return ps;
	}

	// Additional functions for PHP to Java translation
	public static int string_date_to_day_of_week( String pDateStr )
	{
		return new HexaDate( pDateStr ).getDay();
	}

	// ex 2013-06-11 => 20130611
	public static int string_date_to_int( String d )
	{
		HexaDate date = new HexaDate( d );
		return date.getHumanYear() * 10000 + date.getHumanMonth() * 100 + date.getHumanDate();
	}

	public static String max_date( String d1, String d2 )
	{
		if( d1.compareTo( d2 ) >= 0 )
			return d1;

		return d2;
	}

	public static String min_date( String d1, String d2 )
	{
		if( d1.compareTo( d2 ) <= 0 )
			return d1;

		return d2;
	}
}
