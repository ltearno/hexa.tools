package fr.lteconsulting.hexa.classinfo;

public class ClassInfoProvider
{
	public static IClassInfo get()
	{
		return ClassInfoJre.get();
	}
}
