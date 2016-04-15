package fr.lteconsulting.hexa.client.form.fieldtypes;

import java.util.HashMap;

import com.google.gwt.user.client.ui.Widget;

public abstract class FieldTypeBase implements FieldType
{
	protected abstract void installRealHandler( Widget widget );

	protected HashMap<Widget, DefaultChangeHandlerManager> handlerManagers = null;

	protected void pushCurrentValue( Widget widget, String value )
	{
		if( handlerManagers == null )
			return;

		DefaultChangeHandlerManager handlerManager = handlerManagers.get( widget );
		if( handlerManager == null )
			return;

		handlerManager.pushValue( value );
	}

	protected void signalChange( Widget widget )
	{
		if( handlerManagers == null )
			return;

		DefaultChangeHandlerManager handlerManager = handlerManagers.get( widget );
		if( handlerManager == null )
			return;

		handlerManager.signalChange();
	}

	@Override
	final public FieldChangeHandlerManager getHandlerManager( Widget widget )
	{
		if( handlerManagers == null )
			handlerManagers = new HashMap<Widget, DefaultChangeHandlerManager>();

		DefaultChangeHandlerManager mng = handlerManagers.get( widget );
		if( mng == null )
		{
			// tells implementation that it should link its events to the change
			// handler manager
			installRealHandler( widget );

			mng = new DefaultChangeHandlerManager();
			handlerManagers.put( widget, mng );
		}

		return mng;
	}
}
