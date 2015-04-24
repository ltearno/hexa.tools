package fr.lteconsulting.hexa.client.datatable;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.TableLayout;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.dom.client.TableSectionElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.ProvidesResize;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.impl.FocusImpl;

import fr.lteconsulting.hexa.client.css.HexaCss;
import fr.lteconsulting.hexa.client.tools.Action1;
import fr.lteconsulting.hexa.client.ui.containers.CustomPanel;
import fr.lteconsulting.hexa.client.ui.miracle.Size;

/**
 * A Table/Tree widget.
 * 
 * @author Arnaud
 */
public class DataTable extends Composite implements RequiresResize, ProvidesResize, Focusable//, HasKeyPressHandlers
{
	interface Css extends HexaCss
	{
		String main();
	}
	
	private static final Css CSS = GWT.create( Css.class );

	static final FocusImpl focusImpl = FocusImpl.getFocusImplForWidget();//Panel();
	private CustomPanel customPanel;
	private DivElement wrapper;
	private TableElement wrapperTable;
	private TableElement theadTable;
	private Element headTableColgroup;
	private TableElement table;
	private Element tableColgroup;
	private TableSectionElement thead;
	private TableRowElement theadtr;
	private TableSectionElement tbody;

	private RowImpl rootRow = new RowImpl( true );

	public DataTable()
	{
		// wrapper Div
		wrapper = Document.get().createDivElement();
		wrapper.getStyle().setPosition( Position.RELATIVE );
		wrapper.setTabIndex( 0 ); // for focusability

		// headings table
		theadTable = Document.get().createTableElement();
		theadTable.setClassName( "table" );
		theadTable.getStyle().setWidth( 100, Unit.PCT );
		theadTable.getStyle().setTableLayout( TableLayout.FIXED );
		
		headTableColgroup = Document.get().createElement( "colgroup" );
		
		// headings elements
		thead = Document.get().createTHeadElement();
		theadtr = Document.get().createTRElement();

		// main table
		table = Document.get().createTableElement();
		table.setClassName( "table" );
		table.getStyle().setWidth( 100, Unit.PCT );
		table.getStyle().setTableLayout( TableLayout.FIXED );
		
		tableColgroup = Document.get().createElement( "colgroup" );

		// table body
		tbody = Document.get().createTBodyElement();

		// wrapper table
		wrapperTable = Document.get().createTableElement();
		wrapperTable.getStyle().setTableLayout( TableLayout.FIXED );
		wrapperTable.getStyle().setWidth( 100, Unit.PCT );
		wrapperTable.getStyle().setHeight( 100, Unit.PCT );
		TableRowElement tr0 = Document.get().createTRElement();
		TableCellElement td0 = Document.get().createTDElement();
		DivElement div0 = Document.get().createDivElement();
		div0.getStyle().setWidth( 100, Unit.PCT );
		TableRowElement tr1 = Document.get().createTRElement();
		tr1.getStyle().setHeight( 100, Unit.PCT );
		TableCellElement td1 = Document.get().createTDElement();
		DivElement div1 = Document.get().createDivElement();
		div1.getStyle().setHeight( 100, Unit.PCT );
		div1.getStyle().setWidth( 100, Unit.PCT );
		div1.getStyle().setPosition( Position.RELATIVE );
		DivElement div2 = Document.get().createDivElement();
		div2.getStyle().setPosition( Position.ABSOLUTE );
		div2.getStyle().setTop( 0, Unit.PX );
		div2.getStyle().setLeft( 0, Unit.PX );
		div2.getStyle().setRight( 0, Unit.PX );
		div2.getStyle().setBottom( 0, Unit.PX );
		div2.getStyle().setOverflow( Overflow.AUTO );

		// bind together
		theadTable.appendChild( headTableColgroup );
		theadTable.appendChild( thead.<Node> cast() );
		thead.appendChild( theadtr.<Node> cast() );
		table.appendChild( tableColgroup );
		table.appendChild( tbody.<Node> cast() );
		td0.appendChild( div0 );
		div0.appendChild( theadTable.<Node> cast() );
		div1.appendChild( div2 );
		div2.appendChild( table );
		td1.appendChild( div1.<Node> cast() );
		tr0.appendChild( td0 );
		tr1.appendChild( td1 );
		wrapperTable.appendChild( tr0 );
		wrapperTable.appendChild( tr1 );
		wrapper.appendChild( wrapperTable );

		customPanel = new CustomPanel( wrapper );
		initWidget( customPanel );

		setStylePrimaryName( CSS.main() );
	}

