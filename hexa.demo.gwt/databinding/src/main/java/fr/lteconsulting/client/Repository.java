package fr.lteconsulting.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.lteconsulting.hexa.databinding.watchablecollection.WatchableCollection;

/**
 * Class which holds the application data.
 * In this demo, the data are hard-coded whereas in a real
 * application they would be loaded from the server and so on...
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
 */
public class Repository
{
	private final static String[] names = { "Bike", "Lean", "Starter", "Kit", "Wheel", "Dumb" };
	
	/**
	 * Hard-coded categories
	 */
	private static List<Category> categories = Arrays.asList(
			new Category( "Food", "#652" ),
			new Category( "Travel", "#256" ),
			new Category( "Sport", "#265" ) );
	
	/**
	 * The hard-coded list of articles.
	 * 
	 * The {@link WatchableCollection} is a {@link ArrayList} augmented with change subscription
	 * ability.
	 */
	private static final WatchableCollection<Article> articles = new WatchableCollection<>();
	
	static
	{
		for( int i = 0; i < 7; i++ )
			articles.add( createRandomArticle() );
	}
	
	/**
	 * Creates a randomly initialized {@link Article} instance
	 * 
	 * @return A random Article instance
	 */
	public static Article createRandomArticle()
	{
		return new Article( randomName(), ((int) (500 * Math.random())) + " grams", categories.get( (int) (Math.random() * categories.size()) ) );
	}
	
	/**
	 * Returns the hard-coded list of articles.
	 * 
	 * The {@link WatchableCollection} is a {@link ArrayList} augmented with change subscription
	 * ability.
	 */
	public static WatchableCollection<Article> getArticles()
	{
		return articles;
	}
	
	/**
	 * Returns the hard-coded list of categories.
	 */
	public static List<Category> getCategories()
	{
		return categories;
	}

	private static String randomName()
	{
		return names[(int) (names.length * Math.random())] + " " + names[(int) (names.length * Math.random())];
	}
}
