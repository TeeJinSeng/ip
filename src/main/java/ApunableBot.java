import java.util.ArrayList;
import java.util.Scanner;

public class ApunableBot {
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        
        String botName = "ApunableBot"; // A pure pineapple bot

        ArrayList<Task> tasks = new ArrayList<>();
        String input = "";
        String[] inputs;
        ArrayList<String> outputs = new ArrayList<>();

        outputs.add(String.format("Hello! I'm %s", botName));
        outputs.add("What can I do for you?");
        prettyPrint(outputs);

        while (!input.equals("bye")) {
            outputs.clear();
            System.out.print("Your query:\n");

            input = sc.nextLine();
            inputs = input.split(" ", 2);

            switch (inputs[0]) {
                case "bye" -> {
                    outputs.add("Bye. Hope to see you again soon!");
                }
                case "list" -> {
                    outputs.add("Here are the tasks in your list:");
                    for(int i = 0; i < tasks.size(); i ++) {
                        outputs.add(String.format("%d.%s", i + 1, tasks.get(i)));
                    }
                }
                case "mark" -> {
                    int index = Integer.parseInt(inputs[1]) - 1;
                    tasks.get(index).markAs(true);
                    outputs.add("Nice! I've marked this task as done:");
                    outputs.add("  " + tasks.get(index).toString());
                }
                case "unmark" -> {
                    int index = Integer.parseInt(inputs[1]) - 1;
                    tasks.get(index).markAs(false);
                    outputs.add("OK, I've marked this task as not done yet:");
                    outputs.add("  " + tasks.get(index).toString());
                }
                case "todo", "event", "deadline" -> {
                    outputs.add("Got it. I've added this task:");
                    Task task;
                    
                    switch (inputs[0]) {
                        case "todo" -> task = new Todo(inputs[1]);
                        case "event" -> {
                            inputs = inputs[1].split(" /from ");
                            String desc = inputs[0];
                            inputs = inputs[1].split(" /to ");
                            task = new Event(desc, inputs[0], inputs[1]);
                        }
                        case "deadline" -> {
                            inputs = inputs[1].split(" /by ");
                            task = new Deadline(inputs[0], inputs[1]);
                        }
                        default -> task = new Task(input);
                    }
                    tasks.add(task);

                    outputs.add("  " + task);
                    outputs.add(String.format("Now you have %d tasks in the list.", tasks.size()));
                }
                default -> {
                    outputs.add("I know it sucks to retype due to that little typo, but please retype again :)");
                }
            }

            prettyPrint(outputs);
        }
    }
}
