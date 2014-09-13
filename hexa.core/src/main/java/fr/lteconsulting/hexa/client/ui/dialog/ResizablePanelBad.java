package fr.lteconsulting.hexa.client.ui.dialog;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.ProvidesResize;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.ui.resources.image.ImageResources;

class ResizablePanelBad extends ComplexPanel implements MouseMoveHandler, MouseUpHandler, MouseDownHandler, RequiresResize, ProvidesResize
{
	private Widget contentWidget = null;
	
	int resizeHandlerSize = 5;
	int titleSize;

	static final int LEFT = 1;
	static final int TOP = 2;
	static final int RIGHT = 4;
	static final int BOTTOM = 8;
	static final int MOVE = 16;

	int minWidth = 800;
	int minHeight = 500;

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

	public ResizablePanelBad( String titleText, Widget contentWidget )
	{
		Element main = DOM.createDiv();
		setElement( main );

		setStylePrimaryName( ResizablePanel.CSS.main() );

		ImageResource closeImage = ImageResources.INSTANCE.close();
		titleSize = Math.max( closeImage.getHeight(), closeImage.getWidth() ) + 5;

		String html = template.replaceAll( "#HANDLER_SIZE#", "" + resizeHandlerSize ).replaceAll( "#TITLE_SIZE#", "" + titleSize ).replaceAll( "#CONTENT_TOP#", "" + (resizeHandlerSize + titleSize) ).replaceAll( "#TITLE_RIGHT#", "" + (resizeHandlerSize + titleSize) );
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
		
		title.setInnerText( titleText );
		
		this.contentWidget = contentWidget;
	}

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

	public void show( boolean isModal, boolean isAutoHide )
	{
		// try to auto size the dialog, based on the content size, mais ca marche pas ... :(
		if( contentWidget != null )
		{
			Scheduler.get().scheduleDeferred( new ScheduledCommand()
			{
				@Override
				public void execute()
				{
					// try to fit to the content size

					int maxWidth = Window.getClientWidth() - 20; // 20 pixels of
																	// margin
					int w = Math.max( ResizablePanelBad.this.getOffsetWidth(), Math.max( contentWidget.getElement().getScrollWidth(), contentWidget.getElement().getOffsetWidth() ) ) + resizeHandlerSize * 2;
					if( w > maxWidth )
						w = maxWidth;

					int maxHeight = Window.getClientHeight() - 20;
					int h = Math.max( ResizablePanelBad.this.getOffsetHeight(), Math.max( contentWidget.getElement().getScrollHeight(), contentWidget.getElement().getOffsetHeight() ) ) + resizeHandlerSize * 2 + titleSize;
					if( h > maxHeight )
						h = maxHeight;

					PositionAndSize posSize = new PositionAndSize( (Window.getClientWidth() - w) / 2, (Window.getClientHeight() - h) / 2, w, h );
					posSize.limit();

					RootLayoutPanel.get().setWidgetLeftWidth( ResizablePanelBad.this, posSize.left, Unit.PX, posSize.width, Unit.PX );
					RootLayoutPanel.get().setWidgetTopHeight( ResizablePanelBad.this, posSize.top, Unit.PX, posSize.height, Unit.PX );

					RootLayoutPanel.get().animate( 300 );
				}
			} );
		}

		if( isModal )
		{
			glass = new Glass();
			RootLayoutPanel.get().add( glass );
		}

		RootLayoutPanel.get().add( this );

		int screenWidth = RootLayoutPanel.get().getOffsetWidth();
		int screenHeight = RootLayoutPanel.get().getOffsetHeight();

		int width = 400;
		int height = 230;

		PositionAndSize posSize = new PositionAndSize( (screenWidth - width) / 2, (screenHeight - height) / 2, width, height );
		posSize.limit();

		RootLayoutPanel.get().setWidgetLeftWidth( this, posSize.left, Unit.PX, posSize.width, Unit.PX );
		RootLayoutPanel.get().setWidgetTopHeight( this, posSize.top, Unit.PX, posSize.height, Unit.PX );

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

		startLeft = ResizablePanelBad.this.getAbsoluteLeft();
		startTop = ResizablePanelBad.this.getAbsoluteTop();
		startWidth = ResizablePanelBad.this.getOffsetWidth();
		startHeight = ResizablePanelBad.this.getOffsetHeight();

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
			PositionAndSize posSize = new PositionAndSize( startLeft + devX, startTop + devY, startWidth, startHeight );
			posSize.limit();

			RootLayoutPanel.get().setWidgetLeftWidth( ResizablePanelBad.this, posSize.left, Unit.PX, posSize.width, Unit.PX );
			RootLayoutPanel.get().setWidgetTopHeight( ResizablePanelBad.this, posSize.top, Unit.PX, posSize.height, Unit.PX );

			return;
		}

