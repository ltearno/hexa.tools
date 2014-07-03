package fr.lteconsulting.hexa.client.ui.chart;

import java.util.ArrayList;

import fr.lteconsulting.hexa.client.ui.chart.raphael.PathBuilder;
import fr.lteconsulting.hexa.client.ui.chart.raphael.Raphael.Circle;
import fr.lteconsulting.hexa.client.ui.chart.raphael.Raphael.Path;

public class XYMoneyPrev implements ChartRemovable
{
	Layer layer = null;
	Referential ref = null;
	XYSerie serie = null;

	float prevAmount;

	public XYMoneyPrev( float prevAmount )
	{
		this.prevAmount = prevAmount;
	}

	// Chart objects
	Path fill = null;
	Path line = null;
	Circle[] dots = null;

	int emphasizedPoint = -1;

	// A BIT OF STYLING
	float fillOpacity = (float) 0.3;
	int lineStroke = 1; // 2
	float dotNormalSize = (float) 1;// 0.5; // 4
	int dotBigSize = 4; // 7
	int dotStrokeWidth = 1; // 2
	String dotStrokeColor = "#ffffff";

	public void remove()
	{
		if( fill != null )
		{
			fill.removeFromParent();
			fill = null;
		}

		if( line != null )
		{
			line.removeFromParent();
			line = null;
		}

		if( dots != null )
		{
			for( int i = 0; i < dots.length; i++ )
				dots[i].removeFromParent();
			dots = null;
		}
	}

	public void setLayer( Layer layer )
	{
		this.layer = layer;
	}

	public void setReferential( Referential referential )
	{
		this.ref = referential;
	}

	public void setSerie( XYSerie serie )
	{
		this.serie = serie;
	}

	public void setAll( Layer layer, Referential referential, XYSerie serie )
	{
		setLayer( layer );
		setReferential( referential );
		setSerie( serie );
	}

	// index < 0 means that we emphasize no point at all
	public void emphasizePoint( int index )
	{
		if( dots == null || dots.length < (index - 1) )
			return; // impossible !

		// if no change, nothing to do
		if( emphasizedPoint == index )
			return;

		// de-emphasize the current emphasized point
		if( emphasizedPoint >= 0 )
		{
			dots[emphasizedPoint].attr( "r", dotNormalSize );

			emphasizedPoint = -1;
		}

		if( index >= 0 )
		{
			dots[index].attr( "r", dotBigSize );

			emphasizedPoint = index;
		}
	}

	public void update()
	{
		// dumb check
		if( layer == null || ref == null || serie == null )
			return;

		// TODO : this is a hack to get the dot stroke color the same as the
		// line's color
		dotStrokeColor = serie.getColor();

		// now, create or update the objects
		updateLineAndFill();
		updateDots();
	}

	private void updateLineAndFill()
	{
		PathBuilder linePath = new PathBuilder();
		PathBuilder fillPath = new PathBuilder();

		ArrayList<Float> xs = serie.getXs();
		ArrayList<Float> ys = serie.getYs();
		String color = serie.getColor();

		// fillPath.M( ref.getRealX(ref.getMinX()), ref.getRealY(ref.getMinY())
		// );

		int nbPoints = serie.xs.size();
		for( int i = 0; i < nbPoints; i++ )
		{
			float x = xs.get( i );
			float y = ys.get( i ) + prevAmount;

			float realX = ref.getRealX( x );
			float realY = ref.getRealY( y );

			if( i == 0 )
			{
				fillPath.M( realX, ref.getRealY( prevAmount ) );

				linePath.M( realX, realY );
				fillPath.L( realX, realY );
			}
			else if( i < nbPoints - 1 )
			{
				linePath.L( realX, realY );
				fillPath.L( realX, realY );
			}
			else
			{
				linePath.L( realX, realY );
				fillPath.L( realX, realY );
			}
		}

		fillPath.L( ref.getRealX( ref.getMaxX() ), ref.getRealY( prevAmount ) );

		// Create or update the fill path object
		if( fill == null )
			fill = layer.addPath( fillPath );
		else
			fill.attr( "path", fillPath.toString() );

		// Update fill style
		fill.attr( "stroke", "none" );
		fill.attr( "fill", color );
		fill.attr( "opacity", fillOpacity );

		// Create or update the line path object
		if( line == null )
			line = layer.addPath( linePath );
		else
			line.attr( "path", linePath.toString() );

		// update line style
		line.attr( "stroke", color );
		line.attr( "stroke-width", lineStroke );
		line.attr( "stroke-linejoin", "round" );
	}

	// draw dots
	private void updateDots()
	{
		ArrayList<Float> xs = serie.getXs();
		ArrayList<Float> ys = serie.getYs();
		String color = serie.getColor();

		assert xs.size() == ys.size() : "BAD x and y arrays are not the same size : THEY SHOULD";

		emphasizePoint( -1 );

		int nbPoints = xs.size();

		if( dots == null )
		{
			dots = new Circle[nbPoints];
		}
		else if( dots.length != nbPoints )
		{
			for( int i = 0; i < dots.length; i++ )
				dots[i].removeFromParent();
			dots = new Circle[nbPoints];
		}

		for( int i = 0; i < nbPoints; i++ )
		{
			float x = xs.get( i );
			float y = ys.get( i );

			if( dots[i] == null )
			{
				dots[i] = layer.addCircle( ref.getRealX( x ), ref.getRealY( y ), dotNormalSize );
			}
			else
			{
				dots[i].attr( "cx", ref.getRealX( x ) );
				dots[i].attr( "cy", ref.getRealY( y ) );
				dots[i].attr( "r", dotNormalSize );
			}

			dots[i].attr( "fill", color );
			dots[i].attr( "opacity", "1" );
			dots[i].attr( "stroke", dotStrokeColor );
			dots[i].attr( "stroke-width", dotStrokeWidth );
		}
	}
}
