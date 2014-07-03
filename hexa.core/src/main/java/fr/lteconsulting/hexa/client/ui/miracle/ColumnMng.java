package fr.lteconsulting.hexa.client.ui.miracle;

public class ColumnMng<T> implements IColumnMng<T>
{
	PrintsOn<T> prints;
	Edits<T> edits;
	CellClickMng<T> clicks;

	PrintsOn<Void> hdrPrintsOn;
	CellClickMng<Void> hdrClickMng;

	public ColumnMng( PrintsOn<T> prints, Edits<T> edits, CellClickMng<T> clicks, PrintsOn<Void> hdrPrintsOn, CellClickMng<Void> hdrClickMng )
	{
		this.prints = prints;
		this.edits = edits;
		this.clicks = clicks;

		this.hdrPrintsOn = hdrPrintsOn;
		this.hdrClickMng = hdrClickMng;
	}

	@Override
	public PrintsOn<Void> getHdrPrintsOn()
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
	public CellClickMng<Void> getHdrClickMng()
	{
		return hdrClickMng;
	}
}
