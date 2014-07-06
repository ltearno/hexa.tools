package fr.lteconsulting.hexa.client.databinding.tools;

import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent;

/**
 * A class containing only one field : "content" that can be used
 * as a source or target for data binding.
 * 
 * It can be used as a "holder" of a property
 * 
 * @author Arnaud
 *
 * @param <T>
 */
public class DynamicContainer<T>
{
	private T content;

	public DynamicContainer()
	{
		this( null );
	}

	public DynamicContainer( T content )
	{
		this.content = content;
	}

	public void setContent( T content )
	{
		this.content = content;
		
		NotifyPropertyChangedEvent.notify( this, "content" );
	}

	public T getContent()
	{
		return content;
	}
}
