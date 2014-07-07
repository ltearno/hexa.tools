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

import java.util.List;
import java.util.Date;
import java.util.Calendar;


public interface TypedQuery<X> extends Query {
    List<X> getResultList();

    X getSingleResult();

    TypedQuery<X> setMaxResults(int maxResult);

    TypedQuery<X> setFirstResult(int startPosition);

    TypedQuery<X> setHint(String hintName, Object value);

     <T> TypedQuery<X> setParameter(Parameter<T> param, T value);

    TypedQuery<X> setParameter(Parameter<Calendar> param, 
                               Calendar value,  
                               TemporalType temporalType);

    TypedQuery<X> setParameter(Parameter<Date> param, Date value,  
                               TemporalType temporalType);

    TypedQuery<X> setParameter(String name, Object value);

    TypedQuery<X> setParameter(String name, Calendar value, 
                               TemporalType temporalType);

    TypedQuery<X> setParameter(String name, Date value, 
                               TemporalType temporalType);

    TypedQuery<X> setParameter(int position, Object value);

    TypedQuery<X> setParameter(int position, Calendar value,  
                               TemporalType temporalType);

    TypedQuery<X> setParameter(int position, Date value,  
                               TemporalType temporalType);

     TypedQuery<X> setFlushMode(FlushModeType flushMode);

     TypedQuery<X> setLockMode(LockModeType lockMode);

}
