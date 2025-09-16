package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import tasks.TaskList;
import utils.Ui;

public interface CommandHandler {
    public void handle(TaskList taskList, Ui ui, 
            String firstParam, HashMap<String, String> params) throws ApunableException;
}
