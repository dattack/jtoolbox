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

import com.dattack.jtoolbox.jdbc.JdbcObjectProxy;

import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * This interface defines and provides a default implementation of a proxy to the standard JDBC interface
 * {@link java.sql.Connection}. All methods call the corresponding method on the "delegate" object provided by the
 * {@link #getDelegate()} method.
 *
 * @author cvarela
 * @since 0.6
 */
@SuppressWarnings("PMD.TooManyMethods")
public interface ProxyConnection extends ExtendedConnection, JdbcObjectProxy<Connection> {

    @Override
    default void abort(Executor executor) throws SQLException {
        getDelegate().abort(executor);
    }

    @Override
    default void clearWarnings() throws SQLException {
        getDelegate().clearWarnings();
    }

    @Override
    default void close() throws SQLException {
        getDelegate().close();
    }

    @Override
    default void commit() throws SQLException {
        getDelegate().commit();
    }

    @Override
    default Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return getDelegate().createArrayOf(typeName, elements);
    }

    @Override
    default Blob createBlob() throws SQLException {
        return getDelegate().createBlob();
    }

    @Override
    default Clob createClob() throws SQLException {
        return getDelegate().createClob();
    }

    @Override
    default NClob createNClob() throws SQLException {
        return getDelegate().createNClob();
    }

    @Override
    default SQLXML createSQLXML() throws SQLException {
        return getDelegate().createSQLXML();
    }

    @Override
    default Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return getDelegate().createStruct(typeName, attributes);
    }

    @Override
    default boolean getAutoCommit() throws SQLException {
        return getDelegate().getAutoCommit();
    }

    @Override
    default void setAutoCommit(boolean autoCommit) throws SQLException {
        getDelegate().setAutoCommit(autoCommit);
    }

    @Override
    default String getCatalog() throws SQLException {
        return getDelegate().getCatalog();
    }

    @Override
    default void setCatalog(String catalog) throws SQLException {
        getDelegate().setCatalog(catalog);
    }

    @Override
    default String getClientInfo(String name) throws SQLException {
        return getDelegate().getClientInfo(name);
    }

    @Override
    default Properties getClientInfo() throws SQLException {
        return getDelegate().getClientInfo();
    }

    @Override
    default void setClientInfo(Properties properties) throws SQLClientInfoException {
        getDelegate().setClientInfo(properties);
    }

    @Override
    default int getHoldability() throws SQLException {
        return getDelegate().getHoldability();
    }

    @Override
    default void setHoldability(int holdability) throws SQLException {
        getDelegate().setHoldability(holdability);
    }

    @Override
    default DatabaseMetaData getMetaData() throws SQLException {
        return getDelegate().getMetaData();
    }

    @Override
    default int getNetworkTimeout() throws SQLException {
        return getDelegate().getNetworkTimeout();
    }

    @Override
    default String getSchema() throws SQLException {
        return getDelegate().getSchema();
    }

    @Override
    default void setSchema(String schema) throws SQLException {
        getDelegate().setSchema(schema);
    }

    @Override
    default int getTransactionIsolation() throws SQLException {
        return getDelegate().getTransactionIsolation();
    }

    @Override
    default void setTransactionIsolation(int level) throws SQLException {
        getDelegate().setTransactionIsolation(level);
    }

    @Override
    default Map<String, Class<?>> getTypeMap() throws SQLException {
        return getDelegate().getTypeMap();
    }

    @Override
    default void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        getDelegate().setTypeMap(map);
    }

    @Override
    default SQLWarning getWarnings() throws SQLException {
        return getDelegate().getWarnings();
    }

    @Override
    default boolean isClosed() throws SQLException {
        return getDelegate().isClosed();
    }

    @Override
    default boolean isReadOnly() throws SQLException {
        return getDelegate().isReadOnly();
    }

    @Override
    default void setReadOnly(boolean readOnly) throws SQLException {
        getDelegate().setReadOnly(readOnly);
    }

    @Override
    default boolean isValid(int timeout) throws SQLException {
        return getDelegate().isValid(timeout);
    }

    @Override
    default boolean isWrapperFor(Class<?> iface) throws SQLException {
        if (iface.isAssignableFrom(getClass())) {
            return true;
        }
        if (iface.isAssignableFrom(getDelegate().getClass())) {
            return true;
        }
        return getDelegate().isWrapperFor(iface);
    }

    @Override
    default String nativeSQL(String sql) throws SQLException {
        return getDelegate().nativeSQL(sql);
    }

    @Override
    default void releaseSavepoint(Savepoint savepoint) throws SQLException {
        getDelegate().releaseSavepoint(savepoint);
    }

    @Override
    default void rollback() throws SQLException {
        getDelegate().rollback();
    }

    @Override
    default void rollback(Savepoint savepoint) throws SQLException {
        getDelegate().rollback(savepoint);
    }

    @Override
    default void setClientInfo(String name, String value) throws SQLClientInfoException {
        getDelegate().setClientInfo(name, value);
    }

    @Override
    default void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        getDelegate().setNetworkTimeout(executor, milliseconds);
    }

    @Override
    default Savepoint setSavepoint() throws SQLException {
        return getDelegate().setSavepoint();
    }

    @Override
    default Savepoint setSavepoint(String name) throws SQLException {
        return getDelegate().setSavepoint(name);
    }

    @Override
    default <E> E unwrap(Class<E> iface) throws SQLException {
        if (iface.isAssignableFrom(getClass())) {
            return iface.cast(this);
        }
        if (iface.isAssignableFrom(getDelegate().getClass())) {
            return iface.cast(getDelegate());
        }
        return getDelegate().unwrap(iface);
    }
}
