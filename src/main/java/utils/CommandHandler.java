package utils;

import tasks.Deadline;
import tasks.Event;
import tasks.ProgramData;
import tasks.Task;
import tasks.Todo;
import types.FormattedInput;

public class CommandHandler {
    /**
     * Handles the list command from user and list out all the added tasks. 
     * 
     * @param input formatted user input
     */
    public static void handleList(FormattedInput input) {
        OutputFormatter.outputs.add("Here are the tasks in your list:");
        for(int i = 0; i < ProgramData.tasks.size(); i ++) {
            OutputFormatter.outputs.add(String.format("%d.%s", i + 1, ProgramData.tasks.get(i)));
        }

        OutputFormatter.flushOutput();
    }

    /**
     * Checks if the index provided by user is in correct number format and within the correct range. 
     * Specifically used by {@code handleMark}, {@code handleUnmark}, {@code handleDelete} where 
     * the user need to specify index of targeted task to perform action. 
     * 
     * @param input formatted user input
     */
    private static boolean hasIndexError(FormattedInput input) {
        if (input.firstParam == null || input.firstParam.isEmpty()) {
            OutputFormatter.echo("Please provide index of the task");
            return true;
        }
        try {
            int index = Integer.parseInt(input.firstParam) - 1;
            ProgramData.tasks.get(index);
        } catch (NumberFormatException e) {
            OutputFormatter.echo("Invalid number format for task index");
            return true;
        } catch (IndexOutOfBoundsException e) {
            OutputFormatter.echo(String.format("Please enter a number between 1 and %d", 
                ProgramData.tasks.size()));
            return true;
        }

        return false;
    }

    /**
     * Checks if the given {@code strToCheck} is null or empty, if yes, let the Chatbot to reply 
     * message indicating certain parameter of task need to be provided. 
     * Specifically used by command handlers that create new task such as {@code handleTodo}, {@code handleEvent}, {@code handleDeadline}
     * 
     * @param strToCheck parameter of the task provided by user
     * @param name name of the parameter
     * @param taskName name of the task
     * @return {@code true} and output message if yes, or else return {@code false} 
     */
    private static boolean isNullOrEmpty(String strToCheck, String name, String taskName) {
        if (strToCheck == null || strToCheck.isEmpty()) {
            OutputFormatter.echo(String.format("%s for %s cannot be empty", name, taskName));
            return true;
        }

        return false;
    }

    /**
     * Handles the mark command from user. 
     * 
     * @param input formatted user input
     */
    public static void handleMark(FormattedInput input) {
        if (hasIndexError(input)) {
            return;
        }

        int index = Integer.parseInt(input.firstParam) - 1;

        ProgramData.tasks.get(index).markAs(true);
        OutputFormatter.outputs.add("Nice! I've marked this task as done:");
        OutputFormatter.outputs.add("  " + ProgramData.tasks.get(index).toString());
        OutputFormatter.flushOutput();
    }

    /**
     * Handles the unmark command from user. 
     * 
     * @param input formatted user input
     */
    public static void handleUnmark(FormattedInput input) {
        if (hasIndexError(input)) {
            return;
        }

        int index = Integer.parseInt(input.firstParam) - 1;

        ProgramData.tasks.get(index).markAs(false);
        OutputFormatter.outputs.add("OK, I've marked this task as not done yet:");
        OutputFormatter.outputs.add("  " + ProgramData.tasks.get(index).toString());
        OutputFormatter.flushOutput();
    }

    /**
     * Lets the chatbot to output add task message after a new task(e.g. {@code Todo}, {@code Event}, 
     * {@code Deadline}) is added. 
     * 
     * @param task the newly added task which the chatbot need to acknowledge its creation. 
     */
    public static void printAddTaskMsg(Task task) {
        OutputFormatter.outputs.add("Noted. I've added this task:");
        OutputFormatter.outputs.add("  " + task.toString());
        OutputFormatter.outputs.add(String.format("Now you have %d tasks in the list.", ProgramData.tasks.size()));
        OutputFormatter.flushOutput();
    }

    /**
     * Handles the todo command from user and add a new {@code Todo} task. 
     * 
     * @param input formatted user input
     */
    public static void handleTodo(FormattedInput input) {
        if (isNullOrEmpty(input.firstParam, "Description", "todo")) {
            return;
        }
        
        Task task = new Todo(input.firstParam.trim());
        ProgramData.tasks.add(task);

        printAddTaskMsg(task);
    }

    /**
     * Handles the event command from user and add a new {@code Event} task. 
     * 
     * @param input formatted user input
     */
    public static void handleEvent(FormattedInput input) {
        String desc = input.firstParam;
        String from = input.params.get("from");
        String to = input.params.get("to");
        
        if (isNullOrEmpty(desc, "Description", "event")
            || isNullOrEmpty(from, "From", "event")
            || isNullOrEmpty(to, "to", "event")) {
            return;
        }

        Task task = new Event(desc, from, to);
        ProgramData.tasks.add(task);

        printAddTaskMsg(task);
    }

    /**
     * Handles the deadline command from user and add a new {@code Deadline} task. 
     * 
     * @param input formatted user input
     */
    public static void handleDeadline(FormattedInput input) {
        Task task = null;

        if (!input.params.containsKey("by")) {
            OutputFormatter.outputs.add("/by is missing");
        }

        String desc = input.firstParam;
        String by = input.params.get("by");

        if (desc.isEmpty()) {
            OutputFormatter.outputs.add("Description for deadline cannot be empty");
        } else if (by.isEmpty()) {
            OutputFormatter.outputs.add("by for deadline cannot be empty");
        }

        task = new Deadline(desc, by);

        ProgramData.tasks.add(task);

        printAddTaskMsg(task);
    }

    /**
     * Handles the {@code delete <index>} command from user and delete a task based on provided index. 
     * 
     * @param input formatted user input
     */
    public static void handleDelete(FormattedInput input) {
        if (hasIndexError(input)) {
            return;
        }

        int index = Integer.parseInt(input.firstParam) - 1;

        Task taskToRemove = ProgramData.tasks.get(index);
        ProgramData.tasks.remove(index);

        OutputFormatter.outputs.add("Noted. I've deleted this task:");
        OutputFormatter.outputs.add("  " + taskToRemove.toString());
        OutputFormatter.outputs.add(String.format("Now you have %d tasks in the list.", ProgramData.tasks.size()));
        OutputFormatter.flushOutput();
    }
}
