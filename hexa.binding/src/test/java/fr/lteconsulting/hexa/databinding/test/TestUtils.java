package fr.lteconsulting.hexa.databinding.test;

public class TestUtils
{
	private static String[] names = { "John", "Maria", "Cassandra", "Aurora", "Klert", "Minau", "Herfva", "Orpia", "Caronin", "Tart" };

	public static String randomName()
	{
		return names[(int)(Math.random()*names.length)] + " " + names[(int)(Math.random()*names.length)];
	}
	
	
	private static String[] colors = { "red", "blue", "white", "black", "green", "pink", "grey", "yellow" };

	public static String randomColor()
	{
		return colors[(int)(Math.random()*colors.length)];
	}
}
