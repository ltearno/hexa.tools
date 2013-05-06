package com.hexa.client.uploadjs;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class DropTarget
{
	public interface Callback
	{
		void onDragEnter();

		void onDragLeave();

		void onDropFiles( FilesList files );
	}

	public static DropTarget create( Widget w, Callback callback )
	{
		if( !canDropZone( w.getElement() ) )
			return null;

		return new DropTarget( w, callback );
	}

	private DropTarget( Widget w, Callback callback )
	{
		initDropZone( w.getElement(), callback );
	}

	private static native boolean canDropZone( Element test )
	/*-{
		return !!test.addEventListener;
	}-*/;

	private native void initDropZone( Element dropzone, Callback callback )
	/*-{
		if (!dropzone.addEventListener)
			return;

		dropzone
				.addEventListener(
						"dragenter",
						function(event) {
							event.preventDefault();

							callback.@com.hexa.client.uploadjs.DropTarget.Callback::onDragEnter()();
						}, true);

		dropzone
				.addEventListener(
						"dragover",
						function(event) {
							event.preventDefault();

							callback.@com.hexa.client.uploadjs.DropTarget.Callback::onDragEnter()();
						}, true);

		dropzone
				.addEventListener(
						"dragleave",
						function(event) {
							event.preventDefault();

							callback.@com.hexa.client.uploadjs.DropTarget.Callback::onDragLeave()();
						}, true);

		dropzone
				.addEventListener(
						"drop",
						function(event) {
							event.preventDefault();

							var allTheFiles = event.dataTransfer.files;

							callback.@com.hexa.client.uploadjs.DropTarget.Callback::onDropFiles(Lcom/hexa/client/uploadjs/FilesList;)( allTheFiles );
						}, true);
	}-*/;
}
