package fr.lteconsulting.hexa.databinding.annotation.processor;

import fr.lteconsulting.hexa.databinding.annotation.Observable;

import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
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
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

import static fr.lteconsulting.hexa.databinding.annotation.processor.Tags.*;

@SupportedAnnotationTypes({"fr.lteconsulting.hexa.databinding.annotation.Observable"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class ObservableAnnotationProcessor extends AbstractProcessor {

	static String[] getterPrefixes = new String[] {
		"get", "is"
	};

	static String[] setterPrefixes = new String[] {
		"set"
	};

	protected Elements elementUtils;
	protected Filer filer;
	protected Types types;
	protected TypeSimplifier typeSimplifier;
	protected Messager msg;

	protected String getTemplateName() {
		return "fr/lteconsulting/hexa/databinding/annotation/processor/TemplateClass.txt";
	}

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
	@SuppressWarnings("unchecked")
	public boolean process( Set<? extends TypeElement> arg0, RoundEnvironment roundEnv )
	{
		if( !roundEnv.processingOver() )
		{
			for(String supported : getSupportedAnnotationTypes()) {
				try {
					Class<? extends Annotation> annotationClazz = (Class<? extends Annotation>)Class.forName(supported);
					for (final TypeElement annotatedElement : ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(annotationClazz))) {
						Annotation annotation = annotatedElement.getAnnotation(annotationClazz);
						createObservableFor(annotatedElement, getTemplateName(), inheritFromSuperClassDepth(annotation));
					}
				}
				catch (ClassNotFoundException | ClassCastException ex) {
					ex.printStackTrace();
				}
			}
		}
		return true;
	}

	protected int inheritFromSuperClassDepth(Annotation annotation) {
		if(annotation instanceof Observable) {
			Observable observable = (Observable)annotation;
			int depth = observable.inherit() ? 1 : 0;
			return observable.inheritDepth() > depth ? observable.inheritDepth() : depth;
		}
		return -1;
	}

	protected String[] getExtraImports() {
		return null;
	}

	protected String getPreConstructor(String pkgName, String targetTypeName, TypeElement sourceType) {
		return "";
	}

	private void createObservableFor( TypeElement sourceType, String templateName, int inheritDepth )
	{
		String pkgName = elementUtils.getPackageOf( sourceType ).getQualifiedName().toString();

		String sourceTypeName = sourceType.getSimpleName().toString();

		String targetTypeName;
		String SUFFIX = "Internal";
		if( sourceTypeName.endsWith( SUFFIX ) )
			targetTypeName = capitalizeFirstLetter( sourceTypeName )
				.substring(0, sourceTypeName.length() - SUFFIX.length());
		else
			targetTypeName = "Observable" + capitalizeFirstLetter( sourceTypeName );

		Template targetClass = Template.fromResource( templateName, 0 )
			.replace(SOURCE_CLASS_FQN, sourceType.getQualifiedName().toString())
			.replace(SOURCE_CLASS_NAME, sourceType.getSimpleName().toString() + TypeSimplifier.actualTypeParametersString(sourceType))
			.replace(PACKAGE_NAME, pkgName).replace(TARGET_CLASS_PARAMETRIZED, targetTypeName + typeSimplifier.formalTypeParametersString(sourceType))
			.replace(TARGET_CLASS_NAME, targetTypeName);

		String[] extraImports = getExtraImports();
		if(extraImports != null) {
			StringBuilder importBuilder = new StringBuilder();
			for(int i = 0; i < extraImports.length; ++i) {
				String extraImport = extraImports[i];
				if(!extraImport.startsWith("import ")) {
					extraImport = "import " + extraImport;
				}
				if(!extraImport.endsWith(";")) {
					extraImport += ";";
				}
				importBuilder.append(extraImport);

				if(i < extraImports.length-1) {
					importBuilder.append("\n");
				}
			}
			targetClass.replace(EXTRA_IMPORTS, importBuilder.toString());
		} else {
			targetClass.replace(EXTRA_IMPORTS, "");
		}

		try
		{
			JavaFileObject jfo = filer.createSourceFile( pkgName + "." + targetTypeName );
			Writer writer = jfo.openWriter();

			targetClass.replace(PRE_CONSTRUCTOR, getPreConstructor(pkgName, targetTypeName, sourceType));
			targetClass.replace(CONSTRUCTORS, processConstructors(targetTypeName, sourceType, templateName, inheritDepth));
			targetClass.replace(FIELDS_AND_METHODS, processFieldsAndMethods(sourceType, templateName, inheritDepth));

			writer.write( targetClass.toString() );
			writer.close();
		}
		catch( IOException e )
		{
			e.printStackTrace();
			error( e.getMessage() );
		}
	}

	private String processConstructors( String targetTypeName, TypeElement factoredType, String templateName, int inheritDepth )
	{
		StringBuilder result = new StringBuilder();

		for( ExecutableElement constr : ElementFilter.constructorsIn( factoredType.getEnclosedElements() ) )
		{
			if( !constr.getModifiers().contains( Modifier.PUBLIC ) )
				continue;

			Template ctr = Template.fromResource( templateName, 1 ).replace( TARGET_CLASS_NAME, targetTypeName );

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
			ctr.replace( FORMAL_PARAMETERS, formalParameters.toString() );

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
			ctr.replace( PARAMETERS, parameters.toString() );

			result.append( ctr.toString() );
		}

		Template ctr = Template.fromResource(templateName, 2).replace(TARGET_CLASS_NAME, targetTypeName);
		ctr.replace(SOURCE_CLASS_NAME, factoredType.getSimpleName().toString());
		ctr.replace(MAP_FIELDS, processConstructorFieldMapping(targetTypeName, factoredType, templateName, inheritDepth));

		result.append(ctr.toString());
		return result.toString();
	}

	private String processConstructorFieldMapping(String targetTypeName, TypeElement factoredType,
												  String templateName, int inheritDepth) {
		StringBuilder fields = new StringBuilder();
		boolean indent = false;
		for( VariableElement field : ElementFilter.fieldsIn( factoredType.getEnclosedElements() ) ) {
			String propertyName = field.getSimpleName().toString();
			String setMethod = "set" + capitalizeFirstLetter( propertyName );

			if (field.getModifiers().contains(Modifier.PRIVATE)) {
				// Ensure the method exists for field
				ExecutableElement getterMethod = getPropertyGetterMethod(factoredType, propertyName);
				if (getterMethod != null) {
					if(indent){ fields.append("\n\t\t"); } else { indent = true; }
					String methodName = getterMethod.getSimpleName().toString();
					fields.append(setMethod).append("(").append("o.").append(methodName).append("()").append(")").append(";");
				}
			}
			else {
				if(indent){ fields.append("\n\t\t"); } else { indent = true; }
				fields.append("this.").append(propertyName).append(" = ").append("o.").append(propertyName).append(";");
			}
		}

		if(inheritDepth > 0) {
			// Process the superclass
			TypeMirror superClassTypeMirror = factoredType.getSuperclass();
			if (superClassTypeMirror instanceof DeclaredType) {
				TypeElement superClassTypeElement = (TypeElement)((DeclaredType)
						superClassTypeMirror).asElement();

				if (!superClassTypeElement.getQualifiedName().toString().equals("java.lang.Object")) {
					fields.append("\n\t\t").append(processConstructorFieldMapping(targetTypeName,
						superClassTypeElement, templateName, inheritDepth-1));
				}
			}
		}
		return fields.toString();
	}

	private String processFieldsAndMethods( TypeElement factoredType, String templateName, int inheritDepth ) {
		Set<String> settersDone = new HashSet<>();
		Set<String> gettersDone = new HashSet<>();

		String methods = processMethods(factoredType, settersDone, gettersDone, templateName, inheritDepth);
		String fields = processFields(factoredType, settersDone, gettersDone, templateName, inheritDepth);

		return methods + fields;
	}

	/**
	 * Check if the method can be used to set or get.
	 */
	private String processMethods( TypeElement factoredType, Set<String> settersDone, Set<String> gettersDone,
								   String templateName, int inheritDepth ) {
		StringBuilder result = new StringBuilder();

		for( ExecutableElement method : ElementFilter.methodsIn(factoredType.getEnclosedElements()) ) {
			String methodName = method.getSimpleName().toString();

			if( method.getModifiers().contains(Modifier.PRIVATE) )
				continue;

			String propertyName = lowerFirstLetter(methodName.substring(3));

			if( methodName.startsWith( "set" ) ) {
				if( method.getModifiers().contains( Modifier.FINAL ) )
					continue;

				Template setter = Template.fromResource( templateName, 4 );

				StringBuilder modifiersBuilder = new StringBuilder();
				for( Modifier m : method.getModifiers() ) {
					modifiersBuilder.append(m.toString());
				}
				setter.replace( MODIFIERS, modifiersBuilder.toString() );

				setter.replace( METHOD_NAME, methodName );
				setter.replace( PROPERTY_CLASS, Utils.getTypeQualifiedName( method.getParameters().get( 0 ).asType() ) );
				setter.replace( PROPERTY, propertyName );

				result.append( setter.toString() );
				settersDone.add( propertyName );
			}
			else if(methodName.startsWith( "get" )) {
				gettersDone.add(propertyName);
			}
		}

		if(inheritDepth > 0) {
			// Process the superclass
			TypeMirror superClassTypeMirror = factoredType.getSuperclass();
			if (superClassTypeMirror instanceof DeclaredType) {
				TypeElement superClassTypeElement = (TypeElement) ((DeclaredType)
						superClassTypeMirror).asElement();

				if (!superClassTypeElement.getQualifiedName().toString().equals("java.lang.Object")) {
					result.append(processMethods(superClassTypeElement, settersDone, gettersDone, templateName, inheritDepth-1));
				}
			}
		}

		return result.toString();
	}

	/**
	 * Check if the field can be used to set or get.
	 */
	private String processFields( TypeElement factoredType, Set<String> settersDone, Set<String> gettersDone,
								  String templateName, int inheritDepth )
	{
		StringBuilder result = new StringBuilder();

		for( VariableElement field : ElementFilter.fieldsIn(factoredType.getEnclosedElements()) )
		{
			if( field.getModifiers().contains( Modifier.PRIVATE ) ) {
				continue;
			}

			String propertyName = field.getSimpleName().toString();

			String fieldTypeName = field.asType().toString();
			if( !settersDone.contains( propertyName ) )
			{
				String methodName = "set" + capitalizeFirstLetter( propertyName );

				if(fieldTypeName.contains( "<any>" ))
					msg.printMessage( Kind.ERROR, "Parametrizing a generated type is impossible ! This problem is being investigated... Stay tuned....", field );

				Template setter = Template.fromResource( templateName, 5 );
				setter.replace( MODIFIERS, "public" );

				setter.replace( METHOD_NAME, methodName );
				setter.replace( PROPERTY_CLASS, fieldTypeName );
				setter.replace( PROPERTY, propertyName );

				result.append( setter.toString() );
				settersDone.add( propertyName );
			}

			if( !gettersDone.contains( propertyName ) ) {
				String methodName = "get" + capitalizeFirstLetter( propertyName );
				result.append( createGetterStub(methodName, propertyName, fieldTypeName, templateName) );
				gettersDone.add( propertyName );
			}
		}

		if(inheritDepth > 0) {
			// Process the superclass
			TypeMirror superClassTypeMirror = factoredType.getSuperclass();
			if (superClassTypeMirror instanceof DeclaredType) {
				TypeElement superClassTypeElement = (TypeElement) ((DeclaredType)
						superClassTypeMirror).asElement();

				if (!superClassTypeElement.getQualifiedName().toString().equals("java.lang.Object")) {
					result.append(processFields(superClassTypeElement, settersDone, gettersDone, templateName, inheritDepth-1));
				}
			}
		}
		return result.toString();
	}

	private String createGetterStub(String methodName, String propertyResult, String fieldTypeName, String templateName) {
		if(fieldTypeName.contains( "<any>" )) {
			msg.printMessage(Kind.ERROR, "Parametrizing a generated type is impossible!" +
				" This problem is being investigated... Stay tuned...." + methodName);
		}

		Template getter = Template.fromResource( templateName, 3 );
		getter.replace( PROPERTY_CLASS, fieldTypeName );
		getter.replace( METHOD_NAME, methodName );
		getter.replace( PROPERTY, propertyResult );

		return getter.toString();
	}

	private ExecutableElement getPropertySetterMethod(TypeElement factoredType, String propertyName) {
		return getMethodForProperty(factoredType, propertyName, setterPrefixes);
	}

	private ExecutableElement getPropertyGetterMethod(TypeElement factoredType, String propertyName) {
		return getMethodForProperty(factoredType, propertyName, getterPrefixes);
	}

	private ExecutableElement getMethodForProperty(TypeElement factoredType, String propertyName, String... startsWith) {
		for( ExecutableElement method : ElementFilter.methodsIn(factoredType.getEnclosedElements()) ) {
			String methodName = method.getSimpleName().toString();

			if (method.getModifiers().contains(Modifier.PRIVATE))
				continue;

			for(String prefix : startsWith) {
				if (methodName.startsWith(prefix)) {
					String propName = lowerFirstLetter(methodName.substring(prefix.length()));
					if (!propName.equals(propertyName) || method.getModifiers().contains(Modifier.FINAL))
						continue;

					return method;
				}
			}
		}
		return null;
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
