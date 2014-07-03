package fr.lteconsulting.hexa.server.qpath;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBResults
{
	Statement stmt;
	ResultSet sqlRes;
	ResultSetMetaData meta;

	public DBResults( ResultSet sqlRes, Statement stmt )
	{
		this.stmt = stmt;
		this.sqlRes = sqlRes;
	}

	public void close()
	{
		try
		{
			sqlRes.close();
		}
		catch( SQLException e )
		{
			e.printStackTrace();
		}
	}

	/*
	 * Results meta data
	 */

	public int getColumnCount()
	{
		ensureMeta();

		try
		{
			return meta.getColumnCount();
		}
		catch( SQLException e )
		{
			e.printStackTrace();
			return 0;
		}
	}

	public String getColumnName( int column )
	{
		ensureMeta();

		try
		{
			return meta.getColumnLabel( column + 1 );
		}
		catch( SQLException e )
		{
			e.printStackTrace();
			return null;
		}
	}

	public int getColumnIndex( String column )
	{
		int n = getColumnCount();

		for( int i = 0; i < n; i++ )
		{
			if( getColumnName( i ).equalsIgnoreCase( column ) )
				return i;
		}

		return -1;
	}

	public int getRowCount()
	{
		try
		{
			// get the current position so that we can later put back the cursor
			int current = sqlRes.getRow();

			int res;

			if( !sqlRes.last() )
				res = 0;
			else
				res = sqlRes.getRow();

			// reposition the cursor to where it was before the call
			if( current == 0 )
				sqlRes.beforeFirst();
			else
				sqlRes.absolute( current );

			return res;
		}
		catch( SQLException e )
		{
			return -1;
		}
	}

	/*
	 * Results navigation
	 */

	public boolean next()
	{
		try
		{
			return sqlRes.next();
		}
		catch( SQLException e )
		{
			e.printStackTrace();
			return false;
		}
	}

	public Object getObject( int column )
	{
		try
		{
			return sqlRes.getObject( column + 1 );
		}
		catch( SQLException e )
		{
			e.printStackTrace();
			return null;
		}
	}

	public String getString( int column )
	{
		try
		{
			return sqlRes.getString( column + 1 );
		}
		catch( SQLException e )
		{
			e.printStackTrace();
			return null;
		}
	}

	public Integer getInt( int column )
	{
		try
		{
			return sqlRes.getInt( column + 1 );
		}
		catch( SQLException e )
		{
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * Privates...
	 */

	private void ensureMeta()
	{
		if( meta != null )
			return;

		try
		{
			meta = sqlRes.getMetaData();
		}
		catch( SQLException e )
		{
			e.printStackTrace();
		}
	}
}