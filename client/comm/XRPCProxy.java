package com.hexa.client.comm;

import com.google.gwt.core.client.JavaScriptObject;
import com.hexa.client.interfaces.IAsyncCallback;

public interface XRPCProxy
{
	void signalResultParseError( String parsedTxt, String trace );

	void signalRequestError( String trace, Throwable exception );

	void signalServerCommMessage( int msgLevel, String msg );

	void hangOut( String title, String description, String name, String type, JavaScriptObject currentData, IAsyncCallback<JavaScriptObject> callback );
}
