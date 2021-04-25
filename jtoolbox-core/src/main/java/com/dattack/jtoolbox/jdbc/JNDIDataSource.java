/*
 * Copyright (c) 2016, The Dattack team (http://www.dattack.com)
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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Implementation of a <code>DataSource</code> that references a object registered in a JNDI context.
 *
 * @author cvarela
 * @since 0.1
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public final class JNDIDataSource extends AbstractDataSource {

    private final transient String jndiName;
    private transient volatile DataSource dataSource;

    public JNDIDataSource(final String jndiName) {
        super();
        this.jndiName = jndiName;
    }

    private void initializeDataSource() throws SQLException {
        try {
            final InitialContext context = new InitialContext();
            final Object obj = context.lookup(jndiName);
            if (Objects.isNull(obj)) {
                throw new SQLException("Unknown JNDI resource '" + jndiName + "'");
            }

            if (obj instanceof DataSource) {
                this.dataSource = (DataSource) obj;
            } else {
                throw new SQLException(String.format("Unable to get a Connection (JNDI-name: %s, Class: %s)", jndiName,
                        obj.getClass()));
            }

        } catch (final NamingException e) {
            throw new SQLException(
                    String.format("Unable to get a connection from JNDI name '%s': %s", jndiName, e.getMessage()), e);
        }
    }

    private DataSource getDataSource() throws SQLException {
        if (Objects.isNull(dataSource)) {
            synchronized (this) {
                if (Objects.isNull(dataSource)) {
                    initializeDataSource();
                }
            }
        }
        return dataSource;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    @Override
    public Connection getConnection(final String username, final String pwd) throws SQLException {
        throw new UnsupportedOperationException("Not implemented");
    }
}
