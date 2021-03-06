/*
 * Copyright (c) 2017, The Dattack team (http://www.dattack.com)
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

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * A base delegating implementation of {@link PreparedStatement} interface. All of the methods from this class are
 * delegated to the provided object.
 *
 * @author cvarela
 * @since 0.2
 */
public class DelegatingPreparedStatement implements PreparedStatement {

    private final PreparedStatement delegate;

    public DelegatingPreparedStatement(final PreparedStatement delegate) {
        this.delegate = delegate;
    }

    @Override
    public void addBatch() throws SQLException {
        delegate.addBatch();
    }

    @Override
    public void addBatch(final String sql) throws SQLException {
        delegate.addBatch(sql);
    }

    @Override
    public void cancel() throws SQLException {
        delegate.cancel();
    }

    @Override
    public void clearBatch() throws SQLException {
        delegate.clearBatch();
    }

    @Override
    public void clearParameters() throws SQLException {
        delegate.clearParameters();
    }

    @Override
    public void clearWarnings() throws SQLException {
        delegate.clearWarnings();
    }

    @Override
    public void close() throws SQLException {
        delegate.close();
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        delegate.closeOnCompletion();
    }

    @Override
    public boolean execute() throws SQLException {
        return delegate.execute();
    }

    @Override
    public boolean execute(final String sql) throws SQLException {
        return delegate.execute(sql);
    }

    @Override
    public boolean execute(final String sql, final int autoGeneratedKeys) throws SQLException {
        return delegate.execute(sql, autoGeneratedKeys);
    }

    @Override
    public boolean execute(final String sql, final int[] columnIndexes) throws SQLException {
        return delegate.execute(sql, columnIndexes);
    }

    @Override
    public boolean execute(final String sql, final String[] columnNames) throws SQLException {
        return delegate.execute(sql, columnNames);
    }

    @Override
    public int[] executeBatch() throws SQLException {
        return delegate.executeBatch();
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        return delegate.executeQuery();
    }

    @Override
    public ResultSet executeQuery(final String sql) throws SQLException {
        return delegate.executeQuery(sql);
    }

    @Override
    public int executeUpdate() throws SQLException {
        return delegate.executeUpdate();
    }

    @Override
    public int executeUpdate(final String sql) throws SQLException {
        return delegate.executeUpdate(sql);
    }

    @Override
    public int executeUpdate(final String sql, final int autoGeneratedKeys) throws SQLException {
        return delegate.executeUpdate(sql, autoGeneratedKeys);
    }

    @Override
    public int executeUpdate(final String sql, final int[] columnIndexes) throws SQLException {
        return delegate.executeUpdate(sql, columnIndexes);
    }

    @Override
    public int executeUpdate(final String sql, final String[] columnNames) throws SQLException {
        return delegate.executeUpdate(sql, columnNames);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return delegate.getConnection();
    }

    protected PreparedStatement getDelegate() {
        return delegate;
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return delegate.getFetchDirection();
    }

    @Override
    public int getFetchSize() throws SQLException {
        return delegate.getFetchSize();
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return delegate.getGeneratedKeys();
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return delegate.getMaxFieldSize();
    }

    @Override
    public int getMaxRows() throws SQLException {
        return delegate.getMaxRows();
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return delegate.getMetaData();
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return delegate.getMoreResults();
    }

    @Override
    public boolean getMoreResults(final int current) throws SQLException {
        return delegate.getMoreResults(current);
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return delegate.getParameterMetaData();
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return delegate.getQueryTimeout();
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return delegate.getResultSet();
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return delegate.getResultSetConcurrency();
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return delegate.getResultSetHoldability();
    }

    @Override
    public int getResultSetType() throws SQLException {
        return delegate.getResultSetType();
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return delegate.getUpdateCount();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return delegate.getWarnings();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return delegate.isClosed();
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return delegate.isCloseOnCompletion();
    }

    @Override
    public boolean isPoolable() throws SQLException {
        return delegate.isPoolable();
    }

    @Override
    public boolean isWrapperFor(final Class<?> iface) throws SQLException {
        return delegate.isWrapperFor(iface);
    }

    @Override
    public void setArray(final int parameterIndex, final Array value) throws SQLException {
        delegate.setArray(parameterIndex, value);
    }

