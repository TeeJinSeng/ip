package utils;

import java.time.LocalDateTime;
import java.util.List;

import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Task;
import tasks.Todo;
import utils.Command;

public class DepCommandHandler {

    /**
     * Checks if the index provided by user is in correct number format and within the correct range. 
     * Specifically used by {@code handleMark}, {@code handleUnmark}, {@code handleDelete} where 
     * the user need to specify index of targeted task to perform action. 
     * 
     * @param input formatted user input
     */
    private static boolean hasIndexError(Command input) {
        if (input.firstParam == null || input.firstParam.isEmpty()) {
            Ui.echo("Please provide index of the task");
            return true;
        }
        try {
            int index = Integer.parseInt(input.firstParam) - 1;
            TaskList.tasks.get(index);
        } catch (NumberFormatException e) {
            Ui.echo("Invalid number format for task index");
            return true;
        } catch (IndexOutOfBoundsException e) {
            Ui.echo(String.format("Please enter a number between 1 and %d", 
                TaskList.tasks.size()));
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
            Ui.echo(String.format("%s for %s cannot be empty", name, taskName));
            return true;
        }

        return false;
    }
    
    /**
     * Lets the chatbot to output add task message after a new task(e.g. {@code Todo}, {@code Event}, 
     * {@code Deadline}) is added. 
     * 
     * @param task the newly added task which the chatbot need to acknowledge its creation. 
     */
    private static void printAddTaskMsg(Task task) {
        Ui.outputs.add("Noted. I've added this task:");
        Ui.outputs.add("  " + task.toString());
        Ui.outputs.add(String.format("Now you have %d tasks in the list.", TaskList.tasks.size()));
    }
}
