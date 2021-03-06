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
package com.dattack.jtoolbox.jdbc;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 * A {@link DataSource} decorator that allows the necessary drivers to be loaded dynamically from a list of local
 * paths that do not necessarily have to be part of the application's classpath.
 *
 * @author cvarela
 * @since 0.1
 */
public final class DataSourceClasspathDecorator extends AbstractDataSourceDecorator {

    private static final Logger LOGGER = Logger.getLogger(DataSourceClasspathDecorator.class.getName());

    private final Set<File> extraClasspath;
    private volatile boolean initialized;

    private static void configureClasspathFromUrls(final List<URL> urlList) throws NoSuchMethodException,
            SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        final Class<?> urlClass = URLClassLoader.class;
        final Method method = urlClass.getDeclaredMethod("addURL", URL.class);
        method.setAccessible(true);

        for (final URL u : urlList) {
            method.invoke(ClassLoader.getSystemClassLoader(), u);
        }
    }

    private static void configureDirectoryClasspath(final File directory) throws NoSuchMethodException,
            SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        final File[] jars = directory.listFiles(pathname -> pathname.isFile() && pathname.getAbsolutePath().endsWith(
                ".jar"));

        if (jars != null) {
            final List<URL> urlList = new ArrayList<>(jars.length);
            for (final File jar : jars) {
                try {
                    LOGGER.log(Level.INFO, "Scanning JAR: {0}", jar);
                    urlList.add(jar.toURI().toURL());
                } catch (final MalformedURLException e) {
                    LOGGER.log(Level.WARNING, e.getMessage());
                }
            }

            configureClasspathFromUrls(urlList);
        }
    }

    private static void configureJarClasspath(final File jar) throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        final List<URL> urlList = new ArrayList<>();
        try {
            LOGGER.log(Level.INFO, "Scanning JAR: {0}", jar);
            urlList.add(jar.toURI().toURL());
            configureClasspathFromUrls(urlList);
        } catch (final MalformedURLException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    /**
     * Default constructor.
     *
     * @param inner          the datasource to be decorated
     * @param extraClasspath a list of local paths that may contain the necessary drivers
     */
    public DataSourceClasspathDecorator(final DataSource inner, final Collection<File> extraClasspath) {
        super(inner);
        this.extraClasspath = new HashSet<>(extraClasspath);
        initialized = false;
    }

    private synchronized void configureClasspath() {

        if (initialized) {
            return;
        }

        for (final File file : extraClasspath) {
            if (file.exists()) {
                try {
                    if (file.isDirectory()) {
                        configureDirectoryClasspath(file);
                    } else {
                        configureJarClasspath(file);
                    }
                } catch (final NoSuchMethodException | SecurityException | IllegalAccessException
                        | IllegalArgumentException | InvocationTargetException e) {
                    LOGGER.log(Level.WARNING, e.getMessage());
                }
            } else {
                LOGGER.log(Level.FINER, "Missing directory/file: ''{0}''", file);
            }
        }

        initialized = true;
    }

    @Override
    public Connection getConnection() throws SQLException {

        if (!initialized) {
            configureClasspath();
        }

        return super.getConnection();
    }

    @Override
    public Connection getConnection(final String username, final String password) throws SQLException {
        if (!initialized) {
            configureClasspath();
        }
        return super.getConnection(username, password);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        if (!initialized) {
            configureClasspath();
        }
        return super.getLoginTimeout();
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        if (!initialized) {
            configureClasspath();
        }
        return super.getLogWriter();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        if (!initialized) {
            configureClasspath();
        }
        return super.getParentLogger();
    }

    @Override
    public boolean isWrapperFor(final Class<?> iface) throws SQLException {
        if (!initialized) {
            configureClasspath();
        }
        return super.isWrapperFor(iface);
    }

    @Override
    public void setLoginTimeout(final int seconds) throws SQLException {
        if (!initialized) {
            configureClasspath();
        }
        super.setLoginTimeout(seconds);

    }

    @Override
    public void setLogWriter(final PrintWriter out) throws SQLException {
        if (!initialized) {
            configureClasspath();
        }
        super.setLogWriter(out);
    }

    @Override
    public <T> T unwrap(final Class<T> iface) throws SQLException {
        if (!initialized) {
            configureClasspath();
        }
        return super.unwrap(iface);
    }
}
