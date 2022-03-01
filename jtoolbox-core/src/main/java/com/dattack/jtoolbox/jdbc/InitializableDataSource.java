/*
 * Copyright (c) 2020, The Dattack team (http://www.dattack.com)
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

import com.dattack.jtoolbox.jdbc.internal.ProxyConnectionFactory;
import com.dattack.jtoolbox.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;

/**
 * Implementation of a {@link DataSource} decorator that allows to execute initialization scripts when obtaining
 * a connection from the decorated datasource.
 *
 * @author cvarela
 * @since 0.4
 */
public final class InitializableDataSource extends AbstractDataSourceDecorator {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitializableDataSource.class);

    private final transient List<String> onConnectStatements;

    public InitializableDataSource(final DataSource delegate, final List<String> onConnectStatements) {
        super(delegate);
        this.onConnectStatements = onConnectStatements;
    }

    @Override
    @SuppressWarnings("PMD.CloseResource")
    public Connection getConnection() throws SQLException {
        final Connection connection = super.getConnection();
        initialize(connection);
        return ProxyConnectionFactory.build(connection);
    }

    @Override
    @SuppressWarnings("PMD.CloseResource")
    public Connection getConnection(final String username, final String pwd) throws SQLException {
        final Connection connection = super.getConnection(username, pwd);
        initialize(connection);
        return ProxyConnectionFactory.build(connection);
    }

    private void initialize(final Connection connection) throws SQLException {

        if (CollectionUtils.isNotEmpty(onConnectStatements)) {
            for (final String sqlStatement : onConnectStatements) {
                try (Statement stmt = connection.createStatement()) {
                    execute(stmt, sqlStatement);
                }
            }
        }
    }

    private void execute(final Statement stmt, final String sql) throws SQLException {
        try {
            LOGGER.debug("Running statement: {}", sql);
            stmt.executeUpdate(sql);
        } catch (final SQLException e) {
            throw new SQLException(String.format("Error executing initialization statement '%s': %s",
                    sql, e.getMessage()), e);
        }
    }
}
