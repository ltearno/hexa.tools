package fr.lteconsulting.hexa.client.ui.chart.raphael;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

public class RaphaelWidget extends Widget
{
	public RaphaelJS overlay;

	public RaphaelWidget( int width, int height )
	{
		Element raphaelDiv = DOM.createDiv();
		setElement( raphaelDiv );
		overlay = RaphaelJS.create( raphaelDiv, width, height );
	}
}
