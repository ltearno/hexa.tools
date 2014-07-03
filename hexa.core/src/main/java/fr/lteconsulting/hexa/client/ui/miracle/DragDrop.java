package fr.lteconsulting.hexa.client.ui.miracle;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class DragDrop
{
	public interface Callback<T>
	{
		String getGhostInnerHTML( T cookie, Element source );

		void onDragDropFinished( T cookie, Element source, Element destination );
	}

	private static class CaptureWidget extends FocusPanel implements MouseMoveHandler, MouseUpHandler
	{
		int startX = -1;
		int startY = -1;
		Ghost<?> ghost = null;

		boolean fDragging = false;

		public CaptureWidget()
		{
			addMouseMoveHandler( this );
			addMouseUpHandler( this );

			Style style = getElement().getStyle();
			// workaround for IE8 opacity
			// http://code.google.com/p/google-web-toolkit/issues/detail?id=5538
			style.setProperty( "filter", "alpha(opacity=0)" );
			style.setOpacity( 0 );
			style.setZIndex( 0 );// 1000);
			style.setMargin( 0, Style.Unit.PX );
			style.setBorderStyle( BorderStyle.NONE );
			style.setBackgroundColor( "blue" );

			setPixelSize( RootPanel.get().getOffsetWidth(), RootPanel.get().getOffsetHeight() );
		}

		void start( int x, int y, Ghost<?> ghost )
		{
			startX = x;
			startY = y;
			this.ghost = ghost;

			fDragging = false;

			DOM.setCapture( getElement() );
		}

		@Override
		public void onMouseUp( MouseUpEvent event )
		{
			DOM.releaseCapture( getElement() );
			RootPanel.get().remove( this );

			if( fDragging )
			{
				RootPanel.get().remove( ghost );

				event.preventDefault();
				event.stopPropagation();

				Element target = DOM.eventGetTarget( DOM.eventGetCurrentEvent() );
				ghost.signalFinish( target );
			}
			else
			{

			}
		}

		@Override
		public void onMouseMove( MouseMoveEvent event )
		{
			// move the ghost
			int x = event.getClientX();
			int y = event.getClientY();

			if( fDragging )
			{
				ghost.getElement().getStyle().clearDisplay();

				// instead of ghost.w/2
				int w = ghost.getElement().getClientWidth();
				int h = ghost.getElement().getClientHeight();

				ghost.getElement().getStyle().setLeft( x - w / 2, Unit.PX );
				ghost.getElement().getStyle().setTop( y - h / 2, Unit.PX );
			}
			else
			{
				int d = x - startX;
				d = d * d;
				int d2 = y - startY;
				d = d + d2 * d2;
				if( d > 25 )
				{
					ghost.updateInnerHTML();
					int w = ghost.getElement().getClientWidth();
					int h = ghost.getElement().getClientHeight();

					ghost.getElement().getStyle().setDisplay( Display.NONE );

					fDragging = true;
					RootPanel.get().add( ghost, x - w / 2, y - h / 2 );
				}
			}
		}
	}

	private static class Ghost<T> extends Widget
	{
		Element source;
		Callback<T> callback;
		T cookie;

		public Ghost( int x, int y, int w, int h, Element source, Callback<T> callback, T cookie )
		{
			this.source = source;
			this.callback = callback;
			this.cookie = cookie;

			Element ghost = DOM.createDiv();

			Style style = ghost.getStyle();
			style.setPosition( Position.FIXED );
			style.setLeft( x, Unit.PX );
			style.setTop( y, Unit.PX );
			// style.setWidth( w, Unit.PX );
			// style.setHeight( h, Unit.PX );

			// style.setBackgroundColor( "rgba(200,200,200,0.5)" );

			style.setProperty( "pointerEvents", "none" );

			setElement( ghost );
		}

		void updateInnerHTML()
		{
			String html = callback.getGhostInnerHTML( cookie, source );

			if( html == null )
				getElement().setInnerHTML( source.getString() );
			else
				getElement().setInnerHTML( html );
		}

		void signalFinish( Element destination )
		{
			if( source == destination )
				return;
			callback.onDragDropFinished( cookie, source, destination );
		}
	}

	public static <T> void initiate( Element element, Callback<T> callback, T cookie, Event event )
	{
		int x = event.getClientX();
		int y = event.getClientY();
		int w = element.getClientWidth();
		int h = element.getClientHeight();

		CaptureWidget captureWidget = new CaptureWidget();
		Ghost<T> ghost = new Ghost<T>( x, y, w, h, element, callback, cookie );

		// append the ghost to the body of the document
		RootPanel.get().add( captureWidget );

		// capture the mouse on the captureWidget and update the ghost position
		captureWidget.start( x, y, ghost );

		event.preventDefault();
		event.stopPropagation();
	}
}
