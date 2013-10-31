package com.hexa.client.ui.dialog;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.ProvidesResize;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.hexa.client.ui.resources.image.ImageResources;

class ResizablePanel extends ComplexPanel implements MouseMoveHandler, MouseUpHandler, MouseDownHandler, RequiresResize, ProvidesResize
{
	int resizeHandlerSize = 5;
	int titleSize;

	static final int LEFT = 1;
	static final int TOP = 2;
	static final int RIGHT = 4;
	static final int BOTTOM = 8;
	static final int MOVE = 16;

	int minWidth = 50;
	int minHeight = 50;

	Element topLeft;
	Element top;
	Element topRight;
	Element right;
	Element bottomRight;
	Element bottom;
	Element bottomLeft;
	Element left;
	Element content;
	Element title;
	Element close;

	boolean fResizing = false;
	int direction = 0;

	int startX;
	int startY;

	int startTop;
	int startLeft;
	int startWidth;
	int startHeight;

	static final String template = "<div class='top_left_handler' style='position: absolute; left:0px; top:0px; width:#HANDLER_SIZE#px; height:#HANDLER_SIZE#px;'></div>"
			+ "<div class='top_handler' style='position: absolute; left:#HANDLER_SIZE#px; top:0px; right:#HANDLER_SIZE#px; height:#HANDLER_SIZE#px;'></div>"
			+ "<div class='top_right_handler' style='position: absolute; width:#HANDLER_SIZE#px; top:0px; right:0px; height:#HANDLER_SIZE#px;'></div>"
			+ "<div class='right_handler' style='position: absolute; width:#HANDLER_SIZE#px; top:#HANDLER_SIZE#px; right:0px; bottom:#HANDLER_SIZE#px;'></div>"
			+ "<div class='bottom_right_handler' style='position: absolute; width:#HANDLER_SIZE#px; height:#HANDLER_SIZE#px; right:0px; bottom:0px;'></div>"
			+ "<div class='bottom_handler' style='position: absolute; left:#HANDLER_SIZE#px; height:#HANDLER_SIZE#px; right:#HANDLER_SIZE#px; bottom:0px;'></div>"
			+ "<div class='bottom_left_handler' style='position: absolute; left:0px; height:#HANDLER_SIZE#px; width:#HANDLER_SIZE#px; bottom:0px;'></div>"
			+ "<div class='left_handler' style='position: absolute; left:0px; top:#HANDLER_SIZE#px; width:#HANDLER_SIZE#px; bottom:#HANDLER_SIZE#px;'></div>"
			+ "<div class='title' style='overflow:hidden; position: absolute; left:#HANDLER_SIZE#px; top:#HANDLER_SIZE#px; right:#TITLE_RIGHT#px; height:#TITLE_SIZE#px;'></div>"
			+ "<div class='close_button' style='position: absolute; width:#TITLE_SIZE#px; top:#HANDLER_SIZE#px; right:#HANDLER_SIZE#px; height:#TITLE_SIZE#px;'><img></img></div>"
			+ "<div class='content' style='overflow:auto; position: absolute; left:#HANDLER_SIZE#px; top:#CONTENT_TOP#px; right:#HANDLER_SIZE#px; bottom:#HANDLER_SIZE#px;'></div>";

