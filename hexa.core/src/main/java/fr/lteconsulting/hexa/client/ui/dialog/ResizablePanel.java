package fr.lteconsulting.hexa.client.ui.dialog;

import com.google.gwt.core.shared.GWT;

import fr.lteconsulting.hexa.client.css.HexaCss;

/**
 * This class is used to handle the Css styles for the different dialog boxes
 */
public class ResizablePanel
{
	public static final Css CSS = GWT.create( Css.class );
	
	interface Css extends HexaCss
	{
		public String main();
		public String bkgnd();
		public String title();
		public String content();
	}
}
