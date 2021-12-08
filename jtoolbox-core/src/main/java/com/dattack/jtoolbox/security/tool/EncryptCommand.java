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
import java.security.PublicKey;
import java.util.Arrays;

/**
 * A command that encrypts a plain message using the key provided.
 *
 * @author cvarela
 * @since 0.2
 */
final class EncryptCommand extends AbstractCommand {

    public EncryptCommand(final Console console) {
        super(console);
    }

    @Override
    public void execute() {

        try {
            final String publicKeyPath = getPublicKeyPath(DEFAULT_PUBLIC_KEY_FILENAME);

            getConsole().info("[INFO] Loading public key: %s", publicKeyPath);
            final PublicKey key = RsaUtils.loadPublicKey(publicKeyPath);

            byte[] secret = null;
            while (true) {
                secret = getConsole().passwordReader().withPrompt("> Secret message: ").read();
                final byte[] retypeSecret =
                        getConsole().passwordReader().withPrompt("> Retype secret message: ").read();
                if (Arrays.equals(secret, retypeSecret)) {
                    break;
                }
                getConsole().error("Secret messages don't match");
            }

            getConsole().info("[INFO] Running encrypt process ...");
            final byte[] encryptedSecret = RsaUtils.encryptBase64(secret, key);

            getConsole().info("[INFO] Encrypted data: %s", new String(encryptedSecret, StandardCharsets.UTF_8));

        } catch (final DattackSecurityException e) {
            getConsole().error(e.getMessage());
        }
    }

    @Override
    protected String getDescription() {
        return "Encrypt a plain text";
    }

    @Override
    protected String getName() {
        return "encrypt";
    }
}
