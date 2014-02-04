package com.hexa.client.ui.dialog;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.ProvidesResize;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.hexa.client.ui.resources.image.ImageResources;

class ResizablePanel extends ComplexPanel implements RequiresResize, ProvidesResize
{
	private boolean isDisplayed = false;
	
	private Element main;
	private Element title;
	private Element close;
	private Element content;
	
	public ResizablePanel()
	{
		Element glass = Document.get().createDivElement();
		setElement( glass );
		
		glass.getStyle().setBackgroundColor( "rgba(0, 0, 0, 0.5)" );
		glass.getStyle().setPosition( Position.ABSOLUTE );
		glass.getStyle().setLeft( 0, Unit.PX );
		glass.getStyle().setTop( 0, Unit.PX );
		glass.getStyle().setRight( 0, Unit.PX );
		glass.getStyle().setBottom( 0, Unit.PX );
		glass.getStyle().setTextAlign( com.google.gwt.dom.client.Style.TextAlign.CENTER );
		
		ImageResource closeImage = ImageResources.INSTANCE.close();
		int headerSize = Math.max( closeImage.getHeight(), closeImage.getWidth() ) + 5;
		
		glass.setInnerHTML( "<div style='background-color:pink; display: inline-block; margin: 50px; position: relative; overflow:auto;'>"+
				"<div style='position:absolute; left:0px; right:"+headerSize+"px; top:0px; height:"+headerSize+"px'></div>"+
				"<div style='position:absolute; right:0px; top:0px;'><img></img></div>"+
				"<div style='position: relative; top:"+headerSize+"px;'> <!-- content holder --> </div>"+
			"</div>" );		

		main = (Element) glass.getChild( 0 );
		
		title = (Element) main.getChild( 0 );
		close = (Element) main.getChild( 1 ).getChild( 0 );
		close.setAttribute( "src", closeImage.getSafeUri().asString() );
		content = (Element) main.getChild( 2 );
	}

	private Widget contentWidget = null;

	public void setText( String titleText )
	{
		title.setInnerText( titleText );
	}

	public void setContent( Widget w )
	{
		if( contentWidget != null )
			remove( contentWidget );

		contentWidget = w;

		add( w, content );
	}

	public void show( boolean modal )
	{
		if( isDisplayed )
			return;
		isDisplayed = true;

		RootLayoutPanel.get().add( this );
		
		onResize();
	}

	public void hide()
	{
		if( ! isDisplayed )
			return;
		isDisplayed = false;
		
		RootLayoutPanel.get().remove( this );
	}

	public HandlerRegistration addCloseClickHandler( final ClickHandler handler )
	{
		return addDomHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				if( (Object) event.getNativeEvent().getEventTarget() != (Object) close )
					return;

				handler.onClick( event );
			}
		}, ClickEvent.getType() );
	}

	@Override
	public void onResize()
	{
		if( contentWidget instanceof RequiresResize )
		{
			int m = 20;
			main.getStyle().setPosition( Position.ABSOLUTE );
			main.getStyle().setLeft( m, Unit.PX );
			main.getStyle().setTop( m, Unit.PX );
			main.getStyle().setRight( m, Unit.PX );
			main.getStyle().setBottom( m, Unit.PX );
			
			((RequiresResize)contentWidget).onResize();
		}	
	}
}

class PanelForNormalWidget
{
	
}