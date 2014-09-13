package fr.lteconsulting.hexa.rebind;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public abstract class AbstractGenerator extends Generator
{
	// Context and logger for code generation
	protected TreeLogger logger = null;
	protected GeneratorContext context = null;
	TypeOracle typeOracle;
	
	protected String typeName = null;
	protected JClassType type = null;
	
	protected abstract String generate();

	@Override
	public String generate( TreeLogger logger, GeneratorContext context, String typeName ) throws UnableToCompleteException
	{
		this.logger = logger;
		this.context = context;
		this.typeName = typeName;
		
		if( typeName == null )
			return null;
		
		typeOracle = context.getTypeOracle();
		
		try
		{
			type = typeOracle.getType( typeName );
		}
		catch( NotFoundException e )
		{
			e.printStackTrace();
			throw new RuntimeException( e );
		}
		
		return generate();
	}	
}
