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
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><meta http-equiv='Content-Type'" + 
                    "content='text/html; charset=UTF-8'>");
        out.println("<title>Servlet Activity</title></head><body>");
        out.println("<h1 align=\"center\">Servlet Activity</h1>");
        out.println("<table>");
        out.println("<form name=\"saveForm\" method=\"post\" action=\"addPerson\">");
        out.println("<tr><td>Title: </td>");
        out.println("<td><input type=\"text\" name=\"title\" " +
                    "value=\""+valueCheck(request.getParameter("title"))+"\"/>");
        out.println("</td></tr>");
        out.println("<tr><td>Firstname: </td>");
        out.println("<td><input type=\"text\" name=\"firstname\" " + 
                    "value=\""+valueCheck(request.getParameter("firstname"))+"\"/>");
        out.println("</td></tr>");
        out.println("<tr><td>Middlename: </td>");
        out.println("<td><input type=\"text\" name=\"middlename\" " +
                    "value=\""+valueCheck(request.getParameter("middlename"))+"\"/></td></tr>");
        out.println("<tr><td>Lastname: </td>");
        out.println("<td><input type=\"text\" name=\"lastname\" " + 
                    "value=\""+valueCheck(request.getParameter("lastname"))+"\"/></td></tr>");
        out.println("<tr><td>Suffix: </td><td>");
        out.println("<input type=\"text\" name=\"suffix\" " +
                    "value=\""+valueCheck(request.getParameter("suffix"))+"\"/></td></tr>");
        out.println("<tr><td>Birthday: </td>");
        out.println("<td><input type=\"text\" name=\"birthday\" " +
                    "value=\""+valueCheck(request.getParameter("birthday"))+"\" maxlength=\"10\" placeholder=\"MM-DD-YYYY\"/></td></tr>");
        out.println("<tr><td>Employed: </td>");
        out.println("<td><input type=\"radio\" name=\"employed\" " +
                    "value=\"true\" checked=\"checked\"/>True");
        out.println("<input type=\"radio\" name=\"employed\" " +
                    "value=\"false\" />False</td></tr>");
        out.println("<tr><td>GWA: </td>");
        out.println("<td><input type=\"text\" name=\"gwa\" " +
                    "value=\""+valueCheck(request.getParameter("gwa"))+"\"/></td></tr>");
        out.println("<tr><td>Gender: </td>");
        out.println("<td><input type=\"radio\" name=\"gender\" " +
                    "value=\"0\" checked=\"checked\"/>Male");
        out.println("<input type=\"radio\" name=\"gender\" " +
                    "value=\"1\" />Female");
        out.println("<input type=\"radio\" name=\"gender\" " +
                    "value=\"2\" />Undecided</td></tr>");
        out.println("<tr><td>Street No.: </td>");
        out.println("<td><input type=\"text\" name=\"stNo\" " +
                    "value=\""+valueCheck(request.getParameter("stNo"))+"\"/></td></tr>");
        out.println("<tr><td>Brgy: </td>");
        out.println("<td><input type=\"text\" name=\"brgy\" " +
                    "value=\""+valueCheck(request.getParameter("brgy"))+"\"/></td></tr>");
        out.println("<tr><td>Subdivision: </td>");
        out.println("<td><input type=\"text\" name=\"subdivision\" " +
                    "value=\""+valueCheck(request.getParameter("subdivision"))+"\"/></td></tr>");
        out.println("<tr><td>City: </td>");
        out.println("<td><input type=\"text\" name=\"city\" " +
                    "value=\""+valueCheck(request.getParameter("city"))+"\"/></td></tr>");
        out.println("<tr><td>Zipcode: </td>");
        out.println("<td><input type=\"text\" name=\"zipcode\" " +
                    "value=\""+valueCheck(request.getParameter("zipcode"))+"\"/></td></tr>");
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
        System.out.println("DDDDDDDDDDDDD" + request.getParameter("personId"));
       /* InputValidator inputValidator = new InputValidator();
        String title = (String) request.getParameter("title");
        String firstname = (String) request.getParameter("firstname");
        String middlename = (String) request.getParameter("middlename");
        String lastname = (String) request.getParameter("lastname");
        String suffix = (String) request.getParameter("suffix");
        String birthday = (String) request.getParameter("birthday");
        Boolean employed = Boolean.valueOf(request.getParameter("employed"));
        Float gwa = Float.valueOf(request.getParameter("gwa"));
        Integer gender = Integer.valueOf(request.getParameter("gender"));
        String stNo = (String) request.getParameter("stNo");
        String brgy = (String) request.getParameter("brgy");
        String subdivision = (String) request.getParameter("subdivision");
        String city = (String) request.getParameter("city");
        String zipcode = (String) request.getParameter("zipcode");
        




     /*   PersonService personService = new PersonService();
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
        System.out.println(person.getBirthday());*/
      //  doGet(request, response);
        //personService.addPerson(person);
        
        //response.sendRedirect("/addPerson");
    }
    
    public String valueCheck(String value) {
        if(value!=null) {
            return value;
        } else {
            return "";
        }
    }
}
