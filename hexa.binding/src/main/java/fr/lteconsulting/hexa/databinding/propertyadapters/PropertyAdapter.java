package fr.lteconsulting.hexa.databinding.propertyadapters;

import fr.lteconsulting.hexa.client.tools.Action2;

/**
 * Allows the data binding system to work with any object's property
 * 
 * A property will generally be a field in a class, or a pair of getter/setter.
 * It can also be an adapter to HasValue<T> widgets, and so on...
 * 
 * @author Arnaud Tournier
 *
 */
public interface PropertyAdapter
{
	/**
	 * Gets the Property value
	 * 
	 * @return The Property value
	 */
	public Object getValue();

	/**
	 * Sets the Property value
	 * 
	 * @param object The value to be set
	 */
	public void setValue( Object object );

	/**
	 * Registers a Property change event handler
	 * 
	 * @param callback Instance of a callback which will be called on Property value change, with the PropertyAdpater instance and cookie value
	 * @param cookie A cookie, this object will be given back in notifications
	 * @return
	 */
	public Object registerPropertyChanged( Action2<PropertyAdapter, Object> callback, Object cookie );

	/**
	 * Unregisters a handler
	 * 
	 * @param handlerRegistration The handler to be unregistered
	 */
	public void removePropertyChangedHandler( Object handlerRegistration );
}