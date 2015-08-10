package com.dandy.core;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class IdGenerator implements IdentifierGenerator {
    
    @Override
    public Serializable generate(SessionImplementor sessionImpl, Object data)
            throws HibernateException {
        Serializable result = null;
        Connection connection = sessionImpl.connection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = connection.prepareStatement("select max(personId) from person");                  
                 resultSet = ps.executeQuery();
            
            if(resultSet.next()) {
                int nextValue = resultSet.getInt(1);                
                int generated = nextValue + 1;               
                result = generated;
                System.out.println("Custom generated Sequence value : "+result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

