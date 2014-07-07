package fr.lteconsulting.hexa.demo.client.databinding.data;

import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent;

public class Category
{
	private int id;
	private String name;
	private String color;
	
	public Category()
	{
	}

	public Category( int id, String name, String color )
	{
		super();
		this.id = id;
		this.name = name;
		this.color = color;
	}

	public int getId()
	{
		return id;
	}

	public void setId( int id )
	{
		this.id = id;
		
		NotifyPropertyChangedEvent.notify( this, "id" );
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
