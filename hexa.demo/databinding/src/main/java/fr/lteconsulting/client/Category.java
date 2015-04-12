package fr.lteconsulting.client;

import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent;

public class Category
{
	String name;
	String color;
	
	public Category()
	{
	}

	public Category( String name, String color )
	{
		super();
		this.name = name;
		this.color = color;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
		NotifyPropertyChangedEvent.notify( this, "name" );
	}

	public String getColor()
	{
		return color;
	}

	public void setColor( String color )
	{
		this.color = color;
		NotifyPropertyChangedEvent.notify( this, "color" );
	}
}
