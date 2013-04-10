package com.hexa.client.tools;

import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gwt.user.client.DOM;

public class HTMLSnip
{
	String raw = null;
	HashMap<String, String> elementIds = new HashMap<String, String>();

	String snip = null;

	public void setRaw( String raw )
	{
		this.raw = raw;
	}

	public void addElement( String name )
	{
		elementIds.put( name, DOM.createUniqueId() );
	}

	public String getSnip()
	{
		if( snip != null )
			return snip;

		snip = raw;
		for( Entry<String, String> e : elementIds.entrySet() )
			snip = snip.replaceAll( "#" + e.getKey() + "#", e.getValue() );

		return snip;
	}

	public String getElementId( String name )
	{
		return elementIds.get( name );
	}
}
