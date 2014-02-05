package com.hexa.client.ui.dialog;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.HasCloseHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.hexa.client.ui.dialog.DialogBoxBuilder.DialogBox;
import com.hexa.client.ui.resources.image.ImageResources;

class ResizablePanel extends ComplexPanel implements DialogBox, HasCloseHandlers<DialogBox>
{
	private boolean isDisplayed = false;
	
	private Element main;
	private Element title;
	private Element close;
	private Element content;
	
	private boolean isAutoHide;
	
	public ResizablePanel( String titleText, Widget contentWidget )
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
		
		glass.setInnerHTML( "<div class='ResizablePanel-bkgnd' style='display: inline-block; position: relative; margin-top:50px'>"+
				"<div class='ResizablePanel-title' style='position:relative; left:0px; margin-right:"+headerSize+"px; top:0px; height:"+headerSize+"px'></div>"+
				"<div style='position:absolute; right:0px; top:0px;'><img></img></div>"+
				"<div style='position: relative; overflow:auto;'> <!-- content holder --> </div>"+
			"</div>" );		

		main = (Element) glass.getChild( 0 );
		
		title = (Element) main.getChild( 0 );
		title.setInnerText( titleText );
		
		close = (Element) main.getChild( 1 ).getChild( 0 );
		close.setAttribute( "src", closeImage.getSafeUri().asString() );
		content = (Element) main.getChild( 2 );
		
		add( contentWidget, content );
		
		addDomHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				if( ! isAutoHide )
					return;
				
				if( (Object) event.getNativeEvent().getEventTarget() != (Object) close )
					return;
				
				hide();
			}
		}, ClickEvent.getType() );
	}
	
	@Override
	public void show()
	{
		show( true );
	}
	
	@Override
	public void show( boolean isAutoHide )
	{
		this.isAutoHide = isAutoHide;
		
		if( isDisplayed )
			return;
		isDisplayed = true;

		RootPanel.get().add( this );
	}
	
	@Override
	public void hide()
	{
		if( ! isDisplayed )
			return;
		isDisplayed = false;
		
		RootPanel.get().remove( this );
		
		CloseEvent.fire( this, null );
	}
	
	public HandlerRegistration addCloseHandler( CloseHandler<DialogBox> handler )
	{
		return addHandler( handler, CloseEvent.getType() );
	}
}