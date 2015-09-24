package fr.lteconsulting.hexa.client.interfaces;

public interface IValueControl<T> {
    public abstract T getValue();

    public abstract void setValue(T value);

    public void addCallback(Callback<T> callback, Object cookie);

    public interface Callback<T> {
        void onValueControlChange(Object cookie);
    }
}
