package fr.lteconsulting.hexa.databinding;

import fr.lteconsulting.hexa.databinding.properties.PropertyChangedHandler;

/**
 * Interface for objects which let others subscribe to property change events
 */
public interface INotifyPropertyChanged
{
	/**
	 * Registers a handler on <i>propertyName</i> preperty of the object.<i>handler</i> will be called each
	 * time the property value changes.
	 * @param propertyName The name of the object's property to watch value for
	 * @param handler Instance of the handler that will receive notifications
	 * @return An opaque object that can be used for unregistration
	 */
	Object registerPropertyChangedEvent( String propertyName, PropertyChangedHandler handler );

	/**
	 * Unregister a property change handler
	 * @param handlerRegistration The handler to unregister from the property system
	 */
	void removePropertyChangedHandler( Object handlerRegistration );
}