		if( (direction & LEFT) == LEFT )
		{
			PositionAndSize posSize = new PositionAndSize( startLeft + devX, startTop, startWidth - devX, startHeight );
			posSize.limit();

			RootLayoutPanel.get().setWidgetLeftWidth( ResizablePanelBad.this, posSize.left, Unit.PX, posSize.width, Unit.PX );
		}

		if( (direction & RIGHT) == RIGHT )
		{
			PositionAndSize posSize = new PositionAndSize( startLeft, startTop, startWidth + devX, startHeight );
			posSize.limit();

			RootLayoutPanel.get().setWidgetLeftWidth( ResizablePanelBad.this, posSize.left, Unit.PX, posSize.width, Unit.PX );
		}

		if( (direction & TOP) == TOP )
		{
			PositionAndSize posSize = new PositionAndSize( startLeft, startTop + devY, startWidth, startHeight - devY );
			posSize.limit();

			RootLayoutPanel.get().setWidgetTopHeight( ResizablePanelBad.this, posSize.top, Unit.PX, posSize.height, Unit.PX );
		}

		if( (direction & BOTTOM) == BOTTOM )
		{
			PositionAndSize posSize = new PositionAndSize( startLeft, startTop, startWidth, startHeight + devY );
			posSize.limit();

			RootLayoutPanel.get().setWidgetTopHeight( ResizablePanelBad.this, posSize.top, Unit.PX, posSize.height, Unit.PX );
		}
	}

	@Override
	public void onResize()
	{
		int left = ResizablePanelBad.this.getAbsoluteLeft();
		int top = ResizablePanelBad.this.getAbsoluteTop();
		int width = ResizablePanelBad.this.getOffsetWidth();
		int height = ResizablePanelBad.this.getOffsetHeight();

		PositionAndSize posSize = new PositionAndSize( left, top, width, height );

		boolean needAdjustment = posSize.limit();
		if( needAdjustment )
		{
			GWT.log( "onResize => re-resize !" );

			RootLayoutPanel.get().setWidgetLeftWidth( ResizablePanelBad.this, posSize.left, Unit.PX, posSize.width, Unit.PX );
			RootLayoutPanel.get().setWidgetTopHeight( ResizablePanelBad.this, posSize.top, Unit.PX, posSize.height, Unit.PX );

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

	private class PositionAndSize
	{
		int top;
		int left;

		int width;
		int height;

		public PositionAndSize( int left, int top, int width, int height )
		{
			this.top = top;
			this.left = left;
			this.width = width;
			this.height = height;
		}

		// ensure that the position and size fits in the screen
		// returns false if something has been changed
		boolean limit()
		{
			boolean res = true;

			int screenHeight = Window.getClientHeight();
			int screenWidth = Window.getClientWidth();

			// adjust horizontally

			// limit left position
			if( left < 0 )
			{
				left = 0;
				res = false;
			}
			int maxLeft = screenWidth - minWidth;
			if( left > maxLeft )
			{
				left = maxLeft;
				res = false;
			}

			// limit width
			if( width < minWidth )
			{
				width = minWidth;
				res = false;
			}
			int maxWidth = screenWidth - width;
			if( width > maxWidth )
			{
				width = maxWidth;
				res = false;
			}

			// adjust vertically

			// limit top position
			if( top < 0 )
			{
				top = 0;
				res = false;
			}
			int maxTop = screenHeight - minHeight;
			if( top > maxTop )
			{
				top = maxTop;
				res = false;
			}

			// limit height
			if( height < minHeight )
			{
				height = minHeight;
				res = false;
			}
			int maxHeight = screenHeight - top;
			if( height > maxHeight )
			{
				height = maxHeight;
				res = false;
			}

			return res;
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
		setElement( Document.get().createDivElement() );
		getElement().setAttribute( "isGlass", "true" );
		getElement().getStyle().setBackgroundColor( "rgba(0, 0, 0, 0.6)" );
	}
}