package fr.lteconsulting.hexa.client.interfaces;

public interface IValueControl<T>
{
	public interface Callback<T>
	{
		void onValueControlChange( Object cookie );
	}

	public abstract void setValue( T value );

	public abstract T getValue();

	public void addCallback( Callback<T> callback, Object cookie );
}
