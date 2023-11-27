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

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

/**
 * This interface defines and provides a default implementation of a proxy to the standard JDBC interface
 * {@link java.sql.ResultSet}. All methods call the corresponding method on the "delegate" object provided by the
 * {@link #getDelegate()} method.
 *
 * @author cvarela
 * @since 0.6
 */
@SuppressWarnings({"PMD.TooManyMethods", "PMD.ExcessivePublicCount"})
public interface ProxyResultSet extends ResultSet, JdbcObjectProxy<ResultSet> {

    @Override
    default boolean absolute(int row) throws SQLException {
        return getDelegate().absolute(row);
    }

    @Override
    default void afterLast() throws SQLException {
        getDelegate().afterLast();
    }

    @Override
    default void beforeFirst() throws SQLException {
        getDelegate().beforeFirst();
    }

    @Override
    default void cancelRowUpdates() throws SQLException {
        getDelegate().cancelRowUpdates();
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
    default void deleteRow() throws SQLException {
        getDelegate().deleteRow();
    }

    @Override
    default int findColumn(String columnLabel) throws SQLException {
        return getDelegate().findColumn(columnLabel);
    }

    @Override
    default boolean first() throws SQLException {
        return getDelegate().first();
    }

    @Override
    default Array getArray(int columnIndex) throws SQLException {
        return getDelegate().getArray(columnIndex);
    }

    @Override
    default Array getArray(String columnLabel) throws SQLException {
        return getDelegate().getArray(columnLabel);
    }

    @Override
    default InputStream getAsciiStream(int columnIndex) throws SQLException {
        return getDelegate().getAsciiStream(columnIndex);
    }

    @Override
    default InputStream getAsciiStream(String columnLabel) throws SQLException {
        return getDelegate().getAsciiStream(columnLabel);
    }

    @Deprecated
    @Override
    default BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
        return getDelegate().getBigDecimal(columnIndex, scale);
    }

