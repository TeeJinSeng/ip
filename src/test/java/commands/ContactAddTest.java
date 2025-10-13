package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import exceptions.ApunableException;
import tasks.Contact;
import tasks.ContactList;
import tasks.TaskList;
import utils.Ui;

public class ContactAddTest {
    @Test
    public void handle_allFieldPresent_success() throws ApunableException {
        TaskList taskList = new TaskList();
        ContactList contactList = new ContactList();
        Ui ui = new Ui();

        Contact contact = new ContactBuilder().build();

        ContactList expectedContactList = new ContactList();
        expectedContactList.add(contact);

        ContactAdd contactAdd = new ContactAdd();
        contactAdd.handle(taskList, contactList, ui, ContactBuilder.DEFAULT_NAME, new ContactBuilder().params);

        assertEquals(expectedContactList, contactList);
    }
}