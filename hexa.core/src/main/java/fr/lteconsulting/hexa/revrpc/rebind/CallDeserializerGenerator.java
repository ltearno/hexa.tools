package fr.lteconsulting.hexa.revrpc.rebind;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

public class CallDeserializerGenerator extends Generator
{
	// Context and logger for code generation
	TreeLogger logger = null;
	GeneratorContext context = null;
	TypeOracle typeOracle = null;

	// asked type name
	String typeName = null;

	// type info on the asked class
	JClassType classType = null;

	// package of the asked type
	String packageName = null;

	// generated class name
	String className = null;

	String implementationClass = "Void";
	JClassType implementationType = null;

	@Override
	public String generate( TreeLogger logger, GeneratorContext context, String typeName ) throws UnableToCompleteException
	{
		this.logger = logger;
		this.context = context;
		this.typeName = typeName;

		// get the "reflection" machine of GWT compiler
		typeOracle = context.getTypeOracle();
		try
		{
			// get classType and save instance variables
			classType = typeOracle.getType( typeName );
			packageName = classType.getPackage().getName();
			className = classType.getSimpleSourceName() + "Impl";

			// Generate class source code
			generateClass();
		}
		catch( Exception e )
		{
			// record to logger that Map generation threw an exception
			logger.log( TreeLogger.ERROR, "ERROR when generating " + className + " for " + typeName, e );
		}

		// return the fully qualifed name of the class generated
		return packageName + "." + className;
	}

	private void msg( String message )
	{
		logger.log( TreeLogger.INFO, message, null );
	}

	private void generateClass()
	{
		deserializers = new HashMap<String, DeserializerInfo>();
		toGenerate = new ArrayList<String>();

		// get print writer that receives the source code
		PrintWriter printWriter = null;

		printWriter = context.tryCreate( logger, packageName, className );
		// print writer if null, source code has ALREADY been generated, return
		if( printWriter == null )
			return;

		// init composer, set class properties, create source writer
		ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory( packageName, className );

		// output a class "typeName" + "Impl"
		// which extends the asked type
		composer.addImplementedInterface( typeName );
		composer.addImport( "java.util.List" );
		composer.addImport( "java.util.ArrayList" );
		composer.addImport( "java.util.Date" );
		composer.addImport( "com.google.gwt.json.client.JSONObject" );
		composer.addImport( "com.google.gwt.json.client.JSONValue" );
		composer.addImport( "com.google.gwt.json.client.JSONArray" );
		composer.addImport( "com.dng.revrpc.client.CallDeserializerUtil" );

		msg( "Requested type : " + classType.getParameterizedQualifiedSourceName() );
		JClassType[] implementedInterfaces = classType.getImplementedInterfaces();
		for( int i = 0; i < implementedInterfaces.length; i++ )
			msg( "Implemented interface : " + implementedInterfaces[i].getParameterizedQualifiedSourceName() );

		JParameterizedType parametrizedType = implementedInterfaces[0].isParameterized();
		implementationType = parametrizedType.getTypeArgs()[0];
		implementationClass = implementationType.getParameterizedQualifiedSourceName();

		msg( "Implementation type : " + implementationClass );

		SourceWriter sourceWriter = composer.createSourceWriter( context, printWriter );

		// generate the List<String> getMethods(); method
		generateRegisterMethod( sourceWriter );
		generateNewCallMethod( sourceWriter );
		generateDeserializers( sourceWriter );

		// close generated class
		sourceWriter.outdent();
		sourceWriter.println( "}" );

		// commit generated class
		context.commit( logger, printWriter );
	}

	private void generateRegisterMethod( SourceWriter sourceWriter )
	{
		sourceWriter.println( "private " + implementationClass + " implementation = null;" );
		sourceWriter.println();

		sourceWriter.println( "@Override" );
		sourceWriter.println( "public void registerImplementation( " + implementationClass + " implementation )" );
		sourceWriter.println( "{" );
		sourceWriter.indent();
		sourceWriter.println( "this.implementation = implementation;" );
		sourceWriter.outdent();
		sourceWriter.println( "}" );
		sourceWriter.println();
	}

