package fr.lteconsulting.hexa.client.ui.chart;

import fr.lteconsulting.hexa.client.ui.chart.raphael.Raphael.Circle;
import fr.lteconsulting.hexa.client.ui.chart.raphael.Raphael.Text;

public class LegendElement
{
	Layer layer = null;

	Circle dot = null;
	Text text = null;

	float textMargin = 1;

	float dotSize = 4;
	String dotStrokeColor = "#ffffff";
	float dotStrokeWidth = 2;
	float dotOpacity = 1;

	public void init( Layer layer )
	{
		this.layer = layer;
	}

	public void remove()
	{
		if( dot != null )
			dot.removeFromParent();
		if( text != null )
			text.removeFromParent();
		dot = null;
		text = null;
	}

	public void update( float x, float y, String color, String legend )
	{
		// Dot update

		if( dot == null )
		{
			dot = layer.addCircle( x, y, dotSize );
		}
		else
		{
			dot.attr( "cx", x ).attr( "cy", y );
		}

		dot.attr( "fill", color );
		dot.attr( "opacity", dotOpacity );
		dot.attr( "stroke", dotStrokeColor );
		dot.attr( "stroke-width", dotStrokeWidth );

		// Text update

		if( text == null )
		{
			text = layer.addText( x + dotSize + textMargin, y + 2, legend );
			text.attr( "text-anchor", "start" );
		}
		else
		{
			text.attr( "x", x + dotSize + textMargin );
			text.attr( "y", y + 2 );
			text.attr( "text", legend );
		}
	}

	public float getWidth()
	{
		if( text == null )
			return 0;

		return dotSize + textMargin + (float) (text.getBBox().width());
	}
}
