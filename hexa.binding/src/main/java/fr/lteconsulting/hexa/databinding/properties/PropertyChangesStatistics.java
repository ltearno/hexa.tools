package fr.lteconsulting.hexa.databinding.properties;

import java.util.HashMap;
import java.util.Map.Entry;

import fr.lteconsulting.hexa.databinding.properties.PropertyChanges.HandlerInfo;

class PropertyChangesStatistics
{
	private static int nbRegisteredHandlers = 0;
	private static int nbNotifications = 0;
	private static int nbDispatches = 0;
	private static HashMap<String, Integer> counts = new HashMap<>();
	private static HashMap<String, Integer> oldCounts = new HashMap<>();
	
	/**
	 * Show an alert containing useful information for debugging. It also
	 * shows how many registrations happened since last call ; that's useful
	 * to detect registration leaks.
	 */
	String getStatistics()
	{
		String msg = "PropertyChanges stats :\r\n"
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
	
	void addNotification()
	{
		nbNotifications++;
	}
	
	void addDispatch()
	{
		nbDispatches++;
	}
	
	void statsAddedRegistration( HandlerInfo info )
	{
		nbRegisteredHandlers++;
		
		String key = info.propertyName + "@" + info.source.getClass().getSimpleName();
		Integer count = counts.get( key );
		if( count == null )
			count = 0;
		count++;
		counts.put( key, count );
	}

	void statsRemovedRegistration( HandlerInfo info )
	{
		nbRegisteredHandlers--;
		
		String key = info.propertyName + "@" + info.source.getClass().getSimpleName();
		counts.put( key, counts.get( key ) - 1 );
	}
}
