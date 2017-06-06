/*
 * Copyright (c) 2017, The Dattack team (http://www.dattack.com)
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

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * A decorator that don't close the underlying {@link InputStream}. It's usefull to prevent <code>System.in</code> be
 * closed when you embed into an <code>java.io.Scanner</code>.
 *
 * @author cvarela
 * @since 0.2
 */
public class UnclosableInputStream extends FilterInputStream {

    public UnclosableInputStream(final InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public void close() throws IOException {
        // don't close the input stream!
    }
}
