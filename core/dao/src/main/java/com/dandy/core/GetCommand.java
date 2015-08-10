package com.dandy.core;

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
        this.t = (T) session.get(type, id);
    }

    public T getEntity() {
        return this.t;
    }

}
