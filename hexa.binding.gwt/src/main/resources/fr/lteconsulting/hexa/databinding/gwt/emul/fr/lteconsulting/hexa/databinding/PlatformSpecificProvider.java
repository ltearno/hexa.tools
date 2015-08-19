package fr.lteconsulting.hexa.databinding;

import fr.lteconsulting.hexa.databinding.gwt.PlatformSpecificGwt;

public class PlatformSpecificProvider
{
	public static PlatformSpecific get()
	{
		return PlatformSpecificGwt.get();
	}
}
