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
package com.dattack.jtoolbox.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author cvarela
 * @since 0.1
 */
public final class PropertiesUtils {

    /**
     * Reads a {@link Properties} from the input file.
     *
     * @param file
     *            the File to load
     * @return the Properties object
     * @throws IOException
     *             if an I/O error occurs
     */
    public static Properties loadProperties(final File file) throws IOException {

        try (FileInputStream fin = new FileInputStream(file)) {
            final Properties properties = new Properties();
            properties.load(fin);
            return properties;
        }
    }

    /**
     * Null-safe utility method that allows converting a <code>Properties</code> object into a
     * <code>Map&lt;String, String&gt;</code>.
     *
     * @param properties the object to be converted
     * @return a map that will contain the same keys and values that exist in the Properties object.
     */
    public static Map<String, String> toMap(Properties properties) {

        if (properties == null) {
            return new HashMap<>();
        }

        return properties.entrySet().stream().collect(
                Collectors.toMap(
                        e -> e.getKey().toString(),
                        e -> e.getValue().toString()
                )
        );
    }

    private PropertiesUtils() {
        // utility class
    }
}
