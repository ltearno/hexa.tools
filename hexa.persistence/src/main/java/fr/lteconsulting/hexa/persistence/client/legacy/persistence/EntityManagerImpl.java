package fr.lteconsulting.hexa.persistence.client.legacy.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.GenerationType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.shared.GWT;

import fr.lteconsulting.hexa.client.classinfo.Clazz;
import fr.lteconsulting.hexa.client.classinfo.Field;
import fr.lteconsulting.hexa.client.sql.SQLite;
import fr.lteconsulting.hexa.client.sql.SQLiteResult;
import fr.lteconsulting.hexa.client.sql.SQLiteTypeManagerManager;
import fr.lteconsulting.hexa.client.sql.SQLiteTypeManagerManager.SQLiteTypeManager;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.ManagedObjectPool.AttachedObjectInfo;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.PersistenceConfiguration.EntityConfiguration;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.PersistenceConfiguration.FieldConfiguration;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.PersistenceConfiguration.ManyToOneFieldConfiguration;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.PersistenceConfiguration.OneToManyFieldConfiguration;

public class EntityManagerImpl implements EntityManager
{
	PersistenceConfiguration configuration;

	private final SQLite sqlite;

	ManagedObjectPool pool = new ManagedObjectPool();

	private TransactionImpl currentTx;

	public EntityManagerImpl( String name, PersistenceConfiguration configuration, SQLite sqlite )
	{
		this.configuration = configuration;
		this.sqlite = sqlite;
	}

	private int generateId( String tableName )
	{
		JavaScriptObject res = sqlite.execute( "select nextId from NEXTID where tableName='"+tableName+"'" );
		SQLiteResult sqlRes = new SQLiteResult( res );
		if( sqlRes.size() == 0 )
		{
			sqlite.execute( "insert into NEXTID (tableName, nextId) values ('"+tableName+"', 2)" );
			return 1;
		}
		else
		{
			int nextId = Integer.parseInt( sqlRes.getRow( 0 ).getColumnValue( "nextId" ) );
			sqlite.execute( "update NEXTID set nextId=nextId+1 where tableName='"+tableName+"'" );
			return nextId;
		}
	}

	@Override
	public void clear()
	{
		assert false;
	}

	@Override
	public void close()
	{
		assert false;
	}

	@Override
	public boolean contains( Object arg0 )
	{
		assert false;
		return false;
	}

	@Override
	public Query createNamedQuery( String arg0 )
	{
		assert false;
		return null;
	}

	@Override
	public <T> TypedQuery<T> createNamedQuery( String arg0, Class<T> arg1 )
	{
		assert false;
		return null;
	}

	@Override
	public Query createNativeQuery( String arg0 )
	{
		assert false;
		return null;
	}

	@Override
	public Query createNativeQuery( String arg0, @SuppressWarnings( "rawtypes" ) Class arg1 )
	{
		assert false;
		return null;
	}

	@Override
	public Query createNativeQuery( String arg0, String arg1 )
	{
		assert false;
		return null;
	}

	@Override
	public Query createQuery( String arg0 )
	{
		assert false;
		return null;
	}

	@Override
	public <T> TypedQuery<T> createQuery( CriteriaQuery<T> arg0 )
	{
		return new TypedQueryImpl<T>( (CriteriaQueryImpl<T>) arg0, this );
	}

	@Override
	public <T> TypedQuery<T> createQuery( String arg0, Class<T> arg1 )
	{
		assert false;
		return null;
	}

	@Override
	public void detach( Object arg0 )
	{
		AttachedObjectInfo info = pool.findAttachedObjectByReference( arg0 );
		if( info != null )
			pool.detachObject( info );
	}

