package com.airtribe.learntrack.ui;

import java.io.IOException;
import java.io.InputStream;

public class MenuOptionRenderer {

    public static String RenderMenuAndGetSelectedITem(String[] options) throws Exception {
        options = new String[] { "Pizza", "Burger", "Sushi", "Salad", "Exit" };
        int selectedIndex = 0;

        // 1. Register a Shutdown Hook to clean up if the user hits Ctrl+C
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                cleanUpTerminal();
                System.out.println("\nExiting program... Terminal restored.");
            } catch (Exception e) {
                // Suppress shutdown exceptions
            }
        }));

        // 2. Initialize terminal: Raw mode and hide cursor
        setTerminalRawMode(true);
        System.out.print("\033[?25l");
        System.out.flush();

        InputStream in = System.in;

        while (true) {
            renderMenu(options, selectedIndex);

            int key = in.read();

            // Handle Windows Ctrl+C input (sent as byte value 3 when TreatControlCAsInput
            // is true)
            if (key == 3) {
                System.exit(0);
            }

            // Check for ANSI escape sequences (Arrow keys)
            if (key == 27) { // Escape
                int nextKey = in.read();
                if (nextKey == 91) { // '['
                    int arrowKey = in.read();
                    if (arrowKey == 65) { // Up
                        selectedIndex = (selectedIndex - 1 + options.length) % options.length;
                    } else if (arrowKey == 66) { // Down
                        selectedIndex = (selectedIndex + 1) % options.length;
                    }
                }
            } else if (key == 10 || key == 13) { // Enter Key
                break;
            }
        }

        return options[selectedIndex];
    }

    private static void renderMenu(String[] options, int selectedIndex) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("=== Use Up/Down arrows and press ENTER to select ===");
        System.out.println("=== Press Ctrl+C to abort ===");
        for (int i = 0; i < options.length; i++) {
            if (i == selectedIndex) {
                System.out.println("\033[7m > " + options[i] + " \033[0m");
            } else {
                System.out.println("   " + options[i]);
            }
        }
    }

    private static void cleanUpTerminal() throws IOException, InterruptedException {
        // Show cursor and reset colors
        System.out.print("\033[?25h\033[0m");
        System.out.flush();
        setTerminalRawMode(false);
    }

    private static void setTerminalRawMode(boolean raw) throws IOException, InterruptedException {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            String mode = raw ? "$e = [System.Console]; $e::TreatControlCAsInput=$true"
                    : "$e = [System.Console]; $e::TreatControlCAsInput=$false";
            new ProcessBuilder("powershell", "-Command", mode).inheritIO().start().waitFor();
        } else {
            String[] cmd = raw ? new String[] { "/bin/sh", "-c", "stty raw -echo < /dev/tty" }
                    : new String[] { "/bin/sh", "-c", "stty cooked echo < /dev/tty" };
            Runtime.getRuntime().exec(cmd).waitFor();
        }
    }

}
