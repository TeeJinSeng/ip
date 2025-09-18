package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.ApunableException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

/**
 * A util that handles storing and loading tasks from and to a provided {@code filePath}. 
 */
public class Storage {
    private final String FILE_PATH;

    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    /**
     * Saves the tasks into file.
     * 
     * @param tasks list of tasks added by user recently. 
     */
    public void save(TaskList tasks) throws ApunableException {
        File dataFile = new File(FILE_PATH);

        // Ensure parent directories exist
        File parentDirectory = dataFile.getParentFile();
        if (parentDirectory != null && !parentDirectory.exists()) {
            boolean isDirectoriesCreated = parentDirectory.mkdirs();
            if (!isDirectoriesCreated) {
                throw new ApunableException("Failed to create parent directories for file: " + FILE_PATH);
            }
        }

        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            throw new ApunableException("Failed to create new file");
        }

        try {
            FileWriter fw = new FileWriter(dataFile);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).getFormattedString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed to write to file");
        }
    }

    private Task createTaskFromString(String formattedString, int lineNum) throws ApunableException {
        String[] taskInfos = formattedString.split(" \\| ", 4);
        Task task = null;

        switch (taskInfos[0]) {
            case "T" -> {
                task = new Todo(taskInfos[2]);
            }
            case "D" -> {
                task = new Deadline(taskInfos[2], taskInfos[3]);
            }
            case "E" -> {
                String[] fromTo = taskInfos[3].split(" \\| ");
                task = new Event(taskInfos[2], fromTo[0], fromTo[1]);
            }
            default -> {
                throw new ApunableException("Wrong format at line " + lineNum);
            }
        }

        if (task != null && taskInfos[1].equals("1")) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Loads the tasks from {@code FILE_PATH} specified.
     */
    public ArrayList<Task> load() throws ApunableException {
        ArrayList<Task> tasks = new ArrayList<>();

        File dataFile = new File(FILE_PATH);
        Scanner sc;
        try {
            sc = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            throw new ApunableException("data file does not exist");
        }
        
        try {
            while (sc.hasNext()) {
                String formattedString = sc.nextLine();
                tasks.add(createTaskFromString(formattedString, tasks.size() + 1));
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ApunableException(String.format("Wrong format at line %d, message: %s", 
                tasks.size() + 1, e.getMessage()));
        } catch (ApunableException e) {
            throw e;
        } finally {
            sc.close();
        }

        return tasks;
    }
}
