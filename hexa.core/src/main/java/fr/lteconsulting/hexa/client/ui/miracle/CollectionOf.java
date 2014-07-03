package fr.lteconsulting.hexa.client.ui.miracle;

import java.util.HashMap;

public abstract class CollectionOf<T> implements RefMng<T>
{
	abstract protected int getObjectId( T object );

	abstract protected void updated( T object );

	abstract protected void deleted( int ref, T object );

	abstract protected void refreshed( Iterable<T> objects );

	private HashMap<Integer, T> map = new HashMap<Integer, T>();

	@Override
	final public int getRef( T object )
	{
		return getObjectId( object );
	}

	@Override
	final public T getObject( int ref )
	{
		return map.get( ref );
	}

	final public void add( T object )
	{
		map.put( getObjectId( object ), object );

		updated( getObjectId( object ) );
	}

	final public void refresh( Iterable<T> list )
	{
		map.clear();

		if( list != null )
		{
			for( T t : list )
				add( t );
		}

		refreshed( map.values() );
	}

	final public void update( T object )
	{
		map.put( getObjectId( object ), object );

		updated( getObjectId( object ) );
	}

	final public void updated( int ref )
	{
		T object = getObject( ref );

		updated( object );
	}

	final public void delete( T object )
	{
		delete( getRef( object ) );
	}

	final public void delete( int ref )
	{
		T object = map.remove( ref );

		deleted( ref, object );
	}

	final public Iterable<T> getList()
	{
		return map.values();
	}

	final public int size()
	{
		return map.size();
	}
}