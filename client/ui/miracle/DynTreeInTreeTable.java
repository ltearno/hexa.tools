package com.hexa.client.ui.miracle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;
import com.hexa.client.ui.miracle.Edits.Editor;
import com.hexa.client.ui.treetable.TreeTable;
import com.hexa.client.ui.treetable.TreeTable.Row;
import com.hexa.client.ui.treetable.TreeTableHandler;

public class DynTreeInTreeTable<T> implements Prints<Iterable<T>>, DynArrayManager<T>
{
	public interface Resources
	{
		ImageResource treeMinus3();

		ImageResource treePlus3();

		ImageResource blank16();
	}

	interface DefaultResources extends Resources, ClientBundle
	{
		@Override
		@Source( "images/treeMinus3.png" )
		ImageResource treeMinus3();

		@Override
		@Source( "images/treePlus3.png" )
		ImageResource treePlus3();

		@Override
		@Source( "images/blank16.png" )
		ImageResource blank16();
	}

	private static Resources defaultResources = null;
	private Resources resources = null;

	TreeTable table;
	TreeRefMng<T> refMng;

	ArrayList<ColumnMng<T>> columns = new ArrayList<ColumnMng<T>>();

	Comparator<T> userComparator = null;

	// edition state
	private class EditionState
	{
		int editedCol = -1;
		T editedObject = null;
		Printer editedPrinter = null;
		Editor editedEditor = null;

		void close()
		{
			if( editedEditor != null )
				editedEditor.close();
			editedEditor = null;
		}
	}

	EditionState edition = null;

	public DynTreeInTreeTable( TreeTable table, TreeRefMng<T> refMng, Resources resources )
	{
		if( resources == null )
		{
			if( defaultResources == null )
				defaultResources = GWT.create( DefaultResources.class );

			this.resources = defaultResources;
		}
		else
		{
			this.resources = resources;
		}

		this.table = table;
		this.refMng = refMng;

		table.setHandler( tableHandler );
		table.addDomHandler( onTableKeyUp, KeyDownEvent.getType() );
		table.addDomHandler( onTableMouseDown, MouseDownEvent.getType() );
	}

	public DynTreeInTreeTable( TreeTable table, TreeRefMng<T> refMng )
	{
		this( table, refMng, null );
	}

	public void addColumn( PrintsOn<T> column, Edits<T> editMng, CellClickMng<T> clickMng, PrintsOn<Void> hdrPrintsOn, CellClickMng<Void> hdrClickMng )
	{
		columns.add( new ColumnMng<T>( column, editMng, clickMng, hdrPrintsOn, hdrClickMng ) );
	}

	class HdrInTreeTablePrinter implements Printer
	{
		TreeTable table;
		int col;

		HdrInTreeTablePrinter( TreeTable table, int col )
		{
			this.table = table;
			this.col = col;
		}

		@Override
		public void setText( String text )
		{
			table.setHeader( col, text );
		}

		@Override
		public void setHTML( String html )
		{
			setText( html );
		}

		@Override
		public void setWidget( Widget widget )
		{
			assert false;
			table.setHeader( col, "WIDGET PLACE" );
		}

		@Override
		public TextPrinter cloneTextPrinterForLaterUse()
		{
			return cloneForLaterUse();
		}

		@Override
		public HtmlPrinter cloneHTMLPrinterForLaterUse()
		{
			return cloneForLaterUse();
		}

		@Override
		public WidgetPrinter cloneWidgetPrinterForLaterUse()
		{
			return cloneForLaterUse();
		}

		@Override
		public Printer cloneForLaterUse()
		{
			return this;
		}
	}

	class CellInTreeTablePrinter implements Printer
	{
		Row item;
		int col;

		CellInTreeTablePrinter( Row item, int col )
		{
			this.item = item;
			this.col = col;
		}

		@Override
		public void setText( String text )
		{
			item.setText( col, text );
		}

		@Override
		public void setHTML( String html )
		{
			item.setHTML( col, html );
		}

		@Override
		public void setWidget( Widget widget )
		{
			item.setWidget( col, widget );
		}

		@Override
		public TextPrinter cloneTextPrinterForLaterUse()
		{
			return cloneForLaterUse();
		}

		@Override
		public HtmlPrinter cloneHTMLPrinterForLaterUse()
		{
			return cloneForLaterUse();
		}

		@Override
		public WidgetPrinter cloneWidgetPrinterForLaterUse()
		{
			return cloneForLaterUse();
		}

