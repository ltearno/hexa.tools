package fr.lteconsulting.hexa.client.ui.miracle;

/*
 * An object that knows how to manage the editing process of
 * an object of type T
 */
public interface Edits<T>
{
	public interface Callback
	{
		void cancelEdition();

		// necessary to call for the editing host to jump on to the next editing
		// place
		void validateEdition( boolean fJumpNext );
	}

	public interface Editor
	{
		void close();
	}

	Editor createEditor( T editedData, Printer printer, Callback editionCallback, int width, int height );
}
