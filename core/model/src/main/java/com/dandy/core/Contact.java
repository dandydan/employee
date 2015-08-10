package com.dandy.core;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @Column(name = "contactId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;

    @Column(name = "description")
    private String description;

    @Column(name = "number")
    private long number;

    public Contact() {}

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setNumber(long number) {
        this.number = number;
    }
    public int getContactId() {
        return this.contactId;
    }
    public String getDescription() {
        return this.description;
    }
    public long getNumber() {
        return this.number;
    }

    public boolean equals(Object obj) {
      if (obj == null) return false;
      if (!this.getClass().equals(obj.getClass())) return false;

      Contact obj2 = (Contact)obj;
      if((this.number == obj2.getNumber()) && (this.description.equals(obj2.getDescription())))
      {
         return true;
      }
      return false;
   }

   public int hashCode() {
      int tmp = 0;
      tmp = ( number + description ).hashCode();
      return tmp;
   }
}
