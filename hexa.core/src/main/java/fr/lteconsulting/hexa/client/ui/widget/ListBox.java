package fr.lteconsulting.hexa.client.ui.widget;

import java.util.Collection;
import java.util.HashMap;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;

/**
 * A ListBox that can handle a generic T type. It is more practical than the
 * text-only GWT ListBox. But it is only a very light thin layer above it.
 * 
 * @author Arnaud Tournier (c) LTE Consulting - 2015 http://www.lteconsulting.fr
 *
 * @param <T>
 */
public class ListBox<T> extends Composite implements HasValue<T>
{
	private HashMap<Integer, T> items = new HashMap<>();
	private com.google.gwt.user.client.ui.ListBox list = new com.google.gwt.user.client.ui.ListBox();
	private boolean registered = false;

	public ListBox()
	{
		initWidget( list );
	}
	
	public void addAll( Collection<T> items)
	{
		for( T item : items )
			addItem( item );
	}

	public void addItem( T item )
	{
		addItem( ""+item, item );
	}

	public void addItem( String text, T item )
	{
		int hashCode = item.hashCode();
		list.addItem( text, String.valueOf( hashCode ) );
		items.put( hashCode, item );
	}

	public void removeItem( T item )
	{
		if( item == null )
			return;

		int index = getItemIndex( item );
		if( index >= 0 )
			list.removeItem( index );

		int hashCode = item.hashCode();
		items.remove( hashCode );
	}

	public int getItemIndex( T value )
	{
		if( value == null )
			return -1;

		int hashCode = value.hashCode();
		int count = list.getItemCount();
		for( int i = 0; i < count; i++ )
		{
			if( Integer.parseInt( list.getValue( i ) ) == hashCode )
				return i;
		}

		return -1;
	}

	@Override
	public HandlerRegistration addValueChangeHandler( ValueChangeHandler<T> handler )
	{
		if( !registered )
		{
			registered = true;
			list.addChangeHandler( new ChangeHandler()
			{
				@Override
				public void onChange( ChangeEvent event )
				{
					ValueChangeEvent.fire( ListBox.this, getValue() );
				}
			} );
		}
		return addHandler( handler, ValueChangeEvent.getType() );
	}

	@Override
	public T getValue()
	{
		String value = list.getSelectedValue();
		if( value == null )
			return null;

		int hashCode = Integer.parseInt( value );

		T item = items.get( hashCode );

		return item;
	}

	@Override
	public void setValue( T value )
	{
		setValue( value, true );
	}

	@Override
	public void setValue( T value, boolean fireEvents )
	{
		int toSelect = getItemIndex( value );
		if( toSelect == list.getSelectedIndex() )
			return;
		
		list.setSelectedIndex( toSelect );
		ValueChangeEvent.fire( this, value );
	}
}
