package com.hexa.client.ui.upload;

import com.hexa.client.tools.HexaTools;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class Uploadify extends Widget implements IUploader
{
	Callback callback;
	
	Element i;
	
	String script;
	String queueElementId;
	JavaScriptObject data;
	
	public Uploadify( String script, String queueElementId, JavaScriptObject data, Callback callback )
	{
		this.data = data;
		this.callback = callback;
		this.script = script;
		this.queueElementId = queueElementId;
		
		String id = DOM.createUniqueId();

		Element div = DOM.createDiv();
		
		i = DOM.createElement( "input" );
		i.setId( id );
		i.setAttribute( "name", id );
		i.setAttribute( "type", "file" );
		
		div.appendChild( i );
		
		setElement( div );
	}
	
	boolean fUploadified = false;
	@Override
	protected void onLoad()
	{
		if( ! fUploadified )
			uploadify();
		fUploadified = true;
	}
	
	private void uploadify()
	{
		JSONObject json = new JSONObject( data );
		uploadifyImpl( i, script, json.toString(), queueElementId );
	}
	
	public void onServerResponse( String response )
	{
		callback.onFileUploaded( response );
	}
	
	public boolean onUploadOpen()
	{
		if( data == null )
		{
			HexaTools.alert( "Error", "No uploadKey has been set, please retry !" );
			return false;
		}
		
		return true;
	}
	
	public void onError( JavaScriptObject event, String ID, JavaScriptObject fileObj, JavaScriptObject errorObj )
	{
		JSONObject jsonEvent = new JSONObject( event );
		JSONObject jsonFile = new JSONObject( fileObj );
		JSONObject jsonError = new JSONObject( errorObj );
		
		HexaTools.alert( "Error uploading", "Event<br>"+jsonEvent.toString()+"<br>ID:"+ID+"<br>File<br>"+jsonFile.toString()+"<br>Error<br>"+jsonError.toString() );
	}
	
	// script: '/karim/index.php'
	public native void uploadifyImpl( Element e, String script, String data, String queueElementId )
	/*-{
		var me = this;
		var onComplete = function( event, queueID, fileObj, response, data )
		{
			me.@com.hexa.client.ui.upload.Uploadify::onServerResponse(Ljava/lang/String;)( response );
			
			return true;
		};
		
		var onError = function( event, ID, fileObj, errorObj )
		{
			me.@com.hexa.client.ui.upload.Uploadify::onError(Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;)( event, ID, fileObj, errorObj );
			
			return true;
		};
		
		var onOpen = function( event, queueID, fileObj )
		{
			return me.@com.hexa.client.ui.upload.Uploadify::onUploadOpen()();
		};
		
		var options = {
			'uploader'      : 'uploadify/uploadify.swf',
			'script'        : script,
			'buttonImg'		: 'uploadify/up.png',
			'cancelImg'     : 'uploadify/cancel.png',
			//'folder'        : '/_uploads',
			'multi'         : false,
			'auto'			: true,
			
			'width'         : 13,
			'height'		: 13,
			
			'onComplete'	: onComplete,
			'onError'		: onError,
			
			'scriptData'	: { 'container':'uploadify', 'uploadData':data },
			//'method'		: 'GET'
		};
		
		if( queueElementId )
			options['queueID'] = queueElementId;
		
		$wnd.$(e).uploadify( options );
	}-*/;
}
