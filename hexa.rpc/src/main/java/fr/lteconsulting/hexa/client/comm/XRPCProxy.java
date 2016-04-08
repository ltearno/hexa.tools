package fr.lteconsulting.hexa.client.comm;

import com.google.gwt.core.client.JavaScriptObject;
import fr.lteconsulting.hexa.client.interfaces.IAsyncCallback;

/**
 * Callback used when some non frequent events happen on the RPC layer.
 * 
 * <p>Those events can be:<ul>
 * <li>A server message which should be shown to the user,
 * <li>An hang out request. This happens when the server requires more information that was originally provided for a rpc call to be made. The user has to answer a question like a comment or so on and and the rpc call can continue.
 * </lu>
 */
public interface XRPCProxy
{
	void signalResultParseError( String parsedTxt, String trace );

	void signalRequestError( String trace, Throwable exception );

	void signalServerCommMessage( int msgLevel, String msg );

	void hangOut( String title, String description, String name, String type, JavaScriptObject currentData, IAsyncCallback<JavaScriptObject> callback );
}
