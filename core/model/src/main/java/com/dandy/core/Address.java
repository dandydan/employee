package com.dandy.core;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "address")
public class Address {
    @GenericGenerator(name = "generator", strategy = "foreign", 
	              parameters = @Parameter(name = "property", value = "person"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "personId")
    private int personId;

    @Column(name = "stNo")
    private int stNo;

    @Column(name = "brgy")
    private String brgy;

    @Column(name = "subdivision")
    private String subdivision;

    @Column(name = "city")
    private String city;

    @Column(name = "zipcode")
    private int zipcode;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Person person;

    public Address() {}

    public void setPersonId(int personId) {
        this.personId = personId;
    }
    public void setStNo(int stNo) {
        this.stNo = stNo;
    }
    public void setBrgy(String brgy) {
        this.brgy = brgy;
    }
    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    public int getPersonId() {
        return this.personId;
    }
    public int getStNo() {
        return this.stNo;
    }
    public String getBrgy() {
        return this.brgy;
    }
    public String getSubdivision() {
        return this.subdivision;
    }
    public String getCity() {
        return this.city;
    }
    public int getZipcode() {
        return this.zipcode;
    }
    public Person getPerson() {
        return this.person;
    }
}
