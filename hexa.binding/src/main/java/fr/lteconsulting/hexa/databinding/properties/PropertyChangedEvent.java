package fr.lteconsulting.hexa.databinding.properties;

/**
 * Holds necessary information to process a property changed event
 * 
 * @author Arnaud Tournier
 */
public class PropertyChangedEvent
{
	private final Object sender;

	private final String propertyName;

	/**
	 * Constructor, can only be created by the
	 * {@link notify} method.
	 * @param sender
	 * @param propertyName
	 */
	PropertyChangedEvent( Object sender, String propertyName )
	{
		this.sender = sender;
		this.propertyName = propertyName;
	}

	/**
	 * Returns the object that sent the event
	 * 
	 * @return The object that sent the event
	 */
	public Object getSender()
	{
		return sender;
	}

	/**
	 * Returns the name of the property that changed
	 * @return The name of the property that changed
	 */
	public String getPropertyName()
	{
		return propertyName;
	}
}
