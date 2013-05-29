package com.hexa.client.raphael;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.hexa.client.raphael.RaphaelJS.BBox;

/**
 * element objected returned by circle(), rect(), etc.
 */
public class Element extends JavaScriptObject
{
	protected Element()
	{
	}

	/**
	 * Gives you a reference to the DOM object, so you can assign event handlers or just mess around.
	 * 
	 * @return reference to the current DOM object
	 */
	public final native com.google.gwt.user.client.Element node() /*-{
																	return this.node;
																	}-*/;

	/**
	 * Removes element from the DOM. You can't use it after this method call.
	 */
	public final native void remove() /*-{
										this.remove();
										}-*/;

	/**
	 * Makes element invisible
	 * 
	 * @return the javascript object hidden
	 */
	public final native Element hide() /*-{
										return this.hide();
										}-*/;

	/**
	 * Makes element visible
	 * 
	 * @return the javascript object made visible
	 */
	public final native Element show() /*-{
										return this.show();
										}-*/;

	/**
	 * Rotates the element by the given degree from its center point relatively.
	 * 
	 * @param degree
	 *            number of degrees of rotation (0-360)
	 * 
	 * @return the javascript object rotated
	 */
	public final native Element rotate( double degree ) /*-{
														return this.rotate(degree);
														}-*/;

	/**
	 * Rotates the element by the given degree from its center point.
	 * 
	 * @param degree
	 *            number of degrees of rotation (0-360)
	 * @param isAbsolute
	 *            boolean Specifies if degree is relative to previous position (false) or is it absolute angle (true)
	 * 
	 * @return the javascript object rotated
	 */
	public final native Element rotate( double degree, boolean isAbsolute ) /*-{
																			return this.rotate(degree, isAbsolute);
																			}-*/;

	/**
	 * Rotates the element by the given degree from its center point relatively.
	 * 
	 * @param degree
	 *            number of degrees of rotation (0-360)
	 * @param cx
	 *            number X coordinate of center of rotation
	 * @param cy
	 *            number Y coordinate of center of rotation
	 * 
	 * @return the javascript object rotated
	 */
	public final native Element rotate( double degree, double cx, double cy ) /*-{
																				return this.rotate(degree, cx, cy);
																				}-*/;

	/**
	 * Moves the element around the canvas by the given distances.
	 * 
	 * @param dx
	 *            number of pixels of translation by X-axis
	 * @param dy
	 *            number of pixels of translation by Y-axis
	 * 
	 * @return the javascript object translated
	 */
	public final native Element translate( double dx, double dy ) /*-{
																	return this.translate(dx, dy);
																	}-*/;

	/**
	 * Resizes the element by the given multipliers.
	 * 
	 * @param Xtimes
	 *            factor to scale horizontally
	 * @param Ytimes
	 *            factor to scale vertically
	 * 
	 * @return the javascript object scaled
	 */
	public final native Element scale( double Xtimes, double Ytimes ) /*-{
																		return this.scale(Xtimes, Ytimes);
																		}-*/;

	/**
	 * Resizes the element by the given multipliers.
	 * 
	 * @param Xtimes
	 *            factor to scale horizontally
	 * @param Ytimes
	 *            factor to scale vertically
	 * 
	 * @return the javascript object scaled
	 */
	public final native Element scale( double Xtimes, double Ytimes, double centerX ) /*-{
																						return this.scale(Xtimes, Ytimes, centerX);
																						}-*/;

	/**
	 * Resizes the element by the given multipliers.
	 * 
	 * @param Xtimes
	 *            factor to scale horizontally
	 * @param Ytimes
	 *            factor to scale vertically
	 * 
	 * @return the javascript object scaled
	 */
	public final native Element scale( double Xtimes, double Ytimes, double centerX, double centerY ) /*-{
																										return this.scale(Xtimes, Ytimes, centerX, centerY);
																										}-*/;

	/**
	 * Linearly changes an attribute from its current value to its specified value in the given amount of milliseconds.
	 * 
	 * @param newAttrs
	 *            string Attributes of the object after animation (not all attributes can be animated)
	 * @attributes_to_animate: 1. cx number 2. cy number 3. fill colour 4. fill-opacity number 5. font-size number 6. height number 7. opacity number 8. path
	 *                         pathString 9. r number 10. rotation number 11. rx number 12. ry number 13. scale CSV 14. stroke colour 15. stroke-opacity number
	 *                         16. stroke-width number 17. translation CSV 18. width number 19. x number 20. y number
	 * @param duration
	 *            The duration of the animation, given in milliseconds
	 * 
	 */
	public final native Element animate( JavaScriptObject newAttrs, int ms ) /*-{
																				return this.animate(newAttrs, ms);
																				}-*/;

	/**
	 * Linearly changes an attribute from its current value to its specified value in the given amount of milliseconds (with callback)
	 * 
	 * @param newAttrs
	 *            string Attributes of the object after animation (not all attributes can be animated)
	 * @attributes_to_animate: see animate(String, int) function for complete listing
	 * @param ms
	 *            int the duration of the animation, given in milliseconds
	 * @param easing
	 *            [>, <, <>, backIn, backOut, bounce, elastic]
	 * 
	 */
	public final native Element animate( JavaScriptObject newAttrs, int ms, String easing ) /*-{
																							return this.animate(newAttrs, ms, easing);
																							}-*/;

