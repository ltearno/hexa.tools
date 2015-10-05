package fr.lteconsulting.hexa.client.interfaces;

import com.google.gwt.user.client.ui.Composite;

public abstract class AValueControl<T> extends Composite implements IValueControl<T> {
    private Callback<T> callback = null;
    private Object cookie = null;

    @Override
    public void addCallback(Callback<T> callback, Object cookie) // throws
    // Exception
    {
        assert (this.callback == null);
        // if( this.callback != null )
        // throw new Exception(
        // "AValueControl::addCallback has been called but a callback was already set !"
        // );

        this.callback = callback;
        this.cookie = cookie;
    }

    @Override
    public abstract T getValue();

    @Override
    public abstract void setValue(T value);

    protected void signalCallbacks() {
        if (callback == null)
            return;

        callback.onValueControlChange(cookie);
    }
}
