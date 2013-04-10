package com.hexa.client.ui.chart;

import com.hydro4ge.raphaelgwt.client.PathBuilder;
import com.hydro4ge.raphaelgwt.client.Raphael.Circle;
import com.hydro4ge.raphaelgwt.client.Raphael.Path;
import com.hydro4ge.raphaelgwt.client.Raphael.Rect;
import com.hydro4ge.raphaelgwt.client.Raphael.Shape;
import com.hydro4ge.raphaelgwt.client.Raphael.Text;

public class Layer {
	Chart chart;
	
	Rect zOrderPlace = null;

	public Layer(Chart chart) {
		this.chart = chart;
		
		// draw something invisible so to keep track of our z-order in the graph
		zOrderPlace = chart.new Rect( 0, 0, 0, 0 );
		zOrderPlace.hide();
	}
	
	public float getWidth()
	{
		return chart.width;
	}
	
	public float getHeight()
	{
		return chart.height;
	}
	
	public Circle addCircle( float x, float y, float r )
	{
		return adjustZOrder( chart.new Circle( x, y, r ) );
	}
	
	public Rect addRect( float x, float y, float width, float height, float radius )
	{
		return adjustZOrder( chart.new Rect( x, y, width, height, radius ) );
	}
	
	public Path addPath( PathBuilder pathBuilder )
	{
		return adjustZOrder( chart.new Path( pathBuilder ) );
	}
	
	public Text addText( float x, float y, String text )
	{
		return adjustZOrder( chart.new Text(x, y, text) );
	}
	
	private <T extends Shape> T adjustZOrder( T shape )
	{
		shape.getElement().getParentElement().insertAfter( shape.getElement(), zOrderPlace.getElement() );
		
		return shape;
	}
}
