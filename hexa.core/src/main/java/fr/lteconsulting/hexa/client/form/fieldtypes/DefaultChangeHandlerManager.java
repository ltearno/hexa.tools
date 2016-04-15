package fr.lteconsulting.hexa.client.form.fieldtypes;

import java.util.ArrayList;

import fr.lteconsulting.hexa.client.form.fieldtypes.FieldType.FieldChangeHandler;

public class DefaultChangeHandlerManager implements FieldType.FieldChangeHandlerManager
{
	String currentValue = null;

	ArrayList<Info> handlers = null;

	public DefaultChangeHandlerManager()
	{
	}

	public void pushValue( String value )
	{
		if( currentValue != null && value.equals( currentValue ) )
			return;

		currentValue = value;

		signalChange();
	}

	public void signalChange()
	{
		currentValue = null;
		if( handlers != null )
		{
			for( Info info : handlers )
				info.handler.onFieldChange( info.cookie );
		}
	}

	private class Info
	{
		FieldChangeHandler handler;
		Object cookie;

		Info( FieldChangeHandler handler, Object cookie )
		{
			this.handler = handler;
			this.cookie = cookie;
		}
	}

	@Override
	public Object addChangeHandler( FieldChangeHandler handler, Object cookie )
	{
		if( handlers == null )
			handlers = new ArrayList<DefaultChangeHandlerManager.Info>();

		Info info = new Info( handler, cookie );
		handlers.add( info );
		return info;
	}

	@Override
	public void removeChangeHandler( Object registration )
	{
		handlers.remove( (Info) registration );
	}
}
