package fr.lteconsulting.hexa.persistence.client.legacy.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.Root;

import fr.lteconsulting.hexa.client.sql.SQLiteResult;
import fr.lteconsulting.hexa.client.sql.SQLiteResult.Row;
import fr.lteconsulting.hexa.client.sql.SQLiteTypeManagerManager;
import fr.lteconsulting.hexa.client.sql.SQLiteTypeManagerManager.SQLiteTypeManager;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.ManagedObjectPool.AttachedObjectInfo;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.PersistenceConfiguration.EntityConfiguration;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.PersistenceConfiguration.FieldConfiguration;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.PersistenceConfiguration.ManyToOneFieldConfiguration;

public class QueryStructure
{
	PersistenceConfiguration configuration;

	ArrayList<RootImpl<?>> roots = new ArrayList<RootImpl<?>>();
	ArrayList<SqlRenderable> whereClauses = new ArrayList<SqlRenderable>();
	Selection selection;

	QueryStructure( PersistenceConfiguration configuration )
	{
		this.configuration = configuration;
	}

	public <X> Root<X> from( Class<X> entityClass )
	{
		RootImpl<X> root = new RootImpl<X>( entityClass, configuration );
		roots.add( root );
		return root;
	}

	public void where( Object... clauses )
	{
		for( int i=0; i<clauses.length; i++ )
			whereClauses.add( (SqlRenderable) clauses[i] );
	}

	public void multiselect( Object[] selections )
	{
		selection = new ObjectSelection( selections );
	}

	public String getSQL()
	{
		StringBuilder sb = new StringBuilder();

		sb.append( "select " );

		if( selection == null )
			selection = new DefaultSelection();

		selection.processSelectClause( sb );

		sb.append( " from " );

		boolean addComa = false;
		for( RootImpl<?> root : roots )
		{
			if( addComa )
				sb.append( ", " );
			addComa = true;

			EntityConfiguration entityConfig = configuration.getConfigurationForEntity( root.entityClass );
			sb.append( entityConfig.tableName );
			sb.append( " " );
			sb.append( root.sqlAlias );
		}

		if( ! whereClauses.isEmpty() )
			sb.append( " where " );
		boolean addAnd = false;
		for( SqlRenderable whereClause : whereClauses )
		{
			if( addAnd )
				sb.append( " and " );
			addAnd = true;

			whereClause.appendSql( sb );
		}

		return sb.toString();
	}

	public List<?> interpretResultAndFeedEntityManager( SQLiteResult results, EntityManagerImpl em )
	{
		ArrayList<?> list = new ArrayList<>();

		for( SQLiteResult.Row row : results )
		{
			selection.processResultRow( row, em, list );
		}

		return list;
	}

	interface Selection
	{
		void processResultRow( Row row, EntityManagerImpl em, ArrayList<?> list );
		void processSelectClause( StringBuilder sb );
	}

	class ObjectSelection implements Selection
	{
		Object[] selections;
		HashMap<Object, String> aliases = new HashMap<Object, String>();

		public ObjectSelection( Object[] selections )
		{
			this.selections = selections;
		}

		@Override
		public void processSelectClause( StringBuilder sb )
		{
			for( int i=0; i<selections.length; i++ )
			{
				if( i > 0 )
					sb.append( ", " );

				SqlRenderable sqlRenderable = (SqlRenderable) selections[i];
				sqlRenderable.appendSql( sb );
				sb.append( " as " );

				String alias = AliasGeneration.nextAlias();
				aliases.put( sqlRenderable, alias );
				sb.append( alias );
			}
		}

		@SuppressWarnings( { "unchecked", "rawtypes" } )
		@Override
		public void processResultRow( Row row, EntityManagerImpl em, ArrayList<?> list )
		{
			Object[] result = new Object[selections.length];

			for( int i=0; i<selections.length; i++ )
			{
				String stringValue = row.getColumnValue( aliases.get( selections[i] ) );

				if( selections[i] instanceof PathImpl )
				{
					PathImpl<?> path = (PathImpl<?>) selections[i];
					EntityConfiguration config = path.root.configuration.getConfigurationForEntity( path.root.entityClass );

					SQLiteTypeManager sqlMng = SQLiteTypeManagerManager.get( config.getFieldConfiguration( path.path ).fieldClass );

					result[i] = sqlMng.getValueFromString( stringValue );
				}
			}
			
			((List)list).add( result );
		}
	}

	// default selection : selects all fields from all root entities
	class DefaultSelection implements Selection
	{
		@Override
		public void processSelectClause( StringBuilder sb )
		{
			boolean addComa = false;
			for( RootImpl<?> root : roots )
			{
				EntityConfiguration entityConfig = configuration.getConfigurationForEntity( root.entityClass );

				addOneSelectedField( sb, addComa, root.sqlAlias, entityConfig.idField.columnName );
				addComa = true;

				for( FieldConfiguration field : entityConfig.directFields )
					addOneSelectedField( sb, addComa, root.sqlAlias, field.columnName );

				for( ManyToOneFieldConfiguration field : entityConfig.manyToOneFields )
					addOneSelectedField( sb, addComa, root.sqlAlias, field.columnName );
			}
		}

		@SuppressWarnings( { "unchecked", "rawtypes" } )
		@Override
		public void processResultRow( Row row, EntityManagerImpl em, ArrayList<?> list )
		{
			assert roots.size() == 1 : "not yet implemented !";

			RootImpl<?> root = roots.get( 0 );
			EntityConfiguration entityConfig = configuration.getConfigurationForEntity( root.entityClass );

			// for this root entity, what is the id of the selected object ?
			String idValue = row.getColumnValue( root.sqlAlias + "_" + entityConfig.idField.columnName );
			Object id = SQLiteTypeManagerManager.get( entityConfig.idField.fieldClass ).getValueFromString( idValue );

			// is it already in the managed object pool ?
			AttachedObjectInfo entityInfo = em.pool.findAttachedObjectByTableAndId( entityConfig.tableName, id );
			if( entityInfo != null )
			{
				// if yes, the registered object will be added to the list
				((List)list).add( entityInfo.managedObject );
			}
			else
			{
				// if not, we create the object and register it, and add it to the list
				SQLiteResult.Row convertedRow = new Row();
				convertedRow.addCell( entityConfig.idField.columnName, row.getColumnValue( root.sqlAlias + "_" + entityConfig.idField.columnName ) );
				for( FieldConfiguration field : entityConfig.directFields )
					convertedRow.addCell( field.columnName, row.getColumnValue( root.sqlAlias + "_" + field.columnName ) );
				for( ManyToOneFieldConfiguration field : entityConfig.manyToOneFields )
					convertedRow.addCell( field.columnName, row.getColumnValue( root.sqlAlias + "_" + field.columnName ) );

				Object entity = em.createObjectAndRegisterIt( convertedRow, id, entityConfig );
				((List)list).add( entity );
			}
		}

		private void addOneSelectedField( StringBuilder sb, boolean addComa, String tableAlias, String columnName )
		{
			if( addComa )
				sb.append( ", " );

			sb.append( tableAlias + "." + columnName );
			sb.append( " as " );
			sb.append( tableAlias + "_" + columnName );
		}
	}
}