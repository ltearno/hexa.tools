package fr.lteconsulting.hexa.rebind.classinfo;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public class ClazzGenerator extends Generator
{
	// Context and logger for code generation
	TreeLogger logger;
	GeneratorContext context;

	// asked type name
	String askedTypeName;

	// type for which we provide information
	JClassType reflectedType;

	// generated class name
	String generatedClassName;

	private JClassType getReflectedType( TypeOracle typeOracle, String askedTypeName ) throws UnableToCompleteException
	{
		JClassType askedType;
		try
		{
			askedType = typeOracle.getType( askedTypeName );
		}
		catch( NotFoundException e )
		{
			throw new UnableToCompleteException();
		}

		for( JClassType classType : askedType.getImplementedInterfaces() )
		{
			if( !classType.getQualifiedSourceName().equals( "fr.lteconsulting.hexa.classinfo.Clazz" ) )
				continue;

			JParameterizedType parametrized = classType.isParameterized();
			JClassType[] typeArgs = parametrized.getTypeArgs();

			return typeArgs[0];
		}

		throw new UnableToCompleteException();
	}

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
			reflectedType = getReflectedType( typeOracle, typeName );
			ClazzInfoBuilder builder = new ClazzInfoBuilder( logger, context );

			return builder.buildClassInfoFor(reflectedType);
		}
		catch( Exception e )
		{
			// record to logger that Map generation threw an exception
			logger.log( TreeLogger.ERROR, "ERROR when generating " + generatedClassName + " for " + typeName, e );
			return null;
		}
	}
}
