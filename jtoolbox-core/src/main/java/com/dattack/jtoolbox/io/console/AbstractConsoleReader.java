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

/**
 * @author cvarela
 * @since 0.1
 */
public abstract class AbstractConsoleReader<T> {

    private final AnsiConsole console;

    /**
     * Prints a String. If the argument is null, then prints none.
     *
     * @param text
     *            the <code>String</code> to be printed
     */
    static void print(final AnsiStyle style, final String text) {
        if (text != null) {
            AnsiConsole.printAndReset(style, text);
        }
    }

    /**
     * Prints a String and then terminate the line. This method behaves as though it invokes print(String) and then
     * println().
     *
     * @param text
     *            the <code>String</code> to be printed
     */
    static void println(final AnsiStyle style, final String text) {
        if (text != null) {
            AnsiConsole.printlnAndReset(style, text);
        }
    }

    AbstractConsoleReader(final AnsiConsole ansiConsole) {
        this.console = ansiConsole;
    }

    /**
     * Returns the ansi console.
     *
     * @return the ansiConsole
     */
    AnsiConsole getConsole() {
        return console;
    }

    abstract T read();
}
