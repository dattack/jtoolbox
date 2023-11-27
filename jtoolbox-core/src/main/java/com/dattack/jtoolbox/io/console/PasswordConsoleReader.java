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
import org.apache.commons.lang.StringUtils;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

/**
 * A password reader that does not display the characters entered in the console.
 *
 * @author cvarela
 * @since 0.1
 */
public class PasswordConsoleReader extends AbstractConsoleReader<byte[]> {

    private static final byte[] EMPTY = new byte[0];

    private transient String prompt;
    private transient boolean mandatory;
    private transient AnsiStyle style;

    /* default */ PasswordConsoleReader(final Console console, final InputStream inputStream) {
        super(console, inputStream);
        this.mandatory = true;
    }

    @Override
    public byte[] read() {
        while (true) {
            final String userInput = readString();
            byte[] result = null;
            if (StringUtils.isBlank(userInput)) {
                if (mandatory) {
                    getConsole().error("Enter a valid text");
                } else {
                    result = EMPTY;
                }
            } else {
                result = userInput.getBytes(StandardCharsets.UTF_8);
            }

            if (Objects.nonNull(result)) {
                return result;
            }
        }
    }

    private String readString() {

        final java.io.Console ioConsole = System.console();

        final String result;
        if (Objects.nonNull(ioConsole)) {
            result = new String(ioConsole.readPassword(prompt));
        } else {
            // no console available (IDE)
            try (Scanner scanner = new Scanner(new UnclosableInputStream(getInputStream()), //
                    StandardCharsets.UTF_8.name()))
            {
                print(style, prompt);
                result = scanner.nextLine();
            }
        }
        return result;
    }

    public PasswordConsoleReader withMandatory(final boolean value) {
        this.mandatory = value;
        return this;
    }

    public PasswordConsoleReader withPrompt(final String value) {
        this.prompt = value;
        return this;
    }

    public PasswordConsoleReader withStyle(final AnsiStyle ansiStyle) {
        this.style = ansiStyle;
        return this;
    }
}