	// update the db with the current persistence context...
	private void commitChanges( ManagedObjectPool pool )
	{
		GWT.log( "Committing changed into database..." );

		// first do inserts
		List<AttachedObjectInfo> toInsert = pool.getObjectsToBeInserted();
		while( ! toInsert.isEmpty() )
		{
			AttachedObjectInfo info = toInsert.remove( 0 );
			boolean postpone = false;

			// SQL : "insert into TABLE (fields) (values)"
			StringBuilder sb = new StringBuilder();

			sb.append( "INSERT INTO " );
			sb.append( info.entityConfiguration.tableName );
			sb.append( "(" );

			StringBuilder sbValues = new StringBuilder();

			boolean fComa = false;

			// also insert object's ID (if GenerationType != IDENTITY)
			if( info.entityConfiguration.idGenerationType != GenerationType.IDENTITY )
			{
				fComa = true;

				SQLiteTypeManager mng = SQLiteTypeManagerManager.get( info.entityConfiguration.idField.fieldClass );
				Field field = info.entityConfiguration.entityClazz.getAllField( info.entityConfiguration.idField.fieldName );

				sb.append( info.entityConfiguration.idField.columnName + " " );

				if( ! mng.appendUpdateValueSql( sbValues, field, info.managedObject ) )
					throw new RuntimeException( "Cannot append SQL update for ID field " + info.entityConfiguration.idField.fieldName + " of type " + info.entityConfiguration.idField.fieldClass.getName() );
			}

			for( FieldConfiguration fieldConfiguration : info.entityConfiguration.directFields )
			{
				SQLiteTypeManager mng = SQLiteTypeManagerManager.get( fieldConfiguration.fieldClass );
				Field field = info.entityConfiguration.entityClazz.getAllField( fieldConfiguration.fieldName );

				if( fComa )
				{
					sb.append( ", " );
					sbValues.append( ", " );
				}
				fComa = true;

				sb.append( fieldConfiguration.columnName + " " );

				if( ! mng.appendUpdateValueSql( sbValues, field, info.managedObject ) )
					throw new RuntimeException( "Cannot append SQL update for field " + fieldConfiguration.fieldName + " of type " + fieldConfiguration.fieldClass.getName() );
			}

			// check that there is not a relationship that forbids us to insert it
			for( ManyToOneFieldConfiguration fieldConfig : info.entityConfiguration.manyToOneFields )
			{
				Field field = info.entityConfiguration.entityClazz.getAllField( fieldConfig.fieldName );
				Object value = field.getValue( info.managedObject );
				if( value != null )
				{
					AttachedObjectInfo relatedObjectInfo = pool.findAttachedObjectByReference( value );
					assert relatedObjectInfo != null : "You are trying to persist an object which references a non managed object, this is bad !";

					if( relatedObjectInfo.isToBeInserted() )
					{
						toInsert.add( info );
						postpone = true;
						break;
					}

					// insert the id...
					if( fComa )
					{
						sb.append( ", " );
						sbValues.append( ", " );
					}
					fComa = true;

					sb.append( fieldConfig.columnName + " " );

					EntityConfiguration targetEntityConfiguration = configuration.getConfigurationForEntity( fieldConfig.fieldClass );
					assert targetEntityConfiguration != null : "Targetted entity has not been configured !";

					SQLiteTypeManager mng = SQLiteTypeManagerManager.get( targetEntityConfiguration.idField.fieldClass );
					if( ! mng.appendUpdateValueSql( sbValues, targetEntityConfiguration.entityClazz.getAllField( targetEntityConfiguration.idField.fieldName ), relatedObjectInfo.managedObject ) )
						throw new RuntimeException( "Cannot append SQL update for FK field " + fieldConfig.fieldName + " of type " + fieldConfig.fieldClass.getName() );
				}
			}
			if( postpone )
				continue;

			sb.append( ") VALUES (" );
			sb.append( sbValues.toString() );
			sb.append( ");" );

			String sql = sb.toString();

			sqlite.execute( sql );

			// update object's ID (if GenerationType == IDENTITY)
			if( info.entityConfiguration.idGenerationType == GenerationType.IDENTITY )
			{
				assert info.entityConfiguration.idField.fieldClass == int.class;
				int lastId = sqlite.getLastInsertedId();
				if( lastId > 0 )
				{
					Field idField = info.entityConfiguration.entityClazz.getAllField( info.entityConfiguration.idField.fieldName );
					idField.setValue( info.managedObject, lastId );
				}
			}

			info.markAsInserted();
		}

		// then do updates
		for( AttachedObjectInfo info : pool.attachedObjects )
		{
			if( info.row == null || info.fToDelete )
				continue;

			// SQL : update from TABLE SET xxx=vvv WHERE id=ID
			// UPDATE users SET name = 'toto', xxx = value WHERE expression;
			StringBuilder sb = new StringBuilder();

			sb.append( "UPDATE " );
			sb.append( info.entityConfiguration.tableName );
			sb.append( " SET " );

			// TODO also update record id if it has changed (the id from the database is in info.managedObjectId)

			boolean fComa = false;

			for( FieldConfiguration fieldConfiguration : info.entityConfiguration.directFields )
			{
				SQLiteTypeManager mng = SQLiteTypeManagerManager.get( fieldConfiguration.fieldClass );
				Field field = info.entityConfiguration.entityClazz.getAllField( fieldConfiguration.fieldName );

				// is the original data in the database the same as the one we have got now ?
				String dbValue = info.row.getColumnValue( fieldConfiguration.columnName );
				String currentValue = mng.getStringForValue( field.getValue( info.managedObject ) );
				if( stringsEqual( dbValue, currentValue ) )
					continue;

				if( fComa )
					sb.append( ", " );
				else
					fComa = true;

				sb.append( fieldConfiguration.columnName + " " );
				sb.append( " = " );
				mng.appendUpdateValueSql( sb, field, info.managedObject );
			}

			// do the same for ManyToOne fields (referenced ids might have changed)
			for( ManyToOneFieldConfiguration fieldConfiguration : info.entityConfiguration.manyToOneFields )
			{
				EntityConfiguration targetConfiguration = configuration.getConfigurationForEntity( fieldConfiguration.fieldClass );
				SQLiteTypeManager mng = SQLiteTypeManagerManager.get( targetConfiguration.idField.fieldClass );

				// has the referencing id changed ?
				String dbValue = info.row.getColumnValue( fieldConfiguration.columnName );
				Object targetEntity = info.entityConfiguration.entityClazz.getAllField( fieldConfiguration.fieldName ).getValue( info.managedObject );
				Object targetEntityId = targetConfiguration.entityClazz.getAllField( targetConfiguration.idField.fieldName ).getValue( targetEntity );
				String currentValue = mng.getStringForValue( targetEntityId );

				if( stringsEqual( dbValue, currentValue ) )
					continue;

				if( fComa )
					sb.append( ", " );
				else
					fComa = true;

				sb.append( fieldConfiguration.columnName + " " );
				sb.append( " = " );
				mng.appendUpdateValueSql( sb, targetConfiguration.entityClazz.getAllField( targetConfiguration.idField.fieldName ), targetEntity );
			}

			// TODO : take id type in account

			if( ! fComa )
				continue; // nothing to update

			sb.append( " WHERE id=" );
			sb.append( info.managedObjectId );
			sb.append( ";" );

			String sql = sb.toString();

			sqlite.execute( sql );
		}

		// and eventually, do deletes
		for( AttachedObjectInfo info : pool.attachedObjects )
		{
			if( ! info.fToDelete )
				continue;

			// SQL : delete from TABLE where id=ID
			if( info.managedObjectId == null )
				continue; // no need to delete a record that does not exist in database

			StringBuilder sb = new StringBuilder();

			sb.append( "delete from " );
			sb.append( info.entityConfiguration.tableName );

			sb.append( " WHERE id=" );
			sb.append( info.managedObjectId );
			sb.append( ";" );

			String sql = sb.toString();

			sqlite.execute( sql );
		}
	}

