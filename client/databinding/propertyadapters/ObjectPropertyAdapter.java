package com.hexa.client.databinding.propertyadapters;

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

	public ObjectPropertyAdapter( Object source, String sourceProperty )
	{
		this.source = source;
		this.sourceProperty = sourceProperty;
	}

	@Override
	public Object registerPropertyChanged( Action2<PropertyAdapter, Object> callback, Object cookie )
	{
		// Can we register ?
		if( !(source instanceof INotifyPropertyChanged) )
			return null;

		this.callback = callback;
		this.cookie = cookie;

		return ((INotifyPropertyChanged) source).registerPropertyChangedEvent( sourceProperty, this );
	}

	@Override
	public void removePropertyChangedHandler( Object registration )
	{
		if( !(source instanceof INotifyPropertyChanged) )
			return;

		((INotifyPropertyChanged) source).removePropertyChangedHandler( registration );
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
		if( callback == null )
			return;

		callback.exec( this, cookie );
	}
}