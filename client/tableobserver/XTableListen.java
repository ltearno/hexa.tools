package com.hexa.client.tableobserver;

public interface XTableListen<T>
{
	void deleted( int recordId, T oldRecord );

	void updated( int recordId, T record );

	void updatedField( int recordId, String fieldName, T record );

	void wholeTable( Iterable<T> records );

	void clearAll();
}
