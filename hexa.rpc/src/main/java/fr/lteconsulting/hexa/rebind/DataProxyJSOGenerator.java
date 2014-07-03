package fr.lteconsulting.hexa.rebind;

import java.io.PrintWriter;

import fr.lteconsulting.hexa.client.comm.CustomMethod;
import fr.lteconsulting.hexa.client.comm.FieldName;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

public class DataProxyJSOGenerator extends Generator
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
			logger.log( TreeLogger.INFO, requestedClass + " : CANNOT CREATE PRINT WRITER", null );
			return fullClassName;
		}

		ClassSourceFileComposerFactory composerFactory = new ClassSourceFileComposerFactory( packageName, className );
		composerFactory.addImport( "com.google.gwt.core.client.GWT" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.DataProxyJSO" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.GenericJSO" );
		composerFactory.addImport( "com.google.gwt.core.client.JsArray" );
		composerFactory.addImplementedInterface( requestedClass );

		composerFactory.setSuperclass( "GenericJSO" );

		SourceWriter sw = composerFactory.createSourceWriter( context, printWriter );
		if( sw == null )
		{
			// logger.log( TreeLogger.WARN, requestedClass +
			// " : CANNOT CREATE SOURCEWRITER", null );
			return fullClassName; // null, already generated
		}

		JMethod[] methods = requestedType.getMethods();

		for( JMethod method : methods )
		{
			FieldName fnAnnotation = method.getAnnotation( FieldName.class );
			CustomMethod cmAnnotation = method.getAnnotation( CustomMethod.class );
			if( cmAnnotation != null )
			{
				sw.println( "public " + method.getReturnType().getSimpleSourceName() + " " + method.getName() + "() {" );
				sw.indent();
				sw.println( cmAnnotation.body() );
				sw.outdent();
				sw.println( "}" );
			}
			else if( fnAnnotation != null )
			{
				sw.println( "public final " + method.getReturnType().getParameterizedQualifiedSourceName() + " " + method.getName() + "() {" );
				sw.indent();

				if( method.getReturnType().getSimpleSourceName().compareTo( "ArrayList" ) != 0 )
				{
					String jsoType = method.getReturnType().getSimpleSourceName();
					if( method.getReturnType().getSimpleSourceName().compareTo( "int" ) == 0 )
						jsoType = "Int";
					if( method.getReturnType().getSimpleSourceName().compareTo( "double" ) == 0 )
						jsoType = "Double";
					sw.println( "return get" + jsoType + "( \"" + fnAnnotation.fieldName() + "\" );" );
				}
				else
				{
					JParameterizedType ptype = method.getReturnType().isParameterized();
					JClassType[] typeArgs = ptype.getTypeArgs();
					assert (typeArgs.length == 1);
					String type = typeArgs[0].getParameterizedQualifiedSourceName();
					String field = fnAnnotation.fieldName();

					sw.println( "ArrayList<" + type + "> res = new ArrayList<" + type + ">();" );
					sw.println( "JsArray<GenericJSO> jsos = jso.getArray( \"" + field + "\" );" );
					sw.println( "for( int i=0; i<jsos.length(); i++ ) {" );
					sw.println( "	" + type + " elem = GWT.create( " + type + ".class );" );
					sw.println( "	elem.init( jsos.get(i) );" );
					sw.println( "	res.add( elem );" );
					sw.println( "}" );
					sw.println( "return res;" );
				}

				sw.outdent();
				sw.println( "}" );
			}
		}

		sw.commit( logger );

		return fullClassName;
	}
}
