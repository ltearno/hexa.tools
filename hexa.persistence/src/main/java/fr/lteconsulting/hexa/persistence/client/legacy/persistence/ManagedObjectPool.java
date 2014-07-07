package fr.lteconsulting.hexa.persistence.client.legacy.persistence;

import java.util.ArrayList;
import java.util.List;

import fr.lteconsulting.hexa.client.sql.SQLiteResult;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.PersistenceConfiguration.EntityConfiguration;

public class ManagedObjectPool
{
	List<AttachedObjectInfo> attachedObjects = new ArrayList<AttachedObjectInfo>();

	public static class AttachedObjectInfo
	{
		final EntityConfiguration entityConfiguration;
		final Object managedObject;

		Object managedObjectId;

		// null means that the object has never been loaded from DB
		SQLiteResult.Row row;

		boolean fToDelete = false;
		boolean fToBeInserted;
		boolean isProxy;

		public AttachedObjectInfo( EntityConfiguration entityConfiguration, Object managedObjectId, Object managedObject, SQLiteResult.Row row, boolean isProxy )
		{
			this.entityConfiguration = entityConfiguration;
			this.managedObject = managedObject;
			this.managedObjectId = managedObjectId;
			this.row = row;
			this.isProxy = isProxy;

			fToBeInserted = row == null && (! isProxy);
		}

		public void markAsToBeDeleted()
		{
			fToDelete = true;
		}

		public void markAsInserted()
		{
			fToBeInserted = false;
		}

		public boolean isToBeInserted()
		{
			return fToBeInserted;
		}
	}

	public List<AttachedObjectInfo> getObjectsToBeInserted()
	{
		List<AttachedObjectInfo> res = new ArrayList<AttachedObjectInfo>();
		for( AttachedObjectInfo info : attachedObjects )
		{
			if( ! info.isToBeInserted() )
				continue;

			res.add( info );
		}

		return res;
	}

	public void clear()
	{
		attachedObjects.clear();
	}

	public AttachedObjectInfo attachObject( EntityConfiguration entityConfiguration, Object id, Object managedObject, SQLiteResult.Row row, boolean isProxy )
	{
		AttachedObjectInfo res = new AttachedObjectInfo( entityConfiguration, id, managedObject, row, isProxy );

		attachedObjects.add( res );

		return res;
	}

	public void detachObject( AttachedObjectInfo info )
	{
		attachedObjects.remove( info );
	}

	public AttachedObjectInfo findAttachedObjectByTableAndId( String tableName, Object id )
	{
		if( id == null )
			return null;

		for( AttachedObjectInfo i : attachedObjects )
		{
			if( i.entityConfiguration.tableName.equals( tableName ) && id.equals( i.managedObjectId ) )
				return i;
		}

		return null;
	}

	public AttachedObjectInfo findAttachedObjectByReference( Object o )
	{
		for( AttachedObjectInfo i : attachedObjects )
		{
			if( i.managedObject == o )
				return i;
		}

		return null;
	}
}
