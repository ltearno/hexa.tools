package fr.lteconsulting.hexa.client.datatable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.comm.HexaFramework;
import fr.lteconsulting.hexa.client.tools.Func1;
import fr.lteconsulting.hexa.client.ui.miracle.Printer;
import fr.lteconsulting.hexa.client.ui.miracle.Size;
import fr.lteconsulting.hexa.client.ui.tools.IColumn;
import fr.lteconsulting.hexa.client.ui.tools.IEditor;
import fr.lteconsulting.hexa.client.ui.tools.IEditorHost;

public class ObjectCollectionManager<T> implements HasSelectionHandlers<T>
{
	public interface Callback<T>
	{
		void onWantDelete( T record );
	}
	
	public interface RowCustomizer<T>
	{
		void onAfterPrint( Row row, T record );
	}
	
	private static class ColumnInfo<T>
	{
		public ColumnInfo( IColumn<T> column, String size )
		{
			this.column = column;
			this.size = size;
		}
		
		private final IColumn<T> column;
		private final String size;
	}
	
	private final SimpleEventBus bus = new SimpleEventBus();
	
	private final DataTable table;
	private Callback<T> callback;
	
	private final List<ColumnInfo<T>> columns;
	private final List<RowCustomizer<T>> customizers;
	
	private final boolean withDeleteColumn;
	
	DataTableCellSelection selection;
	T previouslySelectedRecord;
	
	HashMap<T, Row> rows = new HashMap<>();
	HashMap<Row, T> records = new HashMap<>();
	
	IEditor currentEditor;
	Cell editedCell;
	T editedRecord;
	
	/**
	 * If set, this hierarchy function allows to ask for the parent of a record
	 */
	Func1<T, T> hierarchyFunction;
	
	Comparator<T> sortFunction;
	
	public ObjectCollectionManager( boolean withDeleteColumn )
	{
		this.withDeleteColumn = withDeleteColumn;
		
		columns = new ArrayList<>();
		customizers = new ArrayList<>();
		table = new DataTable();
	}
	
	public void setHierarchyFunction( Func1<T, T> hierarchyFunction )
	{
		this.hierarchyFunction = hierarchyFunction;
	}
	
	public void setSortFunction( Comparator<T> comparator )
	{
		this.sortFunction = comparator;
	}
	
	public void addColumn( IColumn<T> column, String size )
	{
		columns.add( new ColumnInfo<T>( column, size ) );
	}
	
	public void addRowCustomizer( RowCustomizer<T> customizer )
	{
		customizers.add( customizer );
	}
	
	public void init()
	{
		if( withDeleteColumn )
		{
			addColumn( new IColumn<T>()
			{
				@Override
				public void fillCell( Printer printer, final T record )
				{
					printer.setHTML( "<img src='" + HexaFramework.images.delete().getSafeUri().asString() + "'/>" );
				}
	
				@Override
				public String getTitle()
				{
					return "";
				}
	
				@Override
				public IEditor editCell( T record )
				{
					return null;
				}
			}, "40px" );
			
			final ColumnInfo<T> newColumn = columns.get( columns.size() - 1 );
			
			// handles clicks on the last column (delete column)
			table.addCellClickHandler( new ClickHandler()
			{
				@Override
				public void onClick( ClickEvent event )
				{
					Cell cell = table.getCellForEvent( event.getNativeEvent().<Event>cast() );
					if( cell == null )
						return;
					
					// delete column is the last column
					if( cell.getCellIndex() != columns.indexOf( newColumn ) )
						return;
					
					T record = getRecordForRow( cell.getParentRow() );
					if( callback != null )
						callback.onWantDelete( record );
				}
			} );
		}
		
		for( ColumnInfo<T> c : columns )
			table.addColumn( c.column.getTitle(), c.size );
		
		selection = new DataTableCellSelection( table );
		selection.init();
		selection.addSelectionHandler( new SelectionHandler<Cell>()
		{
			@Override
			public void onSelection( SelectionEvent<Cell> event )
			{
				cancelCurrentEdition();
				
				Cell cell = selection.getSelectedCell();
				if( cell == null )
					return;
				
				T record = getRecordForRow( cell.getParentRow() );
				
				if( previouslySelectedRecord != record )
					SelectionEvent.fire( ObjectCollectionManager.this, record );
				
				previouslySelectedRecord = record;
			}
		} );
		
		table.addKeyUpHandler( new KeyUpHandler()
		{
			@Override
			public void onKeyUp( KeyUpEvent event )
			{
				if( event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER
						|| event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F2 )
				{
					editCell( selection.getSelectedCell() );
				}
			}
		} );
		
		table.addCellDoubleClickHandler( new DoubleClickHandler()
		{
			@Override
			public void onDoubleClick( DoubleClickEvent event )
			{
				editCell( selection.getSelectedCell() );
			}
		});
	}
	
