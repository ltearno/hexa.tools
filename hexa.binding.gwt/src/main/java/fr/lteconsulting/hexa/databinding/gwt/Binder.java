package fr.lteconsulting.hexa.databinding.gwt;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;

import fr.lteconsulting.hexa.classinfo.gwt.ClazzBundle;
import fr.lteconsulting.hexa.databinding.gwt.propertyadapters.ListBoxPropertyAdapter;
import fr.lteconsulting.hexa.databinding.gwt.propertyadapters.ValuePropertyAdapter;
import fr.lteconsulting.hexa.databinding.propertyadapters.CompositePropertyAdapter;
import fr.lteconsulting.hexa.databinding.propertyadapters.ObjectAsValuePropertyAdapter;
import fr.lteconsulting.hexa.databinding.propertyadapters.PropertyAdapter;

/**
 * Binder is a class providing a fluent api access to DataBinding.
 * <br/>
 * By default the data binding is:
 * <ul>
 * 	<li>in TwoWays mode,</li>
 * 	<li>without data converter,</li>
 * 	<li>direct activation, - not logged.</li>
 * </ul>
 * One can create and activate data binding in one line like this :
 * <pre>
 * // selectedPerson to person form
 * Binder.bind(personListBox, "selectedPerson").mode(Mode.OneWay).log("PERSONFORM").to(personForm, "person");
 * 
 * // selected person's category to category form
 * Binder.bind(personListBox, "selectedPerson.category").mode(Mode.OneWay).to(categoryForm, "$DTOMap");
 * 
 * // map selectedPerson to a person form
 * Binder.bind(personListWidget, "selectedPerson").mapTo(personForm);
 * 
 * // selected person's description to Window's title
 * Binder.bind(personListWidget, "selectedPerson.description").mode(Mode.OneWay).to(new WriteOnlyPropertyAdapter())
 * </pre>
 * The second parameter of the Bind and To methods, which is a String, is the
 * path to the desired property. It is '.' separated, so you can compose like
 * this : "person.category.creationDate" and the binding tool will automatically
 * follow the property's value.
 * <br/><br/>
 * In order to support the data binding engine, one has also to declare a
 * {@link ClazzBundle} interface to process type information of the data-bounded
 * classes. Check the samples for further details.
 * 
 * @author Arnaud Tournier
 */
public class Binder
{
	/**
	 * First step, accepts a data binding source definition and creates a binder
	 * 
	 * The source value is searched as specified in the @param propertyPath, in
	 * the context of the @param source object.
	 * 
	 * For example : <i>...To( customer, "company.address.city" )</i> can be
	 * used to access data at different depths. If all intermediary steps
	 * provide a correct implementation for the Data Binding mechanism, any
	 * change at any depth will be catch.
	 * 
	 * @param source
	 *            The source object
	 * @param propertyPath
	 *            The source object's property path
	 * @return The Binder to continue specifying the data binding
	 */
	public static BindingCreation bind( Object source, String propertyPath )
	{
		return new BindingCreation( new CompositePropertyAdapter( source, propertyPath ) );
	}

	/**
	 * First step, accepts a data binding source definition and creates a binder
	 * 
	 * This method accepts any implementation of PropertyAdapter, especially
	 * user ones so that is a good start to customize the data binding
	 * possibilities.
	 *
	 * @return The Binder to continue specifying the data binding
	 */
	public static BindingCreation bind( PropertyAdapter source )
	{
		return new BindingCreation( source );
	}
	
	/**
	 * First step, accepts a data binding source definition and creates a binder
	 * 
	 * @param widget
	 *            The HasValue widget, like a TextBox. The binding system will
	 *            the use setValue, getValue and addValueChangeHandler methods to
	 *            set, get and get change notifications on the widget.
	 * @return The Binder to continue specifying the data binding
	 */
	public static BindingCreation bind( HasValue<?> widget )
	{
		return bind( new ValuePropertyAdapter( widget ) );
	}

	/**
	 * First step, accepts a data binding source definition and creates a binder
	 *
	 * @param listBox
	 *            The ListBox widget, like a TextBox. The binding system will
	 *            use the setValue, getValue and addChangeHandler methods to
	 *            set, get and get change notifications on the widget.
	 * @return The Binder to continue specifying the data binding
	 */
	public static BindingCreation bind( ListBox listBox )
	{
		return bind( new ListBoxPropertyAdapter( listBox ) );
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
	public static BindingCreation bindObject( final Object source )
	{
		return bind( new ObjectAsValuePropertyAdapter( source ) );
	}

	/**
	 * Maps two objects together. All matching fields will then be two-way
	 * data-bound.
	 */
	public static DataBinding map( Object source, Object destination )
	{
		return (DataBinding)bindObject(source).mapTo( destination );
	}
}
