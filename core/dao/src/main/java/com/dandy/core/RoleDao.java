package com.dandy.core;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import java.util.List;
import java.util.ArrayList;
import com.dandy.infra.HibernateUtil;
import org.hibernate.stat.Statistics;

class RoleDao {

    CommandInvoker commandInvoker;
    GetListCommand getListCommand;

    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<Role>();
        getListCommand = new GetListCommand(Role.class);
        commandInvoker = new CommandInvoker(getListCommand);
        execute(commandInvoker);
        roles = getListCommand.getListEntity();
        return roles;
    }

    void execute(CommandInvoker command) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session sess = HibernateUtil.getSessionFactory().openSession();
        Statistics stats = sessionFactory.getStatistics();
        stats.setStatisticsEnabled(true);
        try {
            sess.beginTransaction();
            command.invoke(sess);
            sess.getTransaction().commit();
        } catch (HibernateException e) {
            sess.getTransaction().rollback();
        }
        System.out.println("Level 2 cache hits: " + stats.getSecondLevelCacheHitCount());
    }

}
