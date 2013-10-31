package com.hexa.client.ui.miracle;

import com.google.gwt.user.client.ui.Widget;
import com.hexa.client.ui.treetable.TreeTableBase.Item;

public class CellInTreeTablePrinter implements Printer
{
	Item item;
	int col;

	public CellInTreeTablePrinter()
	{
		this( null, -1 );
	}

	public CellInTreeTablePrinter( Item item, int col )
	{
		this.item = item;
		this.col = col;
	}

	public void setItemAndColumn( Item item, int col )
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