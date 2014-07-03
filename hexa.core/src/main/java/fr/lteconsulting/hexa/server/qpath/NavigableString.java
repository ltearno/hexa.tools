package fr.lteconsulting.hexa.server.qpath;

public class NavigableString
{
	String buf;
	public int pos;

	public NavigableString( String buf )
	{
		this.buf = buf;

		pos = 0;
	}

	public char cur()
	{
		return buf.charAt( pos );
	}

	public char ahead()
	{
		return ahead( 1 );
	}

	public char ahead( int i )
	{
		return buf.charAt( pos + i );
	}

	public String extract( int extractLenght )
	{
		// rtrim(substr($text,$pos,$i))
		return buf.substring( pos, pos + extractLenght ).trim();
	}
}
