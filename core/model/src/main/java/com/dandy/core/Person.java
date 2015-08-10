package com.dandy.core;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.JoinTable;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "personId")
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "com.dandy.core.IdGenerator")
    int personId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "middleName")
    private String middleName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "suffix")
    private String suffix;

    @Column(name = "title")
    private String title;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "employed")
    private boolean employed;

    @Column(name = "gwa")
    private float gwa;

    @Column(name = "gender")
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    @JoinColumn(name = "personId", nullable = false)
    private Set<Contact> contacts;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="personroles", 
                joinColumns={@JoinColumn(name="personId")}, 
                inverseJoinColumns={@JoinColumn(name="roleId")})
    private Set<Role> roles;

    public Person(){}

    public void setPersonId(int personId) {
        this.personId = personId;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public void setEmployed(boolean employed) {
        this.employed = employed;
    }
    public void setGwa(float gwa) {
        this.gwa = gwa;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public int getPersonId() {
        return this.personId;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getMiddleName() {
        return this.middleName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getSuffix() {
        return this.suffix;
    }
    public String getTitle() {
        return this.title;
    }
    public Date getBirthday() {
        return this.birthday;
    }
    public boolean getEmployed() {
        return this.employed;
    }
    public float getGwa() {
        return this.gwa;
    }
    public Gender getGender() {
        return this.gender;
    }
    public Address getAddress() {
        return this.address;
    }
    public Set<Contact> getContacts() {
        return this.contacts;
    }
    public Set<Role> getRoles() {
        return this.roles;
    }
}
