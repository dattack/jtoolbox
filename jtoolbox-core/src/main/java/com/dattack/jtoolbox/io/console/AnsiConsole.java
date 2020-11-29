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
package com.dattack.jtoolbox.io.console;

import com.dattack.jtoolbox.io.console.AnsiStyle.Color;
import com.dattack.jtoolbox.io.console.AnsiStyle.EscapeCode;

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
public class AnsiConsole implements Console {

    private static final AnsiStyle DEFAULT_ERROR_STYLE = new AnsiStyle().foreground(Color.RED);
    private static final AnsiStyle DEFAULT_INFO_STYLE = new AnsiStyle().foreground(Color.CYAN);
    private static final AnsiStyle RESET_STYLE = new AnsiStyle().add(EscapeCode.RESET);

    private final AnsiStyle errorStyle;
    private final AnsiStyle infoStyle;

    private static void print(final AnsiStyle style) {
        if (style != null) {
            System.out.print(style.toAnsiEscapeCodes());
        }
    }

    public void print(final AnsiStyle style, final String text, final Object... args) {
        print(style);
        System.out.format(text, args);
    }

    /**
     * Prints a formatted string to this output stream using the specified ANSI style, format string and arguments.
     *
     * @param style the ANSI style
     * @param text  a format string
     * @param args  arguments referenced by the format specifiers in the format string
     */
    public void printAndReset(final AnsiStyle style, final String text, final Object... args) {
        print(style);
        System.out.format(text, args);
        System.out.print(RESET_STYLE.toAnsiEscapeCodes());
    }

    public void println(final AnsiStyle style, final String text, final Object... args) {
        print(style, text, args);
        System.out.println();
    }

    public void printlnAndReset(final AnsiStyle style, final String text, final Object... args) {
        print(style, text, args);
        System.out.println(RESET_STYLE.toAnsiEscapeCodes());
    }

    public AnsiConsole() {
        this(DEFAULT_ERROR_STYLE, DEFAULT_INFO_STYLE);
    }

    public AnsiConsole(final AnsiStyle errorStyle, final AnsiStyle infoStyle) {
        this.errorStyle = errorStyle;
        this.infoStyle = infoStyle;
    }

    public void error(final String text, final Object... args) {
        println(errorStyle, text, args);
    }

    public void info(final String text, final Object... args) {
        println(infoStyle, text, args);
    }

    public IntConsoleReader intReader() {
        return new IntConsoleReader(this);
    }

    public PasswordConsoleReader passwordReader() {
        return new PasswordConsoleReader(this);
    }

    public StringConsoleReader stringReader() {
        return new StringConsoleReader(this);
    }
}
