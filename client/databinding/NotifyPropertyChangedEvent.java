package com.hexa.client.databinding;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class NotifyPropertyChangedEvent extends GwtEvent<NotifyPropertyChangedEvent.Handler>
{
	private static EventBus bus = new SimpleEventBus();

	public interface Handler extends EventHandler
	{
		public void onNotifyPropertChanged( NotifyPropertyChangedEvent event );
	}

	public static HandlerRegistration registerPropertyChangedEvent( NotifyPropertyChangedEvent.Handler handler )
	{
		return bus.addHandler( TYPE, handler );
	}

	public static void notify( Object sender, String propertyName )
	{
		bus.fireEvent( new NotifyPropertyChangedEvent( sender, propertyName ) );
	}

	private final Object sender;
	private final String propertyName;

	private NotifyPropertyChangedEvent( Object sender, String propertyName )
	{
		this.sender = sender;
		this.propertyName = propertyName;
	}

	public Object getSender()
	{
		return sender;
	}

	public String getPropertyName()
	{
		return propertyName;
	}

	@Override
	protected void dispatch( NotifyPropertyChangedEvent.Handler handler )
	{
		handler.onNotifyPropertChanged( this );
	}

	@Override
	public GwtEvent.Type<NotifyPropertyChangedEvent.Handler> getAssociatedType()
	{
		return TYPE;
	}

	public static final GwtEvent.Type<NotifyPropertyChangedEvent.Handler> TYPE = new GwtEvent.Type<NotifyPropertyChangedEvent.Handler>();
}
