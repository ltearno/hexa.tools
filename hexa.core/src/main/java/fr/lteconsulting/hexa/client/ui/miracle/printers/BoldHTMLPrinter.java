package fr.lteconsulting.hexa.client.ui.miracle.printers;

import com.google.gwt.user.client.ui.Widget;
import fr.lteconsulting.hexa.client.ui.miracle.Printer;

public class BoldHTMLPrinter implements Printer
{
	Printer realPrinter;

	public BoldHTMLPrinter( Printer realPrinter )
	{
		this.realPrinter = realPrinter;
	}

	@Override
	public void setText( String text )
	{
		realPrinter.setHTML( "<b>" + text + "</b>" );
	}

	@Override
	public void setHTML( String html )
	{
		realPrinter.setHTML( "<b>" + html + "</b>" );
	}

	@Override
	public void setWidget( Widget widget )
	{
		assert false;
	}
}