	public void setCallback( Callback<T> callback )
	{
		this.callback = callback;
	}
	
	public DataTable getTable()
	{
		return table;
	}
	
	public DataPlug<T> getDataPlug()
	{
		return dataPlug;
	}
	
	protected void storeRow( T record, Row row )
	{
		rows.put( record, row );
		records.put( row, record );
	}
	
	protected void forgetRow( T record )
	{
		Row row = rows.remove( record );
		if( row != null )
			records.remove( row );
	}
	
	protected void forgetAllRows()
	{
		rows.clear();
		records.clear();
	}
	
	private void printRecordOnRow( T record, Row row )
	{
		if( row == null )
			return;
		
		int i = 0;
		for( ColumnInfo<T> c : columns )
			c.column.fillCell( row.getCell( i++ ), record );
		
		for( RowCustomizer<T> customizer : customizers )
			customizer.onAfterPrint( row, record );
	}
	
	/**
	 * Begin cell edition
	 * 
	 * @param cell
	 */
	private void editCell( Cell cell )
	{
		if( cell == null || cell == editedCell )
			return;
		
		T record = getRecordForRow( cell.getParentRow() );
		if( record == null )
			return;
		
		registerCurrentEdition( cell, record );
	}
	
	/**
	 * Gets the record associated with a specific Row
	 * 
	 * @param row The row
	 * @return The associated record, or null.
	 */
	public T getRecordForRow( Row row )
	{
		return records.get( row );
	}
	
	private void registerCurrentEdition( Cell cell, T record )
	{
		// maybe cancel current edition
		cancelCurrentEdition();
		
		// create an editor
		int column = cell.getCellIndex();
		currentEditor = columns.get( column ).column.editCell( record );
		
		if( currentEditor == null )
			return;
		
		// register current edition
		editedCell = cell;
		editedRecord = record;
		
		// register editor as current and begin talk with it
		currentEditor.setHost( editorHost );
		
		Widget editorWidget = currentEditor.getWidget();
		Style s = editorWidget.getElement().getStyle();
		s.setWidth( 100, Unit.PCT );
		s.setProperty( "border", "none" );
		s.setPadding( 0, Unit.PX );
		s.setHeight( 100, Unit.PCT );
		
		cell.setWidget( editorWidget );
	}
	
	private final IEditorHost editorHost = new IEditorHost()
	{
		@Override
		public Size getPreferredSize()
		{
			if( editedCell == null )
				return null;
			
			return editedCell.getDisplaySize();
		}
		
		@Override
		public void finishedEdition()
		{
			cancelCurrentEdition();
			table.setFocus( true );
		}
	};
	
	private void cancelCurrentEdition()
	{
		if( editedCell != null )
		{
			printRecordOnRow( editedRecord, editedCell.getParentRow() );
			editedCell = null;
		}
		
		if( currentEditor != null )
		{
			currentEditor.setHost( null );
			currentEditor = null;
		}
		
		editedRecord = null;
	}
	
	public void redraw()
	{
		for( T record : rows.keySet() )
			dataPlug.updated( record );
	}
	
	public void visitDepthFirstPre( Visitor<Row> visitor )
	{
		table.getRootRow().visitDepthFirstPre( visitor );
	}
	
	public void visitDeepFirst( Row initRow, Visitor<Row> visitor )
	{
		initRow.visitDepthFirstPre( visitor );
	}
	
	public interface DataPlug<T>
	{
		void deleted( T record );
		void updated( T record );
		void clearAll();
	}
	
