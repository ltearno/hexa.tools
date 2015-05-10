package fr.lteconsulting.hexabinding.sample;

import fr.lteconsulting.hexa.databinding.annotation.Observable;
import fr.lteconsulting.hexa.databinding.properties.Properties;

/**
 * The main DTO class of the application.
 * 
 * If has several fields and getters. The setters call the Properties.notify()
 * method in order for the bindings to work
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

	public PersonInternal( String firstName, String lastName )
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.preferredColor = "grey";
	}

	public void setFirstName( String firstName )
	{
		this.firstName = firstName;

		// since the first name is part of the name,
		// we notify that the "name" changes also
		Properties.notify( this, "name" );
	}

	public void setLastName( String lastName )
	{
		this.lastName = lastName;

		// since the last name is part of the name,
		// we notify that the "name" changes also
		Properties.notify( this, "name" );
	}

	/**
	 * We mimic a property "name" with this getter.
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
}
