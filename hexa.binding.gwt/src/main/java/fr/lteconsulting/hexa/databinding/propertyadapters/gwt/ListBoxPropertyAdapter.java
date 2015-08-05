package fr.lteconsulting.hexa.databinding.propertyadapters.gwt;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.ListBox;
import fr.lteconsulting.hexa.client.tools.Action2;
import fr.lteconsulting.hexa.databinding.propertyadapters.PropertyAdapter;

/**
 * A PropertyAdapter to bind a {@link ListBox}.<br/>
 * 
 * - When receiving an object, the adapter will set it as the selected object.<br/>
 * - When the selected item changes, the binding system gets triggered.
 * 
 * @author Ben Dol
 */
public class ListBoxPropertyAdapter implements PropertyAdapter {
	private ListBox listBox;

	/**
	 * Uses the given {@link ListBox} and acts as a binding property.
	 * Used to bind the objects change value to another value.
	 */
	public ListBoxPropertyAdapter(ListBox listBox) {
		this.listBox = listBox;
	}

	@Override
	public void setValue(Object object) {
		for(int i = 0; i < listBox.getItemCount(); i++) {
			if (listBox.getValue(i).equals(object.toString())) {
				listBox.setSelectedIndex(i);
				return;
			}
		}
	}

	@Override
	public Object getValue() {
		return listBox.getSelectedValue();
	}
	
	@Override
	public void removePropertyChangedHandler(Object handlerRegistration) {
		((HandlerRegistration)handlerRegistration).removeHandler();
	}
	
	@Override
	public Object registerPropertyChanged(final Action2<PropertyAdapter, Object> callback, final Object cookie) {
		return listBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				callback.exec(ListBoxPropertyAdapter.this, cookie);
			}
		});
	}
}