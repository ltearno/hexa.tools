package com.hexa.client.classinfo;

import com.google.gwt.user.client.ui.HasValue;
import com.hexa.client.databinding.CompositePropertyAdapter;


public class ClazzUtils
{
	public static boolean HasSomethingToGetField( Clazz<?> clazz, String name )
	{
		String getterName = "get" + canon(name);
		Method getter = clazz.getMethod( getterName );
		if( getter != null )
			return true;

		// try direct field access
		Field<?> field = clazz.getField( name );
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
		String getterName = "get" + canon(name);
		Method getter = clazz.getMethod( getterName );
		if( getter != null )
			return getter.getReturnType();

		// try direct field access
		Field<?> field = clazz.getField( name );
		if( field != null )
			return field.getType();

		return null;
	}

	public static Object GetProperty( Object object, String name )
	{
		return GetProperty( object, name, true );
	}

	@SuppressWarnings( "rawtypes" )
	public static Object GetProperty( Object object, String name, boolean fTryDirectFieldAccess )
	{
		if( name.equals( CompositePropertyAdapter.HASVALUE_TOKEN ) )
			return ((HasValue) object).getValue();

		Clazz<?> s = ClassInfo.Clazz( object.getClass() );

		String getterName = "get" + canon(name);
		Method getter = s.getMethod( getterName );
		if( getter != null )
			return getter.call( object, null );

		if( ! fTryDirectFieldAccess )
		{
			assert false : "ObjectAdapter : no getter for property " + name + " and field not found !";
			return null;
		}

		// try direct field access
		Field<?> field = s.getField( name );
		if( field != null )
		{
			return field.getValue( object );
		}

		assert false : "ObjectAdapter : no getter/field for property " + name + " and field not found !";
		return null;
	}

	public static boolean HasSomethingToSetField( Clazz<?> clazz, String name )
	{
		String setterName = "set" + canon(name);
		Method setter = clazz.getMethod( setterName );
		if( setter != null )
			return true;

		// try direct field access
		Field<?> field = clazz.getField( name );
		if( field != null )
			return true;

		return false;
	}

	public static Class<?> GetSetterPropertyType( Clazz<?> clazz, String name )
	{
		String setterName = "set" + canon(name);
		Method setter = clazz.getMethod( setterName );
		if( setter!=null && setter.getParameterTypes().size()==1 )
			return setter.getParameterTypes().get( 0 );

		// try direct field access
		Field<?> field = clazz.getField( name );
		if( field != null )
			return field.getType();

		return null;
	}

	public static boolean SetProperty( Object object, String name, Object value )
	{
		return SetProperty( object, name, value, true );
	}

	@SuppressWarnings( { "unchecked", "rawtypes" } )
	public static boolean SetProperty( Object object, String name, Object value, boolean fTryDirectFieldAccess )
	{
		Clazz<?> s = ClassInfo.Clazz( object.getClass() );

		if( name.equals( CompositePropertyAdapter.HASVALUE_TOKEN ) )
		{
			((HasValue) object).setValue( value );
			return true;
		}

		String setterName = "set" + canon(name);
		Method setter = s.getMethod( setterName );
		if( setter != null )
		{
			setter.call( object, new Object[] { value } );
			return true;
		}

		if( ! fTryDirectFieldAccess )
		{
			assert false : "ObjectAdapter : no setter " + name + " found on instance of class " + object.getClass().getName();
			return false;
		}

		// try direct field access
		Field<?> field = s.getField( name );
		if( field != null )
		{
			field.setValue( object, value );
			return true;
		}

		assert false : "ObjectAdapter : no setter nor field " + name + " found on instance of class " + object.getClass().getName();
		return false;
	}

	public static String canon(String s)
	{
		return s.substring( 0, 1 ).toUpperCase() + s.substring( 1 );
	}

	public static String uncanon(String s)
	{
		return s.substring( 0, 1 ).toLowerCase() + s.substring( 1 );
	}

	public static Class<?> getBoxedType( Class<?> c )
	{
		if( c == int.class )
			return Integer.class;
		if( c == char.class )
			return Character.class;
		if( c == double.class )
			return Double.class;
		if( c == float.class )
			return Float.class;
		return c;
	}
}
