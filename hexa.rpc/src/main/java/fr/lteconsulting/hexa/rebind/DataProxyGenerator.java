package fr.lteconsulting.hexa.rebind;

import java.io.PrintWriter;
import java.util.HashMap;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JGenericType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.JTypeParameter;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;
import fr.lteconsulting.hexa.client.comm.CustomMethod;
import fr.lteconsulting.hexa.client.comm.FieldName;

public class DataProxyGenerator extends Generator
{
	private String genMethodPrototype( JMethod method )
	{
		StringBuilder sb = new StringBuilder();

		sb.append( "public " + method.getReturnType().getParameterizedQualifiedSourceName() + " " + method.getName() + "( " );

		for( int i = 0; i < method.getParameters().length; i++ )
		{
			JParameter param = method.getParameters()[i];
			sb.append( param.getType().getParameterizedQualifiedSourceName() + " " + param.getName() );
			if( i < method.getParameters().length - 1 )
				sb.append( ", " );
		}

		sb.append( " )" );

		return sb.toString();
	}

	// HexaDate fields
	HashMap<String, String> hexaDateFields = new HashMap<String, String>();

	String registerHexaDateVariable( String fieldName )
	{
		String variableName = hexaDateFields.get( fieldName );
		if( variableName == null )
		{
			variableName = "hexaDate_" + hexaDateFields.size();
			hexaDateFields.put( fieldName, variableName );
		}
		return variableName;
	}

	void generateHexaDateVariables( SourceWriter sw )
	{
		for( String variableName : hexaDateFields.values() )
			sw.println( "HexaDate " + variableName + " = null;" );
	}

	// HexaTime fields
	HashMap<String, String> hexaTimeFields = new HashMap<String, String>();

	String registerHexaTimeVariable( String fieldName )
	{
		String variableName = hexaTimeFields.get( fieldName );
		if( variableName == null )
		{
			variableName = "hexaTime_" + hexaTimeFields.size();
			hexaTimeFields.put( fieldName, variableName );
		}
		return variableName;
	}

	void generateHexaTimeVariables( SourceWriter sw )
	{
		for( String variableName : hexaTimeFields.values() )
			sw.println( "HexaTime " + variableName + " = null;" );
	}

	// HexaDateTime fields
	HashMap<String, String> hexaDateTimeFields = new HashMap<String, String>();

	String registerHexaDateTimeVariable( String fieldName )
	{
		String variableName = hexaDateTimeFields.get( fieldName );
		if( variableName == null )
		{
			variableName = "hexaDateTime_" + hexaDateTimeFields.size();
			hexaDateTimeFields.put( fieldName, variableName );
		}
		return variableName;
	}

	void generateHexaDateTimeVariables( SourceWriter sw )
	{
		for( String variableName : hexaDateTimeFields.values() )
			sw.println( "HexaDateTime " + variableName + " = null;" );
	}

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

		// Get type parameters informations so that we generate a fitting class
		String parameterizedTypeExt = "";
		String typeExt = "";
		JGenericType genericType = requestedType.isGenericType();
		if( genericType != null )
		{
			parameterizedTypeExt = "<";
			typeExt = "<";
			JTypeParameter[] tps = genericType.getTypeParameters();
			boolean needComa = false;
			for( int i = 0; i < tps.length; i++ )
			{
				if( needComa )
				{
					parameterizedTypeExt += ", ";
					typeExt += ", ";
				}
				needComa = true;

				JTypeParameter tp = tps[i];

				parameterizedTypeExt += tp.getName() + " extends ";
				typeExt += tp.getName();

				JClassType[] cts = tp.getBounds();
				boolean needAnd = false;
				for( int j = 0; j < cts.length; j++ )
				{
					if( needAnd )
						parameterizedTypeExt += " & ";
					needAnd = true;

					JClassType ct = cts[j];
					parameterizedTypeExt += ct.getName();
				}
			}
			parameterizedTypeExt += ">";
			typeExt += ">";
		}

