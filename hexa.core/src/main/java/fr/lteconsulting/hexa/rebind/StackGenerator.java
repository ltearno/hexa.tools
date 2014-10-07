package fr.lteconsulting.hexa.rebind;

import java.io.PrintWriter;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

public class StackGenerator extends Generator
{
	@Override
	public String generate( TreeLogger logger, GeneratorContext context, String requestedClass ) throws UnableToCompleteException
	{
		logger.log( TreeLogger.INFO, "Generate '" + requestedClass, null );

		TypeOracle typeOracle = context.getTypeOracle();

		JClassType requestedType = typeOracle.findType( requestedClass );
		if( requestedType == null )
		{
			logger.log( TreeLogger.ERROR, "Type '" + requestedClass + "' has not been found by the Oracle", null );
			throw new UnableToCompleteException();
		}

		String className = requestedType.getName() + "Impl";
		String fullClassName = requestedClass + "Impl";
		String packageName = requestedType.getPackage().getName();

		PrintWriter printWriter = context.tryCreate( logger, packageName, className );
		if( printWriter == null )
		{
			logger.log( TreeLogger.DEBUG, requestedClass + " : CANNOT CREATE PRINT WRITER", null );
			return fullClassName;
		}

		JClassType[] imple = requestedType.getImplementedInterfaces();
		JClassType implemented = imple[0];
		JParameterizedType parametrized = implemented.isParameterized();
		JClassType[] typeArgs = parametrized.getTypeArgs();
		logger.log( TreeLogger.INFO, imple.length + " implemented interfaces / " + typeArgs.length + " type args:" + typeArgs[0].getName() );

		//
		String typeFactored = typeArgs[0].getSimpleSourceName();
		String factoredTypeQualified = typeArgs[0].getQualifiedSourceName();

		ClassSourceFileComposerFactory composerFactory = new ClassSourceFileComposerFactory( packageName, className );
		composerFactory.addImport( "com.google.gwt.core.client.GWT" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.Stack" );
		composerFactory.addImport( factoredTypeQualified );
		composerFactory.addImplementedInterface( requestedClass );

		SourceWriter sw = composerFactory.createSourceWriter( context, printWriter );
		if( sw == null )
			return fullClassName; // null, already generated

		// extended version : stack instances share the same objects.
		// BE CAREFUL : This is ok in GWT since browsers are single threaded
		// but it wouldn't work as-it-is in a multi-threaded env

		sw.println( "private static " + typeFactored + "[] objs = new " + typeFactored + "[Stack.NB_OBJS];" );
		sw.println( "private static int next = 0;" );
		sw.println( "" );

		sw.println( "public " + className + "() {" );
		sw.println( "if( objs[0] != null )" );
		sw.indent();
		sw.println( "return;" );
		sw.outdent();
		sw.println( "for( int i=0;i<Stack.NB_OBJS;i++ )" );
		sw.indent();
		sw.println( "objs[i] = new " + typeFactored + "();" );
		sw.outdent();
		sw.println( "}" );
		sw.println( "" );

		sw.println( "public " + typeFactored + " alloc()" );
		sw.println( "{" );
		sw.println( "next %= Stack.NB_OBJS;" );
		sw.println( "return objs[next++];" );
		sw.println( "}" );

		// simple version : each stack instance has its own objects
		/*
		 * sw.println( "public "+className+"() {" ); sw.println(
		 * "for( int i=0;i<Stack.NB_OBJS;i++ )" ); sw.indent(); sw.println(
		 * "objs[i] = new "+typeFactored+"();" ); sw.outdent(); sw.println( "}"
		 * );
		 * 
		 * sw.println(
		 * "private "+typeFactored+"[] objs = new "+typeFactored+"[Stack.NB_OBJS];"
		 * ); sw.println( "private int next = 0;" ); sw.println(
		 * "public "+typeFactored+" alloc()" ); sw.println( "{" ); sw.println(
		 * "next %= Stack.NB_OBJS;" ); sw.println( "return objs[next++];" );
		 * sw.println( "}" );
		 */
		sw.commit( logger );

		return fullClassName;
	}
}
