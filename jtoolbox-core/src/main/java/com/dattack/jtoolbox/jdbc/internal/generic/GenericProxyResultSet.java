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
package com.dattack.jtoolbox.jdbc.internal.generic;

import com.dattack.jtoolbox.jdbc.internal.ProxyResultSet;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * A delegating implementation of {@link ResultSet}. All methods call the corresponding method on the "delegate"
 * provided in the constructor.
 *
 * @author cvarela
 * @since 0.6
 */
public final class GenericProxyResultSet implements ProxyResultSet {

    private final GenericProxyStatement<?> statement;
    private final ResultSet delegate;

    private GenericProxyResultSet(GenericProxyStatement<?> statement, ResultSet delegate) {
        this.statement = statement;
        this.delegate = delegate;
    }

    public static GenericProxyResultSet build(GenericProxyStatement<?> statement, ResultSet delegate) {
        return new GenericProxyResultSet(statement, delegate);
    }

    @Override
    public ResultSet getDelegate() {
        return delegate;
    }

    @Override
    public Statement getStatement() {
        return statement;
    }
}
