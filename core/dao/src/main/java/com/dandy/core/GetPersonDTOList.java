package com.dandy.core;

import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import java.util.List;
import java.util.ArrayList;

public class GetPersonDTOList implements DbCommand {

    List<PersonDTO> personDTOs = new ArrayList<PersonDTO>();
    String field = "";
    String searchText = "";
    String order = "";

    GetPersonDTOList(String field, String searchText, String order) {
        this.field = field;
        this.searchText = searchText;
        this.order = order;
    }

    @Override
    public void execute (Session session){
        Criteria crit = session.createCriteria(Person.class, "person");
        crit.createAlias("contacts", "contacts", Criteria.LEFT_JOIN);
        crit.createAlias("roles", "roles", Criteria.LEFT_JOIN);
        crit.createAlias("address", "address");
        crit.setProjection(Projections.distinct(Projections.projectionList()
                           .add( Projections.property("person.personId"), "personId")
                           .add( Projections.property("person.firstName"), "firstName")
                           .add( Projections.property("person.lastName"), "lastName")
                           .add( Projections.property("person.gwa"), "gwa")
                           .add( Projections.property("address.zipcode"), "zipcode")
                           .add( Projections.property("person.birthday"), "birthday")
                           .add( Projections.property("contacts.number"), "number")
                           .add( Projections.property("roles.pos"),"pos")));
        crit.setResultTransformer(Transformers.aliasToBean(PersonDTO.class));
        if(field != "") {
            crit.add(Restrictions.ilike(field, searchText, MatchMode.ANYWHERE));
        }
        if(order != "") {
            crit.addOrder(Order.asc(order));
        }
        this.personDTOs = crit.list();
    }

    public List<PersonDTO> getDTOList() {
        return this.personDTOs;
    }

}
