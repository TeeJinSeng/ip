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

        
        assert !desc.isEmpty() : "Event for deadline cannot be empty! ";
        assert !fromStr.isEmpty() : "Please provide argument for /from";
        assert !toStr.isEmpty() : "Please provide argument for /to";

        LocalDateTime from;
        LocalDateTime to;

        from = DateTimeUtil.tryParse(fromStr);
        to = DateTimeUtil.tryParse(toStr);
        
        assert from != null : "Invalid date time format for /from";
        assert to != null : "Invalid date time format for /to";

        return new Event(desc, from, to);
    }
}