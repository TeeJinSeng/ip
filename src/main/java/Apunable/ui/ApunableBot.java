package Apunable.ui;

import exceptions.ApunableException;
import tasks.TaskList;
import utils.Command;
import utils.Parser;
import utils.Storage;
import utils.Ui;

/**
 * A Chatbot that only talks about tasks with users.
 */
public class ApunableBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes a new Chatbot instance and load tasklist from {@code filePath}
     * @param filePath path to file that store tasklist
     */
    public ApunableBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ApunableException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts running the chatbot to interact with users(accept inputs, process and produce output). 
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ApunableException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new ApunableBot("data/tasks.txt").run();
    }
}
