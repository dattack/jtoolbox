/*
 * Copyright (c) 2016, The Dattack team (http://www.dattack.com)
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

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import java.util.ArrayList;
import java.util.List;
import javax.mail.internet.InternetAddress;

/**
 * A fluent builder for {@code HtmlEmail} class.
 *
 * @author cvarela
 * @since 0.1
 */
public class HtmlEmailBuilder {

    private String hostname;
    private int port;
    private String username;
    private String password;
    private Boolean sslOnConnect;
    private Boolean startTlsEnabled;
    private String from;
    private String subject;
    private String message;
    private final List<InternetAddress> toList;
    private final List<InternetAddress> ccList;
    private final List<InternetAddress> bccList;

    private static String trim(final String value) {

        String text = value;
        if (text != null) {
            text = text.trim();
        }
        return text;
    }

    /**
     * Default constructor.
     */
    public HtmlEmailBuilder() {
        toList = new ArrayList<>();
        ccList = new ArrayList<>();
        bccList = new ArrayList<>();
    }

    /**
     * Builder method.
     *
     * @return the HtmlEmail
     * @throws EmailException
     *             if an error occurs while creating the email
     */
    public HtmlEmail build() throws EmailException {

        if (hostname == null || hostname.isEmpty()) {
            throw new EmailException(String.format("Invalid SMTP server (hostname: '%s')", hostname));
        }

        if (from == null || from.isEmpty()) {
            throw new EmailException(String.format("Invalid email address (FROM: '%s'", from));
        }

        final HtmlEmail email = new HtmlEmail();
        email.setHostName(hostname);
        email.setFrom(from);
        email.setSubject(subject);

        if (message != null && !message.isEmpty()) {
            email.setMsg(message);
        }

        if (port > 0) {
            email.setSmtpPort(port);
        }

        if (username != null && !username.isEmpty()) {
            email.setAuthenticator(new DefaultAuthenticator(username, password));
        }

        if (sslOnConnect != null) {
            email.setSSLOnConnect(sslOnConnect);
        }

        if (startTlsEnabled != null) {
            email.setStartTLSEnabled(startTlsEnabled);
        }

        if (!toList.isEmpty()) {
            email.setTo(toList);
        }

        if (!ccList.isEmpty()) {
            email.setCc(ccList);
        }

        if (!bccList.isEmpty()) {
            email.setBcc(bccList);
        }
        return email;
    }

    public HtmlEmailBuilder withBccAddress(final InternetAddress address) {
        this.bccList.add(address);
        return this;
    }

    public HtmlEmailBuilder withCcAddress(final InternetAddress address) {
        this.ccList.add(address);
        return this;
    }

    public HtmlEmailBuilder withFrom(final String value) {
        this.from = trim(value);
        return this;
    }

    public HtmlEmailBuilder withHostName(final String value) {
        this.hostname = trim(value);
        return this;
    }

    public HtmlEmailBuilder withMessage(final String value) {
        this.message = trim(value);
        return this;
    }

    public HtmlEmailBuilder withPassword(final String value) {
        this.password = trim(value);
        return this;
    }

    public HtmlEmailBuilder withPort(final Integer value) {
        if (value != null) {
            this.port = value;
        }
        return this;
    }

    public HtmlEmailBuilder withSslOnConnect(final Boolean value) {
        this.sslOnConnect = value;
        return this;
    }

    public HtmlEmailBuilder withStartTlsEnabled(final Boolean value) {
        this.startTlsEnabled = value;
        return this;
    }

    public HtmlEmailBuilder withSubject(final String value) {
        this.subject = trim(value);
        return this;
    }

    public HtmlEmailBuilder withToAddress(final InternetAddress address) {
        this.toList.add(address);
        return this;
    }

    public HtmlEmailBuilder withUsername(final String value) {
        this.username = trim(value);
        return this;
    }
}
