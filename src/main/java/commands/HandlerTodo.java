package commands;

import java.util.HashMap;

import tasks.Task;
import tasks.Todo;
import exceptions.ApunableException;

/**
 * Handles the todo command from user and add a new {@code Todo} task. 
 * 
 * @param input formatted user input
 */
public class HandlerTodo extends CreateTaskHandler {
    @Override
    public Task createTask(String firstParam, HashMap<String, String> params) throws ApunableException {
        try {
            if (firstParam.isEmpty()) {
                throw new ApunableException("Description for todo cannot be empty");
            }
    
            return new Todo(firstParam.trim());
        } catch (NumberFormatException e) {
            throw new ApunableException("Wrong index format");
        } catch (IndexOutOfBoundsException e) {
            throw new ApunableException("Wrong index format");
        }
    }
}