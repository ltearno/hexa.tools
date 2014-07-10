package fr.lteconsulting.hexa.persistence.client.legacy.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GenerationType;

import fr.lteconsulting.hexa.client.classinfo.ClassInfo;
import fr.lteconsulting.hexa.client.classinfo.Clazz;

public class PersistenceConfiguration
{
	HashMap<String, EntityConfiguration> entityConfigurations = new HashMap<String, EntityConfiguration>();

	public EntityConfiguration addEntityConfiguration( Class<?> entityClass, Class<?> idFieldClass, String idFieldName, GenerationType idGenerationType )
	{
		if( entityClass == null )
			return null;
		if( entityConfigurations.containsKey( entityClass.getName() ) )
			return null;

		String tableName = entityClass.getName().substring( 1 + entityClass.getName().lastIndexOf( "." ) );

		EntityConfiguration entityConfiguration = new EntityConfiguration( entityClass, tableName, idFieldClass, idFieldName, idGenerationType );
		entityConfigurations.put( entityClass.getName(), entityConfiguration );

		return entityConfiguration;
	}

	public EntityConfiguration getConfigurationForEntity( Class<?> clazz )
	{
		return entityConfigurations.get( clazz.getName() );
	}

	public static class EntityConfiguration
	{
		Class<?> entityClass;
		Clazz<?> entityClazz;
		String tableName;

		GenerationType idGenerationType;
		FieldConfiguration idField;
		List<FieldConfiguration> directFields;
		List<ManyToOneFieldConfiguration> manyToOneFields;
		List<OneToManyFieldConfiguration> oneToManyFields;

		public EntityConfiguration( Class<?> entityClass, String tableName, Class<?> idFieldClass, String idFieldName, GenerationType idGenerationType )
		{
			this.entityClass = entityClass;
			this.entityClazz = ClassInfo.Clazz( entityClass );
			this.tableName = tableName;

			this.idGenerationType = idGenerationType;
			this.idField = new FieldConfiguration( idFieldClass, idFieldName, idFieldName );

			directFields = new ArrayList<FieldConfiguration>();
			manyToOneFields = new ArrayList<ManyToOneFieldConfiguration>();
			oneToManyFields = new ArrayList<OneToManyFieldConfiguration>();
		}

		public void addFieldConfiguration( Class<?> fieldClass, String fieldName )
		{
			directFields.add( new FieldConfiguration( fieldClass, fieldName, fieldName ) );
		}

		public void addManyToOneFieldConfiguration( Class<?> fieldClass, String fieldName, String columnName, FetchType fetchType )
		{
			manyToOneFields.add( new ManyToOneFieldConfiguration( fieldClass, fieldName, columnName, fetchType ) );
		}

		public void addOneToManyFieldConfiguration( Class<?> containerClass, Class<?> targetClass, String fieldName, String mappedBy, boolean isOrphanRemoval )
		{
			oneToManyFields.add( new OneToManyFieldConfiguration( containerClass, targetClass, fieldName, mappedBy, isOrphanRemoval ) );
		}

		public FieldConfiguration getFieldConfiguration( String fieldName )
		{
			if( idField.fieldName.equals( fieldName ) )
				return idField;

			for( FieldConfiguration c : directFields )
				if( c.fieldName.equals( fieldName ) )
					return c;
			return null;
		}

		public ManyToOneFieldConfiguration getManyToOneFieldConfiguration( String fieldName )
		{
			for( ManyToOneFieldConfiguration c : manyToOneFields )
				if( c.fieldName.equals( fieldName ) )
					return c;
			return null;
		}

		public Object createEntityProxy( EntityManagerImpl em )
		{
			Object proxy = null;
			if( entityClass == Category.class )
				proxy = new CategoryProxy( em );

			return proxy;
		}
	}

	public static class FieldConfiguration
	{
		Class<?> fieldClass;
		String fieldName;
		String columnName;

		public FieldConfiguration( Class<?> fieldClass, String fieldName, String columnName )
		{
			this.fieldClass = fieldClass;
			this.fieldName = fieldName;
			this.columnName = columnName;
		}
	}

	public static class ManyToOneFieldConfiguration
	{
		Class<?> fieldClass;
		String fieldName;
		String columnName;

		FetchType fetchType;

		public ManyToOneFieldConfiguration( Class<?> fieldClass, String fieldName, String columnName, FetchType fetchType )
		{
			this.fieldClass = fieldClass;
			this.fieldName = fieldName;
			this.columnName = columnName;
			this.fetchType = fetchType;
		}
	}

	public static class OneToManyFieldConfiguration
	{
		Class<?> containerClass;
		Class<?> targetClass;
		String fieldName;
		String mappedBy;
		boolean isOrphanRemoval;

		public OneToManyFieldConfiguration( Class<?> containerClass, Class<?> targetClass, String fieldName, String mappedBy, boolean isOrphanRemoval )
		{
			this.containerClass = containerClass;
			this.targetClass = targetClass;
			this.fieldName = fieldName;
			this.mappedBy = mappedBy;
			this.isOrphanRemoval = isOrphanRemoval;
		}
	}
}
