package com.hexa.client.ui.miracle;

import com.hexa.client.interfaces.IHasIntegerId;

public final class DataPlugRefMngWithIdForDynArrayManager<T extends IHasIntegerId> extends DataPlugRefMngWithId<T>
{
	DynArrayManager<T> mng = null;

	public void setMng( DynArrayManager<T> mng )
	{
		this.mng = mng;
	}

	@Override
	protected void updated( T object )
	{
		mng.updateRow( object );
	}

	@Override
	protected void deleted( int ref, T object )
	{
		mng.deleteRow( ref );
	}

	@Override
	protected void refreshed( Iterable<T> objects )
	{
		mng.print( objects );
	}
}
