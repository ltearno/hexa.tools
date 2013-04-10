package com.hexa.client.tools;

import java.util.ArrayList;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Widget;

public class Pager
{
	public interface PagerCallback
	{
		public void onPagerWant( Object cookie, int startPosition, int endPosition );
	}
	
	PagerCallback callback = null;
	Object cookie = null;
	
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
	
	public Pager( PagerCallback callback, Object cookie )
	{
		setCallback( callback, cookie );
	}
	
	public void setCallback( PagerCallback callback, Object cookie )
	{
		this.callback = callback;
		this.cookie = cookie;
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
		fFirst = currentPosition >= 2*nbPerPage;
		fPrev = start > 1;
		fNext = (currentPosition+nbDisplayed) <= lastPosition;
		fLast = (currentPosition+nbDisplayed) <= lastPosition - nbPerPage;
		
		updateWidgets();
	}
	
	ArrayList<PagingWidget> widgets = new ArrayList<PagingWidget>();
	
	void updateWidgets()
	{
		for( PagingWidget w : widgets )
			w.update();
	}
	
	class PagingWidget extends Widget
	{
		Element span;
		Element first;
		Element prev;
		Element position;
		Element next;
		Element last;
		
		public PagingWidget()
		{
			span = DOM.createSpan();
			
			setElement( span );
			span.getStyle().setFloat( Style.Float.RIGHT );
			
			first = DOM.createAnchor();
			first.setInnerText( "First" );
			first.setAttribute( "href", "#first" );
			span.appendChild( first );
			
			addSpace();
			
			prev = DOM.createAnchor();
			prev.setInnerText( "Prev" );
			prev.setAttribute( "href", "#prev" );
			span.appendChild( prev );
			
			position = DOM.createSpan();
			span.appendChild( position );
			
			next = DOM.createAnchor();
			next.setInnerText( "Next" );
			next.setAttribute( "href", "#next" );
			span.appendChild( next );
			
			addSpace();
			
			last = DOM.createAnchor();
			last.setInnerText( "Last" );
			last.setAttribute( "href", "#last" );
			span.appendChild( last );
			
			
			
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
			JQuery.jqHtml( position, "&nbsp;<b>"+start+" - "+end+"</b> of <b>"+nb+"</b>&nbsp;" );
			
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
		
		private void addSpace()
		{
			Element dum = DOM.createSpan();
			dum.setInnerHTML( "&nbsp;" );
			span.appendChild( dum );
		}
	}
	
	public Widget createPagingWidget()
	{
		PagingWidget w = new PagingWidget();
		widgets.add( w );
		w.update();
		
		return w;
	}
	
	EventListener firstEvent = new EventListener() {
		public void onBrowserEvent(Event event)
		{
			if( event.getTypeInt() != Event.ONCLICK )
				return;
			
			if( callback != null )
				callback.onPagerWant( cookie, 0, nbPerPage - 1 );
			
			event.preventDefault();
			event.stopPropagation();
		}
	};
	
	EventListener prevEvent = new EventListener() {
		public void onBrowserEvent(Event event)
		{
			if( event.getTypeInt() != Event.ONCLICK )
				return;
			
			int nextPos = currentPosition - nbPerPage;
			if( callback != null )
				callback.onPagerWant( cookie, nextPos, nextPos + nbPerPage - 1 );
			
			event.preventDefault();
			event.stopPropagation();
		}
	};
	
	EventListener nextEvent = new EventListener() {
		public void onBrowserEvent(Event event)
		{
			if( event.getTypeInt() != Event.ONCLICK )
				return;
			
			int nextPos = currentPosition + nbPerPage;
			if( callback != null )
				callback.onPagerWant( cookie, nextPos, nextPos + nbPerPage - 1 );
			
			event.preventDefault();
			event.stopPropagation();
		}
	};
	
	EventListener lastEvent = new EventListener() {
		public void onBrowserEvent(Event event)
		{
			if( event.getTypeInt() != Event.ONCLICK )
				return;
			
			int nextPos = lastPosition - ( lastPosition % nbPerPage);
			if( callback != null )
				callback.onPagerWant( cookie, nextPos, nextPos + nbPerPage - 1 );
			
			event.preventDefault();
			event.stopPropagation();
		}
	};
}