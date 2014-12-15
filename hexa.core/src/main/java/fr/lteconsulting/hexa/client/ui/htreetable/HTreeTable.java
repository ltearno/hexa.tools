package fr.lteconsulting.hexa.client.ui.htreetable;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.css.HexaCss;
import fr.lteconsulting.hexa.client.tools.HexaTools;

public class HTreeTable extends ComplexPanel implements ClickHandler
{
	interface Css extends HexaCss
	{
		static final Css CSS = GWT.create( Css.class );

		String main();
	}

	public interface Callback
	{
		void onHTableClick( Object item );
	}

	HTItem m_root = new HTItem( HTItemType.Item, null );

	Element m_table;

	Callback m_callback = null;

	Widget headerWidget = null;

	HashMap<Element, HTItem> cells = new HashMap<Element, HTItem>();

	@UiConstructor
	public HTreeTable()
	{
		m_table = DOM.createTable();
		m_table.setAttribute( "border", "1" );
		m_table.addClassName( Css.CSS.main() );

		setElement( m_table );

		addDomHandler( this, ClickEvent.getType() );
	}

	public void setHeaderWidget( Widget w )
	{
		if( headerWidget != null )
			remove( headerWidget );

		headerWidget = w;

		add( headerWidget, m_table );
	}

	public void setCallback( Callback callback )
	{
		m_callback = callback;
	}

	@Override
	public void onClick( ClickEvent event )
	{
		if( m_callback == null )
			return;

		HTItem item = getHTItemForEvent( event );
		if( item == null )
		{
			HexaTools.alert( "getHTItemForEvent return null in HTreeTable.java" );
			return;
		}

		m_callback.onHTableClick( item );
	}

	private HTItem getHTItemForEvent( ClickEvent event )
	{
		Element td = getEventTargetCell( Event.as( event.getNativeEvent() ) );
		if( td == null )
			return null;

		return cells.get( td );
	}

	protected Element getEventTargetCell( Event event )
	{
		Element td = DOM.eventGetTarget( event );

		for( ; td != null; td = DOM.getParent( td ) )
		{
			// If it's a TD, it might be the one we're looking for.
			if( td.getTagName().equalsIgnoreCase( "td" ) )
			{
				// Make sure it's directly a part of this table before returning
				// it.
				Element tr = DOM.getParent( td );
				Element table = DOM.getParent( tr );
				if( table == m_table )
					return td;
			}

			// If we run into this table's body, we're out of options.
			if( td == m_table )
				return null;
		}

		return null;
	}

	@Override
	public void clear()
	{
		clear( null );
	}

	public void clear( String innerHTML )
	{
		// removeItem( m_root );
		while( m_root.m_children.size() > 0 )
			removeItem( m_root.m_children.get( m_root.m_children.size() - 1 ) );

		if( innerHTML != null )
			m_table.setInnerHTML( innerHTML );
	}

	public void endBulk()
	{
		m_root.updateRowSpan();
	}

	public Object addSpliter()
	{
		Element row = DOM.createTR();
		Element cell = DOM.createTD();
		cell.setAttribute( "colspan", "10" ); // because only ff supports
												// colspan=0, this is hardcoded
												// here
		row.appendChild( cell );

		row.addClassName( "Spliter" );
		cell.addClassName( "Spliter" );

		m_table.insertAfter( row, m_root.m_row );

		HTItem item = new HTItem( HTItemType.Splitter, m_root );

		cells.put( cell, item );

		item.m_row = row;
		item.m_cell = cell;

		return item;
	}

	public boolean getExpanded( Object item )
	{
		if( item == null )
			return true;

		HTItem spliter = (HTItem) item;
		if( spliter.m_type != HTItemType.Splitter )
			return true;

		return spliter.m_fExpanded;
	}

	public void setExpandedSpliter( Object item )
	{
		if( item == null )
			return;

		HTItem spliter = (HTItem) item;
		if( spliter.m_type != HTItemType.Splitter )
			return;

		// balayer tous les spliter,
		// si == spliter => expand
		// sinon collapse

		for( HTItem s : m_root.m_children )
		{
			if( s.m_type != HTItemType.Splitter )
				continue;

			s.setExpanded( s == spliter );
		}
	}

	public Object addItem( Object parentItem )
	{
		return addItem( parentItem, true );
	}

	public Object addItemBulk( Object parentItem )
	{
		return addItem( parentItem, false );
	}

