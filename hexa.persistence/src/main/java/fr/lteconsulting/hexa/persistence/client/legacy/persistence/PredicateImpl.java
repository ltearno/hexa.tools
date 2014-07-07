package fr.lteconsulting.hexa.persistence.client.legacy.persistence;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;

public class PredicateImpl implements Predicate, SqlRenderable
{
	public enum Type
	{
		EQ( "=" ),
		NE( "!=" ),
		LE( "<=" ),
		LT( "<" ),
		GE( ">=" ),
		GT( ">" );

		final String sql;

		Type( String sql )
		{
			this.sql = sql;
		}

		String sql()
		{
			return sql;
		}
	}

	Type type;
	Expression<?> expression;
	Object argument;

	public PredicateImpl( Type type, Expression<?> arg0, Object arg1 )
	{
		this.type = type;
		this.expression = arg0;
		this.argument = arg1;
	}

	@Override
	public void appendSql( StringBuilder sb )
	{
		sb.append( "(" );
		((SqlRenderable)expression).appendSql( sb );
		sb.append( type.sql() );
		sb.append( argument );
		sb.append( ")" );
	}

	@Override
	public <X> Expression<X> as( Class<X> arg0 )
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
	public Selection<Boolean> alias( String arg0 )
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
	public Class<? extends Boolean> getJavaType()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Expression<Boolean>> getExpressions()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BooleanOperator getOperator()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isNegated()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate not()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
