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
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 * Core implementation of the final datasources defined in this package.
 *
 * @author cvarela
 * @since 0.1
 */
public abstract class AbstractDataSource implements DataSource {

    private transient int loginTimeout;
    private transient PrintWriter logWriter;

    public AbstractDataSource() {
        this.loginTimeout = 0; // zero means that the timeout is the default system timeout
        // When a DataSource object is created, the log writer is initially null
    }

    @Override
    public int getLoginTimeout() {
        return loginTimeout;
    }

    @Override
    public PrintWriter getLogWriter() {
        return logWriter;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public boolean isWrapperFor(final Class<?> iface) {
        return false;
    }

    @Override
    public void setLoginTimeout(final int loginTimeout) {
        this.loginTimeout = loginTimeout;
    }

    @Override
    public void setLogWriter(final PrintWriter logWriter) {
        this.logWriter = logWriter;
    }

    @Override
    public <T> T unwrap(final Class<T> iface) throws SQLException {
        throw new SQLException("This object is not a wrapper");
    }
}
