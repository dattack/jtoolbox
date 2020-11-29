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
import java.util.regex.Pattern;

/**
 * A String reader from console.
 *
 * @author cvarela
 * @since 0.1
 */
public class StringConsoleReader extends AbstractConsoleReader<String> {

    private String prompt;
    private String defaultValue;
    private String regex;
    private AnsiStyle style;

    StringConsoleReader(final Console console) {
        super(console);
    }

    @Override
    public String read() {
        while (true) {
            String result = readString();
            if (result == null || result.trim().isEmpty()) {
                result = defaultValue;
            }

            if (regex != null && !regex.trim().isEmpty() && !Pattern.compile(regex).matcher(result).matches()) {
                getConsole().error("The input not matches the regular expression: %s", regex);
            } else {
                return result;
            }
        }
    }

    private String readString() {
        try (Scanner scanner = new Scanner(new UnclosableInputStream(System.in))) {
            print(style, prompt);
            return scanner.nextLine();
        }
    }

    public StringConsoleReader setDefaultValue(final String value) {
        this.defaultValue = value;
        return this;
    }

    public StringConsoleReader setPrompt(final String value) {
        this.prompt = value;
        return this;
    }

    public StringConsoleReader setRegex(final String value) {
        this.regex = value;
        return this;
    }

    public StringConsoleReader setStyle(final AnsiStyle ansiStyle) {
        this.style = ansiStyle;
        return this;
    }
}
