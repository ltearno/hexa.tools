package fr.lteconsulting.hexa.server.qpath;

import java.util.List;

import fr.lteconsulting.hexa.server.qpath.QPathResult.QPathResultRow;

public class Tools
{
	public static String implode( String separator, List<String> list )
	{
		StringBuilder b = new StringBuilder();
		boolean addSeparator = false;

		for( String s : list )
		{
			if( s == null || s.isEmpty() )
				continue;

			if( addSeparator )
				b.append( separator );
			else
				addSeparator = true;

			b.append( s );
		}

		return b.toString();
	}

	public static String toHTMLTable( QPathResult res )
	{
		int numCols = res.getNbCols();
		if( numCols == 0 )
			return "Empty QPath results<br/>";

		StringBuilder sb = new StringBuilder();

		sb.append( "<table border='1'><tr>" );

		String columnNames[] = res.getColumnNames();

		for( int i = 0; i < numCols; i++ )
			sb.append( "<td>" + columnNames[i] + "</td>" );
		sb.append( "</tr>" );

		for( QPathResultRow row : res )
		{
			sb.append( "<tr>" );
			for( int i = 0; i < numCols; i++ )
				sb.append( "<td>" + row.get( columnNames[i] ) + "</td>" );
			sb.append( "</tr>" );
		}

		sb.append( "<tr><td colspan='" + numCols + "'>" + res.getNbRows() + " rows.</td></tr>" );

		sb.append( "</table>" );

		return sb.toString();
	}
}
