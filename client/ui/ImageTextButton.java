package com.hexa.client.ui;

import com.hexa.client.tools.JQuery;
import com.google.gwt.user.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

public class ImageTextButton extends Widget implements ClickHandler, HasClickHandlers
{
	public interface Callback
	{
		void onClick( Object cookie );
	}

	ImageResource resource;
	String title;

	Element button;

	Object cookie = null;
	Callback callback = null;

	@UiConstructor
	public ImageTextButton( ImageResource resource, String title )
	{
		this.resource = resource;
		this.title = title;

		button = DOM.createButton();
		button.setClassName( "buttonV2" );
		// button.setClassName( "ImageTextButtonV2" );
		// button.setAttribute( "style",
		// "min-height:"+(resource.getHeight()+2)+"px;"+"padding-left:"+resource.getWidth()+"px; background: url('"+resource.getURL()+"') no-repeat 0px center;"
		// );

		setText( title );

		setElement( button );
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

		int width = 16;// resource.getWidth()
		int height = 16;// resource.getHeight()
		String elem;
		if( resource != null )
			elem = "<img src='" + resource.getURL() + "' style='width:" + width + "px; height:" + height + "px; position:relative; margin-right:2px;'/>";
		else
			elem = "";
		JQuery.jqHtml( button, elem + text );
	}

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
/*
 * public class ImageTextButton extends Composite implements ClickHandler { public interface Callback { void onClick( Object cookie ); }
 * 
 * HTML h;
 * 
 * ImageResource resource; String title;
 * 
 * Object cookie = null; Callback callback = null;
 * 
 * private String getHTML() { return "<button class='ImageTextButtonV2' style=\"padding-left:" +resource.getWidth()+
 * "px; background: url('"+resource.getURL()+"') no-repeat 0px center;\">" +title+"</button>"; }
 * 
 * @UiConstructor public ImageTextButton( ImageResource resource, String title ) { this.resource = resource; this.title = title;
 * 
 * h = new HTML( getHTML() );
 * 
 * initWidget( h );
 * 
 * Element button = DOM.createButton(); button.setClassName( "ImageTextButtonV2" ); button.setAttribute( "style",
 * "padding-left:"+resource.getWidth()+"px; background: url('" +resource.getURL()+"') no-repeat 0px center;" ); }
 * 
 * public void setCallback( Callback callback, Object cookie ) { this.callback = callback; this.cookie = cookie;
 * 
 * addClickHandler( this ); }
 * 
 * public void setCookie( Object cookie ) { this.cookie = cookie; }
 * 
 * public Object getCookie() { return cookie; }
 * 
 * public void setText( String text ) { title = text; h.setHTML( getHTML() ); }
 * 
 * public void addClickHandler( ClickHandler handler ) { h.addClickHandler( handler ); }
 * 
 * public boolean isMyClickEvent( ClickEvent event ) { if( event.getSource() == h ) return true; return false; }
 * 
 * @Override public void onClick(ClickEvent event) { assert( callback != null );
 * 
 * callback.onClick( cookie ); } }
 */
