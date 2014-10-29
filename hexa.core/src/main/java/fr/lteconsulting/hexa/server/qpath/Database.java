package fr.lteconsulting.hexa.server.qpath;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;

import fr.lteconsulting.hexa.server.tools.LoggerFactory;

public class Database
{
	Connection connection;

	DatabaseMetaData databaseMetaData;

	private static final Logger logger = LoggerFactory.getLogger();

	public boolean init( Connection connection )
	{
		logger.info( "Database initialisation" );

		this.connection = connection;

		return true;
	}

	public void term()
	{
		logger.info( "Database term" );

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
	
	public Connection getConnection()
	{
		return connection;
	}

	public String getCurrentDatabase()
	{
		try
		{
			if( connection == null )
				return null;
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
		logger.info( "START TRANSACTION" );
		sql( "START TRANSACTION" );
	}

	public void commit()
	{
		logger.info( "COMMIT" );
		sql( "COMMIT" );
	}

	public void rollback()
	{
		logger.info( "ROLLBACK" );
		sql( "ROLLBACK" );
	}

	public DBResults sql( String sql )
	{
		try
		{
			logger.info( "SQL-SELECT: " + sql );

			Statement stmt = connection.createStatement();

			DBResults res = new DBResults( stmt.executeQuery( sql ), stmt );
			return res;
		}
		catch( SQLException exception )
		{
			String message = "SQLException during call to sql executing statement '" + sql + "' !";

			logger.error( message );

			exception.printStackTrace();

			throw new DatabaseException( message, exception );
		}
	}

	public int sqlInsert( String sql )
	{
		try
		{
			logger.info( "SQL-INSERT: " + sql );

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

			logger.error( message );

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
			logger.info( "SQL-MODIFY: " + sql );

			PreparedStatement stmt = connection.prepareStatement( sql );
			stmt.execute();

			return stmt.getUpdateCount();
		}
		catch( SQLException exception )
		{
			String message = "SQLException during call to sqlUpdate executing statement '" + sql + "' !";

			logger.error( message );

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

				logger.error( message );

				e.printStackTrace();

				throw new DatabaseException( message, e );
			}
		}
	}
}
