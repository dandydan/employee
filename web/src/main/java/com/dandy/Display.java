package com.dandy;

import java.io.PrintWriter;

import com.dandy.core.PersonService;
import com.dandy.core.Person;
import com.dandy.core.Address;
import com.dandy.core.Contact;
import com.dandy.core.Role;
import com.dandy.core.PersonDTO;
import com.dandy.core.Gender;

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
import java.util.Date;

public class Display {

    public void paramInputs(HttpServletRequest request, PrintWriter out) {
        Person person = (Person) request.getAttribute("person");
        String male = "";
        String female = "";
        String undecided = "";
        String president = "";
        String manager = "";
        String leader = "";
        String senior = "";
        String junior = "";
        String employed = "";
        String unemployed = "";

        if(person == null) {
            person = new Person();
            Address address = new Address();
            Set<Role> roles = new HashSet<Role>();
            Set<Contact> contacts = new HashSet<Contact>();
            person.setContacts(contacts);
            person.setRoles(roles);
            person.setEmployed(true);
            person.setAddress(address);
        }

        if(person.getGender() == Gender.Undecided) {
            undecided = "Checked";
        } else if(person.getGender() == Gender.Female) {
            female = "Checked";
        } else {
            male = "Checked";
        }

        for(Role role : person.getRoles()){
            if(role.getPos().equals("President")){
                    president = "checked";
            }else if (role.getPos().equals("Manager")){
                    manager = "checked";
            }else if (role.getPos().equals("Team Leader")){
                    leader = "checked";
            }else if (role.getPos().equals("Senior Developer")){
                    senior = "checked";
            }else if (role.getPos().equals("Junior Developer")){
                    junior = "checked";
            }
        }

        if(person.getEmployed()){
            employed = "checked";
        }else{
            unemployed = "checked";
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
                    "value=\""+dateCheck(person.getBirthday())+"\" "
                    + "maxlength=\"10\" placeholder=\"MM-DD-YYYY\"/></td></tr>");
        out.println("<tr><td>Employed: </td>");
        out.println("<td><input type=\"radio\" name=\"employed\" " +
                    "value=\"true\" " + employed + "/>True");
        out.println("<input type=\"radio\" name=\"employed\" " +
                    "value=\"false\"" + unemployed + " />False</td></tr>");
        out.println("<tr><td>GWA: </td>");
        out.println("<td><input type=\"text\" name=\"gwa\" " +
                    "value=\""+floatDisplay(person.getGwa())+"\"/></td></tr>");
        out.println("<tr><td>Gender: </td>");
        out.println("<td><input type=\"radio\" name=\"gender\" " +
                    "value=\"0\"" + male + "/>Male");
        out.println("<input type=\"radio\" name=\"gender\" " +
                    "value=\"1\"" + female + " />Female");
        out.println("<input type=\"radio\" name=\"gender\" " +
                    "value=\"2\"" + undecided + " />Undecided</td></tr>");
        out.println("<tr><td>Street No.: </td>");
        out.println("<td><input type=\"text\" name=\"stNo\" " +
                    "value=\""+intDisplay(person.getAddress().getStNo())+"\"/></td></tr>");
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
                    "value=\""+intDisplay(person.getAddress().getZipcode())+"\"/></td></tr>");

