package fr.lteconsulting.hexa.persistence.client.legacy.persistence;

import java.util.Map;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GenerationType;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.storage.client.Storage;

import fr.lteconsulting.hexa.client.sql.SQLite;
import fr.lteconsulting.hexa.client.sql.SQLiteTypeManagerManager;
import fr.lteconsulting.hexa.client.sql.SQLiteTypeManagerManager.SQLiteTypeManager;
import fr.lteconsulting.hexa.client.tools.Action2;
import fr.lteconsulting.hexa.client.tools.Delayer;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.PersistenceConfiguration.EntityConfiguration;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.PersistenceConfiguration.FieldConfiguration;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.PersistenceConfiguration.ManyToOneFieldConfiguration;

public class EntityManagerFactoryImpl implements EntityManagerFactory
{
	private final String name;
	PersistenceConfiguration configuration;
	SQLite sqlite;

	Delayer delay = new Delayer( 1000, new Delayer.Callback()
	{
		@Override
		public void onDelayedEvent()
		{
			Storage store = Storage.getLocalStorageIfSupported();
			if( store == null )
				return;

			JsArrayInteger jsArray = sqlite.exportData();
			if( jsArray != null )
				store.setItem( "db_" + name, new JSONArray( jsArray ).toString() );
		}
	}, true );

	EntityManagerFactoryImpl( String name, @SuppressWarnings( "rawtypes" ) Map parameters )
	{
		this.name = name;
		if( parameters != null )
		{
			configuration = (PersistenceConfiguration) parameters.get( "entitiesConfiguration" );
		}

		Storage store = Storage.getLocalStorageIfSupported();
		if( store != null )
		{
			String item = store.getItem( "db_" + name );
			if( item != null )
			{
				JSONValue json = JSONParser.parseLenient( item );
				JsArrayInteger jsArray = json.isArray().getJavaScriptObject().cast();
				sqlite = SQLite.create( jsArray );
			}
		}

		if( sqlite == null )
		{
			// TODO : try to load the database from local storage
			sqlite = SQLite.create();

			// create database structure from configuration
			// TODO : also need to manage updates...
			createDatabaseStructure( configuration );

			sqlite.execute( "create table NEXTID (tableName VARCHAR(100), nextId INTEGER)" );
		}

		sqlite.setStatementCallback( new Action2<String, JavaScriptObject>()
		{
			@Override
			public void exec( String p1, JavaScriptObject p2 )
			{
				delay.trigger();
			}
		} );
	}

	void createDatabaseStructure( PersistenceConfiguration configuration )
	{
		for( EntityConfiguration entityConfiguration : configuration.entityConfigurations.values() )
		{
			StringBuilder sb = new StringBuilder();

			sb.append( "create table " );
			sb.append( entityConfiguration.tableName );
			sb.append( "(" );

			FieldConfiguration idFieldConfiguration = entityConfiguration.idField;
			SQLiteTypeManager mng = SQLiteTypeManagerManager.get( idFieldConfiguration.fieldClass );
			String creationString = mng.createFieldSql( idFieldConfiguration.columnName, true, entityConfiguration.idGenerationType==GenerationType.IDENTITY );
			assert creationString != null;

			sb.append( idFieldConfiguration.columnName );
			sb.append( " " );
			sb.append( creationString );
			sb.append( " " );

			for( FieldConfiguration fieldConfiguration : entityConfiguration.directFields )
			{
				sb.append( ", " );

				mng = SQLiteTypeManagerManager.get( fieldConfiguration.fieldClass );
				creationString = mng.createFieldSql( fieldConfiguration.columnName, false, false );

				assert creationString != null;

				sb.append( fieldConfiguration.columnName );
				sb.append( " " );
				sb.append( creationString );
			}

			for( ManyToOneFieldConfiguration fieldConfiguration : entityConfiguration.manyToOneFields )
			{
				sb.append( ", " );

				EntityConfiguration relatedEntityConfiguration = configuration.getConfigurationForEntity( fieldConfiguration.fieldClass );
				assert relatedEntityConfiguration != null : "Cannot find a proper configuration for entity " + fieldConfiguration.fieldClass.getName();

				mng = SQLiteTypeManagerManager.get( relatedEntityConfiguration.idField.fieldClass );
				creationString = mng.createFieldSql( fieldConfiguration.columnName, false, false );

				assert creationString != null;

				sb.append( fieldConfiguration.columnName );
				sb.append( " " );
				sb.append( creationString );
			}

			sb.append( ");" );

			String sql = sb.toString();

			sqlite.execute( sql );
		}
	}

	@Override
	public void close()
	{
		assert false;
	}

	@Override
	public EntityManager createEntityManager()
	{
		return createEntityManager( null );
	}

	@Override
	public EntityManager createEntityManager( @SuppressWarnings( "rawtypes" ) Map arg0 )
	{
		EntityManager em = new EntityManagerImpl( name, configuration, sqlite );

		return em;
	}

	@Override
	public Cache getCache()
	{
		assert false;
		return null;
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder()
	{
		assert false;
		return null;
	}

	@Override
	public Metamodel getMetamodel()
	{
		assert false;
		return null;
	}

	@Override
	public PersistenceUnitUtil getPersistenceUnitUtil()
	{
		assert false;
		return null;
	}

	@Override
	public Map<String, Object> getProperties()
	{
		assert false;
		return null;
	}

	@Override
	public boolean isOpen()
	{
		assert false;
		return false;
	}
}
