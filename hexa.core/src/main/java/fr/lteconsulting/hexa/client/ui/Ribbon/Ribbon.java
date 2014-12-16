/**
 *
 */
package fr.lteconsulting.hexa.client.ui.Ribbon;

import java.util.ArrayList;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
//import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Arnaud
 *
 */
public class Ribbon extends Composite implements RibbonView
{
	public interface RibbonCallback
	{
		void onRibbonChange( Object obj );
	}

	RibbonCallback callback;

	ArrayList<Widget> m_tabs;
	SimplePanel m_tabPanel;

	TabBar m_tabBar;

	RibbonDef m_conf;

	public Ribbon( RibbonDef conf, final RibbonCallback callback )
	{
		this.callback = callback;
		m_conf = conf;

		FlowPanel panel = new FlowPanel();

		m_tabBar = new TabBar( new TabBar.Callback()
		{
			@Override
			public void onSelection( int index )
			{
				m_tabPanel.setWidget( m_tabs.get( index ) );
			}
		} );
		panel.add( m_tabBar );

		m_tabPanel = new SimplePanel();
		panel.add( m_tabPanel );

		/*
		 * Definition of the Ribbon
		 */

		m_tabs = new ArrayList<Widget>();
		for( int i = 0; i < conf.tabs.size(); i++ )
		{
			m_tabBar.addTab( conf.tabs.get( i ).name );

			final ButtonBar bar = new ButtonBar();
			for( int j = 0; j < conf.tabs.get( i ).buttons.size(); j++ )
			{
				final Object obj = conf.tabs.get( i ).buttons.get( j ).obj;

				Button button = new Button( conf.tabs.get( i ).buttons.get( j ).name, new ClickHandler()
				{
					@Override
					public void onClick( ClickEvent event )
					{
						callback.onRibbonChange( obj );
					}
				} );

				bar.addButton( button, obj );
			}
			m_tabs.add( bar );
		}

		initWidget( panel );
		panel.setSize( "100%", "100%" );

		setStyleName( "Ribbon" );
		m_tabPanel.setStyleName( "RibbonPanel" );

		if( m_tabBar.getTabCount() > 0 )
			m_tabBar.selectTab( 0 );
	}

	public void setEnabled( Object obj, boolean fEnabled )
	{
		for( int i = 0; i < m_conf.tabs.size(); i++ )
		{
			ButtonBar bar = (ButtonBar) m_tabs.get( i );
			bar.setEnabled( obj, fEnabled );
		}
	}

	@Override
	public void selectButton( Object obj )
	{
		for( int i = 0; i < m_conf.tabs.size(); i++ )
		{
			ButtonBar bar = (ButtonBar) m_tabs.get( i );
			bar.selectButton( obj );

			for( int j = 0; j < m_conf.tabs.get( i ).buttons.size(); j++ )
			{
				if( m_conf.tabs.get( i ).buttons.get( j ).obj == obj )
				{
					m_tabBar.selectTab( i );
				}
			}
		}
	}

	@Override
	public void setTabText( String id, String text )
	{
		int index = -1;
		for( int i = 0; i < m_conf.tabs.size(); i++ )
			if( m_conf.tabs.get( i ).id.equals( id ) )
			{
				index = i;
				break;
			}
		if( index < 0 )
			return;

		m_tabBar.setTabText( index, text );
	}

	@Override
	public void setButtonText( Object obj, String text )
	{
		for( int i = 0; i < m_conf.tabs.size(); i++ )
		{
			ButtonBar bar = (ButtonBar) m_tabs.get( i );
			bar.setButtonText( obj, text );
		}
	}
}

class TabBar extends Widget
{
	private Callback callback = null;

	private Element selected = null;
	private Element clear = null;

	interface Callback
	{
		void onSelection( int index );
	}

	public TabBar( Callback callback )
	{
		this.callback = callback;

		Element div = DOM.createDiv();

		setElement( div );

		setStylePrimaryName( "RibbonTab" );

		clear = DOM.createDiv();
		clear.getStyle().setProperty( "clear", "both" );
		div.appendChild( clear );

		addDomHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				Element tgt = event.getNativeEvent().getEventTarget().cast();
				if( tgt.getParentElement() != getElement() )
					return;

				int index = DOM.getChildIndex( getElement(), tgt );
				if( index >= getTabCount() )
					return;

				selectTab( index );
			}
		}, ClickEvent.getType() );
	}

	public void addTab( String text )
	{
		Element button = DOM.createDiv();

		button.setClassName( getStylePrimaryName() + "-Item" );

		button.setInnerText( text );

		getElement().insertBefore( button, clear );
	}

	public void setTabText( int index, String text )
	{
		Element tab = getTab( index );
		if( tab == null )
			return;

		tab.setInnerText( text );
	}

	public String getTabText( int index )
	{
		Element tab = getTab( index );
		if( tab == null )
			return null;

		return tab.getInnerText();
	}

	public int getTabCount()
	{
		return getElement().getChildCount() - 1;
	}

	public void selectTab( int index )
	{
		selectTab( index, true );
	}

	public void selectTab( int index, boolean fFireEvent )
	{
		Element toSelect = getTab( index );

		if( selected == toSelect )
			return;

		if( selected != null )
			selected.removeClassName( getStylePrimaryName() + "-Item-selected" );

		if( toSelect != null )
			toSelect.addClassName( getStylePrimaryName() + "-Item-selected" );

		selected = toSelect;

		if( fFireEvent )
			callback.onSelection( index );
	}

	private Element getTab( int index )
	{
		if( index >= getElement().getChildCount() - 1 )
			return null;

		return Element.as( getElement().getChild( index ) ).cast();
	}
}

class ButtonBar extends Composite
{
	FlowPanel panel = new FlowPanel();

	public ButtonBar()
	{
		initWidget( panel );
	}

	public void addButton( Button button, Object obj )
	{
		button.getElement().setPropertyObject( "object", obj );

		panel.add( button );
	}

	public void setEnabled( Object obj, boolean fEnabled )
	{
		for( int i = 0; i < panel.getWidgetCount(); i++ )
		{
			Button b = (Button) panel.getWidget( i );
			if( b.getElement().getPropertyObject( "object" ) == obj )
				b.setEnabled( fEnabled );
		}
	}

	public void selectButton( Object obj )
	{
		for( int i = 0; i < panel.getWidgetCount(); i++ )
		{
			Button b = (Button) panel.getWidget( i );
			if( b.getElement().getPropertyObject( "object" ) == obj )
				b.addStyleName( "Ribbon-selected" );
			else
				b.removeStyleName( "Ribbon-selected" );
		}
	}

	public void setButtonText( Object obj, String text )
	{
		for( int i = 0; i < panel.getWidgetCount(); i++ )
		{
			Button b = (Button) panel.getWidget( i );
			if( b.getElement().getPropertyObject( "object" ) == obj )
				b.setText( text );
		}
	}

	public String getButtonText( Object obj )
	{
		for( int i = 0; i < panel.getWidgetCount(); i++ )
		{
			Button b = (Button) panel.getWidget( i );
			if( b.getElement().getPropertyObject( "object" ) == obj )
				return b.getText();
		}

		return null;
	}
}