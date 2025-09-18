package commands;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
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

        List<Task> occuringTasks = Stream.iterate(0, i -> i < taskList.size(), i -> i + 1)
                .map(i -> taskList.get(i)).filter(task -> {
                    Boolean isOcurring = task.isOcurringAt(inputDate);
                    return isOcurring != null && isOcurring == true;
                }).toList();

        // for (int i = 0; i < taskList.size(); i++) {
        //     if (taskList.get(i).isOcurringAt(inputDate) == true) {
        //         occuringTasks.add(taskList.get(i));
        //     }
        // }

        if (occuringTasks.isEmpty()) {
            ui.echo("(no tasks occuring at given date time)");
        } else {
            ui.echo("Here are the tasks occuring on this date:");

            Stream.iterate(0, i -> i < occuringTasks.size(), i -> i + 1).forEach(i -> {
                ui.echo(String.format("%d.%s", i + 1, occuringTasks.get(i)));
            });

            // for (int i = 0; i < occuringTasks.size(); i ++) {
            //     ui.echo(String.format("%d.%s", i + 1, occuringTasks.get(i)));
            // }
        }
    }
}
