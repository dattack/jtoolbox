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
package com.dattack.jtoolbox.jdbc.internal;

import com.dattack.jtoolbox.jdbc.internal.generic.GenericProxyConnection;
import com.dattack.jtoolbox.jdbc.internal.oracle.OracleProxyConnection;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Factory used to instantiate concrete {@link ProxyConnection} objects.
 *
 * @author cvarela
 * @since 0.6
 */
public final class ProxyConnectionFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyConnectionFactory.class);

    private ProxyConnectionFactory() {
        // static class
    }

    @SuppressWarnings("PMD.CloseResource")
    public static ProxyConnection build(final Connection connection) throws SQLException {

        Objects.requireNonNull(connection, "connection must not be null");

        ProxyConnection proxy = tryCast(connection);
        if (proxy == null) {
            proxy = createProxy(connection);
        }
        return proxy;
    }

    private static ProxyConnection tryCast(final Connection connection) {
        return connection instanceof ProxyConnection ? (ProxyConnection) connection : null;
    }

    private static ProxyConnection createProxy(final Connection connection) throws SQLException {
        ProxyConnection proxy;
        if (StringUtils.containsIgnoreCase(connection.getMetaData().getDriverName(), "Oracle")) {
            LOGGER.trace("Creating an Oracle-proxy for connection {}", connection.getMetaData().getURL());
            proxy = OracleProxyConnection.build(connection);
        } else {
            LOGGER.trace("Creating an Generic-proxy for connection {}", connection.getMetaData().getURL());
            proxy = GenericProxyConnection.build(connection);
        }
        return proxy;
    }
}
