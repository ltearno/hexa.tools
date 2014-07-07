package fr.lteconsulting.hexa.persistence.rebind;

import java.io.PrintWriter;
import java.lang.reflect.Field;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.WithEntityClasses;

public class PersistenceConfigurationFactoryGenerator extends Generator
{
	private static final String CLASSBUNDLE_INTERFACE_NAME = "ClassBundle";

	// Context and logger for code generation
	TreeLogger logger = null;
	GeneratorContext context = null;
	TypeOracle typeOracle = null;

	Class<?>[] entityClasses;

	JClassType askedType;

	String generatedClassPackageName;
	String generatedClassName;

	@Override
	public String generate( TreeLogger logger, GeneratorContext context, String typeName ) throws UnableToCompleteException
	{
		this.logger = logger;
		this.context = context;
		this.typeOracle = context.getTypeOracle();

		try
		{
			// get classType and save instance variables
			askedType = typeOracle.getType( typeName );

			WithEntityClasses withEntityClassesAnnotation = askedType.getAnnotation( WithEntityClasses.class );

			entityClasses = withEntityClassesAnnotation.classes();

			generatedClassPackageName = askedType.getPackage().getName();
			generatedClassName = askedType.getSimpleSourceName() + "_EntityConfigurationFactoryImpl";

			// Generate class source code
			generateClass();
		}
		catch( Exception e )
		{
			// record to logger that Map generation threw an exception
			logger.log( TreeLogger.ERROR, "ERROR when generating " + generatedClassName + " for " + typeName, e );
		}

		// return the fully qualifed name of the class generated
		return generatedClassPackageName + "." + generatedClassName;
	}

	void generateClass()
	{
		// get print writer that receives the source code
		PrintWriter printWriter = null;

		printWriter = context.tryCreate( logger, generatedClassPackageName, generatedClassName );
		// print writer if null, source code has ALREADY been generated, return
		if( printWriter == null )
			return;

		// init composer, set class properties, create source writer
		ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory( generatedClassPackageName, generatedClassName );

		composer.addImport( "fr.lteconsulting.hexa.client.classinfo.ReflectedClasses" );
		composer.addImport( "fr.lteconsulting.hexa.client.classinfo.ClazzBundle" );
		composer.addImport( "fr.lteconsulting.hexa.persistence.client.legacy.persistence.PersistenceConfiguration" );
		composer.addImport( "fr.lteconsulting.hexa.persistence.client.legacy.persistence.PersistenceConfigurationFactory" );
		composer.addImport( "fr.lteconsulting.hexa.persistence.client.legacy.persistence.PersistenceConfiguration.EntityConfiguration" );
		composer.addImport( "javax.persistence.GenerationType" );
		composer.addImport( "javax.persistence.FetchType" );
		composer.addImport( "com.google.gwt.core.shared.GWT" );
		composer.addImplementedInterface( askedType.getParameterizedQualifiedSourceName() );

		SourceWriter sourceWriter = composer.createSourceWriter( context, printWriter );

		// create the classbundle interface
		writeClassBundleInterface( sourceWriter, CLASSBUNDLE_INTERFACE_NAME );

		// write the body
		writeBody( sourceWriter );

		// close generated class
		sourceWriter.outdent();
		sourceWriter.println( "}" );

		// commit generated class
		context.commit( logger, printWriter );
	}

	private Field getIdField( Class<?> clazz )
	{
		Field[] fields = clazz.getDeclaredFields();
		for( int i=0; i<fields.length; i++ )
		{
			Id idAnnotation = fields[i].getAnnotation( Id.class );
			if( idAnnotation != null )
				return fields[i];
		}
		return null;
	}

