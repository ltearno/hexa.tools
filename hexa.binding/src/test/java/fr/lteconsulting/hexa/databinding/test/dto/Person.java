package fr.lteconsulting.hexa.databinding.test.dto;

import java.util.Date;

import fr.lteconsulting.hexa.databinding.properties.Properties;

public class Person
{
	private String name;
	private ObservableWorkplace workplace;
	private Date birthDate;

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
		Properties.notify( this, "name" );
	}

	public ObservableWorkplace getWorkplace()
	{
		return workplace;
	}

	public void setWorkplace( ObservableWorkplace workplace )
	{
		this.workplace = workplace;
		Properties.notify( this, "workplace" );
	}

	public Date getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate( Date birthDate )
	{
		this.birthDate = birthDate;
		Properties.notify( this, "birthDate" );
	}
	
	@Override
	public String toString()
	{
		return "[Person name:"+name+" birthdate:"+birthDate+" workplace:"+workplace+"]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((workplace == null) ? 0 : workplace.hashCode());
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
		Person other = (Person) obj;
		if( birthDate == null )
		{
			if( other.birthDate != null )
				return false;
		}
		else if( !birthDate.equals( other.birthDate ) )
			return false;
		if( name == null )
		{
			if( other.name != null )
				return false;
		}
		else if( !name.equals( other.name ) )
			return false;
		if( workplace == null )
		{
			if( other.workplace != null )
				return false;
		}
		else if( this!=other && !workplace.equals( other.workplace ) )
			return false;
		return true;
	}
}