	private boolean stringsEqual( String s1, String s2 )
	{
		if( s1==null && s2==null )
			return true;
		if( s1==null || s2==null )
			return false;
		return s1.equals( s2 );
	}

	@SuppressWarnings( "unchecked" )
	public <T> List<T> executeTypedQueryAndGetResultList( TypedQueryImpl<T> query )
	{
		// compute SQLite's SQL dialect
		String sql = query.criteriaQuery.queryStructure.getSQL();

		// execute query on DB
		JavaScriptObject r = sqlite.execute( sql );
		SQLiteResult results = new SQLiteResult( r );

		// interpret results
		// feed the entity manager
		return (List<T>) query.criteriaQuery.queryStructure.interpretResultAndFeedEntityManager( results, this );
	}

	@Override
	public <T> T find( Class<T> arg0, Object arg1 )
	{
		// SELECT * FROM entity.tableName WHERE idFieldName=arg1
		EntityConfiguration config = configuration.entityConfigurations.get( arg0.getName() );
		assert config != null : "Class " + arg0.getName() + " is not part of the configured entities !";

		// if already managed, return it
		AttachedObjectInfo objectInfo = pool.findAttachedObjectByTableAndId( config.tableName, arg1 );
		if( objectInfo != null )
		{
			// TODO : maybe load data if not yet loaded...
			@SuppressWarnings( "unchecked" )
			T object = (T) objectInfo.managedObject;
			return object;
		}

		SQLiteResult.Row row = readObjectFromDatabase( config, arg1 );
		if( row == null )
			return null;

		@SuppressWarnings( "unchecked" )
		T object = (T) config.entityClazz.NEW();

		readSQLiteResultToEntityObject( row, object, config );

		pool.attachObject( config, arg1, object, row, false );

		return object;
	}

