package commands;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import exceptions.ApunableException;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;
import utils.Ui;

public class HandlerDeleteTest {
    @Test
    public void testDelete_wrongNumberFormat_throwException() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        HandlerDelete handlerDelete = new HandlerDelete();
        HashMap<String, String> params = new HashMap<>();

        assertThrows(ApunableException.class, () -> handlerDelete.handle(taskList, ui, "abc", params));
    }

    @Test
    public void testDelete_indexOutOfBound_throwException() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        HandlerDelete handlerDelete = new HandlerDelete();
        HashMap<String, String> params = new HashMap<>();

        assertThrows(ApunableException.class, () -> handlerDelete.handle(taskList, ui, "-1", params));
    }

    @Test
    public void normalTest() {
        TaskList taskList = new TaskList();
        Task firstTask = new Todo("1");
        Task secondTask = new Todo("2");
        Task thirdTask = new Todo("3");

        taskList.add(firstTask);
        taskList.add(secondTask);
        taskList.add(thirdTask);

        Ui ui = new Ui();
        HandlerDelete handlerDelete = new HandlerDelete();
        HashMap<String, String> params = new HashMap<>();

        try {
            handlerDelete.handle(taskList, ui, "2", params);
        } catch (Exception e) {}

        assertEquals(taskList.get(0), firstTask);
        assertEquals(taskList.get(1), thirdTask);
    }
}