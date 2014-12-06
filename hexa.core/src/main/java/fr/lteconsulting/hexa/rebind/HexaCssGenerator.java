package fr.lteconsulting.hexa.rebind;

import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;


/**
 * Generates a file reporting use of Css classes
 *
 */
public class HexaCssGenerator extends AbstractGenerator
{
	@Override
	protected String generate()
	{
		String generatedClassName = typeName.replaceAll( "\\.", "_" ) + "_HexaCssImpl";
		
		HexaCssArtifact artifact = new HexaCssArtifact();
		
		boolean isGenerated = generateClass( generatedClassName, artifact.getReferencesMapping() );
		if( isGenerated )
		{
			try
			{
				context.commitArtifact( logger, artifact );
			}
			catch( UnableToCompleteException e )
			{
				e.printStackTrace();
			}
		}
		
		return type.getPackage().getName() + "." + generatedClassName;
	}
	
	private boolean generateClass( String name, HashMap<String,String> classMapping )
	{
		String packageName = type.getPackage().getName();
		
		// get print writer that receives the source code
		PrintWriter printWriter = null;

		printWriter = context.tryCreate( logger, packageName, name );
		// print writer if null, source code has ALREADY been generated, return
		if( printWriter == null )
			return false;

		// init composer, set class properties, create source writer
		ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory( packageName, name );
		
		composer.addImplementedInterface( type.getQualifiedSourceName() );
		
		SourceWriter sourceWriter = composer.createSourceWriter( context, printWriter );

		// generate the List<String> getMethods(); method
		String prefix = cssClassPrefix();
		for( JMethod m : type.getMethods() )
		{
			String normalName = prefix + m.getName();
			String shrinkedName = classMapping.get( normalName );
			if( shrinkedName == null )
			{
				shrinkedName = "h" + generateClassSignature( normalName );
				classMapping.put( normalName, shrinkedName );
			}
			
			sourceWriter.println( "public String " + m.getName() + "() { return \"" + shrinkedName + "\"; }" );
		}

		// close generated class
		sourceWriter.outdent();
		sourceWriter.println( "}" );

		// commit generated class
		context.commit( logger, printWriter );
		
		return true;
	}
	
	private String generateClassSignature( String className )
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update( className.getBytes() ); 
	      	byte[] output = md.digest();
	      	return bytesToHex(output).substring( 0, 6 );
			
		}
		catch( NoSuchAlgorithmException e )
		{
			e.printStackTrace();
			return null;
		}
	}

	private static String bytesToHex(byte[] b)
	{
		char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer buf = new StringBuffer();
		for( int j = 0; j < b.length; j++ )
		{
			buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
			buf.append(hexDigit[b[j] & 0x0f]);
		}
		return buf.toString();
	}
	
	private String cssClassPrefix()
	{
		String res = type.getQualifiedSourceName().replaceAll( "\\.", "-" ).replaceAll( "\\$", "-" ) + "-";
		if( res.endsWith( "Css-" ) )
			return res.substring( 0, res.length() - 4 );
		return res;
	}
}
