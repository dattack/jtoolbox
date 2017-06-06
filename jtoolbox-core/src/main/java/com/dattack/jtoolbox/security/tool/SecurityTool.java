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
public class SecurityTool {

    private static final AnsiConsole ansiConsole = new AnsiConsole();

    private static final AbstractCommand[] COMMANDS = { //
        new GenerateKeyCommand(ansiConsole), //
        new EncryptCommand(ansiConsole), //
        new DecryptCommand(ansiConsole), //
        new AbstractCommand() {

            @Override
            void execute() {
                System.out.println("\n  Available commands:\n");
                for (final AbstractCommand command : COMMANDS) {
                    System.out.format("    - %-12s: %s%n", command.getName().toLowerCase(), command.getDescription());
                }
                System.out.println();
            }

            @Override
            String getDescription() {
                return "Print this help message";
            }

            @Override
            String getName() {
                return "Help";
            }
        }, //
        new AbstractCommand() {

            @Override
            void execute() {
                System.out.println("\n  Have a nice day!\n");
                System.exit(0);
            }

            @Override
            String getDescription() {
                return "Exit the application";
            }

            @Override
            String getName() {
                return "Exit";
            }
        }, //
    };

    /**
     * Main method.
     *
     * @param args
     *            execution arguments
     */
    public static void main(final String[] args) {

        final AnsiStyle boldStyle = new AnsiStyle().add(EscapeCode.INTENSITY_BOLD_ON);
        AnsiConsole.println(boldStyle, "Welcome to the Dattack Security Tool");
        System.out.println();

        final AnsiStyle greenStyle = new AnsiStyle().foreground(Color.GREEN);

        while (true) {

            try {

                final String commandName = ansiConsole.stringReader() //
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
                    ansiConsole.error("%s: command not found", commandName);
                }
            } catch (final RuntimeException e) {
                ansiConsole.error(e.getMessage());
            }
        }
    }
}
