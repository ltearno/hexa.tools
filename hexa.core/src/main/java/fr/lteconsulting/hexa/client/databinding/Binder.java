package fr.lteconsulting.hexa.client.databinding;

import com.google.gwt.user.client.ui.HasValue;

import fr.lteconsulting.hexa.client.classinfo.ClazzBundle;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.CompositePropertyAdapter;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.PropertyAdapter;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.WidgetPropertyAdapter;
import fr.lteconsulting.hexa.client.databinding.tools.Property;

/**
 * Binder is a class providing a fluent api access to DataBinding.
 * 
 * By default the data binding is :
 * - in TwoWays mode,
 * - without data converter,
 * - activation not deferred,
 * - not logged.
 * 
 * One can create and activate data binding in one line like this :
 * 
 * // selectedPerson to personne form
 * Bind( personListWidget, "selectedPersonne" ).Mode( Mode.OneWay ).Log("PERSONNEFORM").To( personneForm, "personne" );
 * 
 * // selected person's category to category form
 * Bind( personListWidget, "selectedPersonne.category" ).Mode( Mode.OneWay ).To( categoryForm, "$DTOMap" );
 * 
 * // selected person's description to Window's title
 * Bind( personListWidget, "selectedPersonne.description" ).Mode( Mode.OneWay ).To( new WriteOnlyPropertyAdapter()
 * 
 * In order to support the data binding engine, one has also to declare a {@link ClazzBundle} interface to process
 * type information of the data-bounded classes. Check the samples for further details.
 * 
 * @author Arnaud Tournier
 *
 */
public class Binder
{
	private PropertyAdapter source;
	private Mode mode = Mode.TwoWay;
	private Converter converter;
	private boolean fDeferActivate;
	private String logPrefix;

	/**
	 * First step, accepts a data binding source definition and creates a binder
	 * 
	 * The source value is searched as specified in the @param propertyPath, in the context
	 * of the @param source object.
	 * 
	 * For example : <i>...To( customer, "company.address.city" )</i> can be used to
	 * access data at different depths. If all intermediary steps provide a correct implementation
	 * for the Data Binding mechanism, any change at any depth will be catched.
	 * 
	 * @param source The source object
	 * @param propertyPath The source object's property path
	 * @return The Binder to continue specifying the data binding
	 */
	public static Binder Bind( Object source, String propertyPath )
	{
		return Bind( new CompositePropertyAdapter( source, propertyPath ) );
	}

	/**
	 * First step, accepts a data binding source definition and creates a binder
	 * 
	 * The object used as the binding's source is a HasValue widget, like a TextBox.
	 * The binding system will the use setValue, getValue and adValueChangeHandler methods
	 * to set, get and get change notifications on the @param widget parameter.
	 * 
	 * @param widget The widget
	 * @return The Binder to continue specifying the data binding
	 */
	public static Binder Bind( HasValue<?> widget )
	{
		return Bind( new WidgetPropertyAdapter( widget ) );
	}

	/**
	 * First step, accepts a data binding source definition and creates a binder
	 * 
	 * This method accepts any implementation of PropertyAdapter, especially user ones
	 * so that is a good start to customize the data binding possibilities.
	 * 
	 * @param source
	 * @param propertyName
	 * @return The Binder to continue specifying the data binding
	 */
	public static Binder Bind( PropertyAdapter source )
	{
		Binder b = new Binder();
		b.source = source;
		
		return b;
	}

	/**
	 * Second step, parameters.
	 * 
	 * Defines the data binding mode
	 * 
	 * @param mode
	 * @return The Binder to continue specifying the data binding
	 */
	public Binder Mode( Mode mode )
	{
		this.mode = mode;
		
		return this;
	}

	/**
	 * Second step, parameters.
	 * 
	 * Defines the logging prefix used with GWT.log to log data binding state.
	 * 
	 * @param prefix
	 * @return The Binder to continue specifying the data binding
	 */
	public Binder Log( String prefix )
	{
		this.logPrefix = prefix;
		
		return this;
	}

	/**
	 * Second step, parameters.
	 * 
	 * Defines a converter to be used by the data binding system when 
	 * getting and setting values.
	 * 
	 * @param mode
	 * @return The Binder to continue specifying the data binding
	 */
	public Binder WithConverter( Converter converter )
	{
		this.converter = converter;

		return this;
	}

	/**
	 * Second step, parameters.
	 * 
	 * The created data binding will be activated at the next event loop. The
	 * Scheduler.get().scheduleDeferred() method will be used.
	 * 
	 * @param mode
	 * @return The Binder to continue specifying the data binding
	 */
	public Binder DeferActivate()
	{
		fDeferActivate = true;

		return this;
	}
	
	/**
	 * Final step, defines the data binding destination and activates the binding
	 * 
	 * The destination value is searched as specified in the @param path, in the context
	 * of the @param destination object.
	 * 
	 * For example : <i>...To( customer, "company.address.city" )</i> can be used to
	 * access data at different depths. If all intermediary steps provide a correct implementation
	 * for the Data Binding mechanism, any change at any depth will be catched.
	 * 
	 * @param destination The destination object
	 * @param propertyPath The destination object's property path
	 * @return The DataBinding object
	 */
	public DataBinding To( Object destination, String propertyPath )
	{
		return To( new CompositePropertyAdapter( destination, propertyPath ) );
	}

	/**
	 * Final step, defines the data binding destination and activates the binding
	 * 
	 * The object used as the binding's destination is a HasValue widget, like a TextBox.
	 * The binding system will the use setValue, getValue and adValueChangeHandler methods
	 * to set, get and get change notifications on the @param widget parameter.
	 * 
	 * @param widget The widget
	 * @return The DataBinding object
	 */
	public DataBinding To( HasValue<?> widget )
	{
		return To( new WidgetPropertyAdapter( widget ) );
	}

	/**
	 * Final step, defines the data binding destination and activates the binding
	 * 
	 * This method accepts any implementation of PropertyAdapter, especially user ones
	 * so that is a good start to customize the data binding possibilities
	 * 
	 * @param destination The destination property adapter
	 * @return The DataBinding object
	 */
	public DataBinding To( PropertyAdapter destination )
	{
		// create the binding according to the parameters
		DataBinding binding = new DataBinding( source, destination, mode, converter, logPrefix );
		
		// activate the binding : launch a value event
		if( fDeferActivate )
			binding.deferActivate();
		else
			binding.activate();

		source = null;
		
		return binding;
	}
}
