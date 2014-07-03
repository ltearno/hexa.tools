package fr.lteconsulting.hexa.client;

import com.google.gwt.storage.client.Storage;

public class LocalStorage
{
	private final static Storage STORE;

	static
	{
		STORE = Storage.getLocalStorageIfSupported();
	}

	public static boolean isSupported()
	{
		return STORE != null;
	}

	public static int getLength()
	{
		if( STORE == null )
			return -1;
		return STORE.getLength();
	}

	public static void clear()
	{
		if( STORE == null )
			return;

		STORE.clear();
	}
}
