package com.hexa.client.ui;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;

public class UiBuilder
{
	public static <T extends HasWidgets.ForIsWidget> T addIn( T parent, IsWidget... children )
	{
		for( IsWidget widget : children )
			parent.add( widget );

		return parent;
	}
}
