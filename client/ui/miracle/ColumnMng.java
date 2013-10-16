package com.hexa.client.ui.miracle;

public class ColumnMng<T, H> implements IColumnMng<T, H>
{
	PrintsOn<T> prints;
	Edits<T> edits;
	CellClickMng<T> clicks;

	PrintsOn<H> hdrPrintsOn;
	CellClickMng<H> hdrClickMng;

	public ColumnMng( PrintsOn<T> prints, Edits<T> edits, CellClickMng<T> clicks, PrintsOn<H> hdrPrintsOn, CellClickMng<H> hdrClickMng )
	{
		this.prints = prints;
		this.edits = edits;
		this.clicks = clicks;

		this.hdrPrintsOn = hdrPrintsOn;
		this.hdrClickMng = hdrClickMng;
	}

	@Override
	public PrintsOn<H> getHdrPrintsOn()
	{
		return hdrPrintsOn;
	}

	@Override
	public PrintsOn<T> getPrintsOn()
	{
		return prints;
	}

	@Override
	public Edits<T> getEdits()
	{
		return edits;
	}

	@Override
	public CellClickMng<T> getClicks()
	{
		return clicks;
	}

	@Override
	public CellClickMng<H> getHdrClickMng()
	{
		return hdrClickMng;
	}
}
