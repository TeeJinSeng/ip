package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import tasks.Task;
import tasks.TaskList;
import utils.Command;
import utils.Ui;

/**
 * Handles the {@code delete <index>} command from user and delete a task based on provided index. 
 * 
 * @param input formatted user input
 */
public class HandlerDelete implements CommandHandler {
    @Override
    public void handle(TaskList taskList, Ui ui, String firstParam, HashMap<String, String> params) throws ApunableException {

        try {
            int index = Integer.parseInt(firstParam) - 1;
    
            Task taskToRemove = taskList.get(index);
            taskList.remove(index);

            ui.echo("Noted. I've deleted this task:");
            ui.echo("  " + taskToRemove.toString());
            ui.echo(String.format("Now you have %d tasks in the list.", TaskList.tasks.size()));
        } catch (NumberFormatException e) {
            throw new ApunableException("Invalid index format");
        } catch (IndexOutOfBoundsException e) {
            throw new ApunableException(String.format("Please enter a number between 1 and %d", taskList.size()));
        }
    }
}
