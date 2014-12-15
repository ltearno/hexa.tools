package fr.lteconsulting.hexa.client.ui;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

import fr.lteconsulting.hexa.client.css.HexaCss;

public class ListTable<T> extends Composite
{
	interface Css extends HexaCss
	{
		public static final Css CSS = GWT.create( Css.class );

		String main();
	}

	FlexTable table = new FlexTable();
	HashMap<Integer, T> rows = new HashMap<Integer, T>();

	T selected = null;

	public ListTable()
	{
		initWidget( table );

		setStylePrimaryName( Css.CSS.main() );

		table.addClickHandler( new ClickHandler()
		{
			@Override
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
		table.addClickHandler( new ClickHandler()
		{
			@Override
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
