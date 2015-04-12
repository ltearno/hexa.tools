package fr.lteconsulting.hexa.client.databinding.propertyadapters;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HasValue;

import fr.lteconsulting.hexa.client.classinfo.ClassInfo;
import fr.lteconsulting.hexa.client.classinfo.Clazz;
import fr.lteconsulting.hexa.client.classinfo.Field;
import fr.lteconsulting.hexa.client.classinfo.Method;
import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent;
import fr.lteconsulting.hexa.client.databinding.tools.Property;

public class ObjectPropertiesUtils
{
	public static boolean HasSomethingToGetField( Clazz<?> clazz, String name )
	{
		String getterName = "get" + canon( name );
		Method getter = clazz.getMethod( getterName );
		if( getter != null )
			return true;

		// try direct field access
		Field field = clazz.getAllField( name );
		if( field != null )
			return true;

		return false;
	}

	public static Class<?> GetPropertyType( Clazz<?> clazz, String name )
	{
		Class<?> getterType = GetGetterPropertyType( clazz, name );
		Class<?> setterType = GetSetterPropertyType( clazz, name );

		if( getterType == setterType )
			return getterType;

		return null;
	}

	public static Class<?> GetGetterPropertyType( Clazz<?> clazz, String name )
	{
		String getterName = "get" + canon( name );
		Method getter = clazz.getMethod( getterName );
		if( getter != null )
			return getter.getReturnType();

		// try direct field access
		Field field = clazz.getAllField( name );
		if( field != null )
			return field.getType();

		return null;
	}

	public static Object GetProperty( Object object, String name )
	{
		return GetProperty( object, name, true );
	}

	public interface PropertyGetter
	{
		Object getPropertyValue();
	}

	@SuppressWarnings( "unchecked" )
	public static Object GetProperty( Object object, String name, boolean fTryDirectFieldAccess )
	{
		Object result = GetPropertyImpl( object, name, fTryDirectFieldAccess );
		if( result instanceof Property )
			return ((Property<Object>) result).getValue();

		return result;
	}

	@SuppressWarnings( "rawtypes" )
	private static Object GetPropertyImpl( Object object, String name, boolean fTryDirectFieldAccess )
	{
		if( name.equals( CompositePropertyAdapter.HASVALUE_TOKEN ) )
			return ((HasValue) object).getValue();

		if( name.equals( CompositePropertyAdapter.DTOMAP_TOKEN ) )
			throw new RuntimeException( "Property of type $DTOMap cannot be readden !" );

		// if has dynamic-property, return it !
		if( hasObjectDynamicProperty( object, name ) )
		{
			GWT.log( "DataBinding: Uses dynamic property read '" + name + "' for object " + object );
			return getObjectDynamicProperty( object, name );
		}

		Clazz<?> s = ClassInfo.Clazz( object.getClass() );

		String getterName = "get" + canon( name );
		Method getter = s.getMethod( getterName );
		if( getter != null )
		{
			try
			{
				return getter.invoke( object );
			}
			catch( Exception e )
			{
				throw new RuntimeException( "ObjectAdapter [object]." + object.getClass().getName() + "." + getterName + "() : getter call throwed an exception. See cause.", e );
			}
		}

		if( fTryDirectFieldAccess )
		{
			// try direct field access
			Field field = s.getAllField( name );
			if( field != null )
				return field.getValue( object );
		}

		// Maybe a dynamic property will be set later on
		GWT.log( "DataBinding: Warning: assuming that the object would in the future have a dynamic property set / Maybe have an opt-in option on the Binding to clarify things" );
		//assert false : "ObjectAdapter (" + object.getClass().getName() + ") : no getter/field for property " + name + " and field not found !";
		return null;
	}

	public static boolean HasSomethingToSetField( Clazz<?> clazz, String name )
	{
		String setterName = "set" + canon( name );
		Method setter = clazz.getMethod( setterName );
		if( setter != null )
			return true;

		// try direct field access
		Field field = clazz.getAllField( name );
		if( field != null )
			return true;

		return false;
	}

	public static Class<?> GetSetterPropertyType( Clazz<?> clazz, String name )
	{
		String setterName = "set" + canon( name );
		Method setter = clazz.getMethod( setterName );
		if( setter != null && setter.getParameterTypes().size() == 1 )
			return setter.getParameterTypes().get( 0 );

		// try direct field access
		Field field = clazz.getAllField( name );
		if( field != null )
			return field.getType();
		return null;
	}

