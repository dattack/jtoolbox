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
package com.dattack.jtoolbox.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A class loader to load classes and resources from a set of local filesystem paths.
 *
 * @author cvarela
 * @since 0.4
 */
public final class FilesystemClassLoaderUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilesystemClassLoaderUtils.class);

    private FilesystemClassLoaderUtils() {
        // utility class
    }

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
                    LOGGER.info("Scanning JAR: {}", jar);
                    urlList.add(jar.toURI().toURL());
                } catch (final MalformedURLException e) {
                    LOGGER.warn(e.getMessage());
                }
            }

            configureClasspathFromUrls(urlList);
        }
    }

    private static void configureJarClasspath(final File jar) throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        final List<URL> urlList = new ArrayList<>();
        try {
            LOGGER.info("Scanning JAR: {}", jar);
            urlList.add(jar.toURI().toURL());
            configureClasspathFromUrls(urlList);
        } catch (final MalformedURLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * Loads the classes and resources that exist in the indicated paths.
     *
     * @param paths the set of paths to be explored
     */
    public static void ensureClassLoaded(final Set<File> paths) {

        for (final File path : paths) {
            if (path.exists()) {
                try {
                    if (path.isDirectory()) {
                        configureDirectoryClasspath(path);
                    } else {
                        configureJarClasspath(path);
                    }
                } catch (final NoSuchMethodException | SecurityException | IllegalAccessException
                        | IllegalArgumentException | InvocationTargetException e) {
                    LOGGER.warn(e.getMessage());
                }
            } else {
                LOGGER.trace("Missing directory/file: ''{}''", path);
            }
        }
    }
}
