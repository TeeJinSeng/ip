package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import tasks.Task;
import tasks.Todo;

/**
 * Handles the todo command from user and add a new {@code Todo} task. 
 */
public class CreateTaskTodo extends CreateTaskHandler {
    @Override
    public Task createTask(String firstParam, HashMap<String, String> params) throws ApunableException {
        String desc = firstParam;
        
        assert !desc.isEmpty() : "Description for todo cannot be empty";
    
        return new Todo(desc);
    }
}