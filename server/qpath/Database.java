package com.hexa.server.qpath;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hexa.server.tools.Logger;

public class Database
{
	Connection connection;

	DatabaseMetaData databaseMetaData;

	private final Logger logger = Logger.getLogger( Database.class );

	public boolean init( Connection connection )
	{
		logger.log( "Database initialisation" );
		
		this.connection = connection;

		return true;
	}

	public void term()
	{
		logger.log( "Database term" );
		
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
		try
		{
			logger.log( "SQL-SELECT: " + sql );

			Statement stmt = connection.createStatement();

			DBResults res = new DBResults( stmt.executeQuery( sql ), stmt );
			return res;
		}
		catch( SQLException exception )
		{
			String message = "SQLException during call to sql executing statement '" + sql + "' !";
			
			logger.err( message );

			exception.printStackTrace();
			
			throw new DatabaseException( message, exception );
		}
	}

	public int sqlInsert( String sql )
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
			String message = "SQLException during call to sqlInsert executing statement '" + sql + "' !";
			
			logger.err( message );

			exception.printStackTrace();

			throw new DatabaseException( message, exception );
		}
	}

	public int sqlDelete( String sql )
	{
		// there is no technical difference with sqlUpdate,
		// however the semantic is not the same for the user
		return sqlUpdate( sql );
	}

	public int sqlUpdate( String sql )
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
			String message = "SQLException during call to sqlUpdate executing statement '" + sql + "' !";
			
			logger.err( message );

			exception.printStackTrace();

			throw new DatabaseException( message, exception );
		}
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
				String message = "SQLException during call to ensureMetadata !";
				
				logger.err( message );
				
				e.printStackTrace();
				
				throw new DatabaseException( message, e );
			}
		}
	}
}
