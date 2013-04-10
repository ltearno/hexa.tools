package com.hexa.server.qpath;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.server.spring.Logger;
import com.hexa.server.database.DatabaseConnectionFactory;

public class Database
{
	private final static int NBTRIES = 2;

	DatabaseConnectionFactory connectionFactory;

	Connection connection;

	DatabaseMetaData databaseMetaData;

	private final Logger logger = Logger.getLogger( Database.class );

	public boolean init( DatabaseConnectionFactory connectionFactory )
	{
		this.connectionFactory = connectionFactory;

		connection = connectionFactory.getConnection();

		return true;
	}

	public void term()
	{
		if( connection == null )
			return;

		databaseMetaData = null;

		try
		{
			connection.close();
		}
		catch( SQLException e )
		{
			e.printStackTrace();
		}

		connection = null;
	}

	public void reinit()
	{
		term();
		init( connectionFactory );
	}

	public String getCurrentDatabase()
	{
		try
		{
			return connection.getCatalog();
		}
		catch( SQLException e )
		{
			e.printStackTrace();

			return null;
		}
	}

	public DatabaseMetaData getDatabaseMetaData()
	{
		ensureMetadata();

		return databaseMetaData;
	}

	public void startTransaction()
	{
		logger.log( "START TRANSACTION" );
		sql( "START TRANSACTION" );
	}

	public void commit()
	{
		logger.log( "COMMIT" );
		sql( "COMMIT" );
	}

	public void rollback()
	{
		logger.log( "ROLLBACK" );
		sql( "ROLLBACK" );
	}

	public DBResults sql( String sql )
	{
		int nbTries = NBTRIES;
		while( nbTries-- > 0 )
		{
			try
			{
				logger.log( "SQL-SELECT: " + sql );

				Statement stmt = connection.createStatement();

				DBResults res = new DBResults( stmt.executeQuery( sql ), stmt );
				return res;
			}
			catch( SQLException exception )
			{
				exception.printStackTrace();

				logger.wrn( "SQLException during call to sql, try reconnecting to the sql server..." );
				reinit();
			}
		}

		return null;
	}

	public int sqlInsert( String sql )
	{
		int nbTries = NBTRIES;
		while( nbTries-- > 0 )
		{
			try
			{
				logger.log( "SQL-INSERT: " + sql );

				PreparedStatement stmt = connection.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
				stmt.execute();

				ResultSet res = stmt.getGeneratedKeys();
				if( !res.next() )
					return 0;

				return (int) res.getLong( 1 );
			}
			catch( SQLException exception )
			{
				exception.printStackTrace();

				logger.wrn( "SQLException during call to sqlInsert, try reconnecting to the sql server..." );
				reinit();
			}
		}

		return -1;
	}

	public int sqlDelete( String sql )
	{
		// there is no technical difference with sqlUpdate,
		// however the semantic is not the same for the user
		return sqlUpdate( sql );
	}

	public int sqlUpdate( String sql )
	{
		int nbTries = NBTRIES;
		while( nbTries-- > 0 )
		{
			try
			{
				logger.log( "SQL-MODIFY: " + sql );

				PreparedStatement stmt = connection.prepareStatement( sql );
				stmt.execute();

				return stmt.getUpdateCount();
			}
			catch( SQLException exception )
			{
				exception.printStackTrace();

				logger.wrn( "SQLException during call to sqlUpdate, try reconnecting to the sql server..." );
				reinit();
			}
		}

		return -1;
	}

	private void ensureMetadata()
	{
		if( databaseMetaData == null )
		{
			try
			{
				databaseMetaData = connection.getMetaData();
			}
			catch( SQLException e )
			{
				e.printStackTrace();
			}
		}
	}
}
