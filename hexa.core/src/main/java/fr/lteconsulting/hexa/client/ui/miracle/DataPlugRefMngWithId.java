package fr.lteconsulting.hexa.client.ui.miracle;

import fr.lteconsulting.hexa.client.interfaces.IHasIntegerId;

public abstract class DataPlugRefMngWithId<T extends IHasIntegerId> extends DataPlugRefMng<T>
{
	@Override
	protected final int getObjectId( T object )
	{
		return object.getId();
	}
}
