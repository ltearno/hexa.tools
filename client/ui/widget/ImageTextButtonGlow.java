package com.hexa.client.ui.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

public class ImageTextButtonGlow extends Composite
{
	private ImageResource resource = null;
	HTML html = new HTML();

	public @UiConstructor
	ImageTextButtonGlow( ImageResource resource, String title )
	{
		this.resource = resource;

		html.setHTML( "<div class='TestButton'><div class='TestButton-left'></div><div class='TestButton-image'><img src='" + resource.getSafeUri().asString()
				+ "'/></div><div class='TestButton-text'>" + title + "</div><div class='TestButton-right'></div></div>" );
		initWidget( html );
	}

	public void setText( String text )
	{
		html.setHTML( "<div class='TestButton'><div class='TestButton-left'></div><div class='TestButton-image'><img src='" + resource.getSafeUri().asString()
				+ "'/></div><div class='TestButton-text'>" + text + "</div><div class='TestButton-right'></div><br style='clear:both;'/></div>" );
	}

	public void addClickHandler( ClickHandler handler )
	{
		html.addClickHandler( handler );
	}

	public boolean isMyClickEvent( ClickEvent event )
	{
		if( event.getSource() == html )
			return true;
		return false;
	}
}
