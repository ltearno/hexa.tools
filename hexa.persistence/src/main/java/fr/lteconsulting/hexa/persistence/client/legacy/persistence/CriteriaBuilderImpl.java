package fr.lteconsulting.hexa.persistence.client.legacy.persistence;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.persistence.Tuple;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.Subquery;

public class CriteriaBuilderImpl implements CriteriaBuilder
{
	private EntityManagerImpl em;

	CriteriaBuilderImpl( EntityManagerImpl em )
	{
		this.em = em;
	}

	@Override
	public <N extends Number> Expression<N> abs( Expression<N> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y> Expression<Y> all( Subquery<Y> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate and( Predicate... arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate and( Expression<Boolean> arg0, Expression<Boolean> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y> Expression<Y> any( Subquery<Y> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompoundSelection<Object[]> array( Selection<?>... arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order asc( Expression<?> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <N extends Number> Expression<Double> avg( Expression<N> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y extends Comparable<? super Y>> Predicate between( Expression<? extends Y> arg0, Expression<? extends Y> arg1, Expression<? extends Y> arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y extends Comparable<? super Y>> Predicate between( Expression<? extends Y> arg0, Y arg1, Y arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Coalesce<T> coalesce()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y> Expression<Y> coalesce( Expression<? extends Y> arg0, Expression<? extends Y> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y> Expression<Y> coalesce( Expression<? extends Y> arg0, Y arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<String> concat( Expression<String> arg0, Expression<String> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<String> concat( Expression<String> arg0, String arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<String> concat( String arg0, Expression<String> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate conjunction()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y> CompoundSelection<Y> construct( Class<Y> arg0, Selection<?>... arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Long> count( Expression<?> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Long> countDistinct( Expression<?> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CriteriaQuery<Object> createQuery()
	{
		return createQuery( Object.class );
	}

	@Override
	public <T> CriteriaQuery<T> createQuery( Class<T> arg0 )
	{
		return new CriteriaQueryImpl<T>( this, em );
	}

	@Override
	public CriteriaQuery<Tuple> createTupleQuery()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Date> currentDate()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Time> currentTime()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Timestamp> currentTimestamp()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order desc( Expression<?> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <N extends Number> Expression<N> diff( Expression<? extends N> arg0, Expression<? extends N> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <N extends Number> Expression<N> diff( Expression<? extends N> arg0, N arg1 )
	{
		// TODO implement
		return null;
	}

	@Override
	public <N extends Number> Expression<N> diff( N arg0, Expression<? extends N> arg1 )
	{
		// TODO implement
		return null;
	}

	@Override
	public Predicate disjunction()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate equal( Expression<?> arg0, Expression<?> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate equal( Expression<?> arg0, Object arg1 )
	{
		return new PredicateImpl( PredicateImpl.Type.EQ, arg0, arg1 );
	}

	@Override
	public Predicate exists( Subquery<?> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Expression<T> function( String arg0, Class<T> arg1, Expression<?>... arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate ge( Expression<? extends Number> arg0, Expression<? extends Number> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate ge( Expression<? extends Number> arg0, Number arg1 )
	{
		return new PredicateImpl( PredicateImpl.Type.GE, arg0, arg1 );
	}

	@Override
	public <Y extends Comparable<? super Y>> Predicate greaterThan( Expression<? extends Y> arg0, Expression<? extends Y> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y extends Comparable<? super Y>> Predicate greaterThan( Expression<? extends Y> arg0, Y arg1 )
	{
		return new PredicateImpl( PredicateImpl.Type.GT, arg0, arg1 );
	}

	@Override
	public <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo( Expression<? extends Y> arg0, Expression<? extends Y> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo( Expression<? extends Y> arg0, Y arg1 )
	{
		return new PredicateImpl( PredicateImpl.Type.GE, arg0, arg1 );
	}

	@Override
	public <X extends Comparable<? super X>> Expression<X> greatest( Expression<X> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate gt( Expression<? extends Number> arg0, Expression<? extends Number> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate gt( Expression<? extends Number> arg0, Number arg1 )
	{
		return new PredicateImpl( PredicateImpl.Type.GT, arg0, arg1 );
	}

	@Override
	public <T> In<T> in( Expression<? extends T> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <C extends Collection<?>> Predicate isEmpty( Expression<C> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate isFalse( Expression<Boolean> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E, C extends Collection<E>> Predicate isMember( Expression<E> arg0, Expression<C> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E, C extends Collection<E>> Predicate isMember( E arg0, Expression<C> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <C extends Collection<?>> Predicate isNotEmpty( Expression<C> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E, C extends Collection<E>> Predicate isNotMember( Expression<E> arg0, Expression<C> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E, C extends Collection<E>> Predicate isNotMember( E arg0, Expression<C> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate isNotNull( Expression<?> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate isNull( Expression<?> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate isTrue( Expression<Boolean> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K, M extends Map<K, ?>> Expression<Set<K>> keys( M arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate le( Expression<? extends Number> arg0, Expression<? extends Number> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate le( Expression<? extends Number> arg0, Number arg1 )
	{
		return new PredicateImpl( PredicateImpl.Type.LE, arg0, arg1 );
	}

	@Override
	public <X extends Comparable<? super X>> Expression<X> least( Expression<X> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Integer> length( Expression<String> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y extends Comparable<? super Y>> Predicate lessThan( Expression<? extends Y> arg0, Expression<? extends Y> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y extends Comparable<? super Y>> Predicate lessThan( Expression<? extends Y> arg0, Y arg1 )
	{
		return new PredicateImpl( PredicateImpl.Type.LT, arg0, arg1 );
	}

	@Override
	public <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo( Expression<? extends Y> arg0, Expression<? extends Y> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo( Expression<? extends Y> arg0, Y arg1 )
	{
		return new PredicateImpl( PredicateImpl.Type.LE, arg0, arg1 );
	}

	@Override
	public Predicate like( Expression<String> arg0, Expression<String> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate like( Expression<String> arg0, String arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate like( Expression<String> arg0, Expression<String> arg1, Expression<Character> arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate like( Expression<String> arg0, Expression<String> arg1, char arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate like( Expression<String> arg0, String arg1, Expression<Character> arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate like( Expression<String> arg0, String arg1, char arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Expression<T> literal( T arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Integer> locate( Expression<String> arg0, Expression<String> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Integer> locate( Expression<String> arg0, String arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Integer> locate( Expression<String> arg0, Expression<String> arg1, Expression<Integer> arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Integer> locate( Expression<String> arg0, String arg1, int arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<String> lower( Expression<String> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate lt( Expression<? extends Number> arg0, Expression<? extends Number> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate lt( Expression<? extends Number> arg0, Number arg1 )
	{
		return new PredicateImpl( PredicateImpl.Type.LT, arg0, arg1 );
	}

	@Override
	public <N extends Number> Expression<N> max( Expression<N> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <N extends Number> Expression<N> min( Expression<N> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Integer> mod( Expression<Integer> arg0, Expression<Integer> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Integer> mod( Expression<Integer> arg0, Integer arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Integer> mod( Integer arg0, Expression<Integer> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <N extends Number> Expression<N> neg( Expression<N> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate not( Expression<Boolean> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate notEqual( Expression<?> arg0, Expression<?> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate notEqual( Expression<?> arg0, Object arg1 )
	{
		return new PredicateImpl( PredicateImpl.Type.NE, arg0, arg1 );
	}

	@Override
	public Predicate notLike( Expression<String> arg0, Expression<String> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate notLike( Expression<String> arg0, String arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate notLike( Expression<String> arg0, Expression<String> arg1, Expression<Character> arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate notLike( Expression<String> arg0, Expression<String> arg1, char arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate notLike( Expression<String> arg0, String arg1, Expression<Character> arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate notLike( Expression<String> arg0, String arg1, char arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Expression<T> nullLiteral( Class<T> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y> Expression<Y> nullif( Expression<Y> arg0, Expression<?> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y> Expression<Y> nullif( Expression<Y> arg0, Y arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate or( Predicate... arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate or( Expression<Boolean> arg0, Expression<Boolean> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ParameterExpression<T> parameter( Class<T> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ParameterExpression<T> parameter( Class<T> arg0, String arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <N extends Number> Expression<N> prod( Expression<? extends N> arg0, Expression<? extends N> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <N extends Number> Expression<N> prod( Expression<? extends N> arg0, N arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <N extends Number> Expression<N> prod( N arg0, Expression<? extends N> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Number> quot( Expression<? extends Number> arg0, Expression<? extends Number> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Number> quot( Expression<? extends Number> arg0, Number arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Number> quot( Number arg0, Expression<? extends Number> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Case<R> selectCase()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <C, R> SimpleCase<C, R> selectCase( Expression<? extends C> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <C extends Collection<?>> Expression<Integer> size( Expression<C> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <C extends Collection<?>> Expression<Integer> size( C arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Y> Expression<Y> some( Subquery<Y> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Double> sqrt( Expression<? extends Number> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<String> substring( Expression<String> arg0, Expression<Integer> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<String> substring( Expression<String> arg0, int arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<String> substring( Expression<String> arg0, Expression<Integer> arg1, Expression<Integer> arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<String> substring( Expression<String> arg0, int arg1, int arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <N extends Number> Expression<N> sum( Expression<N> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <N extends Number> Expression<N> sum( Expression<? extends N> arg0, Expression<? extends N> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <N extends Number> Expression<N> sum( Expression<? extends N> arg0, N arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <N extends Number> Expression<N> sum( N arg0, Expression<? extends N> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Double> sumAsDouble( Expression<Float> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Long> sumAsLong( Expression<Integer> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<BigDecimal> toBigDecimal( Expression<? extends Number> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<BigInteger> toBigInteger( Expression<? extends Number> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Double> toDouble( Expression<? extends Number> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Float> toFloat( Expression<? extends Number> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Integer> toInteger( Expression<? extends Number> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Long> toLong( Expression<? extends Number> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<String> toString( Expression<Character> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<String> trim( Expression<String> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<String> trim( Trimspec arg0, Expression<String> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<String> trim( Expression<Character> arg0, Expression<String> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<String> trim( char arg0, Expression<String> arg1 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<String> trim( Trimspec arg0, Expression<Character> arg1, Expression<String> arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<String> trim( Trimspec arg0, char arg1, Expression<String> arg2 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompoundSelection<Tuple> tuple( Selection<?>... arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<String> upper( Expression<String> arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <V, M extends Map<?, V>> Expression<Collection<V>> values( M arg0 )
	{
		// TODO Auto-generated method stub
		return null;
	}

}
