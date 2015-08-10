package com.dandy.core;

import java.util.List;
import java.util.Date;
import java.util.Comparator;

public class PersonDTO implements Comparator<PersonDTO>{

    private int personId;
    private String firstName;
    private String lastName;
    private float gwa;
    private int zipcode;
    private Date birthday;
    private Long number;
    private String pos;
    
    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGwa(float gwa) {
        this.gwa = gwa;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
    
    public int getPersonId() {
        return this.personId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public float getGwa() {
        return this.gwa;
    }

    public int getZipcode() {
        return this.zipcode;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public Long getNumber() {
        return this.number;
    }

    public String getPos() {
        return this.pos;
    }

    @Override
    public int compare(PersonDTO person1, PersonDTO person2) {
	    return Float.compare(person1.getGwa(), person2.getGwa());
    }
}
