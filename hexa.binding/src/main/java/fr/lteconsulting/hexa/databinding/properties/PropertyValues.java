package fr.lteconsulting.hexa.databinding.properties;

import java.util.logging.Logger;

import fr.lteconsulting.hexa.classinfo.ClassInfo;
import fr.lteconsulting.hexa.classinfo.Clazz;
import fr.lteconsulting.hexa.classinfo.Field;
import fr.lteconsulting.hexa.classinfo.Method;
import fr.lteconsulting.hexa.databinding.PlatformSpecific;
import fr.lteconsulting.hexa.databinding.PlatformSpecificProvider;
import fr.lteconsulting.hexa.databinding.propertyadapters.CompositePropertyAdapter;
import fr.lteconsulting.hexa.databinding.tools.Property;

class PropertyValues
{
	private final static Logger LOGGER = Logger.getLogger( PropertyValues.class.getName() );

	private final static PlatformSpecific propertyBagAccess = PlatformSpecificProvider.get();

	/**
	 * Returns the class of the property
	 * 
	 * @param clazz
	 * @param name
	 * @return
	 */
	Class<?> getPropertyType( Clazz<?> clazz, String name )
	{
		Class<?> getterType = getGetterPropertyType( clazz, name );
		Class<?> setterType = getSetterPropertyType( clazz, name );
	
		if( getterType == setterType )
			return getterType;
	
		return null;
	}

	/**
	 * Whether a getter or a field is available with that name
	 * 
	 * @param clazz
	 * @param name
	 * @return
	 */
	boolean hasSomethingToGetField( Clazz<?> clazz, String name )
	{
		return getGetterPropertyType( clazz, name ) != null;
	}

	/**
	 * Return the property getter type
	 * 
	 * @param clazz
	 * @param name
	 * @return
	 */
	Class<?> getGetterPropertyType( Clazz<?> clazz, String name )
	{
		String getterName = "get" + capitalizeFirstLetter( name );
		Method getter = clazz.getMethod( getterName );
		if( getter != null )
			return getter.getReturnType();

		// try direct field access
		Field field = clazz.getAllField( name );
		if( field != null )
			return field.getType();

		return null;
	}

	/**
	 * Gets the property's value from an object
	 * 
	 * @param object
	 *            The object
	 * @param name
	 *            Property name
	 * @return
	 */
	<T> T getProperty( Object object, String name )
	{
		T result = getPropertyImpl( object, name );
		if( result instanceof Property )
		{
			@SuppressWarnings( "unchecked" )
			Property<T> property = ((Property<T>) result);
			return property.getValue();
		}

		return result;
	}

	/**
	 * Whether there is a setter or a field to write this property
	 */
	boolean hasSomethingToSetField( Clazz<?> clazz, String name )
	{
		return getSetterPropertyType( clazz, name ) != null;
	}

	/**
	 * Returns the class of the setter property. It can be this of the setter or
	 * of the field
	 */
	Class<?> getSetterPropertyType( Clazz<?> clazz, String name )
	{
		String setterName = "set" + capitalizeFirstLetter( name );
		Method setter = clazz.getMethod( setterName );
		if( setter != null && setter.getParameterTypes().size() == 1 )
			return setter.getParameterTypes().get( 0 );

		Field field = clazz.getAllField( name );
		if( field != null )
			return field.getType();

		return null;
	}

	/**
	 * Sets a value on an object's property
	 * 
	 * @param object the object on which the property is set
	 * @param propertyName the name of the property value to be set
	 * @param value the new value of the property
	 */
	boolean setProperty( Object object, String propertyName, Object value )
	{
		Clazz<?> s = ClassInfo.Clazz( object.getClass() );

		if( Property.class == getPropertyType( s, propertyName ) )
		{
			@SuppressWarnings( "unchecked" )
			Property<Object> property = (Property<Object>) getPropertyImpl( object, propertyName );
			if( property != null )
			{
				property.setValue( value );
				return true;
			}
			
			return false;
		}

		return setPropertyImpl( s, object, propertyName, value );
	}

