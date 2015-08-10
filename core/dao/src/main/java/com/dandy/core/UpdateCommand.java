package com.dandy.core;

import org.hibernate.Session;

public class UpdateCommand<T> implements DbCommand {

    T t;

    UpdateCommand(T t) {
      this.t = t;
    }

    @Override
    public void execute (Session session){
        session.update(t);
    }
}
