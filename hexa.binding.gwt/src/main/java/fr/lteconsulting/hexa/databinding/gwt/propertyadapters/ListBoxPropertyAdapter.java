package fr.lteconsulting.hexa.databinding.gwt.propertyadapters;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.ListBox;
import fr.lteconsulting.hexa.client.tools.Action2;
import fr.lteconsulting.hexa.databinding.propertyadapters.PropertyAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * A PropertyAdapter to bind a {@link ListBox}.<br/>
 * 
 * - When receiving an object, the adapter will set it as the selected object.<br/>
 * - When the selected item changes, the binding system gets triggered.<br/>
 * - When using a multiselect ListBox you need to ensure you are binding a collection.
 * <br/><br/>
 * When the {@link ListBox} has multiselect enabled, {@link #getValue()} returns an
 * {@link ArrayList} of String representing the selected values. <br/>
 * If you need a different collection type to be mapped create a {@link fr.lteconsulting.hexa.databinding.Converter}.
 * 
 * @author Ben Dol
 */
public class ListBoxPropertyAdapter implements PropertyAdapter {
	private Logger logger = Logger.getLogger(ListBoxPropertyAdapter.class.getName());

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
		deselectItems();
		if (object instanceof Collection) {
			for (Object item : (Collection<?>) object) {
				selectItem(item);
			}
		}
		else {
			selectItem(object);
		}
	}

	@Override
	public Object getValue() {
		if(listBox.isMultipleSelect()) {
			List<String> selectedItems = new ArrayList<>();
			for (int i = 0; i < listBox.getItemCount(); i++) {
				if (listBox.isItemSelected(i)) {
					selectedItems.add(listBox.getValue(i));
				}
			}
			return selectedItems;
		}
		else {
			return listBox.getSelectedValue();
		}
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

	private void selectItem(Object item) {
		int index = getValueIndex(item);
		if (index > -1) {
			listBox.setItemSelected(index, true);
		}
		else {
			logger.warning("Failed to select item using: " + item.toString()
				+ " " + item.getClass().getName());
		}
	}

	private void deselectItems() {
		for(int i = 0; i < listBox.getItemCount(); i++) {
			if (listBox.isItemSelected(i)) {
				listBox.setItemSelected(i, false);
			}
		}
	}

	private int getValueIndex(Object value) {
		for(int i = 0; i < listBox.getItemCount(); i++) {
			if (listBox.getValue(i).equals(String.valueOf(value))) {
				return i;
			}
		}
		return -1;
	}
}