	@SuppressWarnings( "unchecked" )
	public <T> List<T> getCollectionList( Object owner, OneToManyFieldConfiguration fieldConfiguration )
	{
		AttachedObjectInfo ownerInfo = pool.findAttachedObjectByReference( owner );
		assert ownerInfo != null : "Bad !! ownerInfo is not in the managed pool. That must be a bug !";

		// sql to get the records of the collection
		EntityConfiguration targetConfiguration = configuration.getConfigurationForEntity( fieldConfiguration.targetClass );
		ManyToOneFieldConfiguration manyToOneFieldConfiguration = targetConfiguration.getManyToOneFieldConfiguration( fieldConfiguration.mappedBy );
		assert manyToOneFieldConfiguration != null : "A OneToMany relationship should have the inverse relationship defined in the target entity !";

		SQLiteResult dbResults = readObjectsFromDatabase( targetConfiguration, manyToOneFieldConfiguration.columnName, ownerInfo.managedObjectId );

		List<T> list = new ArrayList<T>();

		// for each record, check if already in the pool. In that case add the already registered object in the pool
		SQLiteTypeManager mng = SQLiteTypeManagerManager.get( targetConfiguration.idField.fieldClass );
		for( SQLiteResult.Row row : dbResults )
		{
			String idColumnValue = row.getColumnValue( targetConfiguration.idField.columnName );
			Object rowId = mng.getValueFromString( idColumnValue );

			AttachedObjectInfo rowInfo = pool.findAttachedObjectByTableAndId( targetConfiguration.tableName, rowId );
			if( rowInfo != null )
			{
				// if the record already exist in the pool, use the existing
				list.add(  (T) rowInfo.managedObject );
			}
			else
			{
				// if not, create and attach a object representing the db row
				T object = (T) targetConfiguration.entityClazz.NEW();

				readSQLiteResultToEntityObject( row, object, targetConfiguration );

				pool.attachObject( targetConfiguration, rowId, object, row, false );

				list.add( object );
			}
		}

		// return the list
		return list;
	}

