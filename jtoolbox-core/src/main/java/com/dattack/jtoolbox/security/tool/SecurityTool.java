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
import com.dattack.jtoolbox.io.console.Console;
import com.dattack.jtoolbox.io.console.SimpleConsole;

import java.util.Locale;
import java.util.Objects;

/**
 * A security tool that allows the generation of security keys and the execution of encryption and decryption of text
 * strings. All the executed operations are delegated in commands that can be extended to increase the tool's
 * functionalities.
 *
 * @author cvarela
 * @since 0.2
 */
@SuppressWarnings({"PMD.SystemPrintln", "PMD.ClassNamingConventions"})
public final class SecurityTool {

    private static Console console = new SimpleConsole();

    private static boolean exit;

    private SecurityTool() {
        // Main class
    }

    /* default */ static void switchConsole() {

        if (console instanceof AnsiConsole) {
            console = new SimpleConsole();
        } else {
            console = new AnsiConsole();
        }
    }

    /* default */ static void stop() {
        SecurityTool.exit = true;
    }

    /**
     * Main method.
     *
     * @param args execution arguments
     */
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public static void main(final String[] args) {

        final AnsiStyle boldStyle = new AnsiStyle().add(EscapeCode.INTENSITY_BOLD_ON);
        console.println(boldStyle, "Welcome to the Dattack Security Tool");
        System.out.println();

        final AnsiStyle greenStyle = new AnsiStyle().foreground(Color.GREEN);

        while (!exit) {

            try {
                final String commandName = console.stringReader() //
                    .withPrompt("[SecurityTool]$ ") //
                    .withStyle(greenStyle) //
                    .read();

                if (Objects.isNull(commandName)) {
                    continue;
                }

                final AbstractCommand command = lookupCommand(commandName);
                if (Objects.isNull(command)) {
                    console.error("%s: command not found", commandName);
                } else {
                    command.execute();
                }
            } catch (final RuntimeException e) {
                console.error(e.getMessage());
            }
        }
    }

    private static AbstractCommand lookupCommand(final String commandName) {

        AbstractCommand result = null;
        for (final AbstractCommand command : COMMANDS) {
            if (command.getName().equalsIgnoreCase(commandName)) {
                result = command;
                break;
            }
        }
        return result;
    }

    /* package */ static final AbstractCommand[] COMMANDS = {
        // generate keys command
        new GenerateKeyCommand(console),

        // encrypt command
        new EncryptCommand(console),

        // decrypt command
        new DecryptCommand(console),

        // help command
        new AbstractCommand(console) {

            @Override
            protected void execute() {
                System.out.println("\n  Available commands:\n");
                for (AbstractCommand command : COMMANDS) {
                    System.out.format("    - %-12s: %s%n", command.getName().toLowerCase(Locale.getDefault()),
                                      command.getDescription());
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
        },
        // exit command
        new AbstractCommand(console) {

            @Override
            protected void execute() {
                System.out.println("\n  Have a nice day!\n");
                stop();
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
        new AbstractCommand(console) {

            @Override
            protected void execute() {
                switchConsole();
            }

            @Override
            protected String getDescription() {
                return "Enable/disable ANSI console";
            }

            @Override
            protected String getName() {
                return "ansi";
            }
        } //
    };
}
