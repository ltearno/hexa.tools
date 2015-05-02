package fr.lteconsulting.hexa.databinding;

import com.google.gwt.core.client.GWT;

public class PlatformSpecificProvider
{
	public static PlatformSpecific get()
	{
		return PlatformSpecificGwt.get();
	}
}
