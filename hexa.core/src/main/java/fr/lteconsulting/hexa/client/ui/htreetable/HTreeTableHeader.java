package fr.lteconsulting.hexa.client.ui.htreetable;

import java.util.ArrayList;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public class HTreeTableHeader extends ComplexPanel implements ClickHandler
{
	ItemImpl m_rootItem = new ItemImpl( null );
	Element m_thead;
	ArrayList<Element> m_rows = new ArrayList<Element>();

	ArrayList<RowHdrImpl> m_rowHeaders = new ArrayList<RowHdrImpl>();

	public HTreeTableHeader()
	{
		m_thead = DOM.createTHead();
		setElement( m_thead );

		addDomHandler( this, ClickEvent.getType() );
	}

	@Override
	public void onClick( ClickEvent event )
	{
		event.stopPropagation();
		event.preventDefault();
	}

	// means to update the col spans...
	public void draw()
	{
		m_rootItem.updateColSpans();

		String nbRows = String.valueOf( m_rows.size() );
		int nbHeaders = m_rowHeaders.size();

		for( int i = 0; i < nbHeaders; i++ )
			m_rowHeaders.get( i ).m_td.setAttribute( "rowspan", nbRows );
	}

	public class RowHdrImpl
	{
		Element m_tr;
		Element m_td;

		public void setText( String text )
		{
			m_td.setInnerText( text );
		}
	}

	public RowHdrImpl addRowHeader()
	{
		RowHdrImpl item = new RowHdrImpl();
		m_rowHeaders.add( item );

		if( m_rows.isEmpty() )
		{
			Element tr = DOM.createTR();
			m_rows.add( tr );
			m_thead.appendChild( tr );
		}

		item.m_tr = m_rows.get( 0 );

		item.m_td = DOM.createTH();
		item.m_tr.appendChild( item.m_td );

		return item;
	}

	public class ItemImpl
	{
		int m_level = -2;
		int m_nbLeaves = -1;

		ItemImpl m_parent;
		ArrayList<ItemImpl> m_childs = new ArrayList<ItemImpl>();

		Element m_tr = null;
		Element m_td = null;

		ItemImpl( ItemImpl parent )
		{
			m_parent = parent;
			if( m_parent != null )
				m_parent.m_childs.add( this );
		}

		public void setText( String text )
		{
			m_td.setInnerText( text );
		}

		public void setWidget( Widget w )
		{
			setText( "" );
			add( w, m_td );
		}

		void updateColSpans()
		{
			for( ItemImpl c : m_childs )
			{
				int nbLeaf = c.getNbLeaves();
				c.m_td.setAttribute( "colspan", String.valueOf( nbLeaf ) );
				c.updateColSpans();
			}
		}

		int getNbLeaves()
		{
			if( m_nbLeaves == -1 )
			{
				if( m_childs.isEmpty() )
					m_nbLeaves = 1;
				else
				{
					int nb = 0;
					for( ItemImpl c : m_childs )
						nb += c.getNbLeaves();
					m_nbLeaves = nb;
				}
			}

			return m_nbLeaves;
		}

		int getLevel()
		{
			if( m_level == -2 )
			{
				if( m_parent == null )
					m_level = -1;
				else
					m_level = 1 + m_parent.getLevel();
			}

			return m_level;
		}

		ItemImpl getPreviousAtSameLevel()
		{
			return getPreviousAtLevel( getLevel() );
		}

		ItemImpl getLastDescendant( int level )
		{
			int myLevel = getLevel();
			if( level > myLevel )
				return null;
			if( myLevel == level )
				return this;

			int i = m_childs.size() - 1;
			while( i >= 0 )
			{
				ItemImpl t = getLastDescendant( level );
				if( t != null )
					return t;
				i--;
			}

			return null;
		}

		ItemImpl getPreviousAtLevel( int level )
		{
			if( m_parent == null )
				return null;

			ItemImpl position = this;
			ItemImpl ancestor = m_parent;
			while( ancestor != null )
			{
				int prevIdx = ancestor.m_childs.indexOf( position ) - 1;
				while( prevIdx >= 0 )
				{
					// test getLastDescendant( level )
					ItemImpl t = ancestor.m_childs.get( prevIdx ).getLastDescendant( level );
					if( t != null )
						return t;
					prevIdx--;
				}

				// did not find, so go to superior parent
				position = ancestor;
				ancestor = ancestor.m_parent;
			}

			// did not find, so return null
			return null;
		}
	}

	public ItemImpl addItem( ItemImpl parentItem )
	{
		ItemImpl item = new ItemImpl( parentItem == null ? m_rootItem : parentItem );
		int level = item.getLevel();

		while( m_rows.size() < level + 1 )
		{
			Element tr = DOM.createTR();
			m_thead.appendChild( tr );
			m_rows.add( tr );
		}

		item.m_tr = m_rows.get( level );

		// th is inserted after the last child of our parent
		item.m_td = DOM.createTH();
		ItemImpl previousItem = item.getPreviousAtSameLevel();
		if( previousItem != null )
			item.m_tr.insertAfter( item.m_td, previousItem.m_td );
		else
			item.m_tr.appendChild( item.m_td );

		return item;
	}
}
