package fr.lteconsulting.hexa.client.tools;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;
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

	protected interface JQueryBundle extends ClientBundle
	{
		@Source( "jqueryjs/jquery-1.4.2.min.js" )
		TextResource JQueryJS();

		@Source( "jqueryjs/jquery-ui-1.8.2.custom.min.js" )
		TextResource JQueryUIJS();
	}

	private static JQueryBundle bundle = null;

	public static void ensureScriptsLoaded()
	{
		if( bundle != null )
			return;

		bundle = GWT.create( JQueryBundle.class );

		Document doc = Document.get();
		ScriptElement sqljs = doc.createScriptElement();
		sqljs.setAttribute( "type", "text/javascript" );
		sqljs.setInnerText( bundle.JQueryJS().getText() );
		doc.getDocumentElement().getFirstChildElement().appendChild( sqljs );

		sqljs = doc.createScriptElement();
		sqljs.setAttribute( "type", "text/javascript" );
		sqljs.setInnerText( bundle.JQueryUIJS().getText() );
		doc.getDocumentElement().getFirstChildElement().appendChild( sqljs );
	}

	private static JQuery INSTANCE = null;

	public static JQuery get()
	{
		if( INSTANCE != null )
			return INSTANCE;

		ensureScriptsLoaded();

		INSTANCE = new JQuery();
		return INSTANCE;
	}

	public native JsArray<Element> jqSelect( String selector ) /*-{
																return $wnd.$(selector).get();
																}-*/;

	public native JsArray<Element> jqSelect( String selector, Element element ) /*-{
																				return $wnd.$(selector, element).get();
																				}-*/;

	public native JsArray<Element> jqChildren( Element e, String selector ) /*-{
																			return $wnd.$(e).children(selector).get();
																			}-*/;

	public native JsArray<Element> jqChildren( JsArray<Element> e, String selector ) /*-{
																						return $wnd.$(e).children(selector).get();
																						}-*/;

	public native void jqClick( Element element, EventFunction callback ) /*-{
																			$wnd
																			.$(element)
																			.click(
																			function(eventObject) {
																			if (callback != null) {
																			var res = callback.@fr.lteconsulting.hexa.client.tools.JQuery.EventFunction::onEvent(Lcom/google/gwt/user/client/Event;)( eventObject );
																			if (!res)
																			eventObject.preventDefault();
																			}
																			});
																			}-*/;

	public native void jqClick( JsArray<Element> element, EventFunction callback ) /*-{
																					$wnd
																					.$(element)
																					.click(
																					function(eventObject) {
																					if (callback != null) {
																					var res = callback.@fr.lteconsulting.hexa.client.tools.JQuery.EventFunction::onEvent(Lcom/google/gwt/user/client/Event;)( eventObject );
																					if (!res)
																					eventObject.preventDefault();
																					}
																					});
																					}-*/;

	public native void jqMouseDown( JsArray<Element> element, EventFunction callback ) /*-{
																						$wnd
																						.$(element)
																						.mousedown(
																						function(eventObject) {
																						if (callback != null) {
																						var res = callback.@fr.lteconsulting.hexa.client.tools.JQuery.EventFunction::onEvent(Lcom/google/gwt/user/client/Event;)( eventObject );
																						if (!res)
																						eventObject.preventDefault();
																						}
																						});
																						}-*/;

	public native void jqMouseUp( JsArray<Element> element, EventFunction callback ) /*-{
																						$wnd
																						.$(element)
																						.mouseup(
																						function(eventObject) {
																						if (callback != null) {
																						var res = callback.@fr.lteconsulting.hexa.client.tools.JQuery.EventFunction::onEvent(Lcom/google/gwt/user/client/Event;)( eventObject );
																						if (!res)
																						eventObject.preventDefault();
																						}
																						});
																						}-*/;

	public native void jqMouseMove( JsArray<Element> element, EventFunction callback ) /*-{
																						$wnd
																						.$(element)
																						.mousemove(
																						function(eventObject) {
																						if (callback != null) {
																						var res = callback.@fr.lteconsulting.hexa.client.tools.JQuery.EventFunction::onEvent(Lcom/google/gwt/user/client/Event;)( eventObject );
																						if (!res)
																						eventObject.preventDefault();
																						}
																						});
																						}-*/;

	public native void jqAppend( JsArray<Element> elements, String html ) /*-{
																			//for( e in elements )
																			$wnd.$(elements).append(html);
																			}-*/;

	public native void jqAppend( Element element, String html ) /*-{
																$wnd.$(element).append(html);
																}-*/;

	public native void jqAttr( JsArray<Element> elements, String name, String value ) /*-{
																						$wnd.$(elements).attr(name, value);
																						}-*/;

	public void jqEffect( String effect, int ms, Element e, Callback callback )
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

	public native void jqEffectImpl( String effect, int ms, Element e, Callback callback ) /*-{
																							$wnd
																							.$(e)
																							.effect(
																							effect,
																							null,
																							ms,
																							function() {
																							if (callback != null)
																							callback.@fr.lteconsulting.hexa.client.tools.JQuery.Callback::onFinished()();
																							});
																							}-*/;

	public native void jqFadeIn( Element e, int ms, Callback callback ) /*-{
																		$wnd
																		.$(e)
																		.fadeIn(
																		ms,
																		function() {
																		if (callback != null)
																		callback.@fr.lteconsulting.hexa.client.tools.JQuery.Callback::onFinished()();
																		});
																		}-*/;

	public native void jqFadeOut( Element e, int ms, Callback callback ) /*-{
																			$wnd
																			.$(e)
																			.fadeOut(
																			ms,
																			function() {
																			if (callback != null)
																			callback.@fr.lteconsulting.hexa.client.tools.JQuery.Callback::onFinished()();
																			});
																			}-*/;

	public native void jqHide( String effect, Element e, Callback callback ) /*-{
																				$wnd
																				.$(e)
																				.hide(
																				effect,
																				null,
																				1000,
																				function() {
																				if (callback != null)
																				callback.@fr.lteconsulting.hexa.client.tools.JQuery.Callback::onFinished()();
																				});
																				}-*/;

	public native void jqHide( Element e )
	/*-{
		$wnd.$( e ).hide();
	}-*/;

	public native void jqShow( String effect, Element e ) /*-{
															$wnd.$(e).show(effect, null, 1000);
															}-*/;

	public native void jqShow( Element e )
	/*-{
		$wnd.$( e ).show();
	}-*/;

	public native void jqDatepicker( Element e ) /*-{
													$wnd.$(e).datepicker();
													}-*/;

	// public native void jqHtml( Element e, String html ) /*-{
	// $wnd.$(e).html(html);
	// }-*/;

	public native void jqHtml( JsArray<Element> e, String html ) /*-{
																	$wnd.$(e).html(html);
																	}-*/;

	public native void jqText( JsArray<Element> e, String text ) /*-{
																	$wnd.$(e).text(text);
																	}-*/;

	public native void jqCss( JsArray<Element> e, String property, String value ) /*-{
																					$wnd.$(e).css(property, value);
																					}-*/;

	public native void jqHeight( Element e, String height ) /*-{
															$wnd.$(e).height(height);
															}-*/;

	public native boolean jqHasClass( Element e, String className ) /*-{
																	return $wnd.$(e).hasClass(className);
																	}-*/;

	public native void addClass( Element e, String className ) /*-{
																$wnd.$(e).addClass(className);
																}-*/;

	public native void addClass( JsArray<Element> e, String className ) /*-{
																		$wnd.$(e).addClass(className);
																		}-*/;

	public native void removeClass( Element e, String className ) /*-{
																	$wnd.$(e).removeClass(className);
																	}-*/;

	public native void removeClass( JsArray<Element> e, String className ) /*-{
																			$wnd.$(e).removeClass(className);
																			}-*/;

	public native void toggleClass( Element e, String className ) /*-{
																	$wnd.$(e).toggleClass(className);
																	}-*/;

	public native void toggleClass( JsArray<Element> e, String className ) /*-{
																			$wnd.$(e).toggleClass(className);
																			}-*/;
}
