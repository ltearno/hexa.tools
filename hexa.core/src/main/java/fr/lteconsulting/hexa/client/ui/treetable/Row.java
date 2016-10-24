package fr.lteconsulting.hexa.client.ui.treetable;

import java.util.ArrayList;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.interfaces.IAsyncCallback;
import fr.lteconsulting.hexa.client.tools.JQuery;
import fr.lteconsulting.hexa.client.ui.miracle.Printer;
import fr.lteconsulting.hexa.client.ui.miracle.Size;

public class Row
{
	private final TreeTable treeTable;

	Row( TreeTable treeTable )
	{
		this.treeTable = treeTable;
	}

	Row m_parent = null;
	Element m_tr = null;
	Element m_trToDelete = null;
	ArrayList<Row> m_childs;

	boolean m_fExpanded = true;

	int m_ref = -1; // this field is synchronized with the dom element
					// m_tr's "ref" attribute
	Object m_dataObject = null;

	private SafeHtml getExpandImageHtml()
	{
		StringBuilder sb = new StringBuilder();
		sb.append( "<img src='" );
		if( !hasChilds() )
			sb.append( "" );
		else if( getExpanded() )
			sb.append( this.treeTable.treeMinus.getSafeUri().asString() );
		else
			sb.append( this.treeTable.treePlus.getSafeUri().asString() );
		sb.append( "'></img>" );

		return SafeHtmlUtils.fromTrustedString( sb.toString() );
	}

	private void updateExpandImage()
	{
		if( m_tr == null )
			return;

		Element td = m_tr.getChild( 0 ).cast();
		if( td.getChildCount() == 0 )
			return;

		ImageElement img = td.getChild( 0 ).cast();

		if( !hasChilds() )
			img.setSrc( "" );
		else if( getExpanded() )
			img.setSrc( this.treeTable.treeMinus.getSafeUri().asString() );
		else
			img.setSrc( this.treeTable.treePlus.getSafeUri().asString() );
	}

	public Row getParent()
	{
		return m_parent == this.treeTable.m_rootItem ? null : m_parent;
	}

	public Cell getCell( int column )
	{
		return new Cell( column );
	}

	// adds an item at the end of the children of the parent
	public Row addLastChild()
	{
		assert (this.treeTable.m_nbColumns > 0) : "Table should have at least one column before adding items";

		Row newItem = new Row( this.treeTable );
		newItem.m_tr = DOM.createTR();
		newItem.m_tr.setPropertyObject( "linkedItem", newItem );

		newItem.m_tr.setInnerHTML( this.treeTable.m_rowTemplate );

		// DOM add
		Row lastParentLeaf = getLastLeaf();
		Element trToInsertAfter = lastParentLeaf.m_tr;
		if( trToInsertAfter != null )
		{
			int after = DOM.getChildIndex( this.treeTable.m_body, trToInsertAfter );
			int before = after + 1;
			DOM.insertChild( this.treeTable.m_body, newItem.m_tr, before );
		}
		else
		{
			DOM.appendChild( this.treeTable.m_body, newItem.m_tr );
		}

		// logical add
		newItem.m_parent = this;
		getChilds().add( newItem );
		signalStateChange();

		// take care of the left padding
		Element firstTd = DOM.getChild( newItem.m_tr, 0 );
		firstTd.getStyle().setPaddingLeft( newItem.getLevel() * this.treeTable.treePadding, Unit.PX );

		return newItem;
	}

	// move to be the last item of parent
	public void moveLastChild( Row newParent )
	{
		if( this == newParent )
			return;

		// remove from its current position
		Row parentItem = m_parent;
		if( parentItem == null )
			parentItem = this.treeTable.m_rootItem;
		parentItem.getChilds().remove( this );
		// DOM.removeChild( m_body, m_tr );

		if( newParent == null )
			newParent = this.treeTable.m_rootItem;

		// insert at the end of the current parent

		// DOM add
		Row lastLeaf = newParent.getLastLeaf();
		Element trToInsertAfter = lastLeaf.m_tr;
		if( trToInsertAfter != null )
		{
			int after = DOM.getChildIndex( this.treeTable.m_body, trToInsertAfter );
			int before = after + 1;

			DOM.insertChild( this.treeTable.m_body, m_tr, before );
		}
		else
		{
			DOM.appendChild( this.treeTable.m_body, m_tr );
		}

		parentItem.getChilds().add( this );

		// take care of the left padding
		Element firstTd = DOM.getChild( m_tr, 0 );
		firstTd.getStyle().setPaddingLeft( getLevel() * this.treeTable.treePadding, Unit.PX );
	}

