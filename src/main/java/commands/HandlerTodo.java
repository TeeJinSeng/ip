package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import tasks.Task;
import tasks.Todo;

/**
 * Handles the todo command from user and add a new {@code Todo} task. 
 */
public class HandlerTodo extends CreateTaskHandler {
    @Override
    public Task createTask(String firstParam, HashMap<String, String> params) throws ApunableException {
        assert !firstParam.isEmpty() : "Description for todo cannot be empty";
    
        return new Todo(firstParam.trim());
    }
}