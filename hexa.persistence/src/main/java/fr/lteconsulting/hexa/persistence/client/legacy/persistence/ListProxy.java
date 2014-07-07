package fr.lteconsulting.hexa.persistence.client.legacy.persistence;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import fr.lteconsulting.hexa.persistence.client.legacy.persistence.PersistenceConfiguration.OneToManyFieldConfiguration;

public class ListProxy<T> implements List<T>
{
	EntityManagerImpl em;
	Object owner;
	OneToManyFieldConfiguration fieldConfiguration;

	List<T> data;

	public ListProxy( EntityManagerImpl em, Object owner, OneToManyFieldConfiguration fieldConfiguration )
	{
		this.em = em;
		this.owner = owner;
		this.fieldConfiguration = fieldConfiguration;
	}

	private void ensureData()
	{
		if( data != null )
			return;

		data = em.getCollectionList( owner, fieldConfiguration );
	}

	@Override
	public int size()
	{
		ensureData();
		return data.size();
	}

	@Override
	public boolean isEmpty()
	{
		ensureData();
		return data.isEmpty();
	}

	@Override
	public boolean contains( Object o )
	{
		ensureData();
		return data.contains( o );
	}

	@Override
	public Iterator<T> iterator()
	{
		ensureData();
		return data.iterator();
	}

	@Override
	public Object[] toArray()
	{
		ensureData();
		return data.toArray();
	}

	@Override
	public <E> E[] toArray( E[] a )
	{
		ensureData();
		return data.toArray( a );
	}

	@Override
	public boolean add( T e )
	{
		ensureData();
		return data.add( e );
	}

	@Override
	public boolean remove( Object o )
	{
		// TODO : if orphanRemoval => mark the child entity to be deleted ! And in other methods also
		ensureData();
		return data.remove( o );
	}

	@Override
	public boolean containsAll( Collection<?> c )
	{
		ensureData();
		return data.containsAll( c );
	}

	@Override
	public boolean addAll( Collection<? extends T> c )
	{
		ensureData();
		return data.addAll( c );
	}

	@Override
	public boolean addAll( int index, Collection<? extends T> c )
	{
		ensureData();
		return data.addAll( index, c );
	}

	@Override
	public boolean removeAll( Collection<?> c )
	{
		ensureData();
		return data.removeAll( c );
	}

	@Override
	public boolean retainAll( Collection<?> c )
	{
		ensureData();
		return data.retainAll( c );
	}

	@Override
	public void clear()
	{
		ensureData();
		data.clear();
	}

	@Override
	public boolean equals( Object o )
	{
		ensureData();
		return data.equals( o );
	}

	@Override
	public int hashCode()
	{
		ensureData();
		return data.hashCode();
	}

	@Override
	public T get( int index )
	{
		ensureData();
		return data.get( index );
	}

	@Override
	public T set( int index, T element )
	{
		ensureData();
		return data.set( index, element );
	}

	@Override
	public void add( int index, T element )
	{
		ensureData();
		data.add( index, element );
	}

	@Override
	public T remove( int index )
	{
		ensureData();
		return data.remove( index );
	}

	@Override
	public int indexOf( Object o )
	{
		ensureData();
		return data.indexOf( o );
	}

	@Override
	public int lastIndexOf( Object o )
	{
		ensureData();
		return data.lastIndexOf( o );
	}

	@Override
	public ListIterator<T> listIterator()
	{
		ensureData();
		return data.listIterator();
	}

	@Override
	public ListIterator<T> listIterator( int index )
	{
		ensureData();
		return data.listIterator( index );
	}

	@Override
	public List<T> subList( int fromIndex, int toIndex )
	{
		ensureData();
		return data.subList( fromIndex, toIndex );
	}
}
