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

import java.security.PublicKey;
import java.util.Arrays;

import com.dattack.jtoolbox.io.console.AnsiConsole;
import com.dattack.jtoolbox.security.DattackSecurityException;
import com.dattack.jtoolbox.security.RsaUtils;

/**
 * @author cvarela
 * @since 0.2
 */
final class EncryptCommand extends AbstractCommand {

    private final AnsiConsole ansiConsole;

    public EncryptCommand(final AnsiConsole ansiConsole) {
        this.ansiConsole = ansiConsole;
    }

    @Override
    public void execute() {

        try {
            final String publicKeyPath = getPublicKeyPath(DEFAULT_PUBLIC_KEY_FILENAME);

            ansiConsole.info("[INFO] Loading public key: %s", publicKeyPath);
            final PublicKey key = RsaUtils.loadPublicKey(publicKeyPath);

            byte[] secret = null;
            while (true) {
                secret = ansiConsole.passwordReader().setPrompt("> Secret message: ").read();
                final byte[] retypeSecret = ansiConsole.passwordReader().setPrompt("> Retype secret message: ").read();
                if (Arrays.equals(secret, retypeSecret)) {
                    break;
                }
                ansiConsole.error("Secret messages don't match");
            }

            ansiConsole.info("[INFO] Running encrypt process ...");
            final byte[] encryptedSecret = RsaUtils.encryptBase64(secret, key);

            ansiConsole.info("[INFO] Encrypted data: %s", new String(encryptedSecret));

        } catch (final DattackSecurityException e) {
            ansiConsole.error(e.getMessage());
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
