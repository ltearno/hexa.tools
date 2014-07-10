package fr.lteconsulting.hexa.persistence.client.legacy.persistence;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Article
{
	@Id
	int id;

	String name;

	@ManyToOne( fetch = FetchType.LAZY, cascade = { CascadeType.MERGE } )
	@JoinColumn( name="category_id" )
	Category category;

	int price;

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public Category getCategory()
	{
		return category;
	}

	public void setCategory( Category category )
	{
		this.category = category;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice( int price )
	{
		this.price = price;
	}

	@Override
	public String toString()
	{
		return "Article [id=" + id + ", name=" + name + ", category=" + category + "]";
	}
}