	public ResizablePanel()
	{
		Element main = DOM.createDiv();
		setElement( main );

		setStylePrimaryName( "ResizablePanel" );

		ImageResource closeImage = ImageResources.INSTANCE.close();
		titleSize = Math.max( closeImage.getHeight(), closeImage.getWidth() ) + 5;

		String html = template.replaceAll( "#HANDLER_SIZE#", "" + resizeHandlerSize ).replaceAll( "#TITLE_SIZE#", "" + titleSize )
				.replaceAll( "#CONTENT_TOP#", "" + (resizeHandlerSize + titleSize) ).replaceAll( "#TITLE_RIGHT#", "" + (resizeHandlerSize + titleSize) );
		main.setInnerHTML( html );

		topLeft = (Element) main.getChild( 0 );
		top = (Element) main.getChild( 1 );
		topRight = (Element) main.getChild( 2 );
		right = (Element) main.getChild( 3 );
		bottomRight = (Element) main.getChild( 4 );
		bottom = (Element) main.getChild( 5 );
		bottomLeft = (Element) main.getChild( 6 );
		left = (Element) main.getChild( 7 );
		title = (Element) main.getChild( 8 );
		close = (Element) main.getChild( 9 ).getChild( 0 );
		close.setAttribute( "src", closeImage.getSafeUri().asString() );
		content = (Element) main.getChild( 10 );

		addDomHandler( this, MouseMoveEvent.getType() );
		addDomHandler( this, MouseUpEvent.getType() );
		addDomHandler( this, MouseDownEvent.getType() );
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

	Glass glass;

	public void show( boolean modal )
	{
		// try to auto size the dialog, based on the content size, mais ca marche pas ... :(
		if( contentWidget != null )
		{
			Scheduler.get().scheduleDeferred( new ScheduledCommand()
			{
				@Override
				public void execute()
				{
					int w = Math.max( contentWidget.getElement().getScrollWidth(), contentWidget.getElement().getOffsetWidth() );
					int h = Math.max( contentWidget.getElement().getScrollHeight(), contentWidget.getElement().getOffsetHeight() );

					if( w > RootLayoutPanel.get().getOffsetWidth() )
					{
						w += resizeHandlerSize * 2;

						GWT.log( "Dialog width put to " + w );

						int screenWidth = RootLayoutPanel.get().getOffsetWidth();

						RootLayoutPanel.get().setWidgetLeftWidth( ResizablePanel.this, (screenWidth - w) / 2, Unit.PX, w, Unit.PX );

						RootLayoutPanel.get().animate( 300 );
					}

					if( h > RootLayoutPanel.get().getOffsetHeight() )
					{
						h += resizeHandlerSize * 2 + titleSize;

						GWT.log( "Dialog height put to " + h );

						int screenHeight = RootLayoutPanel.get().getOffsetHeight();

						RootLayoutPanel.get().setWidgetTopHeight( ResizablePanel.this, (screenHeight - h) / 2, Unit.PX, h, Unit.PX );

						RootLayoutPanel.get().animate( 300 );
					}
				}
			} );
		}

		if( modal )
		{
			glass = new Glass();
			RootLayoutPanel.get().add( glass );
		}

		RootLayoutPanel.get().add( this );

		int screenWidth = RootLayoutPanel.get().getOffsetWidth();
		int screenHeight = RootLayoutPanel.get().getOffsetHeight();

		int width = 400;
		int height = 230;
		RootLayoutPanel.get().setWidgetLeftWidth( this, (screenWidth - width) / 2, Unit.PX, width, Unit.PX );
		RootLayoutPanel.get().setWidgetTopHeight( this, (screenHeight - height) / 2, Unit.PX, height, Unit.PX );

		RootLayoutPanel.get().animate( 300 );
	}

	public void hide()
	{
		RootLayoutPanel.get().remove( this );

		if( glass != null )
		{
			RootLayoutPanel.get().remove( glass );
			glass = null;
		}
	}

	@Override
	public void onMouseDown( MouseDownEvent event )
	{
		direction = getDirection( event.getNativeEvent().getEventTarget() );
		if( direction == 0 )
			return;

		startX = event.getScreenX();
		startY = event.getScreenY();

		startLeft = ResizablePanel.this.getAbsoluteLeft();
		startTop = ResizablePanel.this.getAbsoluteTop();
		startWidth = ResizablePanel.this.getOffsetWidth();
		startHeight = ResizablePanel.this.getOffsetHeight();

		DOM.setCapture( getElement() );

		fResizing = true;

		event.stopPropagation();
		event.preventDefault();
	}

	@Override
	public void onMouseMove( MouseMoveEvent event )
	{
		if( !fResizing )
			return;

		updateSize( event.getNativeEvent().getEventTarget(), event.getScreenX() - startX, event.getScreenY() - startY );

		event.stopPropagation();
		event.preventDefault();
	}

	@Override
	public void onMouseUp( MouseUpEvent event )
	{
		if( !fResizing )
			return;

		updateSize( event.getNativeEvent().getEventTarget(), event.getScreenX() - startX, event.getScreenY() - startY );

		DOM.releaseCapture( getElement() );

		fResizing = false;

		event.stopPropagation();
		event.preventDefault();
	}

	private int getDirection( Object source )
	{
		if( source == top )
			return TOP;
		else if( source == topRight )
			return TOP + RIGHT;
		else if( source == right )
			return RIGHT;
		else if( source == bottomRight )
			return RIGHT + BOTTOM;
		else if( source == bottom )
			return BOTTOM;
		else if( source == bottomLeft )
			return BOTTOM + LEFT;
		else if( source == left )
			return LEFT;
		else if( source == topLeft )
			return TOP + LEFT;
		else if( source == title )
			return MOVE;

		return 0;
	}

	private void updateSize( Object source, int devX, int devY )
	{
		if( direction == MOVE )
		{
			RootLayoutPanel.get().setWidgetLeftWidth( ResizablePanel.this,
					Math.min( Math.max( 0, startLeft + devX ), RootLayoutPanel.get().getOffsetWidth() - startWidth ), Unit.PX, startWidth, Unit.PX );
			RootLayoutPanel.get().setWidgetTopHeight( ResizablePanel.this,
					Math.min( Math.max( 0, startTop + devY ), RootLayoutPanel.get().getOffsetHeight() - startHeight ), Unit.PX, startHeight, Unit.PX );
			return;
		}

		if( (direction & LEFT) == LEFT )
			RootLayoutPanel.get().setWidgetLeftWidth( ResizablePanel.this, startLeft + devX, Unit.PX, Math.max( minWidth, startWidth - devX ), Unit.PX );
		if( (direction & TOP) == TOP )
			RootLayoutPanel.get().setWidgetTopHeight( ResizablePanel.this, startTop + devY, Unit.PX, Math.max( minHeight, startHeight - devY ), Unit.PX );

		if( (direction & RIGHT) == RIGHT )
			RootLayoutPanel.get().setWidgetLeftWidth( ResizablePanel.this, startLeft, Unit.PX, Math.max( minWidth, startWidth + devX ), Unit.PX );
		if( (direction & BOTTOM) == BOTTOM )
			RootLayoutPanel.get().setWidgetTopHeight( ResizablePanel.this, startTop, Unit.PX, Math.max( minHeight, startHeight + devY ), Unit.PX );
	}

	@Override
	public void onResize()
	{
		int left = ResizablePanel.this.getAbsoluteLeft();
		int top = ResizablePanel.this.getAbsoluteTop();
		int width = ResizablePanel.this.getOffsetWidth();
		int height = ResizablePanel.this.getOffsetHeight();

		double newLeft = Math.max( 0, Math.min( Math.max( 0, left ), RootLayoutPanel.get().getOffsetWidth() - width ) );
		double newTop = Math.max( 0, Math.min( Math.max( 0, top ), RootLayoutPanel.get().getOffsetHeight() - height ) );

		if( newLeft != left || newTop != top )
		{
			GWT.log( "onResize => re-resize !" );

			RootLayoutPanel.get().setWidgetLeftWidth( ResizablePanel.this, newLeft, Unit.PX, width, Unit.PX );
			RootLayoutPanel.get().setWidgetTopHeight( ResizablePanel.this, newTop, Unit.PX, height, Unit.PX );

			return;
		}

		for( Widget child : getChildren() )
		{
			if( child instanceof RequiresResize )
			{
				((RequiresResize) child).onResize();
			}
		}
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
}

class Glass extends Widget
{
	public Glass()
	{
		setElement( DOM.createDiv() );
		getElement().getStyle().setBackgroundColor( "rgba(0, 0, 0, 0.5)" );
	}
}