	/**
	 * Gets a dynamic property value on an object
	 * 
	 * @param object the object from which one wants to get the property value
	 * @param propertyName the property name
	 */
	<T> T getObjectDynamicProperty( Object object, String propertyName )
	{
		DynamicPropertyBag bag = propertyBagAccess.getObjectDynamicPropertyBag( object );
		if( bag == null )
			return null;

		@SuppressWarnings( "unchecked" )
		T result = (T) bag.get( propertyName );

		return result;
	}

	/**
	 * Whether a dynamic property value has already been set on this object
	 */
	boolean hasObjectDynamicProperty( Object object, String propertyName )
	{
		DynamicPropertyBag bag = propertyBagAccess.getObjectDynamicPropertyBag( object );
		if( bag == null )
			return false;

		return bag.contains( propertyName );
	}

	/**
	 * Sets a dynamic property value on an object.
	 */
	void setObjectDynamicProperty( Object object, String propertyName, Object value )
	{
		DynamicPropertyBag bag = propertyBagAccess.getObjectDynamicPropertyBag( object );
		if( bag == null )
		{
			bag = new DynamicPropertyBag();
			propertyBagAccess.setObjectDynamicPropertyBag( object, bag );
		}

		bag.set( propertyName, value );

		Properties.notify( object, propertyName );
	}

	private <T> T getPropertyImpl( Object object, String name )
	{
		if( PlatformSpecificProvider.get().isBindingToken( name ) )
		{
			return PlatformSpecificProvider.get().getBindingValue( object, name );
		}

		if( name.equals( CompositePropertyAdapter.DTOMAP_TOKEN ) )
			throw new RuntimeException( "Property of type $DTOMap cannot be readden !" );

		// if has dynamic-property, return it !
		if( hasObjectDynamicProperty( object, name ) )
		{
			LOGGER.fine( "'" + name + "' read dynamic property on object " + object );
			return getObjectDynamicProperty( object, name );
		}

		Clazz<?> s = ClassInfo.Clazz( object.getClass() );

		String getterName = "get" + capitalizeFirstLetter( name );
		Method getter = s.getMethod( getterName );
		if( getter != null )
		{
			try
			{
				@SuppressWarnings( "unchecked" )
				T result = (T) getter.invoke( object );
				return result;
			}
			catch( Exception e )
			{
				throw new RuntimeException( "ObjectAdapter [object]." + object.getClass().getName() + "." + getterName + "() : getter call throwed an exception. See cause.", e );
			}
		}

		// try direct field access
		Field field = s.getAllField( name );
		if( field != null )
			return field.getValue( object );

		// Maybe a dynamic property will be set later on
		LOGGER.warning( "DataBinding: Warning: assuming that the object would in the future have a dynamic property set / Maybe have an opt-in option on the Binding to clarify things" );

		return null;
	}

	private boolean setPropertyImpl( Clazz<?> s, Object object, String name, Object value )
	{
		if( PlatformSpecificProvider.get().isBindingToken( name ) )
			return PlatformSpecificProvider.get().setBindingValue( object, name, value );

		String setterName = "set" + capitalizeFirstLetter( name );
		Method setter = s.getMethod( setterName );
		if( setter != null )
		{
			setter.invoke( object, value );
			return true;
		}

		Field field = s.getAllField( name );
		if( field != null )
		{
			field.setValue( object, value );
			return true;
		}

		if( !hasObjectDynamicProperty( object, name ) )
			LOGGER.warning( "'" + name + "' write dynamic property on object " + object.getClass().getName() + " with value " + value + " WARNING : THAT MEANS THERE IS NO GETTER/SETTER/FIELD FOR THAT CLASS ! PLEASE CHECK THAT IT IS REALLY INTENTIONAL !" );

		setObjectDynamicProperty( object, name, value );

		return false;
	}

	private String capitalizeFirstLetter( String s )
	{
		return s.substring( 0, 1 ).toUpperCase() + s.substring( 1 );
	}
}