		ClassSourceFileComposerFactory composerFactory = new ClassSourceFileComposerFactory( packageName, className + parameterizedTypeExt );
		composerFactory.addImport( "com.google.gwt.core.client.GWT" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.DataProxy" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.GenericJSO" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.ResponseJSO" );
		composerFactory.addImport( "java.util.ArrayList" );
		composerFactory.addImport( "com.google.gwt.core.client.JsArray" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.common.HexaDate" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.common.HexaTime" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.common.HexaDateTime" );
		composerFactory.addImport( "com.google.gwt.core.client.JavaScriptObject" );
		composerFactory.addImport( "com.google.gwt.json.client.JSONObject" );
		composerFactory.addImplementedInterface( requestedClass + typeExt );

		SourceWriter sw = composerFactory.createSourceWriter( context, printWriter );
		if( sw == null )
		{
			// logger.log( TreeLogger.WARN, requestedClass +
			// " : CANNOT CREATE SOURCEWRITER", null );
			return fullClassName; // null, already generated
		}

		sw.println( "private GenericJSO jso = null;" );

		JMethod[] methods = requestedType.getMethods();

		sw.println( "@Override public String toString() {" );
		sw.indent();
		sw.println( "return new JSONObject(jso).toString();" );
		sw.outdent();
		sw.println( "}" );

		sw.println( "public void init( GenericJSO jso ) {" );
		sw.indent();
		sw.println( "this.jso = jso;" );
		sw.outdent();
		sw.println( "}" );

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
				String methodPrototype = genMethodPrototype( method );
				sw.println( methodPrototype );
				sw.println( "{" );
				// sw.println( "public " +
				// method.getReturnType().getParameterizedQualifiedSourceName()
				// + " " + method.getName() + "() {" );
				sw.indent();

				if( method.getReturnType().getSimpleSourceName().compareTo( "HexaDate" ) == 0 )
				{
					String variableName = registerHexaDateVariable( fnAnnotation.fieldName() );
					sw.println( "if( " + variableName + " == null ) " + variableName + " = new HexaDate( jso.getString( \"" + fnAnnotation.fieldName() + "\" ) );" );
					sw.println( "return " + variableName + ";" );
				}
				else if( method.getReturnType().getSimpleSourceName().compareTo( "HexaTime" ) == 0 )
				{
					String variableName = registerHexaTimeVariable( fnAnnotation.fieldName() );
					sw.println( "if( " + variableName + " == null ) " + variableName + " = new HexaTime( jso.getString( \"" + fnAnnotation.fieldName() + "\" ) );" );
					sw.println( "return " + variableName + ";" );

					// sw.println( "return new HexaTime( jso.getString( \"" +
					// fnAnnotation.fieldName() + "\" ) );" );
				}
				else if( method.getReturnType().getSimpleSourceName().compareTo( "HexaDateTime" ) == 0 )
				{
					String variableName = registerHexaDateTimeVariable( fnAnnotation.fieldName() );
					sw.println( "if( " + variableName + " == null ) " + variableName + " = new HexaDateTime( jso.getString( \"" + fnAnnotation.fieldName() + "\" ) );" );
					sw.println( "return " + variableName + ";" );

					// sw.println( "return new HexaDateTime( jso.getString( \""
					// + fnAnnotation.fieldName() + "\" ) );" );
				}
				else if( isJSOType( method.getReturnType() ) )
				{
					sw.println( "return jso.getGenericJSO( \"" + fnAnnotation.fieldName() + "\" ).cast();" );
				}
				//else if( !method.getReturnType().getSimpleSourceName().equals( "ArrayList" ) )
				else if( ! ( method.getReturnType().getQualifiedSourceName().equals( "java.util.ArrayList" ) || method.getReturnType().getQualifiedSourceName().equals( "java.util.List" ) ) )
				{
					String jsoType = method.getReturnType().getSimpleSourceName();
					if( method.getReturnType().getSimpleSourceName().compareTo( "int" ) == 0 )
						jsoType = "Int";
					else if( method.getReturnType().getSimpleSourceName().compareTo( "Integer" ) == 0 )
						jsoType = "Integer";
					else if( method.getReturnType().getSimpleSourceName().compareTo( "boolean" ) == 0 )
						jsoType = "Boolean";
					else if( method.getReturnType().getSimpleSourceName().compareTo( "double" ) == 0 )
						jsoType = "Double";
					sw.println( "return jso.get" + jsoType + "( \"" + fnAnnotation.fieldName() + "\" );" );
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

		generateHexaDateVariables( sw );
		generateHexaTimeVariables( sw );
		generateHexaDateTimeVariables( sw );

		sw.commit( logger );

		return fullClassName;
	}

	boolean isJSOType( JType type )
	{
		JClassType cType = type.isClass();
		if( cType == null )
			cType = type.isInterface();
		if( cType == null )
			return false;

		for( JClassType t = cType; t != null; t = t.getSuperclass() )
		{
			// sw.println( "// supertype : " + t.getSimpleSourceName() );
			JTypeParameter typeParam = t.isTypeParameter();
			if( typeParam != null )
			{
				// sw.println( "// which is a type parameter" );
				JClassType[] bounds = typeParam.getBounds();
				for( int b = 0; b < bounds.length; b++ )
				{
					// sw.println( "// which is bound to : " +
					// bounds[b].getSimpleSourceName() );
					if( bounds[b].getSimpleSourceName().compareTo( "JavaScriptObject" ) == 0 )
						return true;
				}
			}
			if( t.getSimpleSourceName().compareTo( "JavaScriptObject" ) == 0 )
				return true;
		}

		return false;
	}
}
