package fr.lteconsulting.hexa.client.ui.uploadjs;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.typedarrays.shared.ArrayBuffer;

public class File extends JavaScriptObject
{
	public interface Callback
	{
		void onDataReady( String data );
	}

	public interface CallbackArrayBuffer
	{
		void onDataReady( ArrayBuffer data );
	}

	protected File()
	{
	}

	public final native String getFileName() /*-{ return this.name; }-*/; // ||
																			// this.fileName;
																			// }-*/;public
																			// final
																			// native
																			// int
																			// getFileSize()
																			// /*-{
																			// return
																			// this.size;
																			// }-*/;

	public final native String getMimeType() /*-{ return this.type; }-*/;

	// public final native Object getAsBinary() /*-{ return this.getAsBinary &&
	// this.getAsBinary(); }-*/;
	public final native String getAsText() /*-{ return this.getAsText && this.getAsText(); }-*/;

	public final native void getAsBinary( Callback callback )
	/*-{
		var reader = new FileReader();
		reader.onload = function( event )
		{
			callback.@fr.lteconsulting.hexa.client.ui.uploadjs.File.Callback::onDataReady(Ljava/lang/String;)( event.target.result );
		};

		reader.readAsBinaryString( this );
	}-*/;

	public final native void getAsBinaryArrayBuffer( CallbackArrayBuffer callback )
	/*-{
		var reader = new FileReader();
		reader.onload = function( event )
		{
			callback.@fr.lteconsulting.hexa.client.ui.uploadjs.File.CallbackArrayBuffer::onDataReady(Lcom/google/gwt/typedarrays/shared/ArrayBuffer;)( event.target.result );
		};

		reader.readAsArrayBuffer( this );
	}-*/;

	public final native void getAsDataUrl( Callback callback )
	/*-{
		var reader = new FileReader();
		reader.onload = function( event )
		{
			callback.@fr.lteconsulting.hexa.client.ui.uploadjs.File.Callback::onDataReady(Ljava/lang/String;)( event.target.result );
		};

		reader.readAsDataURL( this );
	}-*/;
}
