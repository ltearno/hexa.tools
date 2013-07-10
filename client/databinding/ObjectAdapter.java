package com.hexa.client.databinding;

import com.hexa.client.classinfo.ClassInfo;
import com.hexa.client.classinfo.Clazz;
import com.hexa.client.classinfo.Method;
import com.hexa.client.databinding.DataBinding.DataAdapter;
import com.hexa.client.databinding.NotifyPropertyChangedEvent.Handler;
import com.hexa.client.tools.Action1;

class ObjectAdapter implements DataAdapter, Handler
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
	public void registerPropertyChanged( Action1<DataAdapter> callback )
	{
		this.callback = callback;

		((INotifyPropertyChanged) source).registerPropertyChangedEvent( this );
	}

	@Override
	public Object getValue()
	{
		Clazz<?> s = ClassInfo.Clazz( source.getClass() );

		Method getter = s.getMethod( "get" + sourceProperty.substring( 0, 1 ).toUpperCase() + sourceProperty.substring( 1 ) );
		Object value = getter.call( source, null );

		// Object value = s.getField( sourceProperty ).getValue( source );
		return value;
	}

	@Override
	public void setValue( Object value )
	{
		Clazz<?> d = ClassInfo.Clazz( source.getClass() );

		Method setter = d.getMethod( "set" + sourceProperty.substring( 0, 1 ).toUpperCase() + sourceProperty.substring( 1 ) );
		setter.call( source, new Object[] { value } );
		// d.getField( sourceProperty ).setValue( source, value );
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