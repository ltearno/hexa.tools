package fr.lteconsulting.hexa.client.tools;

import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gwt.user.client.DOM;

/**
 * A tool to generate html stream with unique ids.
 * 
 * Example :
 * snip.setRaw( "<div><div id="#name#"></div></div>" );
 * snip.addElement( "name" );
 * String html = snip.getSnip(); // returns #name# replaced by a unique id
 * // add html inside your document...
 * Document.getElement( snip.getElementId( "name" ) ); // returns the id of the named element in the raw snip
 * 
 * @author Arnaud Tournier
 *
 */
public class HTMLSnip
{
	String raw = null;
	HashMap<String, String> elementIds = new HashMap<String, String>();

	String snip = null;
	
	public HTMLSnip()
	{
	}
	
	public HTMLSnip( String raw )
	{
		this.raw = raw;
	}

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
