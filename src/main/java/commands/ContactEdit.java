package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import tasks.ContactList;
import tasks.TaskList;
import utils.Ui;

public class ContactEdit implements ContactHandler {
    public void handle(TaskList taskList, ContactList contactList, Ui ui, 
            String firstParam, HashMap<String, String> params) throws ApunableException {

        String name = firstParam;

        assert !name.isEmpty() : "No name provided for delete contact command";

        int index = 0;// contactList.getNames().indexOf(name);

        if (index == -1) {
            throw new ApunableException("name not in the contact");
        } else {
            // ContactList.remove(index);
        }
    }

}
