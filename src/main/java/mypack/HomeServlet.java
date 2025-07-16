package mypack;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        // Get cookie
        String cookieUsername = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    cookieUsername = c.getValue();
                }
            }
        }

        // Get session
        HttpSession session = req.getSession();
        session.setAttribute("username", cookieUsername);

        if (cookieUsername != null) {
            out.println("<h2>Welcome, " + cookieUsername + " (from Cookie)</h2>");
            out.println("<h3>Session also created for user: " + session.getAttribute("username") + "</h3>");
        } else {
            out.println("<h2>No cookie found. Please <a href='login.html'>Login</a> again.</h2>");
        }
    }
}