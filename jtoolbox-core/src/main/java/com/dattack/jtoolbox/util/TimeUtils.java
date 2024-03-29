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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * A suite of utilities surrounding the use of the {@link java.util.Calendar} and {@link java.util.Date} object.
 *
 * @author cvarela
 * @since 0.1
 */
public final class TimeUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeUtils.class);

    private static final long MILLIS_PER_SECOND = 1000;
    private static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
    private static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
    private static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;
    private static final long MILLIS_PER_WEEK = 7 * MILLIS_PER_DAY;

    private static final List<String> ISO_8601_PATTERN_LIST = computeIso8601List();

    private static List<String> computeIso8601List() {

        final String[] patterns = {
            "yyyy-MM-dd'T'HH:mm", //
            "yyyy-MM-dd'T'HH:mm:ss", //
            "yyyy-MM-dd'T'HH:mm:ss.S", //
            "yyyy-MM-dd HH:mm", //
            "yyyy-MM-dd HH:mm:ss", //
            "yyyy-MM-dd HH:mm:ss.S" //
        };

        final List<String> patternList = Arrays.asList(patterns);
        patternList.sort(Comparator.comparingInt(String::length));
        return patternList;
    }

    /**
     * <p>
     * Parses a string representing a date by trying a variety of different parsers based on ISO 8601 standard (Data
     * elements and interchange formats -- Information interchange -- Representation of dates and times).
     * </p>
     * <p>
     * The parse will try a few parse pattern in turn. A parse is only deemed successful if it parses the whole of the
     * input string.
     * </p>
     * <b>This method is null-safe.</b>
     *
     * @param txt the date to parse
     * @return the parsed date or null if no parse patterns match
     */
    public static Date parseDate(final String txt) {
        return StringUtils.isBlank(txt) ? null : parseNotNullDate(txt);
    }

    private static Date parseNotNullDate(final String txt) {

        final SimpleDateFormat parser = new SimpleDateFormat();

        Date result = null;
        for (final String pattern : ISO_8601_PATTERN_LIST) {
            if (txt.length() <= pattern.length()) {
                try {
                    parser.applyPattern(pattern);
                    result = parser.parse(txt);
                    break;
                } catch (final ParseException e) {
                    LOGGER.debug(e.getMessage());
                }
            }
        }
        return result;
    }

    /**
     * Computes the number of milliseconds represented by the specified span expression.
     *
     * @param text the span expression
     * @return the number of milliseconds
     */
    @SuppressWarnings("PMD.CyclomaticComplexity")
    private static Long parseTimeSpanExpression(final String text) {

        long timeInMillis = 0;
        long value = 0;
        for (int i = 0; i < text.length(); i++) {
            final char charAt = text.charAt(i);
            if (Character.isDigit(charAt)) {
                value = value * 10 + Character.digit(charAt, 10);
            } else {
                switch (charAt) {
                    case 'w':
                    case 'W':
                        timeInMillis += value * MILLIS_PER_WEEK;
                        break;
                    case 'd':
                    case 'D':
                        timeInMillis += value * MILLIS_PER_DAY;
                        break;
                    case 'h':
                    case 'H':
                        timeInMillis += value * MILLIS_PER_HOUR;
                        break;
                    case 'm':
                    case 'M':
                        timeInMillis += value * MILLIS_PER_MINUTE;
                        break;
                    case 's':
                    case 'S':
                        timeInMillis += value * MILLIS_PER_SECOND;
                        break;
                    default:
                        throw new IllegalArgumentException(String.format("Unknown time unit: '%s'", charAt));
                        // ignore value
                }
                value = 0;
            }
        }
        timeInMillis += value;
        return timeInMillis;
    }

    /**
     * <p>
     * Parse a time span expression and returns the milliseconds represented by this one. A valid time span expression
     * has the following format: <code>(&lt;digit+&gt;&lt;letter&gt;)+</code>. The following pattern letters are
     * defined:
     * </p>
     *
     * <pre>
     * | Letter | Time unit |
     * |--------|-----------|
     * | w or W | Week      |
     * | d or D | Day       |
     * | h or H | Hour      |
     * | m or M | Minute    |
     * | s or S | Second    |
     * </pre>
     *
     *
     * <p>
     * Example: <code>2h30m = 2 hours + 30 minutes = 2 * 60 * 60 * 1000 + 30 * 60 * 1000 = 9.000.000 milliseconds</code>
     * </p>
     *
     * @param text the span expression
     * @return the number of milliseconds
     */
    public static Long parseTimeSpanMillis(final String text) {

        Long result = null;
        if (text != null) {
            try {
                result = Long.valueOf(text);
            } catch (final NumberFormatException e) {
                result = parseTimeSpanExpression(text);
            }
        }
        return result;
    }

    private TimeUtils() {
        // utility class
    }
}
