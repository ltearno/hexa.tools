package fr.lteconsulting.hexa.client.ui.widget;

import com.google.gwt.uibinder.client.UiConstructor;
import fr.lteconsulting.hexa.client.tools.HexaTools;
import fr.lteconsulting.hexa.client.ui.chart.raphael.RaphaelJS;
import fr.lteconsulting.hexa.client.ui.chart.raphael.RaphaelWidget;

public class GraphView extends RaphaelWidget
{
	int width;
	int height;

	double max = 200.0;
	int fadeDuration = 200;

	RaphaelJS.Element[] elements;
	RaphaelJS.Element[] elementsFill;

	@UiConstructor
	public GraphView( int width, int height, int nbGraphs, int nbPoints )
	{
		super( width, height );

		this.width = width;
		this.height = height;

		String path = "M0,0";

		elements = new RaphaelJS.Element[nbGraphs];
		elementsFill = new RaphaelJS.Element[nbGraphs];

		RaphaelJS.Set set = overlay.set();

		String[] colors = new String[] { "#ffe500", "red", "blue" };

		for( int i = 0; i < nbGraphs; i++ )
		{
			elements[i] = overlay.path( path );
			elements[i].attr( HexaTools.evalJSO( "{ 'stroke-width':3, stroke:'" + colors[i] + "', fill:'none' }" ) );

			elementsFill[i] = overlay.path( path );
			elementsFill[i].attr( HexaTools.evalJSO( "{ opacity: 0.3, stroke:'none', fill:'" + colors[i] + "' }" ) );

			set.push( elementsFill[i] );
			set.push( elements[i] );
		}
	}

	public void setMax( double max )
	{
		this.max = Math.max( max, 1 );
	}

	public void setData( int graphNo, double[] data )
	{
		if( (graphNo < 0) || (graphNo >= elements.length) )
			return;

		int nb = data.length;
		if( nb <= 0 )
			return;

		double x = width / nb;

		StringBuilder path = new StringBuilder();
		double prev = height - (height * data[0]) / max;
		path.append( "M0," + prev );
		for( int i = 1; i < nb; i++ )
		{
			double y = height - (height * data[i]) / max;
			double midX = ((2 * i) - 1) * x / 2.0;
			path.append( "C" );
			path.append( midX + "," + prev + "," );
			path.append( midX + "," + y + "," );
			path.append( x * i + "," + y + (i < nb - 1 ? "," : "") );
			prev = y;
		}

		elements[graphNo].animate( HexaTools.evalJSO( "{path:'" + path.toString() + "'}" ), fadeDuration );
		elementsFill[graphNo].animate( HexaTools.evalJSO( "{path:'" + path.toString() + "L" + width + "," + height + "L0," + height + "Z" + "'}" ), fadeDuration );
	}
}