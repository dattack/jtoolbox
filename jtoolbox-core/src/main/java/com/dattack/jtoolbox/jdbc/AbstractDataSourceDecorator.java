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

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 * Implementation of the decorator pattern on a DataSource object. All operations are delegated to the decorated object.
 *
 * @author cvarela
 * @since 0.1
 */
public abstract class AbstractDataSourceDecorator implements DataSource {

    private final DataSource inner;

    public AbstractDataSourceDecorator(final DataSource inner) {
        this.inner = inner;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return inner.getConnection();
    }

    @Override
    public Connection getConnection(final String username, final String password) throws SQLException {
        return inner.getConnection(username, password);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return inner.getLoginTimeout();
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return inner.getLogWriter();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return inner.getParentLogger();
    }

    @Override
    public boolean isWrapperFor(final Class<?> iface) throws SQLException {
        return inner.isWrapperFor(iface);
    }

    @Override
    public void setLoginTimeout(final int seconds) throws SQLException {
        inner.setLoginTimeout(seconds);
    }

    @Override
    public void setLogWriter(final PrintWriter out) throws SQLException {
        inner.setLogWriter(out);
    }

    @Override
    public <T> T unwrap(final Class<T> iface) throws SQLException {
        return inner.unwrap(iface);
    }
}
