package com.hexa.client.databinding.propertyadapters;

import com.google.web.bindery.event.shared.HandlerRegistration;
import com.hexa.client.databinding.INotifyPropertyChanged;
import com.hexa.client.databinding.NotifyPropertyChangedEvent;
import com.hexa.client.databinding.NotifyPropertyChangedEvent.Handler;
import com.hexa.client.tools.Action2;

public class ObjectPropertyAdapter implements PropertyAdapter, Handler
{
	private final Object source;
	private final String sourceProperty;

	private Action2<PropertyAdapter, Object> callback;
	private Object cookie;

	private boolean fChanging = false;

	public ObjectPropertyAdapter( Object source, String sourceProperty )
	{
		this.source = source;
		this.sourceProperty = sourceProperty;
	}

	@Override
	public Object registerPropertyChanged( Action2<PropertyAdapter, Object> callback, Object cookie )
	{
		// Can we register ?
		if( ! ( source instanceof INotifyPropertyChanged ) )
			return null;

		this.callback = callback;
		this.cookie = cookie;

		return ((INotifyPropertyChanged) source).registerPropertyChangedEvent( this );
	}

	@Override
	public void removePropertyChangedHandler( Object handler )
	{
		if( handler != null )
			((HandlerRegistration)handler).removeHandler();
	}

	@Override
	public Object getValue()
	{
		return ObjectPropertiesUtils.GetProperty( source, sourceProperty, true );
	}

	@Override
	public void setValue( Object value )
	{
		ObjectPropertiesUtils.SetProperty( source, sourceProperty, value, true );
	}

	@Override
	public void onNotifyPropertChanged( NotifyPropertyChangedEvent event )
	{
		if( callback != null && !fChanging && event.getPropertyName().equals( sourceProperty ) )
		{
			fChanging = true;
			callback.exec( this, cookie );
			fChanging = false;
		}
	}
}