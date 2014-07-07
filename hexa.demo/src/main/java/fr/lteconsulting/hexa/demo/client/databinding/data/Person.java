package fr.lteconsulting.hexa.demo.client.databinding.data;

import java.util.List;

import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent;

public class Person
{
	private int id;
	private String nom;
	private String prenom;
	private int age;
	private List<String> passions;
	private Category category;

	public Person()
	{
	}

	public Person( int id, String nom, String prenom, int age, List<String> passions, Category category )
	{
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.passions = passions;
		this.category = category;
	}
	
	public String getDescription()
	{
		return nom + " " + prenom;
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

	public String getNom()
	{
		return nom;
	}

	public void setNom( String nom )
	{
		this.nom = nom;
		
		NotifyPropertyChangedEvent.notify( this, "nom" );
		NotifyPropertyChangedEvent.notify( this, "description" );
	}

	public String getPrenom()
	{
		return prenom;
	}

	public void setPrenom( String prenom )
	{
		this.prenom = prenom;
		
		NotifyPropertyChangedEvent.notify( this, "prenom" );
		NotifyPropertyChangedEvent.notify( this, "description" );
	}

	public int getAge()
	{
		return age;
	}

	public void setAge( int age )
	{
		this.age = age;
		
		NotifyPropertyChangedEvent.notify( this, "age" );
	}

	public List<String> getPassions()
	{
		return passions;
	}

	public void setPassions( List<String> passions )
	{
		this.passions = passions;
		
		NotifyPropertyChangedEvent.notify( this, "passions" );
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
