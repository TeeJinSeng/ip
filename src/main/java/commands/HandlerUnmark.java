package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import models.ContactBook;
import models.Task;
import models.TaskList;
import utils.Ui;

/**
 * Handles the {@code unmark} command from user.
 */
public class HandlerUnmark implements CommandHandler {
    @Override
    public void handle(TaskList taskList, ContactBook contactList, Ui ui,
                       String firstParam, HashMap<String, String> params) throws ApunableException {

            assert !firstParam.isEmpty() : "Please provide index for unmark command";

            try {
            int index = Integer.parseInt(firstParam) - 1;
            Task task = taskList.get(index);

            if (task.getStatusIcon().equals(" ")) {
                ui.echo("This task has been marked as undone");
            } else {
                task.markAsUndone();

                ui.echo("OK, I've marked this task as not done yet:");
                ui.echo("  " + task.toString());
            }
        } catch (NumberFormatException e) {
            throw new ApunableException("Wrong index format");
        } catch (IndexOutOfBoundsException e) {
            if (taskList.size() == 0) {
                throw new ApunableException("Your taskList is empty! Nothing to mark as undone");
            }
            throw new ApunableException(String.format("Please enter a number between 1 and %d", taskList.size()));
        }
    }
}
