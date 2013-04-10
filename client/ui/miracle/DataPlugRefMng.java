package com.hexa.client.ui.miracle;

import com.hexa.client.tableobserver.XTableListen;

/*
 * This class is a DataPlug connector aiming to provide the RefMng interface
 * for the use of DynArrayInFlexTable for example
 */

public abstract class DataPlugRefMng<T> extends CollectionOf<T> implements RefMng<T>, XTableListen<T>
{
	@Override
	final public void wholeTable( Iterable<T> records, Object cookie )
	{
		refresh( records );
	}
	
	@Override
	final public void updatedField(int recordId, String fieldName, T record, Object cookie)
	{
		update( record );
	}
	
	@Override
	final public void updated(int recordId, T record, Object cookie)
	{
		update( record );
	}
	
	@Override
	final public void deleted(int recordId, T oldRecord, Object cookie)
	{
		delete( recordId );
	}
	
	@Override
	final public void clearAll(Object cookie)
	{
		refresh( null );
	}
}
