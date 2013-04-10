package com.hexa.client.ui.upload;

import com.hexa.client.ui.IFrame;
import com.hexa.client.ui.ImageButton;
import com.hexa.client.ui.MyDialogBox;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class UploadBasic extends Composite implements IUploader
{
	String script;
	JavaScriptObject data;
	Callback callback;

	public UploadBasic( String script, JavaScriptObject data, ImageResource buttonImg, Callback callback )
	{
		this.script = script;
		this.data = data;
		this.callback = callback;

		ImageButton img = new ImageButton( buttonImg, "Upload a new image" );
		img.addClickHandler( new ClickHandler()
		{
			public void onClick( ClickEvent event )
			{
				showUploadForm();
			}
		} );
		initWidget( img );
	}

	private void showUploadForm()
	{
		final MyDialogBox db = new MyDialogBox();

		db.setTitle( "Upload an image" );

		VerticalPanel panel = new VerticalPanel();
		db.setWidget( panel );

		JSONObject json = new JSONObject( data );
		IFrame frame = new IFrame( script + "?container=uploadify&uploadData=" + URL.encode( json.toString() ) );
		panel.add( frame );

		Button finished = new Button( "Finished" );
		panel.add( finished );
		finished.addClickHandler( new ClickHandler()
		{
			public void onClick( ClickEvent event )
			{
				db.hide();
				callback.onFileUploaded( null );
			}
		} );

		db.center();
		db.show();
	}
}