	public static boolean SetProperty( Object object, String name, Object value )
	{
		return SetProperty( object, name, value, true );
	}

	public static boolean SetProperty( Object object, String name, Object value, boolean fTryDirectFieldAccess )
	{
		Clazz<?> s = ClassInfo.Clazz( object.getClass() );

		if( Property.class == GetPropertyType( s, name ) )
		{
			@SuppressWarnings( "unchecked" )
			Property<Object> property = (Property<Object>) GetPropertyImpl( object, name, fTryDirectFieldAccess );
			if( property != null )
			{
				property.setValue( value );
				return true;
			}
			return false;
		}

		return SetPropertyImpl( s, object, name, value, fTryDirectFieldAccess );
	}

	@SuppressWarnings( { "unchecked", "rawtypes" } )
	private static boolean SetPropertyImpl( Clazz<?> s, Object object, String name, Object value, boolean fTryDirectFieldAccess )
	{
		if( name.equals( CompositePropertyAdapter.HASVALUE_TOKEN ) )
		{
			((HasValue) object).setValue( value, true );
			return true;
		}

		String setterName = "set" + canon( name );
		Method setter = s.getMethod( setterName );
		if( setter != null )
		{
			if( setter.getParameterTypes().get( 0 ) == Property.class )
			{
			}
			setter.invoke( object, value );
			return true;
		}

		if( fTryDirectFieldAccess )
		{
			// try direct field access
			Field field = s.getAllField( name );
			if( field != null )
			{
				field.setValue( object, value );
				return true;
			}
		}
		
		GWT.log( "DataBinding: Uses dynamic property write '" + name + "' for object " + object + " of class " + object.getClass().getName() + " with value " + value + " WARNING : THAT MEANS THERE IS NO GETTER/SETTER/FIELD FOR THAT CLASS ! PLEASE CHECK THAT IT IS REALLY INTENTIONAL !");
		setObjectDynamicProperty( object, name, value );

		//assert false : "ObjectAdapter : no setter nor field " + name + " found on instance of class " + object.getClass().getName();
		return false;
	}

	public static String canon( String s )
	{
		return s.substring( 0, 1 ).toUpperCase() + s.substring( 1 );
	}

	public static String uncanon( String s )
	{
		return s.substring( 0, 1 ).toLowerCase() + s.substring( 1 );
	}

	private native static void setObjectDynamicPropertyBag( Object object, DynamicPropertyBag bag )
	/*-{
		object.__hexa_dynamic_ppty_bag = bag;
	}-*/;

	private native static DynamicPropertyBag getObjectDynamicPropertyBag( Object object )
	/*-{
		return object.__hexa_dynamic_ppty_bag || null;
	}-*/;

	@SuppressWarnings( "unchecked" )
	public static <T> T getObjectDynamicProperty( Object object, String propertyName )
	{
		DynamicPropertyBag bag = getObjectDynamicPropertyBag( object );
		if( bag == null )
			return null;

		return (T) bag.get( propertyName );
	}
	
	public static boolean hasObjectDynamicProperty( Object object, String propertyName )
	{
		DynamicPropertyBag bag = getObjectDynamicPropertyBag( object );
		if( bag == null )
			return false;

		return bag.contains( propertyName );
	}

	public static void setObjectDynamicProperty( Object object, String propertyName, Object value )
	{
		DynamicPropertyBag bag = getObjectDynamicPropertyBag( object );
		if( bag == null )
		{
			bag = new DynamicPropertyBag();
			setObjectDynamicPropertyBag( object, bag );
		}

		bag.set( propertyName, value );
		
		NotifyPropertyChangedEvent.notify( object, propertyName );
	}

	private static class DynamicPropertyBag
	{
		private HashMap<String, Object> map;

		void set( String propertyName, Object value )
		{
			if( map == null )
				map = new HashMap<>();

			map.put( propertyName, value );
		}

		Object get( String propertyName )
		{
			if( map == null )
				return null;

			return map.get( propertyName );
		}
		
		boolean contains( String propertyName )
		{
			if( map == null )
				return false;
			
			return map.containsKey( propertyName );
		}
	}
}
