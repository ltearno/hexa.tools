package fr.lteconsulting.hexa.client.databinding.tools;

import fr.lteconsulting.hexa.client.databinding.Binder;
import fr.lteconsulting.hexa.client.databinding.DataBinding;
import fr.lteconsulting.hexa.client.databinding.Mode;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.PropertyAdapter;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.WriteOnlyPropertyAdapter;

/**
 * An instance of this class will allow to bind values from one and
 * one source only. Any new registration will automatically remove the
 * previous one.
 * 
 * @author Arnaud
 *
 */
public class SmartRegistration
{
	DataBinding dataBinding;
	PropertyAdapter adapter;
	
	/**
	 * Creates a SmartRegistration object that will throw values
	 * at the specified adapter.
	 * 
	 * <p>One simple use is to use a {@link WriteOnlyPropertyAdapter}
	 * to receive values from the registered data binding
	 * 
	 * @param adapter
	 */
	public SmartRegistration( PropertyAdapter adapter )
	{
		this.adapter = adapter;
	}
	
	/**
	 * Registers the databinding source. If any previous source
	 * was binded, its bindings are freed.
	 * 
	 * @param source The object which is the source of the binding
	 * @param path The path of the data
	 */
	public void register( Object source, String path )
	{
		unregister();
		
		dataBinding = Binder.Bind( source, path ).Mode( Mode.OneWay ).To( adapter ).activate();
	}
	
	/**
	 * Frees the current data binding, if any
	 */
	public void unregister()
	{
		if( dataBinding != null )
		{
			dataBinding.term();
			dataBinding = null;
		}
	}
}