package com.hexa.client.ui.miracle;

public interface TreeRefMng<T> extends RefMng<T>
{
	T getParentObject( T object );
}
