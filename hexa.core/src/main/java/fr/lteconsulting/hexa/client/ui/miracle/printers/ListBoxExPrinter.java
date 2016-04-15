package fr.lteconsulting.hexa.client.ui.miracle.printers;

import fr.lteconsulting.hexa.client.ui.miracle.TextPrinter;
import fr.lteconsulting.hexa.client.ui.widget.ListBoxEx;

public class ListBoxExPrinter implements TextPrinter
{
	private final ListBoxEx lb;
	private final int id;

	public ListBoxExPrinter( ListBoxEx lb, int id )
	{
		this.lb = lb;
		this.id = id;
	}

	@Override
	public void setText( String text )
	{
		lb.setItemText( id, text );
	}
}