package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import tasks.Task;
import tasks.TaskList;
import utils.Ui;

public abstract class CreateTaskHandler implements CommandHandler {
    @Override
    public void handle(TaskList taskList, Ui ui, String firstParam, HashMap<String, String> params) throws ApunableException {
        Task task = createTask(firstParam, params);
        taskList.add(task);

        ui.echo("Noted. I've added this task:");
        ui.echo("  " + task.toString());
        ui.echo(String.format("Now you have %d tasks in the list.", taskList.size()));
    }

    public abstract Task createTask(String firstParam, HashMap<String, String> params) throws ApunableException;
}
