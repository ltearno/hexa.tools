package fr.lteconsulting.angular2gwt.processor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.SimpleAnnotationValueVisitor8;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

import jsinterop.annotations.JsType;
import fr.lteconsulting.angular2gwt.Component;
import fr.lteconsulting.angular2gwt.Directive;
import fr.lteconsulting.angular2gwt.Hosts;
import fr.lteconsulting.angular2gwt.Injectable;
import fr.lteconsulting.angular2gwt.Input;
import fr.lteconsulting.angular2gwt.Output;
import fr.lteconsulting.angular2gwt.RouteConfigs;

@SupportedAnnotationTypes( { AngularComponentProcessor.DirectiveAnnotationFqn, AngularComponentProcessor.ComponentAnnotationFqn, AngularComponentProcessor.InjectableAnnotationFqn } )
@SupportedSourceVersion( SourceVersion.RELEASE_8 )
public class AngularComponentProcessor extends AbstractProcessor
{
	public final static String DirectiveAnnotationFqn = "fr.lteconsulting.angular2gwt.Directive";
	public final static String ComponentAnnotationFqn = "fr.lteconsulting.angular2gwt.Component";
	public final static String InjectableAnnotationFqn = "fr.lteconsulting.angular2gwt.Injectable";

	private final static String DIRECTIVE_HELPER_CLASS_SUFFIX = "_AngularDirective";
	private final static String COMPONENT_HELPER_CLASS_SUFFIX = "_AngularComponent";
	private final static String INJECTABLE_HELPER_CLASS_SUFFIX = "_AngularInjectable";

	@Override
	public boolean process( Set<? extends TypeElement> annotations, RoundEnvironment roundEnv )
	{
		for( TypeElement element : ElementFilter.typesIn( roundEnv.getElementsAnnotatedWith( processingEnv.getElementUtils().getTypeElement( DirectiveAnnotationFqn ) ) ) )
		{
			processDirective( element );
		}

		for( TypeElement element : ElementFilter.typesIn( roundEnv.getElementsAnnotatedWith( processingEnv.getElementUtils().getTypeElement( ComponentAnnotationFqn ) ) ) )
		{
			processComponent( element );
		}

		for( TypeElement element : ElementFilter.typesIn( roundEnv.getElementsAnnotatedWith( processingEnv.getElementUtils().getTypeElement( InjectableAnnotationFqn ) ) ) )
		{
			processInjectable( element );
		}

		roundEnv.errorRaised();

		return true;
	}

