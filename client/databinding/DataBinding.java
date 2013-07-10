package com.hexa.client.databinding;

public class DataBinding implements NotifyPropertyChangedEvent.Handler
{
	private boolean fActivated;

	private final Object source;
	private final String sourceProperty;
	private final Object destination;
	private final String destinationProperty;
	private final Mode bindingMode;

	public DataBinding( Object source, String sourceProperty, Object destination, String destinationProperty, Mode bindingMode )
	{
		this.source = source;
		this.sourceProperty = sourceProperty;
		this.destination = destination;
		this.destinationProperty = destinationProperty;
		this.bindingMode = bindingMode;

		((INotifyPropertyChanged) source).registerPropertyChangedEvent( this );
	}

	public void activate()
	{
		fActivated = true;
	}

	@Override
	public void onNotifyPropertChanged( NotifyPropertyChangedEvent event )
	{
		if( event.getSender() != source )
			return;

		if( !event.getPropertyName().equals( sourceProperty ) )
			return;

		// Object value = getPropertyValue( source, sourceProperty );
		// setPropertyValue( destination, destinationProperty, value );
	}
}
