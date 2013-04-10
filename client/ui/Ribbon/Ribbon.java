/**
 * 
 */
package com.hexa.client.ui.Ribbon;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.VerticalPanel;
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
		
		HorizontalPanel hPanel = new HorizontalPanel();
		
		final VerticalPanel panel = new VerticalPanel();
		
		m_tabBar = new TabBar();
		panel.add( m_tabBar );
		
		m_tabPanel = new SimplePanel();
		panel.add( m_tabPanel );
		
		/*
		 * Definition of the Ribbon
		 */
		
		m_tabs = new ArrayList<Widget>();
		for( int i=0; i<conf.tabs.size(); i++ )
		{
			m_tabBar.addTab( conf.tabs.get(i).name );
		
			FlexTable table = new FlexTable();
			for( int j=0; j<conf.tabs.get(i).buttons.size(); j++ )
			{
				final Object obj = conf.tabs.get(i).buttons.get(j).obj;
				Button button = new Button( conf.tabs.get(i).buttons.get(j).name, new ClickHandler()
				{
					public void onClick( ClickEvent event )
					{
						callback.onRibbonChange( obj );
					}
				} );
				
				table.setWidget( 0, j, button );
			}
			m_tabs.add( table );
		}
		
		// listeners for TabBar
		m_tabBar.addSelectionHandler( new SelectionHandler<java.lang.Integer>()
		{
			public void onSelection( SelectionEvent<java.lang.Integer> event )
			{
				int tabIndex = event.getSelectedItem();
				m_tabPanel.setWidget( m_tabs.get( tabIndex ) );
			}
		} );
		
		panel.setWidth( "100%" );
		hPanel.setWidth( "100%" );
		if( conf.logo != null )
			hPanel.add( new Image( conf.logo ) );
		hPanel.add( panel );
		if( conf.additionalWidget != null )
			hPanel.add( conf.additionalWidget );
		
		initWidget( hPanel );
		
		setStyleName( "Ribbon" );
		addStyleName( "FramedPanel" );
		m_tabPanel.setStyleName( "RibbonPanel" );
		
		m_tabBar.selectTab( 0 );
	}
	
	public void setEnabled( Object obj, boolean fEnabled )
	{
		for( int i=0; i<m_conf.tabs.size(); i++ )
		{
			FlexTable table = (FlexTable)m_tabs.get( i );
			for( int j=0; j<m_conf.tabs.get(i).buttons.size(); j++ )
			{
				Button button = (Button)table.getWidget( 0, j );
				
				if( m_conf.tabs.get(i).buttons.get(j).obj == obj )
				{
					button.setEnabled( fEnabled );
					return;
				}
			}
		}
	}
	
	public void selectButton( Object obj )
	{
		for( int i=0; i<m_conf.tabs.size(); i++ )
		{
			FlexTable table = (FlexTable)m_tabs.get( i );
			for( int j=0; j<m_conf.tabs.get(i).buttons.size(); j++ )
			{
				Button button = (Button)table.getWidget( 0, j );
				
				if( m_conf.tabs.get(i).buttons.get(j).obj == obj )
				{
					m_tabBar.selectTab( i );
					button.addStyleName( "Ribbon-selected" );
				}
				else
				{
					button.removeStyleName( "Ribbon-selected" );
				}
			}
		}
	}
	
	public void setTabText( String id, String text )
	{
		int index = -1;
		for( int i=0; i<m_conf.tabs.size(); i++ )
			if( m_conf.tabs.get(i).id.equals( id ) )
			{
				index = i;
				break;
			}
		if( index < 0 )
			return;
		
		m_tabBar.setTabText( index, text );
	}
	
	public void setButtonText( Object obj, String text )
	{
		for( int i=0; i<m_conf.tabs.size(); i++ )
		{
			FlexTable table = (FlexTable)m_tabs.get( i );
			for( int j=0; j<m_conf.tabs.get(i).buttons.size(); j++ )
			{
				Button button = (Button)table.getWidget( 0, j );
				
				if( m_conf.tabs.get(i).buttons.get(j).obj == obj )
				{
					button.setText( text );
					return;
				}
			}
		}
	}
}
