package fr.lteconsulting.hexa.databinding.gwt.propertyadapters;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import fr.lteconsulting.hexa.client.tools.Action2;
import fr.lteconsulting.hexa.databinding.propertyadapters.PropertyAdapter;

/**
 * A PropertAdapter to bind a {@link SingleSelectionModel}.<br/>
 * 
 * - When receiving an object, the adapter will set it as the selected object.<br/>
 * - When the selected item changes, the binding system gets triggered.
 * 
 * @author Arnaud
 */
public class SelectionModelAdapter<T> implements PropertyAdapter
{
	private SingleSelectionModel<T> model;
	
	/**
	 * Uses the given {@link SingleSelectionModel} and acts as a binding property.
	 * Used to bind the model's selected value to another value.
	 */
	public SelectionModelAdapter(SingleSelectionModel<T> selectionModel) {
		this.model = selectionModel;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void setValue(Object object) {
		model.clear();
		model.setSelected((T)object, true);
	}
	
	@Override
	public void removePropertyChangedHandler(Object handlerRegistration) {
		((HandlerRegistration)handlerRegistration).removeHandler();
	}
	
	@Override
	public Object registerPropertyChanged(final Action2<PropertyAdapter, Object> callback, final Object cookie) {
		return model.addSelectionChangeHandler(new Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				callback.exec(SelectionModelAdapter.this, cookie);
			}
		});
	}
	
	@Override
	public Object getValue() {
		return model.getSelectedObject();
	}
}