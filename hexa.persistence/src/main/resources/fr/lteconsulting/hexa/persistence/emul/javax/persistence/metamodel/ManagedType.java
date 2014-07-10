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

package javax.persistence.metamodel;

import java.util.Set;


public interface ManagedType<X> extends Type<X> {

     Set<Attribute<? super X, ?>> getAttributes();

     Set<Attribute<X, ?>> getDeclaredAttributes();

    <Y> SingularAttribute<? super X, Y> getSingularAttribute(String name, Class<Y> type);

    <Y> SingularAttribute<X, Y> getDeclaredSingularAttribute(String name, Class<Y> type);

    Set<SingularAttribute<? super X, ?>> getSingularAttributes();

    Set<SingularAttribute<X, ?>> getDeclaredSingularAttributes();

    <E> CollectionAttribute<? super X, E> getCollection(String name, Class<E> elementType);

    <E> CollectionAttribute<X, E> getDeclaredCollection(String name, Class<E> elementType);

    <E> SetAttribute<? super X, E> getSet(String name, Class<E> elementType);

    <E> SetAttribute<X, E> getDeclaredSet(String name, Class<E> elementType);

    <E> ListAttribute<? super X, E> getList(String name, Class<E> elementType);

    <E> ListAttribute<X, E> getDeclaredList(String name, Class<E> elementType);

    <K, V> MapAttribute<? super X, K, V> getMap(String name, 
                                                Class<K> keyType, 
                                                Class<V> valueType);

    <K, V> MapAttribute<X, K, V> getDeclaredMap(String name, 
                                                Class<K> keyType, 
                                                Class<V> valueType);
    Set<PluralAttribute<? super X, ?, ?>> getPluralAttributes();

    Set<PluralAttribute<X, ?, ?>> getDeclaredPluralAttributes();


    Attribute<? super X, ?> getAttribute(String name); 

    Attribute<X, ?> getDeclaredAttribute(String name); 

    SingularAttribute<? super X, ?> getSingularAttribute(String name);

    SingularAttribute<X, ?> getDeclaredSingularAttribute(String name);

    CollectionAttribute<? super X, ?> getCollection(String name); 

    CollectionAttribute<X, ?> getDeclaredCollection(String name); 

    SetAttribute<? super X, ?> getSet(String name);

    SetAttribute<X, ?> getDeclaredSet(String name);

    ListAttribute<? super X, ?> getList(String name);

    ListAttribute<X, ?> getDeclaredList(String name);

    MapAttribute<? super X, ?, ?> getMap(String name); 

    MapAttribute<X, ?, ?> getDeclaredMap(String name);
}
