package com.dandy;

import com.dandy.core.Person;
import com.dandy.core.Address;
import com.dandy.core.Contact;
import com.dandy.core.Role;
import com.dandy.core.PersonDTO;
import com.dandy.core.PersonService;
import com.dandy.core.RoleService;
import com.dandy.infra.HibernateUtil;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

public class Program5 {
    PersonService  personService  = new PersonService();
    RoleService    roleService    = new RoleService();
    InputValidator inputValidator = new InputValidator();

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Statistics stats = sessionFactory.getStatistics();
        stats.setStatisticsEnabled(true);
        int           choice = 0;
        boolean          run = true;
        Program5    program5 = new Program5();

        do {
            System.out.println("DATABASE OF PERSONS");
            System.out.println("1.  Add Person");
            System.out.println("2.  Edit Person Info");
            System.out.println("3.  Edit Address");
            System.out.println("4.  Remove Person");
            System.out.println("5.  Add Contact Info");
            System.out.println("6.  Remove Contact Info");
            System.out.println("7.  Add Role");
            System.out.println("8.  Remove Role");
            System.out.println("9.  Search by LastName");
            System.out.println("10. Search by Role");
            System.out.println("11. Sort by Lastame");
            System.out.println("12. Sort by Birthday");
            System.out.println("13. Sort by GWA");
            System.out.println("14. Exit");

            choice = program5.inputValidator.integerChecker();
            if(choice==1) {
                program5.addPersonInput();
            } else if (choice<=8) {
                program5.updatePersonInput(choice);
            } else if (choice<=13) {
		program5.displayPersons(choice);
            } else if (choice==14) {
                run = false;
            } else {
                System.out.println("Please choose 1 to 14 :");
            }
        } while (run);
        System.out.println("Opened Sessions: " + stats.getSessionOpenCount());
        System.out.println("Closed Sessions: " + stats.getSessionCloseCount());
        HibernateUtil.closeSessionFactory();
    }

    public void updatePersonInput(int choice) {
        System.out.print("Enter ID number: ");
        int personId = inputValidator.integerChecker();
        Person person = personService.getPersonById(personId);

        if(person.getPersonId() == 0) {
            System.out.println("Person not found!");
        } else {
            switch(choice) {
                case 2:
                  personService.updatePerson(editPerson(person));
                  break;
                case 3:
                  personService.updatePerson(editAddress(person));
                  break;
                case 4:
                  personService.removePerson(person);
                  break;
                case 5:
                  personService.updatePerson(addContacts(person));
                  break;
                case 6:
                  personService.removeContacts(person);
                  break;
                case 7:
                  personService.updatePerson(updatePersonRoleInput(person));
                  break;
                case 8:
                  personService.updatePerson(removePersonRoleInput(person));
                  break;
            }
        }
    }

    public Person editPerson(Person person) {
        String edit = "";

        if(person.getPersonId()!=0) {
            edit = "New ";
            System.out.println("Current Title: " + person.getTitle());
            System.out.println("Current Firstname: " + person.getFirstName());
            System.out.println("Current Middlename: " + person.getMiddleName());
            System.out.println("Current Lastname: " + person.getLastName());
            System.out.println("Current Suffix: " + person.getSuffix());
            System.out.println("Current Birthday: MM-DD-YYYY " + inputValidator.dateDisplay(person.getBirthday()));
            System.out.println("Current Employment Status: " + person.getEmployed());
            System.out.println("Current GWA: " + person.getGwa());
            System.out.println("Current Gender: " + person.getGender());
        } 
        System.out.print(edit + "Title: ");
        person.setTitle(inputValidator.simpleString());
        System.out.print(edit + "Firstname: ");
        person.setFirstName(inputValidator.simpleString());
        System.out.print(edit + "Middlename: ");
        person.setMiddleName(inputValidator.simpleString());
        System.out.print(edit + "Lastname: ");
        person.setLastName(inputValidator.simpleString());
        System.out.print(edit + "Suffix: ");
        person.setSuffix(inputValidator.simpleString());
        System.out.print(edit + "Birthday in this format MM-DD-YYYY: ");
        person.setBirthday(inputValidator.dateFormatter());
        System.out.print(edit + "Employed: ");
	person.setEmployed(inputValidator.employmentProcess());
        System.out.print(edit + "GWA: ");
	person.setGwa(inputValidator.gwaChecker());
        System.out.print(edit + "Gender: ");
        person.setGender(inputValidator.genderProcess());

        return person;
    }

    public Person editAddress(Person person) {
        String edit = "";

        if(person.getPersonId()!=0) {
            edit = "New ";
            System.out.println("Current Street No.: " + person.getAddress().getStNo());
            System.out.println("Current Brgy: " + person.getAddress().getBrgy());
            System.out.println("Current Subdivision: " + person.getAddress().getSubdivision());
            System.out.println("Current City: " + person.getAddress().getCity());
            System.out.println("Current Zipcode: " + person.getAddress().getZipcode());
        }
        System.out.print(edit + "Street No.: ");
        person.getAddress().setStNo(inputValidator.integerChecker());
        System.out.print(edit + "Brgy: ");
        person.getAddress().setBrgy(inputValidator.simpleString());
        System.out.print(edit + "Subdivision: ");
        person.getAddress().setSubdivision(inputValidator.simpleString());
        System.out.print(edit + "City: ");
        person.getAddress().setCity(inputValidator.simpleString()); 
        System.out.print(edit + "Zipcode: ");
        person.getAddress().setZipcode(inputValidator.integerChecker());

        return person;
    }

    public Person addContacts(Person person) {
        int choice = 0;
        boolean run = true;
        do {
            System.out.println("1. Add a contact");
            System.out.println("2. Exit from adding contact");
            choice = inputValidator.integerChecker();
            switch (choice) {
                case 1:
                    Contact contact = new Contact();
                    System.out.println("Contact Description: ");
                    contact.setDescription(inputValidator.contactDescriptor());
                    System.out.print("Contact Number: ");
                    contact.setNumber(inputValidator.longChecker());
                    person.getContacts().add(contact);
                    break;
                case 2:
                    run=false;
                    break;
                default:
		    System.out.println("Please choose 1 or 2 :");
		    break;
                }
            } while(run);
        return person;
    }

    public void addPersonInput() {
        int  choice = 0;
        boolean run = true;
	    Person         person = new Person();
        Address       address = new Address();
        Set<Contact> contacts = new HashSet<Contact>();

        person = editPerson(person);
        person.setAddress(address);
        person = editAddress(person);
        person.setContacts(contacts);
        person = addContacts(person);
        address.setPerson(person);
        personService.addPerson(person);
    }

    public Person updatePersonRoleInput(Person person) {
        int choice = 0;
        boolean run = true;
        List<Role> roles = roleService.getRoles();
        do {
            for(Role role: roles) {
                System.out.println(role.getRoleId() + ". " + role.getPos());
            }
            System.out.println("6. Done");
            choice = inputValidator.integerChecker();
            if(choice==6){
                run = false;
            } else if (choice>6) {
                System.out.println("choose 1 to 6");
            } else {
                person.getRoles().add(roles.get(choice-1));
            }
        }while(run);
        return person;
    }

    public Person removePersonRoleInput(Person person) {
        boolean run = true;
        Set<Role> roles = person.getRoles();
        do {
             int choice = 0;
             Map<Integer,Role> roleChoice = new HashMap<Integer,Role>();
             for(Role role: roles) {
                 System.out.println((++choice) + ". " + role.getPos());
                 roleChoice.put(choice,role);
             }
             System.out.println((++choice) + ". Done");
             choice = inputValidator.integerChecker();
             if(choice==(roles.size()+1)){
                 run = false;
             } else if (choice>roles.size()) {
                 System.out.println("choose 1 to " + (roles.size()+1));
             } else {
                roles.remove(roleChoice.get(choice));
             }
        }while(run);
        person.setRoles(roles);
        return person;
    }

    public void displayPersons(int conditionVar) {
        String field = "";
        String searchText = "";
        String order = "";

        switch(conditionVar) {
            case 9:
                System.out.print("Enter a string: ");
                searchText = inputValidator.simpleString();
                field = "lastName";
                break;
            case 10:
                System.out.print("Enter a string: ");
                searchText = inputValidator.simpleString();
                field = "roles.pos";
                break;
            case 11:
                order = "lastName";
                break;
            case 12:
                order = "birthday";
                break;
        }
        List<PersonDTO> result = personService.getPersons(field, searchText, order);
        if(conditionVar==13) {
             Collections.sort(result, new PersonDTO());
        }
        Set<String> numbers = new HashSet<String>();
        Set<String> roles = new HashSet<String>();
        for(int i = 0; i < result.size() ; i++) {
            if(i<(result.size()-1) && (result.get(i).getPersonId() == result.get(i+1).getPersonId())){
                numbers.add(getStringNumber(result.get(i).getNumber()));
                roles.add(getRoles(result.get(i).getPos()));
            } else{
            numbers.add(getStringNumber(result.get(i).getNumber()));
            roles.add(getRoles(result.get(i).getPos()));
            System.out.print("ID: " + result.get(i).getPersonId());
            System.out.print("\tFirstname: " + result.get(i).getFirstName());
            System.out.print(" Lastname: " + result.get(i).getLastName());
            System.out.print("\tGWA: " + result.get(i).getGwa());
            System.out.print("\tBirthday: " + inputValidator.dateDisplay(result.get(i).getBirthday()));
            System.out.print("\tZipcode: " + result.get(i).getZipcode());
            System.out.print("\tNumber: " );
            for(String number : numbers) {
                System.out.print(number + " ");
            }
            System.out.print("\tRole: ");
            for(String role : roles) {
                System.out.print(role + " ");
            }
            System.out.println();
            numbers.clear();
            roles.clear();
            }
        }
    }

    public String getStringNumber (Long number) {
        if (number!= null) {
            return number.toString();
        } else {
        return "";
        }
    }

    public String getRoles (String role) {
        if (role != null) {
            return role;
        } else {
        return "";
        }
    }
}
