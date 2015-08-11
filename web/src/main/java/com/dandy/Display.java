package com.dandy;

import java.io.PrintWriter;

import com.dandy.core.PersonService;
import com.dandy.core.Person;
import com.dandy.core.Address;
import com.dandy.core.Contact;
import com.dandy.core.Role;
import com.dandy.core.PersonDTO;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class Display {


    public void printPerson(PrintWriter out, String field, String searchText, String order) {
    PersonService personService;
        InputValidator inputValidator = new InputValidator();
        personService = new PersonService();

 /*       switch(conditionVar) {
            case 9:
                System.out.print("Enter a string: ");
                searchText = inputValidator.simpleString();
                field = "lastName";
                break;
            case 10:
                System.out.print("Enter a string: ");
                searchText = inputValidator.simpleString();
                field = "roles.pos";
                break;
            case 11:
                order = "lastName";
                break;
            case 12:
                order = "birthday";
                break;
        }*/
        List<PersonDTO> result = personService.getPersons(field, searchText, order);
       // if(conditionVar==13) {
         //    Collections.sort(result, new PersonDTO());
        //}
        Set<String> numbers = new HashSet<String>();
        Set<String> roles = new HashSet<String>();
        for(int i = 0; i < result.size() ; i++) {
            if(i<(result.size()-1) && (result.get(i).getPersonId() == result.get(i+1).getPersonId())){
                numbers.add(getStringNumber(result.get(i).getNumber()));
                roles.add(getRoles(result.get(i).getPos()));
            } else{
            numbers.add(getStringNumber(result.get(i).getNumber()));
            roles.add(getRoles(result.get(i).getPos()));
            out.println("<tr>");
            out.println("<td>" + result.get(i).getPersonId() + "</td>");
            out.println("<td>" + result.get(i).getFirstName() + "</td>");
            out.println("<td>" + result.get(i).getLastName() + "</td>");
            out.println("<td>" + result.get(i).getGwa() + "</td>");
            out.println("<td>" + inputValidator.dateDisplay(result.get(i).getBirthday()) + "</td>");
            out.println("<td>" + result.get(i).getZipcode() + "</td>");
            out.println("<td>");
            for(String number : numbers) {
                out.print(number + " ");
            }
            out.print("</td><td>");
            for(String role : roles) {
                out.print(role + " ");
            }
            out.println("</td></tr>");
            numbers.clear();
            roles.clear();
            }
        }
    }

    public String getStringNumber (Long number) {
        if (number!= null) {
            return number.toString();
        } else {
        return "";
        }
    }

    public String getRoles (String role) {
        if (role != null) {
            return role;
        } else {
        return "";
        }
    }
}
