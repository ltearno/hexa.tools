package com.hexa.client.tableobserver;

public abstract class SimpleDataPlug<T> implements XTableListen<T>
{
	@Override
	public final void deleted( int recordId, T oldRecord, Object cookie )
	{
		currentRecord( null, cookie );
	}

	@Override
	public final void updated( int recordId, T record, Object cookie )
	{
		currentRecord( record, cookie );
	}

	@Override
	public final void updatedField( int recordId, String fieldName, T record, Object cookie )
	{
		currentRecord( record, cookie );
	}

	@Override
	public final void wholeTable( Iterable<T> records, Object cookie )
	{
		boolean ok = false;
		for( T r : records )
		{
			ok = true;
			currentRecord( r, cookie );
			break;
		}
		if( !ok )
			currentRecord( null, cookie );
	}

	@Override
	public final void clearAll( Object cookie )
	{
		currentRecord( null, cookie );
	}

	protected abstract void currentRecord( T record, Object cookie );
}