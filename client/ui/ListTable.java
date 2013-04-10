package com.hexa.client.ui;

import java.util.HashMap;

import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;

public class ListTable<T> extends Composite
{
	FlexTable table = new FlexTable();
	HashMap<Integer, T> rows = new HashMap<Integer, T>();

	T selected = null;

	public ListTable()
	{
		initWidget( table );

		setStylePrimaryName( "ListTable" );

		table.addClickHandler( new ClickHandler() {
			public void onClick( ClickEvent event )
			{
				Cell cell = table.getCellForEvent( event );
				if( cell == null )
					return;
				selected = rows.get( cell.getRowIndex() );
			}
		} );
	}

	public void addItem( String text, T object )
	{
		int row = table.getRowCount();

		rows.put( row, object );
		table.setText( row, 0, text );
	}

	public void addChangeHandler( final ChangeHandler handler )
	{
		table.addClickHandler( new ClickHandler() {
			public void onClick( ClickEvent event )
			{
				handler.onChange( null );
			}
		} );
	}

	public T getSelected()
	{
		return selected;
	}

	public void setSelected( T selected )
	{
		this.selected = selected;
	}
}
