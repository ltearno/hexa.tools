package com.hexa.client.ui.uploadjs;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.core.client.JavaScriptException;

public class FileUploader
{
	public interface Callback
	{
		void onProgress( int percentage, String response );
	}
	
	public void uploadFile( String url, Map<String, String> parameters, String name, File file, Callback callback )
	{
		tasks.add( new UploadTask( url, parameters, name, file, callback ) );
		
		processNextTask();
	}
	
	class UploadTask
	{
		UploadTask( String url, Map<String, String> parameters, String name, File file, Callback callback )
		{
			this.url = url;
			this.parameters = parameters;
			this.name = name;
			this.file = file;
			this.callback = callback;
		}
		
		String url;
		String name;
		File file;
		Map<String, String> parameters;
		Callback callback;
		
		void launch()
		{
			file.getAsBinary( new File.Callback() {
				public void onDataReady( String data ) {
					realUpload( data );
				}
			});
		}
		
		void realUpload( Object binaryData )
		{
			String boundary = "AJAX------"+ Math.random() + "" + new Date().getTime();
			
			final XMLHttpRequestEx req = XMLHttpRequestEx.create();
			req.open( "POST", url );
			req.setRequestHeader( "Content-Type", "multipart/form-data; boundary=" + boundary );
			
			String CRLF = "\r\n";
			
			String data = "--" + boundary + CRLF;
			
			data += "Content-Disposition: form-data; ";
			data += "name=\"" + name + "\"; ";
			data += "filename=\"" + file.getFileName() + "\"; " + CRLF;
			data += "Content-Type: " + file.getMimeType();
			data += CRLF + CRLF;
			data += binaryData + CRLF;
			
			if( parameters != null )
			{
				for( Entry<String, String> e : parameters.entrySet() )
				{
					data += "--" + boundary + CRLF;
					
					data += "Content-Disposition: form-data; ";
					data += "name=\"" + e.getKey() + "\"" + CRLF + CRLF;
					data += e.getValue() + CRLF;
				}
			}
			
			data += "--" + boundary + "--" + CRLF;
			
			try {
				req.sendAsBinary( data, new XMLHttpRequestEx.Callback() {
					public void onProgress(int percentage) {
						callback.onProgress( percentage, req.getResponseText() );
						
						if( percentage<0 || percentage==100 )
						{
							currentTask = null;
							processNextTask();
						}
					}
				} );
		    } catch (JavaScriptException e) {
		    	callback.onProgress( -1, null );
	
		    	currentTask = null;
		    	processNextTask();
		    }
		}
	}
	
	UploadTask currentTask = null;
	ArrayList<UploadTask> tasks = new ArrayList<UploadTask>();
	
	void processNextTask()
	{
		if( currentTask != null )
			return;
		if( tasks.isEmpty() )
			return;
		
		currentTask = tasks.remove( 0 );
		currentTask.launch();
	}
}
