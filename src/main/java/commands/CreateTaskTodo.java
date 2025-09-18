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
        try {
            String desc = firstParam;

            if (desc.isEmpty()) {
                throw new ApunableException("Description for todo cannot be empty");
            }
    
            return new Todo(desc);
        } catch (NumberFormatException e) {
            throw new ApunableException("Wrong index format");
        } catch (IndexOutOfBoundsException e) {
            throw new ApunableException("Wrong index format");
        }
    }
}