package fr.lteconsulting.hexa.rebind;

import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.resources.client.CssResource.ClassName;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import fr.lteconsulting.hexa.client.css.annotation.HexaCssExtra;


/**
 * Generates a file reporting use of Css classes
 *
 */
public class HexaCssGenerator extends Generator
{
	// Context and logger for code generation
	protected TreeLogger logger = null;
	protected GeneratorContext context = null;
	TypeOracle typeOracle;
	
	protected String typeName = null;
	protected JClassType type = null;
	
	private ConcurrentHashMap<String,String> classMapping = new ConcurrentHashMap<>();

	@Override
	public String generate( TreeLogger logger, GeneratorContext context, String typeName ) throws UnableToCompleteException
	{
		this.logger = logger;
		this.context = context;
		this.typeName = typeName;
		
		if( typeName == null )
			return null;
		
		typeOracle = context.getTypeOracle();
		
		try
		{
			type = typeOracle.getType( typeName );
		}
		catch( NotFoundException e )
		{
			e.printStackTrace();
			throw new RuntimeException( e );
		}
		
		return generate();
	}
	
	protected String generate()
	{
		String generatedClassName = typeName.replaceAll( "\\.", "_" ) + "_HexaCssImpl";
		
		generateClass( generatedClassName );
		
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
		
		for( JMethod m : type.getMethods() )
		{
			String shrinkedName = getMethodSignature( m );
			
			sourceWriter.println( "public String " + m.getName() + "() { return \"" + shrinkedName + "\"; }" );
		}

		// close generated class
		sourceWriter.outdent();
		sourceWriter.println( "}" );

		// commit generated class
		context.commit( logger, printWriter );
		
		// commit compilation artifact
		try
		{
			HexaCssArtifact artifact = new HexaCssArtifact( classMapping );
			context.commitArtifact( logger, artifact );
		}
		catch( UnableToCompleteException e )
		{
			e.printStackTrace();
		}
		
		return true;
	}

	private String getMethodSignature( JMethod method )
	{
		String normalName;
		
		ClassName classNameAnnotation = method.getAnnotation( ClassName.class );
		if( classNameAnnotation != null )
		{
			normalName = classNameAnnotation.value();
		}
		else
		{
			HexaCssExtra hexaAnnotation = method.getAnnotation( HexaCssExtra.class );
			if(hexaAnnotation!=null)
				normalName = hexaAnnotation.name();
			else
				normalName = cssClassPrefix() + method.getName();
		}
		
		String shrinkedName = classMapping.get( normalName );
		if( shrinkedName == null )
		{
			shrinkedName = "h" + generateClassSignature( normalName );
			classMapping.put( normalName, shrinkedName );
		}
		
		return shrinkedName;
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
		if( res.endsWith( "-Css-" ) )
			return res.substring( 0, res.length() - 4 );
		return res;
	}
}
