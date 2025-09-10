package types;

import utils.Ui;
import tasks.TaskList;

import java.util.HashMap;

import exceptions.ApunableException;

/**
 * Handles the mark command from user. 
 * 
 * @param input formatted user input
 */
public class HandlerMark implements CommandHandler {
    @Override
    public void handle(TaskList taskList, Ui ui, String firstParam, HashMap<String, String> params) throws ApunableException {
        try {
            int index = Integer.parseInt(firstParam) - 1;
            taskList.get(index).markAsDone();

            ui.echo("Nice! I've marked this task as done:");
            ui.echo("  " + taskList.get(index).toString());
        } catch (NumberFormatException e) {
            throw new ApunableException("Wrong index format");
        } catch (IndexOutOfBoundsException e) {
            throw new ApunableException("Wrong index format");
        }
    }
}
