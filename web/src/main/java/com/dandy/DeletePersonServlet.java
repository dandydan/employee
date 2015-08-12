package com.dandy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import com.dandy.core.Person;
import com.dandy.core.PersonService;


@WebServlet("/deletePerson")
public class DeletePersonServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                      HttpServletResponse response)
                      throws ServletException, IOException {
        PersonService personService = new PersonService();
        Person person = personService.getPersonById(Integer.valueOf(request.getParameter("personId")));
        personService.removePerson(person);
        response.sendRedirect("/");
    }
}
