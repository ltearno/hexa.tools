package fr.lteconsulting.hexa.databinding;

public class PlatformSpecificProvider
{
	public static PlatformSpecific get()
	{
		return PlatformSpecificJre.get();
	}
}
