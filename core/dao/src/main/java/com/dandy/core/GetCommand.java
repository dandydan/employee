package com.dandy.core;

import com.dandy.core.Person;
import org.hibernate.FetchMode;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.hibernate.Session;

public class GetCommand<T> implements DbCommand {

    int id;
    Class<T> type;
    T t;

    GetCommand(Class<T> type, int id) {
      this.id = id;
      this.type = type;
    }

    @Override
    public void execute (Session session){
        if(type == Person.class) {
            setPerson(session);
        }else{
            this.t = (T) session.get(type, id);
        }
    }

    public void setPerson(Session session) {
        Criteria crit = session.createCriteria(Person.class, "person");
        crit.add(Restrictions.eq("person.personId", id));
        crit.setFetchMode("contacts", FetchMode.JOIN);
        crit.setFetchMode("roles", FetchMode.JOIN);
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        t = (T) crit.uniqueResult();
    }

    public T getEntity() {
        return this.t;
    }

}
