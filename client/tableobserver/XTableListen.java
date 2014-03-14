package com.hexa.client.tableobserver;

import com.hexa.client.interfaces.IHasIntegerId;

public interface XTableListen<T extends IHasIntegerId>
{
	void deleted( int recordId, T oldRecord );

	void updated( int recordId, T record );

	void updatedField( int recordId, String fieldName, T record );

	void wholeTable( Iterable<T> records );

	void clearAll();
}
