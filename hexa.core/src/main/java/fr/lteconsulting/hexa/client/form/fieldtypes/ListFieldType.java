package fr.lteconsulting.hexa.client.form.fieldtypes;

import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.ui.widget.ListBoxEx;

public class ListFieldType extends FieldTypeBase
{
	HashMap<Integer, String> items = new HashMap<Integer, String>();

	public void addItem( int id, String text )
	{
		items.put( id, text );
	}

	@Override
	public Widget getWidget()
	{
		ListBoxEx lb = new ListBoxEx();

		for( Entry<Integer, String> e : items.entrySet() )
			lb.addItem( e.getValue(), e.getKey() );

		lb.setSelected( 0 );

		return lb;
	}

	@Override
	public void setValue( Widget w, JSONValue value )
	{
		((ListBoxEx) w).setSelected( (int) value.isNumber().doubleValue() );
	}

	@Override
	public JSONValue getValue( Widget widget )
	{
		return new JSONNumber( ((ListBoxEx) widget).getSelected() );
	}

	@Override
	protected void installRealHandler( Widget widget )
	{
		((ListBoxEx) widget).setCallback( new ListBoxEx.Callback()
		{
			public void onListBoxExChange( ListBoxEx listBoxEx, Object cookie )
			{
				signalChange( listBoxEx );
			}
		}, null );
	}
}
