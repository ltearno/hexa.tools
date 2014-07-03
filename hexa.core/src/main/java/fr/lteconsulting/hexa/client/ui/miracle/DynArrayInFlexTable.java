package fr.lteconsulting.hexa.client.ui.miracle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.Widget;
import fr.lteconsulting.hexa.client.tools.JQuery;
import fr.lteconsulting.hexa.client.ui.miracle.Edits.Editor;

public class DynArrayInFlexTable<T> implements Prints<Iterable<T>>, DynArrayManager<T>, HasColumns<T>
{
	MiracleTable table;
	RefMng<T> refMng;

	ArrayList<ColumnMng<T>> columns = new ArrayList<ColumnMng<T>>();

	Comparator<T> userComparator = null;

	// edition state
	private class EditionState
	{
		// int editedRow = -1;
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

	public DynArrayInFlexTable( MiracleTable table, RefMng<T> refMng )
	{
		this.table = table;
		this.refMng = refMng;

		table.addClickHandler( onTableClick );

		table.addDomHandler( onTableKeyUp, KeyDownEvent.getType() );

		table.addMouseDownHandler( onTableMouseDown );
	}

	@Override
	public void addColumn( PrintsOn<T> column, Edits<T> editMng, CellClickMng<T> clickMng, PrintsOn<Void> hdrPrintsOn, CellClickMng<Void> hdrClickMng )
	{
		columns.add( new ColumnMng<T>( column, editMng, clickMng, hdrPrintsOn, hdrClickMng ) );
	}

	@Override
	public void printHeaders()
	{
		int nbCols = columns.size();
		for( int i = 0; i < nbCols; i++ )
			columns.get( i ).hdrPrintsOn.print( null, table.getHdrPrinter( i ) );
	}

	@Override
	public void print( Iterable<T> data )
	{
		killCurrentEdit();
		table.clear( true );

		if( data == null )
			return;

		if( userComparator != null )
		{
			ArrayList<T> tmp = new ArrayList<T>();
			for( T t : data )
			{
				int ins = Collections.binarySearch( tmp, t, userComparator );
				tmp.add( -ins - 1, t );
			}
			data = tmp;
		}

		// each row
		int j = 0;
		for( T d : data )
		{
			printRow( d, j );

			j++;
		}
	}

	@Override
	public void updateRow( T object )
	{
		// find the row associated with this object
		int objectRef = refMng.getRef( object );
		int row = getRow( objectRef );

		if( edition != null && refMng.getRef( edition.editedObject ) == objectRef )
			killCurrentEdit();

		int insPos = userComparator != null ? getInsertPoint( object ) : row;

		// insert or move the row to the right place and create a cell printer
		if( row < 0 )
		{
			// new
			if( insPos >= 0 )
				table.insertRow( insPos );
			else
				insPos = table.getRowCount();
		}
		else
		{
			if( insPos != row )
			{
				GWT.log( "is at row " + row + " but should be at " + insPos );
				insPos = moveRowFor( row, insPos, objectRef );
			}
		}

		// print the row
		printRow( object, insPos );
	}

	// return the row it was moved to
	private int moveRowFor( int actual, int target, int objectRef )
	{
		Element tr = table.getBodyElement().getChild( actual ).cast();
		assert tr.getPropertyInt( "ref" ) == objectRef; // just to be sure we do what we want

		Element parent = tr.getParentElement().cast();

		parent.removeChild( tr );

		if( target < 0 )
		{
			DOM.appendChild( parent, tr );
			return parent.getChildCount() - 1;
		}

		if( target >= actual )
			target--;

		DOM.insertChild( parent, tr, target );

		return target;
	}

	@Override
	public void deleteRow( int ref )
	{
		int row = getRow( ref );
		if( row < 0 )
			return;

		// to reset the edition state, just in case... (an only if we are
		// editing on the row we delete)
		if( edition != null && refMng.getRef( edition.editedObject ) == ref )
			killCurrentEdit();

		table.removeRow( row );
	}

	@Override
	public void setComparator( Comparator<T> comparator )
	{
		userComparator = comparator;
		sortAndPrint( comparator );
	}

	public void sortAndPrint( final Comparator<T> userComparator )
	{
		class It
		{
			Element tr;
			T object;

			It( int row, Element tr, T object )
			{
				this.object = object;
				this.tr = tr;
			}
		}
		;

		ArrayList<It> its = new ArrayList<It>();

		// get all objects from the table
		int nbRows = table.getRowCount();
		for( int r = 0; r < nbRows; r++ )
		{
			Element tr = table.getRowFormatter().getElement( r );
			int ref = tr.getPropertyInt( "ref" );
			its.add( new It( r, tr, refMng.getObject( ref ) ) );
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
			int refAtRow = getRefAtRow( j );
			int needRef = refMng.getRef( its.get( j ).object );
			if( needRef != refAtRow )
			{
				// we have to take the row and put it at its right place
				Element tr = its.get( j ).tr;
				assert tr.getPropertyInt( "ref" ) == needRef; // just to be sure
																// we do what we
																// want
				DOM.insertChild( tr.getParentElement(), tr, j );
			}
		}
	}

