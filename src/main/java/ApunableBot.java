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

    private static void handleMarkOrUnmark(String[] inputs, ArrayList<Task> tasks, ArrayList<String> outputs, boolean b) {
        if (inputs.length <= 1) {
            outputs.add("Please provide index of the task");
            return;
        }

        int index;
        try {
            index = Integer.parseInt(inputs[1]) - 1;
        } catch (NumberFormatException e) {
            outputs.add("Invalid number format");
            return;
        }

        if (index < 0 || index >= tasks.size()) {
            outputs.add(String.format("Please enter a number between 1 and %d", tasks.size()));
            return;
        }

        tasks.get(index).markAs(true);
        outputs.add("Nice! I've marked this task as done:");
        outputs.add("  " + tasks.get(index).toString());
    }

    private static Task handleTaskCreation(String[] inputs, ArrayList<String> outputs)  {
        Task task = null;

        if (inputs.length <= 1) {
            outputs.add("Please provide more information for the task");
            return null;
        }
        
        switch (inputs[0]) {
            case "todo" -> task = new Todo(inputs[1].trim());
            case "event" -> {
                int fromIndex = inputs[1].indexOf("/from");
                int toIndex = inputs[1].indexOf("/to");

                if (fromIndex == -1) {
                    outputs.add("/from is missing");
                    return null;
                } else if (toIndex == -1) {
                    outputs.add("/to is missing");
                    return null;
                }

                String desc = inputs[1].substring(0, fromIndex).trim();
                String from = inputs[1].substring(fromIndex + "/from".length(), toIndex).trim();
                String to = inputs[1].substring(toIndex + "/to".length()).trim();

                if (desc.isEmpty()) {
                    outputs.add("Description for event cannot be empty");
                    return null;
                } else if (from.isEmpty()) {
                    outputs.add("From for event cannot be empty");
                    return null;
                } else if (to.isEmpty()) {
                    outputs.add("To for event cannot be empty");
                    return null;
                }

                task = new Event(desc, from, to);
            }
            case "deadline" -> {
                inputs = inputs[1].split("/by");
                if (inputs.length <= 1) {
                    outputs.add("/by is missing");
                    return null;
                }

                String desc = inputs[0].trim();
                String by = inputs[1].trim();

                if (desc.isEmpty()) {
                    outputs.add("Description for deadline cannot be empty");
                    return null;
                } else if (by.isEmpty()) {
                    outputs.add("by for deadline cannot be empty");
                    return null;
                }

                task = new Deadline(desc, by);
            }
        }

        return task;
    }

    private static void handleDelete(String[] inputs, ArrayList<Task> tasks, ArrayList<String> outputs) {
        if (inputs.length <= 1 || inputs[1].isBlank()) {
            outputs.add("Please provide more information for the task");
            return;
        }

        int index;
        try {
            index = Integer.parseInt(inputs[1]) - 1;
        } catch (NumberFormatException e) {
            outputs.add("Invalid number format");
            return;
        }

        if (index < 0 || index >= tasks.size()) {
            outputs.add(String.format("Please enter a number between 1 and %d", tasks.size()));
            return;
        }

        Task taskToRemove = tasks.get(index);
        tasks.remove(index);

        outputs.add("Noted. I've removed this task:");
        outputs.add("  " + taskToRemove.toString());
        outputs.add(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String botName = "ApunableBot"; // A pure pineapple bot

        ArrayList<Task> tasks = FileSave.readFromFile();
        String input = "";
        String[] inputs;
        ArrayList<String> outputs = new ArrayList<>();

        outputs.add(String.format("Hello! I'm %s", botName));
        outputs.add("What can I do for you?");
        prettyPrint(outputs);

        while (true) {
            outputs.clear();
            System.out.print("Your query:\n");

            input = sc.nextLine();
            input = input.trim();
            inputs = input.split(" ", 2);

            if (input.equals("")) {
                outputs.add("Please type something");
                prettyPrint(outputs);
                continue;
            }

            switch (inputs[0]) {
                case "bye" -> {
                    outputs.add("Bye. Hope to see you again soon!");
                    prettyPrint(outputs);
                    FileSave.saveToFile(tasks);
                    return;
                }
                case "list" -> {
                    outputs.add("Here are the tasks in your list:");
                    for(int i = 0; i < tasks.size(); i ++) {
                        outputs.add(String.format("%d.%s", i + 1, tasks.get(i)));
                    }
                }
                case "mark" -> handleMarkOrUnmark(inputs, tasks, outputs, true);
                case "unmark" -> handleMarkOrUnmark(inputs, tasks, outputs, false);
                case "todo", "event", "deadline" -> {
                    Task task = handleTaskCreation(inputs, outputs);

                    if (task != null) {
                        tasks.add(task);

                        outputs.add("Got it. I've added this task:");
                        outputs.add("  " + task);
                        outputs.add(String.format("Now you have %d tasks in the list.", tasks.size()));
                    }
                }
                case "delete" -> handleDelete(inputs, tasks, outputs);
                default -> {
                    outputs.add("I know it sucks to retype due to that little typo, but please retype again :)");
                }
            }

            prettyPrint(outputs);
        }
    }
}