	private String arrayToString( ArrayList<String> list )
	{
		String res = "";
		boolean addComa = false;

		for( String s : list )
		{
			if( addComa )
				res += ", ";
			addComa = true;

			res += s;
		}

		return res;
	}

	static class DeserializerInfo
	{
		String methodName;
		JType serializedType;
	}

	HashMap<String, DeserializerInfo> deserializers = new HashMap<String, DeserializerInfo>();
	ArrayList<String> toGenerate = new ArrayList<String>();

	private void generateNewCallMethod( SourceWriter sourceWriter )
	{
		sourceWriter.println( "@Override" );
		sourceWriter.println( "public void newCall( JSONObject json )" );
		sourceWriter.println( "{" );
		sourceWriter.indent();
		sourceWriter.println( "String method = json.get( \"method\" ).isString().stringValue();" );

		JMethod[] methods = implementationType.getMethods();
		for( int m = 0; m < methods.length; m++ )
		{
			JMethod method = methods[m];

			sourceWriter.println( "if( method.equals(\"" + method.getName() + "\") )" );
			sourceWriter.println( "{" );
			sourceWriter.indent();
			JParameter[] parameters = method.getParameters();
			ArrayList<String> pNames = new ArrayList<String>();
			for( int p = 0; p < parameters.length; p++ )
			{
				JParameter prm = parameters[p];
				pNames.add( prm.getName() );

				sourceWriter.println( "JSONValue " + prm.getName() + "Json = CallDeserializerUtil.getParam( json, " + p + ", \"" + prm.getType().getParameterizedQualifiedSourceName() + "\" );" );

				String deserializerMethodName = getDeserializerMethodName( prm.getType() );

				sourceWriter.println( prm.getType().getParameterizedQualifiedSourceName() + " " + prm.getName() + " = " + deserializerMethodName + "( " + prm.getName() + "Json.isObject().get( \"value\" ) );" );
			}

			// make the call
			sourceWriter.println();
			sourceWriter.println( "implementation." + method.getName() + "(" + arrayToString( pNames ) + ");" );
			sourceWriter.outdent();
			sourceWriter.println( "}" );
		}

		sourceWriter.outdent();
		sourceWriter.println( "}" );
		sourceWriter.println();
	}

	private String getDeserializerMethodName( JType type )
	{
		DeserializerInfo info = deserializers.get( type.getParameterizedQualifiedSourceName() );
		if( info == null )
		{
			info = new DeserializerInfo();
			info.methodName = "deserialize_" + deserializers.size() + "_" + type.getSimpleSourceName();
			info.serializedType = type;

			deserializers.put( type.getParameterizedQualifiedSourceName(), info );

			toGenerate.add( type.getParameterizedQualifiedSourceName() );

			msg( "NEED FOR DESERIALIZER FOR TYPE " + type.getParameterizedQualifiedSourceName() );
		}

		return info.methodName;
	}

	private void generateDeserializers( SourceWriter sourceWriter )
	{
		while( !toGenerate.isEmpty() )
		{
			String typeName = toGenerate.remove( 0 );

			msg( "GENERATING DESERIALIZER FOR TYPE " + typeName );

			DeserializerInfo info = deserializers.get( typeName );
			JType type = info.serializedType;

			generateDeserializer( sourceWriter, info.methodName, type );
		}
	}

