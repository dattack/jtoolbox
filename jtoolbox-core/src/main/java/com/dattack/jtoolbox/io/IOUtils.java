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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A collection of useful method to simplify the I/O operations.
 *
 * @author cvarela
 * @since 0.1
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public final class IOUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(IOUtils.class);

    /**
     * Closes a {@link Closeable} unconditionally.
     *
     * @param obj
     *            the object to close, may be null
     */
    public static void closeQuietly(final Closeable obj) {
        if (obj != null) {
            try {
                obj.close();
            } catch (final IOException e) {
                LOGGER.debug("Error closing object {}: {}", obj, e.getMessage());
            }
        }
    }

    /**
     * Scans the specified directory and returns the files and directories whose name contains the specified pattern.
     * This method is case-insensitive and null-safe.
     *
     * @param dir the directory to scan
     * @param pattern the pattern to find
     * @return the files and directories whose name contains the specified pattern
     * @throws IOException if an I/O error occurs when reading the directory
     */
    public static Set<Path> listFiles(Path dir, String pattern) throws IOException {

        if (dir == null || !Files.isDirectory(dir)) {
            return Collections.emptySet();
        }

        try (Stream<Path> stream = Files.list(dir)) {
            return stream //
                    .filter(path -> StringUtils.isEmpty(pattern) //
                            || StringUtils.containsIgnoreCase(path.getFileName().toString(), pattern)) //
                    .collect(Collectors.toSet());
        }
    }

    private IOUtils() {
        // utility class
    }
}
