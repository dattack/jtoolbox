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
package com.dattack.jtoolbox.io.console;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class that defines the ANSI escape sequences to color the console output.
 *
 * @author cvarela
 * @since 0.2
 */
public class AnsiStyle {

    private static final int FG_CODE = 30; // Set text color (foreground)
    private static final int BG_CODE = 40; // Set background color
    private static final int FG_BRIGHT_CODE = 90; // Set foreground text color, high intensity
    private static final int BG_BRIGHT_CODE = 100; // Set background color, high intensity
    private static final char ESCAPE_CHAR = 27;
    private static final char ESCAPE_CHAR_START = '[';
    private static final char ESCAPE_CHAR_SEPARATOR = ';';
    private static final char ESCAPE_CHAR_END = 'm';

    private final transient List<Integer> list;
    private final transient boolean isAnsiCompatible;

    public enum Color {
        BLACK(0), //
        RED(1), //
        GREEN(2), //
        YELLOW(3), //
        BLUE(4), //
        MAGENTA(5), //
        CYAN(6), //
        WHITE(7), //
        DEFAULT(9);

        private final int internalValue;

        Color(final int index) {
            this.internalValue = index;
        }

        public int value() {
            return internalValue;
        }
    }

    public enum EscapeCode {
        RESET(0), // all attributes off
        // ON flags
        INTENSITY_BOLD_ON(1), // Bold or increased intensity
        INTENSITY_FAINT_ON(2), // Faint (decreased intensity)
        ITALIC_ON(3), // Italic: on
        UNDERLINE_ON(4), // Underline: Single
        BLINK_SLOW_ON(5), // Blink: Slow
        BLINK_RAPID_ON(6), // Blink: Rapid
        REVERSE_VIDEO_ON(7), // Image: Negative
        CONCEAL_ON(8), // Conceal
        CROSSED_OUT_ON(9), // Crossed-out
        // OFF flags
        UNDERLINE_DOUBLE(21), // Bold: off or Underline: Double
        INTENSITY_BOLD_OFF(22), // Normal color or intensity
        ITALIC_OFF(23), // Not italic
        UNDERLINE_OFF(24), // Underline: None
        BLINK_OFF(25), // Blink: off
        REVERSE_VIDEO_OFF(27), // Image: Positive
        CONCEAL_OFF(28), // conceal off
        CROSSED_OUT_OFF(29); // Not crossed out

        private final int internalValue;

        EscapeCode(final int value) {
            this.internalValue = value;
        }

        public int value() {
            return internalValue;
        }
    }

    /**
     * Constructor.
     */
    public AnsiStyle() {
        list = new ArrayList<>();
        // check OS
        isAnsiCompatible = !System.getProperty("os.name").contains("win");
    }

    /**
     * Adds a new escape code to this style.
     *
     * @param escapeCode
     *            the ANSI escape code to add
     * @return this object
     */
    public AnsiStyle add(final EscapeCode escapeCode) {
        if (escapeCode != null) {
            add(escapeCode.value());
        }
        return this;
    }

    private void add(final int escapeCode) {
        if (isAnsiCompatible) {
            list.add(escapeCode);
        }
    }

    public AnsiStyle background(final Color color) {
        add(BG_CODE + color.value());
        return this;
    }

    public AnsiStyle backgroundBright(final Color color) {
        add(BG_BRIGHT_CODE + color.value());
        return this;
    }

    public AnsiStyle foreground(final Color color) {
        add(FG_CODE + color.value());
        return this;
    }

    public AnsiStyle foregroundBright(final Color color) {
        add(FG_BRIGHT_CODE + color.value());
        return this;
    }

    /**
     * Returns the ANSI escape code sequence or <code>""</code> when it's not an ANSI compatible console.
     *
     * @return the ANSI escape code sequence or <code>""</code> when it's not an ANSI compatible console.
     */
    public String toAnsiEscapeCodes() {

        String ansiCode = "";
        if (!list.isEmpty()) {
            final StringBuilder builder = new StringBuilder();
            builder.append(ESCAPE_CHAR).append(ESCAPE_CHAR_START);
            for (int i = 0; i < list.size(); i++) {
                if (i > 0) {
                    builder.append(ESCAPE_CHAR_SEPARATOR);
                }
                builder.append(list.get(i));
            }
            builder.append(ESCAPE_CHAR_END);
            ansiCode = builder.toString();
        }
        return ansiCode;
    }
}
