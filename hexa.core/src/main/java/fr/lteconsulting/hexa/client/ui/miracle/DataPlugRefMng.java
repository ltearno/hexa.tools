package fr.lteconsulting.hexa.client.ui.miracle;

import fr.lteconsulting.hexa.client.interfaces.IHasIntegerId;
import fr.lteconsulting.hexa.client.tableobserver.XTableListen;

/*
 * This class is a DataPlug connector aiming to provide the RefMng interface
 * for the use of DynArrayInFlexTable for example
 */

// implements and finalize the XTableListen methods
public abstract class DataPlugRefMng<T extends IHasIntegerId> extends CollectionOf<T> implements RefMng<T>, XTableListen<T>
{
	@Override
	final public void wholeTable( Iterable<T> records )
	{
		refresh( records );
	}

	@Override
	final public void updatedField( String fieldName, T record )
	{
		update( record );
	}

	@Override
	// TODO : check that, not sure about refactoring
	/*final*/ public void updated( T record )
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
