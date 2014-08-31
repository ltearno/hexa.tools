package fr.lteconsulting.hexa.server.database;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DatabaseContextFactory
{
	private static Logger log = Logger.getLogger( DatabaseContextFactory.class.getName() );

	String host;
	int port;
	String database;
	String user;
	String password;

	DatabaseConnectionFactory connectionFactory;

	public DatabaseContextFactory()
	{
	}

	public boolean init( String databaseUri )
	{
		DatabaseConnectionFactoryC3P0Impl impl = new DatabaseConnectionFactoryC3P0Impl();
		connectionFactory = impl;

		boolean res = impl.init( log, "com.mysql.jdbc.Driver", databaseUri );
		
		return res;
	}

	synchronized public DatabaseContext requestDatabaseContext()
	{
		DatabaseContext context = new DatabaseContext();
		Connection connexion = connectionFactory.getConnection();
		context.init( connexion );

		log.info( " ... DatabaseContext creation" );

		return context;
	}

	synchronized public void releaseDatabaseContext( DatabaseContext databaseContext )
	{
		databaseContext.term();
	}
}

class DatabaseConnectionFactoryImpl implements DatabaseConnectionFactory
{
	Logger log;

	String driver;
	String url;

	public boolean init( Logger log, String driver, String url )
	{
		this.log = log;

		this.driver = driver;
		this.url = url;

		try
		{
			// loads the jdbc driver
			Class.forName( driver );
		}
		catch( ClassNotFoundException e )
		{
			log.severe( "Driver load failed: ClassNotFoundException: " );
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public Connection getConnection()
	{
		// initiate a connection to db
		Connection connection;
		try
		{
			connection = DriverManager.getConnection( url );
		}
		catch( SQLException e )
		{
			log.severe( "SQLException: " );
			e.printStackTrace();
			return null;
		}

		log.info( "Initialized with database " + url );

		return connection;
	}

}

class DatabaseConnectionFactoryC3P0Impl implements DatabaseConnectionFactory
{
	Logger log;

	ComboPooledDataSource pooledDataSource;

	String driver;
	String url;
	String user;
	String password;

	public boolean init( Logger log, String driver, String url )
	{
		pooledDataSource = new ComboPooledDataSource();
		try
		{
			pooledDataSource.setDriverClass( driver );
		}
		catch( PropertyVetoException e )
		{
			e.printStackTrace();
			return false;
		}

		// loads the jdbc driver
		pooledDataSource.setJdbcUrl( url );

		return true;
	}

	@Override
	public Connection getConnection()
	{
		try
		{
			Connection conn = pooledDataSource.getConnection();

			return conn;
		}
		catch( SQLException e )
		{
			e.printStackTrace();

			return null;
		}
	}
}