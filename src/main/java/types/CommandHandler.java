package types;

import utils.Ui;
import utils.Storage;
import tasks.TaskList;

import java.util.HashMap;

import exceptions.ApunableException;

public interface CommandHandler {
    public void handle(TaskList taskList, Ui ui, String firstParam, HashMap<String, String> params) throws ApunableException;
}
