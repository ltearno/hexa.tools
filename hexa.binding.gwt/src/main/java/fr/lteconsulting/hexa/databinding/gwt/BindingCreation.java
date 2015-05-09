package fr.lteconsulting.hexa.databinding.gwt;

import com.google.gwt.user.client.ui.HasValue;

import fr.lteconsulting.hexa.databinding.Converter;
import fr.lteconsulting.hexa.databinding.DataBinding;
import fr.lteconsulting.hexa.databinding.Mode;
import fr.lteconsulting.hexa.databinding.propertyadapters.PropertyAdapter;
import fr.lteconsulting.hexa.databinding.propertyadapters.gwt.WidgetPropertyAdapter;

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
public class BindingCreation extends fr.lteconsulting.hexa.databinding.BindingCreation
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
	public DataBinding to( HasValue<?> widget )
	{
		return to( new WidgetPropertyAdapter( widget ) );
	}
	
	@Override
	public BindingCreation log(String prefix) {
		super.log(prefix);
		return this;
	}
	
	@Override
	public BindingCreation deferActivate() {
		super.deferActivate();
		return this;
	}
	
	@Override
	public BindingCreation mode(Mode mode) {
		super.mode(mode);
		return this;
	}
	
	@Override
	public fr.lteconsulting.hexa.databinding.BindingCreation withConverter( Converter converter) {
		super.withConverter(converter);
		return this;
	}
}
