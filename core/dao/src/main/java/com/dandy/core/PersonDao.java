package com.dandy.core;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.ArrayList;

import com.dandy.core.Person;
import com.dandy.core.PersonDTO;
import com.dandy.core.Address;
import com.dandy.core.Contact;
import com.dandy.infra.HibernateUtil;

class PersonDao {

    SaveCommand           saveCommand;
    UpdateCommand       updateCommand;
    DeleteCommand       deleteCommand;
    CommandInvoker     commandInvoker;
    GetCommand             getCommand;
    GetListCommand     getListCommand;
    GetPersonDTOList getPersonDTOList;

    private Session getSession() {
        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
	    return sess;
    }

    public void updatePerson(Person person){
        updateCommand  = new UpdateCommand(person);
        commandInvoker = new CommandInvoker(updateCommand);
        execute(commandInvoker);
    }

    public void removeContacts(Person person) {
        person.getContacts().clear();
        updateCommand  = new UpdateCommand(person);
        commandInvoker = new CommandInvoker(updateCommand);
        execute(commandInvoker);
    }

    public void removePerson(Person person){
        deleteCommand  = new DeleteCommand(person);
        commandInvoker = new CommandInvoker(deleteCommand);
        execute(commandInvoker);
    }

    public void addPerson(Person person){
        saveCommand    = new SaveCommand(person);
        commandInvoker = new CommandInvoker(saveCommand);
        execute(commandInvoker);
    }

    public Person getPersonById(int personId) {
        getCommand     = new GetCommand(Person.class, personId);
        commandInvoker = new CommandInvoker(getCommand);
        execute(commandInvoker);
        Person person = (Person) getCommand.getEntity();
        return person;
    }

    public List<PersonDTO> getPersons(String field, String searchText, String order) {
        List<PersonDTO> personDTOs = new ArrayList<PersonDTO>();
        getPersonDTOList = new GetPersonDTOList(field, searchText, order);
        commandInvoker   = new CommandInvoker(getPersonDTOList);
        execute(commandInvoker);
        personDTOs = getPersonDTOList.getDTOList();
        return personDTOs;
    }

    public List<Person> getPersonList () {
        List<Person> persons = new ArrayList<Person>();
        getListCommand = new GetListCommand(Person.class);
        commandInvoker = new CommandInvoker(getListCommand);
        execute(commandInvoker);
        persons = getListCommand.getListEntity();
        return persons;
    }

    void execute(CommandInvoker command) {
        try {
            getSession().beginTransaction();
            command.invoke(getSession());
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        }
    }

}