		@Override
		public Printer cloneForLaterUse()
		{
			return this;
		}
	}

	@Override
	public void printHeaders()
	{
		table.setHeader( 0, "+/-" );

		HdrInTreeTablePrinter printer = new HdrInTreeTablePrinter( table, 0 );
		int nbCols = columns.size();
		for( int i = 0; i < nbCols; i++ )
		{
			printer.col = i;

			ColumnMng<T> c = columns.get( i );
			if( c.hdrPrintsOn.print( null, printer ) )
				printer = new HdrInTreeTablePrinter( table, 0 );
		}

		// table.setColumnWidth( 0, 12 );
	}

	@Override
	public void print( Iterable<T> data )
	{
		killCurrentEdit();
		table.emptyTable();

		if( data == null )
			return;

		CellInTreeTablePrinter cp = new CellInTreeTablePrinter( null, 0 );

		// each row
		for( T d : data )
		{
			InsertPos insPos = getInsertPoint( d );
			cp.item = insPos.insert();
			cp.item.setRef( refMng.getRef( d ) );

			cp = printRow( d, cp );
		}
	}

	@Override
	public void updateRow( T object )
	{
		// find the row associated with this object
		int objectRef = refMng.getRef( object );
		Row item = getRow( objectRef );

		if( edition != null && refMng.getRef( edition.editedObject ) == objectRef )
			killCurrentEdit();

		// Item insPos = userComparator != null ? getInsertPoint( object ) :
		// item;
		InsertPos insPos = getInsertPoint( object );

		// insert or move the row to the right place and create a cell printer
		if( item == null )
		{
			// new item
			item = insPos.insert();

			item.setRef( refMng.getRef( object ) );
		}
		else
		{
			if( insPos.before != item )
			{
				insPos.move( item );
			}
		}
		CellInTreeTablePrinter cp = new CellInTreeTablePrinter( item, 0 );

		// print the row
		printRow( object, cp );
	}

	class InsertPos
	{
		Row parent;
		Row before;

		void move( Row item )
		{
			if( before != null )
				item.moveBefore( before );
			else
				item.moveLastChild( parent );
		}

		Row insert()
		{
			if( before != null )
				return before.addBefore();
			Row item = table.addRow( parent );
			if( parent != null )
			{
				int ref = getRefAtRow( parent );
				T obj = refMng.getObject( ref );

				PrinterFirstCol p = new PrinterFirstCol();
				p.item = parent;
				columns.get( 0 ).prints.print( obj, p );
			}
			return item;
		}
	}

	private InsertPos getInsertPoint( T object )
	{
		InsertPos res = new InsertPos();

		// first get the parent
		T parent = refMng.getParentObject( object );
		if( parent != null )
		{
			res.parent = getRow( refMng.getRef( parent ) );
			assert (res.parent != null) : "Cannot add a child of the parent is not firt added";
		}

		ArrayList<Row> items = table.getItemChilds( res.parent );
		int nbRows = items.size();
		int objectRef = refMng.getRef( object );

		if( userComparator != null )
		{
			for( Row item : items )
			{
				int refAtRow = item.getRef();

				if( userComparator.compare( object, refMng.getObject( refAtRow ) ) <= 0 )
				{
					if( refAtRow == objectRef )
					{
						// keep the same place only if lesser than the nxt item
						// or if there is no next
						if( items.indexOf( item ) == nbRows - 1 )
						{
							res.before = item;
							return res;
						}
						else
						{
							int refAtNextRow = getRefAtRow( item.getNextSiblingItem() );
							if( userComparator.compare( object, refMng.getObject( refAtNextRow ) ) <= 0 )
							{
								res.before = item;
								return res;
							}
						}
					}
					else
					{
						res.before = item;
						return res;
					}
				}
			}
		}

		return res;
	}

	@Override
	public void deleteRow( int ref )
	{
		GWT.log( "DELETE ROW " + ref );

		Row item = getRow( ref );
		if( item == null )
			return;

		// to reset the edition state, just in case... (an only if we are
		// editing on the row we delete)
		if( edition != null && refMng.getRef( edition.editedObject ) == ref )
			killCurrentEdit();

		Row parent = item.getParent();
		GWT.log( "PARENT : " + parent );
		GWT.log( "PARENT REF : " + (parent == null ? "-" : parent.getRef()) );

		GWT.log( "BEFORE REMOVE " + ref );
		item.remove();
		GWT.log( "AFTER REMOVE " + ref );

		if( parent != null )
		{
			GWT.log( "PARENT HAS N CHILD : " + parent.getChilds().size() );

			T obj = refMng.getObject( parent.getRef() );

			PrinterFirstCol p = new PrinterFirstCol();
			p.item = parent;
			columns.get( 0 ).prints.print( obj, p );
		}
	}

