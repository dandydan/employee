package com.dandy.core;

import java.util.List;

public class PersonService {
    private PersonDao personDao;

    public boolean addPerson(Person person) {
        personDao = new PersonDao();
        return personDao.addPerson(person);
    }

    public boolean updatePerson(Person person){
        personDao = new PersonDao();
        return personDao.updatePerson(person);
    }
    
    public Person getPersonById(int personId){
        personDao = new PersonDao();
        Person person = personDao.getPersonById(personId);
        return person;
    }

    public void removePerson(Person person) {
        personDao = new PersonDao();
        personDao.removePerson(person);
    }

    public void removeContacts(Person person) {
        personDao = new PersonDao();
        personDao.removeContacts(person);
    }

    public List<PersonDTO> getPersons(String field, String searchText, String order) {
        personDao = new PersonDao();
        return personDao.getPersons(field, searchText, order);
    }
}