    @Override
    public void setAsciiStream(final int parameterIndex, final InputStream value) throws SQLException {
        delegate.setAsciiStream(parameterIndex, value);
    }

    @Override
    public void setAsciiStream(final int parameterIndex, final InputStream value, final int length)
            throws SQLException {
        delegate.setAsciiStream(parameterIndex, value, length);
    }

    @Override
    public void setAsciiStream(final int parameterIndex, final InputStream value, final long length)
            throws SQLException {
        delegate.setAsciiStream(parameterIndex, value, length);
    }

    @Override
    public void setBigDecimal(final int parameterIndex, final BigDecimal value) throws SQLException {
        delegate.setBigDecimal(parameterIndex, value);
    }

    @Override
    public void setBinaryStream(final int parameterIndex, final InputStream value) throws SQLException {
        delegate.setBinaryStream(parameterIndex, value);
    }

    @Override
    public void setBinaryStream(final int parameterIndex, final InputStream value, final int length)
            throws SQLException {
        delegate.setBinaryStream(parameterIndex, value, length);
    }

    @Override
    public void setBinaryStream(final int parameterIndex, final InputStream value, final long length)
            throws SQLException {
        delegate.setBinaryStream(parameterIndex, value, length);
    }

    @Override
    public void setBlob(final int parameterIndex, final Blob value) throws SQLException {
        delegate.setBlob(parameterIndex, value);
    }

    @Override
    public void setBlob(final int parameterIndex, final InputStream inputStream) throws SQLException {
        delegate.setBlob(parameterIndex, inputStream);
    }

    @Override
    public void setBlob(final int parameterIndex, final InputStream inputStream, final long length)
            throws SQLException {
        delegate.setBlob(parameterIndex, inputStream, length);
    }

    @Override
    public void setBoolean(final int parameterIndex, final boolean value) throws SQLException {
        delegate.setBoolean(parameterIndex, value);
    }

    @Override
    public void setByte(final int parameterIndex, final byte value) throws SQLException {
        delegate.setByte(parameterIndex, value);
    }

    @Override
    public void setBytes(final int parameterIndex, final byte[] value) throws SQLException {
        delegate.setBytes(parameterIndex, value);
    }

    @Override
    public void setCharacterStream(final int parameterIndex, final Reader reader) throws SQLException {
        delegate.setCharacterStream(parameterIndex, reader);
    }

