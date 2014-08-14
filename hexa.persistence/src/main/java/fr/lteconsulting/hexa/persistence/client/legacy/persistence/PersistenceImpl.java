package fr.lteconsulting.hexa.persistence.client.legacy.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import com.google.gwt.core.shared.GWT;

public class PersistenceImpl
{
	private final static HashMap<String, EntityManagerFactoryImpl> entityManagerFactories = new HashMap<String, EntityManagerFactoryImpl>();

	public static EntityManagerFactory createEntityManagerFactory( String name )
	{
		return createEntityManagerFactory( name, null );
	}

	public static EntityManagerFactory createEntityManagerFactory( String name, @SuppressWarnings( "rawtypes" ) Map parameters )
	{
		GWT.log( "Persistence EntityManagerFactory creation..." );

		EntityManagerFactoryImpl impl = entityManagerFactories.get( name );
		if( impl == null )
		{
			impl = new EntityManagerFactoryImpl( name, parameters );
			entityManagerFactories.put( name, impl );
		}

		return impl;
	}
}
