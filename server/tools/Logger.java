package com.hexa.server.tools;

import java.util.logging.Level;

public class Logger
{
	public static Logger getLogger( Class<?> cls )
	{
		return new Logger( cls );
	}

	private final java.util.logging.Logger logger;

	private Logger( Class<?> cls )
	{
		logger = java.util.logging.Logger.getLogger( cls.getName() );
	}

	public void log( String msg )
	{
		logger.log( Level.INFO, msg );
	}

	public void wrn( String msg )
	{
		logger.log( Level.WARNING, msg );
	}

	public void err( String msg )
	{
		logger.log( Level.ALL, msg );
	}
}
