/*
 * Copyright (c) 2017, The Dattack team (http://www.dattack.com)
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

import com.dattack.jtoolbox.jdbc.internal.NamedPreparedStatementConfig;
import com.dattack.jtoolbox.jdbc.internal.ProxyNamedPreparedStatement;
import org.apache.commons.lang.StringUtils;

import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * {@link ProxyNamedPreparedStatement} specific implementation for an Oracle underlying {@link PreparedStatement}.
 *
 * @author cvarela
 * @since 0.6
 */
public class OracleProxyNamedPreparedStatement extends OracleProxyPreparedStatement<PreparedStatement>
        implements ProxyNamedPreparedStatement {

    private static final int MAX_LENGTH_CLOB_AS_VARCHAR = 4_000;

    private final transient NamedPreparedStatementConfig namedPreparedStatementConfig;

    protected OracleProxyNamedPreparedStatement(final OracleProxyConnection connection,
            final PreparedStatement delegate, final NamedPreparedStatementConfig namedPreparedStatementConfig) {
        super(connection, delegate);
        this.namedPreparedStatementConfig = namedPreparedStatementConfig;
    }

    /**
     * Creates a <code>NamedParameterPreparedStatement</code> object for sending parameterized SQL statements to the
     * database.
     *
     * @param connection a connection (session) with a specific database.
     * @param sql        a SQL statement that may contain one or more ':parameterName' IN parameter placeholders
     * @return a <code>NamedParameterPreparedStatement</code> object containing the pre-compiled SQL statement
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    public static OracleProxyNamedPreparedStatement build(final OracleProxyConnection connection,
            final String sql) throws SQLException {
        final NamedPreparedStatementConfig preparedStatementConfig = NamedPreparedStatementConfig.parse(sql);
        return new OracleProxyNamedPreparedStatement(connection, connection.prepareStatement(
                preparedStatementConfig.getCompiledSql()), preparedStatementConfig);
    }

    @Override
    public NamedPreparedStatementConfig getNamedPreparedStatementConfig() {
        return namedPreparedStatementConfig;
    }

    @Override
    public void setClob(final String parameterName, final String value) throws SQLException {
        if (StringUtils.length(value) < MAX_LENGTH_CLOB_AS_VARCHAR) {
            setString(parameterName, value);
        } else {
            setClob(parameterName, createClob(value));
        }
    }

    private Clob createClob(String value) throws SQLException {
        Clob clob = getConnection().createClob();
        clob.setString(1, value);
        return clob;
    }
}
