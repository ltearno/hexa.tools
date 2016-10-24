package fr.lteconsulting.hexa.client.common;

import java.util.Date;

import fr.lteconsulting.hexa.client.common.hexadatedisplayformatfactory.HexaDateDisplayFormatFactory.Format;
import fr.lteconsulting.hexa.client.common.text.DateTimeFormat;
import fr.lteconsulting.hexa.client.common.text.NumberFormat;

/*
 * Because java.util.Date is not well managed in GWT,
 * this class is aimed to replace it.
 */

public class HexaDate
{
	DateTimeFormat ff;

	public static int TIME_BEGIN = 732840;
	public static int TIME_END = 758145;// 930000;

	public static final String[] DayNames = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thirsday", "Friday", "Saturday" };
	public static final String[] MonthNames = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

	static public HexaDateDisplayFormat getDisplayFormat()
	{
		return s_displayFormat;
	}

	static public void setDisplayFormat( HexaDateDisplayFormat format )
	{
		s_displayFormat = format;
	}

	static private HexaDateDisplayFormat s_displayFormat = HexaDateDisplayFormatList.getFormat( Format.FORMAT_2 );// new
																													// HexaDateDisplayFormat2();

	// static private DateTimeFormat dateFormat =
	// DateTimeFormat.getFormat("yyyy-MM-dd");

	private static NumberFormat yearFormat = NumberFormat.getFormat( "0000" );
	private static NumberFormat monthFormat = NumberFormat.getFormat( "00" );
	private static NumberFormat dayFormat = NumberFormat.getFormat( "00" );

	private boolean fInvalid = false;

	private int year = 0; // -1900
	private int month = 0; // 0 to 11
	private int date = 0; // 1 to 31

	// needs contructor from :
	// * String
	// * Date
	// * Integer (interop format = y*1000 + m*31 + d)

	// Today's date
	public HexaDate()
	{
		this( new Date() );
	}

	@SuppressWarnings( "deprecation" )
	public HexaDate( Date d )
	{
		year = d.getYear();
		month = d.getMonth();
		date = d.getDate();
	}

	// string is in the YYYY-MM-DD format.
	public HexaDate( String string )
	{
		set( string );
	}

	// day is the int encoded version
	public HexaDate( int day )
	{
		if( day < TIME_BEGIN )
			day = TIME_BEGIN;
		else if( day > TIME_END )
			day = TIME_END;

		date = day % 31;
		day -= date;
		day /= 31;
		date++;

		month = day % 12;
		day -= month;
		day /= 12;

		year = day - 1900;
	}

	public HexaDate( int year, int month, int date )
	{
		set( year, month, date );
	}

	public void set( String string )
	{
		fInvalid = false;
		if( string.length() != 10 )
		{
			// GWT.log( "Invalid string " + string +
			// " for HexaDate initialization" );
			fInvalid = true;
			return;
		}

		if( string.compareTo( "0000-00-00" ) == 0 )
		{
			// GWT.log( "Invalid string " + string +
			// " for HexaDate initialization" );
			fInvalid = true;
			return;
		}

		try
		{
			year = Integer.parseInt( string.substring( 0, 4 ) ) - 1900;
			month = Integer.parseInt( string.substring( 5, 7 ) ) - 1;
			date = Integer.parseInt( string.substring( 8, 10 ) );
		}
		catch( Exception e )
		{
			fInvalid = true;
			e.printStackTrace();
		}
	}

	public static HexaDate now()
	{
		return new HexaDate();
	}

	public int compareTo( HexaDate date )
	{
		return toInt() - date.toInt();
	}

	public boolean equals( HexaDate other )
	{
		if( other.fInvalid == fInvalid && other.year == year && other.month == month && other.date == date )
			return true;
		return false;
	}

	// TODO : à essayer avec les années bissextiles
	public int getIntervalDays( HexaDate other )
	{
		long interval = other.getJavaDate().getTime() - getJavaDate().getTime();
		int res = (int) (interval / 86400000);
		return res;
	}

	public HexaDate addDays( int nbDays )
	{
		// see public static Date OLDaddDays( Date start, int nbDays )
		int day = this.date; // 1 - 31
		int month = this.month; // 0 - 11
		int year = this.year + 1900;

		while( nbDays != 0 )
		{
			if( nbDays > 0 )
			{
				if( day + nbDays > GetDaysInMonth( year, month ) )
				{
					nbDays -= 1 + (GetDaysInMonth( year, month ) - day);

					day = 1;
					month++;

					if( month > 11 )
					{
						month = 0;
						year++;
					}
				}
				else
				{
					day += nbDays;
					nbDays = 0;
				}
			}
			if( nbDays < 0 )
			{
				if( day + nbDays < 1 )
				{
					nbDays += day;

					month--;
					day = GetDaysInMonth( year, month );

					if( month < 0 )
					{
						month = 11;
						day = GetDaysInMonth( year, month );
						year--;
					}
				}
				else
				{
					day += nbDays;
					nbDays = 0;
				}
			}
		}

		return new HexaDate( year - 1900, month, day );
	}

	@SuppressWarnings( "deprecation" )
	public Date getJavaDate()
	{
		if( fInvalid )
			return null;
		return new Date( year, month, date );
	}

	public int getYear()
	{
		return year;
	}

	public int getMonth()
	{
		return month;
	}

	// position of the day in the month (from 1 to 31)
	public int getDate()
	{
		return date;
	}

	public int getHumanYear()
	{
		return year + 1900;
	}

	public int getHumanMonth()
	{
		return month + 1;
	}

	public int getHumanDate()
	{
		return date;
	}

	public int getDay()
	{
		// Algorithme de Mike Keith
		int y = year + 1900;
		int m = month + 1;
		int d = date;

		int z = m < 3 ? y - 1 : y;
		int r = m < 3 ? 0 : 2;

		return (23 * m / 9 + d + 4 + y + z / 4 - z / 100 + z / 400 - r) % 7;
	}

	public boolean isValid()
	{
		return !fInvalid;
	}

	// encodes the date into an int
	public int toInt()
	{
		int day = ((year + 1900) * 12 + month) * 31 + (date - 1);

		if( day < TIME_BEGIN )
			day = TIME_BEGIN;
		else if( day > TIME_END )
			day = TIME_END;

		return day;
	}

	@Override
	public String toString()
	{
		assert false : "Please do not use the HexaDate::toString, use getString() instead, it will minimize chances of human errors...";
		return getString();
	}

	public String getDisplayString()
	{
		return s_displayFormat.format( fInvalid, year, month, date );
	}

	public String getString()
	{
		if( fInvalid )
			return "";
		// return "0000-00-00";
		return yearFormat.format( year + 1900 ) + "-" + monthFormat.format( month + 1 ) + "-" + dayFormat.format( date );
	}

	public void set( int year, int month, int date )
	{
		this.year = year;
		this.month = month;
		this.date = date;
		fInvalid = false;
	}

	public void setYear( int year )
	{
		this.year = year;
	}

	public void setMonth( int month )
	{
		this.month = month;
	}

	/**
	 * sets the day of the month
	 *
	 * @param date
	 */
	public void setDate( int date )
	{
		this.date = date;
	}

	public static int GetDaysInMonth( int year, int month )
	{
		switch( month )
		{
			case 1:
				return (((year % 4) == 0 && (year % 100) != 0) || (year % 400) == 0) ? 29 : 28;

			case 3:
			case 5:
			case 8:
			case 10:
				return 30;

			default:
				return 31;
		}
	}
}
