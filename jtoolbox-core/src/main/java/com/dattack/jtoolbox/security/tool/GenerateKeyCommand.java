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

import com.dattack.jtoolbox.io.FilesystemUtils;
import com.dattack.jtoolbox.io.console.Console;
import com.dattack.jtoolbox.security.DattackSecurityException;
import com.dattack.jtoolbox.security.RsaUtils;
import java.io.File;
import java.io.IOException;
import java.security.KeyPair;

/**
 * A command to generate a new key pair (public & private).
 *
 * @author cvarela
 * @since 0.2
 */
class GenerateKeyCommand extends AbstractCommand {

    private static final int RSA_MIN_SIZE = 512;
    private static final int DEFAULT_RSA_SIZE = 1024;

    public GenerateKeyCommand(final Console console) {
        super(console);
    }

    @Override
    public void execute() {

        try {
            // key size
            final int keySize = getConsole().intReader() //
                    .withPrompt(String.format("> Key size (default %d): ", DEFAULT_RSA_SIZE)) //
                    .withDefaultValue(DEFAULT_RSA_SIZE) //
                    .withMinValue(RSA_MIN_SIZE) //
                    .read();

            // private key path
            String privateKeyPath = getPrivateKeyPath(DEFAULT_PRIVATE_KEY_FILENAME);

            final File filepath = new File(privateKeyPath);
            if (filepath.exists() && filepath.isDirectory()) {
                privateKeyPath = new File(filepath, DEFAULT_PRIVATE_KEY_FILENAME).getAbsolutePath();
            }

            // public key path
            final String publicKeyPath = getPublicKeyPath(privateKeyPath + ".pub");

            getConsole().info("[INFO] Generating RSA key pair ...");
            final KeyPair keyPair = RsaUtils.createKeys(keySize);

            FilesystemUtils.writeToFile(privateKeyPath, keyPair.getPrivate().getEncoded());
            FilesystemUtils.writeToFile(publicKeyPath, keyPair.getPublic().getEncoded());

            getConsole().info("[INFO] Private key file: %s", privateKeyPath);
            getConsole().info("[INFO] Public key file: %s", publicKeyPath);

        } catch (final DattackSecurityException | IOException e) {
            getConsole().error(e.getMessage());
        }
    }

    @Override
    protected String getDescription() {
        return "Generate a new key pair";
    }

    @Override
    protected String getName() {
        return "generateKeys";
    }
}
