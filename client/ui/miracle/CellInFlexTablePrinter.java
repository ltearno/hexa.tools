package com.hexa.client.ui.miracle;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class CellInFlexTablePrinter implements Printer
{
	FlexTable table;
	int row;
	int col;

	public CellInFlexTablePrinter( FlexTable table, int row, int col )
	{
		this.table = table;
		this.row = row;
		this.col = col;
	}

	@Override
	public void setHTML( String html )
	{
		if( (table.getRowCount() > row) && (table.getCellCount( row ) > col) )
			table.clearCell( row, col );
		table.setHTML( row, col, html );
	}

	@Override
	public void setText( String text )
	{
		if( (table.getRowCount() > row) && (table.getCellCount( row ) > col) )
			table.clearCell( row, col );
		table.setText( row, col, text );
	}

	@Override
	public void setWidget( Widget widget )
	{
		table.setWidget( row, col, widget );
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