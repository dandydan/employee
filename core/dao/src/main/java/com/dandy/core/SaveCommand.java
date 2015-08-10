package com.dandy.core;

import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import com.dandy.infra.HibernateUtil;

public class SaveCommand<T> implements DbCommand {
    T t;
    Session session;

    SaveCommand(T t) {
      this.t = t;
    }

    @Override
    public void execute (Session session) {
        this.session = session;

        if (t instanceof Person) {
            savePerson();
        }
    }
    
    private void savePerson() {
        Person person = (Person) t;
  	    Criteria crit = session.createCriteria(Person.class);
        crit.add(Restrictions.eq("firstName", person.getFirstName()));
        crit.add(Restrictions.eq("middleName", person.getMiddleName()));
        crit.add(Restrictions.eq("lastName", person.getLastName()));
        crit.setProjection(Property.forName("personId"));
        if ((Integer)crit.uniqueResult()!=null) {
            System.out.println("Person already exist, edit or add another person");
        } else {
            session.save(person);
        }
    }
}
