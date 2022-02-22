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

import java.io.InputStream;
import java.io.PrintStream;

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

    private final transient AnsiStyle errorStyle;
    private final transient AnsiStyle infoStyle;
    private final transient InputStream inputStream;
    private final transient PrintStream printStream;

    public AnsiConsole() {
        this(System.in, System.out, DEFAULT_ERROR_STYLE, DEFAULT_INFO_STYLE);
    }

    public AnsiConsole(final InputStream inputStream, final PrintStream printStream, final AnsiStyle errorStyle,
                       final AnsiStyle infoStyle) {
        this.inputStream = inputStream;
        this.printStream = printStream;
        this.errorStyle = errorStyle;
        this.infoStyle = infoStyle;
    }

    private void print(final AnsiStyle style) {
        if (style != null) {
            printStream.print(style.toAnsiEscapeCodes());
        }
    }

    @Override
    public void print(final AnsiStyle style, final String text, final Object... args) {
        print(style);
        printStream.format(text, args);
    }

    /**
     * Prints a formatted string to this output stream using the specified ANSI style, format string and arguments.
     *
     * @param style the ANSI style
     * @param text  a format string
     * @param args  arguments referenced by the format specifiers in the format string
     */
    @Override
    public void printAndReset(final AnsiStyle style, final String text, final Object... args) {
        print(style);
        printStream.format(text, args);
        printStream.print(RESET_STYLE.toAnsiEscapeCodes());
    }

    @Override
    public void println(final AnsiStyle style, final String text, final Object... args) {
        print(style, text, args);
        printStream.println();
    }

    @Override
    public void printlnAndReset(final AnsiStyle style, final String text, final Object... args) {
        print(style, text, args);
        printStream.println(RESET_STYLE.toAnsiEscapeCodes());
    }

    @Override
    public void error(final String text, final Object... args) {
        println(errorStyle, text, args);
    }

    @Override
    public void info(final String text, final Object... args) {
        println(infoStyle, text, args);
    }

    @Override
    public IntConsoleReader intReader() {
        return new IntConsoleReader(this, inputStream);
    }

    @Override
    public PasswordConsoleReader passwordReader() {
        return new PasswordConsoleReader(this, inputStream);
    }

    @Override
    public StringConsoleReader stringReader() {
        return new StringConsoleReader(this, inputStream);
    }
}
