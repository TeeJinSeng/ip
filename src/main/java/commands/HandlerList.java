package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import tasks.TaskList;
import utils.Ui;

/**
 * Handles the list command from user and list out all the added tasks. 
 */
public class HandlerList implements CommandHandler {
    @Override
    public void handle(TaskList taskList, Ui ui, String firstParam, HashMap<String, String> params) throws ApunableException {
        ui.echo("Here are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i ++) {
            ui.echo(String.format("%d.%s", i + 1, taskList.get(i)));
        }
    }
}
