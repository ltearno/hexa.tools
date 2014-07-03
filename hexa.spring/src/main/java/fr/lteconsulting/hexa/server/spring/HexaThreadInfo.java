package fr.lteconsulting.hexa.server.spring;

import javax.servlet.http.HttpServletRequest;

import fr.lteconsulting.hexa.server.database.DatabaseContext;

public class HexaThreadInfo
{
	private static final ThreadLocal<HexaThreadInfo> perThreadInfo = new ThreadLocal<HexaThreadInfo>();

	public static HexaThreadInfo get()
	{
		HexaThreadInfo info = perThreadInfo.get();

		if( info == null )
		{
			info = new HexaThreadInfo();
			perThreadInfo.set( info );
		}

		return info;
	}

	public static HexaThreadInfo getIfPresent()
	{
		return perThreadInfo.get();
	}

	public HttpServletRequest request;

	public DatabaseContext databaseContext;
}
