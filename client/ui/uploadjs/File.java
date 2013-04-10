package com.hexa.client.ui.uploadjs;

import com.google.gwt.core.client.JavaScriptObject;

public class File extends JavaScriptObject
{
	public interface Callback
	{
		void onDataReady( String data );
	}
	
	protected File() {}
	
	public final native String getFileName() /*-{ return this.name; }-*/;
	public final native int getFileSize() /*-{ return this.size; }-*/;
	public final native String getMimeType() /*-{ return this.type; }-*/;
	
	//public final native Object getAsBinary() /*-{ return this.getAsBinary && this.getAsBinary(); }-*/;
	public final native String getAsText() /*-{ return this.getAsText && this.getAsText(); }-*/;
	
	public final native void getAsBinary( Callback callback )
	/*-{
		var reader = new FileReader();
		reader.onload = function( event )
		{
			callback.@com.hexa.client.ui.uploadjs.File.Callback::onDataReady(Ljava/lang/String;)( event.target.result );
		};
		
		reader.readAsBinaryString( this );
	}-*/;
	
	public final native void getAsDataUrl( Callback callback )
	/*-{
		var reader = new FileReader();
		reader.onload = function( event )
		{
			callback.@com.hexa.client.ui.uploadjs.File.Callback::onDataReady(Ljava/lang/String;)( event.target.result );
		};
		
		reader.readAsDataURL( this );
	}-*/;
}
