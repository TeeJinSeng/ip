package utils;
import java.util.HashMap;

import commands.CommandEnum;
import exceptions.ApunableException;
import tasks.TaskList;

public class Command {
    private CommandEnum commandType;
    private String firstParam;
    private HashMap<String, String> params;
    private boolean isExitAfter;

    public Command() {
        params = new HashMap<>();
    }

    public Command(CommandEnum cmd, String fstParam, HashMap<String, String> params) {
        commandType = cmd;
        firstParam = fstParam;
        this.params = params;
    }

    public boolean isExit() {
        return isExitAfter;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws ApunableException {
        if (commandType == CommandEnum.BYE) {
            try {
                ui.echo("Bye. Hope to see you again soon!");
                isExitAfter = true;
                storage.save(tasks);
            } catch (ApunableException ex) {
                ui.echo(ex.getMessage());
            }
        } else {
            commandType.handler.handle(tasks, ui, firstParam, params);
        }
    }
}
