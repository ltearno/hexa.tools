package fr.lteconsulting.hexa.client.ui.miracle;

import com.google.gwt.user.client.ui.Widget;
import fr.lteconsulting.hexa.client.ui.treetable.Row;

public class CellInTreeTablePrinter implements Printer
{
	final Row item;
	final int col;

	public CellInTreeTablePrinter( Row item, int col )
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
}