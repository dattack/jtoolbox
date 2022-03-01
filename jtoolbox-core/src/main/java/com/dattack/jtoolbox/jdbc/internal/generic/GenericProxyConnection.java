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

import com.dattack.jtoolbox.jdbc.internal.AbstractProxyConnection;
import com.dattack.jtoolbox.jdbc.internal.NamedPreparedStatement;
import com.dattack.jtoolbox.jdbc.internal.ProxyConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * {@link ProxyConnection} generic implementation for an underlying connection.
 *
 * @author cvarela
 * @see com.dattack.jtoolbox.jdbc.internal.oracle.OracleProxyConnection
 * @since 0.6
 */
public class GenericProxyConnection extends AbstractProxyConnection {

    protected GenericProxyConnection(final Connection delegate) {
        super(delegate);
    }

    public static GenericProxyConnection build(Connection delegate) {
        return new GenericProxyConnection(delegate);
    }

    @Override
    public NamedPreparedStatement prepareNamedStatement(String sql) throws SQLException {
        return GenericProxyNamedPreparedStatement.build(this, sql);
    }

    @Override
    protected Statement doCreateStatement(final Statement statement) {
        return GenericProxyStatement.build(this, statement);
    }

    @Override
    protected CallableStatement doPrepareCall(final CallableStatement callableStatement) {
        return GenericProxyCallableStatement.build(this, callableStatement);
    }

    @Override
    protected PreparedStatement doPrepareStatement(final PreparedStatement preparedStatement) {
        return GenericProxyPreparedStatement.build(this, preparedStatement);
    }
}
