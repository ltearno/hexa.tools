package fr.lteconsulting.hexa.client.databinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Window;

public class NotifyPropertyChangedEvent extends GwtEvent<NotifyPropertyChangedEvent.Handler>
{
	public interface Handler extends EventHandler
	{
		public void onNotifyPropertChanged( NotifyPropertyChangedEvent event );
	}
	
	private native static void setObjectMetadata( Object object, Object metadata )
	/*-{
		object.__hexa_metadata = metadata;
	}-*/;
	
	private native static <T> T getObjectMetadata( Object object )
	/*-{
		return object.__hexa_metadata || null;
	}-*/;
	
	static int nbRegisteredHandlers = 0;
	static int nbNotifications = 0;
	static int nbDispatches = 0;
	static HashMap<String, Integer> counts = new HashMap<>();
	static HashMap<String, Integer> oldCounts = new HashMap<>();
	
	public static void showStats()
	{
		String msg = "NotifyPropertyChanged stats :\r\n"
				+ "# registered handlers : " + nbRegisteredHandlers + "\r\n"
				+ "# notifications       : " + nbNotifications + "\r\n"
				+ "# dispatches          : " + nbDispatches + "\r\n";
		
		StringBuilder details = new StringBuilder();
		for( Entry<String, Integer> e : counts.entrySet() )
		{
			details.append( e.getKey() + " => " + e.getValue() );
			
			Integer oldCount = oldCounts.get( e.getKey() );
			if( oldCount!=null )
				details.append( " (diff: " + (e.getValue()-oldCount) + ")" );
			
			details.append( "\n" );
		}
		
		oldCounts = new HashMap<>( counts );
		
		Window.alert( msg + details.toString() );
	}

	public static Object registerPropertyChangedEvent( Object source, String propertyName, NotifyPropertyChangedEvent.Handler handler )
	{
		// Through object's interface implementation
		if( source instanceof INotifyPropertyChanged )
		{
			DirectHandlerInfo info = new DirectHandlerInfo();
			info.source = (INotifyPropertyChanged) source;
			info.registrationObject = info.source.registerPropertyChangedEvent( propertyName, handler );
			
			return info;
		}
		
		HashMap<String, ArrayList<NotifyPropertyChangedEvent.Handler>> handlersMap = getObjectMetadata( source );
		if( handlersMap == null )
		{
			handlersMap = new HashMap<>();
			setObjectMetadata( source, handlersMap );
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
		
		statsAddedRegistration( info );
		
		return info;
	}
	
	static void statsAddedRegistration( HandlerInfo info )
	{
		nbRegisteredHandlers++;
		
		String key = info.propertyName + "@" + info.source.getClass().getSimpleName();
		Integer count = counts.get( key );
		if( count == null )
			count = 0;
		count++;
		counts.put( key, count );
	}
	
	static void statsRemovedRegistration( HandlerInfo info )
	{
		nbRegisteredHandlers--;
		
		String key = info.propertyName + "@" + info.source.getClass().getSimpleName();
		counts.put( key, counts.get( key ) - 1 );
	}
	
	static class DirectHandlerInfo
	{
		INotifyPropertyChanged source;
		Object registrationObject;
	}

	static class HandlerInfo
	{
		Object source;
		String propertyName;
		NotifyPropertyChangedEvent.Handler handler;
	}

	public static void removePropertyChangedHandler( Object handlerRegistration )
	{
		// Through object's interface implementation
		if( handlerRegistration instanceof DirectHandlerInfo )
		{
			DirectHandlerInfo info = (DirectHandlerInfo) handlerRegistration;
			info.source.removePropertyChangedHandler( info.registrationObject );
			return;
		}
		
		if( ! ( handlerRegistration instanceof HandlerInfo ) )
			return;
		
		HandlerInfo info = (HandlerInfo) handlerRegistration;

		HashMap<String, ArrayList<NotifyPropertyChangedEvent.Handler>> handlersMap = getObjectMetadata( info.source );
		if( handlersMap == null )
			return;

		ArrayList<NotifyPropertyChangedEvent.Handler> handlerList = handlersMap.get( info.propertyName );
		if( handlerList == null )
			return;

		handlerList.remove( info.handler );

		if( handlerList.isEmpty() )
			handlersMap.remove( info.propertyName );

		if( handlersMap.isEmpty() )
			setObjectMetadata( info.source, null );
		
		statsRemovedRegistration( info );

		info.handler = null;
		info.propertyName = null;
		info.source = null;
	}

	public static void notify( Object sender, String propertyName )
	{
		nbNotifications++;
		
		HashMap<String, ArrayList<NotifyPropertyChangedEvent.Handler>> handlersMap = getObjectMetadata( sender );
		if( handlersMap == null )
			return;

		NotifyPropertyChangedEvent event = null;
		
		ArrayList<NotifyPropertyChangedEvent.Handler> handlerList = handlersMap.get( propertyName );
		if( handlerList != null )
		{
			if( event == null )
				event = new NotifyPropertyChangedEvent( sender, propertyName );
	
			for( NotifyPropertyChangedEvent.Handler handler : handlerList )
			{
				handler.onNotifyPropertChanged( event );
				nbDispatches++;
			}
		}
		
		handlerList = handlersMap.get( "*" );
		if( handlerList != null )
		{
			if( event == null )
				event = new NotifyPropertyChangedEvent( sender, propertyName );
	
			for( NotifyPropertyChangedEvent.Handler handler : handlerList )
			{
				handler.onNotifyPropertChanged( event );
				nbDispatches++;
			}
		}
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
