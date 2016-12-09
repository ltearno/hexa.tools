package fr.lteconsulting.hexa.rebind.classinfo;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import fr.lteconsulting.hexa.classinfo.gwt.ClazzBundle;
import fr.lteconsulting.hexa.classinfo.gwt.ReflectedClasses;

public class ClazzBundleGenerator extends Generator
{
	// Context and logger for code generation
	TreeLogger logger;
	GeneratorContext context;
	TypeOracle typeOracle;

	// asked type name
	String askedTypeName;

	// type info on the asked class
	JClassType askedType;
	Set<JType> introspectedTypes;

	JMethod registerMethod;

	// package of the asked type
	String packageName;

	// generated class name
	String generatedClassName;

	// generated class name + package
	String fullGeneratedClassName;

	@Override
	public String generate( TreeLogger logger, GeneratorContext context, String typeName ) throws UnableToCompleteException
	{
		// We have already processed all the class bundles
		if(fullGeneratedClassName != null) {
			return fullGeneratedClassName;
		}
		this.logger = logger;
		this.context = context;
		this.askedTypeName = typeName;

		// get the "reflection" machine of GWT compiler
		typeOracle = context.getTypeOracle();
		try {
			// get classType and save instance variables
			askedType = typeOracle.getType( typeName );

			List<JClassType> clazzBundles = getClazzBundles();
			clazzBundles.add(askedType);

			introspectedTypes = new HashSet<JType>();
			// Ensure only one method exists
			if(askedType.getMethods().length > 1) {
				logger.log(TreeLogger.Type.WARN, "You should only have 1 method " +
					"registering the class information, the first method will be used.");
			}
			registerMethod = askedType.getMethods()[0];

			for(JClassType bundle : clazzBundles) {
				// All introspected classes will be combined
				if(bundle.getMethods().length > 0) {
					JMethod method = bundle.getMethods()[0];

					// list all return types of all methods
					ReflectedClasses classes = method.getAnnotation(ReflectedClasses.class);
					if (classes == null || classes.classes() == null || classes.classes().length == 0)
						continue;

					for (Class<?> clazz : classes.classes()) {
						JType classType = typeOracle.getType(clazz.getName());
						if (classType != null)
							introspectedTypes.add(classType);
					}
				}
			}

			// Generation information
			packageName = askedType.getPackage().getName();
			generatedClassName = askedType.getSimpleSourceName() + "ClazzBundleImpl";

			// Generate class source code
			generateClass();
		}
		catch(Exception e) {
			// record to logger that Map generation threw an exception
			logger.log( TreeLogger.ERROR, "ERROR when generating " + generatedClassName + " for " + typeName, e );
		}

		// return the fully qualifed name of the class generated
		fullGeneratedClassName = packageName + "." + generatedClassName;
		return fullGeneratedClassName;
	}

	private List<JClassType> getClazzBundles() {
		List<JClassType> clazzBundles = new ArrayList<>();

		// Generate all other class bundles
		JClassType[] types = typeOracle.getTypes();
		for(JClassType type : types) {
			if(TypeHelper.isInstanceOf(type, ClazzBundle.class)) {
				clazzBundles.add(type);
			}
		}
		return clazzBundles;
	}

	private void generateClass()
	{
		// get print writer that receives the source code
		PrintWriter printWriter;

		printWriter = context.tryCreate( logger, packageName, generatedClassName );
		// print writer if null, source code has ALREADY been generated, return
		if( printWriter == null )
			return;

		// init composer, set class properties, create source writer
		ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory( packageName, generatedClassName );

		// output a class "typeName" + "Impl"
		// which extends the asked type
		composer.addImplementedInterface( askedType.getParameterizedQualifiedSourceName() );

		composer.addImport( "fr.lteconsulting.hexa.classinfo.Clazz" );
		composer.addImport( "fr.lteconsulting.hexa.classinfo.ClassInfo" );
		composer.addImport( "com.google.gwt.core.shared.GWT" );

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

		for( JType type : introspectedTypes )
		{
			String interfaceName = "Clazz_" + type.getQualifiedSourceName().replaceAll( "\\.", "_" );
			sourceWriter.println( "public interface " + interfaceName + " extends Clazz<" + type.getQualifiedSourceName() + "> {}" );
		}
		sourceWriter.println( "" );

		sourceWriter.println( "public void "+registerMethod.getName()+"()" );
		sourceWriter.println( "{" );
		sourceWriter.indent();
		for(JType type : introspectedTypes) {
			String interfaceName = "Clazz_" + type.getQualifiedSourceName().replaceAll( "\\.", "_" );
			sourceWriter.println("ClassInfo.RegisterClazz( (Clazz<?>) GWT.create( " + interfaceName + ".class ) );" );
		}
		sourceWriter.outdent();
		sourceWriter.println( "}" );
		sourceWriter.println( "" );
	}
}
