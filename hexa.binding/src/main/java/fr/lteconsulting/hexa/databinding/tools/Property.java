package fr.lteconsulting.hexa.databinding.tools;

import fr.lteconsulting.hexa.client.tools.Action2;
import fr.lteconsulting.hexa.databinding.properties.Properties;
import fr.lteconsulting.hexa.databinding.properties.PropertyChangedEvent;
import fr.lteconsulting.hexa.databinding.properties.PropertyChangedHandler;
import fr.lteconsulting.hexa.databinding.propertyadapters.PropertyAdapter;

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

		Properties.notify( owner, name );
	}
	
	public Object register( PropertyChangedHandler handler )
	{
		return Properties.register( owner, name, handler );
	}
	
	public void removeRegistration( Object handlerRegistration )
	{
		Properties.removeHandler( handlerRegistration );
	}

	@Override
	public Object registerPropertyChanged( final Action2<PropertyAdapter, Object> callback, final Object cookie )
	{
		return register( new PropertyChangedHandler()
		{
			@Override
			public void onPropertyChanged(PropertyChangedEvent event)
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
