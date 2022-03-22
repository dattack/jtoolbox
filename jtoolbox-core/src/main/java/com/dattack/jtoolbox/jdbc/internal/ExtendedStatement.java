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

import java.sql.SQLException;
import java.sql.Statement;

/**
 * This interface defines the extensions to the standard JDBC interface {@link java.sql.Statement}.
 *
 * @author cvarela
 * @since 0.6
 */
public interface ExtendedStatement extends Statement {

    /**
     * <p>Returns the LOB prefetch size.</p>
     * <p><b>This is an Oracle driver exclusive property.</b></p>
     * <p>See <code>oracle.jdbc.OracleStatement#getLobPrefetchSize()</code>for details.</p>
     *
     * @return the LOB prefetch size
     * @throws SQLException when a database error occurs
     */
    int getLobPrefetchSize() throws SQLException;

    /**
     * <p>Overrides the LOB prefetch size for this statement.</p>
     * <p><b>This is an Oracle driver exclusive property.</b> See <code>oracle.jdbc.OracleStatement#setLobPrefetchSize
     * (int)</code>for details.</p>
     *
     * @param value must be &gt;= -1. -1 disables the feature. 0 enables LOB prefetch of metadata only (lob length and
     *              chunk size). Any value &gt;=0 represents the number of bytes to be prefetched for BLOB and the
     *              number of chars for CLOB.
     * @throws SQLException if value &lt; -1
     */
    void setLobPrefetchSize(int value) throws SQLException;
}
