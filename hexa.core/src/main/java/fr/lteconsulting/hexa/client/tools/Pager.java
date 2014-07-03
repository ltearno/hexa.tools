package fr.lteconsulting.hexa.client.tools;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Widget;

public class Pager
{
	public interface PagerCallback
	{
		public void onPagerWant( int startPosition, int endPosition );
	}

	PagerCallback callback = null;

	int currentPosition = 0;
	int nbDisplayed = 0;
	int lastPosition = 0;
	int nbPerPage = 0;

	int start = 0;
	int end = 0;
	int nb = 0;
	boolean fFirst = false;
	boolean fPrev = false;
	boolean fNext = false;
	boolean fLast = false;

	public Pager( PagerCallback callback )
	{
		setCallback( callback );
	}

	public void setCallback( PagerCallback callback )
	{
		this.callback = callback;
	}

	public void setCurrent( int currentPosition, int nbDisplayed, int lastPosition, int nbPerPage )
	{
		this.currentPosition = currentPosition;
		this.nbDisplayed = nbDisplayed;
		this.lastPosition = lastPosition;
		this.nbPerPage = nbPerPage;

		start = currentPosition + 1;
		end = start + nbDisplayed - 1;
		nb = lastPosition + 1;
		fFirst = currentPosition >= 2 * nbPerPage;
		fPrev = start > 1;
		fNext = (currentPosition + nbDisplayed) <= lastPosition;
		fLast = (currentPosition + nbDisplayed) <= lastPosition - nbPerPage;

		updateWidgets();
	}

	ArrayList<PagingWidget> widgets = new ArrayList<PagingWidget>();

	void updateWidgets()
	{
		for( PagingWidget w : widgets )
			w.update();
	}

	interface PagerWidgetUiBinder extends UiBinder<Element, PagingWidget>
	{
	}

	private static PagerWidgetUiBinder uiBinder = GWT.create( PagerWidgetUiBinder.class );

	class PagingWidget extends Widget
	{
		@UiField
		Element first;
		@UiField
		Element prev;
		@UiField
		Element position;
		@UiField
		Element next;
		@UiField
		Element last;

		public PagingWidget()
		{
			setElement( uiBinder.createAndBindUi( this ) );

			DOM.setEventListener( first, firstEvent );
			DOM.sinkEvents( first, Event.ONCLICK );

			DOM.setEventListener( prev, prevEvent );
			DOM.sinkEvents( prev, Event.ONCLICK );

			DOM.setEventListener( next, nextEvent );
			DOM.sinkEvents( next, Event.ONCLICK );

			DOM.setEventListener( last, lastEvent );
			DOM.sinkEvents( last, Event.ONCLICK );
		}

		public void update()
		{
			position.setInnerHTML( "&nbsp;<b>" + start + " - " + end + "</b> of <b>" + nb + "</b>&nbsp;" );

			if( fFirst )
				first.getStyle().clearDisplay();
			else
				first.getStyle().setDisplay( Display.NONE );

			if( fPrev )
				prev.getStyle().clearDisplay();
			else
				prev.getStyle().setDisplay( Display.NONE );

			if( fNext )
				next.getStyle().clearDisplay();
			else
				next.getStyle().setDisplay( Display.NONE );

			if( fLast )
				last.getStyle().clearDisplay();
			else
				last.getStyle().setDisplay( Display.NONE );
		}
	}

	public Widget createPagingWidget()
	{
		PagingWidget w = new PagingWidget();
		widgets.add( w );
		w.update();

		return w;
	}

	EventListener firstEvent = new EventListener()
	{
		@Override
		public void onBrowserEvent( Event event )
		{
			if( event.getTypeInt() != Event.ONCLICK )
				return;

			if( callback != null )
				callback.onPagerWant( 0, nbPerPage - 1 );

			event.preventDefault();
			event.stopPropagation();
		}
	};

	EventListener prevEvent = new EventListener()
	{
		@Override
		public void onBrowserEvent( Event event )
		{
			if( event.getTypeInt() != Event.ONCLICK )
				return;

			int nextPos = currentPosition - nbPerPage;
			if( callback != null )
				callback.onPagerWant( nextPos, nextPos + nbPerPage - 1 );

			event.preventDefault();
			event.stopPropagation();
		}
	};

	EventListener nextEvent = new EventListener()
	{
		@Override
		public void onBrowserEvent( Event event )
		{
			if( event.getTypeInt() != Event.ONCLICK )
				return;

			int nextPos = currentPosition + nbPerPage;
			if( callback != null )
				callback.onPagerWant( nextPos, nextPos + nbPerPage - 1 );

			event.preventDefault();
			event.stopPropagation();
		}
	};

	EventListener lastEvent = new EventListener()
	{
		@Override
		public void onBrowserEvent( Event event )
		{
			if( event.getTypeInt() != Event.ONCLICK )
				return;

			int nextPos = lastPosition - (lastPosition % nbPerPage);
			if( callback != null )
				callback.onPagerWant( nextPos, nextPos + nbPerPage - 1 );

			event.preventDefault();
			event.stopPropagation();
		}
	};
}