	// adds a new sibling item, just below (with same parent)
	public Row addBefore()
	{
		assert (this.treeTable.m_nbColumns > 0);

		// which is the parent ? => same parent as item
		Row parentItem = m_parent;
		if( parentItem == null )
			parentItem = this.treeTable.m_rootItem;

		Row newItem = new Row( this.treeTable );
		newItem.m_tr = Document.get().createTRElement();
		newItem.m_tr.setPropertyObject( "linkedItem", newItem );

		newItem.m_tr.setInnerHTML( this.treeTable.m_rowTemplate );

		// DOM add
		this.treeTable.m_body.insertBefore( newItem.m_tr, m_tr );

		// logical add
		newItem.m_parent = parentItem;
		int itemPos = parentItem.getChilds().indexOf( this );
		parentItem.getChilds().add( itemPos, newItem );
		parentItem.signalStateChange();

		// take care of the left padding
		Element firstTd = DOM.getChild( newItem.m_tr, 0 );
		firstTd.getStyle().setPaddingLeft( newItem.getLevel() * this.treeTable.treePadding, Unit.PX );

		return newItem;
	}

	// move to position just before "item"
	public void moveBefore( Row item )
	{
		if( this == item )
			return;

		Element lastTrToMove = getLastLeafTR();
		Element firstChildRow = DOM.getNextSibling( m_tr );

		// remove from its current position
		Row parentItem = m_parent;
		if( parentItem == null )
			parentItem = this.treeTable.m_rootItem;
		parentItem.getChilds().remove( this );

		// insert at the selected position
		if( item == null )
		{
			// insert at the end of the current parent

			// DOM add
			Row lastLeaf = parentItem.getLastLeaf();
			Element trToInsertAfter = lastLeaf.m_tr;
			if( trToInsertAfter != null )
			{
				int after = DOM.getChildIndex( this.treeTable.m_body, trToInsertAfter );
				int before = after + 1;

				DOM.insertChild( this.treeTable.m_body, m_tr, before );
			}
			else
			{
				DOM.appendChild( this.treeTable.m_body, m_tr );
			}

			parentItem.getChilds().add( this );
		}
		else
		{
			Row newParentItem = item.m_parent;
			if( newParentItem == null )
				newParentItem = this.treeTable.m_rootItem;
			int itemPos = item.m_parent.getChilds().indexOf( item );
			newParentItem.getChilds().add( itemPos, this );
			DOM.insertBefore( this.treeTable.m_body, m_tr, item.m_tr );
		}

		// take care of the left padding
		Element firstTd = DOM.getChild( m_tr, 0 );
		firstTd.getStyle().setPaddingLeft( getLevel() * this.treeTable.treePadding, Unit.PX );

		// update child rows
		Element nextTR = DOM.getNextSibling( m_tr );
		if( firstChildRow != null && lastTrToMove != null && hasChilds() )
		{
			while( true )
			{
				Element next = DOM.getNextSibling( firstChildRow );
				DOM.insertBefore( this.treeTable.m_body, firstChildRow, nextTR );
				if( firstChildRow == lastTrToMove )
					break;
				firstChildRow = next;
			}
		}
	}

	public Element getTdElement( int column )
	{
		return DOM.getChild( m_tr, column );
	}

	public Row getNextTraversalItem()
	{
		// if has child, return first child
		if( hasChilds() )
			return m_childs.get( 0 );

		// return next sibling if any
		Row parent = m_parent;
		if( parent == null )
			parent = this.treeTable.m_rootItem;
		int me = parent.m_childs.indexOf( this );
		if( me < parent.m_childs.size() - 1 )
			return parent.m_childs.get( me + 1 );

		// return the next sibling of our parent
		Row parentNext = null;
		parent = m_parent;
		while( parentNext == null && parent != null )
		{
			parentNext = parent.getNextSiblingNoBack();
			parent = parent.m_parent;
		}
		if( parentNext != null )
			return parentNext;

		return this.treeTable.m_rootItem.getChilds().get( 0 );
	}

	private Row getNextSiblingNoBack()
	{
		Row parent = m_parent;
		if( parent == null )
			parent = this.treeTable.m_rootItem;
		int me = parent.m_childs.indexOf( this );
		if( me == parent.m_childs.size() - 1 )
			return null;
		return parent.m_childs.get( me + 1 );
	}

	public Row getPrevTraversalItem()
	{
		Row parent = m_parent;
		if( parent == null )
			parent = this.treeTable.m_rootItem;
		int me = parent.m_childs.indexOf( this );
		if( me == 0 )
			return parent.m_childs.get( parent.m_childs.size() - 1 );
		return parent.m_childs.get( me - 1 );
	}

	public Row getNextSiblingItem()
	{
		Row parent = m_parent;
		if( parent == null )
			parent = this.treeTable.m_rootItem;
		int me = parent.m_childs.indexOf( this );
		if( me == parent.m_childs.size() - 1 )
			return parent.m_childs.get( 0 );
		return parent.m_childs.get( me + 1 );
	}

