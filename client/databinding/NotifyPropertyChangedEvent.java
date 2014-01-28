package com.hexa.client.databinding;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class NotifyPropertyChangedEvent extends GwtEvent<NotifyPropertyChangedEvent.Handler>
{
	private static final HashMap<Object, HashMap<String, ArrayList<NotifyPropertyChangedEvent.Handler>>> handlers = new HashMap<Object, HashMap<String, ArrayList<Handler>>>();

	public interface Handler extends EventHandler
	{
		public void onNotifyPropertChanged( NotifyPropertyChangedEvent event );
	}

	public static Object registerPropertyChangedEvent( Object source, String propertyName, NotifyPropertyChangedEvent.Handler handler )
	{
		HashMap<String, ArrayList<NotifyPropertyChangedEvent.Handler>> handlersMap = handlers.get( source );
		if( handlersMap == null )
		{
			handlersMap = new HashMap<String, ArrayList<Handler>>();
			handlers.put( source, handlersMap );
		}

		ArrayList<NotifyPropertyChangedEvent.Handler> handlerList = handlersMap.get( propertyName );
		if( handlerList == null )
		{
			handlerList = new ArrayList<NotifyPropertyChangedEvent.Handler>();
			handlersMap.put( propertyName, handlerList );
		}

		handlerList.add( handler );

		HandlerInfo info = new HandlerInfo();
		info.source = source;
		info.propertyName = propertyName;
		info.handler = handler;

		return info;
	}

	static class HandlerInfo
	{
		Object source;
		String propertyName;
		NotifyPropertyChangedEvent.Handler handler;
	}

	public static void removePropertyChangedHandler( Object handlerRegistration )
	{
		HandlerInfo info = (HandlerInfo) handlerRegistration;

		HashMap<String, ArrayList<NotifyPropertyChangedEvent.Handler>> handlersMap = handlers.get( info.source );
		if( handlersMap == null )
			return;

		ArrayList<NotifyPropertyChangedEvent.Handler> handlerList = handlersMap.get( info.propertyName );
		if( handlerList == null )
			return;

		handlerList.remove( info.handler );

		if( handlerList.isEmpty() )
			handlersMap.remove( info.propertyName );

		if( handlersMap.isEmpty() )
			handlers.remove( info.source );

		info.handler = null;
		info.propertyName = null;
		info.source = null;
	}

	public static void notify( Object sender, String propertyName )
	{
		// GWT.log( "NotifyPptyChanged : " + sender.getClass().getName() + " : "
		// + propertyName );

		HashMap<String, ArrayList<NotifyPropertyChangedEvent.Handler>> handlersMap = handlers.get( sender );
		if( handlersMap == null )
			return;

		ArrayList<NotifyPropertyChangedEvent.Handler> handlerList = handlersMap.get( propertyName );
		if( handlerList == null )
			return;

		NotifyPropertyChangedEvent event = new NotifyPropertyChangedEvent( sender, propertyName );

		for( NotifyPropertyChangedEvent.Handler handler : handlerList )
			handler.onNotifyPropertChanged( event );
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
