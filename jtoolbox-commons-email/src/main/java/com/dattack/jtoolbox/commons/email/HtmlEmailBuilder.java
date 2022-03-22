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
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import static com.dattack.jtoolbox.util.function.FunctionHelper.executeIfNotEmpty;
import static com.dattack.jtoolbox.util.function.FunctionHelper.executeIfNotNull;
import static com.dattack.jtoolbox.util.function.FunctionHelper.throwingConsumerWrapper;

/**
 * A fluent builder for {@code HtmlEmail} class.
 *
 * @author cvarela
 * @since 0.1
 */
@SuppressWarnings({ "PMD.TooManyMethods", "unused" })
public class HtmlEmailBuilder {

    private final transient List<InternetAddress> toList;
    private final transient List<InternetAddress> ccList;
    private final transient List<InternetAddress> bccList;
    private transient String hostname;
    private transient Integer port;
    private transient String username;
    private transient String password;
    private transient Boolean sslOnConnect;
    private transient Boolean startTlsEnabled;
    private transient Boolean startTlsRequired;
    private transient String from;
    private transient String subject;
    private transient String message;

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
     * @throws EmailException if an error occurs while creating the email
     */
    public HtmlEmail build() throws EmailException {

        requireNotBlank(hostname, "Invalid SMTP server (hostname: '%s')");
        requireNotBlank(from, "Invalid email address (FROM: '%s')");

        final HtmlEmail email = new HtmlEmail();
        executeIfNotNull(hostname, email::setHostName);
        executeIfNotNull(from, throwingConsumerWrapper(email::setFrom));
        executeIfNotNull(subject, email::setSubject);
        executeIfNotNull(port, email::setSmtpPort);
        executeIfNotNull(sslOnConnect, email::setSSLOnConnect);
        executeIfNotNull(startTlsEnabled, email::setStartTLSEnabled);
        executeIfNotNull(startTlsRequired, email::setStartTLSRequired);
        executeIfNotNull(message, throwingConsumerWrapper(email::setMsg));
        executeIfNotEmpty(toList, throwingConsumerWrapper(email::setTo));
        executeIfNotEmpty(ccList, throwingConsumerWrapper(email::setCc));
        executeIfNotEmpty(bccList, throwingConsumerWrapper(email::setBcc));

        if (StringUtils.isNotBlank(username)) {
            email.setAuthenticator(new DefaultAuthenticator(username, password));
        }

        return email;
    }

    /**
     * Adds a new BCC address.
     *
     * @param address the address in RFC822 format
     * @return itself
     * @throws AddressException if the parse of the address failed
     */
    public HtmlEmailBuilder withBccAddress(final String address) throws AddressException {
        return withBccAddress(new InternetAddress(address));
    }

    /**
     * Adds a new BCC address.
     *
     * @param address the address to be added
     * @return itself
     */
    public HtmlEmailBuilder withBccAddress(final InternetAddress address) {
        this.bccList.add(address);
        return this;
    }

    /**
     * Adds a new CC address.
     *
     * @param address the address in RFC822 format
     * @return itself
     * @throws AddressException if the parse of the address failed
     */
    public HtmlEmailBuilder withCcAddress(final String address) throws AddressException {
        return withCcAddress(new InternetAddress(address));
    }

    /**
     * Adds a new BCC address.
     *
     * @param address the address to be added
     * @return itself
     */
    public HtmlEmailBuilder withCcAddress(final InternetAddress address) {
        this.ccList.add(address);
        return this;
    }

    /**
     * Sets the FROM text.
     *
     * @param value the FROM value
     * @return itself
     */
    public HtmlEmailBuilder withFrom(final String value) {
        this.from = StringUtils.trimToNull(value);
        return this;
    }

    /**
     * Sets the hostname of the mail server.
     *
     * @param value the hostname of the mail server
     * @return itself
     */
    public HtmlEmailBuilder withHostName(final String value) {
        this.hostname = StringUtils.trimToNull(value);
        return this;
    }

    /**
     * Sets the text of the body.
     *
     * @param value the text of the body
     * @return itself
     */
    public HtmlEmailBuilder withMessage(final String value) {
        this.message = StringUtils.trimToEmpty(value);
        return this;
    }

    /**
     * Sets the password to connect to the mail server.
     *
     * @param value the plain password
     * @return itself
     */
    public HtmlEmailBuilder withPassword(final String value) {
        this.password = StringUtils.trimToNull(value);
        return this;
    }

    /**
     * Sets the port used to connect to the mail server.
     *
     * @param value the port to connect to the mail server
     * @return itself
     */
    public HtmlEmailBuilder withPort(final Integer value) {
        this.port = value;
        return this;
    }

    /**
     * Sets whether SSL/TLS encryption should be enabled for the SMTP transport upon connection (SMTPS/POPS).
     *
     * @param value the SSL/TLS encryption configuration
     * @return itself
     */
    public HtmlEmailBuilder withSslOnConnect(final Boolean value) {
        this.sslOnConnect = value;
        return this;
    }

    /**
     * Set or disable the STARTTLS encryption.
     *
     * @param value the STARTTLS configuration
     * @return itself
     */
    public HtmlEmailBuilder withStartTlsEnabled(final Boolean value) {
        this.startTlsEnabled = value;
        return this;
    }

    /**
     * Set or disable the required STARTTLS encryption.
     *
     * @param value the STARTTLS configuration
     * @return itself
     */
    public HtmlEmailBuilder withStartTlsRequired(final Boolean value) {
        this.startTlsRequired = value;
        return this;
    }

    /**
     * Sets the SUBJECT text.
     *
     * @param value the subject text
     * @return itself
     */
    public HtmlEmailBuilder withSubject(final String value) {
        this.subject = StringUtils.trimToEmpty(value);
        return this;
    }

    /**
     * Adds a new TO address.
     *
     * @param address the address in RFC822 format
     * @return itself
     * @throws AddressException if the parse of the address failed
     */
    public HtmlEmailBuilder withToAddress(final String address) throws AddressException {
        return withToAddress(new InternetAddress(address));
    }

    /**
     * Adds a new TO address.
     *
     * @param address the address to be added
     * @return itself
     */
    public HtmlEmailBuilder withToAddress(final InternetAddress address) {
        this.toList.add(address);
        return this;
    }

    /**
     * Sets the username to connect to the mail server.
     *
     * @param value the username to connect to the mail server
     * @return itself
     */
    public HtmlEmailBuilder withUsername(final String value) {
        this.username = StringUtils.trimToNull(value);
        return this;
    }

    private void requireNotBlank(final String value, final String message) throws EmailException {
        if (StringUtils.isBlank(value)) {
            throw new EmailException(String.format(message, value));
        }
    }
}
