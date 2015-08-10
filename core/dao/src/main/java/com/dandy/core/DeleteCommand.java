package com.dandy.core;

import org.hibernate.Session;

public class DeleteCommand<T> implements DbCommand {

    T t;

    DeleteCommand(T t) {
      this.t = t;
    }

    @Override
    public void execute (Session session){
        session.delete(t);
    }

}
