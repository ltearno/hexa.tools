/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

//
// This source code implements specifications defined by the Java
// Community Process. In order to remain compliant with the specification
// DO NOT add / change / or delete method signatures!
//
package javax.persistence.criteria;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.persistence.Tuple;


public interface CriteriaBuilder {

    CriteriaQuery<Object> createQuery();

    <T> CriteriaQuery<T> createQuery(Class<T> resultClass);

    CriteriaQuery<Tuple> createTupleQuery();

    <Y> CompoundSelection<Y> construct(Class<Y> resultClass, Selection<?>... selections);

    CompoundSelection<Tuple> tuple(Selection<?>... selections);

    CompoundSelection<Object[]> array(Selection<?>... selections);

    Order asc(Expression<?> x);

    Order desc(Expression<?> x);

    <N extends Number> Expression<Double> avg(Expression<N> x);

    <N extends Number> Expression<N> sum(Expression<N> x);

    Expression<Long> sumAsLong(Expression<Integer> x);

    Expression<Double> sumAsDouble(Expression<Float> x);

    <N extends Number> Expression<N> max(Expression<N> x);

    <N extends Number> Expression<N> min(Expression<N> x);

    <X extends Comparable<? super X>> Expression<X> greatest(Expression<X> x);
    <X extends Comparable<? super X>> Expression<X> least(Expression<X> x);

    Expression<Long> count(Expression<?> x);

    Expression<Long> countDistinct(Expression<?> x);

    Predicate exists(Subquery<?> subquery);

    <Y> Expression<Y> all(Subquery<Y> subquery);

    <Y> Expression<Y> some(Subquery<Y> subquery);

    <Y> Expression<Y> any(Subquery<Y> subquery);

    Predicate and(Expression<Boolean> x, Expression<Boolean> y);

    Predicate and(Predicate... restrictions);

    Predicate or(Expression<Boolean> x, Expression<Boolean> y);

    Predicate or(Predicate... restrictions);

    Predicate not(Expression<Boolean> restriction);

    Predicate conjunction();

    Predicate disjunction();

    Predicate isTrue(Expression<Boolean> x);

    Predicate isFalse(Expression<Boolean> x);

    Predicate isNull(Expression<?> x);

    Predicate isNotNull(Expression<?> x);

    Predicate equal(Expression<?> x, Expression<?> y);

    Predicate equal(Expression<?> x, Object y);

    Predicate notEqual(Expression<?> x, Expression<?> y);

    Predicate notEqual(Expression<?> x, Object y);

    <Y extends Comparable<? super Y>> Predicate greaterThan(Expression<? extends Y> x, Expression<? extends Y> y);

    <Y extends Comparable<? super Y>> Predicate greaterThan(Expression<? extends Y> x, Y y);

    <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo(Expression<? extends Y> x, Expression<? extends Y> y);

    <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo(Expression<? extends Y> x, Y y);

    <Y extends Comparable<? super Y>> Predicate lessThan(Expression<? extends Y> x, Expression<? extends Y> y);

    <Y extends Comparable<? super Y>> Predicate lessThan(Expression<? extends Y> x, Y y);

    <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo(Expression<? extends Y> x, Expression<? extends Y> y);

    <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo(Expression<? extends Y> x, Y y);

    <Y extends Comparable<? super Y>> Predicate between(Expression<? extends Y> v, Expression<? extends Y> x, Expression<? extends Y> y);

    <Y extends Comparable<? super Y>> Predicate between(Expression<? extends Y> v, Y x, Y y);

    Predicate gt(Expression<? extends Number> x, Expression<? extends Number> y);

    Predicate gt(Expression<? extends Number> x, Number y);

    Predicate ge(Expression<? extends Number> x, Expression<? extends Number> y);

    Predicate ge(Expression<? extends Number> x, Number y);

    Predicate lt(Expression<? extends Number> x, Expression<? extends Number> y);

    Predicate lt(Expression<? extends Number> x, Number y);

    Predicate le(Expression<? extends Number> x, Expression<? extends Number> y);

    Predicate le(Expression<? extends Number> x, Number y);

    <N extends Number> Expression<N> neg(Expression<N> x);

    <N extends Number> Expression<N> abs(Expression<N> x);

    <N extends Number> Expression<N> sum(Expression<? extends N> x, Expression<? extends N> y);

    <N extends Number> Expression<N> sum(Expression<? extends N> x, N y);

    <N extends Number> Expression<N> sum(N x, Expression<? extends N> y);

    <N extends Number> Expression<N> prod(Expression<? extends N> x, Expression<? extends N> y);

    <N extends Number> Expression<N> prod(Expression<? extends N> x, N y);

    <N extends Number> Expression<N> prod(N x, Expression<? extends N> y);

    <N extends Number> Expression<N> diff(Expression<? extends N> x, Expression<? extends N> y);

    <N extends Number> Expression<N> diff(Expression<? extends N> x, N y);

    <N extends Number> Expression<N> diff(N x, Expression<? extends N> y);

    Expression<Number> quot(Expression<? extends Number> x, Expression<? extends Number> y);

    Expression<Number> quot(Expression<? extends Number> x, Number y);

    Expression<Number> quot(Number x, Expression<? extends Number> y);

    Expression<Integer> mod(Expression<Integer> x, Expression<Integer> y);

    Expression<Integer> mod(Expression<Integer> x, Integer y);

    Expression<Integer> mod(Integer x, Expression<Integer> y);

    Expression<Double> sqrt(Expression<? extends Number> x);

    Expression<Long> toLong(Expression<? extends Number> number);

    Expression<Integer> toInteger(Expression<? extends Number> number);

