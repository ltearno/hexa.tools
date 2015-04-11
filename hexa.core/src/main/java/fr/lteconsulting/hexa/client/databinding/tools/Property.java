package fr.lteconsulting.hexa.client.databinding.tools;

import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent;

/**
 * A class containing only one field : "value" that can be used as a source or
 * target for data binding.
 * 
 * It can be used as a "holder" of a property
 * 
 * @author Arnaud
 *
 * @param <T>
 */
public class Property<T>
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

	public void setValue( T value )
	{
		if( this.value == value )
			return;

		this.value = value;

		NotifyPropertyChangedEvent.notify( owner, name );
	}
}
