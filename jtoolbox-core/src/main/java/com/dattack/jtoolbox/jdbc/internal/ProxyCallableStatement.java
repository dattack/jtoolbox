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

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

/**
 * This interface defines and provides a default implementation of a proxy to the standard JDBC interface
 * {@link java.sql.CallableStatement}. All methods call the corresponding method on the "delegate" object provided by
 * the {@link #getDelegate()} method.
 *
 * @author cvarela
 * @since 0.6
 */
@SuppressWarnings({"PMD.TooManyMethods", "PMD.ExcessivePublicCount"})
public interface ProxyCallableStatement extends CallableStatement, ProxyPreparedStatement<CallableStatement> {

    @Override
    default Array getArray(int parameterIndex) throws SQLException {
        return getDelegate().getArray(parameterIndex);
    }

    @Override
    default Array getArray(String parameterName) throws SQLException {
        return getDelegate().getArray(parameterName);
    }

    @Deprecated
    @Override
    default BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
        return getDelegate().getBigDecimal(parameterIndex, scale);
    }

    @Override
    default BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        return getDelegate().getBigDecimal(parameterIndex);
    }

    @Override
    default BigDecimal getBigDecimal(String parameterName) throws SQLException {
        return getDelegate().getBigDecimal(parameterName);
    }

    @Override
    default Blob getBlob(int parameterIndex) throws SQLException {
        return getDelegate().getBlob(parameterIndex);
    }

    @Override
    default Blob getBlob(String parameterName) throws SQLException {
        return getDelegate().getBlob(parameterName);
    }

    @Override
    default boolean getBoolean(int parameterIndex) throws SQLException {
        return getDelegate().getBoolean(parameterIndex);
    }

    @Override
    default boolean getBoolean(String parameterName) throws SQLException {
        return getDelegate().getBoolean(parameterName);
    }

    @Override
    default byte getByte(int parameterIndex) throws SQLException {
        return getDelegate().getByte(parameterIndex);
    }

    @Override
    default byte getByte(String parameterName) throws SQLException {
        return getDelegate().getByte(parameterName);
    }

    @Override
    default byte[] getBytes(int parameterIndex) throws SQLException {
        return getDelegate().getBytes(parameterIndex);
    }

    @Override
    default byte[] getBytes(String parameterName) throws SQLException {
        return getDelegate().getBytes(parameterName);
    }

    @Override
    default Reader getCharacterStream(int parameterIndex) throws SQLException {
        return getDelegate().getCharacterStream(parameterIndex);
    }

    @Override
    default Reader getCharacterStream(String parameterName) throws SQLException {
        return getDelegate().getCharacterStream(parameterName);
    }

    @Override
    default Clob getClob(int parameterIndex) throws SQLException {
        return getDelegate().getClob(parameterIndex);
    }

    @Override
    default Clob getClob(String parameterName) throws SQLException {
        return getDelegate().getClob(parameterName);
    }

    @Override
    default Date getDate(int parameterIndex) throws SQLException {
        return getDelegate().getDate(parameterIndex);
    }

    @Override
    default Date getDate(int parameterIndex, Calendar cal) throws SQLException {
        return getDelegate().getDate(parameterIndex, cal);
    }

    @Override
    default Date getDate(String parameterName) throws SQLException {
        return getDelegate().getDate(parameterName);
    }

    @Override
    default Date getDate(String parameterName, Calendar cal) throws SQLException {
        return getDelegate().getDate(parameterName, cal);
    }

    @Override
    default double getDouble(int parameterIndex) throws SQLException {
        return getDelegate().getDouble(parameterIndex);
    }

    @Override
    default double getDouble(String parameterName) throws SQLException {
        return getDelegate().getDouble(parameterName);
    }

    @Override
    default float getFloat(int parameterIndex) throws SQLException {
        return getDelegate().getFloat(parameterIndex);
    }

    @Override
    default float getFloat(String parameterName) throws SQLException {
        return getDelegate().getFloat(parameterName);
    }

    @Override
    default int getInt(int parameterIndex) throws SQLException {
        return getDelegate().getInt(parameterIndex);
    }

    @Override
    default int getInt(String parameterName) throws SQLException {
        return getDelegate().getInt(parameterName);
    }

    @Override
    default long getLong(int parameterIndex) throws SQLException {
        return getDelegate().getLong(parameterIndex);
    }

    @Override
    default long getLong(String parameterName) throws SQLException {
        return getDelegate().getLong(parameterName);
    }

    @Override
    default Reader getNCharacterStream(int parameterIndex) throws SQLException {
        return getDelegate().getNCharacterStream(parameterIndex);
    }

    @Override
    default Reader getNCharacterStream(String parameterName) throws SQLException {
        return getDelegate().getNCharacterStream(parameterName);
    }

    @Override
    default NClob getNClob(int parameterIndex) throws SQLException {
        return getDelegate().getNClob(parameterIndex);
    }

    @Override
    default NClob getNClob(String parameterName) throws SQLException {
        return getDelegate().getNClob(parameterName);
    }

    @Override
    default String getNString(int parameterIndex) throws SQLException {
        return getDelegate().getNString(parameterIndex);
    }

    @Override
    default String getNString(String parameterName) throws SQLException {
        return getDelegate().getNString(parameterName);
    }

    @Override
    default Object getObject(int parameterIndex) throws SQLException {
        return getDelegate().getObject(parameterIndex);
    }

    @Override
    default Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
        return getDelegate().getObject(parameterIndex, map);
    }

    @Override
    default Object getObject(String parameterName) throws SQLException {
        return getDelegate().getObject(parameterName);
    }

    @Override
    default Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
        return getDelegate().getObject(parameterName, map);
    }

    @Override
    default <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
        return getDelegate().getObject(parameterIndex, type);
    }

    @Override
    default <T> T getObject(String parameterName, Class<T> type) throws SQLException {
        return getDelegate().getObject(parameterName, type);
    }

    @Override
    default Ref getRef(int parameterIndex) throws SQLException {
        return getDelegate().getRef(parameterIndex);
    }

    @Override
    default Ref getRef(String parameterName) throws SQLException {
        return getDelegate().getRef(parameterName);
    }

    @Override
    default RowId getRowId(int parameterIndex) throws SQLException {
        return getDelegate().getRowId(parameterIndex);
    }

    @Override
    default RowId getRowId(String parameterName) throws SQLException {
        return getDelegate().getRowId(parameterName);
    }

    @Override
    default SQLXML getSQLXML(int parameterIndex) throws SQLException {
        return getDelegate().getSQLXML(parameterIndex);
    }

    @Override
    default SQLXML getSQLXML(String parameterName) throws SQLException {
        return getDelegate().getSQLXML(parameterName);
    }

    @Override
    default short getShort(int parameterIndex) throws SQLException {
        return getDelegate().getShort(parameterIndex);
    }

    @Override
    default short getShort(String parameterName) throws SQLException {
        return getDelegate().getShort(parameterName);
    }

    @Override
    default String getString(int parameterIndex) throws SQLException {
        return getDelegate().getString(parameterIndex);
    }

    @Override
    default String getString(String parameterName) throws SQLException {
        return getDelegate().getString(parameterName);
    }

    @Override
    default Time getTime(int parameterIndex) throws SQLException {
        return getDelegate().getTime(parameterIndex);
    }

    @Override
    default Time getTime(int parameterIndex, Calendar cal) throws SQLException {
        return getDelegate().getTime(parameterIndex, cal);
    }

    @Override
    default Time getTime(String parameterName) throws SQLException {
        return getDelegate().getTime(parameterName);
    }

    @Override
    default Time getTime(String parameterName, Calendar cal) throws SQLException {
        return getDelegate().getTime(parameterName, cal);
    }

    @Override
    default Timestamp getTimestamp(int parameterIndex) throws SQLException {
        return getDelegate().getTimestamp(parameterIndex);
    }

    @Override
    default Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
        return getDelegate().getTimestamp(parameterIndex, cal);
    }

    @Override
    default Timestamp getTimestamp(String parameterName) throws SQLException {
        return getDelegate().getTimestamp(parameterName);
    }

    @Override
    default Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
        return getDelegate().getTimestamp(parameterName, cal);
    }

    @Override
    default URL getURL(int parameterIndex) throws SQLException {
        return getDelegate().getURL(parameterIndex);
    }

    @Override
    default URL getURL(String parameterName) throws SQLException {
        return getDelegate().getURL(parameterName);
    }

    @Override
    default void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
        getDelegate().registerOutParameter(parameterIndex, sqlType);
    }

    @Override
    default void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
        getDelegate().registerOutParameter(parameterIndex, sqlType, scale);
    }

    @Override
    default void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
        getDelegate().registerOutParameter(parameterIndex, sqlType, typeName);
    }

    @Override
    default void registerOutParameter(String parameterName, int sqlType) throws SQLException {
        getDelegate().registerOutParameter(parameterName, sqlType);
    }

    @Override
    default void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
        getDelegate().registerOutParameter(parameterName, sqlType, scale);
    }

    @Override
    default void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
        getDelegate().registerOutParameter(parameterName, sqlType, typeName);
    }

    @Override
    default void registerOutParameter(int parameterIndex, SQLType sqlType) throws SQLException {
        getDelegate().registerOutParameter(parameterIndex, sqlType);
    }

    @Override
    default void registerOutParameter(int parameterIndex, SQLType sqlType, int scale) throws SQLException {
        getDelegate().registerOutParameter(parameterIndex, sqlType, scale);
    }

    @Override
    default void registerOutParameter(int parameterIndex, SQLType sqlType, String typeName) throws SQLException {
        getDelegate().registerOutParameter(parameterIndex, sqlType, typeName);
    }

    @Override
    default void registerOutParameter(String parameterName, SQLType sqlType) throws SQLException {
        getDelegate().registerOutParameter(parameterName, sqlType);
    }

    @Override
    default void registerOutParameter(String parameterName, SQLType sqlType, int scale) throws SQLException {
        getDelegate().registerOutParameter(parameterName, sqlType, scale);
    }

    @Override
    default void registerOutParameter(String parameterName, SQLType sqlType, String typeName) throws SQLException {
        getDelegate().registerOutParameter(parameterName, sqlType, typeName);
    }

    @Override
    default void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
        getDelegate().setAsciiStream(parameterName, x, length);
    }

    @Override
    default void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
        getDelegate().setAsciiStream(parameterName, x, length);
    }

    @Override
    default void setAsciiStream(String parameterName, InputStream x) throws SQLException {
        getDelegate().setAsciiStream(parameterName, x);
    }

    @Override
    default void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
        getDelegate().setBigDecimal(parameterName, x);
    }

    @Override
    default void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
        getDelegate().setBinaryStream(parameterName, x, length);
    }

    @Override
    default void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
        getDelegate().setBinaryStream(parameterName, x, length);
    }

    @Override
    default void setBinaryStream(String parameterName, InputStream x) throws SQLException {
        getDelegate().setBinaryStream(parameterName, x);
    }

    @Override
    default void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
        getDelegate().setBlob(parameterName, inputStream, length);
    }

    @Override
    default void setBlob(String parameterName, Blob x) throws SQLException {
        getDelegate().setBlob(parameterName, x);
    }

    @Override
    default void setBlob(String parameterName, InputStream inputStream) throws SQLException {
        getDelegate().setBlob(parameterName, inputStream);
    }

    @Override
    default void setBoolean(String parameterName, boolean x) throws SQLException {
        getDelegate().setBoolean(parameterName, x);
    }

    @Override
    default void setByte(String parameterName, byte x) throws SQLException {
        getDelegate().setByte(parameterName, x);
    }

    @Override
    default void setBytes(String parameterName, byte[] x) throws SQLException {
        getDelegate().setBytes(parameterName, x);
    }

    @Override
    default void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
        getDelegate().setCharacterStream(parameterName, reader, length);
    }

    @Override
    default void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
        getDelegate().setCharacterStream(parameterName, reader, length);
    }

    @Override
    default void setCharacterStream(String parameterName, Reader reader) throws SQLException {
        getDelegate().setCharacterStream(parameterName, reader);
    }

    @Override
    default void setClob(String parameterName, Reader reader, long length) throws SQLException {
        getDelegate().setClob(parameterName, reader, length);
    }

    @Override
    default void setClob(String parameterName, Clob x) throws SQLException {
        getDelegate().setClob(parameterName, x);
    }

    @Override
    default void setClob(String parameterName, Reader reader) throws SQLException {
        getDelegate().setClob(parameterName, reader);
    }

    @Override
    default void setDate(String parameterName, Date x) throws SQLException {
        getDelegate().setDate(parameterName, x);
    }

    @Override
    default void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
        getDelegate().setDate(parameterName, x, cal);
    }

    @Override
    default void setDouble(String parameterName, double x) throws SQLException {
        getDelegate().setDouble(parameterName, x);
    }

    @Override
    default void setFloat(String parameterName, float x) throws SQLException {
        getDelegate().setFloat(parameterName, x);
    }

    @Override
    default void setInt(String parameterName, int x) throws SQLException {
        getDelegate().setInt(parameterName, x);
    }

    @Override
    default void setLong(String parameterName, long x) throws SQLException {
        getDelegate().setLong(parameterName, x);
    }

    @Override
    default void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
        getDelegate().setNCharacterStream(parameterName, value, length);
    }

    @Override
    default void setNCharacterStream(String parameterName, Reader value) throws SQLException {
        getDelegate().setNCharacterStream(parameterName, value);
    }

    @Override
    default void setNClob(String parameterName, NClob value) throws SQLException {
        getDelegate().setNClob(parameterName, value);
    }

    @Override
    default void setNClob(String parameterName, Reader reader, long length) throws SQLException {
        getDelegate().setNClob(parameterName, reader, length);
    }

    @Override
    default void setNClob(String parameterName, Reader reader) throws SQLException {
        getDelegate().setNClob(parameterName, reader);
    }

    @Override
    default void setNString(String parameterName, String value) throws SQLException {
        getDelegate().setNString(parameterName, value);
    }

    @Override
    default void setNull(String parameterName, int sqlType) throws SQLException {
        getDelegate().setNull(parameterName, sqlType);
    }

    @Override
    default void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
        getDelegate().setNull(parameterName, sqlType, typeName);
    }

    @Override
    default void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
        getDelegate().setObject(parameterName, x, targetSqlType, scale);
    }

    @Override
    default void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
        getDelegate().setObject(parameterName, x, targetSqlType);
    }

    @Override
    default void setObject(String parameterName, Object x) throws SQLException {
        getDelegate().setObject(parameterName, x);
    }

    @Override
    default void setObject(String parameterName, Object x, SQLType targetSqlType,
            int scaleOrLength) throws SQLException
    {
        getDelegate().setObject(parameterName, x, targetSqlType, scaleOrLength);
    }

    @Override
    default void setObject(String parameterName, Object x, SQLType targetSqlType) throws SQLException {
        getDelegate().setObject(parameterName, x, targetSqlType);
    }

    @Override
    default void setRowId(String parameterName, RowId x) throws SQLException {
        getDelegate().setRowId(parameterName, x);
    }

    @Override
    default void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
        getDelegate().setSQLXML(parameterName, xmlObject);
    }

    @Override
    default void setShort(String parameterName, short x) throws SQLException {
        getDelegate().setShort(parameterName, x);
    }

    @Override
    default void setString(String parameterName, String x) throws SQLException {
        getDelegate().setString(parameterName, x);
    }

    @Override
    default void setTime(String parameterName, Time x) throws SQLException {
        getDelegate().setTime(parameterName, x);
    }

    @Override
    default void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
        getDelegate().setTime(parameterName, x, cal);
    }

    @Override
    default void setTimestamp(String parameterName, Timestamp x) throws SQLException {
        getDelegate().setTimestamp(parameterName, x);
    }

    @Override
    default void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
        getDelegate().setTimestamp(parameterName, x, cal);
    }

    @Override
    default void setURL(String parameterName, URL val) throws SQLException {
        getDelegate().setURL(parameterName, val);
    }

    @Override
    default boolean wasNull() throws SQLException {
        return getDelegate().wasNull();
    }
}
