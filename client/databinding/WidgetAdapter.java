package com.hexa.client.databinding;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HasValue;
import com.hexa.client.databinding.DataBinding.DataAdapter;
import com.hexa.client.tools.Action1;

@SuppressWarnings( "rawtypes" ) class WidgetAdapter implements DataAdapter, ValueChangeHandler
{
	Object widget;

	Action1<DataAdapter> callback;

	public WidgetAdapter( Object widget )
	{
		this.widget = widget;

		if( !(widget instanceof HasValueChangeHandlers) )
			throw new RuntimeException( "Should have HasValueChangeHandlers interface implemented" );

		if( !(widget instanceof HasValue) )
			throw new RuntimeException( "Should have HasValue interface implemented" );
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public void registerPropertyChanged( Action1<DataAdapter> callback )
	{
		this.callback = callback;

		((HasValueChangeHandlers) widget).addValueChangeHandler( this );
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
		return ((HasValue) widget).getValue();
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public void setValue( Object object )
	{
		((HasValue) widget).setValue( object, false );
	}
}