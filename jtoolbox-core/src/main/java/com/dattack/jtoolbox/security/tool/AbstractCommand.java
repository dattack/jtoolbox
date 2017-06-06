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

import com.dattack.jtoolbox.io.console.AnsiConsole;

/**
 * @author cvarela
 * @since 0.2
 */
abstract class AbstractCommand {

    protected static final String DEFAULT_PRIVATE_KEY_FILENAME = "dattack_rsa";
    protected static final String DEFAULT_PUBLIC_KEY_FILENAME = "dattack_rsa.pub";

    private static String getKeyPath(final String defaultValue, final String prompt) {
        return new AnsiConsole().stringReader().setPrompt(prompt).setDefaultValue(defaultValue).read();
    }

    static String getPrivateKeyPath(final String defaultValue) {
        return getKeyPath(defaultValue, String.format("> Private key filename (default: %s): ", defaultValue));
    }

    static String getPublicKeyPath(final String defaultValue) {
        return getKeyPath(defaultValue, String.format("> Public key filename (default: %s): ", defaultValue));
    }

    abstract void execute();

    abstract String getDescription();

    abstract String getName();
}
