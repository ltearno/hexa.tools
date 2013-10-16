package com.hexa.client.ui.miracle;

public interface IColumnMng<T, H>
{
	PrintsOn<T> getPrintsOn();
	Edits<T> getEdits();
	CellClickMng<T> getClicks();

	PrintsOn<H> getHdrPrintsOn();
	CellClickMng<H> getHdrClickMng();
}
