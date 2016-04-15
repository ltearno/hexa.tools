package fr.lteconsulting.hexa.client.ui.miracle.printers;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;
import fr.lteconsulting.hexa.client.ui.miracle.Printer;

public class GridPrinter implements Printer
{
	private final Grid table;
	private final int row;
	private final int column;

	public GridPrinter( Grid grid, int row, int column )
	{
		this.table = grid;
		this.row = row;
		this.column = column;
	}

	@Override
	public void setText( String text )
	{
		table.setText( row, column, text );
	}

	@Override
	public void setHTML( String html )
	{
		table.setHTML( row, column, html );
	}

	@Override
	public void setWidget( Widget widget )
	{
		table.setWidget( row, column, widget );
	}
}