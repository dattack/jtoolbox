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
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * A {@link java.sql.PreparedStatement} with named parameter markers instead of standard parameter markers. A
 * named parameter marker is identified by the format <i>:param_name</i>.
 *
 * @author cvarela
 * @since 0.6
 */
@SuppressWarnings({"PMD.ExcessivePublicCount", "checkstyle:AbbreviationAsWordInName"})
public interface NamedPreparedStatement extends PreparedStatement {

    boolean hasNamedParameter(final String parameter);

    boolean hasNamedParameters();

    /**
     * {@link java.sql.PreparedStatement#setArray(int, Array)}.
     *
     * @param parameterName the parameter name
     * @param value         an <code>Array</code> object that maps an SQL <code>ARRAY</code> value
     * @throws SQLException                    if parameterName does not correspond to a parameter marker in the SQL
     *                                         statement; if a
     *                                         database access error occurs or this method is called on a closed
     *                                         PreparedStatement
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support this method
     */
    void setArray(final String parameterName, final Array value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setAsciiStream(int, InputStream)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setAsciiStream(final String parameterName, final InputStream value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setAsciiStream(int, InputStream, int)}.
     *
     * @param parameterName the parameter name
     * @param value         the Java input stream that contains the ASCII parameter value
     * @param length        the number of bytes in the stream
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setAsciiStream(final String parameterName, final InputStream value, final int length) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setAsciiStream(int, InputStream, long)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @param length        the number of characters in the parameter data.
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setAsciiStream(final String parameterName, final InputStream value, final long length) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setBigDecimal(int, BigDecimal)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setBigDecimal(final String parameterName, final BigDecimal value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setBinaryStream(int, InputStream, int)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @param length        the number of bytes in the stream
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setBinaryStream(final String parameterName, final InputStream value, final int length) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setBinaryStream(int, InputStream, long)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @param length        the number of characters in the parameter data.
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setBinaryStream(final String parameterName, final InputStream value, final long length) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setBinaryStream(int, InputStream)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setBinaryStream(final String parameterName, final InputStream value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setBlob(int, Blob)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setBlob(final String parameterName, final Blob value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setBlob(int, InputStream, long)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @param length        the number of characters in the parameter data.
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setBlob(final String parameterName, final InputStream value, final long length) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setBlob(int, InputStream)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setBlob(final String parameterName, final InputStream value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setBoolean(int, boolean)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setBoolean(final String parameterName, final boolean value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setByte(int, byte)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setByte(final String parameterName, final byte value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setBytes(int, byte[])}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setBytes(final String parameterName, final byte[] value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setCharacterStream(int, Reader, int)}.
     *
     * @param parameterName the parameter name
     * @param value         the <code>java.io.Reader</code> object that contains the
     *                      Unicode data
     * @param length        the number of characters in the stream
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setCharacterStream(final String parameterName, final Reader value, final int length) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setCharacterStream(int, Reader, long)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @param length        the number of characters in the parameter data.
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setCharacterStream(final String parameterName, final Reader value, final long length) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setCharacterStream(int, Reader)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setCharacterStream(final String parameterName, final Reader value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setClob(int, Clob)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setClob(final String parameterName, final Clob value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setClob(int, Clob)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setClob(final String parameterName, final String value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setClob(int, Reader, long)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @param length        the number of characters in the parameter data.
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setClob(final String parameterName, final Reader value, final long length) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setClob(int, Reader)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setClob(final String parameterName, final Reader value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setDate(int, Date)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setDate(final String parameterName, final Date value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setDate(int, Date, Calendar)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @param cal           the <code>Calendar</code> object the driver will use
     *                      to construct the date
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setDate(final String parameterName, final Date value, final Calendar cal) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setDouble(int, double)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setDouble(final String parameterName, final double value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setFloat(int, float)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setFloat(final String parameterName, final float value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setInt(int, int)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setInt(final String parameterName, final int value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setLong(int, long)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setLong(final String parameterName, final long value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setNCharacterStream(int, Reader, long)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @param length        the number of characters in the parameter data.
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */

    void setNCharacterStream(final String parameterName, final Reader value, final long length) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setNCharacterStream(int, Reader)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setNCharacterStream(final String parameterName, final Reader value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setNClob(int, NClob)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setNClob(final String parameterName, final NClob value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setNClob(int, Reader, long)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @param length        the number of characters in the parameter data.
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setNClob(final String parameterName, final Reader value, final long length) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setNClob(int, Reader)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setNClob(final String parameterName, final Reader value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setNString(int, String)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setNString(final String parameterName, final String value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setNull(int, int)}.
     *
     * @param parameterName the parameter name
     * @param sqlType       the SQL type code defined in <code>java.sql.Types</code>
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setNull(final String parameterName, final int sqlType) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setNull(int, int, String)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @param typeName      the fully-qualified name of an SQL user-defined type;
     *                      ignored if the parameter is not a user-defined type or REF
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setNull(final String parameterName, final int value, final String typeName) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setObject(int, Object)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setObject(final String parameterName, final Object value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setObject(int, Object, int)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @param targetSqlType the SQL type (as defined in java.sql.Types) to be sent to the database. The scale
     *                      argument may further qualify this type.
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setObject(final String parameterName, final Object value, final int targetSqlType) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setObject(int, Object, int, int)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @param targetSqlType the SQL type (as defined in java.sql.Types) to be sent to the database. The scale
     *                      argument may further
     *                      qualify this type.
     * @param scaleOrLength for java.sql.Types.DECIMAL or java.sql.Types.NUMERIC types, this is the number of digits
     *                      after the
     *                      decimal point. For Java Object types InputStream and Reader, this is the length of the
     *                      data in the
     *                      stream or reader. For all other types, this value will be ignored.
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setObject(final String parameterName, final Object value, final int targetSqlType,
            final int scaleOrLength) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setRef(int, Ref)}.
     *
     * @param parameterName the parameter name
     * @param value         an SQL <code>REF</code> value
     * @throws SQLException                    if parameterName does not correspond to a parameter marker in the SQL
     *                                         statement; if a
     *                                         database access error occurs or this method is called on a closed
     *                                         PreparedStatement
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support this method
     */
    void setRef(final String parameterName, final Ref value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setRowId(int, RowId)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setRowId(final String parameterName, final RowId value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setSQLXML(int, SQLXML)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setSQLXML(final String parameterName, final SQLXML value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setShort(int, short)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setShort(final String parameterName, final short value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setString(int, String)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setString(final String parameterName, final String value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setTime(int, Time)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setTime(final String parameterName, final Time value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setTime(int, Time, Calendar)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @param cal           the <code>Calendar</code> object the driver will use
     *                      to construct the time
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setTime(final String parameterName, final Time value, final Calendar cal) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setTimestamp(int, Timestamp)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setTimestamp(final String parameterName, final Timestamp value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setTimestamp(int, Timestamp, Calendar)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @param cal           the <code>Calendar</code> object the driver will use
     *                      to construct the timestamp
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setTimestamp(final String parameterName, final Timestamp value, final Calendar cal) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setURL(int, URL)}.
     *
     * @param parameterName the parameter name
     * @param value         the parameter value
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    void setURL(final String parameterName, final URL value) throws SQLException;

    /**
     * {@link java.sql.PreparedStatement#setUnicodeStream(int, InputStream, int)}.
     *
     * @param parameterName the parameter name
     * @param value         a java.io.InputStream object that contains the Unicode parameter value
     * @param length        the number of bytes in the stream
     * @throws SQLException if parameterName does not correspond to a parameter marker in the SQL statement; if a
     *                      database access error occurs or this method is called on a closed PreparedStatement
     */
    @Deprecated
    void setUnicodeStream(final String parameterName, final InputStream value, final int length) throws SQLException;
}
