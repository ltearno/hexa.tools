package fr.lteconsulting.hexa.client.tools;

import com.google.gwt.core.client.GWT;

/*
 * MIGHT NOT BE USED ANYMORE, COMES FROM AN OLD FILE THAT I DID NOT DELETE
 */

public class URLEncoderEx
{
	private static int ROTATION = 7;

	private static int[] codeToEncode = null;
	private static int[] encodeToCode = null;

	static
	{
		codeToEncode = new int[123];
		encodeToCode = new int[123];

		for( int i = 0; i < 123; i++ )
		{
			int encode = _codeToEncode( i );
			codeToEncode[i] = encode;
			encodeToCode[encode] = i;
		}
	}

	public static String encode( String input )
	{
		StringBuilder b = new StringBuilder();

		int checkSum = 0;

		int inputLen = input.length();
		for( int i = 0; i < inputLen; i++ )
		{
			int n = charCodeAt( input, i );

			checkSum += n;

			n = codeToEncode( n );

			pushToList( n, b );
		}

		b.append( (char) encodeToCode( checkSum % 61 ) );

		return b.toString();
	}

	static class StringReader
	{
		int idx = 0;
		String string;

		public StringReader( String string )
		{
			this.string = string;
		}

		public boolean finished()
		{
			return idx >= string.length();
		}

		public int get()
		{
			return charCodeAt( string, idx++ );
		}
	}

	public static String decode( String encoded )
	{
		if( encoded == null || encoded.length() == 0 )
			return "";

		int checkSum = codeToEncode( charCodeAt( encoded.substring( encoded.length() - 1 ), 0 ) );
		int control = 0;

		encoded = encoded.substring( 0, encoded.length() - 1 );

		StringReader reader = new StringReader( encoded );

		StringBuilder b = new StringBuilder();

		while( !reader.finished() )
		{
			int n = encodeToCode( consumeList( reader ) );

			control += n;

			b.append( (char) n );
		}

		if( control % 61 != checkSum )
		{
			GWT.log( "Badly formatted encoded string ! " + encoded + " key is " + checkSum + " and processed : " + (control % 61) );
			return null;
		}

		return b.toString();
	}

	private static int consumeList( StringReader encodedList )
	{
		assert !encodedList.finished();

		int n = codeToEncode( rotateCode( encodedList.get(), -ROTATION ) );

		if( n < 61 )
		{
			return n;
		}
		else
		{
			// it is the marker, IT SHOULD
			assert n == 61;

			int reste = codeToEncode( rotateCode( encodedList.get(), -ROTATION ) );

			return reste + 61 * consumeList( encodedList );
		}
	}

	private static void pushToList( int n, StringBuilder b )
	{
		if( n < 61 )
		{
			// empiler n directement
			b.append( (char) rotateCode( encodeToCode( n ), ROTATION ) );
		}
		else
		{
			// empiler le markeur (61)
			b.append( (char) rotateCode( encodeToCode( 61 ), ROTATION ) );

			// empiler le reste
			int reste = n % 61;
			b.append( (char) rotateCode( encodeToCode( reste ), ROTATION ) );

			// recursion avec (n-reste)/61
			pushToList( (n - reste) / 61, b );
		}
	}

	private static int codeToEncode( int code )
	{
		if( code < 123 )
			return codeToEncode[code];
		return code;
	}

	private static int encodeToCode( int encode )
	{
		if( encode < 123 )
			return encodeToCode[encode];
		return encode;
	}

	private static int rotateCode( int charCode, int rotation )
	{
		if( charCode >= 48 && charCode <= 57 )
			return 48 + (((charCode - 48) + (rotation + 10)) % 10);

		if( charCode >= 65 && charCode <= 90 )
			return 65 + (((charCode - 65) + (rotation + (90 - 65 + 1))) % (90 - 65 + 1));

		if( charCode >= 97 && charCode <= 122 )
			return 97 + (((charCode - 97) + (rotation + (122 - 97 + 1))) % (122 - 97 + 1));

		return charCode;
	}

	private static int _codeToEncode( int code )
	{
		if( code >= 0 && code <= 47 )
			return 62 + code;
		if( code >= 48 && code <= 57 )
			return code - 48;
		if( code >= 58 && code <= 64 )
			return code - 58 + 62 + 48;
		if( code >= 65 && code <= 90 )
			return code - 65 + 10;
		if( code >= 91 && code <= 96 )
			return code - 91 + 117;// 62 + 48 + 26;
		if( code >= 97 && code <= 122 )
			return code - 97 + 36;

		return code;// - 123 + 162;//62 + 48 + 26 + 26;
	}

	private static native int charCodeAt( String s, int i )
	/*-{
		return s.charCodeAt( i );
	}-*/;

	private static native String fromCharCode( int code )
	/*-{
		return String.fromCharCode( code );
	}-*/;
}
