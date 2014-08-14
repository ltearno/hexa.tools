package fr.lteconsulting.hexa.persistence.client.legacy.persistence;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;
import javax.persistence.metamodel.Bindable;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SingularAttribute;

import fr.lteconsulting.hexa.persistence.client.legacy.persistence.PersistenceConfiguration.EntityConfiguration;

public class PathImpl<X> implements Path<X>, SqlRenderable
{
	RootImpl<?> root;
	String path;

	public PathImpl( RootImpl<?> root, String path )
	{
		this.root = root;
		this.path = path;
	}

	@Override
	public void appendSql( StringBuilder sb )
	{
		EntityConfiguration config = root.configuration.getConfigurationForEntity( root.entityClass );
		sb.append( root.sqlAlias );
		sb.append( "." );
		sb.append( config.getFieldConfiguration( path ).columnName );
	}

	@Override
	public <Y> Expression<Y> as( Class<Y> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate in( Object... arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate in( Expression<?>... arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate in( Collection<?> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate in( Expression<Collection<?>> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate isNotNull()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate isNull()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Selection<X> alias( String arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Selection<?>> getCompoundSelectionItems()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCompoundSelection()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAlias()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends X> getJavaType()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y> Path<Y> get( SingularAttribute<? super X, Y> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E, C extends Collection<E>> Expression<C> get( PluralAttribute<X, C, E> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K, V, M extends Map<K, V>> Expression<M> get( MapAttribute<X, K, V> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y> Path<Y> get( String arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bindable<X> getModel()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path<?> getParentPath()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Class<? extends X>> type()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
