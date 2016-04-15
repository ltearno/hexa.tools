package fr.lteconsulting.hexa.client.ui.search;

import java.util.ArrayList;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import fr.lteconsulting.hexa.client.common.Pair;
import fr.lteconsulting.hexa.client.ui.search.ICriteriaWidget;
import fr.lteconsulting.hexa.client.ui.widget.ListBoxGen;

public class FieldCompCriteriaWidget extends Composite implements ICriteriaWidget
{
	String fieldName;
	ListBoxGen<String> opLb = new ListBoxGen<String>();
	ValueUI valueUI;

	public FieldCompCriteriaWidget( String fieldName, ArrayList<Pair<String, String>> ops, ValueUI valueUI, boolean fReadOnly )
	{
		this.fieldName = fieldName;
		this.valueUI = valueUI;

		HorizontalPanel p = new HorizontalPanel();

		for( Pair<String, String> op : ops )
			opLb.addItem( op.first, op.last );
		p.add( opLb );

		p.add( valueUI );

		initWidget( p );
	}

	@Override
	public void setValue( JSONValue json )
	{
		if( json != null )
		{
			JSONObject obj = json.isObject();
			if( obj != null )
			{
				assert (obj.get( "field" ).isString().stringValue().equals( fieldName ));

				opLb.setSelected( obj.get( "op" ).isString().stringValue() );

				valueUI.setValue( obj.get( "value" ) );
			}
		}
	}

	@Override
	public JSONValue getValue()
	{
		JSONObject obj = new JSONObject();
		obj.put( "field", new JSONString( fieldName ) );
		obj.put( "op", new JSONString( opLb.getSelected() ) );
		obj.put( "value", valueUI.getValue() );
		return obj;
	}

	@Override
	public Widget asWidget()
	{
		return this;
	}
}