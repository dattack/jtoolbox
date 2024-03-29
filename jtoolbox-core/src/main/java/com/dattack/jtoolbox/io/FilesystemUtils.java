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
package com.dattack.jtoolbox.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;

/**
 * A collection of useful methods to deal with filesystem operations.
 *
 * @author cvarela
 * @since 0.1
 */
public final class FilesystemUtils {

    /**
     * Create a new {@link FilenameFilter}} that can be used to filter directory listings and retrieve only the
     * filenames that contains that extension.
     *
     * @param extension
     *            the extension to filter. If the extension is <code>null</code> then the filter do nothing.
     * @return the filename filter instance
     */
    public static FilenameFilter createFilenameFilterByExtension(final String extension) {

        final String lowerExtension = extension.toLowerCase(Locale.getDefault());
        return (dir, name) -> name.toLowerCase(Locale.getDefault()).endsWith(lowerExtension);
    }

    /**
     * Gets the extension of a filename.
     *
     * @param file
     *            he file to retrieve the extension of.
     * @return the extension of the file or an empty string if none exists.
     * @throws NullPointerException when file is null
     */
    public static String getFileExtension(final File file) {

        Objects.requireNonNull(file, "Unable to get the file extension from 'null'");

        final String fileName = file.getName();
        final int lastIndexOfDot = fileName.lastIndexOf('.');
        String extension = "";
        if (lastIndexOfDot > 0) {
            extension = fileName.substring(lastIndexOfDot + 1);
        }
        return extension;
    }

    /**
     * Finds into the classpath a file with the given name. If no one exists, then use the name as the path of the file.
     *
     * @param path
     *            the name of the file to locate
     * @return the <code>File</code>
     * @throws NullPointerException when path is null
     */
    public static File locateFile(final String path) {

        Objects.requireNonNull(path, "Unable to locate the file 'null'");

        final URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        File file;
        if (Objects.isNull(url)) {
            file = new File(path);
        } else {
            try {
                final URI uri = new URI(url.toExternalForm());
                file = new File(uri);
            } catch (final URISyntaxException e) {
                // URI syntax error? we have a valid URL
                file = new File(path);
            }
        }

        return file;
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

        final Collection<File> result = new HashSet<>(paths.size());
        for (final Object item : paths) {
            if (item instanceof File) {
                result.add((File) item);
            } else {
                result.add(locateFile(Objects.toString(item)));
            }
        }
        return result;
    }

    /**
     * Writes the data from the specified byte array to the named file.
     *
     * @param path
     *            The file path.
     * @param data
     *            the content to write
     * @throws IOException
     *             if an I/O error occurs.
     * @since 0.2
     */
    public static void writeToFile(final String path, final byte[] data) throws IOException {

        final File file = new File(path);
        final File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
            throw new IOException("Unable to create directory: " + parentFile);
        }

        try (OutputStream fos = Files.newOutputStream(Paths.get(path))) {
            fos.write(data);
            fos.flush();
        }
    }

    private FilesystemUtils() {
        // static class
    }
}
