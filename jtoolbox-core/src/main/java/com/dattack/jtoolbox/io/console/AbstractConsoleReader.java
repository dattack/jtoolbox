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
 * An abstract reader to read data from the console.
 *
 * @author cvarela
 * @since 0.1
 */
public abstract class AbstractConsoleReader<T> {

    private final Console console;

    /* default */ AbstractConsoleReader(final Console console) {
        this.console = console;
    }

    /**
     * Returns the ansi console.
     *
     * @return the ansiConsole
     */
    protected Console getConsole() {
        return console;
    }

    /**
     * Prints a String. If the argument is null, then prints none.
     *
     * @param style the ANSI style to use
     * @param text  the <code>String</code> to be printed
     */
    protected void print(final AnsiStyle style, final String text) {
        if (text != null) {
            console.printAndReset(style, text);
        }
    }

    /**
     * Prints a String and then terminate the line. This method behaves as though it invokes print(String) and then
     * println().
     *
     * @param style the ANSI style to use
     * @param text  the <code>String</code> to be printed
     */
    protected void println(final AnsiStyle style, final String text) {
        if (text != null) {
            console.printlnAndReset(style, text);
        }
    }

    protected abstract T read();
}