	private void generateDeserializer( SourceWriter sourceWriter, String methodName, JType type )
	{
		generateDeserializerBegin( sourceWriter, methodName, type );

		if( type.getParameterizedQualifiedSourceName().equals( "java.lang.String" ) )
			sourceWriter.println( "return json.isString().stringValue();" );

		else if( type.getParameterizedQualifiedSourceName().equals( "java.lang.Integer" ) )
			sourceWriter.println( "return (Integer) json.isNumber().doubleValue();" );

		else if( type.getParameterizedQualifiedSourceName().equals( "int" ) )
			sourceWriter.println( "return (int) json.isNumber().doubleValue();" );

		else if( type.getParameterizedQualifiedSourceName().equals( "java.lang.Boolean" ) )
			sourceWriter.println( "return (Boolean) json.isBoolean().booleanValue();" );

		else if( type.getParameterizedQualifiedSourceName().equals( "boolean" ) )
			sourceWriter.println( "return (boolean) json.isBoolean().booleanValue();" );

		else if( type.getParameterizedQualifiedSourceName().equals( "java.util.Date" ) )
			sourceWriter.println( "return new Date( json.isString().stringValue() );" );

		else if( type.getQualifiedSourceName().equals( "java.util.List" ) )
		{
			JParameterizedType parametrizedType = type.isParameterized();
			JClassType itemsType = parametrizedType.getTypeArgs()[0];

			sourceWriter.println( "java.util.ArrayList res = new java.util.ArrayList();" );

			sourceWriter.println( "JSONArray jsonArray = json.isArray();" );
			sourceWriter.println( "for( int i=0; i<jsonArray.size(); i++ )" );
			sourceWriter.indent();
			sourceWriter.println( "res.add( " + getDeserializerMethodName( itemsType ) + "( jsonArray.get( i ) ) );" );
			sourceWriter.outdent();

			sourceWriter.println( "return res;" );
		}

		else if( type.isEnum() != null )
		{
			// JEnumType type.isEnum();
			sourceWriter.println( "return " + type.getParameterizedQualifiedSourceName() + ".valueOf( json.isString().stringValue() );" );
		}

		else
		{
			sourceWriter.println( type.getParameterizedQualifiedSourceName() + " res = new " + type.getParameterizedQualifiedSourceName() + "();" );

			JField fields[] = type.isClass().getFields();
			for( int i = 0; i < fields.length; i++ )
			{
				JField field = fields[i];

				String deserializerMethodName = getDeserializerMethodName( field.getType() );

				sourceWriter.println( field.getType().getParameterizedQualifiedSourceName() + " " + field.getName() + " = " + deserializerMethodName + "( json.isObject().get( \"" + field.getName() + "\" ) );" );
				sourceWriter.println( "res." + field.getName() + " = " + field.getName() + ";" );
			}

			sourceWriter.println( "return res;" );
		}

		generateDeserializerEnd( sourceWriter );
	}

	private void generateDeserializerBegin( SourceWriter sourceWriter, String methodName, JType type )
	{
		sourceWriter.println( "public " + type.getParameterizedQualifiedSourceName() + " " + methodName + "( JSONValue json )" );
		sourceWriter.println( "{" );
		sourceWriter.indent();
	}

	private void generateDeserializerEnd( SourceWriter sourceWriter )
	{
		sourceWriter.outdent();
		sourceWriter.println( "}" );
	}

	/*
	 * private void generateGetMethods( SourceWriter sourceWriter ) { // check
	 * what are the methods of "typeName" JMethod[] methods =
	 * classType.getMethods();
	 * 
	 * // maybe should use a better name so that it cannot possibly conflict
	 * with the extended class
	 * sourceWriter.println("private ArrayList<String> _methods = null;");
	 * sourceWriter.println("public List<String> getMethods()");
	 * sourceWriter.println("{"); sourceWriter.indent();
	 * 
	 * sourceWriter.println("if( _methods != null ) return _methods;");
	 * sourceWriter.println("_methods = new ArrayList<String>();"); for( int
	 * i=0; i< methods.length; i++ ) {
	 * sourceWriter.println("_methods.add( \""+methods[i].getName()+"\" );"); }
	 * sourceWriter.outdent(); sourceWriter.println("return _methods;");
	 * sourceWriter.println("}"); }
	 */
}
