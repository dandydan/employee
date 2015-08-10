package com.dandy;

import com.dandy.core.PersonService;
import com.dandy.core.Person;
import com.dandy.core.Address;
import com.dandy.core.Contact;
import com.dandy.core.Role;
import com.dandy.core.PersonDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Home")
public class HomeServlet extends HttpServlet {
    private PersonService personService;
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                      throws ServletException, IOException {
        response.setContentType("text/html");
        personService = new PersonService();
        List<Person> persons = personService.getPersonList();

        PrintWriter out = response.getWriter();
        String title ="Servlet Activity";
        String docType =
            "<!doctype html public \"-//w3c//dtd html 4.0 "+
            "transitional//en\">\n";
        out.println(docType +
        "<html>\n"+
        "<head><title>"+ title +"</title></head>\n");
        out.println("<body>\n"+
        "<h1 align=\"center\">"+ title +"</h1>\n"+
        "<table><tr><th>Firstname</th><th>Lastname</th></tr>");
        for(Person person:persons) {
           out.println(" <tr><td>"+ person.getFirstName()+"</td> " +
           " <td>"+person.getLastName()+"</td></tr> ");
        }
        out.println("</table>\n"+
        "</body></html>");
    }
}
