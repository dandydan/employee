package com.dandy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import com.dandy.core.PersonService;
import com.dandy.core.Person;

@WebServlet("/editPerson")
public class EditPerson extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                      throws ServletException, IOException {
        response.setContentType("text/html");
        PersonService personService = new PersonService();
        Person person = personService.getPersonById(1);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>Servlet Activity</title></head><body>");
        out.println("<h1 align=\"center\">Servlet Activity</h1>");
        out.println("<table>");
        out.println("<form name=\"editForm\" method=\"post\" action=\"edit\">");
        out.println("<tr><td>Title: </td><td><input type=\"text\" name=\"firstname\" value=\"" 
                    + person.getTitle() + "\"/></td>");
        out.println("<td>Firstname: </td><td><input type=\"text\" name=\"firstname\" value=\"" 
                    + person.getFirstName() + "\"/></td>");
        out.println("<td>Firstname: </td><td><input type=\"text\" name=\"middlename\" value=\"" 
                    + person.getMiddleName() + "\"/></td>");
        out.println("<td>Firstname: </td><td><input type=\"text\" name=\"lastname\" value=\"" 
                    + person.getLastName() + "\"/></td>");
        out.println("<td>Firstname: </td><td><input type=\"text\" name=\"suffix\" value=\"" 
                    + person.getSuffix() + "\"/></td>");
        out.println("<td>Firstname: </td><td><input type=\"text\" name=\"birthday\" value=\"" 
                    + person.getBirthday() + "\"/></td>");
        out.println("<td>Firstname: </td><td><input type=\"text\" name=\"employed\" value=\"" 
                    + person.getEmployed() + "\"/></td>");
        out.println("<td>Firstname: </td><td><input type=\"text\" name=\"gwa\" value=\"" 
                    + person.getGwa() + "\"/></td>");
        out.println("<td>Firstname: </td><td><input type=\"text\" name=\"gender\" value=\"" 
                    + person.getGender() + "\"/></td>");
        out.println("<td></td><td><input type=\"submit\" value=\"Update\"/></td></tr></form>");
        out.println("</table");
        out.println("</body></html>");
    }
}
