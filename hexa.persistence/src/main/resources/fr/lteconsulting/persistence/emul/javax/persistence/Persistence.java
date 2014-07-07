package javax.persistence;

import java.util.Map;

public class Persistence
{
	public static EntityManagerFactory createEntityManagerFactory( String name )
	{
		return createEntityManagerFactory( name, null );
	}
	
	public static EntityManagerFactory createEntityManagerFactory( String name, Map parameters )
	{
		return fr.lteconsulting.jpa4gwt.demo.client.legacy.persistence.PersistenceImpl.createEntityManagerFactory( name, parameters );
	}
}
