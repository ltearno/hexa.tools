package com.hexa.client.ui;

import com.hexa.client.interfaces.IBkgndStackPanel;
import com.hexa.client.interfaces.IStackPanel;
import com.hexa.client.interfaces.IStackPanelRow;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class LayeredAbsoluteStackPanel extends Composite implements MouseWheelHandler, MouseMoveHandler, KeyUpHandler
{
	private IBkgndStackPanel.Callback m_callback = null;
	private Object m_cookie = null;
	
	private FocusPanel m_focusPanel = new FocusPanel();
	private AbsolutePanel m_canvas = new AbsolutePanel();
	
	private AbsolutePanel m_backgroundCanvas = new AbsolutePanel();
	
	private FlowPanel m_stacksPanel = new FlowPanel();
	
	private AbsoluteStackPanel m_stackPanel = new AbsoluteStackPanel();
	
	int width = 0;
	
	int scrollBarWidth = 25; // TODO this is a guess, how to get that dimension ?
	int spaceHeight = 50;
	private ScrollPanel m_scroll = new ScrollPanel();
	
	public LayeredAbsoluteStackPanel()
	{
		m_stacksPanel.add( m_stackPanel );
		
		m_scroll.setWidget( m_stacksPanel );
		
		m_canvas.add( m_backgroundCanvas, 0, 0 );
		m_canvas.add( m_scroll, 0, spaceHeight );
		//m_canvas.add( m_stackPanel, 0, 50 );
		
		m_focusPanel.add( m_canvas );
		m_focusPanel.addMouseWheelHandler( this );
		m_focusPanel.addMouseMoveHandler( this );
		m_focusPanel.addKeyUpHandler( this );
		m_focusPanel.addStyleName( "AroundPanel" );
		
		initWidget( m_focusPanel );
	}
	
	public void setCallback( IBkgndStackPanel.Callback callback, Object cookie )
	{
		m_callback = callback;
		m_cookie = cookie;
	}
	
	public void setSize( int width, int height )
	{
		this.width = width;
		
		String w = width + "px";
		String h = height + "px";
		
		m_backgroundCanvas.setPixelSize( width - 2*scrollBarWidth, height );
		m_scroll.setPixelSize( width, height - spaceHeight );
		m_stacksPanel.setWidth( (width-scrollBarWidth)+"px" );
		m_stackPanel.setWidth( (width-scrollBarWidth)+"px" );
		//m_stackPanel.setPixelSize( width, height );
		
		m_focusPanel.setWidth( w );
		m_focusPanel.setHeight( h );
	
		m_canvas.setWidth( w );
		m_canvas.setHeight( h );
	}
	
	public void clearBackground()
	{
		m_backgroundCanvas.clear();
	}
	
	IBkgndStackPanel.Background bgProxy = new IBkgndStackPanel.Background() {
		public void setWidgetPosition(Widget w, int x, int y) {
			m_backgroundCanvas.setWidgetPosition(w, x, y);
		}
		public void removeItem(Widget w) {
			m_backgroundCanvas.remove( w );
		}
		public void clearAll() {
			m_backgroundCanvas.clear();
		}
		public void add(Widget w, int x, int y) {
			m_backgroundCanvas.add( w, x, y );
		}
		public void add(Widget w) {
			m_backgroundCanvas.add( w );
		}
	};
	public IBkgndStackPanel.Background getBackgroundCanvas()
	{
		return bgProxy;
	}
	
	public IStackPanel getStackPanel()
	{
		return m_stackPanel;
	}
	
	public interface IStackPanelSized extends IStackPanel
	{
		void setHeight( int height );
	}
	
	public IStackPanelSized createSizedPanel( int height )
	{
		final AbsoluteStackPanel panel = new AbsoluteStackPanel();
		final ScrollPanel scroll = new ScrollPanel( panel );
		
		scroll.setPixelSize( width-scrollBarWidth, height );
		panel.setWidth( (width-2*scrollBarWidth)+"px" );
		m_stacksPanel.add( scroll );
		
		return new IStackPanelSized() {
			public void clear() {
				panel.clear();
			}
			
			public IStackPanelRow addRow() {
				return panel.addRow();
			}
			
			public void setHeight(int height) {
				scroll.setHeight( height+"px" );
			}
		};
	}
	
	public IStackPanel createPanel()
	{
		AbsoluteStackPanel panel = new AbsoluteStackPanel();
		
		panel.setWidth( (width-scrollBarWidth)+"px" );
		m_stacksPanel.add( panel );
		
		return panel;
	}

	@Override
	public void onMouseWheel( MouseWheelEvent event )
	{
		if( m_callback == null )
			return;
		
		event.preventDefault();
		
		int x = event.getRelativeX( m_canvas.getElement() );
		
		int delta = event.getDeltaY();
		if( delta > 0 ) delta = 1;
		else if( delta < 0 ) delta = -1;
		
		m_callback.onMouseWheel( m_cookie, event, x, delta );
	}

	@Override
	public void onMouseMove( MouseMoveEvent event )
	{
		if( m_callback == null )
			return;
		
		event.preventDefault();
		
		int x = event.getRelativeX( m_canvas.getElement() );
		
		m_callback.onMouseMove( m_cookie, x );
	}

	@Override
	public void onKeyUp(KeyUpEvent event)
	{
		if( m_callback == null )
			return;
		
		event.preventDefault();
		
		m_callback.onKeyUp( m_cookie, event );
	}
}
