package fr.lteconsulting.hexa.client.databinding.test.watchablecollection;

import fr.lteconsulting.hexa.classinfo.gwt.ClazzBundle;
import fr.lteconsulting.hexa.classinfo.gwt.ReflectedClasses;


public interface MyClassBundle extends ClazzBundle
{
	@ReflectedClasses( classes = { A.class } )
	void register();
}