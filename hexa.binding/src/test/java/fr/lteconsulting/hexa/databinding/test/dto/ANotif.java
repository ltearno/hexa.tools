package fr.lteconsulting.hexa.databinding.test.dto;

import fr.lteconsulting.hexa.databinding.NotifyPropertyChangedEvent;

public class ANotif
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
		NotifyPropertyChangedEvent.notify( this, "firstName" );
	}

	public void setLastName( String lastName )
	{
		this.lastName = lastName;
		NotifyPropertyChangedEvent.notify( this, "lastName" );
	}
}