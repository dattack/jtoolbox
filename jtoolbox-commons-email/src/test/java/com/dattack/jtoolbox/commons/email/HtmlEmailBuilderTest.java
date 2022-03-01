/*
 * Copyright (c) 2022, The Dattack team (http://www.dattack.com)
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
package com.dattack.jtoolbox.commons.email;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.junit.jupiter.api.Test;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import static com.dattack.junit.AssertionsExt.assertAll;
import static com.dattack.junit.AssertionsExt.assertEquals;
import static com.dattack.junit.AssertionsExt.assertFalse;
import static com.dattack.junit.AssertionsExt.assertNotNull;
import static com.dattack.junit.AssertionsExt.assertTrue;

/**
 * Test case for {@link com.dattack.jtoolbox.commons.email.HtmlEmailBuilder}.
 *
 * @author carlosvs
 * @since 0.6
 */
@SuppressWarnings({ "PMD.JUnitTestContainsTooManyAsserts", "PMD.TooManyStaticImports" })
class HtmlEmailBuilderTest {

    private static final String FROM = "acme@example.com";
    private static final String HOSTNAME = "mail.example.com";
    private static final String MESSAGE = "My content";
    private static final int PORT = 123;
    private static final String SSL_ON_CONNECT = "SSL on connect";
    private static final String START_TLS_ENABLED = "Start TLS enabled";
    private static final String START_TLS_REQUIRED = "Start TLS required";
    private static final String SUBJECT = "My subject";

    @Test
        /* default */ void testFullMessage() throws EmailException, AddressException {

        HtmlEmail email = new HtmlEmailBuilder() //
            .withHostName(HOSTNAME) //
            .withFrom(FROM) //
            .withPort(PORT) //
            .withSubject(SUBJECT) //
            .withMessage(MESSAGE) //
            .withPassword("p4ssw0rd") //
            .withUsername("username") //
            .withSslOnConnect(true) //
            .withStartTlsEnabled(true) //
            .withStartTlsRequired(true) //
            .withToAddress("to1@example.com") //
            .withToAddress("to2@example.com") //
            .withCcAddress("cc1@example.com") //
            .withCcAddress("cc2@example.com") //
            .withCcAddress("cc3@example.com") //
            .withBccAddress("bcc@example.com") //
            .build();

        assertAll("Test all email properties", //
                  () -> assertEquals(HOSTNAME, email.getHostName(), "Hostname equals?"), //
                  () -> assertNotNull(email.getFromAddress(), "Not null From address"), //
                  () -> assertEquals(FROM, extractAddress(email.getFromAddress()), "From address"), //
                  () -> assertEquals(2, email.getToAddresses().size(), "Number of addresses (TO:)"), //
                  () -> assertEquals("to1@example.com", extractAddress(email.getToAddresses().get(0)), "TO:"), //
                  () -> assertEquals("to2@example.com", extractAddress(email.getToAddresses().get(1)), "TO:"), //
                  () -> assertEquals(3, email.getCcAddresses().size(), "Number of addresses (CC:)"), //
                  () -> assertEquals("cc1@example.com", extractAddress(email.getCcAddresses().get(0)), "CC:"), //
                  () -> assertEquals("cc2@example.com", extractAddress(email.getCcAddresses().get(1)), "CC:"), //
                  () -> assertEquals("cc3@example.com", extractAddress(email.getCcAddresses().get(2)), "CC:"), //
                  () -> assertEquals(1, email.getBccAddresses().size(), "Number of addresses (BCC:)"), //
                  () -> assertEquals("bcc@example.com", extractAddress(email.getBccAddresses().get(0)), "BCC:"), //
                  () -> assertTrue(email.isSSLOnConnect(), SSL_ON_CONNECT), //
                  () -> assertTrue(email.isStartTLSEnabled(), START_TLS_ENABLED), //
                  () -> assertTrue(email.isStartTLSRequired(), START_TLS_REQUIRED) //
        );
    }

    @Test
        /* default */ void testMinimalMessage() throws EmailException {

        HtmlEmail email = new HtmlEmailBuilder() //
            .withHostName(HOSTNAME) //
            .withFrom(FROM) //
            .build();

        assertAll("Test minimal email properties", //
                  () -> assertEquals(HOSTNAME, email.getHostName(), "Hostname equals?"), //
                  () -> assertNotNull(email.getFromAddress(), "Not null From address"), //
                  () -> assertEquals(FROM, email.getFromAddress().getAddress(), "From address"), //
                  () -> assertFalse(email.isSSLOnConnect(), SSL_ON_CONNECT), //
                  () -> assertFalse(email.isStartTLSEnabled(), START_TLS_ENABLED), //
                  () -> assertFalse(email.isStartTLSRequired(), START_TLS_REQUIRED));
    }

    @Test
        /* default */ void testSslOnConnect() throws EmailException {

        HtmlEmail email = new HtmlEmailBuilder() //
            .withHostName(HOSTNAME) //
            .withFrom(FROM) //
            .withSslOnConnect(true) //
            .build();

        assertAll("Check boolean properties", //
                  () -> assertTrue(email.isSSLOnConnect(), SSL_ON_CONNECT), //
                  () -> assertFalse(email.isStartTLSEnabled(), START_TLS_ENABLED), //
                  () -> assertFalse(email.isStartTLSRequired(), START_TLS_REQUIRED));
    }

    @Test
        /* default */ void testStartTlsEnabled() throws EmailException {

        HtmlEmail email = new HtmlEmailBuilder() //
            .withHostName(HOSTNAME) //
            .withFrom(FROM) //
            .withStartTlsEnabled(true) //
            .build();

        assertAll("Check boolean properties", //
                  () -> assertFalse(email.isSSLOnConnect(), SSL_ON_CONNECT), //
                  () -> assertTrue(email.isStartTLSEnabled(), START_TLS_ENABLED), //
                  () -> assertFalse(email.isStartTLSRequired(), START_TLS_REQUIRED));
    }

    @Test
        /* default */ void testStartTlsRequired() throws EmailException {

        HtmlEmail email = new HtmlEmailBuilder() //
            .withHostName(HOSTNAME) //
            .withFrom(FROM) //
            .withStartTlsRequired(true) //
            .build();

        assertAll("Check boolean properties", //
                  () -> assertFalse(email.isSSLOnConnect(), SSL_ON_CONNECT), //
                  () -> assertFalse(email.isStartTLSEnabled(), START_TLS_ENABLED), //
                  () -> assertTrue(email.isStartTLSRequired(), START_TLS_REQUIRED));
    }

    private String extractAddress(final InternetAddress address) {
        return address == null ? "" : address.getAddress();
    }
}
