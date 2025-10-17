package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import models.ContactBook;
import models.TaskList;
import utils.Ui;

/**
 * Handles the {@code filtercontact} command from user and delete a contact with given name.
 */
public class ContactFilter implements ContactHandler {
    @Override
    public void handle(TaskList taskList, ContactBook contactList, Ui ui,
                       String firstParam, HashMap<String, String> params) throws ApunableException {

        for (int index : contactList.getIndices(params)) {
            ui.echo(contactList.get(index).basicInfo());
        }
    }
}
