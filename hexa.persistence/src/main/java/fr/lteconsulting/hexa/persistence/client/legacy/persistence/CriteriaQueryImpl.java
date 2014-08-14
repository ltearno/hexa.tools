package fr.lteconsulting.hexa.persistence.client.legacy.persistence;

import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.Subquery;
import javax.persistence.metamodel.EntityType;

public class CriteriaQueryImpl<T> implements CriteriaQuery<T>
{
	CriteriaBuilderImpl cb;
	EntityManagerImpl em;

	final QueryStructure queryStructure;

	CriteriaQueryImpl( CriteriaBuilderImpl cb, EntityManagerImpl em )
	{
		this.cb = cb;
		this.em = em;

		queryStructure = new QueryStructure( em.configuration );
	}

	@Override
	public <X> Root<X> from( Class<X> entityClass )
	{
		return queryStructure.from( entityClass );
	}

	@Override
	public <X> Root<X> from( EntityType<X> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Expression<?>> getGroupList()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate getGroupRestriction()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate getRestriction()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<T> getResultType()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Root<?>> getRoots()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Selection<T> getSelection()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDistinct()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <U> Subquery<U> subquery( Class<U> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CriteriaQuery<T> distinct( boolean arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getOrderList()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ParameterExpression<?>> getParameters()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CriteriaQuery<T> groupBy( Expression<?>... arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CriteriaQuery<T> groupBy( List<Expression<?>> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CriteriaQuery<T> having( Expression<Boolean> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CriteriaQuery<T> having( Predicate... arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CriteriaQuery<T> multiselect( Selection<?>... arg0 )
	{
		queryStructure.multiselect( arg0 );
		return null;
	}

	@Override
	public CriteriaQuery<T> multiselect( List<Selection<?>> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CriteriaQuery<T> orderBy( Order... arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CriteriaQuery<T> orderBy( List<Order> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CriteriaQuery<T> select( Selection<? extends T> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CriteriaQuery<T> where( Expression<Boolean> arg0 )
	{
		queryStructure.where( arg0 );
		return null;
	}

	@Override
	public CriteriaQuery<T> where( Predicate... arg0 )
	{
		queryStructure.where( (Object[]) arg0 );
		return this;
	}

}
