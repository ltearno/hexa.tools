package fr.lteconsulting.hexa.client.ui.containers;

import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.user.client.ui.HeaderPanel;

/*
 * I didnt want to code this all by hand, so
 * i do a really dirty thing : use a super class and
 * hack it...
 */

public class ScrollHeaderPanel extends HeaderPanel
{
	public ScrollHeaderPanel()
	{
		super();

		// obtain the contentContainer element and set its overflow property to
		// AUTO, so that it is scrollable...
		((com.google.gwt.dom.client.Element) getElement().getChild( 2 )).getStyle().setOverflow( Overflow.AUTO );
	}
}
