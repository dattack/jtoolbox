/*
 * Copyright (c) 2016, The Dattack team (http://www.dattack.com)
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
package com.dattack.jtoolbox.io;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * A collection of useful method to simplify the I/O operations.
 *
 * @author cvarela
 * @since 0.1
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public final class IOUtils {

    private static final Logger LOGGER = Logger.getLogger(IOUtils.class.getName());

    /**
     * Closes a {@link Closeable} unconditionally.
     *
     * @param obj
     *            the object to close, may be null
     */
    @SuppressWarnings("PMD.EmptyCatchBlock")
    public static void closeQuietly(final Closeable obj) {
        if (obj != null) {
            try {
                obj.close();
            } catch (final IOException e) {
                LOGGER.finest(e.getMessage());
            }
        }
    }

    private IOUtils() {
        // utility class
    }
}
