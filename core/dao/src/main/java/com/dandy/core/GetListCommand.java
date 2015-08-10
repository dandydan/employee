package com.dandy.core;

import org.hibernate.Session;
import org.hibernate.Criteria;

import java.util.List;

public class GetListCommand<T> implements DbCommand {

    Class<T> type;
    List<T> t;

    GetListCommand(Class<T> type) {
      this.type = type;
    }

    @Override
    public void execute (Session session){
        Criteria crit = session.createCriteria(type);
        crit.setCacheable(true);
        this.t = crit.list();
    }

    public List<T> getListEntity() {
        return this.t;
    }

}