	public int addColumn( String html, String width )
	{
		headTableColgroup.appendChild( createColumnElement( width ) );
		tableColgroup.appendChild( createColumnElement( width ) );

		TableCellElement th = Document.get().createTHElement();
		th.setInnerHTML( html );

		thead.appendChild( th );

		int index = getNbColumns() - 1;

		return index;
	}
	
	public Element getColumnElement( int index )
	{
		if( index < 0 || index >= getNbColumns() )
			return null;
		
		return tableColgroup.getChild( index ).cast();
	}

	private Element createColumnElement( String size )
	{
		Element col = Document.get().createElement( "col" );
		if( size != null )
			col.getStyle().setProperty( "width", size );
		else
			col.getStyle().clearProperty( "width" );
		return col;
	}

	private class RowImpl implements Row
	{
		private final TableRowElement tr;
		RowImpl parent;

		ArrayList<RowImpl> childs;

		ArrayList<CellImpl> cells = new ArrayList<>();

		public RowImpl()
		{
			this( false );
		}

		public RowImpl( boolean isRoot )
		{
			if( isRoot )
			{
				tr = null;
			}
			else
			{
				tr = Document.get().createTRElement();

				tr.setPropertyObject( "rowimpl", this );

				int nb = getNbColumns();
				StringBuilder sb = new StringBuilder();
				for( int i = 0; i < nb; i++ )
					sb.append( "<td></td>" );

				tr.setInnerHTML( sb.toString() );
			}
		}
		
		private boolean isOpened()
		{
			if( tr == null )
				return true;
			
			String state = tr.getAttribute( "open" );
			return state == null || state.isEmpty();
		}

		private void detach()
		{
			if( tr != null )
			{
				if( tr.getParentElement() != null )
					tr.removeFromParent();
			}

			if( parent != null )
			{
				parent.childs.remove( this );
				parent = null;
			}
		}

		/**
		 * Create a child row
		 */
		@Override
		public Row addRow()
		{
			RowImpl child = new RowImpl();

			insertLastChild( child );
			
			updateTreeCell();

			return child;
		}
		
		@Override
		public Row insertRowAt( int position )
		{
			RowImpl child = new RowImpl();

			insertChildAt( child, position );
			
			updateTreeCell();
			
			return child;
		}

		@Override
		public void acceptAsLastChild( Row row )
		{
			if( row == null )
				return;

			if( !(row instanceof RowImpl) )
				throw new RuntimeException( "Row cannot be accept as a child, incompatible implementation." );

			RowImpl child = (RowImpl) row;

			insertLastChild( child );
		}
		
		@Override
		public void acceptAsNthChild( Row row, int position )
		{
			if( row == null )
				return;

			if( !(row instanceof RowImpl) )
				throw new RuntimeException( "Row cannot be accept as a child, incompatible implementation." );

			RowImpl child = (RowImpl) row;

			insertChildAt( child, position );
		}
		
		@Override
		public boolean isFolded()
		{
			if( ! hasChildren() )
				return false;
			return ! isOpened();
		}
		
		@Override
		public void setFolded( boolean isFolded )
		{
			setOpened( ! isFolded );
		}
		
		@Override
		public void toggleChildDisplay()
		{
			if( ! hasChildren() )
				return;
			
			final boolean isOpened = ! isOpened();
			
			setOpened( isOpened );
		}

