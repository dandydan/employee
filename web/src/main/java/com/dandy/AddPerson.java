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

@WebServlet("/addPerson")
public class AddPerson extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                      throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>Servlet Activity</title></head><body>");
        out.println("<h1 align=\"center\">Servlet Activity</h1>");
        out.println("<table>");
        out.println("<form name=\"editForm\" method=\"post\" action=\"edit\">");
        out.println("<tr><tr><td>Title: </td><td><input type=\"text\" name=\"firstname\" value=\"\"/></td></tr>");
        out.println("<tr><td>Firstname: </td><td><input type=\"text\" name=\"firstname\" value=\"\"/></td></tr>");
        out.println("<tr><td>Middlename: </td><td><input type=\"text\" name=\"middlename\" value=\"\"/></td></tr>");
        out.println("<tr><td>Lastname: </td><td><input type=\"text\" name=\"lastname\" value=\"\"/></td></tr>");
        out.println("<tr><td>Suffix: </td><td><input type=\"text\" name=\"suffix\" value=\"\"/></td></tr>");
        out.println("<tr><td>Birthday: </td><td><input type=\"text\" name=\"birthday\" value=\"\"/></td></tr>");
        out.println("<tr><td>Employed: </td><td><input type=\"radio\" name=\"employed\" value=\"true\" />True");
        out.println("<input type=\"radio\" name=\"employed\" value=\"false\" />False</td></tr>");
        out.println("<tr><td>GWA: </td><td><input type=\"text\" name=\"gwa\" value=\"\"/></td></tr>");
        out.println("<tr><td>Gender: </td><td><input type=\"radio\" name=\"gender\" value=\""+Gender.Male+"\" />Male");
        out.println("<input type=\"radio\" name=\"gender\" value=\""+Gender.Female+"\" />Female");
        out.println("<input type=\"radio\" name=\"gender\" value=\""+Gender.Undecided+"\" />Undecided</td></tr>");
        out.println("<tr><td></td><td><input type=\"submit\" value=\"Add\"/></td></tr></form>");
        out.println("</table");
        out.println("</body></html>");
    }
}
