/*
 * Copyright (c) 2016, The Dattack team (http://www.dattack.com)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dattack.jtoolbox.io;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A collection of useful methods to deal with filesystem operations.
 *
 * @author cvarela
 * @since 0.1
 */
public final class FilesystemUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilesystemUtils.class);

    /**
     * Create a new {@link FilenameFilter}} that can be used to filter directory listings and retrieve only the
     * filenames that contains that extension.
     * 
     * @param extension
     *            the extension to filter. If the extension is <tt>null</tt> then the filter do nothing.
     * @return the filename filter instance
     */
    public static final FilenameFilter createFilenameFilterByExtension(final String extension) {

        final String lowerExtension = extension.toLowerCase();
        FilenameFilter filter = new FilenameFilter() {

            @Override
            public boolean accept(final File dir, final String name) {
                return name.toLowerCase().endsWith(lowerExtension);
            }
        };

        return filter;
    }

    /**
     * Gets the extension of a filename.
     *
     * @param file
     *            he file to retrieve the extension of.
     * @return the extension of the file or an empty string if none exists.
     */
    public static String getFileExtension(final File file) {

        if (file == null) {
            throw new IllegalArgumentException("Unable to get the file extension from 'null'");
        }

        final String fileName = file.getName();
        final int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot > 0) {
            return fileName.substring(lastIndexOfDot + 1);
        }
        return "";
    }

    /**
     * Finds into the classpath a file with the given name. If no one exists, then use the name as the path of the file.
     *
     * @param path
     *            the name of the file to locate
     * @return the <code>File</code>
     */
    public static File locateFile(final String path) {

        if (path == null) {
            throw new IllegalArgumentException("Unable to locate the file 'null'");
        }

        final URL url = FilesystemUtils.class.getClassLoader().getResource(path);
        if (url != null) {
            try {
                final URI uri = new URI(url.toExternalForm());
                return new File(uri);
            } catch (final URISyntaxException e) {
                // URI syntax error? we have a valid URL
                LOGGER.warn(e.getMessage());
                return new File(path);
            }
        }

        return new File(path);
    }

    /**
     * Try to find into the classpath the files with the given names.
     *
     * @see #locateFile(String)
     * @param paths
     *            the name of the files to locate
     * @return the <code>File</code>
     */
    public static Collection<File> locateFiles(final Collection<?> paths) {

        Collection<File> result = new HashSet<>(paths.size());
        for (Object item : paths) {
            if (item instanceof File) {
                result.add((File) item);
            } else {
                result.add(locateFile(Objects.toString(item)));
            }
        }
        return result;
    }

    private FilesystemUtils() {
        // static class
    }
}