	private void writeBody( SourceWriter sourceWriter )
	{
		sourceWriter.println( "private PersistenceConfiguration config;" );

		sourceWriter.println( "@Override" );
		sourceWriter.println( "public PersistenceConfiguration getPersistenceConfiguration() {" );
		sourceWriter.println( "if( config == null )" );
		sourceWriter.println( "createConfig();" );
		sourceWriter.println( "return config;" );
		sourceWriter.println( "}" );

		sourceWriter.println( "private void createConfig() {" );
		sourceWriter.println( "ClassBundle clazzBundle = GWT.create( ClassBundle.class );" );
		sourceWriter.println( "clazzBundle.register();" );

		sourceWriter.println( "config = new PersistenceConfiguration();" );
		sourceWriter.println( "EntityConfiguration ec = null;" );
		for( int i=0; i<entityClasses.length; i++ )
		{
			Class<?> entityClass = entityClasses[i];

			// ensure class has the @Entity annotation...
			assert entityClass.getAnnotation( Entity.class ) != null : "Class xxx should be annotated with @Entity !";

			// search @Id field : class, name, generation policy
			Field idField = getIdField( entityClass );
			assert idField != null : "Entity class should have an @Id field";
			String generationTypeString = "GenerationType.SEQUENCE";
			GeneratedValue generatedValueAnnotation = idField.getAnnotation( GeneratedValue.class );
			if( generatedValueAnnotation != null && generatedValueAnnotation.strategy() != null )
				generationTypeString = "GenerationType." + generatedValueAnnotation.strategy().name();
			sourceWriter.println( "ec = config.addEntityConfiguration( "+entityClass.getName()+".class, "+idField.getType().getName()+".class, \""+idField.getName()+"\", "+generationTypeString+" );" );

			Field[] fields = entityClass.getDeclaredFields();
			for( int f=0; f<fields.length; f++ )
			{
				Id idAnnotation = fields[f].getAnnotation( Id.class );
				if( idAnnotation != null )
					continue;

				// TODO : check transcient, final, ...

				OneToMany oneToMany = fields[f].getAnnotation( OneToMany.class );
				if( oneToMany != null )
				{
					//@OneToMany( mappedBy = "category" )
					//private List<Article> articles;

					String mappedBy = oneToMany.mappedBy();

					//ec.addOneToManyFieldConfiguration( List.class, Article.class, "articles", "category", false );
					JClassType entityType = typeOracle.findType( entityClass.getName() );
					JField jField = entityType.findField( fields[f].getName() );
					String containerClassName = jField.getType().getErasedType().getQualifiedSourceName();
					String targetClassName = jField.getType().isParameterized().getTypeArgs()[0].getQualifiedSourceName();
					sourceWriter.println( "ec.addOneToManyFieldConfiguration( "+containerClassName+".class, "+targetClassName+".class, \""+fields[f].getName()+"\", \""+mappedBy+"\", false );" );
					continue;
				}

				ManyToOne manyToOne = fields[f].getAnnotation( ManyToOne.class );
				if( manyToOne != null )
				{
					//@ManyToOne( fetch = FetchType.LAZY, cascade = { CascadeType.MERGE } )
					//@JoinColumn( name="category_id" )
					//Category category;

					String columnName = fields[f].getName() + "_id";
					JoinColumn joinColumn = fields[f].getAnnotation( JoinColumn.class );
					if( joinColumn != null )
						columnName = joinColumn.name();

					String fetchTypeString = "FetchType." + manyToOne.fetch();

					// ec.addManyToOneFieldConfiguration( Category.class, "category", "category_id", FetchType.LAZY );
					sourceWriter.println( "ec.addManyToOneFieldConfiguration( "+fields[f].getType().getName()+".class, \""+fields[f].getName()+"\", \""+columnName+"\", "+fetchTypeString+" );" );
					continue;
				}

				// normal field fields
				sourceWriter.println( "ec.addFieldConfiguration( "+fields[f].getType().getName()+".class, \""+fields[f].getName()+"\" );" );
			}
		}

		sourceWriter.println( "}" );
	}

	private void writeClassBundleInterface( SourceWriter sourceWriter, String classBundleInterfaceName )
	{
		StringBuilder sb = new StringBuilder();
		for( int i=0; i<entityClasses.length; i++ )
		{
			if( i > 0 )
				sb.append(  ", " );
			sb.append( entityClasses[i].getName() );
			sb.append( ".class" );
		}

		sourceWriter.println( "interface "+classBundleInterfaceName+" extends ClazzBundle {" );
		sourceWriter.println( "@ReflectedClasses( classes = { "+sb.toString()+" } )" );
		sourceWriter.println( "void register();" );
		sourceWriter.println( "}" );
	}
}
