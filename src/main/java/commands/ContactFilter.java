package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import tasks.ContactList;
import tasks.TaskList;
import utils.Ui;

public class ContactFilter implements ContactHandler {
    public void handle(TaskList taskList, ContactList contactList, Ui ui, 
            String firstParam, HashMap<String, String> params) throws ApunableException {

        for (int index : contactList.getIndices(params)) {
            ui.echo(contactList.get(index).basicInfo());
        }
    }
}
