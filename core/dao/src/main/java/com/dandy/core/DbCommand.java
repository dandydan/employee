package com.dandy.core;

import org.hibernate.Session;

public interface DbCommand {
    public void execute (Session session);
}
