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
package com.dattack.jtoolbox.security.tool;

import com.dattack.jtoolbox.io.console.Console;

/**
 * Abstract class that defines the methods that must implement the commands that can be executed from
 * {@link SecurityTool}.
 *
 * @author cvarela
 * @since 0.2
 */
@SuppressWarnings("PMD.LongVariable")
abstract class AbstractCommand {

    protected static final String DEFAULT_PRIVATE_KEY_FILENAME = "id_rsa";
    protected static final String DEFAULT_PUBLIC_KEY_FILENAME = "id_rsa.pub";

    private final Console console;

    protected AbstractCommand(final Console console) {
        this.console = console;
    }

    protected Console getConsole() {
        return console;
    }

    private String getKeyPath(final String defaultValue, final String prompt) {
        return getConsole().stringReader().withPrompt(prompt).withDefaultValue(defaultValue).read();
    }

    protected String getPrivateKeyPath(final String defaultValue) {
        return getKeyPath(defaultValue, String.format("> Private key filename (default: %s): ", defaultValue));
    }

    protected String getPublicKeyPath(final String defaultValue) {
        return getKeyPath(defaultValue, String.format("> Public key filename (default: %s): ", defaultValue));
    }

    protected abstract void execute();

    protected abstract String getDescription();

    protected abstract String getName();
}
