package com.hexa.client.raphael;

import com.google.gwt.core.client.JavaScriptObject;

public class RaphaelJS extends JavaScriptObject {

	  /**
	   * determine whether Raphael is defined on the client platform
	   * NB: do not call any other methods if Raphael is not defined
	   */
	  static public final native boolean isDefined() /*-{
	    return $wnd.Raphael!=undefined;
	  }-*/;

	  /**
	   * Raphael factory method
	   */
	  static public final native RaphaelJS create(String elementId, int width, int height) /*-{
	      return $wnd.Raphael(elementId, width, height);
	  }-*/;

	  /**
	   * Raphael factory method
	   */
	  static public final native RaphaelJS create(com.google.gwt.user.client.Element element, int width, int height) /*-{
	      return $wnd.Raphael(element, width, height);
	  }-*/;

	  /**
	   * bounding box object returned by Element.getBBox()
	   */
	  protected static class BBox extends JavaScriptObject {
	    protected BBox() {}
	    public final native double x() /*-{
	      if (this.x == undefined)
	        return -1;
	      else
	        return this.x;
	    }-*/;
	    public final native double y() /*-{
	      if (this.y == undefined)
	        return -1;
	      else
	        return this.y;
	    }-*/;
	    public final native double width() /*-{
	      if (this.width == undefined)
	        return -1;
	      else
	        return this.width;
	    }-*/;
	    public final native double height() /*-{
	      if (this.height == undefined)
	        return -1;
	      else
	        return this.height;
	    }-*/;
	  }

	  /**
	   * color object returned by Element.getColor() and friends
	   */
	  protected static class Color extends JavaScriptObject {
	    protected Color() {}
	    /**
	     * reset getColor() function to restart from the beginning
	     */
	    public final native void reset() /*-{
	      this.reset();
	    }-*/;
	  }

	  /**
	   * font object returned by Path.getFont() and friends
	   */
	  protected static class Font extends JavaScriptObject {
	    protected Font() {}
	  }

	  public static class Set extends Element {
	    protected Set() {}
	    public final native Set push(Element obj) /*-{
	      return this.push(obj);
	    }-*/;
	    public final native Element pop() /*-{
	      return this.pop();
	    }-*/;
	  }

	  /**
	   * overlay raphael class constructor - must be protected, empty, and no-argument
	   */
	  protected RaphaelJS() {}

	  /**
	   * Draw a circle to the Raphael canvas
	   *
	   * @param x     number x coordinate of the center
	   * @param y     number y coordinate of the center
	   * @param r     r number radius of the circle
	   *
	   * @return      the circle object
	   */
	  public final native Element circle(double x, double y, double r) /*-{
	    return this.circle(x,y,r);
	  }-*/;

	  /**
	   * Draw an ellipse to the Raphael canvas
	   *
	   * @param x     number x coordinate of the center
	   * @param y     number y coordinate of the center
	   * @param rx    number horizontal radius
	   * @param ry    number vertical radius
	   *
	   * @return      the ellipse object
	   */
	  public final native Element ellipse(double x, double y, double rx, double ry) /*-{
	    return this.ellipse(x, y, rx, ry);
	  }-*/;

	  /**
	   * return the next color in the spectrum
	   */
	  public final native Color getColor() /*-{
	    return this.getColor();
	  }-*/;

	  /**
	   * return the next color in the spectrum
	   *
	   * @param brightness
	   */
	  public final native Color getColor(double brightness) /*-{
	    return this.getColor(brightness);
	  }-*/;

	  public final native Font getFont(String family) /*-{
	    return this.getFont(family);
	  }-*/;

	  public final native Font getFont(String family, String weight) /*-{
	    return this.getFont(family, weight);
	  }-*/;

	  public final native Font getFont(String family, String weight, String style) /*-{
	    return this.getFont(family, weight, style);
	  }-*/;

	  public final native Font getFont(String family, String weight, String style, String stretch) /*-{
	    return this.getFont(family, weight, style, stretch);
	  }-*/;

	  /**
	   * parse color string and return a Color object
	   */
	  public final native Color getRGB(String color) /*-{
	    return this.getRGB(color);
	  }-*/;

	  /**
	   * Draw an image to the Raphael canvas
	   *
	   * @param src    string URI of the source image
	   * @param x      number x coordinate position
	   * @param y      number y coordinate position
	   * @param width  number width of the image
	   * @param height number height of the image
	   *
	   * @return       the image object
	   */
	  public final native Element image(String src, double x, double y, double width, double height) /*-{
	    return this.image(src, x, y, width, height);
	  }-*/;

	  /**
	   * Draws a path to the Raphael canvas
	   *
	   * @param obj    string Attributes for the resulting path as described in the attr reference.
	   *
	   * @return       the path object
	   */
	  public final native Path path() /*-{
	    return this.path();
	  }-*/;

	  /**
	   * Draws a path to the Raphael canvas given the SVG path string
	   *
	   * @param pathString   string Path data in SVG path string format.
	   *
	   * @return             the path object
	   */
	  public final native Path path(String pathString) /*-{
	    return this.path(pathString);
	  }-*/;

	  public final native Font print(double x, double y, String text, Font font, double font_size) /*-{
	    return this.print(x, y, text, font, font-size);
	  }-*/;

	  /**
	   * Draw a rectangle to the Raphael canvas
	   *
	   * @param x        number x coordinate of top left corner
	   * @param y        number y coordinate of top left corner
	   * @param width    width of the rectangle
	   * @param height   height of the rectangle
	   *
	   * @return         the rectangle object
	   */
	  public final native Element rect(double x, double y, double width, double height) /*-{
	    return this.rect(x, y, width, height);
	  }-*/;

	  /**
	   * Draw a rectangle (with rounded corners) to the Raphael canvas
	   *
	   * @param x        number x coordinate of top left corner
	   * @param y        number y coordinate of top left corner
	   * @param width    width of the rectangle
	   * @param height   height of the rectangle
	   * @param r        r number [optional] radius for rounded corners, default is 0
	   *
	   * @return         the rectangle object with rounded corners
	   */
	  public final native Element rect(double x, double y, double width, double height, double r) /*-{
	    return this.rect(x, y, width, height, r);
	  }-*/;

	  public final native Font registerFont(JavaScriptObject font) /*-{
	    return this.registerFont(font);
	  }-*/;

	  /**
	   * creates an object to hold and operate on multiple Elements
	   */
	  public final native Set set() /*-{
	    return this.set();
	  }-*/;

	  /**
	   * change the dimensions of the canvas
	   */
	  public final native Element setSize(int width, int height) /*-{
	    return this.setSize(width, height);
	  }-*/;

	  public final native Element text(double x, double y, String str) /*-{
	    return this.text(x, y, str);
	  }-*/;

	}