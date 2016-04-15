package fr.lteconsulting.hexa.client.ui.miracle.printers;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTML;
import fr.lteconsulting.hexa.client.ui.miracle.HTMLPrinter;
import fr.lteconsulting.hexa.client.ui.miracle.TextPrinter;

public class HTMLWidgetPrinter implements HTMLPrinter, TextPrinter
{
	private final HTML html;
	
	public HTMLWidgetPrinter( HTML html )
	{
		this.html = html;
	}

	@Override
	public void setHTML( String html )
	{
		this.html.setHTML( html );
	}

	@Override
	public void setText( String text )
	{
		this.html.setHTML( new SafeHtmlBuilder().appendEscaped( text ).toSafeHtml() );
	}
}
