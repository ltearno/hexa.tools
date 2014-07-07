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

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SetAttribute;
import java.util.Set;


@SuppressWarnings("hiding")
public interface From<Z, X> extends Path<X>, FetchParent<Z, X> {

    Set<Join<X, ?>> getJoins();
    boolean isCorrelated();

    From<Z, X> getCorrelationParent();

    <Y> Join<X, Y> join(SingularAttribute<? super X, Y> attribute);

    <Y> Join<X, Y> join(SingularAttribute<? super X, Y> attribute, JoinType jt);

    <Y> CollectionJoin<X, Y> join(CollectionAttribute<? super X, Y> collection);

    <Y> SetJoin<X, Y> join(SetAttribute<? super X, Y> set);

    <Y> ListJoin<X, Y> join(ListAttribute<? super X, Y> list);

    <K, V> MapJoin<X, K, V> join(MapAttribute<? super X, K, V> map);

    <Y> CollectionJoin<X, Y> join(CollectionAttribute<? super X, Y> collection, JoinType jt);

    <Y> SetJoin<X, Y> join(SetAttribute<? super X, Y> set, JoinType jt);

    <Y> ListJoin<X, Y> join(ListAttribute<? super X, Y> list, JoinType jt);

    <K, V> MapJoin<X, K, V> join(MapAttribute<? super X, K, V> map, JoinType jt);

    <X, Y> Join<X, Y> join(String attributeName);	

    <X, Y> CollectionJoin<X, Y> joinCollection(String attributeName);	

    <X, Y> SetJoin<X, Y> joinSet(String attributeName);	

    <X, Y> ListJoin<X, Y> joinList(String attributeName);

    <X, K, V> MapJoin<X, K, V> joinMap(String attributeName);	

    <X, Y> Join<X, Y> join(String attributeName, JoinType jt);

    <X, Y> CollectionJoin<X, Y> joinCollection(String attributeName, JoinType jt);	

    <X, Y> SetJoin<X, Y> joinSet(String attributeName, JoinType jt);	

    <X, Y> ListJoin<X, Y> joinList(String attributeName, JoinType jt);	

    <X, K, V> MapJoin<X, K, V> joinMap(String attributeName, JoinType jt);	
}
