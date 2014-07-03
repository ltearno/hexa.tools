package fr.lteconsulting.hexa.client.ui.chart;

import java.util.ArrayList;
import java.util.Collections;

public class XYSerie
{
	ArrayList<Float> xs = null;
	ArrayList<Float> ys = null;

	String title;
	String color;

	public XYSerie( String title, String color )
	{
		this.title = title;
		this.color = color;
	}

	public void fill( ArrayList<Float> xs, ArrayList<Float> ys )
	{
		this.xs = new ArrayList<Float>( xs );
		this.ys = new ArrayList<Float>( ys );
	}

	public void shiftWith( float x, float y )
	{
		xs.remove( 0 );
		xs.add( x );

		ys.remove( 0 );
		ys.add( y );
	}

	public ArrayList<Float> getXs()
	{
		return xs;
	}

	public ArrayList<Float> getYs()
	{
		return ys;
	}

	public String getTitle()
	{
		return title;
	}

	public String getColor()
	{
		return color;
	}

	public int getNearestPoint( float x )
	{
		int idx = Collections.binarySearch( xs, x );
		if( idx < 0 )
			idx = -idx - 1;
		return idx;

		// find in the serie the closest point with x<xs[i]
		// int idx = 0;
		// for( int i=0; i<xs.size(); i++ )
		// {
		// if( xs.get(i) <= x )
		// idx = i;
		// else
		// break;
		// }
		//
		// return idx;
	}

	/*
	 * These functions are just for testing purpose only, really...
	 */

	public void generateRandom( int nbPoints, float minX, float maxX, float minY, float maxY )
	{
		xs = new ArrayList<Float>();
		ys = new ArrayList<Float>();

		for( int i = 0; i < nbPoints; i++ )
		{
			float r = (float) Math.random();
			r = (float) (((int) (r * 100)) / 100.0);
			xs.add( minX + (i * (maxX - minX) / (nbPoints - 1)) );
			ys.add( minY + r * (maxY - minY) );
		}
	}

	public void generateFormula( int idx, int nbPoints, float minX, float maxX, float minY, float maxY )
	{
		xs = new ArrayList<Float>();
		ys = new ArrayList<Float>();

		for( int i = 0; i < nbPoints; i++ )
		{
			xs.add( minX + (i * (maxX - minX) / (nbPoints - 1)) );

			double r = minY + 2.0 + (0.5 + (Math.cos( xs.get( i ) * (1 + idx) ) / 2.0)) * (maxY - minY);
			r *= (1 + idx);
			ys.add( (float) (minY + r * (maxY - minY)) );
		}
	}
}