	@Override
	public void setComparator( Comparator<T> comparator )
	{
		userComparator = comparator;
		sortAndPrint( comparator );
	}

	public void sortAndPrint( Comparator<T> userComparator )
	{
		sortAndPrint( userComparator, null );
	}

	// sort all children of parent
	public void sortAndPrint( final Comparator<T> userComparator, Row parent )
	{
		class It
		{
			Row item;
			T object;

			It( Row item, T object )
			{
				this.object = object;
				this.item = item;
			}
		}
		;

		ArrayList<It> its = new ArrayList<It>();

		// get all objects from the table
		ArrayList<Row> childs = table.getItemChilds( parent );
		for( Row item : childs )
		{
			int ref = getRefAtRow( item );

			its.add( new It( item, refMng.getObject( ref ) ) );
		}

		// sort them
		Collections.sort( its, new Comparator<It>()
		{
			@Override
			public int compare( It o1, It o2 )
			{
				return userComparator.compare( o1.object, o2.object );
			}
		} );

		// now reorder the lines
		for( int j = 0; j < its.size(); j++ )
		{
			InsertPos ip = getInsertPoint( its.get( j ).object );
			if( ip.before != its.get( j ).item )
				ip.move( its.get( j ).item );
		}

		// proceed recursively for deeper level
		childs = table.getItemChilds( parent );
		for( Row item : childs )
			sortAndPrint( userComparator, item );
	}

	private int getRefAtRow( Row item )
	{
		return item.getRef();
	}

	// return the row index of the row associated to the object referenced as
	// objectRef
	// returns null if the row is not found
	private Row getRow( int objectRef )
	{
		return table.getItemForRef( objectRef );
	}

	// returns a printer that can be used for the next call. a new one can be
	// created
	// WARNING : assumes the printer item is correctly initialized
	private CellInTreeTablePrinter printRow( T object, CellInTreeTablePrinter printer )
	{
		// to reset the edition state, just in case...
		if( edition != null && refMng.getRef( edition.editedObject ) == refMng.getRef( object ) )
			killCurrentEdit();

		// writes the element reference on the corresponding row element
		// note that this does not create a hard link to the referenced object
		// so
		// no garbage is created here
		printer.item.setRef( refMng.getRef( object ) );

		// print the icon correcponding to expand/shrink state
		PrinterFirstCol firstCol = new PrinterFirstCol();
		firstCol.item = printer.item;
		columns.get( 0 ).prints.print( object, firstCol );
		// drawExpShrink( printer.item );

		// each column
		for( int i = 1; i < columns.size(); i++ )
		{
			printer.col = i;

			boolean fStillInUse = columns.get( i ).prints.print( object, printer );

			// recreate a printer if the old one is still in use by our client
			if( fStillInUse )
				printer = new CellInTreeTablePrinter( null, 0 );
		}

		return printer;
	}

	class PrinterFirstCol implements Printer
	{
		Row item;

		@Override
		public void setText( String text )
		{
			setHTML( text );
		}

		@Override
		public void setHTML( String html )
		{
			item.setHTML( 0, getExpShrinkHTML() + html );
		}

		@Override
		public void setWidget( Widget widget )
		{
			assert (false) : "NOT IMPLEMENTED : PUTTING WIDGETS IN THE FIRST COLUMN...";
		}

		private String getExpShrinkHTML()
		{
			if( item.hasChilds() )
			{
				if( item.getExpanded() )
					return "<img src='" + resources.treeMinus3().getSafeUri().asString() + "'/>";
				else
					return "<img src='" + resources.treePlus3().getSafeUri().asString() + "'/>";
			}
			else
				return "<img src='" + resources.blank16().getSafeUri().asString() + "'/>";
		}

		@Override
		public TextPrinter cloneTextPrinterForLaterUse()
		{
			return cloneForLaterUse();
		}

		@Override
		public HtmlPrinter cloneHTMLPrinterForLaterUse()
		{
			return cloneForLaterUse();
		}

		@Override
		public WidgetPrinter cloneWidgetPrinterForLaterUse()
		{
			return cloneForLaterUse();
		}

		@Override
		public Printer cloneForLaterUse()
		{
			return this;
		}
	}

