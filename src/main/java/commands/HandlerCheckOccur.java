package commands;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import exceptions.ApunableException;
import tasks.Task;
import tasks.TaskList;
import utils.DateTimeUtil;
import utils.Ui;

/**
 * Prints a list of tasks that occur at given date(i.e. {@code task.isOccuringAt()} return true). 
 */
public class HandlerCheckOccur implements CommandHandler {
    @Override
    public void handle(TaskList taskList, Ui ui, 
            String firstParam, HashMap<String, String> params) throws ApunableException {

        assert !firstParam.isEmpty() : "Please provide date time for check-occur";

        LocalDateTime inputDate = DateTimeUtil.tryParse(firstParam);

        assert inputDate != null : "Invalid date time format";

        ArrayList<Task> occuringTasks = new ArrayList<>();

        for(int i = 0; i < taskList.size(); i++) {
            Boolean isOccuring = taskList.get(i).isOcurringAt(inputDate);

            if (isOccuring != null && isOccuring == true) {
                occuringTasks.add(taskList.get(i));
            }
        }

        ui.echo("Here are the tasks occuring on this date:");

        for(int i = 0; i < occuringTasks.size(); i ++) {
            ui.echo(String.format("%d.%s", i + 1, occuringTasks.get(i)));
        }
    }
}
