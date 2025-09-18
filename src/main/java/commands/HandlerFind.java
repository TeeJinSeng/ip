package commands;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.ApunableException;
import tasks.Task;
import tasks.TaskList;
import utils.Ui;

/**
 * Handles the find command from user and list out all the tasks which descriptions contains the provided keyword. 
 * 
 * @param input formatted user input. 
 */
public class HandlerFind implements CommandHandler {
    @Override
    public void handle(TaskList taskList, Ui ui, String firstParam, HashMap<String, String> params) throws ApunableException {
        if (firstParam.isEmpty()) {
            throw new ApunableException("Find keyword cannot be empty");
        }

        ArrayList<Task> matchTasks = new ArrayList<>();

        String lowerFirstParam = firstParam.toLowerCase();

        for(int i = 0; i < taskList.size(); i++) {
            String lowerDescription = taskList.get(i).getDescription().toLowerCase();

            if (lowerDescription.contains(lowerFirstParam)) {
                matchTasks.add(taskList.get(i));
            }
        }

        if (matchTasks.isEmpty()) {
            ui.echo("(no matching task)");
        } else {
            ui.echo("Here are the matching tasks in your list:");

            for(int i = 0; i < matchTasks.size(); i ++) {
                ui.echo(String.format("%d.%s", i + 1, matchTasks.get(i)));
            }
        }
    }
}