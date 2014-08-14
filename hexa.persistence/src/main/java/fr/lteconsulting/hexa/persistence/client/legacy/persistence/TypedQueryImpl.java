package fr.lteconsulting.hexa.persistence.client.legacy.persistence;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Parameter;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

public class TypedQueryImpl<T> implements TypedQuery<T>
{
	EntityManagerImpl em;
	CriteriaQueryImpl<T> criteriaQuery;

	public TypedQueryImpl( CriteriaQueryImpl<T> criteriaQuery, EntityManagerImpl em )
	{
		this.criteriaQuery = criteriaQuery;
		this.em = em;
	}

	@Override
	public int executeUpdate()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFirstResult()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FlushModeType getFlushMode()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getHints()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LockModeType getLockMode()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxResults()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Parameter<?> getParameter( String arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Parameter<?> getParameter( int arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> Parameter<U> getParameter( String arg0, Class<U> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> Parameter<U> getParameter( int arg0, Class<U> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> U getParameterValue( Parameter<U> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getParameterValue( String arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getParameterValue( int arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Parameter<?>> getParameters()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBound( Parameter<?> arg0 )
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <U> U unwrap( Class<U> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getResultList()
	{
		return em.executeTypedQueryAndGetResultList( this );
	}

	@Override
	public T getSingleResult()
	{
		List<T> res = em.executeTypedQueryAndGetResultList( this );
		if( res==null || res.size()!=1 )
			throw new RuntimeException( "The query result has a number of elements different than 1 !" );
		return res.get( 0 );
	}

	@Override
	public TypedQuery<T> setFirstResult( int arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypedQuery<T> setFlushMode( FlushModeType arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypedQuery<T> setHint( String arg0, Object arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypedQuery<T> setLockMode( LockModeType arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypedQuery<T> setMaxResults( int arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> TypedQuery<T> setParameter( Parameter<U> arg0, U arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypedQuery<T> setParameter( String arg0, Object arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypedQuery<T> setParameter( int arg0, Object arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypedQuery<T> setParameter( Parameter<Calendar> arg0, Calendar arg1, TemporalType arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypedQuery<T> setParameter( Parameter<Date> arg0, Date arg1, TemporalType arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypedQuery<T> setParameter( String arg0, Calendar arg1, TemporalType arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypedQuery<T> setParameter( String arg0, Date arg1, TemporalType arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypedQuery<T> setParameter( int arg0, Calendar arg1, TemporalType arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypedQuery<T> setParameter( int arg0, Date arg1, TemporalType arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}
}
