package com.hexa.client.application.archi;

import com.google.gwt.user.client.ui.Widget;

public interface View
{
	Widget asWidget();

	public interface SelectionHandler<T>
	{
		void onSelected( T selected );
	}

	public interface HasSelectionHandlers<T>
	{
		void addSelectionhandler( SelectionHandler<T> handler );
	}
}
