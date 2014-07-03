package fr.lteconsulting.hexa.client.ui.treetable;

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

	HashMap<Integer, Row> elements = new HashMap<Integer, Row>();
	HashMap<Integer, Integer> versions = new HashMap<Integer, Integer>();
	int currentVersion = 0;

	ArrayList<Row> elementsAdded = new ArrayList<Row>();

	ArrayList<Integer> elementsToRemove = new ArrayList<Integer>();
	TreeTable table = null;
	Timer timer = new Timer()
	{
		@Override
		public void run()
		{
			if( elementsToRemove.size() > 0 )
			{
				int toRemove = elementsToRemove.remove( 0 );
				_remove( toRemove );

				timer.schedule( 10 );
			}
		}
	};

	public TreeTableElemMng( TreeTable table, TreeTableElemMngCallback<T> callback )
	{
		this.table = table;
		this.callback = callback;
	}

	public Row addOrUpdateItemInCurrentVersion( T element, Row parentItem )
	{
		int identifier = callback.getElementIdentifier( element );

		versions.put( identifier, currentVersion );

		Row item = elements.get( identifier );
		if( item != null )
			return item;

		item = table.addRow( parentItem );
		elements.put( identifier, item );
		item.highLite();

		return item;
	}

	public void deleteItemInCurrentVersion( T element, TreeTable table )
	{
		int identifier = callback.getElementIdentifier( element );
		versions.remove( identifier );

		Row item = elements.get( identifier );
		if( item == null )
			return;

		item.remove();
	}

	public Row getItem( T element, Row parentItem )
	{
		int identifier = callback.getElementIdentifier( element );

		versions.put( identifier, currentVersion + 1 );

		Row item = elements.get( identifier );
		if( item != null )
			return item;

		item = table.addRow( parentItem );
		elements.put( identifier, item );
		elementsAdded.add( item );

		return item;
	}

	public Row remove( T element )
	{
		int identifier = callback.getElementIdentifier( element );

		return _remove( identifier );
	}

	public Row remove( int elementIdentifier )
	{
		return _remove( elementIdentifier );
	}

	public Row _remove( int identifier )
	{
		versions.remove( identifier );

		Row item = elements.get( identifier );
		if( item == null )
			return null;

		elements.remove( identifier );
		item.remove();

		return item;
	}

	public void commitVersion()
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
			timer.schedule( 10 );
	}
}
