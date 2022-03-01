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

import com.dattack.jtoolbox.jdbc.internal.ProxyPreparedStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link ProxyPreparedStatement} specific implementation for an Oracle underlying {@link PreparedStatement}.
 *
 * @author cvarela
 * @since 0.6
 */
public class OracleProxyPreparedStatement<P extends PreparedStatement> extends OracleProxyStatement<P>
        implements ProxyPreparedStatement<P> {

    protected OracleProxyPreparedStatement(final OracleProxyConnection connection, final P delegate) {
        super(connection, delegate);
    }

    public static <P extends PreparedStatement> OracleProxyPreparedStatement<P> build(
            final OracleProxyConnection connection, final P delegate) {
        return new OracleProxyPreparedStatement<>(connection, delegate);
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        return OracleProxyResultSet.build(this, getDelegate().executeQuery());
    }
}
