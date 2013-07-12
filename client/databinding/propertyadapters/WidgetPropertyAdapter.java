package com.hexa.client.databinding.propertyadapters;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.hexa.client.tools.Action2;

@SuppressWarnings( "rawtypes" )
public class WidgetPropertyAdapter implements PropertyAdapter, ValueChangeHandler
{
	HasValue hasValue;

	Action2<PropertyAdapter, Object> callback;
	Object cookie;

	public WidgetPropertyAdapter( HasValue hasValue )
	{
		this.hasValue = hasValue;
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public Object registerPropertyChanged( Action2<PropertyAdapter, Object> callback, Object cookie )
	{
		this.callback = callback;
		this.cookie = cookie;

		return hasValue.addValueChangeHandler( this );
	}

	@Override
	public void onValueChange( ValueChangeEvent event )
	{
		if( callback != null )
			callback.exec( this, cookie );
	}

	@Override
	public Object getValue()
	{
		return hasValue.getValue();
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public void setValue( Object object )
	{
		hasValue.setValue( object, true );
	}

	@Override
	public void removePropertyChangedHandler( Object handler )
	{
		((HandlerRegistration) handler).removeHandler();
	}
}