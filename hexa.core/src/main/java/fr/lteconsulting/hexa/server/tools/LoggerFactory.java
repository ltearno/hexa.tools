package fr.lteconsulting.hexa.server.tools;

import org.slf4j.Logger;

public class LoggerFactory
{
	public static Logger getLogger()
	{	
		Logger log = org.slf4j.LoggerFactory.getLogger( getCallerClass(3).getName() );
		return log;
	}
	
	private static Class<?> getCallerClass(int depth)
	{
		return new SecurityManager()
		{
			Class<?> getCaller( int depth )
			{
				return getClassContext()[depth];
			}
		}.getCaller( depth );
	}
}
