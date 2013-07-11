package com.hexa.client.databinding;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.hexa.client.databinding.DataBinding.DataAdapter;
import com.hexa.client.tools.Action1;

@SuppressWarnings( "rawtypes" )
class WidgetAdapter implements DataAdapter, ValueChangeHandler
{
	HasValue widget;

	Action1<DataAdapter> callback;

	public WidgetAdapter( HasValue widget )
	{
		this.widget = widget;
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public Object registerPropertyChanged( Action1<DataAdapter> callback )
	{
		this.callback = callback;

		return widget.addValueChangeHandler( this );
	}

	@Override
	public void onValueChange( ValueChangeEvent event )
	{
		if( callback != null )
			callback.exec( this );
	}

	@Override
	public Object getValue()
	{
		return widget.getValue();
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public void setValue( Object object )
	{
		widget.setValue( object, true );
	}

	@Override
	public void removePropertyChangedHandler( Object handler )
	{
		((HandlerRegistration) handler).removeHandler();
	}
}