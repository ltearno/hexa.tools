package fr.lteconsulting.hexa.client.ui.chart;

import com.google.gwt.dom.client.Style.Overflow;
import fr.lteconsulting.hexa.client.ui.chart.raphael.Raphael;

public class Chart extends Raphael
{
	int marginLeft = 0;
	int marginTop = 0;
	int marginRight = 0;
	int marginBottom = 0;

	int width;
	int height;

	public Chart( int width, int height )
	{
		super( width, height );

		getElement().getStyle().setOverflow( Overflow.HIDDEN );

		this.width = width;
		this.height = height;
	}

	public void setMargins( int left, int top, int right, int bottom )
	{
		marginLeft = left;
		marginRight = right;
		marginTop = top;
		marginBottom = bottom;
	}

	public int getMarginLeft()
	{
		return marginLeft;
	}

	public int getMarginTop()
	{
		return marginTop;
	}

	public int getInsideWidth()
	{
		return width - marginLeft - marginRight;
	}

	public Layer createLayer()
	{
		return new Layer( this );
	}
}