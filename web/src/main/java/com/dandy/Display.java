package com.dandy;

import java.io.PrintWriter;

import com.dandy.core.PersonService;
import com.dandy.core.Person;
import com.dandy.core.Address;
import com.dandy.core.Contact;
import com.dandy.core.Role;
import com.dandy.core.PersonDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class Display {

    public void paramInputs(HttpServletRequest request, PrintWriter out) {
        Person person = (Person) request.getAttribute("person");
        if(person == null) {
            person = new Person();
            Address address = new Address();
            person.setAddress(address);
        }
        out.println("<tr><td>Title: </td>");
        out.println("<td><input type=\"text\" name=\"title\" " +
                    "value=\""+valueCheck(person.getTitle())+"\"/>");
        out.println("</td></tr>");
        out.println("<tr><td>Firstname: </td>");
        out.println("<td><input type=\"text\" name=\"firstname\" " + 
                    "value=\""+valueCheck(person.getFirstName())+"\"/>");
        out.println("</td></tr>");
        out.println("<tr><td>Middlename: </td>");
        out.println("<td><input type=\"text\" name=\"middlename\" " +
                    "value=\""+valueCheck(person.getMiddleName())+"\"/></td></tr>");
        out.println("<tr><td>Lastname: </td>");
        out.println("<td><input type=\"text\" name=\"lastname\" " + 
                    "value=\""+valueCheck(person.getLastName())+"\"/></td></tr>");
        out.println("<tr><td>Suffix: </td><td>");
        out.println("<input type=\"text\" name=\"suffix\" " +
                    "value=\""+valueCheck(person.getSuffix())+"\"/></td></tr>");
        out.println("<tr><td>Birthday: </td>");
        out.println("<td><input type=\"text\" name=\"birthday\" " +
                    "value=\""+person.getBirthday()+"\" maxlength=\"10\" placeholder=\"MM-DD-YYYY\"/></td></tr>");
        out.println("<tr><td>Employed: </td>");
        out.println("<td><input type=\"radio\" name=\"employed\" " +
                    "value=\"true\" checked=\"checked\"/>True");
        out.println("<input type=\"radio\" name=\"employed\" " +
                    "value=\"false\" />False</td></tr>");
        out.println("<tr><td>GWA: </td>");
        out.println("<td><input type=\"text\" name=\"gwa\" " +
                    "value=\""+person.getGwa()+"\"/></td></tr>");
        out.println("<tr><td>Gender: </td>");
        out.println("<td><input type=\"radio\" name=\"gender\" " +
                    "value=\"0\" checked=\"checked\"/>Male");
        out.println("<input type=\"radio\" name=\"gender\" " +
                    "value=\"1\" />Female");
        out.println("<input type=\"radio\" name=\"gender\" " +
                    "value=\"2\" />Undecided</td></tr>");
        out.println("<tr><td>Street No.: </td>");
        out.println("<td><input type=\"text\" name=\"stNo\" " +
                    "value=\""+person.getAddress().getStNo()+"\"/></td></tr>");
        out.println("<tr><td>Brgy: </td>");
        out.println("<td><input type=\"text\" name=\"brgy\" " +
                    "value=\""+valueCheck(person.getAddress().getBrgy())+"\"/></td></tr>");
        out.println("<tr><td>Subdivision: </td>");
        out.println("<td><input type=\"text\" name=\"subdivision\" " +
                    "value=\""+valueCheck(person.getAddress().getSubdivision())+"\"/></td></tr>");
        out.println("<tr><td>City: </td>");
        out.println("<td><input type=\"text\" name=\"city\" " +
                    "value=\""+valueCheck(person.getAddress().getCity())+"\"/></td></tr>");
        out.println("<tr><td>Zipcode: </td>");
        out.println("<td><input type=\"text\" name=\"zipcode\" " +
                    "value=\""+person.getAddress().getZipcode()+"\"/></td></tr>");
    }



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

        out.println("<table><tr><th>ID</th><th>Firstname</th><th>Lastname</th>");
        out.println("<th>GWA</th><th>Birthday</th><th>zipcode</th><th>Number</th><th>Role</th><th colspan=\"2\">Action</th></tr>");
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
                out.print(number + "</br>");
            }
            out.print("</td><td>");
            for(String role : roles) {
                out.print(role + "</br>");
            }
            out.println("</td>");
            out.println("<form name=\"editForm\" method=\"post\" action=\"editPerson\">");
            out.println("<input type=\"hidden\" name=\"personId\" value=\""+result.get(i).getPersonId()+"\">");
            out.println("<td><input type=\"submit\" " +
                        "value=\"Update\"/></td></form>");
            out.println("<form name=\"deleteForm\" method=\"post\" action=\"deletePerson\">");
            out.println("<input type=\"hidden\" name=\"personId\" value=\""+result.get(i).getPersonId()+"\">");
            out.println("<td><input type=\"submit\" " +
                        "value=\"Delete\"/></td></form>");
            out.println("</tr>");
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

    public String valueCheck(String value) {
        if(value!=null) {
            return value;
        } else {
            return "";
        }
    }
}
