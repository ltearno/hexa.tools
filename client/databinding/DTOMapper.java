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
import com.hexa.client.databinding.DataBinding.DataAdapter;


public class DTOMapper
{
	// tries to bind as much fields of source to destination and the other way around
	public static void Map( Object source, Object destination )
	{
		GWT.log( "Binding object of class " + source.getClass().getName() + " to another of class " + destination.getClass().getName() );
		
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
			
			// ensure that types match
			Class<?> srcPptyType = ClazzUtils.GetPropertyType( ClassInfo.Clazz( source.getClass() ), name );
			Class<?> destPptyType = ClazzUtils.GetPropertyType( ClassInfo.Clazz( destination.getClass() ), name );
			
			Converter converter = null;
			DataAdapter destinationAdapter = null;
			// test if the destination is a widget and has the HasValue interface... we do that kind of blindly !
			Object widget = ClazzUtils.GetProperty( destination, name );
			if( widget != null && (widget instanceof HasValue) )
			{
				// the destination is a HasValue<T> widget
				// we hope in that case that T is the same as srcPptyType, but we cannot know for sure right now...
				
				// try to guess the HasValue type
				Class<?> hasValueType = null;
				if( widget instanceof TextBox )
					hasValueType = String.class;
				
				if( hasValueType!=null && hasValueType!=srcPptyType )
				{
					// try to find a converter
					converter = Converters.findConverter( srcPptyType, hasValueType );
					if( converter == null )
						continue;
				}
				
				destinationAdapter = new WidgetAdapter( widget );
			}
			else
			{
				if( srcPptyType != destPptyType )
					continue;
				
				destinationAdapter = new ObjectAdapter( destination, name );
			}
			
			GWT.log( "Binding property " + name );
			
			ObjectAdapter sourceAdapter = new ObjectAdapter( source, name );
			
			DataBinding binding = new DataBinding( sourceAdapter, destinationAdapter, bindingMode, converter );
			binding.activate();
		}
	}
}
