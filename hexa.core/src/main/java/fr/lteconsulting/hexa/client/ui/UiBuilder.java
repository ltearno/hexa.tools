package fr.lteconsulting.hexa.client.ui;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;

import fr.lteconsulting.hexa.client.ui.widget.VerticalPanel;

public class UiBuilder
{
	public static <T extends HasWidgets.ForIsWidget> T addIn( T parent, IsWidget... children )
	{
		for( IsWidget widget : children )
			parent.add( widget );

		return parent;
	}

	public static HorizontalPanel horiz( IsWidget... children )
	{
		return addIn( new HorizontalPanel(), children );
	}

	public static VerticalPanel vert( IsWidget... children )
	{
		return addIn( new VerticalPanel(), children );
	}
}
