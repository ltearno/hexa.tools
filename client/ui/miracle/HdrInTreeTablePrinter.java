package com.hexa.client.ui.miracle;

import com.google.gwt.user.client.ui.Widget;
import com.hexa.client.ui.treetable.TreeTable;

public class HdrInTreeTablePrinter implements Printer
{
	TreeTable table;
	int col;

	public HdrInTreeTablePrinter( TreeTable table, int col )
	{
		this.table = table;
		this.col = col;
	}

	public void setCol( int col )
	{
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

	@Override
	public TextPrinter cloneTextPrinterForLaterUse()
	{
		return cloneForLaterUse();
	}

	@Override
	public HTMLPrinter cloneHTMLPrinterForLaterUse()
	{
		return cloneForLaterUse();
	}

	@Override
	public WidgetPrinter cloneWidgetPrinterForLaterUse()
	{
		return cloneForLaterUse();
	}

	@Override
	public Printer cloneForLaterUse()
	{
		return this;
	}
}