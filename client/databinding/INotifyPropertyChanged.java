package com.hexa.client.databinding;

import com.google.web.bindery.event.shared.HandlerRegistration;

public interface INotifyPropertyChanged
{
	// registering notify events
	HandlerRegistration registerPropertyChangedEvent( NotifyPropertyChangedEvent.Handler handler );
}
