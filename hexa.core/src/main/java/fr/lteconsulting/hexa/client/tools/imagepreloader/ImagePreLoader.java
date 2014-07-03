package fr.lteconsulting.hexa.client.tools.imagepreloader;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public class ImagePreLoader
{
	interface Resources extends ClientBundle
	{
		@Source( "preloadImageScript.js" )
		TextResource preloadImageScript();
	}

	private static Resources resources = GWT.create( Resources.class );

	private static int id = 1;

	private static ImagePreLoader singleton;

	public static ImagePreLoader getSingleton()
	{
		if( singleton == null )
			singleton = new ImagePreLoader();

		return singleton;
	}

	public interface Callback
	{
		void onLoaded();

		void onError();
	}

	public ImagePreLoader()
	{
	}

	public void preload( String url, Callback callback )
	{
		if( !isScriptInstalled() )
		{
			ScriptElement script = Document.get().createScriptElement();
			script.setText( resources.preloadImageScript().getText() );

			Document.get().getBody().appendChild( script );
		}

		preloadImpl( id++, url, callback );
	}

	private native boolean isScriptInstalled() /*-{
												return $wnd.preloadImageScript != null;
												}-*/;

	private native void preloadImpl( int id, String url, Callback callback )
	/*-{
		$wnd.preloadImageScript(url, function() {
			callback.@fr.lteconsulting.hexa.client.tools.imagepreloader.ImagePreLoader.Callback::onLoaded()();
		}, function() {
			callback.@fr.lteconsulting.hexa.client.tools.imagepreloader.ImagePreLoader.Callback::onError()();
		});
	}-*/;
}