		private void setOpened( final boolean isOpened )
		{
			if( isOpened )
				tr.removeAttribute( "open" );
			else
				tr.setAttribute( "open", "x" );
			
			updateTreeCell();
			
			visitDepthFirstPre( new AbstractVisitor<Row>()
			{
				@Override
				public void beginVisit( Row node )
				{
					if( node == RowImpl.this )
						return;
					
					if( ! isOpened )
					{
						node.getTr().getStyle().setDisplay( Display.NONE );
						return;
					}
					else
					{
						node.getTr().getStyle().clearDisplay();
					}
				}
			} );
		}

		@Override
		public RowImpl getParentRow()
		{
			return parent;
		}

		@SuppressWarnings( "unchecked" )
		@Override
		public List<Row> getChildrenRows()
		{
			return (List<Row>) (Object) childs;
		}

		@Override
		public Object visitDepthFirstPre( Visitor<Row> visitor )
		{
			visitor.beginVisit( this );

			if( hasChildren() )
			{
				for( Row child : getChildrenRows() )
				{
					visitor.beginVisitChild( this, child );
					
					Object childRes = ((RowImpl) child).visitDepthFirstPre( visitor );

					visitor.endVisitChild( this, child, childRes );
				}
			}

			return visitor.endVisit( this );
		}

		@Override
		public int getLevel()
		{
			if( parent == null )
				return 0;

			return 1 + parent.getLevel();
		}
		
		private void updateTreeCell()
		{
			if( cells!=null && (!cells.isEmpty()) && cells.get( 0 ) instanceof TreeCellImpl )
				((TreeCellImpl)cells.get( 0 )).updateTreeInfo();
		}

		/**
		 * Does both the logical and physical attachment of the child row
		 * 
		 * @param child
		 *            The row to be added at the end of the children collection
		 */
		private void insertLastChild( RowImpl child )
		{
			assert (child != null);

			child.detach();

			// do the DOM attach
			RowImpl lastChildRow = getLastChildDeep();
			tbody.insertAfter( child.tr, lastChildRow.tr );

			// do the logical attach (to the child list)
			child.parent = this;
			getChildren().add( child );

			// ensure child's descendants are at a good place
			class ReplacingVisitor implements Action1<DataTable.RowImpl>
			{
				private Element previousTr = null;

				@Override
				public void exec( RowImpl row )
				{
					TableCellElement td = row.tr.getChild( 0 ).cast();
					td.getStyle().setPaddingLeft( 10 * row.getLevel(), Unit.PX );

					if( previousTr == null )
					{
						previousTr = row.tr;
						return;
					}

					tbody.insertAfter( row.tr, previousTr );
					previousTr = row.tr;
				}
			}

			child.browseDeep( new ReplacingVisitor() );
		}
		
		private void insertChildAt( RowImpl child, int position )
		{
			assert (child != null);

			child.detach();

			// do the DOM attach
			if( position > 0 )
			{
				RowImpl lastChildRow = getChildren().get( position - 1 ).getLastChildDeep();
				tbody.insertAfter( child.tr, lastChildRow.tr );
			}
			else
			{
				if( tr != null )
					tbody.insertAfter( child.tr, tr );
				else
					tbody.insertFirst( child.tr );
			}
			
			// do the logical attach (to the child list)
			child.parent = this;
			getChildren().add( position, child );

			// ensure child's descendants are at a good place
			class ReplacingVisitor implements Action1<DataTable.RowImpl>
			{
				private Element previousTr = null;

				@Override
				public void exec( RowImpl row )
				{
					TableCellElement td = row.tr.getChild( 0 ).cast();
					td.getStyle().setPaddingLeft( 10 * row.getLevel(), Unit.PX );

					if( previousTr == null )
					{
						previousTr = row.tr;
						return;
					}

					tbody.insertAfter( row.tr, previousTr );
					previousTr = row.tr;
				}
			}

			child.browseDeep( new ReplacingVisitor() );
		}