	private int getRefAtRow( int row )
	{
		return table.getRowFormatter().getElement( row ).getPropertyInt( "ref" );
	}

	// return the row index of the row associated to the object referenced as
	// objectRef
	// returns -1 if the row is not found
	private int getRow( int objectRef )
	{
		JsArray<Element> rows = JQuery.get().jqSelect( "tr[ref=\"" + objectRef + "\"]", table.getBodyElement() );
		if( rows.length() > 1 )
			return -1; // an error actually
		if( rows.length() == 0 )
			return -1;
		Element tr = rows.get( 0 );
		int row = DOM.getChildIndex( tr.getParentElement(), tr );

		return row;
	}

	private void printRow( T object, int row )
	{
		// to reset the edition state, just in case...
		if( edition != null && refMng.getRef( edition.editedObject ) == refMng.getRef( object ) )
			killCurrentEdit();

		// each column
		for( int i = 0; i < columns.size(); i++ )
			columns.get( i ).prints.print( object, new CellInFlexTablePrinter( table, row, i ) );

		// writes the element reference on the corresponding row element
		// note that this does not create a hard link to the referenced object
		// so
		// no garbage is created here

		// to avoid cases when the colummn has printed nothing and the row
		// doesnt exist
		if( table.getRowCount() <= row )
			table.setText( row, 0, "" );
		table.getRowFormatter().getElement( row ).setPropertyInt( "ref", refMng.getRef( object ) );
	}

	private boolean beginEdit( int row, int col )
	{
		if( edition != null && getRefAtRow( row ) == refMng.getRef( edition.editedObject ) )
			return true; // already began !!!

		killCurrentEdit();

		Edits<T> editMng = columns.get( col ).edits;
		if( editMng == null )
			return false;

		// find the T object that has been clicked
		int ref = table.getRowFormatter().getElement( row ).getPropertyInt( "ref" );
		T object = refMng.getObject( ref );
		assert object != null;
		if( object == null )
			return false;

		Element td = table.getCellFormatter().getElement( row, col );

		edition = new EditionState();

		// initialize and set the current edit
		// edition.editedRow = row;
		edition.editedCol = col;
		edition.editedObject = object;

		// create a Printer corresponding to this cell
		edition.editedPrinter = new CellInFlexTablePrinter( table, row, col );

		edition.editedPrinter = new DynamicTablePrinter( object, col );

		// create an editor and init it
		// -2 to remove padding : HACK HACK HACK
		edition.editedEditor = editMng.createEditor( edition.editedObject, edition.editedPrinter, onEdit, td.getOffsetWidth() - 2, td.getClientHeight() - 2 );

		return true;
	}

	private class DynamicTablePrinter implements Printer
	{
		final CellInFlexTablePrinter printer;

		DynamicTablePrinter( T object, int col )
		{
			printer = new CellInFlexTablePrinter( table, getRow( refMng.getRef( object ) ), col );
		}

		@Override
		public void setWidget( Widget widget )
		{
			printer.setWidget( widget );
		}

		@Override
		public void setText( String text )
		{
			printer.setText( text );
		}

		@Override
		public void setHTML( String html )
		{
			printer.setHTML( html );
		}
	}

	private static class CellPos
	{
		int row;
		int col;

		CellPos( int row, int col )
		{
			this.row = row;
			this.col = col;
		}
	}

	private final ClickHandler onTableClick = new ClickHandler()
	{
		@Override
		public void onClick( ClickEvent event )
		{
			// event.preventDefault();
			// event.stopPropagation();

			Cell cell = DynArrayInFlexTable.this.table.getCellForEvent( event );

			// if clicking on a cell that is in editing mode, return
			if( cell != null && edition != null && (refMng.getRef( edition.editedObject ) == getRefAtRow( cell.getRowIndex() ) && edition.editedCol == cell.getCellIndex()) )
				return;

			killCurrentEdit();

			if( cell == null )
			{
				// try to see if it's not on a th element
				int hdr = table.getHeaderForEvent( event.getNativeEvent() );
				if( hdr < 0 )
					return;

				CellClickMng<Void> clickMng = columns.get( hdr ).hdrClickMng;
				if( clickMng == null )
					return;

				// get the printer, and go
				Printer printer = table.getHdrPrinter( hdr );
				clickMng.onTableClick( null, DOM.eventGetTarget( Event.as( event.getNativeEvent() ) ), printer );

				return;
			}

			boolean fHandled = false;

			// Tries edition
			fHandled = beginEdit( cell.getRowIndex(), cell.getCellIndex() );

			// If not handled, tries click manager
			if( !fHandled )
			{
				// if there is a click manager
				CellClickMng<T> clickMng = columns.get( cell.getCellIndex() ).clicks;
				if( clickMng != null )
				{
					// find the T object that has been clicked
					int ref = table.getRowFormatter().getElement( cell.getRowIndex() ).getPropertyInt( "ref" );
					T object = refMng.getObject( ref );
					if( object == null )
						return;

					CellInFlexTablePrinter pr = new CellInFlexTablePrinter( table, cell.getRowIndex(), cell.getCellIndex() );

					fHandled = clickMng.onTableClick( object, DOM.eventGetTarget( Event.as( event.getNativeEvent() ) ), pr );
				}
			}
		}
	};

