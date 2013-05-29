package com.hexa.server.spring;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.hexa.server.database.DatabaseContext;
import com.hexa.server.database.DatabaseContextFactory;
import com.hexa.server.tools.Logger;

public class HexaSpring
{
	private static Logger log = Logger.getLogger( HexaSpring.class );

	private static HexaSpring instance = null;

	private static DatabaseContextFactory databaseContextFactory;

	public static final String LOGGED_USER_ID = "LOGGED_USER_ID";

	public static final String USER_TOKEN_URL_PARAM_NAME = "security";

	public static HexaSpring hexa()
	{
		if( instance == null )
			instance = new HexaSpring();

		return instance;
	}

	public void init()
	{
		log.log( "Initialisation..." );

		log.log( " ... Properties" );
		initProperties();

		log.log( " ... DatabaseContext pool" );
		databaseContextFactory = new DatabaseContextFactory();
		if( !databaseContextFactory.init( databaseUri ) )
		{
			log.err( "**********************************************************************************" );
			log.err( "Cannot initialize database connection pool, it won't be available to the program !" );
			log.err( "**********************************************************************************" );
		}

		log.log( "Initialisation Ok." );
	}

	//

	private String configurationDirectory;
	private String rootDataDir;
	private String databaseUri;
	private String serverRootUrl;
	private String administratorEmail;

	private void initProperties()
	{
		configurationDirectory = System.getProperty( "jboss.server.config.dir" );
		String filename = "photo-config.properties";
		File f = new File( configurationDirectory, filename );
		Properties p = new Properties();
		try
		{
			p.load( new FileInputStream( f ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		rootDataDir = p.getProperty( "root_data_dir" );
		log.log( " ...  root_data_dir: " + rootDataDir );

		databaseUri = p.getProperty( "database_uri" );
		log.log( " ...  database_uri: " + databaseUri );

		serverRootUrl = p.getProperty( "server_root_url" );
		log.log( " ...  server_root_url: " + serverRootUrl );

		administratorEmail = p.getProperty( "administrator_email" );
		log.log( " ...  administrator_email: " + administratorEmail );
	}

	// configuration

	public String rootDataDirectory()
	{
		return rootDataDir;
	}

	public String administratorEmail()
	{
		return administratorEmail;
	}

	public String serverRootUrl()
	{
		return serverRootUrl;
	}

	//

	public void runInBackground( final Runnable runnable )
	{
		Thread thread = new Thread( new Runnable()
		{
			@Override
			public void run()
			{
				log.log( "Starting a background thread..." );

				runnable.run();

				cleanThread();

				log.log( "Background thread stopped" );
			}
		} );

		thread.start();
	}

	public interface TransactionManagedAction<T>
	{
		T execute( DatabaseContext ctx );
	}

	public <T> T manageTransaction( TransactionManagedAction<T> action )
	{
		return manageTransaction( db(), action );
	}

	// do a transaction management : prepare tx, and watch for any exception, then rollback it.
	// it everything goes fine, commit
	public <T> T manageTransaction( DatabaseContext ctx, TransactionManagedAction<T> action )
	{
		ctx.db.startTransaction();
		try
		{
			T result = action.execute( ctx );

			ctx.db.commit();

			return result;
		}
		catch( Exception exception )
		{
			ctx.db.rollback();

			throw new ManagedTransactionException( "Exception during managed transaction, see cause for details", exception );
		}
	}

	private void cleanThread()
	{
		HexaThreadInfo info = HexaThreadInfo.getIfPresent();

		if( info == null )
			return;

		// release current thread database context
		if( info.databaseContext != null )
		{
			databaseContextFactory.releaseDatabaseContext( info.databaseContext );
			info.databaseContext = null;
		}
	}

	// handles the database connection creation

	public DatabaseContext db()
	{
		HexaThreadInfo info = HexaThreadInfo.get();
		if( info.databaseContext != null )
			return info.databaseContext;

		info.databaseContext = databaseContextFactory.requestDatabaseContext();
		assert info.databaseContext != null;

		return info.databaseContext;
	}
}
