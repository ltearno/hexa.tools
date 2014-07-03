package fr.lteconsulting.hexa.client.ui.miracle;

public class DynArrayInFlexTableColumnMng<T, H>
{
	PrintsOn<T> prints;
	Edits<T> edits;
	CellClickMng<T> clicks;

	PrintsOn<H> hdrPrintsOn;
	CellClickMng<H> hdrClickMng;

	public DynArrayInFlexTableColumnMng( PrintsOn<T> prints, Edits<T> edits, CellClickMng<T> clicks, PrintsOn<H> hdrPrintsOn, CellClickMng<H> hdrClickMng )
	{
		this.prints = prints;
		this.edits = edits;
		this.clicks = clicks;

		this.hdrPrintsOn = hdrPrintsOn;
		this.hdrClickMng = hdrClickMng;
	}
}
