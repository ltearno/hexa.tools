package fr.lteconsulting.client;

import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent;

public class Article
{
	String name;
	String weight;
	Category category;
	
	public Article()
	{
	}

	public Article( String name, String weight, Category category )
	{
		super();
		this.name = name;
		this.weight = weight;
		this.category = category;
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

	public String getWeight()
	{
		return weight;
	}

	public void setWeight( String weight )
	{
		this.weight = weight;
		NotifyPropertyChangedEvent.notify( this, "weight" );
	}

	public Category getCategory()
	{
		return category;
	}

	public void setCategory( Category category )
	{
		this.category = category;
		NotifyPropertyChangedEvent.notify( this, "category" );
	}
}
