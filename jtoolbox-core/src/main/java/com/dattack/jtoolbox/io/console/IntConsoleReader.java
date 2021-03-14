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

import com.dattack.jtoolbox.io.UnclosableInputStream;
import com.dattack.jtoolbox.util.NumberUtils;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * An integer reader from Console.
 *
 * @author cvarela
 * @since 0.1
 */
public class IntConsoleReader extends AbstractConsoleReader<Integer> {

    private transient String prompt;
    private transient Integer defaultValue;
    private transient Integer minValue;
    private transient Integer maxValue;
    private transient AnsiStyle style;

    /* default */ IntConsoleReader(final Console console) {
        super(console);
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
            } else if (maxValue != null && result > maxValue) {
                getConsole().error("Value must be less than %d", maxValue);
            } else {
                return result;
            }
        }
    }

    @SuppressWarnings("PMD.EmptyCatchBlock")
    private int readInt() {
        try (Scanner scanner = new Scanner(new UnclosableInputStream(System.in), StandardCharsets.UTF_8.name())) {
            while (true) {
                print(style, prompt);

                final String userInput = scanner.nextLine();

                try {
                    return NumberUtils.parseInt(userInput, defaultValue);
                } catch (@SuppressWarnings("unused") final NumberFormatException nfe) {
                    // ignore
                }

                if (scanner.hasNextLine()) {
                    scanner.nextLine(); // empty buffer
                }
            }
        }
    }

    public IntConsoleReader withDefaultValue(final int value) {
        this.defaultValue = value;
        return this;
    }

    public IntConsoleReader withMaxValue(final int value) {
        this.maxValue = value;
        return this;
    }

    public IntConsoleReader withMinValue(final int value) {
        this.minValue = value;
        return this;
    }

    public IntConsoleReader withPrompt(final String value) {
        this.prompt = value;
        return this;
    }

    public IntConsoleReader withStyle(final AnsiStyle ansiStyle) {
        this.style = ansiStyle;
        return this;
    }
}
