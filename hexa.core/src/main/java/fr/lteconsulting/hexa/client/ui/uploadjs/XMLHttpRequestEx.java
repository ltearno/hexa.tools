package fr.lteconsulting.hexa.client.ui.uploadjs;

import com.google.gwt.core.client.JavaScriptObject;

public class XMLHttpRequestEx extends JavaScriptObject
{
	public interface Callback
	{
		void onProgress( int percentage );
	}

	public interface ReadyStateChangeHandler
	{

		/**
		 * This is called whenever the state of the XMLHttpRequest changes. See
		 * {@link XMLHttpRequest#setOnReadyStateChange}.
		 * 
		 * @param xhr
		 *            the object whose state has changed.
		 */
		void onReadyStateChange( XMLHttpRequestEx xhr );
	}

	/*
	 * NOTE: Testing discovered that for some bizarre reason, on Mozilla, the
	 * JavaScript <code>XmlHttpRequest.onreadystatechange</code> handler
	 * function maybe still be called after it is deleted. The theory is that
	 * the callback is cached somewhere. Setting it to null or an empty function
	 * does seem to work properly, though.
	 * 
	 * On IE, there are two problems: Setting onreadystatechange to null (as
	 * opposed to an empty function) sometimes throws an exception. With
	 * particular (rare) versions of jscript.dll, setting onreadystatechange
	 * from within onreadystatechange causes a crash. Setting it from within a
	 * timeout fixes this bug (see issue 1610).
	 * 
	 * End result: *always* set onreadystatechange to an empty function (never
	 * to null). Never set onreadystatechange from within onreadystatechange
	 * (always in a setTimeout()).
	 */

	/**
	 * When constructed, the XMLHttpRequest object must be in the UNSENT state.
	 */
	public static final int UNSENT = 0;

	/**
	 * The OPENED state is the state of the object when the open() method has
	 * been successfully invoked. During this state request headers can be set
	 * using setRequestHeader() and the request can be made using send().
	 */
	public static final int OPENED = 1;

	/**
	 * The HEADERS_RECEIVED state is the state of the object when all response
	 * headers have been received.
	 */
	public static final int HEADERS_RECEIVED = 2;

	/**
	 * The LOADING state is the state of the object when the response entity
	 * body is being received.
	 */
	public static final int LOADING = 3;

	/**
	 * The DONE state is the state of the object when either the data transfer
	 * has been completed or something went wrong during the transfer (infinite
	 * redirects for instance).
	 */
	public static final int DONE = 4;

	/**
	 * Creates an XMLHttpRequest object.
	 * 
	 * @return the created object
	 */
	public static native XMLHttpRequestEx create() /*-{
													// Don't check window.XMLHttpRequest, because it can
													// cause cross-site problems on IE8 if window's URL
													// is javascript:'' .
													if ($wnd.XMLHttpRequest) {
													return new $wnd.XMLHttpRequest();
													} else {
													try {
													return new $wnd.ActiveXObject('MSXML2.XMLHTTP.3.0');
													} catch (e) {
													return new $wnd.ActiveXObject("Microsoft.XMLHTTP");
													}
													}
													}-*/;

	protected XMLHttpRequestEx()
	{
	}

	/**
	 * Aborts the current request.
	 * <p>
	 * See <a href="http://www.w3.org/TR/XMLHttpRequest/#abort"
	 * >http://www.w3.org/TR/XMLHttpRequest/#abort</a>.
	 */
	public final native void abort() /*-{
										this.abort();
										}-*/;

	/**
	 * Clears the {@link ReadyStateChangeHandler}.
	 * <p>
	 * See <a href="http://www.w3.org/TR/XMLHttpRequest/#onreadystatechange"
	 * >http://www.w3.org/TR/XMLHttpRequest/#onreadystatechange</a>.
	 * 
	 * @see #clearOnReadyStateChange()
	 */
	public final native void clearOnReadyStateChange() /*-{
														var self = this;
														$wnd.setTimeout(function() {
														// Using a function literal here leaks memory on ie6
														// Using the same function object kills HtmlUnit
														self.onreadystatechange = new Function();
														}, 0);
														}-*/;

