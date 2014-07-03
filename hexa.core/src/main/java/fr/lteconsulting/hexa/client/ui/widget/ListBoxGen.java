package fr.lteconsulting.hexa.client.ui.widget;

import java.util.HashMap;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;

public class ListBoxGen<T> extends Composite
{
	int nextIdx = 0;
	ListBox base = new ListBox();
	HashMap<Integer, T> items = new HashMap<Integer, T>();
	HashMap<T, Integer> idxs = new HashMap<T, Integer>();

	public ListBoxGen()
	{
		initWidget( base );
	}

	public void clear()
	{
		base.clear();
		items.clear();
		idxs.clear();
	}

	public void addItem( String text, T object )
	{
		base.addItem( text );
		items.put( nextIdx, object );
		idxs.put( object, nextIdx );

		nextIdx++;
	}

	public void setItemText( T object, String text )
	{
		Integer idx = idxs.get( object );
		if( idx == null )
			return;

		base.setItemText( idx, text );
	}

	public void setSelected( T object )
	{
		Integer idx = idxs.get( object );
		if( idx == null )
			return;

		base.setSelectedIndex( idx );
	}

	public T getSelected()
	{
		T res = items.get( base.getSelectedIndex() );
		return res;
	}

	public void addChangeHandler( ChangeHandler handler )
	{
		base.addChangeHandler( handler );
	}

	public void setEnabled( boolean fEnabled )
	{
		base.setEnabled( fEnabled );
	}
}