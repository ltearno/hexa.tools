package fr.lteconsulting.hexa.databinding.test.dto;

import fr.lteconsulting.hexa.databinding.PropertyChanges;

public class BNotif
{
	private String firstName;

	private String lastName;

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setFirstName( String firstName )
	{
		this.firstName = firstName;
		PropertyChanges.notify( this, "firstName" );
	}

	public void setLastName( String lastName )
	{
		this.lastName = lastName;
		PropertyChanges.notify( this, "lastName" );
	}
}