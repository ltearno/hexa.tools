package fr.lteconsulting.hexa.client.databinding;

public class PlatformSpecificProvider
{
	public static PlatformSpecific get()
	{
		return PlatformSpecificJre.get();
	}
}
