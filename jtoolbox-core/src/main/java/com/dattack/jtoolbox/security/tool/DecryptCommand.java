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
import com.dattack.jtoolbox.security.DattackSecurityException;
import com.dattack.jtoolbox.security.RsaUtils;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;

/**
 * A command that decrypts an encrypted message using the key provided.
 *
 * @author cvarela
 * @since 0.2
 */
final class DecryptCommand extends AbstractCommand {

    public DecryptCommand(final Console console) {
        super(console);
    }

    @Override
    public void execute() {

        try {
            final String privateKeyPath = getPrivateKeyPath(DEFAULT_PRIVATE_KEY_FILENAME);

            getConsole().info("[INFO] Loading private key: %s", privateKeyPath);
            final PrivateKey key = RsaUtils.loadPrivateKey(privateKeyPath);

            final String encryptedData = getConsole().stringReader().withPrompt("> Encrypted data: ").read().trim();

            getConsole().info("[INFO] Running decrypt process ...");
            final byte[] plainMessage = RsaUtils.decryptBase64(encryptedData.getBytes(StandardCharsets.UTF_8), key);

            getConsole().info("[INFO] Secret message: %s", new String(plainMessage, StandardCharsets.UTF_8));

        } catch (final DattackSecurityException e) {
            getConsole().error(e.getMessage());
        }
    }

    @Override
    protected String getDescription() {
        return "Decrypt an encrypted text";
    }

    @Override
    protected String getName() {
        return "decrypt";
    }
}
