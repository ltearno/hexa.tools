package fr.lteconsulting.hexa.client.databinding.watchablecollection;

import fr.lteconsulting.hexa.client.classinfo.gwt.ClazzBundle;
import fr.lteconsulting.hexa.client.classinfo.gwt.ReflectedClasses;


public interface MyClassBundle extends ClazzBundle
{
	@ReflectedClasses( classes = { A.class } )
	void register();
}