package fr.lteconsulting.hexa.client.ui.uploadjs;

import java.util.HashMap;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class UploadTest implements EntryPoint
{
	FlexTable table = new FlexTable();

	@Override
	public void onModuleLoad()
	{
		final DecoratorPanel deco = new DecoratorPanel();
		Label text = new Label( "Drop files here..." );
		deco.setWidget( text );

		DropTarget target = DropTarget.create( deco, new DropTarget.Callback()
		{
			@Override
			public void onDropFiles( FilesList files )
			{
				deco.getElement().getStyle().clearBackgroundColor();
				sendFiles( files );
			}

			@Override
			public void onDragLeave()
			{
				deco.getElement().getStyle().clearBackgroundColor();
			}

			@Override
			public void onDragEnter()
			{
				deco.getElement().getStyle().setBackgroundColor( "grey" );
			}
		} );

		if( target != null )
		{
			RootPanel.get().add( target );
		}
		else
		{
			RootPanel.get().add( new Label( "Sorry, no drop enabled..." ) );
		}
		RootPanel.get().add( table );
	}

	private void sendFiles( FilesList files )
	{
		int nb = files.getCount();

		HashMap<String, String> prms = new HashMap<String, String>();
		prms.put( "image", "mon Igmaeh" );
		prms.put( "coolVariable", "coolValue" );

		FileUploader uploader = new FileUploader();

		for( int i = 0; i < nb; i++ )
		{
			final File file = files.getFile( i );

			final int row = table.getRowCount();

			final ProgressBar bar = new ProgressBar();

			table.setWidget( row, 0, bar );
			final String title = file.getFileName() + " (" + file.getMimeType() + ")";
			table.setText( row, 1, title );

			String mime = file.getMimeType();
			if( mime != null && (mime.equalsIgnoreCase( "image/png" ) || mime.equalsIgnoreCase( "image/jpeg" )) )
			{
				final Image img = new Image();
				file.getAsDataUrl( new File.Callback()
				{
					@Override
					public void onDataReady( String data )
					{
						img.setUrl( data );
					}
				} );

				int w = img.getWidth();
				int h = img.getHeight();
				if( w == 0 || h == 0 )
				{
					w = 30;
					h = 30;
				}
				else if( w > h )
				{
					h = (int) ((h * 30.0f) / w);
					w = 30;
				}
				else
				{
					w = (int) ((w * 30.0f) / h);
					h = 30;
				}
				img.setPixelSize( w, h );
				table.setWidget( row, 2, img );
			}

			uploader.uploadFile( "upload.php", prms, "uploadedFile", file, new FileUploader.Callback()
			{
				@Override
				public void onStart()
				{
					table.setText( row, 1, "Started " + title );
				}

				@Override
				public void onProgress( int percentage, float speed, String response )
				{
					bar.setValue( percentage );

					if( percentage < 0 )
						table.setText( row, 1, "Failure " + title );
					else
						table.setText( row, 1, "Uploaded " + title + " " + percentage + "% speed:" + speed );
				}
			} );
		}
	}
}
