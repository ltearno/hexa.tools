package com.hexa.client.ui.upload;

public interface IUploader
{
	public interface Callback
	{
		void onFileUploaded( String message );
	}
}
