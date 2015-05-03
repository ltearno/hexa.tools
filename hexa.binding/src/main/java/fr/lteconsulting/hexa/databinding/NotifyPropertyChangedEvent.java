package fr.lteconsulting.hexa.databinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * This class is part of the Hexa DataBinding and provides :
 * <ol>
 * <li>registering to object's property change
 * <li>notifying when a property changes in an object
 * </ol>
 * 
 * @author Arnaud Tournier
 *
 */
public class NotifyPropertyChangedEvent
{
	/**
	 * Interface through which one receives {@link PropertyChangedEvent}
	 */
	public interface Handler
	{
		void onNotifyPropertChanged( NotifyPropertyChangedEvent event );
	}
	
	private static int nbRegisteredHandlers = 0;
	private static int nbNotifications = 0;
	private static int nbDispatches = 0;
	private static HashMap<String, Integer> counts = new HashMap<>();
	private static HashMap<String, Integer> oldCounts = new HashMap<>();
	
	/**
	 * Registers an handler for a specific property change on an object. The object
	 * itself does not need to implement anything. But at least it should call the 
	 * {@link notify(Object, String)} method when its internal property changes.
	 * 
	 * @param source The object from which one wants notifications
	 * @param propertyName The property subscribed. You can use "*" to subscribe to all properties in one time
	 * @param handler
	 * @return
	 */
	public static Object registerPropertyChangedEvent( Object source, String propertyName, NotifyPropertyChangedEvent.Handler handler )
	{
		if( source instanceof INotifyPropertyChanged )
		{
			DirectHandlerInfo info = new DirectHandlerInfo();
			info.source = (INotifyPropertyChanged) source;
			info.registrationObject = info.source.registerPropertyChangedEvent( propertyName, handler );
			
			return info;
		}
		
		HashMap<String, ArrayList<NotifyPropertyChangedEvent.Handler>> handlersMap = PlatformSpecificProvider.get().getObjectMetadata( source );
		if( handlersMap == null )
		{
			handlersMap = new HashMap<>();
			PlatformSpecificProvider.get().setObjectMetadata( source, handlersMap );
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

	/**
	 * Unregisters a handler, freeing associated resources
	 * 
	 * @param handlerRegistration The object received after a call to {@link registerPropertyChangedEvent}
	 */
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
	
		HashMap<String, ArrayList<NotifyPropertyChangedEvent.Handler>> handlersMap = PlatformSpecificProvider.get().getObjectMetadata( info.source );
		if( handlersMap == null )
			return;
	
		ArrayList<NotifyPropertyChangedEvent.Handler> handlerList = handlersMap.get( info.propertyName );
		if( handlerList == null )
			return;
	
		handlerList.remove( info.handler );
	
		if( handlerList.isEmpty() )
			handlersMap.remove( info.propertyName );
	
		if( handlersMap.isEmpty() )
			PlatformSpecificProvider.get().setObjectMetadata( info.source, null );
		
		statsRemovedRegistration( info );
	
		info.handler = null;
		info.propertyName = null;
		info.source = null;
	}

	/**
	 * Notifies the Hexa event system of an object changing one of
	 * its properties.
	 * 
	 * @param sender The object whom property changed
	 * @param propertyName The changed property name
	 */
	public static void notify( Object sender, String propertyName )
	{
		nbNotifications++;
		
		HashMap<String, ArrayList<NotifyPropertyChangedEvent.Handler>> handlersMap = PlatformSpecificProvider.get().getObjectMetadata( sender );
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

	/**
	 * Show an alert containing useful information for debugging. It also
	 * shows how many registrations happened since last call ; that's useful
	 * to detect registration leaks.
	 */
	public static String getStats()
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
		
		return msg + details.toString();
	}

	private final Object sender;

	private final String propertyName;

	/**
	 * Private constructor, can only be created by the
	 * {@link notify} method.
	 * @param sender
	 * @param propertyName
	 */
	private NotifyPropertyChangedEvent( Object sender, String propertyName )
	{
		this.sender = sender;
		this.propertyName = propertyName;
	}

	/**
	 * Returns the object that sent the event
	 * 
	 * @return The object that sent the event
	 */
	public Object getSender()
	{
		return sender;
	}

	/**
	 * Returns the name of the property that changed
	 * @return The name of the property that changed
	 */
	public String getPropertyName()
	{
		return propertyName;
	}
	
	private static class DirectHandlerInfo
	{
		INotifyPropertyChanged source;
		Object registrationObject;
	}

	private static class HandlerInfo
	{
		Object source;
		String propertyName;
		NotifyPropertyChangedEvent.Handler handler;
	}

	private static void statsAddedRegistration( HandlerInfo info )
	{
		nbRegisteredHandlers++;
		
		String key = info.propertyName + "@" + info.source.getClass().getSimpleName();
		Integer count = counts.get( key );
		if( count == null )
			count = 0;
		count++;
		counts.put( key, count );
	}

	private static void statsRemovedRegistration( HandlerInfo info )
	{
		nbRegisteredHandlers--;
		
		String key = info.propertyName + "@" + info.source.getClass().getSimpleName();
		counts.put( key, counts.get( key ) - 1 );
	}
}
