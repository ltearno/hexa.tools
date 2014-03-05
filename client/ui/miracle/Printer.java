package com.hexa.client.ui.miracle;

import com.google.gwt.user.client.ui.Widget;

public interface Printer extends TextPrinter, HtmlPrinter, WidgetPrinter
{
	// creates a printer clone that will be indefinitely available...
	Printer cloneForLaterUse();
	
	@Override
	void setText( String text );

	@Override
	void setHTML( String html );

	@Override
	void setWidget( Widget widget );
}
