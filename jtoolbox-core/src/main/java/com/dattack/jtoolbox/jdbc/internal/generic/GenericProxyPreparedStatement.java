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
package com.dattack.jtoolbox.jdbc.internal.generic;

import com.dattack.jtoolbox.jdbc.internal.ProxyPreparedStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A delegating implementation of {@link PreparedStatement}. All methods call the corresponding method on the "delegate"
 * provided in the constructor.
 *
 * @author cvarela
 * @since 0.6
 */
public class GenericProxyPreparedStatement<P extends PreparedStatement> extends GenericProxyStatement<P>
        implements ProxyPreparedStatement<P> {

    protected GenericProxyPreparedStatement(final GenericProxyConnection connection, final P delegate) {
        super(connection, delegate);
    }

    public static <P extends PreparedStatement> GenericProxyPreparedStatement<P> build(
            final GenericProxyConnection connection, final P delegate) {
        return new GenericProxyPreparedStatement<>(connection, delegate);
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        return GenericProxyResultSet.build(this, getDelegate().executeQuery());
    }
}
