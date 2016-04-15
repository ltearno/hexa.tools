package fr.lteconsulting.hexa.client.ui.search;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.lteconsulting.hexa.client.ui.search.SearchMng.SearchInstance;

public class StandardPointedByCriteriaMng implements ICriteriaMng
{
	String displayName;
	SearchMng searchMng;
	String pointingTable;

	public StandardPointedByCriteriaMng( String displayName, SearchMng searchMng, String pointingTable )
	{
		this.displayName = displayName;
		this.searchMng = searchMng;
		this.pointingTable = pointingTable;
	}

	@Override
	public String getDisplayName()
	{
		return displayName;
	}

	@Override
	public ICriteriaWidget createCriteriaWidget( JSONValue json, boolean fReadOnly )
	{
		WidgetImpl impl = new WidgetImpl( fReadOnly );
		impl.setValue( json );
		return impl;
	}

	class WidgetImpl extends Composite implements ICriteriaWidget
	{
		VerticalPanel panel = new VerticalPanel();

		SearchInstance inst;

		WidgetImpl( boolean fReadOnly )
		{
			inst = searchMng.createSearchWidget( fReadOnly );

			panel.add( inst );

			initWidget( panel );
		}

		@Override
		public JSONValue getValue()
		{
			JSONObject obj = new JSONObject();
			obj.put( "op", new JSONString( "<-" ) );
			obj.put( "pointing_table", new JSONString( pointingTable ) );
			obj.put( "sub", inst.getValue() );

			return obj;
		}

		@Override
		public void setValue( JSONValue json )
		{
			if( json == null )
				return;
			inst.setValue( json );
		}
	}
}
