package fr.lteconsulting.hexa.client.ui.chart.raphael;

import com.google.gwt.event.dom.client.HasMouseOverHandlers;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

public interface RaphaelObject extends Comparable<RaphaelObject>, HasMouseOverHandlers, Cloneable
{
	public RaphaelObject animate( JSONObject newAttrs, int duration );

	public RaphaelObject animate( JSONObject newAttrs, int duration, AnimationCallback callback );

	public RaphaelObject animate( JSONObject newAttrs, int duration, String easing );

	public RaphaelObject animate( JSONObject newAttrs, int duration, String easing, AnimationCallback callback );

	public RaphaelObject attr( String attributeName, String value );

	public RaphaelObject attr( String attributeName, double value );

	public RaphaelObject attr( JSONObject params );

	public double attrAsDouble( String name );

	public String attrAsString( String name );

	public JSONObject attr( JSONArray attributeNames );

	public BBox getBBox();

	public void removeFromParent();

	public RaphaelObject rotate( double degree );

	public RaphaelObject rotate( double degree, boolean isAbsolute );

	public RaphaelObject rotate( double degree, double cx, double cy );

	public RaphaelObject rotate( double degree, double cx, double cy, boolean isAbsolute );

	public RaphaelObject scale( double sx, double sy );

	public RaphaelObject scale( double sx, double sy, double cx, double cy );

	public RaphaelObject toFront();

	public RaphaelObject toBack();

	public RaphaelObject translate( double dx, double dy );
}
