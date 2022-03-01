/*
 * Copyright (c) 2022, The Dattack team (http://www.dattack.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dattack.jtoolbox.jdbc;

import java.sql.Wrapper;

/**
 * Interface implemented by classes that act as proxy of another object.
 *
 * @param <T> the type of the proxying class
 * @author cvarela
 * @since 0.6
 */
public interface JdbcObjectProxy<T extends Wrapper> {

    T getDelegate();

    default T getInnermostDelegate() {
        T child = getDelegate();
        while (child instanceof JdbcObjectProxy) {
            JdbcObjectProxy<T> other = (JdbcObjectProxy<T>) child;
            child = other.getDelegate();
            if (this == child) { //NOPMD - suppressed CompareObjectsWithEquals - compared by reference is needed
                return null;
            }
        }
        return child;
    }
}
