package com.hexa.client.ui.miracle;

import com.hexa.client.tableobserver.XTableListen;

/*
 * This class is a DataPlug connector aiming to provide the RefMng interface
 * for the use of DynArrayInFlexTable for example
 */

// implements and finalize the XTableListen methods
public abstract class DataPlugRefMng<T> extends CollectionOf<T> implements RefMng<T>, XTableListen<T>
{
	@Override
	final public void wholeTable( Iterable<T> records )
	{
		refresh( records );
	}

	@Override
	final public void updatedField( int recordId, String fieldName, T record )
	{
		update( record );
	}

	@Override
	final public void updated( int recordId, T record )
	{
		update( record );
	}

	@Override
	// TODO : check that, not sure about refactoring
	/*final*/ public void deleted( int recordId, T oldRecord )
	{
		delete( recordId );
	}

	@Override
	final public void clearAll()
	{
		refresh( null );
	}
}
