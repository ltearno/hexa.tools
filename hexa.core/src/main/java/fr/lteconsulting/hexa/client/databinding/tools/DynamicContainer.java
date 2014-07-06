package fr.lteconsulting.hexa.client.databinding.tools;

import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent;

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
