package fr.lteconsulting.hexa.client.databinding.tools;

import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent;
import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent.Handler;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.PropertyAdapter;
import fr.lteconsulting.hexa.client.tools.Action2;

/**
 * A class containing only one field : "value" that can be used as a source or
 * target for data binding.
 * 
 * It can be used as a "holder" of a property
 * 
 * @author Arnaud
 *
 * @param <T> Type of the property value
 */
public class Property<T> implements PropertyAdapter
{
	private Object owner;
	private String name;
	private T value;

	public Property( Object owner, String name, T value )
	{
		this.owner = owner;
		this.name = name;
		this.value = value;
	}
	
	public T getValue()
	{
		return value;
	}

	@SuppressWarnings( "unchecked" )
	public void setValue( Object value )
	{
		if( this.value == value )
			return;

		this.value = (T) value;

		NotifyPropertyChangedEvent.notify( owner, name );
	}
	
	public Object register( NotifyPropertyChangedEvent.Handler handler )
	{
		return NotifyPropertyChangedEvent.registerPropertyChangedEvent( owner, name, handler );
	}
	
	public void removeRegistration( Object handlerRegistration )
	{
		NotifyPropertyChangedEvent.removePropertyChangedHandler( handlerRegistration );
	}

	@Override
	public Object registerPropertyChanged( final Action2<PropertyAdapter, Object> callback, final Object cookie )
	{
		return register( new Handler()
		{
			@Override
			public void onNotifyPropertChanged( NotifyPropertyChangedEvent event )
			{
				callback.exec( Property.this, cookie );
			}
		} );
	}

	@Override
	public void removePropertyChangedHandler( Object handlerRegistration )
	{
		removeRegistration(handlerRegistration);
	}
}
