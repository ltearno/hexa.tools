package fr.lteconsulting.hexa.classinfo;

import fr.lteconsulting.hexa.classinfo.gwt.ClassInfoGwt;

public class ClassInfoProvider
{
	public static IClassInfo get()
	{
		return new ClassInfoGwt();
	}
}
