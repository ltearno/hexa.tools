package fr.lteconsulting.hexa.client.databinding.watchablecollection;

import fr.lteconsulting.hexa.client.classinfo.ClazzBundle;
import fr.lteconsulting.hexa.client.classinfo.ReflectedClasses;

public interface MyClassBundle extends ClazzBundle
{
	@ReflectedClasses( classes = { A.class } )
	void register();
}