package com.dandy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dandy.core.Gender;
import com.dandy.core.Person;
import com.dandy.core.PersonService;

public class EditPersonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                      throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("/home");
        }
        PrintWriter out = response.getWriter();
        Display display = new Display();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><meta http-equiv='Content-Type'" + 
                    "content='text/html; charset=UTF-8'>");
        request.setAttribute("action" , "Update");
        if(session.getAttribute("flags") != null) {
            boolean flags = (Boolean) session.getAttribute("flags");
            if(!flags) {
                out.println("<script> alert(\"Can't Update\");</script>");
            }
        }
        out.println("<title>Servlet Activity</title></head><body>");
        out.println("<h1 align=\"center\">Servlet Activity</h1>");
        out.println("<form name=\"editForm\" method=\"post\" action=\"editPerson\">");
        out.println("<table align='center'>");
        out.println("<input type=\"hidden\" name=\"fromGet\" value=\"true\">");
        out.println("<input type=\"hidden\" name=\"personId\""+
                    "value=\""+session.getAttribute("personId")+"\">");
        display.paramInputs(request, out, session);
        
        if(session!=null) {
            session.invalidate();
        }

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                      HttpServletResponse response)
                      throws ServletException, IOException {
        PersonService personService = new PersonService();
        HttpSession session = request.getSession(true);
        if(request.getParameter("fromGet") == null) {
            Person person = personService.getPersonById(Integer.valueOf(request.getParameter("personId")));
            session.setAttribute("person", person);
            session.setAttribute("personId", Integer.valueOf(request.getParameter("personId")));
            response.sendRedirect("/editPerson");
        } else {
            InputValidator inputValidator = new InputValidator();
            Person person = personService.getPersonById(Integer.valueOf(request.getParameter("personId")));
            boolean flags = true;
            person = inputValidator.personCheck(request,person);
            session.setAttribute("personId", Integer.valueOf(request.getParameter("personId")));
            session.setAttribute("person", person); 
            if (person.getFirstName().trim().length() == 0 ||
                person.getMiddleName().trim().length() == 0 ||
                person.getLastName().trim().length() == 0 ||
                person.getBirthday() == null || person.getGwa() == 0) {
                session.setAttribute("flags", false);
                response.sendRedirect("/editPerson");
            }else{
                flags = personService.updatePerson(person);
                if (!flags) {
                    session.setAttribute("flags", flags);
                    response.sendRedirect("/editPerson");
                } else {
                    response.sendRedirect("/home");
                }
            }
        }
    }
}
