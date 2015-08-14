package com.dandy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import com.dandy.core.Gender;
import com.dandy.core.Person;
import com.dandy.core.Address;
import com.dandy.core.PersonService;


@WebServlet("/addPerson")
public class AddPerson extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                      throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Display display = new Display();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><meta http-equiv='Content-Type'" + 
                    "content='text/html; charset=UTF-8'>");
        request.setAttribute("action" , "Save");
        if(request.getAttribute("flags") != null) {
            boolean flags = (Boolean) request.getAttribute("flags");
            if(!flags) {
                out.println("<script> alert(\"Not Save\");</script>");
            }
        }
        out.println("<title>Servlet Activity</title></head><body>");
        out.println("<h1 align=\"center\">Servlet Activity</h1>");
        out.println("<table>");
        out.println("<form name=\"saveForm\" method=\"post\" action=\"addPerson\">");
        display.paramInputs(request, out);
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                      HttpServletResponse response)
                      throws ServletException, IOException {
        boolean inputIsValid = true;
        InputValidator inputValidator = new InputValidator();
        PersonService personService = new PersonService();
        Person person = new Person();        
        Address address = new Address();
        person.setAddress(address);
        address.setPerson(person);
        boolean flags = true;
        person = inputValidator.personCheck(request,person);       
        if (person.getFirstName().trim().length() == 0 ||
            person.getMiddleName().trim().length() == 0 ||
            person.getLastName().trim().length() == 0 ||
            person.getBirthday() == null) {
                    request.setAttribute("person", person); 
                    doGet(request, response);
        }else{

        flags = true;//personService.addPerson(person);
        if (!flags) {
        request.setAttribute("flags", flags);
            doGet(request, response);
        } else {
        response.sendRedirect("/home");
        }
        }

    }
    
}
