package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import tasks.ContactList;
import tasks.TaskList;
import utils.Ui;

public class ContactHandlerList implements ContactHandler {
    @Override
    public void handle(TaskList taskList, ContactList contactList, Ui ui, 
            String firstParam, HashMap<String, String> params) throws ApunableException {

        ui.echo("Here are the list of contacts: ");

        for (int i = 0; i < contactList.size(); i++) {
            ui.echo(contactList.get(i).basicInfo());
        }
    }
}
