package fr.lteconsulting.hexa.client.ui.widget;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

import fr.lteconsulting.hexa.client.css.HexaCss;

public class ImageTextButtonGlow extends Composite
{
	interface Css extends HexaCss
	{
		static final Css CSS = GWT.create( Css.class );

		String main();
		String left();
		String right();
		String image();
		String text();
	}

	private ImageResource resource = null;
	HTML html = new HTML();

	public @UiConstructor
	ImageTextButtonGlow( ImageResource resource, String title )
	{
		this.resource = resource;

		html.setHTML( "<div class='"+Css.CSS.main()+"'><div class='"+Css.CSS.left()+"'></div><div class='"+Css.CSS.image()+"'><img src='" + resource.getSafeUri().asString() + "'/></div><div class='"+Css.CSS.text()+"'>" + title + "</div><div class='"+Css.CSS.right()+"'></div></div>" );
		initWidget( html );
	}

	public void setText( String text )
	{
		html.setHTML( "<div class='"+Css.CSS.main()+"'><div class='"+Css.CSS.left()+"'></div><div class='"+Css.CSS.image()+"'><img src='" + resource.getSafeUri().asString() + "'/></div><div class='"+Css.CSS.text()+"'>" + text
				+ "</div><div class='TestButton-right'></div><br style='clear:both;'/></div>" );
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
