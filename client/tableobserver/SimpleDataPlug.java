package com.hexa.client.tableobserver;

public abstract class SimpleDataPlug<T> implements XTableListen<T>
{
	public void deleted( int recordId, T oldRecord, Object cookie )
	{
		currentRecord( null, cookie );
	}
	public void updated( int recordId, T record, Object cookie )
	{
		currentRecord( record, cookie );
	}
	public void updatedField( int recordId, String fieldName, T record, Object cookie )
	{
		currentRecord( record, cookie );
	}
	public void wholeTable( Iterable<T> records, Object cookie )
	{
		boolean ok = false;
		for( T r : records )
		{
			ok = true;
			currentRecord( r, cookie );
			break;
		}
		if( ! ok )
			currentRecord( null, cookie );
	}
	public void clearAll( Object cookie )
	{
		currentRecord( null, cookie );
	}
	
	protected abstract void currentRecord( T record, Object cookie );
}