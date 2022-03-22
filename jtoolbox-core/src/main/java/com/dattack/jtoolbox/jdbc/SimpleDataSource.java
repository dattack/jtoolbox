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

import com.dattack.jtoolbox.jdbc.internal.ProxyConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

/**
 * A basic implementation of the standard JDBC DataSource interface that returns a new Connection from every
 * getConnection call.
 *
 * @author cvarela
 * @since 0.1
 */
public final class SimpleDataSource extends AbstractDataSource {

    private final transient String username;
    private final transient String password;
    private final transient String url;
    private final transient String driver;

    private transient volatile boolean ensureDriverLoadedNeeded;

    /**
     * Create a new SimpleDataSource with the given standard Driver parameters.
     *
     * @param driver the JDBC Driver object
     * @param url the JDBC URL to use for accessing the DriverManager
     * @param username - the JDBC username to use for accessing the DriverManager
     * @param password - the JDBC password to use for accessing the DriverManager
     */
    public SimpleDataSource(final String driver, final String url, final String username, final String password) {
        super();
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
        ensureDriverLoadedNeeded = true;
    }

    private void ensureDriverLoaded() throws SQLException {

        if (ensureDriverLoadedNeeded) {
            synchronized (this) {
                if (ensureDriverLoadedNeeded) {
                    try {
                        Class.forName(driver);
                        ensureDriverLoadedNeeded = false;
                    } catch (final ClassNotFoundException e) {
                        throw new SQLException("Unable to load the driver class: " + driver, e);
                    }
                }
            }
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getConnection(username, password);
    }

    @Override
    @SuppressWarnings("PMD.CloseResource")
    public Connection getConnection(final String user, final String pass) throws SQLException {

        ensureDriverLoaded();

        final Connection connection;
        if (Objects.isNull(user) || Objects.isNull(pass)) {
            connection = DriverManager.getConnection(url);
        } else {
            connection = DriverManager.getConnection(url, user, pass);
        }
        return ProxyConnectionFactory.build(connection);
    }
}
