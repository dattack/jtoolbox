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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A collection of useful method to simplify working with JDBC.
 *
 * @author cvarela
 * @since 0.1
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public final class JDBCUtils {

    private static final Logger LOGGER = Logger.getLogger(JDBCUtils.class.getName());

    /**
     * Close a <code>Connection</code> ignoring <code>null</code> values and exceptions.
     *
     * @param connection
     *            the Connection to close, may be null.
     */
    public static void closeQuietly(final Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (final SQLException e) {
                LOGGER.log(Level.WARNING, "Unable to close the Connection object", e.getMessage());
            }
        }
    }

    /**
     * Close a <code>ResultSet</code> ignoring <code>null</code> values and exceptions.
     *
     * @param resultSet
     *            the ResultSet to close, may be null.
     */
    public static void closeQuietly(final ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (final SQLException e) {
                LOGGER.log(Level.WARNING, "Unable to close the ResultSet object", e.getMessage());
            }
        }
    }

    /**
     * Close a <code>Statement</code> ignoring <code>null</code> values and exceptions.
     *
     * @param stmt
     *            the Statement to close, may be null.
     */
    public static void closeQuietly(final Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (final SQLException e) {
                LOGGER.log(Level.WARNING, "Unable to close the Statement object", e.getMessage());
            }
        }
    }

    private JDBCUtils() {
        // utility class
    }
}
