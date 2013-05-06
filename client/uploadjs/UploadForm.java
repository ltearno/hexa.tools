package com.hexa.client.uploadjs;

import com.hexa.client.tools.Action1;
import com.hexa.client.uploadjs.UploadManager.Cookie;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

public class UploadForm extends Composite
{
	UploadManager manager;

	HTML dropZone = new HTML( "Ou glissez-lâchez des fichiers pour les envoyer (ne fonctionne pas avec Internet Explorer)" );
	FlexTable table = new FlexTable();

	UploadWidget uploadWidget = new UploadWidget();

	Action1<String> callback;

	public UploadForm( Action1<String> callback )
	{
		this.callback = callback;

		manager = new UploadManager( managerCallback );

		dropZone.getElement().getStyle().setFontWeight( FontWeight.BOLD );
		dropZone.getElement().getStyle().setBorderColor( "black" );
		dropZone.getElement().getStyle().setBorderStyle( BorderStyle.SOLID );
		dropZone.getElement().getStyle().setBorderWidth( 1, Unit.PX );
		dropZone.getElement().getStyle().setPadding( 20, Unit.PX );
		dropZone.getElement().getStyle().setBackgroundColor( "#efefef" );

		DropTarget.create( dropZone, new DropTarget.Callback()
		{
			@Override
			public void onDropFiles( FilesList files )
			{
				dropZone.getElement().getStyle().setBackgroundColor( "#efefef" );

				manager.sendFiles( files );
			}

			@Override
			public void onDragLeave()
			{
				dropZone.getElement().getStyle().setBackgroundColor( "#efefef" );
			}

			@Override
			public void onDragEnter()
			{
				dropZone.getElement().getStyle().setBackgroundColor( "grey" );
			}
		} );

		VerticalPanel panel = new VerticalPanel();
		panel.add( dropZone/* target */);
		panel.add( uploadWidget );

		initWidget( panel );
	}

	class UploadWidget extends Composite
	{
		ProgressBar bar;
		Image img;

		public UploadWidget()
		{
			bar = new ProgressBar();
			img = new Image();
			img.setPixelSize( 30, 30 );

			table = new FlexTable();
			table.setWidget( 0, 1, img );
			table.setWidget( 0, 2, bar );

			table.setText( 1, 0, "Prêt à envoyer des fichiers" );
			table.getFlexCellFormatter().setColSpan( 1, 0, 3 );

			initWidget( table );
		}

		public void setCount( int countUploads )
		{
			if( countUploads == 0 )
				table.setText( 1, 0, "Tous les fichiers ont été envoyés" );
			else
				table.setText( 1, 0, countUploads + " fichiers à envoyer..." );
		}

		public void setDownloadImageDataUrl( String dataUrl )
		{
			img.setUrl( dataUrl );
			bar.setValue( 0 );
		}

		public void setUploading( Cookie cookie, int percentage, float speed, String fileName )
		{
			bar.setValue( percentage );
			if( percentage >= 100 )
				table.setText( 0, 0, "Uploaded " + cookie.getFile().getFileName()  );
			else
				table.setText( 0, 0, "Uploading " + cookie.getFile().getFileName() + ", speed: " + NumberFormat.getFormat( "0.00" ).format( speed ) + " kbps" );
		}

		public void addUploadError( Cookie cookie )
		{
			int row = table.getRowCount();

			table.setText( row, 0, "Error uploading file " + cookie.getFile().getFileName() );
			table.getFlexCellFormatter().setColSpan( row, 0, 3 );
		}
	}

	UploadManager.Callback managerCallback = new UploadManager.Callback()
	{
		int countUploads;

		@Override
		public void onUploadInitialized( Cookie cookie )
		{
			countUploads++;

			uploadWidget.setCount( countUploads );
		}

		@Override
		public void onImageParsingBegin( Cookie cookie )
		{
		}

		@Override
		public void onImageParsingFinished( Cookie cookie, String data )
		{
		}

		@Override
		public void onUploadBegin( Cookie cookie )
		{
			uploadWidget.setDownloadImageDataUrl( cookie.getDataUrl() );
		}

		@Override
		public void onUploadProgress( Cookie cookie, int percentage, float speed )
		{
			uploadWidget.setUploading( cookie, percentage, speed, cookie.getFile().getFileName() );
		}

		@Override
		public void onUploadFinished( Cookie cookie, String responseText, boolean error )
		{
			if( !error && responseText != null )
				callback.exec( responseText );

			countUploads--;

			uploadWidget.setCount( countUploads );
		}
	};
}
