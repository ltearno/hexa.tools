package fr.lteconsulting.hexa.client.datatable;

import java.util.ArrayList;
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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.comm.HexaFramework;
import fr.lteconsulting.hexa.client.interfaces.IHasIntegerId;
import fr.lteconsulting.hexa.client.tableobserver.XTableListen;
import fr.lteconsulting.hexa.client.tools.Func1;
import fr.lteconsulting.hexa.client.ui.miracle.Printer;
import fr.lteconsulting.hexa.client.ui.miracle.Size;
import fr.lteconsulting.hexa.client.ui.tools.IColumn;
import fr.lteconsulting.hexa.client.ui.tools.IEditor;
import fr.lteconsulting.hexa.client.ui.tools.IEditorHost;

public class TableCollectionManager<T extends IHasIntegerId> implements HasSelectionHandlers<T>
{
	public interface Callback<T>
	{
		void onWantAdd();
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
	private final Button addButton;
	private Callback<T> callback;
	
	private final boolean withDeleteColumn;
	
	private final List<ColumnInfo<T>> columns;
	private final List<RowCustomizer<T>> customizers;
	
	DataTableCellSelection selection;
	T previouslySelectedRecord;
	
	HashMap<Integer, Row> rows = new HashMap<>();
	HashMap<Row, Integer> rowToRecordIds = new HashMap<>();
	HashMap<Integer, T> records = new HashMap<>();
	
	IEditor currentEditor;
	Cell editedCell;
	T editedRecord;
	
	/**
	 * If set, this hierarchy function allows to ask for the parent id of a record
	 */
	Func1<T, Integer> hierarchyFunction;
	
	public TableCollectionManager( boolean withDeleteColumn )
	{
		this.withDeleteColumn = withDeleteColumn;
		
		columns = new ArrayList<>();
		customizers = new ArrayList<>();
		table = new DataTable();
		addButton = new Button( "add" );
	}
	
	public void setHierarchyFunction( Func1<T, Integer> hierarchyFunction )
	{
		this.hierarchyFunction = hierarchyFunction;
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
					SelectionEvent.fire( TableCollectionManager.this, record );
				
				previouslySelectedRecord = record;
			}
		} );
		
		addButton.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				if( callback != null )
					callback.onWantAdd();
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
	
	public Widget getAddButton()
	{
		return addButton;
	}
	
	public XTableListen<T> getDataPlug()
	{
		return dataPlug;
	}
	
	protected void storeRow( T record, Row row )
	{
		rows.put( record.getId(), row );
		records.put( record.getId(), record );
		rowToRecordIds.put( row, record.getId() );
	}
	
	protected void forgetRow( int recordId )
	{
		Row row = rows.remove( recordId );
		if( row != null )
			rowToRecordIds.remove( row );
		records.remove( recordId );
	}
	
	protected void forgetAllRows()
	{
		rows.clear();
		records.clear();
		rowToRecordIds.clear();
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
		Integer recordId = rowToRecordIds.get( row );
		if( recordId == null )
			return null;
		
		return getRecordForId( recordId );
	}
	
	/**
	 * Gets the record object associated with id
	 * 
	 * @param recordId
	 * @return The object or null if none is registered with this id
	 */
	public T getRecordForId( int recordId )
	{
		T record = records.get( recordId );
		
		return record;
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
	
	/**
	 * Redraw all lines of the table, based on the current dataset
	 */
	public void redraw()
	{
		for( T record : records.values() )
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
	
	XTableListen<T> dataPlug = new XTableListen<T>()
	{
		@Override
		public void deleted( int recordId, T oldRecord )
		{
			Row row = rows.get( recordId );
			if( row != null )
				row.remove();
			
			forgetRow( recordId );
		}

		@Override
		public void updated( T record )
		{
			Row row = rows.get( record.getId() );
			if( row == null )
			{
				Row parentRow = getShouldBeParentRow( record );
				row = parentRow.addRow();
				
				storeRow( record, row );
			}
			else if( hierarchyFunction != null )
			{
				// check that the row has the correct parent
				Row shouldBeParentRow = getShouldBeParentRow( record );
				if( shouldBeParentRow != row.getParentRow() )
				{
					shouldBeParentRow.acceptAsLastChild( row );
				}
			}
			
			printRecordOnRow( record, row );
			
			for( RowCustomizer<T> customizer : customizers )
				customizer.onAfterPrint( row, record );
		}
		
		private Row getShouldBeParentRow( T record )
		{
			if( record == null )
				return null;
			
			if( hierarchyFunction == null )
				return table.getRootRow();
			
			Integer parentRecordId = hierarchyFunction.exec( record );
			if( parentRecordId==null || parentRecordId <= 0 )
				return table.getRootRow();
			
			Row parentRow = rows.get( parentRecordId );
			if( parentRow == null )
				throw new RuntimeException( "Parent row not found ! You should insert parents first." );
			
			return parentRow;
		}

		@Override
		public void updatedField( String fieldName, T record )
		{
			updated( record );
		}

		@Override
		public void wholeTable( Iterable<T> records )
		{
			clearAll();
			
			for( T record : records )
				updated( record );
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
		return rows.get( record.getId() );
	}

	public Row getRowForRecordId( int recordId )
	{
		return rows.get( recordId );
	}
}
