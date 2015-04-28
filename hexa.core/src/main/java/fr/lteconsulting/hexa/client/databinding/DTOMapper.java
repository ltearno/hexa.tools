package fr.lteconsulting.hexa.client.databinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;

import fr.lteconsulting.hexa.client.classinfo.ClassInfo;
import fr.lteconsulting.hexa.client.classinfo.Clazz;
import fr.lteconsulting.hexa.client.classinfo.Field;
import fr.lteconsulting.hexa.client.classinfo.Method;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.CompositePropertyAdapter;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.ObjectPropertiesUtils;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.ObjectPropertyAdapter;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.PropertyAdapter;
import fr.lteconsulting.hexa.client.databinding.tools.Property;

/**
 * A data binding utility for the support of automatic DTO binding.
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
 */
public class DTOMapper
{
	// tries to bind as much fields of source to destination and the other way
	// around
	// returns mapping resources handle that were created for this mapping
	public static Object Map( Object source, Object destination )
	{
		List<DataBinding> res = new ArrayList<DataBinding>();

		GWT.log( "Binding object of class " + getSimpleName( source.getClass() ) + " to another of class " + getSimpleName( destination.getClass() ) );

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
			boolean srcRead = ObjectPropertiesUtils.HasSomethingToGetField( ClassInfo.Clazz( source.getClass() ), name );
			boolean srcWrite = ObjectPropertiesUtils.HasSomethingToSetField( ClassInfo.Clazz( source.getClass() ), name );

			boolean destinationRead = ObjectPropertiesUtils.HasSomethingToGetField( ClassInfo.Clazz( destination.getClass() ), name );
			boolean destinationWrite = ObjectPropertiesUtils.HasSomethingToSetField( ClassInfo.Clazz( destination.getClass() ), name );

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

			GWT.log( "[" + getSimpleName( sourceAdapterInfo.dataType ) + "] " + sourceAdapterInfo.debugString + symbol + destinationAdapterInfo.debugString );

			DataBinding binding = new DataBinding( sourceAdapterInfo.adapter, destinationAdapterInfo.adapter, bindingMode, destinationAdapterInfo.converter, null );
			binding.activate();

			res.add( binding );
		}

		return res;
	}

	public static void FreeMapping( Object mappingResourceHandle )
	{
		@SuppressWarnings( "unchecked" )
		List<DataBinding> bindings = (List<DataBinding>) mappingResourceHandle;
		for( DataBinding binding : bindings )
			binding.term();
		bindings.clear();
	}

	static String getSimpleName( Class<?> cls )
	{
		String[] path = cls.getName().split( "\\." );
		return path[path.length - 1];
	}

	static class DataAdapterInfo
	{
		PropertyAdapter adapter;
		Converter converter;
		Class<?> dataType;

		String debugString;
	}

	static DataAdapterInfo createDataAdapter( Object context, String property, Class<?> srcPptyType )
	{
		DataAdapterInfo res = new DataAdapterInfo();
		res.dataType = ObjectPropertiesUtils.GetPropertyType( ClassInfo.Clazz( context.getClass() ), property );
		res.debugString = getSimpleName( context.getClass() ) + ", ";

		// test to see if the asked property is in fact a HasValue widget
		Object widget = ObjectPropertiesUtils.GetProperty( context, property );
		if( widget != null && (widget instanceof HasValue) )
		{
			// try to guess the HasValue type
			res.dataType = null;
			if( widget instanceof TextBox )
				res.dataType = String.class;

			// try to find a converter if dataType does not match srcPptyType
			if( srcPptyType != null && res.dataType != null && res.dataType != srcPptyType && srcPptyType!=Property.class )
			{
				// try to find a converter, if not : fail
				res.converter = Converters.findConverter( srcPptyType, res.dataType );
				if( res.converter == null )
					return null;

				res.debugString = "[" + getSimpleName( srcPptyType ) + ">" + getSimpleName( res.dataType ) + "] " + res.debugString;
			}

			res.debugString += "\"" + property + ".$HasValue\"";

			res.adapter = new CompositePropertyAdapter( context, property + ".$HasValue" );
		}
		else
		{
			res.debugString += "\"" + property + "\"";

			res.adapter = new ObjectPropertyAdapter( context, property );
		}

		return res;
	}
}
