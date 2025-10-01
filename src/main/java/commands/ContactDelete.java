package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import tasks.Contact;
import tasks.ContactList;
import tasks.TaskList;
import utils.Ui;

public class ContactDelete implements ContactHandler {
    @Override
    public void handle(TaskList taskList, ContactList contactList, Ui ui, 
            String firstParam, HashMap<String, String> params) throws ApunableException {

        String name = firstParam;

        assert !name.isEmpty() : "No name provided for delete contact command";

        HashMap<String, String> filterCriteria = new HashMap<>(1);
        filterCriteria.put("name", name);

        Integer[] index = contactList.getIndices(filterCriteria);
        Contact contactToDelete = null;

        if (index.length == 0) {
            throw new ApunableException("name not in the contact");
        } else {
            contactToDelete = contactList.get(index[0]);
            contactList.remove(index[0]);
            
            ui.echo("Got it, I have deleted this contact: ");
            ui.echo("  " + contactToDelete.basicInfo());
        }
    }

}
