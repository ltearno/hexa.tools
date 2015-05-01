package fr.lteconsulting.hexa.client.databinding.test.dto;

import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent;

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
		NotifyPropertyChangedEvent.notify( this, "firstName" );
	}

	public void setLastName( String lastName )
	{
		this.lastName = lastName;
		NotifyPropertyChangedEvent.notify( this, "lastName" );
	}
}