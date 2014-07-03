package fr.lteconsulting.hexa.client.ui.chart.raphael;

import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

/**
 * Utility class that provides easy, type-checked access to the shape attributes
 * in Raphael shape properties and animation methods.
 * <p>
 * Example usage:
 * 
 * <pre>
 * Shape s = ...;
 * s.attr(new Attr().fill("#f20").opacity(50.0).rotation(45).strokeWidth(1));
 * </pre>
 * 
 * @author Tassos Bassoukos
 */
public class Attr extends JSONObject
{
	public enum StrokeLineCap
	{
		butt,
		square,
		round;
	}

	public enum StrokeLineJoin
	{
		bevel,
		miter,
		round;
	}

	public enum TextAnchor
	{
		start,
		middle,
		end;
	}

	public enum StrokeDashArray
	{
		SHORT_DASH( "-" ),
		SHORT_DOT( "." ),
		SHORT_DASH_DOT( "-." ),
		SHORT_DASH_DOT_DOT( "-.." ),
		DASH( "- " ),
		DOT( ". " ),
		LONG_DASH( "--" ),
		DASH_DOT( "- ." ),
		LONG_DASH_DOT( "--." ),
		LONG_DASH_DOT_DOT( "--.." );

		private String cssName;

		private StrokeDashArray( String name )
		{
			cssName = name;
		}

		public String getCssName()
		{
			return cssName;
		};
	}

	public Attr cursor( Cursor cursor )
	{
		this.put( "cursor", cursor == null ? null : new JSONString( cursor.getCssName() ) );
		return this;
	}

	public Attr cursor( String cursor )
	{
		this.put( "cursor", new JSONString( cursor ) );
		return this;
	}

	public Attr fill( String color )
	{
		this.put( "fill", new JSONString( color ) );
		return this;
	}

	public Attr cx( double cx )
	{
		this.put( "cx", new JSONNumber( cx ) );
		return this;
	}

	public Attr cy( double cy )
	{
		this.put( "cy", new JSONNumber( cy ) );
		return this;
	}

	public Attr x( double x )
	{
		this.put( "x", new JSONNumber( x ) );
		return this;
	}

	public Attr y( double y )
	{
		this.put( "y", new JSONNumber( y ) );
		return this;
	}

	public Attr rx( double rx )
	{
		this.put( "rx", new JSONNumber( rx ) );
		return this;
	}

	public Attr ry( double ry )
	{
		this.put( "ry", new JSONNumber( ry ) );
		return this;
	}

	public Attr r( double r )
	{
		this.put( "r", new JSONNumber( r ) );
		return this;
	}

	public Attr rotation( double r )
	{
		this.put( "rotation", new JSONNumber( r ) );
		return this;
	}

	public Attr opacity( double opacity )
	{
		this.put( "opacity", new JSONNumber( opacity ) );
		return this;
	}

	public Attr width( double rotation )
	{
		this.put( "rotation", new JSONNumber( rotation ) );
		return this;
	}

	public Attr height( double height )
	{
		this.put( "height", new JSONNumber( height ) );
		return this;
	}

	public Attr strokeOpacity( double strokeOpacity )
	{
		this.put( "stroke-opacity", new JSONNumber( strokeOpacity ) );
		return this;
	}

	public Attr fillOpacity( double fillOpacity )
	{
		this.put( "fill-opacity", new JSONNumber( fillOpacity ) );
		return this;
	}

	public Attr strokeWidth( double strokeWidth )
	{
		this.put( "stroke-width", new JSONNumber( strokeWidth ) );
		return this;
	}

	public Attr strokeMiterLimit( double strokeMiterLimit )
	{
		this.put( "stroke-miterlimit", new JSONNumber( strokeMiterLimit ) );
		return this;
	}

	public Attr fontSize( double fontSize )
	{
		this.put( "font-size", new JSONNumber( fontSize ) );
		return this;
	}

	public Attr clipRect( double x, double y, double width, double height )
	{
		this.put( "clip-rect", new JSONString( x + " " + y + " " + width + " " + height ) );
		return this;
	}

	public Attr font( String font )
	{
		this.put( "font", new JSONString( font ) );
		return this;
	}

	public Attr fontFamily( String fontFamily )
	{
		this.put( "font-family", new JSONString( fontFamily ) );
		return this;
	}

	public Attr fontWeight( String fontWeight )
	{
		this.put( "font-weight", new JSONString( fontWeight ) );
		return this;
	}

	public Attr path( PathBuilder path )
	{
		this.put( "path", new JSONString( path.toString() ) );
		return this;
	}

	public Attr path( String path )
	{
		this.put( "path", new JSONString( path ) );
		return this;
	}

	public Attr scale( double rx, double ry, double centerX, double centerY )
	{
		this.put( "scale", new JSONString( rx + " " + ry + " " + centerX + " " + centerY ) );
		return this;
	}

	public Attr scale( double rx, double ry )
	{
		this.put( "scale", new JSONString( rx + " " + ry ) );
		return this;
	}

	public Attr stroke( String color )
	{
		this.put( "stroke", new JSONString( color ) );
		return this;
	}

	public Attr strokeDash( StrokeDashArray dashes )
	{
		this.put( "stroke-dasharray", new JSONString( dashes.getCssName() ) );
		return this;
	}

	public Attr strokeDash( String dashes )
	{
		this.put( "stroke-dasharray", new JSONString( dashes ) );
		return this;
	}

	public Attr strokeLineCap( StrokeLineCap lineCap )
	{
		this.put( "stroke-linecap", new JSONString( lineCap.name() ) );
		return this;
	}

	public Attr strokeLineJoin( StrokeLineJoin lineJoin )
	{
		this.put( "stroke-linejoin", new JSONString( lineJoin.name() ) );
		return this;
	}

	public Attr textAnchor( TextAnchor anchor )
	{
		this.put( "text-Anchor", new JSONString( anchor.name() ) );
		return this;
	}

	public Attr title( String title )
	{
		this.put( "title", new JSONString( title ) );
		return this;
	}

	public Attr translation( double dx, double dy )
	{
		this.put( "translation", new JSONString( dx + " " + dy ) );
		return this;
	}
}
