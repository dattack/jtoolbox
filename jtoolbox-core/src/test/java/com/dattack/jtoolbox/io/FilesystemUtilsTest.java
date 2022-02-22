/*
 * Copyright (c) 2022, The Dattack team (http://www.dattack.com)
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

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test-case for {@link FilesystemUtils}.
 *
 * @author cvarela
 * @since 0.6
 */
/* default */ class FilesystemUtilsTest {

    @Test
        /* package */ void testGetFileExtension() {
        File file = new File("path/to/file.name.txt");
        assertEquals("txt", FilesystemUtils.getFileExtension(file), "Invalid file extension");
    }
}
