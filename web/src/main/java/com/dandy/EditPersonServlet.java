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
import com.dandy.core.PersonService;


@WebServlet("/editPerson")
public class EditPersonServlet extends HttpServlet {

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
        out.println("<title>Servlet Activity</title></head><body>");
        out.println("<h1 align=\"center\">Servlet Activity</h1>");
        out.println("<table>");
        out.println("<form name=\"editForm\" method=\"post\" action=\"editPerson\">");
        display.paramInputs(request, out);
        out.println("<input type=\"hidden\" name=\"fromGet\" value=\"true\">");
        out.println("<input type=\"hidden\" name=\"personId\" value=\""+request.getAttribute("personId")+"\">");
        out.println("<tr><td><input type=\"submit\" " +
                    "value=\"Save\"/></td>");
        out.println("</form>");
        out.println("<td><button onclick=\"location.href = " +
                    "'http://localhost:8080/home';\">Home</button></td></tr>");
        out.println("</table");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                      HttpServletResponse response)
                      throws ServletException, IOException {
        PersonService personService = new PersonService();
        if(request.getParameter("fromGet") == null) {
            Person person = personService.getPersonById(Integer.valueOf(request.getParameter("personId")));
            request.setAttribute("person", person);
            request.setAttribute("personId", Integer.valueOf(request.getParameter("personId")));
            doGet(request, response);
        } else {

            InputValidator inputValidator = new InputValidator();
            Person person = personService.getPersonById(Integer.valueOf(request.getParameter("personId")));
            boolean flags = true;
            person = inputValidator.personCheck(request,person);
            request.setAttribute("person", person); 
            if (person.getFirstName().trim().length() == 0 ||
                person.getMiddleName().trim().length() == 0 ||
                person.getLastName().trim().length() == 0 ||
                person.getBirthday() == null) {
                doGet(request, response);
            }else{

                flags = personService.updatePerson(person);
                if (!flags) {
                    request.setAttribute("flags", flags);
                    doGet(request, response);
                } else {
                    response.sendRedirect("/home");
                }
            }
        }
    }
}
