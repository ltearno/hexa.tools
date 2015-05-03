package fr.lteconsulting.hexa.databinding.test.dto;

import fr.lteconsulting.hexa.databinding.annotation.Observable;

@Observable
class Workplace
{
	String name;
	String color;
	Person owner;
	
	public Workplace()
	{
	}
	
	public Workplace(String name, String color, Person owner)
	{
		this.name = name;
		this.color = color;
		this.owner = owner;
	}
}
