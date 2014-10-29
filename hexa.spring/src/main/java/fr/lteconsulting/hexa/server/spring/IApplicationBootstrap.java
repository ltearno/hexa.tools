package fr.lteconsulting.hexa.server.spring;

import javax.servlet.ServletContext;

/**
 * Interface implemented by classes whishing to be part of
 * the HexaSpring application bootstrap process.
 * 
 * <p>The onSartup() method will be called after Hexa initialization.
 * That gives the opportunity for the application to
 * start its own threads and initialization process.
 * 
 * @author Arnaud Tournier
 *
 */
public interface IApplicationBootstrap
{
	void onStartup( ServletContext c );
}
