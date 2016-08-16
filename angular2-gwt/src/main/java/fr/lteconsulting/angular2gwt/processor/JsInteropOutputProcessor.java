package fr.lteconsulting.angular2gwt.processor;

import static fr.lteconsulting.angular2gwt.processor.AngularComponentProcessor.COMPONENT_HELPER_CLASS_SUFFIX;
import static fr.lteconsulting.angular2gwt.processor.AngularComponentProcessor.ComponentAnnotationFqn;
import static fr.lteconsulting.angular2gwt.processor.AngularComponentProcessor.DIRECTIVE_HELPER_CLASS_SUFFIX;
import static fr.lteconsulting.angular2gwt.processor.AngularComponentProcessor.DirectiveAnnotationFqn;
import static fr.lteconsulting.angular2gwt.processor.AngularComponentProcessor.INJECTABLE_HELPER_CLASS_SUFFIX;
import static fr.lteconsulting.angular2gwt.processor.AngularComponentProcessor.InjectableAnnotationFqn;
import static fr.lteconsulting.angular2gwt.processor.AngularComponentProcessor.NG_MODULE_HELPER_CLASS_SUFFIX;
import static fr.lteconsulting.angular2gwt.processor.AngularComponentProcessor.NgModuleAnnotationFqn;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.SimpleAnnotationValueVisitor8;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

import fr.lteconsulting.angular2gwt.Component;
import fr.lteconsulting.angular2gwt.Directive;
import fr.lteconsulting.angular2gwt.Hosts;
import fr.lteconsulting.angular2gwt.Injectable;
import fr.lteconsulting.angular2gwt.Input;
import fr.lteconsulting.angular2gwt.NgModule;
import fr.lteconsulting.angular2gwt.Output;
import fr.lteconsulting.roaster.Block;
import fr.lteconsulting.roaster.JavaClassText;
import jsinterop.annotations.JsType;

public class JsInteropOutputProcessor {
	private final ProcessingEnvironment processingEnv;

	public JsInteropOutputProcessor(ProcessingEnvironment processingEnv) {
		this.processingEnv = processingEnv;
	}

	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		for (TypeElement element : ElementFilter.typesIn(roundEnv
				.getElementsAnnotatedWith(processingEnv.getElementUtils().getTypeElement(NgModuleAnnotationFqn)))) {
			processModule(element);
		}

		for (TypeElement element : ElementFilter.typesIn(roundEnv
				.getElementsAnnotatedWith(processingEnv.getElementUtils().getTypeElement(ComponentAnnotationFqn)))) {
			processComponent(element);
		}

		for (TypeElement element : ElementFilter.typesIn(roundEnv
				.getElementsAnnotatedWith(processingEnv.getElementUtils().getTypeElement(DirectiveAnnotationFqn)))) {
			processDirective(element);
		}

		for (TypeElement element : ElementFilter.typesIn(roundEnv
				.getElementsAnnotatedWith(processingEnv.getElementUtils().getTypeElement(InjectableAnnotationFqn)))) {
			processInjectable(element);
		}

		roundEnv.errorRaised();

