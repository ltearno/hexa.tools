package fr.lteconsulting.hexa.rebind;

import java.io.PrintWriter;

import fr.lteconsulting.hexa.client.comm.FactoredInterface;
import fr.lteconsulting.hexa.client.comm.FieldName;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

public class DataProxyFastFactoryGenerator extends Generator
{
	static String getDataProxyFastImplName( JType type )
	{
		return type.getSimpleSourceName() + "Impl";
	}

	public static void generateDataImpl( String className, JType type, SourceWriter sw, TreeLogger logger )
	{
		sw.println( "protected " + className + "() {}" );

		JClassType clazz = type.isInterface();
		for( JMethod m : clazz.getMethods() )
		{
			FieldName fnAnnotation = m.getAnnotation( FieldName.class );
			assert (fnAnnotation != null) : "DataProxyFast factory needs FieldName annotation";
			sw.println( "public final " + m.getReturnType().getParameterizedQualifiedSourceName() + " " + m.getName() + "()" );
			sw.println( "{" );
			sw.indent();

			if( m.getReturnType().getSimpleSourceName().equals( "int" ) )
				sw.println( "return getInt( \"" + fnAnnotation.fieldName() + "\" );" );
			else if( m.getReturnType().getSimpleSourceName().equals( "String" ) )
				sw.println( "return getString( \"" + fnAnnotation.fieldName() + "\" );" );

			sw.outdent();
			sw.println( "}" );
		}
	}

	public static void generate( JType type, SourceWriter sw, TreeLogger logger )
	{
		sw.println( "public <T> T getData( JavaScriptObject obj )" );
		sw.println( "{" );
		sw.indent();

		// sw.println( "GWT.log( \"A Casting obj : \" + obj.toString() );" );
		// sw.println( "GWT.log( \"Jsoncontent:\"+HexaTools.toJSON(obj) );" );
		// sw.println( type.getSimpleSourceName() +
		// "ImplStd impl = "+type.getSimpleSourceName() + "ImplStd.as( obj );"
		// );
		sw.println( "return (T)obj;" );

		sw.outdent();
		sw.println( "}" );
	}

	@Override
	public String generate( TreeLogger logger, GeneratorContext ctx, String requestedClass ) throws UnableToCompleteException
	{
		TypeOracle typeOracle = ctx.getTypeOracle();
		assert (typeOracle != null);

		JClassType remoteService = typeOracle.findType( requestedClass );
		if( remoteService == null )
		{
			logger.log( TreeLogger.ERROR, "Unable to find metadata for type '" + requestedClass + "'", null );
			throw new UnableToCompleteException();
		}

		if( remoteService.isInterface() == null )
		{
			logger.log( TreeLogger.ERROR, remoteService.getQualifiedSourceName() + " is not an interface", null );
			throw new UnableToCompleteException();
		}

		FactoredInterface fiAnnotation = remoteService.getAnnotation( FactoredInterface.class );
		JClassType fiType = typeOracle.findType( fiAnnotation.clazz().getName() );

		logger.log( TreeLogger.INFO, "Generating " + remoteService.getSimpleSourceName() );

		String createdClassName = remoteService.getSimpleSourceName() + "Impl";// requestedClassName
																				// +
																				// "Impl";
		String fullCreatedClassName = remoteService.getPackage().getName() + "." + /*
																					 * "com.example.client.data.record."
																					 * +
																					 */remoteService.getSimpleSourceName() + "Impl";// requestedClass
		// +
		// "Impl";
		String packageName = remoteService.getPackage().getName();// "com.example.client.data.record";//
																	// remoteService.getPackage().getName();

		/*
		 * String dataImplName = fiType.getSimpleSourceName() + "ImplStd";
		 * PrintWriter pw2 = ctx.tryCreate( logger, packageName, dataImplName );
		 * if( pw2 != null ) { ClassSourceFileComposerFactory cf2 = new
		 * ClassSourceFileComposerFactory( packageName, dataImplName );
		 * cf2.addImport( "fr.lteconsulting.hexa.client.comm.GenericJSO" ); cf2.addImport(
		 * "com.google.gwt.core.client.JavaScriptObject" ); cf2.addImport(
		 * "com.google.gwt.core.client.GWT" ); cf2.addImport(
		 * "fr.lteconsulting.hexa.client.tools.HexaTools" ); cf2.addImplementedInterface(
		 * fiType.getParameterizedQualifiedSourceName() ); cf2.setSuperclass(
		 * "GenericJSO" ); //sw.println(
		 * "static class "+getDataProxyFastImplName
		 * (type)+" extends GenericJSO implements "
		 * +type.getParameterizedQualifiedSourceName() ); SourceWriter sw2 =
		 * cf2.createSourceWriter( ctx, pw2 ); generateDataImpl( fiType, sw2,
		 * logger ); sw2.commit( logger ); }
		 */

		PrintWriter printWriter = ctx.tryCreate( logger, packageName, createdClassName );
		if( printWriter == null )
		{
			// logger.log( TreeLogger.INFO, requestedClass +
			// " : CANNOT CREATE PRINT WRITER", null );
			return fullCreatedClassName;
		}

		ClassSourceFileComposerFactory composerFactory = new ClassSourceFileComposerFactory( packageName, createdClassName );
		composerFactory.addImplementedInterface( requestedClass );
		composerFactory.addImport( "com.google.gwt.core.client.GWT" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.tools.HexaTools" );
		composerFactory.addImport( "java.util.ArrayList" );
		composerFactory.addImport( "java.util.HashMap" );
		composerFactory.addImport( "java.util.Iterator" );
		composerFactory.addImport( "java.util.Map.Entry" );
		// composerFactory.addImport( packageName + "." + dataImplName );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.GenericJSO" );
		composerFactory.addImport( "com.google.gwt.core.client.JavaScriptObject" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.DataProxy" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.DataProxyFastFactories" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.IDataProxyFastFactory" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.Service" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.ServerComm" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.CachedServerComm" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.ResponseJSO" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.JSOArrayInteger" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.ServerComm.ServerCommCb" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.interfaces.ITablesManager" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.interfaces.IAsyncCallback" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.ServerComm.ServerCommMessageCb" );
		composerFactory.addImport( "com.google.gwt.core.client.GWT" );
		composerFactory.addImport( "com.google.gwt.core.client.JsArray" );
		composerFactory.addImport( "com.google.gwt.core.client.JsArrayInteger" );
		composerFactory.addImport( "com.google.gwt.core.client.JsArrayString" );
		composerFactory.addImport( "com.google.gwt.http.client.URL" );
		composerFactory.addImport( "com.google.gwt.user.client.Window" );
		composerFactory.addImport( "com.google.gwt.user.client.rpc.AsyncCallback" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.common.HexaDate" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.common.HexaTime" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.common.HexaDateTime" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.FactoredInterface" );
		composerFactory.addImport( "com.google.gwt.json.client.JSONObject" );

		SourceWriter sw = composerFactory.createSourceWriter( ctx, printWriter );
		if( sw == null )
		{
			logger.log( TreeLogger.ERROR, requestedClass + " : CANNOT CREATE SOURCEWRITER", null );
			return fullCreatedClassName; // null, already generated
		}

		generate( fiType, sw, logger );

		sw.commit( logger );

		return fullCreatedClassName;
	}
}