	DataPlug<T> dataPlug = new DataPlug<T>()
	{
		@Override
		public void deleted( T oldRecord )
		{
			Row row = rows.get( oldRecord );
			if( row != null )
				row.remove();
			
			forgetRow( oldRecord );
		}

		@Override
		public void updated( T record )
		{
			Row parentRow = getShouldBeParentRow( record );
			
			Row row = rows.get( record );
			if( row == null )
			{
				// create the row
				int insertPosition = getInsertPosition( record, parentRow );
				if( insertPosition < 0 )
				{
					row = parentRow.addRow();
				}
				else
				{
					row = parentRow.insertRowAt( insertPosition );
				}
				
				storeRow( record, row );
			}
			else
			{
				boolean move = false;
				
				if( parentRow != row.getParentRow() )
					move = true;
				
				if( !move && sortFunction != null )
				{
					// check position
					int currentPosition = parentRow.getChildrenRows().indexOf( row );
					
					// previous sibling, if existing should be inferior or equal
					if( currentPosition > 0 )
					{
						if( sortFunction.compare( records.get( parentRow.getChildrenRows().get( currentPosition - 1 ) ), record ) > 0 )
							move = true;
					}
					
					// next sibling, if existing should be superior or equal
					if( ! move && currentPosition < (parentRow.getChildrenRows().size() - 1) )
					{
						if( sortFunction.compare( records.get( parentRow.getChildrenRows().get( currentPosition + 1 ) ), record ) < 0 )
							move = true;
					}
				}
					
				// if not correct, move !
				if( move )
				{
					// insert the row in the correct parent
					int insertPosition = getInsertPosition( record, parentRow );
					if( insertPosition < 0 )
					{
						parentRow.acceptAsLastChild( row );
					}
					else
					{
						parentRow.acceptAsNthChild( row, insertPosition );
					}
				}
			}
			
			printRecordOnRow( record, row );
			
			for( RowCustomizer<T> customizer : customizers )
				customizer.onAfterPrint( row, record );
		}
		
		private int getInsertPosition( T record, Row parentRow )
		{
			if( sortFunction!=null && parentRow.hasChildren() )
			{
				int i = 0;
				for( Row childRow : parentRow.getChildrenRows() )
				{
					T childRecord = records.get( childRow );
					if( childRecord == record )
						continue;
					int res = sortFunction.compare( record, childRecord );
					if( res <= 0 )
					{
						return i;
					}
					
					i++;
				}
			}
			
			// last
			return -1;
		}
		
		private Row getShouldBeParentRow( T record )
		{
			if( record == null )
				return null;
			
			if( hierarchyFunction == null )
				return table.getRootRow();
			
			T parentRecord = hierarchyFunction.exec( record );
			if( parentRecord == null )
				return table.getRootRow();
			
			Row parentRow = rows.get( parentRecord );
			if( parentRow == null )
				throw new RuntimeException( "Parent row not found ! You should insert parents first." );
			
			return parentRow;
		}

		@Override
		public void clearAll()
		{
			// remove from table
			for( Row row : rows.values() )
				row.remove();
			
			// forget all rows
			forgetAllRows();
		}
	};

	public T getSelectedRecord()
	{
		Cell selectedCell = selection.getSelectedCell();
		if( selectedCell == null )
			return null;
		return getRecordForRow( selectedCell.getParentRow() );
	}

	public List<T> getSelectedRecords()
	{
		List<Cell> cells = selection.getSelectedCells();
		List<T> records = new ArrayList<>();
		for( Cell cell : cells )
			records.add( getRecordForRow( cell.getParentRow() ) );
		return records;
	}

	@Override
	public void fireEvent( GwtEvent<?> event )
	{
		bus.fireEvent( event );
	}

	@Override
	public HandlerRegistration addSelectionHandler( final SelectionHandler<T> handler )
	{
		return bus.addHandler( SelectionEvent.getType(), handler );
	}

	public int getColumnIndex( IColumn<T> column )
	{
		for( int i=0; i<columns.size(); i++ )
			if( columns.get( i ).column == column )
				return i;
		return -1;
	}

	public void setSelectedRecord( int recordId )
	{
		Row row = rows.get( recordId );
		if( row == null )
			return;
		
		Cell cell = row.getCell( 0 );
		
		selection.setSelectedCell( cell );
	}

	public Row getRowForRecord( T record )
	{
		return rows.get( record );
	}
}
