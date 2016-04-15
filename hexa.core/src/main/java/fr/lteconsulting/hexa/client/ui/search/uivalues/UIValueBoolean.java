package fr.lteconsulting.hexa.client.ui.search.uivalues;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;

import fr.lteconsulting.hexa.client.ui.search.ICriteriaMng;
import fr.lteconsulting.hexa.client.ui.search.StandardNoOpCriteriaMng;
import fr.lteconsulting.hexa.client.ui.search.ValueUI;

public class UIValueBoolean extends Composite implements ValueUI
{
	CheckBox cb = new CheckBox();

	private UIValueBoolean( boolean fReadOnly )
	{
		cb.setEnabled( !fReadOnly );
		initWidget( cb );
	}

	@Override
	public void setValue( JSONValue json )
	{
		if( json == null )
			cb.setValue( false );
		else
			cb.setValue( ((int) json.isNumber().doubleValue()) > 0 ? true : false );
	}

	@Override
	public JSONValue getValue()
	{
		try
		{
			return new JSONNumber( cb.getValue() ? 1 : 0 );
		}
		catch( Exception e )
		{
			return new JSONNumber( 0 );
		}
	}

	public static ICriteriaMng createCriteriaMng( String displayName, String fieldName )
	{
		return new StandardNoOpCriteriaMng( displayName, fieldName )
		{
			@Override
			public ValueUI factory( boolean fReadOnly )
			{
				return new UIValueBoolean( fReadOnly );
			}
		};
	}
}