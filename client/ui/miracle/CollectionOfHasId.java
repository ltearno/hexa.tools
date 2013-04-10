package com.hexa.client.ui.miracle;

import com.hexa.client.interfaces.IHasIntegerId;

public abstract class CollectionOfHasId<T extends IHasIntegerId> extends CollectionOf<T>
{
	@Override
	final protected int getObjectId( T object )
	{
		return object.getId();
	}
}
