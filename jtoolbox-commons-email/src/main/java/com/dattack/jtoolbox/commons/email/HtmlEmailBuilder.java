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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.mail.internet.InternetAddress;

/**
 * A fluent builder for {@code HtmlEmail} class.
 *
 * @author cvarela
 * @since 0.1
 */
@SuppressWarnings("PMD.TooManyMethods")
public class HtmlEmailBuilder {

    private transient String hostname;
    private transient int port;
    private transient String username;
    private transient String password;
    private transient Boolean sslOnConnect;
    private transient Boolean startTlsEnabled;
    private transient String from;
    private transient String subject;
    private transient String message;
    private final transient List<InternetAddress> toList;
    private final transient List<InternetAddress> ccList;
    private final transient List<InternetAddress> bccList;

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
    @SuppressWarnings({"PMD.CyclomaticComplexity", "PMD.NPathComplexity"})
    public HtmlEmail build() throws EmailException {

        if (StringUtils.isBlank(hostname)) {
            throw new EmailException(String.format("Invalid SMTP server (hostname: '%s')", hostname));
        }

        if (StringUtils.isBlank(from)) {
            throw new EmailException(String.format("Invalid email address (FROM: '%s'", from));
        }

        final HtmlEmail email = new HtmlEmail();
        email.setHostName(hostname);
        email.setFrom(from);
        email.setSubject(subject);

        if (StringUtils.isNotBlank(message)) {
            email.setMsg(message);
        }

        if (port > 0) {
            email.setSmtpPort(port);
        }

        if (StringUtils.isNotBlank(username)) {
            email.setAuthenticator(new DefaultAuthenticator(username, password));
        }

        if (Objects.nonNull(sslOnConnect)) {
            email.setSSLOnConnect(sslOnConnect);
        }

        if (Objects.nonNull(startTlsEnabled)) {
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
        this.from = StringUtils.trim(value);
        return this;
    }

    public HtmlEmailBuilder withHostName(final String value) {
        this.hostname = StringUtils.trim(value);
        return this;
    }

    public HtmlEmailBuilder withMessage(final String value) {
        this.message = StringUtils.trim(value);
        return this;
    }

    public HtmlEmailBuilder withPassword(final String value) {
        this.password = StringUtils.trim(value);
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
        this.subject = StringUtils.trim(value);
        return this;
    }

    public HtmlEmailBuilder withToAddress(final InternetAddress address) {
        this.toList.add(address);
        return this;
    }

    public HtmlEmailBuilder withUsername(final String value) {
        this.username = StringUtils.trim(value);
        return this;
    }
}
