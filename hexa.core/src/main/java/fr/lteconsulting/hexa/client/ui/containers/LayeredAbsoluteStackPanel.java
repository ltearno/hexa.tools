package fr.lteconsulting.hexa.client.ui.containers;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.interfaces.IBkgndStackPanel;
import fr.lteconsulting.hexa.client.interfaces.IStackPanel;
import fr.lteconsulting.hexa.client.interfaces.IStackPanelRow;

public class LayeredAbsoluteStackPanel extends ResizeComposite implements MouseWheelHandler, MouseMoveHandler, KeyUpHandler
{
	private IBkgndStackPanel.Callback m_callback = null;
	private Object m_cookie = null;

	private FocusLayoutPanel m_focusPanel = new FocusLayoutPanel();
	private AbsolutePanel m_canvas = new AbsolutePanel();

	private AbsolutePanel m_backgroundCanvas = new AbsolutePanel();

	private FlowPanel m_stacksPanel = new FlowPanel();

	private AbsoluteStackPanel m_stackPanel = new AbsoluteStackPanel();
	
	int spaceHeight = 50;
	private ScrollPanel m_scroll = new ScrollPanel();

	public LayeredAbsoluteStackPanel()
	{
		m_stacksPanel.add( m_stackPanel );
		m_stacksPanel.setWidth( "100%" );

		m_scroll.setWidget( m_stacksPanel );
		m_scroll.setSize( "100%", "100%" );

		m_backgroundCanvas.setSize( "100%", "100%" );
		m_canvas.add( m_backgroundCanvas, 0, 0 );
		m_canvas.add( m_scroll, 0, spaceHeight );
		m_canvas.setSize( "100%", "100%" );

		m_focusPanel.add( m_canvas );
		m_focusPanel.addMouseWheelHandler( this );
		m_focusPanel.addMouseMoveHandler( this );
		m_focusPanel.addKeyUpHandler( this );
		m_focusPanel.addStyleName( "AroundPanel" );
		m_focusPanel.setSize( "100%", "100%" );

		initWidget( m_focusPanel );
	}
	
	static class FocusLayoutPanel extends FocusPanel implements RequiresResize
	{
		@Override
		public void onResize()
		{
			Widget child = getWidget();
			if( child instanceof RequiresResize )
				((RequiresResize) child).onResize();
		}
	}

	public void setCallback( IBkgndStackPanel.Callback callback, Object cookie )
	{
		m_callback = callback;
		m_cookie = cookie;
	}
	
	@Override
	public void onResize()
	{
		super.onResize();
	}

	public void clearBackground()
	{
		m_backgroundCanvas.clear();
	}

	IBkgndStackPanel.Background bgProxy = new IBkgndStackPanel.Background()
	{
		@Override
		public void setWidgetPosition( Widget w, int x, int y )
		{
			m_backgroundCanvas.setWidgetPosition( w, x, y );
		}

		@Override
		public void removeItem( Widget w )
		{
			m_backgroundCanvas.remove( w );
		}

		@Override
		public void clearAll()
		{
			m_backgroundCanvas.clear();
		}

		@Override
		public void add( Widget w, int x, int y )
		{
			m_backgroundCanvas.add( w, x, y );
		}

		@Override
		public void add( Widget w )
		{
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

		scroll.setWidth( "100%" );
		panel.setWidth( "100%" );
		scroll.setHeight( height + "px" );
		m_stacksPanel.add( scroll );

		return new IStackPanelSized()
		{
			@Override
			public void clear()
			{
				panel.clear();
			}

			@Override
			public IStackPanelRow addRow()
			{
				return panel.addRow();
			}

			@Override
			public void setHeight( int height )
			{
				scroll.setHeight( height + "px" );
			}
		};
	}

	public IStackPanel createPanel()
	{
		AbsoluteStackPanel panel = new AbsoluteStackPanel();
		panel.setWidth( "100%" );
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
		if( delta > 0 )
			delta = 1;
		else if( delta < 0 )
			delta = -1;

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
	public void onKeyUp( KeyUpEvent event )
	{
		if( m_callback == null )
			return;

		event.preventDefault();

		m_callback.onKeyUp( m_cookie, event );
	}
}
