package tasks;

import java.util.ArrayList;

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
     * @param index index of the task to get(1-based). 
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * @return the size of {@code taskList}. 
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Add a new task into taskList. 
     * 
     * @param task task to be added. 
     */
    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int index) {
        tasks.remove(index);
    }
}
