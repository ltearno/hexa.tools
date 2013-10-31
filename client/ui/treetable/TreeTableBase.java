package com.hexa.client.ui.treetable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.hexa.client.tools.JQuery;
import com.hexa.client.ui.miracle.Printer;

public class TreeTableBase extends Panel
{
	final int treePadding = 15;

	// can be : sub item added, sub item removed, expanded, shrinked
	public interface IItemStateCallback
	{
		void onItemStateChange();
	}

	TreeTableHandler m_handler = null;

	Element m_decorator;
	Element m_table;
	Element m_head;
	Element m_body;
	Element m_headerRow = null;
	Item m_rootItem = new Item();

	int m_nbColumns = 0;
	String[] m_headers = null;
	String m_rowTemplate = "";

	HashMap<Element, Widget> m_widgets = new HashMap<Element, Widget>();

	public TreeTableBase()
	{
		m_decorator = DOM.createDiv();
		m_decorator.setClassName( "tableDecorator" );
		setElement( m_decorator );

		m_table = DOM.createTable();
		m_table.setClassName( "TreeTable" );
		m_decorator.appendChild( m_table );

		m_head = DOM.createTHead();
		m_table.appendChild( m_head );

		m_body = DOM.createTBody();
		m_table.appendChild( m_body );

		addDomHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				if( m_handler == null )
					return;

				Element td = getEventTargetCell( Event.as( event.getNativeEvent() ) );
				if( td == null )
				{
					Element th = getEventTargetHeader( DOM.eventGetTarget( Event.as( event.getNativeEvent() ) ) );
					if( th == null )
						return;
					int column = DOM.getChildIndex( m_headerRow, th );
					m_handler.onTableHeaderClick( column, event );
					return;
				}

				Element tr = DOM.getParent( td );
				int column = DOM.getChildIndex( tr, td );

				Item item = (Item) tr.getPropertyObject( "linkedItem" );
				if( item != null )
					m_handler.onTableCellClick( item, column, event );
			}
		}, ClickEvent.getType() );
	}

	@Override
	public void clear()
	{
		emptyTable();
	}

	public void setHandler( TreeTableHandler handler )
	{
		m_handler = handler;
	}

	public int getEventTargetHeaderIdx( Element th )
	{
		th = getEventTargetHeader( th );
		if( th == null )
			return -1;
		return DOM.getChildIndex( m_headerRow, th );
	}

	protected Element getEventTargetHeader( Element th )
	{
		for( ; th != null; th = DOM.getParent( th ) )
		{
			if( DOM.getElementProperty( th, "tagName" ).equalsIgnoreCase( "th" ) )
			{
				Element head = DOM.getParent( th );
				if( head == m_headerRow )
					return th;
			}
			// If we run into this table's head, we're out of options.
			if( th == m_headerRow )
			{
				return null;
			}
		}
		return null;
	}

	protected Element getEventTargetCell( Event event )
	{
		Element td = DOM.eventGetTarget( event );
		for( ; td != null; td = DOM.getParent( td ) )
		{
			// If it's a TD, it might be the one we're looking for.
			if( DOM.getElementProperty( td, "tagName" ).equalsIgnoreCase( "td" ) )
			{
				// Make sure it's directly a part of this table before returning
				// it
				Element tr = DOM.getParent( td );
				Element body = DOM.getParent( tr );
				if( body == m_body )
					return td;
			}

			// If we run into this table's body, we're out of options.
			if( td == m_body )
				return null;
		}

		return null;
	}

	public void emptyTable()
	{
		for( Entry<Element, Widget> entry : m_widgets.entrySet() )
			removeWidget( entry.getKey(), entry.getValue(), false );
		m_widgets.clear();

		m_rootItem = new Item();

		m_body.setInnerText( "" );
	}

	public void setExpanded( Object item, boolean fExpanded )
	{
		if( item == null )
			return;
		((Item) item).setExpanded( fExpanded );
	}

	public void setDataObject( Object item, Object dataObject )
	{
		((Item) item).setDataObject( dataObject );
	}

	public Object getDataObject( Object item )
	{
		return ((Item) item).getDataObject();
	}

	public void setText( Object item, int column, String text )
	{
		((Item) item).setText( column, text );
	}

	public void setText( Object item, int column, int text )
	{
		((Item) item).setText( column, text );
	}

	public void setText( Object item, int column, double text )
	{
		((Item) item).setText( column, text );
	}

	public void setHTML( Object item, int column, String html )
	{
		((Item) item).setHTML( column, html );
	}

	public void highLite( Object item )
	{
		((Item) item).highLite();
	}

	public void addClassRow( Object item, String clazz )
	{
		((Item) item).addClassRow( clazz );
	}

	public void removeClassRow( Object item, String clazz )
	{
		((Item) item).removeClassRow( clazz );
	}

	public void setWidget( Object item, int column, Widget w )
	{
		((Item) item).setWidget( column, w );
	}

	private void clearCell( Element td )
	{
		clearCellWidget( td );
		clearCellText( td );
	}

	private void clearCellWidget( Element td )
	{
		Widget old = m_widgets.get( td );
		if( old != null )
			removeWidget( td, old, true );
	}

	private void clearCellText( Element td )
	{
		td.setInnerText( "" );
	}

	private void removeWidget( Element td, Widget w, boolean fDoLogical )
	{
		assert (td != null) && (w != null);

		// Validate.
		if( w.getParent() != this )
			return;

		try
		{
			orphan( w );
		}
		finally
		{
			// Physical detach.
			Element elem = w.getElement();
			DOM.removeChild( DOM.getParent( elem ), elem );

			// Logical detach.
			if( fDoLogical )
				m_widgets.remove( td );
		}
	}

	public Item getItemForRef( int ref )
	{
		JsArray<Element> rows = JQuery.get().jqSelect( "tr[ref=\"" + ref + "\"]", m_body );
		if( rows.length() != 1 )
			return null;

		Element tr = rows.get( 0 );
		if( tr == null )
			return null;

		Object item = tr.getPropertyObject( "linkedItem" );

		return (Item) item;
	}

	public ArrayList<Item> getItemChilds( Item item )
	{
		if( item == null )
			item = m_rootItem;
		return item.getChilds();
	}

	public class Item
	{
		Item m_parent = null;
		Element m_tr = null;
		Element m_trToDelete = null;
		ArrayList<Item> m_childs = new ArrayList<Item>();

		ArrayList<IItemStateCallback> m_stateCallbacks = null;

		boolean m_fExpanded = true;

		int m_ref = -1; // this field is synchronized with the dom element
						// m_tr's "ref" attribute
		Object m_dataObject = null;

		public Item getParent()
		{
			return m_parent == m_rootItem ? null : m_parent;
		}

		// adds an item at the end of the children of the parent
		public Item addLastChild()
		{
			assert (m_nbColumns > 0) : "Table should have at least one column before adding items";

			// Item parentItem = this;

			Item newItem = new Item();
			newItem.m_tr = DOM.createTR();
			newItem.m_tr.setPropertyObject( "linkedItem", newItem );

			//JQuery.get().jqHtml( newItem.m_tr, m_rowTemplate );
			newItem.m_tr.setInnerHTML( m_rowTemplate );

			// DOM add
			Item lastParentLeaf = getLastLeaf();
			Element trToInsertAfter = lastParentLeaf.m_tr;
			if( trToInsertAfter != null )
			{
				int after = DOM.getChildIndex( m_body, trToInsertAfter );
				int before = after + 1;
				DOM.insertChild( m_body, newItem.m_tr, before );
			}
			else
			{
				DOM.appendChild( m_body, newItem.m_tr );
			}

			// logical add
			newItem.m_parent = this;
			m_childs.add( newItem );
			signalStateChange();

			// take care of the left padding
			Element firstTd = DOM.getChild( newItem.m_tr, 0 );
			firstTd.getStyle().setPaddingLeft( newItem.getLevel() * treePadding, Unit.PX );

			// JQuery.get().jqEffect( "highlight", 250, newItem.m_tr, null );

			return newItem;
		}

		// move to be the last item of parent
		public void moveLastChild( Item newParent )
		{
			if( this == newParent )
				return;

			// remove from its current position
			Item parentItem = m_parent;
			if( parentItem == null )
				parentItem = m_rootItem;
			parentItem.m_childs.remove( this );
			// DOM.removeChild( m_body, m_tr );

			if( newParent == null )
				newParent = m_rootItem;

			// insert at the end of the current parent

			// DOM add
			Item lastLeaf = newParent.getLastLeaf();
			Element trToInsertAfter = lastLeaf.m_tr;
			if( trToInsertAfter != null )
			{
				int after = DOM.getChildIndex( m_body, trToInsertAfter );
				int before = after + 1;

				DOM.insertChild( m_body, m_tr, before );
			}
			else
			{
				DOM.appendChild( m_body, m_tr );
			}

			parentItem.m_childs.add( this );

			// take care of the left padding
			Element firstTd = DOM.getChild( m_tr, 0 );
			firstTd.getStyle().setPaddingLeft( getLevel() * treePadding, Unit.PX );

			// highLite();
		}

		// adds a new sibling item, just below (with same parent)
		public Item addBefore()
		{
			assert (m_nbColumns > 0);

			// which is the parent ? => same parent as item
			Item parentItem = m_parent;
			if( parentItem == null )
				parentItem = m_rootItem;

			Item newItem = new Item();
			newItem.m_tr = DOM.createTR();
			newItem.m_tr.setPropertyObject( "linkedItem", newItem );

			//JQuery.get().jqHtml( newItem.m_tr, m_rowTemplate );
			newItem.m_tr.setInnerHTML( m_rowTemplate );

			// DOM add
			DOM.insertBefore( m_body, newItem.m_tr, m_tr );

			// logical add
			newItem.m_parent = parentItem;
			int itemPos = parentItem.m_childs.indexOf( this );
			parentItem.m_childs.add( itemPos, newItem );
			parentItem.signalStateChange();

			// take care of the left padding
			Element firstTd = DOM.getChild( newItem.m_tr, 0 );
			firstTd.getStyle().setPaddingLeft( newItem.getLevel() * treePadding, Unit.PX );

			// JQuery.get().jqEffect( "highlight", 250, newItem.m_tr, null );

			return newItem;
		}

		// move to position just before "item"
		public void moveBefore( Item item )
		{
			if( this == item )
				return;

			Element lastTrToMove = getLastLeafTR();
			Element firstChildRow = DOM.getNextSibling( m_tr );

			// remove from its current position
			Item parentItem = m_parent;
			if( parentItem == null )
				parentItem = m_rootItem;
			parentItem.m_childs.remove( this );
			// DOM.removeChild( m_body, m_tr );

			// insert at the selected position
			if( item == null )
			{
				// insert at the end of the current parent

				// DOM add
				Item lastLeaf = parentItem.getLastLeaf();
				Element trToInsertAfter = lastLeaf.m_tr;
				if( trToInsertAfter != null )
				{
					int after = DOM.getChildIndex( m_body, trToInsertAfter );
					int before = after + 1;

					DOM.insertChild( m_body, m_tr, before );
				}
				else
				{
					DOM.appendChild( m_body, m_tr );
				}

				parentItem.m_childs.add( this );
			}
			else
			{
				Item newParentItem = item.m_parent;
				if( newParentItem == null )
					newParentItem = m_rootItem;
				int itemPos = item.m_parent.m_childs.indexOf( item );
				newParentItem.m_childs.add( itemPos, this );
				DOM.insertBefore( m_body, m_tr, item.m_tr );
			}

			// take care of the left padding
			Element firstTd = DOM.getChild( m_tr, 0 );
			firstTd.getStyle().setPaddingLeft( getLevel() * treePadding, Unit.PX );

			// update child rows
			Element nextTR = DOM.getNextSibling( m_tr );
			if( firstChildRow != null && lastTrToMove != null && !m_childs.isEmpty() )
			{
				while( true )
				{
					Element next = DOM.getNextSibling( firstChildRow );
					DOM.insertBefore( m_body, firstChildRow, nextTR );
					if( firstChildRow == lastTrToMove )
						break;
					firstChildRow = next;
				}
			}

			// highLite();
		}

		public Element getTdElement( int column )
		{
			return DOM.getChild( m_tr, column );
		}

		// returns true if the item is the last of its parent
		/*
		 * private boolean isLastChild() { Item parent = m_parent; if( parent == null ) parent = m_rootItem;
<<<<<<< HEAD
		 *
=======
		 *
>>>>>>> origin/regsys
		 * return parent.m_childs.get(parent.m_childs.size()-1) == this; }
		 */

		public Item getNextTraversalItem()
		{
			// if has child, return first child
			if( !m_childs.isEmpty() )
				return m_childs.get( 0 );

			// return next sibling if any
			Item parent = m_parent;
			if( parent == null )
				parent = m_rootItem;
			int me = parent.m_childs.indexOf( this );
			if( me < parent.m_childs.size() - 1 )
				return parent.m_childs.get( me + 1 );

			// return the next sibling of our parent
			Item parentNext = null;
			parent = m_parent;
			while( parentNext == null && parent != null )
			{
				parentNext = parent.getNextSiblingNoBack();
				parent = parent.m_parent;
			}
			if( parentNext != null )
				return parentNext;

			return m_rootItem.m_childs.get( 0 );
			/*
			 * // while our parent if the last of its siblings, go up one level Item ancestor = parent; while( ancestor!=null && ancestor.isLastChild() )
			 * ancestor = ancestor.m_parent;
<<<<<<< HEAD
			 *
			 * // return the next sibling of this ancestor if( ancestor == null ) return m_rootItem.m_childs.get(0);
			 *
=======
			 *
			 * // return the next sibling of this ancestor if( ancestor == null ) return m_rootItem.m_childs.get(0);
			 *
>>>>>>> origin/regsys
			 * if( me == parent.m_childs.size() - 1 ) return parent.m_childs.get( 0 ); return parent.m_childs.get( me + 1 );
			 */
		}

		private Item getNextSiblingNoBack()
		{
			Item parent = m_parent;
			if( parent == null )
				parent = m_rootItem;
			int me = parent.m_childs.indexOf( this );
			if( me == parent.m_childs.size() - 1 )
				return null;
			return parent.m_childs.get( me + 1 );
		}

		public Item getPrevTraversalItem()
		{
			Item parent = m_parent;
			if( parent == null )
				parent = m_rootItem;
			int me = parent.m_childs.indexOf( this );
			if( me == 0 )
				return parent.m_childs.get( parent.m_childs.size() - 1 );
			return parent.m_childs.get( me - 1 );
		}

		public Item getNextSiblingItem()
		{
			Item parent = m_parent;
			if( parent == null )
				parent = m_rootItem;
			int me = parent.m_childs.indexOf( this );
			if( me == parent.m_childs.size() - 1 )
				return parent.m_childs.get( 0 );
			return parent.m_childs.get( me + 1 );
		}

		public Item getPrevSiblingItem()
		{
			Item parent = m_parent;
			if( parent == null )
				parent = m_rootItem;
			int me = parent.m_childs.indexOf( this );
			if( me == 0 )
				return parent.m_childs.get( parent.m_childs.size() - 1 );
			return parent.m_childs.get( me - 1 );
		}

		public void setRef( int ref )
		{
			if( m_ref != ref )
			{
				m_tr.setAttribute( "ref", String.valueOf( ref ) );
				m_ref = ref;
			}
		}

		public int getRef()
		{
			if( m_ref < 0 )
			{
				String value = m_tr.getAttribute( "ref" );
				if( value == null )
					return -1;
				try
				{
					m_ref = Integer.parseInt( value );
					return m_ref;
				}
				catch( Exception e )
				{
					return -1;
				}
			}
			return m_ref;
		}

		public void setDataObject( Object dataObject )
		{
			m_dataObject = dataObject;
		}

		public Object getDataObject()
		{
			return m_dataObject;
		}

		public void setText( int column, String text )
		{
			assert column < m_nbColumns;
			if( m_tr == null )
				return;

			Element td = DOM.getChild( m_tr, column );

			clearCell( td );

			td.setInnerText( text );
		}

		public void setText( int column, int text )
		{
			setText( column, String.valueOf( text ) );
		}

		public void setText( int column, double text )
		{
			setText( column, String.valueOf( text ) );
		}

		public void setHTML( int column, String html )
		{
			assert column < m_nbColumns;
			if( m_tr == null )
				return;

			Element td = DOM.getChild( m_tr, column );

			clearCell( td );

			//JQuery.get().jqHtml( td, html );
			td.setInnerHTML( html );
		}

		public void setWidget( int column, Widget w )
		{
			assert column < m_nbColumns;
			if( m_tr == null )
				return;

			Element td = DOM.getChild( m_tr, column );

			clearCell( td );

			// detach new child
			w.removeFromParent();

			// logical add
			m_widgets.put( td, w );

			// physical add
			td.appendChild( w.getElement() );

			// adopt
			adopt( w );
		}

		public void highLite()
		{
			JQuery.get().jqEffect( "highlight", 2000, m_tr, null );
		}

		public void addClassRow( String clazz )
		{
			m_tr.addClassName( clazz );
		}

		public void removeClassRow( String clazz )
		{
			m_tr.removeClassName( clazz );
		}

		public boolean hasChilds()
		{
			return !m_childs.isEmpty();
		}

		public ArrayList<Item> getChilds()
		{
			return m_childs;
		}

		public void addStateChangeCallback( IItemStateCallback callback )
		{
			if( m_stateCallbacks == null )
				m_stateCallbacks = new ArrayList<IItemStateCallback>();

			m_stateCallbacks.add( callback );
		}

		public boolean getExpanded()
		{
			return m_fExpanded;
		}

		public void setExpanded( boolean fExpanded )
		{
			m_fExpanded = fExpanded;
			signalStateChange();

			ensureAllChildRespectExpand();
		}

		void signalStateChange()
		{
			if( m_stateCallbacks == null )
				return;

			for( IItemStateCallback cb : m_stateCallbacks )
				cb.onItemStateChange();
		}

		void ensureAllChildRespectExpand()
		{
			boolean fOneParentAboveShrinked = false;
			Item parent = this;
			while( parent != null )
			{
				if( !parent.m_fExpanded )
				{
					fOneParentAboveShrinked = true;
					break;
				}
				parent = parent.m_parent;
			}

			ensureAllChildRespectExpand( fOneParentAboveShrinked );
		}

		void ensureAllChildRespectExpand( boolean fOneParentAboveShrinked )
		{
			for( Item child : m_childs )
			{
				if( m_fExpanded && (!fOneParentAboveShrinked) )
					child.m_tr.getStyle().clearDisplay();
				else
					child.m_tr.getStyle().setDisplay( Display.NONE );
				child.ensureAllChildRespectExpand( fOneParentAboveShrinked || !m_fExpanded );
			}
		}

		Item getLastLeaf()
		{
			int nbChilds = m_childs.size();
			if( nbChilds == 0 )
				return this;
			return m_childs.get( nbChilds - 1 ).getLastLeaf();
		}

		Element getLastLeafTR()
		{
			return getLastLeaf().m_tr;
		}

		public void remove()
		{
			removeRec();
			// remove from parent
			if( m_parent != null )
				m_parent.m_childs.remove( this );
		}

		public void removeRec()
		{
			// remove all children
			while( !m_childs.isEmpty() )
				m_childs.remove( 0 ).remove();

			// remove all widgets
			removeAllWidgets();

			// abandon row
			setRef( -1 );
			m_trToDelete = m_tr;
			m_tr = null;

			// remove row
			if( m_trToDelete != null )
			{
				final Element tr = m_trToDelete;
				m_trToDelete = null;

				JQuery.get().jqFadeOut( tr, 250, new JQuery.Callback()
				{
					@Override
					public void onFinished()
					{
						// physical remove
						DOM.removeChild( m_body, tr );
					}
				} );
				// DOM.removeChild( m_body, m_trToDelete );
			}
		}

//		public void removeOLD()
//		{
//			// logical delete
//			logicalRemove();
//
//			JQuery.get().jqFadeOut( m_trToDelete, 250, new JQuery.Callback()
//			{
//				@Override
//				public void onFinished()
//				{
//					// physical remove
//					physicalRemove();
//				}
//			} );
//		}

		void logicalRemove()
		{
			// remove from parent
			if( m_parent != null )
			{
				m_parent.m_childs.remove( this );
				m_parent.signalStateChange();
			}
			m_parent = null;

			setRef( -1 );
			m_trToDelete = m_tr;
			m_tr = null;

			//
			for( Item child : m_childs )
				child.logicalRemove();
		}

		void removeAllWidgets()
		{
			int nbTd = DOM.getChildCount( m_tr );
			for( int i = 0; i < nbTd; i++ )
			{
				Element td = DOM.getChild( m_tr, i );
				clearCellWidget( td );
			}

			// for( Item child: m_childs )
			// child.removeAllWidgets();
		}

		void physicalRemove()
		{
			// remove all widgets
			removeAllWidgets();

			// remove myself...
			if( m_trToDelete != null )
				DOM.removeChild( m_body, m_trToDelete );

			// ...and all my children
			for( Item child : m_childs )
				child.physicalRemove();
		}

		public int getLevel()
		{
			if( m_parent == null )
				return -1;
			return 1 + m_parent.getLevel();
		}
	}

	private class HeaderPrinter implements Printer
	{
		int column;

		HeaderPrinter( int column )
		{
			this.column = column;
		}

		@Override
		public void setWidget( Widget widget )
		{
			assert false;
			setHeader( column, "WIDGET SHOULD BE HERE, NOT IMPLEMENTED" );
		}

		@Override
		public void setText( String text )
		{
			setHeader( column, text );
		}

		@Override
		public void setHTML( String html )
		{
			setHeader( column, html + " (html not implemented)" );
		}
	}

	public Printer getHeaderPrinter( int column )
	{
		return new HeaderPrinter( column );
	}

	public void setColumnWidth( int column, int width )
	{
		DOM.getChild( m_headerRow, column ).setAttribute( "width", width + "px" );
	}

	public void setHeader( int col, String header )
	{
		if( col >= m_nbColumns )
		{
			String[] newHeaders = new String[Math.max( col + 1, m_nbColumns )];
			for( int i = 0; i < m_nbColumns; i++ )
				newHeaders[i] = m_headers[i];
			m_headers = newHeaders;
		}

		m_headers[col] = header;

		setHeaders( m_headers );
	}

	public void setHeaders( String... headers )
	{
		Element oldHeaderRow = m_headerRow;

		m_headers = headers;

		m_headerRow = DOM.createTR();
		m_headerRow.setClassName( "thead" );
		StringBuilder b = new StringBuilder();
		StringBuilder bTemplate = new StringBuilder();
		m_nbColumns = headers.length;
		for( int i = 0; i < m_nbColumns; i++ )
		{
			b.append( "<th>" + headers[i] + "</th>" );
			bTemplate.append( "<td/>" );
		}
		//JQuery.get().jqHtml( m_headerRow, b.toString() );
		m_headerRow.setInnerHTML( b.toString() );

		if( oldHeaderRow != null )
			m_head.replaceChild( m_headerRow, oldHeaderRow );
		else
			DOM.insertChild( m_head, m_headerRow, 0 );

		m_rowTemplate = bTemplate.toString();
	}

	public Item addItem()
	{
		return addItem( null );
	}

	// adds an item at the end of the children of the parent
	public Item addItem( Object parent )
	{
		Item parentItem = (Item) parent;
		if( parentItem == null )
			parentItem = m_rootItem;

		return parentItem.addLastChild();
	}

	public Item addItemBefore( Object item )
	{
		return ((Item) item).addBefore();
	}

	public void removeItem( Object item )
	{
		((Item) item).remove();
	}

	@Override
	public Iterator<Widget> iterator()
	{
		return m_widgets.values().iterator();
	}

	@Override
	public boolean remove( Widget child )
	{
		return false;
	}
}