		/**
		 * Visits this node and its child.
		 * 
		 * @param visitor
		 */
		private void browseDeep( Action1<RowImpl> visitor )
		{
			visitor.exec( this );

			if( !hasChildren() )
				return;
			for( RowImpl child : childs )
				child.browseDeep( visitor );
		}

		/**
		 * Returns the last deep child RowImp or <code>this</code> if there is
		 * no child
		 * 
		 * @return
		 */
		private RowImpl getLastChildDeep()
		{
			if( !hasChildren() )
				return this;
			return childs.get( childs.size() - 1 ).getLastChildDeep();
		}

		private RowImpl getNextSiblingRow()
		{
			if( parent == null )
				return null;

			int index = getRowIndex() + 1;
			if( index >= parent.childs.size() )
				return null;

			return parent.childs.get( index );
		}

		private RowImpl getPreviousSiblingRow()
		{
			if( parent == null )
				return null;

			int index = getRowIndex() - 1;
			if( index < 0 )
				return null;

			return parent.childs.get( index );
		}

		public boolean hasChildren()
		{
			return childs != null && !childs.isEmpty();
		}

		public ArrayList<RowImpl> getChildren()
		{
			if( childs == null )
				childs = new ArrayList<>();
			return childs;
		}

		@Override
		public void remove()
		{
			tr.setPropertyObject( "rowimpl", null );

			RowImpl oldParent = parent;
			
			detach();
			
			if( oldParent != null )
				oldParent.updateTreeCell();
		}

		@Override
		public Cell getCell( int column )
		{
			ensureEnoughCells( column + 1 );

			return cells.get( column );
		}

		@Override
		public int getCellCount()
		{
			return cells.size();
		}

		@Override
		public Row getPreviousRow()
		{
			// previous sibling
			RowImpl sibling = getPreviousSiblingRow();
			if( sibling == null )
			{
				// go up to the parent
				if( parent == rootRow )
					return rootRow.getLastChildDeep();
				
				return parent;
			}
			
			RowImpl lastChild = sibling.getLastChildDeep();
			if( lastChild == null )
				return sibling;
			
			RowImpl closedOne = getFirstClosedAncestor( lastChild );
			return closedOne != null ? closedOne : lastChild;
		}
		
		private RowImpl getFirstClosedAncestor( RowImpl row )
		{
			RowImpl first = null;
			while( row != null )
			{
				if( ! row.isOpened() )
					first = row;
				
				row = row.getParentRow();
			}
			
			return first;
		}

		@Override
		public Row getNextRow()
		{
			// first child if any
			if( hasChildren() && isOpened() )
				return getChildren().get( 0 );
			
			// recursively go up to find the first next sibling of the first parent encountered
			RowImpl cur = this;
			while( cur != null )
			{
				RowImpl sibling = cur.getNextSiblingRow();
				if( sibling != null )
					return sibling;
				
				cur = cur.getParentRow();
			}
			
			return rootRow.getNextRow();
		}

		int getRowIndex()
		{
			return parent.childs.indexOf( this );
		}

		@Override
		public TableRowElement getTr()
		{
			return tr;
		}

		private void ensureEnoughCells( int nb )
		{
			if( cells.size() >= nb )
				return;

			for( int i = cells.size(); i < nb; i++ )
			{
				if( tr.getChildCount() < i + 1 )
				{
					TableCellElement td = Document.get().createTDElement();
					tr.appendChild( td );
				}

				CellImpl cell = i == 0 ? new TreeCellImpl() : new CellImpl();
				cells.add( cell );
			}
		}
		
		private class TreeCellImpl extends CellImpl implements EventListener
		{
			void updateTreeInfo()
			{
				if( getParentRow().hasChildren() )
				{
					if( getParentRow().isOpened() )
						getTd().getChild( 0 ).<Element>cast().setInnerText( "[-] " );
					else
						getTd().getChild( 0 ).<Element>cast().setInnerText( "[+] " );
				}
				else
				{
					getTd().getChild( 0 ).<Element>cast().setInnerText( "" );
				}
				
			}
			
