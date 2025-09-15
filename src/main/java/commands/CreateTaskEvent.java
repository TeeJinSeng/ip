package commands;

import java.time.LocalDateTime;
import java.util.HashMap;

import exceptions.ApunableException;
import tasks.Event;
import tasks.Task;
import utils.DateTimeUtil;

/**
 * Handles the event command from user and add a new {@code Event} task. 
 */
public class CreateTaskEvent extends CreateTaskHandler {
    @Override
    public Task createTask(String firstParam, HashMap<String, String> params) throws ApunableException {

        String desc = firstParam;

        String fromStr = params.getOrDefault("from", "");
        String toStr = params.getOrDefault("to", "");

        if (desc.isEmpty() || fromStr.isEmpty() || toStr.isEmpty()) {
            throw new ApunableException("Missing arguments(description, /from or /to)");
        }

        LocalDateTime from;
        LocalDateTime to;

        from = DateTimeUtil.tryParse(fromStr);
        to = DateTimeUtil.tryParse(toStr);

        if (from == null || to == null) {
            throw new ApunableException("Invalid date time format for from/to");
        }

        return new Event(desc, from, to);
    }
}