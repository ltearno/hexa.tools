package fr.lteconsulting.hexa.server.spring;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener
{
	@Override
	public void contextInitialized( ServletContextEvent arg0 )
	{
		HexaSpring.hexa().onContextInitialized( arg0 );
	}

	@Override
	public void contextDestroyed( ServletContextEvent arg0 )
	{
	}
}