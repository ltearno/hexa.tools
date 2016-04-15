package fr.lteconsulting.hexa.client.other;

public interface HasSelectionHandlers<T>
{
	void addSelectionhandler( SelectionHandler<T> handler );
}