		return true;
	}
	
	
	private void processModule(TypeElement element) {
		String packageName = ((PackageElement) element.getEnclosingElement()).getQualifiedName().toString();
		String angularModuleName = element.getSimpleName() + NG_MODULE_HELPER_CLASS_SUFFIX;
		
		HashMap<String,String> generatedAccessorTypestypes = new HashMap<>();
		
		JavaClassText javaClassText = new JavaClassText(packageName);
		
		javaClassText.addImport("jsinterop.annotations.JsProperty");
		javaClassText.addImport("fr.lteconsulting.angular2gwt.client.JsArray");
		javaClassText.addImport("fr.lteconsulting.angular2gwt.client.interop.angular.AngularComponentConstructorFunction");
		javaClassText.addImport("fr.lteconsulting.angular2gwt.client.interop.angular.NgModule");
		javaClassText.addImport("fr.lteconsulting.angular2gwt.client.interop.angular.NgModuleMetadata");
		javaClassText.addImport( "fr.lteconsulting.angular2gwt.client.JsToolsInjector" );
		
		Block classBlock = javaClassText.rootBlock().clazz(angularModuleName);

		// imports, exports, declarations, providers, bootstrap
		String imports = findModuleAttributes( element, "imports", classBlock, generatedAccessorTypestypes );
		String exports = findModuleAttributes( element, "exports", classBlock, generatedAccessorTypestypes );
		String providers = findModuleAttributes( element, "providers", classBlock, generatedAccessorTypestypes );
		String declarations = findModuleAttributes( element, "declarations", classBlock, generatedAccessorTypestypes );
		String entryComponents = findModuleAttributes( element, "entryComponents", classBlock, generatedAccessorTypestypes );
		String bootstrap = findModuleAttributes( element, "bootstrap", classBlock, generatedAccessorTypestypes );
		
		classBlock.line("@JsProperty( namespace = [{#}], name = [{#}] )", packageName, element.getSimpleName());
		classBlock.line("private native static AngularComponentConstructorFunction constructorFunction();");
		classBlock.line();

		classBlock.line("public static Object getComponentPrototype()");
		classBlock.block((e) -> {
			e.line( "JsToolsInjector.inject();" );
			e.separator();
			
			e.line("AngularComponentConstructorFunction constructorFunction = constructorFunction();");
			
			e.separator();
			
			e.line("if( constructorFunction.annotations == null )").block((i) -> {
				i.line("NgModuleMetadata metadata = new NgModuleMetadata();");
				i.separator();
				if( imports != null )
					i.line( "metadata.imports = [{}];", imports );
				if( exports != null )
					i.line( "metadata.exports = [{}];", exports );
				if( declarations != null )
					i.line( "metadata.declarations = [{}];", declarations );
				if( providers != null )
					i.line( "metadata._providers = [{}];", providers );
				if( bootstrap != null )
					i.line( "metadata.bootstrap = [{}];", bootstrap );
				if( entryComponents != null )
					i.line( "metadata.entryComponents = [{}];", entryComponents );
				i.separator();
				i.line("constructorFunction.annotations = JsArray.of( new NgModule( metadata ) );");
			});
			
			e.separator();
			
			e.line("return constructorFunction;");
		});

		try {
			String targetClassFqn = packageName + "." + angularModuleName;
			JavaFileObject jfo = processingEnv.getFiler().createSourceFile(targetClassFqn, element);

			OutputStream os = jfo.openOutputStream();
			OutputStreamWriter pw = new OutputStreamWriter(os, "UTF-8");
			StringBuilder sb = new StringBuilder();
			javaClassText.render(sb);
			pw.write(sb.toString());
			pw.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			processingEnv.getMessager().printMessage(Kind.ERROR, "AngularModule not generated !" + e, element);
		}
	}
	
	

	private void processComponent(TypeElement element) {
		String packageName = ((PackageElement) element.getEnclosingElement()).getQualifiedName().toString();
		String angularComponentName = element.getSimpleName() + COMPONENT_HELPER_CLASS_SUFFIX;

		Component annotation = element.getAnnotation(Component.class);
		
		HashMap<String,String> generatedAccessorTypestypes = new HashMap<>();
		
		JavaClassText javaClassText = new JavaClassText(packageName);
		
		javaClassText.addImport("jsinterop.annotations.JsProperty");
		javaClassText.addImport("fr.lteconsulting.angular2gwt.client.JsArray");
		javaClassText.addImport("fr.lteconsulting.angular2gwt.client.interop.angular.AngularComponentConstructorFunction");
		javaClassText.addImport("fr.lteconsulting.angular2gwt.client.interop.angular.Component");
		javaClassText.addImport("fr.lteconsulting.angular2gwt.client.interop.angular.ComponentMetadata");
		javaClassText.addImport( "fr.lteconsulting.angular2gwt.client.JsToolsInjector" );
		
		Block classBlock = javaClassText.rootBlock().clazz(angularComponentName);

		String aSelector = annotation.selector();
		String aTemplate = annotation.template().isEmpty() ? null : annotation.template();
		String aTemplateUrl = annotation.templateUrl().isEmpty() ? null : annotation.templateUrl();
		String aStyles = findComponentStyles( annotation );
		String aStyleUrls = findComponentStyleUrls( annotation );
		String directives = findComponentDirectives( element, classBlock, generatedAccessorTypestypes );
		String providers = findComponentProviders( element, classBlock, generatedAccessorTypestypes );
		String outputs = findComponentOutputs( element );
		String parameters = findComponentConstructorParameters( element, classBlock, generatedAccessorTypestypes );
		
		// input fields
		List<FieldSetterMethodInformation> fieldsByMethods = findFieldMethods( element );
		String inputs = findInputs( element, fieldsByMethods );
		
		classBlock.line("@JsProperty( namespace = [{#}], name = [{#}] )", packageName, element.getSimpleName());
		classBlock.line("private native static AngularComponentConstructorFunction constructorFunction();");
		classBlock.line();

		classBlock.line("public static Object getComponentPrototype()");
		classBlock.block((e) -> {
			e.line( "JsToolsInjector.inject();" );
			e.separator();
			
			e.line("AngularComponentConstructorFunction constructorFunction = constructorFunction();");
			
			if( parameters != null )
			{
				e.line();
				e.line("if( constructorFunction.parameters == null )").block((i) -> {
						i.line("constructorFunction.parameters = [{}];", parameters);
				});
			}
			
			e.separator();
			
			e.line("if( constructorFunction.annotations == null )").block((i) -> {
				i.line("ComponentMetadata metadata = new ComponentMetadata();");
				i.line();
				i.line("metadata.selector = [{#}];", aSelector );
				if( aTemplate != null )
					i.line( "metadata.template = [{#}];", aTemplate );
				if( aTemplateUrl!=null )
					i.line( "metadata.templateUrl = [{#}];", aTemplateUrl );
				if( aStyles != null )
					i.line( "metadata.styles = [{}];", aStyles );
				if( aStyleUrls != null )
					i.line( "metadata.styleUrls = [{}];", aStyleUrls );
				if( directives != null )
					i.line( "metadata.directives = [{}];", directives );
				if( providers != null )
					i.line( "metadata.providers = [{}];", providers );
				if( inputs != null )
					i.line( "metadata.inputs = [{}];", inputs );
				if( outputs != null )
					i.line( "metadata.outputs = [{}];", outputs );
				i.line();
				i.line("constructorFunction.annotations = JsArray.of( new Component( metadata ) );");
				buildFieldMethodDefinitions( element.getSimpleName().toString(), fieldsByMethods, i, classBlock, javaClassText );
			});
			
			e.separator();
			
			e.line("return constructorFunction;");
		});

		try {
			String targetClassFqn = packageName + "." + angularComponentName;
			JavaFileObject jfo = processingEnv.getFiler().createSourceFile(targetClassFqn, element);

			OutputStream os = jfo.openOutputStream();
			OutputStreamWriter pw = new OutputStreamWriter(os, "UTF-8");
			StringBuilder sb = new StringBuilder();
			javaClassText.render(sb);
			pw.write(sb.toString());
			pw.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			processingEnv.getMessager().printMessage(Kind.ERROR, "AngularComponent non généré !" + e, element);
		}
	}

	private void processInjectable(TypeElement element) {
		String packageName = ((PackageElement) element.getEnclosingElement()).getQualifiedName().toString();
		String angularComponentName = element.getSimpleName() + INJECTABLE_HELPER_CLASS_SUFFIX;

		JavaClassText javaClassText = new JavaClassText(packageName);
		
		javaClassText.addImport("jsinterop.annotations.JsProperty");
		javaClassText.addImport("fr.lteconsulting.angular2gwt.client.JsArray");
		javaClassText.addImport("fr.lteconsulting.angular2gwt.client.interop.angular.AngularComponentConstructorFunction");
		javaClassText.addImport("fr.lteconsulting.angular2gwt.client.interop.angular.Injectable");
		javaClassText.addImport( "fr.lteconsulting.angular2gwt.client.JsToolsInjector" );
		
		Block classBlock = javaClassText.rootBlock().clazz(angularComponentName);
		
		HashMap<String,String> generatedAccessorTypestypes = new HashMap<>();
		String parameters = findComponentConstructorParameters( element, classBlock, generatedAccessorTypestypes );
		
		classBlock.line("@JsProperty( namespace = [{#}], name = [{#}] )", packageName, element.getSimpleName());
		classBlock.line("private native static AngularComponentConstructorFunction constructorFunction();");
		
		classBlock.separator();
		
		classBlock.line("public static Object getComponentPrototype()");
		classBlock.block((e) -> {
			e.line( "JsToolsInjector.inject();" );
			e.separator();
			
			e.line("AngularComponentConstructorFunction constructorFunction = constructorFunction();");
			
			if( parameters != null )
			{
				e.separator();
				
				e.line("if( constructorFunction.parameters == null )").block((i) -> {
						i.line("constructorFunction.parameters = [{}];", parameters);
				});
			}
			
			e.separator();
			
			e.line("if( constructorFunction.annotations == null )").block((i) -> {
				i.line("constructorFunction.annotations = JsArray.of( new Injectable() );");
			});
			
			e.separator();
			
			e.line("return constructorFunction;");
		});
		
		try {
			String targetClassFqn = packageName + "." + angularComponentName;
			JavaFileObject jfo = processingEnv.getFiler().createSourceFile(targetClassFqn, element);

			OutputStream os = jfo.openOutputStream();
			OutputStreamWriter pw = new OutputStreamWriter(os, "UTF-8");
			StringBuilder sb = new StringBuilder();
			javaClassText.render(sb);
			pw.write(sb.toString());
			pw.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			processingEnv.getMessager().printMessage(Kind.ERROR, "AngularComponent non généré !" + e, element);
		}
	}

	private void processDirective(TypeElement element) {
		String packageName = ((PackageElement) element.getEnclosingElement()).getQualifiedName().toString();
		String angularComponentName = element.getSimpleName() + DIRECTIVE_HELPER_CLASS_SUFFIX;

		JavaClassText javaClassText = new JavaClassText(packageName);
		
		javaClassText.addImport("jsinterop.annotations.JsProperty");
		javaClassText.addImport("fr.lteconsulting.angular2gwt.client.JsArray");
		javaClassText.addImport("fr.lteconsulting.angular2gwt.client.interop.angular.AngularComponentConstructorFunction");
		javaClassText.addImport("fr.lteconsulting.angular2gwt.client.interop.angular.Directive");
		javaClassText.addImport("fr.lteconsulting.angular2gwt.client.interop.angular.DirectiveMetadata");
		javaClassText.addImport( "fr.lteconsulting.angular2gwt.client.JsToolsInjector" );
		
		Block classBlock = javaClassText.rootBlock().clazz(angularComponentName);
		
		HashMap<String,String> generatedAccessorTypestypes = new HashMap<>();
		String parameters = findComponentConstructorParameters( element, classBlock, generatedAccessorTypestypes );
		
		Directive directive = element.getAnnotation( Directive.class );
		String aSelector = directive.selector();
		String host = findDirectiveHostsEventActions( element, classBlock );
		
		// input fields
		List<FieldSetterMethodInformation> fieldsByMethods = findFieldMethods( element );
		String inputs = findInputs( element, fieldsByMethods );
		
		classBlock.line("@JsProperty( namespace = [{#}], name = [{#}] )", packageName, element.getSimpleName());
		classBlock.line("private native static AngularComponentConstructorFunction constructorFunction();");
		
		classBlock.separator();

		classBlock.line("public static Object getComponentPrototype()");
		classBlock.block((e) -> {
			e.line( "JsToolsInjector.inject();" );
			e.separator();
			
			e.line("AngularComponentConstructorFunction constructorFunction = constructorFunction();");
			
			e.separator();
			
			e.line("if( constructorFunction.parameters == null )").block((i) -> {
				i.line("constructorFunction.parameters = [{}];", parameters);
			});
			
			e.line();
			e.line("if( constructorFunction.annotations == null )").block((i) -> {
				i.line("DirectiveMetadata metadata = new DirectiveMetadata();");
				
				e.separator();
				
				i.line("metadata.selector = [{#}];", aSelector );
				if( host != null )
					i.line( "metadata.host = [{}]", host );
				if( inputs != null )
					i.line( "metadata.inputs = [{}];", inputs );
				
				e.separator();
				
				i.line("constructorFunction.annotations = JsArray.of( new Directive( metadata ) );");
				
				buildFieldMethodDefinitions( element.getSimpleName().toString(), fieldsByMethods, i, classBlock, javaClassText );
			});
			
			e.separator();
			
			e.line("return constructorFunction;");
		});

		try {
			String targetClassFqn = packageName + "." + angularComponentName;
			JavaFileObject jfo = processingEnv.getFiler().createSourceFile(targetClassFqn, element);

			OutputStream os = jfo.openOutputStream();
			OutputStreamWriter pw = new OutputStreamWriter(os, "UTF-8");
			StringBuilder sb = new StringBuilder();
			javaClassText.render(sb);
			pw.write(sb.toString());
			pw.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			processingEnv.getMessager().printMessage(Kind.ERROR, "AngularDirective could not be generated !" + e, element);
		}
	}
	
	private String findComponentStyles( Component annotation )
	{
		if( annotation.styles().length == 0 )
			return null;
		
		StringBuilder aStyles = new StringBuilder();
		
		aStyles.append("JsArray.of( ");
		
		for( int i = 0; i < annotation.styles().length; i++ )
		{
			if( i > 0 )
				aStyles.append( ", " );
			
			aStyles.append( "\"" + annotation.styles()[i] + "\"" );
		}
		
		aStyles.append(" )");
		
		return aStyles.toString();
	}
	
	private String findComponentStyleUrls( Component annotation )
	{
		if( annotation.styleUrls().length == 0 )
			return null;
		
		StringBuilder aStyleUrls = new StringBuilder();
		
		aStyleUrls.append("JsArray.of( ");
		
		for( int i = 0; i < annotation.styleUrls().length; i++ )
		{
			if( i > 0 )
				aStyleUrls.append( ", " );
			
			aStyleUrls.append( "\"" + annotation.styleUrls()[i] + "\"" );
		}
		
		aStyleUrls.append(" )");
		
		return aStyleUrls.toString();
	}
	
	/**
	 * 
	 * @param element
	 * @param memberName imports, exports, declarations, providers, bootstrap
	 * @param classBlock
	 * @param generatedAccessorTypestypes
	 * @return
	 */
	private String findModuleAttributes( TypeElement element, String memberName, Block classBlock, HashMap<String,String> generatedAccessorTypestypes )
	{
		List<String> classNames = getAnnotationClassListValue( element, NgModuleAnnotationFqn, memberName );
		if( classNames==null || classNames.isEmpty() )
			return null;

		StringBuilder directives = new StringBuilder();

		directives.append("JsArray.of( ");
		
		boolean add = false;
		if( !classNames.isEmpty() )
		{
			directives.append( classNames.stream().map( name -> getConstructorFunctionAccessorName( name, classBlock, generatedAccessorTypestypes ) ).collect( Collectors.joining( ", " ) ) );
			if( add )
				directives.append( ", " );
			else
				add = true;
		}
		
		directives.append(" )");
		
		return directives.toString();
	}
	
	private String findComponentDirectives( TypeElement element, Block classBlock, HashMap<String,String> generatedAccessorTypestypes )
	{
		List<String> directiveClassNames = getAnnotationClassListValue( element, ComponentAnnotationFqn, "directives" );
		if( directiveClassNames==null || directiveClassNames.isEmpty() )
			return null;

		StringBuilder directives = new StringBuilder();

		directives.append("JsArray.of( ");
		
		boolean add = false;
		if( !directiveClassNames.isEmpty() )
		{
			directives.append( directiveClassNames.stream().map( name -> getConstructorFunctionAccessorName( name, classBlock, generatedAccessorTypestypes ) ).collect( Collectors.joining( ", " ) ) );
			if( add )
				directives.append( ", " );
			else
				add = true;
		}
		
		directives.append(" )");
		
		return directives.toString();
	}
	
	private String findComponentProviders( TypeElement element, Block classBlock, HashMap<String,String> generatedAccessorTypestypes )
	{
		List<String> providerClassNames = getAnnotationClassListValue( element, ComponentAnnotationFqn, "providers" );
		if( providerClassNames==null || providerClassNames.isEmpty() )
			return null;
		
		StringBuilder providers = new StringBuilder();

		providers.append("JsArray.of( ");
		
		boolean add = false;
		if( ! providerClassNames.isEmpty() )
		{
			providers.append( providerClassNames.stream().map( name -> getConstructorFunctionAccessorName( name, classBlock, generatedAccessorTypestypes ) ).collect( Collectors.joining( ", " ) ) );
			if( add )
				providers.append( ", " );
			else
				add = true;
		}
		
		providers.append(" )");
		
		return providers.toString();
	}
	
	private String findComponentOutputs( TypeElement element )
	{
		StringBuilder outputs = new StringBuilder();
		
		ElementFilter.fieldsIn( processingEnv.getElementUtils().getAllMembers( element ) ).stream().filter( f -> f.getAnnotation( Output.class ) != null ).map( f -> f.getSimpleName().toString() ).forEach( name -> {
			if( outputs.length() > 0 )
				outputs.append( ", " );
			outputs.append( "\"" );
			outputs.append( name );
			outputs.append( "\"" );
		} );
		
		if( outputs.length() == 0 )
			return null;
		
		return "JsArray.of( "+outputs.toString()+" )";
	}
	
	// parameters
	// trouver le constructeur (soit aucun et c'est bon, soit un seul et
	// c'est celui la, soit plusieurs et c'est celui qui a @JsConstructor
	// parcourir ses paramètres, et les ajouter dans les métadonnées
	private String findComponentConstructorParameters( TypeElement element, Block classBlock, HashMap<String,String> generatedAccessorTypestypes )
	{
		StringBuilder parameters = new StringBuilder();
		
		List<ExecutableElement> constructors = ElementFilter.constructorsIn( element.getEnclosedElements() );
		if( constructors != null && !constructors.isEmpty() )
		{
			if( constructors.size() > 1 )
			{
				processingEnv.getMessager().printMessage( Kind.ERROR, "Multiple constructors not yet supported", element );
				return null;
			}

			ExecutableElement constructor = constructors.get( 0 );
			constructor.getParameters().forEach( p -> {
				if( parameters.length() > 0 )
					parameters.append( ", " );

				String fqn = p.asType().toString();
				parameters.append( getConstructorFunctionAccessorName( fqn, classBlock, generatedAccessorTypestypes ) );
			} );
		}
		
		if( parameters.length() == 0 )
			return null;

		return "JsArray.of( " + parameters.toString() + " )";
	}
	
	private List<FieldSetterMethodInformation> findFieldMethods( TypeElement element )
	{
		List<FieldSetterMethodInformation> methodFields = new ArrayList<>();
		
		ElementFilter.methodsIn( processingEnv.getElementUtils().getAllMembers( element ) ).stream().filter( f -> f.getAnnotation( Input.class ) != null ).forEach( method -> {
			String methodName = method.getSimpleName().toString();
			
			if( ! methodName.startsWith( "set" ) )
			{
				processingEnv.getMessager().printMessage( Kind.ERROR, "@Input method name should begin by 'set'", method );
				return;
			}
			
			if( method.getParameters().size() != 1 )
			{
				processingEnv.getMessager().printMessage( Kind.ERROR, "@Input method should have one and only one argument", method );
				return;
			}
			
			VariableElement parameter = method.getParameters().get( 0 );
			String fieldName = methodName.substring( 3, 4 ).toLowerCase() + methodName.substring( 4 );
			
			methodFields.add( new FieldSetterMethodInformation(fieldName, methodName, parameter.asType().toString()) );
		} );
		
		return methodFields;
	}
	
	private String findInputs( TypeElement element, List<FieldSetterMethodInformation> methodFields )
	{
		List<String> inputFields = ElementFilter.fieldsIn( processingEnv.getElementUtils().getAllMembers( element ) ).stream().filter( f -> f.getAnnotation( Input.class ) != null ).map( f -> f.getSimpleName().toString() ).collect( Collectors.toList() );
		Set<String> inputNames = new HashSet<>();
		inputNames.addAll( inputFields );
		inputNames.addAll( methodFields.stream().map( ( i ) -> i.fieldName ).collect( Collectors.toList() ) );
		
		if( inputNames .isEmpty() )
			return null;
		
		StringBuilder inputs = new StringBuilder();

		for( String inputName : inputNames )
		{
			if( inputs.length() > 0 )
				inputs.append( ", " );

			inputs.append( "\"" );
			inputs.append( inputName );
			inputs.append( "\"" );
		}

		return "JsArray.of( " + inputs.toString() + " )";
	}
	
	private static class FieldSetterMethodInformation
	{
		private final String fieldName;
		private final String setterMethodName;
		private final String setterArgumentClassName;

		public FieldSetterMethodInformation(String fieldName, String setterMethodName, String setterArgumentClassName)
		{
			this.fieldName = fieldName;
			this.setterMethodName = setterMethodName;
			this.setterArgumentClassName = setterArgumentClassName;
		}
	}
	
	private void buildFieldMethodDefinitions( String componentClassName, List<FieldSetterMethodInformation> fields, Block block, Block additionnalDefinitionsBlock, JavaClassText classText )
	{
		if( fields==null || fields.isEmpty() )
			return;
		
		block.separator();
		
		classText.addImport( "fr.lteconsulting.angular2gwt.client.JsTools" );
		classText.addImport( "fr.lteconsulting.angular2gwt.client.interop.PropertyDefinition" );			
		
		for( FieldSetterMethodInformation info : fields )
		{
			
			String setterInterfaceName = info.setterMethodName.substring( 0, 1 ).toUpperCase() + info.setterMethodName.substring( 1 ) + "Caller";
			additionnalDefinitionsBlock.separator();
			additionnalDefinitionsBlock.line( "interface [{}]", setterInterfaceName ).block((iBlock)->{
				iBlock.line( "void setValue( [{}] value );", info.setterArgumentClassName );
			});
			
			block.line( "JsTools.defineProperty( constructorFunction.proto, [{#}], PropertyDefinition.create( null, (object, value)-> {", info.fieldName );
			block.indent( (l)->{
				l.line( "[{}] component = ([{}]) object;", componentClassName, componentClassName );
				l.line( "[{SetterInterface}] methodCaller = component::[{setterMethod}];", setterInterfaceName, info.setterMethodName );
				l.line( "methodCaller.setValue( ([{setterArgumentType}]) value );", info.setterArgumentClassName );
			});
			block.line("} ) );");				
		}
	}
	
	private List<String> getAnnotationClassListValue( TypeElement element, String annotationFqn, String annotationFieldName )
	{
		List<String> result = new ArrayList<>();

		Optional<? extends AnnotationMirror> optAnnotationMirror = getElementAnnotation( element, annotationFqn );

		if( optAnnotationMirror.isPresent() )
		{
			AnnotationMirror annotationMirror = optAnnotationMirror.get();
			Optional<? extends AnnotationValue> optValue = getAnnotationValue( annotationMirror, annotationFieldName );
			if( optValue.isPresent() )
			{
				AnnotationValue value = optValue.get();

				value.accept( new SimpleAnnotationValueVisitor8<Void, Void>()
				{
					@Override
					public Void visitArray( List<? extends AnnotationValue> vals, Void p )
					{
						for( AnnotationValue v : vals )
						{
							v.accept( new SimpleAnnotationValueVisitor8<Void, Void>()
							{
								@Override
								public Void visitType( javax.lang.model.type.TypeMirror t, Void p )
								{
									String name = t.toString();
									result.add( name );
									return null;
								};
							}, null );
						}
						return null;
					}
				}, null );
			}
		}

		return result;
	}

	private Optional<AnnotationValue> getAnnotationValue( AnnotationMirror annotationMirror, String annotationFieldName )
	{
		for( Entry<? extends ExecutableElement, ? extends AnnotationValue> e : annotationMirror.getElementValues().entrySet() )
		{
			if( e.getKey().getSimpleName().toString().equals( annotationFieldName ) )
			{
				return Optional.of( e.getValue() );
			}
		}

		return Optional.empty();
	}

	private Optional<? extends AnnotationMirror> getElementAnnotation( TypeElement element, String annotationFqn )
	{
		Optional<? extends AnnotationMirror> optAnnotationMirror = element.getAnnotationMirrors().stream().filter( m -> {
			return processingEnv.getTypeUtils().isSameType( m.getAnnotationType(), processingEnv.getElementUtils().getTypeElement( annotationFqn ).asType() );
		} ).findFirst();
		return optAnnotationMirror;
	}
	
	/**
	 * returns the processed accessor or the generated accessor method
	 * @param fqn
	 * @return
	 */
	private String getConstructorFunctionAccessorName( String fqn, Block classBlock, HashMap<String,String> generatedAccessorTypestypes )
	{
		TypeElement element = processingEnv.getElementUtils().getTypeElement( fqn );
		if( element != null )
		{
			if( element.getAnnotation( Directive.class ) != null )
				return fqn + DIRECTIVE_HELPER_CLASS_SUFFIX + ".getComponentPrototype()";

			if( element.getAnnotation( Component.class ) != null )
				return fqn + COMPONENT_HELPER_CLASS_SUFFIX + ".getComponentPrototype()";

			if( element.getAnnotation( Injectable.class ) != null )
				return fqn + INJECTABLE_HELPER_CLASS_SUFFIX + ".getComponentPrototype()";

			if( element.getAnnotation( NgModule.class ) != null )
				return fqn + NG_MODULE_HELPER_CLASS_SUFFIX + ".getComponentPrototype()";
			
			Optional<? extends TypeMirror> optProviderWrapper = element.getInterfaces().stream().filter((t)->t.toString().equals("fr.lteconsulting.angular2gwt.client.interop.angular.ProviderWrapper")).findFirst();
			if( optProviderWrapper.isPresent() )
				return "new " + fqn + "().get()";

			if( generatedAccessorTypestypes.containsKey(fqn))
				return generatedAccessorTypestypes.get(fqn);

			String name = element.getSimpleName().toString();
			String ns = element.getEnclosingElement().toString().replace( "package ", "" );

			Optional<? extends AnnotationMirror> optAM = getElementAnnotation( element, JsType.class.getName() );
			if( optAM.isPresent() )
			{
				AnnotationMirror am = optAM.get();

				Optional<? extends AnnotationValue> optName = getAnnotationValue( am, "name" );
				if( optName.isPresent() )
				{
					name = optName.get().toString();
					name = name.replaceAll( "\"", "" );
				}

				Optional<? extends AnnotationValue> optNamespace = getAnnotationValue( am, "namespace" );
				if( optNamespace.isPresent() )
				{
					ns = optNamespace.get().toString();
					ns = ns.replaceAll( "\"", "" );
				}
			}
			
			String constructorFunctionName = "constructorFunctionOf_" + ns.replaceAll("\\.", "_") + "_" + name;
			
			classBlock.line("@JsProperty( namespace = [{#}], name = [{#}] )", ns, name);
			classBlock.line("private native static AngularComponentConstructorFunction [{}]();", constructorFunctionName);
			classBlock.line();
			
			String result = constructorFunctionName + "()";
			
			generatedAccessorTypestypes.put(fqn, result );

			return result;
		}

		return "/* no type element for " + fqn + " ! */ $wnd." + fqn;
	}
	
	private String findDirectiveHostsEventActions( TypeElement element, Block classBlock )
	{
		HashMap<String, String> hostsEventActions = new HashMap<>();
		Optional<? extends AnnotationMirror> hostsAnnotation = getElementAnnotation( element, Hosts.class.getName() );
		if( hostsAnnotation.isPresent() )
		{
			Optional<? extends AnnotationValue> valueOptional = getAnnotationValue( hostsAnnotation.get(), "value" );
			if( valueOptional.isPresent() )
			{
				// list of hosts
				AnnotationValue value = valueOptional.get();
				value.accept( new SimpleAnnotationValueVisitor8<Void, Void>()
				{
					@Override
					public Void visitArray( List<? extends AnnotationValue> vals, Void p )
					{
						for( AnnotationValue v : vals )
						{
							// v is a Host
							v.accept( new SimpleAnnotationValueVisitor8<Void, Void>()
							{
								@Override
								public Void visitAnnotation( AnnotationMirror annotationMirror, Void p )
								{
									value.accept( new SimpleAnnotationValueVisitor8<Void, Void>()
									{
										@Override
										public Void visitArray( List<? extends AnnotationValue> vals, Void p )
										{
											for( AnnotationValue v : vals )
											{
												v.accept( new SimpleAnnotationValueVisitor8<Void, Void>()
												{
													@Override
													public Void visitAnnotation( AnnotationMirror annotationMirror, Void p )
													{
														String event = getAnnotationValue( annotationMirror, "event" ).get().toString().replaceAll( "\"", "" );
														String action = getAnnotationValue( annotationMirror, "action" ).get().toString().replaceAll( "\"", "" );

														hostsEventActions.put( event, action );

														return null;
													}
												}, null );
											}

											return null;
										}
									}, null );

									return null;
								}
							}, null );
						}
						return null;
					}
				}, null );
			}
		}
		
		if( hostsEventActions.isEmpty() )
			return null;
		
		classBlock.separator();
		
		List<String> hosts = new ArrayList<>( hostsEventActions.keySet() );
		
		Map<String,String> accronyms = new HashMap<>();
		int i = 0;
		for(String hostName : hosts )
			accronyms.put(hostName, "host_" + i++);
			
		classBlock.line("@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = \"Object\")");
		classBlock.line("static class Host");
		classBlock.block((b)->{
			for( String hostName : hosts )
			{
				b.line( "@JsProperty( name=[{#}])", hostName );
				b.line( "String [{}];", accronyms.get(hostName) );
			}
			
			b.separator();
			
			String createFormalParameters = hosts.stream().map((h)->"String "+accronyms.get(h)).collect(Collectors.joining(", "));
			
			b.line( "static Host create([{}])", createFormalParameters );
			b.block( ( c ) -> {
				b.line("Host host = new Host();");
				b.separator();
				for( String hostName : hosts )
					b.line( "host.[{}] = [{}];", accronyms.get(hostName), accronyms.get(hostName) );
				b.separator();
				b.line("return host;");
			} );
		});

		String createParameters = hosts.stream().map((h)->"\""+hostsEventActions.get(h)+"\"").collect(Collectors.joining(", "));
		
		return "Host.create( "+createParameters+" )";
	}
}
