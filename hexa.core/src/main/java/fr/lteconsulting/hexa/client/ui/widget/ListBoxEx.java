package fr.lteconsulting.hexa.client.ui.widget;

import java.util.HashMap;

import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;

public class ListBoxEx extends Composite implements ChangeHandler
{
	public interface Callback
	{
		void onListBoxExChange( ListBoxEx listBoxEx, Object cookie );
	}

	private Callback callback = null;
	private Object cookie = null;

	private ListBox listBox = new ListBox();
	int nextIdx = 0;

	private HashMap<Integer, Integer> ids = new HashMap<Integer, Integer>();

	public ListBoxEx()
	{
		initWidget( listBox );

		listBox.addChangeHandler( this );
	}

	public void setCallback( Callback callback, Object cookie )
	{
		this.callback = callback;
		this.cookie = cookie;
	}

	public void clear()
	{
		listBox.clear();
		nextIdx = 0;
		ids.clear();
	}

	public void addItem( String text, int id )
	{
		listBox.addItem( text, String.valueOf( id ) );
		ids.put( id, nextIdx++ );
	}

	public void setItemText( int id, String text )
	{
		listBox.setItemText( ids.get( id ), text );
	}

	public int getSelected()
	{
		return Integer.parseInt( listBox.getValue( listBox.getSelectedIndex() ) );
	}

	public void setSelected( int id )
	{
		Integer idx = ids.get( id );
		if( idx == null )
			return;
		listBox.setSelectedIndex( idx );
	}

	@Override
	public void onChange( ChangeEvent event )
	{
		if( callback == null )
			return;
		callback.onListBoxExChange( this, cookie );
	}

	public HandlerRegistration addBlurHandler( BlurHandler handler )
	{
		return listBox.addBlurHandler( handler );
	}

	public HandlerRegistration addChangeHandler( ChangeHandler handler )
	{
		return listBox.addChangeHandler( handler );
	}

	public void setFocus( boolean fFocused )
	{
		listBox.setFocus( fFocused );
	}

	public void setEnabled( boolean fEnabled )
	{
		listBox.setEnabled( fEnabled );
	}
}
