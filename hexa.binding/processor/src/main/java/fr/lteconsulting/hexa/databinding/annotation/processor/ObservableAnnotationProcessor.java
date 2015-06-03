package fr.lteconsulting.hexa.databinding.annotation.processor;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

import fr.lteconsulting.hexa.databinding.annotation.Observable;
import fr.lteconsulting.hexa.databinding.annotation.ObservableGwt;

@SupportedAnnotationTypes( { "fr.lteconsulting.hexa.databinding.annotation.Observable", 
	"fr.lteconsulting.hexa.databinding.annotation.ObservableGwt" } )
@SupportedSourceVersion( SourceVersion.RELEASE_7 )
public class ObservableAnnotationProcessor extends AbstractProcessor
{
	private Elements elementUtils;
	private Filer filer;
	private Types types;
	private TypeSimplifier typeSimplifier;
	private Messager msg;

	private static final String TEMPLATE_NAME = "fr/lteconsulting/hexa/databinding/annotation/processor/TemplateClass.txt";
	private static final String GWT_TEMPLATE_NAME = "fr/lteconsulting/hexa/databinding/annotation/processor/TemplateClassGwt.txt";

	@Override
	public synchronized void init( ProcessingEnvironment processingEnv )
	{
		super.init( processingEnv );

		elementUtils = processingEnv.getElementUtils();
		filer = processingEnv.getFiler();
		types = processingEnv.getTypeUtils();
		typeSimplifier = new TypeSimplifier( types );
		msg = processingEnv.getMessager();
	}

	@Override
	public boolean process( Set<? extends TypeElement> arg0, RoundEnvironment roundEnv )
	{
		if( !roundEnv.processingOver() )
		{
			for( final TypeElement annotatedElement : ElementFilter.typesIn( roundEnv.getElementsAnnotatedWith( Observable.class ) ) )
				createObservableFor( annotatedElement, TEMPLATE_NAME );

			for( final TypeElement annotatedElement : ElementFilter.typesIn( roundEnv.getElementsAnnotatedWith( ObservableGwt.class ) ) )
				createObservableFor( annotatedElement, GWT_TEMPLATE_NAME );
		}
		return true;
	}

	private void createObservableFor( TypeElement sourceType, String templateName )
	{
		String pkgName = elementUtils.getPackageOf( sourceType ).getQualifiedName().toString();

		String sourceTypeName = sourceType.getSimpleName().toString();

		String targetTypeName;
		String SUFFIX = "Internal";
		if( sourceTypeName.endsWith( SUFFIX ) )
			targetTypeName = capitalizeFirstLetter( sourceTypeName ).substring( 0, sourceTypeName.length() - SUFFIX.length() );
		else
			targetTypeName = "Observable" + capitalizeFirstLetter( sourceTypeName );

		Template targetClass = Template.fromResource( templateName, 0 ).replace( "$SourceClassFqn", sourceType.getQualifiedName().toString() ).replace( "$SourceClassName", sourceType.getSimpleName().toString() + TypeSimplifier.actualTypeParametersString( sourceType ) )
				.replace( "$PackageName", pkgName ).replace( "$TargetClassNameParametrized", targetTypeName + typeSimplifier.formalTypeParametersString( sourceType ) ).replace( "$TargetClassName", targetTypeName );

		try
		{
			JavaFileObject jfo = filer.createSourceFile( pkgName + "." + targetTypeName );
			Writer writer = jfo.openWriter();

			targetClass.replace( "$Constructors", processConstructors( targetTypeName, sourceType, templateName ) );

			targetClass.replace( "$FieldsAndMethods", processFieldsAndMethods( sourceType, templateName ) );

			writer.write( targetClass.toString() );

			writer.close();
		}
		catch( IOException e )
		{
			e.printStackTrace();
			error( e.getMessage() );
		}
	}

	private String processConstructors( String targetTypeName, TypeElement factoredType, String templateName )
	{
		StringBuilder result = new StringBuilder();

		for( ExecutableElement constr : ElementFilter.constructorsIn( factoredType.getEnclosedElements() ) )
		{
			if( !constr.getModifiers().contains( Modifier.PUBLIC ) )
				continue;

			Template ctr = Template.fromResource( templateName, 1 ).replace( "$TargetClassName", targetTypeName );

			StringBuilder formalParameters = new StringBuilder();
			boolean addComa = false;
			for( VariableElement parameter : constr.getParameters() )
			{
				String paramTypeName = Utils.getTypeQualifiedName( parameter.asType() );

				if( addComa )
					formalParameters.append( ", " );
				else
					addComa = true;

				formalParameters.append( paramTypeName );
				formalParameters.append( " " );
				formalParameters.append( parameter.getSimpleName() );
			}
			ctr.replace( "$FormalParameters", formalParameters.toString() );

			StringBuilder parameters = new StringBuilder();
			addComa = false;
			for( VariableElement parameter : constr.getParameters() )
			{
				if( addComa )
					parameters.append( ", " );
				else
					addComa = true;
				parameters.append( parameter.getSimpleName() );
			}
			ctr.replace( "$Parameters", parameters.toString() );

			result.append( ctr.toString() );
		}

		return result.toString();
	}

