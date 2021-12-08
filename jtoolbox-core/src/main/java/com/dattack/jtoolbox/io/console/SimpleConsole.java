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

import java.io.InputStream;
import java.io.PrintStream;

/**
 * A {@link Console} implementation that avoids all ANSI codes.
 * This class is not synchronized.
 *
 * @author cvarela
 * @since 0.2
 */
public class SimpleConsole implements Console {

    private final transient InputStream inputStream;
    private final transient PrintStream printStream;

    public SimpleConsole() {
        this(System.in, System.out);
    }

    public SimpleConsole(final InputStream inputStream, final PrintStream printStream) {
        this.inputStream = inputStream;
        this.printStream = printStream;
    }

    @Override
    public void print(final AnsiStyle style, final String text, final Object... args) {
        printStream.format(text, args);
    }

    @Override
    public void printAndReset(final AnsiStyle style, final String text, final Object... args) {
        print(style, text, args);
    }

    @Override
    public void println(final AnsiStyle style, final String text, final Object... args) {
        print(style, text + "%n", args);
    }

    @Override
    public void printlnAndReset(final AnsiStyle style, final String text, final Object... args) {
        println(style, text, args);
    }

    @Override
    public void error(final String text, final Object... args) {
        printStream.format(text + "%n", args);
    }

    @Override
    public void info(final String text, final Object... args) {
        println(null, text, args);
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
