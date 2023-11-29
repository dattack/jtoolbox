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

import com.dattack.jtoolbox.jdbc.internal.ProxyStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * {@link ProxyStatement} specific implementation for an Oracle underlying {@link Statement}.
 *
 * @author cvarela
 * @since 0.6
 */
public class OracleProxyStatement<S extends Statement> implements ProxyStatement<S> {

    private final S delegate;
    private final OracleProxyConnection connection;

    protected OracleProxyStatement(final OracleProxyConnection connection, final S delegate) {
        this.delegate = delegate;
        this.connection = connection;
    }

    public static <S extends Statement> OracleProxyStatement<S> build(final OracleProxyConnection connection,
            final S delegate)
    {
        return new OracleProxyStatement<>(connection, delegate);
    }

    @Override
    public ResultSet executeQuery(final String sql) throws SQLException {
        return OracleProxyResultSet.build(this, getDelegate().executeQuery(sql));
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connection;
    }

    @Override
    public S getDelegate() {
        return delegate;
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return OracleProxyResultSet.build(this, getDelegate().getGeneratedKeys());
    }

    @Override
    public int getLobPrefetchSize() {
        return OracleUtils.getLobPrefetchSize(getInnermostDelegate());
    }

    @Override
    public void setLobPrefetchSize(final int value) {
        OracleUtils.setLobPrefetchSize(getInnermostDelegate(), value);
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return OracleProxyResultSet.build(this, getDelegate().getResultSet());
    }
}
