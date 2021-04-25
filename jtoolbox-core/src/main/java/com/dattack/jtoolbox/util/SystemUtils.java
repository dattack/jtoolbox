/*
 * Copyright (c) 2021, The Dattack team (http://www.dattack.com)
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
package com.dattack.jtoolbox.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * Utility class to access some system properties.
 *
 * @author cvarela
 * @since 0.4
 */
public final class SystemUtils {

    private SystemUtils() {
        // static class
    }

    public static String getFileSeparator() {
        return System.getProperty("file.separator");
    }

    /**
     * If defined, it will return the value of the COMPUTERNAME property, otherwise it will use the HOSTNAME property.
     * If neither is defined, it will return the host name for this IP address or the textual representation of the IP
     * address.
     *
     * @return the local hostname
     */
    public static String getHostname() {

        String hostname = System.getenv("COMPUTERNAME");
        if (Objects.isNull(hostname)) {
            hostname = System.getenv("HOSTNAME");
            if (Objects.isNull(hostname)) {
                try {
                    hostname =  InetAddress.getLocalHost().getHostName();
                } catch (UnknownHostException e) { // NOPMD
                    // ignore
                }
            }
        }
        return hostname;
    }

    public static String getJavaClasspath() {
        return System.getProperty("java.class.path");
    }

    public static String getJavaHome() {
        return System.getProperty("java.home");
    }

    public static String getJavaVendor() {
        return System.getProperty("java.vendor");
    }

    public static String getJavaVendorUrl() {
        return System.getProperty("java.vendor.url");
    }

    public static String getJavaVersion() {
        return System.getProperty("java.version");
    }

    public static String getLineSeparator() {
        return System.getProperty("line.separator");
    }

    public static String getOsArch() {
        return System.getProperty("os.arch");
    }

    public static String getOsName() {
        return System.getProperty("os.name");
    }

    public static String getOsVersion() {
        return System.getProperty("os.version");
    }

    public static String getPathSeparator() {
        return System.getProperty("path.separator");
    }

    public static String getUserDir() {
        return System.getProperty("user.dir");
    }

    public static String getUserHome() {
        return System.getProperty("user.home");
    }

    public static String getUsername() {
        return System.getProperty("user.name");
    }
}
