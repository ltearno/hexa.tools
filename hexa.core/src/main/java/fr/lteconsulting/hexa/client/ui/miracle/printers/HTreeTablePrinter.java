package fr.lteconsulting.hexa.client.ui.miracle.printers;

import com.google.gwt.user.client.ui.Widget;
import fr.lteconsulting.hexa.client.ui.htreetable.HTreeTable;
import fr.lteconsulting.hexa.client.ui.miracle.Printer;

public class HTreeTablePrinter implements Printer
{
	HTreeTable table;
	Object item;

	public HTreeTablePrinter( HTreeTable table, Object item )
	{
		this.table = table;
		this.item = item;
	}

	@Override
	public void setText( String text )
	{
		table.setText( item, text );
	}

	@Override
	public void setHTML( String html )
	{
		assert false;
	}

	@Override
	public void setWidget( Widget widget )
	{
		assert false;
	}
}