	/**
	 * Gets all the HTTP response headers, as a single string.
	 * <p>
	 * See <a href="http://www.w3.org/TR/XMLHttpRequest/#getallresponseheaders"
	 * >http://www.w3.org/TR/XMLHttpRequest/#getallresponseheaders</a>.
	 * 
	 * @return the response headers.
	 */
	public final native String getAllResponseHeaders() /*-{
														return this.getAllResponseHeaders();
														}-*/;

	/**
	 * Get's the current ready-state.
	 * <p>
	 * See <a href="http://www.w3.org/TR/XMLHttpRequest/#readystate"
	 * >http://www.w3.org/TR/XMLHttpRequest/#readystate</a>.
	 * 
	 * @return the ready-state constant
	 */
	public final native int getReadyState() /*-{
											return this.readyState;
											}-*/;

	/**
	 * Gets an HTTP response header.
	 * <p>
	 * See <a href="http://www.w3.org/TR/XMLHttpRequest/#getresponseheader"
	 * >http://www.w3.org/TR/XMLHttpRequest/#getresponseheader</a>.
	 * 
	 * @param header
	 *            the response header to be retrieved
	 * @return the header value
	 */
	public final native String getResponseHeader( String header ) /*-{
																	return this.getResponseHeader(header);
																	}-*/;

	/**
	 * Gets the response text.
	 * <p>
	 * See <a href="http://www.w3.org/TR/XMLHttpRequest/#responsetext"
	 * >http://www.w3.org/TR/XMLHttpRequest/#responsetext</a>.
	 * 
	 * @return the response text
	 */
	public final native String getResponseText() /*-{
													return this.responseText;
													}-*/;

	/**
	 * Gets the status code.
	 * <p>
	 * See <a href="http://www.w3.org/TR/XMLHttpRequest/#status"
	 * >http://www.w3.org/TR/XMLHttpRequest/#status</a>.
	 * 
	 * @return the status code
	 */
	public final native int getStatus() /*-{
										return this.status;
										}-*/;

	/**
	 * Gets the status text.
	 * <p>
	 * See <a href="http://www.w3.org/TR/XMLHttpRequest/#statustext"
	 * >http://www.w3.org/TR/XMLHttpRequest/#statustext</a>.
	 * 
	 * @return the status text
	 */
	public final native String getStatusText() /*-{
												return this.statusText;
												}-*/;

	/**
	 * Opens an asynchronous connection.
	 * <p>
	 * See <a href="http://www.w3.org/TR/XMLHttpRequest/#open"
	 * >http://www.w3.org/TR/XMLHttpRequest/#open</a>.
	 * 
	 * @param httpMethod
	 *            the HTTP method to use
	 * @param url
	 *            the URL to be opened
	 */
	public final native void open( String httpMethod, String url ) /*-{
																	this.open(httpMethod, url, true);
																	}-*/;

	/**
	 * Opens an asynchronous connection.
	 * <p>
	 * See <a href="http://www.w3.org/TR/XMLHttpRequest/#open"
	 * >http://www.w3.org/TR/XMLHttpRequest/#open</a>.
	 * 
	 * @param httpMethod
	 *            the HTTP method to use
	 * @param url
	 *            the URL to be opened
	 * @param user
	 *            user to use in the URL
	 */
	public final native void open( String httpMethod, String url, String user ) /*-{
																				this.open(httpMethod, url, true, user);
																				}-*/;

	/**
	 * Opens an asynchronous connection.
	 * <p>
	 * See <a href="http://www.w3.org/TR/XMLHttpRequest/#open"
	 * >http://www.w3.org/TR/XMLHttpRequest/#open</a>.
	 * 
	 * @param httpMethod
	 *            the HTTP method to use
	 * @param url
	 *            the URL to be opened
	 * @param user
	 *            user to use in the URL
	 * @param password
	 *            password to use in the URL
	 */
	public final native void open( String httpMethod, String url, String user, String password ) /*-{
																									this.open(httpMethod, url, true, user, password);
																									}-*/;

	/**
	 * Initiates a request with no request data. This simply calls
	 * {@link #send(String)} with <code>null</code> as an argument, because the
	 * no-argument <code>send()</code> method is unavailable on Firefox.
	 */
	public final void send()
	{
		send( null );
	}

