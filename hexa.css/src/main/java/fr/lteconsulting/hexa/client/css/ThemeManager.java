package fr.lteconsulting.hexa.client.css;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LinkElement;

public class ThemeManager
{
	private static ThemeManager INSTANCE;
	
	public static ThemeManager get()
	{
		if( INSTANCE == null )
			INSTANCE = new ThemeManager();
		
		return INSTANCE;
	}
	
	private String currentThemeName = null;
	private LinkElement element = null;
	
	public void setTheme( String name )
	{
		if( name == null )
			return;
		
		if( name.equals( currentThemeName ) )
			return;
		
		if( element == null )
		{
			element = Document.get().createLinkElement();
			element.setType( "text/css" );
			element.setRel( "stylesheet" );
			Document.get().getHead().appendChild( element );
		}
		
		element.setHref( name + ".css" );
	}
}