	private SQLiteResult.Row readObjectFromDatabase( EntityConfiguration config, Object id )
	{
		JavaScriptObject result = sqlite.execute( "select * from " + config.tableName + " where " + config.idField.columnName + " = " + id );
		SQLiteResult sqliteResults = new SQLiteResult( result );
		if( sqliteResults.size() == 0 )
			return null;
		return sqliteResults.getRow( 0 );
	}

	private SQLiteResult readObjectsFromDatabase( EntityConfiguration config, String columnName, Object columnValue )
	{
		JavaScriptObject result = sqlite.execute( "select * from " + config.tableName + " where " + columnName + " = " + columnValue );
		SQLiteResult sqliteResults = new SQLiteResult( result );
		return sqliteResults;
	}

	<T> T createObjectAndRegisterIt( SQLiteResult.Row row, Object id, EntityConfiguration config )
	{
		// create the object
		@SuppressWarnings( "unchecked" )
		T object = (T) config.entityClazz.NEW();

		readSQLiteResultToEntityObject( row, object, config );

		pool.attachObject( config, id, object, row, false );

		return object;
	}

	private <T> void readSQLiteResultToEntityObject( SQLiteResult.Row row, T object, EntityConfiguration config )
	{
		// id field
		fillValueFromSQLiteResult( config.entityClazz, object, row, config.idField );

		// direct fields
		for( FieldConfiguration fieldConfiguration : config.directFields )
			fillValueFromSQLiteResult( config.entityClazz, object, row, fieldConfiguration );

		// ManyToOne fields
		for( ManyToOneFieldConfiguration fieldConfiguration : config.manyToOneFields )
		{
			EntityConfiguration referencedEntityConfiguration = configuration.getConfigurationForEntity( fieldConfiguration.fieldClass );

			// sql type of the field is the type of the referenced table's id field
			SQLiteTypeManager mng = SQLiteTypeManagerManager.get( referencedEntityConfiguration.idField.fieldClass );
			String columnValue = row.getColumnValue( fieldConfiguration.columnName );

			// if reference id IS NULL, do nothing
			if( columnValue == null )
				continue;

			// do we already have a managed instance for that record ?
			Object id = mng.getValueFromString( columnValue );
			AttachedObjectInfo attachedObjectInfo = pool.findAttachedObjectByTableAndId( referencedEntityConfiguration.tableName, id );
			if( attachedObjectInfo == null )
			{
				// create a Proxy initialized only with the id field
				Object proxy = createProxyFor( referencedEntityConfiguration, id );
				attachedObjectInfo = pool.attachObject( referencedEntityConfiguration, id, proxy, null, true );
			}

			// put the proxy in the entity field
			config.entityClazz.getAllField( fieldConfiguration.fieldName ).setValue( object, attachedObjectInfo.managedObject );
		}

		// OneToMany fields
		for( OneToManyFieldConfiguration fieldConfiguration : config.oneToManyFields )
		{
			// create collection proxy for a List of Article... parameter : config and fieldName
			assert fieldConfiguration.containerClass == List.class : "For the moment, only List collection type is supported...";
			ListProxy<?> listProxy = new ListProxy<T>( this, object, fieldConfiguration );

			// put the list proxy in the entity field
			config.entityClazz.getAllField( fieldConfiguration.fieldName ).setValue( object, listProxy );
		}
	}

	private Object createProxyFor( EntityConfiguration configuration, Object id )
	{
		Object proxy = configuration.createEntityProxy( this );

		// id field
		configuration.entityClazz.getAllField( configuration.idField.fieldName ).setValue( proxy, id );

		return proxy;
	}

