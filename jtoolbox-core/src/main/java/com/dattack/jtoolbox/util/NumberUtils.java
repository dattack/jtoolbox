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

/**
 * Utility class to perform common operations with Number objects.
 *
 * @author cvarela
 * @since 0.4
 * @deprecated Use {@link org.apache.commons.lang.math.NumberUtils}
 */
@Deprecated
public final class NumberUtils {

    /**
     * Parses the string argument as a signed decimal integer.
     *
     * @param value        the int representation to be parsed, may be null.
     * @param defaultValue the default value
     * @return the integer value represented by the argument or the default value when the argument is null, empty
     *     ("") or whitespace only.
     * @throws NumberFormatException if the string does not contain a valid integer representation.
     */
    public static Integer parseInt(final String value, final Integer defaultValue) throws NumberFormatException {
        Integer result = defaultValue;
        if (StringUtils.isNotBlank(value)) {
            result = Integer.parseInt(value);
        }
        return result;
    }

    private NumberUtils() {
        // utility class
    }
}
