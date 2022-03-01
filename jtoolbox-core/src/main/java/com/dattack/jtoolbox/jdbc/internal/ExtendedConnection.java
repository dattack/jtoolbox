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

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This interface defines the extensions to the standard JDBC interface {@link java.sql.Connection}.
 *
 * @author cvarela
 * @since 0.6
 */
public interface ExtendedConnection extends Connection {

    /**
     * Creates a {@link NamedPreparedStatement} object for sending parameterized SQL statements to the database.
     *
     * @param sql a SQL statement that may contain one or more ':param' IN parameter placeholders
     * @return a new default NamedPreparedStatement object containing the pre-compiled SQL statement
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    NamedPreparedStatement prepareNamedStatement(String sql) throws SQLException;
}
