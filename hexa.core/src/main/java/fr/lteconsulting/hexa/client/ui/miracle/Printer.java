package fr.lteconsulting.hexa.client.ui.miracle;

import com.google.gwt.user.client.ui.Widget;

/**
 * Contract for printing on a receipient
 * 
 * This contract allows to print text, html, and widgets in a
 * container. But it does not give any insight on the container
 * itself
 * 
 * @author Arnaud
 *
 */
public interface Printer extends TextPrinter, HTMLPrinter, WidgetPrinter
{
	@Override
	void setText( String text );

	@Override
	void setHTML( String html );

	@Override
	void setWidget( Widget widget );
}