	private boolean beginEdit( Row item, int col )
	{
		int ref = getRefAtRow( item );

		if( edition != null && ref == refMng.getRef( edition.editedObject ) )
			return true; // already began !!!

		killCurrentEdit();

		Edits<T> editMng = columns.get( col ).edits;
		if( editMng == null )
			return false;

		// find the T object that has been clicked
		T object = refMng.getObject( ref );
		assert object != null;
		if( object == null )
			return false;

		Element td = item.getTdElement( col );

		edition = new EditionState();

		// initialize and set the current edit
		edition.editedCol = col;
		edition.editedObject = object;

		// create a Printer corresponding to this cell
		edition.editedPrinter = new CellInTreeTablePrinter( item, col );

		// create an editor and init it
		// -2 to remove padding : HACK HACK HACK
		edition.editedEditor = editMng.createEditor( edition.editedObject, edition.editedPrinter, onEdit, td.getOffsetWidth() - 2, td.getClientHeight() - 2 );

		return true;
	}

	private static class CellPos
	{
		Row item;
		int col;

		CellPos( Row item, int col )
		{
			this.item = item;
			this.col = col;
		}
	}

	private final MouseDownHandler onTableMouseDown = new MouseDownHandler()
	{
		@Override
		public void onMouseDown( MouseDownEvent event )
		{
			Element source = DOM.eventGetTarget( Event.as( event.getNativeEvent() ) );

			// try to see if it's not on a th element
			int hdr = table.getEventTargetHeaderIdx( source );
			if( hdr < 0 )
				return;

			DragDrop.initiate( source, onDragDrop, hdr, Event.as( event.getNativeEvent() ) );
		}
	};

	DragDrop.Callback<Integer> onDragDrop = new DragDrop.Callback<Integer>()
	{
		@Override
		public String getGhostInnerHTML( Integer cookie, Element source )
		{
			// return
			// "<div style='background-color:rgba(120,120,120,0.4);'>Moving column "+cookie+"</div>";

			class TempPrinter implements Printer
			{
				String html = null;

				@Override
				public void setWidget( Widget widget )
				{
					assert false;
				}

				@Override
				public void setText( String text )
				{
					html = text;
				}

				@Override
				public void setHTML( String html )
				{
					this.html = html;
				}

				@Override
				public TextPrinter cloneTextPrinterForLaterUse()
				{
					return cloneForLaterUse();
				}

				@Override
				public HtmlPrinter cloneHTMLPrinterForLaterUse()
				{
					return cloneForLaterUse();
				}

				@Override
				public WidgetPrinter cloneWidgetPrinterForLaterUse()
				{
					return cloneForLaterUse();
				}

				@Override
				public Printer cloneForLaterUse()
				{
					return this;
				}
			}

			TempPrinter printer = new TempPrinter();

			columns.get( cookie ).hdrPrintsOn.print( null, printer );

			// Due to asynchronism, it may be possible that the printer did not
			// yet print anything, well... Too late, we use the source element
			// instead !!
			if( printer.html == null )
				return "<div style='background-color:rgba(120,120,120,0.4);'>" + source.getInnerHTML() + "</div>";

			return "<div style='background-color:rgba(120,120,120,0.4);width:20px;height:20px;'>" + printer.html + "</div>";
		}

		@Override
		public void onDragDropFinished( Integer cookie, Element source, Element destination )
		{
			int newPos = table.getEventTargetHeaderIdx( destination );
			if( newPos < 0 )
				return;

			ColumnMng<T> dum = columns.get( cookie );
			columns.set( cookie, columns.get( newPos ) );
			columns.set( newPos, dum );

			// Redraw headers
			printHeaders();

			// Redraw all objects in the table
			CellInTreeTablePrinter printer = new CellInTreeTablePrinter( null, 0 );
			redrawAll( null, printer );
		}
	};

	private CellInTreeTablePrinter redrawAll( Row parent, CellInTreeTablePrinter printer )
	{
		ArrayList<Row> items = table.getItemChilds( parent );
		for( Row item : items )
		{
			printer.item = item;
			int ref = item.getRef();
			printer = printRow( refMng.getObject( ref ), printer );

			printer = redrawAll( item, printer );
		}

		return printer;
	}

	private final Edits.Callback onEdit = new Edits.Callback()
	{
		@Override
		public void cancelEdition()
		{
			killCurrentEdit();
		}

		@Override
		public void validateEdition( boolean fJumpNext )
		{
			// just forget the edition state, but don't redraw
			// that means we let the client continue drawing on its cell
			if( edition != null && fJumpNext )
			{
				CellPos next = getNextPos( getRow( refMng.getRef( edition.editedObject ) ), edition.editedCol );

				edition.close();
				edition = null;

				beginEdit( next.item, next.col );
			}
		}
	};

