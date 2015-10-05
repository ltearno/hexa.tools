package fr.lteconsulting.hexa.databinding.gwt.propertyadapters;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

import fr.lteconsulting.hexa.client.tools.Action2;
import fr.lteconsulting.hexa.databinding.propertyadapters.PropertyAdapter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A PropertyAdapter implementation that works with a target
 * implementing the HasValue interface.
 * <p/>
 * Typically, this will be a Widget, like a TextBox for example,
 * that will receive and notify its changes
 *
 * @author Arnaud Tournier
 */
public class ValuePropertyAdapter implements PropertyAdapter, ValueChangeHandler<Object> {
    private static final Logger logger = Logger.getLogger(ValuePropertyAdapter.class.getName());

    HasValue<Object> hasValue;

    Action2<PropertyAdapter, Object> callback;
    Object cookie;

    @SuppressWarnings("unchecked")
    public ValuePropertyAdapter(HasValue<?> hasValue) {
        this.hasValue = (HasValue<Object>) hasValue;
    }

    @Override
    public Object registerPropertyChanged(Action2<PropertyAdapter, Object> callback, Object cookie) {
        this.callback = callback;
        this.cookie = cookie;

        return hasValue.addValueChangeHandler(this);
    }

    @Override
    public void onValueChange(ValueChangeEvent<Object> event) {
        if (callback != null)
            callback.exec(this, cookie);
    }

    @Override
    public Object getValue() {
        return hasValue.getValue();
    }

    @Override
    public void setValue(Object object) {
        try {
            if (object != null) {
                hasValue.setValue(object, true);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Unable to setValue for object '"
                + hasValue.getClass().getName() + "' using the value: " + object);

            throw ex;
        }
    }

    @Override
    public void removePropertyChangedHandler(Object handler) {
        ((HandlerRegistration) handler).removeHandler();
    }
}