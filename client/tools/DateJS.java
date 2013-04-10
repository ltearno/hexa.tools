package com.hexa.client.tools;

import com.google.gwt.core.client.JavaScriptObject;

/*
 * This class wraps calls to the Date.js script
 */

public class DateJS
{
	public final static native JavaScriptObject create( String text )
	/*-{
		var date;
		
		date = $wnd.Date.parseExact( text, "yyyy-MM-dd" );
		if( date == null )
			date = $wnd.Date.parseExact( text, ["d MMMM yyyy","d MMM yyyy"] );
		if( date == null )
			date = $wnd.Date.parse( text );
		
		//@com.google.gwt.core.client.GWT::log(Ljava/lang/String;)( "JSNative datejs for " + text + " : " + date );
		
		if( date == null )
			return null;
		
		var object = { content: date };
		
		return object;
	}-*/;
	
	// gives the value of the date in DB UTC format
	public final static native String toConformity( JavaScriptObject date )
	/*-{
		if( date.content == null )
			return "";
		return date.content.toString( "yyyy-MM-dd" );
	}-*/;
}
