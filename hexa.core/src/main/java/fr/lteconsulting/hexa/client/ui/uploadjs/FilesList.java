package fr.lteconsulting.hexa.client.ui.uploadjs;

import com.google.gwt.core.client.JavaScriptObject;

public class FilesList extends JavaScriptObject
{
	protected FilesList()
	{
	}

	public final native int getCount()
	/*-{
		return this.length;
	}-*/;

	public final native File getFile( int i )
	/*-{
		return this[i];
	}-*/;
}