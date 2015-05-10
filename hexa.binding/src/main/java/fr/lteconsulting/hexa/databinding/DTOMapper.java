package fr.lteconsulting.hexa.databinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import fr.lteconsulting.hexa.classinfo.ClassInfo;
import fr.lteconsulting.hexa.classinfo.Clazz;
import fr.lteconsulting.hexa.classinfo.Field;
import fr.lteconsulting.hexa.classinfo.Method;
import fr.lteconsulting.hexa.databinding.properties.Properties;
import fr.lteconsulting.hexa.databinding.propertyadapters.ObjectPropertyAdapter;

/**
 * A data binding utility for the support of automatic DTO binding.
 * 
 * @author Arnaud Tournier (c) LTE Consulting - 2015 http://www.lteconsulting.fr
 *
 */
public class DTOMapper
{
	private static final Logger LOGGER = Logger.getLogger( DTOMapper.class.getName() );

	// tries to bind as much fields of source to destination and the other way
	// around
	// returns mapping resources handle that were created for this mapping
	public static Object Map( Object source, Object destination )
	{
		List<DataBinding> res = new ArrayList<DataBinding>();

		LOGGER.fine( "Binding object of class " + getSimpleName( source.getClass() ) + " to another of class " + getSimpleName( destination.getClass() ) );

		Clazz<?> sourceClass = ClassInfo.Clazz( source.getClass() );
		Clazz<?> destinationClass = ClassInfo.Clazz( destination.getClass() );

		// registers all possible bindings...
		HashSet<String> bindedNames = new HashSet<String>();

		// fields wise...
		for( Field field : sourceClass.getAllFields() )
			bindedNames.add( field.getName() );
		for( Field field : destinationClass.getAllFields() )
			bindedNames.add( field.getName() );

		// ... and method wise
		for( Method method : sourceClass.getMethods() )
		{
			if( !method.getName().startsWith( "get" ) && !method.getName().startsWith( "set" ) )
				continue;

			String fieldName = method.getName().substring( 3, 4 ).toLowerCase() + method.getName().substring( 4 );
			bindedNames.add( fieldName );
		}
		for( Method method : destinationClass.getMethods() )
		{
			if( !method.getName().startsWith( "get" ) && !method.getName().startsWith( "set" ) )
				continue;

			String fieldName = method.getName().substring( 3, 4 ).toLowerCase() + method.getName().substring( 4 );
			bindedNames.add( fieldName );
		}

		for( String name : bindedNames )
		{
			boolean srcRead = Properties.hasSomethingToGetField( ClassInfo.Clazz( source.getClass() ), name );
			boolean srcWrite = Properties.hasSomethingToSetField( ClassInfo.Clazz( source.getClass() ), name );

			boolean destinationRead = Properties.hasSomethingToGetField( ClassInfo.Clazz( destination.getClass() ), name );
			boolean destinationWrite = Properties.hasSomethingToSetField( ClassInfo.Clazz( destination.getClass() ), name );

			// ensure both have necessary methods or field
			if( !srcRead || !destinationWrite )
				continue; // bypass

			// adjust binding mode according to capabilities
			Mode bindingMode = Mode.OneWay;
			if( srcWrite && destinationRead )
				bindingMode = Mode.TwoWay;

			DataAdapterInfo sourceAdapterInfo = createDataAdapter( source, name, null );
			if( sourceAdapterInfo == null )
				continue;

			DataAdapterInfo destinationAdapterInfo = createDataAdapter( destination, name, sourceAdapterInfo.dataType );
			if( destinationAdapterInfo == null )
				continue;

			// bind source, "color" <----> destination, "color.$HasValue"
			String symbol = "";
			switch( bindingMode )
			{
				case OneWay:
					symbol = "----->";
					break;
				case TwoWay:
					symbol = "<---->";
					break;
				case OneWayToSource:
					symbol = "<-----";
					break;
			}

			LOGGER.fine( "[" + getSimpleName( sourceAdapterInfo.dataType ) + "] " + sourceAdapterInfo.debugString + symbol + destinationAdapterInfo.debugString );

			DataBinding binding = new DataBinding( sourceAdapterInfo.adapter, destinationAdapterInfo.adapter, bindingMode, destinationAdapterInfo.converter, null );
			binding.activate();

			res.add( binding );
		}

		return res;
	}

	public static void freeMapping( Object mappingResourceHandle )
	{
		@SuppressWarnings( "unchecked" )
		List<DataBinding> bindings = (List<DataBinding>) mappingResourceHandle;
		for( DataBinding binding : bindings )
			binding.terminate();
		bindings.clear();
	}

	static String getSimpleName( Class<?> cls )
	{
		String[] path = cls.getName().split( "\\." );
		return path[path.length - 1];
	}

	static DataAdapterInfo createDataAdapter( Object context, String property, Class<?> srcPptyType )
	{
		DataAdapterInfo res = new DataAdapterInfo();
		res.dataType = Properties.getPropertyType( ClassInfo.Clazz( context.getClass() ), property );
		res.debugString = getSimpleName( context.getClass() ) + ", ";

		// test to see if the asked property is in fact a HasValue widget
		Object widget = Properties.getValue( context, property );
		if( PlatformSpecificProvider.get().isSpecificDataAdapter( widget ) )
		{
			PlatformSpecificProvider.get().fillSpecificDataAdapter( widget, context, property, srcPptyType, res );
		}
		else
		{
			res.debugString += "\"" + property + "\"";

			res.adapter = new ObjectPropertyAdapter( context, property );
		}

		if( res.adapter == null )
			return null;

		return res;
	}
}
