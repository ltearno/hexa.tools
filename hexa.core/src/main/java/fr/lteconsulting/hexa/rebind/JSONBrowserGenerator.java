package fr.lteconsulting.hexa.rebind;

import java.io.PrintWriter;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.TreeLogger.Type;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;
import fr.lteconsulting.hexa.client.jsonbrowser.Path;

public class JSONBrowserGenerator extends Generator {
	TypeOracle oracle;
	SourceWriter sourceWriter;
	
	@Override
	public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {
		logger.log( Type.ERROR, "On me demande de générer : " + typeName );
		
		oracle = context.getTypeOracle();
		
		String generatedClass = typeName + "Impl";
		JClassType type = oracle.findType( typeName );
		
		PrintWriter pw = context.tryCreate(logger, type.getPackage().getName(), type.getSimpleSourceName()+"Impl");
		if( pw == null )
			return generatedClass;
		
		logger.log(Type.ERROR, "PKNAME:"+type.getPackage().getName()+" / CLSNAME:"+type.getSimpleSourceName()+"Impl" );
		
		ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory( type.getPackage().getName(), type.getSimpleSourceName()+"Impl" );
		composer.addImplementedInterface(typeName);
		composer.addImport( "com.google.gwt.json.client.JSONValue" );
		composer.addImport( "com.google.gwt.json.client.JSONObject" );
		composer.addImport( "com.google.gwt.json.client.JSONString" );
		
		logger.log(Type.ERROR, "Composer: " + composer);
		sourceWriter = composer.createSourceWriter( context, pw );
		
		// quelles méthodes dans l'interface à implémenter ?
		JMethod[] methods = type.getMethods();
		for( int m=0; m<methods.length; m++ )
			generateMethod( methods[m] );
		
		sourceWriter.commit(logger);
		
		return generatedClass;
	}
	
	private void generateMethod( JMethod method )
	{
		Path pathAnnotation = method.getAnnotation( Path.class );
		String path = pathAnnotation.path();
		
		String[] parts = path.split( "\\." );
		
		sourceWriter.println( "// path : " + path );
		sourceWriter.println( "// parts : " + parts.length );
		sourceWriter.println( "public " + method.getReturnType().getParameterizedQualifiedSourceName() + " " + method.getName() + "( JSONValue json )" );
		sourceWriter.println( "{" );
		sourceWriter.indent();
		
		sourceWriter.println( "JSONObject o;" );
		
		for( int i=0; i<parts.length; i++ )
		{
			sourceWriter.println( "if( json == null ) return null;" );
			sourceWriter.println( "o = json.isObject();" );
			sourceWriter.println( "if( o == null ) return null;" );
			sourceWriter.println( "json = o.get( \""+parts[i]+"\" );" );
		}
		
		sourceWriter.println( "if( json == null ) return null;" );
		sourceWriter.println( "JSONString s = json.isString();" );
		sourceWriter.println( "if( s == null ) return null;" );
		sourceWriter.println( "return s.stringValue();" );
		
		sourceWriter.outdent();
		sourceWriter.println( "}" );
	}
}
