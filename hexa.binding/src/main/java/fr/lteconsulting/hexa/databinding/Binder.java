package fr.lteconsulting.hexa.databinding;

import fr.lteconsulting.hexa.databinding.propertyadapters.CompositePropertyAdapter;
import fr.lteconsulting.hexa.databinding.propertyadapters.ObjectAsValuePropertyAdapter;
import fr.lteconsulting.hexa.databinding.propertyadapters.PropertyAdapter;

/**
 * Binder is a class providing a fluent api access to DataBinding.
 * 
 * By default the data binding is : - in TwoWays mode, - without data converter,
 * - direct activation, - not logged.
 * 
 * One can create and activate data binding in one line like this :
 * 
 * // selectedPerson to personne form Bind( personListWidget, "selectedPersonne"
 * ).Mode( Mode.OneWay ).Log("PERSONNEFORM").To( personneForm, "personne" );
 * 
 * // selected person's category to category form Bind( personListWidget,
 * "selectedPersonne.category" ).Mode( Mode.OneWay ).To( categoryForm, "$DTOMap"
 * );
 * 
 * // map selectedPerson to a personne form Bind( personListWidget,
 * "selectedPersonne" ).MapTo( personneForm );
 * 
 * // selected person's description to Window's title Bind( personListWidget,
 * "selectedPersonne.description" ).Mode( Mode.OneWay ).To( new
 * WriteOnlyPropertyAdapter()
 * 
 * The second parameter of the Bind and To methods, which is a String, is the
 * path to the desired property. It is '.' separated, so you can compose like
 * this : "person.category.creationDate" and the binding tool will automatically
 * follow the property's value.
 * 
 * @author Arnaud Tournier
 *
 */
public class Binder
{
	private static BindingCreation createBinder( PropertyAdapter source )
	{
		return new BindingCreation( source );
	}
	
	/**
	 * First step, accepts a data binding source definition and creates a binder
	 * 
	 * The source value is searched as specified in the @param propertyPath, in
	 * the context of the @param source object.
	 * 
	 * For example : <i>...To( customer, "company.address.city" )</i> can be
	 * used to access data at different depths. If all intermediary steps
	 * provide a correct implementation for the Data Binding mechanism, any
	 * change at any depth will be catched.
	 * 
	 * @param source
	 *            The source object
	 * @param propertyPath
	 *            The source object's property path
	 * @return The Binder to continue specifying the data binding
	 */
	public static BindingCreation Bind( Object source, String propertyPath )
	{
		return createBinder( new CompositePropertyAdapter( source, propertyPath ) );
	}

	/**
	 * First step, accepts a data binding source definition and creates a binder
	 * 
	 * This method accepts any implementation of PropertyAdapter, especially
	 * user ones so that is a good start to customize the data binding
	 * possibilities.
	 * 
	 * @param source
	 * @param propertyName
	 * @return The Binder to continue specifying the data binding
	 */
	public static BindingCreation Bind( PropertyAdapter source )
	{
		BindingCreation b = createBinder( source );
		return b;
	}
	
	/**
	 * First step, accepts a data binding source definition and creates a binder
	 * 
	 * @param source
	 *            Accepts a source object as the value provided for the binding.
	 *            In that case, <b>note</b> that the provided source must be
	 *            bound in one way mode, because the source cannot be modified
	 *            in that mode.
	 * @return The Binder to continue specifying the data binding
	 */
	public static BindingCreation BindObject( final Object source )
	{
		return Bind( new ObjectAsValuePropertyAdapter( source ) );
	}

	/**
	 * Maps two objects together. All matching fields will then be two-way
	 * data-bound.
	 * 
	 * @param source
	 * @param destination
	 * @return
	 */
	public static DataBinding Map( Object source, Object destination )
	{
		return BindObject( source ).MapTo( destination );
	}
}
