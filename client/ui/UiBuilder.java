package com.hexa.client.ui;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class UiBuilder
{
	public static <T extends HasWidgets.ForIsWidget> T addIn( T parent, IsWidget... children )
	{
		for( IsWidget widget : children )
			parent.add( widget );

		return parent;
	}

	public static Widget horiz( IsWidget... children )
	{
		return addIn( new HorizontalPanel(), children );
	}

	public static Widget vert( IsWidget... children )
	{
		return addIn( new VerticalPanel(), children );
	}
}
