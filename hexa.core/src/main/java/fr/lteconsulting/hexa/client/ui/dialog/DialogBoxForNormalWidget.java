package fr.lteconsulting.hexa.client.ui.dialog;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.HasCloseHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.ui.dialog.DialogBoxBuilder.DialogBox;
import fr.lteconsulting.hexa.client.ui.resources.image.ImageResources;

class DialogBoxForNormalWidget extends ComplexPanel implements DialogBox, HasCloseHandlers<DialogBox>
{
	private boolean isDisplayed = false;

	private Element main;
	private Element title;
	private Element close;
	private Element content;

	private boolean isAutoHide;

	public DialogBoxForNormalWidget( String titleText, Widget contentWidget )
	{
		Element glass = Document.get().createDivElement();
		setElement( glass );

		glass.getStyle().setBackgroundColor( "rgba(0, 0, 0, 0.25)" );
		glass.getStyle().setPosition( Position.ABSOLUTE );
		glass.getStyle().setLeft( 0, Unit.PX );
		glass.getStyle().setTop( 0, Unit.PX );
		glass.getStyle().setRight( 0, Unit.PX );
		glass.getStyle().setBottom( 0, Unit.PX );
		glass.getStyle().setTextAlign( com.google.gwt.dom.client.Style.TextAlign.CENTER );

		ImageResource closeImage = ImageResources.INSTANCE.close();
		int headerSize = Math.max( closeImage.getHeight(), closeImage.getWidth() ) + 5;

		glass.setInnerHTML( "<div class='"+ResizablePanel.CSS.bkgnd()+"' style='display: inline-block; position: relative; margin-top:50px'>"+
				"<div class='"+ResizablePanel.CSS.title()+"' style='position:relative; left:0px; margin-right:"+headerSize+"px; top:0px; height:"+headerSize+"px'></div>"+
				"<div style='position:absolute; right:0px; top:0px;'><img></img></div>"+
				"<div style='position: relative; overflow:auto;'> <!-- content holder --> </div>"+
			"</div>" );

		main = (Element) glass.getChild( 0 );

		title = (Element) main.getChild( 0 );
		title.setInnerText( titleText );

		close = (Element) main.getChild( 1 ).getChild( 0 );
		close.setAttribute( "src", closeImage.getSafeUri().asString() );
		content = (Element) main.getChild( 2 );
		
		content.addClassName( ResizablePanel.CSS.content() );

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

		addDomHandler( mouseDownHandler, MouseDownEvent.getType() );
		addDomHandler( mouseMoveHandler, MouseMoveEvent.getType() );
		addDomHandler( mouseUpHandler, MouseUpEvent.getType() );
	}

	boolean isMoving = false;
	int movingMouseOffsetX;
	int movingMouseOffsetY;
	int movingOriginX;
	int movingOriginY;

	private MouseDownHandler mouseDownHandler = new MouseDownHandler()
	{
		@Override
		public void onMouseDown( MouseDownEvent event )
		{
			if( event.getNativeEvent().getEventTarget().<Element>cast() == title )
			{
				isMoving = true;
				movingMouseOffsetX = event.getClientX();
				movingMouseOffsetY = event.getClientY();

				DOM.setCapture( title );
				event.preventDefault();
				event.stopPropagation();
			}
		}
	};

	private MouseMoveHandler mouseMoveHandler = new MouseMoveHandler()
	{
		@Override
		public void onMouseMove( MouseMoveEvent event )
		{
			if( isMoving )
			{
				main.getStyle().setLeft( movingOriginX + event.getClientX() - movingMouseOffsetX, Unit.PX );
				main.getStyle().setTop( movingOriginY + event.getClientY() - movingMouseOffsetY, Unit.PX );
			}
		}
	};

	private MouseUpHandler mouseUpHandler = new MouseUpHandler()
	{
		@Override
		public void onMouseUp( MouseUpEvent event )
		{
			isMoving = false;
			DOM.releaseCapture( title );

			movingOriginX += event.getClientX() - movingMouseOffsetX;
			movingOriginY += event.getClientY() - movingMouseOffsetY;
		}
	};

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

	@Override
	public HandlerRegistration addCloseHandler( CloseHandler<DialogBox> handler )
	{
		return addHandler( handler, CloseEvent.getType() );
	}
}