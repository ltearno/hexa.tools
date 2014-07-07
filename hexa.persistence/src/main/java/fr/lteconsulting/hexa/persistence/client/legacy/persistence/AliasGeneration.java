package fr.lteconsulting.hexa.persistence.client.legacy.persistence;

public class AliasGeneration
{
	private static int nextAlias = 0;

	public static String nextAlias()
	{
		return "a_" + (nextAlias++);
	}
}
