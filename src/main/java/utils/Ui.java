package utils;

import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Echos the {@code message} to the chatbot user.
     * 
     * @param message message to be printed out by chatbot to the user.
     */
    public void echo(String message) {
        System.out.println("\t" + message);
    }

    public void showLine() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }

    public void showWelcome() {
        final String BOT_NAME = "ApunableBot"; // A pure pineapple bot

        showLine();
        echo(String.format("Hello! I'm %s", BOT_NAME));
        echo("What can I do for you?");
        showLine();
    }

    public void showLoadingError() {
        System.out.println("Failed to load tasks from file");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public String readCommand() {
        String command = sc.nextLine();

        return command;
    }

    public void close() {
        sc.close();
    }
}
