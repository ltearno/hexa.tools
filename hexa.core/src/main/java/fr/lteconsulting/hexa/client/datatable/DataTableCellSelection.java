package fr.lteconsulting.hexa.client.datatable;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Event;

/**
 * Provides management of a set of single selected cell in a DataTable.
 * It will listen for clicks and arrow keys to move the
 * selection cell inside the table.<br/>
 * 
 * The current selected cell's td element is applied the "selected"
 * css class.<br/>
 * 
 * One can get the currently selected cell by calling the getSelectedCell method.
 * 
 * @author Arnaud Tournier
 */
public class DataTableCellSelection implements HasSelectionHandlers<Cell>
{
	SimpleEventBus bus = new SimpleEventBus();
	
	DataTable table;
	
	List<Cell> selectedCells = new ArrayList<>();
	
	public DataTableCellSelection( DataTable table )
	{
		this.table = table;
	}
	
	public void init()
	{
		table.addKeyDownHandler( new KeyDownHandler()
		{
			@Override
			public void onKeyDown( KeyDownEvent event )
			{
				if( ! event.getNativeEvent().getEventTarget().equals( table.getElement() ) )
					return;
				
				if( selectedCells.isEmpty() )
				{
					updateSelectedCell( table.getRootRow().getChildrenRows().get( 0 ).getCell( 0 ), false );
					return;
				}
				
				Cell firstCell = selectedCells.get( 0 );
				
				switch( event.getNativeEvent().getKeyCode() )
				{
					case KeyCodes.KEY_RIGHT:
						if( firstCell.getCellIndex()==0 && firstCell.getParentRow().hasChildren() && firstCell.getParentRow().isFolded() )
						{
							firstCell.getParentRow().setFolded(false);
							break;
						}
						updateSelectedCell( firstCell.getNextCell(), false );
						break;
						
					case KeyCodes.KEY_LEFT:
						if( firstCell.getCellIndex()==0 && firstCell.getParentRow().hasChildren() && ! firstCell.getParentRow().isFolded() )
						{
							firstCell.getParentRow().setFolded(true);
							break;
						}
						updateSelectedCell( firstCell.getPreviousCell(), false );
						break;
					
					case KeyCodes.KEY_UP:
						updateSelectedCell( firstCell.getParentRow().getPreviousRow().getCell(firstCell.getCellIndex()), false );
						break;
					
					case KeyCodes.KEY_DOWN:
						updateSelectedCell( firstCell.getParentRow().getNextRow().getCell(firstCell.getCellIndex()), false );
						break;
				}
			}
		} );
		
		table.addCellMouseDownHandler( new MouseDownHandler()
		{
			@Override
			public void onMouseDown( MouseDownEvent event )
			{
				if( event.getNativeButton() == NativeEvent.BUTTON_LEFT )
				{
					Cell cell = table.getCellForEvent( Event.as( event.getNativeEvent() ) );
					updateSelectedCell( cell, event.isControlKeyDown() );
				}
			}
		} );
	}
	
	private static native Element getActiveElement()
	/*-{
		return $doc.activeElement;
	}-*/;
	
	public Cell getSelectedCell()
	{
		return selectedCells.isEmpty() ? null : selectedCells.get( 0 );
	}
	
	public List<Cell> getSelectedCells()
	{
		return selectedCells;
	}
	
	private void updateSelectedCell( Cell cell, boolean toggle )
	{
		if( toggle )
		{
			if( selectedCells.contains( cell ) )
			{
				cell.removeClassName( "selected" );
				cell.getParentRow().removeClassName( "selected" );
				selectedCells.remove( cell );
				return;
			}
		}
		else
		{
			if( selectedCells.size()==1 && cell==selectedCells.get( 0 ) )
				return;
			
			// clear current selection
			for( Cell c : selectedCells )
			{
				c.removeClassName( "selected" );
				c.getParentRow().removeClassName( "selected" );
			}
			selectedCells.clear();
		}
		
		if( cell != null )
		{
			selectedCells.add( cell );
			
			cell.addClassName( "selected" );
			cell.getParentRow().addClassName( "selected" );
			cell.scrollIntoView();
		}
		
		SelectionEvent.fire( this, cell );
	}

	@Override
	public void fireEvent( GwtEvent<?> event )
	{
		bus.fireEvent( event );
	}

	@Override
	public HandlerRegistration addSelectionHandler( SelectionHandler<Cell> handler )
	{
		return bus.addHandler( SelectionEvent.getType(), handler );
	}

	public void setSelectedCell( Cell cell )
	{
		updateSelectedCell( cell, false );
	}
}