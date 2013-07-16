package com.hexa.client.databinding;


public interface INotifyPropertyChanged
{
	// registering notify events
	Object registerPropertyChangedEvent( String propertyName, NotifyPropertyChangedEvent.Handler handler );
	void removePropertyChangedHandler( Object handlerRegistration );
}
