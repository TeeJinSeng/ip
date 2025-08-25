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
                    break;
                }
                case "list" -> {
                    outputs.add("Here are the tasks in your list:");
                    for(int i = 0; i < tasks.size(); i ++) {
                        outputs.add(String.format("%d.%s", i + 1, tasks.get(i)));
                    }
                }
                case "mark" -> {
                    int index;
                    try {
                        index = Integer.parseInt(inputs[1]) - 1;
                    } catch (NumberFormatException e) {
                        outputs.add("Invalid number format");
                        prettyPrint(outputs);
                        continue;
                    }

                    if (index < 0 || index >= tasks.size()) {
                        outputs.add(String.format("Please enter a number between 1 and %d", tasks.size()));
                        prettyPrint(outputs);
                        continue;
                    }

                    tasks.get(index).markAs(true);
                    outputs.add("Nice! I've marked this task as done:");
                    outputs.add("  " + tasks.get(index).toString());
                }
                case "unmark" -> {
                    int index;
                    try {
                        index = Integer.parseInt(inputs[1]) - 1;
                    } catch (NumberFormatException e) {
                        outputs.add("Invalid number format");
                        prettyPrint(outputs);
                        continue;
                    }

                    if (index < 0 || index >= tasks.size()) {
                        outputs.add(String.format("Please enter a number between 1 and %d", tasks.size()));
                        prettyPrint(outputs);
                        continue;
                    }

                    tasks.get(index).markAs(false);
                    outputs.add("OK, I've marked this task as not done yet:");
                    outputs.add("  " + tasks.get(index).toString());
                }
                case "todo", "event", "deadline" -> {
                    Task task;

                    if (inputs.length <= 1 || inputs[1].isBlank()) {
                        outputs.add("Please provide more information for the task");
                        prettyPrint(outputs);
                        continue;
                    }
                    
                    switch (inputs[0]) {
                        case "todo" -> task = new Todo(inputs[1].trim());
                        case "event" -> {
                            int fromIndex = inputs[1].indexOf("/from");
                            int toIndex = inputs[1].indexOf("/to");

                            if (fromIndex == -1) {
                                outputs.add("/from is missing");
                                prettyPrint(outputs);
                                continue;
                            } else if (toIndex == -1) {
                                outputs.add("/to is missing");
                                prettyPrint(outputs);
                                continue;
                            }

                            String desc = inputs[1].substring(0, fromIndex).trim();
                            String from = inputs[1].substring(fromIndex + "/from".length(), toIndex).trim();
                            String to = inputs[1].substring(toIndex + "/to".length()).trim();

                            if (desc.isEmpty()) {
                                outputs.add("Description for event cannot be empty");
                                prettyPrint(outputs);
                                continue;
                            } else if (from.isEmpty()) {
                                outputs.add("From for event cannot be empty");
                                prettyPrint(outputs);
                                continue;
                            } else if (to.isEmpty()) {
                                outputs.add("To for event cannot be empty");
                                prettyPrint(outputs);
                                continue;
                            }

                            task = new Event(desc, from, to);
                        }
                        case "deadline" -> {
                            inputs = inputs[1].split("/by");
                            if (inputs.length <= 1) {
                                outputs.add("/by is missing");
                                prettyPrint(outputs);
                                continue;
                            }

                            String desc = inputs[0].trim();
                            String by = inputs[1].trim();

                            if (desc.isEmpty()) {
                                outputs.add("Description for deadline cannot be empty");
                                prettyPrint(outputs);
                                continue;
                            } else if (by.isEmpty()) {
                                outputs.add("by for deadline cannot be empty");
                                prettyPrint(outputs);
                                continue;
                            }

                            task = new Deadline(desc, by);
                        }
                        default -> task = new Task(input);
                    }
                    tasks.add(task);

                    outputs.add("Got it. I've added this task:");
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