			private void ensureTdOk()
			{
				TableCellElement td = getTd();
				if( td == null )
					return;
				
				int count = td.getChildCount();
				
				if( count <= 0 )
				{
					SpanElement treeView = Document.get().createSpanElement();
					td.appendChild( treeView );
					
					DOM.sinkEvents( treeView, Event.ONCLICK );
					DOM.setEventListener( treeView, this );
				}
				
				if( count <= 1 )
				{
					SpanElement content = Document.get().createSpanElement();
					td.appendChild( content );
				}
			}

			@Override
			public void onBrowserEvent( Event arg0 )
			{
				if( arg0.getTypeInt() == Event.ONCLICK )
				{
					RowImpl row = getParentRow();
					row.toggleChildDisplay();
				}
			}
			
			@Override
			public void setText( String text )
			{
				clearCellWidget();
				
				ensureTdOk();
				
				getTd().getChild( 1 ).<Element>cast().setInnerText( text );
			}

			@Override
			public void setHTML( String html )
			{
				clearCellWidget();
				
				ensureTdOk();
				
				getTd().getChild( 1 ).<Element>cast().setInnerHTML( html );
			}

			@Override
			public void setWidget( Widget widget )
			{
				clearCellWidget();
				
				ensureTdOk();

				getTd().getChild( 1 ).<Element>cast().setInnerText( "" );

				childWidget = widget;
				if( childWidget != null )
				{
					customPanel.addIn( getTd().getChild( 1 ).<Element>cast(), childWidget );
				}
			}
		}

		private class CellImpl implements Cell
		{
			Widget childWidget = null;

			@Override
			public RowImpl getParentRow()
			{
				return RowImpl.this;
			}

			@Override
			public int getCellIndex()
			{
				return cells.indexOf( this );
			}

			@Override
			public TableCellElement getTd()
			{
				TableRowElement tr = getTr();
				if( tr == null )
					return null;

				return tr.getChild( getCellIndex() ).cast();
			}

			@Override
			public void setText( String text )
			{
				clearCellWidget();

				TableCellElement td = getTd();
				if( td != null )
					td.setInnerText( text );
			}

			@Override
			public void setHTML( String html )
			{
				clearCellWidget();

				TableCellElement td = getTd();
				if( td != null )
					td.setInnerHTML( html );
			}

			@Override
			public void setWidget( Widget widget )
			{
				clearCellWidget();

				TableCellElement td = getTd();
				if( td != null )
				{
					td.setInnerText( "" );

					childWidget = widget;
					if( childWidget != null )
					{
						customPanel.addIn( td, childWidget );
					}
				}
			}

			@Override
			public void scrollIntoView()
			{
				TableCellElement td = getTd();
				if( td != null )
					td.scrollIntoView();
			}

			@Override
			public void addClassName( String className )
			{
				TableCellElement td = getTd();
				if( td != null )
					td.addClassName( className );
			}

			@Override
			public void removeClassName( String className )
			{
				TableCellElement td = getTd();
				if( td != null )
					td.removeClassName( className );
			}

			@Override
			public Cell getNextCell()
			{
				if( getCellIndex() < cells.size() - 1 )
					return cells.get( getCellIndex() + 1 );

				Row row = getNextRow();
				return row.getCell( 0 );
			}

			@Override
			public Cell getPreviousCell()
			{
				if( getCellIndex() > 0 )
					return cells.get( getCellIndex() - 1 );

				// last cell in the previous row
				Row row = getPreviousRow();
				return row.getCell( row.getCellCount() - 1 );
			}

			@Override
			public Size getDisplaySize()
			{
				Element td = getTd();
				Size size = new Size( td.getOffsetWidth(), td.getOffsetHeight() );
				return size;
			}

			protected void clearCellWidget()
			{
				if( childWidget != null )
				{
					customPanel.remove( childWidget );
					childWidget = null;
				}
			}
		}

		@Override
		public void addClassName( String className )
		{
			TableRowElement tr = getTr();
			if( tr != null )
				tr.addClassName( className );
		}

