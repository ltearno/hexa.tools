package fr.lteconsulting.hexa.client.ui.uploadjs;

import java.util.HashMap;

import fr.lteconsulting.hexa.client.tools.Action;
import fr.lteconsulting.hexa.client.ui.uploadjs.Tasker.AsynchroneTask;

public class UploadManager
{
	interface Cookie
	{
		File getFile();

		String getDataUrl();
	}

	public interface Callback
	{
		void onUploadInitialized( Cookie cookie );

		void onImageParsingBegin( Cookie cookie );

		void onImageParsingFinished( Cookie cookie, String data );

		void onUploadBegin( Cookie cookie );

		void onUploadProgress( Cookie cookie, int percentage, float speed );

		void onUploadFinished( Cookie cookie, String responseText, boolean error );
	}

	Callback eventsCallback;

	Tasker tasker = new Tasker();

	FileUploader uploader = new FileUploader();

	public UploadManager( Callback eventsCallback )
	{
		this.eventsCallback = eventsCallback;
	}

	void sendFiles( FilesList files )
	{
		int nb = files.getCount();
		for( int i = 0; i < nb; i++ )
		{
			FileUploadInfo info = new FileUploadInfo( files.getFile( i ) );

			eventsCallback.onUploadInitialized( info );

			// this task is the upload of a file
			tasker.enqueueTask( info );
		}
	}

	class FileUploadInfo implements Cookie, Tasker.AsynchroneTask
	{
		File file;
		String dataUrl;

		Tasker tasker = new Tasker();

		public FileUploadInfo( File file )
		{
			this.file = file;
		}

		@Override
		public void execute( final Action taskFinishedCallback )
		{
			tasker.enqueueTask( parseImage );
			tasker.enqueueTask( uploadImage, taskFinishedCallback );
		}

		AsynchroneTask parseImage = new AsynchroneTask()
		{
			@Override
			public void execute( final Action taskFinishedCallback )
			{
				eventsCallback.onImageParsingBegin( FileUploadInfo.this );

				file.getAsDataUrl( new File.Callback()
				{
					@Override
					public void onDataReady( String data )
					{
						dataUrl = data;

						eventsCallback.onImageParsingFinished( FileUploadInfo.this, data );

						taskFinishedCallback.exec();
					}
				} );
			}
		};

		AsynchroneTask uploadImage = new AsynchroneTask()
		{
			@Override
			public void execute( final Action taskFinishedCallback )
			{
				HashMap<String, String> prms = new HashMap<String, String>();
				prms.put( "uploadData", "{ \"type\" : \"upload_picture\", \"user_id\" : " + 0 + " }" );

				uploader.uploadFile( "upload", prms, "Filedata", file, new FileUploader.Callback()
				{
					@Override
					public void onStart()
					{
						eventsCallback.onUploadBegin( FileUploadInfo.this );
					}

					@Override
					public void onProgress( int percentage, float speed, String responseText )
					{
						if( percentage >= 0 )
							eventsCallback.onUploadProgress( FileUploadInfo.this, percentage, speed );

						if( percentage == 100 || percentage < 0 )
						{
							eventsCallback.onUploadFinished( FileUploadInfo.this, responseText, percentage < 0 );

							taskFinishedCallback.exec();
						}
					}
				} );
			}
		};

		@Override
		public File getFile()
		{
			return file;
		}

		@Override
		public String getDataUrl()
		{
			return dataUrl;
		}
	}
}
