package fr.lteconsulting.hexa.client.ui.uploadjs;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DropTarget extends Composite
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
		initWidget( w );

		initDropZone( getElement(), callback );
	}

	private static native boolean canDropZone( Element test )
	/*-{
		return !! test.addEventListener;
	}-*/;

	private native void initDropZone( Element dropzone, Callback callback )
	/*-{
		if( ! dropzone.addEventListener )
			return;

		dropzone.addEventListener( "dragenter", function(event)
			{
				event.preventDefault();

				callback.@fr.lteconsulting.hexa.client.ui.uploadjs.DropTarget.Callback::onDragEnter()();
			}, true );

		dropzone.addEventListener( "dragover", function(event)
			{
				event.preventDefault();

				callback.@fr.lteconsulting.hexa.client.ui.uploadjs.DropTarget.Callback::onDragEnter()();
			}, true );

		dropzone.addEventListener( "dragleave", function(event)
			{
				event.preventDefault();

				callback.@fr.lteconsulting.hexa.client.ui.uploadjs.DropTarget.Callback::onDragLeave()();
			}, true );

		dropzone.addEventListener( "drop", function(event)
			{
			  event.preventDefault();

			  var allTheFiles = event.dataTransfer.files;
			  //$wnd.droppedFiles = allTheFiles;

			  callback.@fr.lteconsulting.hexa.client.ui.uploadjs.DropTarget.Callback::onDropFiles(Lfr/lteconsulting/hexa/client/ui/uploadjs/FilesList;)( allTheFiles );
			}, true);
	}-*/;
}
