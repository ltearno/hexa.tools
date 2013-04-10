package com.hexa.client.tools;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;

public class JQuery
{
	public static abstract class Callback
	{
		public abstract void onFinished();
	}

	public interface EventFunction
	{
		boolean onEvent( Event event );
	}

	public static native JsArray<Element> jqSelect( String selector ) /*-{
																		return $wnd.$( selector ).get();
																		}-*/;

	public static native JsArray<Element> jqSelect( String selector, Element element ) /*-{
																						return $wnd.$( selector, element ).get();
																						}-*/;

	public static native JsArray<Element> jqChildren( Element e, String selector ) /*-{
																					return $wnd.$( e ).children( selector ).get();
																					}-*/;

	public static native JsArray<Element> jqChildren( JsArray<Element> e, String selector ) /*-{
																							return $wnd.$( e ).children( selector ).get();
																							}-*/;

	public static native void jqClick( Element element, EventFunction callback ) /*-{
																					$wnd.$( element ).click( function( eventObject ) {
																					if( callback != null )
																					{
																					var res = callback.@com.hexa.client.tools.JQuery.EventFunction::onEvent(Lcom/google/gwt/user/client/Event;)( eventObject );
																					if( ! res )
																					eventObject.preventDefault();
																					}
																					} );
																					}-*/;

	public static native void jqClick( JsArray<Element> element, EventFunction callback ) /*-{
																							$wnd.$( element ).click( function( eventObject ) {
																							if( callback != null )
																							{
																							var res = callback.@com.hexa.client.tools.JQuery.EventFunction::onEvent(Lcom/google/gwt/user/client/Event;)( eventObject );
																							if( ! res )
																							eventObject.preventDefault();
																							}
																							} );
																							}-*/;

	public static native void jqMouseDown( JsArray<Element> element, EventFunction callback ) /*-{
																								$wnd.$( element ).mousedown( function( eventObject ) {
																								if( callback != null )
																								{
																								var res = callback.@com.hexa.client.tools.JQuery.EventFunction::onEvent(Lcom/google/gwt/user/client/Event;)( eventObject );
																								if( ! res )
																								eventObject.preventDefault();
																								}
																								} );
																								}-*/;

	public static native void jqMouseUp( JsArray<Element> element, EventFunction callback ) /*-{
																							$wnd.$( element ).mouseup( function( eventObject ) {
																							if( callback != null )
																							{
																							var res = callback.@com.hexa.client.tools.JQuery.EventFunction::onEvent(Lcom/google/gwt/user/client/Event;)( eventObject );
																							if( ! res )
																							eventObject.preventDefault();
																							}
																							} );
																							}-*/;

	public static native void jqMouseMove( JsArray<Element> element, EventFunction callback ) /*-{
																								$wnd.$( element ).mousemove( function( eventObject ) {
																								if( callback != null )
																								{
																								var res = callback.@com.hexa.client.tools.JQuery.EventFunction::onEvent(Lcom/google/gwt/user/client/Event;)( eventObject );
																								if( ! res )
																								eventObject.preventDefault();
																								}
																								} );
																								}-*/;

	public static native void jqAppend( JsArray<Element> elements, String html ) /*-{
																					//for( e in elements )
																					$wnd.$( elements ).append( html );
																					}-*/;

	public static native void jqAppend( Element element, String html ) /*-{
																		$wnd.$( element ).append( html );
																		}-*/;

	public static native void jqAttr( JsArray<Element> elements, String name, String value ) /*-{
																								$wnd.$( elements ).attr( name, value );
																								}-*/;

	public static void jqEffect( String effect, int ms, Element e, Callback callback )
	{
		// don't know why, highlighting from TreeTableBase::newItem throws an
		// exception
		// on Safari and Chrome when editing a Pricing Scheme
		// (PricingSchemeEditor::schemeTable)
		try
		{
			jqEffectImpl( effect, ms, e, callback );
		}
		catch( Exception exception )
		{
		}
	}

	public static native void jqEffectImpl( String effect, int ms, Element e, Callback callback ) /*-{
																									$wnd.$( e ).effect( effect, null, ms, function() {
																									if( callback != null )
																									callback.@com.hexa.client.tools.JQuery.Callback::onFinished()(); 
																									} );
																									}-*/;

	public static native void jqFadeIn( Element e, int ms, Callback callback ) /*-{
																				$wnd.$( e ).fadeIn( ms, function() {
																				if( callback != null )
																				callback.@com.hexa.client.tools.JQuery.Callback::onFinished()(); 
																				} );
																				}-*/;

	public static native void jqFadeOut( Element e, int ms, Callback callback ) /*-{
																				$wnd.$( e ).fadeOut( ms, function() {
																				if( callback != null )
																				callback.@com.hexa.client.tools.JQuery.Callback::onFinished()(); 
																				} );
																				}-*/;

	public static native void jqHide( String effect, Element e, Callback callback ) /*-{
																					$wnd.$( e ).hide( effect, null, 1000, function() {
																					if( callback != null )
																					callback.@com.hexa.client.tools.JQuery.Callback::onFinished()(); 
																					} );
																					}-*/;

	public static native void jqShow( String effect, Element e ) /*-{
																	$wnd.$( e ).show( effect, null, 1000 );
																	}-*/;

	public static native void jqDatepicker( Element e ) /*-{
														$wnd.$( e ).datepicker();
														}-*/;

	public static native void jqHtml( Element e, String html ) /*-{
																$wnd.$( e ).html( html );
																}-*/;

	public static native void jqHtml( JsArray<Element> e, String html ) /*-{
																		$wnd.$( e ).html( html );
																		}-*/;

	public static native void jqText( JsArray<Element> e, String text ) /*-{
																		$wnd.$( e ).text( text );
																		}-*/;

	public static native void jqCss( JsArray<Element> e, String property, String value ) /*-{
																							$wnd.$( e ).css( property, value );
																							}-*/;

	public static native void jqHeight( Element e, String height ) /*-{
																	$wnd.$( e ).height( height );
																	}-*/;

	public static native boolean jqHasClass( Element e, String className ) /*-{
																			return $wnd.$( e ).hasClass( className );
																			}-*/;

	public static native void addClass( Element e, String className ) /*-{
																		$wnd.$( e ).addClass( className );
																		}-*/;

	public static native void addClass( JsArray<Element> e, String className ) /*-{
																				$wnd.$( e ).addClass( className );
																				}-*/;

	public static native void removeClass( Element e, String className ) /*-{
																			$wnd.$( e ).removeClass( className );
																			}-*/;

	public static native void removeClass( JsArray<Element> e, String className ) /*-{
																					$wnd.$( e ).removeClass( className );
																					}-*/;

	public static native void toggleClass( Element e, String className ) /*-{
																			$wnd.$( e ).toggleClass( className );
																			}-*/;

	public static native void toggleClass( JsArray<Element> e, String className ) /*-{
																					$wnd.$( e ).toggleClass( className );
																					}-*/;
}
