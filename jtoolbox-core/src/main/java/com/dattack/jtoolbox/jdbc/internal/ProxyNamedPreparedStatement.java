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
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * This interface defines and provides a default implementation of a proxy to the interface
 * {@link NamedPreparedStatement}. All methods call the corresponding method on the "delegate" object provided by
 * the {@link #getDelegate()} method.
 *
 * @author cvarela
 * @since 0.6
 */
@SuppressWarnings("PMD.ExcessivePublicCount")
public interface ProxyNamedPreparedStatement extends NamedPreparedStatement, ProxyPreparedStatement<PreparedStatement> {

    NamedPreparedStatementConfig getNamedPreparedStatementConfig();

    @Override
    default boolean hasNamedParameter(final String parameter) {
        return getNamedPreparedStatementConfig().hasNamedParameter(parameter);
    }

    @Override
    default boolean hasNamedParameters() {
        return getNamedPreparedStatementConfig().hasNamedParameters();
    }

    @Override
    default void setArray(final String parameterName, final Array value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setArray(i, value);
        }
    }

    @Override
    default void setAsciiStream(final String parameterName, final InputStream value) throws SQLException {

        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setAsciiStream(i, value);
        }
    }

    @Override
    default void setAsciiStream(final String parameterName, final InputStream value,
            final int length) throws SQLException
    {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setAsciiStream(i, value, length);
        }
    }

    @Override
    default void setAsciiStream(final String parameterName, final InputStream value,
            final long length) throws SQLException
    {
        for (final Integer index : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setAsciiStream(index, value, length);
        }
    }

    @Override
    default void setBigDecimal(final String parameterName, final BigDecimal value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setBigDecimal(i, value);
        }
    }

    @Override
    default void setBinaryStream(final String parameterName, final InputStream value,
            final int length) throws SQLException
    {

        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setBinaryStream(i, value, length);
        }
    }

    @Override
    default void setBinaryStream(final String parameterName, final InputStream value,
            final long length) throws SQLException
    {
        for (final Integer index : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setBinaryStream(index, value, length);
        }
    }

    @Override
    default void setBinaryStream(final String parameterName, final InputStream value) throws SQLException {
        for (final Integer index : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setBinaryStream(index, value);
        }
    }

    @Override
    default void setBlob(final String parameterName, final Blob value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setBlob(i, value);
        }
    }

    @Override
    default void setBlob(final String parameterName, final InputStream value, final long length) throws SQLException {
        for (final Integer index : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setBlob(index, value, length);
        }
    }

    @Override
    default void setBlob(final String parameterName, final InputStream value) throws SQLException {
        for (final Integer index : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setBlob(index, value);
        }
    }

    @Override
    default void setBoolean(final String parameterName, final boolean value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setBoolean(i, value);
        }
    }

    @Override
    default void setByte(final String parameterName, final byte value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setByte(i, value);
        }
    }

    @Override
    default void setBytes(final String parameterName, final byte[] value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setBytes(i, value);
        }
    }

    @Override
    default void setCharacterStream(final String parameterName, final Reader value,
            final int length) throws SQLException
    {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setCharacterStream(i, value, length);
        }
    }

    @Override
    default void setCharacterStream(final String parameterName, final Reader value,
            final long length) throws SQLException
    {
        for (final Integer index : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setCharacterStream(index, value, length);
        }
    }

    @Override
    default void setCharacterStream(final String parameterName, final Reader value) throws SQLException {
        for (final Integer index : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setCharacterStream(index, value);
        }
    }

    @Override
    default void setClob(final String parameterName, final Clob value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setClob(i, value);
        }
    }

    @Override
    default void setClob(final String parameterName, final Reader value, final long length) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setClob(i, value, length);
        }
    }

    @Override
    default void setClob(final String parameterName, final Reader value) throws SQLException {
        for (final Integer index : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setClob(index, value);
        }
    }

    @Override
    default void setClob(final String parameterName, final String value) throws SQLException {
        Clob clob = getConnection().createClob();
        clob.setString(1, value);
        setClob(parameterName, clob);

    }

    @Override
    default void setDate(final String parameterName, final Date value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setDate(i, value);
        }
    }

    @Override
    default void setDate(final String parameterName, final Date value, final Calendar cal) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setDate(i, value, cal);
        }
    }

    @Override
    default void setDouble(final String parameterName, final double value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setDouble(i, value);
        }
    }

    @Override
    default void setFloat(final String parameterName, final float value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setFloat(i, value);
        }
    }

    @Override
    default void setInt(final String parameterName, final int value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setInt(i, value);
        }
    }

    @Override
    default void setLong(final String parameterName, final long value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setLong(i, value);
        }
    }

    @Override
    default void setNCharacterStream(final String parameterName, final Reader value,
            final long length) throws SQLException
    {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setNCharacterStream(i, value, length);
        }
    }

    @Override
    default void setNCharacterStream(final String parameterName, final Reader value) throws SQLException {
        for (final Integer index : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setNCharacterStream(index, value);
        }
    }

    @Override
    default void setNClob(final String parameterName, final NClob value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setNClob(i, value);
        }
    }

    @Override
    default void setNClob(final String parameterName, final Reader value, final long length) throws SQLException {
        for (final Integer index : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setNClob(index, value, length);
        }
    }

    @Override
    default void setNClob(final String parameterName, final Reader value) throws SQLException {
        for (final Integer index : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setNClob(index, value);
        }
    }

    @Override
    default void setNString(final String parameterName, final String value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setNString(i, value);
        }
    }

    @Override
    default void setNull(final String parameterName, final int sqlType) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setNull(i, sqlType);
        }
    }

    @Override
    default void setNull(final String parameterName, final int value, final String typeName) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setNull(i, value, typeName);
        }
    }

    @Override
    default void setObject(final String parameterName, final Object value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setObject(i, value);
        }
    }

    @Override
    default void setObject(final String parameterName, final Object value,
            final int targetSqlType) throws SQLException
    {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setObject(i, value, targetSqlType);
        }
    }

    @Override
    default void setObject(final String parameterName, final Object value, final int targetSqlType,
            final int scaleOrLength) throws SQLException
    {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setObject(i, value, targetSqlType, scaleOrLength);
        }
    }

    @Override
    default void setRef(final String parameterName, final Ref value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setRef(i, value);
        }
    }

    @Override
    default void setRowId(final String parameterName, final RowId value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setRowId(i, value);
        }
    }

    @Override
    default void setSQLXML(final String parameterName, final SQLXML value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setSQLXML(i, value);
        }
    }

    @Override
    default void setShort(final String parameterName, final short value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setShort(i, value);
        }
    }

    @Override
    default void setString(final String parameterName, final String value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setString(i, value);
        }
    }

    @Override
    default void setTime(final String parameterName, final Time value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setTime(i, value);
        }
    }

    @Override
    default void setTime(final String parameterName, final Time value, final Calendar cal) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setTime(i, value, cal);
        }
    }

    @Override
    default void setTimestamp(final String parameterName, final Timestamp value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setTimestamp(i, value);
        }
    }

    @Override
    default void setTimestamp(final String parameterName, final Timestamp value,
            final Calendar cal) throws SQLException
    {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setTimestamp(i, value, cal);
        }
    }

    @Override
    default void setURL(final String parameterName, final URL value) throws SQLException {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setURL(i, value);
        }
    }

    @Deprecated
    @Override
    default void setUnicodeStream(final String parameterName, final InputStream value,
            final int length) throws SQLException
    {
        for (final Integer i : getNamedPreparedStatementConfig().getParameterIndexes(parameterName)) {
            getDelegate().setUnicodeStream(i, value, length);
        }
    }
}