	// wait a bit to avoid ui jitter...
	// use refMng in case the data change during the time waited
	private void killCurrentEdit()
	{
		if( edition == null )
			return;

		// reprints the cell, with uptodate data
		columns.get( edition.editedCol ).prints.print( refMng.getObject( refMng.getRef( edition.editedObject ) ), edition.editedPrinter );

		edition.close();
		edition = null;
	}

	CellPos getNextPos( Row item, int col )
	{

		// either on the same row
		for( int c = col + 1; c < columns.size(); c++ )
		{
			if( columns.get( c ).edits != null )
				return new CellPos( item, c );
		}

		// or on the next
		item = item.getNextTraversalItem();
		for( int c = 0; c < columns.size(); c++ )
		{
			if( columns.get( c ).edits != null )
				return new CellPos( item, c );
		}

		return null;
	}

	CellPos getPrevPos( Row item, int col )
	{
		// either on the same row
		for( int c = col - 1; c >= 0; c-- )
		{
			if( columns.get( c ).edits != null )
				return new CellPos( item, c );
		}

		// or on the previous
		item = item.getPrevTraversalItem();
		for( int c = columns.size() - 1; c >= 0; c-- )
		{
			if( columns.get( c ).edits != null )
				return new CellPos( item, c );
		}

		return null;
	}

	private final KeyDownHandler onTableKeyUp = new KeyDownHandler()
	{
		@Override
		public void onKeyDown( KeyDownEvent event )
		{
			if( event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE )
			{
				if( edition == null )
					return;

				event.stopPropagation();
				event.preventDefault();

				killCurrentEdit();
			}
			else if( event.getNativeKeyCode() == KeyCodes.KEY_TAB )
			{
				if( edition == null )
					return;

				event.stopPropagation();
				event.preventDefault();

				// find next cell to be edited

				Row editionItem = getRow( refMng.getRef( edition.editedObject ) );
				CellPos pos = null;
				if( event.isShiftKeyDown() )
					pos = getPrevPos( editionItem, edition.editedCol );
				else
					pos = getNextPos( editionItem, edition.editedCol );

				if( pos != null )
				{
					beginEdit( pos.item, pos.col );
					return;
				}
			}
		}
	};

	private final TreeTableHandler tableHandler = new TreeTableHandler()
	{
		@Override
		public void onTableHeaderClick( int column, ClickEvent event )
		{
			if( column < 0 )
				return;

			CellClickMng<Void> clickMng = columns.get( column ).hdrClickMng;
			if( clickMng == null )
				return;

			// get the printer, and go
			Printer printer = table.getHeaderPrinter( column );
			clickMng.onTableClick( null, DOM.eventGetTarget( Event.as( event.getNativeEvent() ) ), printer );
		}

		@Override
		public void onTableCellClick( Row item, int column, ClickEvent event )
		{
			int clickedRef = getRefAtRow( item );
			// if clicking on a cell that is in editing mode, return
			if( edition != null && (refMng.getRef( edition.editedObject ) == clickedRef && edition.editedCol == column) )
				return;

			killCurrentEdit();

			if( column == 0 )
			{
				// TODO : if clicked on the exp/shrink icon, do that
				// else, offer the possibility to edit the cell, or else send
				// the click event to click mng

				// expand / shrink item
				item.setExpanded( !item.getExpanded() );

				T obj = refMng.getObject( clickedRef );

				PrinterFirstCol p = new PrinterFirstCol();
				p.item = item;
				columns.get( 0 ).prints.print( obj, p );
				return;
			}

			boolean fHandled = false;

			// Tries edition
			fHandled = beginEdit( item, column );

			// If not handled, tries click manager
			if( !fHandled )
			{
				// if there is a click manager
				CellClickMng<T> clickMng = columns.get( column ).clicks;
				if( clickMng != null )
				{
					// find the T object that has been clicked
					T object = refMng.getObject( clickedRef );
					if( object == null )
						return;

					CellInTreeTablePrinter pr = new CellInTreeTablePrinter( item, column );

					fHandled = clickMng.onTableClick( object, DOM.eventGetTarget( Event.as( event.getNativeEvent() ) ), pr );
				}
			}
		}
	};

	@Override
	public void clearAllRows()
	{
		killCurrentEdit();

		table.clear();
	}
}
