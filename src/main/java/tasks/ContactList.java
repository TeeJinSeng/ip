package tasks;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.ApunableException;

/**
 * Stores names added by user. 
 */
public class ContactList implements Savable {
    public static ArrayList<Contact> contacts;

    public ContactList() {
        contacts = new ArrayList<>();
    }

    private Contact createContactFromString(String header, String row) throws ApunableException {
        String[] headers = header.split("\\|");
        String[] arguments = row.split("\\|");
        HashMap<String, String> paramArgs = new HashMap<>();

        for(int i = 0;i < headers.length; i++) {
            if (!headers[i].trim().isEmpty()) {
                paramArgs.put(headers[i].trim(), arguments[i].trim());
            }
        }

        return new Contact(paramArgs);
    }

    public ContactList(ArrayList<String> contactStrs) throws ApunableException {
        contacts = new ArrayList<>();

        for (int i = 1; i < contactStrs.size(); i++) {
            try {
                contacts.add(createContactFromString(contactStrs.get(0), contactStrs.get(i)));
            } catch (ApunableException e) {
                throw new ApunableException(e.getMessage() + " at line " + i);
            }
        }
    }

    @Override
    public String getFormattedString() {
        return String.join("\t| ", Contact.getHeader()) + "\n"
                + String.join("\n", contacts.stream().map(c -> c.getFormattedString()).toList());
    }

    /**
     * Returns the name at specific 0-based {@code index}. 
     * 
     * @param index index of the name to be retrieved(0-based). 
     */
    public Contact get(int index) {
        return contacts.get(index);
    }

    /**
     * @return the number of names in this {@code contactList}. 
     */
    public int size() {
        return contacts.size();
    }

    /**
     * Adds a new name into this {@code contactList}. 
     * 
     * @param name name to be added. 
     */
    public void add(Contact name) {
        contacts.add(name);
    }

    /**
     * Removes a name from this {@code contactList}. 
     * 
     * @param index index of name to be removed. 
     */
    public void remove(int index) {
        contacts.remove(index);
    }

    public boolean contains(Contact contact) {
        return contacts.stream().map(c -> c.getName()).toList().contains(contact.getName());
    }

    public Integer[] getIndices(HashMap<String, String> criteria) {
        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).fit(criteria)) {
                indices.add(i);
            }
        }

        return indices.toArray(new Integer[indices.size()]);
    }
}
