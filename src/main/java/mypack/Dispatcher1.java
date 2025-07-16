package mypack;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class Dispatcher1 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("username");

        // Forward request to Dispatcher2
        RequestDispatcher rd = request.getRequestDispatcher("second");
        rd.forward(request, response);
    }

    // To support GET requests if user directly opens the URL
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}