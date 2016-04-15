package fr.lteconsulting.hexa.client.ui.search;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.ui.search.SearchMng.SearchInstance;

public class StandardPointingFieldCriteriaMng implements ICriteriaMng
{
	String displayName;
	SearchMng searchMng;
	String pointingField;
	String pointedTable;
	String pointedField;

	public StandardPointingFieldCriteriaMng( String displayName, SearchMng searchMng, String pointingField, String pointedTable, String pointedField )
	{
		this.displayName = displayName;
		this.searchMng = searchMng;
		this.pointingField = pointingField;
		this.pointedTable = pointedTable;
		this.pointedField = pointedField;
	}

	@Override
	public String getDisplayName()
	{
		return displayName;
	}

	@Override
	public ICriteriaWidget createCriteriaWidget( JSONValue json, boolean fReadOnly )
	{
		JSONObject obj = null;
		if( json != null )
			obj = json.isObject();
		JSONValue subValue = null;
		if( obj != null )
			subValue = obj.get( "sub" );

		final SearchInstance inst = searchMng.createSearchWidget( fReadOnly );
		inst.setValue( subValue );

		return new ICriteriaWidget()
		{
			@Override
			public JSONValue getValue()
			{
				JSONObject obj = new JSONObject();
				obj.put( "op", new JSONString( "->" ) );
				obj.put( "field", new JSONString( pointingField ) );
				if( pointedTable != null )
					obj.put( "pointed_table", new JSONString( pointedTable ) );
				if( pointedField != null )
					obj.put( "pointed_field", new JSONString( pointedField ) );
				obj.put( "sub", inst.getValue() );

				return obj;
			}

			@Override
			public Widget asWidget()
			{
				return inst.asWidget();
			}

			@Override
			public void setValue( JSONValue json )
			{
				inst.setValue( json );
			}
		};
	}

}
