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

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class loader to load classes and resources from a set of local filesystem paths.
 *
 * @author cvarela
 * @since 0.4
 */
public final class FilesystemClassLoaderUtils {

    private static final Logger LOGGER = Logger.getLogger(FilesystemClassLoaderUtils.class.getName());

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
     * Loads the classes and resources that exist in the indicated paths.
     *
     * @param paths the set of paths to be explored
     */
    public static synchronized void ensureClassLoaded(Set<File> paths) {

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
                    LOGGER.log(Level.WARNING, e.getMessage());
                }
            } else {
                LOGGER.log(Level.FINER, "Missing directory/file: ''{0}''", path);
            }
        }
    }
}