	public final native Element animate( JavaScriptObject newAttrs, int ms, String easing, Callback callback ) /*-{
																												var me = this;
																												return this.animate(newAttrs, ms, easing, function() {
																												callback.@com.hexa.client.raphael.Callback::onCallback(Lcom/hexa/client/raphael/Element;)( me );
																												} );
																												}-*/;

	/**
	 * Sets the attributes of elements directly.
	 * 
	 * @param attributeName
	 *            string of the attribute name
	 * @attribute_names: 1. cx number 2. cy number 3. fill colour 4. fill-opacity number 5. font string 6. font-family string 7. font-size number 8. font-weight
	 *                   string 9. gradient object|string 10. height number 11. opacity number 12. path pathString 13. r number 14. rotation number 15. rx
	 *                   number 16. ry number 17. scale CSV 18. src string (URL) 19. stroke colour 20. stroke-dasharray string 21. stroke-linecap string 22.
	 *                   stroke-linejoin string 23. stroke-miterlimit number 24. stroke-opacity number 25. stroke-width number 26. translation CSV 27. width
	 *                   number 28. x number 29. y number
	 * @param value
	 *            string of the new value
	 * 
	 * @return the javascript object having attributes modified
	 */
	public final native Element attr( String attributeName, String value ) /*-{
																			return this.attr(attributeName, value);
																			}-*/;

	public final native Element attr( String attributeName, double value ) /*-{
																			return this.attr(attributeName, value);
																			}-*/;

	/**
	 * sets a values for given attribute names
	 * 
	 * @return the current value for the given attribute name
	 */
	public final native Element attr( JavaScriptObject params ) /*-{
																return this.attr(params);
																}-*/;

	/**
	 * gets the value for an attribute
	 * 
	 * @return the current value for the given attribute name
	 */
	public final native double attrAsDouble( String attributeName ) /*-{
																	return this.attr(attributeName);
																	}-*/;

	/**
	 * gets the value for an attribute
	 * 
	 * @return the current value for the given attribute name
	 */
	public final native String attrAsString( String attributeName ) /*-{
																	return this.attr(attributeName);
																	}-*/;

	/**
	 * gets an array of values for given attribute names
	 * 
	 * @return the current value for the given attribute name
	 */
	public final native JsArray<?> attr( JsArray<?> attributeNames ) /*-{
																		return this.attr(attributeNames);
																		}-*/;

	public final native BBox getBBox() /*-{
										return this.getBBox();
										}-*/;

	public final native Element toFront() /*-{
											return this.toFront();
											}-*/;

	public final native Element toBack() /*-{
											return this.toBack();
											}-*/;

	public final native Element insertBefore( Element obj ) /*-{
															return this.insertBefore(obj);
															}-*/;

	public final native Element insertAfter( Element obj ) /*-{
															return this.insertAfter(obj);
															}-*/;

	public final native void click( Callback callback ) /*-{
														var me = this;
														this.click( function( event ) {
														callback.@com.hexa.client.raphael.Callback::onCallback(Lcom/hexa/client/raphael/Element;)( me );
														} );
														}-*/;

	public final native void dblclick( Callback callback ) /*-{
															var me = this;
															this.dblclick( function( event ) {
															callback.@com.hexa.client.raphael.Callback::onCallback(Lcom/hexa/client/raphael/Element;)( me );
															} );
															}-*/;

	public final native void mousedown( Callback callback ) /*-{
															var me = this;
															this.mousedown( function( event ) {
															callback.@com.hexa.client.raphael.Callback::onCallback(Lcom/hexa/client/raphael/Element;)( me );
															} );
															}-*/;

	public final native void mousemove( Callback callback ) /*-{
															var me = this;
															this.mousemove( function( event ) {
															callback.@com.hexa.client.raphael.Callback::onCallback(Lcom/hexa/client/raphael/Element;)( me );
															} );
															}-*/;

	public final native void mouseout( Callback callback ) /*-{
															var me = this;
															this.mouseout( function( event ) {
															callback.@com.hexa.client.raphael.Callback::onCallback(Lcom/hexa/client/raphael/Element;)( me );
															} );
															}-*/;

	public final native void mouseover( Callback callback ) /*-{
															var me = this;
															this.mouseover( function( event ) {
															callback.@com.hexa.client.raphael.Callback::onCallback(Lcom/hexa/client/raphael/Element;)( me );
															} );
															}-*/;

	public final native void mouseup( Callback callback ) /*-{
															var me = this;
															this.mouseup( function( event ) {
															callback.@com.hexa.client.raphael.Callback::onCallback(Lcom/hexa/client/raphael/Element;)( me );
															} );
															}-*/;

	/*
	 * 
	 * element.hover(function (event) { this.attr({fill: "red"}); }, function (event) { this.attr({fill: "black"}); });
	 */
}