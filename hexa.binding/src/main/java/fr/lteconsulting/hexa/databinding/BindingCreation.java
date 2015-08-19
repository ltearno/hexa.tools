package fr.lteconsulting.hexa.databinding;

import fr.lteconsulting.hexa.databinding.propertyadapters.CompositePropertyAdapter;
import fr.lteconsulting.hexa.databinding.propertyadapters.PropertyAdapter;

/**
 * Second part of the fluent API for Data Binding. When a binding is
 * prepared by calling a method on the Binder class, an instance of
 * this class is returned to finish the binding creation process.
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 */
public class BindingCreation
{
	protected final PropertyAdapter source;
	protected Mode mode = Mode.TwoWay;
	protected Converter converter;
	protected String logPrefix;
	
	public BindingCreation( PropertyAdapter source )
	{
		this.source = source;
	}
	
	/**
	 * Second step, parameters.
	 * 
	 * Defines the data binding mode
	 * 
	 * @param mode
	 * @return The Binder to continue specifying the data binding
	 */
	public BindingCreation mode( Mode mode )
	{
		this.mode = mode;

		return this;
	}

	/**
	 * Second step, parameters.
	 * 
	 * Defines the logging prefix used to log data binding state.
	 * 
	 * @param prefix
	 * @return The Binder to continue specifying the data binding
	 */
	public BindingCreation log( String prefix )
	{
		this.logPrefix = prefix;

		return this;
	}

	/**
	 * Second step, parameters.
	 * 
	 * Defines a converter to be used by the data binding system when getting
	 * and setting values.
	 * 
	 * @param converter
	 * @return The Binder to continue specifying the data binding
	 */
	public BindingCreation withConverter( Converter converter )
	{
		this.converter = converter;

		return this;
	}

	/**
	 * Final step, defines the data binding destination and activates the
	 * binding
	 * 
	 * The destination value is searched as specified in the @param path, in the
	 * context of the @param destination object.
	 * 
	 * For example : <i>...To( customer, "company.address.city" )</i> can be
	 * used to access data at different depths. If all intermediary steps
	 * provide a correct implementation for the Data Binding mechanism, any
	 * change at any depth will be catched.
	 * 
	 * @param destination
	 *            The destination object
	 * @param propertyPath
	 *            The destination object's property path
	 * @return The DataBinding object
	 */
	public DataBinding to( Object destination, String propertyPath )
	{
		return to( new CompositePropertyAdapter( destination, propertyPath ) );
	}

	/**
	 * Final step, defines the data binding destination and activates the
	 * binding
	 * 
	 * The object used as the binding's destination will be mapped to the source
	 * object. Each of the matching properties of the source and destination
	 * will be two-way bound, so that a change in one gets written in the other
	 * one.
	 * 
	 * @param destination
	 *            The object that will be mapped to the source
	 * @return The DataBinding object
	 */
	public DataBinding mapTo( Object destination )
	{
		return mode( Mode.OneWay ).to( new CompositePropertyAdapter( destination, "$DTOMap" ) );
	}

	/**
	 * Final step, defines the data binding destination and activates the
	 * binding
	 * 
	 * This method accepts any implementation of PropertyAdapter, especially
	 * user ones so that is a good start to customize the data binding
	 * possibilities
	 * 
	 * @param destination
	 *            The destination property adapter
	 * @return The DataBinding object
	 */
	public DataBinding to( PropertyAdapter destination )
	{
		// create the binding according to the parameters
		DataBinding binding = new DataBinding( source, destination, mode, converter, logPrefix );

		// activate the binding
		binding.activate();

		return binding;
	}
}
