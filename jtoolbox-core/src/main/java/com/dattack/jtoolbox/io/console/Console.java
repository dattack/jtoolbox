/*
 * Copyright (c) 2019, The Dattack team (http://www.dattack.com)
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
package com.dattack.jtoolbox.io.console;

/**
 * <p>
 * A basic implementation of an ANSI console printer. See
 * <a href="https://en.wikipedia.org/wiki/ANSI_escape_code">Wikipedia</a> for information.
 * </p>
 *
 * <p>
 * This class is not synchronized.
 * </p>
 *
 * @author cvarela
 * @since 0.2
 */
public interface Console {

    /**
     * Writes a formatted string to this output stream using the specified format string, arguments and ANSI style.
     *
     * @param style
     *            the ANSI style
     * @param text
     *            a format string
     * @param args
     *            arguments referenced by the format specifiers in the format string
     */
    void print(final AnsiStyle style, final String text, final Object... args);

    /**
     * Writes a formatted string to this output stream using the specified format string, arguments and ANSI style.
     *
     * @param style
     *            the ANSI style
     * @param text
     *            a format string
     * @param args
     *            arguments referenced by the format specifiers in the format string
     */
    void printAndReset(final AnsiStyle style, final String text, final Object... args);

    /**
     * Writes a formatted string to this output stream using the specified format string, arguments and ANSI style.
     * Then, terminates the current line by writing the line separator string.
     *
     * @param style
     *            the ANSI style
     * @param text
     *            a format string
     * @param args
     *            arguments referenced by the format specifiers in the format string
     */
    void println(final AnsiStyle style, final String text, final Object... args);

    /**
     * Writes a formatted string to this output stream using the specified format string, arguments and ANSI style.
     * Then, terminates the current line by writing the line separator string.
     *
     * @param style
     *            the ANSI style
     * @param text
     *            a format string
     * @param args
     *            arguments referenced by the format specifiers in the format string
     */
    void printlnAndReset(final AnsiStyle style, final String text, final Object... args);

    /**
     * Writes a formatted string to this output stream using the default error ANSI style.
     *
     * @param text
     *            a format string
     * @param args
     *            arguments referenced by the format specifiers in the format string
     */
    void error(final String text, final Object... args);

    /**
     * Writes a formatted string to this output stream using the default info ANSI style.
     *
     * @param text
     *            a format string
     * @param args
     *            arguments referenced by the format specifiers in the format string
     */
    void info(final String text, final Object... args);

    /**
     * Returns an IntConsoleReader that will use this Console to read the input data.
     *
     * @return an IntConsoleReader that will use this Console to read the input data.
     */
    IntConsoleReader intReader();

    /**
     * Returns an PasswordConsoleReader that will use this Console to read the input data.
     *
     * @return an PasswordConsoleReader that will use this Console to read the input data.
     */
    PasswordConsoleReader passwordReader();

    /**
     * Returns an StringConsoleReader that will use this Console to read the input data.
     *
     * @return an StringConsoleReader that will use this Console to read the input data.
     */
    StringConsoleReader stringReader();
}