	/**
	 * Initiates a request with data. If there is no data, specify null.
	 * <p>
	 * See <a href="http://www.w3.org/TR/XMLHttpRequest/#send"
	 * >http://www.w3.org/TR/XMLHttpRequest/#send</a>.
	 * 
	 * @param requestData
	 *            the data to be sent with the request
	 */
	public final native void send( String requestData ) /*-{
														this.send(requestData);
														}-*/;

	public final void sendAsBinary( String requestData, final Callback callback )
	{
		setOnReadyStateChange( new ReadyStateChangeHandler()
		{
			public void onReadyStateChange( XMLHttpRequestEx xhr )
			{
				if( xhr.getReadyState() == XMLHttpRequestEx.DONE )
				{
					if( XMLHttpRequestEx.getBrowserSpecificFailure( xhr ) != null )
					{
						callback.onProgress( -1 );
					}
					else
					{
						xhr.clearOnReadyStateChange();

						callback.onProgress( 100 );
					}
				}
			}
		} );

		_sendAsBinary( this, requestData, callback );
	}

	private final native void _sendAsBinary( XMLHttpRequestEx xhr, String requestData, Callback callback )
	/*-{
		var upload = this.upload;
		upload.addEventListener( "progress", function( event ) {
			if (event.lengthComputable)
			{
				var percentage = Math.round( (event.loaded * 100) / event.total );
				if (percentage < 100)
					callback.@fr.lteconsulting.hexa.client.ui.uploadjs.XMLHttpRequestEx.Callback::onProgress(I)( percentage );
			}
		}, false );
		
		if( ! xhr.sendAsBinary )
		{
			function byteValue(x) {
		        return x.charCodeAt(0) & 0xff;
		    }
		    var ords = Array.prototype.map.call(requestData, byteValue);
		    var ui8a = new Uint8Array(ords);
		    this.send(ui8a.buffer);
		}
		else
			xhr.sendAsBinary( requestData );
	}-*/;

	/**
	 * Sets the {@link ReadyStateChangeHandler} to be notified when the object's
	 * ready-state changes.
	 * <p>
	 * See <a href="http://www.w3.org/TR/XMLHttpRequest/#onreadystatechange"
	 * >http://www.w3.org/TR/XMLHttpRequest/#onreadystatechange</a>.
	 * 
	 * <p>
	 * Note: Applications <em>must</em> call {@link #clearOnReadyStateChange()}
	 * when they no longer need this object, to ensure that it is cleaned up
	 * properly. Failure to do so will result in memory leaks on some browsers.
	 * </p>
	 * 
	 * @param handler
	 *            the handler to be called when the ready state changes
	 * @see #clearOnReadyStateChange()
	 */
	public final native void setOnReadyStateChange( ReadyStateChangeHandler handler ) /*-{
																						// The 'this' context is always supposed to point to the xhr object in the
																						// onreadystatechange handler, but we reference it via closure to be extra sure.
																						var _this = this;
																						this.onreadystatechange = $entry(function() {
																						handler.@fr.lteconsulting.hexa.client.ui.uploadjs.XMLHttpRequestEx.ReadyStateChangeHandler::onReadyStateChange(Lfr/lteconsulting/hexa/client/ui/uploadjs/XMLHttpRequestEx;)( _this );
																						});
																						}-*/;

	/**
	 * Sets a request header.
	 * <p>
	 * See <a href="http://www.w3.org/TR/XMLHttpRequest/#setrequestheader"
	 * >http://www.w3.org/TR/XMLHttpRequest/#setrequestheader</a>.
	 * 
	 * @param header
	 *            the header to be set
	 * @param value
	 *            the header's value
	 */
	public final native void setRequestHeader( String header, String value ) /*-{
																				this.setRequestHeader(header, value);
																				}-*/;

	private native static String getBrowserSpecificFailure( XMLHttpRequestEx xhr ) /*-{
																					try {
																					if (xhr.status === undefined) {
																					return "XmlHttpRequest.status == undefined, please see Safari bug " +
																					"http://bugs.webkit.org/show_bug.cgi?id=3810 for more details";
																					}
																					return null;
																					} catch (e) {
																					return "Unable to read XmlHttpRequest.status; likely causes are a " +
																					"networking error or bad cross-domain request. Please see " +
																					"https://bugzilla.mozilla.org/show_bug.cgi?id=238559 for more " +
																					"details";
																					}
																					}-*/;
}
