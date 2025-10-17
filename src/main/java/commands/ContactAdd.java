package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import models.Contact;
import models.ContactBook;
import models.TaskList;
import utils.Storage;
import utils.Ui;

/**
 * Handles the {@code addcontact} command from user and add a new contact. Throws exception if name already in contact.
 */
public class ContactAdd implements ContactHandler {
    @Override
    public void handle(TaskList taskList, ContactBook contactBook, Ui ui,
                       String firstParam, HashMap<String, String> params) throws ApunableException {

        if (firstParam.isEmpty()) {
            throw new ApunableException("Please provide name for the contact");
        }

        if (!params.containsKey("phone") || !params.containsKey("email") || !params.containsKey("address")) {
            throw new ApunableException("Please more information about the person");
        }

        params.put("name", firstParam);

        Contact contactToAdd = new Contact(params);

        if (contactBook.hasPerson(contactToAdd)) {
            throw new ApunableException("Name is already in contact");
        }

        contactBook.add(contactToAdd);

        new Storage("data/contacts.txt").save(contactBook);

        ui.echo("got it, I have saved this contact: ");
        ui.echo("  " + contactToAdd.basicInfo());
    }
}
