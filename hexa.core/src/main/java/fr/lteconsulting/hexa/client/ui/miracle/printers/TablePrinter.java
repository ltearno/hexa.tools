package fr.lteconsulting.hexa.client.ui.miracle.printers;

import com.google.gwt.user.client.ui.Widget;
import fr.lteconsulting.hexa.client.ui.miracle.Printer;
import fr.lteconsulting.hexa.client.ui.treetable.Row;

public class TablePrinter implements Printer
{
	private final Row row;
	private final int column;

	public TablePrinter( Row row, int column )
	{
		this.row = row;
		this.column = column;
	}

	@Override
	public void setText( String text )
	{
		row.setText( column, text );
	}

	@Override
	public void setHTML( String html )
	{
		row.setHTML( column, html );
	}

	@Override
	public void setWidget( Widget widget )
	{
		row.setWidget( column, widget );
	}
}