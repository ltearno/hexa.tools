package fr.lteconsulting;

import fr.lteconsulting.hexa.classinfo.ClassInfo;
import fr.lteconsulting.hexa.classinfo.Clazz;
import fr.lteconsulting.hexa.databinding.properties.Properties;
import fr.lteconsulting.hexa.databinding.properties.PropertyChangedEvent;
import fr.lteconsulting.hexa.databinding.properties.PropertyChangedHandler;


public class UniversalEditor {
	private Object value;
	private Object registration;

	public UniversalEditor() {
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;

		System.out.println("Clear all properties");

		if (registration != null) {
			Properties.removeHandler(registration);
			registration = null;
		}

		if (value != null) {
			// TODO get the object's properties
			Clazz<?> claz = ClassInfo.Clazz(value.getClass());
			// ...

			registration = Properties
					.register(value, "*", handlePropertyChange);
		}

		Properties.notify(this, "value");
	}

	private PropertyChangedHandler handlePropertyChange = new PropertyChangedHandler() {
		public void onPropertyChanged( PropertyChangedEvent event ) {
			String ptyName = event.getPropertyName();
			Object sender = event.getSender();
			System.out.println("Show property " + ptyName + " of object "
					+ sender + " with value "
					+ Properties.getValue(sender, ptyName));
		}
	};
}