		@Override
		public void removeClassName( String className )
		{
			TableRowElement tr = getTr();
			if( tr != null )
				tr.removeClassName( className );
		}
	}

	public Row addRow()
	{
		return rootRow.addRow();
	}

	public int getNbColumns()
	{
		return tableColgroup.getChildCount();
	}

	public Row getRootRow()
	{
		return rootRow;
	}

	public Cell getCellForEvent( Event event )
	{
		Element td = getEventTargetCell( event );
		if( td == null )
			return null;

		Element tr = td.getParentElement();
		if( tr == null )
			return null;

		int column = DOM.getChildIndex( tr, td );

		RowImpl row = (RowImpl) tr.getPropertyObject( "rowimpl" );
		if( row == null )
			return null;

		return row.cells.get( column );
	}

	public Row getRowForElement( Element element )
	{
		Element tr = getEventTargetRow( element );
		if( tr == null )
			return null;

		RowImpl row = (RowImpl) tr.getPropertyObject( "rowimpl" );
		return row;
	}

	@Override
	public void onResize()
	{
	}

	@Override
	public void setAccessKey( char key )
	{
		focusImpl.setAccessKey( getElement(), key );
	}

	@Override
	public void setFocus( boolean focused )
	{
		if( focused )
		{
			focusImpl.focus( getElement() );
		}
		else
		{
			focusImpl.blur( getElement() );
		}
	}

	@Override
	public int getTabIndex()
	{
		return focusImpl.getTabIndex( getElement() );
	}

	@Override
	public void setTabIndex( int index )
	{
		focusImpl.setTabIndex( getElement(), index );
	}

	public HandlerRegistration addKeyDownHandler( final KeyDownHandler handler )
	{
		return addDomHandler( handler, KeyDownEvent.getType() );
	}

	public HandlerRegistration addKeyUpHandler( final KeyUpHandler handler )
	{
		return addDomHandler( handler, KeyUpEvent.getType() );
	}

	public HandlerRegistration addKeyPressHandler( final KeyPressHandler handler )
	{
		return addDomHandler( handler, KeyPressEvent.getType() );
	}

	public HandlerRegistration addCellClickHandler( final ClickHandler handler )
	{
		return addDomHandler( handler, ClickEvent.getType() );
	}

	public HandlerRegistration addCellDoubleClickHandler( final DoubleClickHandler handler )
	{
		return addDomHandler( handler, DoubleClickEvent.getType() );
	}

	public HandlerRegistration addCellMouseDownHandler( final MouseDownHandler handler )
	{
		return addDomHandler( handler, MouseDownEvent.getType() );
	}
	
	public HandlerRegistration addCellMouseUpHandler( final MouseUpHandler handler )
	{
		return addDomHandler( handler, MouseUpEvent.getType() );
	}

	protected Element getEventTargetCell( Event event )
	{
		Element me = getElement();

		Element td = DOM.eventGetTarget( event );
		for( ; td != null; td = DOM.getParent( td ) )
		{
			// If it's a TD, it might be the one we're looking for.
			if( td.getTagName().equalsIgnoreCase( "td" ) )
			{
				// Make sure it's directly a part of this table before returning
				// it
				Element tr = DOM.getParent( td );
				Element body = DOM.getParent( tr );
				if( body == tbody )
					return td;
			}

			// If we run into this widget's root element, we're out of options.
			if( td == me )
				return null;
		}

		return null;
	}
	
	protected Element getEventTargetRow( Element element )
	{
		Element me = getElement();

		for( ; element != null; element = DOM.getParent( element ) )
		{
			// If it's a TR, it might be the one we're looking for.
			if( element.getTagName().equalsIgnoreCase( "tr" ) )
			{
				// Make sure it's directly a part of this table before returning it
				Element body = DOM.getParent( element );
				if( body == tbody )
					return element;
			}

			// If we run into this widget's root element, we're out of options.
			if( element == me )
				return null;
		}

		return null;
	}
}
