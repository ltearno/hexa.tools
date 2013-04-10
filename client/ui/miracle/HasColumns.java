package com.hexa.client.ui.miracle;

public interface HasColumns<T, H>
{
	void addColumn( PrintsOn<T> column, Edits<T> editMng, CellClickMng<T> clickMng, PrintsOn<H> hdrPrintsOn, CellClickMng<H> hdrClickMng );
}
