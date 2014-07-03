package fr.lteconsulting.hexa.client.tableobserver;

import fr.lteconsulting.hexa.client.interfaces.IHasIntegerId;

public interface XTableListen<T extends IHasIntegerId>
{
	void deleted( int recordId, T oldRecord );

	void updated( T record );

	void updatedField( String fieldName, T record );

	void wholeTable( Iterable<T> records );

	void clearAll();
}
