package types;

import java.time.LocalDateTime;
import java.util.HashMap;

import exceptions.ApunableException;
import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;
import utils.DateTimeUtil;

/**
 * Handles the deadline command from user and add a new {@code Deadline} task. 
 * 
 * @param input formatted user input
 */
public class CreateTaskDeadline extends CreateTaskHandler {
    @Override
    public Task createTask(String firstParam, HashMap<String, String> params) throws ApunableException {
        Task task = null;

        String desc = firstParam;
        String byStr = params.get("by");

        if (desc.isEmpty() || byStr.isEmpty()) {
            throw new ApunableException("Missing arguments");
        }

        LocalDateTime by = DateTimeUtil.tryParse(byStr);

        if (by == null) {
            throw new ApunableException("Invalid date format for by");
        }

        return new Deadline(desc, by);
    }
}