	public Row getPrevSiblingItem()
	{
		Row parent = m_parent;
		if( parent == null )
			parent = this.treeTable.m_rootItem;
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

	public void setVisible( final boolean isVisible )
	{
		visitTreeDeep( new IAsyncCallback<Row>()
		{
			@Override
			public void onSuccess( Row result )
			{
				if( isVisible )
					result.m_tr.getStyle().clearDisplay();
				else
					result.m_tr.getStyle().setDisplay( Display.NONE );
			}
		} );
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
		assert column < this.treeTable.m_nbColumns;
		if( column >= this.treeTable.m_nbColumns )
			return;

		if( m_tr == null )
			return;

		Element td = DOM.getChild( m_tr, column );

		this.treeTable.clearCell( td );

		if( column == 0 )
		{
			SafeHtmlBuilder sb = new SafeHtmlBuilder();
			sb.append( getExpandImageHtml() );
			sb.appendEscaped( text );

			td.setInnerHTML( sb.toSafeHtml().asString() );
		}
		else
		{
			td.setInnerText( text );
		}
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
		assert column < this.treeTable.m_nbColumns;
		if( column >= this.treeTable.m_nbColumns )
			return;

		if( m_tr == null )
			return;

		Element td = DOM.getChild( m_tr, column );

		this.treeTable.clearCell( td );

		if( column == 0 )
			td.setInnerHTML( getExpandImageHtml().asString() + html );
		else
			td.setInnerHTML( html );
	}

	public void setWidget( int column, IsWidget w )
	{
		setWidget( column, w.asWidget() );
	}

	public void setWidget( int column, Widget w )
	{
		assert column < this.treeTable.m_nbColumns;
		if( column >= this.treeTable.m_nbColumns )
			return;

		if( m_tr == null )
			return;

		Element td = DOM.getChild( m_tr, column );

		treeTable.clearCell( td );

		if( column == 0 )
			td.setInnerHTML( getExpandImageHtml().asString() );

		treeTable.addWidget( td, w );
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
		return (m_childs != null) && (!m_childs.isEmpty());
	}

	public ArrayList<Row> getChilds()
	{
		if( m_childs == null )
			m_childs = new ArrayList<>();
		return m_childs;
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
		updateExpandImage();
	}

	private void visitTreeDeep( IAsyncCallback<Row> callback )
	{
		if( hasChilds() )
		{
			for( Row child : getChilds() )
				child.visitTreeDeep( callback );
		}

		callback.onSuccess( this );
	}

	void ensureAllChildRespectExpand()
	{
		boolean fOneParentAboveShrinked = false;
		Row parent = this;
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
		if( !hasChilds() )
			return;

		for( Row child : getChilds() )
		{
			if( m_fExpanded && (!fOneParentAboveShrinked) )
				child.m_tr.getStyle().clearDisplay();
			else
				child.m_tr.getStyle().setDisplay( Display.NONE );
			child.ensureAllChildRespectExpand( fOneParentAboveShrinked || !m_fExpanded );
		}
	}

	Row getLastLeaf()
	{
		if( !hasChilds() )
			return this;

		int nbChilds = m_childs.size();
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
			m_parent.getChilds().remove( this );
	}

	public void removeRec()
	{
		// remove all children
		while( hasChilds() )
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
					try
					{
						Row.this.treeTable.m_body.removeChild( tr );
					}
					catch( Exception e )
					{
					}
				}
			} );
		}
	}

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
		if( m_childs != null )
		{
			for( Row child : m_childs )
				child.logicalRemove();
		}
	}

	void removeAllWidgets()
	{
		int nbTd = DOM.getChildCount( m_tr );
		for( int i = 0; i < nbTd; i++ )
		{
			Element td = DOM.getChild( m_tr, i );
			this.treeTable.clearCellWidget( td );
		}
	}

	void physicalRemove()
	{
		// remove all widgets
		removeAllWidgets();

		// remove myself...
		if( m_trToDelete != null )
			this.treeTable.m_body.removeChild( m_trToDelete );

		// ...and all my children
		if( m_childs != null )
		{
			for( Row child : m_childs )
				child.physicalRemove();
		}
	}

	public int getLevel()
	{
		if( m_parent == null )
			return -1;
		return 1 + m_parent.getLevel();
	}

	public class Cell implements Printer
	{
		private final int column;

		private Cell( int col )
		{
			this.column = col;
		}

		@Override
		public void setText( String text )
		{
			Row.this.setText( column, text );
		}

		@Override
		public void setHTML( String html )
		{
			Row.this.setHTML( column, html );
		}

		@Override
		public void setWidget( Widget widget )
		{
			Row.this.setWidget( column, widget );
		}

		public Element getTdElement()
		{
			return DOM.getChild( m_tr, column );
		}

		public Size getSize()
		{
			Element td = getTdElement();
			return new Size( td.getOffsetWidth(), td.getOffsetHeight() );
		}
	}

	public boolean isDisplayed()
	{
		String display = m_tr.getStyle().getDisplay();
		return display == null || !display.equalsIgnoreCase( "none" );
	}
}