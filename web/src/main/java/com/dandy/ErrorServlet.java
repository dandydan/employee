package com.dandy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorServlet extends HttpServlet {
 
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException{
      Throwable throwable = (Throwable)
      request.getAttribute("javax.servlet.error.exception");
      Integer statusCode = (Integer)
      request.getAttribute("javax.servlet.error.status_code");
      String servletName = (String)
      request.getAttribute("javax.servlet.error.servlet_name");
      if (servletName == null){
         servletName = "Unknown";
      }
      String requestUri = (String)
      request.getAttribute("javax.servlet.error.request_uri");
      if (requestUri == null){
         requestUri = "Unknown";
      }

      response.setContentType("text/html");
 
      PrintWriter out = response.getWriter();
      out.write("<html><head><title>Exception/Error Details</title></head><body>");
      if(statusCode == 404) {
        out.write("<h3>Oops! Page not Found!</h3>");
        out.write("<strong>At Requested URI</strong>:"+requestUri+"</br>");
        out.println("Please return to the <a href ='http://localhost:8080/home'>Home Page</a>.");
      }else if(statusCode != 500){
        out.write("<h3>Error Details</h3>");
        out.write("<strong>Status Code</strong>:"+statusCode+"<br>");
        out.write("<strong>Requested URI</strong>:"+requestUri);
      }else{
        out.write("<h3>Exception Details</h3>");
        out.write("<ul><li>Servlet Name:"+servletName+"</li>");
        out.write("<li>Exception Name:"+throwable.getClass().getName()+"</li>");
        out.write("<li>Requested URI:"+requestUri+"</li>");
        out.write("<li>Exception Message:"+throwable.getMessage()+"</li>");
        out.write("</ul>");
      }
      out.println("</body>");
      out.println("</html>");
  }

  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
      doGet(request, response);
  }
}