	public void loadProxyInternalObject( Object proxy )
	{
		AttachedObjectInfo info = pool.findAttachedObjectByReference( proxy );
		assert info != null : "Error, proxy should be found in the managed pool, you should not try to access not loaded proxified instances on a detached proxy...";

		SQLiteResult.Row row = readObjectFromDatabase( info.entityConfiguration, info.managedObjectId );
		assert row != null : "Error, proxy should be found in the managed pool, you should not try to access not loaded proxified instances on a detached proxy (bis)...";

		readSQLiteResultToEntityObject( row, info.managedObject, info.entityConfiguration );

		info.row = row;
	}

	private void copyValues( Object from, Object to, EntityConfiguration config )
	{
		// id field
		copyValue( from, to, config.entityClazz, config.idField.fieldName );

		// direct fields
		for( FieldConfiguration fieldConfiguration : config.directFields )
			copyValue( from, to, config.entityClazz, fieldConfiguration.fieldName );

		// ManyToOne fields
		for( ManyToOneFieldConfiguration fieldConfiguration : config.manyToOneFields )
			copyValue( from, to, config.entityClazz, fieldConfiguration.fieldName );
	}

	private void copyValue( Object from, Object to, Clazz<?> clazz, String fieldName )
	{
		Field field = clazz.getAllField( fieldName );
		field.setValue( to, field.getValue( from ) );
	}

	private void fillValueFromSQLiteResult( Clazz<?> objectType, Object object, SQLiteResult.Row row, FieldConfiguration fieldConfiguration )
	{
		SQLiteTypeManager mng = SQLiteTypeManagerManager.get( fieldConfiguration.fieldClass );
		String columnValue = row.getColumnValue( fieldConfiguration.columnName );
		mng.setFieldValueFromString( objectType.getAllField( fieldConfiguration.fieldName ), object, columnValue );
	}

	@Override
	public <T> T find( Class<T> arg0, Object arg1, Map<String, Object> arg2 )
	{
		assert false;
		return null;
	}

	@Override
	public <T> T find( Class<T> arg0, Object arg1, LockModeType arg2 )
	{
		assert false;
		return null;
	}

	@Override
	public <T> T find( Class<T> arg0, Object arg1, LockModeType arg2, Map<String, Object> arg3 )
	{
		assert false;
		return null;
	}

