package com.hexa.client.ui;

import java.util.ArrayList;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

public class ColorBox
{
	public static void launch( ArrayList<String> urls )
	{
		Element root = DOM.createDiv();
		
		for( String url: urls )
		{
			Element a = DOM.createAnchor();
			a.setAttribute( "href", url );
			root.appendChild( a );
		}
		
		launchImpl( root );
	}
	
	private static native void launchImpl( Element e )
	/*-{
		$wnd.$( "a", e ).colorbox( { open:true } );
	}-*/;
}