    @Deprecated
    @Override
    default BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
        return getDelegate().getBigDecimal(columnLabel, scale);
    }

    @Override
    default BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        return getDelegate().getBigDecimal(columnIndex);
    }

    @Override
    default BigDecimal getBigDecimal(String columnLabel) throws SQLException {
        return getDelegate().getBigDecimal(columnLabel);
    }

    @Override
    default InputStream getBinaryStream(int columnIndex) throws SQLException {
        return getDelegate().getBinaryStream(columnIndex);
    }

    @Override
    default InputStream getBinaryStream(String columnLabel) throws SQLException {
        return getDelegate().getBinaryStream(columnLabel);
    }

    @Override
    default Blob getBlob(int columnIndex) throws SQLException {
        return getDelegate().getBlob(columnIndex);
    }

    @Override
    default Blob getBlob(String columnLabel) throws SQLException {
        return getDelegate().getBlob(columnLabel);
    }

    @Override
    default boolean getBoolean(int columnIndex) throws SQLException {
        return getDelegate().getBoolean(columnIndex);
    }

    @Override
    default boolean getBoolean(String columnLabel) throws SQLException {
        return getDelegate().getBoolean(columnLabel);
    }

    @Override
    default byte getByte(int columnIndex) throws SQLException {
        return getDelegate().getByte(columnIndex);
    }

    @Override
    default byte getByte(String columnLabel) throws SQLException {
        return getDelegate().getByte(columnLabel);
    }

    @Override
    default byte[] getBytes(int columnIndex) throws SQLException {
        return getDelegate().getBytes(columnIndex);
    }

    @Override
    default byte[] getBytes(String columnLabel) throws SQLException {
        return getDelegate().getBytes(columnLabel);
    }

    @Override
    default Reader getCharacterStream(int columnIndex) throws SQLException {
        return getDelegate().getCharacterStream(columnIndex);
    }

    @Override
    default Reader getCharacterStream(String columnLabel) throws SQLException {
        return getDelegate().getCharacterStream(columnLabel);
    }

    @Override
    default Clob getClob(int columnIndex) throws SQLException {
        return getDelegate().getClob(columnIndex);
    }

    @Override
    default Clob getClob(String columnLabel) throws SQLException {
        return getDelegate().getClob(columnLabel);
    }

    @Override
    default int getConcurrency() throws SQLException {
        return getDelegate().getConcurrency();
    }

    @Override
    default String getCursorName() throws SQLException {
        return getDelegate().getCursorName();
    }

    @Override
    default Date getDate(int columnIndex) throws SQLException {
        return getDelegate().getDate(columnIndex);
    }

    @Override
    default Date getDate(String columnLabel) throws SQLException {
        return getDelegate().getDate(columnLabel);
    }

    @Override
    default Date getDate(int columnIndex, Calendar cal) throws SQLException {
        return getDelegate().getDate(columnIndex, cal);
    }

    @Override
    default Date getDate(String columnLabel, Calendar cal) throws SQLException {
        return getDelegate().getDate(columnLabel, cal);
    }

    @Override
    default double getDouble(int columnIndex) throws SQLException {
        return getDelegate().getDouble(columnIndex);
    }

    @Override
    default double getDouble(String columnLabel) throws SQLException {
        return getDelegate().getDouble(columnLabel);
    }

    @Override
    default int getFetchDirection() throws SQLException {
        return getDelegate().getFetchDirection();
    }

    @Override
    default void setFetchDirection(int direction) throws SQLException {
        getDelegate().setFetchDirection(direction);
    }

    @Override
    default int getFetchSize() throws SQLException {
        return getDelegate().getFetchSize();
    }

    @Override
    default void setFetchSize(int rows) throws SQLException {
        getDelegate().setFetchSize(rows);
    }

    @Override
    default float getFloat(int columnIndex) throws SQLException {
        return getDelegate().getFloat(columnIndex);
    }

    @Override
    default float getFloat(String columnLabel) throws SQLException {
        return getDelegate().getFloat(columnLabel);
    }

    @Override
    default int getHoldability() throws SQLException {
        return getDelegate().getHoldability();
    }

    @Override
    default int getInt(int columnIndex) throws SQLException {
        return getDelegate().getInt(columnIndex);
    }

    @Override
    default int getInt(String columnLabel) throws SQLException {
        return getDelegate().getInt(columnLabel);
    }

    @Override
    default long getLong(int columnIndex) throws SQLException {
        return getDelegate().getLong(columnIndex);
    }

    @Override
    default long getLong(String columnLabel) throws SQLException {
        return getDelegate().getLong(columnLabel);
    }

    @Override
    default ResultSetMetaData getMetaData() throws SQLException {
        return getDelegate().getMetaData();
    }

    @Override
    default Reader getNCharacterStream(int columnIndex) throws SQLException {
        return getDelegate().getNCharacterStream(columnIndex);
    }

    @Override
    default Reader getNCharacterStream(String columnLabel) throws SQLException {
        return getDelegate().getNCharacterStream(columnLabel);
    }

    @Override
    default NClob getNClob(int columnIndex) throws SQLException {
        return getDelegate().getNClob(columnIndex);
    }

    @Override
    default NClob getNClob(String columnLabel) throws SQLException {
        return getDelegate().getNClob(columnLabel);
    }

    @Override
    default String getNString(int columnIndex) throws SQLException {
        return getDelegate().getNString(columnIndex);
    }

    @Override
    default String getNString(String columnLabel) throws SQLException {
        return getDelegate().getNString(columnLabel);
    }

    @Override
    default Object getObject(int columnIndex) throws SQLException {
        return getDelegate().getObject(columnIndex);
    }

    @Override
    default Object getObject(String columnLabel) throws SQLException {
        return getDelegate().getObject(columnLabel);
    }

    @Override
    default Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException {
        return getDelegate().getObject(columnIndex, map);
    }

    @Override
    default Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException {
        return getDelegate().getObject(columnLabel, map);
    }

    @Override
    default <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        return getDelegate().getObject(columnIndex, type);
    }

    @Override
    default <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
        return getDelegate().getObject(columnLabel, type);
    }

    @Override
    default Ref getRef(int columnIndex) throws SQLException {
        return getDelegate().getRef(columnIndex);
    }

    @Override
    default Ref getRef(String columnLabel) throws SQLException {
        return getDelegate().getRef(columnLabel);
    }

    @Override
    default int getRow() throws SQLException {
        return getDelegate().getRow();
    }

    @Override
    default RowId getRowId(int columnIndex) throws SQLException {
        return getDelegate().getRowId(columnIndex);
    }

    @Override
    default RowId getRowId(String columnLabel) throws SQLException {
        return getDelegate().getRowId(columnLabel);
    }

    @Override
    default SQLXML getSQLXML(int columnIndex) throws SQLException {
        return getDelegate().getSQLXML(columnIndex);
    }

    @Override
    default SQLXML getSQLXML(String columnLabel) throws SQLException {
        return getDelegate().getSQLXML(columnLabel);
    }

    @Override
    default short getShort(int columnIndex) throws SQLException {
        return getDelegate().getShort(columnIndex);
    }

    @Override
    default short getShort(String columnLabel) throws SQLException {
        return getDelegate().getShort(columnLabel);
    }

    @Override
    default String getString(int columnIndex) throws SQLException {
        return getDelegate().getString(columnIndex);
    }

    @Override
    default String getString(String columnLabel) throws SQLException {
        return getDelegate().getString(columnLabel);
    }

    @Override
    default Time getTime(int columnIndex) throws SQLException {
        return getDelegate().getTime(columnIndex);
    }

    @Override
    default Time getTime(String columnLabel) throws SQLException {
        return getDelegate().getTime(columnLabel);
    }

    @Override
    default Time getTime(int columnIndex, Calendar cal) throws SQLException {
        return getDelegate().getTime(columnIndex, cal);
    }

    @Override
    default Time getTime(String columnLabel, Calendar cal) throws SQLException {
        return getDelegate().getTime(columnLabel, cal);
    }

    @Override
    default Timestamp getTimestamp(int columnIndex) throws SQLException {
        return getDelegate().getTimestamp(columnIndex);
    }

    @Override
    default Timestamp getTimestamp(String columnLabel) throws SQLException {
        return getDelegate().getTimestamp(columnLabel);
    }

    @Override
    default Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
        return getDelegate().getTimestamp(columnIndex, cal);
    }

    @Override
    default Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
        return getDelegate().getTimestamp(columnLabel, cal);
    }

    @Override
    default int getType() throws SQLException {
        return getDelegate().getType();
    }

    @Override
    default URL getURL(int columnIndex) throws SQLException {
        return getDelegate().getURL(columnIndex);
    }

    @Override
    default URL getURL(String columnLabel) throws SQLException {
        return getDelegate().getURL(columnLabel);
    }

    @Deprecated
    @Override
    default InputStream getUnicodeStream(int columnIndex) throws SQLException {
        return getDelegate().getUnicodeStream(columnIndex);
    }

    @Deprecated
    @Override
    default InputStream getUnicodeStream(String columnLabel) throws SQLException {
        return getDelegate().getUnicodeStream(columnLabel);
    }

    @Override
    default SQLWarning getWarnings() throws SQLException {
        return getDelegate().getWarnings();
    }

    @Override
    default void insertRow() throws SQLException {
        getDelegate().insertRow();
    }

    @Override
    default boolean isAfterLast() throws SQLException {
        return getDelegate().isAfterLast();
    }

    @Override
    default boolean isBeforeFirst() throws SQLException {
        return getDelegate().isBeforeFirst();
    }

    @Override
    default boolean isClosed() throws SQLException {
        return getDelegate().isClosed();
    }

    @Override
    default boolean isFirst() throws SQLException {
        return getDelegate().isFirst();
    }

    @Override
    default boolean isLast() throws SQLException {
        return getDelegate().isLast();
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
    default boolean last() throws SQLException {
        return getDelegate().last();
    }

    @Override
    default void moveToCurrentRow() throws SQLException {
        getDelegate().moveToCurrentRow();
    }

    @Override
    default void moveToInsertRow() throws SQLException {
        getDelegate().moveToInsertRow();
    }

    @Override
    default boolean next() throws SQLException {
        return getDelegate().next();
    }

    @Override
    default boolean previous() throws SQLException {
        return getDelegate().previous();
    }

    @Override
    default void refreshRow() throws SQLException {
        getDelegate().refreshRow();
    }

    @Override
    default boolean relative(int rows) throws SQLException {
        return getDelegate().relative(rows);
    }

    @Override
    default boolean rowDeleted() throws SQLException {
        return getDelegate().rowDeleted();
    }

    @Override
    default boolean rowInserted() throws SQLException {
        return getDelegate().rowInserted();
    }

    @Override
    default boolean rowUpdated() throws SQLException {
        return getDelegate().rowUpdated();
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

    @Override
    default void updateArray(int columnIndex, Array x) throws SQLException {
        getDelegate().updateArray(columnIndex, x);
    }

    @Override
    default void updateArray(String columnLabel, Array x) throws SQLException {
        getDelegate().updateArray(columnLabel, x);
    }

    @Override
    default void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
        getDelegate().updateAsciiStream(columnIndex, x, length);
    }

    @Override
    default void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException {
        getDelegate().updateAsciiStream(columnLabel, x, length);
    }

    @Override
    default void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
        getDelegate().updateAsciiStream(columnIndex, x, length);
    }

    @Override
    default void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
        getDelegate().updateAsciiStream(columnLabel, x, length);
    }

    @Override
    default void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
        getDelegate().updateAsciiStream(columnIndex, x);
    }

    @Override
    default void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
        getDelegate().updateAsciiStream(columnLabel, x);
    }

    @Override
    default void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
        getDelegate().updateBigDecimal(columnIndex, x);
    }

    @Override
    default void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException {
        getDelegate().updateBigDecimal(columnLabel, x);
    }

    @Override
    default void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
        getDelegate().updateBinaryStream(columnIndex, x, length);
    }

    @Override
    default void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException {
        getDelegate().updateBinaryStream(columnLabel, x, length);
    }

    @Override
    default void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
        getDelegate().updateBinaryStream(columnIndex, x, length);
    }

    @Override
    default void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
        getDelegate().updateBinaryStream(columnLabel, x, length);
    }

    @Override
    default void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
        getDelegate().updateBinaryStream(columnIndex, x);
    }

    @Override
    default void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
        getDelegate().updateBinaryStream(columnLabel, x);
    }

    @Override
    default void updateBlob(int columnIndex, Blob x) throws SQLException {
        getDelegate().updateBlob(columnIndex, x);
    }

    @Override
    default void updateBlob(String columnLabel, Blob x) throws SQLException {
        getDelegate().updateBlob(columnLabel, x);
    }

    @Override
    default void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
        getDelegate().updateBlob(columnIndex, inputStream, length);
    }

    @Override
    default void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
        getDelegate().updateBlob(columnLabel, inputStream, length);
    }

    @Override
    default void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
        getDelegate().updateBlob(columnIndex, inputStream);
    }

    @Override
    default void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
        getDelegate().updateBlob(columnLabel, inputStream);
    }

    @Override
    default void updateBoolean(int columnIndex, boolean x) throws SQLException {
        getDelegate().updateBoolean(columnIndex, x);
    }

    @Override
    default void updateBoolean(String columnLabel, boolean x) throws SQLException {
        getDelegate().updateBoolean(columnLabel, x);
    }

    @Override
    default void updateByte(int columnIndex, byte x) throws SQLException {
        getDelegate().updateByte(columnIndex, x);
    }

    @Override
    default void updateByte(String columnLabel, byte x) throws SQLException {
        getDelegate().updateByte(columnLabel, x);
    }

    @Override
    default void updateBytes(int columnIndex, byte[] x) throws SQLException {
        getDelegate().updateBytes(columnIndex, x);
    }

    @Override
    default void updateBytes(String columnLabel, byte[] x) throws SQLException {
        getDelegate().updateBytes(columnLabel, x);
    }

    @Override
    default void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
        getDelegate().updateCharacterStream(columnIndex, x, length);
    }

    @Override
    default void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException {
        getDelegate().updateCharacterStream(columnLabel, reader, length);
    }

    @Override
    default void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        getDelegate().updateCharacterStream(columnIndex, x, length);
    }

    @Override
    default void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        getDelegate().updateCharacterStream(columnLabel, reader, length);
    }

    @Override
    default void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
        getDelegate().updateCharacterStream(columnIndex, x);
    }

    @Override
    default void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
        getDelegate().updateCharacterStream(columnLabel, reader);
    }

    @Override
    default void updateClob(int columnIndex, Clob x) throws SQLException {
        getDelegate().updateClob(columnIndex, x);
    }

    @Override
    default void updateClob(String columnLabel, Clob x) throws SQLException {
        getDelegate().updateClob(columnLabel, x);
    }

    @Override
    default void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
        getDelegate().updateClob(columnIndex, reader, length);
    }

    @Override
    default void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
        getDelegate().updateClob(columnLabel, reader, length);
    }

    @Override
    default void updateClob(int columnIndex, Reader reader) throws SQLException {
        getDelegate().updateClob(columnIndex, reader);
    }

    @Override
    default void updateClob(String columnLabel, Reader reader) throws SQLException {
        getDelegate().updateClob(columnLabel, reader);
    }

    @Override
    default void updateDate(int columnIndex, Date x) throws SQLException {
        getDelegate().updateDate(columnIndex, x);
    }

    @Override
    default void updateDate(String columnLabel, Date x) throws SQLException {
        getDelegate().updateDate(columnLabel, x);
    }

    @Override
    default void updateDouble(int columnIndex, double x) throws SQLException {
        getDelegate().updateDouble(columnIndex, x);
    }

    @Override
    default void updateDouble(String columnLabel, double x) throws SQLException {
        getDelegate().updateDouble(columnLabel, x);
    }

    @Override
    default void updateFloat(int columnIndex, float x) throws SQLException {
        getDelegate().updateFloat(columnIndex, x);
    }

    @Override
    default void updateFloat(String columnLabel, float x) throws SQLException {
        getDelegate().updateFloat(columnLabel, x);
    }

    @Override
    default void updateInt(int columnIndex, int x) throws SQLException {
        getDelegate().updateInt(columnIndex, x);
    }

    @Override
    default void updateInt(String columnLabel, int x) throws SQLException {
        getDelegate().updateInt(columnLabel, x);
    }

    @Override
    default void updateLong(int columnIndex, long x) throws SQLException {
        getDelegate().updateLong(columnIndex, x);
    }

    @Override
    default void updateLong(String columnLabel, long x) throws SQLException {
        getDelegate().updateLong(columnLabel, x);
    }

    @Override
    default void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        getDelegate().updateNCharacterStream(columnIndex, x, length);
    }

    @Override
    default void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        getDelegate().updateNCharacterStream(columnLabel, reader, length);
    }

    @Override
    default void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
        getDelegate().updateNCharacterStream(columnIndex, x);
    }

    @Override
    default void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
        getDelegate().updateNCharacterStream(columnLabel, reader);
    }

    @Override
    default void updateNClob(int columnIndex, NClob value) throws SQLException {
        getDelegate().updateNClob(columnIndex, value);
    }

    @Override
    default void updateNClob(String columnLabel, NClob value) throws SQLException {
        getDelegate().updateNClob(columnLabel, value);
    }

    @Override
    default void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
        getDelegate().updateNClob(columnIndex, reader, length);
    }

    @Override
    default void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
        getDelegate().updateNClob(columnLabel, reader, length);
    }

    @Override
    default void updateNClob(int columnIndex, Reader reader) throws SQLException {
        getDelegate().updateNClob(columnIndex, reader);
    }

    @Override
    default void updateNClob(String columnLabel, Reader reader) throws SQLException {
        getDelegate().updateNClob(columnLabel, reader);
    }

    @Override
    default void updateNString(int columnIndex, String value) throws SQLException {
        getDelegate().updateNString(columnIndex, value);
    }

    @Override
    default void updateNString(String columnLabel, String value) throws SQLException {
        getDelegate().updateNString(columnLabel, value);
    }

    @Override
    default void updateNull(int columnIndex) throws SQLException {
        getDelegate().updateNull(columnIndex);
    }

    @Override
    default void updateNull(String columnLabel) throws SQLException {
        getDelegate().updateNull(columnLabel);
    }

    @Override
    default void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException {
        getDelegate().updateObject(columnIndex, x, scaleOrLength);
    }

    @Override
    default void updateObject(int columnIndex, Object x) throws SQLException {
        getDelegate().updateObject(columnIndex, x);
    }

    @Override
    default void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException {
        getDelegate().updateObject(columnLabel, x, scaleOrLength);
    }

    @Override
    default void updateObject(String columnLabel, Object x) throws SQLException {
        getDelegate().updateObject(columnLabel, x);
    }

    @Override
    default void updateObject(int columnIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        getDelegate().updateObject(columnIndex, x, targetSqlType, scaleOrLength);
    }

    @Override
    default void updateObject(String columnLabel, Object x, SQLType targetSqlType,
            int scaleOrLength) throws SQLException
    {
        getDelegate().updateObject(columnLabel, x, targetSqlType, scaleOrLength);
    }

    @Override
    default void updateObject(int columnIndex, Object x, SQLType targetSqlType) throws SQLException {
        getDelegate().updateObject(columnIndex, x, targetSqlType);
    }

    @Override
    default void updateObject(String columnLabel, Object x, SQLType targetSqlType) throws SQLException {
        getDelegate().updateObject(columnLabel, x, targetSqlType);
    }

    @Override
    default void updateRef(int columnIndex, Ref x) throws SQLException {
        getDelegate().updateRef(columnIndex, x);
    }

    @Override
    default void updateRef(String columnLabel, Ref x) throws SQLException {
        getDelegate().updateRef(columnLabel, x);
    }

    @Override
    default void updateRow() throws SQLException {
        getDelegate().updateRow();
    }

    @Override
    default void updateRowId(int columnIndex, RowId x) throws SQLException {
        getDelegate().updateRowId(columnIndex, x);
    }

    @Override
    default void updateRowId(String columnLabel, RowId x) throws SQLException {
        getDelegate().updateRowId(columnLabel, x);
    }

    @Override
    default void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
        getDelegate().updateSQLXML(columnIndex, xmlObject);
    }

    @Override
    default void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
        getDelegate().updateSQLXML(columnLabel, xmlObject);
    }

    @Override
    default void updateShort(int columnIndex, short x) throws SQLException {
        getDelegate().updateShort(columnIndex, x);
    }

    @Override
    default void updateShort(String columnLabel, short x) throws SQLException {
        getDelegate().updateShort(columnLabel, x);
    }

    @Override
    default void updateString(int columnIndex, String x) throws SQLException {
        getDelegate().updateString(columnIndex, x);
    }

    @Override
    default void updateString(String columnLabel, String x) throws SQLException {
        getDelegate().updateString(columnLabel, x);
    }

    @Override
    default void updateTime(int columnIndex, Time x) throws SQLException {
        getDelegate().updateTime(columnIndex, x);
    }

    @Override
    default void updateTime(String columnLabel, Time x) throws SQLException {
        getDelegate().updateTime(columnLabel, x);
    }

    @Override
    default void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
        getDelegate().updateTimestamp(columnIndex, x);
    }

    @Override
    default void updateTimestamp(String columnLabel, Timestamp x) throws SQLException {
        getDelegate().updateTimestamp(columnLabel, x);
    }

    @Override
    default boolean wasNull() throws SQLException {
        return getDelegate().wasNull();
    }
}
