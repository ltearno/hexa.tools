package com.hexa.client.ui;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class SideButton extends Widget implements MouseOverHandler, MouseOutHandler, MouseUpHandler
{
	ClickHandler clickHandler = null;
	String text = "";
	
	public SideButton( String text )
	{
		Element e = DOM.createDiv();
		e.setClassName( "SideButton" );
		setElement( e );
		
		addDomHandler( this, MouseOverEvent.getType() );
		addDomHandler( this, MouseOutEvent.getType() );
		addDomHandler( this, MouseUpEvent.getType() );
		
		setText( text );
	}
	
	public void show()
	{
		RootPanel.get().add( this );
	}
	
	public void setText( String text )
	{
		this.text = text;
		getElement().setInnerHTML( getHTML() );
	}
	
	private String getHTML()
	{
		return "<object data=\"data:image/svg+xml,&lt;svg xmlns=&quot;http://www.w3.org/2000/svg&quot;&gt;&lt;rect x=&quot;0&quot; y=&quot;0&quot; width=&quot;27px&quot; height=&quot;162px&quot; fill=&quot;rgb(0, 0, 0)&quot; stroke=&quot;none&quot;&gt;&lt;/rect&gt;&lt;text  x=&quot;-0&quot; y=&quot;0&quot; font-family=&quot;Trebuchet MS,Arial sans-serif&quot;  fill=&quot;rgb(255, 255, 255)&quot; font-size=&quot;16&quot;  style=&quot;cursor:pointer !important; text-anchor: start; dominant-baseline: hanging&quot; transform=&quot;rotate(90, 13.5, 13.5)&quot; text-rendering=&quot;optimizeSpeed&quot;&gt;"+text+"&lt;/text&gt;&lt;/svg&gt;\" type=\"image/svg+xml\" style=\"height: 162px; width: 27px;\" class=\"flip_label\"></object>";
	}
	
	class MyAnim extends Animation
	{
		boolean way = true;
		
		public void run( boolean way )
		{
			this.way = way;
			
			getElement().getStyle().getLeft();
			
			super.run( 500 );
		}
		
		protected void onUpdate( double progress )
		{
			if( way )
				getElement().getStyle().setLeft( -35 + 35*interpolate(progress), Unit.PX );
			else
				getElement().getStyle().setLeft( - 35*interpolate(progress), Unit.PX );
		}
	}
	MyAnim anim = new MyAnim();

	@Override
	public void onMouseOver(MouseOverEvent event)
	{
		anim.run( true );
		//getElement().getStyle().setLeft( 0, Unit.PX );
	}

	@Override
	public void onMouseOut(MouseOutEvent event)
	{
		anim.run( false );
		//getElement().getStyle().setLeft( -35, Unit.PX );
	}
	
	public void addClickHandler( ClickHandler handler )
	{
		clickHandler = handler;
	}

	@Override
	public void onMouseUp(MouseUpEvent event)
	{
		if( clickHandler != null )
			clickHandler.onClick( null );
	}
}
