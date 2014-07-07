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

import java.util.List;
import java.util.Set;


public interface CriteriaQuery<T> extends AbstractQuery<T> {

    CriteriaQuery<T> select(Selection<? extends T> selection);

    CriteriaQuery<T> multiselect(Selection<?>... selections);

    CriteriaQuery<T> multiselect(List<Selection<?>> selectionList);

    CriteriaQuery<T> where(Expression<Boolean> restriction);

    CriteriaQuery<T> where(Predicate... restrictions);

    CriteriaQuery<T> groupBy(Expression<?>... grouping);

    CriteriaQuery<T> groupBy(List<Expression<?>> grouping);

    CriteriaQuery<T> having(Expression<Boolean> restriction);

    CriteriaQuery<T> having(Predicate... restrictions);

    CriteriaQuery<T> orderBy(Order... o);

    CriteriaQuery<T> orderBy(List<Order> o);

    CriteriaQuery<T> distinct(boolean distinct);

    List<Order> getOrderList();

    Set<ParameterExpression<?>> getParameters();
}
