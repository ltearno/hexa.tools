package fr.lteconsulting.hexa.client.ui.search;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Widget;

public abstract class StandardNoOpCriteriaMng implements ICriteriaMng
{
	public abstract ValueUI factory( boolean fReadOnly );

	String displayName;
	String fieldName;
	String operator;

	public StandardNoOpCriteriaMng( String displayName, String fieldName )
	{
		this( displayName, fieldName, "eq" );
	}

	public StandardNoOpCriteriaMng( String displayName, String fieldName, String operator )
	{
		this.displayName = displayName;
		this.fieldName = fieldName;
		this.operator = operator;
	}

	@Override
	public String getDisplayName()
	{
		return displayName;
	}

	@Override
	public ICriteriaWidget createCriteriaWidget( JSONValue json, boolean fReadOnly )
	{
		final ValueUI uiValue = factory( fReadOnly );

		ICriteriaWidget cw = new ICriteriaWidget()
		{
			@Override
			public JSONValue getValue()
			{
				JSONObject obj = new JSONObject();
				obj.put( "field", new JSONString( fieldName ) );
				obj.put( "op", new JSONString( operator ) );
				obj.put( "value", uiValue.getValue() );
				return obj;
			}

			@Override
			public Widget asWidget()
			{
				return uiValue.asWidget();
			}

			@Override
			public void setValue( JSONValue json )
			{
				if( json == null )
				{
					uiValue.setValue( null );
					return;
				}

				JSONObject obj = json.isObject();
				if( obj != null )
				{
					assert (obj.get( "field" ).isString().stringValue().equals( fieldName ));

					uiValue.setValue( obj.get( "value" ) );
				}
				else
				{
					uiValue.setValue( null );
					return;
				}
			}
		};

		cw.setValue( json );

		return cw;
	}
}
