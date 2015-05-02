package fr.lteconsulting.hexa.client.databinding.gwt;

import com.google.gwt.user.client.ui.HasValue;

import fr.lteconsulting.hexa.client.databinding.DataBinding;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.PropertyAdapter;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.gwt.WidgetPropertyAdapter;

/**
 * Second part of the fluent API for Data Binding. When a binding is
 * prepared by calling a method on the Binder class, an instance of
 * this class is returned to finish the binding creation process.
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
 */
public class BindingCreation extends fr.lteconsulting.hexa.client.databinding.BindingCreation
{
	public BindingCreation( PropertyAdapter source )
	{
		super(source);
	}
	
	/**
	 * Final step, defines the data binding destination and activates the
	 * binding
	 * 
	 * The object used as the binding's destination is a HasValue widget, like a
	 * TextBox. The binding system will the use setValue, getValue and
	 * adValueChangeHandler methods to set, get and get change notifications on
	 * the @param widget parameter.
	 * 
	 * @param widget
	 *            The widget
	 * @return The DataBinding object
	 */
	public DataBinding To( HasValue<?> widget )
	{
		return To( new WidgetPropertyAdapter( widget ) );
	}
}
