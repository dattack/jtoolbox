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
package com.dattack.jtoolbox.security;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * @author cvarela
 * @since 0.2
 */
public final class RsaUtils {

    private static final String ALGORITHM = "RSA";

    /**
     * Create a RSA {@link KeyPair} with the given size.
     *
     * @param keySize
     *            the key size
     * @return the generated key pair
     * @throws DattackSecurityException
     *             if an error occurs
     */
    public static KeyPair createKeys(final int keySize) throws DattackSecurityException {

        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(keySize);
            return keyGen.generateKeyPair();
        } catch (final NoSuchAlgorithmException e) {
            throw new DattackSecurityException(e);
        }
    }

    /**
     * Decrypt all bytes from the specified byte array using the given private key.
     *
     * @param input
     *            the encrypted data
     * @param key
     *            the private key
     * @return the plain data
     * @throws DattackSecurityException
     *             if an error occurs.
     */
    public static byte[] decrypt(final byte[] input, final PrivateKey key) throws DattackSecurityException {
        return execEncryptionOperation(input, Cipher.DECRYPT_MODE, key);
    }

    /**
     * Decrypt all bytes from the specified byte array using the given private key. The input byte array is encoded
     * using Base64 encoding schema.
     *
     * @param input
     *            the encrypted data encoded using Base64
     * @param key
     *            the private key
     * @return the plain data
     * @throws DattackSecurityException
     *             if an error occurs.
     */
    public static byte[] decryptBase64(final byte[] input, final PrivateKey key) throws DattackSecurityException {
        return execEncryptionOperation(Base64.getDecoder().decode(input), Cipher.DECRYPT_MODE, key);
    }

    /**
     * Encrypts the given data using a public key.
     *
     * @param input
     *            the data to encrypt
     * @param key
     *            the public key
     * @return the encrypted data
     * @throws DattackSecurityException
     *             if an error occurs.
     */
    public static byte[] encrypt(final byte[] input, final PublicKey key) throws DattackSecurityException {
        return execEncryptionOperation(input, Cipher.ENCRYPT_MODE, key);
    }

    /**
     * Encrypts the given data using a public key and encodes the result using the Base64 encoding scheme.
     *
     * @param input
     *            the data to encrypt
     * @param key
     *            the public key
     * @return the encrypted data encoded using Base64
     * @throws DattackSecurityException
     *             if an error occurs.
     */
    public static byte[] encryptBase64(final byte[] input, final PublicKey key) throws DattackSecurityException {
        return Base64.getEncoder().encode(execEncryptionOperation(input, Cipher.ENCRYPT_MODE, key));
    }

    private static byte[] execEncryptionOperation(final byte[] input, final int encryptMode, final Key key)
            throws DattackSecurityException {

        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(encryptMode, key);
            return cipher.doFinal(input);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
            throw new DattackSecurityException(e);
        }
    }

    /**
     * Read a {@link PrivateKey} from filesystem.
     *
     * @param path
     *            the file to read
     * @return the private key
     * @throws DattackSecurityException
     *             if an error occurs
     */
    public static PrivateKey loadPrivateKey(final String path) throws DattackSecurityException {

        try {
            final byte[] keyBytes = Files.readAllBytes(new File(path).toPath());
            final PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            final KeyFactory kf = KeyFactory.getInstance(ALGORITHM);
            return kf.generatePrivate(spec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new DattackSecurityException(e);
        }
    }

    /**
     * Read a {@link PublicKey} from filesystem.
     *
     * @param filename
     *            the file to read
     * @return the public key
     * @throws DattackSecurityException
     *             if an error occurs
     */
    public static PublicKey loadPublicKey(final String filename) throws DattackSecurityException {

        try {
            final byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
            final X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            final KeyFactory kf = KeyFactory.getInstance(ALGORITHM);
            return kf.generatePublic(spec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new DattackSecurityException(e);
        }
    }

    private RsaUtils() {
        // static class
    }
}
