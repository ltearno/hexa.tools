package fr.lteconsulting.hexa.client.common;

public class Cook<T, COOKIE>
{
	T object;
	COOKIE cookie;

	public Cook( T object, COOKIE cookie )
	{
		this.object = object;
		this.cookie = cookie;
	}

	public T getObject()
	{
		return object;
	}

	public COOKIE getCookie()
	{
		return cookie;
	}
}