	public Object addItem( Object parentItem, boolean fUpdateRowSpan )
	{
		HTItem parent = (HTItem) parentItem;
		if( parent == null )
			parent = m_root;

		// ajouter une ligne, quelle position ?
		Element row = null;
		if( parent.m_type == HTItemType.Splitter )
		{
			row = DOM.createTR();
			m_table.insertAfter( row, parent.m_row );
		}
		else if( parent.m_children.size() == 0 )
		{
			// pas de creation de TR
			row = parent.m_row;
		}
		else
		{
			// row of the last grand grand son
			HTItem parentLastChild = parent.getLastGrandChild();
			row = DOM.createTR();
			m_table.insertAfter( row, parentLastChild.m_row );
		}
		// seul cas de nullit� : c'est le premier TR
		if( row == null )
		{
			row = DOM.createTR();
			m_table.appendChild( row );
		}

		Element cell = DOM.createTD();
		row.appendChild( cell );

		HTItem item = new HTItem( HTItemType.Item, parent );

		cells.put( cell, item );

		item.m_row = row;
		item.m_cell = cell;

		if( fUpdateRowSpan )
			m_root.updateRowSpan();

		return item;
	}

	public void removeItem( Object item )
	{
		HTItem htItem = (HTItem) item;
		if( htItem == null )
			return;

		removeItemRec( htItem );

		m_root.updateRowSpan();
	}

	public void removeItemRec( HTItem item )
	{
		while( item.m_children.size() > 0 )
			removeItemRec( item.m_children.remove( item.m_children.size() - 1 ) );

		removeWidget( item );
		item.m_row.removeChild( item.m_cell );
		cells.remove( item.m_cell );

		// tous les freres d'apres doivent prendre le row les pr�c�dant
		Element curRow = item.m_row;
		int nextBrotherPos = item.m_parent.m_children.indexOf( item ) + 1;
		if( nextBrotherPos < item.m_parent.m_children.size() )
			item.m_parent.m_children.get( nextBrotherPos ).assignRow( curRow );

		if( item.m_row.getChildCount() == 0 )
			m_table.removeChild( item.m_row );

		item.m_parent.m_children.remove( item );
	}

	public Object getParentItem( Object item )
	{
		HTItem htItem = (HTItem) item;
		if( htItem == null )
			return null;

		htItem = htItem.m_parent;
		if( htItem == m_root )
			return null;

		return htItem;
	}

	public void setText( Object item, String text )
	{
		HTItem htItem = (HTItem) item;

		removeWidget( htItem );

		htItem.m_cell.setInnerText( text );
	}

	public void setWidget( Object item, Widget widget )
	{
		HTItem htItem = (HTItem) item;

		htItem.m_cell.setInnerText( "" );

		if( htItem.m_widget != null )
			remove( htItem.m_widget );

		add( widget, htItem.m_cell );
		htItem.m_widget = widget;
	}

	public boolean removeWidget( HTItem item )
	{
		if( item.m_widget == null )
			return false;

		boolean res = remove( item.m_widget );

		item.m_widget = null;

		return res;
	}

	public enum HTItemType
	{
		Splitter,
		Item;
	}

	class HTItem
	{
		HTItemType m_type;

		HTItem m_parent;
		ArrayList<HTItem> m_children = new ArrayList<HTItem>();

		boolean m_fExpanded = true;

		Element m_row = null;
		Element m_cell = null;

		Widget m_widget = null;

		public HTItem( HTItemType type, HTItem parent )
		{
			m_type = type;
			m_parent = parent;
			if( m_parent != null )
				m_parent.m_children.add( this );
		}

		public HTItem getLastGrandChild()
		{
			int nbChild = m_children.size();
			if( nbChild == 0 )
				return null;

			HTItem last = m_children.get( nbChild - 1 ).getLastGrandChild();
			if( last == null )
				last = m_children.get( nbChild - 1 );

			return last;
		}

		public int getNbRows()
		{
			int count = 0;

			for( HTItem c : m_children )
				count += c.getNbRows();

			if( count == 0 )
				return 1;

			return count;
		}

		public void setExpanded( boolean fExpanded )
		{
			if( m_type != HTItemType.Splitter )
				return;

			m_fExpanded = fExpanded;

			for( HTItem child : m_children )
				child.setVisible( fExpanded );
		}

		public void setVisible( boolean fVisible )
		{
			if( fVisible )
				m_row.getStyle().clearDisplay();
			else
				m_row.getStyle().setDisplay( Display.NONE );

			for( HTItem child : m_children )
				child.setVisible( fVisible );
		}

		public void updateRowSpan()
		{
			if( m_type != HTItemType.Splitter )
			{
				try
				{
					int nbRows = getNbRows();
					m_cell.setAttribute( "rowspan", String.valueOf( nbRows ) );
				}
				catch( Exception e )
				{
				}
			}

			for( HTItem c : m_children )
				c.updateRowSpan();
		}

		public void assignRow( Element row )
		{
			int pos = m_parent.m_children.indexOf( this );
			int nextPos = pos + 1;

			m_cell.removeFromParent();

			// si il y a un frere suivant, on lui donne notre TR
			int parentSize = m_parent.m_children.size();
			if( parentSize > nextPos )
				m_parent.m_children.get( nextPos ).assignRow( m_row );

			if( m_row.getChildCount() == 0 )
				m_row.removeFromParent();

			m_row = row;
			m_row.appendChild( m_cell );

			if( m_children.size() > 0 )
				m_children.get( 0 ).assignRow( m_row );
		}
	}
}
