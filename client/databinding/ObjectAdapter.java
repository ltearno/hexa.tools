package com.hexa.client.databinding;

import com.google.web.bindery.event.shared.HandlerRegistration;
import com.hexa.client.classinfo.ClazzUtils;
import com.hexa.client.databinding.DataBinding.DataAdapter;
import com.hexa.client.databinding.NotifyPropertyChangedEvent.Handler;
import com.hexa.client.tools.Action1;

public class ObjectAdapter implements DataAdapter, Handler
{
	private final Object source;
	private final String sourceProperty;

	private Action1<DataAdapter> callback;

	private boolean fChanging = false;

	public ObjectAdapter( Object source, String sourceProperty )
	{
		this.source = source;
		this.sourceProperty = sourceProperty;
	}

	@Override
	public Object registerPropertyChanged( Action1<DataAdapter> callback )
	{
		// Can we register ?
		if( ! ( source instanceof INotifyPropertyChanged ) )
			return null;

		this.callback = callback;

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
		return ClazzUtils.GetProperty( source, sourceProperty, true );
	}

	@Override
	public void setValue( Object value )
	{
		ClazzUtils.SetProperty( source, sourceProperty, value, true );
	}

	@Override
	public void onNotifyPropertChanged( NotifyPropertyChangedEvent event )
	{
		if( callback != null && !fChanging && event.getPropertyName().equals( sourceProperty ) )
		{
			fChanging = true;
			callback.exec( this );
			fChanging = false;
		}
	}
}