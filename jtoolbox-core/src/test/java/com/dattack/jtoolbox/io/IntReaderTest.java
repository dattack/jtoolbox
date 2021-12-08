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
package com.dattack.jtoolbox.io;

import com.dattack.jtoolbox.io.console.AnsiStyle;
import com.dattack.jtoolbox.io.console.IntConsoleReader;
import com.dattack.jtoolbox.io.console.SimpleConsole;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import static com.dattack.junit.AssertionsExt.*;

/**
 * @author cvarela
 * @since 0.1
 */
public class IntReaderTest {

    private static final int DEFAULT_VALUE = 7;
    private static final int MIN_VALUE = 2;
    private static final int MAX_VALUE = 10;

    private IntConsoleReader createIntConsoleReader(final int inputValue)
            throws UnsupportedEncodingException {
        return createIntConsoleReader(String.valueOf(inputValue), new ByteArrayOutputStream());
    }

    private IntConsoleReader createIntConsoleReader(final String inputValue)
            throws UnsupportedEncodingException {
        return createIntConsoleReader(inputValue, new ByteArrayOutputStream());
    }

    private IntConsoleReader createIntConsoleReader(final int inputValue, final ByteArrayOutputStream out)
            throws UnsupportedEncodingException {
        return createIntConsoleReader(String.valueOf(inputValue), out);
    }
    private IntConsoleReader createIntConsoleReader(final String inputValue, final ByteArrayOutputStream out)
            throws UnsupportedEncodingException {

        final InputStream inputStream =
                new ByteArrayInputStream(inputValue.getBytes(StandardCharsets.UTF_8));

        final PrintStream printStream = new PrintStream(out, true, StandardCharsets.UTF_8.name());

        return new SimpleConsole(inputStream, printStream)
                .intReader()
                .withDefaultValue(DEFAULT_VALUE)
                .withMinValue(MIN_VALUE)
                .withMaxValue(MAX_VALUE)
                .withPrompt("Enter Integer")
                .withStyle(new AnsiStyle().background(AnsiStyle.Color.BLUE).foreground(AnsiStyle.Color.WHITE));
    }

    @Test
    public void testReadInt() throws UnsupportedEncodingException {

        final int inputValue = 5;
        final Integer integer = createIntConsoleReader(inputValue).read();
        assertEquals(inputValue, integer);
    }

    @Test
    public void testReadDefaultInt() throws UnsupportedEncodingException {

        final Integer integer = createIntConsoleReader("\n").read();
        assertEquals(DEFAULT_VALUE, integer);
    }

    @Test
    public void testReadIntOutOfRangeHigh() throws UnsupportedEncodingException {

        final int inputValue = 13;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();

        final Exception exception = assertThrows(NoSuchElementException.class, () -> {
            createIntConsoleReader(inputValue, out).read();
        });

        assertContains(exception.getMessage(), "No line found");
        assertContains(out.toString(), "Value must be less than " + MAX_VALUE);
    }

    @Test
    public void testReadIntOutOfRangeLow() throws UnsupportedEncodingException {

        final int inputValue = 1;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();

        final Exception exception = assertThrows(NoSuchElementException.class, () -> {
            createIntConsoleReader(inputValue, out).read();
        });

        assertContains(exception.getMessage(), "No line found");
        assertContains(out.toString(), "Value must be greater than " + MIN_VALUE);
    }
}
