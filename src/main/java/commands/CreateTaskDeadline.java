package commands;

import java.time.LocalDateTime;
import java.util.HashMap;

import exceptions.ApunableException;
import tasks.Deadline;
import tasks.Task;
import utils.DateTimeUtil;

/**
 * Handles the deadline command from user and add a new {@code Deadline} task. 
 * 
 * @param input formatted user input
 */
public class CreateTaskDeadline extends CreateTaskHandler {
    @Override
    public Task createTask(String firstParam, HashMap<String, String> params) throws ApunableException {
        String desc = firstParam;
        String byStr = params.getOrDefault("by", "");

        if (desc.isEmpty() || byStr.isEmpty()) {
            throw new ApunableException("Missing arguments(description or /by)");
        }

        LocalDateTime by = DateTimeUtil.tryParse(byStr);

        if (by == null) {
            throw new ApunableException("Invalid date format for by");
        }

        return new Deadline(desc, by);
    }
}