package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class DataSaver {
    /**
     * Saves the tasks into file.
     * @param tasks list of tasks added by user recently
     */
    public static void saveToFile(ArrayList<Task> tasks) {
        File dataFile = new File("./data/data.txt");
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Failed to create new file");
            return;
        }

        try {
            FileWriter fw = new FileWriter(dataFile);
            for (Task task : tasks) {
                fw.write(task.getFormattedString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed to write to file");
        }
    }

    /**
     * Reads the tasks from file.
     */
    public static ArrayList<Task> loadFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();

        File dataFolder = new File("./data");
        if (!dataFolder.exists() || !dataFolder.isDirectory()) {
            System.out.println("data folder does not exist");
            return tasks;
        }

        File dataFile = new File("./data/data.txt");
        if (!dataFile.exists() || !dataFile.isFile()) {
            System.out.println("data file does not exist");
            return tasks;
        } else if (!dataFile.canWrite()) {
            System.out.println("data file cannot be modified");
            return tasks;
        }

        Scanner sc;
        try {
            sc = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            return tasks;
        }
        
        while(sc.hasNext()) {
            String formattedString = sc.nextLine();
            String[] taskInfos = formattedString.split(" \\| ");

            try {
                switch (taskInfos[0]) {
                    case "T" -> {
                        tasks.add(new Todo(taskInfos[2]));
                    }
                    case "D" -> {
                        tasks.add(new Deadline(taskInfos[2], taskInfos[3]));
                    }
                    case "E" -> {
                        String[] fromTo = taskInfos[3].split("-");
                        tasks.add(new Event(taskInfos[2], fromTo[0], fromTo[1]));
                    }
                    default -> {
                        System.out.println("Wrong format at line " + (tasks.size()+1));
                        sc.close();
                        return tasks;
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(String.format("Wrong format at line %d, message: %s", 
                    tasks.size() + 1, e.getMessage()));
                sc.close();
                return tasks;
            }

            if (taskInfos[1].equals("1")) {
                tasks.get(tasks.size() - 1).markAs(true);
            }
        }
        sc.close();

        return tasks;
    }
}
