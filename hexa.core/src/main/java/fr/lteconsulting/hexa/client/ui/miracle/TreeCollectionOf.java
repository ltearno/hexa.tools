package fr.lteconsulting.hexa.client.ui.miracle;

import java.util.HashMap;

public abstract class TreeCollectionOf<T> implements TreeRefMng<T>
{
	abstract protected int getObjectId( T object );

	abstract protected int getParentObjectId( T object );

	abstract protected void updated( T object );

	abstract protected void deleted( int ref, T object );

	abstract protected void refreshed( Iterable<T> objects );

	class ObjInfo
	{
		T obj;

		T parent;
		T prev;
		T next;
	}

	HashMap<Integer, ObjInfo> objects = new HashMap<Integer, ObjInfo>();
	ObjInfo firstItem = null;
	HashMap<Integer, ObjInfo> firstChilds = new HashMap<Integer, ObjInfo>();

	public int getRef( T object )
	{
		return getObjectId( object );
	}

	@Override
	public T getObject( int ref )
	{
		ObjInfo info = objects.get( ref );
		if( info == null )
			return null;
		return info.obj;
	}

	@Override
	public T getParentObject( T object )
	{
		ObjInfo info = objects.get( getRef( object ) );
		if( info == null )
			return null;
		return info.parent;
	}

	public void clear()
	{
		firstItem = null;
		objects.clear();
		firstChilds.clear();
	}

	final public void refresh( Iterable<T> list )
	{
		clear();

		if( list != null )
		{
			for( T t : list )
				updateRecordInternal( t );
		}

		refreshed( list );
	}

	final public void updateRecord( T b )
	{
		updateRecordInternal( b );
		updated( b );
	}

	private void updateRecordInternal( T b )
	{
		// until we have the info on the parent, we cannot really add the item
		T parent = null;
		int parentBookingId = getParentObjectId( b );
		if( parentBookingId > 0 )
		{
			ObjInfo parentInfo = objects.get( parentBookingId );
			assert (parentInfo != null) : "Cannot add a child if the parent has not been inserted first";
			parent = parentInfo.obj;
		}

		// do we have already an info on b ?
		ObjInfo info = objects.get( getRef( b ) );
		if( info == null )
		{
			// that's a new object here
			info = new ObjInfo();
			info.obj = b;
			info.parent = parent;

			// are we the first child of the parent ?
			ObjInfo firstChild = null;
			if( parent == null )
				firstChild = firstItem;
			else
				firstChild = firstChilds.get( getRef( parent ) );

			if( firstChild == null )
			{
				// yes we are
				if( parent == null )
					firstItem = info;
				else
					firstChilds.put( getRef( parent ), info );
			}
			else
			{
				// no we are not
				info.prev = firstChild.obj;
				info.next = firstChild.next;
				firstChild.next = info.obj;
				if( info.next != null )
					objects.get( getRef( info.next ) ).prev = info.obj;
			}

			objects.put( getRef( b ), info );
		}
		else
		{
			info.obj = b;
		}
	}

	final public void removeRecord( int ref )
	{
		ObjInfo info = objects.get( ref );
		if( info == null )
			return; // nothing to be deleted

		// recursively remove children
		ObjInfo child = firstChilds.remove( ref );
		while( child != null )
		{
			int childRef = getRef( child.obj );
			child = objects.get( child.next );
			removeRecord( childRef );
		}

		// remove the item reference from its parent
		if( info.parent == null )
		{
			// parent is the root

			if( info == firstItem )
			{
				// we are the first of the first !!!
				if( firstItem.next == null )
					firstItem = null;
				else
				{
					firstItem = objects.get( getRef( firstItem.next ) );
					firstItem.prev = null;
				}
			}
			else
			{
				// remove us from the chained list
				ObjInfo prev = null;
				ObjInfo next = null;

				if( info.prev != null )
					prev = objects.get( getRef( info.prev ) );
				if( info.next != null )
					next = objects.get( getRef( info.next ) );

				if( prev != null )
					prev.next = info.next;
				if( next != null )
					next.prev = info.prev;
			}
		}
		else
		{
			int parentRef = getRef( info.parent );

			if( info == firstChilds.get( parentRef ) )
			{
				ObjInfo next = null;
				if( info.next != null )
					next = objects.get( getRef( info.next ) );

				if( next == null )
					firstChilds.remove( parentRef );
				else
				{
					firstChilds.put( parentRef, next );
					next.prev = null;
				}
			}
			else
			{
				// remove us from the chained list
				ObjInfo prev = null;
				ObjInfo next = null;

				if( info.prev != null )
					prev = objects.get( getRef( info.prev ) );
				if( info.next != null )
					next = objects.get( getRef( info.next ) );

				if( prev != null )
					prev.next = info.next;
				if( next != null )
					next.prev = info.prev;
			}
		}

		// effectively remove from maps
		objects.remove( ref );

		// signal the table to remove the corresponding row
		deleted( ref, info.obj );
	}
}
