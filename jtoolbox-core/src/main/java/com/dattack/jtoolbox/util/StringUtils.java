/*
 * Copyright (c) 2021, The Dattack team (http://www.dattack.com)
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

import java.util.Objects;

/**
 * Utility class to perform common operations with String objects.
 *
 * @author cvarela
 * @since 0.4
 * @deprecated Use {@link org.apache.commons.lang.StringUtils}
 */
@Deprecated
public final class StringUtils {

    /**
     * Checks if a String is null, empty ("") or whitespace only.
     *
     * @param str the String to check, may be null
     * @return true if the String is null, empty or whitespace only
     */
    public static boolean isBlank(final String str) {

        boolean isBlank = true;
        if (Objects.nonNull(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    isBlank = false;
                    break;
                }
            }
        }
        return isBlank;
    }

    /**
     * Checks if a String is not null, not empty ("") and not whitespace only.
     *
     * @param str the String to check, may be null
     * @return true if the String is not null, not empty and not whitespace only
     */
    public static boolean isNotBlank(final String str) {
        return !isBlank(str);
    }

    /**
     * Removes all leading and trailing space from the string argument.
     *
     * @param value the String to be trimmed, may be null
     * @return the trimmed String or null if the argument is null.
     */
    public static String trim(final String value) {

        String text = value;
        if (Objects.nonNull(text)) {
            text = text.trim();
        }
        return text;
    }

    private StringUtils() {
        // utility class
    }
}
