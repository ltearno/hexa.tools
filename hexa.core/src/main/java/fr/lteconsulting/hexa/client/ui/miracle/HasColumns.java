package fr.lteconsulting.hexa.client.ui.miracle;

public interface HasColumns<T>
{
	void addColumn( PrintsOn<T> column, Edits<T> editMng, CellClickMng<T> clickMng, PrintsOn<Void> hdrPrintsOn, CellClickMng<Void> hdrClickMng );
}
