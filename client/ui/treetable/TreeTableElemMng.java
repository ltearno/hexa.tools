package com.hexa.client.ui.treetable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gwt.user.client.Timer;

public class TreeTableElemMng<T>
{
	public interface TreeTableElemMngCallback<T>
	{
		int getElementIdentifier( T element );
	}

	TreeTableElemMngCallback<T> callback;

	HashMap<Integer, Object> elements = new HashMap<Integer, Object>();
	HashMap<Integer, Integer> versions = new HashMap<Integer, Integer>();
	int currentVersion = 0;

	ArrayList<Object> elementsAdded = new ArrayList<Object>();

	ArrayList<Integer> elementsToRemove = new ArrayList<Integer>();
	TreeTable hackTable = null;
	Timer timer = new Timer()
	{
		@Override
		public void run()
		{
			if( elementsToRemove.size() > 0 )
			{
				int toRemove = elementsToRemove.remove( 0 );
				_remove( toRemove, hackTable );

				timer.schedule( 10 );
			}

			/*
			 * if( elementsAdded.size() > 0 ) { if( elementsAdded.size() <=
			 * elements.size()/2 ) { for( Object element : elementsAdded )
			 * hackTable.highLite( element ); } elementsAdded.clear(); }
			 */
		}
	};

	public TreeTableElemMng( TreeTableElemMngCallback<T> callback )
	{
		this.callback = callback;
	}

	public Object addOrUpdateItemInCurrentVersion( T element, TreeTable table, Object parentItem )
	{
		int identifier = callback.getElementIdentifier( element );

		versions.put( identifier, currentVersion );

		Object item = elements.get( identifier );
		if( item != null )
		{
			// ((TreeTableBase.Item)item).highLite();
			return item;
		}

		item = table.addRow( parentItem );
		elements.put( identifier, item );
		((TreeTable.Row) item).highLite();

		return item;
	}

	public void deleteItemInCurrentVersion( T element, TreeTable table )
	{
		int identifier = callback.getElementIdentifier( element );
		versions.remove( identifier );

		Object item = elements.get( identifier );
		if( item == null )
			return;
		((TreeTable.Row) item).remove();
	}

	public Object getItem( T element, TreeTable table, Object parentItem )
	{
		int identifier = callback.getElementIdentifier( element );

		versions.put( identifier, currentVersion + 1 );

		Object item = elements.get( identifier );
		if( item != null )
			return item;

		item = table.addRow( parentItem );
		elements.put( identifier, item );
		elementsAdded.add( item );

		return item;
	}

	public Object remove( T element, TreeTable table )
	{
		int identifier = callback.getElementIdentifier( element );

		return _remove( identifier, table );
	}

	public Object remove( int elementIdentifier, TreeTable table )
	{
		return _remove( elementIdentifier, table );
	}

	public Object _remove( int identifier, TreeTable table )
	{
		versions.remove( identifier );

		Object item = elements.get( identifier );
		if( item == null )
			return null;

		elements.remove( identifier );
		table.removeRow( item );

		return item;
	}

	public void commitVersion( TreeTable table )
	{
		currentVersion++;
		boolean fRearm = false;

		for( Iterator<Entry<Integer, Integer>> it = versions.entrySet().iterator(); it.hasNext(); )
		{
			Entry<Integer, Integer> entry = it.next();
			if( entry.getValue() < currentVersion )
			{
				fRearm = true;
				elementsToRemove.add( entry.getKey() );
			}
		}

		if( fRearm || elementsAdded.size() > 0 )
		{
			hackTable = table;
			timer.schedule( 10 );
		}
	}
}
