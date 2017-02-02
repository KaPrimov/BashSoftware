package com.KaPrim;

import java.util.Scanner;

public class InputReader {

    private static final String END_COMMAND = "quit";

    public static void readCommands() {
        OutputWriter.writeMessage(String.format("%s > ", SessionData.currentPath));
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        while (!input.equals(END_COMMAND)) {
            CommandInterpreter.interpretCommand(input);
            OutputWriter.writeMessage(String.format("%s > ", SessionData.currentPath));

            input = scanner.nextLine().trim();
        }
    }
}
