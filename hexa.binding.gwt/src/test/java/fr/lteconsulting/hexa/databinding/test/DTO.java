package fr.lteconsulting.hexa.databinding.test;

import fr.lteconsulting.hexa.databinding.properties.Properties;

public class DTO
{
	private String name;

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
		Properties.notify( this, "name" );
	}
}