package fr.lteconsulting.hexabinding.sample;

import fr.lteconsulting.hexa.databinding.gwt.annotation.Observable;
import fr.lteconsulting.hexa.databinding.properties.Properties;

/**
 * The main DTO class of the application will be the {@link Person} class.
 * 
 * Here is the template class that combined with the {@link Observable}
 * generates the full class.
 * 
 * It only declares methods in which we do specific processing. The others
 * will be generated.
 * 
 * The full class ({@link Person} will have a getter and a setter with Property
 * change notification for each of the fields declared here, which is very
 * practical to generate POJOs compatible with the binding system.
 * 
 * To activate Annotation Processing (on which the Observable annotation is based) in
 * your IDE, refer to the user manual. On Eclipse, the most convenient is
 * to install the m2e-apt connector for m2e.
 * 
 * @author Arnaud Tournier
 *
 */
@Observable
class PersonInternal
{
	/**
	 * The observable fields
	 */
	String firstName;
	String lastName;
	String preferredColor;
	
	public PersonInternal()
	{
	}

	public PersonInternal( String firstName, String lastName )
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.preferredColor = "grey";
	}

	/**
	 * We mimic a property "name" with this getter. This method will always return
	 * the combination of first name plus last name and thus depends on the 
	 * 'firstName' and 'lastName' properties
	 * 
	 * Note that we notify on the "name" property when a field on which the
	 * "name" depends is modified.
	 * 
	 * @return
	 */
	public String getName()
	{
		return firstName + " " + lastName;
	}

	/**
	 * Setter for first name. It will be overriden in the Person class generated
	 * by the Observable annotation.
	 * 
	 * In the overriden version of the method (in the Person class),
	 * the Properties.notify(...) method will be called for the 
	 * "firstName" property, so we don't have to call it here.
	 * 
	 * @param firstName
	 */
	public void setFirstName( String firstName )
	{
		this.firstName = firstName;

		// since the first name is part of the name,
		// we notify that the "name" changes also
		Properties.notify( this, "name" );
	}

	/**
	 * Setter for last name. It will be overriden in the Person class generated
	 * by the Observable annotation.
	 * 
	 * In the overriden version of the method (in the Person class),
	 * the Properties.notify(...) method will be called for the 
	 * "lastName" property, so we don't have to call it here.
	 * 
	 * @param firstName
	 */
	public void setLastName( String lastName )
	{
		this.lastName = lastName;

		// since the last name is part of the name,
		// we notify that the "name" changes also
		Properties.notify( this, "name" );
	}
}
