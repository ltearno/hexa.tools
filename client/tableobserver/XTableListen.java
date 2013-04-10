package com.hexa.client.tableobserver;

public interface XTableListen<T>
{
	void deleted( int recordId, T oldRecord, Object cookie );
	void updated( int recordId, T record, Object cookie );
	void updatedField( int recordId, String fieldName, T record, Object cookie );
	void wholeTable( Iterable<T> records, Object cookie );
	void clearAll( Object cookie );
}
