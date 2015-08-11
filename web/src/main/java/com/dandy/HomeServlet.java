package com.dandy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                      throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Display display = new Display();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>Servlet Activity</title></head><body>");
        out.println("<h1 align=\"center\">Servlet Activity</h1>");
        out.println("<a href=\"http://localhost:8080/addPerson\"> Add Person</a>");
        out.println("<table><tr><th>ID</th><th>Firstname</th><th>Lastname</th>");
        out.println("<th>GWA</th><th>Birthday</th><th>zipcode</th><th>Number</th><th>Role</th></tr>");
        display.printPerson(out, "", "", "personId");
        out.println("</body></html>");
    }
}
