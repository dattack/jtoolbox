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
package com.dattack.jtoolbox.jdbc.internal;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * This interface defines and provides a default implementation of a proxy to the standard JDBC interface
 * {@link java.sql.PreparedStatement}. All methods call the corresponding method on the "delegate" object provided by
 * the {@link #getDelegate()} method.
 *
 * @author cvarela
 * @since 0.6
 */
@SuppressWarnings("PMD.ExcessivePublicCount")
public interface ProxyPreparedStatement<P extends PreparedStatement> extends PreparedStatement, ProxyStatement<P> {

    @Override
    default void addBatch() throws SQLException {
        getDelegate().addBatch();
    }

    @Override
    default void clearParameters() throws SQLException {
        getDelegate().clearParameters();
    }

    @Override
    default boolean execute() throws SQLException {
        return getDelegate().execute();
    }

    @Override
    default long executeLargeUpdate() throws SQLException {
        return getDelegate().executeLargeUpdate();
    }

    @Override
    default int executeUpdate() throws SQLException {
        return getDelegate().executeUpdate();
    }

    @Override
    default ResultSetMetaData getMetaData() throws SQLException {
        return getDelegate().getMetaData();
    }

    @Override
    default ParameterMetaData getParameterMetaData() throws SQLException {
        return getDelegate().getParameterMetaData();
    }

    @Override
    default void setArray(int parameterIndex, Array x) throws SQLException {
        getDelegate().setArray(parameterIndex, x);
    }

    @Override
    default void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        getDelegate().setAsciiStream(parameterIndex, x, length);
    }

    @Override
    default void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        getDelegate().setAsciiStream(parameterIndex, x, length);
    }

    @Override
    default void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        getDelegate().setAsciiStream(parameterIndex, x);
    }

    @Override
    default void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        getDelegate().setBigDecimal(parameterIndex, x);
    }

    @Override
    default void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        getDelegate().setBinaryStream(parameterIndex, x, length);
    }

    @Override
    default void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        getDelegate().setBinaryStream(parameterIndex, x, length);
    }

    @Override
    default void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        getDelegate().setBinaryStream(parameterIndex, x);
    }

    @Override
    default void setBlob(int parameterIndex, Blob x) throws SQLException {
        getDelegate().setBlob(parameterIndex, x);
    }

    @Override
    default void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        getDelegate().setBlob(parameterIndex, inputStream, length);
    }

    @Override
    default void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        getDelegate().setBlob(parameterIndex, inputStream);
    }

    @Override
    default void setBoolean(int parameterIndex, boolean x) throws SQLException {
        getDelegate().setBoolean(parameterIndex, x);
    }

    @Override
    default void setByte(int parameterIndex, byte x) throws SQLException {
        getDelegate().setByte(parameterIndex, x);
    }

    @Override
    default void setBytes(int parameterIndex, byte[] x) throws SQLException {
        getDelegate().setBytes(parameterIndex, x);
    }

    @Override
    default void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        getDelegate().setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    default void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        getDelegate().setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    default void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        getDelegate().setCharacterStream(parameterIndex, reader);
    }

    @Override
    default void setClob(int parameterIndex, Clob x) throws SQLException {
        getDelegate().setClob(parameterIndex, x);
    }

    @Override
    default void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        getDelegate().setClob(parameterIndex, reader, length);
    }

    @Override
    default void setClob(int parameterIndex, Reader reader) throws SQLException {
        getDelegate().setClob(parameterIndex, reader);
    }

    @Override
    default void setDate(int parameterIndex, Date x) throws SQLException {
        getDelegate().setDate(parameterIndex, x);
    }

    @Override
    default void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        getDelegate().setDate(parameterIndex, x, cal);
    }

    @Override
    default void setDouble(int parameterIndex, double x) throws SQLException {
        getDelegate().setDouble(parameterIndex, x);
    }

    @Override
    default void setFloat(int parameterIndex, float x) throws SQLException {
        getDelegate().setFloat(parameterIndex, x);
    }

    @Override
    default void setInt(int parameterIndex, int x) throws SQLException {
        getDelegate().setInt(parameterIndex, x);
    }

    @Override
    default void setLong(int parameterIndex, long x) throws SQLException {
        getDelegate().setLong(parameterIndex, x);
    }

    @Override
    default void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        getDelegate().setNCharacterStream(parameterIndex, value, length);
    }

    @Override
    default void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        getDelegate().setNCharacterStream(parameterIndex, value);
    }

    @Override
    default void setNClob(int parameterIndex, NClob value) throws SQLException {
        getDelegate().setNClob(parameterIndex, value);
    }

    @Override
    default void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        getDelegate().setNClob(parameterIndex, reader, length);
    }

    @Override
    default void setNClob(int parameterIndex, Reader reader) throws SQLException {
        getDelegate().setNClob(parameterIndex, reader);
    }

    @Override
    default void setNString(int parameterIndex, String value) throws SQLException {
        getDelegate().setNString(parameterIndex, value);
    }

    @Override
    default void setNull(int parameterIndex, int sqlType) throws SQLException {
        getDelegate().setNull(parameterIndex, sqlType);
    }

    @Override
    default void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        getDelegate().setNull(parameterIndex, sqlType, typeName);
    }

    @Override
    default void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        getDelegate().setObject(parameterIndex, x, targetSqlType);
    }

    @Override
    default void setObject(int parameterIndex, Object x) throws SQLException {
        getDelegate().setObject(parameterIndex, x);
    }

    @Override
    default void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        getDelegate().setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    @Override
    default void setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        getDelegate().setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    @Override
    default void setObject(int parameterIndex, Object x, SQLType targetSqlType) throws SQLException {
        getDelegate().setObject(parameterIndex, x, targetSqlType);
    }

    @Override
    default void setRef(int parameterIndex, Ref x) throws SQLException {
        getDelegate().setRef(parameterIndex, x);
    }

    @Override
    default void setRowId(int parameterIndex, RowId x) throws SQLException {
        getDelegate().setRowId(parameterIndex, x);
    }

    @Override
    default void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        getDelegate().setSQLXML(parameterIndex, xmlObject);
    }

    @Override
    default void setShort(int parameterIndex, short x) throws SQLException {
        getDelegate().setShort(parameterIndex, x);
    }

    @Override
    default void setString(int parameterIndex, String x) throws SQLException {
        getDelegate().setString(parameterIndex, x);
    }

    @Override
    default void setTime(int parameterIndex, Time x) throws SQLException {
        getDelegate().setTime(parameterIndex, x);
    }

    @Override
    default void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        getDelegate().setTime(parameterIndex, x, cal);
    }

    @Override
    default void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        getDelegate().setTimestamp(parameterIndex, x);
    }

    @Override
    default void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        getDelegate().setTimestamp(parameterIndex, x, cal);
    }

    @Override
    default void setURL(int parameterIndex, URL x) throws SQLException {
        getDelegate().setURL(parameterIndex, x);
    }

    @Deprecated
    @Override
    default void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        getDelegate().setUnicodeStream(parameterIndex, x, length);
    }
}
