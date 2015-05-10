package fr.lteconsulting.hexa.databinding.properties;

import fr.lteconsulting.hexa.classinfo.Clazz;

/**
 * Utility class supporting the concept of Property.
 * 
 * A Property on an object is a value that can be get and/or set through either
 * a getter/setter or directly through the object's field.
 * 
 * @author Arnaud Tournier (c) LTE Consulting - 2015 http://www.lteconsulting.fr
 *
 */
public class Properties
{
	private final static PropertyValues propertyValues = new PropertyValues();
	private final static PropertyChanges propertyChanges = new PropertyChanges();

	/**
	 * Returns the class of the property
	 * 
	 * @param clazz
	 * @param name
	 * @return
	 */
	public static Class<?> getPropertyType( Clazz<?> clazz, String name )
	{
		return propertyValues.getPropertyType(clazz, name);
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
	public static <T> T getValue( Object object, String name )
	{
		return propertyValues.getValue(object, name);
	}

	/**
	 * Sets a value on an object's property
	 * 
	 * @param object the object on which the property is set
	 * @param propertyName the name of the property value to be set
	 * @param value the new value of the property
	 */
	public static boolean setValue( Object object, String propertyName, Object value )
	{
		return propertyValues.setValue(object, propertyName, value);
	}

	/**
	 * Registers an handler for a specific property change on an object. The object
	 * itself does not need to implement anything. But at least it should call the 
	 * {@link notify(Object, String)} method when its internal property changes.
	 * 
	 * @param source The object from which one wants notifications
	 * @param propertyName The property subscribed. You can use "*" to subscribe to all properties in one time
	 * @param handler
	 * @return
	 */
	public static Object register( Object source, String propertyName, PropertyChangedHandler handler )
	{
		return propertyChanges.register(source, propertyName, handler);
	}

	/**
	 * Unregisters a handler, freeing associated resources
	 * 
	 * @param handlerRegistration The object received after a call to {@link PropertyChanges}
	 */
	public static void removeHandler( Object handlerRegistration )
	{
		propertyChanges.removeHandler(handlerRegistration);
	}

	/**
	 * Notifies the Hexa event system of an object changing one of
	 * its properties.
	 * 
	 * @param sender The object whom property changed
	 * @param propertyName The changed property name
	 */
	public static void notify( Object sender, String propertyName )
	{
		propertyChanges.notify( sender, propertyName );
	}

	/**
	 * Obtain useful information for debugging. That's useful
	 * to detect registration leaks.
	 */
	public static String getStatistics()
	{
		return propertyChanges.getStatistics();
	}

	/**
	 * Whether a getter or a field is available with that name
	 * 
	 * @param clazz
	 * @param name
	 * @return
	 */
	public static boolean hasSomethingToGetField( Clazz<?> clazz, String name )
	{
		return propertyValues.hasSomethingToGetField(clazz, name);
	}

	/**
	 * Return the property getter type
	 * 
	 * @param clazz
	 * @param name
	 * @return
	 */
	public static Class<?> getGetterPropertyType( Clazz<?> clazz, String name )
	{
		return propertyValues.getGetterPropertyType(clazz, name);
	}

	/**
	 * Whether there is a setter or a field to write this property
	 */
	public static boolean hasSomethingToSetField( Clazz<?> clazz, String name )
	{
		return propertyValues.hasSomethingToSetField(clazz, name);
	}

	/**
	 * Returns the class of the setter property. It can be the class of the first 
	 * argument in the setter or the class of the field if no setter is found.
	 * If a virtual property is used, it returns null or the class of the current
	 * property's value
	 */
	public static Class<?> getSetterPropertyType( Clazz<?> clazz, String name )
	{
		return propertyValues.getSetterPropertyType(clazz, name);
	}

	/**
	 * Gets a dynamic property value on an object
	 * 
	 * @param object the object from which one wants to get the property value
	 * @param propertyName the property name
	 */
	public static <T> T getObjectDynamicProperty( Object object, String propertyName )
	{
		return propertyValues.getObjectDynamicProperty(object, propertyName);
	}

	/**
	 * Whether a dynamic property value has already been set on this object
	 */
	public static boolean hasObjectDynamicProperty( Object object, String propertyName )
	{
		return propertyValues.hasObjectDynamicProperty(object, propertyName);
	}

	/**
	 * Sets a dynamic property value on an object.
	 */
	public static void setObjectDynamicProperty( Object object, String propertyName, Object value )
	{
		propertyValues.setObjectDynamicProperty(object, propertyName, value);
	}
}
