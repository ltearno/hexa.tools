package com.hexa.client.databinding;

import java.util.HashSet;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.hexa.client.classinfo.ClassInfo;
import com.hexa.client.classinfo.Clazz;
import com.hexa.client.classinfo.ClazzUtils;
import com.hexa.client.classinfo.Field;
import com.hexa.client.classinfo.Method;
import com.hexa.client.databinding.propertyadapters.PropertyAdapter;
import com.hexa.client.databinding.propertyadapters.ObjectPropertyAdapter;


public class DTOMapper
{
	// tries to bind as much fields of source to destination and the other way around
	public static void Map( Object source, Object destination )
	{
		GWT.log( "Binding object of class " + getSimpleName(source.getClass()) + " to another of class " + getSimpleName(destination.getClass()) );

		Clazz<?> sourceClass = ClassInfo.Clazz( source.getClass() );
		Clazz<?> destinationClass = ClassInfo.Clazz( destination.getClass() );

		// registers all possible bindings...
		HashSet<String> bindedNames = new HashSet<String>();

		// fields wise...
		for( Field<?> field : sourceClass.getFields() )
			bindedNames.add( field.getName() );
		for( Field<?> field : destinationClass.getFields() )
			bindedNames.add( field.getName() );

		// ... and method wise
		for( Method method : sourceClass.getMethods() )
		{
			if( ! method.getName().startsWith( "get" ) && ! method.getName().startsWith( "set" ) )
				continue;

			String fieldName = method.getName().substring( 3, 4 ).toLowerCase() + method.getName().substring( 4 );
			bindedNames.add( fieldName );
		}
		for( Method method : destinationClass.getMethods() )
		{
			if( ! method.getName().startsWith( "get" ) && ! method.getName().startsWith( "set" ) )
				continue;

			String fieldName = method.getName().substring( 3, 4 ).toLowerCase() + method.getName().substring( 4 );
			bindedNames.add( fieldName );
		}

		for( String name : bindedNames )
		{
			boolean srcRead = ClazzUtils.HasSomethingToGetField( ClassInfo.Clazz( source.getClass() ), name );
			boolean srcWrite = ClazzUtils.HasSomethingToSetField( ClassInfo.Clazz( source.getClass() ), name );

			boolean destinationRead = ClazzUtils.HasSomethingToGetField( ClassInfo.Clazz( destination.getClass() ), name );
			boolean destinationWrite = ClazzUtils.HasSomethingToSetField( ClassInfo.Clazz( destination.getClass() ), name );

			// ensure both have necessary methods or field
			if( ! srcRead || ! destinationWrite )
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
			case OneWay: symbol = "----->"; break;
			case TwoWay: symbol = "<---->"; break;
			case OneWayToSource: symbol = "<-----"; break;
			}

			GWT.log( "[" + getSimpleName(sourceAdapterInfo.dataType) + "] " + sourceAdapterInfo.debugString + symbol + destinationAdapterInfo.debugString );

			DataBinding binding = new DataBinding( sourceAdapterInfo.adapter, destinationAdapterInfo.adapter, bindingMode, destinationAdapterInfo.converter );
			binding.activate();
		}
	}

	static String getSimpleName( Class<?> cls )
	{
		String[] path = cls.getName().split( "\\." );
		return path[path.length-1];
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
		res.dataType = ClazzUtils.GetPropertyType( ClassInfo.Clazz( context.getClass() ), property );
		res.debugString = getSimpleName(context.getClass()) + ", ";

		// test to see if the asked property is in fact a HasValue widget
		Object widget = ClazzUtils.GetProperty( context, property );
		if( widget != null && (widget instanceof HasValue) )
		{
			// try to guess the HasValue type
			res.dataType = null;
			if( widget instanceof TextBox )
				res.dataType = String.class;

			// try to find a converter if dataType does not match srcPptyType
			if( srcPptyType!=null && res.dataType!=null && res.dataType!=srcPptyType )
			{
				// try to find a converter, if not : fail
				res.converter = Converters.findConverter( srcPptyType, res.dataType );
				if( res.converter == null )
					return null;

				res.debugString = "["+getSimpleName(srcPptyType)+">"+getSimpleName(res.dataType)+"] " + res.debugString;
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
