package fr.lteconsulting.hexa.client.ui.miracle;

public interface RefMng<T>
{
	int getRef( T object );

	T getObject( int ref );
}
