package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import exceptions.ApunableException;
import utils.DateTimeUtil;

class Phone {
    private String countryCode;
    private String number;

    public Phone(String code, String num) {
        countryCode = code;
        number = num;
    }

    public Phone(String fullNumber) {
        if (fullNumber.charAt(0) == '+' || fullNumber.charAt(2) == ' ') {
            fullNumber = fullNumber.replaceAll("[^0-9]", "");
    
            countryCode = fullNumber.substring(0, 2);
            number = fullNumber.substring(2);
        } else {
            countryCode = "";
            number = fullNumber;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Phone)) {
            return false;
        }

        Phone p = (Phone) obj;

        if (countryCode.isEmpty() || p.countryCode.isEmpty()) {
            return number.equals(p.number);
        }
        return countryCode.equals(p.countryCode) && number.equals(p.number);
    }

    @Override
    public String toString() {
        if (!countryCode.isEmpty()) {
            return String.format("+%s %s", countryCode, number);
        } else {
            return number;
        }
    }
}

public class Contact {
    private String name;
    private String firstName;
    private String lastName;
    private ArrayList<Phone> phoneNumbers;
    private String address;
    private String organization;
    private ArrayList<String> emails;
    private String notes;
    private String nickName;
    private LocalDate birthday;

    public Contact(String phone, String name, String first, String last, String addr) {
        phoneNumbers = new ArrayList<>(List.of(new Phone(phone)));
        
        this.name = name;
        firstName = first;
        lastName = last;
        address = addr;
    }

    public Contact(HashMap<String, String> details) throws ApunableException {
        name = details.getOrDefault("name", "");
        firstName = details.getOrDefault("firstname", "");
        lastName = details.getOrDefault("lastname", "");

        if (name.isEmpty()) {
            name = firstName + " " + lastName;
        } else if (firstName.isEmpty() && lastName.isEmpty()) {
            int spaceInd = name.lastIndexOf(' ');

            firstName = name.substring(0, spaceInd);
            lastName = name.substring(spaceInd + 1);
        }

        String[] phoneNumStrs = details.getOrDefault("phone", "").split(";");
        phoneNumbers = new ArrayList<>();

        for(String phoneNumStr : phoneNumStrs) {
            if (!phoneNumStr.isEmpty()) {
                phoneNumbers.add(new Phone(phoneNumStr));
            }
        }

        address = details.getOrDefault("address", "");
        organization = details.getOrDefault("organization", "");

        String[] emailStrs = details.getOrDefault("email", "").split(";");
        emails = new ArrayList<>();

        for(String emailStr : emailStrs) {
            if (emailStr.isEmpty()) {
                continue;
            }
            if (!emailStr.contains("@")) {
                throw new ApunableException("Invalid email format: " + emailStr);
            }
            emails.add(emailStr);
        }
        notes = details.getOrDefault("notes", "");
        nickName = details.getOrDefault("nickname", "");
        
        String birthdayStr = details.getOrDefault("birthday", "");
        if (birthdayStr.isEmpty()) {
            birthday = null;
        } else {
            birthday = DateTimeUtil.parseDate(birthdayStr);
        }
    }

    public static String[] getHeader() {
        return new String[]{
            "name",
            "firstname",
            "lastname",
            "phone",
            "address",
            "organization",
            "email",
            "notes",
            "nickname",
            "birthday",
        };
    }

    public String getFormattedString() {
        String phoneStr = String.join(";", phoneNumbers.stream().map(p -> p.toString()).toList());
        String emailStr = String.join(";", emails);
        String bdayStr = "";
        if (birthday != null) {
            bdayStr = birthday.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }

        return String.join("\t| ", new String[]{
            name, firstName, lastName, 
            phoneStr, address, organization, 
            emailStr, notes, nickName, bdayStr});
    }

    public String getName() {
        return name;
    }

    public boolean fit(HashMap<String, String> criteria) {
        boolean isFit = true;

        isFit &= name.equals(criteria.getOrDefault("name", name));
        isFit &= firstName.equals(criteria.getOrDefault("firstname", firstName));
        isFit &= lastName.equals(criteria.getOrDefault("lastname", lastName));
        isFit &= (!criteria.containsKey("phone") || phoneNumbers.contains(new Phone(criteria.get("phone"))));
        isFit &= address.equals(criteria.getOrDefault("address", address));
        isFit &= organization.equals(criteria.getOrDefault("organization", organization));
        isFit &= (!criteria.containsKey("email") || emails.contains(criteria.get("email")));
        isFit &= notes.equals(criteria.getOrDefault("notes", notes));
        isFit &= nickName.equals(criteria.getOrDefault("nickname", nickName));
        isFit &= (!criteria.containsKey("birthday") || birthday.format(DateTimeFormatter.ofPattern("dd MMM"))
                .equals(criteria.get("birthday")));

        return isFit;
    }

    public String basicInfo() {
        // name, phone, email, organization
        return String.format("%s(org: %s), \nphone: %s\nemail: %s", name, organization, 
                String.join(", ", phoneNumbers.stream().map(p -> p.toString()).toList()), 
                String.join(", ", emails));
    }

    public String detailedInfo() {
        // name, first name, last name, phone, email, organization, address
        return basicInfo() + String.format("\nfirstname: %s, lastname: %s\naddress: %s", 
                firstName, lastName, address);
    }

    public String allInfo() {
        // name, first name, last name, phone, email, organization, address, notes, nickname, birthday
        return detailedInfo() + String.format("\nnotes: %s\nnickname: %s\nbirthday: %s", 
                notes, nickName, birthday.format(DateTimeFormatter.ofPattern("dd MMM")));
    }
}
