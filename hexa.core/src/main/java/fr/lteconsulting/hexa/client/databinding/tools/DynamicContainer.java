package fr.lteconsulting.hexa.client.databinding.tools;

import fr.lteconsulting.hexa.client.databinding.INotifyPropertyChanged;
import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent;

public class DynamicContainer<T> implements INotifyPropertyChanged
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

	@Override
	public Object registerPropertyChangedEvent( String propertyName, NotifyPropertyChangedEvent.Handler handler )
	{
		return NotifyPropertyChangedEvent.registerPropertyChangedEvent( this, propertyName, handler );
	}

	@Override
	public void removePropertyChangedHandler( Object handlerRegistration )
	{
		NotifyPropertyChangedEvent.removePropertyChangedHandler( handlerRegistration );
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
