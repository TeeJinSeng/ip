package commands;

import java.time.LocalDateTime;
import java.util.HashMap;

import exceptions.ApunableException;
import models.Deadline;
import models.Task;
import utils.DateTimeUtil;

/**
 * Handles the {@code deadline} command from user and add a new {@code Deadline} task.
 */
public class CreateTaskDeadline extends CreateTaskHandler {
    @Override
    public Task createTask(String firstParam, HashMap<String, String> params) throws ApunableException {
        String desc = firstParam;
        String byStr = params.getOrDefault("by", "");

        assert !desc.isEmpty() : "Description for deadline cannot be empty! ";
        assert !byStr.isEmpty() : "Please provide argument for /by";

        LocalDateTime by = DateTimeUtil.tryParse(byStr);

        assert by != null : "Invalid date format for by";

        return new Deadline(desc, by);
    }
}