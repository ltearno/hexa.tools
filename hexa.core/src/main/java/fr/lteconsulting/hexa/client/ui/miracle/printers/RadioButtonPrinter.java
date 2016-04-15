package fr.lteconsulting.hexa.client.ui.miracle.printers;

import com.google.gwt.user.client.ui.RadioButton;
import fr.lteconsulting.hexa.client.ui.miracle.TextPrinter;

public class RadioButtonPrinter implements TextPrinter
{
	private final RadioButton r;

	public RadioButtonPrinter( RadioButton r )
	{
		this.r = r;
	}

	@Override
	public void setText( String text )
	{
		r.setText( text );
	}
}