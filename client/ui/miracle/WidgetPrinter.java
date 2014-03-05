package com.hexa.client.ui.miracle;

import com.google.gwt.user.client.ui.Widget;

public interface WidgetPrinter
{
	WidgetPrinter cloneWidgetPrinterForLaterUse();
	
	void setWidget( Widget widget );
}
