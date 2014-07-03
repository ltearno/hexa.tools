package fr.lteconsulting.hexa.client.ui.miracle;

import java.util.Comparator;

public interface DynArrayManager<T>
{
	void updateRow( T t );

	void deleteRow( int ref );

	void print( Iterable<T> objects );

	void printHeaders();

	void setComparator( Comparator<T> comparator );

	void clearAllRows();
}
