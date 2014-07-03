package fr.lteconsulting.hexa.client.comm;

import com.google.gwt.core.client.JavaScriptObject;

public interface IDataProxyFastFactory
{
	<T> T getData( JavaScriptObject obj );
}
