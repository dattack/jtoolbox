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

import java.util.Scanner;

import com.dattack.jtoolbox.io.UnclosableInputStream;

/**
 * @author cvarela
 * @since 0.1
 */
public class IntConsoleReader extends AbstractConsoleReader<Integer> {

    private String prompt;
    private Integer defaultValue;
    private Integer minValue;
    private Integer maxValue;
    private AnsiStyle style;

    IntConsoleReader(final AnsiConsole ansiConsole) {
        super(ansiConsole);
    }

    /**
     * Read a valid <code>int</code>.
     *
     * @return a valid int value
     */
    @Override
    public Integer read() {

        while (true) {
            final Integer result = readInt();

            if (minValue != null && result < minValue) {
                getConsole().error("Value must be greater than %d", minValue);
                continue;
            }

            if (maxValue != null && result > maxValue) {
                getConsole().error("Value must be less than %d", minValue);
                continue;
            }

            return result;
        }
    }

    private int readInt() {

        while (true) {
            try (Scanner scanner = new Scanner(new UnclosableInputStream(System.in))) {
                print(style, prompt);

                final String userInput = scanner.nextLine();

                if (!"".equals(userInput.trim())) {
                    try {
                        return Integer.parseInt(userInput);
                    } catch (@SuppressWarnings("unused") final NumberFormatException nfe) {
                        // ignore
                    }
                } else if (defaultValue != null) {
                    return defaultValue;
                }

                if (scanner.hasNextLine()) {
                    scanner.nextLine(); // empty buffer
                }
            }
        }
    }

    public IntConsoleReader setDefaultValue(final int value) {
        this.defaultValue = value;
        return this;
    }

    public IntConsoleReader setMaxValue(final int value) {
        this.maxValue = value;
        return this;
    }

    public IntConsoleReader setMinValue(final int value) {
        this.minValue = value;
        return this;
    }

    public IntConsoleReader setPrompt(final String value) {
        this.prompt = value;
        return this;
    }

    public IntConsoleReader setStyle(final AnsiStyle ansiStyle) {
        this.style = ansiStyle;
        return this;
    }
}