    Expression<Float> toFloat(Expression<? extends Number> number);

    Expression<Double> toDouble(Expression<? extends Number> number);

    Expression<BigDecimal> toBigDecimal(Expression<? extends Number> number);

    Expression<BigInteger> toBigInteger(Expression<? extends Number> number);

    Expression<String> toString(Expression<Character> character);

    <T> Expression<T> literal(T value);

    <T> Expression<T> nullLiteral(Class<T> resultClass);

    <T> ParameterExpression<T> parameter(Class<T> paramClass);

    <T> ParameterExpression<T> parameter(Class<T> paramClass, String name);

    <C extends Collection<?>> Predicate isEmpty(Expression<C> collection);

    <C extends Collection<?>> Predicate isNotEmpty(Expression<C> collection);

    <C extends java.util.Collection<?>> Expression<Integer> size(Expression<C> collection);

    <C extends Collection<?>> Expression<Integer> size(C collection);

    <E, C extends Collection<E>> Predicate isMember(Expression<E> elem, Expression<C> collection);

    <E, C extends Collection<E>> Predicate isMember(E elem, Expression<C> collection);

    <E, C extends Collection<E>> Predicate isNotMember(Expression<E> elem, Expression<C> collection);

    <E, C extends Collection<E>> Predicate isNotMember(E elem, Expression<C> collection);

    <V, M extends Map<?, V>> Expression<Collection<V>> values(M map);

    <K, M extends Map<K, ?>> Expression<Set<K>> keys(M map);

    Predicate like(Expression<String> x, Expression<String> pattern);

    Predicate like(Expression<String> x, String pattern);

    Predicate like(Expression<String> x, Expression<String> pattern, Expression<Character> escapeChar);

    Predicate like(Expression<String> x, Expression<String> pattern, char escapeChar);

    Predicate like(Expression<String> x, String pattern, Expression<Character> escapeChar);

    Predicate like(Expression<String> x, String pattern, char escapeChar);

    Predicate notLike(Expression<String> x, Expression<String> pattern);

    Predicate notLike(Expression<String> x, String pattern);

    Predicate notLike(Expression<String> x, Expression<String> pattern, Expression<Character> escapeChar);

    Predicate notLike(Expression<String> x, Expression<String> pattern, char escapeChar);

    Predicate notLike(Expression<String> x, String pattern, Expression<Character> escapeChar);

    Predicate notLike(Expression<String> x, String pattern, char escapeChar);

    Expression<String> concat(Expression<String> x, Expression<String> y);

    Expression<String> concat(Expression<String> x, String y);

    Expression<String> concat(String x, Expression<String> y);

    Expression<String> substring(Expression<String> x, Expression<Integer> from);

    Expression<String> substring(Expression<String> x, int from);

    Expression<String> substring(Expression<String> x, Expression<Integer> from, Expression<Integer> len);

    Expression<String> substring(Expression<String> x, int from, int len);

    public static enum Trimspec { 

        LEADING,
        TRAILING, 

        BOTH 
    }

    Expression<String> trim(Expression<String> x);

    Expression<String> trim(Trimspec ts, Expression<String> x);

    Expression<String> trim(Expression<Character> t, Expression<String> x);

    Expression<String> trim(Trimspec ts, Expression<Character> t, Expression<String> x);

    Expression<String> trim(char t, Expression<String> x);

    Expression<String> trim(Trimspec ts, char t, Expression<String> x);

    Expression<String> lower(Expression<String> x);

    Expression<String> upper(Expression<String> x);

    Expression<Integer> length(Expression<String> x);

    Expression<Integer> locate(Expression<String> x, Expression<String> pattern);

    Expression<Integer> locate(Expression<String> x, String pattern);

    Expression<Integer> locate(Expression<String> x, Expression<String> pattern, Expression<Integer> from);

    Expression<Integer> locate(Expression<String> x, String pattern, int from);

    Expression<java.sql.Date> currentDate();

    Expression<java.sql.Timestamp> currentTimestamp();

    Expression<java.sql.Time> currentTime();

    public static interface In<T> extends Predicate {

         Expression<T> getExpression();
         In<T> value(T value);

         In<T> value(Expression<? extends T> value);
     }

    <T> In<T> in(Expression<? extends T> expression);

    <Y> Expression<Y> coalesce(Expression<? extends Y> x, Expression<? extends Y> y);

    <Y> Expression<Y> coalesce(Expression<? extends Y> x, Y y);

    <Y> Expression<Y> nullif(Expression<Y> x, Expression<?> y);

    <Y> Expression<Y> nullif(Expression<Y> x, Y y);

    public static interface Coalesce<T> extends Expression<T> {

         Coalesce<T> value(T value);

         Coalesce<T> value(Expression<? extends T> value);
    }
    <T> Coalesce<T> coalesce();

    public static interface SimpleCase<C,R> extends Expression<R> {

        Expression<C> getExpression();

        SimpleCase<C, R> when(C condition, R result);

        SimpleCase<C, R> when(C condition, Expression<? extends R> result);

        Expression<R> otherwise(R result);

        Expression<R> otherwise(Expression<? extends R> result);
    }

    <C, R> SimpleCase<C,R> selectCase(Expression<? extends C> expression);

    public static interface Case<R> extends Expression<R> {

        Case<R> when(Expression<Boolean> condition, R result);

        Case<R> when(Expression<Boolean> condition, Expression<? extends R> result);

        Expression<R> otherwise(R result);

        Expression<R> otherwise(Expression<? extends R> result);
    }

    <R> Case<R> selectCase();

   <T> Expression<T> function(String name, Class<T> type, Expression<?>... args);

}

