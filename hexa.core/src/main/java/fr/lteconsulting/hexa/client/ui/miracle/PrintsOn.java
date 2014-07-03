package fr.lteconsulting.hexa.client.ui.miracle;

public interface PrintsOn<T>
{
	// return false to say printer can be reused
	void print( T data, Printer printer );
}
