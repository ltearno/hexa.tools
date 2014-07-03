package fr.lteconsulting.hexa.client.calendar;

class ParsingExpression
{
	private String text;
	private int pos = 0;
	private int len;

	private String date;
	private String day;
	private String from;
	private String to;
	private String op;

	private String _date;
	private String _char;

	// Constructor
	ParsingExpression( String pText )
	{
		text = pText;
		len = pText.length();
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

	public String getDate()
	{
		return date;
	}

	public String getDay()
	{
		return day;
	}

	public String getOp()
	{
		return op;
	}

	// Public methods
	public boolean isEndOfText()
	{
		return pos >= len;
	}

	public boolean testChar( char c )
	{
		if( !isEndOfText() )
		{
			return(text.charAt( pos ) == c);
		}
		return false;
	}

	public void incPos()
	{
		pos++;
	}

	public boolean readFrom()
	{
		if( _readDate() )
		{
			from = _date;
			return true;
		}
		return false;
	}

	public boolean readTo()
	{
		if( _readDate() )
		{
			to = _date;
			return true;
		}
		return false;
	}

	public boolean readDate()
	{
		if( _readDate() )
		{
			date = _date;
			return true;
		}
		return false;
	}

	/**
	 * parse a day number (0=sunday)
	 */
	public boolean readDay()
	{
		if( _readChar() )
		{
			day = _char;
			return true;
		}
		return false;
	}

	public boolean readOp()
	{
		if( _readChar() )
		{
			op = _char;
			return true;
		}
		return false;
	}

	public void skipWhiteSpaces()
	{
		while( testChar( ' ' ) )
		{
			pos++;
		}
	}

	// Private methods

	/**
	 * parse a date in the format yyyy-mm-dd
	 * 
	 * @return boolean
	 */
	private boolean _readDate()
	{
		int endIndex = pos + 10;
		if( endIndex <= len )
		{
			_date = text.substring( pos, endIndex );
			pos += 10;
			return true;
		}
		pos = len;
		return false;
	}

	/**
	 * parse a day number (0=sunday) and put it in the private _day attribute
	 * 
	 * @return boolean
	 */
	private boolean _readChar()
	{
		int endIndex = pos + 1;
		if( endIndex <= len )
		{
			_char = text.substring( pos, endIndex );
			pos++;
			return true;
		}
		pos = len;
		return false;
	}
}