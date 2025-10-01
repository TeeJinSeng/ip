package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import tasks.ContactList;
import tasks.Task;
import tasks.TaskList;
import utils.Ui;

/**
 * Handles the {@code delete <index>} command from user and delete a task based on provided index. 
 */
public class HandlerDelete implements CommandHandler {
    @Override
    public void handle(TaskList taskList, ContactList contactList, Ui ui, 
            String firstParam, HashMap<String, String> params) throws ApunableException {
        
        assert !firstParam.isEmpty() : "Please provide index of task that you want to delete";
        
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
            if (taskList.size() == 0) {
                throw new ApunableException("Your taskList is empty! Nothing to delete");
            }
            throw new ApunableException(String.format("Please enter a number between 1 and %d", taskList.size()));
        }
    }
}
