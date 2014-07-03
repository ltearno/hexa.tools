package fr.lteconsulting.hexa.client.tools;

import java.util.HashMap;

import com.google.gwt.user.client.DOM;

public class HTMLSniper
{
	HashMap<String, String> elementIds = new HashMap<String, String>();

	String getId( String name )
	{
		String res = elementIds.get( name );
		if( res == null )
		{
			res = DOM.createUniqueId();
			elementIds.put( name, res );
		}

		return res;
	}
}
