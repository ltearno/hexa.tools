package fr.lteconsulting.hexa.server.spring;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.lteconsulting.hexa.server.data.UserDTO;
import fr.lteconsulting.hexa.server.data.UserSecurityTokenDTO;
import fr.lteconsulting.hexa.server.database.DatabaseContext;
import fr.lteconsulting.hexa.server.database.DatabaseContextFactory;

public class HexaSpring
{
	private static final Logger log = Logger.getLogger( HexaSpring.class.getName() );

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
	
	public interface InitPropertyProvider
	{
		String getRootDataDir();
		String getDatabaseUri();
		String getServerRootUrl();
		String getAdministratorEmeail();
	}

	public final void onContextInitialized( ServletContextEvent servletContextEvent )
	{
		ServletContext c = servletContextEvent.getServletContext();
		init( c );
	}

	private void init( ServletContext c )
	{
		log.info( "Initialisation..." );

		log.info( " ... Properties" );
		String hexaSpringProperties = c.getInitParameter( "hexa.spring.properties" );
		String hexaSpringPropertiesProvider = c.getInitParameter( "hexa.spring.properties-provider" );
		
		if( hexaSpringProperties != null )
			initProperties( hexaSpringProperties );
		else
			initPropertiesByProvider( hexaSpringPropertiesProvider );

		if( databaseUri != null )
		{
			log.info( " ... DatabaseContext pool" );
			databaseContextFactory = dbFactory( databaseUri );
		}
		else
		{
			log.info( " ... Skiping default DatabaseContext pool because not used" );
		}
		
		log.info( "Initialisation Ok." );
	}

	//

	//private String configurationDirectory;
	private String rootDataDir;
	private String databaseUri;
	private String serverRootUrl;
	private String administratorEmail;

	private void initProperties( String hexaSpringProperties )
	{
		log.info( " ...  Configuring through class " + hexaSpringProperties );
		
		//configurationDirectory = System.getProperty( "jboss.server.config.dir" );
		//String filename = "photo-config.properties";
		//File f = new File( configurationDirectory, filename );
		File f = new File( hexaSpringProperties );
		Properties p = new Properties();
		try
		{
			p.load( new FileInputStream( f ) );
		}
		catch( Exception e )
		{
			log.info( " ...  Failed ! " + e.getMessage() );
			e.printStackTrace();
		}

		rootDataDir = p.getProperty( "root_data_dir" );
		databaseUri = p.getProperty( "database_uri" );
		serverRootUrl = p.getProperty( "server_root_url" );
		administratorEmail = p.getProperty( "administrator_email" );
		
		logConfig();
	}
	
	private void initPropertiesByProvider( String hexaSpringPropertiesProvider )
	{
		try
		{
			log.info( " ...  Configuring through class " + hexaSpringPropertiesProvider );
			Class<?> cls = Class.forName( hexaSpringPropertiesProvider );
			Object provider = cls.newInstance();
			if( provider==null || ! (provider instanceof InitPropertyProvider) )
				return;
			
			InitPropertyProvider p = (InitPropertyProvider) provider;
			
			rootDataDir = p.getRootDataDir();
			databaseUri = p.getDatabaseUri();
			serverRootUrl = p.getServerRootUrl();
			administratorEmail = p.getAdministratorEmeail();
			
			logConfig();
		}
		catch( ClassNotFoundException e )
		{
			log.info( " ...  Failed ! " + e.getMessage() );
			e.printStackTrace();
		}
		catch( InstantiationException e )
		{
			log.info( " ...  Failed ! " + e.getMessage() );
			e.printStackTrace();
		}
		catch( IllegalAccessException e )
		{
			log.info( " ...  Failed ! " + e.getMessage() );
			e.printStackTrace();
		}
	}
	
	private void logConfig()
	{
		log.info( " ...  root_data_dir: " + rootDataDir );
		log.info( " ...  database_uri: " + databaseUri );
		log.info( " ...  server_root_url: " + serverRootUrl );
		log.info( " ...  administrator_email: " + administratorEmail );
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

	public void onBeginServletRequestProcessing( HttpServletRequest request, HttpServletResponse response )
	{
		// store session information
		HexaThreadInfo info = HexaThreadInfo.get();
		info.request = request;

		// auto-login :
		String tokenId = request.getParameter( USER_TOKEN_URL_PARAM_NAME );
		if( tokenId != null )
		{
			UserSecurityTokenDTO token = HexaSpring.hexa().db().qpath.queryOneDTO( UserSecurityTokenDTO.class, "user_security_tokens [id='" + tokenId + "']" );
			if( token != null && token.validUntil.compareTo( new Date() ) >= 0 )
			{
				UserDTO user = HexaSpring.hexa().db().qpath.queryOneDTO( UserDTO.class, "users [id=" + token.userId + "]" );
				if( user != null )
					userIn( user );
			}
		}
	}

	public void onEndServletRequestProcessing()
	{
		cleanThread();
	}

	public void runInBackground( final Runnable runnable )
	{
		Thread thread = new Thread( new Runnable()
		{
			@Override
			public void run()
			{
				log.info( "Starting a background thread..." );

				runnable.run();

				cleanThread();

				log.info( "Background thread stopped" );
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

	// do a transaction management : prepare tx, and watch for any exception,
	// then rollback it.
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

		if( info.request != null )
			info.request = null;

		// release current thread database context
		if( info.databaseContext != null )
		{
			databaseContextFactory.releaseDatabaseContext( info.databaseContext );
			info.databaseContext = null;
		}
	}

	// handles the database connection creation
	
	public synchronized DatabaseContextFactory dbFactory( String databaseUri )
	{
		DatabaseContextFactory factory = new DatabaseContextFactory();
		if( ! factory.init( databaseUri ) )
		{
			log.severe( "Cannot initialize database connection pool, it won't be available to the program !" );
			return null;
		}
		
		return factory;
	}

	public DatabaseContext db()
	{
		HexaThreadInfo info = HexaThreadInfo.get();
		if( info.databaseContext != null )
			return info.databaseContext;

		info.databaseContext = databaseContextFactory.requestDatabaseContext();
		assert info.databaseContext != null;

		return info.databaseContext;
	}

	// Store HTTP session thread wide

	public HttpSession httpSession()
	{
		return HexaThreadInfo.get().request.getSession();
	}

	public HttpServletRequest httpRequest()
	{
		return HexaThreadInfo.get().request;
	}

	// manage per http session logged user

	public void userIn( UserDTO user )
	{
		// store session wide
		httpSession().setAttribute( LOGGED_USER_ID, user );
	}

	public void userOut()
	{
		httpSession().invalidate();
	}

	public UserDTO user()
	{
		return (UserDTO) httpSession().getAttribute( LOGGED_USER_ID );
	}
}
