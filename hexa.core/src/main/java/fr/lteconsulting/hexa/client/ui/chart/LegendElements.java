package fr.lteconsulting.hexa.client.ui.chart;

import java.util.ArrayList;

public class LegendElements
{
	ArrayList<LegendElement> elements = new ArrayList<LegendElement>();

	Layer layer = null;

	float spaceBetween = 10;

	public void init( Layer layer )
	{
		this.layer = layer;
	}

	public void update( float x, float y, String[] colors, String[] legends )
	{
		int nb = colors.length;

		// adjust array size
		while( elements.size() > nb )
			elements.remove( 0 ).remove();
		while( elements.size() < nb )
		{
			LegendElement legend = new LegendElement();
			legend.init( layer );
			elements.add( legend );
		}

		float pos = x;
		for( int i = 0; i < nb; i++ )
		{
			elements.get( i ).update( pos, y, colors[i], legends[i] );
			pos += elements.get( i ).getWidth() + spaceBetween;
		}
	}
}
