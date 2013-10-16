package com.hexa.rebind;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

public class ClazzGenerator extends Generator
{
	// Context and logger for code generation
	TreeLogger logger = null;
	GeneratorContext context = null;

	// asked type name
	String askedTypeName = null;

	// type info on the asked class
	JClassType askedType = null;

	// type for which we provide informations
	JClassType reflectedType = null;
	String reflectedTypeName;

	// package of the asked type
	String packageName = null;

	// generated class name
	String generatedClassName = null;

	@Override
	public String generate( TreeLogger logger, GeneratorContext context, String typeName ) throws UnableToCompleteException
	{
		this.logger = logger;
		this.context = context;
		this.askedTypeName = typeName;

		// get the "reflection" machine of GWT compiler
		TypeOracle typeOracle = context.getTypeOracle();
		try
		{
			// get classType and save instance variables
			askedType = typeOracle.getType( typeName );

			// normally, this class inherits Class<T>. Mission is to find T
			JClassType[] interfaces = askedType.getImplementedInterfaces();
			for( int i = 0; i < interfaces.length; i++ )
			{
				if( !interfaces[i].getQualifiedSourceName().equals( "com.hexa.client.classinfo.Clazz" ) )
					continue;

				JParameterizedType parametrized = interfaces[i].isParameterized();
				JClassType[] typeArgs = parametrized.getTypeArgs();

				reflectedType = typeArgs[0];
				reflectedTypeName = reflectedType.getParameterizedQualifiedSourceName();
			}

			if( reflectedType == null )
				throw new UnableToCompleteException();

			if( reflectedTypeName.equals( "com.google.gwt.core.client.JavaScriptObject" ) )
				return "com.hexa.client.classinfo.internal.JavaScriptObjectClazz";
			if( reflectedTypeName.equals( "java.lang.Object" ) )
				return "com.hexa.client.classinfo.internal.ObjectClazz";

			packageName = reflectedType.getPackage().getName();
			generatedClassName = reflectedType.getSimpleSourceName() + "ClazzImpl";

			// Generate class source code
			generateClass();
		}
		catch( Exception e )
		{
			// record to logger that Map generation threw an exception
			logger.log( TreeLogger.ERROR, "ERROR when generating " + generatedClassName + " for " + typeName, e );
		}

		// return the fully qualifed name of the class generated
		return packageName + "." + generatedClassName;
	}

	private void generateClass()
	{
		// get print writer that receives the source code
		PrintWriter printWriter = null;

		printWriter = context.tryCreate( logger, packageName, generatedClassName );
		// print writer if null, source code has ALREADY been generated, return
		if( printWriter == null )
			return;

		// init composer, set class properties, create source writer
		ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory( packageName, generatedClassName );

		// output a class "typeName" + "Impl"
		// which extends the asked type
		composer.setSuperclass( "com.hexa.client.classinfo.internal.ClazzBase<" + reflectedTypeName + ">" );
		//composer.addImplementedInterface( askedType.getParameterizedQualifiedSourceName() );// "com.hexa.client.classinfo.Clazz<"+reflectedTypeName+">"
																							// );
		composer.addImport( "java.util.List" );
		composer.addImport( "java.util.ArrayList" );
		composer.addImport( "com.hexa.client.classinfo.Field" );
		composer.addImport( "com.hexa.client.classinfo.Method" );

		SourceWriter sourceWriter = composer.createSourceWriter( context, printWriter );

		// generate the List<String> getMethods(); method
		generateClass( sourceWriter );

		// close generated class
		sourceWriter.outdent();
		sourceWriter.println( "}" );

		// commit generated class
		context.commit( logger, printWriter );
	}

