package fr.lteconsulting.hexa.client.ui.uploadjs;

import java.util.Date;

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import fr.lteconsulting.hexa.client.tools.HexaTools;

public class Uploader extends Composite
{
	DecoratorPanel deco = new DecoratorPanel();
	Label text = new Label( "Drop files here..." );

	public Uploader()
	{
		deco.setWidget( text );

		initWidget( deco );

		initDropZone( getElement() );
	}

	private void onDragEnter()
	{
		deco.getElement().getStyle().setBackgroundColor( "grey" );
	}

	private void onDragLeave()
	{
		deco.getElement().getStyle().clearBackgroundColor();
	}

	private native void initDropZone( Element dropzone )
	/*-{
		var me = this;

		if( ! dropzone.addEventListener )
			return;

		dropzone.addEventListener( "dragenter", function(event)
			{
				event.preventDefault();

				me.@fr.lteconsulting.hexa.client.ui.uploadjs.Uploader::onDragEnter()();
			}, true );

		dropzone.addEventListener( "dragover", function(event)
			{
				event.preventDefault();

				me.@fr.lteconsulting.hexa.client.ui.uploadjs.Uploader::onDragEnter()();
			}, true );

		dropzone.addEventListener( "dragleave", function(event)
			{
				event.preventDefault();

				me.@fr.lteconsulting.hexa.client.ui.uploadjs.Uploader::onDragLeave()();
			}, true );

		dropzone.addEventListener( "drop", function(event)
			{
			  event.preventDefault();

			  var allTheFiles = event.dataTransfer.files;
			  //$wnd.droppedFiles = allTheFiles;

			  me.@fr.lteconsulting.hexa.client.ui.uploadjs.Uploader::onDropFiles(Lfr/lteconsulting/hexa/client/ui/uploadjs/FilesList;)( allTheFiles );
			}, true);
	}-*/;

	void onDropFiles( FilesList files )
	{
		if( files == null )
		{
			HexaTools.alert( "null drop !" );
			return;
		}

		int count = files.getCount();

		for( int i = 0; i < count; i++ )
		{
			final ProgressBar bar = new ProgressBar();
			RootPanel.get().add( bar );

			bar.setValue( 0 );

			final File file = files.getFile( i );
			file.getAsBinary( new File.Callback()
			{
				@Override
				public void onDataReady( String fileData )
				{
					String boundary = "AJAX------" + Math.random() + "" + new Date().getTime();

					XMLHttpRequestEx req = XMLHttpRequestEx.create();
					req.open( "POST", "upload.php" );
					req.setRequestHeader( "Content-Type", "multipart/form-data; boundary=" + boundary );

					String CRLF = "\r\n";

					String data = "--" + boundary + CRLF;
					data += "Content-Disposition: form-data; ";
					data += "name=\"" + "uploadedFile" + "\"; ";
					data += "filename=\"" + file.getFileName() + "\"; " + CRLF;

					data += "Content-Type: " + file.getMimeType();
					data += CRLF + CRLF;
					data += fileData + CRLF;

					data += "--" + boundary + "--" + CRLF;

					try
					{
						req.sendAsBinary( data, new XMLHttpRequestEx.Callback()
						{
							@Override
							public void onProgress( int percentage )
							{
								if( percentage < 0 )
								{
									HexaTools.alert( "error" );
									return;
								}
								bar.setValue( percentage );
							}
						} );
					}
					catch( JavaScriptException e )
					{
					}
				}
			} );
		}
	}
}
