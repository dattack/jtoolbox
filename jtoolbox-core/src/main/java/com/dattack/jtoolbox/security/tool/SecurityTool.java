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
package com.dattack.jtoolbox.security.tool;

import com.dattack.jtoolbox.io.console.AnsiConsole;
import com.dattack.jtoolbox.io.console.AnsiStyle;
import com.dattack.jtoolbox.io.console.AnsiStyle.Color;
import com.dattack.jtoolbox.io.console.AnsiStyle.EscapeCode;

/**
 * @author cvarela
 * @since 0.2
 */
public final class SecurityTool {

    private static final AnsiConsole CONSOLE = new AnsiConsole();

    private static boolean exit;

    private static final AbstractCommand[] COMMANDS = { //
        new GenerateKeyCommand(CONSOLE), //
        new EncryptCommand(CONSOLE), //
        new DecryptCommand(CONSOLE), //
        new AbstractCommand() {

            @Override
            protected void execute() {
                System.out.println("\n  Available commands:\n");
                for (final AbstractCommand command : COMMANDS) {
                    System.out.format("    - %-12s: %s%n", command.getName().toLowerCase(), command.getDescription());
                }
                System.out.println();
            }

            @Override
            protected String getDescription() {
                return "Print this help message";
            }

            @Override
            protected String getName() {
                return "Help";
            }
        }, //
        new AbstractCommand() {

            @Override
            protected void execute() {
                System.out.println("\n  Have a nice day!\n");
                exit = true;
            }

            @Override
            protected String getDescription() {
                return "Exit the application";
            }

            @Override
            protected String getName() {
                return "Exit";
            }
        }, //
    };

    static {
        exit = false;
    }

    /**
     * Main method.
     *
     * @param args
     *            execution arguments
     */
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public static void main(final String[] args) {

        final AnsiStyle boldStyle = new AnsiStyle().add(EscapeCode.INTENSITY_BOLD_ON);
        AnsiConsole.println(boldStyle, "Welcome to the Dattack Security Tool");
        System.out.println();

        final AnsiStyle greenStyle = new AnsiStyle().foreground(Color.GREEN);

        while (!exit) {

            try {

                final String commandName = CONSOLE.stringReader() //
                        .setPrompt("[SecurityTool]$ ") //
                        .setStyle(greenStyle) //
                        .read();

                if (commandName == null) {
                    continue;
                }

                boolean unknownCommand = true;
                for (final AbstractCommand command : COMMANDS) {
                    if (command.getName().equalsIgnoreCase(commandName)) {
                        unknownCommand = false;
                        command.execute();
                    }
                }

                if (unknownCommand) {
                    CONSOLE.error("%s: command not found", commandName);
                }
            } catch (final RuntimeException e) {
                CONSOLE.error(e.getMessage());
            }
        }
    }

    private SecurityTool() {
        // Main class
    }
}
