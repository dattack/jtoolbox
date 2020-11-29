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
import java.util.Scanner;

/**
 * A password reader that does not display the characters entered in the console.
 *
 * @author cvarela
 * @since 0.1
 */
public class PasswordConsoleReader extends AbstractConsoleReader<byte[]> {

    private String prompt;
    private boolean mandatory;
    private AnsiStyle style;

    PasswordConsoleReader(final Console console) {
        super(console);
        this.mandatory = true;
    }

    @Override
    public byte[] read() {
        while (true) {
            final String result = readString();
            if (result == null || result.trim().isEmpty()) {
                if (mandatory) {
                    getConsole().error("Enter a valid text");
                } else {
                    return new byte[0];
                }
            } else {
                return result.getBytes();
            }
        }
    }

    private String readString() {

        final java.io.Console ioConsole = System.console();

        if (ioConsole != null) {
            return new String(ioConsole.readPassword(prompt));
        }

        // no console available (IDE)
        try (Scanner scanner = new Scanner(new UnclosableInputStream(System.in))) {
            print(style, prompt);
            return scanner.nextLine();
        }
    }

    public PasswordConsoleReader setMandatory(final boolean value) {
        this.mandatory = value;
        return this;
    }

    public PasswordConsoleReader setPrompt(final String value) {
        this.prompt = value;
        return this;
    }

    public PasswordConsoleReader setStyle(final AnsiStyle ansiStyle) {
        this.style = ansiStyle;
        return this;
    }
}
