package com.hexa.client.databinding;

import com.hexa.client.classinfo.ClassInfo;
import com.hexa.client.classinfo.Clazz;
import com.hexa.client.databinding.DataBinding.DataAdapter;
import com.hexa.client.databinding.NotifyPropertyChangedEvent.Handler;
import com.hexa.client.tools.Action1;

class ObjectAdapter implements DataAdapter, Handler
{
	private final Object source;
	private final String sourceProperty;

	private Action1<DataAdapter> callback;

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
		Object value = s.getField( sourceProperty ).getValue( source );
		return value;
	}

	@Override
	public void setValue( Object value )
	{
		Clazz<?> d = ClassInfo.Clazz( source.getClass() );
		d.getField( sourceProperty ).setValue( source, value );
	}

	@Override
	public void onNotifyPropertChanged( NotifyPropertyChangedEvent event )
	{
		if( callback != null && event.getPropertyName().equals( sourceProperty ) )
			callback.exec( this );
	}
}