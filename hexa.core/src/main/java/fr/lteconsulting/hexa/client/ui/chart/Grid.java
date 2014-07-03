package fr.lteconsulting.hexa.client.ui.chart;

import java.util.ArrayList;

import fr.lteconsulting.hexa.client.ui.chart.raphael.PathBuilder;
import fr.lteconsulting.hexa.client.ui.chart.raphael.Raphael.Path;

public class Grid
{
	Layer layer = null;
	Referential ref = null;
	boolean fVertical;

	String stroke = "#d0d0d0";
	String strokeDashArray = "-";

	PathBuilder pathBuilder = new PathBuilder();

	Path path = null;

	public void init( Layer layer, Referential ref, boolean fVertical )
	{
		this.layer = layer;
		this.ref = ref;
		this.fVertical = fVertical;
	}

	public void update( ArrayList<Float> valuesToDraw )
	{
		preparePathBuilder( valuesToDraw );

		// create or update the object
		if( path != null )
			path.attr( "path", pathBuilder.toString() );
		else
			path = layer.addPath( pathBuilder );

		path.attr( "stroke-dasharray", strokeDashArray );
		path.attr( "stroke", stroke );
		path.attr( "stroke-width", "1" );
		path.attr( "fill", "none" );
		path.attr( "opacity", "1" );
	}

	private void preparePathBuilder( ArrayList<Float> valuesToDraw )
	{
		pathBuilder = new PathBuilder();
		// pathBuilder.clear();

		if( fVertical )
		{
			float realMinY = ref.getRealY( ref.getMinY() );
			float realMaxY = ref.getRealY( ref.getMaxY() );

			for( Float value : valuesToDraw )
			{
				float x = ref.getRealX( value );

				pathBuilder.M( x, realMinY );
				pathBuilder.L( x, realMaxY );
			}
		}
		else
		{
			float realMinX = ref.getRealX( ref.getMinX() );
			float realMaxX = ref.getRealX( ref.getMaxX() );

			for( Float value : valuesToDraw )
			{
				float y = ref.getRealY( value );

				pathBuilder.M( realMinX, y );
				pathBuilder.L( realMaxX, y );
			}
		}
	}
}
