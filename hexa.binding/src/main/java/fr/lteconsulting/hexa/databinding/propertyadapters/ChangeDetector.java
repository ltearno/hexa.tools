package fr.lteconsulting.hexa.databinding.propertyadapters;

import fr.lteconsulting.hexa.databinding.properties.Properties;
import fr.lteconsulting.hexa.databinding.properties.PropertyChangedEvent;
import fr.lteconsulting.hexa.databinding.properties.PropertyChangedHandler;

/**
 * A PropertyAdapter that calls its onChange method when a property value changes 
 * in the currently bound object.
 * 
 * @author Arnaud
 *
 */
public abstract class ChangeDetector extends WriteOnlyPropertyAdapter
{
	private Object reg;
	
	abstract protected void onChange( Object object, String property );
	
	@Override
	public void setValue(Object object) {
		if( reg != null )
		{
			Properties.removeHandler(reg);
			reg = null;
		}
		
		if( object != null )
		{
			reg = Properties.register(object, "*", handler);
		}
	}
	
	PropertyChangedHandler handler = new PropertyChangedHandler() {
		@Override
		public void onPropertyChanged(PropertyChangedEvent event) {
			onChange(event.getSender(), event.getPropertyName());
		}
	};
}