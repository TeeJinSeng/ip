package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import tasks.Contact;
import tasks.ContactList;
import tasks.TaskList;
import utils.Storage;
import utils.Ui;

public class ContactAdd implements ContactHandler {
    @Override
    public void handle(TaskList taskList, ContactList contactList, Ui ui, 
            String firstParam, HashMap<String, String> params) throws ApunableException {

        if (!firstParam.isEmpty()) {
            params.put("name", firstParam);
        }

        Contact contactToAdd = new Contact(params);

        if (contactList.contains(contactToAdd)) {
            throw new ApunableException("Name is already in contact");
        }

        contactList.add(contactToAdd);

        new Storage("data/contacts.txt").save(contactList);

        ui.echo("got it, I have saved this contact: ");
        ui.echo("  " + contactToAdd.basicInfo());
    }
}
