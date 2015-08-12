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
        out.println("<title>Servlet Activity</title></head><body>");
        out.println("<h1 align=\"center\">Servlet Activity</h1>");
        out.println("<table>");
        out.println("<form name=\"saveForm\" method=\"post\" action=\"addPerson\">");
        display.paramInputs(request, out);
        out.println("<tr><td><input type=\"submit\" " +
                    "value=\"Save\"/></td>");
        out.println("</form>");
        out.println("<td><button onclick=\"location.href = " +
                    "'http://localhost:8080/';\">Home</button></td></tr>");
        out.println("</table");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                      HttpServletResponse response)
                      throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        InputValidator inputValidator = new InputValidator();
        String title = (String) request.getParameter("title");
        String firstname = (String) request.getParameter("firstname");
        String middlename = (String) request.getParameter("middlename");
        String lastname = (String) request.getParameter("lastname");
        String suffix = (String) request.getParameter("suffix");
        String birthday = (String) request.getParameter("birthday");
        Boolean employed = Boolean.valueOf(request.getParameter("employed"));
        Float gwa = Float.valueOf(request.getParameter("gwa"));
        Integer gender = Integer.valueOf(request.getParameter("gender"));
        Integer stNo = Integer.valueOf(request.getParameter("stNo"));
        String brgy = (String) request.getParameter("brgy");
        String subdivision = (String) request.getParameter("subdivision");
        String city = (String) request.getParameter("city");
        Integer zipcode = Integer.valueOf(request.getParameter("zipcode"));
        

        PersonService personService = new PersonService();
        Person person = new Person();        
        person.setTitle(title);
        person.setFirstName(firstname);
        person.setMiddleName(middlename);
        person.setLastName(lastname);
        person.setSuffix(suffix);
        person.setBirthday(inputValidator.dateFormatter(birthday));
	    person.setEmployed(employed);
	    person.setGwa(gwa);
        person.setGender(inputValidator.genderProcess(gender));

        Address address = new Address();

        address.setStNo(stNo);
        address.setBrgy(brgy);
        address.setSubdivision(subdivision);
        address.setCity(city); 
        address.setZipcode(zipcode);

        person.setAddress(address);
        address.setPerson(person);
        personService.addPerson(person);
        request.setAttribute("person", person);
        //doGet(request, response);
        
        response.sendRedirect("/");
    }
    
}
