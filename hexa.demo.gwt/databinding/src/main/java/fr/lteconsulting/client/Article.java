package fr.lteconsulting.client;

import fr.lteconsulting.hexa.databinding.properties.Properties;
import fr.lteconsulting.hexa.databinding.tools.Property;

/**
 * The main model of the application.
 * 
 * This is a very simple POJO, augmented with notifications to
 * the binding system when property values are modified.
 * 
 * Notifications are required by the binding system in order
 * to work properly. It only involves calling the
 * NotifyPropertyChangedEvent.notify method.
 * 
 * Notifications can also be automatically generated for you
 * if you use the {@link Property} class, as in the {@link Category} class.
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
 */
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
		Properties.notify( this, "name" );
	}

	public String getWeight()
	{
		return weight;
	}

	public void setWeight( String weight )
	{
		this.weight = weight;
		Properties.notify( this, "weight" );
	}

	public Category getCategory()
	{
		return category;
	}

	public void setCategory( Category category )
	{
		this.category = category;
		Properties.notify( this, "category" );
	}

	@Override
	public String toString()
	{
		return "Article [name=" + name + ", weight=" + weight + ", category=" + category + "]";
	}
}
