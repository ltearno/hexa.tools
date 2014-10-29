package fr.lteconsulting.hexa.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import fr.lteconsulting.hexa.server.tools.LoggerFactory;

public class DatabaseContextFactory
{
	private static Logger log = LoggerFactory.getLogger();

	String host;
	int port;
	String database;
	String user;
	String password;

	DatabaseConnectionFactoryImpl connectionFactory;

	public DatabaseContextFactory()
	{
	}

	public boolean init( String databaseUri )
	{
		//DatabaseConnectionFactoryC3P0Impl impl = new DatabaseConnectionFactoryC3P0Impl();
		DatabaseConnectionFactoryImpl impl = new DatabaseConnectionFactoryImpl();
		connectionFactory = impl;

		boolean res = impl.init( log, "com.mysql.jdbc.Driver", databaseUri );
		
		return res;
	}
	
	Pool<DatabaseContext> dbCtxPool = new Pool<DatabaseContext>()
	{
		@Override
		protected DatabaseContext createObj()
		{
			DatabaseContext context = new DatabaseContext();
			Connection connexion = connectionFactory.getConnection();
			context.init( connexion );

			log.info( " ... DatabaseContext creation in pool" );
			
			return context;
		}
	};

	synchronized public DatabaseContext requestDatabaseContext()
	{
		DatabaseContext context;
		
		do
		{
			context = dbCtxPool.get();
			
			// test the connection
			try
			{
				context.db.sql( "select 1" );
			}
			catch( Exception e )
			{
				log.info( " ... DatabaseContext error with connection, forgetting this one" );
				dbCtxPool.remove( context );
				context.term();
				context = null;
			}
		}
		while( context == null );
		
		return context;
	}

	synchronized public void releaseDatabaseContext( DatabaseContext databaseContext )
	{
		dbCtxPool.replace( databaseContext );
	}
}

abstract class Pool<T>
{
	abstract protected T createObj();
	
	List<T> freeObjects = new ArrayList<>();
	
	public T get()
	{
		T object = null;
		if( freeObjects.isEmpty() )
			object = createObj();
		else
			object = freeObjects.remove( 0 );
		
		return object;
	}
	
	public void replace( T object )
	{
		freeObjects.add( object );
	}
	
	public void remove( T object )
	{
		freeObjects.remove( object );
	}
}

class DatabaseConnectionFactoryImpl
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
			log.error( "Driver load failed: ClassNotFoundException: " );
			e.printStackTrace();
			throw new RuntimeException( e );
		}

		return true;
	}

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
			log.error( "SQLException: " );
			e.printStackTrace();
			return null;
		}

		log.info( "Initialized with database " + url );

		return connection;
	}

}