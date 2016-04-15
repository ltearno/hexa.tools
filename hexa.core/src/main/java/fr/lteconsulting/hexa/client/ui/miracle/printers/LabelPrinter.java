package fr.lteconsulting.hexa.client.ui.miracle.printers;

import com.google.gwt.user.client.ui.Label;
import fr.lteconsulting.hexa.client.ui.miracle.TextPrinter;

public class LabelPrinter implements TextPrinter
{
	private final Label label;

	public LabelPrinter( Label label )
	{
		this.label = label;
	}

	@Override
	public void setText( String text )
	{
		label.setText( text );
	}
}