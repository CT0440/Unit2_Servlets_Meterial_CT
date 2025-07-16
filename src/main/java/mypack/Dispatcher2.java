package mypack;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class Dispatcher2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("username");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Welcome, " + name + "!</h2>");
    }

    // Optional support for GET if needed
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}