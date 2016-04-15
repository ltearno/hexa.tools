package fr.lteconsulting.hexa.client.form.fieldtypes;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.common.Pair;
import fr.lteconsulting.hexa.client.form.marshalls.Marshalls;

public class ListStringFieldType extends FieldTypeBase
{
	ArrayList<Pair<String, String>> items = new ArrayList<Pair<String, String>>();

	public void addItem( String id, String text )
	{
		items.add( new Pair<String, String>( id, text ) );
	}

	public Widget getWidget()
	{
		ListBox lb = new ListBox();

		for( Pair<String, String> e : items )
			lb.addItem( e.last, e.first );

		return lb;
	}

	public void setValue( Widget w, JSONValue value )
	{
		String valueReal = Marshalls.string.get( value );
		for( int i = 0; i < items.size(); i++ )
		{
			if( items.get( i ).first.equals( valueReal ) )
			{
				((ListBox) w).setSelectedIndex( i );
				return;
			}
		}

		((ListBox) w).setSelectedIndex( 0 );
	}

	public JSONValue getValue( Widget widget )
	{
		String value = items.get( ((ListBox) widget).getSelectedIndex() ).first;
		return Marshalls.string.get( value );
	}

	@Override
	protected void installRealHandler( Widget widget )
	{
		((ListBox) widget).addChangeHandler( new com.google.gwt.event.dom.client.ChangeHandler()
		{
			public void onChange( ChangeEvent event )
			{
				signalChange( (ListBox) event.getSource() );
			}
		} );
	}
}