	private void processDirective( TypeElement element )
	{
		String template = readResource( "fr/lteconsulting/angular2gwt/processor/AngularDirective.txt" );

		String packageName = ((PackageElement) element.getEnclosingElement()).getQualifiedName().toString();
		String angularDirectiveName = element.getSimpleName() + DIRECTIVE_HELPER_CLASS_SUFFIX;

		template = template.replaceAll( "PACKAGE", packageName );
		template = template.replaceAll( "CLASS_NAME", angularDirectiveName );
		template = template.replaceAll( "DIRECTIVE_CLASS_FQN", element.getQualifiedName().toString() );

		Directive directive = element.getAnnotation( Directive.class );

		// selector
		String aSelector = directive.selector();

		// parameters
		// trouver le constructeur (soit aucun et c'est bon, soit un seul et
		// c'est celui la, soit plusieurs et c'est celui qui a @JsConstructor
		// parcourir ses paramètres, et les ajouter dans les métadonnées
		StringBuilder parameters = new StringBuilder();
		List<ExecutableElement> constructors = ElementFilter.constructorsIn( element.getEnclosedElements() );
		if( constructors != null && !constructors.isEmpty() )
		{
			if( constructors.size() > 1 )
			{
				processingEnv.getMessager().printMessage( Kind.ERROR, "Multiple constructors not yet supported", element );
				return;
			}

			ExecutableElement constructor = constructors.get( 0 );
			constructor.getParameters().forEach( p -> {
				if( parameters.length() > 0 )
					parameters.append( ", " );

				String fqn = p.asType().toString();
				parameters.append( getJavascriptName( fqn ) );
			} );
		}

		HashMap<String, String> hostsEventActions = new HashMap<>();
		Optional<? extends AnnotationMirror> hostsAnnotation = getElementAnnotation( element, Hosts.class.getName() );
		if( hostsAnnotation.isPresent() )
		{
			Optional<AnnotationValue> valueOptional = getAnnotationValue( hostsAnnotation.get(), "value" );
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
		StringBuilder hostsBuilder = new StringBuilder();
		if( !hostsEventActions.isEmpty() )
		{
			hostsBuilder.append( "host: {" );
			for( Entry<String, String> entry : hostsEventActions.entrySet() )
				hostsBuilder.append( "'" + entry.getKey() + "': '" + entry.getValue() + "', \n" );
			hostsBuilder.append( "}," );
		}

		// input fields
		List<String> inputFields = ElementFilter.fieldsIn( processingEnv.getElementUtils().getAllMembers( element ) ).stream().filter( f -> f.getAnnotation( Input.class ) != null ).map( f -> f.getSimpleName().toString() ).collect( Collectors.toList() );
		Map<String, String> methodFields = new HashMap<>();
		ElementFilter.methodsIn( processingEnv.getElementUtils().getAllMembers( element ) ).stream().filter( f -> f.getAnnotation( Input.class ) != null ).forEach( method -> {
			String methodName = method.getSimpleName().toString();
			String fieldName = methodName;
			if( methodName.startsWith( "set" ) )
				fieldName = methodName.substring( 3, 4 ).toLowerCase() + methodName.substring( 4 );
			else
				processingEnv.getMessager().printMessage( Kind.ERROR, "@Input method name should begin by 'set'", method );
			methodFields.put( fieldName, methodName );
		} );
		StringBuilder inputs = new StringBuilder();
		Set<String> inputNames = new HashSet<>();
		inputNames.addAll( inputFields );
		inputNames.addAll( methodFields.keySet() );
		for( String inputName : inputNames )
		{
			if( inputs.length() > 0 )
				inputs.append( ", " );
			else
				inputs.append( "inputs: [" );
			inputs.append( "'" );
			inputs.append( inputName );
			inputs.append( "'" );
		}
		if( inputs.length() > 0 )
			inputs.append( "]," );
		StringBuilder properties = new StringBuilder();
		if( !methodFields.isEmpty() )
		{
			for( Entry<String, String> entry : methodFields.entrySet() )
			{
				String fieldName = entry.getKey();
				String methodName = entry.getValue();

				properties.append( "if( ! ( '" + fieldName + "' in component ) ) {\n" );
				properties.append( "  Object.defineProperty(proto, '" + fieldName + "', {\n" );
				properties.append( "    set: function( value ) { this." + methodName + "( value ) }\n" );
				properties.append( "  });\n" );
				properties.append( "}\n" );
			}
		}

		template = template.replace( "PARAMETERS", parameters.toString() );
		template = template.replace( "SELECTOR", aSelector );
		template = template.replace( "HOST", hostsBuilder.toString() );
		template = template.replace( "INPUTS", inputs.toString() );
		template = template.replace( "PROPERTIES", properties.toString() );

		String targetClassFqn = packageName + "." + angularDirectiveName;

		try
		{
			JavaFileObject jfo = processingEnv.getFiler().createSourceFile( targetClassFqn, element );

			OutputStream os = jfo.openOutputStream();
			PrintWriter pw = new PrintWriter( os );
			pw.print( template );
			pw.close();
			os.close();
		}
		catch( IOException e )
		{
			e.printStackTrace();
			processingEnv.getMessager().printMessage( Kind.ERROR, "AngularDirective not generated !" + e, element );
		}
	}

	private void processComponent( TypeElement element )
	{
		String template = readResource( "fr/lteconsulting/angular2gwt/processor/AngularComponent.txt" );

		String packageName = ((PackageElement) element.getEnclosingElement()).getQualifiedName().toString();
		String angularComponentName = element.getSimpleName() + COMPONENT_HELPER_CLASS_SUFFIX;

		template = template.replaceAll( "PACKAGE", packageName );
		template = template.replaceAll( "CLASS_NAME", angularComponentName );
		template = template.replaceAll( "COMPONENT_CLASS_FQN", element.getQualifiedName().toString() );

		Component annotation = element.getAnnotation( Component.class );

		// selector
		String aSelector = annotation.selector();

		// template
		String aTemplate = annotation.template().isEmpty() ? "" : "template: \"" + annotation.template() + "\",";

		// templateUrl
		String aTemplateUrl = annotation.templateUrl().isEmpty() ? "" : "templateUrl: \"" + annotation.templateUrl() + "\",";

		// styles
		String aStyles = annotation.styles().isEmpty() ? "" : "styles: [" + annotation.styles() + "],";

		StringBuilder aStyleUrls = new StringBuilder();
		for( int i = 0; i < annotation.styleUrls().length; i++ )
		{
			if( i == 0 )
				aStyleUrls.append( "styleUrls: [" );
			else
				aStyleUrls.append( ", " );
			aStyleUrls.append( "'" + annotation.styleUrls()[i] + "'" );
		}
		if( annotation.styleUrls().length > 0 )
			aStyleUrls.append( "]," );

		// directives
		StringBuilder directives = new StringBuilder();
		List<String> directiveClassNames = getAnnotationClassListValue( element, ComponentAnnotationFqn, "directives" );
		if( !directiveClassNames.isEmpty() )
		{
			directives.append( "directives: [" );
			directives.append( directiveClassNames.stream().map( name -> getJavascriptName( name ) ).collect( Collectors.joining( ", " ) ) );
			directives.append( "]," );
		}

		// providers
		StringBuilder providers = new StringBuilder();
		List<String> providerClassNames = getAnnotationClassListValue( element, ComponentAnnotationFqn, "providers" );
		if( !providerClassNames.isEmpty() )
		{
			providers.append( "providers: [" );
			providers.append( providerClassNames.stream().map( name -> getJavascriptName( name ) ).collect( Collectors.joining( ", " ) ) );
			providers.append( "]," );
		}

		// routeconfigs
		List<RouteConfigDto> routeConfigs = new ArrayList<>();
		element.getAnnotationMirrors().stream().filter( m -> {
			return processingEnv.getTypeUtils().isSameType( m.getAnnotationType(), processingEnv.getElementUtils().getTypeElement( RouteConfigs.class.getName() ).asType() );
		} ).forEach( annotationMirror -> {
			Optional<? extends ExecutableElement> optKey = annotationMirror.getElementValues().keySet().stream().filter( k -> k.getSimpleName().toString().equals( "value" ) ).findFirst();
			if( optKey.isPresent() )
			{
				ExecutableElement key = optKey.get();
				AnnotationValue value = annotationMirror.getElementValues().get( key );

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
									RouteConfigDto dto = new RouteConfigDto();
									routeConfigs.add( dto );

									for( Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : annotationMirror.getElementValues().entrySet() )
									{
										String name = entry.getKey().getSimpleName().toString();
										Object value = entry.getValue().getValue();

										dto.set( name, value );
									}
									return null;
								}
							}, null );
						}
						return null;
					}
				}, null );
			}
		} );
		StringBuilder routeConfisBuilder = new StringBuilder();
		if( !routeConfigs.isEmpty() )
		{
			routeConfisBuilder.append( ", new $wnd.ng.router.RouteConfig([" );
			for( RouteConfigDto dto : routeConfigs )
				routeConfisBuilder.append( dto.toString() + ", " );
			routeConfisBuilder.append( "])" );
		}

		// input fields
		List<String> inputFields = ElementFilter.fieldsIn( processingEnv.getElementUtils().getAllMembers( element ) ).stream().filter( f -> f.getAnnotation( Input.class ) != null ).map( f -> f.getSimpleName().toString() ).collect( Collectors.toList() );
		Map<String, String> methodFields = new HashMap<>();
		ElementFilter.methodsIn( processingEnv.getElementUtils().getAllMembers( element ) ).stream().filter( f -> f.getAnnotation( Input.class ) != null ).forEach( method -> {
			String methodName = method.getSimpleName().toString();
			String fieldName = methodName;
			if( methodName.startsWith( "set" ) )
				fieldName = methodName.substring( 3, 4 ).toLowerCase() + methodName.substring( 4 );
			else
				processingEnv.getMessager().printMessage( Kind.ERROR, "@Input method name should begin by 'set'", method );
			methodFields.put( fieldName, methodName );
		} );
		StringBuilder inputs = new StringBuilder();
		Set<String> inputNames = new HashSet<>();
		inputNames.addAll( inputFields );
		inputNames.addAll( methodFields.keySet() );
		for( String inputName : inputNames )
		{
			if( inputs.length() > 0 )
				inputs.append( ", " );
			else
				inputs.append( "inputs: [" );
			inputs.append( "'" );
			inputs.append( inputName );
			inputs.append( "'" );
		}
		if( inputs.length() > 0 )
			inputs.append( "]," );
		StringBuilder properties = new StringBuilder();
		if( !methodFields.isEmpty() )
		{
			for( Entry<String, String> entry : methodFields.entrySet() )
			{
				String fieldName = entry.getKey();
				String methodName = entry.getValue();

				properties.append( "if( ! ( '" + fieldName + "' in component ) ) {\n" );
				properties.append( "  Object.defineProperty(proto, '" + fieldName + "', {\n" );
				properties.append( "    set: function( value ) { this." + methodName + "( value ) }\n" );
				properties.append( "  });\n" );
				properties.append( "}\n" );
			}
		}

		// output fields
		StringBuilder outputs = new StringBuilder();
		ElementFilter.fieldsIn( processingEnv.getElementUtils().getAllMembers( element ) ).stream().filter( f -> f.getAnnotation( Output.class ) != null ).map( f -> f.getSimpleName().toString() ).forEach( name -> {
			if( outputs.length() > 0 )
				outputs.append( ", " );
			else
				outputs.append( "outputs: [" );
			outputs.append( "'" );
			outputs.append( name );
			outputs.append( "'" );
		} );
		if( outputs.length() > 0 )
			outputs.append( "]," );

		// parameters
		// trouver le constructeur (soit aucun et c'est bon, soit un seul et
		// c'est celui la, soit plusieurs et c'est celui qui a @JsConstructor
		// parcourir ses paramètres, et les ajouter dans les métadonnées
		StringBuilder parameters = new StringBuilder();
		List<ExecutableElement> constructors = ElementFilter.constructorsIn( element.getEnclosedElements() );
		if( constructors != null && !constructors.isEmpty() )
		{
			if( constructors.size() > 1 )
			{
				processingEnv.getMessager().printMessage( Kind.ERROR, "Multiple constructors not yet supported", element );
				return;
			}

			ExecutableElement constructor = constructors.get( 0 );
			constructor.getParameters().forEach( p -> {
				if( parameters.length() > 0 )
					parameters.append( ", " );

				String fqn = p.asType().toString();
				parameters.append( getJavascriptName( fqn ) );
			} );
		}

		template = template.replace( "PARAMETERS", parameters.toString() );
		template = template.replace( "SELECTOR", aSelector );
		template = template.replace( "TEMPLATE_URL", aTemplateUrl );
		template = template.replace( "TEMPLATE", aTemplate );
		template = template.replace( "STYLES", aStyles );
		template = template.replace( "STYLE_URLS", aStyleUrls.toString() );
		template = template.replace( "DIRECTIVES", directives.toString() );
		template = template.replace( "PROVIDERS", providers.toString() );
		template = template.replace( "INPUTS", inputs.toString() );
		template = template.replace( "OUTPUTS", outputs.toString() );
		template = template.replace( "ROUTECONFIGS", routeConfisBuilder.toString() );
		template = template.replace( "PROPERTIES", properties.toString() );

		String targetClassFqn = packageName + "." + angularComponentName;

		try
		{
			JavaFileObject jfo = processingEnv.getFiler().createSourceFile( targetClassFqn, element );

			OutputStream os = jfo.openOutputStream();
			PrintWriter pw = new PrintWriter( os );
			pw.print( template );
			pw.close();
			os.close();
		}
		catch( IOException e )
		{
			e.printStackTrace();
			processingEnv.getMessager().printMessage( Kind.ERROR, "AngularComponent non généré !" + e, element );
		}
	}

	private String getJavascriptName( String fqn )
	{
		TypeElement element = processingEnv.getElementUtils().getTypeElement( fqn );
		if( element != null )
		{
			if( element.getAnnotation( Directive.class ) != null )
				return "@" + fqn + DIRECTIVE_HELPER_CLASS_SUFFIX + "::get()()";

			if( element.getAnnotation( Component.class ) != null )
				return "@" + fqn + COMPONENT_HELPER_CLASS_SUFFIX + "::get()()";

			if( element.getAnnotation( Injectable.class ) != null )
				return "@" + fqn + INJECTABLE_HELPER_CLASS_SUFFIX + "::get()()";

			String name = element.getSimpleName().toString();
			String ns = element.getEnclosingElement().toString().replace( "package ", "" );

			Optional<? extends AnnotationMirror> optAM = getElementAnnotation( element, JsType.class.getName() );
			if( optAM.isPresent() )
			{
				AnnotationMirror am = optAM.get();

				Optional<AnnotationValue> optName = getAnnotationValue( am, "name" );
				if( optName.isPresent() )
				{
					name = optName.get().toString();
					name = name.replaceAll( "\"", "" );
				}

				Optional<AnnotationValue> optNamespace = getAnnotationValue( am, "namespace" );
				if( optNamespace.isPresent() )
				{
					ns = optNamespace.get().toString();
					ns = ns.replaceAll( "\"", "" );
				}
			}

			return "$wnd" + (ns == null || ns.isEmpty() ? "" : ("." + ns)) + "." + name;
		}

		return "/* no type element for " + fqn + " ! */ $wnd." + fqn;

	}

	private void processInjectable( TypeElement element )
	{
		String template = readResource( "fr/lteconsulting/angular2gwt/processor/AngularInjectable.txt" );

		String packageName = ((PackageElement) element.getEnclosingElement()).getQualifiedName().toString();
		String angularComponentName = element.getSimpleName() + INJECTABLE_HELPER_CLASS_SUFFIX;

		template = template.replaceAll( "PACKAGE", packageName );
		template = template.replaceAll( "CLASS_NAME", angularComponentName );
		template = template.replaceAll( "INJECTABLE_CLASS_FQN", element.getQualifiedName().toString() );

		// parameters
		// trouver le constructeur (soit aucun et c'est bon, soit un seul et
		// c'est celui la, soit plusieurs et c'est celui qui a @JsConstructor
		// parcourir ses paramètres, et les ajouter dans les métadonnées
		StringBuilder parameters = new StringBuilder();
		List<ExecutableElement> constructors = ElementFilter.constructorsIn( element.getEnclosedElements() );
		if( constructors != null && !constructors.isEmpty() )
		{
			if( constructors.size() > 1 )
			{
				processingEnv.getMessager().printMessage( Kind.ERROR, "Multiple constructors not yet supported", element );
				return;
			}

			ExecutableElement constructor = constructors.get( 0 );
			constructor.getParameters().forEach( p -> {
				if( parameters.length() > 0 )
					parameters.append( ", " );

				String fqn = p.asType().toString();
				parameters.append( getJavascriptName( fqn ) );
			} );
		}

		template = template.replace( "PARAMETERS", parameters.toString() );

		String targetClassFqn = packageName + "." + angularComponentName;

		try
		{
			JavaFileObject jfo = processingEnv.getFiler().createSourceFile( targetClassFqn, element );

			OutputStream os = jfo.openOutputStream();
			PrintWriter pw = new PrintWriter( os );
			pw.print( template );
			pw.close();
			os.close();
		}
		catch( IOException e )
		{
			e.printStackTrace();
			processingEnv.getMessager().printMessage( Kind.ERROR, "AngularComponent non généré !" + e, element );
		}
	}

	class RouteConfigDto
	{
		String path;
		String name;
		String component;
		boolean useAsDefault;

		void set( String fieldName, Object value )
		{
			if( "name".equals( fieldName ) )
				name = String.valueOf( value );
			else if( "path".equals( fieldName ) )
				path = String.valueOf( value );
			else if( "component".equals( fieldName ) )
				component = String.valueOf( value );
			else if( "useAsDefault".equals( fieldName ) )
				useAsDefault = "true".equals( String.valueOf( value ) );
		}

		@Override
		public String toString()
		{
			return "{ path: '" + path + "', name: '" + name + "', component: " + getJavascriptName( component ) + (useAsDefault ? ", useAsDefault: true" : "") + "}";
		}
	}

	private List<String> getAnnotationClassListValue( TypeElement element, String annotationFqn, String annotationFieldName )
	{
		List<String> result = new ArrayList<>();

		Optional<? extends AnnotationMirror> optAnnotationMirror = getElementAnnotation( element, annotationFqn );

		if( optAnnotationMirror.isPresent() )
		{
			AnnotationMirror annotationMirror = optAnnotationMirror.get();
			Optional<AnnotationValue> optValue = getAnnotationValue( annotationMirror, annotationFieldName );
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

	@SuppressWarnings( "unchecked" )
	private Optional<AnnotationValue> getAnnotationValue( AnnotationMirror annotationMirror, String annotationFieldName )
	{
		return (Optional<AnnotationValue>) annotationMirror.getElementValues().entrySet().stream().filter( e -> e.getKey().getSimpleName().toString().equals( annotationFieldName ) ).map( e -> e.getValue() ).findFirst();
	}

	private Optional<? extends AnnotationMirror> getElementAnnotation( TypeElement element, String annotationFqn )
	{
		Optional<? extends AnnotationMirror> optAnnotationMirror = element.getAnnotationMirrors().stream().filter( m -> {
			return processingEnv.getTypeUtils().isSameType( m.getAnnotationType(), processingEnv.getElementUtils().getTypeElement( annotationFqn ).asType() );
		} ).findFirst();
		return optAnnotationMirror;
	}

	private static String readResource( String fqn )
	{
		try
		{
			return new Scanner( AngularComponentProcessor.class.getClassLoader().getResourceAsStream( fqn ) ).useDelimiter( "\\A" ).next();
		}
		catch( Exception e )
		{
			return null;
		}
	}
}
