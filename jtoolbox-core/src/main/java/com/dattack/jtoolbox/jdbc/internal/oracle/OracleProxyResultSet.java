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
package com.dattack.jtoolbox.jdbc.internal.oracle;

import com.dattack.jtoolbox.jdbc.internal.ProxyResultSet;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * {@link ProxyResultSet} specific implementation for an Oracle underlying {@link ResultSet}.
 *
 * @author cvarela
 * @since 0.6
 */
public final class OracleProxyResultSet implements ProxyResultSet {

    private final OracleProxyStatement<?> statement;
    private final ResultSet delegate;

    private OracleProxyResultSet(OracleProxyStatement<?> statement, ResultSet delegate) {
        this.statement = statement;
        this.delegate = delegate;
    }

    public static OracleProxyResultSet build(OracleProxyStatement<?> statement, ResultSet delegate) {
        return new OracleProxyResultSet(statement, delegate);
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