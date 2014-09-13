package fr.lteconsulting.hexa.rebind;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.linker.ArtifactSet;
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
		
		boolean isGenerated = generateClass( generatedClassName );
		
		if( isGenerated )
		{
			HexaCssArtifact artifact = new HexaCssArtifact();
			generateCssReferences( artifact );
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
	
	private boolean generateClass( String name )
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
			sourceWriter.println( "public String " + m.getName() + "() { return \"" + prefix + m.getName() + "\"; }" );
		}

		// close generated class
		sourceWriter.outdent();
		sourceWriter.println( "}" );

		// commit generated class
		context.commit( logger, printWriter );
		
		return true;
	}
	
	private String cssClassPrefix()
	{
		String res = type.getQualifiedSourceName().replaceAll( "\\.", "-" ).replaceAll( "\\$", "-" ) + "-";
		if( res.endsWith( "Css-" ) )
			return res.substring( 0, res.length() - 4 );
		return res;
	}

	private void generateCssReferences( HexaCssArtifact artifact )
	{
		String prefix = cssClassPrefix();
		
		try
		{
			String fileName = typeName;
			if( fileName.endsWith( ".Css"  ) )
				fileName = fileName.substring( 0, fileName.length() - 4 );
			
			StringBuilder sb = new StringBuilder();
			String resource = fileName.replaceAll( "\\.", "/" ) + ".less";
			InputStream r = context.getResourcesOracle().getResourceAsStream( resource );
			if( r != null )
			{
				InputStreamReader isr = new InputStreamReader( r );
	        	BufferedReader br = new BufferedReader( isr );
	        	String line;
	        	try
				{
	        		while( (line = br.readLine()) != null )
		        	{
						sb.append( line );
						sb.append( "\n" );
					}
	        	}
	        	catch( Exception e )
				{
					e.printStackTrace();
				}
	        	artifact.addFileContent(fileName, sb.toString());
				
				OutputStream outStream = context.tryCreateResource( logger, "/hexa-css/" + fileName + ".css.ref" );
				if( outStream == null )
					return;
				
				PrintWriter outPw = new PrintWriter( outStream );
				
				JMethod[] ms = type.getInheritableMethods();
				for( int i=0; i<ms.length; i++ )
				{
					String cssReference = prefix + ms[i].getName();
					
					outPw.println( cssReference );
					artifact.add( cssReference );
				}
				
				outPw.flush();
				try
				{
					context.commitResource( logger, outStream );
				}
				catch( UnableToCompleteException e )
				{
					e.printStackTrace();
				}
			}
		}
		catch( UnableToCompleteException e1 )
		{
			e1.printStackTrace();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
}
