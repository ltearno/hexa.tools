package com.hexa.client.raphael;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

public class RaphaelWidget extends Widget
{
	public RaphaelJS overlay;
	
	public RaphaelWidget( int width, int height )
	{
		Element raphaelDiv = DOM.createDiv();
	    setElement(raphaelDiv);
	    overlay = RaphaelJS.create(raphaelDiv, width, height );
	}
	
	public static boolean isSupported() {
	    return RaphaelJS.isDefined();
	  }
	
	/*public String getText() {
	    return getElement().getInnerText();
	}*/
}
