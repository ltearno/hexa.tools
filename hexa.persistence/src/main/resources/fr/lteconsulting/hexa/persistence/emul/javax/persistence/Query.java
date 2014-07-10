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


package javax.persistence;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Map;


public interface Query {

    List getResultList();

    Object getSingleResult();

    int executeUpdate();

    Query setMaxResults(int maxResult);

    int getMaxResults();

    Query setFirstResult(int startPosition);

    int getFirstResult();

    Query setHint(String hintName, Object value);

    Map<String, Object> getHints();

    <T> Query setParameter(Parameter<T> param, T value);

    Query setParameter(Parameter<Calendar> param, Calendar value, 
                       TemporalType temporalType);

    Query setParameter(Parameter<Date> param, Date value, 
                       TemporalType temporalType);

    Query setParameter(String name, Object value);

    Query setParameter(String name, Calendar value, 
                       TemporalType temporalType);

    Query setParameter(String name, Date value, 
                       TemporalType temporalType);

    Query setParameter(int position, Object value);

    Query setParameter(int position, Calendar value,  
                       TemporalType temporalType);

    Query setParameter(int position, Date value,  
                       TemporalType temporalType);

    Set<Parameter<?>> getParameters();

    Parameter<?> getParameter(String name);

    <T> Parameter<T> getParameter(String name, Class<T> type);

    Parameter<?> getParameter(int position);

    <T> Parameter<T> getParameter(int position, Class<T> type);

    boolean isBound(Parameter<?> param);

    <T> T getParameterValue(Parameter<T> param);

    Object getParameterValue(String name);

    Object getParameterValue(int position);

    Query setFlushMode(FlushModeType flushMode);

    FlushModeType getFlushMode();

    Query setLockMode(LockModeType lockMode);

    LockModeType getLockMode();

    <T> T unwrap(Class<T> cls);
}
