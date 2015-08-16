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
        Display display = new Display();
        String field = display.valueCheck(request.getParameter("field"));
        String searchText = display.valueCheck(request.getParameter("searchText"));
        String order = display.valueCheck(request.getParameter("order"));
        String gwa = display.valueCheck(request.getParameter("gwa"));

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>Servlet Activity</title>");
        out.println("<style>table {width:60%;}");
        out.println("table, th, td {border: 1px solid black;");
        out.println("border-collapse: collapse;}");
        out.println("td {padding: 5px;text-align: center;}");
        out.println("th {text-align: center;}");
        out.println("table#t01 tr:nth-child(even) {background-color: #eee;}");
        out.println("table#t01 tr:nth-child(odd) {background-color:#fff;}");
        out.println("table#t01 th{background-color: black;color: white;}");
        out.println("div{margin-bottom: 10px; margin-top: 2px;}");
        out.println("</style></head><body>");
        out.println("<h1 align=\"center\">Servlet Activity</h1>");

        out.println("<div align='center'>");
        out.println("<form name=\"homeForm\" method=\"post\" action=\"home\">");
        out.println("<b>Search:</b><input type=\"text\" name=\"searchText\" value=\"\"/>");
        out.println("<input type=\"hidden\" id='category' name=\"field\" value=\"lastName\"/>");
        out.println("<input  style='height:20px; width:100px' type=\"submit\""+
                    "value=\"Lastname\" oncLick=\"mySearch(this)\"/>");
        out.println("<input  style='height:20px; width:100px' type=\"submit\""+
                    "value=\"Role\" oncLick=\"mySearch(this)\"/>");
        out.println("</form></div>");
        out.println("<div align='center'>");
        out.println("<form name=\"homeForm\" method=\"post\" action=\"home\">");
        out.println("<input type=\"hidden\" id='order' name=\"order\" value=\"personId\"/>");
        out.println("<input type=\"hidden\" id='gwa' name=\"gwa\" value=\"\"/>");
        out.println("<b>Sort:</b> <input style='height:20px; width:100px' "+
                    "type=\"submit\" value=\"View All\" oncLick=\"myOrder(this)\"/>");
        out.println("<input style='height:20px; width:100px' type=\"submit\""+
                    " value=\"Lastname\" oncLick=\"myOrder(this)\"/>");
        out.println("<input style='height:20px; width:100px' type=\"submit\""+
                    "value=\"Birthday\" oncLick=\"myOrder(this)\"/>");
        out.println("<input style='height:20px; width:100px' type=\"submit\""+
                    " value=\"GWA\" oncLick=\"myGwa()\"/>");
        out.println("</form></div>");
        if(order.equals("")){
            order = "personId";
        }
        display.printPerson(out, field, searchText, order, gwa);

        out.println("<div align='center'><button onclick=\"location.href = "+
                    "'http://localhost:8080/addPerson';\">Add Employee</button></div>");
        out.println("<script>function mySearch(button)"+
                    " {var hiddenInput = document.getElementById(\"category\");");
        out.println("if(button.value==\"Role\") { hiddenInput.value = 'roles.pos';}}");
        out.println("function myOrder(button) "+
                    "{var hiddenInput = document.getElementById(\"order\");");
        out.println("if(button.value==\"Lastname\") { hiddenInput.value = 'lastName';}");
        out.println("else if(button.value==\"Birthday\"){ hiddenInput.value = 'birthday';}");
        out.println("else{hiddenInput.value = 'personId';}}");
        out.println("function myGwa() "+
                    "{var hiddenInput = document.getElementById(\"gwa\");");
        out.println("hiddenInput.value = 'gwa';}");
        out.println("</script>");

        out.println("</body></html>");
        out.close();
    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
                      throws ServletException, IOException {
        doGet(request, response);
    }
}
