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

import com.dattack.jtoolbox.jdbc.internal.ExtendedStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Utility methods to handle operations on oracle.jdbc.OracleStatement instances.
 *
 * @author cvarela
 * @since 0.6
 */
public final class OracleUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleUtils.class);
    private static final String ORACLE_STMT_CLASS = "oracle.jdbc.OracleStatement";

    private OracleUtils() {
        // static class
    }

    /**
     * Dynamic execution of the method getLobPrefetchSize on an instance of oracle.jdbc.OracleStatement.
     *
     * @param statement the statement
     * @return the LOB prefetch size
     * @see ExtendedStatement#getLobPrefetchSize()
     */
    public static int getLobPrefetchSize(final Statement statement) {
        if (statement != null) {
            try {
                Object oracleStmt = getOracle(statement);
                if (oracleStmt != null) {
                    final Method method = oracleStmt.getClass().getDeclaredMethod("getLobPrefetchSize");
                    method.setAccessible(true);
                    return (int) method.invoke(oracleStmt);
                }
            } catch (SQLException | ReflectiveOperationException e) {
                LOGGER.warn("Unable to get LOB prefetch size ({})", e.getMessage());
            }
        }
        return -1;
    }

    private static Object getOracle(final Statement statement) throws SQLException, ClassNotFoundException {
        Object oraStmt = null;
        final Class<?> oracleClass = Class.forName(ORACLE_STMT_CLASS);
        if (statement.isWrapperFor(oracleClass)) {
            oraStmt = statement.unwrap(oracleClass);
        }
        return oraStmt;
    }

    /**
     * Dynamic execution of the method setLobPrefetchSize(int) on an instance of oracle.jdbc.OracleStatement.
     *
     * @param statement the statement
     * @param value     the value to set
     * @see ExtendedStatement#setLobPrefetchSize(int)
     */
    public static void setLobPrefetchSize(final Statement statement, final int value) {
        if (statement != null) {
            try {
                Object oracleStmt = getOracle(statement);
                if (oracleStmt != null) {
                    final Method method = oracleStmt.getClass().getDeclaredMethod("setLobPrefetchSize", int.class);
                    method.setAccessible(true);
                    method.invoke(oracleStmt, value);
                }
            } catch (SQLException | ReflectiveOperationException e) {
                LOGGER.warn("Unable to set LOB prefetch size ({})", e.getMessage());
            }
        }
    }
}
