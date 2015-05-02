package fr.lteconsulting.hexa.databinding.test.dto;

import fr.lteconsulting.hexa.databinding.NotifyPropertyChangedEvent;

public class Workplace
{
	private String name;
	private String color;
	private Person owner;

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
	
	public Person getOwner()
	{
		return owner;
	}

	public void setOwner( Person owner )
	{
		this.owner = owner;
	}

	@Override
	public String toString()
	{
		return "[Workplace name:"+name+"]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if( this == obj )
			return true;
		if( obj == null )
			return false;
		if( getClass() != obj.getClass() )
			return false;
		Workplace other = (Workplace) obj;
		if( color == null )
		{
			if( other.color != null )
				return false;
		}
		else if( !color.equals( other.color ) )
			return false;
		if( name == null )
		{
			if( other.name != null )
				return false;
		}
		else if( !name.equals( other.name ) )
			return false;
		return true;
	}
}