	private String processFieldsAndMethods( TypeElement factoredType, String templateName )
	{
		Set<String> settersDone = new HashSet<>();
		Set<String> gettersDone = new HashSet<>();

		String methods = processMethods( factoredType, settersDone, gettersDone, templateName );

		String fields = processFields( factoredType, settersDone, gettersDone, templateName );

		return methods + fields;
	}

	private String processMethods( TypeElement factoredType, Set<String> settersDone, Set<String> gettersDone, String templateName )
	{
		StringBuilder result = new StringBuilder();

		for( ExecutableElement method : ElementFilter.methodsIn( factoredType.getEnclosedElements() ) )
		{
			String methodName = method.getSimpleName().toString();

			if( method.getModifiers().contains( Modifier.PRIVATE ) )
				continue;

			String propertyName = lowerFirstLetter( methodName.substring( 3 ) );

			if( methodName.startsWith( "set" ) )
			{
				if( method.getModifiers().contains( Modifier.FINAL ) )
					continue;

				Template setter = Template.fromResource( templateName, 3 );

				StringBuilder modifiersBuilder = new StringBuilder();
				for( Modifier m : method.getModifiers() )
					modifiersBuilder.append( m.toString() );
				setter.replace( "$Modifiers", modifiersBuilder.toString() );

				setter.replace( "$MethodName", methodName );
				setter.replace( "$PropertyClass", Utils.getTypeQualifiedName( method.getParameters().get( 0 ).asType() ) );
				setter.replace( "$Property", propertyName );

				result.append( setter.toString() );
				settersDone.add( propertyName );
			}
			else if( methodName.startsWith( "get" ) )
			{
				gettersDone.add( propertyName );
			}
		}

		return result.toString();
	}

	private String processFields( TypeElement factoredType, Set<String> settersDone, Set<String> gettersDone, String templateName )
	{
		StringBuilder result = new StringBuilder();

		for( VariableElement field : ElementFilter.fieldsIn( factoredType.getEnclosedElements() ) )
		{
			if( field.getModifiers().contains( Modifier.PRIVATE ) )
				continue;

			String propertyName = field.getSimpleName().toString();
			String fieldTypeName = field.asType().toString();

			if( !settersDone.contains( propertyName ) )
			{
				String methodName = "set" + capitalizeFirstLetter( propertyName );
				
				if(fieldTypeName.contains( "<any>" ))
					msg.printMessage( Kind.ERROR, "Parametrizing a generated type is impossible ! This problem is being investigated... Stay tuned....", field );

				Template setter = Template.fromResource( templateName, 4 );
				setter.replace( "$Modifiers", "public" );
				setter.replace( "$MethodName", methodName );
				setter.replace( "$PropertyClass", fieldTypeName );
				setter.replace( "$Property", propertyName );

				result.append( setter.toString() );

				settersDone.add( propertyName );
			}

			if( !gettersDone.contains( propertyName ) )
			{
				String methodName = "get" + capitalizeFirstLetter( propertyName );
				
				if(fieldTypeName.contains( "<any>" ))
					msg.printMessage( Kind.ERROR, "Parametrizing a generated type is impossible ! This problem is being investigated... Stay tuned....", field );

				Template getter = Template.fromResource( templateName, 2 );
				getter.replace( "$PropertyClass", fieldTypeName );
				getter.replace( "$MethodName", methodName );
				getter.replace( "$Property", propertyName );

				result.append( getter.toString() );

				gettersDone.add( propertyName );
			}
		}

		return result.toString();
	}

	private void error( String msg )
	{
		processingEnv.getMessager().printMessage( Kind.ERROR, msg );
	}

	private static String lowerFirstLetter( String s )
	{
		return s.substring( 0, 1 ).toLowerCase() + s.substring( 1 );
	}

	private static String capitalizeFirstLetter( String s )
	{
		return s.substring( 0, 1 ).toUpperCase() + s.substring( 1 );
	}
}
