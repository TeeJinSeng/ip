package tasks;

import java.util.ArrayList;

/**
 * Stores tasks added by user. 
 */
public class TaskList {
    public static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loadedTasks) {
        tasks = loadedTasks;
    }

    /**
     * Returns the task at specific 0-based {@code index}. 
     * 
     * @param index index of the task to be retrieved(0-based). 
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * @return the number of tasks in this {@code taskList}. 
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a new task into this {@code taskList}. 
     * . 
     * 
     * @param task task to be added. 
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from this {@code taskList}. 
     * 
     * @param index index of task to be removed
     */
    public void remove(int index) {
        tasks.remove(index);
    }
}