    @Override
    public void setCharacterStream(final int parameterIndex, final Reader reader, final int length)
            throws SQLException {
        delegate.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setCharacterStream(final int parameterIndex, final Reader reader, final long length)
            throws SQLException {
        delegate.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setClob(final int parameterIndex, final Clob value) throws SQLException {
        delegate.setClob(parameterIndex, value);
    }

    @Override
    public void setClob(final int parameterIndex, final Reader reader) throws SQLException {
        delegate.setClob(parameterIndex, reader);
    }

    @Override
    public void setClob(final int parameterIndex, final Reader reader, final long length) throws SQLException {
        delegate.setClob(parameterIndex, reader, length);
    }

    @Override
    public void setCursorName(final String name) throws SQLException {
        delegate.setCursorName(name);
    }

    @Override
    public void setDate(final int parameterIndex, final Date value) throws SQLException {
        delegate.setDate(parameterIndex, value);
    }

    @Override
    public void setDate(final int parameterIndex, final Date value, final Calendar cal) throws SQLException {
        delegate.setDate(parameterIndex, value, cal);
    }

    @Override
    public void setDouble(final int parameterIndex, final double value) throws SQLException {
        delegate.setDouble(parameterIndex, value);
    }

    @Override
    public void setEscapeProcessing(final boolean enable) throws SQLException {
        delegate.setEscapeProcessing(enable);
    }

    @Override
    public void setFetchDirection(final int direction) throws SQLException {
        delegate.setFetchDirection(direction);
    }

    @Override
    public void setFetchSize(final int rows) throws SQLException {
        delegate.setFetchSize(rows);
    }

    @Override
    public void setFloat(final int parameterIndex, final float value) throws SQLException {
        delegate.setFloat(parameterIndex, value);
    }

    @Override
    public void setInt(final int parameterIndex, final int value) throws SQLException {
        delegate.setInt(parameterIndex, value);
    }

    @Override
    public void setLong(final int parameterIndex, final long value) throws SQLException {
        delegate.setLong(parameterIndex, value);
    }

    @Override
    public void setMaxFieldSize(final int max) throws SQLException {
        delegate.setMaxFieldSize(max);
    }

    @Override
    public void setMaxRows(final int max) throws SQLException {
        delegate.setMaxRows(max);
    }

    @Override
    public void setNCharacterStream(final int parameterIndex, final Reader value) throws SQLException {
        delegate.setNCharacterStream(parameterIndex, value);
    }

    @Override
    public void setNCharacterStream(final int parameterIndex, final Reader value, final long length)
            throws SQLException {
        delegate.setNCharacterStream(parameterIndex, value, length);
    }

    @Override
    public void setNClob(final int parameterIndex, final NClob value) throws SQLException {
        delegate.setNClob(parameterIndex, value);
    }

    @Override
    public void setNClob(final int parameterIndex, final Reader reader) throws SQLException {
        delegate.setNClob(parameterIndex, reader);
    }

    @Override
    public void setNClob(final int parameterIndex, final Reader reader, final long length) throws SQLException {
        delegate.setNClob(parameterIndex, reader, length);
    }

    @Override
    public void setNString(final int parameterIndex, final String value) throws SQLException {
        delegate.setNString(parameterIndex, value);
    }

    @Override
    public void setNull(final int parameterIndex, final int sqlType) throws SQLException {
        delegate.setNull(parameterIndex, sqlType);
    }

    @Override
    public void setNull(final int parameterIndex, final int sqlType, final String typeName) throws SQLException {
        delegate.setNull(parameterIndex, sqlType, typeName);
    }

    @Override
    public void setObject(final int parameterIndex, final Object value) throws SQLException {
        delegate.setObject(parameterIndex, value);
    }

    @Override
    public void setObject(final int parameterIndex, final Object value, final int targetSqlType) throws SQLException {
        delegate.setObject(parameterIndex, value, targetSqlType);
    }

    @Override
    public void setObject(final int parameterIndex, final Object value, final int targetSqlType,
                          final int scaleOrLength) throws SQLException {
        delegate.setObject(parameterIndex, value, targetSqlType, scaleOrLength);
    }

    @Override
    public void setPoolable(final boolean poolable) throws SQLException {
        delegate.setPoolable(poolable);
    }

    @Override
    public void setQueryTimeout(final int seconds) throws SQLException {
        delegate.setQueryTimeout(seconds);
    }

    @Override
    public void setRef(final int parameterIndex, final Ref value) throws SQLException {
        delegate.setRef(parameterIndex, value);
    }

    @Override
    public void setRowId(final int parameterIndex, final RowId value) throws SQLException {
        delegate.setRowId(parameterIndex, value);
    }

    @Override
    public void setShort(final int parameterIndex, final short value) throws SQLException {
        delegate.setShort(parameterIndex, value);
    }

    @Override
    public void setSQLXML(final int parameterIndex, final SQLXML xmlObject) throws SQLException {
        delegate.setSQLXML(parameterIndex, xmlObject);
    }

    @Override
    public void setString(final int parameterIndex, final String value) throws SQLException {
        delegate.setString(parameterIndex, value);
    }

    @Override
    public void setTime(final int parameterIndex, final Time value) throws SQLException {
        delegate.setTime(parameterIndex, value);
    }

    @Override
    public void setTime(final int parameterIndex, final Time value, final Calendar cal) throws SQLException {
        delegate.setTime(parameterIndex, value, cal);
    }

    @Override
    public void setTimestamp(final int parameterIndex, final Timestamp value) throws SQLException {
        delegate.setTimestamp(parameterIndex, value);
    }

    @Override
    public void setTimestamp(final int parameterIndex, final Timestamp value, final Calendar cal) throws SQLException {
        delegate.setTimestamp(parameterIndex, value, cal);
    }

    @Override
    @Deprecated
    public void setUnicodeStream(final int parameterIndex, final InputStream value, final int length)
            throws SQLException {
        delegate.setUnicodeStream(parameterIndex, value, length);
    }

    @Override
    public void setURL(final int parameterIndex, final URL value) throws SQLException {
        delegate.setURL(parameterIndex, value);
    }

    @Override
    public <T> T unwrap(final Class<T> iface) throws SQLException {
        return delegate.unwrap(iface);
    }
}
