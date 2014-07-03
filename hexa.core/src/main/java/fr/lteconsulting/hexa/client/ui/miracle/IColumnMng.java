package fr.lteconsulting.hexa.client.ui.miracle;

public interface IColumnMng<T>
{
	PrintsOn<T> getPrintsOn();

	Edits<T> getEdits();

	CellClickMng<T> getClicks();

	PrintsOn<Void> getHdrPrintsOn();

	CellClickMng<Void> getHdrClickMng();
}
