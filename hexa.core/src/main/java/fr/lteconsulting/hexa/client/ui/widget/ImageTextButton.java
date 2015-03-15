package fr.lteconsulting.hexa.client.ui.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.css.HexaCss;

public class ImageTextButton extends Widget implements ClickHandler, HasClickHandlers
{
	public interface Css extends HexaCss
	{
		public static final Css CSS = GWT.create( Css.class );

		String main();
	}

	public interface Callback
	{
		void onClick( Object cookie );
	}

	ImageResource resource;
	String title;

	protected Element button;

	Object cookie = null;
	Callback callback = null;

	@UiConstructor
	public ImageTextButton( ImageResource resource, String title )
	{
		this.resource = resource;
		this.title = title;

		button = createButtonElement();
		button.setClassName( Css.CSS.main() );

		setText( title );

		setElement( button );
	}
	
	protected Element createButtonElement()
	{
		return DOM.createButton();
	}

	public void setCallback( Callback callback, Object cookie )
	{
		this.callback = callback;
		this.cookie = cookie;

		addClickHandler( this );
	}

	public void setCookie( Object cookie )
	{
		this.cookie = cookie;
	}

	public Object getCookie()
	{
		return cookie;
	}

	public void setText( String text )
	{
		title = text;

		String elem;
		if( resource != null )
			elem = "<img src='" + resource.getSafeUri().asString() + "'/>";
		else
			elem = "";

		button.setInnerHTML( elem + "<span>" + text + "</span>" );
	}

	@Override
	public HandlerRegistration addClickHandler( ClickHandler handler )
	{
		return addDomHandler( handler, ClickEvent.getType() );
	}

	public void setEnabled( boolean fEnable )
	{
		if( fEnable )
		{
			button.removeAttribute( "disabled" );
			button.removeClassName( "disabled" );
		}
		else
		{
			button.setAttribute( "disabled", "true" );
			button.addClassName( "disabled" );
		}
	}

	@Override
	public void onClick( ClickEvent event )
	{
		assert (callback != null);

		callback.onClick( cookie );
	}
}