package utils;

import java.util.ArrayList;

public class OutputFormatter {
    public static ArrayList<String> outputs = new ArrayList<>();

    /**
     * Pretty prints the output. 
     * <pre>
     * Example: 
     * outputs = {"Hello", "How are you"}
     * output: 
     *  ______
     *  Hello
     *  How are you
     *  ______
     * </pre>
     * 
     * @param outputs list of text to output. 
     */
    public static void prettyPrint(ArrayList<String> outputs) {
        String horizontalLine = "____________________________________________________________";
        
        System.out.println("\nReply from bot: \n\t" + horizontalLine);
        
        for (String output : outputs) {
            System.out.println("\t" + output);
        }

        System.out.println("\t" + horizontalLine);
    }

    /**
     * {@code prettyPrint}s everything in the {@code outputs} to the user and clear the {@code outputs}.
     */
    public static void flushOutput() {
        prettyPrint(outputs);
        outputs.clear();
    }

    /**
     * {@code prettyPrint}s the {@code message} to the chatbot user.
     * @param message message to be printed out by chatbot to the user
     */
    public static void echo(String message) {
        outputs.clear();
        outputs.add(message);
        flushOutput();
    }
}
