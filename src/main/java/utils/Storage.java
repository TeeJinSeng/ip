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
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the tasks into file.
     * @param tasks list of tasks added by user recently
     */
    public void save(TaskList tasks) throws ApunableException {
        File dataFile = new File(filePath);
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

    /**
     * Loads the tasks from {@code filePath} specified.
     */
    public ArrayList<Task> load() throws ApunableException {
        ArrayList<Task> tasks = new ArrayList<>();

        File dataFile = new File(filePath);
        Scanner sc;
        try {
            sc = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            throw new ApunableException("data file does not exist");
        }
        
        try {
            while(sc.hasNext()) {
                String formattedString = sc.nextLine();
                String[] taskInfos = formattedString.split(" \\| ", 4);

            
                switch (taskInfos[0]) {
                    case "T" -> {
                        tasks.add(new Todo(taskInfos[2]));
                    }
                    case "D" -> {
                        tasks.add(new Deadline(taskInfos[2], taskInfos[3]));
                    }
                    case "E" -> {
                        String[] fromTo = taskInfos[3].split(" \\| ");
                        tasks.add(new Event(taskInfos[2], fromTo[0], fromTo[1]));
                    }
                    default -> {
                        sc.close();
                        throw new ApunableException("Wrong format at line " + (tasks.size()+1));
                    }
                }

                if (taskInfos[1].equals("1")) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
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