	private final MouseDownHandler onTableMouseDown = new MouseDownHandler()
	{
		@Override
		public void onMouseDown( MouseDownEvent event )
		{
			// try to see if it's not on a th element
			int hdr = table.getHeaderForEvent( event.getNativeEvent() );
			if( hdr < 0 )
				return;

			Element th = table.getEventTargetHeader( Event.as( event.getNativeEvent() ) );
			DragDrop.initiate( th, onDragDrop, hdr, Event.as( event.getNativeEvent() ) );
		}
	};

	DragDrop.Callback<Integer> onDragDrop = new DragDrop.Callback<Integer>()
	{
		@Override
		public String getGhostInnerHTML( Integer cookie, Element source )
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void onDragDropFinished( Integer cookie, Element source, Element destination )
		{
			Element th = table.getElementTargetHeader( destination );
			if( th == null )
				return;

			int newPos = DOM.getChildIndex( DOM.getParent( th ), th );

			ColumnMng<T> dum = columns.get( cookie );
			columns.set( cookie, columns.get( newPos ) );
			columns.set( newPos, dum );

			printHeaders();
			// Redraw all objects in the table
			int nbRows = table.getRowCount();
			for( int r = 0; r < nbRows; r++ )
			{
				Element tr = table.getRowFormatter().getElement( r );
				int ref = tr.getPropertyInt( "ref" );
				printRow( refMng.getObject( ref ), r );
			}
		}
	};

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

				beginEdit( next.row, next.col );
			}
		}
	};

	// wait a bit to avoid ui jitter...
	// use refMng in case the data change during the time waited
	private void killCurrentEdit()
	{
		if( edition == null )
			return;

		// reprints the cell
		// cols.get( edition.editedCol ).print( edition.editedObject,
		// edition.editedPrinter );

		// reprints the cell, with uptodate data
		columns.get( edition.editedCol ).prints.print( refMng.getObject( refMng.getRef( edition.editedObject ) ), edition.editedPrinter );

		edition.close();
		edition = null;
	}

	private int getInsertPoint( T object )
	{
		int nbRows = table.getRowCount();
		int objectRef = refMng.getRef( object );

		if( userComparator != null )
		{
			// search the insert position
			for( int i = 0; i < nbRows; i++ )
			{
				int refAtRow = getRefAtRow( i );
				if( userComparator.compare( object, refMng.getObject( refAtRow ) ) <= 0 )
				{
					if( refAtRow == objectRef )
					{
						// keep the same place only if lesser than th nxt item
						// or if there is no next
						if( i + 1 == nbRows )
						{
							return i;
						}
						else
						{
							int refAtNextRow = getRefAtRow( i + 1 );
							if( userComparator.compare( object, refMng.getObject( refAtNextRow ) ) <= 0 )
								return i;
						}
					}
					else
					{
						return i;
					}
				}
			}
		}

		return -1;
	}

	CellPos getNextPos( int row, int col )
	{
		// either on the same row
		for( int c = col + 1; c < columns.size(); c++ )
		{
			if( columns.get( c ).edits != null )
				return new CellPos( row, c );
		}

		// or on the next
		row = (row + 1) % table.getRowCount();
		for( int c = 0; c < columns.size(); c++ )
		{
			if( columns.get( c ).edits != null )
				return new CellPos( row, c );
		}

		return null;
	}

	CellPos getPrevPos( int row, int col )
	{
		// either on the same row
		for( int c = col - 1; c >= 0; c-- )
		{
			if( columns.get( c ).edits != null )
				return new CellPos( col, c );
		}

		// or on the previous
		row = (row + table.getRowCount() - 1) % table.getRowCount();
		for( int c = columns.size() - 1; c >= 0; c-- )
		{
			if( columns.get( c ).edits != null )
				return new CellPos( row, c );
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

				int editionRow = getRow( refMng.getRef( edition.editedObject ) );
				CellPos pos = null;
				if( event.isShiftKeyDown() )
					pos = getPrevPos( editionRow, edition.editedCol );
				else
					pos = getNextPos( editionRow, edition.editedCol );

				if( pos != null )
				{
					beginEdit( pos.row, pos.col );
					return;
				}
			}
		}
	};

	@Override
	public void clearAllRows()
	{
		// to reset the edition state, just in case...
		killCurrentEdit();

		table.clear( true );
	}
}