	private void generateClass( SourceWriter sourceWriter )
	{
		sourceWriter.println( "" );

		String superClassName;
		JClassType superClass = reflectedType.getSuperclass();
		if( superClass != null )
			superClassName = superClass.getQualifiedSourceName() + ".class";
		else
			superClassName = "null";

		sourceWriter.println( "public " + generatedClassName + "()" );
		sourceWriter.println( "{" );
		sourceWriter.indent();
		sourceWriter.println( "super( " + reflectedTypeName + ".class, \"" + reflectedType.getSimpleSourceName() + "\", " + superClassName + " );" );
		sourceWriter.outdent();
		sourceWriter.println( "}" );
		sourceWriter.println( "" );

		// Fields

		List<String> fieldClassNames = new ArrayList<String>();
		JField[] fields = reflectedType.getFields();
		for( int f = 0; f < fields.length; f++ )
		{
			JField field = fields[f];
			if( field.isStatic() )
				continue; // skip

			String fieldClassName = field.getName() + "_FieldImpl";
			fieldClassNames.add( fieldClassName );

			generateFieldClass( field, sourceWriter );
		}

		sourceWriter.println( "protected List<Field> _getDeclaredFields()" );
		sourceWriter.println( "{" );
		sourceWriter.indent();
		sourceWriter.println( "ArrayList<Field> res = new ArrayList<Field>();" );
		for( String fieldClassName : fieldClassNames )
			sourceWriter.println( "res.add( new " + fieldClassName + "());" );
		sourceWriter.outdent();
		sourceWriter.println( "return res;" );
		sourceWriter.println( "}" );
		sourceWriter.println( "" );

		// Methods

		JMethod[] methods = reflectedType.getMethods();

		List<String> methodClassNames = new ArrayList<String>();
		for( int m = 0; m < methods.length; m++ )
		{
			JMethod method = methods[m];
			String methodClassName = method.getName() + "_MethodImpl";
			while( methodClassNames.contains( methodClassName ) )
				methodClassName += "_";
			methodClassNames.add( methodClassName );

			generateMethodClass( methodClassName, method, sourceWriter );
		}

		sourceWriter.println( "protected List<Method> _getMethods()" );
		sourceWriter.println( "{" );
		sourceWriter.indent();
		sourceWriter.println( "ArrayList<Method> res = new ArrayList<Method>();" );
		for( String methodClassName : methodClassNames )
			sourceWriter.println( "res.add( new " + methodClassName + "());" );
		sourceWriter.outdent();
		sourceWriter.println( "return res;" );
		sourceWriter.println( "}" );
		sourceWriter.println( "" );

		// New
		sourceWriter.println( "@Override" );
		sourceWriter.println( "public " + reflectedTypeName + " NEW()" );
		sourceWriter.println( "{" );
		sourceWriter.indent();
		if( reflectedType.isAbstract() )
			sourceWriter.println( "throw new IllegalArgumentException( \"Targetted class is abstract, cannot create instance\" );" );
		else
			sourceWriter.println( "return new " + reflectedTypeName + "();" );
		sourceWriter.outdent();
		sourceWriter.println( "}" );
		sourceWriter.println( "" );
	}

	private static class ModifierBuilder
	{
		StringBuilder sb = new StringBuilder();
		boolean empty = true;

		public void append( String s )
		{
			if( !empty )
				sb.append( " & " );
			empty = false;
			sb.append( s );
		}

		@Override
		public String toString()
		{
			String res = sb.toString();
			if( res.isEmpty() )
				return "0";
			return res;
		}
	}

	private String getFieldModifier( JField field )
	{
		ModifierBuilder mb = new ModifierBuilder();
		if( field.isPrivate() )
			mb.append( "java.lang.reflect.Modifier.PRIVATE" );
		if( field.isProtected() )
			mb.append( "java.lang.reflect.Modifier.PROTECTED" );
		if( field.isPublic() )
			mb.append( "java.lang.reflect.Modifier.PUBLIC" );

		if( field.isStatic() )
			mb.append( "java.lang.reflect.Modifier.STATIC" );
		if( field.isTransient() )
			mb.append( "java.lang.reflect.Modifier.TRANSIENT" );
		if( field.isVolatile() )
			mb.append( "java.lang.reflect.Modifier.VOLATILE" );
		if( field.isFinal() )
			mb.append( "java.lang.reflect.Modifier.FINAL" );

		return mb.toString();
	}

	private void generateFieldClass( JField field, SourceWriter sourceWriter )
	{
		String fieldClassName = field.getName() + "_FieldImpl";

		sourceWriter.println( "class " + fieldClassName + " extends com.hexa.client.classinfo.internal.FieldBase {" );
		sourceWriter.indent();
		sourceWriter.println( "public " + fieldClassName + "()" );
		sourceWriter.println( "{" );
		sourceWriter.indent();
		sourceWriter.println( "super(" + field.getType().getQualifiedSourceName() + ".class, \"" + field.getName() + "\", " + getFieldModifier( field ) + ");" );
		sourceWriter.outdent();
		sourceWriter.println( "}" );
		sourceWriter.println( "" );
		sourceWriter.println( "@Override" );
		sourceWriter.println( "public void setValue( Object object, Object value )" );
		sourceWriter.println( "{" );
		sourceWriter.indent();
		if( field.getType().isPrimitive() != null )
			sourceWriter.println( "setValueInternal_int( object, (Integer) value );" );
		else
			sourceWriter.println( "setValueInternal_Object( object, value );" );
		sourceWriter.outdent();
		sourceWriter.println( "}" );
		sourceWriter.println( "" );
		sourceWriter.println( "@Override" );
		sourceWriter.println( "public <OUT> OUT getValue( Object object )" );
		sourceWriter.println( "{" );
		sourceWriter.indent();
		if( field.getType().isPrimitive() != null )
			sourceWriter.println( "return (OUT) (Integer) getValueInternal_int( object );" );
		else
			sourceWriter.println( "return (OUT) getValueInternal_Object( object );" );
		sourceWriter.outdent();
		sourceWriter.println( "}" );
		sourceWriter.println( "" );

		sourceWriter.println( "@Override public native final void copyValueTo( Object source, Object destination )" );
		sourceWriter.println( "/*-{" );
		sourceWriter.indent();
		sourceWriter.println( "destination.@" + reflectedTypeName + "::" + field.getName() + " = source.@" + reflectedTypeName + "::" + field.getName() + ";" );
		sourceWriter.outdent();
		sourceWriter.println( "}-*/;" );
		sourceWriter.println( "" );

		sourceWriter.println( "private native final void setValueInternal_int( Object object, int value )" );
		sourceWriter.println( "/*-{" );
		sourceWriter.indent();
		sourceWriter.println( "object.@" + reflectedTypeName + "::" + field.getName() + " = value;" );
		sourceWriter.outdent();
		sourceWriter.println( "}-*/;" );
		sourceWriter.println( "" );
		sourceWriter.println( "private native final int getValueInternal_int( Object object )" );
		sourceWriter.println( "/*-{" );
		sourceWriter.indent();
		sourceWriter.println( "return object.@" + reflectedTypeName + "::" + field.getName() + ";" );
		sourceWriter.outdent();
		sourceWriter.println( "}-*/;" );
		sourceWriter.println( "" );
		sourceWriter.println( "private native final void setValueInternal_Object( Object object, Object value )" );
		sourceWriter.println( "/*-{" );
		sourceWriter.indent();
		sourceWriter.println( "object.@" + reflectedTypeName + "::" + field.getName() + " = value;" );
		sourceWriter.outdent();
		sourceWriter.println( "}-*/;" );
		sourceWriter.println( "" );
		sourceWriter.println( "private native final Object getValueInternal_Object( Object object )" );
		sourceWriter.println( "/*-{" );
		sourceWriter.indent();
		sourceWriter.println( "return object.@" + reflectedTypeName + "::" + field.getName() + ";" );
		sourceWriter.outdent();
		sourceWriter.println( "}-*/;" );
		sourceWriter.outdent();
		sourceWriter.println( "}" );
		sourceWriter.println( "" );
	}

