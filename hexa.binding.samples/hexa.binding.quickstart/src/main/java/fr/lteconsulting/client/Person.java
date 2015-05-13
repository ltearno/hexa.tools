package fr.lteconsulting.client;

import java.lang.String;

import fr.lteconsulting.hexa.databinding.properties.Properties;

public class Person
{
	private String name;

	public void setName( String name )
	{
		this.name = name;
		Properties.notify( this, "name" );
	}

	public String getName()
	{
		return this.name;
	}
}
