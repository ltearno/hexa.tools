package fr.lteconsulting.hexa.client.ui.chart.raphael;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import fr.lteconsulting.hexa.client.ui.chart.raphael.Raphael.Shape;

public class ShapeCollection implements Iterable<Widget>
{

	private class WidgetIterator implements Iterator<Widget>
	{

		private int index = -1;

		public boolean hasNext()
		{
			return index < (size - 1);
		}

		public Widget next()
		{
			if( index >= size )
			{
				throw new NoSuchElementException();
			}
			return array[++index];
		}

		public void remove()
		{
			if( (index < 0) || (index >= size) )
			{
				throw new IllegalStateException();
			}
			parent.remove( array[index--] );
		}
	}

	private static final int INITIAL_SIZE = 4;

	private Shape[] array;
	private HasWidgets parent;
	private int size;

	/**
	 * Constructs a new widget collection.
	 * 
	 * @param parent
	 *            the container whose {@link HasWidgets#remove(Widget)} will be
	 *            delegated to by the iterator's {@link Iterator#remove()}
	 *            method.
	 */
	public ShapeCollection( HasWidgets parent )
	{
		this.parent = parent;
		array = new Shape[INITIAL_SIZE];
	}

	/**
	 * Adds a widget to the end of this collection.
	 * 
	 * @param w
	 *            the widget to be added
	 */
	public void add( Shape w )
	{
		insert( w, size );
	}

	/**
	 * Determines whether a given widget is contained in this collection.
	 * 
	 * @param w
	 *            the widget to be searched for
	 * @return <code>true</code> if the widget is present
	 */
	public boolean contains( Widget w )
	{
		return(indexOf( w ) != -1);
	}

	/**
	 * Gets the widget at the given index.
	 * 
	 * @param index
	 *            the index to be retrieved
	 * @return the widget at the specified index
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range
	 */
	public Widget get( int index )
	{
		if( (index < 0) || (index >= size) )
		{
			throw new IndexOutOfBoundsException();
		}

		return array[index];
	}

	public Shape getShape( Widget w )
	{
		int index = indexOf( w );
		if( (index < 0) || (index >= size) )
		{
			throw new IndexOutOfBoundsException();
		}

		return array[index];
	}

	/**
	 * Gets the index of the specified index.
	 * 
	 * @param w
	 *            the widget to be found
	 * @return the index of the specified widget, or <code>-1</code> if it is
	 *         not found
	 */
	public int indexOf( Widget w )
	{
		for( int i = 0; i < size; ++i )
		{
			if( array[i] == w )
			{
				return i;
			}
		}

		return -1;
	}

	/**
	 * Inserts a widget before the specified index.
	 * 
	 * @param w
	 *            the widget to be inserted
	 * @param beforeIndex
	 *            the index before which the widget will be inserted
	 * @throws IndexOutOfBoundsException
	 *             if <code>beforeIndex</code> is out of range
	 */
	public void insert( Shape w, int beforeIndex )
	{
		if( (beforeIndex < 0) || (beforeIndex > size) )
		{
			throw new IndexOutOfBoundsException();
		}

		// Realloc array if necessary (doubling).
		if( size == array.length )
		{
			Shape[] newArray = new Shape[array.length * 2];
			for( int i = 0; i < array.length; ++i )
			{
				newArray[i] = array[i];
			}
			array = newArray;
		}

		++size;

		// Move all widgets after 'beforeIndex' back a slot.
		for( int i = size - 1; i > beforeIndex; --i )
		{
			array[i] = array[i - 1];
		}

		array[beforeIndex] = w;
	}

	/**
	 * Gets an iterator on this widget collection. This iterator is guaranteed
	 * to implement remove() in terms of its containing {@link HasWidgets}.
	 * 
	 * @return an iterator
	 */
	public Iterator<Widget> iterator()
	{
		return new WidgetIterator();
	}

	/**
	 * Removes the widget at the specified index.
	 * 
	 * @param index
	 *            the index of the widget to be removed
	 * @throws IndexOutOfBoundsException
	 *             if <code>index</code> is out of range
	 */
	public void remove( int index )
	{
		if( (index < 0) || (index >= size) )
		{
			throw new IndexOutOfBoundsException();
		}

		--size;
		for( int i = index; i < size; ++i )
		{
			array[i] = array[i + 1];
		}

		array[size] = null;
	}

	/**
	 * Removes the specified widget.
	 * 
	 * @param w
	 *            the widget to be removed
	 * @throws NoSuchElementException
	 *             if the widget is not present
	 */
	public void remove( Widget w )
	{
		int index = indexOf( w );
		if( index == -1 )
		{
			throw new NoSuchElementException();
		}

		remove( index );
	}

	/**
	 * Gets the number of widgets in this collection.
	 * 
	 * @return the number of widgets
	 */
	public int size()
	{
		return size;
	}
}