	private void generateMethodClass( String methodClassName, JMethod method, SourceWriter sourceWriter )
	{
		JParameter[] params = method.getParameters();

		StringBuilder sb = new StringBuilder();
		sb.append( "new Class<?>[] {" );
		for( int p = 0; p < params.length; p++ )
		{
			if( p > 0 )
				sb.append( ", " );
			sb.append( params[p].getType().getQualifiedSourceName() );
			sb.append( ".class" );
		}
		sb.append( "}" );

		sourceWriter.println( "class " + methodClassName + " extends com.hexa.client.classinfo.internal.MethodBase {" );
		sourceWriter.indent();
		sourceWriter.println( "public " + methodClassName + "()" );
		sourceWriter.println( "{" );
		sourceWriter.indent();
		sourceWriter.println( "super(" + method.getReturnType().getErasedType().getQualifiedSourceName() + ".class, \"" + method.getName() + "\", "
				+ sb.toString() + ");" );
		sourceWriter.outdent();
		sourceWriter.println( "}" );
		sourceWriter.println( "" );

		sourceWriter.println( "@Override" );
		sourceWriter.println( "public Object call( Object target, Object[] parameters )" );
		sourceWriter.println( "{" );
		sourceWriter.indent();
		if( method.isPrivate() )
		{
			sourceWriter.println( "throw new java.lang.RuntimeException(\"CANNOT CALL PRIVATE METHOD " + method.getName() + "\" );" );
		}
		else
		{
			sourceWriter.println( "try {" );
			sourceWriter.indent();

			sb = new StringBuilder();
			if( method.getReturnType().getSimpleSourceName().equals( "void" ) )
				sb.append( "((" + reflectedType.getQualifiedSourceName() + ") target)." + method.getName() + "(" );
			else
				sb.append( "return (Object) ((" + reflectedType.getQualifiedSourceName() + ") target)." + method.getName() + "(" );
			for( int p = 0; p < params.length; p++ )
			{
				if( p > 0 )
					sb.append( ", " );
				JPrimitiveType primitive = params[p].getType().isPrimitive();
				if( primitive != null )
				{
					String boxedCast = primitive.getQualifiedBoxedSourceName();
					sb.append( "(" + params[p].getType().getQualifiedSourceName() + ") (" + boxedCast + ") parameters[" + p + "]" );
				}
				else
				{
					sb.append( "(" + params[p].getType().getQualifiedSourceName() + ") parameters[" + p + "]" );
				}
			}
			sb.append( ");" );
			sourceWriter.println( sb.toString() );
			if( method.getReturnType().getSimpleSourceName().equals( "void" ) )
				sourceWriter.println( "return null;" );

			sourceWriter.outdent();
			sourceWriter.println( "} catch( Throwable e ) {" );
			sourceWriter.indent();
			sourceWriter.println( "throw new java.lang.RuntimeException(\"CALLED METHOD RAISED AN EXCEPTION " + method.getName() + "\" );" );
			sourceWriter.outdent();
			sourceWriter.println( "}" );
		}
		sourceWriter.outdent();
		sourceWriter.println( "}" );
		sourceWriter.println( "" );

		sourceWriter.outdent();
		sourceWriter.println( "}" );
		sourceWriter.println( "" );
	}
}
