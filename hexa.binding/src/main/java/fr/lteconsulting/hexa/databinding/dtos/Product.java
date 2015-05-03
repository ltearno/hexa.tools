package fr.lteconsulting.hexa.databinding.dtos;

import fr.lteconsulting.hexa.databinding.annotation.Observable;

@Observable
public class Product
{
	int id;
	String name;
	String weight;
	Long length;
	
	boolean available;

	public Product( int id, String name )
	{
		this.id = id;
		this.name = name;
	}
}
