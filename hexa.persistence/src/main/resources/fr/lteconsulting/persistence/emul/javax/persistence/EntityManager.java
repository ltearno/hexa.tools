package javax.persistence;

import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;

public interface EntityManager
{
	Query createQuery( String arg0 );
	void close();
	
	void clear();
	boolean contains( Object arg0 );
	Query createNamedQuery( String arg0 );
	<T> TypedQuery<T> createNamedQuery( String arg0, Class<T> arg1 );
	Query createNativeQuery( String arg0 );
	Query createNativeQuery( String arg0, @SuppressWarnings( "rawtypes" ) Class arg1 );
	Query createNativeQuery( String arg0, String arg1 );
	<T> TypedQuery<T> createQuery( CriteriaQuery<T> arg0 );
	<T> TypedQuery<T> createQuery( String arg0, Class<T> arg1 );
	void detach( Object arg0 );
	<T> T find( Class<T> arg0, Object arg1 );
	<T> T find( Class<T> arg0, Object arg1, Map<String, Object> arg2 );
	<T> T find( Class<T> arg0, Object arg1, LockModeType arg2 );
	<T> T find( Class<T> arg0, Object arg1, LockModeType arg2, Map<String, Object> arg3 );
	void flush();
	CriteriaBuilder getCriteriaBuilder();
	Object getDelegate();
	EntityManagerFactory getEntityManagerFactory();
	FlushModeType getFlushMode();
	LockModeType getLockMode( Object arg0 );
	Metamodel getMetamodel();
	Map<String, Object> getProperties();
	<T> T getReference( Class<T> arg0, Object arg1 );
	EntityTransaction getTransaction();
	boolean isOpen();
	void joinTransaction();
	void lock( Object arg0, LockModeType arg1 );
	void lock( Object arg0, LockModeType arg1, Map<String, Object> arg2 );
	<T> T merge( T arg0 );
	void persist( Object arg0 );
	void refresh( Object arg0 );
	void refresh( Object arg0, Map<String, Object> arg1 );
	void refresh( Object arg0, LockModeType arg1 );
	void refresh( Object arg0, LockModeType arg1, Map<String, Object> arg2 );
	void remove( Object arg0 );
	void setFlushMode( FlushModeType arg0 );
	void setProperty( String arg0, Object arg1 );
	<T> T unwrap( Class<T> arg0 );
}
