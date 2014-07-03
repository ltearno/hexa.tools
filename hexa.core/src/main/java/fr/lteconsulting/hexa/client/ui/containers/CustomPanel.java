package fr.lteconsulting.hexa.client.ui.containers;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public class CustomPanel extends ComplexPanel
{
	public CustomPanel( Element element )
	{
		setElement( element );
	}

	public void addIn( Element element, Widget child )
	{
		add( child, element );
	}
}
