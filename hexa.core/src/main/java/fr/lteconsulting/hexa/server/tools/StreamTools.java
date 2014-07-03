package fr.lteconsulting.hexa.server.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamTools
{
	public static boolean copyStream( InputStream input, OutputStream output )
	{
		try
		{
			int size = 4096;
			byte[] buffer = new byte[size];

			int read = 0;
			do
			{
				read = input.read( buffer );
				if( read > 0 )
					output.write( buffer, 0, read );
			}
			while( read == size );

			return true;
		}
		catch( IOException e )
		{
			return false;
		}
	}
}
