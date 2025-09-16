package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import tasks.TaskList;
import utils.Ui;

/**
 * Handles the unmark command from user. 
 */
public class HandlerUnmark implements CommandHandler {
    @Override
    public void handle(TaskList taskList, Ui ui, 
            String firstParam, HashMap<String, String> params) throws ApunableException {
        try {
            int index = Integer.parseInt(firstParam) - 1;

            taskList.get(index).markAsUndone();
            ui.echo("OK, I've marked this task as not done yet:");
            ui.echo("  " + taskList.tasks.get(index).toString());
        } catch (NumberFormatException e) {
            throw new ApunableException("Wrong index format");
        } catch (IndexOutOfBoundsException e) {
            throw new ApunableException("Wrong index format");
        }
    }
}
