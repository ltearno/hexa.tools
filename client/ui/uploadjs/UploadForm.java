package com.hexa.client.ui.uploadjs;

import java.util.Map;
import java.util.Map.Entry;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.ControlLabel;
import com.github.gwtbootstrap.client.ui.Controls;
import com.github.gwtbootstrap.client.ui.Fieldset;
import com.github.gwtbootstrap.client.ui.Form.SubmitCompleteEvent;
import com.github.gwtbootstrap.client.ui.Form.SubmitCompleteHandler;
import com.github.gwtbootstrap.client.ui.ProgressBar;
import com.github.gwtbootstrap.client.ui.ProgressBar.Style;
import com.github.gwtbootstrap.client.ui.Well;
import com.github.gwtbootstrap.client.ui.WellForm;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.hexa.client.interfaces.IAsyncCallback;
import com.hexa.client.tools.HexaTools;

public class UploadForm extends Composite
{
	FileUpload uploader = new FileUpload();

	Well dropZone = new Well( "Vous pouvez <b>glissez-lâchez des fichiers dans cette zone</b> pour les envoyer (ne fonctionne pas avec Internet Explorer). Ou bien vous pouvez <b>utiliser le formulaire ci-dessous</b> pour sélectionner l'image à envoyer." );
	FlexTable table = new FlexTable();

	WellForm manualForm;

	Well tablePanel;

	IAsyncCallback<String> callback;

	boolean fMultiple;
	String url;
	String fieldName;
	Map<String, String> parameters;

	public UploadForm( String url, String fieldName, Map<String, String> parameters, boolean fMultiple, IAsyncCallback<String> callback )
	{
		this.url = url;
		this.fieldName = fieldName;
		this.parameters = parameters;
		this.callback = callback;
		this.fMultiple = fMultiple;

		DropTarget target = DropTarget.create( dropZone, new DropTarget.Callback()
		{
			@Override
			public void onDropFiles( FilesList files )
			{
				dropZone.getElement().getStyle().setBackgroundColor( "#efefef" );

				if( files != null )
					sendFiles( files );
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
		panel.add( target );

		manualForm = createManualUploadPart();
		panel.add( manualForm );

		tablePanel = new Well();
		tablePanel.add( table );
		panel.add( tablePanel );
		tablePanel.setVisible( false );

		initWidget( panel );
	}

	private WellForm createManualUploadPart()
	{
		final WellForm form = new WellForm();
		// form.setType( FormType.HORIZONTAL );
		form.setMethod( "POST" );
		form.setAction( url );
		form.setEncoding( FormPanel.ENCODING_MULTIPART );

		Fieldset fs = new Fieldset();
		form.add( fs );

		ControlGroup g = new ControlGroup();
		fs.add( g );

		ControlLabel cl = new ControlLabel( "Image à envoyer" );
		fs.add( cl );

		Controls cs = new Controls();
		fs.add( cs );

		com.github.gwtbootstrap.client.ui.FileUpload button = new com.github.gwtbootstrap.client.ui.FileUpload();
		button.setName( fieldName );
		fs.add( button );

		// SubmitButton submit = new SubmitButton( "Envoyer" );
		Button submit = new Button( "Envoyer" );
		submit.setType( ButtonType.SUCCESS );
		submit.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				form.submit();
			}
		} );

		cs = new Controls();
		fs.add( cs );
		cs.add( submit );

		for( Entry<String, String> param : parameters.entrySet() )
		{
			Hidden hidden = new Hidden( param.getKey(), param.getValue() );
			fs.add( hidden );
		}

		form.addSubmitCompleteHandler( new SubmitCompleteHandler()
		{

			@Override
			public void onSubmitComplete( SubmitCompleteEvent event )
			{
				callback.onSuccess( event.getResults() );
			}
		} );

		return form;
	}

	void sendFiles( FilesList files )
	{
		int nb = files.getCount();
		if( nb == 0 )
			return;

		if( nb > 1 && !fMultiple )
		{
			HexaTools.alert( "Vous ne pouvez envoyer qu'un seul fichier à la fois" );
			return;
		}

		tablePanel.setVisible( true );
		manualForm.setVisible( false );

		FileUploader uploader = new FileUploader();

		for( int i = 0; i < nb; i++ )
		{
			final File file = files.getFile( i );

			final int row = table.getRowCount();

			final ProgressBar bar = new ProgressBar();
			bar.setType( Style.ANIMATED );
			bar.getElement().getStyle().setWidth( 150, Unit.PX );

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

			uploader.uploadFile( url, parameters, fieldName, file, new FileUploader.Callback()
			{
				@Override
				public void onProgress( int percentage, float speed, String response )
				{
					bar.setPercent( percentage );

					if( percentage < 0 )
					{
						table.getCellFormatter().getElement( row, 1 ).getStyle().setColor( "red" );
						table.setText( row, 1, "Failure " + title );
					}
					else if( percentage == 100 )
					{
						table.setText( row, 1, "Uploaded " + title );

						callback.onSuccess( response );
					}
					else
					{
						table.setText( row, 1, "Uploading " + title + " " + percentage + "% speed:" + speed );
					}
				}

				@Override
				public void onStart()
				{
					table.setText( row, 1, "Upload started " + title );
				}
			} );
		}
	}
}
