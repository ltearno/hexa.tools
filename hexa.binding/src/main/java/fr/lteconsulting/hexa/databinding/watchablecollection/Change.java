package fr.lteconsulting.hexa.databinding.watchablecollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Change
{
	final ChangeType type;
	final Object item;
	final int index;

	public Change( ChangeType type, Object item, int index )
	{
		this.type = type;
		this.item = item;
		this.index = index;
	}

	public static <T> List<Change> ForItems( ChangeType type, Collection<T> items, int startIndex )
	{
		List<Change> res = new ArrayList<>();
		for( T item : items )
			res.add( new Change( type, item, startIndex++ ) );

		return res;
	}

	public ChangeType getType()
	{
		return type;
	}

	@SuppressWarnings( "unchecked" )
	public <T> T getItem()
	{
		return (T) item;
	}
	
	public int getIndex()
	{
		return index;
	}
}