        out.println("<tr><td>Role:</td>");
        out.println("<td><input type=\"checkbox\" name=\"role\" value=\"0\" "+ 
                    president + "/> President</br>");
        out.println("<input type=\"checkbox\" name=\"role\" value=\"1\"" + 
                    manager + "/> Manager</br>");
        out.println("<input type=\"checkbox\" name=\"role\" value=\"2\"" + 
                    leader + " /> Team Leader</br>");
        out.println("<input type=\"checkbox\" name=\"role\" value=\"3\"" + 
                    senior + " /> Senior Developer</br>");
        out.println("<input type=\"checkbox\" name=\"role\" value=\"4\"" + 
                    junior + " /> Junior Developer");
        out.println("</td></tr>");
        out.println("</table>");
        out.println("<table align='center' id=\"contactTable\"><tr>" +
                    "<td>Contact Type</td><td>Contact Number</td><td></td></tr>");
        for(Contact contact: person.getContacts()){
            String tel = "";
            String cel = "";
            if(contact.getDescription().equals("Telephone")){
                tel = "selected";
            } else {
                cel = "selected";
            }
            out.println("<tr><td><select name='ctype'><option "+ 
                        tel + ">Telephone</option><option " + cel + ">"+
                        "Cellphone</option></select></td><td><input type='text'"+
                        " name='number' value='"+contact.getNumber()+"'/></td>"+
                        "<td><button onclick='deleteRow(this)'>Delete</button></td></tr>");
        }
        out.println("</table>");
        out.println("<div align='center'><input style='height:20px; width:100px' type=\"submit\" " +
                    "value=\""+request.getAttribute("action")+"\"/></div>");
        out.println("</form>");
        out.println("<div align='center'> <button style='height:20px; width:100px' "+
                    "onclick=\"location.href ='http://localhost:8080/home';\">Home</button>");
        out.println("<button style='height:20px; width:100px' onclick=\"myFunction()\">"+
                    "Add Contact</button></div>");
        out.println("<script>function myFunction() {var table = "+
                    "document.getElementById(\"contactTable\");");
        out.println("var row = table.insertRow(-1);var cType = row.insertCell(0);"+
                    "var cNum = row.insertCell(1); var cAct = row.insertCell(2);");
        out.println("cType.innerHTML =\"<select name='ctype'><option>Telephone</option>"+
                    "<option>Cellphone</option></select>\";");
        out.println("cNum.innerHTML=\"<input type=\\\"text\\\" name='number' value=''/>\";"+
                    " cAct.innerHTML =\"<button onclick=\\\"deleteRow(this)\\\">Delete</button>\" }");
        out.println("function deleteRow(btn) {var row = btn.parentNode.parentNode;");
        out.println("row.parentNode.removeChild(row);}</script>");
    }

    public void printPerson(PrintWriter out, String field, String searchText, String order, String gwa) {
        PersonService personService;
        InputValidator inputValidator = new InputValidator();
        personService = new PersonService();
        List<PersonDTO> result = personService.getPersons(field, searchText, order);
        if(gwa.equals("gwa")) {
            Collections.sort(result, new PersonDTO());
        }
        Set<String> numbers = new HashSet<String>();
        Set<String> roles = new HashSet<String>();

        out.println("<table align='center' id='t01'><tr><th>ID</th>"+
                    "<th>Firstname</th><th>Lastname</th>");
        out.println("<th>GWA</th><th>Birthday</th><th>zipcode</th>"+
                    "<th>Number</th><th>Role</th><th>Action</th></tr>");
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
            out.println("</td><td>");
            out.println("<form name=\"editForm\" method=\"post\" action=\"editPerson\">");
            out.println("<input type=\"hidden\" name=\"personId\" value=\""+result.get(i).getPersonId()+"\">");
            out.println("<button type=\"submit\" style='height:20px; width:100px'/>Update</button></form>");
            out.println("<form name=\"deleteForm\" method=\"post\" action=\"deletePerson\">");
            out.println("<input type=\"hidden\" name=\"personId\" value=\""+result.get(i).getPersonId()+"\">");
            out.println("<button type=\"submit\"style='height:20px; width:100px'>Delete</button></form></td>");
            out.println("</tr>");
            numbers.clear();
            roles.clear();
            }
        }
        out.println("</table>");
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

    public String dateCheck(Date date) {
        InputValidator inputValidator = new InputValidator();
        if(date!=null) {
            return inputValidator.dateDisplay(date);
        } else {
            return "";
        }
    }

    public String intDisplay(int number) {
        if(number == 0) {
            return "";
        } else {
            return ""+number+"";
        }
    }

    public String floatDisplay(float floatNumber) {
        if(floatNumber == 0.0) {
            return "";
        } else {
            return ""+floatNumber+"";
        }
    }
}
