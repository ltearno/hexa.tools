package fr.lteconsulting.hexa.client.ui.miracle;

import java.util.Comparator;

public class SortMng<T>
{
	DynArrayManager<T> mng;
	Comparator<T> currentComp = null;
	int order = 1;

	public SortMng( DynArrayManager<T> mng )
	{
		this.mng = mng;
	}

	// sort : -1=descending, 0=no sort, 1=ascending
	public int getSort( Comparator<T> comp )
	{
		if( comp == currentComp )
			return order;
		return 0;
	}

	public void setComparator( Comparator<T> comp )
	{
		if( comp == currentComp )
		{
			order *= -1;
		}
		else
		{
			order = 1;
			currentComp = comp;
		}

		// sort the list
		mng.printHeaders();
		mng.setComparator( order > 0 ? currentComp : revComp );
	}

	private final Comparator<T> revComp = new Comparator<T>()
	{
		public int compare( T o1, T o2 )
		{
			return -currentComp.compare( o1, o2 );
		}
	};
}
