package fr.lteconsulting.hexa.client.ui.miracle;

import fr.lteconsulting.hexa.client.interfaces.IHasIntegerId;

public final class DataPlugRefMngWithIdForDynArrayManager<T extends IHasIntegerId> extends DataPlugRefMngWithId<T>
{
	DynArrayManager<T> mng = null;

	public void setMng( DynArrayManager<T> mng )
	{
		this.mng = mng;
	}

	@Override
	public void updated( T object )
	{
		mng.updateRow( object );
	}

	// TODO : really not sure about the refactoring :
	// previously was protected, and implementing the CollectionOf abstract deleted method
	// but now this method name is colliding with XTableListen deleted method...
	// so there might be a big bug here !
	// TODO : check that ...
	@Override
	public void deleted( int ref, T object )
	{
		mng.deleteRow( ref );
	}

	@Override
	protected void refreshed( Iterable<T> objects )
	{
		mng.clearAllRows();
		mng.print( objects );
	}
}