	@Override
	public void flush()
	{
		commitChanges( pool );
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder()
	{
		return new CriteriaBuilderImpl( this );
	}

	@Override
	public Object getDelegate()
	{
		assert false;
		return null;
	}

	@Override
	public EntityManagerFactory getEntityManagerFactory()
	{
		assert false;
		return null;
	}

	@Override
	public FlushModeType getFlushMode()
	{
		assert false;
		return null;
	}

	@Override
	public LockModeType getLockMode( Object arg0 )
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
	public Map<String, Object> getProperties()
	{
		assert false;
		return null;
	}

	@Override
	public <T> T getReference( Class<T> arg0, Object arg1 )
	{
		assert false;
		return null;
	}

	private class TransactionImpl implements EntityTransaction
	{
		boolean active;

		@Override
		public void begin()
		{
			assert ! active : "Transaction already active !";
			active = true;
			sqlite.execute( "begin transaction;" );
		}

		@Override
		public void commit()
		{
			commitChanges( pool );

			active = false;
			sqlite.execute( "commit transaction;" );
			pool.clear();
		}

		@Override
		public boolean getRollbackOnly()
		{
			return false;
		}

		@Override
		public boolean isActive()
		{
			return active;
		}

		@Override
		public void rollback()
		{
			sqlite.execute( "rollback transaction;" );
			pool.clear();
		}

		@Override
		public void setRollbackOnly()
		{
		}
	}

	@Override
	public EntityTransaction getTransaction()
	{
		if( currentTx == null )
			currentTx = new TransactionImpl();

		return currentTx;
	}

	@Override
	public boolean isOpen()
	{
		assert false;
		return false;
	}

	@Override
	public void joinTransaction()
	{
		assert false;
	}

	@Override
	public void lock( Object arg0, LockModeType arg1 )
	{
		assert false;
	}

	@Override
	public void lock( Object arg0, LockModeType arg1, Map<String, Object> arg2 )
	{
		assert false;
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public <T> T merge( T arg0 )
	{
		EntityConfiguration config = configuration.entityConfigurations.get( arg0.getClass().getName() );
		assert config != null : "Class " + arg0.getClass().getName() + " is not part of the configured entities !";

		T result = null;
		if( hasId( arg0, config ) )
		{
			result = (T) find( arg0.getClass(), getEntityObjectId( arg0, config ) );
		}

		if( result == null )
			result = (T) config.entityClazz.NEW();

		// copy field values from arg0 to managed object
		copyValues( arg0, result, config );

		// TODO : maybe cascade merge new records...

		return result;
	}

	@Override
	public void persist( Object arg0 )
	{
		EntityConfiguration config = configuration.entityConfigurations.get( arg0.getClass().getName() );
		assert config != null : "Class " + arg0.getClass().getName() + " is not part of the configured entities !";

		if( hasId( arg0, config ) )
		{
			// TODO if object has an id, it should be already part of the persistence context. Otherwise we should throw an exception...
			AttachedObjectInfo attachedObject = pool.findAttachedObjectByReference( arg0 );
			if( attachedObject == null )
				throw new RuntimeException( "persist() called with an object that already has an id. And this object is not the one we already have in the persistence context : forbidden by JPA law !" );

			// resurrect object if needed...
			attachedObject.fToDelete = false;
		}
		else
		{
			Object id = null;
			if( config.idGenerationType != GenerationType.IDENTITY )
			{
				// generate an id
				assert config.idField.fieldClass == int.class : "id fields different than int not supported yet...";

				id = generateId( config.tableName );

				// and put it in the corresponding object's field
				setEntityObjectId( arg0, config, id );
			}

			// TODO If a relationship is not cascade persist, and a related object is new, then an exception may be thrown if you do not first call persist on the related object.
			// then if a relationship is cascade.persist, go on, cascade !

			// register the object in the persistence context
			pool.attachObject( config, id, arg0, null, false );
		}

		// TODO : maybe cascade persist...
	}

	private boolean hasId( Object o, EntityConfiguration config )
	{
		Object id = getEntityObjectId( o, config );
		if( id instanceof Integer )
			return (Integer)id > 0;

		return id != null;
	}

	private Object getEntityObjectId( Object o, EntityConfiguration config )
	{
		Field idField = config.entityClazz.getAllField( config.idField.fieldName );
		Object id = idField.getValue( o );
		return id;
	}

	private void setEntityObjectId( Object o, EntityConfiguration config, Object id )
	{
		Field idField = config.entityClazz.getAllField( config.idField.fieldName );
		idField.setValue( o, id );
	}

	@Override
	public void refresh( Object arg0 )
	{
		assert false;
	}

	@Override
	public void refresh( Object arg0, Map<String, Object> arg1 )
	{
		assert false;
	}

	@Override
	public void refresh( Object arg0, LockModeType arg1 )
	{
		assert false;
	}

	@Override
	public void refresh( Object arg0, LockModeType arg1, Map<String, Object> arg2 )
	{
		assert false;
	}

	@Override
	public void remove( Object arg0 )
	{
		AttachedObjectInfo info = pool.findAttachedObjectByReference( arg0 );
		if( info == null )
			throw new RuntimeException( "Calling remove on a non-managed object is forbidden !" );

		info.markAsToBeDeleted();
	}

	@Override
	public void setFlushMode( FlushModeType arg0 )
	{
		assert false;
	}

	@Override
	public void setProperty( String arg0, Object arg1 )
	{
		assert false;
	}

	@Override
	public <T> T unwrap( Class<T> arg0 )
	{
		assert false;
		return null;
	}
}
