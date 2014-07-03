package fr.lteconsulting.hexa.client.comm;

// Stores a request description together with the objects that need to get the answer
public class RequestCallInfo
{
	// request description
	public RequestDesc request;

	// client waiting on it
	XRPCRequest callback = null;
	Object cookie = null;

	// data, when received
	boolean fResultReceived = false;
	ResponseJSO retValue = null;
	int msgLevel = 0;
	String msg = null;
	GenericJSO hangout = null;

	public RequestCallInfo( RequestDesc request, XRPCRequest callback, Object cookie )
	{
		this.request = request;
		this.callback = callback;
		this.cookie = cookie;
	}

	public void setResult( int msgLevel, String msg, GenericJSO hangOut, ResponseJSO retValue )
	{
		fResultReceived = true;

		this.msgLevel = msgLevel;
		this.msg = msg;
		this.hangout = hangOut;
		this.retValue = retValue;
	}

	public void giveResultToCallbacks()
	{
		callback.onResponse( cookie, retValue, msgLevel, msg );
	}
}
