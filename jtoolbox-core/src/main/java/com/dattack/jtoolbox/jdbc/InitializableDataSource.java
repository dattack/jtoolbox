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

import com.dattack.jtoolbox.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;

/**
 * Implementation of a <code>DataSource</code> decorator that allows to execute initialization scripts when obtaining
 * a connection from the decorated datasource.
 *
 * @author cvarela
 * @since 0.4
 */
@SuppressWarnings("PMD.LongVariable")
public final class InitializableDataSource extends AbstractDataSourceDecorator {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitializableDataSource.class);

    private final transient List<String> onConnectStatements;

    public InitializableDataSource(final DataSource inner, final List<String> onConnectStatements) {
        super(inner);
        this.onConnectStatements = onConnectStatements;
    }

    @Override
    public Connection getConnection() throws SQLException {
        final Connection connection = super.getConnection();
        initialize(connection);
        return connection;
    }

    @Override
    public Connection getConnection(final String username, final String pwd) throws SQLException {
        final Connection connection = super.getConnection(username, pwd);
        initialize(connection);
        return connection;
    }

    @SuppressWarnings("PMD.ExceptionAsFlowControl")
    private void initialize(final Connection connection) throws SQLException {

        if (CollectionUtils.isNotEmpty(onConnectStatements)) {
            for (final String sqlStatement : onConnectStatements) {
                try (final Statement stmt = connection.createStatement()) { //NOPMD
                    try {
                        LOGGER.debug("Running statement: {}", sqlStatement);
                        stmt.executeUpdate(sqlStatement);
                    } catch (final SQLException e) {
                        throw new SQLException(String.format("Error executing initialization statement '%s': %s",
                                sqlStatement, e.getMessage()), e);
                    }
                }
            }
        }
    }
}