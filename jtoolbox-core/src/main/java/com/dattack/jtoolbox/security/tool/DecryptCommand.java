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

import java.security.PrivateKey;

import com.dattack.jtoolbox.io.console.AnsiConsole;
import com.dattack.jtoolbox.security.DattackSecurityException;
import com.dattack.jtoolbox.security.RsaUtils;

/**
 * @author cvarela
 * @since 0.2
 */
final class DecryptCommand extends AbstractCommand {

    private final AnsiConsole ansiConsole;

    public DecryptCommand(final AnsiConsole ansiConsole) {
        this.ansiConsole = ansiConsole;
    }

    @Override
    public void execute() {

        try {
            final String privateKeyPath = getPrivateKeyPath(DEFAULT_PRIVATE_KEY_FILENAME);

            ansiConsole.info("[INFO] Loading private key: %s", privateKeyPath);
            final PrivateKey key = RsaUtils.loadPrivateKey(privateKeyPath);

            final String encryptedData = ansiConsole.stringReader().setPrompt("> Encrypted data: ").read().trim();

            ansiConsole.info("[INFO] Running decrypt process ...");
            final byte[] plainMessage = RsaUtils.decryptBase64(encryptedData.getBytes(), key);

            ansiConsole.info("[INFO] Secret message: %s", new String(plainMessage));

        } catch (final DattackSecurityException | RuntimeException e) {
            ansiConsole.error(e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Decrypt an encripted text";
    }

    @Override
    public String getName() {
        return "decrypt";
    }
}
