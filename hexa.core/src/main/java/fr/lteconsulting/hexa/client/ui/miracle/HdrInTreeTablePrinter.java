package fr.lteconsulting.hexa.client.ui.miracle;

import com.google.gwt.user.client.ui.Widget;
import fr.lteconsulting.hexa.client.ui.treetable.TreeTable;

public class HdrInTreeTablePrinter implements Printer
{
	final TreeTable table;
	final int col;

	public HdrInTreeTablePrinter( TreeTable table, int col )
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
}