package com.hexa.client.ui;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class SidePanel extends SimplePanel implements ClickHandler
{
	Element contentElement;
	String contentElementId = DOM.createUniqueId();
	
	Callback callback;
	
	boolean fShowState = false;
	
	public interface Callback
	{
		void onWantClose();
	}
	
	public SidePanel( Callback callback )
	{
		super( DOM.createDiv() );
		getElement().setClassName( "SidePanel" );
		
		this.callback = callback;
		
		contentElement = DOM.createDiv();
		contentElement.setId( contentElementId );
		Element e = getElement();
		e.appendChild( contentElement );

		CloseButton closeButton = new CloseButton();
		
		getElement().getStyle().setDisplay( Display.NONE );
		RootPanel.get().add( this );
		
		attachCloseWidget( closeButton );
	}
	
	class CloseButton extends Widget implements ClickHandler
	{
		public CloseButton()
		{
			Element e = DOM.createDiv();
			e.setClassName( "SidePanelCloseButton" );
			
			setElement( e );
			
			addDomHandler( this, ClickEvent.getType() );
		}

		@Override
		public void onClick(ClickEvent event)
		{
			callback.onWantClose();
		}
	}
	
	Widget closeWidget = null;
	private void attachCloseWidget( Widget w )
	{
		w.removeFromParent();
		
		if( closeWidget != null )
		{
			try {
				orphan( closeWidget );
			} finally {
				getElement().removeChild( closeWidget.getElement() );
				
				closeWidget = null;
			}
		}
		
		closeWidget = w;
		
		if( w != null )
		{
			DOM.appendChild( getElement(), w.getElement() );
			
			adopt( w );
		}
	}
	
	public void show()
	{
		if( fShowState )
			return;
		
		Animation anim = new Animation() {
			protected void onUpdate(double progress)
			{
				getElement().getStyle().setOpacity( interpolate(progress) );
			}
			
			protected void onComplete()
			{
				fShowState = true;
			}
		};
		anim.run( 1000 );
		getElement().getStyle().setDisplay( Display.BLOCK );
	}
	
	public void hide()
	{
		if( ! fShowState )
			return;
		
		Animation anim = new Animation() {
			protected void onUpdate(double progress)
			{
				getElement().getStyle().setOpacity( 1.0 - interpolate(progress) );
			}
			
			protected void onComplete()
			{
				getElement().getStyle().setDisplay( Display.NONE );
				fShowState = false;
			}
		};
		anim.run( 1000 );
	}
	
	protected com.google.gwt.user.client.Element getContainerElement()
	{
		return DOM.getElementById( contentElementId );
	}
	
	public void kill()
	{
		RootPanel.get().remove( this );
	}

	@Override
	public void onClick(ClickEvent event)
	{
		
		//if( event.getSource() == closeButton )
		//{
		//	Window.alert( event.getNativeEvent().toString() );
		//}
	}
}
