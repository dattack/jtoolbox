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

import java.io.File;
import java.io.IOException;
import java.security.KeyPair;

import com.dattack.jtoolbox.io.FilesystemUtils;
import com.dattack.jtoolbox.io.console.AnsiConsole;
import com.dattack.jtoolbox.security.DattackSecurityException;
import com.dattack.jtoolbox.security.RsaUtils;

/**
 * @author cvarela
 * @since 0.2
 */
class GenerateKeyCommand extends AbstractCommand {

    private static final int RSA_MIN_SIZE = 512;
    private static final int DEFAULT_RSA_SIZE = 1024;

    private final AnsiConsole ansiConsole;

    public GenerateKeyCommand(final AnsiConsole ansiConsole) {
        this.ansiConsole = ansiConsole;
    }

    @Override
    public void execute() {

        try {
            // key size
            final int keySize = ansiConsole.intReader() //
                    .setPrompt(String.format("> Key size (default %d): ", DEFAULT_RSA_SIZE)) //
                    .setDefaultValue(DEFAULT_RSA_SIZE) //
                    .setMinValue(RSA_MIN_SIZE) //
                    .read();

            // private key path
            String privateKeyPath = getPrivateKeyPath(DEFAULT_PRIVATE_KEY_FILENAME);

            final File filepath = new File(privateKeyPath);
            if (filepath.exists() && filepath.isDirectory()) {
                privateKeyPath = new File(filepath, DEFAULT_PRIVATE_KEY_FILENAME).getAbsolutePath();
            }

            // public key path
            final String publicKeyPath = getPublicKeyPath(privateKeyPath + ".pub");

            ansiConsole.info("[INFO] Generating RSA key pair ...");
            final KeyPair keyPair = RsaUtils.createKeys(keySize);

            FilesystemUtils.writeToFile(privateKeyPath, keyPair.getPrivate().getEncoded());
            FilesystemUtils.writeToFile(publicKeyPath, keyPair.getPublic().getEncoded());

            ansiConsole.info("[INFO] Private key file: %s", privateKeyPath);
            ansiConsole.info("[INFO] Public key file: %s", publicKeyPath);

        } catch (final DattackSecurityException | IOException | RuntimeException e) {
            ansiConsole.error(e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Generate a new key pair";
    }

    @Override
    public String getName() {
        return "generatekeys";
    }
}
